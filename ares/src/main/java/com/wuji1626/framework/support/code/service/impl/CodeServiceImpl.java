package com.wuji1626.framework.support.code.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuji1626.framework.support.code.dao.CodeClassDao;
import com.wuji1626.framework.support.code.dao.CodeDao;
import com.wuji1626.framework.support.code.domain.Code;
import com.wuji1626.framework.support.code.domain.CodeClass;
import com.wuji1626.framework.support.code.service.CodeService;
@Service("codeService")
@Transactional
public class CodeServiceImpl implements CodeService {


	@Autowired
	private CodeDao dao; 
	@Autowired
	private CodeClassDao codeClassDao;
	
	@Override
	public String insertCode(Code code) {
		// TODO Auto-generated method stub
		return dao.insertCode(code);
	}

	@Override
	public List<Code> queryAllCode() {
		// TODO Auto-generated method stub
		return dao.queryAllCode();
	}

	@Override
	public int deleteCode(String code_id) {
		// TODO Auto-generated method stub
		return dao.deleteCode(code_id);
	}

	@Override
	public List<CodeClass> getAllCodeClass() {
		// TODO Auto-generated method stub
		return codeClassDao.queryAllCodeClass();
	}

	@Override
	public Code queryCodeById(String code_id) {
		// TODO Auto-generated method stub
		return dao.queryCodeById(code_id);
	}

	@Override
	public String saveCode(Code code) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(code.getCode_id())){
			return dao.insertCode(code);
		}else{
			if(0<dao.updateCode(code)){
				return code.getCode_id();
			}else{
				return "";
			}
		}
	}

	@Override
	public List<Code> queryCodeByClass(String class_key) {
		// TODO Auto-generated method stub
		return dao.queryCodeByClass(class_key);
	}

}
