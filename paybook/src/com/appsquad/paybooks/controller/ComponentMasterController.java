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
	private ComponentMasterBean componentType = new ComponentMasterBean();
	private ArrayList<ComponentMasterBean> componentTypeList;
	private ArrayList<ComponentMasterBean> componentBeanList;
	
	public Session session = null;

	private String userId;
	
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws Exception {

		Selectors.wireComponents(view, this, false);

		session = Sessions.getCurrent();
		userId = (String) session.getAttribute("userId");
		onLoad();
	}

	public void onLoad(){
		componentTypeList = ComponentMasterService.loadComponentTypes();
	}
	
	public void loadSavedComponents(){
		componentBeanList = ComponentMasterService.loadComponents() ;
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectType(){
		System.out.println("Type MAster : "+componentMasterBean.getComponentTypeId()+" type: "+componentMasterBean.getComponentType());
	}
	
	@Command
	@NotifyChange("*")
	public void onClickExisting(){
		loadSavedComponents();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickSave(){
		if(ComponentMasterService.componentsvalidation(componentMasterBean)){
			if(ComponentMasterDao.saveComponents(userId, componentMasterBean)>0){
				Messagebox.show("New component saved successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
				ComponentMasterService.onComponentClear(componentMasterBean);
				loadSavedComponents();
				onLoad();
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

	public ComponentMasterBean getComponentType() {
		return componentType;
	}

	public void setComponentType(ComponentMasterBean componentType) {
		this.componentType = componentType;
	}

	public ArrayList<ComponentMasterBean> getComponentTypeList() {
		return componentTypeList;
	}

	public void setComponentTypeList(
			ArrayList<ComponentMasterBean> componentTypeList) {
		this.componentTypeList = componentTypeList;
	}
	


}
