package com.appsquad.paybooks.model.research;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputstream {

	public static void main(String[] args) {
		//
		String pdfTypefile = ".pdf";
		String textTypefile = ".txt";
		String fileName = "godhand";
		String filePath = "C:\\pdf test\\"+fileName+textTypefile;
		//String filePath = "C:\\pdf test\\"+fileName+pdfTypefile;
		
		try {
			//fileWrite(filePath);
			fileWrite(filePath, 5);
			//fileGetFd(filePath);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void fileWrite(String filePath) throws IOException{
		FileOutputStream fileOutputStream = new FileOutputStream(filePath);
		int num = 22;
		byte b = 55;
		//fileOutputStream.write(num);
		fileOutputStream.write(b);
		if(fileOutputStream != null){
			fileOutputStream.close();
		}
	}
	public static void fileWrite(String filepath, int i) throws IOException{
		FileOutputStream fileOutputStream = new  FileOutputStream(filepath);
		String byteStr = "This is the beauty of Appsquad";
		byte[] b= byteStr.getBytes();
		byte[] byt = {65,66,67,68,69,70};
		fileOutputStream.write(b);
		fileOutputStream.write(byt, 2, 3);
		//fileOutputStream.write(byt);
		if(fileOutputStream != null){
			fileOutputStream.close();
		}
	}
	public static void fileGetFd(String filePath) throws IOException{
		FileOutputStream fileOutputStream = null;
		FileDescriptor descriptor = null;
		fileOutputStream = new FileOutputStream(filePath);
		descriptor = fileOutputStream.getFD();
		System.out.println("Descriptor "+ descriptor);
		if(fileOutputStream != null){
			fileOutputStream.close();
		}
	}
	
	
	
	
}
