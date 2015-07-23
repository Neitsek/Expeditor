package com.expeditor.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expeditor.bll.CommandeManager;
import com.expeditor.bll.Logger;
import com.expeditor.bo.Commande;
import com.expeditor.bo.Employe;

public class ProchaineCommandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProchaineCommandeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}
	
	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employe employe = (Employe)request.getSession().getAttribute("login");
		if (employe != null) {
			Commande prochaine = CommandeManager.SelectCommandeLaPlusUrgente(employe.getId());
			
			if (prochaine != null) {
				request.getSession().setAttribute("commandeEnCours", prochaine);
			}
			else {
				Logger.affiche("[ERROR][ProchaineCommandeServlet] Commande non trouvée");
			}
			
			request.getRequestDispatcher("/TraitementCommande").forward(request, response);
		}
		else {
			Logger.affiche("[ERROR][ProchaineCommandeServlet] Attribut 'login' non trouvé en session");
			
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
