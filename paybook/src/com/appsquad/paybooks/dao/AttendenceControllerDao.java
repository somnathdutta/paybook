package com.appsquad.paybooks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.AttendenceBean;
import com.appsquad.paybooks.bean.StatusBean;
import com.appsquad.paybooks.database.DatabaseHandler;
import com.appsquad.paybooks.database.Pbpstm;
import com.appsquad.paybooks.model.utils.PropertyFileAccess;

public class AttendenceControllerDao {

	public static ArrayList<AttendenceBean> getTodayAttendence(int companyId){
		ArrayList<AttendenceBean> attendenceBeanList = new ArrayList<AttendenceBean>();
		try {
			SQL:{
					Connection connection = DatabaseHandler.createConnection();
					PreparedStatement preparedStatement = null;
					ResultSet resultSet =  null;
					try {
						preparedStatement = Pbpstm.createQuery(connection, 
								PropertyFileAccess.getPropertyObject().getPropValues("show_today_emp_attendence", "sql.properties"),
								Arrays.asList(companyId));
						resultSet = preparedStatement.executeQuery();
						while (resultSet.next()) {
							AttendenceBean attendenceBean = new AttendenceBean();
							attendenceBean.setAttendenceDate(resultSet.getDate("attendence_date"));
							attendenceBean.setEmpId(resultSet.getInt("emp_id"));
							attendenceBean.setEmpName(resultSet.getString("employee_name"));
							attendenceBean.setCheckInTime(resultSet.getDate("emp_in_time"));
							attendenceBean.setChkInStr(resultSet.getString("emp_in_time"));
							attendenceBean.setChkOutStr(resultSet.getString("emp_out_time"));
							attendenceBean.setCheckOutTime(resultSet.getDate("emp_out_time"));
							
							String status = resultSet.getString("status");
							int statusId = resultSet.getInt("attendence_status_id");
							ArrayList<StatusBean> statusBeans = new ArrayList<StatusBean>();
							StatusBean statusBean = new StatusBean();
							statusBean.setStatus(status);
							statusBean.setStatusId(statusId);
							statusBeans.add(statusBean);
							attendenceBean.setStatus(status);
							attendenceBean.setStatusBeanList(statusBeans);
							attendenceBeanList.add(attendenceBean);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						if(connection!=null){
							connection.close();
						}
						if(preparedStatement!=null){
							preparedStatement.close();
						}
					}
				}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return attendenceBeanList;
	}
	
	public static int updateAttendence(ArrayList<AttendenceBean> attendenceBeanList, int companyId){
		int rows = 0;
		try {
			SQL:{
					Connection connection = DatabaseHandler.createConnection();
					PreparedStatement preparedStatement = null;
					try {
						preparedStatement = connection.prepareStatement(PropertyFileAccess.getPropertyObject().getPropValues("update_attendence", "sql.properties"));
						for(AttendenceBean attendenceBean  : attendenceBeanList){
							if(attendenceBean.isChecked()){
								preparedStatement.setInt(1, attendenceBean.getStatusId());
								preparedStatement.setInt(2, attendenceBean.getEmpId());
								preparedStatement.setInt(3, companyId);
								preparedStatement.addBatch();
							}
							int count[] = preparedStatement.executeBatch();
							
							rows = count.length; 
						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						if(connection!=null){
							connection.close();
						}
						if(preparedStatement!=null){
							preparedStatement.close();
						}
					}
				}
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(rows > 0){
			Messagebox.show("Attendence updated successfully!","Updated",Messagebox.OK,Messagebox.INFORMATION);
		}
		return rows ; 
	}
}
