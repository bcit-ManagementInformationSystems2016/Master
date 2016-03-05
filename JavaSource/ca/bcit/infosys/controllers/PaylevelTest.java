package ca.bcit.infosys.controllers;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.sql.DataSource;

@Named("PayLevel")
@SessionScoped
public class PaylevelTest implements Serializable {
	
	@Resource(mappedName = "java:jboss/datasources/bluehost")

	
	private static DataSource ds;
	
	public PaylevelTest() {
		System.out.println("construct");
	}
	
	public static void Test() throws SQLException {
		System.out.println("1");
		System.out.println(ds.toString());
		Statement stmt = null;
		if (ds == null) {
			System.out.println("no database");
		}
		else {
			System.out.println("ds not null");
		}
		Connection con = ds.getConnection();
		if (con == null) {
			System.out.println("no connection");
		}
		else {
			System.out.println("con not null");
		}
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT FirstName FROM Roles");
		if (rs == null) {
			System.out.println("rs null");
		} else {
			System.out.println("rs not null");
		}
		
	while (rs.next()) {
		System.out.println(rs.getString("FirstName"));
	}
	if (stmt != null) {
		stmt.close();
	}
	if (con != null ) {
		con.close();
	}
}
	
	
	

	

}
