package com.expeditor.bo;

public class Employe {
	private Integer id;
	private String nom;
	private String prenom;
	private String login;
	private String password;
	private Boolean isManager;
	
	public Employe() {
	}
	
	public Employe(Integer id, String nom, String prenom, Boolean isManager) {
		setId(id);
		setNom(nom);
		setPrenom(prenom);
		setIsManager(isManager);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsManager() {
		return isManager;
	}

	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
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
