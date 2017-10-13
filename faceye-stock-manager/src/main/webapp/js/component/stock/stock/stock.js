/**
 * 说明:Stock js 脚本 作者:@haipenge
 */
var Stock = {
	init : function() {
		$('#init-stock-category').click(function(){Stock.initStockCategory();return false;});
		$('#toggle-category').click(function(){$('.category-container').toggle();return false;});
		$('#btn-multi-stock-compare').click(function(){Stock.multiStockReportCompare();});
		$('#init-system').click(function(){Stock.initSystem();return false;});
		$('#export').click(function(){Stock.exportExcel();return false;});
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
		if(checkedIds=='' || ids.length>6){
			var m = new Msg({
				msg : '请选择5只以内股票进行比对',
				type:'warning'
			});
			m.show();
			
		}else{
			location.href='/stock/reportData/compare?stockIds='+checkedIds;
		}
	},
	/**
	*初始化基础配置
	*/
	initSystem:function(){
	  $.ajax({
	    url:'/stock/init',
	    type:'post',
	    dataType:'json',
	    success:function(data,textStatus,xhr){
	       if(data.msg){
	         var m = new Msg({
					msg : '系统初始化成功'
				});
				m.show();
	       }
	    }
	  });
	},
	/**
	 * 将股票导出到Excel
	 */
	exportExcel:function(){
		var url="/stock/stock/export";
		$('#query-form').attr('action',url);
		$('#query-form').submit();
		url='/stock/stock/home';
		$('#query-form').attr('action',url);
	}
};
$(document).ready(function(){Stock.init();});