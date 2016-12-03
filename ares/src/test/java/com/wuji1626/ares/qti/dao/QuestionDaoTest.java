package com.wuji1626.ares.qti.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wuji1626.ares.qti.dao.QuestionDao;
import com.wuji1626.ares.qti.domain.Question;
import com.wuji1626.framework.db.DatabaseContextHolder;
import com.wuji1626.framework.db.DynamicDataSourceAble;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:**/applicationContext.xml","classpath*:**/dispatcher-servlet.xml"})
public class QuestionDaoTest {

	@Autowired
	private QuestionDao dao;
	
	@Test
	public void insertQuestionTest(){
		Question question = new Question();
		question.setQuestionText("test question input");
		question.setDelFlg("00");
		String originalDBType = DatabaseContextHolder.getDBType();
		DatabaseContextHolder.setDBType(DynamicDataSourceAble.DATA_SOURCE_3);
		String uuid = dao.insertQuestion(question);
		DatabaseContextHolder.setDBType(originalDBType);
		Assert.assertEquals(uuid.length(),36);
	}
	@Test
	public void getQuestionByIdTest(){
		Question question = new Question();
		question.setQuestionId("6b27502c-aaba-11e6-a458-00ff794963ca");
		String originalDBType = DatabaseContextHolder.getDBType();
		DatabaseContextHolder.setDBType(DynamicDataSourceAble.DATA_SOURCE_3);
		question = dao.getQuestionById(question);
		DatabaseContextHolder.setDBType(originalDBType);
		Assert.assertEquals(question.getDelFlg(),"00");
	}
	@Test
	public void deleteQuestionTest(){
		Question question = new Question();
		question.setQuestionId("6b27502c-aaba-11e6-a458-00ff794963ca");
		DatabaseContextHolder.setDBType(DynamicDataSourceAble.DATA_SOURCE_3);
		dao.deleteQuestion(question);
		Assert.assertEquals(null, dao.getQuestionById(question));
	}
	@Test
	public void updateQuestionTest(){
		Question question = new Question();
		question.setQuestionId("55614d35-b270-11e6-9dce-00ff794963ca");
		question.setQuestionText("this is a test about update");
		DatabaseContextHolder.setDBType(DynamicDataSourceAble.DATA_SOURCE_3);
		dao.updateQuestion(question);
		Assert.assertEquals("this is a test about update", dao.getQuestionById(question).getQuestionText());
	}
}
