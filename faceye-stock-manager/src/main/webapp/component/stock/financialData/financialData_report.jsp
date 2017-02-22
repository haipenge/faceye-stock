<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/financialData/financialData.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/financialData/financialData.js"/>"></script>
<style>
 td{
  height:10px;
  font:7px;
 }  
</style>
<div class="page-head">
	<h2>财务报表&nbsp;&nbsp;<small>单位：万元</small></h2>
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
			<form action="<c:url value="/stock/financialData/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">
						<div class="col-md-1">
							<input type="text" name="EQ|financialReportId" value="${searchParams.financialReportId}"
								placeholder="<fmt:message key="stock.financialData.financialReportId"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-1">
							<input type="text" name="EQ|stockId" value="${searchParams.stockId}" placeholder="<fmt:message key="stock.financialData.stockId"></fmt:message>"
								class="form-control input-sm">
						</div>
						<div class="col-md-1">
							<input type="text" name="EQ|accountElementId" value="${searchParams.accountElementId}" placeholder="<fmt:message key="stock.financialData.accountElementId"></fmt:message>"
								class="form-control input-sm">
						</div>
						<div class="col-md-1">
							<input type="text" name="EQ|accountingSubjectId" value="${searchParams.accountingSubjectId}"
								placeholder="<fmt:message key="stock.financialData.accountingSubjectId"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-1">
							<input type="text" name="EQ|data" value="${searchParams.data}" placeholder="<fmt:message key="stock.financialData.data"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-1">
							<input type="text" name="EQ|date" value="${searchParams.date}" placeholder="<fmt:message key="stock.financialData.date"></fmt:message>" class="form-control input-sm">
						</div>
						<!--@generate-entity-jsp-query-detail@-->
						<div class="col-md-1">
							<button type="submit" class="btn btn-sm btn-primary">
								<fmt:message key="global.search"></fmt:message>
							</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<input type="hidden" value="${stock.id}" id="stock_id">
		<div id="msg"></div>
		<div class="content">股票:${stock.name} (${stock.code}) &nbsp;&nbsp;<c:if test="${empty page.content  }"><a href="#" class="btn btn-primary start-crawl">爬取数据</a></c:if></div>
		<div class="content">
			<ul class="nav nav-pills" role="tablist">
				<c:forEach items="${reportCategories}" var="reportCategory">
					<li role="presentation"><a href="<c:url value="/stock/financialData/report?reportCategoryId=${reportCategory.id}&stockId=${stock.id}"/>">${reportCategory.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="content">
			<div id="msg"></div>
			<h4>${reportCategory.name }</h4>
			<div class="content">
			  <a href="<c:url value="/stock/financialData/report?reportCategoryId=${param.reportCategoryId}&stockId=${param.stockId }&type=0"/>">年报</a>|
			  <a href="<c:url value="/stock/financialData/report?reportCategoryId=${param.reportCategoryId}&stockId=${param.stockId }&type=1"/>">一季报</a>|
			  <a href="<c:url value="/stock/financialData/report?reportCategoryId=${param.reportCategoryId}&stockId=${param.stockId }&type=2"/>">中报</a>|
			  <a href="<c:url value="/stock/financialData/report?reportCategoryId=${param.reportCategoryId}&stockId=${param.stockId }&type=3"/>">三季报</a>|
			  
			</div>
			<div classs="table-responsive">
				<table class="table table-bordered">
					<tr>
						<td style="width: 20%;" class="bg-gray">报表日期</td>
						<c:forEach items="${dates}" var="date">
							<td class="bg-gray text-center" ><fmt:formatDate value="${date}" pattern="yyyy-MM-dd" /></td>
						</c:forEach>
					</tr>
				</table>
				<table class="table table-bordered">
					<!-- 
					<thead>
						<tr>
							<th><input type="checkbox" name="check-all"></th>
							<th><fmt:message key='stock.financialData.stockId'></fmt:message></th>
							<th><fmt:message key='stock.financialData.accountingSubjectId'></fmt:message></th>
							<th><fmt:message key='stock.financialData.data'></fmt:message>(万元)</th>
							<th><fmt:message key='stock.financialData.date'></fmt:message></th>
						</tr>
						-->
					</thead>
					<tbody>
						<c:forEach items="${accountingElements}" var="accountingElement">
							<tr>
								<td class="bg-gray">${accountingElement.name}</td>
							</tr>
							<tr>
								<td>
									<table>

										<c:forEach items="${accountingSubjects}" var="accountingSubject">
										 <c:if test="${accountingSubject.accountingElement.id eq accountingElement.id }">
											<tr>
												<td style="width: 20%;">${accountingSubject.name}</td>
												<c:forEach items="${page.content }" var="data">
													<c:if test="${accountingSubject.id eq data.accountingSubjectId }">
														<td style="text-align:right;color:blue;"><c:if test="${ not empty data.data}">
														<c:if test="${data.rate gt 0.0 }">
														    <span class="span-suffix">(<fmt:formatNumber value="${data.rate *100}" pattern="#,#0.0#"/>%)</span>
														    </c:if>
																<c:choose>
																	<c:when test="${data.data gt 10000 or data.data lt -10000}">
																		<fmt:formatNumber value="${data.data }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />
																	</c:when>
																	<c:otherwise>${data.data }<span class="span-suffix">元</span>
																	</c:otherwise>
																</c:choose>
																<!-- 
												     : <span class="span-suffix"><fmt:formatDate value="${data.date}" pattern="yyyy-MM-dd" /></span> -->
															</c:if></td>
													</c:if>
												</c:forEach>
											</tr>
											</c:if>
										</c:forEach>
									</table>
								</td>
							</tr>
						</c:forEach>
						<!-- 
						<c:forEach items="${page.content}" var="financialData">
							<tr>
								<td><input type="checkbox" name="check-single" value="${financialData.id}"></td>
								<td>${financialData.stockId}</td>
								<td>${financialData.accountingSubjectId}</td>
								<td style="text-align: right; font-weight: blod; color: blue;"><fmt:formatNumber value="${financialData.data /10000}" type="number" pattern="0.00"
										maxFractionDigits="2" groupingUsed="true" /></td>
								<td><fmt:formatDate value="${financialData.date}" pattern="yyyy-MM-dd" /></td>
							<tr>
						</c:forEach>
						 -->
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/stock/financialData/report" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>