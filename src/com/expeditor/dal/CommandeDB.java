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
import com.expeditor.bo.Employe;

public class CommandeDB {

	private CommandeDB(){
	}

	private static final String INSERT = "insert into commande " +
			" (date_commande,client,adresse,employe,date_debut_prepa,date_fin_prepa,etat)" +
			" values(?,?,?,?,?,?,?)"; 
	
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
			
			com = new Commande();
			com.setId_commande(idCommande);
			com.setDate_commande(dateCommande);
			com.setClient(client);
			com.setAdresse(adresse);
			com.setEmploye(idEmploye);
			com.setDate_debut_prepa(dateDebutPrepa);
			com.setDate_fin_prepa(dateDebutPrepa);
			
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



	private static String SELECT = "SELECT * FROM Commande, Employe " +
			"WHERE Commande.employe = Employe.id_employe ";

	/**
	 * sélection des commandes	 
	 * @param etat
	 * @param date_debut
	 * @param date_fin
	 */
	public static ArrayList<Commande> selectCommandes(ArrayList<String> listeEtat, Date date_debut, Date date_fin) {

		// -- construction de la requete
		// gestion des états
		if (listeEtat.size()>0){
			Logger.affiche("SELECT");
			int i = 0;
			SELECT += "AND etat IN(";
			for (int j = 0; j < listeEtat.size(); j++) {					
				i++;
				SELECT += "'" + listeEtat.get(j) + "'";
				if (listeEtat.size()!=i) {
					SELECT +=",";
				}
			}
			SELECT += ") ";
		}

		// gestion de la date
		if(date_debut!=null && date_fin!=null){
			SELECT += "AND date_commande BETWEEN '" + Outils.pTimestamp(date_debut) + "' AND '" + Outils.pTimestamp(date_fin) + "'";	
		}

		ArrayList<Commande> listeCommande = new ArrayList<Commande>(); 
		Connection cnx = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			cnx = ConnectionDB.connect();
			statement = cnx.prepareStatement(SELECT);
			rs = statement.executeQuery();
			while(rs.next())
			{
				int id_commande = rs.getInt("id_commande");
				Date date_commande = rs.getDate("date_commande");
				String client = rs.getString("client");
				String adresse = rs.getString("adresse");
				int employe = rs.getInt("employe");
				Date date_debut_prepa = rs.getDate("date_debut_prepa");
				Date date_fin_prepa = rs.getDate("date_fin_prepa");
				String etat = rs.getString("etat");
				
				Commande commande = new Commande();
				commande.setId_commande(id_commande);
				commande.setDate_commande(date_commande);
				commande.setClient(client);
				commande.setAdresse(adresse);
				commande.setEmploye(employe);
				commande.setDate_debut_prepa(date_debut_prepa);
				commande.setDate_fin_prepa(date_fin_prepa);
				commande.setEtat(etat);
				
				listeCommande.add(commande);
							
			}

			
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (cnx != null )
				try {
					cnx.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return listeCommande;
	}
		
		
	
	}
	
	public static Commande selectCommandeLaPlusUrgente() {
		Commande com = null;
		
		
		
		return com;
	}
}
