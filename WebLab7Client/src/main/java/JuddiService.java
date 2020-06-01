import Objects.AccessPoints;
import Objects.Business;
import Objects.Services;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.juddi.api_v3.AccessPointType;
import org.apache.juddi.v3.client.UDDIConstants;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.apache.juddi.v3.client.transport.TransportException;
import org.uddi.api_v3.*;
import org.uddi.v3_service.UDDIInquiryPortType;
import org.uddi.v3_service.UDDISecurityPortType;

import javax.ws.rs.core.MediaType;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JuddiService {
    private static UDDISecurityPortType security = null;
    private static UDDIInquiryPortType inquiry = null;
    UDDIClient client;
    BusinessList buffList;
    String token;

    public JuddiService(){
        try {
            client = new UDDIClient("META-INF/simple-browse-uddi.xml");
            Transport transport = client.getTransport("default");
            security = transport.getUDDISecurityService();
            inquiry = transport.getUDDIInquiryService();
            token = GetAuthKey("uddiadmin", "ew23ew23");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String insertService(String BusinessKey, String Name, List<AccessPoints> points) {
        BusinessService myService = new BusinessService();
        myService.setBusinessKey(BusinessKey);

        Name myServName = new Name();
        myServName.setValue(Name);
        myServName.setLang("English");
        myService.getName().add(myServName);

        BindingTemplates myBindingTemplates = new BindingTemplates();
        for(int i = 0; i < points.size(); i++) {
            BindingTemplate myBindingTemplate = new BindingTemplate();
            AccessPoint accessPoint = new AccessPoint();
            accessPoint.setUseType(points.get(i).getType());
            accessPoint.setValue(points.get(i).getUrl());
            myBindingTemplate.setAccessPoint(accessPoint);

            myBindingTemplate = UDDIClient.addSOAPtModels(myBindingTemplate);
            myBindingTemplates.getBindingTemplate().add(myBindingTemplate);
        }

        myService.setBindingTemplates(myBindingTemplates);

        BusinessService svc = client.getClerk("default").register(myService);
        if (svc == null){
            return("Save failed!");
        }

        return "Save sucsses!";
    }

    public void DeleteBusiness(String Key) {
        client.getClerk("default").unRegisterBusiness(Key);
    }

    public void DeleteService(String Key) {
        client.getClerk("default").unRegisterService(Key);
    }

    public Business[] findAllBuisnes() throws RemoteException {
        Business[] bus;
        FindBusiness fb = new FindBusiness();
        fb.setAuthInfo(token);
        org.uddi.api_v3.FindQualifiers fq = new org.uddi.api_v3.FindQualifiers();
        fq.getFindQualifier().add(UDDIConstants.APPROXIMATE_MATCH);

        fb.setFindQualifiers(fq);
        Name searchname = new Name();
        searchname.setValue(UDDIConstants.WILDCARD);
        fb.getName().add(searchname);
        buffList = inquiry.findBusiness(fb);

        bus = new Business[buffList.getBusinessInfos().getBusinessInfo().size()];
        for(int i = 0; i < buffList.getBusinessInfos().getBusinessInfo().size(); i ++) {
            bus[i] = new Business(buffList.getBusinessInfos().getBusinessInfo().get(i).getBusinessKey(),
                    ItemToString(buffList.getBusinessInfos().getBusinessInfo().get(i).getName().toArray(), Name.class),
                    ItemToString(buffList.getBusinessInfos().getBusinessInfo().get(i).getDescription().toArray(), Description.class));
            String[] buff = new String[buffList.getBusinessInfos().getBusinessInfo().get(i).getServiceInfos().getServiceInfo().size()];
            for (int j = 0; j < buffList.getBusinessInfos().getBusinessInfo().get(i).getServiceInfos().getServiceInfo().size(); j++) {
                buff[j] = buffList.getBusinessInfos().getBusinessInfo().get(i).getServiceInfos().getServiceInfo().get(j).getServiceKey();
            }
            bus[i].setServices(findServiceByKey(buff));
        }

        return bus;
    }

    public Business findBusinessByKey(String BusinessKey) throws RemoteException, ConfigurationException, TransportException {
        BusinessEntity bus = client.getClerk("default").getBusinessDetail(BusinessKey);
        return new Business(bus.getBusinessKey(), ItemToString(bus.getName().toArray(), Name.class), ItemToString(bus.getDescription().toArray(), Description.class));
    }

    public Services[] findAllServices(String BusinessKey) throws RemoteException, ConfigurationException, TransportException {
        BusinessEntity bus = client.getClerk("default").getBusinessDetail(BusinessKey);

        String[] buff = new String[bus.getBusinessServices().getBusinessService().size()];
        for (int j = 0; j < bus.getBusinessServices().getBusinessService().size(); j++) {
            buff[j] = bus.getBusinessServices().getBusinessService().get(j).getServiceKey();
        }
        return findServiceByKey(buff);
    }

    public Services[] findServiceByKey(String[] serviceKey) throws RemoteException {
            Services[] buff;
            GetServiceDetail gsd = new GetServiceDetail();
            ServiceDetail serviceDetail;
            for (int i = 0; i < serviceKey.length; i++) {
                gsd.getServiceKey().add(serviceKey[i]);
            }
            gsd.setAuthInfo(token);
            try {
                serviceDetail = inquiry.getServiceDetail(gsd);
            }
            catch(Exception ex) {
                serviceDetail = new ServiceDetail();
            }
            buff = new Services[serviceDetail.getBusinessService().size()];
            for (int i = 0; i < serviceDetail.getBusinessService().size(); i++) {
                buff[i] = new Services(serviceDetail.getBusinessService().get(i).getServiceKey(),
                        serviceDetail.getBusinessService().get(i).getBusinessKey(),
                        ItemToString(serviceDetail.getBusinessService().get(i).getName().toArray(), Name.class),
                        ItemToString(new Object[] {serviceDetail.getBusinessService().get(i).getCategoryBag()}, CategoryBag.class));
                AccessPoints[] accessPoints = new AccessPoints[serviceDetail.getBusinessService().get(i).getBindingTemplates().getBindingTemplate().size()];
                for(int j = 0; j < serviceDetail.getBusinessService().get(i).getBindingTemplates().getBindingTemplate().size(); j ++){
                    accessPoints[j] = new AccessPoints(serviceDetail.getBusinessService().get(i).getBindingTemplates().getBindingTemplate().get(j).getAccessPoint().getValue(),
                            serviceDetail.getBusinessService().get(i).getBindingTemplates().getBindingTemplate().get(j).getAccessPoint().getUseType());
                }
                buff[i].setAccessPoints(accessPoints);
            }
        return buff;
    }

    public Services[] findServiceByName(String Name) throws RemoteException {
        FindService findService = new FindService();
        String[] buff;
        findService.setAuthInfo(token);

        FindQualifiers findQualifiers = new FindQualifiers();
        findQualifiers.getFindQualifier().add(UDDIConstants.APPROXIMATE_MATCH);
        findService.setFindQualifiers(findQualifiers);
        Name searchName = new Name();
        searchName.setValue("%" + Name + "%");
        findService.getName().add(searchName);
        ServiceList serviceList = inquiry.findService(findService);

        if(serviceList.getServiceInfos() == null)
            buff = new String[0];
        else
            buff = new String[serviceList.getServiceInfos().getServiceInfo().size()];

        for (int j = 0; j < buff.length; j++) {
            buff[j] = serviceList.getServiceInfos().getServiceInfo().get(j).getServiceKey();
        }
        return findServiceByKey(buff);
    }

    public String PrintBusisness(Business[] bus)  {
        String res = "Business: \n";
        for(int i = 0; i < bus.length; i ++){
            res += (i + 1) + ".Busines Key: " + bus[i].getKey() + "\n" +
                    "  Busines Name: " + bus[i].getName() + "\n" +
                    "  Busines Discription: " + bus[i].getDescription() + "\n" +
                    "  Services: \n";
            for(int j = 0; j < bus[i].getServices().length; j++){
                res += "  " + (j + 1) + ".Service Key: " + bus[i].getServices()[j].getServiceKey() + "\n" +
                        "    Service Name: " + bus[i].getServices()[j].getName() + "\n" +
                        "    Category Bag: " + bus[i].getServices()[j].getCatBag() + "\n" +
                        "    Access Points: \n";
                for(int k = 0; k < bus[i].getServices()[j].getAccessPoints().length; k++){
                    res += "    " + (k + 1) + ".URL: " + bus[i].getServices()[j].getAccessPoints()[k].getUrl() + "\n" +
                            "      Type: " + bus[i].getServices()[j].getAccessPoints()[k].getType() + "\n";
                }
            }
        }
        return res;
    }

    String CallMetod(AccessPoints points , String[] name, String[] param) {
        Client client = Client.create();
        WebResource webResource = client.resource(points.getUrl());

        for(int i = 0; i < name.length; i++)  {
            webResource = webResource.queryParam(name[i], param[i]);
        }
        ClientResponse response = webResource.accept(MediaType.APPLICATION_ATOM_XML).get(ClientResponse.class);
        GenericType<String> type = new GenericType<String>() {};
        return response.getEntity(type);
    }

    public String PrintServices(Services[] services){
        String res = "";
        for(int j = 0; j < services.length; j++){
            res += "    " + (j + 1) + ".Service Key: " + services[j].getServiceKey() + "\n" +
                    "      Service Name: " + services[j].getName() + "\n" +
                    "      Category Bag: " + services[j].getCatBag() + "\n" +
                    "      Access Points: \n";
            for(int k = 0; k < services[j].getAccessPoints().length; k++){
                res += "      " + (k + 1) + ".URL: " + services[j].getAccessPoints()[k].getUrl() + "\n" +
                        "        Type: " + services[j].getAccessPoints()[k].getType() + "\n";
            }
        }
        if(res == ""){
            res = "Service not found\n";
        }
        return res;
    }

    private String GetAuthKey(String username, String password) {
        try {
            GetAuthToken getAuthTokenRoot = new GetAuthToken();
            getAuthTokenRoot.setUserID(username);
            getAuthTokenRoot.setCred(password);
            AuthToken rootAuthToken = security.getAuthToken(getAuthTokenRoot);
            return rootAuthToken.getAuthInfo();
        } catch (Exception ex) {
            System.out.println("Could not authenticate with the provided credentials " + ex.getMessage());
        }
        return null;
    }

    private String ItemToString(Object[] valuses, Class className) {
        StringBuilder sb = new StringBuilder();

        switch(className.getSimpleName()){
            case "Name":
                Name[] name = Arrays.copyOf(valuses, valuses.length, Name[].class);
                for (int i = 0; i < name.length; i++) {
                    sb.append(name[i].getValue()).append(" ");
                }
                break;
            case "Description":
                Description[] description = Arrays.copyOf(valuses, valuses.length, Description[].class);
                for (int i = 0; i < description.length; i++) {
                    sb.append(description[i].getValue()).append(" ");
                }
                break;
            case "CategoryBag":
                CategoryBag categoryBag = Arrays.copyOf(valuses, 1, CategoryBag[].class)[0];
                if (categoryBag == null) {
                    return "no data";
                }
                for (int i = 0; i < categoryBag.getKeyedReference().size(); i++) {
                    sb.append(ItemToString(new Object[] {categoryBag.getKeyedReference().get(i)}, KeyedReference.class));
                }
                for (int i = 0; i < categoryBag.getKeyedReferenceGroup().size(); i++) {
                    sb.append("Key Ref Grp: TModelKey=");
                    for (int j = 0; j < categoryBag.getKeyedReferenceGroup().get(i).getKeyedReference().size(); j++) {
                        sb.append(ItemToString(new Object[] {categoryBag.getKeyedReferenceGroup().get(i).getKeyedReference().get(j)}, KeyedReference.class));
                    }
                }
                break;
            case "KeyedReference":
                KeyedReference keyedReference = Arrays.copyOf(valuses, 1, KeyedReference[].class)[0];
                sb.append("Key Ref: Name=").
                        append(keyedReference.getKeyName()).
                        append(" Value=").
                        append(keyedReference.getKeyValue()).
                        append(" tModel=").
                        append(keyedReference.getTModelKey()).
                        append(System.getProperty("line.separator"));
                break;
        }
        return sb.toString();
    }
}
