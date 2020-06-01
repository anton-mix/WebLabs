package Lab3;

public class SoapServiceFault {
    private static final String DEFAULT_MESSAGE = "Exeption in service";
    
    String message;
    
    public String getMessage(){
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public static SoapServiceFault defaultInstance() {
        SoapServiceFault fault = new SoapServiceFault();
        fault.message = DEFAULT_MESSAGE;
        return fault;
    }
}
