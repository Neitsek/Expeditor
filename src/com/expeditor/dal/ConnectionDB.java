package com.expeditor.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.expeditor.bll.Logger;

 
public class ConnectionDB {
	private ConnectionDB() {
	}
  
	
	public static Connection connect(){
		Connection conn = null;	

		// chargement du pilote
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			Logger.arret("Impossible de charger le pilote jdbc:odbc");			
		}

		Logger.affiche("Connexion a la base de données");		
		
		try {	   	
			String dbURL = "jdbc:sqlserver://127.0.0.1:1433;database=db_expeditor;user=sa;password=Pa$$w0rd";
			conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				Logger.affiche("Connected");    		   
			}
		} catch (SQLException e) {
			Logger.arret("Connection à la base de données impossible");
		}

		return conn;
	}

	public static void disconnect(Connection connection){
		try {
			connection.close();
		} catch (SQLException e) {			
			e.printStackTrace();			
		}		
	}
}

