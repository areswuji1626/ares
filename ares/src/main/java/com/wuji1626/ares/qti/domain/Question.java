package com.wuji1626.ares.qti.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Question implements Serializable {

	private static final long serialVersionUID = 1L;
	/** question id */
	private String questionId;
	/** question text */
	private String questionText;
	/** create date */
	@DateTimeFormat(pattern = "yyyy-MM-dd")  
	private Date createDate;
	/** modify date */
	@DateTimeFormat(pattern = "yyyy-MM-dd")  
	private Date modifyDate;
	/** create peson */
	private String createPesonId;
	/** modify person */
	private String modifyPersonId;
	/** delete flag: 00: effective, 01: delete */
	private String delFlg = "00";
	
	public String getQuestionId(){
		return questionId;
	}
	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}
	public String getQuestionText(){
		return questionText;
	}
	public void setQuestionText(String questionText){
		this.questionText = questionText;
	}
	public Date getCreateDate(){
		return createDate;
	}
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	public Date getModifyDate(){
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate){
		this.modifyDate = modifyDate;
	}
	public String getCreatePesonId(){
		return createPesonId;
	}
	public void setCreatePesonId(String createPesonId){
		this.createPesonId = createPesonId;
	}
	public String getModifyPersonId(){
		return modifyPersonId;
	}
	public void setModifyPersonId(String modifyPersonId){
		this.modifyPersonId = modifyPersonId;
	}
	public String getDelFlg(){
		return delFlg;
	}
	public void setDelFlg(String delFlg){
		this.delFlg = delFlg;
	}
}