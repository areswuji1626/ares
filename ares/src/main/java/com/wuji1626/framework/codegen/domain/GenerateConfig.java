package com.wuji1626.framework.codegen.domain;

import java.io.Serializable;
import java.util.List;

public class GenerateConfig implements Serializable {

	private static final long serialVersionUID = -1736041912853291600L;
	
	private String packageName;
	private String entityName;
	private List<String> importPackageList;
	private List<ColumnInfo> columnList;
	private DataSourceInfo ds;
	private TableInfo tab;
	private String outputPath;
	private String outputType;
	// 排序字段
	private List<ColumnInfo> orderColumnList;
	// 查询条件字段
	private List<ColumnInfo> conditionColumnList;
	public List<ColumnInfo> getOrderColumnList() {
		return orderColumnList;
	}
	public void setOrderColumnList(List<ColumnInfo> orderColumnList) {
		this.orderColumnList = orderColumnList;
	}
	// 操作列表
	private List<String> operationList;
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public List<ColumnInfo> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<ColumnInfo> columnList) {
		this.columnList = columnList;
	}
	public List<String> getImportPackageList() {
		return importPackageList;
	}
	public void setImportPackageList(List<String> importPackageList) {
		this.importPackageList = importPackageList;
	}
	public DataSourceInfo getDs() {
		return ds;
	}
	public void setDs(DataSourceInfo ds) {
		this.ds = ds;
	}
	public String getOutputPath() {
		return outputPath;
	}
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}
	public List<String> getOperationList() {
		return operationList;
	}
	public void setOperationList(List<String> operationList) {
		this.operationList = operationList;
	}
	public TableInfo getTab() {
		return tab;
	}
	public void setTab(TableInfo tab) {
		this.tab = tab;
	}
	public List<ColumnInfo> getConditionColumnList() {
		return conditionColumnList;
	}
	public void setConditionColumnList(List<ColumnInfo> conditionColumnList) {
		this.conditionColumnList = conditionColumnList;
	}
	public String getOutputType() {
		return outputType;
	}
	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}
	
}
