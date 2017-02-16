package com.appsquad.paybooks.model.service;

import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.EmployeeMasterBean;
import com.appsquad.paybooks.dao.EmployeeMasterDao;
import com.appsquad.paybooks.dao.MyProfileDao;

public class MyProfileService {

	public static boolean isValid(EmployeeMasterBean employee){
		if(employee.getEmployeeName()!=null){
			if(employee.getPassword()!=null){
				if(employee.getEmailID()!=null && employee.getEmailID().matches(".+@.+\\.[a-z]+")){
					return true;
				}else {
					Messagebox.show("Please Enter Proper Email id", "Valid Email id required", Messagebox.OK, Messagebox.EXCLAMATION);return false;
				}
			}else {
				Messagebox.show("Please Enter Password", "Password required", Messagebox.OK, Messagebox.EXCLAMATION);return false;
			}
		}else {
			Messagebox.show("Please Enter Name", "Name required", Messagebox.OK, Messagebox.EXCLAMATION);return false;
		}
	}
	
	public static EmployeeMasterBean getEmployee(int empId){
		return MyProfileDao.getEmployee(empId);
	}
	
	public static void updateMyProfile(EmployeeMasterBean employee,String user){
		if(isValid(employee)){
			EmployeeMasterDao.updateEmployeeInfo(employee, user);
		}
	}
}
