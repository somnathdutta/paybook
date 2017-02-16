package com.appsquad.paybooks.bean;

import java.util.Date;

public class AttendenceBean {

	private int empId,statusId,companyId;
	private String status,chkInStr,chkOutStr;
	private Date checkInTime,checkOutTime,attendenceDate;
	private boolean  checkInDisabled = false,checkOutDisabled = true;
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public Date getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}
	public Date getCheckOutTime() {
		return checkOutTime;
	}
	public void setCheckOutTime(Date checkOutTime) {
		this.checkOutTime = checkOutTime;
	}
	public boolean isCheckInDisabled() {
		return checkInDisabled;
	}
	public void setCheckInDisabled(boolean checkInDisabled) {
		this.checkInDisabled = checkInDisabled;
	}
	public boolean isCheckOutDisabled() {
		return checkOutDisabled;
	}
	public void setCheckOutDisabled(boolean checkOutDisabled) {
		this.checkOutDisabled = checkOutDisabled;
	}
	public Date getAttendenceDate() {
		return attendenceDate;
	}
	public void setAttendenceDate(Date attendenceDate) {
		this.attendenceDate = attendenceDate;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getChkInStr() {
		return chkInStr;
	}
	public void setChkInStr(String chkInStr) {
		this.chkInStr = chkInStr;
	}
	public String getChkOutStr() {
		return chkOutStr;
	}
	public void setChkOutStr(String chkOutStr) {
		this.chkOutStr = chkOutStr;
	}
}
