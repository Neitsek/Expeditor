package com.expeditor.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expeditor.bo.Commande;
import com.expeditor.dal.CommandeDB;
import com.expeditor.dal.ConnectionDB;

public class TerminerCommandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TerminerCommandeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Commande com = (Commande)request.getSession().getAttribute("commandeEnCours");
		
		com.setEtat("TER");
		com.setDate_fin_prepa(new Date());
		
		CommandeDB.updateCommande(com);
		
		request.getSession().removeAttribute("commandeEnCours");
		request.getSession().removeAttribute("impression");
		
		request.getRequestDispatcher("/TraitementCommande").forward(request, response);
	}

}
