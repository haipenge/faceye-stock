/**
*说明:DataStat js 脚本
*作者:@haipenge
*/
var DataStat={
  init:function(){
	  /**
	   * 全选，全不选 
	   */
	  $('input[name="check-all"]').click(function(){
	    Check.onCheck($('input[name="check-all"]'),$('input[name="check-single"]'));
	  });
	  $('.multi-remove').click(function(){
		  DataStat.multiRemove();
	  });
	  var stockId=$('#stockId').val();
	  //营业总收入
	  FinancialData.chartsQuery(stockId,90,$('#operating_income'));
	  $('#stock_stat').click(function(){DataStat.stat();});
  },
  /**
   * 批量删除
   */
  multiRemove:function(){
	  var checkedIds=Check.getCheckedIds($('input[name="check-single"]'));
	  if(checkedIds!=''){
		  $.ajax({
			  url:'/stock/dataStat/multiRemove',
			  type:'post',
			  dataType:'json',
			  data:{
				  ids:checkedIds
			  },
			  success:function(data,textStatux,xhr){
				  var msg=new Msg({msg:'数据删除成功'});
				  var idArray=checkedIds.split(',');
				  for(var i=0;i<idArray.length;i++){
					  $('#'+idArray[i]).remove();
				  }
				  msg.show();
			  }
		  });
	  }else{
		  var msg=new Msg({msg:'请选择要删除的数据',type:'warning'});
		  msg.show();
	  }
  },
  /**
   * 数据分析
   */
  stat:function(){
	  var stockId=$('input[name="stockId"]').val();
	  $.ajax({
		  url:'/stock/dataStat/stat',
		  type:'post',
		  dataType:'json',
		  data:{
			  stockId:stockId
		  },
		  success:function(data,textStatus,xhr){
			  
		  }
	  });
  }
};

$(document).ready(function(){
	DataStat.init();
});