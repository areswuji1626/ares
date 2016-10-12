package com.wuji1626.framework.codegen.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wuji1626.framework.codegen.dao.CodeGenDao;
import com.wuji1626.framework.codegen.domain.DataSourceInfo;

@Repository
public class CodeGenDaoImpl implements CodeGenDao {
	
	public static final String NEW_DS = "insertDataSource";
	public static final String LIST_DS = "listDataSource";

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public String insertDataSource(DataSourceInfo ds) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.insert(NEW_DS, ds);
		return ds.getDs_id();
	}

	@Override
	public List<DataSourceInfo> listDataSource() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(LIST_DS);
	}


}
