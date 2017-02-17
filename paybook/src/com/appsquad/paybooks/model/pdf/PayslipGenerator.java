package com.appsquad.paybooks.model.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

import org.zkoss.io.Files;
import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.GeneratePayslipBean;
import com.appsquad.paybooks.model.service.GeneratePayslipService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

public class PayslipGenerator {
	private String filePath;
	private Document document = null;
	private PdfWriter writer = null;
	
	Font normalFont = new Font(Font.FontFamily.HELVETICA, 10.7f, Font.NORMAL);
	Font earnDeductionLabelboldFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
	int tableoutLineWidth = 94;
	int cellpadding = 2;
	
	public void getSlipDetails(String path, ArrayList<GeneratePayslipBean> employeePayBeanList,
			GeneratePayslipBean headerBean) throws Exception, DocumentException{
		
		filePath = path+"paySlip.pdf";
		System.out.println("My file path :: "+filePath);
		
		for(GeneratePayslipBean bean : employeePayBeanList){
			
			document = new Document(PageSize.A4, 1, 1, 40, 50);
			writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
			writer.setBoxSize("art", new Rectangle(40, 58, 900, 850));
			
			document.open();
			document.add(TestTableGenerator.createHeaderTable(headerBean, tableoutLineWidth));  								// header table
			document.add(TestTableGenerator.gapTable(tableoutLineWidth));                     									// gap between header and middle table
			document.add(TestTableGenerator.generateMiddleTable(bean, tableoutLineWidth, normalFont, cellpadding));				// middle table (employee details)  bean will be changed
			document.add(TestTableGenerator.gapTable(tableoutLineWidth));                     									// gap between middle and salary table
			document.add(TestTableGenerator.earningDeductionLabel(earnDeductionLabelboldFont, tableoutLineWidth, cellpadding)); // earning and deduction label
			document.add(TestTableGenerator.generateSalaryTable(bean, tableoutLineWidth,normalFont, cellpadding ));		 		// salary table bean will be from list
			document.add(TestTableGenerator.totalAmnt(bean, earnDeductionLabelboldFont, tableoutLineWidth, cellpadding));       // total amount earning and deduction
			document.add(TestTableGenerator.lastTable(bean, earnDeductionLabelboldFont, tableoutLineWidth, cellpadding));		// last table(summary)
			document.add(TestTableGenerator.gapTable(tableoutLineWidth));                     									// gap between middle and salary table
			document.add(TestTableGenerator.digitalStatment(tableoutLineWidth));				 								// digital signature
			
			document.close();
			DownloadPdf.download(filePath, bean.getEmployeeName());
			
		}
		
	}
	
	public String getSlipDetailsPerEmp(String path, GeneratePayslipBean bean,
			GeneratePayslipBean headerBean,boolean isMailRequired) throws Exception, DocumentException{
		
		filePath = path+"paySlip.pdf";
		System.out.println("My file path :: "+filePath);
			
			document = new Document(PageSize.A4, 1, 1, 40, 50);
			writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
			writer.setBoxSize("art", new Rectangle(40, 58, 900, 850));
			
			document.open();
			document.add(TestTableGenerator.createHeaderTable(headerBean, tableoutLineWidth));  								// header table
			document.add(TestTableGenerator.gapTable(tableoutLineWidth));                     									// gap between header and middle table
			document.add(TestTableGenerator.generateMiddleTable(bean, tableoutLineWidth, normalFont, cellpadding));				// middle table (employee details)  bean will be changed
			document.add(TestTableGenerator.gapTable(tableoutLineWidth));                     									// gap between middle and salary table
			document.add(TestTableGenerator.earningDeductionLabel(earnDeductionLabelboldFont, tableoutLineWidth, cellpadding)); // earning and deduction label
			document.add(TestTableGenerator.generateSalaryTable(bean, tableoutLineWidth,normalFont, cellpadding ));		 		// salary table bean will be from list
			document.add(TestTableGenerator.totalAmnt(bean, earnDeductionLabelboldFont, tableoutLineWidth, cellpadding));       // total amount earning and deduction
			document.add(TestTableGenerator.lastTable(bean, earnDeductionLabelboldFont, tableoutLineWidth, cellpadding));		// last table(summary)
			document.add(TestTableGenerator.gapTable(tableoutLineWidth));                     									// gap between middle and salary table
			document.add(TestTableGenerator.digitalStatment(tableoutLineWidth));				 								// digital signature
			
			document.close();
			bean.setMonth(headerBean.getMonth());
			bean.setYear(headerBean.getYear());
			if(isMailRequired){
				if(SendAttachment.generateAndSendEmailForApproveOrReject(bean, "status", filePath)){
					Messagebox.show("One mail is sent to your mail id,kindly check it","Mail Success",Messagebox.OK,Messagebox.INFORMATION);
				}
			}else{
				DownloadPdf.download(filePath, bean.getEmployeeName()+"--"+headerBean.getMonth()+"--"+headerBean.getYear());
			}
			//if(SendAttachment.generateAndSendEmailForApproveOrReject(bean, "status", filePath)){
				
			//}
			
			return filePath;	
	}
	
