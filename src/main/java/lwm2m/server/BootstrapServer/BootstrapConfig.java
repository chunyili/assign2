package lwm2m.server.BootstrapServer;

import com.google.gson.Gson;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by jilongsun on 6/22/15.
 */
@Entity
public class BootstrapConfig implements Serializable{
    @Override
    public String toString() {
        return "BootstrapConfig{" +
                "id=" + id +
                ", shortId=" + shortId +
                ", lifetime=" + lifetime +
                ", defaultMinPeriod=" + defaultMinPeriod +
                ", defaultMaxPeriod=" + defaultMaxPeriod +
                ", disableTimeout=" + disableTimeout +
                ", notifIfDisabled=" + notifIfDisabled +
                ", binding=" + binding +
                ", uri='" + uri + '\'' +
                ", bootstrapServer=" + bootstrapServer +
                ", securityMode=" + securityMode +
                ", publicKeyOrId=" + Arrays.toString(publicKeyOrId) +
                ", serverPublicKeyOrId=" + Arrays.toString(serverPublicKeyOrId) +
                ", secretKey=" + Arrays.toString(secretKey) +
                ", smsSecurityMode=" + smsSecurityMode +
                ", smsBindingKeyParam=" + Arrays.toString(smsBindingKeyParam) +
                ", smsBindingKeySecret=" + Arrays.toString(smsBindingKeySecret) +
                ", serverSmsNumber='" + serverSmsNumber + '\'' +
                ", serverId=" + serverId +
                ", clientOldOffTime=" + clientOldOffTime +
                '}';
    }

    @Id

    private ObjectId id;

        public int shortId;
        public int lifetime = 86400;
        public int defaultMinPeriod = 1;
        public String lwm2mVersion = "2.0";
        public Integer defaultMaxPeriod = null;
        public Integer disableTimeout = null;
        public boolean notifIfDisabled = true;
        public BindingMode binding = BindingMode.U;

        public String uri;
        public boolean bootstrapServer = false;
        public SecurityMode securityMode;
        public byte[] publicKeyOrId = new byte[] {};
        public byte[] serverPublicKeyOrId = new byte[] {};
        public byte[] secretKey = new byte[] {};

    public SmsSecurityMode smsSecurityMode = SmsSecurityMode.NO_SEC;
        public byte[] smsBindingKeyParam = new byte[] {};
        public byte[] smsBindingKeySecret = new byte[] {};
        public String serverSmsNumber = "";
        public Integer serverId;
        public int clientOldOffTime = 1;

    public String toJSON(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}

