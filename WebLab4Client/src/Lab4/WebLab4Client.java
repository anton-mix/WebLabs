/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class WebLab4Client {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        RestManager manager = new RestManager();
        String operation, buffId = "", buffName = "", buffSecondName = "", buffAge = "", buffSex = "";
        while(true) {
                buffId         = readFild(reader, "Id");
                
                try{
                    if(buffId.length() != 0)
                        Integer.parseInt(buffId);
                }
                catch(Exception ex){
                    System.out.println("Incorect Id. Try again");
                    continue;
                }
                
                buffName       = readFild(reader, "Name");
                buffSecondName = readFild(reader, "SecondName");
                buffAge        = readFild(reader, "Age");
                
                try{
                    if(buffAge.length() != 0)
                        Integer.parseInt(buffAge);
                }
                catch(Exception ex){
                    System.out.println("Incorect Age. Try again");
                    continue;
                }
                
                buffSex        = readFild(reader, "Sex");    

                List<SQLObject> buff = manager.Select(buffId, buffName, buffSecondName, buffAge, buffSex);
                for(int i = 0; i < buff.size(); i++) {
                    System.out.println((i  + 1)+ "." + buff.get(i).toString());
                }
                System.out.println("\n");      
        }
    }
    
    static String readFild(BufferedReader reader, String nameVal) throws IOException {
        System.out.println("Input " + nameVal + ":");
        return reader.readLine();
    }
    
}
