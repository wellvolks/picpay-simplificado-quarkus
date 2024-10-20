package picpay.simplificado.exceptions.handlers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import picpay.simplificado.exceptions.DataIntegrityViolationException;
import picpay.simplificado.exceptions.ResourceNotFoundException;
import picpay.simplificado.models.ErrorResponse;

@Provider
public class DataIntegrityViolationExceptionHandler implements ExceptionMapper<DataIntegrityViolationException> {

    @Override
    public Response toResponse(DataIntegrityViolationException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setStatus(Response.Status.NOT_FOUND.getStatusCode());

        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .build();
    }
}
