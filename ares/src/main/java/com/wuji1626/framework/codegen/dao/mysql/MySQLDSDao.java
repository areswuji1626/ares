package com.wuji1626.framework.codegen.dao.mysql;

import java.util.List;

import com.googlecode.mp4parser.DataSource;
import com.wuji1626.framework.codegen.domain.TableInfo;

public interface MySQLDSDao {

	public List<TableInfo> getTableListByDatasource(DataSource ds);
	
}
