
package lab1;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "MissFormatException", targetNamespace = "http://Lab1/")
public class MissFormatException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private SoapServiceFault faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public MissFormatException(String message, SoapServiceFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public MissFormatException(String message, SoapServiceFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: lab1.SoapServiceFault
     */
    public SoapServiceFault getFaultInfo() {
        return faultInfo;
    }

}
