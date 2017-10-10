<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/generate/component/component.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/generate/component/component.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="generate.component.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
				<tr>
					<td><fmt:message key="generate.component.name"></fmt:message></td>
					<td>${component.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="generate.component.createDate"></fmt:message></td>
					<td>${component.createDate}</td>
				</tr>
				<tr>
					<td><fmt:message key="generate.component.projectId"></fmt:message></td>
					<td>${component.projectId}</td>
				</tr>
				<tr>
					<td><fmt:message key="generate.component.packageName"></fmt:message></td>
					<td>${component.packageName}</td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->




			</table>
		</div>
	</div>
</div>
