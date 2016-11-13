<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package}。domain.${entityName}">

<select id="getAll${entityName}" resultType="${package}.domain.${entityName}">
	select ${fieldsStr}
	<#-- table name converts to lowercase -->
	from ${tab.table_name?lower_case}
	<#if orderStr??>
	order by ${orderStr}
	</#if>
</select>
	
</mapper>
  