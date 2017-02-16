package com.appsquad.paybooks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.appsquad.paybooks.bean.StatusBean;
import com.appsquad.paybooks.database.DatabaseHandler;
import com.appsquad.paybooks.database.Pbpstm;
import com.appsquad.paybooks.model.utils.PropertyFileAccess;

public class StatusDao {

	public static ArrayList<StatusBean> getStatusList(){
		ArrayList<StatusBean> statusList = new ArrayList<StatusBean>();
		try {
			SQL:{
					Connection connection = DatabaseHandler.createConnection();
					PreparedStatement preparedStatement = null;
					ResultSet resultSet = null;
					try {
						preparedStatement = Pbpstm.createQuery(connection, 
								PropertyFileAccess.getPropertyObject().getPropValues("load_status", "sql.properties"), null);
						resultSet = preparedStatement.executeQuery();
						while (resultSet.next()) {
							StatusBean statusBean = new StatusBean();
							statusBean.setStatus(resultSet.getString("status"));
							statusBean.setStatusId(resultSet.getInt("status_id"));
							statusList.add(statusBean);
						}
					} catch (Exception e) {
						// TODO: handle exception
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
		return statusList;
	}
}
