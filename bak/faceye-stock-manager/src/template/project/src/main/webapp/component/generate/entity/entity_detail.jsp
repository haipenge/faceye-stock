<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/generate/entity/entity.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/generate/entity/entity.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="generate.entity.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
				<tr>
					<td><fmt:message key="generate.entity.name"></fmt:message></td>
					<td>${entity.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="generate.entity.className"></fmt:message></td>
					<td>${entity.className}</td>
				</tr>
				<tr>
					<td><fmt:message key="generate.entity.componentId"></fmt:message></td>
					<td>${entity.component.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="generate.entity.remark"></fmt:message></td>
					<td>${entity.remark}</td>
				</tr>
				<tr>
					<td><fmt:message key="generate.entity.createDate"></fmt:message></td>
					<td>${entity.createDate}</td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->





			</table>
		</div>
	</div>
</div>
