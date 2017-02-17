<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/financialData/financialData.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/financialData/financialData.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty financialData.id}">
					<fmt:message key="stock.financialData.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="stock.financialData.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
		<form:form action="/stock/financialData/save" method="post" role="form" cssClass="form-horizontal" commandName="financialData">
			<form:hidden path="id" />
			<fieldset>
				
				<div class="form-group">
					<label class="col-md-2 control-label" for="stockId"> <spring:message code="stock.financialData.stockId" />
					</label>
					<div class="col-md-6">
						<form:input path="stockId" cssClass="form-control" />
						<form:errors path="stockId" cssClass="error" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-2 control-label" for="accountingSubjectId"> <spring:message code="stock.financialData.accountingSubjectId" />
					</label>
					<div class="col-md-6">
						<form:input path="accountingSubjectId" cssClass="form-control" />
						<form:errors path="accountingSubjectId" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="data"> <spring:message code="stock.financialData.data" />
					</label>
					<div class="col-md-6">
						<form:input path="data" cssClass="form-control" />
						<form:errors path="data" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="date"> <spring:message code="stock.financialData.date" />
					</label>
					<div class="col-md-6">
						<form:input path="date" cssClass="form-control" />
						<form:errors path="date" cssClass="error" />
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