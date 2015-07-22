package com.expeditor.bo;

public class CommandeArticle {
	private Integer id_commande_article;
	private Article article;
	private Integer quantite;
	
	public CommandeArticle() {
		
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public Integer getId_commande_article() {
		return id_commande_article;
	}

	public void setId_commande_article(Integer id_commande_article) {
		this.id_commande_article = id_commande_article;
	}
}
