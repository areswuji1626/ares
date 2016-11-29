package ${package}.dao.impl;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ${package}.domain.${entityName};
import ${package}.dao.${entityName}Dao;

@Repository
public class ${entityName}DaoImpl implements ${entityName}Dao{

	public static final String GET_ALL_${entityName?upper_case} = "getAll${entityName}";
	public static final String GET_${entityName?upper_case}_BY_ID = "get${entityName}ById";
	public static final String INSERT_${entityName?upper_case} = "insert${entityName}";
	public static final String DELETE_${entityName?upper_case} = "delete${entityName}";

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<${entityName}> getAll${entityName}(){
		return sqlSessionTemplate.selectList(GET_ALL_${entityName?upper_case});
	}
	@Override
	public ${entityName} get${entityName}ById(${entityName} ${entityName?uncap_first}){
		return sqlSessionTemplate.selectOne(GET_${entityName?upper_case}_BY_ID, ${entityName?uncap_first});
	}

	<#if primaryKey??>
	@Override
	public String insert${entityName}(${entityName} ${entityName?uncap_first}){
		sqlSessionTemplate.insert(INSERT_${entityName?upper_case}, ${entityName?uncap_first});
		return ${entityName?uncap_first}.get${pkGetter?cap_first}();
	<#else>
	@Override
	public void insert${entityName}(${entityName} ${entityName?uncap_first}){
		sqlSessionTemplate.insert(INSERT_${entityName?upper_case}, ${entityName?uncap_first});
	</#if>
	}
	@Override
	public void delete${entityName}(${entityName} ${entityName?uncap_first}){
		sqlSessionTemplate.update(DELETE_${entityName?upper_case}, ${entityName?uncap_first});
	}
}