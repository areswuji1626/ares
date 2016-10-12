package com.wuji1626.framework.codegen.domain;

import java.io.Serializable;

public class ColumnInfo implements Serializable {

	private static final long serialVersionUID = 6353871783462237406L;
	
	private String table_name;
	private String column_name;
	private String data_type;
	private long data_length;
	private String comments;
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public long getData_length() {
		return data_length;
	}
	public void setData_length(long data_length) {
		this.data_length = data_length;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
