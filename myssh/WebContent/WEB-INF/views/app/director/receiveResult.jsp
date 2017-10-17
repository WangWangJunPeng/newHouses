<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta content="yes" name="apple-mobile-web-app-capable">
        <meta content="yes" name="apple-touch-fullscreen">
        <meta content="telephone=no,email=no" name="format-detection">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
        <title>${data.buttonText}</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/mui.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/frozenui/css/frozen.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/calendarApp.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/anchangSuitable.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/liberatingData.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/receiveResult.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/iconfont.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/echarts.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
   		<script type="text/javascript">
   		$(document).ready(function(){
    		getGuanLian();
    	});
    	function getGuanLian(){
    		$.ajax({
     			 type : "post",
     			 async : false,
     			 dataType : "json",
     			 url:"to_getSeeRelationFenXiInfo",
     			 data : {chartDataId : $('#chartDataId').val()},
     			 success : function (data) {
     				 getGuanLianList(data);
     			 }
     		 });
    	}
    	function getGuanLianList(data){
    		var s = '';
    		$.each(data,function(v,o){
    			s += '<li><a href="to_goToSeeCustomerReceptionPage?chartDataId='+o.chartDataId+'" title="">';
    			if (o.iconUrl != null && o.iconUrl != ""){
                	s += '<img src="'+o.iconUrl+'" alt="">';
                }else {
                    s += '<img src="<%=request.getContextPath()%>/static/images/yeji.png" alt="">';
                }
    			s += '<p>'+o.buttonText+'</p></a></li>';
    		});
    		if(data.length>0){
      			$('#managerShouCangInfo').html(s);
  			 }
    	}
    	function goToBack(){
    		window.location.href="to_getManagerAnalysePage";
    	}
   		</script>
   
    </head>
    <body ontouchstart>
         <header class="ui-header ui-header-positive ui-border-b">
            <i class="ui-icon-return" onclick="goToBack()"></i><h1 style="height:45px;line-height:45px;">${data.buttonText}</h1>
        </header>
        <section id="rili" style="display: none;z-index: 2000;">
        <header class="ui-header ui-header-positive ui-border-b">
            <i class="ui-icon-return" onclick="closeThis()"></i><h1 style="height:45px;line-height:45px;">选择日期</h1>
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
<script src="<%=request.getContextPath()%>/static/js/zepto.js"></script>
<script src="<%=request.getContextPath()%>/static/js/jqmobi.js"></script>
<script src="<%=request.getContextPath()%>/static/js/calendarApp.js"></script>
<script type="text/javascript">
/*关闭日期*/
 function closeThis() {
                 $("#rili").css("display","none");
                 $(".ui-container").css("display","block");
             }           
