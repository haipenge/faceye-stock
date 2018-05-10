<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page isELIgnored="false"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/generate/project/project.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/generate/project/project.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty project.id}">
					<fmt:message key="generate.project.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="generate.project.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
		<form:form action="/generate/project/save" method="post" role="form" cssClass="form-horizontal" commandName="project">
			<form:hidden path="id" />
			<fieldset>
				<!-- 
				<div class="form-group">
					<form:errors path="*" cssStyle="color:red"></form:errors>
					<spring:hasBindErrors name="project">
						<c:if test="${errors.fieldErrorCount > 0}">
							<br />
							<c:forEach items="${errors.fieldErrors}" var="error">
								<spring:message var="message" code="${error.code}" arguments="${error.arguments}" text="${error.defaultMessage}" />
            ${error.field}------${message}<br />
							</c:forEach>
						</c:if>

						<c:if test="${errors.globalErrorCount > 0}">
							<br />
							<c:forEach items="${errors.globalErrors}" var="error">
								<spring:message var="message" code="${error.code}" arguments="${error.arguments}" text="${error.defaultMessage}" />
								<c:if test="${not empty message}">
                ${message}<br />
								</c:if>
							</c:forEach>
						</c:if>
					</spring:hasBindErrors>
				</div>
				 -->
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="generate.project.name" />
					</label>
					<div class="col-md-6">
						<form:input path="name" cssClass="form-control" />
						<form:errors path="name" cssClass="error" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-2 control-label" for="packageName"> <spring:message
							code="generate.project.packageName" />
					</label>
					<div class="col-md-6">
						<c:if test="${not empty project.packageName }">
							<form:input path="packageName" cssClass="form-control" readOnly="true" />
						</c:if>
						<c:if test="${ empty project.packageName }">
							<form:input path="packageName" cssClass="form-control" />
						</c:if>
						<form:errors path="packageName" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="repositoryType"> <spring:message code="generate.project.repositoryType" />
					</label>
					<div class="col-md-6">
						<form:input path="repositoryType" cssClass="form-control" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-2 control-label" for="author"> <spring:message code="generate.project.author" />
					</label>
					<div class="col-md-6">
						<form:input path="author" cssClass="form-control" />
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
		<!-- 
		<form action="<c:url value="/generate/project/save"/>" method="post" role="form" class="form-horizontal">
			<input type="hidden" name="id" value="${project.id}" />
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <fmt:message key="generate.project.name"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="name" value="${project.name}" class="form-control">
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-2 control-label" for="packageName"> <fmt:message key="generate.project.packageName"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="packageName" value="${project.packageName}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="author"> <fmt:message key="generate.project.author"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="author" value="${project.author}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="isGenerate"> <fmt:message key="generate.project.isGenerate"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="isGenerate" value="${project.isGenerate}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="isRun"> <fmt:message key="generate.project.isRun"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="isRun" value="${project.isRun}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="isPackage"> <fmt:message key="generate.project.isPackage"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="isPackage" value="${project.isPackage}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="global.submit.save"></fmt:message>
						</button>
					</div>
				</div>
			</fieldset>
		</form>
		 -->
	</div>
</div>
