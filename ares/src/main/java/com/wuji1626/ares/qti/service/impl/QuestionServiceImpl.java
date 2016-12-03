package com.wuji1626.ares.qti.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuji1626.framework.db.DatabaseContextHolder;
import com.wuji1626.framework.db.DynamicDataSourceAble;
import com.wuji1626.framework.db.DynamicDataSourceAble;

import com.wuji1626.ares.qti.domain.Question;
import com.wuji1626.ares.qti.service.QuestionService;
import com.wuji1626.ares.qti.dao.QuestionDao;

@Service("questionService")
@Transactional
public class QuestionServiceImpl implements QuestionService,DynamicDataSourceAble{

	@Autowired
	private QuestionDao questionDao;
	
	@Override
	public List<Question> getAllQuestion(){
		DatabaseContextHolder.setDBType(DATA_SOURCE_3);
		return questionDao.getAllQuestion();
	}
	@Override
	public Question getQuestionById(Question question){
		DatabaseContextHolder.setDBType(DATA_SOURCE_3);
		return questionDao.getQuestionById(question);
	}

	@Override
	public String insertQuestion(Question question){
		DatabaseContextHolder.setDBType(DATA_SOURCE_3);
		return questionDao.insertQuestion(question);
	}
	
	@Override
	public void deleteQuestion(Question question){
		DatabaseContextHolder.setDBType(DATA_SOURCE_3);
		questionDao.deleteQuestion(question);
	}
	
	@Override
	public void updateQuestion(Question question){
		DatabaseContextHolder.setDBType(DATA_SOURCE_3);
		questionDao.updateQuestion(question);
	}
}