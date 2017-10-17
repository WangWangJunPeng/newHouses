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
        <title>业绩报表</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/frozenui/css/frozen.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/anchangSuitable.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/report.css">      
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/calendarApp.css">   
        <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/static/frozenui/lib/zepto.min.js"></script>
        <script src="<%=request.getContextPath()%>/static/frozenui/js/frozen.js"></script>
        <script type="text/javascript">
        $(document).ready(function(){
    		getJieFangReport();
    	});
        function getJieFangReport(){
        	$.ajax({
   			 type : "post",
   			 async : false,
   			 url:"to_getVisitStatement",
   			 data:{startTime:starTime,
   				 endTime:endTime},
   			 success : function (data) {
   				 data = eval("(" +data+")");
   				 //console.log(data);
   				 getAgentJieFangRank(data.data);
   			 }
   		 });
        }
        function getAgentJieFangRank(data){
        	$('#waichang').html("");
        	$('#chengjiao').html("");
        	$('#jiefang').html("");
        	 var length = data.length;
			 var array1 = [];
			 var array2 = [];
			 var s = "";
			 for(var i = 1;i<=length;i++){
				 var aa = "data_"+i
				 var bb = "name_"+i
				 
				 s += '<li class="ui-border-t" ><div class="ui-list-info">';
				 s += '<h4>' + data[bb] + '</h4>';
				 s += '</div><div class="num" >' + data[aa] + '</div></li>';
			 }
			 $('#jiefang').html(s);
        }
        function getjiefangInfo(){
        	getJieFangReport();
        }
        function getchengjiaoInfo(){
        	$.ajax({
      			 type : "post",
      			 async : false,
      			 url:"to_getDealStatement",
      			 data:{startTime:starTime,
      				 endTime:endTime},
      			 success : function (data) {
      				 data = eval("(" +data+")");
      				 getAgentchengjiaoRank(data.data);
      			 }
      		 });
        }
        function getAgentchengjiaoRank(data){
        	$('#waichang').html("");
        	$('#chengjiao').html("");
        	$('#jiefang').html("");
       	 var length = data.length;
			 var array1 = [];
			 var array2 = [];
			 var s = "";
			 for(var i = 1;i<=length;i++){
				 var aa = "data_"+i
				 var bb = "name_"+i
				 
				 s += '<li class="ui-border-t" ><div class="ui-list-info">';
				 s += '<h4>' + data[bb] + '</h4>';
				 s += '</div><div class="num" >' + data[aa] + '</div></li>';
			 }
			 $('#chengjiao').html(s);
        }
        function getwaichangInfo(){
        	$.ajax({
      			 type : "post",
      			 async : false,
      			 url:"to_getOutFieldStatement",
      			 data:{startTime:starTime,
      				 endTime:endTime},
      			 success : function (data) {
      				 data = eval("(" +data+")");
      				 getAgentwaichangRank(data.data);
      			 }
      		 });
        }
        function getAgentwaichangRank(data){
        	$('#waichang').html("");
        	$('#chengjiao').html("");
        	$('#jiefang').html("");
       	 var length = data.length;
			 var array1 = [];
			 var array2 = [];
			 var s = "";
			 for(var i = 1;i<=length;i++){
				 var aa = "data_"+i
				 var bb = "name_"+i
				 
				 s += '<li class="ui-border-t" ><div class="ui-list-info">';
				 s += '<h4>' + data[bb] + '</h4>';
				 s += '</div><div class="num" >' + data[aa] + '</div></li>';
			 }
			 $('#waichang').html(s);
        }
        
        </script>
</head>
<body>
	<header class="ui-header ui-header-positive ui-boeder-b">
        <i class="ui-icon-return" onclick="history.back()"></i>
        <h1>业绩报表</h1>
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
      var date = params.date
      
      // var dateName = params.dateName

      calendarIns.setSelectDate(date, params.startEnd)
      if (calendarIns.startDate && calendarIns.endDate) {
        $(".ui-arrowlink").html(calendarIns.startDate+' 至 '+calendarIns.endDate); 
        starTime = calendarIns.startDate+" 00:00:00";
        endTime = calendarIns.endDate+" 23:59:59";
        
        $("#rili").css("display","none");
        window.scrollTo(0, 0);
        $(".ui-container").css("display","block");
        if($("#tabList1").hasClass("current")){
        	getjiefangInfo();
        }
        if($("#tabList2").hasClass("current")){
        	getchengjiaoInfo();
        }
        if($("#tabList3").hasClass("current")){
        	getwaichangInfo();
        }
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
                    <!-- <img class="topLeft" src="images/riliPic.png" alt=""> -->
                    <div class="topLeft">
                        <img src="<%=request.getContextPath()%>/static/images/riliPic.png" alt="">
                    </div>
                    <div class="topRight">
                        <h2 id="mainTime" class="ui-arrowlink"><span class="ui-panel-subtitle"></span></h2>
                    </div>
                </div>
            </div>    

			<div class="bottom">
                <!--信息展示-->
				<div class="ui-tab" id="tab1">
    				<ul class="ui-tab-nav ui-border-b">
        				<li class="current" id="tabList1">
        					<img src="<%=request.getContextPath()%>/static/images/jiefang.png" alt="" > 
        					<span>接访</span>
        				</li>
        				<li id="tabList2">
							<img src="<%=request.getContextPath()%>/static/images/deal.png" alt="" > 
        					<span>成交</span>
        				</li>
        				<li id="tabList3">
        					<img src="<%=request.getContextPath()%>/static/images/outfield.png" alt="" > 
        					<span>外场</span>
        				</li>
    				</ul>
    				<ul class="ui-tab-content" style="width:400%">
        				<li>
							<ul class="ui-list ui-list-text ui-list-cover ui-border-tb" id="jiefang"></ul>
        				</li>
        				<li>
							<ul class="ui-list ui-list-text ui-list-cover ui-border-tb" id="chengjiao"></ul>
        				</li>
        				<li>
							<ul class="ui-list ui-list-text ui-list-cover ui-border-tb" id="waichang"></ul>
        				</li>
   					 </ul>
				</div>
			</div>
		</section>
		<script >
		$(function(){
      	  
     	   var mydate = new Date();
     	   var str = "" + mydate.getFullYear() + "年";
     	   str += (mydate.getMonth()+1) + "月";
     	   str += mydate.getDate()-1 + "日";
     	   $(".ui-arrowlink").html(str);
     	 });
        (function (){
            var tab = new fz.Scroll('.ui-tab', {
                role: 'tab',
                autoplay: false,
            });
            /* 滑动结束 */
             tab.on('scrollEnd', function(curPage) {
                 // curPage 当前页
            	 if(curPage==0){
            		 getjiefangInfo();
                 }
                 if(curPage==1){
                	 getchengjiaoInfo();
                 }
                 if(curPage==2){
                	 getwaichangInfo();
                 }
             });
        })();
        </script>
        
        
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
                window.scrollTo(0, document.body.scrollHeight);
            })

        })();
        </script>
        
</body>
</html>