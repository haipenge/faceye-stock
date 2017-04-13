<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/dailyData/dailyData.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/dailyData/dailyData.js"/>"></script>

<div class="page-head">
	<h2>
		<fmt:message key="stock.dailyData.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/stock/dailyData/input"/>"> <fmt:message key="stock.dailyData.add"></fmt:message>
		</a>
	</h2>
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
							<th>昨天收盘价</th>
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
							<th>是否星标</th>
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
							<td><f:boolean value="${dailyData.starDataType eq 1 }" /></td>
							<td>${dailyData.ema12}</td>   
 <td>${dailyData.ema26}</td>   
 <td>${dailyData.dif}</td>   
 <td>${dailyData.dea}</td>   
 <td>${dailyData.macd}</td>   
 <!--@generate-entity-jsp-property-value@-->
						<tr>
					</c:forEach>
				</table>
			</div>
			<f:page page="${page}" url="/stock/dailyData/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>