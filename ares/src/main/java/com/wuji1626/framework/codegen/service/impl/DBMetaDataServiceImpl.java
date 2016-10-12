package com.wuji1626.framework.codegen.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.wuji1626.framework.codegen.dao.CodeGenDao;
import com.wuji1626.framework.codegen.domain.ColumnInfo;
import com.wuji1626.framework.codegen.domain.DataSourceInfo;
import com.wuji1626.framework.codegen.domain.FieldInfo;
import com.wuji1626.framework.codegen.domain.GenerateConfig;
import com.wuji1626.framework.codegen.domain.TableInfo;
import com.wuji1626.framework.codegen.service.DBMetaDataService;
import com.wuji1626.framework.constant.CommonConstant;
import com.wuji1626.framework.constant.ErrorCode;
import com.wuji1626.framework.result.Result;
import com.wuji1626.framework.utils.ValidateUtil;

@Service("metaDataService")
@Transactional
public class DBMetaDataServiceImpl implements DBMetaDataService {

	@Autowired
	private CodeGenDao dao;
	
	private static String MYSQL_GET_TABLES_SQL = "select table_schema, table_name, table_comment from information_schema.tables where table_schema=? and table_type='base table'";
	private static String ORACLE_GET_TABLES_SQL = "select a.table_name,b.comments from user_tables a, user_tab_comments b where a.table_name=b.table_name order by a.table_name";
	private static String ORACLE_GET_COLUMNS_SQL = "select a.table_name,b.column_name,b.data_type,b.data_length,a.comments from user_col_comments a, user_tab_columns b where a.table_name=b.table_name and a.column_name=b.column_name and a.table_name=?";
	private static String MYSQL_GET_COLUMNS_SQL = "select table_name,column_name,data_type,character_maximum_length,column_comment from information_schema.columns where table_name=?";
	@Override
	public Result<String> insertDataSource(DataSourceInfo ds) {
		// TODO Auto-generated method stub
		Result<String> res = new Result<String>();
		
		// 校验数据源是否可用
		res = testDataSource(ds);
		if(res.getStatus().equals(CommonConstant.SUCCESS_ST)){
			String ds_id = dao.insertDataSource(ds);
			List<String> dsList = new ArrayList<String>();
			dsList.add(ds_id);
			res.setResultSet(dsList);
		}
		
		return res;
	}

	@Override
	public Result<String> testDataSource(DataSourceInfo ds) {
		// TODO Auto-generated method stub
		
		switch(ds.getDs_type()){
			case CommonConstant.MYSQL_DS:
				return testMySQLDs(ds);
			case CommonConstant.ORACLE_DS:
				return testOracleDs(ds);
			default:
				Result<String> res = new Result<String>();
				res.setStatus(CommonConstant.FAIL_ST);
				res.setErrorCode(ErrorCode.NO_MATCH_DS);
				res.setMsg(ErrorCode.NO_MATCH_DS_MSG);
				return res;
		}
	}
	private Result<String> testMySQLDs(DataSourceInfo ds){
		Result<String> res = new Result<String>();
		
		try {
			Class.forName(CommonConstant.MYSQL_CLASS_NAME);
			//指定连接类型  
			Connection conn = DriverManager.getConnection(ds.getDs_url(), 
					ds.getDs_user(), ds.getDs_password());//获取连接  
			PreparedStatement pst = conn.prepareStatement(CommonConstant.MYSQL_TEST_SQL);//准备执行语句
			ResultSet rs = pst.executeQuery();
			rs.first();
			if(!StringUtils.isEmpty(rs.getString(1))){
				res.setStatus(CommonConstant.SUCCESS_ST);
			}else{
				res.setStatus(CommonConstant.FAIL_ST);
				res.setErrorCode(ErrorCode.TEST_SQL_WRONG_RS);
				res.setMsg(ErrorCode.TEST_SQL_WRONG_RS_MSG);
			}
			pst.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setStatus(CommonConstant.FAIL_ST);
			res.setErrorCode(ErrorCode.CLASS_NO_FOUND_E);
			res.setMsg(ErrorCode.CLASS_NO_FOUND_E_MSG);
			res.setErrorStack(ExceptionUtils.getFullStackTrace(e));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setStatus(CommonConstant.FAIL_ST);
			res.setErrorCode(ErrorCode.SQL_EXCEPTION);
			res.setMsg(ErrorCode.SQL_EXCEPTION_MSG);
			res.setErrorStack(ExceptionUtils.getFullStackTrace(e));
		}
		
		return res;
	}

