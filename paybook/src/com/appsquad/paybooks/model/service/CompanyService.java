package com.appsquad.paybooks.model.service;

import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.CompanyBean;
import com.appsquad.paybooks.dao.CompanyDao;

public class CompanyService {

	public static int saveCompany(CompanyBean companyBean,String user){
		if(isValid(companyBean)){
			return CompanyDao.saveCompany(companyBean, user);
		}
		return 0;
	}
	
	public static int updateCompany(CompanyBean companyBean,String user){
		if(isValid(companyBean)){
			return CompanyDao.updateCompany(companyBean, user);
		}
		return 0;
	}
	
	public static boolean isValid(CompanyBean companyBean){
		if(companyBean.getCompanyName()!=null){
			if(companyBean.getAddress()!=null){
				if(companyBean.getWorkLocation()!=null){
					return true;
				}else{
					Messagebox.show("Work location required!","Work location required",Messagebox.OK,Messagebox.EXCLAMATION);
					return false;
				}
			}else{
				Messagebox.show("Company address required!","Address required",Messagebox.OK,Messagebox.EXCLAMATION);
				return false;
			}
		}else{
			Messagebox.show("Company name required!","Company name required",Messagebox.OK,Messagebox.EXCLAMATION);
			return false;
		}
	}
}
