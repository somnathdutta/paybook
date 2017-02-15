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
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws Exception {

		Selectors.wireComponents(view, this, false);
		
		session = Sessions.getCurrent();
		if (session.getAttribute("userId") != null) {
			userId = (String) session.getAttribute("userId");
			//userId = "Welcome " + userId;
			userId = "Welcome "+(String) session.getAttribute("userName");
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
	
	
}
