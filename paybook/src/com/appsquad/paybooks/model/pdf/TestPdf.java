package com.appsquad.paybooks.model.pdf;

import java.io.FileOutputStream;



import java.io.IOException;
import java.net.MalformedURLException;








import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class TestPdf {

	public static void main(String[] args) throws DocumentException, MalformedURLException, IOException {
		
		//String filePath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
		String path = "C:\\pdf test\\Hello.pdf";
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));;
		
		document.open();
		document.add(new Paragraph("This is My Final Test Pdf"));
		document.addAuthor("TEST");
		
		/*String url = "http://i.imgur.com/7xRBVvQ.jpg";
		Image image = Image.getInstance(new URL(url));
		document.add(image);*/
		document.add(headerTable());
		document.add(createMiddleTable());
		
		document.close();
		writer.close();	
	}
	
	public static PdfPTable headerTable() throws DocumentException{
		
		float[] columnWidths = {1f, 3f}; 
		
		PdfPTable table = new PdfPTable(2);
		
		table.setWidthPercentage(98);
		table.setSpacingBefore(10f);
		table.setSpacingAfter(10f);
		table.setWidths(columnWidths);
		
		PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 1"));
		cell1.setBorderColor(BaseColor.PINK);
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		
		PdfPCell cell2 = new PdfPCell(insideHeader());
		cell2.setBorder(Rectangle.NO_BORDER);
		cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		
		table.addCell(cell1);
		table.addCell(cell2);
		
		return table;
	}
	
	public static PdfPTable insideHeader(){
		
		PdfPTable table = new PdfPTable(1);
		
		Phrase phrase1 = new Phrase("AppsQuad 1");
		PdfPCell cell1 = new PdfPCell(phrase1);
		cell1.setBorder(Rectangle.NO_BORDER);
		
		Phrase phrase2 = new Phrase("AppsQuad 2");
		PdfPCell cell2 = new PdfPCell(phrase2);
		cell2.setBorder(Rectangle.NO_BORDER);
		
		
		Phrase phrase3 = new Phrase("AppsQuad 3");
		PdfPCell cell3 = new PdfPCell(phrase3);
		cell3.setBorder(Rectangle.NO_BORDER);
		
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		
		return table;
	}
	
	public static PdfPTable createMiddleTable() throws DocumentException{
		
		float[] colWidths = {2f,2f};
		
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(98);
		table.setWidths(colWidths);
		
		PdfPCell cell1 = new PdfPCell(createMiddleTableL());
		
		PdfPCell cell2 = new PdfPCell(createMiddleTableR());
		
		
		table.addCell(cell1);
		table.addCell(cell2);
		
		return table;
	}
	
	public static PdfPTable createMiddleTableL() throws DocumentException{
		
		float[] colWidths = {2f,0.4f,2f};
		
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(98);
		table.setWidths(colWidths);
		
		PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 3"));
		cell1.setBorderColor(BaseColor.BLUE);
		
		PdfPCell cell2 = new PdfPCell(new Paragraph(":"));
		cell2.setBorderColor(BaseColor.GREEN);
		
		PdfPCell cell3 = new PdfPCell(new Paragraph("Cell 5"));
		cell3.setBorderColor(BaseColor.RED);
		
		
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		
		
		return table;
	}
	
	public static PdfPTable createMiddleTableR() throws DocumentException{
		
		float[] colWidths = {2f,2f,2f};
		
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(98);
		table.setWidths(colWidths);
		
		PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 3"));
		cell1.setBorderColor(BaseColor.BLACK.brighter());
		
		PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 4"));
		cell2.setBorderColor(BaseColor.CYAN);
		
		PdfPCell cell3 = new PdfPCell(new Paragraph("Cell 5"));
		cell3.setBorderColor(BaseColor.MAGENTA);
		
		
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		
		
		return table;
	}
	
}
