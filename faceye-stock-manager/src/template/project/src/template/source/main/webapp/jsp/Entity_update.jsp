<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/@component.name@/@entity.config.name@/@entity.config.name@.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/@component.name@/@entity.config.name@/@entity.config.name@.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty @entity.config.name@.id}">
					<fmt:message key="@component.name@.@entity.config.name@.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="@component.name@.@entity.config.name@.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/@component.name@/@entity.config.name@/save" method="post" role="form" cssClass="form-horizontal"
			commandName="@entity.config.name@">
			<form:hidden path="id"/>
			<fieldset>
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
