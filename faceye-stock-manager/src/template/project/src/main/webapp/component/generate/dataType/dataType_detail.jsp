<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/generate/dataType/dataType.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/generate/dataType/dataType.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="generate.dataType.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
			 <tr>
	<td><fmt:message key="generate.dataType.name"></fmt:message></td>
	<td>${dataType.name}</td>
</tr>
<tr>
	<td><fmt:message key="generate.dataType.typeClass"></fmt:message></td>
	<td>${dataType.typeClass}</td>
</tr>
<tr>
	<td><fmt:message key="generate.dataType.shortTypeClass"></fmt:message></td>
	<td>${dataType.shortTypeClass}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->




			</table>
		</div>
	</div>
</div>
