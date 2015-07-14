package lwm2m.server.RegisterServer;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by jilongsun on 6/25/15.
 */
public class ClientRegistry {


    private final Map<Integer, Client> clientsByEp = new ConcurrentHashMap();
    private final List<ClientRegistryListener> listeners = new CopyOnWriteArrayList();
    public Collection<Client> allClients() {
        return Collections.unmodifiableCollection(clientsByEp.values());
    }
    public Client get(int endPoint) {
        return clientsByEp.get(endPoint);
    }

    public void addListener(ClientRegistryListener listener) {
        listeners.add(listener);
    }
    public void removeListener(ClientRegistryListener listener) {
        listeners.remove(listener);
    }

    public boolean registerClient(Client client) {


        Client previous = clientsByEp.put(client.getEndpoint(), client);
        if (previous != null) {
            for (ClientRegistryListener l : listeners) {
                l.unregistered(previous);
            }
        }
        for (ClientRegistryListener l : listeners) {
            l.registered(client);
        }

        return true;
    }
    private Client findByRegistrationId(String id) {
        Client result = null;
        if (id != null) {
            for (Client client : clientsByEp.values()) {
                if (id.equals(client.getRegistrationId())) {
                    result = client;
                    break;
                }
            }
        }
        return result;
    }


}
