<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/starDataStat/starDataStat.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/stock/stock.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/starDataStat/starDataStat.js"/>"></script>
<div class="page-head">
	<h2>
		${stock.name}<small>${stock.code}</small>星标分析
	</h2>
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
		<!-- 
		<div class="content">
			<form action="<c:url value="/stock/starDataStat/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">
						<div class="col-md-1">
							<input type="text" name="EQ|stockId" value="${searchParams.stockId}" placeholder="<fmt:message key="stock.starDataStat.stockId"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-1">
							<input type="text" name="EQ|max5DayIncreaseRate" value="${searchParams.max5DayIncreaseRate}"
								placeholder="<fmt:message key="stock.starDataStat.max5DayIncreaseRate"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|max10DayIncreaseRate" value="${searchParams.max10DayIncreaseRate}"
								placeholder="<fmt:message key="stock.starDataStat.max10DayIncreaseRate"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|max20DayIncreaseRate" value="${searchParams.max20DayIncreaseRate}"
								placeholder="<fmt:message key="stock.starDataStat.max20DayIncreaseRate"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|max30DayIncreaseRate" value="${searchParams.max30DayIncreaseRate}"
								placeholder="<fmt:message key="stock.starDataStat.max30DayIncreaseRate"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|max60DayIncreaseRate" value="${searchParams.max60DayIncreaseRate}"
								placeholder="<fmt:message key="stock.starDataStat.max60DayIncreaseRate"></fmt:message>" class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|starDataDate" value="${searchParams.starDataDate}" placeholder="<fmt:message key="stock.starDataStat.starDataDate"></fmt:message>"
								class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|starDailyDataId" value="${searchParams.starDailyDataId}" placeholder="<fmt:message key="stock.starDataStat.starDailyDataId"></fmt:message>"
								class="form-control input-sm">
						</div>
						
						<div class="col-md-1">
							<button type="submit" class="btn btn-sm btn-primary">
								<fmt:message key="global.search"></fmt:message>
							</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		 -->
		<div class="row" style="margin-top:0px;">
			<div class="col-md-6">
				<div class="page-head">
					<h4>均线表现分析</h4>
				</div>
				<div class="content">
					<div id="msg"></div>

					<div classs="table-responsive">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th><fmt:message key='stock.starDataStat.max5DayIncreaseRate'></fmt:message></th>
									<th><fmt:message key='stock.starDataStat.max10DayIncreaseRate'></fmt:message></th>
									<th><fmt:message key='stock.starDataStat.max20DayIncreaseRate'></fmt:message></th>
									<th><fmt:message key='stock.starDataStat.max30DayIncreaseRate'></fmt:message></th>
									<th><fmt:message key='stock.starDataStat.max60DayIncreaseRate'></fmt:message></th>
									<th><fmt:message key='stock.starDataStat.starDataDate'></fmt:message></th>
									<!--@generate-entity-jsp-property-desc@-->
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="text-right" style="border-bottom: 1px solid blue;"><fmt:formatNumber value="${wrapAvgStarDataStat.max5DayIncreaseSuccessRate *100}" type="number"
											pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
									<td class="text-right" style="border-bottom: 1px solid blue;"><fmt:formatNumber value="${wrapAvgStarDataStat.max10DayIncreaseSuccessRate *100}" type="number"
											pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
									<td class="text-right" style="border-bottom: 1px solid blue;"><fmt:formatNumber value="${wrapAvgStarDataStat.max20DayIncreaseSuccessRate *100}" type="number"
											pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
									<td class="text-right" style="border-bottom: 1px solid blue;"><fmt:formatNumber value="${wrapAvgStarDataStat.max30DayIncreaseSuccessRate *100}" type="number"
											pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
									<td class="text-right" style="border-bottom: 1px solid blue;"><fmt:formatNumber value="${wrapAvgStarDataStat.max60DayIncreaseSuccessRate *100}" type="number"
											pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
									<td class="text-right" style="border-bottom: 1px solid blue;"></td>
								</tr>
								<c:forEach items="${wrapAvgStarDataStat.starDataStats.content}" var="starDataStat">
									<tr>
										<td class="text-right ${starDataStat.max5DayIncreaseRate lt 0 ? "decrease-color":"increase-color"}"><fmt:formatNumber
												value="${starDataStat.max5DayIncreaseRate *100}" type="number" pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
										<td class="text-right ${starDataStat.max10DayIncreaseRate lt 0 ? "decrease-color":"increase-color"}"><fmt:formatNumber
												value="${starDataStat.max10DayIncreaseRate *100}" type="number" pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
										<td class="text-right ${starDataStat.max20DayIncreaseRate lt 0 ? "decrease-color":"increase-color"}"><fmt:formatNumber
												value="${starDataStat.max20DayIncreaseRate *100}" type="number" pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
										<td class="text-right ${starDataStat.max30DayIncreaseRate lt 0 ? "decrease-color":"increase-color"}"><fmt:formatNumber
												value="${starDataStat.max30DayIncreaseRate *100}" type="number" pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
										<td class="text-right ${starDataStat.max60DayIncreaseRate lt 0 ? "decrease-color":"increase-color"}"><fmt:formatNumber
												value="${starDataStat.max60DayIncreaseRate *100}" type="number" pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
										<td class="text-right"><fmt:formatDate pattern="yyyy-MM-dd" value="${starDataStat.starDataDate}" /></td>
										<!--@generate-entity-jsp-property-value@-->
									<tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<f:page page="${wrapAvgStarDataStat.starDataStats}" url="/stock/starDataStat/home" params="<%=request.getParameterMap()%>" />
				</div>
			</div>

			<div class="col-md-6">
				<div class="page-head">
					<h4>MACD表现分析</h4>
				</div>
				<div class="content">
					<div classs="table-responsive">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th><fmt:message key='stock.starDataStat.max5DayIncreaseRate'></fmt:message></th>
									<th><fmt:message key='stock.starDataStat.max10DayIncreaseRate'></fmt:message></th>
									<th><fmt:message key='stock.starDataStat.max20DayIncreaseRate'></fmt:message></th>
									<th><fmt:message key='stock.starDataStat.max30DayIncreaseRate'></fmt:message></th>
									<th><fmt:message key='stock.starDataStat.max60DayIncreaseRate'></fmt:message></th>
									<th><fmt:message key='stock.starDataStat.starDataDate'></fmt:message></th>
									<!--@generate-entity-jsp-property-desc@-->
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="text-right" style="border-bottom: 1px solid blue;"><fmt:formatNumber value="${wrapMACDStarDataStat.max5DayIncreaseSuccessRate *100}" type="number"
											pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
									<td class="text-right" style="border-bottom: 1px solid blue;"><fmt:formatNumber value="${wrapMACDStarDataStat.max10DayIncreaseSuccessRate *100}" type="number"
											pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
									<td class="text-right" style="border-bottom: 1px solid blue;"><fmt:formatNumber value="${wrapMACDStarDataStat.max20DayIncreaseSuccessRate *100}" type="number"
											pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
									<td class="text-right" style="border-bottom: 1px solid blue;"><fmt:formatNumber value="${wrapMACDStarDataStat.max30DayIncreaseSuccessRate *100}" type="number"
											pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
									<td class="text-right" style="border-bottom: 1px solid blue;"><fmt:formatNumber value="${wrapMACDStarDataStat.max60DayIncreaseSuccessRate *100}" type="number"
											pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
									<td class="text-right" style="border-bottom: 1px solid blue;"></td>
								</tr>
								<c:forEach items="${wrapMACDStarDataStat.starDataStats.content}" var="starDataStat">
									<tr>
										<td class="text-right ${starDataStat.max5DayIncreaseRate lt 0 ? "decrease-color":"increase-color"}"><fmt:formatNumber
												value="${starDataStat.max5DayIncreaseRate *100}" type="number" pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
										<td class="text-right ${starDataStat.max10DayIncreaseRate lt 0 ? "decrease-color":"increase-color"}"><fmt:formatNumber
												value="${starDataStat.max10DayIncreaseRate *100}" type="number" pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
										<td class="text-right ${starDataStat.max20DayIncreaseRate lt 0 ? "decrease-color":"increase-color"}"><fmt:formatNumber
												value="${starDataStat.max20DayIncreaseRate *100}" type="number" pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
										<td class="text-right ${starDataStat.max30DayIncreaseRate lt 0 ? "decrease-color":"increase-color"}"><fmt:formatNumber
												value="${starDataStat.max30DayIncreaseRate *100}" type="number" pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
										<td class="text-right ${starDataStat.max60DayIncreaseRate lt 0 ? "decrease-color":"increase-color"}"><fmt:formatNumber
												value="${starDataStat.max60DayIncreaseRate *100}" type="number" pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
										<td class="text-right"><fmt:formatDate pattern="yyyy-MM-dd" value="${starDataStat.starDataDate}" /></td>
										<!--@generate-entity-jsp-property-value@-->
									<tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<f:page page="${wrapMACDStarDataStat.starDataStats}" url="/stock/starDataStat/home" params="<%=request.getParameterMap()%>" />
				</div>
			</div>
		</div>

	</div>
</div>