<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/crm/emplyee/emplyee.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/crm/emplyee/emplyee.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="crm.emplyee.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered">
			 <tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="crm.emplyee.name"></fmt:message></td>
	<td>${emplyee.name}</td>
</tr>
<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="crm.emplyee.age"></fmt:message></td>
	<td>${emplyee.age}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->


			</table>
		</div>
	</div>
</div>