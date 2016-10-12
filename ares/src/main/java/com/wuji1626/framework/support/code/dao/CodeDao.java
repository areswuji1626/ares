package com.wuji1626.framework.support.code.dao;

import java.util.List;

import com.wuji1626.framework.support.code.domain.Code;

public interface CodeDao {
	
	public String insertCode(Code code);
	
	public int deleteCode(String code_id);
	
	public int updateCode(Code code);
	
	public List<Code> queryAllCode();

	public Code queryCodeById(String code_id);
	
	public List<Code> queryCodeByClass(String class_key);
	
}
