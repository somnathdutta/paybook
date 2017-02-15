package com.appsquad.paybooks.controller;

import java.util.ArrayList;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

import com.appsquad.paybooks.bean.ComponentMasterBean;
import com.appsquad.paybooks.bean.GeneratePayslipBean;
import com.appsquad.paybooks.model.research.DesimalFormat;

public class PayslipViewController {

	private GeneratePayslipBean viewPaySlipBean = new GeneratePayslipBean();
	
	private ArrayList<ComponentMasterBean> earningList = new ArrayList<ComponentMasterBean>();
	private ArrayList<ComponentMasterBean> deductionList = new ArrayList<ComponentMasterBean>();
	
	private Session session = null;

	private String userId,message;
	
	private String totSal,earnTotal,dedTotal;
	
	@Wire("#payslipwin")
	private Window payslipwin;
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("parent")GeneratePayslipBean bean)
			throws Exception {

		Selectors.wireComponents(view, this, false);
		System.out.println("payslipview.zul. . .");
		session = Sessions.getCurrent();
		userId = (String) session.getAttribute("userId");
		viewPaySlipBean = bean;
		onLoad();
	}
	
	public void onLoad(){
		double earnTot = 0,dedTot = 0;
		message = "Payslip of "+viewPaySlipBean.getEmployeeName()+" for the month of "+viewPaySlipBean.getMonth()+"-"+viewPaySlipBean.getYear();
		for(ComponentMasterBean comp : viewPaySlipBean.getComponentList()){
			if(comp.geteOrdId() == 1){
				comp.setComponentAmount(DesimalFormat.twoDecimalFormat(comp.getAmount()));
				earningList.add(comp);
				earnTot+=comp.getAmount();
				
			}else{
				comp.setComponentAmount(DesimalFormat.twoDecimalFormat(comp.getAmount()));
				deductionList.add(comp);
				dedTot+=comp.getAmount();
				
			}
		}
		earnTotal = DesimalFormat.twoDecimalFormat(earnTot);
		dedTotal = DesimalFormat.twoDecimalFormat(dedTot);
		totSal = DesimalFormat.twoDecimalFormat(earnTot - dedTot)+" Only" ;
	}
	
	public GeneratePayslipBean getViewPaySlipBean() {
		return viewPaySlipBean;
	}
	public void setViewPaySlipBean(GeneratePayslipBean viewPaySlipBean) {
		this.viewPaySlipBean = viewPaySlipBean;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Window getPayslipwin() {
		return payslipwin;
	}
	public void setPayslipwin(Window payslipwin) {
		this.payslipwin = payslipwin;
	}

	public ArrayList<ComponentMasterBean> getEarningList() {
		return earningList;
	}

	public void setEarningList(ArrayList<ComponentMasterBean> earningList) {
		this.earningList = earningList;
	}

	public ArrayList<ComponentMasterBean> getDeductionList() {
		return deductionList;
	}

	public void setDeductionList(ArrayList<ComponentMasterBean> deductionList) {
		this.deductionList = deductionList;
	}

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTotSal() {
		return totSal;
	}

	public void setTotSal(String totSal) {
		this.totSal = totSal;
	}

	public String getEarnTotal() {
		return earnTotal;
	}

	public void setEarnTotal(String earnTotal) {
		this.earnTotal = earnTotal;
	}

	public String getDedTotal() {
		return dedTotal;
	}

	public void setDedTotal(String dedTotal) {
		this.dedTotal = dedTotal;
	}

	
}
