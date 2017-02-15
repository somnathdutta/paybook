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
		ArrayList<ComponentMasterBean> list = new ArrayList<ComponentMasterBean>();
		list = ComponentMasterDao.loadComponents();
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	public static boolean componentsvalidation(ComponentMasterBean bean){
		if(bean.getComponent() != null && bean.getComponent().trim().length() >0){
			return true;
		}else {
			Messagebox.show("Enter Componet", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);return false;
		}
	}
	
	public static void onComponentClear(ComponentMasterBean bean){
		bean.setComponent(null);
	}
	
	

	
	
}
