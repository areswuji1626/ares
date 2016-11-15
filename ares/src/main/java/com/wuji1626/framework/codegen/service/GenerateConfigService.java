package com.wuji1626.framework.codegen.service;

import java.util.List;

import com.wuji1626.framework.codegen.domain.ColumnInfo;
import com.wuji1626.framework.codegen.domain.GenerateConfig;

public interface GenerateConfigService {

	List<ColumnInfo> getConditionFieldList(GenerateConfig config);
	List<ColumnInfo> getOrderFieldList(GenerateConfig config);
	GenerateConfig setConditionFieldList(GenerateConfig config, String columnName, boolean flag);
	GenerateConfig setOrderFieldList(GenerateConfig config, String columnName, String flag);
}
