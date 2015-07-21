package com.expeditor.bo;

public class Employe {
	private String nom;
	private String prenom;
	private String login;
	private String password;
	
	private Employe() {
		nom = "";
		prenom = "";
	}
	
	private Employe(String nom, String prenom) {
		
	}
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
