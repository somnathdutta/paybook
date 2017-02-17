package com.appsquad.paybooks.model.pdf;


import com.appsquad.paybooks.bean.ComponentMasterBean;
import com.appsquad.paybooks.bean.GeneratePayslipBean;
import com.appsquad.paybooks.model.research.DesimalFormat;
import com.appsquad.paybooks.model.utils.Dateformatter;
import com.appsquad.paybooks.model.utils.NumberToWord;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class TestTableGenerator {
	static PdfPCell cell;
	
	public static PdfPTable createHeaderTable(GeneratePayslipBean headerBean, int tableWidth){
		
		float[] widths = {5,15};

		PdfPTable table = new PdfPTable(widths);
		table.setWidthPercentage(tableWidth);

		PdfPCell cellL = new PdfPCell();
		cellL.setBorder(Rectangle.NO_BORDER);


		PdfPCell cellR = new PdfPCell(createRHSinnerHeaderTable(headerBean));
		cellR.setBorder(Rectangle.NO_BORDER);

		table.addCell(cellL);
		table.addCell(cellR);
			
		return table;
	}
	
	public static PdfPTable createRHSinnerHeaderTable(GeneratePayslipBean headerBean){
		
		Font boldFont = new Font(Font.FontFamily.HELVETICA, 12.9f, Font.BOLD);
		
		PdfPTable table = new PdfPTable(1);
		
		Phrase companyName = new Phrase(headerBean.getCompanyName(), boldFont);
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
	public static PdfPTable generateMiddleTable(GeneratePayslipBean bean, int tableWidth, Font normalFont, int cellpadding) throws DocumentException{
		
		float[] width = {2f,2f};
		PdfPTable table = new PdfPTable(2);
		table.setWidths(width);
		table.setWidthPercentage(tableWidth);
		
		PdfPCell cellL = new PdfPCell(generateMiddleTableL(bean, normalFont));
		cellL.setPadding(cellpadding);
		cellL.setBorderWidth(2f);
		PdfPCell cellR = new PdfPCell(generateMiddleTableR(bean, normalFont));
		cellR.setPadding(cellpadding);
		cellR.setBorderWidth(2f);
		table.addCell(cellL);
		table.addCell(cellR);
		
		
		return table;
		
	}
	
	public static PdfPTable generateMiddleTableL(GeneratePayslipBean bean, Font normalFont) throws DocumentException{
		
		float[] colWidths = {1.5f,0.3f,2f};
		
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		table.setWidths(colWidths);
		
		PdfPCell cell1 = new PdfPCell(generateMiddleTableLeft(normalFont));
		cell1.setBorder(Rectangle.NO_BORDER);
		
		PdfPCell cell2 = new PdfPCell(createDottTable());
		cell2.setBorder(Rectangle.NO_BORDER);
		
		PdfPCell cell3 = new PdfPCell(generateMiddleTableLeft(bean, normalFont));
		cell3.setBorder(Rectangle.NO_BORDER);
		
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		
		return table;
	}
	
	
	
	public static PdfPTable generateMiddleTableLeft(Font normalFont){
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(70);
		
		
		Phrase EmployeeName = new Phrase("Employee Name ", normalFont);
		cell = new PdfPCell(EmployeeName);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase EmployeeID = new Phrase("Employee ID ",normalFont);
		cell = new PdfPCell(EmployeeID);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase Designation = new Phrase("Designation ", normalFont);
		cell = new PdfPCell(Designation);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase Department = new Phrase("Department ", normalFont);
		cell = new PdfPCell(Department);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase doj = new Phrase("Date of Joining ", normalFont);
		cell = new PdfPCell(doj);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase ESINumber = new Phrase("ESI Number ",normalFont);
		cell = new PdfPCell(ESINumber);
		cell.setPaddingBottom(4);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		return table;
	}
	
	public static PdfPTable generateMiddleTableLeft(GeneratePayslipBean empdetBean, Font normalFont){
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		
		Phrase EmployeeName = new Phrase(empdetBean.getEmployeeName(), normalFont);
		cell = new PdfPCell(EmployeeName);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase EmployeeID = new Phrase(empdetBean.getEmployeeCode(), normalFont);
		cell = new PdfPCell(EmployeeID);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase Designation = new Phrase(empdetBean.getDesignation(), normalFont);
		cell = new PdfPCell(Designation);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase Department = new Phrase(" ");
		cell = new PdfPCell(Department);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase doj = new Phrase(Dateformatter.toStringDate(empdetBean.getDojSql().toString()), normalFont);
		cell = new PdfPCell(doj);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase ESINumber = new Phrase(empdetBean.getEsi());
		cell = new PdfPCell(ESINumber);
		cell.setPaddingBottom(4);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		return table;
	}
	 
	public static PdfPTable generateMiddleTableR(GeneratePayslipBean bean, Font normalFont) throws DocumentException{
	
		float[] colWidths = {1f,0.3f,2f};
		
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		table.setWidths(colWidths);
		
		PdfPCell cell1 = new PdfPCell(generateMiddleTableRight(normalFont));
		cell1.setBorder(Rectangle.NO_BORDER);
		
		PdfPCell cell2 = new PdfPCell(createDottTable());
		cell2.setBorder(Rectangle.NO_BORDER);
		
		PdfPCell cell3 = new PdfPCell(generateMiddleTableRight(bean,normalFont));
		cell3.setBorder(Rectangle.NO_BORDER);
		
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		
		return table;
	}	
	
	
	
	
	
	public static PdfPTable generateMiddleTableRight(Font normalFont){
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(70);
		
		Phrase PFNo = new Phrase("PF No ", normalFont);
		cell = new PdfPCell(PFNo);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase uan = new Phrase("UAN ", normalFont);
		cell = new PdfPCell(uan);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase workLocation = new Phrase("Work Location ", normalFont);
		cell = new PdfPCell(workLocation);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase lopDays = new Phrase("LOP Days", normalFont);
		cell = new PdfPCell(lopDays);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase workedDays = new Phrase("Worked Days ", normalFont);
		cell = new PdfPCell(workedDays);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase bankacno = new Phrase("Bank A/c No ", normalFont);
		cell = new PdfPCell(bankacno);
		cell.setPaddingBottom(4);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		return table;
	}
	
	public static PdfPTable generateMiddleTableRight(GeneratePayslipBean empdetBean, Font normalFont){
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		
		Phrase PFNo = new Phrase(empdetBean.getPf());
		cell = new PdfPCell(PFNo);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		 
		Phrase uan = new Phrase(empdetBean.getUan());
		cell = new PdfPCell(uan);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase workLocation = new Phrase(empdetBean.getLocation(), normalFont);
		cell = new PdfPCell(workLocation);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase lopDays = new Phrase("0.00", normalFont);
		cell = new PdfPCell(lopDays);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase workedDays = new Phrase(""+empdetBean.getPresentDays(), normalFont);
		cell = new PdfPCell(workedDays);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase bankacno = new Phrase(empdetBean.getAccNo(), normalFont);
		cell = new PdfPCell(bankacno);
		cell.setPaddingBottom(4);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		return table;
	}
	
	public static PdfPTable createDottTable(){
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(17);
		
		Phrase dot1 = new Phrase(":");
		cell = new PdfPCell(dot1);
		cell.setPaddingBottom(1);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		 
		Phrase dot2 = new Phrase(":");
		cell = new PdfPCell(dot2);
		cell.setPaddingBottom(1);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase dot3 = new Phrase(":");
		cell = new PdfPCell(dot3);
		cell.setPaddingBottom(1);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase dot4 = new Phrase(":");
		cell = new PdfPCell(dot4);
		cell.setPaddingBottom(1);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase dot5 = new Phrase(":");
		cell = new PdfPCell(dot5);
		cell.setPaddingBottom(1);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		Phrase dot6 = new Phrase(":");
		cell = new PdfPCell(dot6);
		cell.setPaddingBottom(2.5f);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		return table;
	}
	
	////////////////////
	public static PdfPTable gapTable(int tableWidth){
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(tableWidth);
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
	public static PdfPTable earningDeductionLabel(Font boldFont, int tableWidth, int cellpadding) throws DocumentException{
		
		float[] colWidth = {1f,1f};
		PdfPTable table = new PdfPTable(2);
		table.setWidths(colWidth);
		table.setWidthPercentage(tableWidth);
		
		PdfPCell cell1 = new PdfPCell(earningsHeaderLabel(boldFont, cellpadding));
		cell1.setPadding(cellpadding);
		cell1.setBorderWidth(2f);
		
		PdfPCell cell2 = new PdfPCell(deductionHeaderLabel(boldFont, cellpadding));
		cell2.setPadding(cellpadding);
		cell2.setBorderWidth(2f);
		table.addCell(cell1);
		table.addCell(cell2);
		
		return table;
	}
	
	
	
	public static PdfPTable earningsHeaderLabel(Font boldFont, int cellpadding) throws DocumentException{
		
		float[] width = {1f,1f};
		PdfPTable table = new PdfPTable(2);
		table.setWidths(width);
		table.setWidthPercentage(100);
		
		Phrase earnings = new Phrase("Earnings", boldFont);
		PdfPCell cell1 = new PdfPCell(earnings);
		cell1.setPadding(cellpadding);
		cell1.setBorder(Rectangle.NO_BORDER);
		
		Phrase earnAmnt = new Phrase("Amount (Rs)", boldFont);
		PdfPCell cell2 = new PdfPCell(earnAmnt);
		cell2.setPadding(cellpadding);
		cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell2.setBorder(Rectangle.NO_BORDER);
		
		table.addCell(cell1);
		table.addCell(cell2);
		
		return table;
	}
	
	public static PdfPTable deductionHeaderLabel(Font boldFont, int cellpadding) throws DocumentException{
		
		float[] width = {1f,1f};
		PdfPTable table = new PdfPTable(2);
		table.setWidths(width);
		table.setWidthPercentage(100);
		
		Phrase deductions = new Phrase("Deductions", boldFont);
		PdfPCell cell1 = new PdfPCell(deductions);
		cell1.setPadding(cellpadding);
		cell1.setBorder(Rectangle.NO_BORDER);
		
		Phrase deductAmnt = new Phrase("Amount (Rs)", boldFont);
		PdfPCell cell2 = new PdfPCell(deductAmnt);
		cell2.setPadding(cellpadding);
		cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell2.setBorder(Rectangle.NO_BORDER);
		
		table.addCell(cell1);
		table.addCell(cell2);
		
		
		return table;
	}
	
	////////////Salary Details
	public static PdfPTable generateSalaryTable(GeneratePayslipBean bean, int tableWidth, Font normalFont, int cellpadding) throws DocumentException{
		
		float[] width = {1f,1f};
		PdfPTable table = new PdfPTable(2);
		table.setWidths(width);
		table.setWidthPercentage(tableWidth);
		
		PdfPCell cell1 = new PdfPCell(generateSalaryTableEarn(bean,normalFont));
		cell1.setPadding(cellpadding);
		cell1.setBorderWidth(2f);
		PdfPCell cell2 = new PdfPCell(generateSalaryTableDeduct(bean, normalFont));
		cell2.setPadding(cellpadding);
		cell2.setBorderWidth(2f);
		table.addCell(cell1);
		table.addCell(cell2);
		
		return table;
		
	}
	
	public static PdfPTable generateSalaryTableEarn(GeneratePayslipBean bean, Font normalFont) throws DocumentException{
		
		float[] colWidths = {2f,1f};
		
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		table.setWidths(colWidths);
		
		PdfPCell cell1 = new PdfPCell(addEarningComponents(bean, normalFont));
		cell1.setBorder(Rectangle.NO_BORDER);
		
		PdfPCell cell2 = new PdfPCell(addEarningComponentsAmnt(bean, normalFont));
		cell2.setBorder(Rectangle.NO_BORDER);
		
		table.addCell(cell1);
		table.addCell(cell2);
		
		return table;
	}
	



	public static PdfPTable addEarningComponents(GeneratePayslipBean earningbean, Font normalFont){
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		
		for(ComponentMasterBean bean : earningbean.getComponentList()){
			System.out.println("Earn:: "+bean.getComponent());
			if(bean.geteOrdId() == 1){
				
			Phrase deductAmnt = new Phrase(bean.getComponent(), normalFont);
			cell = new PdfPCell(deductAmnt);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			}
		}
		return table;
	}
	
	public static PdfPTable addEarningComponentsAmnt(GeneratePayslipBean earningbean, Font normalFont){
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		for(ComponentMasterBean bean : earningbean.getComponentList()){
			System.out.println("EarnCom:: "+bean.getComponent());
			if(bean.geteOrdId() == 1){
			Phrase deductAmnt = new Phrase(""+ DesimalFormat.twoDecimalFormat(bean.getAmount()), normalFont);
			cell = new PdfPCell(deductAmnt);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			}
		}
		return table;
	}
	
	public static PdfPTable generateSalaryTableDeduct(GeneratePayslipBean bean, Font normlaFont) throws DocumentException{
		
		float[] colWidths = {2f,1f};
		
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		table.setWidths(colWidths);
		
		PdfPCell cell1 = new PdfPCell(addDeductionComponents(bean, normlaFont));
		cell1.setBorder(Rectangle.NO_BORDER);
		
		PdfPCell cell2 = new PdfPCell(addDeductComponentsAmnt(bean, normlaFont));
		cell2.setBorder(Rectangle.NO_BORDER);
		
		table.addCell(cell1);
		table.addCell(cell2);
		
		return table;
	}
	
	
	
	public static PdfPTable addDeductionComponents(GeneratePayslipBean earningbean, Font normalFont){
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		for(ComponentMasterBean bean : earningbean.getComponentList()){
			if(bean.geteOrdId() == 2){
			Phrase deductAmnt = new Phrase(bean.getComponent(), normalFont);
			cell = new PdfPCell(deductAmnt);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			}
		}
		return table;
	}
	
	public static PdfPTable addDeductComponentsAmnt(GeneratePayslipBean earningbean, Font normalFont){
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		for(ComponentMasterBean bean : earningbean.getComponentList()){
			if(bean.geteOrdId() == 2){
			Phrase deductAmnt = new Phrase(""+ DesimalFormat.twoDecimalFormat(bean.getAmount()), normalFont);
			cell = new PdfPCell(deductAmnt);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			}
		}
		return table;
	} 
	
	//total amount and total deduction
	public static PdfPTable totalAmnt(GeneratePayslipBean bean, Font boldFont, int tableWidth, int cellpadding) throws DocumentException{
		
		float[] colWidths = {1f,1f};
		
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(tableWidth);
		table.setWidths(colWidths);
		
		Phrase earnAmnt = new Phrase(""+DesimalFormat.twoDecimalFormat(bean.getTotalEarningAmnt()), boldFont);
		cell = new PdfPCell(earnAmnt);
		cell.setBorderWidth(2f);
		cell.setPadding(cellpadding);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);
		
		Phrase deductAmnt = new Phrase(""+DesimalFormat.twoDecimalFormat(bean.getTotalDeductionAmnt()), boldFont);
		cell = new PdfPCell(deductAmnt);
		cell.setBorderWidth(2f);
		cell.setPadding(cellpadding);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);
		
		return table;
		
	}
	
	//last table(summary)
	public static PdfPTable lastTable(GeneratePayslipBean bean, Font boldFont, int tableWidth, int cellpadding) throws DocumentException{
		float[] colWidths = {1f};
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(tableWidth);
		table.setWidths(colWidths);
		
		PdfPCell cell1 = new PdfPCell(lastTableDetails(bean, boldFont));
		cell1.setPaddingLeft(3);
		cell1.setBorderWidth(2f);
		cell1.setPaddingBottom(3);
		table.addCell(cell1);
		
		return table;
		
	}
	
	public static PdfPTable lastTableDetails(GeneratePayslipBean bean, Font boldFont) throws DocumentException{

		float[] colWidths = {1f,0.1f,3f};
		
		PdfPTable table = new PdfPTable(3);
		
		table.setWidths(colWidths);
		
		PdfPCell cell1 = new PdfPCell(summaryCellCompo(boldFont));
		cell1.setBorder(Rectangle.NO_BORDER);
		
		PdfPCell cell2 = new PdfPCell(summuryDotCell());
		cell2.setBorder(Rectangle.NO_BORDER);
		cell2.setPadding(1);
		
		PdfPCell cell3 = new PdfPCell(summaryCellCompo(bean));
		cell3.setBorder(Rectangle.NO_BORDER);
		
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		
		return table;
		
	
	}
	
	
	public static PdfPTable summaryCellCompo(Font boldFont){
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		
		Phrase netpay = new Phrase("Net Pay", boldFont);
		PdfPCell cell1 = new PdfPCell(netpay);
		cell1.setBorder(Rectangle.NO_BORDER);
		
		
		Phrase amntinwords = new Phrase("Amount in Words", boldFont);
		PdfPCell cell2 = new PdfPCell(amntinwords);
		cell2.setBorder(Rectangle.NO_BORDER);
		
		
		Phrase ModeofPayment = new Phrase("Mode of Payment", boldFont);
		PdfPCell cell3 = new PdfPCell(ModeofPayment);
		cell3.setBorder(Rectangle.NO_BORDER);
		
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		
		return table;
		
	}
	
	public static PdfPTable summuryDotCell(){

		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		
		Phrase netpaydot = new Phrase(":");
		PdfPCell cell1 = new PdfPCell(netpaydot);
		cell1.setBorder(Rectangle.NO_BORDER);
		
		Phrase amntinwordsdot = new Phrase(":");
		PdfPCell cell2 = new PdfPCell(amntinwordsdot);
		cell2.setBorder(Rectangle.NO_BORDER);
		
		Phrase ModeofPaymentdot = new Phrase(":");
		PdfPCell cell3 = new PdfPCell(ModeofPaymentdot);
		cell3.setBorder(Rectangle.NO_BORDER);
		
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		
		return table;
		
	}
	
	public static PdfPTable summaryCellCompo(GeneratePayslipBean bean){
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		
		double netpayment = bean.getTotalEarningAmnt()-bean.getTotalDeductionAmnt();
		
		String amountString = NumberToWord.numToStr(netpayment);
		
		
		Phrase netpay = new Phrase(""+DesimalFormat.twoDecimalFormat(netpayment));
		PdfPCell cell1 = new PdfPCell(netpay);
		cell1.setBorder(Rectangle.NO_BORDER);
		
		Phrase amntinwords = new Phrase(amountString+" Only");
		PdfPCell cell2 = new PdfPCell(amntinwords);
		cell2.setBorder(Rectangle.NO_BORDER);
		
		Phrase ModeofPayment = new Phrase(bean.getTransferMode());
		PdfPCell cell3 = new PdfPCell(ModeofPayment);
		cell3.setBorder(Rectangle.NO_BORDER);
		
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		
		return table;
		
	}
	
	//last table
	
	public static PdfPTable digitalStatment(int tableWidth){
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(tableWidth);
		
		Phrase phrase = new Phrase("\"This payslip is computer generated, hence no signature is required\" ");
		cell = new PdfPCell(phrase);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		return table;
	}
	
	
	
}
