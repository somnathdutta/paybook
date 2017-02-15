package com.appsquad.paybooks.model.research;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class DownloadFile {

	public static void main(String[] args) {
		
		String url = "C:\\workspace\\wip\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\paybook\\paySlip.pdf";
		
		try {
			downloadUsingStream(url, "C:\\pdf test\\downloads\\paySlip.pdf");
		
		
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	private static void downloadUsingStream(String urlStr, String file)throws IOException{
		URL url = new URL(urlStr);
		BufferedInputStream bis = new BufferedInputStream(url.openStream());
		FileOutputStream fis = new FileOutputStream(file);
		byte[] buffer = new byte[1024];
		int count =0;
		while ((count = bis.read(buffer,0,1024)) !=-1) {
			fis.write(buffer, 0, count);
		}
		fis.close();
		bis.close();
		
	}
	
}
