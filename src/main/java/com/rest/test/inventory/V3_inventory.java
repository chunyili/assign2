package com.rest.test.inventory;

import com.rest.test.data.SchemaSql;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by jilongsun on 6/12/15.
 */
@Path("/v3/inventory")
public class V3_inventory {

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response addToCustomer2(String incomingData) throws Exception{

        String returnString = null;
        JSONArray json = new JSONArray();
        JSONObject jsonObject =  new JSONObject();
        SchemaSql data = new SchemaSql();


        try{
            JSONObject customerData = new JSONObject(incomingData);
            System.out.println("jsonData"+ customerData.toString());

            // insert into database
            int http_code = data.insertIntoCustomer(customerData.optString("customer_id"),
                                                     customerData.optString("email"),
                                                     customerData.optString("lname"),
                                                     customerData.optString("fname"));

            // if ok
            if(http_code == 200){
                jsonObject.put("http_code", "200");
                jsonObject.put("message", "succeeded");
                returnString = json.put(jsonObject).toString();
            }else{
                return Response.status(500).entity("unable to enter item").build();
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity("Server was not able to process your request").build();

        }
        return Response.ok(returnString).build();
    }
}
