<%@ page language="java" import="java.util.*,com.faceye.feature.util.*,com.faceye.feature.util.host.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<li><a href="#"><i class="fa fa-list-alt"></i><span>股票</span></a>
	<ul class="sub-menu">
		<li class="<%=JspUtil.isActive(request, "stock/stock")%>"><a href="/stock/stock/home"><fmt:message key="stock.stock.manager"></fmt:message></a></li>
		<li class="<%=JspUtil.isActive(request, "stock/dailyData")%>"><a href="/stock/dailyData/home"><fmt:message key="stock.dailyData.manager"></fmt:message></a></li>
		
		<li class="<%=JspUtil.isActive(request, "stock/reportCategory")%>"><a href="/stock/reportCategory/home"><fmt:message key="stock.reportCategory.manager"></fmt:message></a></li>
		<li class="<%=JspUtil.isActive(request, "stock/accountingElement")%>"><a href="/stock/accountingElement/home"><fmt:message key="stock.accountingElement.manager"></fmt:message></a></li>
		<li class="<%=JspUtil.isActive(request, "stock/accountingSubject")%>"><a href="/stock/accountingSubject/home"><fmt:message key="stock.accountingSubject.manager"></fmt:message></a></li>
		<li class="<%=JspUtil.isActive(request, "stock/financialData")%>"><a href="/stock/financialData/report">财务报表</a></li>
		<li class="<%=JspUtil.isActive(request, "stock/financialData")%>"><a href="/stock/financialData/home"><fmt:message key="stock.financialData.manager"></fmt:message></a></li>
		<li class="<%=JspUtil.isActive(request, "stock/financialReport")%>"><a href="/stock/financialReport/charts">统计报表</a></li>
	</ul>
</li>