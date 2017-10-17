<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta content="yes" name="apple-mobile-web-app-capable">
        <meta content="yes" name="apple-touch-fullscreen">
        <meta content="telephone=no,email=no" name="format-detection">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
        <title>成交数据</title>
        <link rel="stylesheet" href="static/frozenui/css/frozen.css">
        <link rel="stylesheet" type="text/css" href="static/css/calendarApp.css">
        <link rel="stylesheet" type="text/css" href="static/css/anchangSuitable.css">
        <link rel="stylesheet" type="text/css" href="static/css/liberatingData.css">
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="static/js/echarts.min.js"></script>
        <script type="text/javascript" src="static/js/success.js"></script>
    </head>
    <body ontouchstart>
         <header class="ui-header ui-header-positive ui-border-b">
            <i class="ui-icon-return" onclick="history.back()"></i><h1>成交数据</h1>
        </header>
        <section id="rili" style="display: none;z-index: 2000;">
        <header class="ui-header ui-header-positive ui-border-b">
            <i class="ui-icon-return" onclick="closeThis()"></i><h1>选择日期</h1>
        </header>
        <div class="wrap">
            <ul class="week-f">
                <li>一</li>
                <li>二</li>
                <li>三</li>
                <li>四</li>
                <li>五</li>
                <li class="wk">六</li>
                <li class="wk">日</li>
            </ul>

            <div id="calendar"></div>

           <!--  <P class="act">
                <button id="prevMonth">上一月</button>                 
                <button id="nextMonth">下一月</button> 
                <button id="ok" @click.stop="show=false">确定</button>
            
             </P> --> 
</div>
<script src="static/js/zepto.js"></script>
<script src="static/js/jqmobi.js"></script>
<script src="static/js/calendarApp.js"></script>
<script>
/*关闭日期*/
            function closeThis() {
                $("#rili").css("display","none");
                $(".ui-container").css("display","block");
            }
    var calendarIns = this.calendarIns
    var starTime = null;
    var endTime = null;
    calendarIns = new calendar.Calendar({
      count: 12,
      selectDateName: '',
      maxDate: new Date(),
      isShowHoliday: true,
      isShowWeek: false
    })
    $(calendarIns).on('afterSelectDate', function (event, params) {
      // console.log('after select date')
      // var curItem = params.curItem
      var date = params.date
      // var dateName = params.dateName

      calendarIns.setSelectDate(date, params.startEnd)
      if (calendarIns.startDate && calendarIns.endDate) {
        starTime = calendarIns.startDate+" 00:00:00";
        endTime = calendarIns.endDate+" 23:59:59";
        $(".ui-arrowlink").html(calendarIns.startDate+' 至 '+calendarIns.endDate);
        $("#rili").css("display","none");
        window.scrollTo(0, 0);
        $(".ui-container").css("display","block");
        getSuccessData();
      }
      if (calendarIns.startDate==calendarIns.endDate) {
        console.log('选择了'+calendarIns.endDate)
      }
    })
</script>
        </section>
        <section class="ui-container" style="display: block;">
            <div class="top">
                <div class="topbar">
                    <div class="topLeft">
                        <img src="static/images/riliPic.png" alt="">
                    </div>
                    <div class="topRight">
                        <h2 class="ui-arrowlink"><span class="ui-panel-subtitle"></span></h2>
                    </div>
                </div>                
            </div>
            <div id="main"></div>
            <div class="maintip">成交数量：<span id="dealCount">0</span></div>
            <div class="bottom">
                <div class="titp">
                    <p>详细数据</p>
                </div>
                <div class="list">
                    <div class="listbar">
                        <img src="static/images/quan1.png" alt="">
                        <div class="rightlist">
                            <span class="ziti1">已锁定房源货值</span>
                            <span class="ziti2 right" id="lockedMoney">0</span>
                        </div>
                    </div>
                </div>
                <div class="list">
                    <div class="listbar">
                        <img src="static/images/quan2.png" alt="">
                        <div class="rightlist">
                            <span class="ziti1">已签约房源货值</span>
                            <span class="ziti3 right" id="signedMoney">0%</span>
                        </div>
                    </div>
                </div>
                <div class="list">
                    <div class="listbar">
                        <img src="static/images/quan3.png" alt="">
                        <div class="rightlist">
                            <span class="ziti1">平台客户认购占比</span>
                            <span class="ziti4 right" id="systemCusEnterBuyRate">0%</span>
                        </div>
                    </div>
                </div>
                <div class="list">
                    <div class="listbar">
                        <img src="static/images/quan4.png" alt="">
                        <div class="rightlist">
                            <span class="ziti1">平台客户签约占比</span>
                            <span class="ziti5 right" id="systemSignRate">0%</span>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <input id="todayPay" type="hidden">
        <input id="todaySign" type="hidden">
        <script src="static/frozenui/lib/zepto.min.js"></script>
        <script src="static/frozenui/js/frozen.js"></script>
        <script>
        $(function(){
      	  
     	   var mydate = new Date();
     	   var str = "" + mydate.getFullYear() + "年";
     	   str += (mydate.getMonth()+1) + "月";
     	   str += mydate.getDate()-1 + "日";
     	   $(".ui-arrowlink").html(str);
     	 });
        function canlenderAndMyChart(){
            /*页面跳转*/
            $('.ui-list li,.ui-tiled li').click(function(){
                if($(this).data('href')){
                location.href= $(this).data('href');
            }
            });
            /*打开日历*/
            $(".topbar").click(function(){
                $(".ui-container").css("display","none");
                $("#rili").css("display","block");
                window.scrollTo(0, document.body.scrollHeight);
            })
            /*echarts图表*/
            var myChart = echarts.init(document.getElementById('main'));

            option = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },             
    textStyle:{
        color:'#666666'
    },
    color:['#ffaf8c','#8caaff'],
    legend: {
        orient: 'horizontal',
        y: 'bottom',
        data:['今日签约','今日下定']
    },
    series: [
        {
            name:'成交数据',
            type:'pie',
            radius: ['45%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[
                {value:$("#todaySign").val(), name:'今日签约'},
                {value:$("#todayPay").val(), name:'今日下定'}
            ]
        }
    ]
};

            myChart.setOption(option);

/*获得数据形成图表*/
/*$.get('data.json').done(function (data) {
    // 填入数据
    myChart.setOption({

        series: [{
            // 根据名字对应到相应的系列
            name:'访问来源',
            data: data.data
        }]
    });
});*/

        }
        </script>
    </body>
</html>