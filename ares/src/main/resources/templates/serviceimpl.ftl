package ${package}.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuji1626.framework.db.DatabaseContextHolder;
import com.wuji1626.framework.db.DynamicDataSourceAble;
import com.wuji1626.framework.db.DynamicDataSourceAble;

import ${package}.domain.${entityName};
import ${package}.service.${entityName}Service;
import ${package}.dao.${entityName}Dao;

@Service("${entityName?uncap_first}Service")
@Transactional
public class ${entityName}ServiceImpl implements ${entityName}Service,DynamicDataSourceAble{

	@Autowired
	private ${entityName}Dao ${entityName?uncap_first}Dao;
	
	@Override
	public List<${entityName}> getAll${entityName}(){
		DatabaseContextHolder.setDBType(DATA_SOURCE_1);
		return ${entityName?uncap_first}Dao.getAll${entityName}();
	}
	@Override
	public ${entityName} get${entityName}ById(${entityName} ${entityName?uncap_first}){
		DatabaseContextHolder.setDBType(DATA_SOURCE_1);
		return ${entityName?uncap_first}Dao.get${entityName}ById(${entityName?uncap_first});
	}

	<#if primaryKey??>
	@Override
	public String insert${entityName}(${entityName} ${entityName?uncap_first}){
		DatabaseContextHolder.setDBType(DATA_SOURCE_1);
		return ${entityName?uncap_first}Dao.insert${entityName}(${entityName?uncap_first});
	<#else>
	@Override
	public void insert${entityName}(${entityName} ${entityName?uncap_first}){
		DatabaseContextHolder.setDBType(DATA_SOURCE_1);
		${entityName?uncap_first}Dao.insert${entityName}(${entityName?uncap_first});
	</#if>
	}
	
	@Override
	public void delete${entityName}(${entityName} ${entityName?uncap_first}){
		DatabaseContextHolder.setDBType(DATA_SOURCE_1);
		${entityName?uncap_first}Dao.delete${entityName}(${entityName?uncap_first});
	}
	
	@Override
	public void update${entityName}(${entityName} ${entityName?uncap_first}){
		DatabaseContextHolder.setDBType(DATA_SOURCE_1);
		${entityName?uncap_first}Dao.update${entityName}(${entityName?uncap_first});
	}
}