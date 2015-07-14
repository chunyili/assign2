package com.rest.test.inventory;

import com.rest.test.data.SchemaSql;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 * Created by jilongsun on 6/12/15.
 */
@Path("/v2/inventory")
public class V2_inventory {

    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnLnames(@QueryParam("lname" )String lnames)throws  Exception{

        String returnString = null;
        JSONArray json = new JSONArray();

        try {

            if(lnames == null){
                return Response.status(400).entity("Error: please specify the lnames").build();
            }

            SchemaSql data = new SchemaSql();
            json = data.queryReturnLnames(lnames);
            returnString = json.toString();

        }
        catch (Exception e){
            e.printStackTrace();
            return Response.status(500).entity("Server was not able to process your request").build();
        }

        return Response.ok(returnString).build();
    }*/
    @Path("/{customer_id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnLname(@PathParam("customer_id" )String customer_id)throws  Exception{

        String returnString = null;
        JSONArray json = new JSONArray();

        try {

            if(customer_id == null){
                return Response.status(400).entity("Error: please specify the lnames").build();
            }
            SchemaSql data = new SchemaSql();
            json = data.queryReturnCustomer_id(customer_id);
            returnString = json.toString();

        }
        catch (Exception e){
            e.printStackTrace();
            return Response.status(500).entity("Server was not able to process your request").build();
        }

        return Response.ok(returnString).build();
    }


    @POST
    @Path("/post")
    @Consumes( MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCustomers(String incomingData) throws Exception{
        String returnString = null;
        JSONArray json = new JSONArray();

        SchemaSql data = new SchemaSql();

        try{
            System.out.println("Incoming Data:"+ incomingData);
            ObjectMapper mapper = new ObjectMapper();

            ItemEntry itemEntry = mapper.readValue(incomingData, ItemEntry.class);
            int http_code = data.insertIntoCustomer(itemEntry.customer_id,itemEntry.email,
                                                     itemEntry.lname, itemEntry.fname);
            if (http_code == 200){

                if(itemEntry.customer_id == null){
                    return Response.status(400).entity("Error: please specify the customer_id").build();
                }
                json = data.queryReturnCustomer_id(itemEntry.customer_id);
                returnString = json.toString();


            }else{
                return Response.status(500).entity("Unable to process item").build();
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return Response.status(500).entity("Server was not able to process your request").build();

        }
        return Response.ok(returnString).build();

    }

}

class ItemEntry{
    public String customer_id;
    public String email;
    public String lname;
    public String fname;
}
