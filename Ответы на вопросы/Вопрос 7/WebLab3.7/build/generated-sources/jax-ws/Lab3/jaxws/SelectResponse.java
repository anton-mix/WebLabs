
package Lab3.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import Lab3.SQLObject;

@XmlRootElement(name = "SelectResponse", namespace = "http://Lab3/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SelectResponse", namespace = "http://Lab3/")
public class SelectResponse {

    @XmlElement(name = "return", namespace = "", nillable = true)
    private SQLObject[] _return;

    /**
     * 
     * @return
     *     returns SQLObject[]
     */
    public SQLObject[] getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(SQLObject[] _return) {
        this._return = _return;
    }

}
