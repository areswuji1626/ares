package com.wuji1626.framework.result;

import java.util.List;

import com.wuji1626.framework.constant.CommonConstant;
import com.wuji1626.framework.utils.ValidateUtil;

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
	
	public void appendErrorCode(String errorCode){
		if(ValidateUtil.isBlank(this.errorCode)){
			this.errorCode = errorCode;
		}else{
			this.errorCode = this.errorCode + CommonConstant.CONNECTOR + errorCode;
		}
	}
	public void appendErrorMsg(String errorMsg){
		if(ValidateUtil.isBlank(this.msg)){
			this.msg = errorMsg;
		}else{
			this.msg = this.msg + CommonConstant.CONNECTOR + errorMsg;
		}
	}
}
