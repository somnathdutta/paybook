package com.appsquad.paybooks.model.service;

import java.util.ArrayList;

import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.EmployeeMasterBean;
import com.appsquad.paybooks.dao.EmployeeMasterDao;

public class EmployeeMasterService {

	public static int saveEmployeeInfo(String userName, EmployeeMasterBean bean){
		return EmployeeMasterDao.saveEmployeeInfo(userName, bean);
	}
	
	public static void updateEmployee(String userName, EmployeeMasterBean employee){
		EmployeeMasterDao.updateEmployeeInfo(employee, userName);
	}
	
	public static ArrayList<EmployeeMasterBean> loadEmpInfo(int companyId){
		return EmployeeMasterDao.loadEmployeeInfo(companyId);
	}
	
	public static boolean onEmpInfoValidation(EmployeeMasterBean bean){
		if(bean.getCompanyId()>0){
			if(bean.getEmployeeName() != null){
				if(bean.getEmployeeCode() != null && bean.getEmployeeCode().trim().length()>0){
					if(bean.getPassword() != null && bean.getPassword().trim().length()>0){
						if(bean.getEmailID()!=null && bean.getEmailID().matches(".+@.+\\.[a-z]+")){
							return true;
						}else {
							Messagebox.show("Please Enter Proper Email id", "Valid Email id required", Messagebox.OK, Messagebox.EXCLAMATION);return false;
						}
					}else {
						Messagebox.show("Please Enter Password", "Password required", Messagebox.OK, Messagebox.EXCLAMATION);return false;
					}
				}else {
					Messagebox.show("Please Enter Employee Code", "Employee code required", Messagebox.OK, Messagebox.EXCLAMATION);return false;
				}
			}else {
				Messagebox.show("Please Enter Employee Name", "Employee name required", Messagebox.OK, Messagebox.EXCLAMATION);return false;
			}
		}else {
			Messagebox.show("Please choose company", "Company required", Messagebox.OK, Messagebox.EXCLAMATION);return false;

		}
	}
	
	public static void onEmpInfoClear(EmployeeMasterBean bean){
		bean.setEmployeeCode(null);
		bean.setEmployeeName(null);
		bean.setBankAcNo(null);
		bean.setDepartment(null);
		bean.setDesignation(null);
		bean.setDojSql(null);
		bean.setEmailID(null);
		bean.setEsiNumber(null);
		bean.setPfNumber(null);
		bean.setEmailID(null);
		bean.setUanNumber(null);
		bean.setPassword(null);
	}
	
	//!email.matches(".+@.+\\.[a-z]+")
}
