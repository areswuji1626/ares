package ${package}.dao;

import java.util.List;
import ${package}.domain.${entityName};

public interface ${entityName}Dao{
	public List<${entityName}> getAll${entityName}();
	public ${entityName} get${entityName}ById(${entityName} ${entityName?uncap_first});
	<#if primaryKey??>
	public String insert${entityName}(${entityName} ${entityName?uncap_first});
	<#else>
	public void insert${entityName}(${entityName} ${entityName?uncap_first});	
	</#if>
}
