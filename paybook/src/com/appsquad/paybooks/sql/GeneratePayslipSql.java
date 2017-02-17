package com.appsquad.paybooks.sql;


public class GeneratePayslipSql {

	public static final String loadEmployee = "select eei.employee_info_id,eei.employee_code, eei.employee_name, "
			+ "eei.designation, eei.email_id, eei.date_of_joining, eei.account_no,eei.esi_number,"
			+ "eei.pf_number,eei.uan_number,eei.department "
											  + " from em_employee_info eei where company_id = ? and eei.is_delete = 'N' ";
	
	public static final String loadEmployeeSalaryComponents = "SELECT component_allocation_id, " +
															   " employee_info_id, " +
															   " salary_components_id, " +
															   " amount, " +
															   " e_or_d_id, " +
															   " component_name " + 
															   " from vw_employee_salary_components_details where employee_info_id = ? and is_delete = 'N' ";
	
}
