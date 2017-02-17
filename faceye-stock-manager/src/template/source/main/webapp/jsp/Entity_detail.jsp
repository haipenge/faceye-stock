<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/@component.name@/@entity.config.name@/@entity.config.name@.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/@component.name@/@entity.config.name@/@entity.config.name@.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="@component.name@.@entity.config.name@.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
			 <!--@generate-entity-jsp-property-detail@-->
			</table>
		</div>
	</div>
	<div class="content">
	  <a href="<c:url value="/@component.name@/@entity.config.name@/edit/${@entity.config.name@}.id"/>" class="btn btn-sm btn-primary"><fmt:message key="global.edit"/></a>
	  <a href="<c:url value="/@component.name@/@entity.config.name@/remove/${@entity.config.name@}.id"/>" class="btn btn-sm btn-danger"><fmt:message key="global.remove"/></a>
	  <a href="<c:url value="/@component.name@/@entity.config.name@/home"/>" class="btn btn-sm btn-info"><fmt:message key="global.back"/></a>
	</div>
</div>
