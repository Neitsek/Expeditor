package com.expeditor.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.expeditor.bo.Employe;

public class EmployeDB {
	private String ID_EMPLOYE = "id_employe";
	private String NOM = "nom";
	private String PRENOM = "prenom";
	private String LOGIN = "login";
	private String PASSWORD = "password";
	private String IS_MANAGER = "is_manager";
	
	private static final String SELECT_ALL = "select * from Employe";
	private static final String SELECT_ONE = "select * from Employe where login = ? and password = ? and nom = ? and prenom = ?";
	
	private EmployeDB() {
		
	}
	
	private static Employe build(ResultSet rs) {
		Employe em = null;
		
		try {
			int idEmploye = rs.getInt("id_employe");
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String login = rs.getString("login");
			String pw = rs.getString("password");
			Boolean is_manager = rs.getBoolean("is_manager");
		
		
			em = new Employe();
			em.setId(idEmploye);
			em.setNom(nom);
			em.setPrenom(prenom);
			em.setLogin(login);
			em.setPassword(pw);
			em.setIsManager(is_manager);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return em;
	}
	
	/**
	 * get one user
	 * @param login
	 * @param password
	 * @return
	 */
	public static Employe getOne(String login, String password) {
		Employe employe = null;
		Connection cnx = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = ConnectionDB.connect().prepareStatement(SELECT_ONE);
			
			statement.setString(0, login);
			statement.setString(1, password);
			
			rs = statement.executeQuery();

			while(rs.next())
			{
				employe = build(rs);
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
		return employe;
	}
}
