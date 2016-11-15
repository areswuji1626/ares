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
public class MapperGenerateTest {
	
	@Autowired
	CodeGenerateService service;
	
	@Autowired
	DBMetaDataService metaService;
	
	@Autowired
	GenerateConfigService configService;
	
	@Test
	public void MySQLMapperGenerateTest(){
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
		config.setTab(tab);
		config.setOutputPath("D:/generateCode/");
		config = configService.setOrderFieldList(config, "create_date", CommonConstant.DESC);
		Result<String> mapperRes = service.generateMyBatisMapper(config);
		Assert.assertEquals(CommonConstant.SUCCESS_ST, mapperRes.getStatus());
	}
	
}
