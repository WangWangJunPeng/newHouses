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
        <title>我的团队</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/frozenui/css/frozen.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/anchangSuitable.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/myTeam.css">      
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/calendarApp.css">   
        <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/static/frozenui/lib/zepto.min.js"></script>
        <script src="<%=request.getContextPath()%>/static/frozenui/js/frozen.js"></script>
        <script type="text/javascript">
        $(document).ready(function(){
    		getJieFangRank();    		    		
    	});
        function getJieFangRank(){
        	$.ajax({
    			 type : "post",
    			 async : false,
    			 url:"to_getVisitRanking",
    			 data:{startTime:starTime,
    				 endTime:endTime},
    			 success : function (data) {
    				 //data = eval("(" +data+")");
    				 //data = $.parseJSON(data);
    				 getAgentJieFangRank(data);
    				 //console.log(data);
    			 }
    		 });
        }
        function getAgentJieFangRank(data){
        	$('#jiefang').html("");
        	$('#rengou').html("");
        	$('#qianyue').html("");
        	$('#chengjiaorate').html("");
        	var s = '';
        	$.each(data,function(v,o){
        		if(v == 0){
        			s += '<a href="to_agent_info_page?userId='+o.userId+'"><div class="msg first"><div class="competition">';
        		}else {
        			s += '<a href="to_agent_info_page?userId='+o.userId+'"><div class="msg"><div class="competition">';
        		}
        		
                if (o.visitNum == 0) {
                	s += '<p>?</p>';
                }else {
                	if (v == 0){
                    	s += '<img src="<%=request.getContextPath()%>/static/images/one.png" alt="">';
                    }else if(v == 1){
                    	s += '<img src="<%=request.getContextPath()%>/static/images/two.png" alt="">';
                    }else if(v == 2) {
                    	s += '<img src="<%=request.getContextPath()%>/static/images/three.png" alt="">';
                    }else {
                    	s += '<p>'+v+'</p>';
                    }
                }
        		
        		s += '</div><div class="portrait">';
        		if (o.photo != null && o.photo != ""){
        			s += '<img src="'+o.photo+'" alt="">';
        		}
        		if (o.photo == null || o.photo == ""){
	        		s += '<img src="<%=request.getContextPath()%>/static/images/morentouxiang.png" alt="">';
        		}
        		s += '</div><p class="name">'+o.userName+'</p>';
            	s += '<div class="rightbox"><p class="num">'+o.visitNum+'</p>';
            	s += '<p class="dec">接访数</p></div></div></a>';
        	});
	        if(data.length>0){
	  			$('#jiefang').html(s);
				 }else {
					 $("#jiefang").html("<tr><td style='width:10%;height:30px;margin:0 auto;'>暂无数据</td></tr>");
	 		}
        }
        function getJieFang(){
        	getJieFangRank();
        }
        function getRenGou(){
        	$.ajax({
   			 type : "post",
   			 async : false,
   			 url:"to_getRengouRanking",
   			 data:{startTime:starTime,
   				 endTime:endTime},
   			 success : function (data) {
   				 //data = eval("(" +data+")");
   				 getAgentRenGouRank(data);
   			 }
   		 });
        }
        function getAgentRenGouRank(data){
        	$('#jiefang').html("");
        	$('#rengou').html("");
        	$('#qianyue').html("");
        	$('#chengjiaorate').html("");
        	var s = '';
        	$.each(data,function(v,o){
        		if(v == 0){
        			s += '<a href="to_agent_info_page?userId='+o.userId+'"><div class="msg first"><div class="competition">';
        		}else {
        			s += '<a href="to_agent_info_page?userId='+o.userId+'"><div class="msg"><div class="competition">';
        		}
        		
                if (o.visitNum == 0) {
                	s += '<p>?</p>';
                }else {
                	if (v == 0){
                    	s += '<img src="<%=request.getContextPath()%>/static/images/one.png" alt="">';
                    }else if(v == 1){
                    	s += '<img src="<%=request.getContextPath()%>/static/images/two.png" alt="">';
                    }else if(v == 2) {
                    	s += '<img src="<%=request.getContextPath()%>/static/images/three.png" alt="">';
                    }else {
                    	s += '<p>'+v+'</p>';
                    }
                }
        		s += '</div><div class="portrait">';
        		if (o.photo != null && o.photo != ""){
        			s += '<img src="'+o.photo+'" alt="">';
        		}
        		if (o.photo == null || o.photo == ""){
	        		s += '<img src="<%=request.getContextPath()%>/static/images/morentouxiang.png" alt="">';
        		}
        		s += '</div><p class="name">'+o.userName+'</p>';
            	s += '<div class="rightbox"><p class="num">'+o.rengouNum+'</p>';
            	s += '<p class="dec">认购数</p></div></div></a>';
        	});
	        if(data.length>0){
	  			$('#rengou').html(s);
				 }else {
					 $("#rengou").html("<tr><td style='width:10%;height:30px;margin:0 auto;'>暂无数据</td></tr>");
	 		}
        }
        function getQianYue(){
        	$.ajax({
      			 type : "post",
      			 async : false,
      			 url:"to_getContractRanking",
      			 data:{startTime:starTime,
      				 endTime:endTime},
      			 success : function (data) {
      				 //data = eval("(" +data+")");
      				 getAgentQianYueRank(data);
      			 }
      		 });
        }
        function getAgentQianYueRank(data){
        	$('#jiefang').html("");
        	$('#rengou').html("");
        	$('#qianyue').html("");
        	$('#chengjiaorate').html("");
        	var s = '';
        	$.each(data,function(v,o){
        		if(v == 0){
        			s += '<a href="to_agent_info_page?userId='+o.userId+'"><div class="msg first"><div class="competition">';
        		}else {
        			s += '<a href="to_agent_info_page?userId='+o.userId+'"><div class="msg"><div class="competition">';
        		}
        		
                if (o.visitNum == 0) {
                	s += '<p>?</p>';
                }else {
                	if (v == 0){
                    	s += '<img src="<%=request.getContextPath()%>/static/images/one.png" alt="">';
                    }else if(v == 1){
                    	s += '<img src="<%=request.getContextPath()%>/static/images/two.png" alt="">';
                    }else if(v == 2) {
                    	s += '<img src="<%=request.getContextPath()%>/static/images/three.png" alt="">';
                    }else {
                    	s += '<p>'+v+'</p>';
                    }
                }
        		s += '</div><div class="portrait">';
        		if (o.photo != null && o.photo != ""){
        			s += '<img src="'+o.photo+'" alt="">';
        		}
        		if (o.photo == null || o.photo == ""){
	        		s += '<img src="<%=request.getContextPath()%>/static/images/morentouxiang.png" alt="">';
        		}
        		s += '</div><p class="name">'+o.userName+'</p>';
            	s += '<div class="rightbox"><p class="num">'+o.contractNum+'</p>';
            	s += '<p class="dec">签约数</p></div></div></a>';
        	});
	        if(data.length>0){
	  			$('#qianyue').html(s);
				 }else {
					 $("#qianyue").html("<tr><td style='width:10%;height:30px;margin:0 auto;'>暂无数据</td></tr>");
	 		}
        }
        function getChengJiaoRate(){
        	$.ajax({
     			 type : "post",
     			 async : false,
     			 url:"to_getSlewRateRanking",
     			 data:{startTime:starTime,
     				 endTime:endTime},
     			 success : function (data) {
     				 //data = eval("(" +data+")");
     				 //getAgentChengJiaoRateRank(data);
     			 }
     		 });
        }
        function getAgentChengJiaoRateRank(data){
        	$('#jiefang').html("");
        	$('#rengou').html("");
        	$('#qianyue').html("");
        	$('#chengjiaorate').html("");
        	var s = '';
        	$.each(data,function(v,o){
        		if(v == 0){
        			s += '<a href="to_agent_info_page?userId='+o.userId+'"><div class="msg first"><div class="competition">';
        		}else {
        			s += '<a href="to_agent_info_page?userId='+o.userId+'"><div class="msg"><div class="competition">';
        		}
        		
                if (o.visitNum == 0) {
                	s += '<p>?</p>';
                }else {
                	if (v == 0){
                    	s += '<img src="<%=request.getContextPath()%>/static/images/one.png" alt="">';
                    }else if(v == 1){
                    	s += '<img src="<%=request.getContextPath()%>/static/images/two.png" alt="">';
                    }else if(v == 2) {
                    	s += '<img src="<%=request.getContextPath()%>/static/images/three.png" alt="">';
                    }else {
                    	s += '<p>'+v+'</p>';
                    }
                }
        		s += '</div><div class="portrait">';
        		if (o.photo != null && o.photo != ""){
        			s += '<img src="'+o.photo+'" alt="">';
        		}
        		if (o.photo == null || o.photo == ""){
	        		s += '<img src="<%=request.getContextPath()%>/static/images/morentouxiang.png" alt="">';
        		}
        		s += '</div><p class="name">'+o.userName+'</p>';
            	s += '<div class="rightbox"><p class="num">'+o.slewRate+'</p>';
            	s += '<p class="dec">成交转化率</p></div></div></a>';
        	});
	        if(data.length>0){
	  			$('#chengjiaorate').html(s);
				 }else {
					 $("#chengjiaorate").html("<tr><td style='width:10%;height:30px;margin:0 auto;'>暂无数据</td></tr>");
	 		}
        }
        </script>
        
