package client;


import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.gson.Gson;
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
 * Created by jilongsun on 6/13/15.
 */
public class BootstrapRequest {

    public static BootstrapConfig getBootstrapInfo(){
        BootstrapConfig config1 = new BootstrapConfig();
        ClientConfig config = new ClientConfig().register(JacksonJsonProvider.class);
                    Client client = ClientBuilder.newClient(config).register(SseFeature.class);
                    WebTarget target = client.target("http://localhost:8080/api/bs").register(new LoggingFilter(java.util.logging.Logger.getLogger("test"), true)).queryParam("ep", "1");

                    EventInput eventInput = target.request().accept(SseFeature.SERVER_SENT_EVENTS).post(Entity.entity("", MediaType.APPLICATION_JSON), EventInput.class);
                    while(! eventInput.isClosed()) {
                        final InboundEvent inboundEvent = eventInput.read();
                        if (inboundEvent == null) {

                            break;
                        }
                        String inputData = inboundEvent.readData(String.class);
                        if(inputData.equals("Error")){
                            return null;
                        }else {

//                            System.out.println(inputData);
                            Gson gson = new Gson();
                            config1 = gson.fromJson(inputData, BootstrapConfig.class);
//                            System.out.println(config1);
                        }

                    }
        return config1;
    }
}
