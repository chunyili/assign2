package com.rest.test.status;


import com.rest.test.data.sqldatabase;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("/v1/status")
public class V1_status {

	private  static final String api_version = "00.00.00";
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Java Web Service</p>";
	}

	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return api_version;
	}

	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception{

		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;

		try{

			conn = sqldatabase.SqlDatabaseconn().getConnection();
			query = conn.prepareStatement("SELECT * FROM customer ");
			ResultSet rs = query.executeQuery();

			while (rs.next()){
				myString = rs.getString("lname");

			}
			query.close();
			returnString = "<p>Database status</p>" + "<p>lname"+myString+"</p>";
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if (conn != null){
				conn.close();
			}
		}
		return returnString;
	}
}
