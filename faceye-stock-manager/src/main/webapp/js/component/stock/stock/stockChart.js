Date.prototype.format = function(format) {
       var date = {
              "M+": this.getMonth() + 1,
              "d+": this.getDate(),
              "h+": this.getHours(),
              "m+": this.getMinutes(),
              "s+": this.getSeconds(),
              "q+": Math.floor((this.getMonth() + 3) / 3),
              "S+": this.getMilliseconds()
       };
       if (/(y+)/i.test(format)) {
              format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
       }
       for (var k in date) {
              if (new RegExp("(" + k + ")").test(format)) {
                     format = format.replace(RegExp.$1, RegExp.$1.length == 1
                            ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
              }
       }
       return format;
};
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
             if(record.pe!=null){
             var date=new Date();
             date.setTime(record.date);
             peDates.push(date.format('yyyy-MM-dd'));
             pes.push(record.pe);
             }
           }
         }
         alert(1);
         var dom=$('#show-pe');
         var app={};
         var chart=echarts.init(document.getElementById('show-pe'));
         alert(peDates.length);
         alert(pes.length);
         var option={
           xAxis:{
             type:'category',
             data:peDates
           },
           yAxis:{
             type:'value'
           },
           series:[{
             data:pes,
             type:'line',
             smooth:true
           }]
         };
         alert(4);
         option = null;
         option = {

    // Make gradient line here
    visualMap: [{
        show: false,
        type: 'continuous',
        seriesIndex: 0,
        min: 0,
        max: 400
    }, {
        show: false,
        type: 'continuous',
        seriesIndex: 1,
        dimension: 0,
        min: 0,
        max: peDates.length - 1
    }],


    title: [{
        left: 'center',
        text: 'Gradient along the y axis'
    }, {
        top: '55%',
        left: 'center',
        text: 'Gradient along the x axis'
    }],
    tooltip: {
        trigger: 'axis'
    },
    xAxis: [{
        data: peDates
    }, {
        data: peDates,
        gridIndex: 1
    }],
    yAxis: [{
        splitLine: {show: false}
    }, {
        splitLine: {show: false},
        gridIndex: 1
    }],
    grid: [{
        bottom: '60%'
    }, {
        top: '60%'
    }],
    series: [{
        type: 'line',
        showSymbol: false,
        data: pes
    }, {
        type: 'line',
        showSymbol: false,
        data: pes,
        xAxisIndex: 1,
        yAxisIndex: 1
    }]
};


option = {
    tooltip: {
        trigger: 'axis',
        position: function (pt) {
            return [pt[0], '10%'];
        }
    },
    title: {
        left: 'center',
        text: '大数据量面积图',
    },
    toolbox: {
        feature: {
            dataZoom: {
                yAxisIndex: 'none'
            },
            restore: {},
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: peDates
    },
    yAxis: {
        type: 'value',
        boundaryGap: [0, '100%']
    },
    dataZoom: [{
        type: 'inside',
        start: 0,
        end: 10
    }, {
        start: 0,
        end: 10,
        handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
        handleSize: '80%',
        handleStyle: {
            color: '#fff',
            shadowBlur: 3,
            shadowColor: 'rgba(0, 0, 0, 0.6)',
            shadowOffsetX: 2,
            shadowOffsetY: 2
        }
    }],
    series: [
        {
            name:'模拟数据',
            type:'line',
            smooth:true,
            symbol: 'none',
            sampling: 'average',
            itemStyle: {
                normal: {
                    color: 'rgb(255, 70, 131)'
                }
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgb(255, 158, 68)'
                    }, {
                        offset: 1,
                        color: 'rgb(255, 70, 131)'
                    }])
                }
            },
            data: pes
        }
    ]
};



         chart.setOption(option,true);
       }
     });
   }
};
$(document).ready(function(){StockChart.init();});