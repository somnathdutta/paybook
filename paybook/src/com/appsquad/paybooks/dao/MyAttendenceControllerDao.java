package com.appsquad.paybooks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.AttendenceBean;
import com.appsquad.paybooks.database.DatabaseHandler;
import com.appsquad.paybooks.database.Pbpstm;
import com.appsquad.paybooks.model.utils.PropertyFileAccess;

public class MyAttendenceControllerDao {

	public static void isAttendenceGiven(AttendenceBean attendenceBean){
		try {
			Connection connection = DatabaseHandler.createConnection();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				preparedStatement = Pbpstm.createQuery(connection, PropertyFileAccess.getPropertyObject().getPropValues("check_attendence", "sql.properties"), 
						Arrays.asList(attendenceBean.getEmpId()));
				resultSet= preparedStatement.executeQuery();
				if(resultSet.next()){
					int incount = resultSet.getInt("today_in");
					int outcount = resultSet.getInt("today_out");
					if(incount > 0){
						attendenceBean.setCheckInDisabled(true);
					}
					if(outcount > 0){
						attendenceBean.setCheckOutDisabled(true);
					}else{
						attendenceBean.setCheckOutDisabled(false);
					}
					if(incount >0 && outcount >0){
						attendenceBean.setCheckInDisabled(true);
						attendenceBean.setCheckOutDisabled(true);
					}
					if(incount == 0 && outcount == 0){
						attendenceBean.setCheckInDisabled(false);
						attendenceBean.setCheckOutDisabled(true);
					}
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
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void giveInAttendence(AttendenceBean attendenceBean){
		try {
			Connection connection = DatabaseHandler.createConnection();
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = Pbpstm.createQuery(connection, 
						PropertyFileAccess.getPropertyObject().getPropValues("give_in_attendence", "sql.properties"), 
						Arrays.asList(attendenceBean.getEmpId(),
								new java.sql.Date(attendenceBean.getAttendenceDate().getTime()),
								attendenceBean.getCompanyId()));
				int count = preparedStatement.executeUpdate();
				if(count >0 ){
					Messagebox.show("Check in time attendence given for today","Successful Attendence",Messagebox.OK,Messagebox.INFORMATION);
					attendenceBean.setCheckInDisabled(true);
					attendenceBean.setCheckOutDisabled(false);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				if(connection!=null){
					connection.close();
				}
				if(preparedStatement!=null){
					preparedStatement.close();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void giveOutAttendence(AttendenceBean attendenceBean){
		try {
			Connection connection = DatabaseHandler.createConnection();
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = Pbpstm.createQuery(connection, 
						PropertyFileAccess.getPropertyObject().getPropValues("give_out_attendence", "sql.properties"), 
						Arrays.asList(attendenceBean.getEmpId(),
								new java.sql.Date(attendenceBean.getAttendenceDate().getTime()),
								attendenceBean.getCompanyId()));
				int count = preparedStatement.executeUpdate();
				if(count >0 ){
					Messagebox.show("Check out time attendence given for today","Successful Attendence",Messagebox.OK,Messagebox.INFORMATION);
					attendenceBean.setCheckOutDisabled(true);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				if(connection!=null){
					connection.close();
				}
				if(preparedStatement!=null){
					preparedStatement.close();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static ArrayList<AttendenceBean> showMyAttendences(int empId,int monthId){
		ArrayList<AttendenceBean> attendenceBeanList = new ArrayList<AttendenceBean>();
		try {
			SQL:{
					Connection connection = DatabaseHandler.createConnection();
					PreparedStatement preparedStatement = null;
					ResultSet resultSet = null;
					try {
						preparedStatement = Pbpstm.createQuery(connection, 
								PropertyFileAccess.getPropertyObject().getPropValues("show_monthly_attendence", "sql.properties"), 
							    Arrays.asList(empId,monthId)	);
						resultSet = preparedStatement.executeQuery();
						while (resultSet.next()) {
							AttendenceBean attendenceBean = new AttendenceBean();
							attendenceBean.setAttendenceDate(resultSet.getDate("attendence_date"));
							attendenceBean.setCheckInTime(resultSet.getDate("emp_in_time"));
							attendenceBean.setChkInStr(resultSet.getString("emp_in_time"));
							attendenceBean.setChkOutStr(resultSet.getString("emp_out_time"));
							attendenceBean.setCheckOutTime(resultSet.getDate("emp_out_time"));
							attendenceBean.setStatus(resultSet.getString("status"));
							attendenceBeanList.add(attendenceBean);
						}
					} catch (Exception e) {
						// TODO: handle exception
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
		if(attendenceBeanList.size()==0){
			Messagebox.show("Oops no attendence found on the selected month!","No Information",Messagebox.OK,Messagebox.EXCLAMATION);
		}
		return attendenceBeanList;
	}
}
