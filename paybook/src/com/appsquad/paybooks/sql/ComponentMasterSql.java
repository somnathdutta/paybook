package com.appsquad.paybooks.sql;

public class ComponentMasterSql {

	public static final String inserComponentSql = "insert into em_salary_component_master (component_name, created_by, updated_by) values (?,?,?) ";
	
	public static final String loadComponentSql = "select salary_components_id,component_name,is_delete from em_salary_component_master ";
	
	
}
