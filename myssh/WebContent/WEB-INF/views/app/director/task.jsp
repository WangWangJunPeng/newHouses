<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
        <title>任务</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/frozenui/css/frozen.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/anchangSuitable.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/task.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/calendarApp.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
    		getPankeReport();
    		getJieFangNotRegister();
    	});
        function getPankeReport(){
        	$.ajax({
   			 type : "post",
   			 async : false,
   			 url:"getCheckCustomerByToday",
   			 data:{time:null},
   			 success : function (data) {
   				 data = eval("(" +data+")");
   				 getManagerPanKe(data.data);
   			 }
   		 });
        }
        function getManagerPanKe(data){
        	$('#notCheckedNum').html(data.notCheckedNum);
        	$('#allCustomerNum').html(data.allCustomerNum);
        }
        function getJieFangNotRegister(){
        	$.ajax({
      			 type : "post",
      			 async : false,
      			 url:"getVisitNotRegisterList",
      			 data:{time:null},
      			 success : function (data) {
      				 data = eval("(" +data+")");
      				getJieFangNotRegisterInfo(data.root);
      			 }
      		 });
        }
        function getJieFangNotRegisterInfo(data){
        	$('.jieFangNotRegisterNum').html(data.length);
        }
		</script>
</head>
<body>
    <header class="ui-header ui-header-positive ui-boeder-b">
        <i class="ui-icon-return" onclick="history.back()"></i>
        <h1>任务</h1>
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

</div>
<script src="<%=request.getContextPath()%>/static/js/zepto.js"></script>
<script src="<%=request.getContextPath()%>/static/js/jqmobi.js"></script>
<script src="<%=request.getContextPath()%>/static/js/calendarApp.js"></script>
<script>
/*关闭日期*/
            function closeThis() {
                $("#rili").css("display","none");
                $(".ui-container").css("display","block");
            }
    var calendarIns = this.calendarIns
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
        $(".ui-arrowlink").html(calendarIns.startDate+' 至 '+calendarIns.endDate);
        $("#rili").css("display","none");
        window.scrollTo(0, 0);
        $(".ui-container").css("display","block");
      }
      if (calendarIns.startDate==calendarIns.endDate) {
        console.log('选择了'+calendarIns.endDate)
      }
    })
</script>
        </section>


        <section class="ui-container" style="display: block;">
            <div class="top">
                <%-- <div class="topbar">
                    <!-- <img class="topLeft" src="images/riliPic.png" alt=""> -->
                    <div class="topLeft">
                        <img src="<%=request.getContextPath()%>/static/images/riliPic.png" alt="">
                    </div>
                    <div class="topRight">
                        <h2 class="ui-arrowlink">请选择时间<span class="ui-panel-subtitle"></span></h2>
                    </div>
                </div>     --%> 
            </div>
            <div class="maintip">

                <a class="num" href="to_getVisitNotRegisterList">
                    <img src="<%=request.getContextPath()%>/static/images/cirle.png" alt="">
                    <p class="jieFangNotRegisterNum"></p>
                </a>
                <div class="desc">
                    <img src="<%=request.getContextPath()%>/static/images/horn.png" alt="">
                    <!-- <p>今天尚有</p><p class="jieFangNotRegisterNum"></p><p>组接访尚未登记</p> -->
                    <span>今天尚有</span><span class="jieFangNotRegisterNum"></span><span>组接访尚未登记</span>
                </div>
            </div>
            <div class="bottom">
                <div class="titp">
                    <p>待完成</p>
                </div>
                <ul class="ui-list ui-list-text ui-list-active ui-list-cover ui-border-tb ui-list-link">
                    <li class="ui-border-t panke" data-href="to_goToPanKePage">
                        <div class="ui-list-thumb">
                            <span style="background-image:url(<%=request.getContextPath()%>/static/images/panke.png)"></span>
                        </div>
                        <div class="ui-list-info">
                            <a href="" class="ui-nowrap">盘客</a>
                            <div class="right pankeNum"><span id="notCheckedNum"></span><span>/</span><span id="allCustomerNum"></span></div>
                        </div>
                    </li>
                </ul>
            </div>
        </section>
        <script src="<%=request.getContextPath()%>/static/frozenui/lib/zepto.min.js"></script>
        <script src="<%=request.getContextPath()%>/static/frozenui/js/frozen.js"></script>
        <script>
        (function (){           
            /*页面跳转*/
            $('.ui-list li,.ui-tiled li,.ui-border-t').click(function(){
                if($(this).data('href')){
                location.href= $(this).data('href');
            }
            });
            /*打开日历*/
            $(".topbar").click(function(){
                $(".ui-container").css("display","none");
                $("#rili").css("display","block");
            })
        })();
        </script>
</body>
</html>