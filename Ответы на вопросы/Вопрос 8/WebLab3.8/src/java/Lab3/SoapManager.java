package Lab3;

import com.sun.faces.mgbean.ErrorBean;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService(serviceName="SoapManager")
public class SoapManager {
    int maxConnection = 2, curConnection = 0;
    private final Object block = new Object();
    
    @WebMethod(operationName = "Select")
    public SQLObject[] Select(@WebParam(name = "Id") String Id, 
        @WebParam(name = "Name") String Name,
        @WebParam(name = "SecondName") String SecondName,
        @WebParam(name = "Age") String Age,
        @WebParam(name = "Sex") String Sex) throws MissFormatException, ThrottlingException, InterruptedException {
        SQLObject[] answer;
        
        checkConnection();
        
        Thread.sleep(5000);
        try {
            if(Id.length() != 0) {
                Integer.parseInt(Id);
            }
            if(Sex.length() != 0) {
                Integer.parseInt(Sex);
            }
        }
        catch(Exception ex) {
            throw new MissFormatException("Wrong format attribute id or sex", SoapServiceFault.defaultInstance());
        }
        
        try  {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/weblabs?useSSL=false","xim","ew23ew23");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM People " + 
                    "WHERE upper(Id) like upper('%" + Id + "%') AND " +
                    "upper(Name) like upper('%" + Name + "%') AND " + 
                    "upper(SecondName) like upper('%" + SecondName + "%') AND " + 
                    "upper(Age) like upper('%" + Age + "%') AND " +
                    "upper(Sex) like upper('%" + Sex + "%')");
            rs.last(); 
            answer = new SQLObject[rs.getRow()];
            rs.beforeFirst();
            int i = 0;
            while(rs.next())  {
                answer[i] = new SQLObject(rs.getString("Id"), rs.getString("Name"), rs.getString("SecondName"), rs.getString("Age"), rs.getString("Sex"));
                i++;
            }
            rs.close();
            st.close();
            con.close();
        }
        catch(ClassNotFoundException | SQLException ex)  {    
            answer = new SQLObject[1];
            answer[0] = new SQLObject();
            answer[0].setName(ex.toString());
        }
        finally {
            connectionOut();
        }
        
        return answer;
    }
    
    @WebMethod(operationName = "Insert")
    public String Insert(@WebParam(name = "Id") String Id, 
        @WebParam(name = "Name") String Name,
        @WebParam(name = "SecondName") String SecondName,
        @WebParam(name = "Age") String Age,
        @WebParam(name = "Sex") String Sex) throws MissFormatException, ThrottlingException {
        int answer = 0;
        String sql1 = "", sql2 = "";
        
        checkConnection();
        
        if(Id.length() != 0) {
            try {
                Integer.parseInt(Id);
            }
            catch(Exception ex) {
                throw new MissFormatException("Wrong format attribute id", SoapServiceFault.defaultInstance());
            }
            
            sql1 = "Id";
            sql2 = Id;
        }
        
        if(Name.length() != 0){
            if(sql1.length() != 0) {
                sql1 += ",";
                sql2 += ",";
            }
            sql1 += "Name";
            sql2 += "'" + Name + "'";
        }
        
        if(SecondName.length() != 0){
            if(sql1.length() != 0) {
                sql1 += ",";
                sql2 += ",";
            }
            sql1 += "SecondName";
            sql2 += "'" + SecondName + "'";
        }
                
        if(Age.length() != 0){
            if(sql1.length() != 0) {
                sql1 += ",";
                sql2 += ",";
            }
            sql1 += "Age";
            sql2 += Age;
        }
        
        if(Sex.length() != 0){
            try {
                Integer.parseInt(Sex);
            }
            catch(Exception ex) {
                throw new MissFormatException("Wrong format attribute Sex", SoapServiceFault.defaultInstance());
            }
            
            if(sql1.length() != 0) {
                sql1 += ",";
                sql2 += ",";
            }
            sql1 += "Sex";
            sql2 += "'" + Sex + "'";
        }
        
        try  {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/weblabs?useSSL=false","xim","ew23ew23");
            PreparedStatement st = con.prepareStatement("INSERT INTO People(" + sql1 + ") VALUES(" + sql2 + ")", Statement.RETURN_GENERATED_KEYS);
            answer = st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if(answer == 1 && rs.next())
                answer = rs.getInt(1);
            rs.close();
            st.close();
            con.close();
        }
        catch(ClassNotFoundException | SQLException ex)  {
            return ex.toString();
        }
        finally {
            connectionOut();
        }
        return String.valueOf(answer);
    }
    
    @WebMethod(operationName = "Update")
    public String Update(@WebParam(name = "Id") String Id, 
        @WebParam(name = "Name") String Name,
        @WebParam(name = "SecondName") String SecondName,
        @WebParam(name = "Age") String Age,
        @WebParam(name = "Sex") String Sex) throws MissFormatException, OutOfRangeException, ThrottlingException, InterruptedException {
        String sql = "";
        
        checkConnection();
        
        try {
            if(Id.length() != 0) {
                Integer.parseInt(Id);
            }
            if(Sex.length() != 0) {
                Integer.parseInt(Sex);
            }
        }
        catch(Exception ex) {
            throw new MissFormatException("Wrong format attribute id or sex", SoapServiceFault.defaultInstance());
        }
        
        if(Select(Id,"","","","").length == 0){
            throw new OutOfRangeException("Id not found", SoapServiceFault.defaultInstance());
        }
        
        
        if(Name.length() != 0){
            sql += ((sql.length() != 0)?",":"") + "Name='" + Name + "'";
        }
        
        if(SecondName.length() != 0){
            sql += ((sql.length() != 0)?",":"") + "SecondName='" + SecondName + "'";
        }
                
        if(Age.length() != 0){
            sql += ((sql.length() != 0)?",":"") + "Age=" + Age;
        }
        
        if(Sex.length() != 0){
            sql += ((sql.length() != 0)?",":"") + "Sex='" + Sex + "'";
        }
        
        try  {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/weblabs?useSSL=false","xim","ew23ew23");
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE People SET " + sql + " WHERE Id = " + Id);
            st.close();
            con.close();
        }
        catch(ClassNotFoundException | SQLException ex)  {
            return ex.toString();
        }
        finally {
            connectionOut();
        }
        return "Ok";
    }
    
    @WebMethod(operationName = "Delete")
    public String Delete(@WebParam(name = "Id") String Id) throws MissFormatException, OutOfRangeException, ThrottlingException, InterruptedException {
        
        checkConnection();
        
        if(Select(Id,"","","","").length == 0){
            throw new OutOfRangeException("Id not found", SoapServiceFault.defaultInstance());
        }
                
        try  {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/weblabs?useSSL=false","xim","ew23ew23");
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM People WHERE id = " + Id);
            st.close();
            con.close();
        }
        catch(ClassNotFoundException | SQLException ex)  {
            return ex.toString();
        }
        finally {
            connectionOut();
        }
        return "Ok";
    }
    
    public void checkConnection() throws ThrottlingException {
        synchronized (block) {
            if(curConnection < maxConnection) {
                curConnection++;
            }
            else {
                throw new ThrottlingException("Max client! Conection fault", SoapServiceFault.defaultInstance());
            }
        }
    }
    
    public void connectionOut(){
        synchronized (block) {
            curConnection--;
        }
    }
}