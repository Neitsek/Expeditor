package com.expeditor.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expeditor.bo.Commande;

public class ImpressionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ImpressionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO : Gérer l'impression du bon de livraison
	
		request.getSession().setAttribute("impression", true);
		
		request.getRequestDispatcher("/TraitementCommande").forward(request, response);
	}

}
