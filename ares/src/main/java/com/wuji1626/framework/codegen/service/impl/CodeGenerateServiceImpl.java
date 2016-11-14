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
import com.wuji1626.framework.codegen.service.GenerateConfigService;
import com.wuji1626.framework.constant.CommonConstant;
import com.wuji1626.framework.constant.ErrorCode;
import com.wuji1626.framework.result.Result;
import com.wuji1626.framework.utils.CommonUtil;
import com.wuji1626.framework.utils.StringUtil;
import com.wuji1626.framework.utils.ValidateUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service("codegenService")
@Transactional
public class CodeGenerateServiceImpl implements CodeGenerateService {

	@Autowired
	DBMetaDataService metaService;
	@Autowired
	GenerateConfigService configService;
	
	@Override
	public Result<String> generateBean(GenerateConfig config) {
		// TODO Auto-generated method stub
	    Result<String> res = new Result<String>();
	    config.setOutputType(CommonConstant.BEAN_OUTPUT_TYPE);

        //create data model（Map）  
        Map<String, Object> root = new HashMap<String, Object>();  
        Result<FieldInfo> fieldRes = convertDBColumn2Code(config.getDs(),config.getColumnList());
        Result<String> importRes = metaService.getImportList(fieldRes.getResultSet(),config);
        // entity's package specify
        root.put("package", config.getPackageName());  
        root.put("entityName", config.getEntityName());  
        root.put("fieldList", fieldRes.getResultSet());
        root.put("importPackageList", importRes.getResultSet());
	        //get output stream with std io  
        if(ValidateUtil.isBlank(config.getOutputPath())){
			res.setStatus(CommonConstant.FAIL_ST);
			res.setErrorCode(ErrorCode.NO_OUTPUT_PATH);
			res.setMsg(ErrorCode.NO_OUTPUT_PATH_MSG);
			return res;
        }
	    res = codeGenerate(root, config, null);
		return res;  
         
	}

	@Override
	public Result<String> generateMyBatisMapper(GenerateConfig config) {
		// TODO Auto-generated method stub
	    Result<String> res = new Result<String>();
	    config.setOutputType(CommonConstant.MAPPER_OUTPUT_TYPE);
	    
	    Map<String, Object> root = new HashMap<String, Object>();  
        Result<FieldInfo> fieldRes = convertDBColumn2Code(config.getDs(),config.getColumnList());
        Result<ColumnInfo> primaryKeyRes = metaService.getPrimaryColumn(config.getDs(), config.getTab());
        root.put("package", config.getPackageName());  
        root.put("entityName", config.getEntityName());  
        root.put("selectFieldsStr", convertSelectFieldsStr(fieldRes.getResultSet()));
        root.put("pkCondition", convertPK2Condition(primaryKeyRes,fieldRes));
        root.put("orderStr", convertOrderStr(configService.getOrderFieldList(config)));
        // if the pk is single, auto-generated pk will be return in mapper;
        // if the pk is compound, pk will not be auto-generated
        root.put("primaryKey", primaryKeyRes.getResultSet().size()==1?primaryKeyRes.getResultSet().get(0).getColumn_name():"");
        Result<FieldInfo> pkGetter = convertDBColumn2Code(config.getDs(),primaryKeyRes.getResultSet());
        root.put("pkGetter", pkGetter.getResultSet().size()==1?pkGetter.getResultSet().get(0).getField_name():"");
        root.put("insertStr", convertInsertStr(config, fieldRes.getResultSet(), primaryKeyRes.getResultSet()));
        // to keep style, table's name is lowcase
        root.put("tab", config.getTab());
        //获取输出流（指定到控制台（标准输出））  
//        Writer out = new FileWriter(new File("D:\\Searchcolumnconfig.java"));
        if(ValidateUtil.isBlank(config.getOutputPath())){
			res.setStatus(CommonConstant.FAIL_ST);
			res.setErrorCode(ErrorCode.NO_OUTPUT_PATH);
			res.setMsg(ErrorCode.NO_OUTPUT_PATH_MSG);
			return res;
        }
        res = codeGenerate(root,config, null);
        
        return res;
        
	}
	

