package com.wuji1626.framework.codegen.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wuji1626.framework.codegen.domain.ColumnInfo;
import com.wuji1626.framework.codegen.domain.DataSourceInfo;
import com.wuji1626.framework.codegen.domain.FieldInfo;
import com.wuji1626.framework.codegen.domain.GenerateConfig;
import com.wuji1626.framework.codegen.domain.TableInfo;
import com.wuji1626.framework.result.Result;

@Service("codegenService")
public interface DBMetaDataService {
	
	public Result<String> insertDataSource(DataSourceInfo ds);
	public Result<DataSourceInfo> listDataSource();
	public Result<String> testDataSource(DataSourceInfo ds);
	
	public Result<TableInfo> listTable(DataSourceInfo ds);
	
	public Result<ColumnInfo> listColumn(DataSourceInfo ds, TableInfo tab);
	public Result<String> getImportList(List<FieldInfo> fields, GenerateConfig config);
	public Result<ColumnInfo> getPrimaryColumn(DataSourceInfo ds, TableInfo tab);
	
}
