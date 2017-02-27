/**
 * 说明:DataStat js 脚本 作者:@haipenge
 */
var DataStat = {
	init : function() {
		/**
		 * 全选，全不选
		 */
		$('input[name="check-all"]').click(function() {
			Check.onCheck($('input[name="check-all"]'), $('input[name="check-single"]'));
		});
		$('.multi-remove').click(function() {
			DataStat.multiRemove();
		});
		var stockId = $('input[name="stockId"]').val();
		// 营业总收入
		// FinancialData.chartsQuery(stockId,90,$('#operating_income'));
		FinancialData.chartsQuery({
			stockId : stockId,
			accountingSubjectId : 90
		}, $('#operating_income'));
		$('#stock_stat').click(function() {
			DataStat.stat();
		});
		$('.operating_income_0').click(function() {
			$('#operating_income').empty();
			FinancialData.chartsQuery({
				stockId : stockId,
				accountingSubjectId : 90,
				type : 0
			}, $('#operating_income'));
		});
		// 营业收入一季报绘图
		$('.operating_income_1').click(function() {
			$('#operating_income').empty();
			FinancialData.chartsQuery({
				stockId : stockId,
				accountingSubjectId : 90,
				type : 1
			}, $('#operating_income'));
		});
		// 营业收入中报绘图
		$('.operating_income_2').click(function() {
			$('#operating_income').empty();
			FinancialData.chartsQuery({
				stockId : stockId,
				accountingSubjectId : 90,
				type : 2
			}, $('#operating_income'));
		});
		// 营业收入三季报绘图
		$('.operating_income_3').click(function() {
			$('#operating_income').empty();
			FinancialData.chartsQuery({
				stockId : stockId,
				accountingSubjectId : 90,
				type : 3
			}, $('#operating_income'));
		});
		// 营业收入-全部
		$('.operating_income_4').click(function() {
			$('#operating_income').empty();
			FinancialData.chartsQuery({
				stockId : stockId,
				accountingSubjectId : 90,
				type : 4
			}, $('#operating_income'));
		});
		$('.operating_data_detail').click(function() {
			$('#operating_data_detail').toggle();
		});
		// 净利润绘图:
		FinancialData.chartsQuery({
			stockId : stockId,
			accountingSubjectId : 128
		}, $('#net_profit'));

		$('.net_profit_0').click(function() {
			$('#net_profit').empty();
			FinancialData.chartsQuery({
				stockId : stockId,
				accountingSubjectId : 128,
				type : 0
			}, $('#net_profit'));
		});

		$('.net_profit_1').click(function() {
			$('#net_profit').empty();
			FinancialData.chartsQuery({
				stockId : stockId,
				accountingSubjectId : 128,
				type : 1
			}, $('#net_profit'));
		});
		$('.net_profit_2').click(function() {
			$('#net_profit').empty();
			FinancialData.chartsQuery({
				stockId : stockId,
				accountingSubjectId : 128,
				type : 2
			}, $('#net_profit'));
		});
		$('.net_profit_3').click(function() {
			$('#net_profit').empty();
			FinancialData.chartsQuery({
				stockId : stockId,
				accountingSubjectId : 128,
				type : 3
			}, $('#net_profit'));
		});
		$('.net_profit_4').click(function() {
			$('#net_profit').empty();
			FinancialData.chartsQuery({
				stockId : stockId,
				accountingSubjectId : 128,
				type : 4
			}, $('#net_profit'));
		});

		/**
		 * 绘制总资产回报率
		 */
		DataStat.chartDataStat({
			stockId : stockId,
			type : 0
		}, $('#returnOnAssets_chart'));

		$('.returnOnAssets_0').click(function() {
			DataStat.chartDataStat({
				stockId : stockId,
				type : 0
			}, $('#returnOnAssets_chart'));
		});
		$('.returnOnAssets_1').click(function() {
			DataStat.chartDataStat({
				stockId : stockId,
				type : 1
			}, $('#returnOnAssets_chart'));
		});
		$('.returnOnAssets_2').click(function() {
			DataStat.chartDataStat({
				stockId : stockId,
				type : 2
			}, $('#returnOnAssets_chart'));
		});

		$('.returnOnAssets_3').click(function() {
			DataStat.chartDataStat({
				stockId : stockId,
				type : 3
			}, $('#returnOnAssets_chart'));
		});
		$('.returnOnAssets_4').click(function() {
			DataStat.chartDataStat({
				stockId : stockId,
				type : 4
			}, $('#returnOnAssets_chart'));
		});

		$('#toggle_returnOnAssets').click(function() {
			$('#returnOnAssets_list').toggle();
		});

	},
	/**
	 * 批量删除
	 */
	multiRemove : function() {
		var checkedIds = Check.getCheckedIds($('input[name="check-single"]'));
		if (checkedIds != '') {
			$.ajax({
				url : '/stock/dataStat/multiRemove',
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

			}
		});
	},
	/**
	 * 数据分析绘图
	 */
	chartDataStat : function(params, el) {
		FinancialData.bindPlothover(el);
		$.ajax({
			url : '/stock/dataStat/chartsQuery',
			type : 'post',
			data : params,
			success : function(data, textStatus, xhr) {
				var datas = [];
				var dates = [];
				var count = 1;
				for (var i = 0; i < data.length; i++) {
					var record = data[i];
					var newDate = new Date();
					newDate.setTime(record.dateCycle);
					var dateStr = newDate.format('yyyy-MM-dd');
					// var array=[dateStr,record.data];
					var arr1 = [ count, record.returnOnAssets * 100 ];
					var arr2 = [ count, dateStr ];
					datas.push(arr1);
					dates.push(arr2);
					count++;
				}
				var plot_statistics = $.plot($(el), [ {
					data : datas,
					label : "%"
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
	DataStat.init();
});