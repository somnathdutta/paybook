package com.appsquad.paybooks.model.research;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputstream {

	public static void main(String[] args) {
		
		String pdfTypefile = ".pdf";
		String textTypefile = ".txt";
		String fileName = "godhand";
		String pdfFile = "Hello.pdf";
		String filePath = "C:\\pdf test\\"+fileName+textTypefile;
		//String filePath = "C:\\pdf test\\"+pdfFile;
		
		try {
			//read(filePath);
			//read(filePath, "");
			//read(filePath, 0);
			read(filePath, 0.0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public static void read(String filePath) throws IOException{
		FileInputStream fileInputStream = new FileInputStream(filePath);
		int i= fileInputStream.read();
		System.out.println((char)i);
		if(fileInputStream != null){
			fileInputStream.close();
		}
	}
	public static void read(String filPath, String s) throws IOException{
		FileInputStream fileInputStream = new FileInputStream(filPath);
		int i =0;
		while ((i = fileInputStream.read()) != -1) {
			System.out.print((char)i);
		}
		if(fileInputStream != null){
			fileInputStream.close();
		}
	}
	public static void read(String filePath, int a) throws IOException{
		FileInputStream fileInputStream = new FileInputStream(filePath);
		int i =0;
		System.out.println("Available " + fileInputStream.available());
		while ((i = fileInputStream.read()) != -1) {
			System.out.print((char)i);
		}
		if(fileInputStream != null){
			fileInputStream.close();
		}
	}
	public static void read(String filePath, double d) throws IOException{
		FileInputStream fileInputStream = new FileInputStream(filePath);
		int size = fileInputStream.available();
		
		byte[] byt = new byte[size];
		char ch;
		int a= fileInputStream.read(byt);
		while (a != -1 ) {
			
			a = a-1;
		}
		
		
		
		/*byte[] b = new byte[size];
		int j =0;
		char c;
		j= fileInputStream.read(b);
		for(byte bs : b){
			c = (char)bs;
			System.out.print(c);
		}*/
		
		
		
		/*int i = 0;
		while ((i = fileInputStream.read()) != -1) {
			System.out.print((char)i);
		}*/
		
		if(fileInputStream != null){
			fileInputStream.close();
		}
		
		
		
	}
	
}
