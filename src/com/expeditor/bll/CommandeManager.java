package com.expeditor.bll;

import java.util.Date;

import com.expeditor.bo.Commande;
import com.expeditor.dal.CommandeDB;

public class CommandeManager {
	private CommandeManager() {
		
	}
	
	public static Commande SelectCommandeLaPlusUrgente(Integer id_employe) {
		Commande commande = CommandeDB.selectCommandeLaPlusUrgente();
		
		if (commande != null) {
			commande.setEmploye(id_employe);
			commande.setEtat("EC");
			commande.setDate_debut_prepa(new Date());
			CommandeDB.updateCommande(commande);
		
			return commande;
		}
		else {
			return null;
		}
	}
}
