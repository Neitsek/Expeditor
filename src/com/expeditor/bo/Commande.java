package com.expeditor.bo;

import java.util.Date;
import java.util.List;

public class Commande {
	private int id_commande;
	private Date date_commande;
	private String client;
	private String adresse;
	private int employe;
	private Date date_debut_prepa;
	private Date date_fin_prepa;
	private String etat;
	private List<CommandeArticle> articles;


	public Commande(){
		super();
	}

	public Commande(int id_commande,Date date_commande,String client,String adresse, int employe, Date date_debut_prepa, Date date_fin_prepa, String etat){
		super();
		this.id_commande = id_commande;
		this.date_commande = date_commande;
		this.client=client;
		this.adresse=adresse;
		this.employe=employe;
		this.date_debut_prepa=date_debut_prepa;
		this.date_fin_prepa=date_fin_prepa;
		this.etat=etat;
	}

	public int getId_commande() {
		return id_commande;
	}

	public void setId_commande(int id_commande) {
		this.id_commande = id_commande;
	}

	public Date getDate_commande() {
		return date_commande;
	}

	public void setDate_commande(Date date_commande) {
		this.date_commande = date_commande;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getEmploye() {
		return employe;
	}

	public void setEmploye(int employe) {
		this.employe = employe;
	}

	public Date getDate_debut_prepa() {
		return date_debut_prepa;
	}

	public void setDate_debut_prepa(Date date_debut_prepa) {
		this.date_debut_prepa = date_debut_prepa;
	}

	public Date getDate_fin_prepa() {
		return date_fin_prepa;
	}

	public void setDate_fin_prepa(Date date_fin_prepa) {
		this.date_fin_prepa = date_fin_prepa;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public List<CommandeArticle> getArticles() {
		return articles;
	}

	public void setArticles(List<CommandeArticle> articles) {
		this.articles = articles;
	}
}
