<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" src="<c:url value="/js/lib/echarts/echarts.min.js"/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/stock/stock.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/stock/stock.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/stock/stock/stockChart.js"/>"></script>

<div class="page-head">
	<h2>
		${stock.name} <span class="span-suffix">(${stock.code})</span> <input type="hidden" name="stockId" value="${stock.id}">&nbsp;&nbsp;
		<small><a href="<c:url value="/stock/reportData/report?stockId=${stock.id}"/>">财报</a> |
				<a href="<c:url value="/stock/starDataStat/home?EQ|stockId=${stock.id}"/>">星标</a>|
				<a href="<c:url value="/stock/totalStock/home?EQ|stockId=${stock.id }"/>">股本 </a>|
				<a href="<c:url value="/stock/bonusRecord/home?EQ|stockId=${stock.id}"/>">分红</a>|
				<a href="<c:url value="/stock/forecast/home?EQ|stockId=${stock.id }"/>">机构估值</a>|
				<a href="<c:url value="/stock/dailyData/home?EQ|stockId=${stock.id}"/>">每日数据</a>|
				<a href="#" class="super-stock-data-init-in-board">初始化</a>
				</small>
	</h2>
</div>
<div class="cl-mcont">
 <div class="stats-bars">
		      <div class="butpro butstyle flat">
		         <div class="sub"><h2>市值</h2><span id="total_clientes">
		        <small>￥</small> <fmt:formatNumber
										value="${stock.dailyStat.marketValue /100000000}" type="number"
										pattern="#,##0.0#" maxFractionDigits="2" groupingUsed="true" /><small>&nbsp;&nbsp;亿</small>
		         </span></div>
		      </div>
		      <div class="butpro butstyle flat">
					<div class="sub"><h2>股价</h2><span><small>￥&nbsp;&nbsp;</small>${stock.dailyStat.todayPrice }</span></div>
					<div class="stat"><span class="<c:if test="${stock.dailyStat.todayIncreaseRate >0  }">up</c:if><c:if test="${stock.dailyStat.todayIncreaseRate <0  }">down</c:if>">
					  <fmt:formatNumber
										value="${stock.dailyStat.todayIncreaseRate *100 }"
										type="number" pattern="#,##0.0#" maxFractionDigits="1"
										groupingUsed="true" />%
					</span></div>
				</div>
				<div class="butpro butstyle flat">
				   <div class="sub"><h2>PE</h2><span><fmt:formatNumber
											value="${stock.dailyStat.pe }" type="number"
											pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" /></span></div>
				</div>
				<div class="butpro butstyle flat">
				   <div class="sub"><h2>PB</h2><span><c:if test="${empty stock.dailyStat.pb }">计算中...</c:if><fmt:formatNumber value="${stock.dailyStat.pb }"
										type="number" pattern="#,##0.0#" maxFractionDigits="2"
										groupingUsed="true" /></span></div>
				</div>
		    </div>
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
			行业:${stock.business}&nbsp;&nbsp;概念:
			<c:forEach var="category" items="${stock.categories}">${category.name}</c:forEach>
		</div>
		<div class="content">
			<div id="msg"></div>
		</div>
		<div class="row dash-cols">
		   
			<div class="col-md-4" style="padding: 0 5px 0 10;">
				<div class="block" style="margin-bottom: 10px;">
				<div class="header noborder">
				<h3>杜邦分析<a href="<c:url value="/stock/reportData/report?stockId=${stock.id }"/>" class="text-right">财报</a></h3>
				</div>
				<c:set var="dataStat" value="${reportResult.dataStats[0] }"/>
				 <table>
				  <!-- 总资产净利率 -->
							<tr>
								<td style="width:100px;">总资产净利率</td>
									<td class="text-right"><fmt:formatNumber value="${dataStat.totalAssetsNetProfitMargin *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="1"
											groupingUsed="true" />%</td>
							</tr>
							<!-- 净资产收益率 -->
							<tr>
								<td>净资产收益率</td>
									<td class="text-right"><fmt:formatNumber value="${dataStat.roe *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
							</tr>
							<!-- 毛利率 -->
							<tr>
								<td>毛利率</td>
									<td class="text-right"><fmt:formatNumber value="${dataStat.grossProfitMargin *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
							</tr>
							<!-- 净利率 -->
							<tr>
								<td>净利率</td>
									<td class="text-right"><fmt:formatNumber value="${dataStat.netProfitMargin *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
							</tr>
							<!-- 资产周转率 -->
							<tr>
								<td>资产周转率</td>
									<td class="text-right"><fmt:formatNumber value="${dataStat.totalAssetsTurnover *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
							</tr>
							<!-- 资产负债率 -->
							<tr>
								<td style="border-bottom: 1px solid gray;">资产负债率</td>
									<td class="text-right" style="border-bottom: 1px solid gray;"><fmt:formatNumber value="${dataStat.debtToAssetsRatio *100 }" type="number" pattern="#,##0.0#"
											maxFractionDigits="1" groupingUsed="true" />%</td>
							</tr>
						<!-- 财务摘要-杜邦分析结束  -->
						<!-- 其它财务指标分析 -->
							<!-- 核心利润率 -->
							<tr>
								<td>核心利润率</td>
									<td class="text-right"><fmt:formatNumber value="${dataStat.coreProfitMargin *100 }" type="number" pattern="#,##0.0#"
											maxFractionDigits="1" groupingUsed="true" />%</td>
							</tr>
						<!-- 其它 财务指标结束 -->
						<!-- 每股指标 -->
						<c:if test="${not empty dataStats}">
							<tr>
								<th rowspan="6" style="margin: 0 auto; width: 20px; line-height: 24px; border-bottom: 2px solid gray;">每股指标</th>
								<td>EPS</td>
									<td class="text-right"><fmt:formatNumber value="${dataStat.eps}" type="number" pattern="#,##0.0#" maxFractionDigits="4" groupingUsed="true" /></td>
							</tr>
						</c:if>
						<c:if test="${not empty dataStats}">
							<tr>
								<td>BPS</td>
									<td class="text-right"><fmt:formatNumber value="${dataStat.bps}" type="number" pattern="#,##0.00#" maxFractionDigits="4" groupingUsed="true" /></td>
							</tr>
						</c:if>
						<c:if test="${not empty dataStats}">
							<tr>
								<td>DPS</td>
									<td class="text-right"><fmt:formatNumber value="${dataStat.dps}" type="number" pattern="#,##0.00#" maxFractionDigits="4" groupingUsed="true" /></td>
							</tr>
						</c:if>
						<c:if test="${not empty dataStats}">
							<tr>
								<td>PB</td>
									<td class="text-right"><fmt:formatNumber value="${dataStat.pb}" type="number" pattern="#,##0.00#" maxFractionDigits="4" groupingUsed="true" /></td>
							</tr>
						</c:if>
						<c:if test="${not empty dataStats}">
							<tr>
								<td>PE</td>
									<td class="text-right"><fmt:formatNumber value="${dataStat.pe}" type="number" pattern="#,##0.00#" maxFractionDigits="4" groupingUsed="true" /></td>
							</tr>
						</c:if>
						<c:if test="${not empty dataStats}">
							<tr>
								<td>ROCE</td>
									<td class="text-right" style="border-bottom: 2px solid gray;"><fmt:formatNumber value="${dataStat.roce *100}" type="number" pattern="#,##0.0#" maxFractionDigits="2"
											groupingUsed="true" />%</td>
							</tr>
						</c:if>
				  </table>
				</div>

				<div class="block" style="margin-bottom: 10px;">
				</div>
				<div class="block" style="margin-bottom: 10px;">
				</div>
				<div class="block" style="margin-bottom: 10px;">
				</div>
			</div>
			<div class="col-md-4" style="padding: 0;">
				<div class="block" style="margin-bottom: 10px;">
				</div>
				<div class="block" style="margin-bottom: 10px;">
				</div>

				<div class="block" style="margin-bottom: 10px;">
				</div>
				<div class="block" style="margin-bottom: 10px;">
				</div>
			</div>
			<div class="col-md-4" style="padding: 0 10 0 5px;">
				<div class="block" style="margin-bottom: 10px;">
				</div>
				<div class="block" style="margin-bottom: 10px;">
				</div>
				<div class="block" style="margin-bottom: 10px;">
				</div>
				<div class="block" style="margin-bottom: 10px;">
				</div>
			</div>
		</div>
	</div>
</div>
