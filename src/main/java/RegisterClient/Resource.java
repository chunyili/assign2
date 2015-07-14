package RegisterClient;

/**
 * Created by jilongsun on 6/27/15.
 */
public class Resource {
    private   int resourceId ;
    private   String name ;
    private String information;


    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}

