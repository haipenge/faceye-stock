var ReportData = {
	init : function() {
		$('.start-crawl').click(function() {
			ReportData.crawlStockFinancialData();
		});
		$('.stock-stat').click(function() {
			ReportData.stat();
			return false;
		});
	},
	/**
	 * 爬取股票数据
	 */
	crawlStockFinancialData : function() {
		var stockId = $('#stock_id').val();
		$.ajax({
			url : '/stock/financialData/crawlStockFinancialData',
			type : 'post',
			data : {
				id : stockId
			},
			beforeSend:function(xhr){
				var m = new Msg({
					msg : '已开始数据爬取任务...'
				});
				m.show();
				return true;
			},
			success : function(data, textStatus, xhr) {
				if (data.result) {
					var m = new Msg({
						msg : '完成数据爬取'
					});
					m.show();
					window.location.reload();
				}
			}
		});
	},
	/**
	 * 数据分析
	 */
	stat : function() {
		var stockId = $('input[name="stockId"]').val();
		$.ajax({
			url : '/stock/dataStat/stat',
			type : 'post',
			dataType : 'json',
			data : {
				stockId : stockId
			},
			beforeSend:function(xhr){
				var m = new Msg({
					msg : '正在进行数据分析,请稍候...'
				});
				m.show();
				return true;
			},
			success : function(data, textStatus, xhr) {
				var m = new Msg({
					msg : '已完成分析.'
				});
				m.show();
				window.location.reload();
			}
		});
	},
};
$(document).ready(function() {
	ReportData.init();
	return;
});