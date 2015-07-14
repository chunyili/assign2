package lwm2m.server.BootstrapServer;

/**
 * Created by jilongsun on 6/22/15.
 */
public enum SecurityMode {
    PSK(0), RPK(1), X509(2), NO_SEC(3);

    public final int code;

    private SecurityMode(int code) {
        this.code = code;
    }
}
