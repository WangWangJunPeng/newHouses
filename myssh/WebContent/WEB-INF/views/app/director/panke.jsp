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
        <title>盘客</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/frozenui/css/frozen.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/anchangSuitable.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/panke.css">      
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/calendarApp.css">   
        <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/static/frozenui/lib/zepto.min.js"></script>
        <script src="<%=request.getContextPath()%>/static/frozenui/js/frozen.js"></script>
</head>
<body>
	<header class="ui-header ui-header-positive ui-boeder-b">
        <i class="ui-icon-return" onclick="history.back()"></i>
        <h1>盘客</h1>
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
<script src="<%=request.getContextPath()%>/static/js/calendarOne.js"></script>
<script>
/*关闭日期*/
           
          
            function closeThis() {
                $("#rili").css("display","none");
                $(".ui-container").css("display","block");
            }
            calendarIns = new calendar.calendar( {
            	count: 12,
                selectDateName: '选中',
                selectDate: '',
                maxDate: new Date(),
                date: new Date( +new Date() - 86400000*30*11), 
                isShowHoliday: true,
                isShowWeek: false
            } );
            var time = null;
        $(calendarIns).on('afterSelectDate', function (event, params) {
            
                var curItem = params.curItem,
                    date = params.date,
                    dateName = params.dateName;

                calendarIns.setSelectDate( date );

          if (calendarIns.selectDate) {
        	time=calendarIns.selectDate+" 00:00:00";
            console.log('选完了:',calendarIns.selectDate)
            $(".ui-arrowlink").html(calendarIns.selectDate);
            $("#rili").css("display","none");
            window.scrollTo(0, 0);
            $(".ui-container").css("display","block");
            showData();
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
                        <h2 class="ui-arrowlink"><span class="ui-panel-subtitle"></span></h2>
                    </div>
                </div>
            </div>    

			<div class="bottom">
                <!--信息展示-->
				<div class="ui-tab" id="tab1">
                    <ul class="ui-tab-nav ui-border-b">
                        <li class="current">未完成</li>
                        <li>已完成</li>
                    </ul>
                    <ul class="ui-tab-content" style="width:300%">
                        <li id="unfinishedli">
                        <!--  
                            <div class="main">
                                <div class="leftbox">
                                    <img src="<%=request.getContextPath()%>/static/images/squar.png" alt="">
                                    <p class="name">李四</p>
                                    <p class="num">待点评客户：10</p>
                                </div>
                                <div class="rightbox">
                                    <table border="1">
                                        <tr>
                                            <th>客户姓名</th>
                                            <th>到访时间</th>
                                        </tr>
                                        <tr>
                                            <td>张三</td>
                                            <td>2017/5/1</td>
                                        </tr>
                                        <tr>
                                            <td>张三</td>
                                            <td>2017/5/1</td>
                                        </tr>
                                    </table>
                                </div>
                                <button type="button" class="seeMore" data-href="to_goToReviewedCusPage">查看更多</button>
                            </div>
                            -->
                            
                        </li>
                        <li id="finishedli">
                        <!-- 
                             <div class="main">
                                <div class="leftbox">
                                    <img src="<%=request.getContextPath()%>/static/images/squar.png" alt="">
                                    <p class="name">李四</p>
                                    <p class="num">待点评客户：10</p>
                                </div>
                                <div class="rightbox">
                                    <table border="1">
                                        <tr>
                                            <th>客户姓名</th>
                                            <th>到访时间</th>
                                        </tr>
                                        <tr>
                                            <td>张三</td>
                                            <td>2017/5/1</td>
                                        </tr>
                                        <tr>
                                            <td>张三</td>
                                            <td>2017/5/1</td>
                                        </tr>
                                    </table>
                                </div>
                                <button type="button">查看更多</button>
                            </div>
                             -->
                        </li>
                    </ul>
                    
                    <!--  -->
                    
                </div>
			</div>
		</section>
		
        <script>
        	//var time = '2017-05-24 09:59:10';
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
            showData();
            /* 滑动开始前 */
            tab.on('beforeScrollStart', function(fromIndex, toIndex) {
            	//showData();
            })
            /* 滑动结束 */
             tab.on('scrollEnd', function(curPage) {
             });       
         
            /*打开日历*/
            $(".topbar").click(function(){
                $(".ui-container").css("display","none");
                $("#rili").css("display","block");
                window.scrollTo(0, document.body.scrollHeight);
            })
            
            $("body").on("click",".seeMore",function(){
            	//alert(time)
            	var agentId = $(this).attr('name');
            	var sign = $(this).attr('data-href');
            	if(!time){
            		var vNow = new Date();
            		time = "";
            		time += String(vNow.getFullYear());
            		time += "-";
            		time += String(vNow.getMonth() + 1);
            		time += "-";
            		time += String(vNow.getDate());
            		time += " 00:00:00";
            	}
	            	location.href = 'to_goToReviewedCusPage?agentId='+agentId+'&sign='+sign+'&time='+time;
            })
            	
            	
            
            $("body").on("click",".seeMore2",function(){
            	var agentId = $(this).attr('name');
            	var sign = $(this).attr('data-href');
            		if(!time){
                		var vNow = new Date();
                		time = "";
                		time += String(vNow.getFullYear());
                		time += "-";
                		time += String(vNow.getMonth() + 1);
                		time += "-";
                		time += String(vNow.getDate());
                		time += " 00:00:00";
                	}
	            	location.href = 'to_goToReviewedCusPage?agentId='+agentId+'&sign='+sign+'&time='+time;
            	
            });
            
            

        })();
        
       
        
        
        function showData(){
        	$("#unfinishedli").empty();
        	$("#finishedli").empty();
        	$.ajax({
       			 url: 'to_getReadyCheckedCustomer',
                    type: 'post',
                    async : false,
                    data:{
                    	time:time
                    },
                    success: function (data) {
                    	//data = "("+data+")";
                    	data = eval("(" +data+")");
                    	dara = data.root;
                    	var tr = '';
                    	var tr2 = '';
                    	$.each(data.root,function(v,o){
                		  tr += '<div class="main">'+
                                '<div class="leftbox">';
                                if(o.photo){
                                	tr += '<img src="'+o.photo+'">';
                                }else{
                                	if(o.sex==1){//男
	                                	tr += '<img src="<%=request.getContextPath()%>/static/images/morentouxiang.png">';
                                	}else{
                                		tr += '<img src="<%=request.getContextPath()%>/static/images/morentouxiang2.png">';
                                	}
                                }
                                   tr += '<p class="name">'+o.agentName+'</p>'+
                                    '<p class="num">待点评客户：'+o.notCheckedNum+'</p>'+
                                '</div>'+
                                '<div class="rightbox">'+
                                    /* '<table border="1">'+ */
                                    '<table class="messageList" border="">'+
                                        '<tr>'+
                                            '<th>客户姓名</th>'+
                                            '<th class="leftNone">到访时间</th>'+
                                        '</tr>';
                                        $.each(o.notCheckedList,function(v1,o1){
                                        	if(v1<2){
	                                        	tr += '<tr>'+
		                                            '<td>'+o1.customerName+'</td>'+
		                                            '<td class="leftNone">'+o1.arriveTime+'</td>'+
		                                        '</tr>';
                                        	}
                                        })
                                    tr+='</table>'+
                                '</div>'+
                                '<button type="button" class="seeMore" name="'+o.agentId+'" data-href="2">查看更多</button>'+
                            '</div>';
                		  tr2 += '<div class="main">'+
                                '<div class="leftbox">';
                                if(o.photo){
                                	tr2 += '<img src="'+o.photo+'">';
                                }else{
                                	if(o.sex==1){//男
	                                	tr2 += '<img src="<%=request.getContextPath()%>/static/images/morentouxiang.png">';
                                	}else{
                                		tr2 += '<img src="<%=request.getContextPath()%>/static/images/morentouxiang2.png">';
                                	}
                                }
                                    tr2 += '<p class="name">'+o.agentName+'</p>'+
                                    '<p class="num">待点评客户：'+o.haveCheckedNum+'</p>'+
                                '</div>'+
                                '<div class="rightbox">'+
                                    '<table border="1">'+
                                        '<tr>'+
                                            '<th>客户姓名</th>'+
                                            '<th>到访时间</th>'+
                                        '</tr>';
                                        $.each(o.haveCheckedList,function(v1,o1){
                                        	if(v1<2){
	                                        	tr2 += '<tr>'+
		                                            '<td>'+o1.customerName+'</td>'+
		                                            '<td>'+o1.arriveTime+'</td>'+
		                                        '</tr>';
                                        	}
                                        })
                                    tr2+='</table>'+
                                '</div>'+
                                '<button type="button" class="seeMore2" name="'+o.agentId+'" data-href="1">查看更多</button>'+
                            '</div>';
                    	})
                    	$(tr).appendTo($("#unfinishedli"));
                    	$(tr2).appendTo($("#finishedli"));
                    }
       			});
        }
        </script>
</body>
</html>