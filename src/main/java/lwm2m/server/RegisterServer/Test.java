package lwm2m.server.RegisterServer;

import lwm2m.server.BootstrapServer.TestObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by jilongsun on 6/27/15.
 */
@Path("/test")
public class Test {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String handleTest(TestObject registerObjects){
        System.out.println(registerObjects);
        return registerObjects.toString();
    }
}
