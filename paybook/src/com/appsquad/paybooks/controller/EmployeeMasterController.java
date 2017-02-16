package com.appsquad.paybooks.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
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

import com.appsquad.paybooks.bean.CompanyBean;
import com.appsquad.paybooks.bean.EmployeeMasterBean;
import com.appsquad.paybooks.model.service.CompanyService;
import com.appsquad.paybooks.model.service.EmployeeMasterService;
import com.appsquad.paybooks.model.service.GeneratePayslipService;
import com.appsquad.paybooks.model.service.LoadAllListService;

public class EmployeeMasterController {
	
	private EmployeeMasterBean employeeMasterBean = new EmployeeMasterBean();
	private CompanyBean companyBean = new CompanyBean();
	private ArrayList<CompanyBean> companyBeanList ;
	private ArrayList<CompanyBean> dbcompanyBeanList ;
	private EmployeeMasterBean existiingEmployeeMasterBean = new EmployeeMasterBean();
	//private EmployeeMasterBean employeeMasterBean = new EmployeeMasterBean();
	private ArrayList<EmployeeMasterBean> employeeList;
	private ArrayList<EmployeeMasterBean> dbEmployeeList;
	
	private ArrayList<EmployeeMasterBean> employeeMasterBeanList = new ArrayList<EmployeeMasterBean>();
	
	Session session = null;

	private String userId;
	
	private int roleId, companyId;
	
	
	private boolean searchBtnVisible = true;
	
	@Wire("#cmpnm")
	private Bandbox cmpBandBox;
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws Exception {

		Selectors.wireComponents(view, this, false);
		System.out.println("employeemaster.zul");
		session = Sessions.getCurrent();
		userId = (String) session.getAttribute("userId");
		companyId = (int)session.getAttribute("primaryId");
		if((int) session.getAttribute("rollId")==3){//company login
			searchBtnVisible = false;
			companyBean.setCompanyName((String) session.getAttribute("userName"));
			employeeMasterBean.setCompanyId(companyId);
		}else{
			loadCompanyList();
		}
		
	}
	
	public void loadCompanyList(){
		dbcompanyBeanList = CompanyService.loadAllCompanyList();
		companyBeanList = dbcompanyBeanList;
	}
	
	@Command
	@NotifyChange("*")
	public void onSelctCompany(){
		cmpBandBox.close();
		employeeMasterBean.setCompanyId(companyBean.getCompanyId());
	}
	
	public void onLoad(){
		//dbEmployeeList = LoadAllListService.loadEmployeeInfo();
		dbEmployeeList = EmployeeMasterService.loadEmpInfo(companyId);
		employeeList = dbEmployeeList;
	}

	public void loadExistingEmployees(){
		employeeMasterBeanList = EmployeeMasterService.loadEmpInfo(companyId);
	}
	
	
	@GlobalCommand
	@NotifyChange("*")
	public void globalEmployeeUpdate(){
		loadExistingEmployees();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickExistingEmployee(){
		loadExistingEmployees();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickSave(){
		if(EmployeeMasterService.onEmpInfoValidation(employeeMasterBean)){
			if(EmployeeMasterService.saveEmployeeInfo(userId, employeeMasterBean)>0){
				Messagebox.show("Employee information saved successfully", "Successful Information", Messagebox.OK, Messagebox.INFORMATION);
				EmployeeMasterService.onEmpInfoClear(employeeMasterBean);
			}
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickCancel(){
		EmployeeMasterService.onEmpInfoClear(employeeMasterBean);
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdate(@BindingParam("bean") EmployeeMasterBean bean){
		Map<String, EmployeeMasterBean> map = new HashMap<String, EmployeeMasterBean>();
		map.put("employee", bean);
		Window window = (Window) Executions.createComponents("/WEB-INF/view/employeeupdate.zul", null, map);
		window.doModal();
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

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}







	public EmployeeMasterBean getEmployeeMasterBean() {
		return employeeMasterBean;
	}







	public void setEmployeeMasterBean(EmployeeMasterBean employeeMasterBean) {
		this.employeeMasterBean = employeeMasterBean;
	}

	public EmployeeMasterBean getExistiingEmployeeMasterBean() {
		return existiingEmployeeMasterBean;
	}

	public void setExistiingEmployeeMasterBean(
			EmployeeMasterBean existiingEmployeeMasterBean) {
		this.existiingEmployeeMasterBean = existiingEmployeeMasterBean;
	}

	public ArrayList<EmployeeMasterBean> getEmployeeMasterBeanList() {
		return employeeMasterBeanList;
	}

	public void setEmployeeMasterBeanList(
			ArrayList<EmployeeMasterBean> employeeMasterBeanList) {
		this.employeeMasterBeanList = employeeMasterBeanList;
	}

	public ArrayList<EmployeeMasterBean> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(ArrayList<EmployeeMasterBean> employeeList) {
		this.employeeList = employeeList;
	}

	public ArrayList<EmployeeMasterBean> getDbEmployeeList() {
		return dbEmployeeList;
	}

	public void setDbEmployeeList(ArrayList<EmployeeMasterBean> dbEmployeeList) {
		this.dbEmployeeList = dbEmployeeList;
	}

	public CompanyBean getCompanyBean() {
		return companyBean;
	}

	public void setCompanyBean(CompanyBean companyBean) {
		this.companyBean = companyBean;
	}

	public ArrayList<CompanyBean> getCompanyBeanList() {
		return companyBeanList;
	}

	public void setCompanyBeanList(ArrayList<CompanyBean> companyBeanList) {
		this.companyBeanList = companyBeanList;
	}

	public ArrayList<CompanyBean> getDbcompanyBeanList() {
		return dbcompanyBeanList;
	}

	public void setDbcompanyBeanList(ArrayList<CompanyBean> dbcompanyBeanList) {
		this.dbcompanyBeanList = dbcompanyBeanList;
	}

	public boolean isSearchBtnVisible() {
		return searchBtnVisible;
	}

	public void setSearchBtnVisible(boolean searchBtnVisible) {
		this.searchBtnVisible = searchBtnVisible;
	}

	public Bandbox getCmpBandBox() {
		return cmpBandBox;
	}

	public void setCmpBandBox(Bandbox cmpBandBox) {
		this.cmpBandBox = cmpBandBox;
	}
	

}
