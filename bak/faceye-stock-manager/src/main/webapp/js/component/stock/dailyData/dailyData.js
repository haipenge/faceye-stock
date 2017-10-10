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
		$('#remove-history').click(function(){
			DailyData.remove();
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
	},
	/**
	 * 清空历史数据 
	 */
	remove:function(){
		var stockId=$('input[name="stockId"]').val();
		$.ajax({
			url:"/stock/dailyData/remove",
			type:'post',
			data:{
				stockId:stockId
			},
			success:function(data,status,xhr){
				if(data.result){
					var msg=new Msg({msg:'数据已清空.'});
					msg.show();
				}
			}
		});
	}
};
$(document).ready(function(){DailyData.init();});