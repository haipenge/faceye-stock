<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/generate/component/component.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/generate/component/component.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty component.id}">
					<fmt:message key="generate.component.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="generate.component.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
		<form:form action="/generate/component/save" method="post" role="form" cssClass="form-horizontal" commandName="component">
			<form:hidden path="id" />
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <fmt:message key="generate.component.name"></fmt:message>
					</label>
					<div class="col-md-6">
						<form:input path="name" cssClass="form-control" />
						<form:errors path="name" cssClass="error" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-2 control-label" for="projectId"> <fmt:message key="generate.component.projectId"></fmt:message>
					</label>
					<div class="col-md-6">
						<form:select path="project.id" cssClass="form-control">
							<form:option value="0" label="--Please Select Project" />
							<form:options items="${projects}" itemValue="id" itemLabel="name" />
						</form:select>
						<form:errors path="project" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="packageName"> <fmt:message key="generate.component.packageName"></fmt:message>
					</label>
					<div class="col-md-6">
						<form:input path="packageName" cssClass="form-control" />
						<form:errors path="packageName" cssClass="error" />
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
		<form action="<c:url value="/generate/component/save"/>" method="post" role="form" class="form-horizontal">
			<input type="hidden" name="id" value="${component.id}" />
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <fmt:message key="generate.component.name"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="name" value="${component.name}" class="form-control">
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-2 control-label" for="projectId"> <fmt:message key="generate.component.projectId"></fmt:message>
					</label>
					<div class="col-md-6">
						<select name="project.id" class="form-control">
							<option value="0">Select Project</option>
							<c:forEach items="${projects}" var="project">
								<option value="${project.id}" <c:if test="${project.id eq component.project.id }">selected</c:if>>${project.name }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="packageName"> <fmt:message key="generate.component.packageName"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="packageName" value="${component.packageName}" class="form-control">
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
