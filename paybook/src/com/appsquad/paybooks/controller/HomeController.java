package com.appsquad.paybooks.controller;


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

public class HomeController {

	Session session = null;

	private String userId;
	
	private Integer roleId;
	
	private boolean open = false;
	
	private boolean master = false,profile=false,slips=false,
					attendence=false,leave=false,company=false,mycompany=false,
					component=false,salayslip =false,componentallocation = false;
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws Exception {

		Selectors.wireComponents(view, this, false);
		
		session = Sessions.getCurrent();
		if (session.getAttribute("userId") != null) {
			userId = (String) session.getAttribute("userId");
			//userId = "Welcome " + userId;
			userId = "Welcome "+(String) session.getAttribute("userName");
			roleId = (int)session.getAttribute("rollId");
			loadMenus();
		}else{	
			Executions.sendRedirect("/index.zul");
		}
	}

	
	@Command
	@NotifyChange("*")
	public void onClickChangePassword(){
		
	}
	
	@Command
	@NotifyChange("*")
	public void onClickLogout(){
		System.out.println("Signing out...");
		if(session!=null){
			session.removeAttribute("userId");
			session.invalidate();
			session=null;
			Executions.sendRedirect("/index.zul");
		}
	}
	
	public void loadMenus(){
		if(roleId==5){//Employee
			profile = true;attendence= true;leave=true;slips=true;component=true;
		}else if(roleId==3){//Company
			master = true;componentallocation = true;salayslip = true;mycompany = true;
		}else{
			master = true;componentallocation = true;salayslip = true;company = true;
		}
	}
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public boolean isOpen() {
		return open;
	}


	public void setOpen(boolean open) {
		this.open = open;
	}


	public boolean isMaster() {
		return master;
	}


	public void setMaster(boolean master) {
		this.master = master;
	}


	public boolean isProfile() {
		return profile;
	}


	public void setProfile(boolean profile) {
		this.profile = profile;
	}


	public boolean isSlips() {
		return slips;
	}


	public void setSlips(boolean slips) {
		this.slips = slips;
	}


	public boolean isAttendence() {
		return attendence;
	}


	public void setAttendence(boolean attendence) {
		this.attendence = attendence;
	}


	public boolean isLeave() {
		return leave;
	}


	public void setLeave(boolean leave) {
		this.leave = leave;
	}


	public boolean isComponent() {
		return component;
	}


	public void setComponent(boolean component) {
		this.component = component;
	}


	public boolean isSalayslip() {
		return salayslip;
	}


	public void setSalayslip(boolean salayslip) {
		this.salayslip = salayslip;
	}


	public boolean isComponentallocation() {
		return componentallocation;
	}


	public void setComponentallocation(boolean componentallocation) {
		this.componentallocation = componentallocation;
	}


	public boolean isCompany() {
		return company;
	}


	public void setCompany(boolean company) {
		this.company = company;
	}


	public boolean isMycompany() {
		return mycompany;
	}


	public void setMycompany(boolean mycompany) {
		this.mycompany = mycompany;
	}
	
	
}
