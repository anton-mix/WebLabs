package Lab6;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider 
public class MissFormatExceptionMapper  implements ExceptionMapper<MissFormatException> {
    
    @Override 
    public Response toResponse(MissFormatException e) { 
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();   
    }
}
