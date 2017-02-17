<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/accountingElement/accountingElement.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/accountingElement/accountingElement.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty accountingElement.id}">
					<fmt:message key="stock.accountingElement.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="stock.accountingElement.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
		<form:form action="/stock/accountingElement/save" method="post" role="form" cssClass="form-horizontal" commandName="accountingElement">
			<form:hidden path="id" />
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="stock.accountingElement.name" />
					</label>
					<div class="col-md-6">
						<form:input path="name" cssClass="form-control" />
						<form:errors path="name" cssClass="error" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-2 control-label" for="reportCategory.id"> <spring:message code="stock.reportCategory" />
					</label>
					<div class="col-md-6">
						<c:choose>
							<c:when test="${not empty reportCategories }">
								<form:select path="reportCategory.id" cssClass="form-control">
									<form:option value="0" label="选择会计报表" />
									<form:options items="${reportCategories}" itemValue="id" itemLabel="name" />
								</form:select>
							</c:when>
							<c:otherwise>
								<form:hidden path="reportCategory.id" />
					           ${reportCategory.name}
					     </c:otherwise>
						</c:choose>
						<form:errors path="reportCategory.id" cssClass="error" />
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