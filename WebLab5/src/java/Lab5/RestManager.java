package Lab5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/Metods")
public class RestManager {
    
    @GET
    @Path("/Select") 
    public SQLObject[] Select(@QueryParam("Id") String Id, 
        @QueryParam("Name") String Name,
        @QueryParam("SecondName") String SecondName,
        @QueryParam("Age") String Age,
        @QueryParam("Sex") String Sex) {
        SQLObject[] answer;
        
        Id = (Id == null)?"":Id;
        Name = (Name == null)?"":Name;
        SecondName = (SecondName == null)?"":SecondName;
        Age = (Age == null)?"":Age;
        Sex = (Sex == null)?"":Sex;
        
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
        return answer;
    }
    
    @GET
    @Path("/Insert") 
    public String Insert(@QueryParam("Id") String Id, 
        @QueryParam("Name") String Name,
        @QueryParam("SecondName") String SecondName,
        @QueryParam("Age") String Age,
        @QueryParam("Sex") String Sex) {
        int answer = 0;
        String sql1 = "", sql2 = "";
        
        if(Id != null && Id.length() != 0) {           
            sql1 = "Id";
            sql2 = Id;
        }
        
        if(Name != null && Name.length() != 0){
            if(sql1.length() != 0) {
                sql1 += ",";
                sql2 += ",";
            }
            sql1 += "Name";
            sql2 += "'" + Name + "'";
        }
        
        if(SecondName != null && SecondName.length() != 0){
            if(sql1.length() != 0) {
                sql1 += ",";
                sql2 += ",";
            }
            sql1 += "SecondName";
            sql2 += "'" + SecondName + "'";
        }
                
        if(Age != null && Age.length() != 0){
            if(sql1.length() != 0) {
                sql1 += ",";
                sql2 += ",";
            }
            sql1 += "Age";
            sql2 += Age;
        }
        
        if(Sex != null && Sex.length() != 0){           
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
        return String.valueOf(answer);
    }
    
    @GET
    @Path("/Update") 
    public String Update(@QueryParam("Id") String Id, 
        @QueryParam("Name") String Name,
        @QueryParam("SecondName") String SecondName,
        @QueryParam("Age") String Age,
        @QueryParam("Sex") String Sex) {
        String sql = "";
        
        if(Name != null && Name.length() != 0){
            sql += ((sql.length() != 0)?",":"") + "Name='" + Name + "'";
        }
        
        if(SecondName != null && SecondName.length() != 0){
            sql += ((sql.length() != 0)?",":"") + "SecondName='" + SecondName + "'";
        }
                
        if(Age != null && Age.length() != 0){
            sql += ((sql.length() != 0)?",":"") + "Age=" + Age;
        }
        
        if(Sex != null && Sex.length() != 0){
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
        return "Ok";
    }
    
    @GET
    @Path("/Delete") 
    public String Delete(@QueryParam("Id") String Id){
                
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
        return "Ok";
    }
    
}
