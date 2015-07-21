package com.expeditor.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.expeditor.bo.Employe;

public class EmployeDB {
	
	private static final String SELECT_ALL = "select * from Employe";
	private static final String SELECT_ONE = "select * from Employe where login = ? and password = ?";

	/**
	 * get one user
	 * @param login
	 * @param password
	 * @return 
	 */
	public static Object getOne(String login, String password) {
		Employe em = null;
		Connection cnx = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = ConnectionDB.connect().prepareStatement(SELECT_ONE);
			
			statement.setString(1, login);
			statement.setString(2, password);
			
			rs = statement.executeQuery();
			
			while(rs.next())
			{
				int id_employe = rs.getInt("id_employe");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				Boolean is_manager = rs.getBoolean("is_manager");
				
				em = new Employe();
					em.setId(id_employe);
					em.setNom(nom);
					em.setPrenom(prenom);
					em.setIsManager(is_manager);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (cnx != null) { 
				try {
					cnx.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return em;
	}
}
