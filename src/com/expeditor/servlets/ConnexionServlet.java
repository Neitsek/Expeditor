package com.expeditor.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.expeditor.bll.Connexion;
import com.expeditor.bo.Employe;
import com.expeditor.dal.EmployeDB;

/**
 * Servlet implementation class Connexion
 */
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ConnexionServlet() {
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
		//récupération du login et mdp et traitement pour connexion
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		Employe employe = (Employe) EmployeDB.getOne(login, password);
		
		if (employe != null){
			/* Création ou récupération de la session */
			request.getSession().setAttribute("login", employe);
			
			request.setAttribute("nom", employe.getNom());
			request.setAttribute("prenom", employe.getPrenom());	
			if(employe.getIsManager() == true){
				request.setAttribute("isManager", "true");
			}else{
				request.setAttribute("isManager", "false");
			}
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/listeCommandes.jsp");
			requestDispatcher.forward(request, response) ;
		}else{
			Object error = "Erreur de login ou de mot de passe, veuillez réessayer.";
			request.setAttribute("error", error);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
			requestDispatcher.forward(request, response);
			
		}
		
		//vérification de l'etat de la session
		if(Connexion.verifSession(request.getSession().getAttribute("login")) == true){
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/listeCommandes.jsp");
			requestDispatcher.forward(request, response) ;
		}else{
			Object error = "vous n'êtes pas connecté, veuillez vous connecter.";
			request.setAttribute("error", error);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
			requestDispatcher.forward(request, response) ;
		}	
		
	}
}
