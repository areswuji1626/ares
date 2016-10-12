package com.wuji1626.framework.codegen.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuji1626.framework.codegen.domain.ColumnInfo;
import com.wuji1626.framework.codegen.domain.DataSourceInfo;
import com.wuji1626.framework.codegen.domain.FieldInfo;
import com.wuji1626.framework.codegen.domain.GenerateConfig;
import com.wuji1626.framework.codegen.service.CodeGenerateService;
import com.wuji1626.framework.codegen.service.DBMetaDataService;
import com.wuji1626.framework.constant.CommonConstant;
import com.wuji1626.framework.constant.ErrorCode;
import com.wuji1626.framework.result.Result;
import com.wuji1626.framework.utils.ValidateUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service("codegenService")
@Transactional
public class CodeGenerateServiceImpl implements CodeGenerateService {

	@Autowired
	DBMetaDataService metaService;
	@Override
	public Result<String> generateBean(GenerateConfig config) {
		// TODO Auto-generated method stub
	    Result<String> res = new Result<String>();
	    config.setOutputType("bean");

        //建立数据模型（Map）  
        Map<String, Object> root = new HashMap<String, Object>();  
        Result<FieldInfo> fieldRes = convertDBColumn2Code(config.getDs(),config.getColumnList());
        Result<String> importRes = metaService.getImportList(fieldRes.getResultSet(),config);
        root.put("package", config.getPackageName()+".domain");  
        root.put("entityName", config.getEntityName());  
        root.put("fieldList", fieldRes.getResultSet());
        root.put("importPackageList", importRes.getResultSet());
	        //获取输出流（指定到控制台（标准输出））  
        if(ValidateUtil.isBlank(config.getOutputPath())){
			res.setStatus(CommonConstant.FAIL_ST);
			res.setErrorCode(ErrorCode.NO_OUTPUT_PATH);
			res.setMsg(ErrorCode.NO_OUTPUT_PATH_MSG);
			return res;
        }
	    res = codeGenerate("bean", root, config);
		return res;  
         
	}

	@Override
	public Result<String> generateMyBatisMapper(GenerateConfig config) {
		// TODO Auto-generated method stub
	    Result<String> res = new Result<String>();
	    config.setOutputType("mapper");
	    
	    Map<String, Object> root = new HashMap<String, Object>();  
        Result<FieldInfo> fieldRes = convertDBColumn2Code(config.getDs(),config.getColumnList());
        root.put("package", config.getPackageName());  
        root.put("entityName", config.getEntityName());  
        root.put("fieldsStr", convertFieldsStr(fieldRes.getResultSet()));
        root.put("orderStr", convertColumnsStr(config.getOrderColumnList()));
        // 为保持代码风格一致，将表名小写
        root.put("tab", config.getTab());
        //获取输出流（指定到控制台（标准输出））  
//        Writer out = new FileWriter(new File("D:\\Searchcolumnconfig.java"));
        if(ValidateUtil.isBlank(config.getOutputPath())){
			res.setStatus(CommonConstant.FAIL_ST);
			res.setErrorCode(ErrorCode.NO_OUTPUT_PATH);
			res.setMsg(ErrorCode.NO_OUTPUT_PATH_MSG);
			return res;
        }
        res = codeGenerate("mapper", root,config);
        
        return res;
        
	}
	protected String convertFieldsStr(List<FieldInfo> cols){
		StringBuffer buf = new StringBuffer();
		for(FieldInfo col:cols){
			buf.append(col.getField_name()+",");
		}
		return buf.substring(0,buf.length()-1);
	}
	protected String convertColumnsStr(List<ColumnInfo> cols){
		StringBuffer buf = new StringBuffer();
		if(cols == null){
			return null;
		}
		for(ColumnInfo col:cols){
			buf.append(col.getColumn_name()+",");
		}
		return buf.substring(0,buf.length()-1);
	}
	public Result<FieldInfo> convertDBColumn2Code(DataSourceInfo ds,List<ColumnInfo> list){
		Result<FieldInfo> res = new Result<FieldInfo>();
		List<FieldInfo> fieldList = new ArrayList<FieldInfo>();
		for(ColumnInfo column:list){
			FieldInfo field = new FieldInfo();
			field.setField_name(column.getColumn_name().toLowerCase());
			if(ds.getDs_type().equals(CommonConstant.ORACLE_DS)){
				field.setData_type(convertOracleDataType(column.getData_type()));
			}else{
				field.setData_type(convertMySQLDataType(column.getData_type()));
			}
			field.setSetter_getter_name(convertFieldName2SetterGetterName(column.getColumn_name()));
			field.setComments(column.getComments());
			fieldList.add(field);
		}
		res.setStatus(CommonConstant.SUCCESS_ST);
		res.setResultSet(fieldList);
		return res;
	}
	
	public String convertOracleDataType(String dataType){
		switch(dataType){
			case "CHAR":
			case "NVARCHAR2":
			case "VARCHAR2":
				return "String";
			case "NUMBER":
				return "long";
			case "DATE":
				return "Date";
			default:
				return "void";
		}
	}
	public String convertMySQLDataType(String dataType){
		switch(dataType){
			case "char":
			case "varchar":
				return "String";
			case "number":
				return "long";
			case "date":
				return "Date";
			default:
				return "void";
		}
	}
	public String convertFieldName2SetterGetterName(String fieldName){
		fieldName = fieldName.toLowerCase();
		return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	public Result<String> codeGenerate(String templateName, Map<String, Object> root,GenerateConfig config){
		Configuration cfg = new Configuration(); 
	    Result<String> res = new Result<String>();
	    try {
			cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
			Template template = cfg.getTemplate(templateName + ".ftl");  
			
			FileOutputStream fos = new FileOutputStream(config.getOutputPath() + convertOutputFileName(config) + convertOutputSuffix(config));
	        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8"); 
	        
	        //StringWriter out = new StringWriter();  
	        //System.out.println(out.toString());  
	        //数据与模板合并（数据+模板=输出）  
	        template.process(root, osw);  
	        osw.flush(); 
	        fos.close();
	        osw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setStatus(CommonConstant.EXCEPTION_ST);
			res.setErrorStack(ExceptionUtils.getFullStackTrace(e));
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setStatus(CommonConstant.EXCEPTION_ST);
			res.setErrorStack(ExceptionUtils.getFullStackTrace(e));
		}
	    res.setStatus(CommonConstant.SUCCESS_ST);
	    return res;
	    
	}
	protected String convertOutputSuffix(GenerateConfig config){
		String suffix = "";
		if(config.getOutputType().equals(CommonConstant.BEAN_OUTPUT_TYPE)){
			suffix = ".java";
		}else if(config.getOutputType().equals(CommonConstant.MAPPER_OUTPUT_TYPE)){
			suffix = "Mapper.xml";
		}
		return suffix;
	}
	protected String convertOutputFileName(GenerateConfig config){
		if(config.getOutputType().equals(CommonConstant.BEAN_OUTPUT_TYPE)){
			return config.getEntityName();
		}else if(config.getOutputType().equals(CommonConstant.MAPPER_OUTPUT_TYPE)){
			return config.getEntityName().toLowerCase();
		}
		return config.getEntityName();
	}
}
