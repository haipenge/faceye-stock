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
	},
	/**
	 * 多只股票财报数据比对
	 */
	multiStockReportCompare:function(){
		var checkedIds = Check.getCheckedIds($('input[name="check-single"]'));
		var ids=checkedIds.split(',');
		if(checkedIds!=''&& ids.length>1){
			location.href='/stock/reportData/compare?stockIds='+checkedIds;
		}else{
			var m = new Msg({
				msg : '请选择比对的股票',
				type:'warning'
			});
			m.show();
		}
	}
};
$(document).ready(function(){Stock.init();});