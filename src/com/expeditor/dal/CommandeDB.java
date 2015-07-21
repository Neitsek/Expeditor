package com.expeditor.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.expeditor.bll.Logger;
import com.expeditor.bll.Outils;
import com.expeditor.bo.Commande;

public class CommandeDB {

	private static final String INSERT = "insert into commande " +
			" (date_commande,client,adresse,employe,date_debut_prepa,date_fin_prepa,etat)" +
			" values(?,?,?,?,?,?,?)"; 
		
	/**
	 * insertion commande 
	 * @param commande
	 */	
	public void insert_commande(Commande c) {
		Connection cnx = null;
		PreparedStatement statement = null;
		try {	
			
			cnx = ConnectionDB.connect();
			Logger.affiche("Préparation de la requete");
			statement = cnx.prepareStatement(INSERT);
			statement.setTimestamp(1,Outils.pTimestamp(c.getDate_commande()));
			statement.setString(2,c.getClient());
			statement.setString(3,c.getAdresse());
			statement.setInt(4,c.getEmploye());
			statement.setTimestamp(5,Outils.pTimestamp(c.getDate_debut_prepa()));
			statement.setTimestamp(6,Outils.pTimestamp(c.getDate_fin_prepa()));
			statement.setString(7,c.getEtat());	
			statement.executeUpdate();
			Logger.affiche("Insertion de la commande");	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (cnx != null)
				try {
					cnx.close();
				} catch (SQLException e) {	
					e.printStackTrace();
					Logger.arret("Erreur lors de la fermeture de la connexion");
				}
		}

	}
}
