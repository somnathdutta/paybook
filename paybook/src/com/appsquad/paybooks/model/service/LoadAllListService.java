package com.appsquad.paybooks.model.service;

import java.util.ArrayList;

import com.appsquad.paybooks.bean.EmployeeMasterBean;
import com.appsquad.paybooks.bean.MonthMasterBean;
import com.appsquad.paybooks.dao.LoadAllListDao;

public class LoadAllListService {

	public static ArrayList<EmployeeMasterBean> loadEmployeeInfo(){
		ArrayList<EmployeeMasterBean> list =new ArrayList<EmployeeMasterBean>();
		
		list = LoadAllListDao.loadActiveEmployeeInfo();
		
		return list;
	}
	
	public ArrayList<EmployeeMasterBean> loadActvEmployeeInfoSearch(String name){
		ArrayList<EmployeeMasterBean> list =new ArrayList<EmployeeMasterBean>();
		
		LoadAllListDao allListDao = new LoadAllListDao();
		list = allListDao.loadActiveEmployeeInfoSearch(name);
		
		return list;
	}
	
	public ArrayList<MonthMasterBean> loadAllMonths(){
		ArrayList<MonthMasterBean> list =new ArrayList<MonthMasterBean>();
		
		LoadAllListDao allListDao = new LoadAllListDao();
		list = allListDao.loadmonths();
		
		return list;
	}
	
	
}
