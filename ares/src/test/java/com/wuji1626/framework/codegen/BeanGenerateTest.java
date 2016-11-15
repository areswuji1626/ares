package com.wuji1626.framework.codegen;

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
import com.wuji1626.framework.codegen.service.GenerateConfigService;
import com.wuji1626.framework.constant.CommonConstant;
import com.wuji1626.framework.result.Result;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:**/applicationContext.xml","classpath*:**/dispatcher-servlet.xml"})
public class BeanGenerateTest {

	@Autowired
	CodeGenerateService service;
	
	@Autowired
	DBMetaDataService metaService;
	
	@Autowired
	GenerateConfigService configService;
	
//	@Test
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
		// output path
		config.setOutputPath("D:/generateCode/");
		
		Result<String> genBean = service.generateBean(config);
		Result<String> genRes = service.generateMyBatisMapper(config);
		Assert.assertEquals(CommonConstant.SUCCESS_ST, genBean.getStatus());
		Assert.assertEquals(CommonConstant.SUCCESS_ST, genRes.getStatus());
	}
	@Test
	public void MySQLBeanGenerateTest(){
		
		DataSourceInfo ds = new DataSourceInfo();
		ds.setDs_name("QTI Database");
		ds.setDs_type("MySQL");
		ds.setDs_url("jdbc:mysql://127.0.0.1:3306/qti?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull");
		ds.setDs_user("root");
		ds.setDs_password("password");
		
		TableInfo tab = new TableInfo();
		tab.setDs_name("QTI Database");
		tab.setTable_name("question");
		
		Result<ColumnInfo> res = metaService.listColumn(ds, tab);
		
		GenerateConfig config = new GenerateConfig();
		config.setPackageName("com.wuji1626.ares.qti");
		config.setEntityName("Question");
		config.setColumnList(res.getResultSet());
		config.setDs(ds);
		config.setOutputPath("D:/generateCode/");
		
		Result<String> genBean = service.generateBean(config);
		Assert.assertEquals(CommonConstant.SUCCESS_ST, genBean.getStatus());
		
	}
}
