package Lab3;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "Lab1.SoapServiceFault")
public class OutOfRangeException extends Exception {
    private static final long serialVersionUID = -6647544772732631047L; 
    private final SoapServiceFault fault; 
    
    public OutOfRangeException(String message, SoapServiceFault fault) { 
        super(message); 
        this.fault = fault; 
    } 
 
    public OutOfRangeException(String message, SoapServiceFault fault, Throwable cause) { 
        super(message, cause); 
        this.fault = fault; 
    } 
 
    public SoapServiceFault getFaultInfo() { 
        return fault; 
    } 
}

