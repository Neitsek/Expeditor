package com.expeditor.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.expeditor.bll.Logger;
import com.expeditor.bll.Outils;
import com.expeditor.bo.Commande;

public class CommandeDB {

	private CommandeDB(){
	}

	private static final String INSERT = "insert into commande " +
			" (date_commande,client,adresse,employe,date_debut_prepa,date_fin_prepa,etat)" +
			" values(?,?,?,?,?,?,?)";
	
	private static final String SELECT_PAR_URGENCE = "SELECT * FROM Commande WHERE etat = 'ATT' AND date_commande = ( SELECT MIN(date_commande) FROM Commande WHERE etat = 'ATT' );";
	
	private static Commande build(ResultSet rs) {
		Commande com = null;
		
		try {
			int idCommande = rs.getInt("id_commande");
			Date dateCommande = rs.getDate("date_commande");
			String client = rs.getString("client");
			String adresse = rs.getString("adresse");
			Integer idEmploye = rs.getInt("employe");
			Date dateDebutPrepa = rs.getDate("date_debut_prepa");
			Date dateFinPrepa = rs.getDate("date_fin_prepa");
			String etat = rs.getString("etat");
			
			com = new Commande();
			com.setId_commande(idCommande);
			com.setDate_commande(dateCommande);
			com.setClient(client);
			com.setAdresse(adresse);
			com.setEmploye(idEmploye);
			com.setDate_debut_prepa(dateDebutPrepa);
			com.setDate_fin_prepa(dateFinPrepa);
			com.setEtat(etat);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return com;
	}
	
	/**
	 * insertion commande 
	 * @param commande
	 */	 
	public static void insertCommande(Commande c) {
		Connection cnx = null;
		PreparedStatement statement = null;
		try {	

			cnx = ConnectionDB.connect();
			Logger.affiche("Pr�paration de la requete");
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



	private static String SELECT = "SELECT * FROM Commande, Employe " +
			"WHERE Commande.employe = Employe.id_employe ";

	/**
	 * s�lection des commandes	 
	 * @param etat
	 * @param date_debut
	 * @param date_fin
	 */
	public static ArrayList<Commande> selectCommandes(ArrayList<String> listeEtat, Date date_debut, Date date_fin) {
		ArrayList<Commande> listCommande = new ArrayList<Commande>();
		// -- construction de la requete
		// gestion des �tats
		String query = SELECT;
		if (listeEtat.size()>0){
			Logger.affiche("SELECT");
			int i = 0;
			query += "AND etat IN(";
			for (int j = 0; j < listeEtat.size(); j++) {					
				i++;
				query += "'" + listeEtat.get(j) + "'";
				if (listeEtat.size()!=i) {
					query +=",";
				}
			}
			query += ") ";
		}

		// gestion de la date
		if(date_debut!=null && date_fin!=null){
			query += "AND date_commande BETWEEN '" + Outils.pTimestamp(date_debut) + "' AND '" + Outils.pTimestamp(date_fin) + "'";	
		}

		ResultSet rs = ConnectionDB.select(query);
		
		try {
			while(rs.next()) {
				listCommande.add(build(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listCommande;
	}
	
	public static Commande selectCommandeLaPlusUrgente() {
		Commande com = null;
		Connection cnx = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		rs = ConnectionDB.select(SELECT_PAR_URGENCE);
		
		try {
			while(rs.next())
			{
				com = build(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return com;
	}
}
