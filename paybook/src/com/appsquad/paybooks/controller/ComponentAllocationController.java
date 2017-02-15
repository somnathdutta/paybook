package com.appsquad.paybooks.controller;

import java.util.ArrayList;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.ComponentAllocationBean;
import com.appsquad.paybooks.bean.ComponentMasterBean;
import com.appsquad.paybooks.bean.EmployeeMasterBean;
import com.appsquad.paybooks.dao.ComponentAllocationDao;
import com.appsquad.paybooks.model.service.ComponentAllocationService;
import com.appsquad.paybooks.model.service.EmployeeMasterService;
import com.appsquad.paybooks.model.service.LoadAllListService;

public class ComponentAllocationController {
	
	private EmployeeMasterBean employeeMasterBean = new EmployeeMasterBean();
	private ComponentMasterBean componentMasterBean = new ComponentMasterBean();
	private ComponentAllocationBean componentAllocationBean = new ComponentAllocationBean();
	private LoadAllListService allListService;
	private EmployeeMasterBean existingemployeeMasterBean = new EmployeeMasterBean();
	private ComponentAllocationBean exAllocatedcomponentAllocationBean = new ComponentAllocationBean();
	
	
	private ArrayList<ComponentMasterBean> exAllocatedcomponentList;
	private ArrayList<EmployeeMasterBean> employeeList;
	private ArrayList<ComponentMasterBean> componentList;
	
	Session session = null;
	private String userId;
	private Integer roleId;
	
