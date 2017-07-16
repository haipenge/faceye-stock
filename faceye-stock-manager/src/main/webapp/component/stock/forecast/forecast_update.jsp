<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/stock/forecast/forecast.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/stock/forecast/forecast.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty forecast.id}">
					<fmt:message key="stock.forecast.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="stock.forecast.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/stock/forecast/save" method="post" role="form" cssClass="form-horizontal"
			commandName="forecast">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
	<label class="col-md-2 control-label" for="stockId"> <spring:message
			code="stock.forecast.stockId"/>
	</label>
	<div class="col-md-6">
		<form:input path="stockId" cssClass="form-control"/>
		<form:errors path="stockId" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="eps"> <spring:message
			code="stock.forecast.eps"/>
	</label>
	<div class="col-md-6">
		<form:input path="eps" cssClass="form-control"/>
		<form:errors path="eps" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="profit"> <spring:message
			code="stock.forecast.profit"/>
	</label>
	<div class="col-md-6">
		<form:input path="profit" cssClass="form-control"/>
		<form:errors path="profit" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="income"> <spring:message
			code="stock.forecast.income"/>
	</label>
	<div class="col-md-6">
		<form:input path="income" cssClass="form-control"/>
		<form:errors path="income" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="roe"> <spring:message
			code="stock.forecast.roe"/>
	</label>
	<div class="col-md-6">
		<form:input path="roe" cssClass="form-control"/>
		<form:errors path="roe" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="year"> <spring:message
			code="stock.forecast.year"/>
	</label>
	<div class="col-md-6">
		<form:input path="year" cssClass="form-control"/>
		<form:errors path="year" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="reportDate"> <spring:message
			code="stock.forecast.reportDate"/>
	</label>
	<div class="col-md-6">
		<form:input path="reportDate" cssClass="form-control"/>
		<form:errors path="reportDate" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="mechanism"> <spring:message
			code="stock.forecast.mechanism"/>
	</label>
	<div class="col-md-6">
		<form:input path="mechanism" cssClass="form-control"/>
		<form:errors path="mechanism" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="researcher"> <spring:message
			code="stock.forecast.researcher"/>
	</label>
	<div class="col-md-6">
		<form:input path="researcher" cssClass="form-control"/>
		<form:errors path="researcher" cssClass="error"/>
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