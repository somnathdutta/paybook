package com.appsquad.paybooks.bean;

public class CompanyBean {

	private String companyName,address,workLocation;
	private int companyId;
	private boolean disabled = true,editVisibility = true,updateVisibility = false;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWorkLocation() {
		return workLocation;
	}
	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public boolean isEditVisibility() {
		return editVisibility;
	}
	public void setEditVisibility(boolean editVisibility) {
		this.editVisibility = editVisibility;
	}
	public boolean isUpdateVisibility() {
		return updateVisibility;
	}
	public void setUpdateVisibility(boolean updateVisibility) {
		this.updateVisibility = updateVisibility;
	}
}
