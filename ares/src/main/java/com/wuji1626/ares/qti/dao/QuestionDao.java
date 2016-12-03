package com.wuji1626.ares.qti.dao;

import java.util.List;
import com.wuji1626.ares.qti.domain.Question;

public interface QuestionDao{
	public List<Question> getAllQuestion();
	public Question getQuestionById(Question question);
	public String insertQuestion(Question question);
	public void deleteQuestion(Question question);
	
	public void updateQuestion(Question question);
}
