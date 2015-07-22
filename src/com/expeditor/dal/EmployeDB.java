package com.expeditor.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.expeditor.bo.Employe;

public class EmployeDB {
	
	private static final String SELECT_ALL = "select * from Employe";
	private static final String SELECT_ALL_EMPLOYE = "select * from Employe where is_manager = 'False'";
	private static final String SELECT_ONE = "select * from Employe where login = ? and password = ?";
	
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
		
		ResultSet rs = ConnectionDB.select(SELECT_ONE, login, password);

		try {
			while(rs.next())
			{
				employe = build(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employe;
	}
	
	/**
	 * get one user
	 * @param is_manager
	 * @return
	 */
	public static ArrayList<Employe> getAllEmploye() {
		ArrayList<Employe> listEmploye = new ArrayList<Employe>();
		
		ResultSet rs = null;
		try {
			rs = ConnectionDB.select(SELECT_ALL_EMPLOYE);

			while(rs.next())
			{
				listEmploye.add(build(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listEmploye;
	}
	
}
