
package Lab1.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Delete", namespace = "urn:SoapManager")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Delete", namespace = "urn:SoapManager")
public class Delete {

    @XmlElement(name = "Id", namespace = "")
    private String id;

    /**
     * 
     * @return
     *     returns String
     */
    public String getId() {
        return this.id;
    }

    /**
     * 
     * @param id
     *     the value for the id property
     */
    public void setId(String id) {
        this.id = id;
    }

}
