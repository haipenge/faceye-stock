var StockChart = {
	init : function() {
		StockChart.drawPe();
	},
	/**
	 * 绘制PE变化曲线
	 */
	drawPe : function() {
		var stockId = $('input[name="stockId"]').val();
		// graph pe,pb .
		$.ajax({
			url : '/stock/dailyData/showDailyData',
			type : 'get',
			data : {
				stockId : stockId
			},
			success : function(data, textStatus, xhr) {
				var pes = new Array();
				var dates = new Array();
				var pbs = new Array();
				var grossProfitMargins = new Array();
				if (data != null) {
					for (var i = 0; i < data.length; i++) {
						var record = data[i];
						if (record.pe != null) {
							var date = new Date();
							date.setTime(record.date);
							dates.push(date.format('yyyy-MM-dd'));
							pes.push(record.pe);
							pbs.push(record.pb);
							grossProfitMargins.push(record.grossProfitMargin);
						}
					}
				}
				StockChart.buildChart(pes, dates, 'PE', 'show-pe');
				StockChart.buildChart(pbs, dates, 'PB', 'show-pb');
			}
		});
/**
 * 对DataStat对像数据进行展现 
 */
		$.ajax({
			url : '/stock/dataStat/chartsQuery',
			type : 'get',
			data : {
				stockId : stockId
			},
			success:function(data,textStatus,xhr){
				var dates=new Array();
				var grossProfitMargins=new Array();
				var netProfitMargins=new Array();
				var totalAssetsTurnovers=new Array();
				var totalAssetsNetProfitMargins=new Array();
				var debtToAssetsRatios=new Array();
				var roes=new Array();
				var coreProfitMargins=new Array();
				var epss=new Array();
				var bpss=new Array();
				for(var i=0;i<data.length;i++){
					var record=data[i];
					var date=new Date();
					date.setTime(record.dateCycle);
					dates.push(date.format('yyyy-MM-dd'));
					grossProfitMargins.push(record.grossProfitMargin);
					netProfitMargins.push(record.netProfitMargin);
					totalAssetsTurnovers.push(record.totalAssetsTurnover);
					totalAssetsNetProfitMargins.push(record.totalAssetsNetProfitMargin);
					debtToAssetsRatios.push(record.debtToAssetsRatio);
					roes.push(record.roe);
					coreProfitMargins.push(record.coreProfitMargin);
					epss.push(record.eps);
					bpss.push(record.bps);
				}
				StockChart.buildChart(grossProfitMargins,dates,'毛利率','show-grossProfitMargin');
				StockChart.buildChart(netProfitMargins,dates,'净利率','show-netProfitMargin');
				StockChart.buildChart(totalAssetsTurnovers,dates,'资产周转率','show-totalAssetsTurnover');
				StockChart.buildChart(totalAssetsNetProfitMargins,dates,'总资产净利率','show-totalAssetsNetProfitMargin');
				StockChart.buildChart(debtToAssetsRatios,dates,'资产负债率','show-debtToAssetsRatio');
				StockChart.buildChart(roes,dates,'净资产收益率','show-roe');
				StockChart.buildChart(coreProfitMargins,dates,'核心利润率','show-coreProfitMargin');
				StockChart.buildChart(epss,dates,'EPS','show-eps');
				StockChart.buildChart(bpss,dates,'BPS','show-bps');
			}
		});
	},
	/**
	 * 构建曲线图
	 */
	buildChart : function(datas, dates, title, el) {
		var chart = echarts.init(document.getElementById(el));
		var peOption = StockChart.buildOption(datas, dates, title);
		chart.setOption(peOption, true);
	},

	buildOption : function(datas, dates, title) {
		var option = {
			tooltip : {
				trigger : 'axis',
				position : function(pt) {
					return [ pt[0], '10%' ];
				}
			},
			title : {
				left : 'center',
				text : title,
				top : 0
			},
			toolbox : {
				feature : {
					dataZoom : {
						yAxisIndex : 'none'
					},
					restore : {},
					saveAsImage : {}
				}
			},
			xAxis : {
				type : 'category',
				boundaryGap : false,
				data : dates
			},
			yAxis : {
				type : 'value',
				boundaryGap : [ 0, '100%' ]
			},
			dataZoom : [
					{
						type : 'inside',
						start : 0,
						end : 100
					},
					{
						start : 0,
						end : 20,
						handleIcon : 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
						handleSize : '80%',
						handleStyle : {
							color : '#fff',
							shadowBlur : 3,
							shadowColor : 'rgba(0, 0, 0, 0.6)',
							shadowOffsetX : 2,
							shadowOffsetY : 2
						}
					} ],
			series : [ {
				name : title,
				type : 'line',
				smooth : true,
				symbol : 'none',
				sampling : 'average',
				itemStyle : {
					normal : {
						color : 'rgb(255, 70, 131)'
					}
				},
				areaStyle : {
					normal : {
						color : new echarts.graphic.LinearGradient(0, 0, 0, 1,
								[ {
									offset : 0,
									color : 'rgb(255, 158, 68)'
								}, {
									offset : 1,
									color : 'rgb(255, 70, 131)'
								} ])
					}
				},
				data : datas
			} ]
		};
		return option;
	}
};
$(document).ready(function() {
	StockChart.init();
});