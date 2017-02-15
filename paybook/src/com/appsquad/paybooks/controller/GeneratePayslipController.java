package com.appsquad.paybooks.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
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
import org.zkoss.zul.Window;

import com.appsquad.paybooks.bean.ComponentMasterBean;
import com.appsquad.paybooks.bean.EmployeeMasterBean;
import com.appsquad.paybooks.bean.GeneratePayslipBean;
import com.appsquad.paybooks.bean.MonthMasterBean;
import com.appsquad.paybooks.dao.LoadAllListDao;
import com.appsquad.paybooks.model.pdf.PayslipGenerator;
import com.appsquad.paybooks.model.service.GeneratePayslipService;
import com.appsquad.paybooks.model.service.LoadAllListService;
public class GeneratePayslipController {

	private EmployeeMasterBean employeeMasterBean = new EmployeeMasterBean();
	private ArrayList<EmployeeMasterBean> employeeList;
	private ArrayList<EmployeeMasterBean> dbEmployeeList;
	private MonthMasterBean monthMasterBean = new MonthMasterBean();
	private MonthMasterBean yearMasterBean = new MonthMasterBean();
	private LoadAllListDao allListDao;
	private GeneratePayslipBean generatePayslipBean = new GeneratePayslipBean();
	private GeneratePayslipBean payslipHeaderBean;// = new GeneratePayslipBean();
	
	
	private ArrayList<GeneratePayslipBean> generatePayslipBeanList;
	private ArrayList<GeneratePayslipBean> dbGeneratePayslipBeanList;
	private ArrayList<GeneratePayslipBean> searchedGeneratePayslipBeanList;
	private ArrayList<MonthMasterBean> monthist;
	private ArrayList<MonthMasterBean> yearist;
	
	
	private Session session = null;

	private String userId;
	
	@Wire("#empnm")
	private Bandbox empBandBox;
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws Exception {

		Selectors.wireComponents(view, this, false);
		System.out.println("generatepayslip.zul. . .");
		session = Sessions.getCurrent();
		userId = (String) session.getAttribute("userId");
		onLoad();
	}

	public void onLoad(){
		dbEmployeeList = LoadAllListService.loadEmployeeInfo();
		employeeList = dbEmployeeList;
		monthist = LoadAllListDao.loadmonths();
		yearist = LoadAllListDao.loadyears();
		dbGeneratePayslipBeanList = GeneratePayslipService.loadempSalDetails();
		generatePayslipBeanList =  dbGeneratePayslipBeanList;
	}
	
	@Command
	@NotifyChange("*")
	public void onSelctEmployeeName(){
		empBandBox.close();
		searchedGeneratePayslipBeanList = GeneratePayslipService.loadEmpSearched(employeeMasterBean.getEmployeeName()
				, dbGeneratePayslipBeanList);
		if(searchedGeneratePayslipBeanList.size()>0){
			generatePayslipBeanList = null;
			generatePayslipBeanList = searchedGeneratePayslipBeanList;
		}
		
	}
	
	@Command
	@NotifyChange("*")
	public void onChangeEmployeeName(){
		employeeList = null;
		employeeList = GeneratePayslipService.searchEmployee(employeeMasterBean.getEmployeeSearch(), dbEmployeeList);
	}
	
