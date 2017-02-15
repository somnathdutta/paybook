package com.appsquad.paybooks.model.research;

public class Test {

	public static void main(String[] args) {
		
		ChildClass childClass = new ChildClass();
		childClass.setC(0);
		childClass.cm();
		childClass.setP(1); //parant data
		childClass.pm();
		
		
		ParentClass parentClass = new ParentClass();
		parentClass.setP(2);
		parentClass.pm();
		//parentClass.cm(); the members and methods is available in child but not available in parent will not available, parent class can't access those
		
		ParentClass p = new ChildClass();
		p.getP();
		p.pm();
	
		
		
	}
	
	
}
