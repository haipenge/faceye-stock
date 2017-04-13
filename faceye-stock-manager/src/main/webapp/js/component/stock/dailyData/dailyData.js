/**
 * 说明:DailyData js 脚本 作者:@haipenge
 */
var DailyData = {
	init : function() {
		$('#crawl-history').click(function() {
			DailyData.crawl();
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
	}
};
$(document).ready(function(){DailyData.init();});