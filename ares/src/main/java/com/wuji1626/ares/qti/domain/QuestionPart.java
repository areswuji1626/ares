package com.wuji1626.ares.qti.domain;

import java.io.Serializable;
import java.util.Date;

public class QuestionPart implements Serializable {
	/** question part uuid */
	private String questionPartId;
	/** question id (FK) */
	private String questionId;
	/**  */
	private String questionPartTitle;
	/** question type. 000:MultiVale */
	private String questionType;
	/** question presentation format. 000:ListSelection */
	private String questionPstForm;
	/** question presentation subformat. 000:RadioButton */
	private String questionPstSubform;
	/** subject area. 000:English */
	private String subjectArea;
	/**  */
	private String questionPlayer;
	/**  */
	private String fileUsedByPlayer;
	/**  */
	private String targetLabelText;
	/**  */
	private String targetAnswerText;
	/** delete flag: 00: effective, 01: delete */
	private Date createDate;
	/**  */
	private Date modifyDate;
	/** create peson */
	private String createPesonId;
	/** modify person */
	private String modifyPersonId;
	/**  */
	private String delFlg;
	
	public String getQuestionPartId(){
		return questionPartId;
	}
	public void setQuestionPartId(String questionPartId){
		this.questionPartId = questionPartId;
	}
	public String getQuestionId(){
		return questionId;
	}
	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}
	public String getQuestionPartTitle(){
		return questionPartTitle;
	}
	public void setQuestionPartTitle(String questionPartTitle){
		this.questionPartTitle = questionPartTitle;
	}
	public String getQuestionType(){
		return questionType;
	}
	public void setQuestionType(String questionType){
		this.questionType = questionType;
	}
	public String getQuestionPstForm(){
		return questionPstForm;
	}
	public void setQuestionPstForm(String questionPstForm){
		this.questionPstForm = questionPstForm;
	}
	public String getQuestionPstSubform(){
		return questionPstSubform;
	}
	public void setQuestionPstSubform(String questionPstSubform){
		this.questionPstSubform = questionPstSubform;
	}
	public String getSubjectArea(){
		return subjectArea;
	}
	public void setSubjectArea(String subjectArea){
		this.subjectArea = subjectArea;
	}
	public String getQuestionPlayer(){
		return questionPlayer;
	}
	public void setQuestionPlayer(String questionPlayer){
		this.questionPlayer = questionPlayer;
	}
	public String getFileUsedByPlayer(){
		return fileUsedByPlayer;
	}
	public void setFileUsedByPlayer(String fileUsedByPlayer){
		this.fileUsedByPlayer = fileUsedByPlayer;
	}
	public String getTargetLabelText(){
		return targetLabelText;
	}
	public void setTargetLabelText(String targetLabelText){
		this.targetLabelText = targetLabelText;
	}
	public String getTargetAnswerText(){
		return targetAnswerText;
	}
	public void setTargetAnswerText(String targetAnswerText){
		this.targetAnswerText = targetAnswerText;
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