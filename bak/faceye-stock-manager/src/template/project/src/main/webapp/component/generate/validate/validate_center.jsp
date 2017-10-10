<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/generate/validate/validate.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/generate/validate/validate.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="generate.validate.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/generate/validate/input"/>"> <fmt:message
				key="generate.validate.add"></fmt:message>
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
		<div class="content">
			<form action="<c:url value="/generate/validate/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">

						<div class="col-md-1">
							<input type="text" name="EQ|name" value="${searchParams.name}"
								placeholder="<fmt:message key="generate.validate.name"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|type" value="${searchParams.type}"
								placeholder="<fmt:message key="generate.validate.type"></fmt:message>" class="form-control input-sm">
						</div>
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
			<div id="msg"></div>
			<button class="btn btn-primary btn-sm">
				<fmt:message key="global.remove"></fmt:message>
			</button>
			<div classs="table-responsive">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th><input type="checkbox" name="check-all"></th>
							<th><fmt:message key='generate.validate.name'></fmt:message></th>
							<th><fmt:message key='generate.validate.type'></fmt:message></th>
							<!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="validate">
							<tr>
								<td><input type="checkbox" name="check-single" value="${validate.id}"></td>
								<td>${validate.name}</td>
								<td>${validate.type}</td>
								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/generate/validate/edit/${validate.id}"/>"> <fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/generate/validate/remove/${validate.id}"/>"> <fmt:message
											key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/generate/validate/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>
