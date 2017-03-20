<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/financialData/financialData.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/financialData/financialData.js"/>"></script>
<div class="page-head">
	<h2></h2>
	<input type="hidden" value="${stock.id}" id="stock_id">
	<div class="content">
		股票:${stock.name} (${stock.code}) &nbsp;&nbsp;
		<c:if test="${empty wrapReporter.records  }">
			<a href="#" class="btn btn-primary start-crawl">爬取数据</a>
		</c:if>
		<c:if test="${ not empty wrapReporter.records  }">
			<a href="#" class="btn btn-primary start-crawl">重新爬取</a>
		</c:if>
	</div>
</div>
<div class="cl-mcont">
	<!-- 
	<div class="header">
		<h2>
			<fmt:message key="security.role.manager"></fmt:message>
		</h2>
		<a class="btn btn-default" href="<c:url value="/security/role/input"/>"> <fmt:message key="security.role.add"></fmt:message>
		</a>
	</div>
	 -->
	<div class="block-flat">
		<div class="content">
			<ul class="nav nav-pills" role="tablist">
				<c:forEach items="${reportCategories}" var="reportCategory">
					<li role="presentation"><a href="<c:url value="/stock/financialData/wrapReporter?reportCategoryId=${reportCategory.id}&stockId=${stock.id}"/>">${reportCategory.name }</a></li>
				</c:forEach>
			</ul>
			<a href="<c:url value="/stock/financialData/wrapReporter?reportCategoryId=${param.reportCategoryId}&stockId=${param.stockId }&type=0"/>">年报</a>| <a
				href="<c:url value="/stock/financialData/wrapReporter?reportCategoryId=${param.reportCategoryId}&stockId=${param.stockId }&type=1"/>">一季报</a>| <a
				href="<c:url value="/stock/financialData/wrapReporter?reportCategoryId=${param.reportCategoryId}&stockId=${param.stockId }&type=2"/>">中报</a>| <a
				href="<c:url value="/stock/financialData/wrapReporter?reportCategoryId=${param.reportCategoryId}&stockId=${param.stockId }&type=3"/>">三季报</a>|
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div classs="table-responsive">
					<table class="table table-bordered">
						<tr>
							<th colspan="2" class="text-center" style="border-bottom: 2px solid gray; font-size: 16px; font-weight: bold;">${reportCategory.name }</th>
							<c:forEach items="${wrapReporter.records}" var="record">
								<th style="border-bottom: 2px solid gray;" class="text-center"><fmt:formatDate value="${record.date}" pattern="yyyy-MM-dd" /></th>
							</c:forEach>
						</tr>
						<c:forEach var="title" items="${wrapReporter.titles}">
							<tr>
								<th rowspan="${fn:length(title.accountingSubjects) + 1}" style="margin: 0 auto; width: 20px; line-height: 24px; border-bottom: 2px solid gray;">${title.accountingElement.name}</th>
							</tr>
							<c:forEach items="${title.accountingSubjects}" var="accountingSubject" varStatus="status">
								<tr>
									<td width="160" <c:if test="${status.last}"> style="border-bottom:2px solid gray;"</c:if>>${accountingSubject.name}</td>
									<c:forEach items="${wrapReporter.records}" var="record">
										<c:forEach items="${record.data2Record}" var="data2Record">
											<c:if test="${accountingSubject.id eq data2Record.accountingSubjectId}">
												<td <c:if test="${status.last}"> style="border-bottom:2px solid gray;"</c:if> class="text-right"><fmt:formatNumber value="${data2Record.data }" type="number"
														pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
											</c:if>
										</c:forEach>
									</c:forEach>
								</tr>
							</c:forEach>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>