package com.appsquad.paybooks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

import com.appsquad.paybooks.bean.EmployeeMasterBean;
import com.appsquad.paybooks.database.DatabaseHandler;
import com.appsquad.paybooks.database.Pbpstm;
import com.appsquad.paybooks.model.utils.PropertyFileAccess;

public class MyProfileDao {

	public static EmployeeMasterBean getEmployee(int empId){
		EmployeeMasterBean employeeMasterBean = null;
		try {
			 Connection connection = DatabaseHandler.createConnection();
			 PreparedStatement preparedStatement = null;
			 ResultSet resultSet = null;
			 try {
				preparedStatement= Pbpstm.createQuery(connection, PropertyFileAccess.getPropertyObject().getPropValues("get_employee", "sql.properties"), 
						Arrays.asList(empId));
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					employeeMasterBean = new EmployeeMasterBean();
					employeeMasterBean.setEmployeeId(resultSet.getInt("employee_info_id"));
					employeeMasterBean.setCompanyName(resultSet.getString("company"));
					employeeMasterBean.setEmployeeCode(resultSet.getString("employee_code"));
					employeeMasterBean.setEmployeeName(resultSet.getString("employee_name"));
					employeeMasterBean.setPassword(resultSet.getString("password"));
					employeeMasterBean.setEmailID(resultSet.getString("email_id"));
					employeeMasterBean.setBankAcNo(resultSet.getString("account_no"));
					employeeMasterBean.setDepartment(resultSet.getString("department"));
					employeeMasterBean.setDesignation(resultSet.getString("designation"));
					employeeMasterBean.setDojSql(resultSet.getDate("date_of_joining"));
					employeeMasterBean.setEsiNumber(resultSet.getString("esi_number"));
					employeeMasterBean.setPfNumber(resultSet.getString("pf_number"));
					employeeMasterBean.setUanNumber(resultSet.getString("uan_number"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(preparedStatement!=null){
					preparedStatement.close();
				}
				if(connection!=null){
					connection.close();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return employeeMasterBean;
	}
	
	public static void updateEmployeePassword(EmployeeMasterBean employee){
		try {
			 Connection connection = DatabaseHandler.createConnection();
			 PreparedStatement preparedStatement = null;
			try {
				preparedStatement= Pbpstm.createQuery(connection, PropertyFileAccess.getPropertyObject().getPropValues("update_password", "sql.properties"), 
						Arrays.asList(employee.getPassword(),employee.getEmployeeName(),
								employee.getEmployeeCode(),employee.getEmployeeId()));
				int count = preparedStatement.executeUpdate();
				if(count>0){
					System.out.println("Password updated in login table!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(preparedStatement!=null){
					preparedStatement.close();
				}
				if(connection!=null){
					connection.close();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static int getCompanyId(int empId){
		int companyId= 0;
		try {
			 Connection connection = DatabaseHandler.createConnection();
			 PreparedStatement preparedStatement = null;
			 ResultSet resultSet = null;
			 try {
				preparedStatement= Pbpstm.createQuery(connection, PropertyFileAccess.getPropertyObject().getPropValues("get_employee_companyid", "sql.properties"), 
						Arrays.asList(empId));
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					companyId = resultSet.getInt("company_id");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(preparedStatement!=null){
					preparedStatement.close();
				}
				if(connection!=null){
					connection.close();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return companyId;
	}
}
