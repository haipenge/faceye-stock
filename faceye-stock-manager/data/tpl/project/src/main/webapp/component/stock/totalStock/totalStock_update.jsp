<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/stock/totalStock/totalStock.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/stock/totalStock/totalStock.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty totalStock.id}">
					<fmt:message key="stock.totalStock.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="stock.totalStock.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/stock/totalStock/save" method="post" role="form" cssClass="form-horizontal"
			commandName="totalStock">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
	<label class="col-md-2 control-label" for="stockId"> <spring:message
			code="stock.totalStock.stockId"/>
	</label>
	<div class="col-md-6">
		<form:input path="stockId" cssClass="form-control"/>
		<form:errors path="stockId" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="stockNum"> <spring:message
			code="stock.totalStock.stockNum"/>
	</label>
	<div class="col-md-6">
		<form:input path="stockNum" cssClass="form-control"/>
		<form:errors path="stockNum" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="changeDate"> <spring:message
			code="stock.totalStock.changeDate"/>
	</label>
	<div class="col-md-6">
		<form:input path="changeDate" cssClass="form-control"/>
		<form:errors path="changeDate" cssClass="error"/>
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