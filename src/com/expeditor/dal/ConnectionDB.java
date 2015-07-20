package com.expeditor.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDB {
   private ConnectionDB() {
	  
   }
	
   public static Connection connect(){
   Connection conn = null;	   
       try {
	   
    	   String dbURL = "jdbc:sqlserver://localhost/db_expeditor";
    	   String user = "sa";
    	   String pass = "Pa$$w0rd";
    	   
    	   conn = DriverManager.getConnection(dbURL, user, pass);
           if (conn != null) {
    		   System.out.println("Connected");
    	   }
	       } catch (SQLException ex) {
	           ex.printStackTrace();
	       } 
       return conn;
   }

}
	   
