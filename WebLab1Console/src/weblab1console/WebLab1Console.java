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
        String filds = "";

        System.out.println("Input Id:");
        try {
            filds += "<Id>" + Integer.parseInt(reader.readLine()) + "</Id>";
        }
        catch (Exception ex) {
            filds += "<Id></Id>";
        }
        
        System.out.println("Input Name:");
        filds += "<Name>" + reader.readLine() + "</Name>";
        
        System.out.println("Input SecondName:");
        filds += "<SecondName>" + reader.readLine() + "</SecondName>";
        
        System.out.println("Input Age:");
        try {
            filds += "<Age>" + reader.readLine() + "</Age>";
        }
        catch (Exception ex) {
            filds += "<Age></Age>";
        }
        
        System.out.println("Input Sex:");
        filds += "<Sex>" + reader.readLine() + "</Sex>";
        
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
            + "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:SoapManager\">"
                + "<soapenv:Header/>"
                + "<soapenv:Body>"
                    + "<urn:SoapRequest>"
                        + filds
                    + "</urn:SoapRequest>"
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
