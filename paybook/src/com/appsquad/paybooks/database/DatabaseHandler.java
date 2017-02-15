package com.appsquad.paybooks.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseHandler {
	
	
	public static Connection createConnection(){
		
		//String DRIVERCLASS = "org.postgresql.Driver";
		String DBNAME="paybooks";
		
		String JDBCURL = "jdbc:postgresql://localhost:5432/"+DBNAME;
		
		String USERNAME = "postgres";
		String PASSWORD = "password";
		
		
		
		Connection connection = null;
		try {
			
			Class.forName("org.postgresql.Driver");
			
			connection = DriverManager.getConnection(JDBCURL, USERNAME,PASSWORD);			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return connection;
	}
	
	
	
}
