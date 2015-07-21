package com.expeditor.bll;

public class Logger {

	public static void affiche(String message) {		
		System.out.println(message);		
	}

	public static void arret(String message) {		
		System.err.println(message);		
		//System.exit(99);		
	} 
	
}
