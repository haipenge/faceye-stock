<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/stock/dataStat/dataStat.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/stock/dataStat/dataStat.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty dataStat.id}">
					<fmt:message key="stock.dataStat.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="stock.dataStat.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/stock/dataStat/save" method="post" role="form" cssClass="form-horizontal"
			commandName="dataStat">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
	<label class="col-md-2 control-label" for="stockId"> <spring:message
			code="stock.dataStat.stockId"/>
	</label>
	<div class="col-md-6">
		<form:input path="stockId" cssClass="form-control"/>
		<form:errors path="stockId" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="returnOnAssets"> <spring:message
			code="stock.dataStat.returnOnAssets"/>
	</label>
	<div class="col-md-6">
		<form:input path="returnOnAssets" cssClass="form-control"/>
		<form:errors path="returnOnAssets" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="dateCycle"> <spring:message
			code="stock.dataStat.dateCycle"/>
	</label>
	<div class="col-md-6">
		<form:input path="dateCycle" cssClass="form-control"/>
		<form:errors path="dateCycle" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="grossProfitMargin"> <spring:message
			code="stock.dataStat.grossProfitMargin"/>
	</label>
	<div class="col-md-6">
		<form:input path="grossProfitMargin" cssClass="form-control"/>
		<form:errors path="grossProfitMargin" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="netProfitMargin"> <spring:message
			code="stock.dataStat.netProfitMargin"/>
	</label>
	<div class="col-md-6">
		<form:input path="netProfitMargin" cssClass="form-control"/>
		<form:errors path="netProfitMargin" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="totalAssetsTurnover"> <spring:message
			code="stock.dataStat.totalAssetsTurnover"/>
	</label>
	<div class="col-md-6">
		<form:input path="totalAssetsTurnover" cssClass="form-control"/>
		<form:errors path="totalAssetsTurnover" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="totalAssetsNetProfitMargin"> <spring:message
			code="stock.dataStat.totalAssetsNetProfitMargin"/>
	</label>
	<div class="col-md-6">
		<form:input path="totalAssetsNetProfitMargin" cssClass="form-control"/>
		<form:errors path="totalAssetsNetProfitMargin" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="debtToAssetsRatio"> <spring:message
			code="stock.dataStat.debtToAssetsRatio"/>
	</label>
	<div class="col-md-6">
		<form:input path="debtToAssetsRatio" cssClass="form-control"/>
		<form:errors path="debtToAssetsRatio" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="roe"> <spring:message
			code="stock.dataStat.roe"/>
	</label>
	<div class="col-md-6">
		<form:input path="roe" cssClass="form-control"/>
		<form:errors path="roe" cssClass="error"/>
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