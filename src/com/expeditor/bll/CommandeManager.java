package com.expeditor.bll;

import com.expeditor.bo.Commande;
import com.expeditor.dal.CommandeDB;

public class CommandeManager {
	private CommandeManager() {
		
	}
	
	public static Commande SelectCommandeLaPlusUrgente(Integer id_employe) {
		Commande commande = CommandeDB.selectCommandeLaPlusUrgente();
		
		if (commande != null) {
			commande.setEmploye(id_employe);
			CommandeDB.updateCommande(commande);
		
			return commande;
		}
		else {
			return null;
		}
	}
}
