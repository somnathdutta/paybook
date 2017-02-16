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
import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.LoginBean;
import com.appsquad.paybooks.model.service.LoginService;

public class LoginController {
	
	private LoginBean loginBean = new LoginBean();
	private String username;
	private String userId;
	
	
	
	public Session session = null;
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws Exception {

		Selectors.wireComponents(view, this, false);
		session = Sessions.getCurrent();
		
	}
	
	@Command
	@NotifyChange("*")
	public void onCLickLogin(){
		if(LoginService.loginValidation(loginBean)){
			LoginBean user = LoginService.loginServ(loginBean);
			if(user!=null){		
				username = (String) session.setAttribute("userId", loginBean.getUserId());
				session.setAttribute("userName", user.getUserName());
				session.setAttribute("rollId", user.getRollId());
				session.setAttribute("primaryId", user.getPrimaryId());
				Executions.sendRedirect("home.zul");
				System.out.println("Loging in...");	
			}else {
				Messagebox.show("Invalid credential given!", "Invalid Login Credential", Messagebox.OK, Messagebox.ERROR);
			}
		}
		
		
		
		
		
	}

	
	
	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
