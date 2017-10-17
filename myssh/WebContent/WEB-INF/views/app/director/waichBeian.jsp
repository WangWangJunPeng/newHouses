<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="en">
<html>
    <head>
        <meta charset="utf-8">
        <meta content="yes" name="apple-mobile-web-app-capable">
        <meta content="yes" name="apple-touch-fullscreen">
        <meta content="telephone=no,email=no" name="format-detection">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
        <title>外场备案</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/frozenui/css/frozen.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/calendarApp.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/anchangSuitable.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/liberatingData.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/gwStatusDeatils.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/waichBeian.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/iconfont.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/echarts.min.js"></script>
    	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
    </head>
    <body ontouchstart>
         <header class="ui-header ui-header-positive ui-border-b">
            <i class="ui-icon-return" onclick="history.back()"></i><h1>外场备案</h1>
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

        </section>
        <script type="text/javascript">
/*关闭日期*/
 function closeThis() {
                 $("#rili").css("display","none");
                 $(".ui-container").css("display","block");
             }           
</script>
        <section class="ui-container" style="display: block;">
            <div class="top">
                <div class="topbar">
                    <div class="topLeft">
                        <img src="<%=request.getContextPath()%>/static/images/riliPic.png" alt="">
                    </div>
                    <div class="topRight">
                        <h2 class="ui-arrowlink"><span class="ui-panel-subtitle"></span></h2>
                    </div>
                </div>                
            </div>
            <div id="main"></div>
            <div class="maintip">
                <p>报备到访率:</p>
                <p id="percent"></p>
                <input type="hidden" id="haveVisit">
                <input type="hidden" id="notVisit">
            </div>
            <div class="bottom">
                <div class="titp" id="beianInfo">
                    <p>详情</p>
                </div>               
            </div>    
        </section>
        <script src="<%=request.getContextPath()%>/static/frozenui/lib/zepto.min.js"></script>
        <script src="<%=request.getContextPath()%>/static/frozenui/js/frozen.js"></script>
                <script src="<%=request.getContextPath()%>/static/js/zepto.js"></script>
<script src="<%=request.getContextPath()%>/static/js/jqmobi.js"></script>
<script src="<%=request.getContextPath()%>/static/js/calendarApp.js"></script>
        <script>
        $(function(){
      	  
     	   var mydate = new Date();
     	   var str = "" + mydate.getFullYear() + "年";
     	   str += (mydate.getMonth()+1) + "月";
     	   str += mydate.getDate()-1 + "日";
     	   $(".ui-arrowlink").html(str);
     	 });
        (function (){    
        	$(document).ready(function(){
        		getWaiChangBeiAn();
        		getBeiAnDaoFang();
        	});
        	function getBeiAnDaoFang(){
        		$.ajax({
        			 type : "post",
        			 async : false,
        			 url:"getGuideRecordsByToday",
        			 data:{startTime:starTime,
       				 endTime:endTime},
        			 success : function (data) {
        				 data = eval("(" +data+")");
        				getBeiAnDaoFangInfo(data.data);
        			 }
        		 });
        	}
        	function getBeiAnDaoFangInfo(data){
        		$('#percent').html(data.percent);
        		$('#haveVisit').val(data.haveVisit);
        		$('#notVisit').val(data.notVisit);
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
                    {value:$('#haveVisit').val(), name:'有效访问'},
                    {value:$('#notVisit').val(), name:'无效来访'}
                ]
            }
        ]
    };
                myChart.setOption(option);
        	}
        	function getWaiChangBeiAn(){
        		$.ajax({
         			 type : "post",
         			 async : false,
         			 url:"getGuideRecordsDetails",
         			 data:{startTime:starTime,
        				 endTime:endTime},
         			 success : function (data) {
         				 data = eval("(" +data+")");
         				getWaiChangBeiAnInfo(data.root);
         			 }
         		 });
        	}
        	function getWaiChangBeiAnInfo(data){
        		$('#beianInfo').siblings().remove();
        		var s = '';
        		$.each(data,function(v,o){
        			s += '<div class="lists"><div class="dielog" style="display: none;"><div class="middel">';
        			s += '<i class="ui-icon-close-page"></i><p>';
        			if (o.housePic != null && o.housePic != ""){
        				s += '<img src="'+o.housePic+'" alt="">';
        			}else {
        				s += '<img src="<%=request.getContextPath()%>/static/images/dizhiPic.png" alt="">';
        			}
        			s += '<span>'+o.shopName+'</span>';
        			s += '</p><p>';
       				s += '<img src="<%=request.getContextPath()%>/static/images/phonePicc.png" alt="">';
       				s += '店长：<span>'+o.phone+'</span></p></div></div>';
       				s += '<table><tbody><tr><td rowspan="2">';
       				if (o.housePic != null && o.housePic != ""){
        				s += '<img src="'+o.housePic+'" alt="">';
        			}else {
        				s += '<img src="<%=request.getContextPath()%>/static/images/fangziPic.png" alt="">';
        			}
       				s += '<p class="nameCss">'+o.shopName+'</p>';
       				s += '<p class="xinxiCss">查看详情</p></td>';
       				s += '<td><p class="line1">地点 </p><p class="line2">'+o.shiqu+'</p></td>';
       				s += '<td><p class="line1">备案客户数</p><p class="line2">'+o.grAllNum+'</p></td></tr>';
       				s += '<tr><td><p class="line1">备案已到访</p><p class="line2">'+o.haveVisitNum+'</p></td>';
       				s += '<td><p class="line1">备案未到访</p><p class="line2">'+o.notVisitNum+'</p></td></tr></tbody></table></div>';
        		});
        		if(data.length>0){
    	  			$('#beianInfo').after(s);
    			}else {
    				$("#beianInfo").after("<td style='width:10%;height:30px;margin:0 auto;text-align:center'>暂无数据</td>");
    	 		}
        	}
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
            });
            /*点击详情*/
            $(".bottom").on("click",".xinxiCss",function(){
            	$(this).parents('table').addClass('model').siblings(".dielog").css('display', 'block');
            })
            /*关闭详情*/
            $(".bottom").on("click",".dielog .ui-icon-close-page",function(){
            	$(this).parents(".dielog").css('display', 'none').siblings("table").removeClass('model');
            })
            /*echarts图表1*/
            

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
			
			getWaiChangBeiAn();
			
			}
			if (calendarIns.startDate==calendarIns.endDate) {
			console.log('选择了'+calendarIns.endDate)
			}      
			})


        })();
        </script>


    </body>
</html>