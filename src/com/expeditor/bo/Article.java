package com.expeditor.bo;

public class Article {
	private String nom;
	private double poids;
	private Integer id;
	
	public Article() {
	}
	
	public Article(Integer id, String nom, double poids) {
		setId(id);
		setNom(nom);
		setPoids(poids);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
