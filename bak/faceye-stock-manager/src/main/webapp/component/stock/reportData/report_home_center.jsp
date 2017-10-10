<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div class="page-head">
	<h2>综合报表</h2>
</div>
<div class="cl-mcont">
	<div class="block-flat">
		<div class="row">
			<div class="col-sm-4">
				<h3>Rule</h3>
				<div class="content">
					<p class="text-danger" style="font-weight: bold">做!</p>
				</div>
			</div>
			<div class="col-sm-4">
				<h3>写在奶酪墙上的话</h3>
				<div class="content">
					变化总是在发生<br> 他们总是不断地拿走你的奶酪<br> <br> 预见变化<br> 随时做好奶酪被拿走的准备<br> <br> 追踪变化<br> 经常闻一闻你的奶酪,以便指导它们什么时候开始变质<br> <br> 尽快适应变化<br>
					越早放弃旧的奶酪,你就会越早享用到新的奶酪<br> <br> 改变<br> 随着奶酪的变化而变化,享受变化！<br> <br> 尝试去冒险,去享受新奶酪的美味！<br> 做好迅速变化的准备,不断地去享受变化<br> <br> <br> <font
						color="red"><b>记住</b></font>：他们仍会不断地拿走你的奶酪<br>
				</div>
			</div>
			<div class="col-sm-4">
			   <!-- 涨幅前30名 -->
				<div class="block-flat">
					<div class="header">
						<h3>涨幅前30名</h3>
					</div>
					<div class="content">
						<table>
							<thead>
								<tr>
								    <th></th>
									<th style="width: 30%;">股票</th>
									<th  class="text-right">今日涨幅</th>
									<th class="text-right">30日波幅</th>
									<th>板块</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${topIncreaseStocks.content}" var="stock" varStatus="status">
									<tr>
									    <td>${status.index }</td>
										<td style="width: 30%;">${stock.name }<span class="span-suffix">(${stock.code})</span></td>
										<td  class="text-right"><fmt:formatNumber value="${stock.dailyStat.todayIncreaseRate *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
										<td class="text-right"><fmt:formatNumber value="${stock.dailyStat.priceAmplitude *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
										<td><a href="<c:url value="/stock/stock/home?EQ|category.$id=${stock.category.id}"/>">${stock.category.name }</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<!-- 跌幅前30名 -->
				
				<div class="block-flat">
					<div class="header">
						<h3>跌幅前30名</h3>
					</div>
					<div class="content">
						<table>
							<thead>
								<tr>
								    <th></th>
									<th style="width: 30%;">股票</th>
									<th  class="text-right">今日跌幅</th>
									<th  class="text-right">30日波幅</th>
									<th>板块</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${footIncreaseStocks.content}" var="stock" varStatus="status">
									<tr>
									<td>${status.index }</td>
										<td style="width: 30%;">${stock.name }<span class="span-suffix">(${stock.code})</span></td>
										<td  class="text-right"><fmt:formatNumber value="${stock.dailyStat.todayIncreaseRate *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
										<td class="text-right"><fmt:formatNumber value="${stock.dailyStat.priceAmplitude *100 }" type="number" pattern="#,##0.0#" maxFractionDigits="1" groupingUsed="true" />%</td>
										<td><a href="<c:url value="/stock/stock/home?EQ|category.$id=${stock.category.id}"/>">${stock.category.name }</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				
				
			</div>
		</div>

	</div>
</div>
