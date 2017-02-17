package com.appsquad.paybooks.model.service;

import java.util.ArrayList;

import com.appsquad.paybooks.bean.ComponentMasterBean;
import com.appsquad.paybooks.bean.EmployeeMasterBean;
import com.appsquad.paybooks.dao.ComponentAllocationDao;

public class ComponentAllocationService {

	public static ArrayList<ComponentMasterBean> loadcomponent(int empId,int companyId){
		ArrayList<ComponentMasterBean> list = ComponentAllocationDao.loadComponent(empId,companyId);
		return list;
		
	}
	
	public static boolean insertcomponentwithemp(String userName ,ArrayList<ComponentMasterBean> list, Integer empId){
		boolean flag = false;
		flag = ComponentAllocationDao.saveComonentwithEmployee(userName, list, empId);
		return flag;
	}
	
	public static void onClear(EmployeeMasterBean employeeMasterBean, ArrayList<ComponentMasterBean> list){
		employeeMasterBean.setEmployeeId(null);
		employeeMasterBean.setEmployeeName(null);
		list.clear();
	}
	
	public static ArrayList<ComponentMasterBean> loadAllocatedcomponentPerEmp(int empId){
		ArrayList<ComponentMasterBean> list = ComponentAllocationDao.loadAllocatedComponent(empId) ;
		return list;
		
	}
	
	public static boolean updatecomponentwithemp(String userName ,ArrayList<ComponentMasterBean> list){
		boolean flag = false;
		flag = ComponentAllocationDao.updateComonentwithEmployee(userName, list);
		return flag;
	}
	
}
