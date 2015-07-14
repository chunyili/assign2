package lwm2m.server.RegisterServer;

/**
 * Created by jilongsun on 6/25/15.
 */
public interface ClientRegistryListener {

    void registered(Client client);

    void updated(Client clientUpdated);

    void unregistered(Client client);


}
