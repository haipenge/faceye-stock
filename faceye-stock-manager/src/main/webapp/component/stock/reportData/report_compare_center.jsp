<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/reportData/reportData.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/reportData/reportData.js"/>"></script>
<div class="page-head">
	<div class="row">
		<div class="col-sm-12">
			<h2>
				财报比对
			</h2>
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
							href="<c:url value="/stock/reportData/compare?reportCategoryId=${reportCategory.id}&type=${param.type }&stockIds=${param.stockIds }"/>">${reportCategory.name }</a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="col-sm-4">
				<ul class="nav nav-pills navbar-right bg-info" style="margin-right: 5px;" role="tablist">
					<li role="presentation" <c:if test="${param.type eq 0 or empty param.type }"> class="active"</c:if>><a
						href="<c:url value="/stock/reportData/compare?reportCategoryId=${param.reportCategoryId}&type=0&stockIds=${param.stockIds }"/>">年报</a></li>
					<li role="presentation" <c:if test="${param.type eq 1}"> class="active"</c:if>><a
						href="<c:url value="/stock/reportData/compare?reportCategoryId=${param.reportCategoryId}&type=1&stockIds=${param.stockIds }"/>">一季报</a></li>
					<li role="presentation" <c:if test="${param.type eq 2}"> class="active"</c:if>><a
						href="<c:url value="/stock/reportData/compare?reportCategoryId=${param.reportCategoryId}&type=2&stockIds=${param.stockIds }"/>">中报</a></li>
					<li role="presentation" <c:if test="${param.type eq 3}"> class="active"</c:if>><a
						href="<c:url value="/stock/reportData/compare?reportCategoryId=${param.reportCategoryId}&type=3&stockIds=${param.stockIds }"/>">三季报</a></li>
				</ul>
			</div>
			<div class="col-sm-2">
			  <!-- 
				<ul class="nav nav-pills navbar-right bg-warning" style="margin-right: 5px;" role="tablist">
					<li role="presentation"><a href="<c:url value="/stock/dataStat/home?EQ|stockId=${stock.id }"/>" class="stock-stat">杜邦分析</a></li>
				</ul>
				 -->
			</div>
			<div class="col-sm-2">
				<c:if test="${not empty wrapCompareReporters[0].wrapReporter.records || not empty wrapCompareReporters[0].dataStats}">
					<c:choose>
						<c:when test="${not empty wrapCompareReporters[0].wrapReporter.records }">
							<c:set var="startDate" value="${wrapCompareReporters[0].wrapReporter.records[0].date.getTime() }" />
						</c:when>
						<c:when test="${not empty wrapCompareReporters[0].dataStats }">
							<c:set var="startDate" value="${wrapCompareReporters[0].dataStats[0].dateCycle.getTime() }" />
						</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
					<ul class="nav nav-pills navbar-right bg-warning" style="margin-right: 5px;" role="tablist">
						<c:if test="${not empty param.startDate }">
							<li role="presentation"><a href="<c:url value="/stock/reportData/compare?stockIds=${param.stockIds }&reportCategoryId=${param.reportCategoryId}&type=${param.type}"/>">首页</a>
								</a></li>
						</c:if>
						<li role="presentation"><a
							href="<c:url value="/stock/reportData/compare?stockIds=${param.stockIds }&reportCategoryId=${param.reportCategoryId}&type=${param.type}&startDate=${startDate }"/>">下一组</a> </a></li>
					</ul>
				</c:if>
			</div>
		</div>
		<div class="row" style="margin-top: 5px;">
			<div class="col-sm-12">
				<div classs="table-responsive">
					<table class="table table-bordered">
						<tr>
							<c:forEach var="wrapCompareReporter" items="${wrapCompareReporters}" varStatus="cxStatus">
								<c:if test="${cxStatus.index ==0 }">
									<th colspan="2" class="text-center" style="border-bottom: 2px solid gray; font-size: 16px; font-weight: bold; vertical-align: middle;">${reportCategory.name }</th>
								</c:if>
								<c:if test="${not empty wrapCompareReporters and empty wrapCompareReporter.dataStats}">
									<th style="border-bottom: 2px solid gray; padding-bottom: 0px; padding-top: 0px;" class="text-center"><p style="margin-bottom: 0px; border-bottom: 1px solid gray;">
											${wrapCompareReporter.stock.name }:
											<fmt:formatDate value="${wrapCompareReporter.wrapReporter.records[0].date}" pattern="yyyy-MM-dd" />
										</p>
										<p style="margin-top: 0px; margin-bottom: 0px;">
											<span class="small pull-left">同型</span><span class="small">金额(元)</span><span class="pull-right small">趋势</span>
										</p></th>
								</c:if>
								<!-- 财务接要-杜邦分析 -->
								<c:if test="${not empty wrapCompareReporter.dataStats}">
									<c:forEach items="${wrapCompareReporter.dataStats}" var="dataStat" begin="0" end="0" step="1">
										<th style="border-bottom: 2px solid gray;" class="text-center">${wrapCompareReporter.stock.name }:<fmt:formatDate value="${dataStat.dateCycle}" pattern="yyyy-MM-dd" /></th>
									</c:forEach>
								</c:if>
							</c:forEach>
						</tr>
						<c:forEach var="wrapCompareReporter" items="${wrapCompareReporters}" varStatus="cStatus" begin="0" end="0" step="1">
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
											<c:if test="${cStatus.first}">
												<td width="160" <c:if test="${status.last}"> style="border-bottom:2px solid gray;"</c:if>>${accountingSubject.name}</td>
											</c:if>
											<c:forEach items="${wrapCompareReporter.wrapReporter.records}" var="record" begin="0" end="0" step="1">
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

											<c:forEach var="iwrapCompareReporter" items="${wrapCompareReporters}" varStatus="ciStatus" begin="1" step="1">
												<c:forEach items="${iwrapCompareReporter.wrapReporter.records}" var="record" begin="0" end="0" step="1">
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
											</c:forEach>
										</tr>
									</c:forEach>
								</c:forEach>
							</c:if>
						</c:forEach>
						<!-- 财务接要 -杜邦分析 -->
						<c:if test="${not empty wrapCompareReporters[0].dataStats}">
							<c:forEach var="wrapCompareReporter" items="${wrapCompareReporters}" varStatus="cStatus">
								<c:if test="${not empty wrapCompareReporter.dataStats}">
									<!-- 总资产净利率 -->
									<c:if test="${cStatus.first }">
										<tr>
											<th rowspan="6" style="margin: 0 auto; width: 20px; line-height: 24px; border-bottom: 2px solid gray;">杜邦分析</th>
											<td>总资产净利率</td>
									</c:if>
									<td class="text-right"><fmt:formatNumber value="${wrapCompareReporter.dataStats[0].totalAssetsNetProfitMargin *100 }" type="number" pattern="#,##0.0#"
											maxFractionDigits="2" groupingUsed="true" />%</td>
									<c:if test="${cStatus.last }">
										</tr>
									</c:if>
								</c:if>
							</c:forEach>
							<!-- 净资产收益率 -->
							<c:forEach var="wrapCompareReporter" items="${wrapCompareReporters}" varStatus="cStatus">
								<c:if test="${cStatus.first }">
									<tr>
										<td>净资产收益率</td>
								</c:if>
								<td class="text-right"><fmt:formatNumber value="${wrapCompareReporter.dataStats[0].roe *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2"
										groupingUsed="true" />%</td>
								<c:if test="${cStatus.last }">
									</tr>
								</c:if>
							</c:forEach>
							<!-- 毛利率 -->
							<c:forEach var="wrapCompareReporter" items="${wrapCompareReporters}" varStatus="cStatus">
								<c:if test="${cStatus.first }">
									<tr>
										<td>毛利率</td>
								</c:if>
								<td class="text-right"><fmt:formatNumber value="${wrapCompareReporter.dataStats[0].grossProfitMargin *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2"
										groupingUsed="true" />%</td>
								<c:if test="${cStatus.last }">
									</tr>
								</c:if>
							</c:forEach>
							<!-- 净利率 -->
							<c:forEach var="wrapCompareReporter" items="${wrapCompareReporters}" varStatus="cStatus">
								<c:if test="${cStatus.first }">
									<tr>
										<td>净利率</td>
								</c:if>
								<td class="text-right"><fmt:formatNumber value="${wrapCompareReporter.dataStats[0].netProfitMargin *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2"
										groupingUsed="true" />%</td>

								<c:if test="${cStatus.last }">
									</tr>
								</c:if>
							</c:forEach>
							<!-- 资产周转率 -->
							<c:forEach var="wrapCompareReporter" items="${wrapCompareReporters}" varStatus="cStatus">
								<c:if test="${cStatus.first }">
									<tr>
										<td>资产周转率</td>
								</c:if>
								<td class="text-right"><fmt:formatNumber value="${wrapCompareReporter.dataStats[0].totalAssetsTurnover *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="2"
										groupingUsed="true" />%</td>
								<c:if test="${cStatus.last }">
									</tr>
								</c:if>
							</c:forEach>
							<!-- 资产负债率 -->
							<c:forEach var="wrapCompareReporter" items="${wrapCompareReporters}" varStatus="cStatus">
								<c:if test="${cStatus.first }">
									<tr>
										<td style="border-bottom: 2px solid gray;">资产负债率</td>
								</c:if>
								<td class="text-right" style="border-bottom: 2px solid gray;"><fmt:formatNumber value="${wrapCompareReporter.dataStats[0].debtToAssetsRatio *100 }" type="number"
										pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />%</td>
								<c:if test="${cStatus.last }">
									</tr>
								</c:if>
							</c:forEach>
						</c:if>
						<!-- 财务摘要-杜邦分析结束  -->
					</table>
				</div>
			</div>
		</div>
	</div>
</div>