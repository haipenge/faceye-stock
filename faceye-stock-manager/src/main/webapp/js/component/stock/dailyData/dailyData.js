/**
 * 说明:DailyData js 脚本 作者:@haipenge
 */
var DailyData = {
	init : function() {
		$('#crawl-history').click(function() {
			DailyData.crawl();
			return false;
		});
		$('#stat-daily-data-2-find-star').click(function(){
			DailyData.statDailyData2FindStar();
			return false;
		});
	},
	crawl : function() {
		var stockId=$('input[name="stockId"]').val();
		$.ajax({
			url : '/stock/dailyData/crawl',
			type : 'post',
			data:{
				stockId:stockId
			},
			success : function(data, textStaus, xhr) {

			}
		});
	},
	/**
	 * 分析每日数据，获得星标
	 */
	statDailyData2FindStar:function(){
		var stockId=$('input[name="stockId"]').val();
		$.ajax({
			url:'/stock/dailyStat/statDailyData2FindStar',
			type:'post',
			data:{
				stockId:stockId
			},
			success:function(data,textStatus,xhr){
				
			}
		});
	}
};
$(document).ready(function(){DailyData.init();});