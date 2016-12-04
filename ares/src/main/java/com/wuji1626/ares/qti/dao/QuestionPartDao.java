package com.wuji1626.ares.qti.dao;

import java.util.List;
import com.wuji1626.ares.qti.domain.QuestionPart;

public interface QuestionPartDao{
	public List<QuestionPart> getAllQuestionPart();
	public QuestionPart getQuestionPartById(QuestionPart questionPart);
	public String insertQuestionPart(QuestionPart questionPart);
	public void deleteQuestionPart(QuestionPart questionPart);
	
	public void updateQuestionPart(QuestionPart questionPart);
}
