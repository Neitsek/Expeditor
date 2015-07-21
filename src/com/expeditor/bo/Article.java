package com.expeditor.bo;

public class Article {
	private String nom;
	private double poids;
	
	public Article() {
		nom = "";
		poids = 0;
	}
	
	public Article(String nom, double poids) {
		nom = nom;
		poids = poids;
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
}
