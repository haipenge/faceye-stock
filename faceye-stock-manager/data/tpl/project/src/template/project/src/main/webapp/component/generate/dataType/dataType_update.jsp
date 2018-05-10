<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/generate/dataType/dataType.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/generate/dataType/dataType.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty dataType.id}">
					<fmt:message key="generate.dataType.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="generate.dataType.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
		<form action="<c:url value="/generate/dataType/save"/>" method="post" role="form" class="form-horizontal">
			<input type="hidden" name="id" value="${dataType.id}" />
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <fmt:message key="generate.dataType.name"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="name" value="${dataType.name}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="typeClass"> <fmt:message key="generate.dataType.typeClass"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="typeClass" value="${dataType.typeClass}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="shortTypeClass"> <fmt:message
							key="generate.dataType.shortTypeClass"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="shortTypeClass" value="${dataType.shortTypeClass}" class="form-control">
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
		</form>
	</div>
</div>
