package com.wuji1626.ares.qti.dao.impl;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wuji1626.ares.qti.domain.Question;
import com.wuji1626.ares.qti.dao.QuestionDao;

@Repository
public class QuestionDaoImpl implements QuestionDao{

	public static final String GET_ALL_QUESTION = "getAllQuestion";
	public static final String GET_QUESTION_BY_ID = "getQuestionById";
	public static final String INSERT_QUESTION = "insertQuestion";
	public static final String DELETE_QUESTION = "deleteQuestion";
	public static final String UPDATE_QUESTION = "updateQuestion";

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<Question> getAllQuestion(){
		return sqlSessionTemplate.selectList(GET_ALL_QUESTION);
	}
	@Override
	public Question getQuestionById(Question question){
		return sqlSessionTemplate.selectOne(GET_QUESTION_BY_ID, question);
	}

	@Override
	public String insertQuestion(Question question){
		sqlSessionTemplate.insert(INSERT_QUESTION, question);
		return question.getQuestionId();
	}
	@Override
	public void deleteQuestion(Question question){
		sqlSessionTemplate.update(DELETE_QUESTION, question);
	}
	
	@Override
	public void updateQuestion(Question question){
		sqlSessionTemplate.update(UPDATE_QUESTION, question);
	}
}