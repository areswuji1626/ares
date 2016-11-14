package com.wuji1626.framework.constant;

public class CommonConstant {

	// status
	public static final String SUCCESS_ST = "000000";
	public static final String FAIL_ST = "000001";
	public static final String EXCEPTION_ST = "000002";
	
	public static final String NEW_LINE = "\n";
	public static final String FILESYS_SEPERATOR = "//";
	public static final String CONNECTOR = "%||%";
	public static final String COMMA = ",";
	public static final String EQUAL = "=";
	public static final String BRANKET_L = "(";
	public static final String BRANKET_R = ")";
	public static final String BLANK = " ";
	public static final String WELL = "#";
	public static final String BRACE_L = "{";
	public static final String BRACE_R = "}";
	
	public static final String OS = "os";
	
	public static final String WINDOWS = "WINDOWS";
	public static final String LINUX = "LINUX";
	
	// Code Class Key
	public static final String DS_TYPE_CLASS_KEY = "ds_type";
	public static final String USER_TYPE_CLASS_KEY = "user_type";
	public static final String GENDER_CLASS_KEY = "gender";
	
	// ds type
	public static final String MYSQL_DS = "MySQL";
	public static final String ORACLE_DS = "Oracle";
	
	// ds Class Name
	public static final String MYSQL_CLASS_NAME = "com.mysql.jdbc.Driver";
	public static final String MYSQL_TEST_SQL = "select 1";

	public static final String ORACLE_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
	public static final String ORACLE_TEST_SQL = "select 1 from dual";
	
	// Generate Type
	public static final String BEAN_OUTPUT_TYPE = "domain";
	public static final String MAPPER_OUTPUT_TYPE = "mapper";
	public static final String DAO_OUTPUT_TYPE = "dao";
	public static final String DAO_IMPL_OUTPUT_TYPE = "daoimpl";
	
	public static final String IMPL_PATH = "impl";
	
	// Order Type
	public static final String DESC = "desc";
	public static final String ASC = "asc";
	
	// ORACLE Data Type
	public static final String DATE_ORACLE_DATA_TYPE = "Date";
	
	public static final String DATE_JAVA_UTIL = "java.util.Date";
	
}
