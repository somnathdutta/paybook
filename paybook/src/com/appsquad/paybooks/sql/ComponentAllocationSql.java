package com.appsquad.paybooks.sql;

public class ComponentAllocationSql {

	public static final String loadSalaryComponentresectiveemployee = "select salary_components_id, component_name,e_or_d_id from em_salary_component_master "
																	  + " where is_delete = 'N' and salary_components_id not in"
																	  + "(select salary_components_id from em_component_allocation where employee_info_id = ?) ";
	
	
	public static final String insertComponentwithEmp = "insert into em_component_allocation "
			+ "(employee_info_id, salary_components_id, amount, e_or_d_id,created_by, updated_by) values(?,?,?,?,?,?) ";
	
	
	public static final String loadAllocatedComponentListPerEmpSql = "SELECT eca.component_allocation_id, eca.salary_components_id, " + 
																	 " eca.amount, eca.is_delete, esc.component_name " +
																	 " FROM em_component_allocation eca, em_salary_component_master esc " + 
																	 " where eca.salary_components_id = esc.salary_components_id and eca.employee_info_id = ? ";
	
	
	public static final String updateExAllocatedComponentSql = "update em_component_allocation set amount = ?,is_delete = ?, updated_by = ? where component_allocation_id = ? "; 

	
}
