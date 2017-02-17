package com.appsquad.paybooks.controller;

import java.util.ArrayList;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.CompanyBean;
import com.appsquad.paybooks.bean.GeneratePayslipBean;
import com.appsquad.paybooks.bean.MonthMasterBean;
import com.appsquad.paybooks.dao.EmployeeMasterDao;
import com.appsquad.paybooks.dao.LoadAllListDao;
import com.appsquad.paybooks.model.pdf.PayslipGenerator;
import com.appsquad.paybooks.model.service.GeneratePayslipService;

public class MySlipController {
	
	private MonthMasterBean monthMasterBean = new MonthMasterBean();
	private MonthMasterBean yearMasterBean = new MonthMasterBean();
	private GeneratePayslipBean generatePayslipBean ;
	private GeneratePayslipBean payslipHeaderBean;// = new GeneratePayslipBean();
	
	
	private ArrayList<GeneratePayslipBean> generatePayslipBeanList;
	private ArrayList<GeneratePayslipBean> dbGeneratePayslipBeanList;
	private ArrayList<MonthMasterBean> monthist;
	private ArrayList<MonthMasterBean> yearist;
		
	private Session session = null;

	private String userId;
	
	@Wire("#empnm")
	private Bandbox empBandBox;
	
	private int primaryId;
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws Exception {

		Selectors.wireComponents(view, this, false);
		System.out.println("generatepayslip.zul. . .");
		session = Sessions.getCurrent();
		userId = (String) session.getAttribute("userId");
		primaryId = (int)session.getAttribute("primaryId");
		onLoad();
	}
	
	public void onLoad(){
		monthist = LoadAllListDao.loadmonths();
		yearist = LoadAllListDao.loadyears();
		generatePayslipBean = EmployeeMasterDao.getEmployeeInfo(primaryId);
		CompanyBean company = LoadAllListDao.getCompany(generatePayslipBean.getCompanyId());
		payslipHeaderBean = new GeneratePayslipBean();
		payslipHeaderBean.setCompanyName(company.getCompanyName());
		payslipHeaderBean.setCompanyAddress(company.getAddress());//"Martin Burn Ispace, Second Floor, Unit 2C1 Kolkata - 700156"
		
		generatePayslipBean.setLocation(company.getWorkLocation());
	}

	@Command
	@NotifyChange("*")
	public void onClickGenerate(){
		payslipHeaderBean.setMonth(monthMasterBean.getMonth());
		payslipHeaderBean.setMonthId(monthMasterBean.getMonthId());
		payslipHeaderBean.setYear(yearMasterBean.getYr());
		generatePayslipBean.setPresentDays(EmployeeMasterDao.getNoOfDayPresent(monthMasterBean.getMonthId(),primaryId));
		if(GeneratePayslipService.isValid(monthMasterBean.getMonth(), yearMasterBean.getYr(), generatePayslipBean)){
			if(generatePayslipBean.getPresentDays() > 0){
				getSlip();
			}else{
				Messagebox.show("Sorry no present day in this month.","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
			}
		}
	}
	
	public void getSlip(){
		String path = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
		PayslipGenerator payslipGenerator = new PayslipGenerator();
		try {
			String filePath = payslipGenerator.getSlipDetailsPerEmp(path, generatePayslipBean, payslipHeaderBean, false);	
			System.out.println("Download path: "+filePath);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public MonthMasterBean getMonthMasterBean() {
		return monthMasterBean;
	}

	public void setMonthMasterBean(MonthMasterBean monthMasterBean) {
		this.monthMasterBean = monthMasterBean;
	}

	public MonthMasterBean getYearMasterBean() {
		return yearMasterBean;
	}

	public void setYearMasterBean(MonthMasterBean yearMasterBean) {
		this.yearMasterBean = yearMasterBean;
	}

	public GeneratePayslipBean getGeneratePayslipBean() {
		return generatePayslipBean;
	}

	public void setGeneratePayslipBean(GeneratePayslipBean generatePayslipBean) {
		this.generatePayslipBean = generatePayslipBean;
	}

	public GeneratePayslipBean getPayslipHeaderBean() {
		return payslipHeaderBean;
	}

	public void setPayslipHeaderBean(GeneratePayslipBean payslipHeaderBean) {
		this.payslipHeaderBean = payslipHeaderBean;
	}

	public ArrayList<GeneratePayslipBean> getGeneratePayslipBeanList() {
		return generatePayslipBeanList;
	}

	public void setGeneratePayslipBeanList(
			ArrayList<GeneratePayslipBean> generatePayslipBeanList) {
		this.generatePayslipBeanList = generatePayslipBeanList;
	}

	public ArrayList<GeneratePayslipBean> getDbGeneratePayslipBeanList() {
		return dbGeneratePayslipBeanList;
	}

	public void setDbGeneratePayslipBeanList(
			ArrayList<GeneratePayslipBean> dbGeneratePayslipBeanList) {
		this.dbGeneratePayslipBeanList = dbGeneratePayslipBeanList;
	}

	public ArrayList<MonthMasterBean> getMonthist() {
		return monthist;
	}

	public void setMonthist(ArrayList<MonthMasterBean> monthist) {
		this.monthist = monthist;
	}

	public ArrayList<MonthMasterBean> getYearist() {
		return yearist;
	}

	public void setYearist(ArrayList<MonthMasterBean> yearist) {
		this.yearist = yearist;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Bandbox getEmpBandBox() {
		return empBandBox;
	}

	public void setEmpBandBox(Bandbox empBandBox) {
		this.empBandBox = empBandBox;
	}

	public int getPrimaryId() {
		return primaryId;
	}

	public void setPrimaryId(int primaryId) {
		this.primaryId = primaryId;
	}

}
