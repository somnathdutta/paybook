package com.appsquad.paybooks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.ComponentMasterBean;
import com.appsquad.paybooks.bean.GeneratePayslipBean;
import com.appsquad.paybooks.database.DatabaseHandler;
import com.appsquad.paybooks.database.Pbpstm;
import com.appsquad.paybooks.model.utils.Dateformatter;
import com.appsquad.paybooks.sql.GeneratePayslipSql;

public class GeneratePayslipDao {

	public static ArrayList<GeneratePayslipBean> loadEmpSalDetails(int companyId){
		ArrayList<GeneratePayslipBean> list = new ArrayList<GeneratePayslipBean>();
		if(list.size()>0){
			list.clear();
		}
		int count =0;
		try {
			Connection connection = DatabaseHandler.createConnection();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				preparedStatement = Pbpstm.createQuery(connection, GeneratePayslipSql.loadEmployee, 
						Arrays.asList(companyId));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					GeneratePayslipBean bean = new GeneratePayslipBean();
					count = count+1;
					bean.setSlNo(count);
					bean.setCheck(false);
					bean.setEmployeeId(resultSet.getInt("employee_info_id"));
					bean.setEmployeeCode(resultSet.getString("employee_code"));
					bean.setEmployeeName(resultSet.getString("employee_name"));
					bean.setDesignation(resultSet.getString("designation"));
					bean.setEmailId(resultSet.getString("email_id"));
					bean.setDojStr(resultSet.getString("date_of_joining"));
					bean.setDojSql(resultSet.getDate("date_of_joining"));
					if(bean.getDojStr() != null){
						bean.setDojStr(Dateformatter.toStringDate(bean.getDojStr()));
					}
					bean.setAccNo(resultSet.getString("account_no"));
					bean.setEsi(resultSet.getString("esi_number"));
					bean.setPf(resultSet.getString("pf_number"));
					bean.setUan(resultSet.getString("uan_number"));
					bean.setDepartment(resultSet.getString("department"));
					bean.setComponentList(GeneratePayslipDao.loadComponents(connection, bean.getEmployeeId(), bean));
					
					list.add(bean);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				Messagebox.show(e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}if(resultSet != null){
					resultSet.close();
				}if(connection != null){
					connection.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
		
	}
	
	public static ArrayList<ComponentMasterBean> loadComponents(Connection connection, int empId, GeneratePayslipBean payslipBean){
		ArrayList<ComponentMasterBean> list = new ArrayList<ComponentMasterBean>();
		if(list.size()>0){
			list.clear();
		}
		double totalEarnings = 0.0;
		double totaldeductions = 0.0;
		NumberFormat nf = new DecimalFormat("#0.00");
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = Pbpstm.createQuery(connection, GeneratePayslipSql.loadEmployeeSalaryComponents, Arrays.asList(empId));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ComponentMasterBean bean = new ComponentMasterBean();
				bean.setComponentId(resultSet.getInt("salary_components_id"));
				bean.setComponent(resultSet.getString("component_name"));
				bean.setAmount(resultSet.getDouble("amount"));
				bean.seteOrdId(resultSet.getInt("e_or_d_id"));
				
				if(bean.geteOrdId() ==1){
					totalEarnings = totalEarnings + bean.getAmount();
				}
				if(bean.geteOrdId() == 2){
					totaldeductions = totaldeductions+ bean.getAmount();
				}
				
				
				list.add(bean);
			}
			payslipBean.setTotalEarningAmnt(totalEarnings);
			payslipBean.setTotalDeductionAmnt(totaldeductions);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
}
