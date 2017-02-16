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
import com.appsquad.paybooks.model.utils.PropertyFileAccess;
import com.appsquad.paybooks.sql.ComponentMasterSql;


public class ComponentMasterDao {

	public static int saveComponents(String userName, ComponentMasterBean bean){
		int id =0;
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, ComponentMasterSql.inserComponentSql, 
						Arrays.asList(bean.getComponent().trim(),bean.getComponentTypeId(),userName));
				id = preparedStatement.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
				if(e.getMessage().startsWith("E")){
					Messagebox.show("["+bean.getComponent()+"] Already Exist" , "Error", Messagebox.OK, Messagebox.ERROR);
				}else {
					Messagebox.show(e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
				}
				
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
		return id;
	}
	
	public static ArrayList<ComponentMasterBean> loadComponents(){
		int count = 0;
		ArrayList<ComponentMasterBean> list = new ArrayList<ComponentMasterBean>();
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, ComponentMasterSql.loadComponentSql, null);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					ComponentMasterBean bean = new ComponentMasterBean();
					count = count+1;
					bean.setSlNo(count);
					bean.setComponentId(resultSet.getInt("salary_components_id"));
					bean.setComponent(resultSet.getString("component_name"));
					bean.setComponentTypeId(resultSet.getInt("e_or_d_id"));
					bean.setComponentType(resultSet.getString("type"));
					if(resultSet.getString("is_delete").equalsIgnoreCase("N")){
						bean.setIsActive("YES");
					}else {
						bean.setIsActive("NO");
					}
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
	
	public static ArrayList<ComponentMasterBean> loadComponentTypes(){
		ArrayList<ComponentMasterBean> list = new ArrayList<ComponentMasterBean>();
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, PropertyFileAccess.getPropertyObject().getPropValues("load_component_types", "sql.properties"), null);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					ComponentMasterBean bean = new ComponentMasterBean();
					bean.setComponentTypeId(resultSet.getInt("component_type_id"));
					bean.setComponentType(resultSet.getString("component_type_name"));
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
