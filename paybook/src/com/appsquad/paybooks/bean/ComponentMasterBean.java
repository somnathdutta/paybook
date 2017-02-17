package com.appsquad.paybooks.bean;

public class ComponentMasterBean {

	private Integer componentId;
	private String component,componentAmount,componentType;
	private int slNo;
	private String isActive;
	private boolean checked;
	private Double amount;
	private Integer eOrdId;
	private int compoAllocationId,componentTypeId,companyId;
	
	
	public Integer getComponentId() {
		return componentId;
	}
	public void setComponentId(Integer componentId) {
		this.componentId = componentId;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public int getSlNo() {
		return slNo;
	}
	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer geteOrdId() {
		return eOrdId;
	}
	public void seteOrdId(Integer eOrdId) {
		this.eOrdId = eOrdId;
	}
	public int getCompoAllocationId() {
		return compoAllocationId;
	}
	public void setCompoAllocationId(int compoAllocationId) {
		this.compoAllocationId = compoAllocationId;
	}
	public String getComponentAmount() {
		return componentAmount;
	}
	public void setComponentAmount(String componentAmount) {
		this.componentAmount = componentAmount;
	}
	public int getComponentTypeId() {
		return componentTypeId;
	}
	public void setComponentTypeId(int componentTypeId) {
		this.componentTypeId = componentTypeId;
	}
	public String getComponentType() {
		return componentType;
	}
	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	
	
}
