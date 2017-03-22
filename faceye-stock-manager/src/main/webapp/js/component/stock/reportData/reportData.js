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
			success : function(data, textStatus, xhr) {
				if (data.result) {
					var m = new Msg({
						msg : '已开始数据爬取任务...'
					});
					m.show();
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
			success : function(data, textStatus, xhr) {
				var m = new Msg({
					msg : '已完成分析.'
				});
				m.show();
			}
		});
	},
};
$(document).ready(function() {
	ReportData.init();
	return;
});