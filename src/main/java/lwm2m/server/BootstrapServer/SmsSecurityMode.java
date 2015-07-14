package lwm2m.server.BootstrapServer;

/**
 * Created by jilongsun on 6/22/15.
 */
public enum SmsSecurityMode {
    RESERVED(0), SPS_DEVICE(1), SPS_SMARTCARD(2), NO_SEC(3), PROPRIETARY(255);

    public final int code;

    private SmsSecurityMode(int code) {
        this.code = code;
    }
}
