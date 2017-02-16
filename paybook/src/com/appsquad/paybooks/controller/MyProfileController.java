package com.appsquad.paybooks.controller;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;

import com.appsquad.paybooks.bean.EmployeeMasterBean;
import com.appsquad.paybooks.model.service.MyProfileService;

public class MyProfileController {
	private EmployeeMasterBean employeeMasterBean ;
	
	Session session = null;

	private String userId;
	
	private int roleId,primaryId;
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws Exception {

		Selectors.wireComponents(view, this, false);
		System.out.println("myprofile.zul");
		session = Sessions.getCurrent();
		userId = (String) session.getAttribute("userId");
		primaryId = (int) session.getAttribute("primaryId");
		loadMyProfile();
	}

	public void loadMyProfile(){
		employeeMasterBean = MyProfileService.getEmployee(primaryId);
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdate(){
		MyProfileService.updateMyProfile(employeeMasterBean, userId);
	}
	
	public EmployeeMasterBean getEmployeeMasterBean() {
		return employeeMasterBean;
	}

	public void setEmployeeMasterBean(EmployeeMasterBean employeeMasterBean) {
		this.employeeMasterBean = employeeMasterBean;
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
}
