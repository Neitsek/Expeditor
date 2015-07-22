package com.expeditor.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class actuListeCommande
 */
public class actuListeCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public actuListeCommande() {
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
			HttpServletResponse response) throws ServletException, IOException {
		
		String debut = request.getParameter("debut");
		String fin = request.getParameter("fin");
		
		if(fin != "" && debut != ""){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date dDebut 	= sdf.parse(request.getParameter("debut"));
				Date dFin 		= sdf.parse(request.getParameter("fin"));
			} catch (ParseException e) {			
				e.printStackTrace();
			}  
		}
 		
		ArrayList<String> listeEtats = new ArrayList<String>();		
		String[] checkboxes = request.getParameterValues("etat");		 
		if (checkboxes != null) {		    		
		    for (int i = 0; i < checkboxes.length; ++i) {   		      
		        listeEtats.add(checkboxes[i]);
		    }
		}
				
		
		
	}
}
