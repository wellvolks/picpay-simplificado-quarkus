package picpay.simplificado.services;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import picpay.simplificado.models.AuthorizationResponse;

@Path("")
@RegisterClientHeaders
@RegisterRestClient(configKey = "external-authorization")
public interface AuthorizationService {

    @GET
    @Path("/authorize")
    @Produces(MediaType.APPLICATION_JSON)
    AuthorizationResponse isAuthorizate();
}