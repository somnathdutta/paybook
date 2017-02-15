package com.appsquad.paybooks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.CompanyBean;
import com.appsquad.paybooks.database.DatabaseHandler;
import com.appsquad.paybooks.database.Pbpstm;
import com.appsquad.paybooks.model.utils.PropertyFileAccess;

public class CompanyDao {

	public static ArrayList<CompanyBean> loadSavedCompanyList(){
		ArrayList<CompanyBean> companyBeanList = new ArrayList<CompanyBean>();
		try {
			SQL:{
					Connection connection = DatabaseHandler.createConnection();
					PreparedStatement preparedStatement = null;
					ResultSet resultSet = null;
					try {
						preparedStatement = Pbpstm.createQuery(connection, 
								PropertyFileAccess.getPropertyObject().getPropValues("load_companyList", "sql.properties"), null);
						System.out.println(preparedStatement);
						resultSet = preparedStatement.executeQuery();
						while (resultSet.next()) {
							CompanyBean companyBean = new CompanyBean();
							companyBean.setCompanyId(resultSet.getInt("company_info_id"));
							companyBean.setCompanyName(resultSet.getString("company"));
							companyBean.setAddress(resultSet.getString("address"));
							companyBean.setWorkLocation(resultSet.getString("work_location"));
							companyBeanList.add(companyBean);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						if(connection!=null){
							connection.close();
						}
					}
				}
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(companyBeanList.size());
		return companyBeanList;
	}
	
	public static int saveCompany(CompanyBean companyBean,String user){
		int count = 0;
		try {
			SQL:{
					Connection connection = DatabaseHandler.createConnection();
					PreparedStatement preparedStatement = null;
					try {
						preparedStatement = Pbpstm.createQuery(connection, 
								PropertyFileAccess.getPropertyObject().getPropValues("save_company", "sql.properties"), 
								Arrays.asList(companyBean.getCompanyName(),companyBean.getAddress(),
										companyBean.getWorkLocation(),user));
						count = preparedStatement.executeUpdate();
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
				}
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(count>0){
			Messagebox.show("Company saved successfully!","Saved successfully",Messagebox.OK,Messagebox.INFORMATION);
		}
		return count;
	}
	
	public static int updateCompany(CompanyBean companyBean,String user){
		int count = 0;
		try {
			SQL:{
					Connection connection = DatabaseHandler.createConnection();
					PreparedStatement preparedStatement = null;
					try {
						preparedStatement = Pbpstm.createQuery(connection, 
								PropertyFileAccess.getPropertyObject().getPropValues("update_company", "sql.properties"), 
								Arrays.asList(companyBean.getCompanyName(),companyBean.getAddress(),
										companyBean.getWorkLocation(),user,companyBean.getCompanyId()));
						count = preparedStatement.executeUpdate();
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
				}
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(count>0){
			Messagebox.show("Company updated successfully!","Saved successfully",Messagebox.OK,Messagebox.INFORMATION);
		}
		return count;
	}
}
