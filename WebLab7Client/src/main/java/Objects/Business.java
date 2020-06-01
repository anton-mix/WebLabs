package Objects;

public class Business {
    String key;
    String name;
    String description;
    Services[] services;

    public Business(String key, String name, String description){
        this.key = key;
        this.name = name;
        this.description = description;
    }

    public Business(String key, String name, String description, Services[] services) {
        this.key = key;
        this.name = name;
        this.description = description;
        this.services = services;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Services[] getServices() {
        return services;
    }

    public void setServices(Services[] services) {
        this.services = services;
    }
}
