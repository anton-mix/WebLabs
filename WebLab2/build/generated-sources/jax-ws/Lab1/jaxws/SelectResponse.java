
package Lab1.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import Lab1.SQLObject;

@XmlRootElement(name = "SelectResponse", namespace = "urn:SoapManager")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SelectResponse", namespace = "urn:SoapManager")
public class SelectResponse {

    @XmlElement(name = "Answer", namespace = "", nillable = true)
    private SQLObject[] answer;

    /**
     * 
     * @return
     *     returns SQLObject[]
     */
    public SQLObject[] getAnswer() {
        return this.answer;
    }

    /**
     * 
     * @param answer
     *     the value for the answer property
     */
    public void setAnswer(SQLObject[] answer) {
        this.answer = answer;
    }

}
