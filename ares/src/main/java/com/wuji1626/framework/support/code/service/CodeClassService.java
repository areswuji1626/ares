package com.wuji1626.framework.support.code.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuji1626.framework.support.code.dao.CodeDao;
import com.wuji1626.framework.support.code.domain.CodeClass;

@Service("codeclassService")
@Transactional
public interface CodeClassService {
	
	public String insertCodeClass(CodeClass cls);
	public List<CodeClass> queryAll();
	public int deleteCodeClass(String class_id);
	
}
