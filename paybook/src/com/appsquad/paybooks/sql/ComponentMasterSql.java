package com.appsquad.paybooks.sql;

public class ComponentMasterSql {

	public static final String inserComponentSql = "insert into em_salary_component_master (component_name, e_or_d_id,"
					+ "company_id,created_by) values (?,?,?,?) ";
	
	public static final String loadComponentSql = "select salary_components_id,component_name,is_delete,e_or_d_id,"
						+ "(select component_type_name from pb_component_type_master where component_type_id = e_or_d_id) As type"
									+" from em_salary_component_master where company_id = ?";
	
	
}
