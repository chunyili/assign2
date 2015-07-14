package lwm2m.server.RegisterServer;

import RegisterClient.RegisterObjects;
import com.rest.test.data.MongoConnection;
import lwm2m.server.BootstrapServer.BindingMode;
import lwm2m.server.BootstrapServer.TestObject;
import org.apache.commons.lang.RandomStringUtils;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;
import org.mongodb.morphia.Datastore;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by jilongsun on 6/25/15.
 */
@Path("/rd")
public class RegisterHandler {
    private ClientRegistry clientRegistry = new ClientRegistry();


    @Path("/test")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String handleTest(TestObject registerObjects){
        System.out.println(registerObjects);
        return registerObjects.toString();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput getRegisterInfo(RegisterObjects registerObjects,
                                       @QueryParam("ep")int endPoint,@QueryParam("lt")long lt,@QueryParam("sms") String sms
                                   ,@QueryParam("lwm2m") String lwm2mVersion,@QueryParam("b")BindingMode bindingMode
    ) throws Exception {
        final String registerId = RegisterHandler.createRegistrationId();
        System.out.print(registerId);

        Client client = new Client(registerId, endPoint, bindingMode, lt, sms, lwm2mVersion);
        client.setRegisterObjects(registerObjects);
        final Datastore ds = MongoConnection.getDataStore1();
        ds.save(client);


        final EventOutput eventOutput = new EventOutput();
        if (clientRegistry.registerClient(client)){

            Thread rdThread = new Thread(new Runnable() {
                public void run() {
                    try{
                        final OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
                        eventBuilder.name("register-message-to-client");
                        eventBuilder.data(String.class, registerId);
                        final OutboundEvent event = eventBuilder.build();
                        eventOutput.write(event);

                    }catch (IOException e){
                        throw new RuntimeException(
                                "Bad Request", e);

                    }finally {
                        try {
                            eventOutput.close();
                        } catch (IOException ioClose) {
                            throw new RuntimeException(
                                    "Error when closing the event output.", ioClose);
                        }
                    }
                }
            });
            rdThread.start();
            rdThread.join();
        }
        MongoConnection.close();
        return eventOutput;
    }
    private static String createRegistrationId() {
        return RandomStringUtils.random(10, true, true);
    }

}
