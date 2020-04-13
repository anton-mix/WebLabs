package Lab4;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
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
    
    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }
     
    public String getSecondName() {
        return SecondName;
    }
    
    public String getAge() {
        return Age;
    }
    
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
    
    @Override 
    public String toString() { 
        return "SQLObject{" + "Id=" + Id + ", Name=" + Name + ", SecondName=" + SecondName + ", Age=" + Age + ", Sex=" + Sex + '}'; 
    } 
}
