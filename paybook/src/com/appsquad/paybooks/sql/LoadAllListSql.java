package com.appsquad.paybooks.sql;

public class LoadAllListSql {

	public static final String loadEmployeeInfo = "select employee_info_id, employee_code, employee_name "
			+ "from em_employee_info where company_id = ? and is_delete = 'N' ";
	
	public static final String loadAEmpInfoBySerch = "select employee_info_id, employee_code, employee_name from em_employee_info where is_delete = 'N' and employee_name Like ?  ";
	
	public static final String loadMonths = "SELECT month_master_id, month_name FROM pb_month_master where is_delete = 'N' ";
	
	public static final String loadYears = "SELECT year_master_id, yr FROM pb_year_master where is_delete = 'N' ";
}