	@Override
	public Result<String> generateDao(GenerateConfig config) {
		// TODO Auto-generated method stub
	    Result<String> res = new Result<String>();
	    config.setOutputType(CommonConstant.DAO_OUTPUT_TYPE);

        //create data model（Map）  
        Map<String, Object> root = new HashMap<String, Object>();  
        Result<ColumnInfo> resPrimaryKey = metaService.getPrimaryColumn(config.getDs(), config.getTab());
        // entity's package specify
        root.put("package", config.getPackageName());  
        root.put("entityName", config.getEntityName());  
        root.put("primaryKey", resPrimaryKey.getResultSet().size()==1?resPrimaryKey.getResultSet().get(0).getColumn_name():"");
        Result<FieldInfo> pkGetter = convertDBColumn2Code(config.getDs(),resPrimaryKey.getResultSet());
        root.put("pkGetter", pkGetter.getResultSet().size()==1?pkGetter.getResultSet().get(0).getField_name():"");
	        //get output stream with std io  
        if(ValidateUtil.isBlank(config.getOutputPath())){
			res.setStatus(CommonConstant.FAIL_ST);
			res.setErrorCode(ErrorCode.NO_OUTPUT_PATH);
			res.setMsg(ErrorCode.NO_OUTPUT_PATH_MSG);
			return res;
        }
	    Result<String> daoRes = codeGenerate(root, config, null);
	    config.setOutputType(CommonConstant.DAO_IMPL_OUTPUT_TYPE);
	    Result<String> daoImplRes = codeGenerate(root, config, CommonConstant.IMPL_PATH);
	    
	    if(daoRes.getStatus().equals(CommonConstant.SUCCESS_ST)||
	    		daoImplRes.getStatus().equals(CommonConstant.SUCCESS_ST)){
	    	res.setStatus(CommonConstant.SUCCESS_ST);
	    }else{
	    	if(daoRes.getStatus().equals(CommonConstant.FAIL_ST)){
	    		res.appendErrorCode(daoRes.getErrorCode());
	    		res.appendErrorMsg(daoRes.getMsg());
	    	}
	    	if(daoImplRes.getStatus().equals(CommonConstant.FAIL_ST)){
	    		res.appendErrorCode(daoImplRes.getErrorCode());
	    		res.appendErrorMsg(daoImplRes.getMsg());
	    	}
	    	res.setStatus(CommonConstant.FAIL_ST);
	    }
		return res;  
         
	}
	
