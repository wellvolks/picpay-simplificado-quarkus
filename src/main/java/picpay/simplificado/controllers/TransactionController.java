package picpay.simplificado.controllers;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import picpay.simplificado.entities.transaction.Transaction;
import picpay.simplificado.dtos.TransactionDTO;
import picpay.simplificado.services.TransactionService;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;
@Path("/transactions")
public class TransactionController {

    @Inject
    private TransactionService transactionService;

    @POST
    @Transactional
    public RestResponse<Transaction> createTransaction(TransactionDTO transaction) throws Exception{
        Transaction newTransaction = this.transactionService.createTransaction(transaction);

        return ResponseBuilder.ok(newTransaction, MediaType.APPLICATION_JSON_TYPE).build();
    }
}
