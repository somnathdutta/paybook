package com.appsquad.paybooks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.ComponentMasterBean;
import com.appsquad.paybooks.database.DatabaseHandler;
import com.appsquad.paybooks.database.Pbpstm;
import com.appsquad.paybooks.sql.ComponentAllocationSql;

public class ComponentAllocationDao {

	public static ArrayList<ComponentMasterBean> loadComponent(int empId,int companyId){
		ArrayList<ComponentMasterBean> list = new ArrayList<ComponentMasterBean>();
		if(list.size()>0){
			list.clear();
		}
		int count = 0;
		try {
			Connection connection = DatabaseHandler.createConnection();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				preparedStatement = Pbpstm.createQuery(connection, ComponentAllocationSql.loadSalaryComponentresectiveemployee, 
						Arrays.asList(empId,companyId));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					ComponentMasterBean bean = new ComponentMasterBean();
					count = count+1;
					bean.setSlNo(count);
					bean.setComponentId(resultSet.getInt("salary_components_id"));
					bean.setComponent(resultSet.getString("component_name"));
					bean.seteOrdId(resultSet.getInt("e_or_d_id"));
					bean.setChecked(false);
					
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
	
	public static boolean saveComonentwithEmployee(String userName ,ArrayList<ComponentMasterBean> list, Integer empId){
		boolean flag=false;
		try {
			Connection connection = DatabaseHandler.createConnection();
			PreparedStatement preparedStatement = null;
			try {
			preparedStatement = connection.prepareStatement(ComponentAllocationSql.insertComponentwithEmp);
			for(ComponentMasterBean bean: list){
				
				preparedStatement.setInt(1, empId);
				preparedStatement.setInt(2, bean.getComponentId());
				preparedStatement.setDouble(3, bean.getAmount());
				preparedStatement.setInt(4, bean.geteOrdId());
				preparedStatement.setString(5, userName);
				preparedStatement.setString(6, userName);
				
				preparedStatement.addBatch();
				
			}
			int[] count = preparedStatement.executeBatch(); 
			if(count.length>0){
				flag = true; 
			}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public static ArrayList<ComponentMasterBean> loadAllocatedComponent(int empId){
		ArrayList<ComponentMasterBean> list = new ArrayList<ComponentMasterBean>();
		if(list.size()>0){
			list.clear();
		}
		int count = 0;
		try {
			Connection connection = DatabaseHandler.createConnection();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				preparedStatement = Pbpstm.createQuery(connection, ComponentAllocationSql.loadAllocatedComponentListPerEmpSql, Arrays.asList(empId));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					ComponentMasterBean bean = new ComponentMasterBean();
					count = count+1;
					bean.setSlNo(count);
					bean.setCompoAllocationId(resultSet.getInt("component_allocation_id"));
					bean.setComponentId(resultSet.getInt("salary_components_id"));
					bean.setComponent(resultSet.getString("component_name"));
					bean.setAmount(resultSet.getDouble("amount"));
					String isActive = resultSet.getString("is_delete");
					
					if(isActive.equals("N")){
						bean.setIsActive("YES");
					}else {
						bean.setIsActive("NO");
					}
					bean.setChecked(false);
					
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
	
	public static boolean updateComonentwithEmployee(String userName ,ArrayList<ComponentMasterBean> list){
		boolean flag=false;
		try {
			Connection connection = DatabaseHandler.createConnection();
			PreparedStatement preparedStatement = null;
			try {
			preparedStatement = connection.prepareStatement(ComponentAllocationSql.updateExAllocatedComponentSql);
			for(ComponentMasterBean bean: list){
				System.out.println("ACTIVE " + bean.getIsActive());
				String isactive = bean.getIsActive();
				if(bean.getIsActive().equalsIgnoreCase("NO")){
					isactive = "Y";
				}else {
					isactive = "N";
				}
				preparedStatement.setDouble(1, bean.getAmount());
				preparedStatement.setString(2, isactive);
				preparedStatement.setString(3, userName);
				preparedStatement.setInt(4, bean.getCompoAllocationId());
				preparedStatement.addBatch();
				
			}
			int[] count = preparedStatement.executeBatch(); 
			if(count.length>0){
				flag = true; 
			}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
