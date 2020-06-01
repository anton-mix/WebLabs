
package Lab3.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Select", namespace = "http://Lab3/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Select", namespace = "http://Lab3/", propOrder = {
    "id",
    "name",
    "secondName",
    "age",
    "sex"
})
public class Select {

    @XmlElement(name = "Id", namespace = "")
    private String id;
    @XmlElement(name = "Name", namespace = "")
    private String name;
    @XmlElement(name = "SecondName", namespace = "")
    private String secondName;
    @XmlElement(name = "Age", namespace = "")
    private String age;
    @XmlElement(name = "Sex", namespace = "")
    private String sex;

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

    /**
     * 
     * @return
     *     returns String
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * @param name
     *     the value for the name property
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getSecondName() {
        return this.secondName;
    }

    /**
     * 
     * @param secondName
     *     the value for the secondName property
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getAge() {
        return this.age;
    }

    /**
     * 
     * @param age
     *     the value for the age property
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getSex() {
        return this.sex;
    }

    /**
     * 
     * @param sex
     *     the value for the sex property
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

}
