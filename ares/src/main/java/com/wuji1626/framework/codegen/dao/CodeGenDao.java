package com.wuji1626.framework.codegen.dao;

import java.util.List;

import com.wuji1626.framework.codegen.domain.DataSourceInfo;

public interface CodeGenDao {

	public String insertDataSource(DataSourceInfo ds);
	public List<DataSourceInfo> listDataSource();
	
}
