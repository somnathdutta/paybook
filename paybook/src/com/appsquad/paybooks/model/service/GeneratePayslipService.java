package com.appsquad.paybooks.model.service;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.ComponentMasterBean;
import com.appsquad.paybooks.bean.EmployeeMasterBean;
import com.appsquad.paybooks.bean.GeneratePayslipBean;
import com.appsquad.paybooks.dao.GeneratePayslipDao;

public class GeneratePayslipService {

	public static int getNoOfDaysInMonth(int year, int month){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public static double[] getAllAmounts(GeneratePayslipBean bean){
		double totalEarningAmout = 0,totalDeductionAmount =0 ,netPay = 0;
		for(ComponentMasterBean component : bean.getComponentList()){
			if(component.geteOrdId() ==1){		
				double amount = (component.getAmount()/bean.getTotalNoOfDaysInMonth()) * bean.getPresentDays();
				totalEarningAmout = totalEarningAmout + amount;
				
			}
			if(component.geteOrdId() == 2){
				double amount =(component.getAmount()/bean.getTotalNoOfDaysInMonth()) * bean.getPresentDays() ;
				totalDeductionAmount = totalDeductionAmount+ amount;
				
			}
		}
		/*System.out.println("Total earn : "+totalEarningAmout
				+" Toal ded: "+totalDeductionAmount+" Net : "+netPay);*/
		
		netPay = totalEarningAmout - totalDeductionAmount;
		double totals[]  = new double[3];
			totals[0] = totalEarningAmout;
			totals[1] = totalDeductionAmount;
			totals[2] = netPay;
		return totals;
	}
	
	public static ArrayList<GeneratePayslipBean> loadempSalDetails(int companyId){
		return GeneratePayslipDao.loadEmpSalDetails(companyId);
	}
	
	public static ArrayList<GeneratePayslipBean> loadEmpSearched(String empName,
			ArrayList<GeneratePayslipBean> dbList){
		ArrayList<GeneratePayslipBean> list = new ArrayList<GeneratePayslipBean>();
		for(GeneratePayslipBean paySlip : dbList){
			if(paySlip.getEmployeeName().equals(empName)){
				list.add(paySlip);
			}
		}
		return list;
	}
	
	public static ArrayList<EmployeeMasterBean> searchEmployee(String empName,
			ArrayList<EmployeeMasterBean> dbList){
		ArrayList<EmployeeMasterBean> list = new ArrayList<EmployeeMasterBean>();
		for(EmployeeMasterBean emp : dbList){
			if(emp.getEmployeeName().toUpperCase().startsWith(empName.toUpperCase())){
				list.add(emp);
			}
		}
		return list;
	}
	
	public static boolean isValid(String month,String year,GeneratePayslipBean bean){
		if(month!=null){
			if(year!=null){
				if(bean.getPresentDays()!=null){
					return true;
				}else{
					Messagebox.show("Please enter no of days present!","Present days required",Messagebox.OK,Messagebox.EXCLAMATION);
					return false;
				}
			}else{
				Messagebox.show("Please choose year!","Year required",Messagebox.OK,Messagebox.EXCLAMATION);
				return false;
			}
		}else{
			Messagebox.show("Please choose month!","Month required",Messagebox.OK,Messagebox.EXCLAMATION);
			return false;
		}
	}
	
	public void uploadPaySlip(String paySlipFile){
		String fileName =null;
		String uploadImageDir = "";
		String uploadImagePath = "";
		URL resourceUrl = this.getClass().getResource("/");
		String filePath = resourceUrl.getFile();
		String serverRootDir = new File(new File(filePath).getParent()).getParent();

		String currentDate = new SimpleDateFormat("dd-MMM-yyyy").format(new java.util.Date()).toString();
		
		String uploadPath = serverRootDir.toString()+File.separator+"uploads"+"/"+"PaySlip"+ File.separator+currentDate;
		File fileDir = new File(uploadPath);
		 if(!fileDir.exists()){
			 fileDir.mkdirs();
			 System.out.println("New Directory Created..");
		 }
		
		String cuisingName = paySlipFile;
		cuisingName = cuisingName.replaceAll("\\s", "");
		String rand = UUID.randomUUID().toString().substring(0, 30);
		String generateName = cuisingName+"_"+rand;
	   // fileName = generateName.toUpperCase();
		fileName = paySlipFile;
	    uploadImageDir = serverRootDir.toString()+File.separator+"view"+File.separator+"uploads"+"/"+"PaySlip"+ File.separator+currentDate;
		
	    uploadImagePath ="uploads"+"/"+"PaySlip"+"/"+currentDate+"/"+fileName;
	    System.out.println(uploadImagePath);
	}
}
