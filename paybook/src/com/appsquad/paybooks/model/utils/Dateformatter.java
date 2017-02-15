package com.appsquad.paybooks.model.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dateformatter {
	String date1;
	
	public static String formatdate(Date date) {
		if (date != null) {
			/*return new SimpleDateFormat("dd-MMM-yyyy").format(date);*/
			return new SimpleDateFormat("yyyy-MM-dd").format(date);
		} else {
			return "";
		}
	}
	
	public static String toStringdate(Date date) {
		return new SimpleDateFormat("dd-MMM-yyyy").format(date);
	}
	
	public static Date getDate(String dd,String mm,String year){
		Date date = null;
		String dateStr = dd+"-"+mm+"-"+year;
		date = todate(dateStr);
		return date; 
	}
	
	public static Date todate(String ddMMMyyy) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = dateFormat.parse(ddMMMyyy);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public static String toStringDate(String yyyyMMDD){
		  String reformattedDate = "";
		  SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
		  //SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		  SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		  
		   try { 
		       reformattedDate = myFormat.format(fromUser.parse(yyyyMMDD));
		   } catch (ParseException e) {
		       e.printStackTrace();
		   }
		   return reformattedDate;
	}
	
	/************* current date *****************/
	public static Date date(){
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-YYYY");
		Date date = new Date();
		
		System.out.println(dateFormat.format(date));
		
		return date;
	}
	
	/************** util to sql date ****************/
	public static java.sql.Date sqlDate(Date date){
		java.sql.Date dateSql = new java.sql.Date(date.getTime());
		return dateSql;
		
	}
	public static Dateformatter getInstance() {
		return new Dateformatter();
	}
}
