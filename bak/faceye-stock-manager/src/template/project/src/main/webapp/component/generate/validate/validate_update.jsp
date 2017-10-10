<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/generate/validate/validate.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/generate/validate/validate.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty validate.id}">
					<fmt:message key="generate.validate.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="generate.validate.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/generate/validate/save" method="post" role="form" cssClass="form-horizontal"
			commandName="validate">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <fmt:message key="generate.validate.name"></fmt:message>
					</label>
					<div class="col-md-6">
						<form:input path="name" cssClass="form-control"/>
						<form:errors path="name" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="type"> <fmt:message key="generate.validate.type"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="type" value="${validate.type}" class="form-control">
						<from:input path="type" cssClass="form-control"/>
						<form:errors path="type" cssClass="error"/>
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
