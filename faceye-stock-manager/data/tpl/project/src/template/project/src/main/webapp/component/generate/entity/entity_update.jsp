<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/generate/entity/entity.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/generate/entity/entity.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty entity.id}">
					<fmt:message key="generate.entity.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="generate.entity.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
		<form:form action="/generate/entity/save" method="post" role="form" cssClass="form-horizontal" commandName="entity">
			<form:hidden path="id" />
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <fmt:message key="generate.entity.name"></fmt:message>
					</label>
					<div class="col-md-6">
						<form:input path="name" cssClass="form-control" />
						<form:errors path="name" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="className"> <fmt:message key="generate.entity.className"></fmt:message>
					</label>
					<div class="col-md-6">
						<form:input path="className" cssClass="form-control" />
						<form:errors path="className" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="componentId"> <fmt:message key="generate.entity.componentId"></fmt:message>
					</label>
					<div class="col-md-6">
					   <c:choose>
					     
					     <c:when test="${not empty entity.component }">
					        <form:hidden path="component.id"/>
					         ${entity.component.name}
					     </c:when>
					     <c:otherwise>
					       <form:select path="component.id" cssClass="form-control">
							<form:option value="0" label="--Please Select Compane" />
							<form:options items="${components}" itemValue="id" itemLabel="name" />
						</form:select>
					     </c:otherwise>
					   </c:choose>
						
						<form:errors path="component.id" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="remark"> <fmt:message key="generate.entity.remark"></fmt:message>
					</label>
					<div class="col-md-6">
						<form:input path="remark" cssClass="form-control" />
						<form:errors path="remark" cssClass="error"/>
					</div>
				</div>

				<!--@generate-entity-jsp-property-update@-->





				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="global.submit.save"></fmt:message>
						</button>
					</div>
				</div>
			</fieldset>
			</form:form>
	</div>
</div>
