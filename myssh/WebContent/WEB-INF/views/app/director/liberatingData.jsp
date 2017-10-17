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
        <title>接访数据</title>
        <link rel="stylesheet" href="static/frozenui/css/frozen.css">
        <link rel="stylesheet" type="text/css" href="static/css/calendarApp.css">
        <link rel="stylesheet" type="text/css" href="static/css/anchangSuitable.css">
        <link rel="stylesheet" type="text/css" href="static/css/liberatingData.css">
         <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="static/js/iconfont.js"></script>
        <script type="text/javascript" src="static/js/echarts.min.js"></script>
        <script type="text/javascript" src="static/js/liberat.js"></script>
    </head>
    <body ontouchstart>
         <header class="ui-header ui-header-positive ui-border-b">
            <i class="ui-icon-return" onclick="history.back()"></i><h1>接访数据</h1>
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
<input id="valid" type="hidden">
<input id="valid" type="hidden">
<input id="unValid" type="hidden">
<script src="static/js/zepto.js"></script>
<script src="static/js/jqmobi.js"></script>
<script src="static/js/calendarApp.js"></script>
<script type="text/javascript">
/*关闭日期*/

            function closeThis() {
                $("#rili").css("display","none");
                $(".ui-container").css("display","block");
            }
    var calendarIns = this.calendar
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
        console.log('选完了')
        console.log('start: ', calendarIns.startDate)
        console.log('end: ', calendarIns.endDate)
        starTime = calendarIns.startDate+" 00:00:00";
        endTime = calendarIns.endDate+" 23:59:59";
        $(".ui-arrowlink").html(calendarIns.startDate+' 至 '+calendarIns.endDate);
        $("#rili").css("display","none");
        window.scrollTo(0, 0);
        $(".ui-container").css("display","block");
        getVisitData();
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
                        <h2 class="ui-arrowlink">请选择时间<span class="ui-panel-subtitle"></span></h2>
                    </div>
                </div>                
                <div class="timeDiv">
                    <p class="div1" id="timeLongs">0分钟</p>
                    <p class="div2">平均接访时间</p>
                </div>
            </div>
            <div id="main"></div>
            <div class="maintip">接访总数：<span id="visitTotalCount">0</span></div>
            <div class="bottom">
                <div class="titp">
                    <p>数据分析</p>
                </div>
                <div class="list">
                    <div class="listbar">
                        <img src="static/images/quan1.png" alt="">
                        <div class="rightlist">
                            <span class="ziti1">有效接访率</span>
                            <span class="ziti2 right" id="vaildRate">0%</span>
                        </div>
                    </div>
                </div>
                <div class="list">
                    <div class="listbar">
                        <img src="static/images/quan2.png" alt="">
                        <div class="rightlist">
                            <span class="ziti1">报备到访率</span>
                            <span class="ziti3 right" id="gueidVisitRate"></span>
                        </div>
                    </div>
                </div>
                <div class="list">
                    <div class="listbar">
                        <img src="static/images/quan3.png" alt="">
                        <div class="rightlist">
                            <span class="ziti1">老客户占比</span>
                            <span class="ziti4 right" id="oldCusRate"></span>
                        </div>
                    </div>
                </div>
                <div class="list">
                    <div class="listbar">
                        <img src="static/images/quan4.png" alt="">
                        <div class="rightlist">
                            <span class="ziti1">总替接率</span>
                            <span class="ziti5 right" id="replaceRate"></span>
                        </div>
                    </div>
                </div>
                <div class="list">
                    <div class="listbar">
                        <img src="static/images/quan5.png" alt="">
                        <div class="rightlist">
                            <span class="ziti1">平台导客占比</span>
                            <span class="ziti6 right" id="systemRate">0%</span>
                        </div>
                    </div>
                </div>
            </div>
        </section>
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
        data:['有效访问','无效来访']
    },
    series: [
        {
            name:'接访',
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
                {value:$("#valid").val(), name:'有效访问'},
                {value:$("#unValid").val(), name:'无效来访'}
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