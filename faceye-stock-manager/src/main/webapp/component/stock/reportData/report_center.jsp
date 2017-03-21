<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/financialData/financialData.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/reportData/reportData.js"/>"></script>
<div class="page-head">
	<div class="row">
		<div class="col-sm-8">
			<h2>
				${stock.name} <small>(${stock.code})</small>&nbsp;&nbsp;财报<input type="hidden" value="${stock.id}" id="stock_id">
			</h2>
		</div>
		<div class="col-sm-4 text-right">
			<c:if test="${empty wrapReporter.records  }">
				<a href="#" class="btn btn-sm btn-warning start-crawl">爬取数据</a>
			</c:if>
			<c:if test="${ not empty wrapReporter.records  }">
				<a href="#" class="btn btn-sm btn-warning start-crawl">重新爬取</a>
			</c:if>
		</div>
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
		<div class="row" style="margin-top: 0px; margin-bottom: 0px;">
			<div class="col-sm-4">
				<ul class="nav nav-pills bg-success" role="tablist">
					<c:forEach items="${reportCategories}" var="reportCategory">
						<li role="presentation" <c:if test="${(param.reportCategoryId eq reportCategory.id) or (empty param.reportCategoryId and reportCategory.id eq  3) }"> class="active"</c:if>><a
							href="<c:url value="/stock/reportData/report?reportCategoryId=${reportCategory.id}&stockId=${stock.id}&type=${param.type }"/>">${reportCategory.name }</a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="col-sm-4">
				<ul class="nav nav-pills navbar-right bg-info" style="margin-right: 5px;" role="tablist">
					<li role="presentation" <c:if test="${param.type eq 0 or empty param.type }"> class="active"</c:if>><a
						href="<c:url value="/stock/reportData/report?reportCategoryId=${param.reportCategoryId}&stockId=${param.stockId }&type=0"/>">年报</a></li>
					<li role="presentation" <c:if test="${param.type eq 1}"> class="active"</c:if>><a
						href="<c:url value="/stock/reportData/report?reportCategoryId=${param.reportCategoryId}&stockId=${param.stockId }&type=1"/>">一季报</a></li>
					<li role="presentation" <c:if test="${param.type eq 2}"> class="active"</c:if>><a
						href="<c:url value="/stock/reportData/report?reportCategoryId=${param.reportCategoryId}&stockId=${param.stockId }&type=2"/>">中报</a></li>
					<li role="presentation" <c:if test="${param.type eq 3}"> class="active"</c:if>><a
						href="<c:url value="/stock/reportData/report?reportCategoryId=${param.reportCategoryId}&stockId=${param.stockId }&type=3"/>">三季报</a></li>
				</ul>
			</div>
			<div class="col-sm-2">
				<ul class="nav nav-pills navbar-right bg-warning" style="margin-right: 5px;" role="tablist">
					<li role="presentation"><a href="<c:url value="/stock/dataStat/home?EQ|stockId=${stock.id }"/>">杜邦分析</a></li>
				</ul>
			</div>
			<div class="col-sm-2">
				<c:if test="${not empty wrapReporter.records}">
					<ul class="nav nav-pills navbar-right bg-warning" style="margin-right: 5px;" role="tablist">
						<c:if test="${not empty param.startDate }">
							<li role="presentation"><a href="<c:url value="/stock/reportData/report?stockId=${param.stockId }&reportCategoryId=${param.reportCategoryId}&type=${param.type}"/>">首页</a>
								</a></li>
						</c:if>
						<li role="presentation"><a
							href="<c:url value="/stock/reportData/report?stockId=${param.stockId }&reportCategoryId=${param.reportCategoryId}&type=${param.type}&startDate=${wrapReporter.records[fn:length(wrapReporter.records)-1].date.getTime() }"/>">下一组</a>
							</a></li>
					</ul>
				</c:if>
			</div>
		</div>
		<div class="row" style="margin-top: 5px;">
			<div class="col-sm-12">
				<div classs="table-responsive">
					<table class="table table-bordered">
						<tr>
							<th colspan="2" class="text-center" style="border-bottom: 2px solid gray; font-size: 16px; font-weight: bold; vertical-align: middle;">${reportCategory.name }</th>
							<c:if test="${not empty wrapReporter.records }">
								<c:forEach items="${wrapReporter.records}" var="record">
									<th style="border-bottom: 2px solid gray; padding-bottom: 0px; padding-top: 0px;" class="text-center"><p style="margin-bottom: 0px; border-bottom: 1px solid gray;">
											<fmt:formatDate value="${record.date}" pattern="yyyy-MM-dd" />
										</p>
										<p style="margin-top: 0px; margin-bottom: 0px;">
											<span class="small pull-left">同型</span><span class="small">金额(元)</span><span class="pull-right small">趋势</span>
										</p></th>
								</c:forEach>
							</c:if>
							<!-- 财务接要-杜邦分析 -->
							<c:if test="${not empty dataStats}">
								<c:forEach items="${dataStats}" var="dataStat">
									<th style="border-bottom: 2px solid gray;" class="text-center"><fmt:formatDate value="${dataStat.dateCycle}" pattern="yyyy-MM-dd" /></th>
								</c:forEach>
							</c:if>
						</tr>
						<!-- 财务报表 -->
						<c:if test="${not empty wrapReporter.titles && not empty wrapReporter.records}">
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
													<td <c:if test="${status.last}"> style="border-bottom:2px solid gray;"</c:if> class="text-center">
														<p style="margin-top: 0px; margin-bottom: 0px;">
															<span class="small pull-left text-info"><c:if test="${data2Record.commonSizeAnalysisResult gt 0 }">
																	<fmt:formatNumber value="${data2Record.commonSizeAnalysisResult *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</c:if></span> <span
																style="margin-left: 5px; margin-right: 5px;"><fmt:formatNumber value="${data2Record.data }" type="number" pattern="#,##0.0#" maxFractionDigits="2"
																	groupingUsed="true" /></span> <span class="small pull-right text-info"><c:if
																	test="${ data2Record.trendAnalysisResult lt 0 or data2Record.trendAnalysisResult gt 0 }">
																	<fmt:formatNumber value="${data2Record.trendAnalysisResult *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</c:if></span>
														</p>
													</td>
												</c:if>
											</c:forEach>
										</c:forEach>
									</tr>
								</c:forEach>
							</c:forEach>
						</c:if>
						<!-- 财务接要 -杜邦分析 -->
						<c:if test="${not empty dataStats}">
							<!-- 总资产净利率 -->
							<tr>
								<th rowspan="6" style="margin: 0 auto; width: 20px; line-height: 24px; border-bottom: 2px solid gray;">杜邦分析</th>
								<td>总资产净利率</td>
								<c:forEach items="${dataStats}" var="dataStat">
									<td class="text-right"><fmt:formatNumber value="${dataStat.totalAssetsNetProfitMargin *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2"
											groupingUsed="true" />%</td>
								</c:forEach>
							</tr>
							<!-- 净资产收益率 -->
							<tr>
								<td>净资产收益率</td>
								<c:forEach items="${dataStats}" var="dataStat">
									<td class="text-right"><fmt:formatNumber value="${dataStat.roe *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2"
											groupingUsed="true" />%</td>
								</c:forEach>
							</tr>
							<!-- 毛利率 -->
							<tr>

								<td>毛利率</td>
								<c:forEach items="${dataStats}" var="dataStat">
									<td class="text-right"><fmt:formatNumber value="${dataStat.grossProfitMargin *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
								</c:forEach>
							</tr>
							<!-- 净利率 -->
							<tr>
								<td>净利率</td>
								<c:forEach items="${dataStats}" var="dataStat">
									<td class="text-right"><fmt:formatNumber value="${dataStat.netProfitMargin *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
								</c:forEach>
							</tr>
							<!-- 资产周转率 -->
							<tr>
								<td>资产周转率</td>
								<c:forEach items="${dataStats}" var="dataStat">
									<td class="text-right"><fmt:formatNumber value="${dataStat.totalAssetsTurnover *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
								</c:forEach>
							</tr>

							<!-- 资产负债率 -->
							<tr>
								<td style="border-bottom: 2px solid gray;">资产负债率</td>
								<c:forEach items="${dataStats}" var="dataStat">
									<td class="text-right" style="border-bottom: 2px solid gray;"><fmt:formatNumber value="${dataStat.debtToAssetsRatio *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
								</c:forEach>
							</tr>

						</c:if>
						<!-- 财务摘要-杜邦分析结束  -->
					</table>
				</div>
			</div>
		</div>
	</div>
</div>