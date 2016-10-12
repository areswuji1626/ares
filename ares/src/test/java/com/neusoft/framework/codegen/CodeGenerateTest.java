package com.neusoft.framework.codegen;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wuji1626.framework.codegen.domain.ColumnInfo;
import com.wuji1626.framework.codegen.domain.DataSourceInfo;
import com.wuji1626.framework.codegen.domain.GenerateConfig;
import com.wuji1626.framework.codegen.domain.TableInfo;
import com.wuji1626.framework.codegen.service.CodeGenerateService;
import com.wuji1626.framework.codegen.service.DBMetaDataService;
import com.wuji1626.framework.constant.CommonConstant;
import com.wuji1626.framework.result.Result;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:**/applicationContext.xml","classpath*:**/dispatcher-servlet.xml"})
public class CodeGenerateTest {

	@Autowired
	CodeGenerateService service;
	
	@Autowired
	DBMetaDataService metaService;
	
	@Test
	public void beanGenerateTest(){
		
		DataSourceInfo ds = new DataSourceInfo();
		ds.setDs_name("对象库");
		ds.setDs_type("Oracle");
//		ds.setDs_url("jdbc:oracle:thin:@10.6.98.40:1521/CJWSZY");
//		ds.setDs_user("mhqt");
//		ds.setDs_password("mhqt");
//		ds.setDs_schema("mhqt");
		
		ds.setDs_url("jdbc:oracle:thin:@10.6.88.90:1521:orcl");
		ds.setDs_user("jiwei2");
		ds.setDs_password("neusoft_cjw");
		ds.setDs_schema("objpre");
		
		TableInfo tab = new TableInfo();
		tab.setDs_name("对象库");
		tab.setTable_name("obj_md");
		tab.setTable_schema("objpre");
		
		Result<ColumnInfo> res = metaService.listColumn(ds, tab);
		
		GenerateConfig config = new GenerateConfig();
		config.setPackageName("com.neusoft.jobplat.domain.obj");
		config.setEntityName("ObjMd");
		config.setColumnList(res.getResultSet());
		config.setDs(ds);
		config.setTab(tab);
		// 排序字段
//		List<ColumnInfo> orderColumn = new ArrayList<ColumnInfo>();
//		ColumnInfo order = new ColumnInfo();
//		order.setColumn_name("columnorder");
//		orderColumn.add(order);
//		config.setOrderColumnList(orderColumn);
		// 条件字段
		List<ColumnInfo> conditionColumn = new ArrayList<ColumnInfo>();
		ColumnInfo condition = new ColumnInfo();
		condition.setColumn_name("obj_tab_code");
		conditionColumn.add(condition);
		ColumnInfo condition2 = new ColumnInfo();
		condition2.setColumn_name("obj_guid");
		conditionColumn.add(condition2);
		config.setConditionColumnList(conditionColumn);
		// 输出路径
		config.setOutputPath("D:/generateCode/");
//		config.setOutputType("bean");
		
		Result<String> genBean = service.generateBean(config);
		Result<String> genRes = service.generateMyBatisMapper(config);
		Assert.assertEquals(CommonConstant.SUCCESS_ST, genBean.getStatus());
		Assert.assertEquals(CommonConstant.SUCCESS_ST, genRes.getStatus());
	}
//	@Test
	public void MySQLBeanGenerateTest(){
		
		DataSourceInfo ds = new DataSourceInfo();
		ds.setDs_name("CD管理数据库");
		ds.setDs_type("MySQL");
		ds.setDs_url("jdbc:mysql://127.0.0.1:3306/cdholder?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull");
		ds.setDs_user("root");
		ds.setDs_password("password");
		
		TableInfo tab = new TableInfo();
		tab.setDs_name("CD管理数据库");
		tab.setTable_name("deptinfo");
		
		Result<ColumnInfo> res = metaService.listColumn(ds, tab);
		
		GenerateConfig config = new GenerateConfig();
		config.setPackageName("com.neusoft.search.domain");
		config.setEntityName("Deptinfo");
		config.setColumnList(res.getResultSet());
		config.setDs(ds);
		service.generateBean(config);
	}
}
