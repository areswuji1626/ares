package com.wuji1626.framework.support.code.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wuji1626.framework.support.code.domain.Code;
import com.wuji1626.framework.support.code.domain.CodeClass;
@Service("codeService")
public interface CodeService {

	public String insertCode(Code code);
	public List<Code> queryAllCode();
	public int deleteCode(String code_id);
	public List<CodeClass> getAllCodeClass();
	public Code queryCodeById(String code_id);
	public String saveCode(Code code);
	public List<Code> queryCodeByClass(String class_key);
	
}
