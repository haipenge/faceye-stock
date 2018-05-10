<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/stock/bonusRecord/bonusRecord.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/stock/bonusRecord/bonusRecord.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty bonusRecord.id}">
					<fmt:message key="stock.bonusRecord.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="stock.bonusRecord.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/stock/bonusRecord/save" method="post" role="form" cssClass="form-horizontal"
			commandName="bonusRecord">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
	<label class="col-md-2 control-label" for="publishDate"> <spring:message
			code="stock.bonusRecord.publishDate"/>
	</label>
	<div class="col-md-6">
		<form:input path="publishDate" cssClass="form-control"/>
		<form:errors path="publishDate" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="giveStockCount"> <spring:message
			code="stock.bonusRecord.giveStockCount"/>
	</label>
	<div class="col-md-6">
		<form:input path="giveStockCount" cssClass="form-control"/>
		<form:errors path="giveStockCount" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="increaseStockCount"> <spring:message
			code="stock.bonusRecord.increaseStockCount"/>
	</label>
	<div class="col-md-6">
		<form:input path="increaseStockCount" cssClass="form-control"/>
		<form:errors path="increaseStockCount" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="dividend"> <spring:message
			code="stock.bonusRecord.dividend"/>
	</label>
	<div class="col-md-6">
		<form:input path="dividend" cssClass="form-control"/>
		<form:errors path="dividend" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="status"> <spring:message
			code="stock.bonusRecord.status"/>
	</label>
	<div class="col-md-6">
		<form:input path="status" cssClass="form-control"/>
		<form:errors path="status" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="exDividendDate"> <spring:message
			code="stock.bonusRecord.exDividendDate"/>
	</label>
	<div class="col-md-6">
		<form:input path="exDividendDate" cssClass="form-control"/>
		<form:errors path="exDividendDate" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="equityRegistrationDate"> <spring:message
			code="stock.bonusRecord.equityRegistrationDate"/>
	</label>
	<div class="col-md-6">
		<form:input path="equityRegistrationDate" cssClass="form-control"/>
		<form:errors path="equityRegistrationDate" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="bonusShareTradingDate"> <spring:message
			code="stock.bonusRecord.bonusShareTradingDate"/>
	</label>
	<div class="col-md-6">
		<form:input path="bonusShareTradingDate" cssClass="form-control"/>
		<form:errors path="bonusShareTradingDate" cssClass="error"/>
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