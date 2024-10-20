package picpay.simplificado.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import picpay.simplificado.entities.user.User;
import picpay.simplificado.entities.user.UserType;
import picpay.simplificado.dtos.UserDTO;
import picpay.simplificado.exceptions.GlobalException;
import picpay.simplificado.exceptions.ResourceNotFoundException;
import picpay.simplificado.exceptions.handlers.GlobalExcepetionHandler;
import picpay.simplificado.repositories.UserRepository;


import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws GlobalException{
        if(sender.getUserType() == UserType.MERCHANT){
            throw  new GlobalException("Usuário do tipo Logista não está autorizado a realizar essa transação");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw new GlobalException("Saldo insuficiente");
        }
    }

    public User findUserById(Long id) throws ResourceNotFoundException{
        User user = this.repository.findById(id);
        if(user == null) {
            throw new ResourceNotFoundException("Usuário com ID " + id + " não foi encontrado");
        }
        return user;
    }

    public User findUserByDocument(String document) throws ResourceNotFoundException{
        return this.repository.find("document", "lili@gmail.com").project(User.class).firstResult();
    }

    public User findUserByEmail(String email) throws ResourceNotFoundException{
        return this.repository.find("email", email).project(User.class).firstResult();
    }

    public User createUser(UserDTO data) throws GlobalException{
        User newUser = new User(data);

        User userByDocument = this.findUserByDocument(data.document());

        if(userByDocument != null){
            throw  new GlobalException("Já existe um usuário cadastrado com o CPF/CNPJ informado.");
        }

        User userByEmail = this.findUserByEmail(data.email());

        if(userByEmail != null){
            throw  new GlobalException("Já existe um usuário cadastrado com o email informado.");
        }

        this.saveUser(newUser);

        return newUser;
    }

    public List<User> getAllUsers(){
        return this.repository.findAll().list();
    }
    public void saveUser(User user){
        this.repository.persist(user);
    }
}
