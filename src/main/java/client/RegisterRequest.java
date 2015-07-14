package client;

import RegisterClient.RegisterObjects;
import RegisterClient.Resource;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import lwm2m.server.BootstrapServer.BootstrapConfig;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.sse.EventInput;
import org.glassfish.jersey.media.sse.InboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Created by jilongsun on 6/26/15.
 */
public class RegisterRequest {
    public static String registerInfo(BootstrapConfig config2) {

        ClientConfig config = new ClientConfig().register(JacksonJsonProvider.class);
        Client client = ClientBuilder.newClient(config).register(SseFeature.class);
        WebTarget target = client.target("http://localhost:8080/api/rd").register(new LoggingFilter(java.util.logging.Logger.getLogger("test"), true))
                .queryParam("ep", config2.shortId)
                .queryParam("lt", config2.lifetime)
                .queryParam("sms", config2.serverSmsNumber)
                .queryParam("lwm2m2", config2.lwm2mVersion)
                .queryParam("b", config2.binding);

        RegisterObjects registerObjects = new RegisterObjects();
        registerObjects.setEndPoint(config2.shortId);
        registerObjects.setLifetime(config2.lifetime);
        registerObjects.setSmsNumber(config2.serverSmsNumber);
        registerObjects.setLwVersion(config2.lwm2mVersion);
        registerObjects.setBindingMode(config2.binding);
        registerObjects.setObjectId(1);
        registerObjects.setObjectInstanceId(1);

        Resource resource = new Resource();
        resource.setResourceId(2);
        resource.setName("wearable");
        resource.setInformation("heart-rate:....");
        registerObjects.setResource(resource);

        EventInput eventInput = target.request().accept(SseFeature.SERVER_SENT_EVENTS)
                .post(Entity.entity(registerObjects, MediaType.APPLICATION_JSON), EventInput.class);

//        TestObject testObject = new TestObject();
//        EventInput eventInput = target.request().accept(MediaType.APPLICATION_JSON)
//                .post(Entity.entity(testObjects, MediaType.APPLICATION_JSON), String.class);
        while (!eventInput.isClosed()) {
            final InboundEvent inboundEvent = eventInput.read();
            if (inboundEvent == null) {

                break;
            }
            String inputData = inboundEvent.readData(String.class);
            System.out.println("RegistrationId is "+ inputData);



        }
        System.out.println(eventInput);
        return "Client has been register successfully";
    }





}
