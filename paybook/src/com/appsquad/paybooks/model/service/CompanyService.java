package com.appsquad.paybooks.model.service;

import java.util.ArrayList;

import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.CompanyBean;
import com.appsquad.paybooks.dao.CompanyDao;

public class CompanyService {

	public static ArrayList<CompanyBean> loadAllCompanyList(){
		return CompanyDao.loadSavedCompanyList();
	}
	
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
			if(companyBean.getCompanyUserId()!=null){
				if(companyBean.getCompanyPassword()!=null){
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
					Messagebox.show("Password required!","Password required",Messagebox.OK,Messagebox.EXCLAMATION);
					return false;
				}
			}else{
				Messagebox.show("User id required!","User id required",Messagebox.OK,Messagebox.EXCLAMATION);
				return false;
			}
		}else{
			Messagebox.show("Company name required!","Name required",Messagebox.OK,Messagebox.EXCLAMATION);
			return false;
		}
	}
	
	public static boolean isUserIdExists(ArrayList<CompanyBean> companyBeanList,String userId){
		for(CompanyBean bean : companyBeanList){
			if(userId.equals(bean.getCompanyUserId())){
				return true;
			}
		}
		return false;
	}
}
