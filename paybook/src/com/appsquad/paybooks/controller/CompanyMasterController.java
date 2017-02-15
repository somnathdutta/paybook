package com.appsquad.paybooks.controller;

import java.util.ArrayList;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;

import com.appsquad.paybooks.bean.CompanyBean;
import com.appsquad.paybooks.dao.CompanyDao;
import com.appsquad.paybooks.model.service.CompanyService;

public class CompanyMasterController {
	Session session = null;

	private String userId;
	
	private Integer roleId;
	
	private CompanyBean companyBean ;
	
	private ArrayList<CompanyBean> companyBeanList ;
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws Exception {

		Selectors.wireComponents(view, this, false);
		System.out.println("companymaster.zul");
		session = Sessions.getCurrent();
		userId = (String) session.getAttribute("userId");
		init();
	}
	
	public void init(){
		if(companyBean==null){
			companyBean = new CompanyBean();
		}
		companyBeanList = CompanyDao.loadSavedCompanyList();
	}

	@Command
	@NotifyChange("*")
	public void onClickSave(){
		if(CompanyService.saveCompany(companyBean, userId) > 0){
			clear();
			init();
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickCancel(){
		clear();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickEdit(@BindingParam("bean")CompanyBean companyBean){
		companyBean.setDisabled(false);
		companyBean.setEditVisibility(false);
		companyBean.setUpdateVisibility(true);
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdate(@BindingParam("bean")CompanyBean companyBean){
		CompanyService.updateCompany(companyBean, userId);
		companyBean.setDisabled(true);
		companyBean.setEditVisibility(true);
		companyBean.setUpdateVisibility(false);
	}
	
	@Command
	@NotifyChange("*")
	public void onClickRowCancel(@BindingParam("bean")CompanyBean companyBean){
		companyBean.setDisabled(true);
		companyBean.setEditVisibility(true);
		companyBean.setUpdateVisibility(false);
	}
	
	public void clear(){
		if(companyBean!=null){
			companyBean.setAddress(null);
			companyBean.setCompanyName(null);
			companyBean.setWorkLocation(null);
		}
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
	
}
