package com.wuji1626.ares.qti.dao.impl;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wuji1626.ares.qti.domain.QuestionPart;
import com.wuji1626.ares.qti.dao.QuestionPartDao;

@Repository
public class QuestionPartDaoImpl implements QuestionPartDao{

	public static final String GET_ALL_QUESTIONPART = "getAllQuestionPart";
	public static final String GET_QUESTIONPART_BY_ID = "getQuestionPartById";
	public static final String INSERT_QUESTIONPART = "insertQuestionPart";
	public static final String DELETE_QUESTIONPART = "deleteQuestionPart";
	public static final String UPDATE_QUESTIONPART = "updateQuestionPart";

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<QuestionPart> getAllQuestionPart(){
		return sqlSessionTemplate.selectList(GET_ALL_QUESTIONPART);
	}
	@Override
	public QuestionPart getQuestionPartById(QuestionPart questionPart){
		return sqlSessionTemplate.selectOne(GET_QUESTIONPART_BY_ID, questionPart);
	}

	@Override
	public String insertQuestionPart(QuestionPart questionPart){
		sqlSessionTemplate.insert(INSERT_QUESTIONPART, questionPart);
		return questionPart.getQuestionPartId();
	}
	@Override
	public void deleteQuestionPart(QuestionPart questionPart){
		sqlSessionTemplate.update(DELETE_QUESTIONPART, questionPart);
	}
	
	@Override
	public void updateQuestionPart(QuestionPart questionPart){
		sqlSessionTemplate.update(UPDATE_QUESTIONPART, questionPart);
	}
}