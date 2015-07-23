package com.expeditor.servlets;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expeditor.bll.Connexion;
import com.expeditor.bo.Commande;
import com.expeditor.bo.Employe;
import com.expeditor.dal.CommandeDB;
import com.expeditor.dal.EmployeDB;

/**
 * Servlet implementation class SuiviCommandeEmploye
 */
public class SuiviCommandeEmploye extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuiviCommandeEmploye() {
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
		//vérification de l'etat de la session
		if(Connexion.verifSession(request.getSession().getAttribute("login")) == true){	
			//si la page est appelé pour la premiere fois
			ArrayList<Employe> listEmploye = new ArrayList<Employe>();
			listEmploye = EmployeDB.getAllEmployeWithoutManager();
			request.setAttribute("listeEmploye", listEmploye);
			
			//si la page est appelé pour un filtrage
			if(request.getParameter("reload") != null){
				System.out.println(request.getParameter("etatSel"));
				Date dateDeb = null;
				Date dateF = null;
				String dateDebut = null;
				String dateFin = null;
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				if(request.getParameter("dateDebut") != ""){
					dateDebut = request.getParameter("dateDebut");
				}
				if(request.getParameter("dateFin") != ""){
					dateFin = request.getParameter("dateFin");
				}
				try {
					if(dateDebut != ""){
						dateDeb = formatter.parse(dateDebut);
					}
					if(dateFin != ""){
						dateF = formatter.parse(dateFin);
					}
			 
				} catch (ParseException e) {
					e.printStackTrace();
				}
				ArrayList<String> listeEtat = new ArrayList<String>();
				for (String etat : request.getParameterValues("etatSel")){
					listeEtat.add(etat);
				}
				System.out.println(listeEtat); 
				System.out.println(dateDeb);
				System.out.println(dateF);
				System.out.println(Integer.parseInt(request.getParameter("employeSel")));
				
				ArrayList<Commande> listeCommande = CommandeDB.selectCommandes(listeEtat, dateDeb, dateF, Integer.parseInt(request.getParameter("employeSel")));
				
				System.out.println(listeCommande);
				if (listeCommande != null){
					request.setAttribute("listeCommande", listeCommande);
				}
			}
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
