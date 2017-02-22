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
		$('.start-crawl').click(function(){
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
        var stockId=$('#stock_id').val();
        $.ajax({
        	url:'/stock/financialData/crawlStockFinancialData',
        	type:'post',
        	data:{
        		id:stockId
        	},
        	success:function(data,textStatus,xhr){
        		if(data.result){
        			var m=new Msg({msg:'已开始数据爬取任务...'});
        			m.show();
        		}
        	}
        });
	}
};

$(document).ready(function() {
	FinancialData.init();
});