
package lab3;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "SoapManager", targetNamespace = "http://Lab3/", wsdlLocation = "http://localhost:8080/WebLab3.7/SoapManager?wsdl")
public class SoapManager_Service
    extends Service
{

    private final static URL SOAPMANAGER_WSDL_LOCATION;
    private final static WebServiceException SOAPMANAGER_EXCEPTION;
    private final static QName SOAPMANAGER_QNAME = new QName("http://Lab3/", "SoapManager");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/WebLab3.7/SoapManager?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SOAPMANAGER_WSDL_LOCATION = url;
        SOAPMANAGER_EXCEPTION = e;
    }

    public SoapManager_Service() {
        super(__getWsdlLocation(), SOAPMANAGER_QNAME);
    }

    public SoapManager_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), SOAPMANAGER_QNAME, features);
    }

    public SoapManager_Service(URL wsdlLocation) {
        super(wsdlLocation, SOAPMANAGER_QNAME);
    }

    public SoapManager_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SOAPMANAGER_QNAME, features);
    }

    public SoapManager_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SoapManager_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SoapManager
     */
    @WebEndpoint(name = "SoapManagerPort")
    public SoapManager getSoapManagerPort() {
        return super.getPort(new QName("http://Lab3/", "SoapManagerPort"), SoapManager.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SoapManager
     */
    @WebEndpoint(name = "SoapManagerPort")
    public SoapManager getSoapManagerPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://Lab3/", "SoapManagerPort"), SoapManager.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SOAPMANAGER_EXCEPTION!= null) {
            throw SOAPMANAGER_EXCEPTION;
        }
        return SOAPMANAGER_WSDL_LOCATION;
    }

}