	private Result<String> testOracleDs(DataSourceInfo ds){
		Result<String> res = new Result<String>();
		
		// ORACLE需要手动指定schema名对数据源加以区分
		if(ValidateUtil.isBlank(ds.getDs_schema())){
			res.setStatus(CommonConstant.FAIL_ST);
			res.setErrorCode(ErrorCode.DS_SCHEMA_NULL);
			res.setMsg(ErrorCode.DS_SCHEMA_NULL_MSG);
			return res;
		}
		
		try {
			Class.forName(CommonConstant.ORACLE_CLASS_NAME);
			//指定连接类型  
			Connection conn = DriverManager.getConnection(ds.getDs_url(), 
					ds.getDs_user(), ds.getDs_password());//获取连接  
			PreparedStatement pst = conn.prepareStatement(CommonConstant.ORACLE_TEST_SQL);//准备执行语句
			ResultSet rs = pst.executeQuery();
			rs.next();
			if(!StringUtils.isEmpty(rs.getString(1))){
				res.setStatus(CommonConstant.SUCCESS_ST);
			}else{
				res.setStatus(CommonConstant.FAIL_ST);
				res.setErrorCode(ErrorCode.TEST_SQL_WRONG_RS);
				res.setMsg(ErrorCode.TEST_SQL_WRONG_RS_MSG);
			}
			pst.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setStatus(CommonConstant.FAIL_ST);
			res.setErrorCode(ErrorCode.CLASS_NO_FOUND_E);
			res.setMsg(ErrorCode.CLASS_NO_FOUND_E_MSG);
			res.setErrorStack(ExceptionUtils.getFullStackTrace(e));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setStatus(CommonConstant.FAIL_ST);
			res.setErrorCode(ErrorCode.SQL_EXCEPTION);
			res.setMsg(ErrorCode.SQL_EXCEPTION_MSG);
			res.setErrorStack(ExceptionUtils.getFullStackTrace(e));
		}
		
		return res;
	}
	
	@Override
	public Result<DataSourceInfo> listDataSource() {
		// TODO Auto-generated method stub
		Result<DataSourceInfo> res = new Result<DataSourceInfo>();
		List<DataSourceInfo> dsList = new ArrayList<DataSourceInfo>();
		dsList = dao.listDataSource();
		res.setResultSet(dsList);
		res.setStatus(CommonConstant.SUCCESS_ST);
		return res;
	}

	@Override
	public Result<TableInfo> listTable(DataSourceInfo ds){
		switch(ds.getDs_type()){
			case CommonConstant.MYSQL_DS:
				return getMySQLTablesByDs(ds);
			case CommonConstant.ORACLE_DS:
				return getOracleTablesByDs(ds);
			default:
				Result<TableInfo> res = new Result<TableInfo>();
				res.setStatus(CommonConstant.FAIL_ST);
				res.setErrorCode(ErrorCode.NO_MATCH_DS);
				res.setMsg(ErrorCode.NO_MATCH_DS_MSG);
				return res;
		}	
	}
	
	@Override
	public Result<ColumnInfo> listColumn(DataSourceInfo ds, TableInfo tab) {
		// TODO Auto-generated method stub
		switch(ds.getDs_type()){
			case CommonConstant.MYSQL_DS:
				return getMySQLColumnsByTable(ds, tab);
			case CommonConstant.ORACLE_DS:
				return getOracleColumnsByTable(ds, tab);
			default:
				Result<ColumnInfo> res = new Result<ColumnInfo>();
				res.setStatus(CommonConstant.FAIL_ST);
				res.setErrorCode(ErrorCode.NO_MATCH_DS);
				res.setMsg(ErrorCode.NO_MATCH_DS_MSG);
				return res;
		}
	}
	
