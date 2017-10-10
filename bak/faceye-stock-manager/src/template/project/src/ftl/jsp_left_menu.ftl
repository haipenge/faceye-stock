<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<li>
<#list components as component>
    <a href="#"><i class="fa fa-file"></i><span>${component.name}</span></a>
		<ul class="sub-menu">
		<#list entities as entity>
			<#if "${entity.component.id}" == "${component.id}">
				<li class="<%=JspUtil.isActive(request, "/${entity.component.packageName}/${entity.className?uncap_first}")%>">
					<a href="/${entity.component.packageName}/${entity.className?uncap_first}/home"><fmt:message key="${entity.component.packageName}.${entity.className?uncap_first}.manager"/></a>
				</li>
			 </#if>
		</#list>
		</ul>
</#list>
</li>

<li><a href="#"><i class="fa fa-smile-o"></i><span>Generater Component</span></a>
	<ul class="sub-menu">
	<!--
		<li class="<%=JspUtil.isActive(request, "/spider/link")%>"><a href="/spider/link/home"><fmt:message key="spider.link.manager"></fmt:message></a></li>
		-->
		<!--@generate-entity-manager-list-group-item@-->
	</ul>
</li>
