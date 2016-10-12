package com.wuji1626.framework.support.code.domain;

import java.io.Serializable;

public class Code implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String code_id;
	private String class_key;
	private String class_name;
	private String code_name;
	private String code_desc;
	private String code_key;
	
	private String create_time;
	private String modify_time;
	private String create_user_id;
	private String modify_user_id;
	private String create_user_name;
	private String modify_user_name;
	public String getCode_id() {
		return code_id;
	}
	public void setCode_id(String code_id) {
		this.code_id = code_id;
	}
	public String getClass_key() {
		return class_key;
	}
	public void setClass_key(String class_key) {
		this.class_key = class_key;
	}
	public String getCode_name() {
		return code_name;
	}
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}
	public String getCode_desc() {
		return code_desc;
	}
	public void setCode_desc(String code_desc) {
		this.code_desc = code_desc;
	}
	public String getCode_key() {
		return code_key;
	}
	public void setCode_key(String code_key) {
		this.code_key = code_key;
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
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
}
