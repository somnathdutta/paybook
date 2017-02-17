package com.appsquad.paybooks.controller;

import java.util.ArrayList;
import java.util.Date;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.AttendenceBean;
import com.appsquad.paybooks.bean.StatusBean;
import com.appsquad.paybooks.dao.AttendenceControllerDao;
import com.appsquad.paybooks.dao.StatusDao;

public class ManageAttendenceController {

	Session session = null;
	
	int primaryId,roleId;
	
	private ArrayList<AttendenceBean> todayattendenceList ;
	
	private ArrayList<AttendenceBean> dbattendenceList ;
	
	private ArrayList<AttendenceBean> attendenceList ;
	
	private StatusBean statusBean = new StatusBean();
	
	private ArrayList<StatusBean> statusBeanList;
	
	private Date startDate,endDate;
	
	private boolean allChecked = false; 
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws Exception {
		Selectors.wireComponents(view, this, false);
		System.out.println("manageattendence.zul");
		session = Sessions.getCurrent();
		primaryId = (int) session.getAttribute("primaryId");
		roleId = (int) session.getAttribute("rollId");
		init();
	}
	
	public void init(){
		attendenceList = AttendenceControllerDao.getTodayAttendence(primaryId);
		statusBeanList = StatusDao.getStatusList();
	}

	@Command
	@NotifyChange("*")
	public void onCheckAll(){
		for(AttendenceBean attendence : attendenceList){
			if(allChecked){
				attendence.setChecked(true);
			}else{
				attendence.setChecked(false);
			}
		}		
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdateAttendence(){
		ArrayList<AttendenceBean> checkedList = new ArrayList<AttendenceBean>();
		for(AttendenceBean attendence : attendenceList){
			if(attendence.isChecked()){
				attendence.setStatusId(statusBean.getStatusId());
				checkedList.add(attendence);
			}
		}
		if(checkedList.size()>0){
			AttendenceControllerDao.updateAttendence(checkedList, primaryId);
			init();
			allChecked = false;
		}else{
			Messagebox.show("Please check at least one employee!","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
		}
	}
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public int getPrimaryId() {
		return primaryId;
	}

	public void setPrimaryId(int primaryId) {
		this.primaryId = primaryId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public ArrayList<AttendenceBean> getTodayattendenceList() {
		return todayattendenceList;
	}

	public void setTodayattendenceList(ArrayList<AttendenceBean> todayattendenceList) {
		this.todayattendenceList = todayattendenceList;
	}

	public ArrayList<AttendenceBean> getDbattendenceList() {
		return dbattendenceList;
	}

	public void setDbattendenceList(ArrayList<AttendenceBean> dbattendenceList) {
		this.dbattendenceList = dbattendenceList;
	}

	public ArrayList<AttendenceBean> getAttendenceList() {
		return attendenceList;
	}

	public void setAttendenceList(ArrayList<AttendenceBean> attendenceList) {
		this.attendenceList = attendenceList;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public StatusBean getStatusBean() {
		return statusBean;
	}

	public void setStatusBean(StatusBean statusBean) {
		this.statusBean = statusBean;
	}

	public ArrayList<StatusBean> getStatusBeanList() {
		return statusBeanList;
	}

	public void setStatusBeanList(ArrayList<StatusBean> statusBeanList) {
		this.statusBeanList = statusBeanList;
	}

	public boolean isAllChecked() {
		return allChecked;
	}

	public void setAllChecked(boolean allChecked) {
		this.allChecked = allChecked;
	}
}
