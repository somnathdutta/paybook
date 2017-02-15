package com.appsquad.paybooks.model.service;

import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.LoginBean;
import com.appsquad.paybooks.dao.LoginDao;

public class LoginService {

	public static LoginBean loginServ(LoginBean bean){
		return LoginDao.login(bean);
	}
	
	public static boolean loginValidation(LoginBean bean){
		if(bean.getUserId() != null){
			if(bean.getPassword() != null){
				return true;	
			}else {
				Messagebox.show("Enter Password", "Password required", Messagebox.OK, Messagebox.EXCLAMATION);
				return false;
			}
		}else {
			Messagebox.show("Enter Userid", "UserId required", Messagebox.OK, Messagebox.EXCLAMATION);
			return false;
		}
		
	}
	
	
}
