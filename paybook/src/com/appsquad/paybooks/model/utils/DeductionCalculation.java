package com.appsquad.paybooks.model.utils;

public class DeductionCalculation {

	public static double getPtAmount(double gross){
		
		if (gross > 10000 && gross < 15001 ) {
			return 110.0;
		}else if (gross > 15000 && gross < 25001 ) {
			return 130.0; 
		}else if (gross > 25000 && gross < 40001 ) {
			return 150.0; 
		}else if (gross>40001) {
			return 200.0;
		}else {
			return 0.0;
		}
	}
	
	
}
