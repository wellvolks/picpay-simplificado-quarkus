package picpay.simplificado.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import picpay.simplificado.entities.transaction.Transaction;
import picpay.simplificado.entities.user.User;
import picpay.simplificado.dtos.TransactionDTO;
import picpay.simplificado.exceptions.GlobalException;
import picpay.simplificado.models.AuthorizationResponse;
import picpay.simplificado.repositories.TransacationRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApplicationScoped
public class TransactionService {

    @Inject
    private UserService userService;

    @Inject
    private TransacationRepository repository;

    @Inject
    private NotificationService notificationService;

    @RestClient
    AuthorizationService authorizationService;

    public Transaction createTransaction(TransactionDTO transaction) throws GlobalException {
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());

        if(!isAuthorized){
            throw new GlobalException("Transação não autorizada.");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.repository.persist(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transação realizada com sucesso");
        this.notificationService.sendNotification(receiver, "Transação recebida com sucesso");

        return newTransaction;
    }

    public boolean authorizeTransaction(User sender, BigDecimal value) throws GlobalException{
        AuthorizationResponse authorization = authorizationService.isAuthorizate();

        if(authorization == null || !authorization.getStatus().equals("success")){
            throw new GlobalException("Serviço de autotização está fora do ar.");
        }

        return authorization.getData().isAuthorization();
    }
}
