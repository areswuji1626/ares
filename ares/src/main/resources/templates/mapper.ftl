<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package}。domain.${entityName}">

<select id="getAll${entityName}" resultType="${package}.domain.${entityName}">
	select ${selectFieldsStr}
	<#-- table name converts to lowercase -->
	from ${tab.table_name?lower_case}
	where del_flg != '01'
	<#if orderStr??>
	order by ${orderStr}
	</#if>
</select>
<select id="get${entityName}ById" parameterType="${package}.domain.${entityName}" resultType="${package}.domain.${entityName}">
	select ${selectFieldsStr}
	<#-- table name converts to lowercase -->
	from ${tab.table_name?lower_case}
	where del_flg != '01'
	and ( ${pkCondition} )
	<#if orderStr??>
	order by ${orderStr}
	</#if>
</select>
	
<insert id="insert${entityName}" parameterType="${package}.domain.${entityName}">
	<#if primaryKey??>
    <selectKey order="AFTER" keyProperty="${pkGetter?uncap_first}" resultType="java.lang.String">
		SELECT ${primaryKey} from ${tab.table_name?lower_case} order by modify_date limit 1;
	</selectKey>
	</#if>
    insert into ${tab.table_name?lower_case} ${insertStr}
</insert>
</mapper>
  