package weblab1console;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebLab1Console {
    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8080/SoapManager/SoapManager";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filds = "", operation = "", function = "";
        while(true) {
            filds = function = "";
            System.out.println("Chouse operation:\n1.Select;\n2.Insert;\n3.Update;\n4.Delete;\n5.Exit");
            operation = reader.readLine();
            
            switch(operation) {
                case "1":
                    function = "Select";
                    
                    filds += readInt(reader, "Id");
                    filds += readString(reader, "Name");
                    filds += readString(reader, "SecondName");
                    filds += readInt(reader, "Age");
                    filds += readString(reader, "Sex");                 
                    break;
                case "2":
                    function = "Insert";
                    
                    filds += readInt(reader, "Id");
                    filds += readString(reader, "Name");
                    filds += readString(reader, "SecondName");
                    filds += readInt(reader, "Age");
                    filds += readString(reader, "Sex");  
                    break;
                case "3":
                    filds += readInt(reader, "Id");
                    if(filds.endsWith("<Id></Id>")){
                        System.out.println("Wrong Id. Try again");
                    }
                    else {
                        function = "Update";  
                        filds += readString(reader, "Name");
                        filds += readString(reader, "SecondName");
                        filds += readInt(reader, "Age");
                        filds += readString(reader, "Sex");  
                    }
                    break;
                case "4":
                    filds += readInt(reader, "Id");
                    if(filds.endsWith("<Id></Id>")){
                        System.out.println("Wrong Id. Try again");
                    }
                    else {
                        function = "Delete";
                    }
                    break;
                case "5":
                    return;
                default:
                  function = "";
                  System.out.println("Try again");
            }
            
            if (function.length() != 0) {
                String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                        + "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:SoapManager\">"
                        + "<soapenv:Header/>"
                        + "<soapenv:Body>"
                        + "<urn:" + function + ">"
                        + filds
                        + "</urn:" + function + ">"
                        + "</soapenv:Body>"
                        + "</soapenv:Envelope>";

                try {
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
                    con.setDoOutput(true);
                    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                    wr.writeBytes(xml);
                    wr.flush();
                    wr.close();
                    String responseStatus = con.getResponseMessage();
                    System.out.println(responseStatus);
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    System.out.println("response:\n" + response.toString().replaceAll("><", ">\n<"));
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
    
    static String readInt(BufferedReader reader, String nameVal) {
        System.out.println("Input " + nameVal + ":");
        try {
            return "<" + nameVal + ">" + Integer.parseInt(reader.readLine()) + "</" + nameVal + ">";
        }
        catch (Exception ex) {
            return "<" + nameVal + "></" + nameVal +">";
        }
    }
    
    static String readString(BufferedReader reader, String nameVal) throws IOException {
        System.out.println("Input " + nameVal + ":");
        return "<" + nameVal + ">" + reader.readLine() + "</" + nameVal + ">";
    }
}
