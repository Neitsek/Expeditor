package com.expeditor.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
		
		ArrayList<String> listeEtats = new ArrayList<String>();
		ArrayList<Commande> listeCommandes = new ArrayList<Commande>();
		Date dDebut = null;
		Date dFin = null;
		
		String isActu =  request.getParameter("actu");
		
		if ("OK".equals(isActu)){	
			/* Etats */
			String[] checkboxes = request.getParameterValues("etat");		 
			if (checkboxes != null) {		    		
			    for (int i = 0; i < checkboxes.length; ++i) {   		      
			        listeEtats.add(checkboxes[i]);
			    }
			}
			
			/* Date */
			String debut = request.getParameter("debut");
			String fin = request.getParameter("fin");
			
			if(fin != "" && debut != ""){
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					dDebut 	= sdf.parse(debut);
					dFin 	= sdf.parse(fin);
				} catch (ParseException e) {			
					e.printStackTrace();
				}  
			}
						
		}else{
			listeEtats.add("ATT");
			listeEtats.add("EC");
		}
		
		// -- Recupère la liste des commandes
		//listeCommandes = CommandeDB.selectCommandes(listeEtats, dDebut, dFin,0);
				
		
		// -- Envoie vers la jsp
		request.setAttribute("listeCommande", listeCommandes);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("listeCommandes.jsp");
		requestDispatcher.forward(request, response);			
	}
}
