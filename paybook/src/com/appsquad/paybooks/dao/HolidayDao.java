package com.appsquad.paybooks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.HolidayBean;
import com.appsquad.paybooks.database.DatabaseHandler;
import com.appsquad.paybooks.database.Pbpstm;
import com.appsquad.paybooks.model.utils.PropertyFileAccess;

public class HolidayDao {

	public static int saveHoliday(LinkedHashSet<HolidayBean> holidayBeanList,String user){
		int count = 0;
		try {
			SQL:{
					Connection connection = DatabaseHandler.createConnection();
					PreparedStatement preparedStatement = null;
					try {
						preparedStatement = connection.prepareStatement(PropertyFileAccess.getPropertyObject().getPropValues("save_holiday", "sql.properties"));
								
						for(HolidayBean holidayBean : holidayBeanList){
							preparedStatement.setDate(1, new java.sql.Date(holidayBean.getDateOfYear().getTime()) );
							preparedStatement.setString(2, holidayBean.getNameOfHoliday());
							preparedStatement.setString(3, holidayBean.getHolidayType());
							preparedStatement.setInt(4, holidayBean.getYear());
							preparedStatement.setString(5, user);
							preparedStatement.addBatch();
						}	
						int rows[] = preparedStatement.executeBatch();
						count = rows.length;
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
		if(count > 0){
			Messagebox.show("Holiday List saved successfully.","Holiday Saved",Messagebox.OK,Messagebox.INFORMATION);
		}
		return count;
	}
	
	public static LinkedHashSet<HolidayBean> loadSavedHolidayList(String year){
		LinkedHashSet<HolidayBean> holidayBeanList = new LinkedHashSet<HolidayBean>();
		try {
			SQL:{
					Connection connection = DatabaseHandler.createConnection();
					PreparedStatement preparedStatement = null;
					ResultSet resultSet = null;
					try {
						preparedStatement = Pbpstm.createQuery(connection, PropertyFileAccess.getPropertyObject().getPropValues("load_holiday", "sql.properties"), 
								Arrays.asList(Integer.parseInt(year)));
						resultSet = preparedStatement.executeQuery();
						while (resultSet.next()) {
							HolidayBean holiday = new HolidayBean();
							holiday.setHolidayId(resultSet.getInt("holiday_id"));
							holiday.setDateOfYear(resultSet.getDate("holiday_date"));
							holiday.setNameOfHoliday(resultSet.getString("holiday_name"));
							holidayBeanList.add(holiday);
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
				}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return holidayBeanList;
	}
}
