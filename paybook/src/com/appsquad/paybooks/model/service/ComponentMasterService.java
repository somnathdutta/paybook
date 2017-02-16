package com.appsquad.paybooks.model.service;

import java.util.ArrayList;

import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.ComponentMasterBean;
import com.appsquad.paybooks.bean.EmployeeMasterBean;
import com.appsquad.paybooks.dao.ComponentMasterDao;

public class ComponentMasterService {



	public static int insertComponent(String userName, ComponentMasterBean bean){
		int i= 0;
		i = ComponentMasterDao.saveComponents(userName, bean);
		return i;
	}
	
	public static ArrayList<ComponentMasterBean> loadComponents(){
		return ComponentMasterDao.loadComponents();
		 
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

	
	
}
