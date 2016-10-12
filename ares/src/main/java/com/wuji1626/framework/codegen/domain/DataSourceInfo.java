package com.wuji1626.framework.codegen.domain;

import java.io.Serializable;

public class DataSourceInfo implements Serializable{

	private static final long serialVersionUID = -7091921536515341388L;
	
	private String ds_id;
	private String ds_type;
	private String ds_name;
	private String ds_schema;
	private String ds_url;
	private String ds_user;
	private String ds_password;
	private String ds_desc;
	
	private String create_time;
	private String modify_time;
	private String create_user_id;
	private String modify_user_id;
	private String create_user_name;
	private String modify_user_name;
	public String getDs_id() {
		return ds_id;
	}
	public void setDs_id(String ds_id) {
		this.ds_id = ds_id;
	}
	public String getDs_schema() {
		return ds_schema;
	}
	public void setDs_schema(String ds_schema) {
		this.ds_schema = ds_schema;
	}
	public String getDs_type() {
		return ds_type;
	}
	public void setDs_type(String ds_type) {
		this.ds_type = ds_type;
	}
	public String getDs_name() {
		return ds_name;
	}
	public void setDs_name(String ds_name) {
		this.ds_name = ds_name;
	}
	public String getDs_url() {
		return ds_url;
	}
	public void setDs_url(String ds_url) {
		this.ds_url = ds_url;
	}
	public String getDs_user() {
		return ds_user;
	}
	public void setDs_user(String ds_user) {
		this.ds_user = ds_user;
	}
	public String getDs_password() {
		return ds_password;
	}
	public void setDs_password(String ds_password) {
		this.ds_password = ds_password;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getModify_time() {
		return modify_time;
	}
	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}
	public String getCreate_user_id() {
		return create_user_id;
	}
	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}
	public String getModify_user_id() {
		return modify_user_id;
	}
	public void setModify_user_id(String modify_user_id) {
		this.modify_user_id = modify_user_id;
	}
	public String getCreate_user_name() {
		return create_user_name;
	}
	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}
	public String getModify_user_name() {
		return modify_user_name;
	}
	public void setModify_user_name(String modify_user_name) {
		this.modify_user_name = modify_user_name;
	}
	public String getDs_desc() {
		return ds_desc;
	}
	public void setDs_desc(String ds_desc) {
		this.ds_desc = ds_desc;
	}
	
}
