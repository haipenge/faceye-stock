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
		$('.super-stock-data-init').click(function(){var stockId=$(this).parent().parent().attr("id");Stock.superInitStockData(stockId);return false;});
		$('.super-stock-data-init-in-board').click(function(){var stockId=$('input[name="stockId"]').val();Stock.superInitStockData(stockId);return false;});
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
	},
	/**
	 * 对股票数据进行超级初始化与计算
	 */
	superInitStockData:function(stockId){
		$.ajax({
			url:'/stock/stock/superInit',
			type:'post',
			data:{
				stockId:stockId
			},
			timeout:180000,
			beforeSend:function(xhr){
				var m = new Msg({
					msg : '已开始数据爬取任务...'
				});
				m.show();
				var win=new Modal({
				id:'data-crawl-wati-win',
				header:false,
				title:'数据爬取',
				body:'正在进行数据爬取,请稍后...'
				});
				win.show();
				return true;
			},
			success:function(data,status,xhr){
				if (data.result) {
					var m = new Msg({
						msg : '完成数据爬取'
					});
					m.show();
					$('#data-crawl-wati-win').remove();
					window.location.reload();
				}
			}
		});
	}
};
$(document).ready(function(){Stock.init();});