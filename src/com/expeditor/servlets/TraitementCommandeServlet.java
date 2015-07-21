package com.expeditor.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TraitementCommandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String path = "/employe/TraitementCommande";

    public TraitementCommandeServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}
	
	private void execute(HttpServletRequest request, HttpServletResponse response) {
		boolean logged = false;
		
		Cookie[] cookies = request.getCookies();
		
		for ( Cookie current : cookies) {
			if ( current.getName().equals("logged") ) {
				logged = true;
				break;
			}
		}
		
		if (!logged) {
			//TODO Redirection vers login
		}
		else {
			request.getRequestDispatcher("");
		}
	}
}
