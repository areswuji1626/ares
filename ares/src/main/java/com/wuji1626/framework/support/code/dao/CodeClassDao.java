package com.wuji1626.framework.support.code.dao;

import java.util.List;

import com.wuji1626.framework.support.code.domain.CodeClass;

public interface CodeClassDao {

	public String insertCodeClass(CodeClass cls);
	public List<CodeClass> queryAllCodeClass();
	public int deleteCodeClass(String class_id);
	
}
