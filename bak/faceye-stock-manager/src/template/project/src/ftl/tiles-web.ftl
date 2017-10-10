<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE tiles-definitions PUBLIC "-//Apache Software Foundation//DTD Tiles Configuration 3.0//EN" "http://tiles.apache.org/dtds/tiles-config_3_0.dtd">
<tiles-definitions>
    <!-- 模块:${component.name} - ${component.packageName} : 业务对像:${entity.name} - ${entity.className},web tiles模板配置 -->
    <!-- 作者:${project.author} -->
    <!-- 创建日期:${.now} -->
    <!-- ${entity.name} : ${entity.className} web 首页(列表页) -->
	<definition name="${component.packageName}.${entity.className?uncap_first}.manager" extends="default.web">
	    <put-attribute name="default-center" value="/component/${component.packageName}/${entity.className?uncap_first}/${entity.className?uncap_first}_center.jsp"></put-attribute>
	</definition>
	
	<!-- ${entity.name} 新增,编辑页面 -->
	<definition name="${component.packageName}.${entity.className?uncap_first}.update" extends="default.web">
	    <put-attribute name="default-center" value="/component/${component.packageName}/${entity.className?uncap_first}/${entity.className?uncap_first}_update.jsp"></put-attribute>
	</definition>
	
	<!-- ${entity.name} 明细页 -->
	<definition name="${component.packageName}.${entity.className?uncap_first}.detail" extends="default.web">
	    <put-attribute name="default-center" value="/component/${component.packageName}/${entity.className?uncap_first}/${entity.className?uncap_first}_detail.jsp"></put-attribute>
	</definition>
	
</tiles-definitions>
