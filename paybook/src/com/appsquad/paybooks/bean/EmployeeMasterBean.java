package com.appsquad.paybooks.bean;

import java.util.Date;

public class EmployeeMasterBean {

	private Integer slNo;
	private Integer employeeId;
	private String employeeCode,designation,department,bankAcNo,esiNumber,
		pfNumber,uanNumber, employeeName, curentEmployee,emailID;
	
	private Date dojUtil;
	private java.sql.Date dojSql,leaveDate;
	private String dojStr;
	
	private Date dolUtil;
	private java.sql.Date dolSql;
	private String dolStr;
	
	private String employeeSearch;
	
	
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public Integer getSlNo() {
		return slNo;
	}
	public void setSlNo(Integer slNo) {
		this.slNo = slNo;
	}
	public String getCurentEmployee() {
		return curentEmployee;
	}
	public void setCurentEmployee(String curentEmployee) {
		this.curentEmployee = curentEmployee;
	}
	public Date getDojUtil() {
		return dojUtil;
	}
	public void setDojUtil(Date dojUtil) {
		this.dojUtil = dojUtil;
	}
	public java.sql.Date getDojSql() {
		return dojSql;
	}
	public void setDojSql(java.sql.Date dojSql) {
		this.dojSql = dojSql;
	}
	public String getDojStr() {
		return dojStr;
	}
	public void setDojStr(String dojStr) {
		this.dojStr = dojStr;
	}
	public Date getDolUtil() {
		return dolUtil;
	}
	public void setDolUtil(Date dolUtil) {
		this.dolUtil = dolUtil;
	}
	public java.sql.Date getDolSql() {
		return dolSql;
	}
	public void setDolSql(java.sql.Date dolSql) {
		this.dolSql = dolSql;
	}
	public String getDolStr() {
		return dolStr;
	}
	public void setDolStr(String dolStr) {
		this.dolStr = dolStr;
	}
	public String getEmployeeSearch() {
		return employeeSearch;
	}
	public void setEmployeeSearch(String employeeSearch) {
		this.employeeSearch = employeeSearch;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getBankAcNo() {
		return bankAcNo;
	}
	public void setBankAcNo(String bankAcNo) {
		this.bankAcNo = bankAcNo;
	}
	public String getEsiNumber() {
		return esiNumber;
	}
	public void setEsiNumber(String esiNumber) {
		this.esiNumber = esiNumber;
	}
	public String getPfNumber() {
		return pfNumber;
	}
	public void setPfNumber(String pfNumber) {
		this.pfNumber = pfNumber;
	}
	public String getUanNumber() {
		return uanNumber;
	}
	public void setUanNumber(String uanNumber) {
		this.uanNumber = uanNumber;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public java.sql.Date getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(java.sql.Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	
	
	
	
}
