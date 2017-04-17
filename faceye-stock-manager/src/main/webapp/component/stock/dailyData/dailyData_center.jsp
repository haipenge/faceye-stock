<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/dailyData/dailyData.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/dailyData/dailyData.js"/>"></script>
<div class="page-head">
	<div class="row">
		<div class="col-sm-8">
			<h2>
				${stock.name}<small>&nbsp;&nbsp;(${stock.code })</small>&nbsp;&nbsp;每日数据<input type="hidden" name="stockId" value="${stock.id }">
			</h2>
		</div>
		<div class="col-sm-4">
			<a class="btn btn-primary pull-right" href="#" id="crawl-history">补全数据 </a> <a class="btn btn-primary pull-right" href="#" id="stat-daily-data-2-find-star">星标分析</a>
		</div>
	</div>

</div>
<div class="cl-mcont">
	<div class="block-flat">
		<div class="content">
			<form action="<c:url value="/stock/dailyData/home"/>" method="post" role="form" class="form-horizontal">

				<div class="col-md-2">
					<input type="text" class="form-control input-sm" name="EQ|stockName" placeholder="Stock Name...">
				</div>
				<div class="col-md-2">
					<button type="submit" value="Search" class="btn btn-sm btn-default">Search</button>
				</div>

			</form>
		</div>
		<div class="content">
			<div classs="table-responsive" style="font-size: 11px;">
				<table class="table table-bordered table-hover" style="font-size: 10px;">
					<thead>
						<tr>
							<th><fmt:message key='stock.dailyData.date'></fmt:message></th>
							<th><fmt:message key='stock.dailyData.kaipanjia'></fmt:message></th>
							<th><fmt:message key='stock.dailyData.shoupanjia'></fmt:message></th>
							<th>昨收</th>
							<th><fmt:message key='stock.dailyData.dangqianjiage'></fmt:message></th>
							<th><fmt:message key='stock.dailyData.jintianzuigaojia'></fmt:message></th>
							<th><fmt:message key='stock.dailyData.jintianzuidijia'></fmt:message></th>
							<th><fmt:message key='stock.dailyData.chengjiaogupiaoshu'></fmt:message></th>
							<th><fmt:message key='stock.dailyData.chengjiaojine'></fmt:message></th>
							<th><fmt:message key='stock.dailyData.avg5'></fmt:message></th>
							<th><fmt:message key='stock.dailyData.avg10'></fmt:message></th>
							<th><fmt:message key='stock.dailyData.avg20'></fmt:message></th>
							<th><fmt:message key='stock.dailyData.avg30'></fmt:message></th>
							<th><fmt:message key='stock.dailyData.avg60'></fmt:message></th>
							<th><fmt:message key='stock.dailyData.avg120'></fmt:message></th>
							<th><fmt:message key='stock.dailyData.avg250'></fmt:message></th>
							<th>星标</th>
							<th><fmt:message key='stock.dailyData.ema12'></fmt:message></th>
							<th><fmt:message key='stock.dailyData.ema26'></fmt:message></th>
							<th><fmt:message key='stock.dailyData.dif'></fmt:message></th>
							<th><fmt:message key='stock.dailyData.dea'></fmt:message></th>
							<th><fmt:message key='stock.dailyData.macd'></fmt:message></th>
							<!--@generate-entity-jsp-property-desc@-->
						</tr>
					</thead>
					<c:forEach items="${page.content}" var="dailyData">
						<tr>
							<td><fmt:formatDate value="${dailyData.date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
							<td><fmt:formatNumber value="${dailyData.kaipanjia}" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
							<td><fmt:formatNumber value="${dailyData.shoupanjia}" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
							<td><fmt:formatNumber value="${dailyData.yesterdayPrice}" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
							<td><fmt:formatNumber value="${dailyData.dangqianjiage}" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
							<td><fmt:formatNumber value="${dailyData.jintianzuigaojia}" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
							<td><fmt:formatNumber value="${dailyData.jintianzuidijia}" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
							<td><fmt:formatNumber value="${dailyData.chengjiaogupiaoshu/1000}" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />K</td>
							<td><fmt:formatNumber value="${dailyData.chengjiaojine/1000}" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" />K</td>
							<td><fmt:formatNumber value="${dailyData.avg5}" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
							<td><fmt:formatNumber value="${dailyData.avg10}" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
							<td><fmt:formatNumber value="${dailyData.avg20}" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
							<td><fmt:formatNumber value="${dailyData.avg30}" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
							<td><fmt:formatNumber value="${dailyData.avg60}" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
							<td><fmt:formatNumber value="${dailyData.avg120}" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
							<td><fmt:formatNumber value="${dailyData.avg250}" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
							<td><c:choose>
									<c:when test=" ${dailyData.starDataType eq 1 }">
										<b>AVG</b>
									</c:when>
									<c:when test=" ${dailyData.starDataType eq 2 }">
										<b>MACD</b>
									</c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose> <f:boolean value="${dailyData.starDataType eq 1 }" /></td>
							<td><fmt:formatNumber value="${dailyData.ema12}" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
							<td><fmt:formatNumber value="${dailyData.ema26}" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
							<td><fmt:formatNumber value="${dailyData.dif}" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
							<td><fmt:formatNumber value="${dailyData.dea}" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
							<td><fmt:formatNumber value="${dailyData.macd}" pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /></td>
							<!--@generate-entity-jsp-property-value@-->
						<tr>
					</c:forEach>
				</table>
			</div>
			<f:page page="${page}" url="/stock/dailyData/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>