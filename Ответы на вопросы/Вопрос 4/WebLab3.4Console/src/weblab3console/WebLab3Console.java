package weblab3console;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import lab3.MissFormatException;
import lab3.OutOfRangeException;
import lab3.SoapManager_Service;
import lab3.SqlObject;

public class WebLab3Console {
    public static void main(String[] args) throws IOException, MissFormatException, OutOfRangeException, InterruptedException {
        String url = "http://localhost:8080/WebLab3/SoapManager?wsdl";
        SoapManager_Service prob = new SoapManager_Service(new URL(url));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String operation;
        OtherThread probThread;
        while(true) {
            System.out.println("Chouse operation:\n1.Select;\n2.Insert;\n3.Update;\n4.Delete;\n5.Exit");
            operation = reader.readLine();
            
            probThread = new OtherThread();
            probThread.setProb(prob);
            probThread.setNumberOperation(operation);
            
            switch(operation) {
                case "1":
                    probThread.setBuffId(readFild(reader, "Id")); 
                    probThread.setBuffName(readFild(reader, "Name")); 
                    probThread.setBuffSecondName(readFild(reader, "SecondName")); 
                    probThread.setBuffAge(readFild(reader, "Age")); 
                    probThread.setBuffSex(readFild(reader, "Sex"));   
                    probThread.start();
                    List<SqlObject> buff;
                    
                    while(true){
                        
                    buff = probThread.getRes2();
                    if(buff !=  null || !probThread.isAlive()){
                        break;
                    }
                    System.out.println("Wating...");
                    Thread.sleep(1000);
                    }
                    if(buff!= null) {
                        for(int i = 0; i < buff.size(); i++) {
                            System.out.println(i + "." + buff.get(i).toString());
                        }
                    }
                    break;
                case "2":                  
                    probThread.setBuffId(readFild(reader, "Id")); 
                    probThread.setBuffName(readFild(reader, "Name")); 
                    probThread.setBuffSecondName(readFild(reader, "SecondName")); 
                    probThread.setBuffAge(readFild(reader, "Age")); 
                    probThread.setBuffSex(readFild(reader, "Sex"));   
                    String res2 = "";
                    while(true){    
                    res2 = probThread.getRes();
                    if(!"".equals(res2) || !probThread.isAlive()){
                        break;
                    }
                    System.out.println("Wating...");
                    Thread.sleep(1000);
                    }
                    
                    System.out.println(res2);
                    break;
                case "3":
                    probThread.setBuffId(readFild(reader, "Id")); 
                    probThread.setBuffName(readFild(reader, "Name")); 
                    probThread.setBuffSecondName(readFild(reader, "SecondName")); 
                    probThread.setBuffAge(readFild(reader, "Age")); 
                    probThread.setBuffSex(readFild(reader, "Sex"));   
                    
                    String res3 = "";
                    while(true){    
                    res3 = probThread.getRes();
                    if(!"".equals(res3) || !probThread.isAlive()){
                        break;
                    }
                    System.out.println("Wating...");
                    Thread.sleep(1000);
                    }
                    
                    System.out.println(res3);
                    break;
                case "4":
                    probThread.setBuffId(readFild(reader, "Id")); 
                    
                    String res4 = "";
                    while(true){    
                    res4 = probThread.getRes();
                    if(!"".equals(res4) || !probThread.isAlive()){
                        break;
                    }
                    System.out.println("Wating...");
                    Thread.sleep(1000);
                    }
                    
                    System.out.println(res4);
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
