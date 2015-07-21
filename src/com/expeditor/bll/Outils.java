package com.expeditor.bll;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Outils {

	
	public static Timestamp pTimestamp(Date d){	
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return  java.sql.Timestamp.valueOf(df.format(d));
	}
	
} 
