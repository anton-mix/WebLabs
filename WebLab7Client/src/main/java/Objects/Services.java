package Objects;

public class Services {
    String serviceKey;
    String owningBusinessKey;
    String name;
    String catBag;
    AccessPoints[] accessPoints;

    public Services(String serviceKey, String owningBusinessKey, String name) {
        this.serviceKey = serviceKey;
        this.owningBusinessKey = owningBusinessKey;
        this.name = name;
    }

    public Services(String serviceKey, String owningBusinessKey, String name, String catBag) {
        this.serviceKey = serviceKey;
        this.owningBusinessKey = owningBusinessKey;
        this.name = name;
        this.catBag = catBag;
    }

    public Services(String serviceKey, String owningBusinessKey, String name, String catBag, AccessPoints[] accessPoints) {
        this.serviceKey = serviceKey;
        this.owningBusinessKey = owningBusinessKey;
        this.name = name;
        this.catBag = catBag;
        this.accessPoints = accessPoints;
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    public String getOwningBusinessKey() {
        return owningBusinessKey;
    }

    public void setOwningBusinessKey(String owningBusinessKey) {
        this.owningBusinessKey = owningBusinessKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatBag() {
        return catBag;
    }

    public void setCatBag(String catBag) {
        this.catBag = catBag;
    }

    public AccessPoints[] getAccessPoints() {
        return accessPoints;
    }

    public void setAccessPoints(AccessPoints[] accessPoints) {
        this.accessPoints = accessPoints;
    }
}
