package com.wuji1626.framework.constant;

public class ErrorCode {

	// 00000000~00009999 为代码生成模块错误代码
	// 没有匹配的数据源
	public static final String NO_MATCH_DS = "00000001";
	public static final String NO_MATCH_DS_MSG = "没有匹配的数据源";
	// 测试语句没有返回预期结果
	public static final String TEST_SQL_WRONG_RS = "00000002";
	public static final String TEST_SQL_WRONG_RS_MSG = "测试语句没有返回预期结果";
	// 未找到驱动类异常
	public static final String CLASS_NO_FOUND_E = "00000003";
	public static final String CLASS_NO_FOUND_E_MSG = "未找到驱动类异常";
	// SQL异常
	public static final String SQL_EXCEPTION = "00000004";
	public static final String SQL_EXCEPTION_MSG = "SQL异常";
	// 数据源未指定schema，Oracle数据源有效
	public static final String DS_SCHEMA_NULL = "00000005";
	public static final String DS_SCHEMA_NULL_MSG = "数据源的schema为空";
	// schema与表所对应的schema不一致
	public static final String DIFF_DS_TAB_SCHEMA = "00000006";
	public static final String DIFF_DS_TAB_SCHEMA_MSG = "数据源Schema与数据库Schema不一致";
	// 未指定输出路径
	public static final String NO_OUTPUT_PATH = "00000007";
	public static final String NO_OUTPUT_PATH_MSG = "未指定输出路径";
}