	protected String convertSelectFieldsStr(List<FieldInfo> cols){
		StringBuffer buf = new StringBuffer();
		for(FieldInfo col:cols){
			buf.append(col.getColumn_name()+" ");
			buf.append(col.getField_name()+",");
		}
		return buf.substring(0,buf.length()-1);
	}
	protected String convertColumnsStr(List<ColumnInfo> cols){
		StringBuffer buf = new StringBuffer();
		if(cols == null || cols.size() == 0){
			return null;
		}
		for(ColumnInfo col:cols){
			buf.append(col.getColumn_name()+",");
		}
		return buf.substring(0,buf.length()-1);
	}
	protected String convertOrderStr(List<ColumnInfo> cols){
		StringBuffer buf = new StringBuffer();
		if(cols == null || cols.size() == 0){
			return null;
		}
		for(ColumnInfo col:cols){
			buf.append(col.getColumn_name() +" ");
			buf.append(col.getOrder_flg()+",");
		}
		return buf.substring(0,buf.length()-1);
	}
	public Result<FieldInfo> convertDBColumn2Code(DataSourceInfo ds,List<ColumnInfo> list){
		Result<FieldInfo> res = new Result<FieldInfo>();
		List<FieldInfo> fieldList = new ArrayList<FieldInfo>();
		for(ColumnInfo column:list){
			FieldInfo field = new FieldInfo();
			// if column name contains '_', convert field name to CamelCase
			if(column.getColumn_name().contains("_")){
				String[] elements = column.getColumn_name().split("_");
				StringBuffer buf = new StringBuffer();
				for(String elem:elements){
					buf.append(StringUtil.makeInitialCharacterUpperCase(elem));
				}
				field.setField_name(StringUtil.makeInitialCharaterLowerCase(buf.toString()));
			// else make the field initial charater lowercase
			}else{
				field.setField_name(column.getColumn_name().toLowerCase());
			}
			field.setColumn_name(column.getColumn_name());
			// ORACLE DS
			if(ds.getDs_type().equals(CommonConstant.ORACLE_DS)){
				field.setData_type(convertOracleDataType(column.getData_type()));
				
			// MySQL DS
			}else{
				field.setData_type(convertMySQLDataType(column.getData_type()));
			}
//			field.setSetter_getter_name(convertFieldName2SetterGetterName(field.getField_name()));
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
			case "datetime":
				return "Date";
			default:
				return "void";
		}
	}
	public String convertFieldName2SetterGetterName(String fieldName){
		
		fieldName = fieldName.toLowerCase();
		return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	public Result<String> codeGenerate(Map<String, Object> root,GenerateConfig config, String relativePath){
		Configuration cfg = new Configuration(); 
	    Result<String> res = new Result<String>();
	    try {
	    	
			cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
			Template template = cfg.getTemplate(config.getOutputType() + ".ftl");  
			StringBuffer outputPath = new StringBuffer();
			outputPath.append(config.getOutputPath());
			outputPath.append(convertOutputType2RelativePath(config) + "/");
			CommonUtil.makeDirExist(outputPath.toString());
			if(!ValidateUtil.isBlank(relativePath)){
				outputPath.append(relativePath + "/");
				CommonUtil.makeDirExist(outputPath.toString());
			}
			outputPath.append(convertOutputFileName(config));
			outputPath.append(convertOutputSuffix(config));
			FileOutputStream fos = new FileOutputStream(outputPath.toString());
	        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8"); 
	         
	        //data merges to template (data + template = output)
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
		}else if(config.getOutputType().equals(CommonConstant.DAO_OUTPUT_TYPE)){
			suffix = "Dao.java";
		}else if(config.getOutputType().equals(CommonConstant.DAO_IMPL_OUTPUT_TYPE)){
			suffix = "DaoImpl.java";
		}
		return suffix;
	}
	protected String convertOutputFileName(GenerateConfig config){
		if(config.getOutputType().equals(CommonConstant.BEAN_OUTPUT_TYPE)||
				config.getOutputType().equals(CommonConstant.DAO_OUTPUT_TYPE)||
				config.getOutputType().equals(CommonConstant.DAO_IMPL_OUTPUT_TYPE)){
			return config.getEntityName();
		}else if(config.getOutputType().equals(CommonConstant.MAPPER_OUTPUT_TYPE)){
			return config.getEntityName().toLowerCase();
		}
		return config.getEntityName();
	}
	protected String convertOutputType2RelativePath(GenerateConfig config){
		if(config.getOutputType().equals(CommonConstant.DAO_OUTPUT_TYPE)||
				config.getOutputType().equals(CommonConstant.DAO_IMPL_OUTPUT_TYPE)){
			return CommonConstant.DAO_OUTPUT_TYPE;
		}else if(config.getOutputType().equals(CommonConstant.MAPPER_OUTPUT_TYPE)){
			return "";
		}else{
			return config.getOutputType();
		}
	}
	@Override
	public Result<String> generatePackage(GenerateConfig config) {
		// TODO Auto-generated method stub
		Result<String> res = new Result<String>();
		res.setStatus(CommonConstant.SUCCESS_ST);
		
		if(ValidateUtil.isBlank(config.getModuleName())){
			res.setStatus(CommonConstant.FAIL_ST);
			res.setErrorCode(ErrorCode.NO_MODULE_NAME);
			res.setMsg(ErrorCode.NO_MODULE_NAME_MSG);
			return res;
		}
		
		if(!ValidateUtil.isBlank(config.getPjBase())){
			// need to implement
		}else{
			if(ValidateUtil.isBlank(config.getOutputPath())){
				res.setStatus(CommonConstant.FAIL_ST);
				res.setErrorCode(ErrorCode.NO_OUTPUT_PATH);
				res.setMsg(ErrorCode.NO_OUTPUT_PATH_MSG);
			}
			// java package: controller/service(impl)/dao(impl)/entity
			StringBuffer javaPathPrefix = new StringBuffer();
			javaPathPrefix.append(config.getOutputPath());
			javaPathPrefix.append("java/");
			javaPathPrefix.append(config.getModuleName());
			javaPathPrefix.append("/");
			CommonUtil.makeDirExist( javaPathPrefix.toString() + "controller");
			CommonUtil.makeDirExist( javaPathPrefix.toString() + "service/impl");
			CommonUtil.makeDirExist( javaPathPrefix.toString() + "dao/impl");
			CommonUtil.makeDirExist( javaPathPrefix.toString() + "entity");
			// jsp module dir only
			StringBuffer rsPathPrefix = new StringBuffer();
			rsPathPrefix.append(config.getOutputPath());
			rsPathPrefix.append("/jsp/");
			rsPathPrefix.append(config.getModuleName());
			rsPathPrefix.append("/");
			CommonUtil.makeDirExist(rsPathPrefix.toString());
			rsPathPrefix = new StringBuffer();
			rsPathPrefix.append(config.getOutputPath());
			rsPathPrefix.append("/js/");
			rsPathPrefix.append(config.getModuleName());
			rsPathPrefix.append("/");
			CommonUtil.makeDirExist(rsPathPrefix.toString());
			rsPathPrefix = new StringBuffer();
			rsPathPrefix.append(config.getOutputPath());
			rsPathPrefix.append("/css/");
			rsPathPrefix.append(config.getModuleName());
			rsPathPrefix.append("/");
			CommonUtil.makeDirExist(rsPathPrefix.toString());
		}
		
		return res;
	}
	protected String convertInsertStr(GenerateConfig config, List<FieldInfo> fields, List<ColumnInfo> pkColumn){
		StringBuffer insertStatement = new StringBuffer();
		insertStatement.append(CommonConstant.BRANKET_L);
		for(FieldInfo field:fields){
			insertStatement.append(field.getColumn_name());
			insertStatement.append(CommonConstant.COMMA);
		}
		insertStatement = new StringBuffer(insertStatement.subSequence(0, insertStatement.length()-1));
		insertStatement.append(CommonConstant.BRANKET_R);
		insertStatement.append(CommonConstant.BLANK);
		
		insertStatement.append("values");
		insertStatement.append(CommonConstant.BLANK);
		insertStatement.append(CommonConstant.BRANKET_L);
		for(FieldInfo field:fields){
			// if the table has a single pk, generater can generate a auto-generate uuid
			if(pkColumn.size()==1){
				// the pk field will append uuid function
				if(pkColumn.get(0).getColumn_name().equals(field.getColumn_name())){
					insertStatement.append(getUUIDStatement(config));
					insertStatement.append(CommonConstant.COMMA);
				}else if(isDateTimeField(field)){
					insertStatement.append(getSysdateStatement(config));
					insertStatement.append(CommonConstant.COMMA);
				}else{
					insertStatement.append(CommonConstant.WELL);
					insertStatement.append(CommonConstant.BRACE_L);
					insertStatement.append(field.getField_name());
					insertStatement.append(CommonConstant.BRACE_R);
					insertStatement.append(CommonConstant.COMMA);
				}
				
			}else{
				insertStatement.append(CommonConstant.WELL);
				insertStatement.append(CommonConstant.BRACE_L);
				insertStatement.append(field.getField_name());
				insertStatement.append(CommonConstant.BRACE_R);
				insertStatement.append(CommonConstant.COMMA);
			}
		}
		insertStatement = new StringBuffer(insertStatement.subSequence(0, insertStatement.length()-1));
		insertStatement.append(CommonConstant.BRANKET_R);
		
		return insertStatement.toString();
	}
	protected String getUUIDStatement(GenerateConfig config){
		switch(config.getDs().getDs_type()){
			case CommonConstant.MYSQL_DS:
				return "uuid()";
			case CommonConstant.ORACLE_DS:
				return "sys_guid()";
			default:
				return "uuid()";
		}
	}
	protected boolean isDateTimeField(FieldInfo field){
		if(field.getData_type().equals("Date")){
			return true;
		}else{
			return false;
		}
	}
	protected String getSysdateStatement(GenerateConfig config){
		switch(config.getDs().getDs_type()){
			case CommonConstant.MYSQL_DS:
				return "sysdate()";
			case CommonConstant.ORACLE_DS:
				return "sysdate";
			default:
				return "sysdate()";
		}
	}
	protected String convertPK2Condition(Result<ColumnInfo> primaryKeys,Result<FieldInfo> fieldRes){
		StringBuffer buf = new StringBuffer();
		for(ColumnInfo primaryKey:primaryKeys.getResultSet()){
			for(FieldInfo field:fieldRes.getResultSet()){
				if(primaryKey.getColumn_name().equals(field.getColumn_name())){
					buf.append(primaryKey.getColumn_name());
					buf.append(CommonConstant.BLANK);
					buf.append(CommonConstant.EQUAL);
					buf.append(CommonConstant.BLANK);
					buf.append(CommonConstant.WELL);
					buf.append(CommonConstant.BRACE_L);
					buf.append(field.getField_name());
					buf.append(CommonConstant.BRACE_R);
					buf.append(CommonConstant.BLANK);
					buf.append("and");
					buf.append(CommonConstant.BLANK);
				}
			}
		}
		return buf.substring(0, buf.length()-4);
	}
}
