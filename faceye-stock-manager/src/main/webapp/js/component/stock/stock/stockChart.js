var StockChart={
   init:function(){
     StockChart.drawPe();
   },
   /**
   *绘制PE变化曲线
   */
   drawPe:function(){
     var stockId=$('input[name="stockId"]').val();
     $.ajax({
       url:'/stock/dailyData/showDailyData',
       type:'get',
       data:{
          stockId:stockId
       },
       success:function(data,textStatus,xhr){
         var pes=new Array();
         var peDates=new Array();
         if(data!=null){
           for(var i=0;i<data.length;i++){
             var record=data[i];
             peDates.push(i);
             pes.push(record.pe);
           }
         }
         var chart = new AChart({
	        theme : AChart.Theme.SmoothBase,
	        id : 'show-pe',
	        width : 950,
	        height : 500,
	        plotCfg : {
	          margin : [50,50,80] //画板的边距
	        },
	        xAxis : {
	          categories : peDates
	        },
	        tooltip : {
	          valueSuffix : '',
	          shared : true, //是否多个数据序列共同显示信息
	          crosshairs : true //是否出现基准线
	        },
	        series : [{
	            name: 'PE',
	            data: pes
	        }]
	    });
	 
	    chart.render();
       }
     });
   }
};
$(document).ready(function(){StockChart.init();});