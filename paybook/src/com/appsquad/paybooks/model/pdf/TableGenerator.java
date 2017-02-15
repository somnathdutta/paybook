package com.appsquad.paybooks.model.pdf;

import com.appsquad.paybooks.bean.ComponentMasterBean;
import com.appsquad.paybooks.bean.GeneratePayslipBean;
import com.appsquad.paybooks.model.utils.NumberToWord;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.sun.javafx.fxml.BeanAdapter;


public class TableGenerator {
	static PdfPCell cell;
	
	public static PdfPTable createHeaderTable(GeneratePayslipBean headerBean){
		
			float[] widths = {5,15};
		
			PdfPTable table = new PdfPTable(widths);
			table.setWidthPercentage(95);
			
			PdfPCell cellL = new PdfPCell();
			cellL.setBorder(Rectangle.NO_BORDER);
			
			
			PdfPCell cellR = new PdfPCell(createRHSinnerHeaderTable(headerBean));
			cellR.setBorder(Rectangle.NO_BORDER);
			
			table.addCell(cellL);
			table.addCell(cellR);
			
		return table;
	}
	
	public static PdfPTable createRHSinnerHeaderTable(GeneratePayslipBean headerBean){
	
		PdfPTable table = new PdfPTable(1);
		
		
		Phrase companyName = new Phrase(headerBean.getCompanyName());
		cell = new PdfPCell(companyName);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase companyAddress = new Phrase(headerBean.getCompanyAddress());
		cell = new PdfPCell(companyAddress);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase others = new Phrase("Payslip for " + headerBean.getMonth() +"-"+headerBean.getYear());
		cell = new PdfPCell(others);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		return table;
	}
	
	
	///////////
	public static PdfPTable generateMiddleTable(GeneratePayslipBean bean){
		
		float[] width = {5,1,5,5,1,5};
		PdfPTable table = new PdfPTable(width);
		table.setWidthPercentage(95);
		
		PdfPCell cell1 = new PdfPCell(generateMiddleTableLeft());
		cell1.setBorderColor(BaseColor.BLACK);
		
		PdfPCell cell2 = new PdfPCell(createDottTable());
		cell2.setBorder(Rectangle.NO_BORDER);
		
		PdfPCell cell3 = new PdfPCell(generateMiddleTableLeft(bean));
		cell3.setBorder(Rectangle.NO_BORDER);
		
		PdfPCell cell4 = new PdfPCell(generateMiddleTableRight());
		cell4.setBorderColor(BaseColor.BLACK);
		
		PdfPCell cell5 = new PdfPCell(createDottTable());
		cell5.setBorder(Rectangle.NO_BORDER);
		
		PdfPCell cell6 = new PdfPCell(generateMiddleTableRight(bean));
		cell6.setBorder(Rectangle.NO_BORDER);
		
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);
		table.addCell(cell5);
		table.addCell(cell6);
		
		/*table.addCell(generateMiddleTableLeft());
		table.addCell(createDottTable());
		table.addCell(generateMiddleTableLeft(bean));
		table.addCell(generateMiddleTableRight());
		table.addCell(createDottTable());
		table.addCell(generateMiddleTableRight(bean));*/
		
