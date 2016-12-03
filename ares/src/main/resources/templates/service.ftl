package ${package}.service;

import java.util.List;
import ${package}.domain.${entityName};

public interface ${entityName}Service{
	public List<${entityName}> getAll${entityName}();
	public ${entityName} get${entityName}ById(${entityName} ${entityName?uncap_first});
	<#if primaryKey??>
	public String insert${entityName}(${entityName} ${entityName?uncap_first});
	<#else>
	public void insert${entityName}(${entityName} ${entityName?uncap_first});	
	</#if>
	public void delete${entityName}(${entityName} ${entityName?uncap_first});
	public void update${entityName}(${entityName} ${entityName?uncap_first});
}
