package com.rest.test.inventory;
import com.rest.test.data.sqldatabase;
import com.rest.test.util.ToJSON;
import org.codehaus.jettison.json.JSONArray;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by jilongsun on 6/11/15.
 */
@Path("/v1/inventory")
public class V1_inventory {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String returnAllLnames() throws  Exception{

        PreparedStatement query = null;
        Connection conn = null;
        String returnString = null;
        try{
            conn = sqldatabase.SqlDatabaseconn().getConnection();
            query = conn.prepareStatement("SELECT * "+"FROM ship.customers ");
            ResultSet rs = query.executeQuery();

            ToJSON converter =  new ToJSON();
            JSONArray json = new JSONArray();
            json = converter.toJSONArray(rs);
            query.close();
            returnString = json.toString();



        }
        catch (Exception e){
            e.printStackTrace();

        }
        finally {
            if(conn != null)
                conn.close();


        }
        return returnString;
    }


}