	@Command
	@NotifyChange("*")
	public void onClickClear(){
		if(generatePayslipBeanList.size()>0){
			generatePayslipBeanList = null;
			generatePayslipBeanList = dbGeneratePayslipBeanList;
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClearEmployeeName(){
		employeeMasterBean.setEmployeeSearch(null);
		employeeList = null;
		employeeList = dbEmployeeList;
	}
	
	@Command
	@NotifyChange("*")
	public void onClickDetails(@BindingParam("bean") GeneratePayslipBean bean){
		System.out.println("EM -- >>> >> > " + bean.getEmployeeName());
		Map<String, GeneratePayslipBean> map = new HashMap<String, GeneratePayslipBean>();
		bean.setMonth(monthMasterBean.getMonth());
		bean.setYear(yearMasterBean.getYr());
		map.put("parent", bean);
		Window window = (Window) Executions.createComponents("/WEB-INF/view/payslipview.zul", null, map);
		window.doModal();
	}
	
	
	
	@Command
	@NotifyChange("*")
	public void onCLickGeneratePayslip(){
		boolean checked = false;
		ArrayList<GeneratePayslipBean> checkedList = new ArrayList<GeneratePayslipBean>();
		for(GeneratePayslipBean bean : generatePayslipBeanList){
			if(bean.isCheck() && bean.getPresentDays() != null){
				checkedList.add(bean);
			}
		}
		
		if(checkedList.size()>0){
			payslipHeaderBean.setCompanyName("Appsquad Pvt. Ltd");
			payslipHeaderBean.setCompanyAddress("Martin Burn Ispace, Second Floor, Unit 2C1 Kolkata  \n700156");
			payslipHeaderBean.setMonth(monthMasterBean.getMonth());
			payslipHeaderBean.setMonthId(monthMasterBean.getMonthId());
			payslipHeaderBean.setYear(yearMasterBean.getYr());
			
			String path = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
			PayslipGenerator payslipGenerator = new PayslipGenerator();
			try {
				payslipGenerator.getSlipDetails(path, checkedList, payslipHeaderBean);
			
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}else {
			Messagebox.show("Please check at least one","Alert Information",Messagebox.OK,Messagebox.EXCLAMATION);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void downloadAndSend(@BindingParam("bean") GeneratePayslipBean bean ){
		if(GeneratePayslipService.isValid(monthMasterBean.getMonth(), yearMasterBean.getYr(), bean)){
			generatePaySlip(bean,false);	
		}
		//payslipHeaderBean = new GeneratePayslipBean();
		
		/*if(bean.isCheck() && bean.getPresentDays() != null){
				
				payslipHeaderBean.setCompanyName("Appsquad Pvt. Ltd");
				payslipHeaderBean.setCompanyAddress("Martin Burn Ispace, Second Floor, Unit 2C1 Kolkata - \n700156");
				payslipHeaderBean.setMonth(monthMasterBean.getMonth());
				payslipHeaderBean.setMonthId(monthMasterBean.getMonthId());
				payslipHeaderBean.setYear(yearMasterBean.getYr());
				
				String path = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
				PayslipGenerator payslipGenerator = new PayslipGenerator();
				try {
					payslipGenerator.getSlipDetailsPerEmp(path, bean, payslipHeaderBean);
				
				} catch (Exception e) {
					
					e.printStackTrace();
				}	
			
		}else {
			Messagebox.show("Please check this row and enter Present day","Alert Information",Messagebox.OK,Messagebox.EXCLAMATION);
		}*/
	}
	@Command
	@NotifyChange("*")
	public void onClickMail(@BindingParam("bean") GeneratePayslipBean bean){
		if(GeneratePayslipService.isValid(monthMasterBean.getMonth(), yearMasterBean.getYr(), bean)){
			generatePaySlip(bean,true);	
		}
	}
	
	
	public void generatePaySlip(GeneratePayslipBean bean,boolean isMailRequired){
		payslipHeaderBean = new GeneratePayslipBean();
		payslipHeaderBean.setCompanyName("Appsquad Pvt. Ltd");
		payslipHeaderBean.setCompanyAddress("Martin Burn Ispace, Second Floor, Unit 2C1 Kolkata - 700156");
		payslipHeaderBean.setMonth(monthMasterBean.getMonth());
		payslipHeaderBean.setMonthId(monthMasterBean.getMonthId());
		payslipHeaderBean.setYear(yearMasterBean.getYr());
		
		String path = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
		PayslipGenerator payslipGenerator = new PayslipGenerator();
		try {
			String filePath = payslipGenerator.getSlipDetailsPerEmp(path, bean, payslipHeaderBean, isMailRequired);	
			System.out.println("Download path: "+filePath);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}	
	}
	
	
	
	/*Calendar now = Calendar.getInstance();
	int year = now.get(Calendar.YEAR);
	String yearInString = String.valueOf(year);
	
	 Calendar cal = Calendar.getInstance();
	 System.out.println(new SimpleDateFormat("MMM").format(cal.getTime()));
	
	*/
	
	
	public MonthMasterBean getMonthMasterBean() {
		return monthMasterBean;
	}


	public void setMonthMasterBean(MonthMasterBean monthMasterBean) {
		this.monthMasterBean = monthMasterBean;
	}


	public LoadAllListDao getAllListDao() {
		return allListDao;
	}


	public void setAllListDao(LoadAllListDao allListDao) {
		this.allListDao = allListDao;
	}


	public ArrayList<MonthMasterBean> getMonthist() {
		return monthist;
	}


	public void setMonthist(ArrayList<MonthMasterBean> monthist) {
		this.monthist = monthist;
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


	public GeneratePayslipBean getGeneratePayslipBean() {
		return generatePayslipBean;
	}


	public void setGeneratePayslipBean(GeneratePayslipBean generatePayslipBean) {
		this.generatePayslipBean = generatePayslipBean;
	}


	public ArrayList<GeneratePayslipBean> getGeneratePayslipBeanList() {
		return generatePayslipBeanList;
	}


	public void setGeneratePayslipBeanList(
			ArrayList<GeneratePayslipBean> generatePayslipBeanList) {
		this.generatePayslipBeanList = generatePayslipBeanList;
	}


	public GeneratePayslipBean getPayslipHeaderBean() {
		return payslipHeaderBean;
	}


	public void setPayslipHeaderBean(GeneratePayslipBean payslipHeaderBean) {
		this.payslipHeaderBean = payslipHeaderBean;
	}


	public MonthMasterBean getYearMasterBean() {
		return yearMasterBean;
	}


	public void setYearMasterBean(MonthMasterBean yearMasterBean) {
		this.yearMasterBean = yearMasterBean;
	}


	public ArrayList<MonthMasterBean> getYearist() {
		return yearist;
	}


	public void setYearist(ArrayList<MonthMasterBean> yearist) {
		this.yearist = yearist;
	}


	public EmployeeMasterBean getEmployeeMasterBean() {
		return employeeMasterBean;
	}


	public void setEmployeeMasterBean(EmployeeMasterBean employeeMasterBean) {
		this.employeeMasterBean = employeeMasterBean;
	}


	public ArrayList<EmployeeMasterBean> getEmployeeList() {
		return employeeList;
	}


	public void setEmployeeList(ArrayList<EmployeeMasterBean> employeeList) {
		this.employeeList = employeeList;
	}

	public Bandbox getEmpBandBox() {
		return empBandBox;
	}

	public void setEmpBandBox(Bandbox empBandBox) {
		this.empBandBox = empBandBox;
	}
	
}
