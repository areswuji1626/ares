package com.wuji1626.framework.codegen;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wuji1626.framework.codegen.domain.GenerateConfig;
import com.wuji1626.framework.codegen.service.CodeGenerateService;
import com.wuji1626.framework.constant.CommonConstant;
import com.wuji1626.framework.result.Result;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:**/applicationContext.xml","classpath*:**/dispatcher-servlet.xml"})
public class PackageGenerateTest {

	@Autowired
	CodeGenerateService service;
	
	@Test
	public void packageGenTest(){
		
		GenerateConfig config = new GenerateConfig();
		config.setOutputPath("D:/generateCode/");
		config.setModuleName("qti");
		
		Result<String> res = service.generatePackage(config);	
		Assert.assertEquals(CommonConstant.SUCCESS_ST, res.getStatus());
	}
	
}
