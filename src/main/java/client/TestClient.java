package client;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import lwm2m.server.BootstrapServer.TestObject;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Created by jilongsun on 6/27/15.
 */
public class TestClient {
    public static void main(String[] args) {

        ClientConfig config = new ClientConfig().register(JacksonJsonProvider.class);
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target("http://localhost:8080/api/rd/test")
                .register(new LoggingFilter(java.util.logging.Logger.getLogger("test"), true));
        TestObject testObject = new TestObject();
        String eventInput = target.request().accept(MediaType.APPLICATION_JSON)
                .post(Entity.entity(testObject, MediaType.APPLICATION_JSON), String.class);
        System.out.println(eventInput);
    }
}
