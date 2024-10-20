package picpay.simplificado.controllers;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;
import picpay.simplificado.entities.user.User;
import picpay.simplificado.dtos.UserDTO;
import picpay.simplificado.services.UserService;

import java.util.List;


@Path(("/users"))
public class UserController {

    @Inject
    private UserService userService;

    @POST
    @Transactional
    public RestResponse<User> createUser(UserDTO user){
        User newUser = userService.createUser(user);

        return RestResponse.ResponseBuilder.ok(newUser, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @GET
    public RestResponse<List<User>> getAllUsers(){
        List<User> users = this.userService.getAllUsers();

        return RestResponse.ResponseBuilder.ok(users, MediaType.APPLICATION_JSON_TYPE).build();
    }
}
