package com.wuji1626.framework.result;

import java.util.List;

public class Result<T> {

	private String status;
	private String errorCode;
	private String msg;
	private String errorStack;
	private List<T> resultSet;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorStack() {
		return errorStack;
	}
	public void setErrorStack(String errorStack) {
		this.errorStack = errorStack;
	}
	public List<T> getResultSet() {
		return resultSet;
	}
	public void setResultSet(List<T> resultSet) {
		this.resultSet = resultSet;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
