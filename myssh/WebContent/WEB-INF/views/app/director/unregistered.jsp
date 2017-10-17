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
        <title>接访未登记</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/frozenui/css/frozen.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/anchangSuitable.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/unregistered.css">      
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/calendarApp.css">   
        <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/static/frozenui/lib/zepto.min.js"></script>
        <script src="<%=request.getContextPath()%>/static/frozenui/js/frozen.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript">
        $(document).ready(function(){
    		getJieFangNotRegister();
    	});
        function getJieFangNotRegister(){
        	console.log(time)
        	$.ajax({
      			 type : "post",
      			 async : false,
      			 url:"getVisitNotRegisterList",
      			 data:{time:time},
      			 success : function (data) {
      				 data = eval("(" +data+")");
      				getJieFangNotRegisterInfo(data.root);
      			 }
      		 });
        }
        function getJieFangNotRegisterInfo(data){
        	$('.jieFangNotRegisterNum').html(data.length);
        	var s = '';
        	$.each(data,function(v,o){
        		s += '<div class="message" ><div class="leftBox"><span>'+o.arriveTime+'</span></div>';
                s += '<div class="midBox"><img src="<%=request.getContextPath()%>/static/images/empyt_circl.png" alt=""></div>';
                s += '<div class="rightBox"><p>'+o.agentName+'</p></div></div>';
        	});
        if(data.length>0){
  			$('#todayNotRegister').html(s);
			}
        }
        </script>
</head>
<body>
	<header class="ui-header ui-header-positive ui-boeder-b">
        <i class="ui-icon-return" onclick="history.back()"></i>
        <h1>接访未登记</h1>
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

        </section>
        <section class="ui-container" style="display: block;">
            <div class="top">
                <div class="topbar">
                    <!-- <img class="topLeft" src="images/riliPic.png" alt=""> -->
                    <div class="topLeft">
                        <img src="<%=request.getContextPath()%>/static/images/riliPic.png" alt="">
                    </div>
                    <div class="topRight">
                        <h2 class="ui-arrowlink"><span class="ui-panel-subtitle"></span></h2>
                    </div>
                </div>
                <div class="desc">
                    <img src="<%=request.getContextPath()%>/static/images/horn.png" alt="">
                    <!-- <p>今天尚有46组接访尚未登记</p> -->
                    <span class="jieFangNotRegisterNum"></span><span>组接访尚未登记</span>
                </div>
            </div>    

			<div class="bottom" id="todayNotRegister">
			</div>
		</section>
<script src="<%=request.getContextPath()%>/static/js/jqmobi.js"></script>
<script src="<%=request.getContextPath()%>/static/js/calendarOne.js"></script>
<script>
/*关闭日期*/
            function closeThis() {
                $("#rili").css("display","none");
                $(".ui-container").css("display","block");
            }
            var time = null;
            calendarIns = new calendar.calendar( {
            	count: 12,
                selectDateName: '',
                maxDate: new Date(),
                isShowHoliday: true,
                isShowWeek: false
            } );
        $(calendarIns).on('afterSelectDate', function (event, params) {
            
                var curItem = params.curItem,
                    date = params.date,
                    dateName = params.dateName;

                calendarIns.setSelectDate( date );
		
                
                
          if (calendarIns.selectDate) {
            time = calendarIns.selectDate+" 00:00:00";
            $(".ui-arrowlink").html(calendarIns.selectDate);
            $("#rili").css("display","none");
            window.scrollTo(0, 0);
            $(".ui-container").css("display","block");
            getJieFangNotRegister();
          }
        })
</script>
        <script>
        $(function(){
      	  
     	   var mydate = new Date();
     	   var str = "" + mydate.getFullYear() + "年";
     	   str += (mydate.getMonth()+1) + "月";
     	   str += mydate.getDate()-1 + "日";
     	   $(".ui-arrowlink").html(str);
     	 });
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