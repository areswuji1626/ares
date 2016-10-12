package com.wuji1626.framework.support.user.domain;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 用户信息
	 */
	private static final long serialVersionUID = 2235859569367716017L;

	//用户信息
	private String user_id;
	private String user_name;
	private String user_show_name;
	private String user_password;
	private String user_status;
	private String user_effect_start;
	private String user_effect_end;
	private String user_type;
	
	// 分页信息
	private long current_page;
	private int page_size;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_show_name() {
		return user_show_name;
	}
	public void setUser_show_name(String user_show_name) {
		this.user_show_name = user_show_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_status() {
		return user_status;
	}
	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}
	public String getUser_effect_start() {
		return user_effect_start;
	}
	public void setUser_effect_start(String user_effect_start) {
		this.user_effect_start = user_effect_start;
	}
	public String getUser_effect_end() {
		return user_effect_end;
	}
	public void setUser_effect_end(String user_effect_end) {
		this.user_effect_end = user_effect_end;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public long getCurrent_page() {
		return current_page;
	}
	public void setCurrent_page(long current_page) {
		this.current_page = current_page;
	}
	public int getPage_size() {
		return page_size;
	}
	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}
	
}
