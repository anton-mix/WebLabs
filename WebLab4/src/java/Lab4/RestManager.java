package Lab4;

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
}