	@Wire("#empnm")
	 private Bandbox empBandBox;
	
	
	@Wire("#exempnm")
	private Bandbox exEmpBandBox;
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws Exception {

		Selectors.wireComponents(view, this, false);
		System.out.println("componentallocation.zul");
		session = Sessions.getCurrent();
		userId = (String) session.getAttribute("userId");
		//allListService = new LoadAllListService();
		employeeList = LoadAllListService.loadEmployeeInfo();
		
	}

	
	@Command
	@NotifyChange("*")
	public void onSelctEmployeeName(){
		empBandBox.close();
		if(employeeMasterBean.getEmployeeId() != null){
			componentList = ComponentAllocationDao.loadComponent(employeeMasterBean.getEmployeeId());
			if(componentList.size() == 0){
				Messagebox.show("Not Found any Component", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}
		
		
	}
	
	@Command
	@NotifyChange("*")
	public void onChangeEmployeeName(){
		
		allListService = new LoadAllListService();
		employeeList = allListService.loadActvEmployeeInfoSearch(employeeMasterBean.getEmployeeSearch());
	}
	
	@Command
	@NotifyChange("*")
	public void onClearEmployeeName(){
		
		employeeMasterBean.setEmployeeSearch(null);
		employeeList = allListService.loadEmployeeInfo();
		
	}
	
	@Command
	@NotifyChange("*")
	public void onClickAllocationSave(){
		boolean flag = false;
		ArrayList<ComponentMasterBean> list = new ArrayList<ComponentMasterBean>();
		
		if(componentList !=null){
		for(ComponentMasterBean bean : componentList){
			if(bean.isChecked() && bean.getAmount() != null){
				list.add(bean);
			}
		}
		if(list.size()>0){
			flag = ComponentAllocationService.insertcomponentwithemp(userId,list, employeeMasterBean.getEmployeeId());
			if(flag){
				Messagebox.show("Saved Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
				componentList = ComponentAllocationDao.loadComponent(employeeMasterBean.getEmployeeId());
			}
		}else {
			Messagebox.show("Select Components and enter Amount", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		}else {
			Messagebox.show("No List Found", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		
	}
	
	
	@Command
	@NotifyChange("*")
	public void onClickClear(){
		if(componentList != null)
		ComponentAllocationService.onClear(employeeMasterBean, componentList);
		allListService = new LoadAllListService();
		employeeList = allListService.loadEmployeeInfo();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickClearEx(){
		if(employeeList != null){
			existingemployeeMasterBean.setEmployeeName(null);
			existingemployeeMasterBean.setEmployeeId(null);
			employeeList = allListService.loadEmployeeInfo();
			if(exAllocatedcomponentList != null){
			exAllocatedcomponentList.clear();
			}
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onChangeExEmployeeName(){

		allListService = new LoadAllListService();
		employeeList = allListService.loadActvEmployeeInfoSearch(existingemployeeMasterBean.getEmployeeSearch());

	}
	
	@Command
	@NotifyChange("*")
	public void onClearExEmployeeName(){
		existingemployeeMasterBean.setEmployeeSearch(null);
		employeeList = allListService.loadEmployeeInfo();
	}
	
	@Command
	@NotifyChange("*")
	public void onSelctExEmployeeName(){

		exEmpBandBox.close();
		if(existingemployeeMasterBean.getEmployeeId() != null){
			exAllocatedcomponentList = ComponentAllocationService.loadAllocatedcomponentPerEmp(existingemployeeMasterBean.getEmployeeId());
			if(exAllocatedcomponentList.size() == 0){
				Messagebox.show("Not Found any Component", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickExAllocationUpdate(){

		boolean flag = false;
		ArrayList<ComponentMasterBean> list = new ArrayList<ComponentMasterBean>();
		
		if(exAllocatedcomponentList !=null){
		for(ComponentMasterBean bean : exAllocatedcomponentList){
			if(bean.isChecked()){
				list.add(bean);
			}
		}
		if(list.size()>0){
			flag = ComponentAllocationService.updatecomponentwithemp(userId, list);
			if(flag){
				Messagebox.show("Updated Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
				exAllocatedcomponentList = ComponentAllocationService.loadAllocatedcomponentPerEmp(existingemployeeMasterBean.getEmployeeId());
			}
		}else {
			Messagebox.show("Not Selected any row", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		}else {
			Messagebox.show("No List Found", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		
	
	}
	
	public EmployeeMasterBean getEmployeeMasterBean() {
		return employeeMasterBean;
	}

	public void setEmployeeMasterBean(EmployeeMasterBean employeeMasterBean) {
		this.employeeMasterBean = employeeMasterBean;
	}

	public ComponentMasterBean getComponentMasterBean() {
		return componentMasterBean;
	}

	public void setComponentMasterBean(ComponentMasterBean componentMasterBean) {
		this.componentMasterBean = componentMasterBean;
	}

	public ComponentAllocationBean getComponentAllocationBean() {
		return componentAllocationBean;
	}

	public void setComponentAllocationBean(
			ComponentAllocationBean componentAllocationBean) {
		this.componentAllocationBean = componentAllocationBean;
	}

	public ArrayList<EmployeeMasterBean> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(ArrayList<EmployeeMasterBean> employeeList) {
		this.employeeList = employeeList;
	}

	public ArrayList<ComponentMasterBean> getComponentList() {
		return componentList;
	}

	public void setComponentList(ArrayList<ComponentMasterBean> componentList) {
		this.componentList = componentList;
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







	public LoadAllListService getAllListService() {
		return allListService;
	}







	public void setAllListService(LoadAllListService allListService) {
		this.allListService = allListService;
	}


	public EmployeeMasterBean getExistingemployeeMasterBean() {
		return existingemployeeMasterBean;
	}


	public void setExistingemployeeMasterBean(
			EmployeeMasterBean existingemployeeMasterBean) {
		this.existingemployeeMasterBean = existingemployeeMasterBean;
	}


	public Bandbox getEmpBandBox() {
		return empBandBox;
	}


	public void setEmpBandBox(Bandbox empBandBox) {
		this.empBandBox = empBandBox;
	}


	public ComponentAllocationBean getExAllocatedcomponentAllocationBean() {
		return exAllocatedcomponentAllocationBean;
	}


	public void setExAllocatedcomponentAllocationBean(
			ComponentAllocationBean exAllocatedcomponentAllocationBean) {
		this.exAllocatedcomponentAllocationBean = exAllocatedcomponentAllocationBean;
	}


	public ArrayList<ComponentMasterBean> getExAllocatedcomponentList() {
		return exAllocatedcomponentList;
	}


	public void setExAllocatedcomponentList(
			ArrayList<ComponentMasterBean> exAllocatedcomponentList) {
		this.exAllocatedcomponentList = exAllocatedcomponentList;
	}


	public Bandbox getExEmpBandBox() {
		return exEmpBandBox;
	}


	public void setExEmpBandBox(Bandbox exEmpBandBox) {
		this.exEmpBandBox = exEmpBandBox;
	}
	
	

}
