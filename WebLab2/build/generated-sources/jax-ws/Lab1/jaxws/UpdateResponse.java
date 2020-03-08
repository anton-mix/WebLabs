
package Lab1.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "UpdateResponse", namespace = "urn:SoapManager")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateResponse", namespace = "urn:SoapManager")
public class UpdateResponse {

    @XmlElement(name = "Answer", namespace = "")
    private String answer;

    /**
     * 
     * @return
     *     returns String
     */
    public String getAnswer() {
        return this.answer;
    }

    /**
     * 
     * @param answer
     *     the value for the answer property
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
