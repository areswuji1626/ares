package com.wuji1626.framework.codegen;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wuji1626.framework.codegen.domain.ColumnInfo;
import com.wuji1626.framework.codegen.domain.DataSourceInfo;
import com.wuji1626.framework.codegen.domain.TableInfo;
import com.wuji1626.framework.codegen.service.DBMetaDataService;
import com.wuji1626.framework.constant.CommonConstant;
import com.wuji1626.framework.result.Result;
import com.wuji1626.framework.support.code.service.CodeService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:**/applicationContext.xml","classpath*:**/dispatcher-servlet.xml"})
public class MetaDataTest {

	@Autowired
	private DBMetaDataService service;

	@Test
	public void dsTest(){
		DataSourceInfo ds = new DataSourceInfo();
		ds.setDs_name("CD管理数据库");
		ds.setDs_type("MySQL");
		ds.setDs_url("jdbc:mysql://127.0.0.1:3306/cdholder?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull");
		ds.setDs_user("root");
		ds.setDs_password("password");
		
		Result<String> res = service.testDataSource(ds);
		Assert.assertEquals(CommonConstant.SUCCESS_ST, res.getStatus());
	}
	@Test
	public void oracleDsTest(){
		DataSourceInfo ds = new DataSourceInfo();
		ds.setDs_name("对象库");
		ds.setDs_type("Oracle");
		ds.setDs_url("jdbc:oracle:thin:@10.6.98.40:1521/CJWSZY");
		ds.setDs_user("mhqt");
		ds.setDs_password("mhqt");
		ds.setDs_schema("objpre");
		Result<String> res = service.testDataSource(ds);
		Assert.assertEquals(CommonConstant.SUCCESS_ST, res.getStatus());
	}
	@Test
	public void getSchemaFromUrl(){
		DataSourceInfo ds = new DataSourceInfo();
		ds.setDs_url("jdbc:mysql://127.0.0.1:3306/cdholder?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull");
		String subUrl = ds.getDs_url().substring(0, ds.getDs_url().indexOf("?"));
		Assert.assertEquals("cdholder", subUrl.substring(subUrl.lastIndexOf("/")+1));
	}
	@Test
	public void listTable(){
		DataSourceInfo ds = new DataSourceInfo();
		ds.setDs_name("CD管理数据库");
		ds.setDs_type("MySQL");
		ds.setDs_url("jdbc:mysql://127.0.0.1:3306/cdholder?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull");
		ds.setDs_user("root");
		ds.setDs_password("password");
		
		
		Result<TableInfo> res = service.listTable(ds);
//		for(TableInfo table: res.getResultSet()){
//			System.out.println("Table Name:" + table.getTable_name());
//		}
		Assert.assertEquals(21, res.getResultSet().size());
		
	}
	@Test
	public void listOracleTable(){
		DataSourceInfo ds = new DataSourceInfo();
		ds.setDs_name("对象库");
		ds.setDs_type("Oracle");
		ds.setDs_url("jdbc:oracle:thin:@10.6.98.40:1521/CJWSZY");
		ds.setDs_user("mhqt");
		ds.setDs_password("mhqt");
		ds.setDs_schema("mhqt");
		
		Result<TableInfo> res = service.listTable(ds);
		for(TableInfo table: res.getResultSet()){
			System.out.println("Table Name:" + table.getTable_name());
		}
		Assert.assertEquals(88, res.getResultSet().size());
		
	}
	@Test
	public void listOracleColumn(){
		DataSourceInfo ds = new DataSourceInfo();
		ds.setDs_name("对象库");
		ds.setDs_type("Oracle");
		ds.setDs_url("jdbc:oracle:thin:@10.6.98.40:1521/CJWSZY");
		ds.setDs_user("mhqt");
		ds.setDs_password("mhqt");
		ds.setDs_schema("mhqt");
		
		TableInfo tab = new TableInfo();
		tab.setDs_name("对象库");
		tab.setTable_name("SEARCHCOLUMNCONFIG");
		tab.setTable_schema("mhqt");
		
		Result<ColumnInfo> columns = service.listColumn(ds, tab);
		for(ColumnInfo column: columns.getResultSet()){
			System.out.println("Column Name:" + column.getColumn_name());
		}
		Assert.assertEquals(5, columns.getResultSet().size());
		
	}
	@Test
	public void insertDs(){
		DataSourceInfo ds = new DataSourceInfo();
		ds.setDs_name("QTI Database");
		ds.setDs_type("MySQL");
		ds.setDs_url("jdbc:mysql://127.0.0.1:3306/qti?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull");
		ds.setDs_user("root");
		ds.setDs_password("password");
		Result<String> res = service.insertDataSource(ds);
		Assert.assertEquals(CommonConstant.SUCCESS_ST, res.getStatus());
	}
}
