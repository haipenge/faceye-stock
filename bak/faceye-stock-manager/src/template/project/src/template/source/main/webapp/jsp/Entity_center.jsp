<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/@component.name@/@entity.config.name@/@entity.config.name@.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/@component.name@/@entity.config.name@/@entity.config.name@.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="@component.name@.@entity.config.name@.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/@component.name@/@entity.config.name@/input"/>"> <fmt:message
				key="@component.name@.@entity.config.name@.add"></fmt:message>
		</a>
	</h2>
</div>
<div class="cl-mcont">
	<!-- 
	<div class="header">
		<h2>
			<fmt:message key="security.role.manager"></fmt:message>
		</h2>
		<a class="btn btn-default" href="<c:url value="/security/role/input"/>"> <fmt:message key="security.role.add"></fmt:message>
		</a>
	</div>
	 -->
	<div class="block-flat">
	<c:import url="/component/core/template/msg/msg.jsp" />
		<div class="content">
			<form action="<c:url value="/@component.name@/@entity.config.name@/home"/>" method="post" role="form"
				class="form-horizontal">
				<fieldset>
					<div class="form-group">
						<!--@generate-entity-jsp-query-detail@-->
						<div class="col-md-1">
							<button type="submit" class="btn btn-sm btn-primary">
								<fmt:message key="global.search"></fmt:message>
							</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<div class="content">
	       <button class="btn btn-primary btn-sm multi-remove"><fmt:message key="global.remove"></fmt:message></button>
			<div classs="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
						   <th><input type="checkbox" name="check-all"></th>
							<!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.view"></fmt:message></th>
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="@entity.config.name@">
							<tr id="@entity.config.name@.id">
							   <td><input type="checkbox" name="check-single" value="${@entity.config.name@.id}"></td>
								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/@component.name@/@entity.config.name@/detail/${@entity.config.name@.id}"/>">
										<fmt:message key="global.view"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/@component.name@/@entity.config.name@/edit/${@entity.config.name@.id}"/>">
										<fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/@component.name@/@entity.config.name@/remove/${@entity.config.name@.id}"/>">
										<fmt:message key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/@component.name@/@entity.config.name@/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>
