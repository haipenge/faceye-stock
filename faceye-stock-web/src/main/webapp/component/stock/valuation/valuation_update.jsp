<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/stock/valuation/valuation.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/stock/valuation/valuation.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty valuation.id}">
					<fmt:message key="stock.valuation.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="stock.valuation.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/stock/valuation/save" method="post" role="form" cssClass="form-horizontal"
			commandName="valuation">
			<form:hidden path="id"/>
			<fieldset>
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