package com.appsquad.paybooks.controller;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

import com.appsquad.paybooks.bean.EmployeeMasterBean;
import com.appsquad.paybooks.model.service.EmployeeMasterService;

public class EmployeeUpdateController {
	private EmployeeMasterBean employeeMasterBean = new EmployeeMasterBean();
	Session session = null;

	private String userId;
	
	private Integer roleId;
	
	@Wire("#updateWin")
	private Window updateWin ;
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("employee")EmployeeMasterBean employee)
			throws Exception {

		Selectors.wireComponents(view, this, false);
		System.out.println("employeeupdate.zul");
		session = Sessions.getCurrent();
		userId = (String) session.getAttribute("userId");
		employeeMasterBean = employee;
	}

	@Command
	@NotifyChange("*")
	public void onClickUpdate(){
		EmployeeMasterService.updateEmployee(userId, employeeMasterBean);
		updateWin.detach();
		BindUtils.postGlobalCommand(null, null, "globalEmployeeUpdate", null);
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

	public Window getUpdateWin() {
		return updateWin;
	}

	public void setUpdateWin(Window updateWin) {
		this.updateWin = updateWin;
	}
	

}
