package com.expeditor.bll;

public class Connexion {
	private static Boolean existe;

	public static Boolean verifSession(Object labelSession) {	
		
		if(labelSession != null){
			existe = true;
		}else{
			existe = false;
		}
		
		return existe;
	}
}
