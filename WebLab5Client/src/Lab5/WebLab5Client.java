/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class WebLab5Client {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        RestManager manager = new RestManager();
        String operation, buffId = "", buffName = "", buffSecondName = "", buffAge = "", buffSex = "";
        while(true) {
            System.out.println("Chouse operation:\n1.Select;\n2.Insert;\n3.Update;\n4.Delete;\n5.Exit");
            operation = reader.readLine();
            
            switch(operation) {
                case "1":
                    buffId         = readFild(reader, "Id");
                    
                    try{
                        if(buffId.length() != 0) 
                            Integer.parseInt(buffId);
                    }
                    catch(Exception ex){
                        System.out.println("Incorect Id. Try again");
                        break;
                    }
                    
                    buffName       = readFild(reader, "Name");
                    buffSecondName = readFild(reader, "SecondName");
                    buffAge        = readFild(reader, "Age");
                    
                    try{
                        if(buffAge.length() != 0) 
                            Integer.parseInt(buffAge);
                    }
                    catch(Exception ex){
                        System.out.println("Incorect Afe. Try again");
                        break;
                    }
                    
                    buffSex        = readFild(reader, "Sex");    
                    
                    List<SQLObject> buff = manager.Select(buffId, buffName, buffSecondName, buffAge, buffSex);
                    for(int i = 0; i < buff.size(); i++) {
                        System.out.println((i  + 1)+ "." + buff.get(i).toString());
                    }
                    System.out.println("\n");
                    break;
                case "2":                  
                    buffId         = readFild(reader, "Id");
                    buffName       = readFild(reader, "Name");
                    buffSecondName = readFild(reader, "SecondName");
                    buffAge        = readFild(reader, "Age");
                    
                    try{
                        Integer.parseInt(buffAge);
                    }
                    catch(Exception ex){
                        System.out.println("Incorect Age. Try again");
                        break;
                    }
                    
                    buffSex        = readFild(reader, "Sex");    
                    
                    System.out.println(manager.Insert(buffId, buffName, buffSecondName, buffAge, buffSex));
                    break;
                case "3":
                    buffId         = readFild(reader, "Id");
                    
                    try{
                        Integer.parseInt(buffId);
                    }
                    catch(Exception ex){
                        System.out.println("Incorect Id. Try again");
                        break;
                    }
                    
                    buffName       = readFild(reader, "Name");
                    buffSecondName = readFild(reader, "SecondName");
                    buffAge        = readFild(reader, "Age");
                    
                    try{
                        Integer.parseInt(buffAge);
                    }
                    catch(Exception ex){
                        System.out.println("Incorect Age. Try again");
                        break;
                    }
                    
                    buffSex        = readFild(reader, "Sex");  
                    
                    System.out.println(manager.Update(buffId, buffName, buffSecondName, buffAge, buffSex));
                    break;
                case "4":
                    buffId         = readFild(reader, "Id");
                    
                    try{
                        Integer.parseInt(buffId);
                    }
                    catch(Exception ex){
                        System.out.println("Try again");
                        break;
                    }
                    
                    System.out.println(manager.Delete(buffId));
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
