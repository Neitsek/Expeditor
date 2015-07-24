package com.expeditor.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.expeditor.bll.Logger;
import com.expeditor.bll.Outils;
import com.expeditor.bo.Article;
import com.expeditor.bo.Commande;
import com.expeditor.bo.CommandeArticle;

public class CommandeDB {
	private CommandeDB(){
	}

	private static final String INSERT = "insert into commande " +
			" (date_commande,client,adresse,employe,date_debut_prepa,date_fin_prepa,etat)" +
			" values(?,?,?,?,?,?,?)";
	
	private static final String SELECT_PAR_URGENCE = "SELECT * FROM Commande WHERE etat = 'ATT' AND employe IS NULL AND date_commande = ( SELECT MIN(date_commande) FROM Commande WHERE etat = 'ATT' );";
	
	private static final String UPDATE = "UPDATE commande SET date_commande = ?, client = ?, adresse = ?, employe = ?, date_debut_prepa = ?, date_fin_prepa = ?, etat = ? WHERE id_commande = ?";
	
	private static final String SELECT_ARTICLES = "SELECT * FROM commande_article CA JOIN article A ON CA.article = A.id_article WHERE commande = ?";
	
	private static final String SELECT_COMMANDEENCOURS = "SELECT * FROM commande WHERE employe = ? AND etat = 'EC'";
	
	private static Commande buildCommande(ResultSet rs) {
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
	
	private static CommandeArticle buildCommandeArticle(ResultSet rs) {
		CommandeArticle com = null;
		Article art = null;
		
		try {
			int idCommandeArticle = rs.getInt("id_commande_article");
			int idArticle = rs.getInt("id_article");
			int qte = rs.getInt("quantite");
			String nom = rs.getString("nom");
			Integer poids = rs.getInt("poids");
			
			art = new Article();
			art.setId(idArticle);
			art.setNom(nom);
			art.setPoids(poids);
			
			com = new CommandeArticle();
			com.setId_commande_article(idCommandeArticle);
			com.setQuantite(qte);
			com.setArticle(art);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return com;
	}
	
	public static List<CommandeArticle> getArticles(Integer id_commande) {
		List<CommandeArticle> listArticles = new ArrayList<CommandeArticle>();
		
		ResultSet rs = ConnectionDB.select(SELECT_ARTICLES, id_commande);
		
		if (rs != null) {
			try {
				while(rs.next()) {
					listArticles.add(buildCommandeArticle(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listArticles;
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



	private static String SELECT = "SELECT * FROM Commande " +
			"WHERE 0=0 ";

	/**
	 * sélection des commandes	 
	 * @param etat
	 * @param date_debut
	 * @param date_fin
	 */
	public static ArrayList<Commande> selectCommandes(ArrayList<String> listeEtat, Date date_debut, Date date_fin, int id_employe) {
		ArrayList<Commande> listCommande = new ArrayList<Commande>();
		// -- construction de la requete
		// gestion des états
		String query = SELECT;
		if (listeEtat.size()>0){

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
			new java.sql.Date(date_debut.getTime());
			DateFormat df = new SimpleDateFormat("yyyy-dd-MM");			
			query += "AND date_commande BETWEEN '" + df.format(date_debut) + "' AND '" + df.format(date_fin) + "'";
		}
		
		if(id_employe!=0)
		{
			query += "AND Employe.id_employe ='"+id_employe+"' ";
		}
		
		query += "ORDER BY id_commande";

		ResultSet rs = ConnectionDB.select(query);
		
		try {
			while(rs.next()) {
				listCommande.add(buildCommande(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		


		return listCommande;
	}
	
	public static Commande selectCommandeLaPlusUrgente() {
		Commande com = null;
		ResultSet rs = null;

		rs = ConnectionDB.select(SELECT_PAR_URGENCE);
		
		try {
			while(rs.next())
			{
				com = buildCommande(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (com != null) {
			com.setArticles(getArticles(com.getId_commande()));
		}
		
		return com;
	}
	
	public static void updateCommande(Commande commande) {
		updateCommande(commande.getId_commande(), commande.getDate_commande(), commande.getClient(), commande.getAdresse(), commande.getEmploye(), commande.getDate_debut_prepa(), commande.getDate_fin_prepa(), commande.getEtat());
	}
	
	public static void updateCommande (Integer id_commande, Date date_commande, String client, String adresse, Integer employe, Date date_debut_prepa, Date date_fin_prepa, String etat) {
		ConnectionDB.update(UPDATE, date_commande, client, adresse, employe, date_debut_prepa, date_fin_prepa, etat, id_commande );
	}
	
	public static Commande selectCommandeEnCours(Integer id_employe) {
		Commande com = null;
		
		ResultSet rs = ConnectionDB.select(SELECT_COMMANDEENCOURS, id_employe);
		
		try {
			if (rs.next()) {
				com = buildCommande(rs);
				com.setArticles(getArticles(com.getId_commande()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return com;
	}
	
	private static String SELECT2 = "SELECT * FROM Commande, Employe " +
			"WHERE Commande.employe = Employe.id_employe ";
	
	/**
	 * sélection des commandes	 
	 * @param etat
	 * @param date_debut
	 * @param date_fin
	 */
	public static ArrayList<Commande> selectCommandesSuivi(ArrayList<String> listeEtat, Date date_debut, Date date_fin, int id_employe) {
		ArrayList<Commande> listCommande = new ArrayList<Commande>();
		// -- construction de la requete
		// gestion des états
		String query = SELECT2;
		if (listeEtat.size()>0){

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
			new java.sql.Date(date_debut.getTime());
			DateFormat df = new SimpleDateFormat("yyyy-dd-MM");			
			query += "AND date_commande BETWEEN '" + df.format(date_debut) + "' AND '" + df.format(date_fin) + "'";
		}
		
		if(id_employe!=0)
		{
			query += "AND Employe.id_employe ='"+id_employe+"' ";
		}
		
		query += "ORDER BY id_commande";

		ResultSet rs = ConnectionDB.select(query);
		
		try {
			while(rs.next()) {
				listCommande.add(buildCommande(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		


		return listCommande;
	}
}
