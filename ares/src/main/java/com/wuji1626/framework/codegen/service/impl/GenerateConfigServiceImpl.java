package com.wuji1626.framework.codegen.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuji1626.framework.codegen.domain.ColumnInfo;
import com.wuji1626.framework.codegen.domain.GenerateConfig;
import com.wuji1626.framework.codegen.service.GenerateConfigService;
import com.wuji1626.framework.utils.ValidateUtil;

@Service("generateConfigService")
@Transactional
public class GenerateConfigServiceImpl implements GenerateConfigService {

	@Override
	public List<ColumnInfo> getConditionFieldList(GenerateConfig config) {
		// TODO Auto-generated method stub
		List<ColumnInfo> conditionList = new ArrayList<ColumnInfo>();
		for(ColumnInfo column:config.getColumnList()){
			if(column.isCondition()){
				conditionList.add(column);
			}
		}
		return conditionList;
	}

	@Override
	public List<ColumnInfo> getOrderFieldList(GenerateConfig config) {
		// TODO Auto-generated method stub
		List<ColumnInfo> orderList = new ArrayList<ColumnInfo>();
		for(ColumnInfo column:config.getColumnList()){
			if(!ValidateUtil.isBlank(column.getOrder_flg())){
				orderList.add(column);
			}
		}
		return orderList;
	}

	@Override
	public GenerateConfig setConditionFieldList(GenerateConfig config, String columnName, boolean flag) {
		// TODO Auto-generated method stub
		for(ColumnInfo column:config.getColumnList()){
			if(column.getColumn_name().equals(columnName)){
				column.setCondition(flag);
			}
		}
		return config;
	}

	@Override
	public GenerateConfig setOrderFieldList(GenerateConfig config, String columnName, String flag) {
		// TODO Auto-generated method stub
		for(ColumnInfo column:config.getColumnList()){
			if(column.getColumn_name().equals(columnName)){
				column.setOrder_flg(flag);;
			}
		}
		return config;
	}

}
