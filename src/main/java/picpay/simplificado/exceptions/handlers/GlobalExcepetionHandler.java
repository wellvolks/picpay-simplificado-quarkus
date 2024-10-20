package picpay.simplificado.exceptions.handlers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import picpay.simplificado.exceptions.GlobalException;
import picpay.simplificado.models.ErrorResponse;


@Provider
public class GlobalExcepetionHandler implements ExceptionMapper<GlobalException> {


    @Override
    public Response toResponse(GlobalException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Ocorreu um erro no servidor interno.");
        errorResponse.setDetails(exception.getMessage());
        errorResponse.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorResponse)
                .build();
    }

}
