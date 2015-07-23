package com.expeditor.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

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
	
	public static ResultSet select(String query, Object... parameters) {
		Connection cnx = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = ConnectionDB.connect().prepareStatement(query);

			int cpt = 0;
			for (Object parameter : parameters) {
				cpt++;
				
				if (parameter == null) {
					statement.setNull(cpt, Types.VARCHAR);
				} else if (parameter instanceof Integer) {
					statement.setInt(cpt, (Integer)parameter);
				} else if (parameter instanceof String) {
					statement.setString(cpt, (String)parameter);
				} else if (parameter instanceof Date) {
					statement.setDate(cpt, new java.sql.Date(((Date) parameter).getTime()));
				} else if (parameter instanceof Boolean ) {
					statement.setBoolean(cpt, (Boolean) parameter);
				}
			}
			
			rs = statement.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (cnx != null) { 
				try {
					cnx.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return rs;
	}
	
	public static void update(String query, Object... parameters) {
		Connection cnx = null;
		PreparedStatement statement = null;
		try {
			statement = ConnectionDB.connect().prepareStatement(query);

			int cpt = 0;
			for (Object parameter : parameters) {
				
				cpt++; 
				
				if (parameter == null) {
					statement.setNull(cpt, Types.VARCHAR);
				} else if (parameter instanceof Integer) {
					statement.setInt(cpt, (Integer)parameter);
				} else if (parameter instanceof String) {
					statement.setString(cpt, (String)parameter);
				} else if (parameter instanceof Date) {
					statement.setDate(cpt, new java.sql.Date(((Date) parameter).getTime()));
				} else if (parameter instanceof Boolean ) {
					statement.setBoolean(cpt, (Boolean) parameter);
				}
			}
			
			statement.executeUpdate();
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (cnx != null) { 
				try {
					cnx.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

