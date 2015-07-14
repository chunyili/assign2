package com.rest.test.data;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by jilongsun on 6/11/15.
 */
public class sqldatabase {

    private static DataSource SqlDatabase = null;
    private static String uid = "root";
    private static String pwd = "";
    private static String db_name = "ship";
    private static String url = "jdbc:mysql://localhost:3306/" + db_name;


    public static DataSource SqlDatabaseconn() throws  Exception{
        if(SqlDatabase != null){
            return SqlDatabase;

        }
        return SqlDatabase;
    }
    protected static Connection sqlCustomersConnection() throws  Exception{
        Connection conn = null;

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        try{
            conn = DriverManager.getConnection(url, uid, pwd);

            return conn;
        }
        catch(Exception e){
            e.printStackTrace();

        }
        return conn;

    }

}
