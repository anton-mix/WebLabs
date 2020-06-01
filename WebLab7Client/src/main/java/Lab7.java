import Objects.AccessPoints;
import Objects.Business;
import Objects.Services;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.juddi.v3.client.transport.TransportException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Lab7 {
    public static void main(String[] arg) throws IOException, TransportException, ConfigurationException {
        String BusinessKey = "uddi:juddi.apache.org:ec153b36-985b-4737-b435-f23a43b49fd3";
        JuddiService prob = new JuddiService();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Business bussinesInfo = prob.findBusinessByKey(BusinessKey);

        while(true){
            System.out.println("Business:\n  Business Key: " + bussinesInfo.getKey() + "\n  Business Name: " +
                bussinesInfo.getName() + "\n  Business Description: " + bussinesInfo.getDescription() + "\n  Choose option:\n" +
                "  1.Find all service\n  2.Find service by key\n  3.Find service by name\n  4.Call metod\n  5.Add service\n" +
                "  6.Delete service\n  7.Exit");
            switch(reader.readLine()){
                case "1":
                    System.out.print(prob.PrintServices(prob.findAllServices(BusinessKey)));
                    break;
                case "2":
                    System.out.print("    Enter service key:\n    ");
                    String key = reader.readLine();
                    System.out.print(prob.PrintServices(prob.findServiceByKey(new String[] {key})));
                    break;
                case "3":
                    System.out.print("    Enter service name:\n    ");
                    String name = reader.readLine();
                    System.out.print(prob.PrintServices(prob.findServiceByName(name)));
                    break;
                case "4":
                    String numberService = "", numberAccessPoint = "";
                    Services[] buff = prob.findAllServices(BusinessKey);
                    System.out.print(prob.PrintServices(buff));

                    System.out.print("    Enter number service:\n");
                    while(true){
                        numberService = reader.readLine();
                        try {
                            if (Integer.valueOf(numberService) < 1 || Integer.valueOf(numberService) > buff.length)
                                System.out.println("    Wrong number. Try again.");
                            else
                                break;
                        }
                        catch(Exception ex){
                            System.out.println("    Wrong number. Try again.");
                        }
                    }

                    System.out.print("    Enter number access point:\n");
                    while(true) {
                        numberAccessPoint = reader.readLine();
                        try {
                            if (Integer.valueOf(numberAccessPoint) < 1 || Integer.valueOf(numberAccessPoint) > buff[Integer.valueOf(numberService)-1].getAccessPoints().length)
                                System.out.println("    Wrong number. Try again.");
                            else
                                break;
                        }
                        catch(Exception ex){
                            System.out.println("    Wrong number. Try again.");
                        }
                    }

                    System.out.print(prob.CallMetod(buff[Integer.valueOf(numberService)-1].getAccessPoints()[Integer.valueOf(numberAccessPoint)-1], new String[0], new String[0]));

                    break;
                case "5":
                    System.out.print("    Enter service name:\n    ");
                    String nameService = reader.readLine();
                    if(nameService == ""){
                        nameService = "defaultName";
                    }

                    ArrayList<AccessPoints> buffPoints = new ArrayList<AccessPoints>();
                    while(true){
                        String url = "", type = "";
                        System.out.print("    Enter url:\n    ");
                        url = reader.readLine();
                        System.out.print("    Enter type url:\n    ");
                        while(true){
                            System.out.print("  1.END_POINT:\n      2.BINDING_TEMPLATE\n      3.WSDL_DEPLOYMENT\n"+
                                    "     4.HOSTING_REDIRECTOR\n      ");
                            switch (reader.readLine()){
                                case "1":
                                    type = "END_POINT";
                                    break;
                                case "2":
                                    type = "BINDING_TEMPLATE";
                                    break;
                                case "3":
                                    type = "WSDL_DEPLOYMENT";
                                    break;
                                case "4":
                                    type = "HOSTING_REDIRECTOR";
                                    break;
                                default:
                                    System.out.println("      Wrong number. Try again");
                            }
                            if(type != "")
                                break;
                        }
                        buffPoints.add(new AccessPoints(url,type));
                        boolean prov = false;
                        while(true) {
                            System.out.print("      Choose action:\n      1.Add access point\n      2.Add service\n      ");
                            boolean prov2 = true;
                            switch(reader.readLine()){
                                case "1":
                                    break;
                                case "2":
                                    prov = true;
                                    break;
                                default:
                                    prov2 = false;
                                    System.out.println("      Wrong number. Try again");
                            }
                            if(prov2)
                                break;
                        }
                        if(prov)
                            break;
                    }
                    prob.insertService(BusinessKey, nameService, buffPoints);
                    break;
                case "6":
                    System.out.print("    Enter service key:\n    ");
                    String key2 = reader.readLine();
                    prob.DeleteService(key2);
                    break;
                case "7":
                    System.exit(1);
                    break;
                default:
                    System.out.println("Wrong number. Try again.");
            }
        }

    }

}
