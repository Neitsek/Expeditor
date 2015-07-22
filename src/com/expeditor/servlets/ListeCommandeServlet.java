package com.expeditor.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expeditor.bo.Commande;
import com.expeditor.dal.CommandeDB;

/**
 * Servlet implementation class ListeCommandeServlet
 */
public class ListeCommandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeCommandeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	private void execute(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException{
		
		// -- Logger ? 
		
		// -- Paramètre pour la liste 
		//request.getParameterValues("lesEtats");				
		ArrayList<String> listeEtats = new ArrayList<String>();
		listeEtats.add("ATT");
		listeEtats.add("EC");
		
		// -- Recupère la liste des commandes
		ArrayList<Commande> listeCommandes = new ArrayList<Commande>();
		listeCommandes = CommandeDB.selectCommandes(listeEtats, null, null);
				
		// -- Envoie vers la jsp
		request.setAttribute("listeCommande", listeCommandes);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("listeCommandes.jsp");
		requestDispatcher.forward(request, response);			
	}
}
