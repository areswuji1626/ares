package com.wuji1626.framework.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
//		System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJ" +DatabaseContextHolder.getDBType());
		return DatabaseContextHolder.getDBType();   
	}

}
