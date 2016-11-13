package com.wuji1626.framework.constant;

public class ErrorCode {

	// 00000000~00009999 code generate
	// 没有匹配的数据源
	public static final String NO_MATCH_DS = "00000001";
	public static final String NO_MATCH_DS_MSG = "没有匹配的数据源";
	// test result unexpect
	public static final String TEST_SQL_WRONG_RS = "00000002";
	public static final String TEST_SQL_WRONG_RS_MSG = "测试语句没有返回预期结果";
	// can't find driver class
	public static final String CLASS_NO_FOUND_E = "00000003";
	public static final String CLASS_NO_FOUND_E_MSG = "未找到驱动类异常";
	// SQL exception
	public static final String SQL_EXCEPTION = "00000004";
	public static final String SQL_EXCEPTION_MSG = "SQL异常";
	// datasource's schema unsetted Oracle only
	public static final String DS_SCHEMA_NULL = "00000005";
	public static final String DS_SCHEMA_NULL_MSG = "数据源的schema为空";
	// unmatched schema and tablem
	public static final String DIFF_DS_TAB_SCHEMA = "00000006";
	public static final String DIFF_DS_TAB_SCHEMA_MSG = "数据源Schema与数据库Schema不一致";
	// output path unsetted
	public static final String NO_OUTPUT_PATH = "00000007";
	public static final String NO_OUTPUT_PATH_MSG = "未指定输出路径";
	// module name unsetted
	public static final String NO_MODULE_NAME = "00000008";
	public static final String NO_MODULE_NAME_MSG = "未指定模块名";
	
	
	
}
