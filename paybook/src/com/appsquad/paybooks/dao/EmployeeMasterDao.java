package com.appsquad.paybooks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import org.zkoss.zul.Messagebox;

import com.appsquad.paybooks.bean.CompanyBean;
import com.appsquad.paybooks.bean.EmployeeMasterBean;
import com.appsquad.paybooks.bean.GeneratePayslipBean;
import com.appsquad.paybooks.database.DatabaseHandler;
import com.appsquad.paybooks.database.Pbpstm;
import com.appsquad.paybooks.model.utils.Dateformatter;
import com.appsquad.paybooks.model.utils.PropertyFileAccess;
import com.appsquad.paybooks.sql.EmployeeMasterSql;

public class EmployeeMasterDao {

	public static int saveEmployeeInfo(String userName, EmployeeMasterBean bean){
		int id =0;
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = DatabaseHandler.createConnection();
				connection.setAutoCommit(false);
				preparedStatement = connection.prepareStatement(PropertyFileAccess.getPropertyObject().getPropValues("employeeinsertsql", "sql.properties"), 
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, bean.getCompanyId());
				preparedStatement.setString(2, bean.getEmployeeCode().trim());
				preparedStatement.setString(3, bean.getPassword().trim()); 
				preparedStatement.setString(4, bean.getEmployeeName().trim()); 
				preparedStatement.setString(5, bean.getDesignation());
				preparedStatement.setString(6,bean.getDepartment());
				preparedStatement.setDate(7,bean.getDojSql());
				preparedStatement.setString(8, bean.getBankAcNo());
				preparedStatement.setString(9,bean.getEsiNumber());
				preparedStatement.setString(10,bean.getPfNumber());
				preparedStatement.setString(11, bean.getUanNumber());
				preparedStatement.setString(12,bean.getEmailID().trim());
				preparedStatement.setString(13,userName);
				preparedStatement.setString(14,userName);
				preparedStatement.execute();
				connection.commit();
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					id = resultSet.getInt(1);
					bean.setEmployeeId(id);
				}
				if(id>0){
					saveUser(bean, 5);//5 means employee
				}else{
					connection.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
				if(e.getMessage().startsWith("ERROR: duplicate key")){
					Messagebox.show("["+bean.getEmployeeCode()+"] Already Exist" , "Error", Messagebox.OK, Messagebox.ERROR);
				}else {
					Messagebox.show(e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
				}
				connection.rollback();
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
	
	public static ArrayList<EmployeeMasterBean> loadEmployeeInfo(int companyId){
		int count = 0;	
		ArrayList<EmployeeMasterBean> list = new ArrayList<EmployeeMasterBean>();
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, PropertyFileAccess.getPropertyObject().getPropValues("load_employeeList", "sql.properties"),
						Arrays.asList(companyId));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					EmployeeMasterBean bean = new EmployeeMasterBean();
					count = count+1;
					bean.setSlNo(count);
					bean.setEmployeeId(resultSet.getInt("employee_info_id"));
					bean.setEmployeeCode(resultSet.getString("employee_code"));
					bean.setEmployeeName(resultSet.getString("employee_name"));
					bean.setBankAcNo(resultSet.getString("account_no"));
					bean.setDepartment(resultSet.getString("department"));
					bean.setDesignation(resultSet.getString("designation"));
					bean.setDojSql(resultSet.getDate("date_of_joining"));
					bean.setLeaveDate(resultSet.getDate("date_of_leaving"));
					bean.setEmailID(resultSet.getString("email_id"));
					bean.setEsiNumber(resultSet.getString("esi_number"));
					bean.setPfNumber(resultSet.getString("pf_number"));
					bean.setUanNumber(resultSet.getString("uan_number"));
					if(resultSet.getString("is_leaved").equalsIgnoreCase("N")){
						bean.setCurentEmployee("YES");
					}else {
						bean.setCurentEmployee("NO");
					}
					if(resultSet.getString("is_delete").equalsIgnoreCase("N")){
						bean.setCurentEmployee("YES");
					}else {
						bean.setCurentEmployee("NO");
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
		if(list.size()==0)
			Messagebox.show("Currently no employee found!","No data found",Messagebox.OK,Messagebox.EXCLAMATION);
		return list;
	}
	
	public static int updateEmployeeInfo(EmployeeMasterBean employee,String user){
		int count = 0;
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, EmployeeMasterSql.updateEmployeeInfoSql, Arrays.asList
						(employee.getEmployeeCode(),employee.getEmployeeName(),
								employee.getEmailID(),employee.getBankAcNo(),
								employee.getEsiNumber(),employee.getPfNumber(),
								employee.getUanNumber(),employee.getDepartment(),
								employee.getDesignation(),employee.getDojSql(),
								employee.getPassword(),
								user,employee.getEmployeeId()));
				count = preparedStatement.executeUpdate();
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
		if(count>0){
			Messagebox.show("Employee information updated successfully.","Update information",Messagebox.OK,Messagebox.INFORMATION);
			if(employee!=null){
				MyProfileDao.updateEmployeePassword(employee);
			}
		}
		return count;
	}
	
	public static boolean saveUser(EmployeeMasterBean employee, int rollId){
		boolean saved = false;
		try {
			SQL:{
					Connection connection = DatabaseHandler.createConnection();
					PreparedStatement preparedStatement = null;
					try {
						preparedStatement = Pbpstm.createQuery(connection, PropertyFileAccess.getPropertyObject().getPropValues("create_user", "sql.properties"), 
								Arrays.asList(employee.getEmployeeCode().trim(),employee.getEmployeeName().trim(),
										employee.getPassword().trim(),employee.getEmployeeId(),rollId));
						int rowCount = preparedStatement.executeUpdate();
						if(rowCount>0){
							System.out.println("Employee created!");
							saved = true;
						}
					}  catch (Exception e) {
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
		return saved;
	}
	
	public static GeneratePayslipBean getEmployeeInfo(int empId){
		GeneratePayslipBean bean = null;
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DatabaseHandler.createConnection();
				preparedStatement = Pbpstm.createQuery(connection, PropertyFileAccess.getPropertyObject().getPropValues("get_employee", "sql.properties"),
						Arrays.asList(empId));
				System.out.println(preparedStatement);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					bean = new GeneratePayslipBean();
					bean.setEmployeeId(resultSet.getInt("employee_info_id"));
					bean.setCompanyId(resultSet.getInt("company_id"));
					bean.setEmployeeCode(resultSet.getString("employee_code"));
					bean.setEmployeeName(resultSet.getString("employee_name"));
					bean.setAccNo(resultSet.getString("account_no"));
					bean.setDepartment(resultSet.getString("department"));
					bean.setDesignation(resultSet.getString("designation"));
					bean.setDojSql(resultSet.getDate("date_of_joining"));
					bean.setEmailId(resultSet.getString("email_id"));
					bean.setEsi(resultSet.getString("esi_number"));
					bean.setPf(resultSet.getString("pf_number"));
					bean.setUan(resultSet.getString("uan_number"));
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

					bean.setComponentList(GeneratePayslipDao.loadComponents(connection, bean.getEmployeeId(), bean));
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
		return bean;
	}
	
	public static int getNoOfDayPresent(int monthId,int empId){
		int workedDays = 0;
		try {
			SQL:{
					Connection connection = DatabaseHandler.createConnection();
					PreparedStatement preparedStatement = null;
					ResultSet resultSet = null;
					try {
						preparedStatement = Pbpstm.createQuery(connection,
								PropertyFileAccess.getPropertyObject().getPropValues("get_present_day", "sql.properties"), 
								Arrays.asList(monthId,empId));
						resultSet = preparedStatement.executeQuery();
						if(resultSet.next()){
							workedDays = resultSet.getInt("worked_days");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						if(preparedStatement != null){
							preparedStatement.close();
						}
						if(connection != null){
							connection.close();
						}
					}	
				}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return workedDays;
	}
}
