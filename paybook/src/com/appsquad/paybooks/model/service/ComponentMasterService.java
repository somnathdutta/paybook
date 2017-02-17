package com.appsquad.paybooks.model.service;

import java.util.ArrayList;

import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.ComponentMasterBean;
import com.appsquad.paybooks.bean.EmployeeMasterBean;
import com.appsquad.paybooks.dao.ComponentMasterDao;

public class ComponentMasterService {

	public static int insertComponent(String userName, ComponentMasterBean bean){
		if(componentsvalidation(bean)){
			return ComponentMasterDao.saveComponents(userName, bean);
		}
		return 0;
	}
	
	public static ArrayList<ComponentMasterBean> loadComponents(int companyId){
		return ComponentMasterDao.loadComponents(companyId);
		 
	}
	
	public static boolean componentsvalidation(ComponentMasterBean bean){
		if(bean.getComponent() != null && bean.getComponent().trim().length() >0){
			if(bean.getComponentTypeId()>0){
				return true;
			}else {
				Messagebox.show("Please choose component type!", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);return false;
			}
		}else {
			Messagebox.show("Please enter component name", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);return false;
		}
	}
	
	public static void onComponentClear(ComponentMasterBean bean){
		bean.setComponent(null);
		bean.setComponentType(null);
		bean.setComponentTypeId(0);
	}
	
	public static ArrayList<ComponentMasterBean> loadComponentTypes(){
		return ComponentMasterDao.loadComponentTypes();
	}

	public static boolean isComponentExists(String component,ArrayList<ComponentMasterBean> componentList){
		for(ComponentMasterBean comp : componentList){
			if(comp.getComponent().toUpperCase().equals(component.toUpperCase())){
				return true;
			}
		}
		return false;
	}
	
}
