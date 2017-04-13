<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/dailyData/dailyData.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/dailyData/dailyData.js"/>"></script>

<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty dailyData.id}">
					<fmt:message key="stock.dailyData.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="stock.dailyData.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
		<form action="<c:url value="/stock/dailyData/save"/>" method="post" role="form" class="form-horizontal">
			<input type="hidden" name="id" value="${dailyData.id}" />
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="kaipanjia"> <fmt:message key="stock.dailyData.kaipanjia"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="kaipanjia" value="${dailyData.kaipanjia}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="shoupanjia"> <fmt:message key="stock.dailyData.shoupanjia"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="shoupanjia" value="${dailyData.shoupanjia}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="dangqianjiage"> <fmt:message key="stock.dailyData.dangqianjiage"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="dangqianjiage" value="${dailyData.dangqianjiage}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="jintianzuigaojia"> <fmt:message
							key="stock.dailyData.jintianzuigaojia"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="jintianzuigaojia" value="${dailyData.jintianzuigaojia}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="jintianzuidijia"> <fmt:message
							key="stock.dailyData.jintianzuidijia"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="jintianzuidijia" value="${dailyData.jintianzuidijia}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="chengjiaogupiaoshu"> <fmt:message
							key="stock.dailyData.chengjiaogupiaoshu"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="chengjiaogupiaoshu" value="${dailyData.chengjiaogupiaoshu}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="chengjiaojine"> <fmt:message key="stock.dailyData.chengjiaojine"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="chengjiaojine" value="${dailyData.chengjiaojine}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="date"> <fmt:message key="stock.dailyData.date"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="date" value="${dailyData.date}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="stockId"> <fmt:message key="stock.dailyData.stockId"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="stockId" value="${dailyData.stockId}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="stockCode"> <fmt:message key="stock.dailyData.stockCode"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="stockCode" value="${dailyData.stockCode}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="stockName"> <fmt:message key="stock.dailyData.stockName"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="stockName" value="${dailyData.stockName}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="avg5"> <fmt:message key="stock.dailyData.avg5"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="avg5" value="${dailyData.avg5}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="avg10"> <fmt:message key="stock.dailyData.avg10"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="avg10" value="${dailyData.avg10}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="avg20"> <fmt:message key="stock.dailyData.avg20"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="avg20" value="${dailyData.avg20}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="avg30"> <fmt:message key="stock.dailyData.avg30"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="avg30" value="${dailyData.avg30}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="avg60"> <fmt:message key="stock.dailyData.avg60"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="avg60" value="${dailyData.avg60}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="avg120"> <fmt:message key="stock.dailyData.avg120"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="avg120" value="${dailyData.avg120}" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="avg250"> <fmt:message key="stock.dailyData.avg250"></fmt:message>
					</label>
					<div class="col-md-6">
						<input type="text" name="avg250" value="${dailyData.avg250}" class="form-control">
					</div>
				</div>
				<div class="form-group">
	<label class="col-md-2 control-label" for="yesterdayPrice"> <spring:message
			code="stock.dailyData.yesterdayPrice"/>
	</label>
	<div class="col-md-6">
		<form:input path="yesterdayPrice" cssClass="form-control"/>
		<form:errors path="yesterdayPrice" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="ema12"> <spring:message
			code="stock.dailyData.ema12"/>
	</label>
	<div class="col-md-6">
		<form:input path="ema12" cssClass="form-control"/>
		<form:errors path="ema12" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="ema26"> <spring:message
			code="stock.dailyData.ema26"/>
	</label>
	<div class="col-md-6">
		<form:input path="ema26" cssClass="form-control"/>
		<form:errors path="ema26" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="dif"> <spring:message
			code="stock.dailyData.dif"/>
	</label>
	<div class="col-md-6">
		<form:input path="dif" cssClass="form-control"/>
		<form:errors path="dif" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="dea"> <spring:message
			code="stock.dailyData.dea"/>
	</label>
	<div class="col-md-6">
		<form:input path="dea" cssClass="form-control"/>
		<form:errors path="dea" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="macd"> <spring:message
			code="stock.dailyData.macd"/>
	</label>
	<div class="col-md-6">
		<form:input path="macd" cssClass="form-control"/>
		<form:errors path="macd" cssClass="error"/>
	</div>
</div>
<!--@generate-entity-jsp-property-update@-->






				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="global.submit.save"></fmt:message>
						</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</div>

