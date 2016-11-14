package ${package}.domain;

import java.io.Serializable;
<#if importPackageList??>
<#list importPackageList as importPackage>
import ${importPackage};
</#list>
</#if>

public class ${entityName} implements Serializable {
	<#list fieldList as field>
	<#if field.comments??>
	/** ${field.comments} */
	</#if>
	private ${field.data_type} ${field.field_name};
	</#list>
	
	<#list fieldList as field>
	public ${field.data_type} get${field.field_name?cap_first}(){
		return ${field.field_name};
	}
	public void set${field.field_name?cap_first}(${field.data_type} ${field.field_name}){
		this.${field.field_name} = ${field.field_name};
	}
	</#list>
}