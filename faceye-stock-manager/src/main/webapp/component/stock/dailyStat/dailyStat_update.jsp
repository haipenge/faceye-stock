<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/stock/dailyStat/dailyStat.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/stock/dailyStat/dailyStat.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty dailyStat.id}">
					<fmt:message key="stock.dailyStat.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="stock.dailyStat.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/stock/dailyStat/save" method="post" role="form" cssClass="form-horizontal"
			commandName="dailyStat">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
	<label class="col-md-2 control-label" for="stockId"> <spring:message
			code="stock.dailyStat.stockId"/>
	</label>
	<div class="col-md-6">
		<form:input path="stockId" cssClass="form-control"/>
		<form:errors path="stockId" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="topPriceOf30Day"> <spring:message
			code="stock.dailyStat.topPriceOf30Day"/>
	</label>
	<div class="col-md-6">
		<form:input path="topPriceOf30Day" cssClass="form-control"/>
		<form:errors path="topPriceOf30Day" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="lowerPriceOf30Day"> <spring:message
			code="stock.dailyStat.lowerPriceOf30Day"/>
	</label>
	<div class="col-md-6">
		<form:input path="lowerPriceOf30Day" cssClass="form-control"/>
		<form:errors path="lowerPriceOf30Day" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="pe"> <spring:message
			code="stock.dailyStat.pe"/>
	</label>
	<div class="col-md-6">
		<form:input path="pe" cssClass="form-control"/>
		<form:errors path="pe" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="topPriceDate"> <spring:message
			code="stock.dailyStat.topPriceDate"/>
	</label>
	<div class="col-md-6">
		<form:input path="topPriceDate" cssClass="form-control"/>
		<form:errors path="topPriceDate" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="lowerPriceDate"> <spring:message
			code="stock.dailyStat.lowerPriceDate"/>
	</label>
	<div class="col-md-6">
		<form:input path="lowerPriceDate" cssClass="form-control"/>
		<form:errors path="lowerPriceDate" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="categoryId"> <spring:message
			code="stock.dailyStat.categoryId"/>
	</label>
	<div class="col-md-6">
		<form:input path="categoryId" cssClass="form-control"/>
		<form:errors path="categoryId" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="dynamicPe"> <spring:message
			code="stock.dailyStat.dynamicPe"/>
	</label>
	<div class="col-md-6">
		<form:input path="dynamicPe" cssClass="form-control"/>
		<form:errors path="dynamicPe" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="pb"> <spring:message
			code="stock.dailyStat.pb"/>
	</label>
	<div class="col-md-6">
		<form:input path="pb" cssClass="form-control"/>
		<form:errors path="pb" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="todayPrice"> <spring:message
			code="stock.dailyStat.todayPrice"/>
	</label>
	<div class="col-md-6">
		<form:input path="todayPrice" cssClass="form-control"/>
		<form:errors path="todayPrice" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="priceAmplitude"> <spring:message
			code="stock.dailyStat.priceAmplitude"/>
	</label>
	<div class="col-md-6">
		<form:input path="priceAmplitude" cssClass="form-control"/>
		<form:errors path="priceAmplitude" cssClass="error"/>
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