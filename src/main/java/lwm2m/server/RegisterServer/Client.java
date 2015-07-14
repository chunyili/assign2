package lwm2m.server.RegisterServer;


import RegisterClient.RegisterObjects;
import lwm2m.server.BootstrapServer.BindingMode;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by jilongsun on 6/25/15.
 */
@Entity
public class Client {
    @Id
    private ObjectId id;
    private static final long DEFAULT_LIFETIME_IN_SEC = 86400;

    private static final String DEFAULT_LWM2M_VERSION = "1.0";





    private final int endpoint;
    private String lwm2mVersion = null;

    private String smsNumber = null;

    private final String registerId;

    private final BindingMode bindingMode;

    private final long lifeTime;
    @Embedded
    private RegisterObjects registerObjects;


    public Client(String registrationId, int endpoint,
                 BindingMode bindingMode, Long lifeTime,String smsNumber, String lwm2mVersion){
        this.registerId = registrationId;
        this.endpoint = endpoint;

        this.smsNumber = smsNumber;
        this.lwm2mVersion = lwm2mVersion;

//        this.objectLinks = objectLinks;

        this.bindingMode = bindingMode == null? BindingMode.U:bindingMode;
        this.lifeTime = lifeTime == null? DEFAULT_LIFETIME_IN_SEC:lifeTime;
    }

    public static long getDefaultLifetimeInSec() {
        return DEFAULT_LIFETIME_IN_SEC;
    }

    public static String getDefaultLwm2mVersion() {
        return DEFAULT_LWM2M_VERSION;
    }



    public int getEndpoint() {
        return endpoint;
    }

    public String getRegistrationId() {
        return registerId;
    }

    public BindingMode getBindingMode() {
        return bindingMode;
    }

    public long getLifeTimeInSec() {
        return lifeTime;
    }

    public RegisterObjects getRegisterObjects() {
        return registerObjects;
    }

    public void setRegisterObjects(RegisterObjects registerObjects) {
        this.registerObjects = registerObjects;
    }
    //    public LinkObject[] getObjectLinks() {
//        return objectLinks;
//    }

    /**
     * Created by jilongsun on 6/25/15.
     */

}

