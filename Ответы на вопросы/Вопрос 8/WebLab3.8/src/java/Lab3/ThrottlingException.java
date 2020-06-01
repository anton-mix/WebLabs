package Lab3;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "Lab1.SoapServiceFault")
public class ThrottlingException extends Exception {
    private static final long serialVersionUID = -6647544772732631046L; 
    private final SoapServiceFault fault; 
    
    public ThrottlingException(String message, SoapServiceFault fault) { 
        super(message); 
        this.fault = fault; 
    } 
 
    public ThrottlingException(String message, SoapServiceFault fault, Throwable cause) { 
        super(message, cause); 
        this.fault = fault; 
    } 

    public SoapServiceFault getFaultInfo() { 
        return fault; 
    } 
}
