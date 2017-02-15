package com.appsquad.paybooks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.EmployeeMasterBean;
import com.appsquad.paybooks.bean.MonthMasterBean;
import com.appsquad.paybooks.database.DatabaseHandler;
import com.appsquad.paybooks.database.Pbpstm;
import com.appsquad.paybooks.sql.LoadAllListSql;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class LoadAllListDao {

	public static ArrayList<EmployeeMasterBean> loadActiveEmployeeInfo(){
		int count =0;
		ArrayList<EmployeeMasterBean> list =new ArrayList<EmployeeMasterBean>();
		if(list.size()>0){
			list.clear();
		}
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, LoadAllListSql.loadEmployeeInfo, null);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					EmployeeMasterBean bean = new EmployeeMasterBean();
					count = count+1;
					bean.setSlNo(count);
					bean.setEmployeeId(resultSet.getInt("employee_info_id"));
					bean.setEmployeeCode(resultSet.getString("employee_code"));
					bean.setEmployeeName(resultSet.getString("employee_name"));
					
					
					list.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Messagebox.show(e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
				
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}
				if(connection != null){
					connection.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	
	}
	
	
	public ArrayList<EmployeeMasterBean> loadActiveEmployeeInfoSearch(String name){
		int count =0;
		ArrayList<EmployeeMasterBean> list =new ArrayList<EmployeeMasterBean>();
		if(list.size()>0){
			list.clear();
		}
		if(name == null){
			name = "";
			System.out.println("NAME " + name);
		}
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, LoadAllListSql.loadAEmpInfoBySerch, Arrays.asList(name.trim() +"%"));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					EmployeeMasterBean bean = new EmployeeMasterBean();
					count = count+1;
					bean.setSlNo(count);
					bean.setEmployeeId(resultSet.getInt("employee_info_id"));
					bean.setEmployeeCode(resultSet.getString("employee_code"));
					bean.setEmployeeName(resultSet.getString("employee_name"));
					
					
					list.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Messagebox.show(e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
				
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}
				if(connection != null){
					connection.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	
	}
	
	public static ArrayList<MonthMasterBean> loadmonths(){
		
		ArrayList<MonthMasterBean> list =new ArrayList<MonthMasterBean>();
		if(list.size()>0){
			list.clear();
		}
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, LoadAllListSql.loadMonths, null);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					MonthMasterBean bean = new MonthMasterBean();
					bean.setMonthId(resultSet.getInt("month_master_id"));
					bean.setMonth(resultSet.getString("month_name"));
					
					list.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Messagebox.show(e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
				
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}
				if(connection != null){
					connection.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	
	}
	
public static ArrayList<MonthMasterBean> loadyears(){
		
		ArrayList<MonthMasterBean> list =new ArrayList<MonthMasterBean>();
		if(list.size()>0){
			list.clear();
		}
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, LoadAllListSql.loadYears, null);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					MonthMasterBean bean = new MonthMasterBean();
					bean.setYearId(resultSet.getInt("year_master_id"));
					bean.setYr(resultSet.getString("yr"));
					
					list.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Messagebox.show(e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
				
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}
				if(connection != null){
					connection.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	
	}
	
}
