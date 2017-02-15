package com.appsquad.paybooks.sql;

public class EmployeeMasterSql {

	public static final String employeeinsertsql = "insert into em_employee_info (employee_code, employee_name,"
			+ "designation,department,date_of_joining,account_no,esi_number,pf_number,"
			+ "uan_number,email_id,created_by,updated_by) values (?,?,?,?,?,?,?,?,?,?,?,?) ";
	
	public static final String loadEmployeeInfosql = "select * from em_employee_info; ";
	
	public static final String getEmployeeInfosql = "select * from em_employee_info where employee_info_id=?; ";
	
	public static final String updateEmployeeInfoSql = " UPDATE em_employee_info "
			   		+" SET  employee_code=?, employee_name=?, email_id=?, "
			   		+"     account_no=?, esi_number=?, pf_number=?, uan_number=?, department=?, "
			   		+"     designation=?, date_of_joining=?, "
			   		+"    updated_by=?, update_date=now() "
			   		+" WHERE employee_info_id=?";

}