</head>
<body>
    <header class="ui-header ui-header-positive ui-boeder-b">
        <i class="ui-icon-return" onclick="history.back()"></i>
        <h1>排行</h1>
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
        	getJieFang();
        }
        if($("#tabList2").hasClass("current")){
        	getRenGou();
        }
        if($("#tabList3").hasClass("current")){
        	getQianYue();
        }
        if($("#tabList4").hasClass("current")){
        	//getChengJiaoRate();
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
                        <li id="tabList1" class="current" >接访</li>
                        <li id="tabList2">认购</li>
                        <li id="tabList3">签约</li>
                        <!-- <li id="tabList4">成交转化率</li> -->
                    </ul>
                    <ul class="ui-tab-content" style="width:300%">
                        <li id="jiefang">
                        
                        </li>
                        <li id="rengou"></li>
                        <li id="qianyue"></li>
                        <!-- <li id="chengjiaorate"></li> -->
                    </ul>
                </div>
            </div>
        </section>
        
        <script>
        (function (){
            var tab = new fz.Scroll('.ui-tab', {
                role: 'tab',
                autoplay: false,
            });
            /* 滑动开始前 */
            tab.on('beforeScrollStart', function(fromIndex, toIndex) {
                // from 为当前页，to 为下一页
            })
            /* 滑动结束 */
             tab.on('scrollEnd', function(curPage) {
                 // curPage 当前页
                 if(curPage==0){
                	 getJieFang();
                 }
                 if(curPage==1){
                	 getRenGou()
                 }
                 if(curPage==2){
                	 getQianYue()
                 }
                 /* if(curPage==3){
                	 getChengJiaoRate()
                 } */
             });           
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
        $(function(){
      	  
     	   var mydate = new Date();
     	   var str = "" + mydate.getFullYear() + "年";
     	   str += (mydate.getMonth()+1) + "月";
     	   str += mydate.getDate()-1 + "日";
     	   $(".ui-arrowlink").html(str);
     	 });
        </script>
</body>
</html>