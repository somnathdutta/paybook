package com.appsquad.paybooks.model.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.zkoss.util.media.AMedia;
import org.zkoss.zhtml.Filedownload;
import org.zkoss.zul.Messagebox;

public class DownloadPdf {
	public static void download(String pdfNamewithPath, String fileName) throws IOException{
		System.out.println("Download method calling..");
		System.out.println("FILE NAME " + fileName);
		  String path=pdfNamewithPath.replace('\\','/');
		  //Messagebox.show(path);
		  byte[] ba1 = new byte[1024];
		  File myFile = new File(path );
		  FileInputStream fis = new FileInputStream(myFile);
		  int baLength = 0;
		  ByteArrayOutputStream bios = new ByteArrayOutputStream();
		  try {
		   try{
		    while ( (baLength = fis.read(ba1)) != -1) {
		     bios.write(ba1, 0, baLength);
		    }

		   } catch (Exception e1) {
			   e1.printStackTrace();
		   }  
		   finally {
		    fis.close();
		    bios.close();
		   }
		   final AMedia amedia = new AMedia(fileName, "pdf", "application/pdf", bios.toByteArray());
		   Filedownload.save(amedia);
		  } catch (Exception ex) {
		   ex.printStackTrace();
		  } finally {
		   File xlsFile = new File(path);
		   if (xlsFile.exists()) {
		    FileUtils.forceDelete(xlsFile);
		    //xlsFile.delete();
		   }

		  }
		 }
}