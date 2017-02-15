package com.appsquad.paybooks.model.research;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DesimalFormat {
	
	public static String twoDecimalFormat(double d){
		NumberFormat nf = new DecimalFormat("#0.00");
		//Double amnt = Double.parseDouble(nf.format(d));
		String str = nf.format(d);
		return str;
	}
}
