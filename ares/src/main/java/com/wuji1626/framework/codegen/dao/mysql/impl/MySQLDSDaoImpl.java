package com.wuji1626.framework.codegen.dao.mysql.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.googlecode.mp4parser.DataSource;
import com.wuji1626.framework.codegen.dao.mysql.MySQLDSDao;
import com.wuji1626.framework.codegen.domain.TableInfo;

@Repository
public class MySQLDSDaoImpl implements MySQLDSDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<TableInfo> getTableListByDatasource(DataSource ds) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
