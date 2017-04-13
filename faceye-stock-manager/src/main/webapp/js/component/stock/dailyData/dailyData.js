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
		$.ajax({
			url : '/stock/dailyData/crawl',
			type : 'post',
			success : function(data, textStaus, xhr) {

			}
		});
	}
};
$(document).ready(function(){DailyData.init();});