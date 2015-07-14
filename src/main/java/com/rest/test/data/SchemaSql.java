package com.rest.test.data;

import com.rest.test.util.ToJSON;
import org.codehaus.jettison.json.JSONArray;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jilongsun on 6/12/15.
 */
public class SchemaSql extends sqldatabase {

    public int insertIntoCustomer(String customer_id,String email, String lname, String fname) throws  Exception{
        PreparedStatement query ;
        Connection conn = null;

        try{
            conn = sqlCustomersConnection();
            query =  conn.prepareStatement("insert into customer " +
                    "(customer_id, email, lname, fname ) " +
                    "VALUES (?,?,?,?)");



            query.setString(1, customer_id);
            query.setString(2, email);
            query.setString(3, lname);
            query.setString(4, fname);
            System.out.println(query.toString());
            query.executeUpdate();




        }
        catch(Exception e){
            e.printStackTrace();
            return 500;

        }
        finally {
            if(conn !=null)
                conn.close();
        }
        return 200;
    }

    public JSONArray queryReturnCustomer_id(String customer_id) throws Exception{

        PreparedStatement query = null;
        Connection conn = null;
        ToJSON converter = new ToJSON();
        JSONArray json = new JSONArray();


        try{
            conn = sqlCustomersConnection();
            query = conn.prepareStatement("select customer_id, email,lname, fname from customer where customer_id = ? ");

            query.setString(1, customer_id);
            ResultSet rs = query.executeQuery();

            json = converter.toJSONArray(rs);

            query.close();

        }
        catch (SQLException sqlError){
            sqlError.printStackTrace();
            return json;
        }
        finally {
            if (conn != null)
                    conn.close();
        }
        return json;
    }

}