	public void getSlipDetails(String path, ArrayList<GeneratePayslipBean> beanList,
			GeneratePayslipBean headerBean,boolean isMailRequired) throws Exception, DocumentException{
		
		filePath = path+"paySlip.pdf";
		System.out.println("My file path :: "+filePath);
			
			document = new Document(PageSize.A4, 1, 1, 40, 50);
			writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
			writer.setBoxSize("art", new Rectangle(40, 58, 900, 850));
			
			document.open();
			for(GeneratePayslipBean bean : beanList){
				int noOfDays = GeneratePayslipService.getNoOfDaysInMonth(Integer.parseInt(bean.getYear()), bean.getMonthId()-1 );
				bean.setTotalNoOfDaysInMonth(noOfDays);
				double[] totalAmounts = GeneratePayslipService.getAllAmounts(bean);
				
				bean.setTotalEarningAmnt(totalAmounts[0]);
				bean.setTotalDeductionAmnt(totalAmounts[1]);
				bean.setNetPayAmount(totalAmounts[2]);
			document.add(TestTableGenerator.createHeaderTable(headerBean, tableoutLineWidth));  								// header table
			document.add(TestTableGenerator.gapTable(tableoutLineWidth));                     									// gap between header and middle table
			document.add(TestTableGenerator.generateMiddleTable(bean, tableoutLineWidth, normalFont, cellpadding));				// middle table (employee details)  bean will be changed
			document.add(TestTableGenerator.gapTable(tableoutLineWidth));                     									// gap between middle and salary table
			document.add(TestTableGenerator.earningDeductionLabel(earnDeductionLabelboldFont, tableoutLineWidth, cellpadding)); // earning and deduction label
			document.add(TestTableGenerator.generateSalaryTable(bean, tableoutLineWidth,normalFont, cellpadding ));		 		// salary table bean will be from list
			document.add(TestTableGenerator.totalAmnt(bean, earnDeductionLabelboldFont, tableoutLineWidth, cellpadding));       // total amount earning and deduction
			document.add(TestTableGenerator.lastTable(bean, earnDeductionLabelboldFont, tableoutLineWidth, cellpadding));		// last table(summary)
			document.add(TestTableGenerator.gapTable(tableoutLineWidth));                     									// gap between middle and salary table
			document.add(TestTableGenerator.digitalStatment(tableoutLineWidth));				 								// digital signature
			}
			document.close();
			DownloadPdf.download(filePath, "slip");	
	}
	
	public void uploadPaySlip(String paySlipFile){
		String fileName =null;
		String uploadImageDir = "";
		String uploadImagePath = "";
		URL resourceUrl = this.getClass().getResource("/");
		String filePath = resourceUrl.getFile();
		String serverRootDir = new File(new File(filePath).getParent()).getParent();

		String currentDate = new SimpleDateFormat("dd-MMM-yyyy").format(new java.util.Date()).toString();
		
		String uploadPath = serverRootDir.toString()+File.separator+"uploads"+"/"+"PaySlip"+ File.separator+currentDate;
		File fileDir = new File(uploadPath);
		 if(!fileDir.exists()){
			 fileDir.mkdirs();
			 System.out.println("New Directory Created..");
		 }
		
		String cuisingName = paySlipFile;
		cuisingName = cuisingName.replaceAll("\\s", "");
		String rand = UUID.randomUUID().toString().substring(0, 30);
		String generateName = cuisingName+"_"+rand;
	   // fileName = generateName.toUpperCase();
		fileName = paySlipFile;
	    uploadImageDir = serverRootDir.toString()+File.separator+"view"+File.separator+"uploads"+"/"+"PaySlip"+ File.separator+currentDate;
		
	    uploadImagePath ="uploads"+"/"+"PaySlip"+"/"+currentDate+"/"+fileName;
	    //Files.copy(new File(filePath + fileName), media.getStreamData());
	    System.out.println(uploadImagePath);
	}
	
}
