package com.wuji1626.framework.codegen.domain;

import java.io.Serializable;

public class FieldInfo implements Serializable{

	private static final long serialVersionUID = -2344885883947860575L;
	
	// java entity's field name
	private String field_name;
	// DB column's field name 
	private String column_name;
//	private String setter_getter_name;
	private String data_type;
	private String comments;
	public String getField_name() {
		return field_name;
	}
	public void setField_name(String field_name) {
		this.field_name = field_name;
	}
//	public String getSetter_getter_name() {
//		return setter_getter_name;
//	}
//	public void setSetter_getter_name(String setter_getter_name) {
//		this.setter_getter_name = setter_getter_name;
//	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	
}
