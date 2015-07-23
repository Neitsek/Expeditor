package com.expeditor.bll;

import com.expeditor.bo.Employe;
import com.expeditor.dal.EmployeDB;

public class EmployeManager {

	public static String nomPrenom(int Id){
	
		Employe emp = EmployeDB.getOneId(Id);
		
		return emp.getNom() + " " + emp.getPrenom();
	}	
}
