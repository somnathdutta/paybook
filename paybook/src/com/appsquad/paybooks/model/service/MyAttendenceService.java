package com.appsquad.paybooks.model.service;

import java.util.ArrayList;

import com.appsquad.paybooks.bean.AttendenceBean;
import com.appsquad.paybooks.dao.MyAttendenceControllerDao;

public class MyAttendenceService {

	public static void isAttendenceGiven(AttendenceBean attendenceBean){
		 MyAttendenceControllerDao.isAttendenceGiven(attendenceBean);
	}
	
	public static void checkIn(AttendenceBean attendenceBean){
		MyAttendenceControllerDao.giveInAttendence(attendenceBean);
	}
	
	public static void checkOut(AttendenceBean attendenceBean){
		MyAttendenceControllerDao.giveOutAttendence(attendenceBean);
	}
	
	public static ArrayList<AttendenceBean> getMyAttendenceList(int empId,int monthId){
		return MyAttendenceControllerDao.showMyAttendences(empId,monthId);
	}
	
	
}
