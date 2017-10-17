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
        <title>顾问状态详情</title>
        <link rel="stylesheet" href="static/frozenui/css/frozen.css">
        <link rel="stylesheet" type="text/css" href="static/css/calendarApp.css">
        <link rel="stylesheet" type="text/css" href="static/css/anchangSuitable.css">
        <link rel="stylesheet" type="text/css" href="static/css/liberatingData.css">
        <link rel="stylesheet" type="text/css" href="static/css/gwStatusDeatils.css">
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="static/js/iconfont.js"></script>
        <script type="text/javascript" src="static/js/echarts.min.js"></script>
        <script type="text/javascript" src="static/js/gwStatusDeatils.js"></script>
    </head>
    <body ontouchstart>
         <header class="ui-header ui-header-positive ui-border-b">
            <i class="ui-icon-return" onclick="history.back()"></i><h1>顾问状态详情</h1>
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
<script src="static/js/calendarApp.js"></script>
<script>
/*关闭日期*/
     
            function closeThis() {
                $("#rili").css("display","none");
                $(".ui-container").css("display","block");
            }
    var calendarIns = this.calendarIns;
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
      var date = params.date;
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
        getGwStstusDeatilsData();
      }
      if (calendarIns.startDate==calendarIns.endDate) {
        console.log('选择了'+calendarIns.endDate)
      }
    })
</script>
        </section>
        <input id="userId" type="hidden" value="${userId}">
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
            <div class="ren">
                <img src="" alt="" id="agentPhoto">
                <p class="nameCss" ><span id="agentName"></span><span class="xinxiCss" id="agentStutas">dadf</span></p>
            </div>
            <div class="lnged">
                <div class="mid">
                    <div class="left">总接访：<span id="totalVisit">0</span></div>
                    <div class="right">有效接访率：<span id="vaildVisitRate"><img id="validVisitImg" src="" alt=""></span></div>
                </div>
                <div id="charts"></div>
            </div>
            <div class="whiteD"></div>
            <div class="lnged1">
                <div class="mid">
                    <div class="left">总储客数：<span id="totalSave">0</span></div>
                    <div class="right">客户回头率：<span id="cusComeBack">0</span><img id="comeBackImg" src="" alt=""></div>
                </div>
                <div class="midd">
                    <div class="line11">
                        <div class="leftbox" id="leftbox1"></div>
                        <div class="rightbox">
                            <p>新增客户数: <span id="oldCusCount"></span></p>
                        </div>
                    </div>
                    <div class="line11">
                        <div class="leftbox" id="leftbox2"></div>
                        <div class="rightbox">
                            <p>新增二次接访: <span id="newAdd"></span></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="whiteD"></div>
            <div class="lnged1">
                <div class="mid">
                    <div class="left">认购到款金额：<span id="enterBuyGetMoney">0.00</span></div>
                    <div class="right">下定签约率：<span id="paySignRate">0%</span><img id="cPaySignRate" src="" alt=""></div>
                </div>
                <div id="main"></div> 
            </div>
            <div class="whiteD"></div>          
        </section>
       	<input id="unValidVisit" type="hidden">
       	<input id="oldCusVisit" type="hidden">
       	<input id="newCusVisit" type="hidden">       	
       	<input id="signed" type="hidden">
       	<input id="payMoney" type="hidden">
       	<input id="waitSign" type="hidden">
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
            /*echarts图表1*/
            var myChart1 = echarts.init(document.getElementById('charts'));
            option = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },             
    textStyle:{
        color:'#666666'
    },
    color:['#ff9f98','#73d6ff','#ffaf8c'],
    series: [
        {
            name:'接访',
            type:'pie',
            radius: ['45%', '70%'],
            avoidLabelOverlap: false,            
            data:[
                {value:$("#oldCusVisit").val(), name:'老客户接访'},
                {value:$("#newCusVisit").val(), name:'新客户接访'},
                {value:$("#unValidVisit").val(), name:'无效来访'}
            ]
        }
    ]
};
            myChart1.setOption(option);

            /*echarts图表2*/
            var myChart2 = echarts.init(document.getElementById('main'));

            option = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },             
    textStyle:{
        color:'#666666'
    },
    color:['#ffaf8c','#97b8fb','#45e1c5'],
    series: [
        {
            name:'成交',
            type:'pie',
            radius: ['45%', '70%'],
            avoidLabelOverlap: false,            
            data:[
                {value:$("#payMoney").val(), name:'下定'},
                {value:$("#waitSign").val(), name:'待签约'},
                {value:$("#signed").val(), name:'已签约'}
            ]
        }
    ]
};

            myChart2.setOption(option);

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