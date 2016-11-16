<li><a href="#"><i class="fa fa-list-alt"></i><span>Stocks</span></a>
						<ul class="sub-menu">
<li class="<%=JspUtil.isActive(request, "stock/stock")%>"><a  href="/stock/stock/home"><fmt:message key="stock.stock.manager"></fmt:message></a></li>
<li class="<%=JspUtil.isActive(request, "dailyData")%>"><a  href="/stock/dailyData/home"><fmt:message key="stock.dailyData.manager"></fmt:message></a></li>
</ul>
</li>