<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/generate/validate/validate.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/generate/validate/validate.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="generate.validate.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
			 <tr>
	<td><fmt:message key="generate.validate.name"></fmt:message></td>
	<td>${validate.name}</td>
</tr>
<tr>
	<td><fmt:message key="generate.validate.type"></fmt:message></td>
	<td>${validate.type}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->


			</table>
		</div>
	</div>
</div>
