package com.wuji1626.framework.support.code.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wuji1626.framework.support.code.dao.CodeDao;
import com.wuji1626.framework.support.code.domain.Code;

@Repository
public class CodeDaoImpl implements CodeDao {
	
	private static final String NEW_CODE = "insertCode";
	private static final String QUERY_ALL_CODE = "queryAllCodes";
	private static final String DEL_CODE = "deleteCode";
	private static final String QUERY_CODE_BY_ID = "queryCodeById";
	private static final String UPDATE_CODE = "updateCode";
	private static final String QUERY_CODE_BY_CLASS = "queryCodeByClass";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public String insertCode(Code code) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.insert(NEW_CODE, code);
		return code.getCode_id();
	}

	@Override
	public List<Code> queryAllCode() {
		// TODO Auto-generated method stub
		List<Code> res = sqlSessionTemplate.selectList(QUERY_ALL_CODE);
		return res;
	}

	@Override
	public int deleteCode(String code_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.delete(DEL_CODE, code_id);
	}

	@Override
	public Code queryCodeById(String code_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(QUERY_CODE_BY_ID,code_id);
	}

	@Override
	public int updateCode(Code code) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update(UPDATE_CODE,code);
	}
	
	@Override
	public List<Code> queryCodeByClass(String class_type){
		return sqlSessionTemplate.selectList(QUERY_CODE_BY_CLASS, class_type);
	}
	
}
