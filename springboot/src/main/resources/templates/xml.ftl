<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${mapperClassPath}.${genContent.className}Mapper" >
  <resultMap id="BaseResultMap" type="${modelClassPath}.${genContent.className}" >
        <#list genContent.genColumns as column>
            <#if column.isPK ==1>
            <result id="${column.column}" property="${column.modelColumn}" jdbcType="${column.columnType}" />
            <#else>
            <result column="${column.column}" property="${column.modelColumn}" jdbcType="${column.columnType}" />
            </#if>
        </#list>
  </resultMap>

    <sql id="Base_Column_List" >
        <#list genContent.genColumns as column><#if column_index=0>${column.column}<#else>,${column.column}</#if></#list>
    </sql>


</mapper>