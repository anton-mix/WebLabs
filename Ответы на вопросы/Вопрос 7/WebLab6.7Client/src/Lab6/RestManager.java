package Lab6;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import java.util.List;
import javax.ws.rs.core.MediaType;

public class RestManager {
    
    private Client client;
    String BASE_URI = "http://localhost:8080/WebLab6.7/webresources";
    
    public RestManager() {
        client = Client.create(); 
        BASE_URI += "/Metods";
    }
    
    List<SQLObject> Select(String Id, String Name, String SecondName, String Age, String Sex, String Authorization) {
        GenericType<List<SQLObject>> type = new GenericType<List<SQLObject>>() {}; 
        return SendMessage(BASE_URI + "/Select", new String[] {"Id", "Name", "SecondName", "Age", "Sex"}, new String[] {Id, Name, SecondName, Age, Sex}, Authorization).getEntity(type);
    } 
    
    String Insert(String Id, String Name, String SecondName, String Age, String Sex, String Authorization) {
        GenericType<String> type = new GenericType<String>() {}; 
        return SendMessage(BASE_URI + "/Insert", new String[] {"Id", "Name", "SecondName", "Age", "Sex"}, new String[] {Id, Name, SecondName, Age, Sex}, Authorization).getEntity(type);
    } 
    
    String Update(String Id, String Name, String SecondName, String Age, String Sex, String Authorization) {
        GenericType<String> type = new GenericType<String>() {}; 
        return SendMessage(BASE_URI + "/Update", new String[] {"Id", "Name", "SecondName", "Age", "Sex"}, new String[] {Id, Name, SecondName, Age, Sex}, Authorization).getEntity(type);
    } 
    
    String Delete(String id, String Authorization) {
        GenericType<String> type = new GenericType<String>() {}; 
        return SendMessage(BASE_URI + "/Delete", new String[] {"Id"}, new String[] {id}, Authorization).getEntity(type);
    } 
    
    ClientResponse SendMessage(String URL , String[] name, String[] param, String Authorization) {
        WebResource webResource = client.resource(URL); 

        for(int i = 0; i < name.length; i++)  {
            webResource = webResource.queryParam(name[i], param[i]); 
        }
        
        ClientResponse response =  webResource.accept(MediaType.APPLICATION_ATOM_XML).header("Authorization", "Basic " + Authorization).get(ClientResponse.class); 
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) { 
            GenericType<String> type = new GenericType<String>() {}; 
            throw new IllegalStateException(response.getEntity(type)); 
        } 
        return response;
    }   
}
