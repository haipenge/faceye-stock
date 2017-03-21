/**
 * 说明:Stock js 脚本 作者:@haipenge
 */
var Stock = {
	init : function() {
		$('#init-stock-category').click(function(){Stock.initStockCategory();return false;});
		$('#toggle-category').click(function(){$('.category-container').toggle();return false;})
	},
	/**
	 * 初始化股票分类
	 */
	initStockCategory : function() {
		$.ajax({
			url : '/stock/stock/initStockCategory',
			type : 'post',
			success : function(data, textStatus, xhr) {
				var m = new Msg({
					msg : '股票分类初始化成功'
				});
				m.show();
			}
		});
	}
};
$(document).ready(function(){Stock.init();});