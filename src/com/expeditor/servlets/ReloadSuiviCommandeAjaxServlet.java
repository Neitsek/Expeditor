package com.expeditor.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expeditor.bll.Connexion;
import com.expeditor.bo.Employe;
import com.expeditor.dal.CommandeDB;
import com.expeditor.dal.EmployeDB;

/**
 * Servlet implementation class ReloadSuiviCommandeAjaxServlet
 */
public class ReloadSuiviCommandeAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReloadSuiviCommandeAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		execute(request, response);
	}
	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getParameter("dateDebut");
		request.getParameter("dateFin");
		request.getParameter("idEmploye");
		ArrayList<String[]> listeEtat = new ArrayList<String[]>();
		listeEtat.add(request.getParameter("listeEtat").split(null, ','));
		
	
		
		System.out.println(request.getParameter("dateDebut"));
		System.out.println(request.getParameter("idEmploye"));
		System.out.println(listeEtat);
		
		//vérification de l'etat de la session
		if(Connexion.verifSession(request.getSession().getAttribute("login")) == true){
			//TODO : recupération dans commandeDB des commande avec les parametre au dessus
			//CommandeDB.selectCommandes(request.getParameter("listeEtat"), request.getParameter("dateDebut"), request.getParameter("dateFin"), request.getParameter("idEmploye"));
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/manager/suiviCommande.jsp");
			requestDispatcher.forward(request, response) ;
		}else{
			Object error = "vous n'êtes pas connecté, veuillez vous connecter.";
			request.setAttribute("error", error);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
			requestDispatcher.forward(request, response) ;
		}
	}

}
