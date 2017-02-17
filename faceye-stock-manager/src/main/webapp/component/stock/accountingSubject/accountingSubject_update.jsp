<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/accountingSubject/accountingSubject.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/accountingSubject/accountingSubject.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty accountingSubject.id}">
					<fmt:message key="stock.accountingSubject.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="stock.accountingSubject.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
		<form:form action="/stock/accountingSubject/save" method="post" role="form" cssClass="form-horizontal" commandName="accountingSubject">
			<form:hidden path="id" />
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="stock.accountingSubject.name" />
					</label>
					<div class="col-md-6">
						<form:input path="name" cssClass="form-control" />
						<form:errors path="name" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="code"> <spring:message code="stock.accountingSubject.code" />
					</label>
					<div class="col-md-6">
						<form:input path="code" cssClass="form-control" />
						<form:errors path="code" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="code"> <spring:message code="stock.accountingElement" />
					</label>
					<div class="col-md-6">
						<c:choose>
							<c:when test="${not empty accountingElements }">
								<form:select path="accountingElement.id" cssClass="form-control">
									<form:option value="0" label="选择会计科目分类" />
									<form:options items="${accountingElements}" itemValue="id" itemLabel="name" />
								</form:select>
							</c:when>
							<c:otherwise>
								<form:hidden path="accountingElement.id" />
					          ${accountingElement.reportCategory.name} ->  ${accountingElement.name}
					     </c:otherwise>
						</c:choose>
						<form:errors path="accountingElement.id" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="sinaUrl"> <spring:message code="stock.accountingSubject.sinaUrl" />
					</label>
					<div class="col-md-6">
						<form:input path="sinaUrl" cssClass="form-control" />
						<form:errors path="sinaUrl" cssClass="error" />
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