</script>
        </section>
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
            <div id="main1"></div>
            <div id="main2"></div>
            <!-- <div class="maintip" style="top:1.2rem;"><span id="name_1"></span>：<span id="data_1"></span></div> -->
            <div class="bottom">
                <div class="titp">
                    <p>数据说明</p>
                </div>
                <div class="shuoming" id="descText" ></div>
                <div class="titp1">
                    <p>关联数据</p>
                </div>
                <div class="list2">
                    <ul id="managerShouCangInfo">
                        
                        <!-- <li>
                            <a href="###" title="">
                                <img src="images/yeji.png" alt="">
                                <p>观察新老客户通道</p>
                            </a>                           
                        </li>
                        <li>
                            <a href="###" title="">
                                <img src="images/yeji.png" alt="">
                                <p>观察替接</p>
                            </a>                           
                        </li> -->
                    </ul>
                </div>
                <div class="tips"></div>
            </div>
          <input type="hidden" id="chartDataId" value="${data.chartDataId }" name="chartDataId">
          <input type="hidden" id="backgroundMethod" value="${data.backgroundMethod }" name="backgroundMethod">
          <input type="hidden" id="graph_1" value="${data.chartName }" name="chartName">
        </section>
        <script src="<%=request.getContextPath()%>/static/frozenui/lib/zepto.min.js"></script>
        <script src="<%=request.getContextPath()%>/static/frozenui/js/frozen.js"></script>
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
            	  starTime = calendarIns.startDate+" 00:00:00";
                  endTime = calendarIns.endDate+" 23:59:59";
            	  
                $(".ui-arrowlink").html(calendarIns.startDate+' 至 '+calendarIns.endDate);
                $("#rili").css("display","none");
                window.scrollTo(0, 0);
                $(".ui-container").css("display","block");
                getFenXiInfo();
              }
              if (calendarIns.startDate==calendarIns.endDate) {
                console.log('选择了'+calendarIns.endDate)
              }
            })
                      

            getFenXiInfo();
            function getFenXiInfo(){
        		$.ajax({
        			 type : "post",
        			 async : false,
        			 url:$('#backgroundMethod').val(),
        			 data:{startTime:starTime,
        				 endTime:endTime,
        				 chartDataId:$('#chartDataId').val()},
        			 success : function (data) {
        				 $("#descText").html(data.descText);
        				 var length = data.length;
        				 var array1 = [];
        				 var array2 = [];
        				 var jsonObj = "[";
        				for(var i = 1;i<=length;i++){
        					var aa = "data_"+i
        					var bb = "name_"+i
        					array1.push(data[aa]);
        					array2.push(data[bb]);
        				 	if(i == length){
        				 		jsonObj += "{'value':" + data[aa] + ",'name':'" + data[bb] + "'}";
        				 	}else{
        				 		jsonObj += "{'value':" + data[aa] + ",'name':'" + data[bb] + "'},";
        				 	}
        				}
        				jsonObj += ']';
        				 var json = eval('(' + jsonObj + ')');
						if(data.graph_1=="饼图"){	
							 $("#main").css("display","block");
							$("#main1").css("display","none");
							$("#main2").css("display","none");
							tubiao1(array2,json);
						}else if(data.graph_1=="码表图"){
							$("#main").css("display","none");
							$("#main1").css("display","none");
							$("#main2").css("display","block");
							tubiao2(array2,json);
						}else if(data.graph_1=="柱图"){
							$("#main").css("display","none");
							$("#main1").css("display","none");
							$("#main2").css("display","block");
							tubiao3(array1,array2);
						}
						
        			 }
        		 });
        	}

        /*echarts图表1*/
            function tubiao1(array2,json){
            	var myChart1 = echarts.init(document.getElementById('main'));

                option1 = {
        tooltip: {
            trigger: 'item',
            confine:true,
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },             
        textStyle:{
            color:'#666666'
        },
        color:['#ffaf8c','#8caaff','#73d6ff','#ff9f98','#45e1c5'],
        legend: {
            orient: 'horizontal',
            bottom: 0,
            data:array2
        },
        
        series: [
            {
            	name:' ',
            	center : ['50%', '40%'],
                type:'pie',
                radius: ['40%', '60%'],
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
                data:json
            }
        ]
    };
    myChart1.setOption(option1, true);  
    }
            
        
        /*echarts图表2*/
        function tubiao2(array2,json) {
        	var myChart2 = echarts.init(document.getElementById('main2'));
            option2 = {
   tooltip : {
	   confine:true,
       formatter: "{a} <br/>{b} : {c}%"
   },
   color:['#ffaf8c','#8caaff','#73d6ff','#ff9f98','#45e1c5'],
   textStyle:{
       color:'#666666'
   },
   series: [
       {
    	   name:' ',
           type: 'gauge',
           detail: {formatter:'{value}%'},
           data: json
       }
   ]
};
myChart2.setOption(option2, true);

   }
        /*echarts图表3*/   
        
        function tubiao3(array1,array2){

        	var myChart3 = echarts.init(document.getElementById('main2'));


            option3 = {
                color: ['#3398DB'],
                tooltip : {
                	confine:true,
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '18%',
                    containLabel: true
                },
                xAxis : [
                    {
                        type : 'category',
                        data : array2,
                        axisLabel:{
                        	interval:0,
                        	rotate:30
                        },
                        axisTick: {
                            alignWithLabel: true
                        }
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                    	name:' ',
                        type:'bar',
                        barWidth: '60%',
                        data:array1
                    }
                ]
            };
            myChart3.setOption(option3, true);
        }
        
			
        })();
        </script>
    </body>
</html>