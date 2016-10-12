package com.wuji1626.framework.support.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuji1626.framework.support.code.dao.CodeClassDao;
import com.wuji1626.framework.support.code.domain.CodeClass;
import com.wuji1626.framework.support.code.service.CodeClassService;

@Service("codeclassService")
@Transactional
public class CodeClassServiceImpl implements CodeClassService{

	@Autowired
	private CodeClassDao dao;
	@Override
	public String insertCodeClass(CodeClass cls) {
		// TODO Auto-generated method stub
		return dao.insertCodeClass(cls);
	}
	@Override
	public List<CodeClass> queryAll() {
		// TODO Auto-generated method stub
		return dao.queryAllCodeClass();
	}
	@Override
	public int deleteCodeClass(String class_id) {
		// TODO Auto-generated method stub
		
		return dao.deleteCodeClass(class_id);
	}

}
