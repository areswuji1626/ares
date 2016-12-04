package com.wuji1626.ares.qti.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuji1626.framework.db.DatabaseContextHolder;
import com.wuji1626.framework.db.DynamicDataSourceAble;
import com.wuji1626.framework.db.DynamicDataSourceAble;

import com.wuji1626.ares.qti.domain.QuestionPart;
import com.wuji1626.ares.qti.service.QuestionPartService;
import com.wuji1626.ares.qti.dao.QuestionPartDao;

@Service("questionPartService")
@Transactional
public class QuestionPartServiceImpl implements QuestionPartService,DynamicDataSourceAble{

	@Autowired
	private QuestionPartDao questionPartDao;
	
	@Override
	public List<QuestionPart> getAllQuestionPart(){
		DatabaseContextHolder.setDBType(DATA_SOURCE_1);
		return questionPartDao.getAllQuestionPart();
	}
	@Override
	public QuestionPart getQuestionPartById(QuestionPart questionPart){
		DatabaseContextHolder.setDBType(DATA_SOURCE_1);
		return questionPartDao.getQuestionPartById(questionPart);
	}

	@Override
	public String insertQuestionPart(QuestionPart questionPart){
		DatabaseContextHolder.setDBType(DATA_SOURCE_1);
		return questionPartDao.insertQuestionPart(questionPart);
	}
	
	@Override
	public void deleteQuestionPart(QuestionPart questionPart){
		DatabaseContextHolder.setDBType(DATA_SOURCE_1);
		questionPartDao.deleteQuestionPart(questionPart);
	}
	
	@Override
	public void updateQuestionPart(QuestionPart questionPart){
		DatabaseContextHolder.setDBType(DATA_SOURCE_1);
		questionPartDao.updateQuestionPart(questionPart);
	}
}