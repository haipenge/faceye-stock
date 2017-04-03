<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/reportData/reportData.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/reportData/reportData.js"/>"></script>
<div class="page-head">
	<div class="row">
		<div class="col-sm-2">
			<h2>
				${stock.name} <small>(${stock.code})</small>&nbsp;&nbsp;<input type="hidden" name="stockId" value="${stock.id}" id="stock_id">
			</h2>
		</div>
		<div class="col-sm-8 text-center">
			<c:if test="${not empty dailyStat }">
				<c:set var="price-css" value="price-rise" />
				<c:if test="${dailyStat.topPriceDate lt dailyStat.lowPriceDate }">
					<c:set var="price-css" value="price-fall" />
				</c:if>
				市盈率:<fmt:formatNumber value="${dailyStat.pe }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />&nbsp;&nbsp;
				动态市盈率:<fmt:formatNumber value="${dailyStat.dynamicPe}" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />&nbsp;&nbsp;
				30天最高价:￥ <fmt:formatNumber value="${dailyStat.topPriceOf30Day}" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />&nbsp;&nbsp;(<fmt:formatDate
					value="${dailyStat.topPriceDate }" pattern="MM-dd" />)&nbsp;&nbsp;
				30天最低价:￥ <fmt:formatNumber value="${dailyStat.lowPriceOf30Day}" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />&nbsp;&nbsp;(<fmt:formatDate
					value="${dailyStat.lowPriceDate }" pattern="MM-dd" />)&nbsp;&nbsp;
				30天波幅:<fmt:formatNumber value="${100* dailyStat.priceAmplitude}" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%
				</c:if>
			当天价格:
			<fmt:formatNumber value="${dailyStat.todayPrice}" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />
			&nbsp;&nbsp; 涨跌:
			<fmt:formatNumber value="${dailyStat.todayIncreaseRate *100}" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />
			%&nbsp;&nbsp;
		</div>
		<div class="col-sm-2 text-right">
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
	<div class="block-flat">
		<div id="msg"></div>
		<div class="row" style="margin-top: 0px; margin-bottom: 0px;">
			<div class="col-sm-4">
				<ul class="nav nav-pills bg-success" role="tablist">
					<c:forEach items="${reportCategories}" var="reportCategory">
						<li role="presentation" <c:if test="${(param.reportCategoryId eq reportCategory.id) or (empty param.reportCategoryId and reportCategory.id eq  2) }"> class="active"</c:if>><a
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
					<li role="presentation"><a href="<c:url value="/stock/dataStat/home?EQ|stockId=${stock.id }"/>" class="stock-stat">杜邦分析</a></li>
				</ul>
			</div>
			<div class="col-sm-2">
				<c:if test="${not empty wrapReporter.records || not empty dataStats}">
					<c:choose>
						<c:when test="${not empty wrapReporter.records }">
							<c:set var="startDate" value="${wrapReporter.records[fn:length(wrapReporter.records)-1].date.getTime() }" />
						</c:when>
						<c:when test="${not empty dataStats }">
							<c:set var="startDate" value="${dataStats[fn:length(dataStats)-1].dateCycle.getTime() }" />
						</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
					<ul class="nav nav-pills navbar-right bg-warning" style="margin-right: 5px;" role="tablist">
						<c:if test="${not empty param.startDate }">
							<li role="presentation"><a href="<c:url value="/stock/reportData/report?stockId=${param.stockId }&reportCategoryId=${param.reportCategoryId}&type=${param.type}"/>">首页</a>
								</a></li>
						</c:if>
						<li role="presentation"><a
							href="<c:url value="/stock/reportData/report?stockId=${param.stockId }&reportCategoryId=${param.reportCategoryId}&type=${param.type}&startDate=${startDate }"/>">下一组</a> </a></li>
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
							<c:if test="${not empty wrapCompareReporters}">
								<c:forEach items="${wrapCompareReporters[0].wrapReporter.records}" var="record" begin="0" end="1">
									<th style="border-bottom: 2px solid gray; padding-bottom: 0px; padding-top: 0px;" class="text-center"><p style="margin-bottom: 0px; border-bottom: 1px solid gray;">
											<fmt:formatDate value="${record.date}" pattern="yyyy-MM-dd" />
										</p>
										<p style="margin-top: 0px; margin-bottom: 0px;">
											<span class="small pull-left">同型</span><span class="small">金额(元)</span><span class="pull-right small">趋势</span>
										</p></th>
								</c:forEach>
							</c:if>

							<c:forEach var="wrapCompareReporter" items="${wrapCompareReporters}" varStatus="cStatus">

								<!-- 财务接要-杜邦分析 -->
								<c:if test="${not empty wrapCompareReporter.dataStats}" begin="0" end="1">
									<c:forEach items="${wrapCompareReporter.dataStats}" var="dataStat">
										<th style="border-bottom: 2px solid gray;" class="text-center"><fmt:formatDate value="${dataStat.dateCycle}" pattern="yyyy-MM-dd" /></th>
									</c:forEach>
								</c:if>
						</tr>
						<!-- 财务报表 -->
						<c:if test="${not empty wrapCompareReporter.wrapReporter.titles && not empty wrapCompareReporter.wrapReporter.records}">
							<c:forEach var="title" items="${wrapCompareReporter.wrapReporter.titles}">
								<c:if test="${cStatus.index == 0 }">
									<tr>
										<th rowspan="${fn:length(title.accountingSubjects) + 1}" style="margin: 0 auto; width: 20px; line-height: 24px; border-bottom: 2px solid gray;">${title.accountingElement.name}</th>
									</tr>
								</c:if>
								<c:forEach items="${title.accountingSubjects}" var="accountingSubject" varStatus="status">
									<tr>
										<c:if test="${cStatus.index == 0 }">
											<td width="160" <c:if test="${status.last}"> style="border-bottom:2px solid gray;"</c:if>>${accountingSubject.name}</td>
										</c:if>
										<c:forEach items="${wrapCompareReporter.wrapReporter.records}" var="record" begin="0" end="1">
											<c:forEach items="${record.data2Record}" var="data2Record">
												<c:if test="${accountingSubject.id eq data2Record.accountingSubjectId}">
													<td <c:if test="${status.last}"> style="border-bottom:2px solid gray;"</c:if> class="text-center">
														<p class="p-data">
															<span class="small pull-left text-info"><c:if test="${data2Record.commonSizeAnalysisResult gt 0 }">
																	<fmt:formatNumber value="${data2Record.commonSizeAnalysisResult *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</c:if></span> <span
																class="span-data"><fmt:formatNumber value="${data2Record.data }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></span> <span
																class="small pull-right text-info"><c:if test="${ data2Record.trendAnalysisResult lt 0 or data2Record.trendAnalysisResult gt 0 }">
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
									<td class="text-right"><fmt:formatNumber value="${dataStat.roe *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
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
									<td class="text-right" style="border-bottom: 2px solid gray;"><fmt:formatNumber value="${dataStat.debtToAssetsRatio *100 }" type="number" pattern="#,##0.0#"
											maxFractionDigits="2" groupingUsed="true" />%</td>
								</c:forEach>
							</tr>
						</c:if>
						<!-- 财务摘要-杜邦分析结束  -->
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>