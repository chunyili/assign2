package RegisterClient;

import lwm2m.server.BootstrapServer.BindingMode;
import org.mongodb.morphia.annotations.Embedded;

/**
 * Created by jilongsun on 6/25/15.
 */
@Embedded
public class RegisterObjects {


    private int endPoint;
    private int lifetime ;
    private String lwVersion = null;
    private BindingMode bindingMode = null;
    private String smsNumber = null;

    @Override
    public String toString() {
        return "RegisterObjects{" +
                "endPoint=" + endPoint +
                ", lifetime=" + lifetime +
                ", lwVersion='" + lwVersion + '\'' +
                ", bindingMode=" + bindingMode +
                ", smsNumber='" + smsNumber + '\'' +
                ", objectId=" + objectId +
                ", objectInstanceId=" + objectInstanceId +
                 +
                '}';
    }

    private  int objectId;
    private  int objectInstanceId;
    private Resource resource;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public RegisterObjects() {

    }
    public int getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(int endPoint) {
        this.endPoint = endPoint;
    }

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public String getLwVersion() {
        return lwVersion;
    }

    public void setLwVersion(String lwVersion) {
        this.lwVersion = lwVersion;
    }

    public BindingMode getBindingMode() {
        return bindingMode;
    }

    public void setBindingMode(BindingMode bindingMode) {
        this.bindingMode = bindingMode;
    }

    public String getSmsNumber() {
        return smsNumber;
    }

    public void setSmsNumber(String smsNumber) {
        this.smsNumber = smsNumber;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getObjectInstanceId() {
        return objectInstanceId;
    }

    public void setObjectInstanceId(int objectInstanceId) {
        this.objectInstanceId = objectInstanceId;
    }







}

