package com.wuji1626.framework.support.job.domain;

import java.io.Serializable;

public class Dept implements Serializable {

	private static final long serialVersionUID = -7353822090814083830L;

	private String dept_id;
	private String parent_dept_id;
	private String dept_name;
	private String dept_desc;
	private String dept_status;
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	public String getParent_dept_id() {
		return parent_dept_id;
	}
	public void setParent_dept_id(String parent_dept_id) {
		this.parent_dept_id = parent_dept_id;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getDept_desc() {
		return dept_desc;
	}
	public void setDept_desc(String dept_desc) {
		this.dept_desc = dept_desc;
	}
	public String getDept_status() {
		return dept_status;
	}
	public void setDept_status(String dept_status) {
		this.dept_status = dept_status;
	}
	
}
