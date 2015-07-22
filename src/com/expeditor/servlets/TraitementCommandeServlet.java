package com.expeditor.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expeditor.bo.Commande;
import com.expeditor.bo.Employe;

public class TraitementCommandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String ServletPath = "/employe/TraitementCommande";

    public TraitementCommandeServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}
	
	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employe user = (Employe) request.getSession().getAttribute("login");
		
		Commande commandeEnCours = (Commande) request.getSession().getAttribute("commandeEnCours");
		
		request.getRequestDispatcher("/employe/traitementCommande.jsp").forward(request, response);
	}
}
