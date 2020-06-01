package weblab1console;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import lab3.MissFormatException;
import lab3.OutOfRangeException;
import lab3.SoapManager;
import lab3.SoapManager_Service;
import lab3.SqlObject;

public class WebLab3Console {
    public static void main(String[] args) throws IOException, MissFormatException, OutOfRangeException {
        String url = "http://localhost:8080/WebLab3.7/SoapManager?wsdl";
        SoapManager prob = new SoapManager_Service(new URL(url)).getSoapManagerPort();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String operation, buffId = "", buffName = "", buffSecondName = "", buffAge = "", buffSex = "";
        
        Map<String, Object> req_ctx = ((BindingProvider)prob).getRequestContext();
        req_ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8080/WebLab3.7/SoapManager?wsdl");

        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Username", Collections.singletonList("anton"));
        headers.put("Password", Collections.singletonList("noskov"));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
        
        while(true) {
            System.out.println("Chouse operation:\n1.Select;\n2.Insert;\n3.Update;\n4.Delete;\n5.Exit");
            operation = reader.readLine();
            
            switch(operation) {
                case "1":
                    buffId         = readFild(reader, "Id");
                    buffName       = readFild(reader, "Name");
                    buffSecondName = readFild(reader, "SecondName");
                    buffAge        = readFild(reader, "Age");
                    buffSex        = readFild(reader, "Sex");    
                    
                    List<SqlObject> buff = prob.select(buffId, buffName, buffSecondName, buffAge, buffSex);
                    for(int i = 0; i < buff.size(); i++) {
                        System.out.println(i + "." + buff.get(i).toString());
                    }
                    break;
                case "2":                  
                    buffId         = readFild(reader, "Id");
                    buffName       = readFild(reader, "Name");
                    buffSecondName = readFild(reader, "SecondName");
                    buffAge        = readFild(reader, "Age");
                    buffSex        = readFild(reader, "Sex");    
                    
                    System.out.println(prob.insert(buffId, buffName, buffSecondName, buffAge, buffSex));
                    break;
                case "3":
                    buffId         = readFild(reader, "Id");
                    buffName       = readFild(reader, "Name");
                    buffSecondName = readFild(reader, "SecondName");
                    buffAge        = readFild(reader, "Age");
                    buffSex        = readFild(reader, "Sex");  
                    
                    System.out.println(prob.update(buffId, buffName, buffSecondName, buffAge, buffSex));
                    break;
                case "4":
                    buffId         = readFild(reader, "Id");
                    
                    System.out.println(prob.delete(buffId));
                    break;
                case "5":
                    return;
                default:
                  System.out.println("Try again");
            }
        }
    }
    
    static String readFild(BufferedReader reader, String nameVal) throws IOException {
        System.out.println("Input " + nameVal + ":");
        return reader.readLine();
    }
}