		return table;
		
	}
	
	public static PdfPTable generateMiddleTableLeft(){
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(70);
		
		
		Phrase EmployeeName = new Phrase("Employee Name ");
		cell = new PdfPCell(EmployeeName);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase EmployeeID = new Phrase("Employee ID ");
		cell = new PdfPCell(EmployeeID);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase Designation = new Phrase("Designation ");
		cell = new PdfPCell(Designation);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase Department = new Phrase("Department ");
		cell = new PdfPCell(Department);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase doj = new Phrase("Date of Joining ");
		cell = new PdfPCell(doj);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase ESINumber = new Phrase("ESI Number ");
		cell = new PdfPCell(ESINumber);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		return table;
	}
	
	public static PdfPTable generateMiddleTableLeft(GeneratePayslipBean empdetBean){
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		
		Phrase EmployeeName = new Phrase(empdetBean.getEmployeeName());
		cell = new PdfPCell(EmployeeName);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase EmployeeID = new Phrase(empdetBean.getEmployeeCode());
		cell = new PdfPCell(EmployeeID);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase Designation = new Phrase(empdetBean.getDesignation());
		cell = new PdfPCell(Designation);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase Department = new Phrase(" ");
		cell = new PdfPCell(Department);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase doj = new Phrase(empdetBean.getDojStr());
		cell = new PdfPCell(doj);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase ESINumber = new Phrase(" ");
		cell = new PdfPCell(ESINumber);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		return table;
	}
	 
	public static PdfPTable generateMiddleTableRight(){
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(70);
		
		Phrase PFNo = new Phrase("PF No ");
		cell = new PdfPCell(PFNo);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase uan = new Phrase("UAN ");
		cell = new PdfPCell(uan);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase workLocation = new Phrase("Work Location ");
		cell = new PdfPCell(workLocation);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase lopDays = new Phrase("LOP Days");
		cell = new PdfPCell(lopDays);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase workedDays = new Phrase("Worked Days ");
		cell = new PdfPCell(workedDays);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase bankacno = new Phrase("Bank A/c No ");
		cell = new PdfPCell(bankacno);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		return table;
	}
	
	public static PdfPTable generateMiddleTableRight(GeneratePayslipBean empdetBean){
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		
		Phrase PFNo = new Phrase(" ");
		cell = new PdfPCell(PFNo);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		 
		Phrase uan = new Phrase(" ");
		cell = new PdfPCell(uan);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase workLocation = new Phrase("Kolkata");
		cell = new PdfPCell(workLocation);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase lopDays = new Phrase("0.00");
		cell = new PdfPCell(lopDays);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase workedDays = new Phrase(""+empdetBean.getPresentDays());
		cell = new PdfPCell(workedDays);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase bankacno = new Phrase(empdetBean.getAccNo());
		cell = new PdfPCell(bankacno);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		return table;
	}
	
	public static PdfPTable createDottTable(){
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(20);
		
		Phrase dot1 = new Phrase(":");
		cell = new PdfPCell(dot1);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		 
		Phrase dot2 = new Phrase(":");
		cell = new PdfPCell(dot2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase dot3 = new Phrase(":");
		cell = new PdfPCell(dot3);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase dot4 = new Phrase(":");
		cell = new PdfPCell(dot4);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase dot5 = new Phrase(":");
		cell = new PdfPCell(dot5);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase dot6 = new Phrase(":");
		cell = new PdfPCell(dot6);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		return table;
	}
	
	////////////////////
	public static PdfPTable gapTable(){
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(95);
		Phrase gap = new Phrase(" ");
		cell = new PdfPCell(gap);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		return table;
	}
	
	public static PdfPTable gapTableWidthBorder(){
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(95);
		Phrase gap = new Phrase(" ");
		cell = new PdfPCell(gap);
		table.addCell(cell);
		
		return table;
	}
	//////////////////Earning and Deduction
	public static PdfPTable earningDeductionLabel(){
		
		float[] width = {4,4,4,4};
		PdfPTable table = new PdfPTable(width);
		table.setWidthPercentage(95);
		
		Phrase earnings = new Phrase("Earnings");
		cell = new PdfPCell(earnings);
		//cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase earnAmnt = new Phrase("Amount (Rs)");
		cell = new PdfPCell(earnAmnt);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		//cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase deductions = new Phrase("Deductions");
		cell = new PdfPCell(deductions);
		//cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase deductAmnt = new Phrase("Amount (Rs)");
		cell = new PdfPCell(deductAmnt);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		//cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		
		return table;
	}
	
	////////////Salary Details
	public static PdfPTable generateSalaryTable(GeneratePayslipBean bean){
		
		float[] width = {5,5,5,5};
		PdfPTable table = new PdfPTable(width);
		table.setWidthPercentage(95);
		
		table.addCell(addEarningComponents(bean));
		
		table.addCell(addEarningComponentsAmnt(bean));
		
		table.addCell(addDeductionComponents(bean));
		
		
		table.addCell(addDeductComponentsAmnt(bean));
		
		
		return table;
		
	}
	
	public static PdfPTable addEarningComponents(GeneratePayslipBean earningbean){
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		
		for(ComponentMasterBean bean : earningbean.getComponentList()){
			if(bean.geteOrdId() == 1){
				
			Phrase deductAmnt = new Phrase(bean.getComponent());
			cell = new PdfPCell(deductAmnt);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			}
		}
		return table;
	}
	
	public static PdfPTable addEarningComponentsAmnt(GeneratePayslipBean earningbean){
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		for(ComponentMasterBean bean : earningbean.getComponentList()){
			if(bean.geteOrdId() == 1){
			Phrase deductAmnt = new Phrase(""+bean.getAmount());
			cell = new PdfPCell(deductAmnt);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			}
		}
		return table;
	}
	
	public static PdfPTable addDeductionComponents(GeneratePayslipBean earningbean){
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		for(ComponentMasterBean bean : earningbean.getComponentList()){
			if(bean.geteOrdId() == 2){
			Phrase deductAmnt = new Phrase(bean.getComponent());
			cell = new PdfPCell(deductAmnt);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			}
		}
		return table;
	}
	
	public static PdfPTable addDeductComponentsAmnt(GeneratePayslipBean earningbean){
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		for(ComponentMasterBean bean : earningbean.getComponentList()){
			if(bean.geteOrdId() == 2){
			Phrase deductAmnt = new Phrase(""+bean.getAmount());
			cell = new PdfPCell(deductAmnt);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			}
		}
		return table;
	} 
	
	//total amount and total deduction
	public static PdfPTable totalAmnt(GeneratePayslipBean bean){
		
		float[] width = {4,4,4,4};
		PdfPTable table = new PdfPTable(width);
		table.setWidthPercentage(95);
		
		Phrase earnings = new Phrase(" ");
		cell = new PdfPCell(earnings);
		//cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase earnAmnt = new Phrase(""+bean.getTotalEarningAmnt());
		cell = new PdfPCell(earnAmnt);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		//cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase deductions = new Phrase(" ");
		cell = new PdfPCell(deductions);
		//cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase deductAmnt = new Phrase(""+bean.getTotalDeductionAmnt());
		cell = new PdfPCell(deductAmnt);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		//cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		return table;
		
	}
	
	///////////last table(summary)
	public static PdfPTable lastTable(GeneratePayslipBean bean){
		float[] width = {5,1,12};
		
		PdfPTable table = new PdfPTable(width);
		table.setWidthPercentage(95);
		
		table.addCell(summaryCellCompo());
		table.addCell(summuryDotCell());
		table.addCell(summaryCellCompo(bean));
		
		return table;
		
	}
	
	public static PdfPTable summaryCellCompo(){
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		
		Phrase netpay = new Phrase("Net Pay");
		cell = new PdfPCell(netpay);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase amntinwords = new Phrase("Amount in Words");
		cell = new PdfPCell(amntinwords);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase ModeofPayment = new Phrase("Mode of Payment");
		cell = new PdfPCell(ModeofPayment);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		return table;
		
	}
	
	public static PdfPTable summuryDotCell(){

		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		
		Phrase netpaydot = new Phrase(":");
		cell = new PdfPCell(netpaydot);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase amntinwordsdot = new Phrase(":");
		cell = new PdfPCell(amntinwordsdot);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase ModeofPaymentdot = new Phrase(":");
		cell = new PdfPCell(ModeofPaymentdot);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		return table;
		
	}
	
	public static PdfPTable summaryCellCompo(GeneratePayslipBean bean){
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		
		double netpayment = bean.getTotalEarningAmnt()-bean.getTotalDeductionAmnt();
		
		String amountString = NumberToWord.numToStr(netpayment);
		
		
		Phrase netpay = new Phrase(""+netpayment);
		cell = new PdfPCell(netpay);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase amntinwords = new Phrase(amountString);
		cell = new PdfPCell(amntinwords);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase ModeofPayment = new Phrase(bean.getTransferMode());
		cell = new PdfPCell(ModeofPayment);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		return table;
		
	}
	
	//last table
	
	public static PdfPTable digitalStatment(){
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(95);
		
		Phrase phrase = new Phrase("\"This payslip is computer generated, hence no signature is required\" ");
		cell = new PdfPCell(phrase);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		return table;
	}
	
	
	
}