	public Result<TableInfo> getMySQLTablesByDs(DataSourceInfo ds){
		Result<TableInfo> res = new Result<TableInfo>();
		List<TableInfo> list = new ArrayList<TableInfo>();
		
		try {
			Class.forName(CommonConstant.MYSQL_CLASS_NAME);
			//指定连接类型  
			Connection conn = DriverManager.getConnection(ds.getDs_url(), 
					ds.getDs_user(), ds.getDs_password());//获取连接  
			PreparedStatement pst = conn.prepareStatement(MYSQL_GET_TABLES_SQL);//准备执行语句
			String schema = getSchemaFromUrl(ds);
			pst.setString(1, schema);
			ResultSet rs = pst.executeQuery();
			rs.beforeFirst();
			while(rs.next()){
				TableInfo tableInfo = new TableInfo();
				tableInfo.setDs_id(ds.getDs_id());
				tableInfo.setDs_name(ds.getDs_name());
				tableInfo.setTable_schema(rs.getString(1));
				tableInfo.setTable_name(rs.getString(2));
				tableInfo.setTable_comment(rs.getString(3));
				list.add(tableInfo);
			}
			res.setResultSet(list);
			pst.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setStatus(CommonConstant.FAIL_ST);
			res.setErrorCode(ErrorCode.CLASS_NO_FOUND_E);
			res.setMsg(ErrorCode.CLASS_NO_FOUND_E_MSG);
			res.setErrorStack(ExceptionUtils.getFullStackTrace(e));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setStatus(CommonConstant.FAIL_ST);
			res.setErrorCode(ErrorCode.SQL_EXCEPTION);
			res.setMsg(ErrorCode.SQL_EXCEPTION_MSG);
			res.setErrorStack(ExceptionUtils.getFullStackTrace(e));
		}
		
		return res;
	}
	public Result<TableInfo> getOracleTablesByDs(DataSourceInfo ds){
		Result<TableInfo> res = new Result<TableInfo>();
		List<TableInfo> list = new ArrayList<TableInfo>();
		// ORACLE需要手动指定schema名对数据源加以区分
		if(ValidateUtil.isBlank(ds.getDs_schema())){
			res.setStatus(CommonConstant.FAIL_ST);
			res.setErrorCode(ErrorCode.DS_SCHEMA_NULL);
			res.setMsg(ErrorCode.DS_SCHEMA_NULL_MSG);
			return res;
		}
		
		try {
			Class.forName(CommonConstant.ORACLE_CLASS_NAME);
			//指定连接类型  
			Connection conn = DriverManager.getConnection(ds.getDs_url(), 
					ds.getDs_user(), ds.getDs_password());//获取连接  
			PreparedStatement pst = conn.prepareStatement(ORACLE_GET_TABLES_SQL);//准备执行语句
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				TableInfo tableInfo = new TableInfo();
				tableInfo.setDs_id(ds.getDs_id());
				tableInfo.setDs_name(ds.getDs_name());
				tableInfo.setTable_schema(ds.getDs_schema());
				tableInfo.setTable_name(rs.getString(1));
				tableInfo.setTable_comment(rs.getString(2));
				list.add(tableInfo);
			}
			res.setResultSet(list);
			pst.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setStatus(CommonConstant.FAIL_ST);
			res.setErrorCode(ErrorCode.CLASS_NO_FOUND_E);
			res.setMsg(ErrorCode.CLASS_NO_FOUND_E_MSG);
			res.setErrorStack(ExceptionUtils.getFullStackTrace(e));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setStatus(CommonConstant.FAIL_ST);
			res.setErrorCode(ErrorCode.SQL_EXCEPTION);
			res.setMsg(ErrorCode.SQL_EXCEPTION_MSG);
			res.setErrorStack(ExceptionUtils.getFullStackTrace(e));
		}
		
