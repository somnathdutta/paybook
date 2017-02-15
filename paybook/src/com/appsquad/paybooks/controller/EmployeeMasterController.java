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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.appsquad.paybooks.bean.EmployeeMasterBean;
import com.appsquad.paybooks.model.service.EmployeeMasterService;
import com.appsquad.paybooks.model.service.LoadAllListService;

public class EmployeeMasterController {
	
	private EmployeeMasterBean employeeMasterBean = new EmployeeMasterBean();
	private EmployeeMasterBean existiingEmployeeMasterBean = new EmployeeMasterBean();
	//private EmployeeMasterBean employeeMasterBean = new EmployeeMasterBean();
	private ArrayList<EmployeeMasterBean> employeeList;
	private ArrayList<EmployeeMasterBean> dbEmployeeList;
	
	private ArrayList<EmployeeMasterBean> employeeMasterBeanList = new ArrayList<EmployeeMasterBean>();
	
	Session session = null;

	private String userId;
	
	private Integer roleId;
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws Exception {

		Selectors.wireComponents(view, this, false);
		System.out.println("employeemaster.zul");
		session = Sessions.getCurrent();
		userId = (String) session.getAttribute("userId");
	}
	
	public void onLoad(){
		dbEmployeeList = LoadAllListService.loadEmployeeInfo();
		employeeList = dbEmployeeList;
	}

	public void loadExistingEmployees(){
		employeeMasterBeanList = EmployeeMasterService.loadEmpInfo();
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
			if(EmployeeMasterService.employeeInfo(userId, employeeMasterBean)>0){
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
	

}
