package Lab6;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider 
public class OutOfRangeExceptionMapper implements ExceptionMapper<OutOfRangeException> { 
 
    @Override 
    public Response toResponse(OutOfRangeException e) { 
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();   
    }
}