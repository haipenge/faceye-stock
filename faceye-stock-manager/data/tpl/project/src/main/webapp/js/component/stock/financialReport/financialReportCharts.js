/**
*说明:FinancialReport js 脚本
*作者:@haipenge
*/
var FinancialReportCharts={
  init:function(){
	  FinancialReportCharts.charts();
  },
  charts:function(){
	  var chart = new AChart({
	        theme : AChart.Theme.SmoothBase,
	        id : 'canvas',
	        width : 950,
	        height : 500,
	        plotCfg : {
	          margin : [50,50,80] //画板的边距
	        },
	        xAxis : {
	          categories : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
	        },
	        tooltip : {
	          valueSuffix : '°C',
	          shared : true, //是否多个数据序列共同显示信息
	          crosshairs : true //是否出现基准线
	        },
	        series : [{
	            name: 'Tokyo',
	            data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
	        }, {
	            name: 'London',
	            data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
	        }, {
	            name: 'Sahnghai',
	            data: [17.0, 16.9, 19.5, 24.5, 28.2, 31.5, 35.2, 36.5, 33.3, 28.3, 23.9, 19.6]
	        }]
	    });
	 
	    chart.render();
  }
};

$(document).ready(function(){
	FinancialReportCharts.init();
});