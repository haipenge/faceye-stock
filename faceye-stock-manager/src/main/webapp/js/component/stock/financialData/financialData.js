/**
 * 说明:FinancialData js 脚本 作者:@haipenge
 */
var FinancialData = {
	init : function() {
		/**
		 * 全选，全不选
		 */
		$('input[name="check-all"]').click(function() {
			Check.onCheck($('input[name="check-all"]'), $('input[name="check-single"]'));
		});
		$('.multi-remove').click(function() {
			FinancialData.multiRemove();
		});
		$('.start-crawl').click(function() {
			FinancialData.crawlStockFinancialData();
		});

	},
	/**
	 * 批量删除
	 */
	multiRemove : function() {
		var checkedIds = Check.getCheckedIds($('input[name="check-single"]'));
		if (checkedIds != '') {
			$.ajax({
				url : '/stock/financialData/multiRemove',
				type : 'post',
				dataType : 'json',
				data : {
					ids : checkedIds
				},
				success : function(data, textStatux, xhr) {
					var msg = new Msg({
						msg : '数据删除成功'
					});
					var idArray = checkedIds.split(',');
					for (var i = 0; i < idArray.length; i++) {
						$('#' + idArray[i]).remove();
					}
					msg.show();
				}
			});
		} else {
			var msg = new Msg({
				msg : '请选择要删除的数据',
				type : 'warning'
			});
			msg.show();
		}
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
	 * 对某一会计科目的绘图统计
	 */
	chartsQuery : function(stockId, accountingSubjectId, el) {
		$.ajax({
			url : '/stock/financialData/chartsQuery',
			type : 'post',
			data : {
				stockId : stockId,
				accountingSubjectId : accountingSubjectId
			},
			success : function(data, textStatus, xhr) {
				var datas=[];
				var dates=[];
				for(var i=0;i<data.length;i++){
					var record=data[i];
					var newDate=new Date();
					newDate.setTime(record.date);
					var dateStr=newDate.format('yyyy-MM-dd');
//					var array=[dateStr,record.data];
					var arr1=[record.data/1000000,record.date];
					var arr2=[record.data/1000000,dateStr];
					datas.push(arr1);
					dates.push(arr2);
				}
				var plot_statistics = $.plot($(el), [ {
					data : datas,
					label : "￥"
				} ], {
					series : {
						lines : {
							show : true,
							lineWidth : 2,
							fill : true,
							fillColor : {
								colors : [ {
									opacity : 0.25
								}, {
									opacity : 0.25
								} ]
							}
						},
						points : {
							show : true
						},
						shadowSize : 2
					},
					legend : {
						show : false
					},
					grid : {
						labelMargin : 10,
						axisMargin : 500,
						hoverable : true,
						clickable : true,
						tickColor : "rgba(0,0,0,0.15)",
						borderWidth : 0
					},
					colors : [ "#50ACFE", "#4A8CF7", "#52e136" ],
					xaxis : {
						ticks : dates,
						tickDecimals : 0
					},
					yaxis : {
						ticks : 5,
						tickDecimals : 0
					}
				});
			}
		});
	}
};

$(document).ready(function() {
	FinancialData.init();
});