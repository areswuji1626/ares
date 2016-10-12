package com.wuji1626.framework.support.code.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wuji1626.framework.support.code.dao.CodeClassDao;
import com.wuji1626.framework.support.code.domain.CodeClass;

@Repository
public class CodeClassDaoImpl implements CodeClassDao {

	private static final String NEW_CODECLASS = "insertCodeClass";
	private static final String QUERY_ALL_CODECLASS = "queryAllCodeClass";
	private static final String DEL_CODECLASS = "deleteCodeClass";
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public String insertCodeClass(CodeClass cls) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.insert(NEW_CODECLASS, cls);
		return cls.getClass_id();
	}

	@Override
	public List<CodeClass> queryAllCodeClass() {
		// TODO Auto-generated method stub
		List<CodeClass> res = sqlSessionTemplate.selectList(QUERY_ALL_CODECLASS);
		return res;
	}

	@Override
	public int deleteCodeClass(String class_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.delete(DEL_CODECLASS, class_id);
	}

}
