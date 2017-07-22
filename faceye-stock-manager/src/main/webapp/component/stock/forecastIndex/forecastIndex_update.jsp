<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/stock/forecastIndex/forecastIndex.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/stock/forecastIndex/forecastIndex.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty forecastIndex.id}">
					<fmt:message key="stock.forecastIndex.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="stock.forecastIndex.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/stock/forecastIndex/save" method="post" role="form" cssClass="form-horizontal"
			commandName="forecastIndex">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
	<label class="col-md-2 control-label" for="reportDate"> <spring:message
			code="stock.forecastIndex.reportDate"/>
	</label>
	<div class="col-md-6">
		<form:input path="reportDate" cssClass="form-control"/>
		<form:errors path="reportDate" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="mechanism"> <spring:message
			code="stock.forecastIndex.mechanism"/>
	</label>
	<div class="col-md-6">
		<form:input path="mechanism" cssClass="form-control"/>
		<form:errors path="mechanism" cssClass="error"/>
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