package lwm2m.server.BootstrapServer;

import com.mongodb.*;
import com.rest.test.data.MongoConnection;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;
import org.mongodb.morphia.Datastore;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by jilongsun on 6/22/15.
 */
@Path("/bs")
public class Bootstrap {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnConfigInfo(@QueryParam("ep") int endpoint) throws Exception{
        System.out.print("Inside bs");
        DB database = MongoConnection.mongoConnection();
        DBCollection collection = database.getCollection("BootstrapConfig");
        DBObject query = new BasicDBObject("shortId",endpoint);
        DBCursor cursor = collection.find(query);
        System.out.println(endpoint);
        return Response.status(200).entity(cursor.one().toString()).build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput getConfigInfo(@QueryParam("ep") final int endpoint) throws Exception{
//        DB database = MongoConnection.mongoConnection();
//        DBCollection collection = database.getCollection("BootstrapConfig");
//        DBObject query = new BasicDBObject("shortId",endpoint);
//        final DBCursor cursor = collection.find(query);
        final Datastore ds = MongoConnection.getDataStore();
                    final List<BootstrapConfig> bsConfigs = ds.createQuery(BootstrapConfig.class)
                            .filter("shortId =", endpoint)
                            .asList();

        System.out.print(bsConfigs.size());
//                    MongoConnection.close();
        final EventOutput eventOutput = new EventOutput();
        Thread bsThread = new Thread(new Runnable() {

            public void run() {
                try {
//                    for (int i = 0; i < 10; i++) {
//                        // ... code that waits 1 second
//                        final OutboundEvent.Builder eventBuilder
//                                = new OutboundEvent.Builder();
//                        eventBuilder.name("message-to-client");
//                        eventBuilder.data(String.class,
//                                "Hello world " + i + "!");
//                        final OutboundEvent event = eventBuilder.build();
//                        eventOutput.write(event);
//
//
//                    }

                    final OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
                        eventBuilder.name("message-to-client");
                    if(bsConfigs.size() == 0){
                        eventBuilder.data(String.class,"Error");
                    }else {
                        eventBuilder.data(String.class, bsConfigs.get(0).toJSON());
                    }



                        final OutboundEvent event = eventBuilder.build();
                        eventOutput.write(event);
//                    MongoConnection.close();
//
//                    final OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
//                    eventBuilder.data(String.class, "helloWorld");
//
//                    final OutboundEvent event = eventBuilder.build();
//                    eventOutput.write(event);
                } catch (IOException e) {
                    throw new RuntimeException(
                            "Error when writing the event.", e);
                } finally {
                    try {
                        eventOutput.close();
                    } catch (IOException ioClose) {
                        throw new RuntimeException(
                                "Error when closing the event output.", ioClose);
                    }
                }
            }
        });
        bsThread.start();
        bsThread.join();

        MongoConnection.close();
        return eventOutput;

//        try{
//            if (cursor == null){
//                return Response.status(500).entity("Server was not able to process your request").build();
//            }else{
//                return Response.status(200).entity(cursor.one().toString()).build();
//
//            }
//        }
//        catch(Exception e){
//            e.printStackTrace();
//            return Response.status(500).entity("Error").build();
//        }
    }




}
