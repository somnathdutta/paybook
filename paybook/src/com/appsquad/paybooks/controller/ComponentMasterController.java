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
import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.ComponentMasterBean;
import com.appsquad.paybooks.dao.ComponentMasterDao;
import com.appsquad.paybooks.model.service.ComponentMasterService;


public class ComponentMasterController {
	
	private ComponentMasterBean componentMasterBean = new ComponentMasterBean();
	private ComponentMasterBean existiingComponrntMasterBean = new ComponentMasterBean();
	
	
	private ArrayList<ComponentMasterBean> componentBeanList;
	
	public Session session = null;

	private String userId;
	
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws Exception {

		Selectors.wireComponents(view, this, false);

		session = Sessions.getCurrent();
		userId = (String) session.getAttribute("userId");
		componentBeanList = ComponentMasterService.loadComponents() ;

	}

	@Command
	@NotifyChange("*")
	public void onClickSave(){
		int i =0;
		if(ComponentMasterService.componentsvalidation(componentMasterBean)){
			i = ComponentMasterDao.saveComponents(userId, componentMasterBean);
		if(i>0){
			Messagebox.show("Saved Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
			ComponentMasterService.onComponentClear(componentMasterBean);
			componentBeanList = ComponentMasterService.loadComponents() ;
		}
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdate(@BindingParam("bean") ComponentMasterBean bean){
		
	}

	public ComponentMasterBean getComponentMasterBean() {
		return componentMasterBean;
	}

	public void setComponentMasterBean(ComponentMasterBean componentMasterBean) {
		this.componentMasterBean = componentMasterBean;
	}

	public ComponentMasterBean getExistiingComponrntMasterBean() {
		return existiingComponrntMasterBean;
	}

	public void setExistiingComponrntMasterBean(
			ComponentMasterBean existiingComponrntMasterBean) {
		this.existiingComponrntMasterBean = existiingComponrntMasterBean;
	}

	public ArrayList<ComponentMasterBean> getComponentBeanList() {
		return componentBeanList;
	}

	public void setComponentBeanList(
			ArrayList<ComponentMasterBean> componentBeanList) {
		this.componentBeanList = componentBeanList;
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
	


}
