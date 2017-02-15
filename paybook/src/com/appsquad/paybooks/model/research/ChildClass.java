package com.appsquad.paybooks.model.research;

public class ChildClass extends ParentClass{
	private int c;
	
	public void cm(){
		System.out.println("child method");
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}
}