		return res;
	}
	public Result<ColumnInfo> getOracleColumnsByTable(DataSourceInfo ds,TableInfo tab){
		Result<ColumnInfo> res = new Result<ColumnInfo>();
		List<ColumnInfo> list = new ArrayList<ColumnInfo>();
		// ORACLE需要手动指定schema名对数据源加以区分
		if(ValidateUtil.isBlank(ds.getDs_schema())){
			res.setStatus(CommonConstant.FAIL_ST);
			res.setErrorCode(ErrorCode.DS_SCHEMA_NULL);
			res.setMsg(ErrorCode.DS_SCHEMA_NULL_MSG);
			return res;
		}
		if(!tab.getTable_schema().equals(ds.getDs_schema())){
			res.setStatus(CommonConstant.FAIL_ST);
			res.setErrorCode(ErrorCode.DS_SCHEMA_NULL);
			res.setMsg(ErrorCode.DS_SCHEMA_NULL_MSG);
			return res;
		}
		try {
			Class.forName(CommonConstant.ORACLE_CLASS_NAME);
			//指定连接类型  
			Connection conn = DriverManager.getConnection(ds.getDs_url(), 
					ds.getDs_user(), ds.getDs_password());//获取连接  
			PreparedStatement pst = conn.prepareStatement(ORACLE_GET_COLUMNS_SQL);//准备执行语句
			pst.setString(1, tab.getTable_name().toUpperCase());
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				ColumnInfo columnInfo = new ColumnInfo();
				columnInfo.setTable_name(rs.getString(1));
				columnInfo.setColumn_name(rs.getString(2));
				columnInfo.setData_type(rs.getString(3));
				columnInfo.setData_length(rs.getLong(4));
				columnInfo.setComments(rs.getString(5));
				list.add(columnInfo);
			}
			res.setResultSet(list);
			pst.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setStatus(CommonConstant.FAIL_ST);
			res.setErrorCode(ErrorCode.CLASS_NO_FOUND_E);
			res.setMsg(ErrorCode.CLASS_NO_FOUND_E_MSG);
			res.setErrorStack(ExceptionUtils.getFullStackTrace(e));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setStatus(CommonConstant.FAIL_ST);
			res.setErrorCode(ErrorCode.SQL_EXCEPTION);
			res.setMsg(ErrorCode.SQL_EXCEPTION_MSG);
			res.setErrorStack(ExceptionUtils.getFullStackTrace(e));
		}
		
		return res;
	}
	public Result<ColumnInfo> getMySQLColumnsByTable(DataSourceInfo ds,TableInfo tab){
		Result<ColumnInfo> res = new Result<ColumnInfo>();
		List<ColumnInfo> list = new ArrayList<ColumnInfo>();

		try {
			Class.forName(CommonConstant.MYSQL_CLASS_NAME);
			//指定连接类型  
			Connection conn = DriverManager.getConnection(ds.getDs_url(), 
					ds.getDs_user(), ds.getDs_password());//获取连接  
			PreparedStatement pst = conn.prepareStatement(MYSQL_GET_COLUMNS_SQL);//准备执行语句
			pst.setString(1, tab.getTable_name());
			ResultSet rs = pst.executeQuery();
			rs.first();
			while(rs.next()){
				ColumnInfo columnInfo = new ColumnInfo();
				columnInfo.setTable_name(rs.getString(1));
				columnInfo.setColumn_name(rs.getString(2));
				columnInfo.setData_type(rs.getString(3));
				columnInfo.setData_length(rs.getLong(4));
				columnInfo.setComments(rs.getString(5));
				list.add(columnInfo);
			}
			res.setResultSet(list);
			pst.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setStatus(CommonConstant.EXCEPTION_ST);
			res.setErrorCode(ErrorCode.CLASS_NO_FOUND_E);
			res.setMsg(ErrorCode.CLASS_NO_FOUND_E_MSG);
			res.setErrorStack(ExceptionUtils.getFullStackTrace(e));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setStatus(CommonConstant.EXCEPTION_ST);
			res.setErrorCode(ErrorCode.SQL_EXCEPTION);
			res.setMsg(ErrorCode.SQL_EXCEPTION_MSG);
			res.setErrorStack(ExceptionUtils.getFullStackTrace(e));
		}
		
		return res;
	}
	protected String getSchemaFromUrl(DataSourceInfo ds){
		String subUrl = ds.getDs_url().substring(0, ds.getDs_url().indexOf("?"));
		return subUrl.substring(subUrl.lastIndexOf("/")+1);
	}

	@Override
	public Result<String> getImportList(List<FieldInfo> fields, GenerateConfig config) {
		// TODO Auto-generated method stub
				switch(config.getDs().getDs_type()){
					case CommonConstant.MYSQL_DS:
//						return getMySQLImportList(ds, tab);
					case CommonConstant.ORACLE_DS:
						return getOracleImportList(fields, config);
					default:
						Result<String> res = new Result<String>();
						res.setStatus(CommonConstant.FAIL_ST);
						res.setErrorCode(ErrorCode.NO_MATCH_DS);
						res.setMsg(ErrorCode.NO_MATCH_DS_MSG);
						return res;
				}
	}
	Result<String> getOracleImportList(List<FieldInfo> fields, GenerateConfig config){
		Result<String> res = new Result<String>();
		List<String> importList = new ArrayList<String>();
		for(FieldInfo field:fields){
			if(field.getData_type().equals(CommonConstant.DATE_ORACLE_DATA_TYPE)&&
					!importList.contains(CommonConstant.DATE_JAVA_UTIL)){
				importList.add(CommonConstant.DATE_JAVA_UTIL);
			}
		}
		res.setResultSet(importList);
		res.setStatus(CommonConstant.SUCCESS_ST);
		return res;
	}
}
