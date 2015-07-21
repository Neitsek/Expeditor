package com.expeditor.dal;

import java.sql.Connection;
import java.sql.DriverManager;



public class ConnectionDB {
   private ConnectionDB() {
	  
   }
	
   public static Connection connect(){
   Connection conn = null;	   
       try {
	       
    	   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	       	
    	   String dbURL = "jdbc:sqlserver://127.0.0.1:1433;database=db_expeditor;user=sa;password=Pa$$w0rd";
 
    	   //String user = "sa";
    	   //String pass = "Pa$$w0rd";
    	   
    	   //conn = DriverManager.getConnection(dbURL, user, pass);
    	   conn = DriverManager.getConnection(dbURL);
    	   
           if (conn != null) {
    		   System.out.println("Connected");    		   
    	   }
	       } catch (Exception ex) {
	           ex.printStackTrace();
	       } 
       return conn;
   }

}
	   
