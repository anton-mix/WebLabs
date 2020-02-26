package Lab1;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(namespace = "urn:SoapManager")
@XmlType(name = "SQLObject")
public class SQLObject {
    private String Id, Name, SecondName, Age, Sex;

    public SQLObject(){
        this.Id = this.Name = this.SecondName = this.Age = this.Sex = "";
    }
    
    public SQLObject(String Id, String Name, String SecondName, String Age, String Sex){
        this.Id = Id;
        this.Name = Name;
        this.SecondName = SecondName;
        this.Age = Age;
        this.Sex = Sex;
    }
    
    @XmlElement(name = "Id")
    public String getId() {
        return Id;
    }

    @XmlElement(name = "Name")
    public String getName() {
        return Name;
    }
    
    @XmlElement(name = "SecondName")    
    public String getSecondName() {
        return SecondName;
    }
    
    @XmlElement(name = "Age")  
    public String getAge() {
        return Age;
    }
    
    @XmlElement(name = "Sex") 
    public String getSex() {
        return Sex;
    }
        
    public void setId(String Id) {
        this.Id = Id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setSecondName(String SecondName) {
        this.SecondName = SecondName;
    }

    public void setAge(String Age) {
        this.Age = Age;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }
        
        
}
