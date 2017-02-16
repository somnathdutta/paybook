package com.appsquad.paybooks.controller;

import java.util.ArrayList;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;

import com.appsquad.paybooks.bean.AttendenceBean;
import com.appsquad.paybooks.bean.MonthMasterBean;
import com.appsquad.paybooks.bean.StatusBean;
import com.appsquad.paybooks.dao.LoadAllListDao;
import com.appsquad.paybooks.dao.MyProfileDao;
import com.appsquad.paybooks.model.service.MyAttendenceService;

public class MyAttendenceController {

	private AttendenceBean attendenceBean = new AttendenceBean();
	
	private MonthMasterBean monthMasterBean = new MonthMasterBean();
	
	private ArrayList<AttendenceBean> attendenceBeanList ;
	
	private ArrayList<MonthMasterBean> monthist;
	
	ArrayList<StatusBean> statusBeanList ;
	
	Session session = null;
	
	int userId;
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws Exception {
		Selectors.wireComponents(view, this, false);
		System.out.println("myattendence.zul");
		session = Sessions.getCurrent();
		userId = (int) session.getAttribute("primaryId");
		init();
	}

	public void init(){
		attendenceBean.setCheckInTime(new java.util.Date());
		attendenceBean.setCheckOutTime(new java.util.Date());
		attendenceBean.setAttendenceDate(new java.util.Date());
		attendenceBean.setEmpId(userId);
		attendenceBean.setCompanyId(MyProfileDao.getCompanyId(userId));
		MyAttendenceService.isAttendenceGiven(attendenceBean);
	}
	
	@Command
	@NotifyChange("*")
	public void onClickCheckIn(){
		MyAttendenceService.checkIn(attendenceBean);
	}
	
	@Command
	@NotifyChange("*")
	public void onClickCheckOut(){
		MyAttendenceService.checkOut(attendenceBean);
	}
	
	@Command
	@NotifyChange("*")
	public void OnClickMyAttendence(){
		monthist = LoadAllListDao.loadmonths();
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectMonth(){
		System.out.println(monthMasterBean.getMonthId());
		attendenceBeanList = MyAttendenceService.getMyAttendenceList(userId,monthMasterBean.getMonthId());
	}
	
	public AttendenceBean getAttendenceBean() {
		return attendenceBean;
	}

	public void setAttendenceBean(AttendenceBean attendenceBean) {
		this.attendenceBean = attendenceBean;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public ArrayList<StatusBean> getStatusBeanList() {
		return statusBeanList;
	}

	public void setStatusBeanList(ArrayList<StatusBean> statusBeanList) {
		this.statusBeanList = statusBeanList;
	}

	public ArrayList<AttendenceBean> getAttendenceBeanList() {
		return attendenceBeanList;
	}

	public void setAttendenceBeanList(ArrayList<AttendenceBean> attendenceBeanList) {
		this.attendenceBeanList = attendenceBeanList;
	}

	public MonthMasterBean getMonthMasterBean() {
		return monthMasterBean;
	}

	public void setMonthMasterBean(MonthMasterBean monthMasterBean) {
		this.monthMasterBean = monthMasterBean;
	}

	public ArrayList<MonthMasterBean> getMonthist() {
		return monthist;
	}

	public void setMonthist(ArrayList<MonthMasterBean> monthist) {
		this.monthist = monthist;
	}
}
