package weblab1console;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import lab3.MissFormatException;
import lab3.OutOfRangeException;
import lab3.SoapManager_Service;
import lab3.SqlObject;

public class WebLab3Console {
    public static void main(String[] args) throws IOException, MissFormatException, OutOfRangeException {
        String url = "http://localhost:8080/WebLab3.3/SoapManager?wsdl";
        SoapManager_Service prob = new SoapManager_Service(new URL(url));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String operation, buffId = "", buffName = "", buffSecondName = "", buffAge = "", buffSex = "";
        while(true) {
            System.out.println("Chouse operation:\n1.Select;\n2.Insert;\n3.Update;\n4.Delete;\n5.Load\n6.Upload\n7.Exit");
            operation = reader.readLine();
            
            switch(operation) {
                case "1":
                    buffId         = readFild(reader, "Id");
                    buffName       = readFild(reader, "Name");
                    buffSecondName = readFild(reader, "SecondName");
                    buffAge        = readFild(reader, "Age");
                    buffSex        = readFild(reader, "Sex");    
                    
                    List<SqlObject> buff = prob.getSoapManagerPort().select(buffId, buffName, buffSecondName, buffAge, buffSex);
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
                    
                    System.out.println(prob.getSoapManagerPort().insert(buffId, buffName, buffSecondName, buffAge, buffSex));
                    break;
                case "3":
                    buffId         = readFild(reader, "Id");
                    buffName       = readFild(reader, "Name");
                    buffSecondName = readFild(reader, "SecondName");
                    buffAge        = readFild(reader, "Age");
                    buffSex        = readFild(reader, "Sex");  
                    
                    System.out.println(prob.getSoapManagerPort().update(buffId, buffName, buffSecondName, buffAge, buffSex));
                    break;
                case "4":
                    buffId         = readFild(reader, "Id");
                    
                    System.out.println(prob.getSoapManagerPort().delete(buffId));
                    break;
                case "5":
                    System.out.println("Input load name:");
                    String name = reader.readLine();
                    byte[] prob2 = prob.getSoapManagerPort().load(name);
                    ByteArrayInputStream bis = new ByteArrayInputStream(prob2);
                    BufferedImage bImage2 = ImageIO.read(bis);
                    ImageIO.write(bImage2, "png", new File("c:\\client\\" + name) );
                    break;
                case "6":
                    System.out.println("Input upload name:");
                    String name2 = reader.readLine();
                    BufferedImage bImage = ImageIO.read(new File("c:\\client\\" +name2));
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ImageIO.write(bImage, "png", bos );
                    prob.getSoapManagerPort().upload(bos.toByteArray());
                    break;
                case "7":
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
