package com.appsquad.paybooks.model.service;

import java.util.ArrayList;

import com.appsquad.paybooks.bean.EmployeeMasterBean;
import com.appsquad.paybooks.bean.MonthMasterBean;
import com.appsquad.paybooks.dao.LoadAllListDao;

public class LoadAllListService {

	public static ArrayList<EmployeeMasterBean> loadEmployeeInfo(int companyId){
		return LoadAllListDao.loadActiveEmployeeInfo(companyId);
	}
	
	public static ArrayList<EmployeeMasterBean> loadActvEmployeeInfoSearch(String name){
		return LoadAllListDao.loadActiveEmployeeInfoSearch(name);
	}
	
	public ArrayList<MonthMasterBean> loadAllMonths(){
		return LoadAllListDao.loadmonths();
	}
	
	
}
