<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/stock/starDataStat/starDataStat.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/stock/starDataStat/starDataStat.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty starDataStat.id}">
					<fmt:message key="stock.starDataStat.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="stock.starDataStat.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/stock/starDataStat/save" method="post" role="form" cssClass="form-horizontal"
			commandName="starDataStat">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
	<label class="col-md-2 control-label" for="stockId"> <spring:message
			code="stock.starDataStat.stockId"/>
	</label>
	<div class="col-md-6">
		<form:input path="stockId" cssClass="form-control"/>
		<form:errors path="stockId" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="avgDataStat"> <spring:message
			code="stock.starDataStat.avgDataStat"/>
	</label>
	<div class="col-md-6">
		<form:input path="avgDataStat" cssClass="form-control"/>
		<form:errors path="avgDataStat" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="avgDataStatDate"> <spring:message
			code="stock.starDataStat.avgDataStatDate"/>
	</label>
	<div class="col-md-6">
		<form:input path="avgDataStatDate" cssClass="form-control"/>
		<form:errors path="avgDataStatDate" cssClass="error"/>
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