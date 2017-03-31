<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/stock/dailyData/dailyData.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/stock/dailyData/dailyData.js"/>"></script>
<div class="panel panel-primary">
	<div class="panel-heading">
		<div class="panel-title">
			<fmt:message key="stock.dailyData.detail"></fmt:message>
		</div>
	</div>
	<div class="panel-body">
			<fieldset>
								<div class="form-group">
					<label class="col-md-4 control-label" for="dailyData">
					  <fmt:message key="stock.dailyData.kaipanjia"></fmt:message>
					 </label>
					<div class="col-md-4">
						${dailyData.kaipanjia}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="dailyData">
					  <fmt:message key="stock.dailyData.shoupanjia"></fmt:message>
					 </label>
					<div class="col-md-4">
						${dailyData.shoupanjia}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="dailyData">
					  <fmt:message key="stock.dailyData.dangqianjiage"></fmt:message>
					 </label>
					<div class="col-md-4">
						${dailyData.dangqianjiage}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="dailyData">
					  <fmt:message key="stock.dailyData.jintianzuigaojia"></fmt:message>
					 </label>
					<div class="col-md-4">
						${dailyData.jintianzuigaojia}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="dailyData">
					  <fmt:message key="stock.dailyData.jintianzuidijia"></fmt:message>
					 </label>
					<div class="col-md-4">
						${dailyData.jintianzuidijia}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="dailyData">
					  <fmt:message key="stock.dailyData.chengjiaogupiaoshu"></fmt:message>
					 </label>
					<div class="col-md-4">
						${dailyData.chengjiaogupiaoshu}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="dailyData">
					  <fmt:message key="stock.dailyData.chengjiaojine"></fmt:message>
					 </label>
					<div class="col-md-4">
						${dailyData.chengjiaojine}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="dailyData">
					  <fmt:message key="stock.dailyData.date"></fmt:message>
					 </label>
					<div class="col-md-4">
						${dailyData.date}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="dailyData">
					  <fmt:message key="stock.dailyData.stockId"></fmt:message>
					 </label>
					<div class="col-md-4">
						${dailyData.stockId}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="dailyData">
					  <fmt:message key="stock.dailyData.stockCode"></fmt:message>
					 </label>
					<div class="col-md-4">
						${dailyData.stockCode}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="dailyData">
					  <fmt:message key="stock.dailyData.stockName"></fmt:message>
					 </label>
					<div class="col-md-4">
						${dailyData.stockName}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="dailyData">
					  <fmt:message key="stock.dailyData.volume"></fmt:message>
					 </label>
					<div class="col-md-4">
						${dailyData.volume}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="dailyData">
					  <fmt:message key="stock.dailyData.money"></fmt:message>
					 </label>
					<div class="col-md-4">
						${dailyData.money}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="dailyData">
					  <fmt:message key="stock.dailyData.avg5"></fmt:message>
					 </label>
					<div class="col-md-4">
						${dailyData.avg5}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="dailyData">
					  <fmt:message key="stock.dailyData.avg10"></fmt:message>
					 </label>
					<div class="col-md-4">
						${dailyData.avg10}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="dailyData">
					  <fmt:message key="stock.dailyData.avg20"></fmt:message>
					 </label>
					<div class="col-md-4">
						${dailyData.avg20}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="dailyData">
					  <fmt:message key="stock.dailyData.avg30"></fmt:message>
					 </label>
					<div class="col-md-4">
						${dailyData.avg30}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="dailyData">
					  <fmt:message key="stock.dailyData.avg60"></fmt:message>
					 </label>
					<div class="col-md-4">
						${dailyData.avg60}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="dailyData">
					  <fmt:message key="stock.dailyData.avg120"></fmt:message>
					 </label>
					<div class="col-md-4">
						${dailyData.avg120}
					</div>
				</div>
								<div class="form-group">
					<label class="col-md-4 control-label" for="dailyData">
					  <fmt:message key="stock.dailyData.avg250"></fmt:message>
					 </label>
					<div class="col-md-4">
						${dailyData.avg250}
					</div>
				</div>
				<tr>
	<td class＝"bg-title-col width-p-20"><fmt:message key="stock.dailyData.yesterdayPrice"></fmt:message></td>
	<td>${dailyData.yesterdayPrice}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->

				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			</fieldset>
	</div>
</div>