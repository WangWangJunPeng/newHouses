<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta content="telephone=no,email=no" name="format-detection">
    <script src="static/js/flexible.js" type="text/javascript"></script>
    <script src="https://code.jquery.com/jquery-3.1.1.js"></script>
    <link href="static/css/mui.min.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/subscriptionApplication.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/calendarApp.css"/>
    <link rel="stylesheet" type="text/css" href="static/css/agentIndexApp.css"/>
    <link rel="stylesheet" href="static/css/suitable.css" />
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/additional-methods.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/js/validateidCard.js" type="text/javascript"></script>
    
    
    <script src="<%=request.getContextPath()%>/static/js//calendarOne.js"></script>
    <script type="text/javascript">
	    $(document).ready(function(){
			getAllMytask();
			getDateNow();
			getFooterTop();
			confirm();
		});
	    
	    function confirm(){
	    	$(".btn-confirm").click(function(){
	    		var phpneNub = $(".phoneinp").val();
	    		var visitNo = $(this).attr("data-value");
	    		console.log(visitNo);
	    		if($(".phoneinp").val().length=="11"){
	    			$("#errMsg").text("");
	    			window.location.href="newCustomerEdit?phone="+phpneNub+"&visitNo="+visitNo+"&index="+1;
	    		}else{
	    			$("#errMsg").text("请输入正确的手机号");
	    			return false;
	    		}
	    	})
	    }
	    function alertbox(){
	    	$(".btn-add").click(function(){
	    		//console.log($(this).attr("data-value"))
	    		$(".alertbox").css("display","block");
	    		$(".popup-backdrop").css("display","block");
	    		$(".btn-confirm").attr("data-value",$(this).attr("data-value"));
	    	})
	    	
	    	$(".btn-addTwo").click(function(){
	    		var phone = $(this).attr("data-phone");
	    		var visitNo = $(this).attr("data-value");
	    		window.location.href="findCustomersInformation_phone?phone="+phone+"&visitNo="+visitNo+"&index="+1;
	    	})
	    	btnclose();
	    	
	    	
	    }
	    function btnclose(){
	    	$(".btn-close").click(function(){
	    		$(".alertbox").css("display","none");
	    		$(".popup-backdrop").css("display","none");
	    		$(".phoneinp").val("");
	    		$("#phone-error").remove();
	    	})
	    }
	    function getFooterTop(){
    		$("#btn-search").focus(function(){
    			$("#footerbar").css("top",$(document.body).height()-$("#footerbar").height());
    		})
    	}
	    function getDateNow(){
	    	var d = $("#selectData").val();
	    	if(d==""){
	    		var data = new Date();
		    	date1 = data.getDate();
		    	$(".dateTime").html(date1);
	    	}else{
	    		console.log(d);
	    		date1 = d.substring(d.lastIndexOf("-")+1);
	    		$(".dateTime").html(date1);
	    	}
	    	
	    }
	    function getAllMytask(){
	    	var d = $("#selectData").val();
	    	 if(d==""){
	    	    var date = new Date();
	    	    var month = date.getMonth() + 1;
	    	    var strDate = date.getDate();
	    	    if (month <= 9) {
	    	        month = "0" + month;
	    	    }
	    	    if (strDate <= 9) {
	    	        strDate = "0" + strDate;
	    	    }
	    		d = date.getFullYear() + "-" + month + "-" + strDate;
	    	}
	    	
	    	$.ajax({ 
   				type:"post",
   				url:"all_my_task_list",
   				data : {dataStr:d},
   				success:function(data){
   					data=eval("("+data+")");
   					initTaskCount(data.total);
   					initCustomerInfo(data.root);
   					/* success(); */
   					alertbox();
   					if(data.root.length>0){
   						$(".nomessage").css("display","none");
   					} else {
   						$("#visitCustomerInfo").html("");
   					}
   				}
   			});
	    }
	    //任务数
	    function initTaskCount(data){
	    	$("#taskCount").text(data);
	    }
	    
	    //待填写客户信息
	    function initCustomerInfo(data){
	    	var s="<thead><tr><th>姓名</th><th>到访时间</th><th>人数</th><th>操作</th></tr></thead>";
	    	$.each(data,function(v,o){
	    		if(o.customerName!=""){
	    			s+='<tbody><tr><td>'+o.customerName+'</td>';
	    		}else{
	    			s+='<tbody><tr><td>未知</td>';
	    		}
       			s+='<td>'+o.arriveTime.substring(5,16)+'</td>';
       			s+= '<td>'+o.customerCount+'</td>';
       			if(o.phone==null || o.phone==''){
       				s+='<td><button id="promptBtn'+v+'" class="btn-add" type="button" data-value='+o.visitNo+'>添加</button></td></tr></tbody >';	
       			}else{
       				s+='<td><button id="hadPhoneBtn'+v+'" class="btn-addTwo" type="button" data-value='+o.visitNo+' data-phone='+o.phone+'>添加</button></td></tr></tbody >';
       				
       			}
       			
       		});
       		
       		if(data.length>0){
				$("#visitCustomerInfo").html(s);
			} else{
				$(".nomessage").css("display","block");
			} 
	    }
	    
    </script>
    <title>置业顾问首页</title>
</head>
<body>
	<div class="mui-content">
		<div class="calendar">
			<!-- <a href="to_calendar_page"> -->
			<img src="static/images/calendar.png" />
		</div>
		<div class="messages">
			<input id="selectData" type="hidden" value="${date}">
			<div class="messageNub">
				<span id="taskCount" class="Nub"></span>
			</div>
			<div class="messageHeader">
				<img src="static/images/messageHeader.png"/>
			</div>
			<div class="meaaageList">
				<table id="visitCustomerInfo"> 
				</table>
				
				<!-- 二级列表 -->
				<ul class="mui-table-view mui-table-view-chevron">				
					<li class="mui-table-view-cell mui-collapse">
						<a class="mui-navigate-right">
							<img alt="" src="static/images/oneClock.png" class="timeImg">
							<div class="timeDiv">
								<p class="timeP">10-13 09：26</p>
								<p class="arTime">到访时间</p>
							</div>
							<div class="arriveDiv">
								<p>+2
									<span><img alt="" src="static/images/zhiding.png" class="zhidingImg"></span>
									<span class="typeSpan">指定</span></p>								
							</div>
						</a>
						
						
						<ul class="mui-table-view mui-table-view-chevron" id="chanpin">
							<li class="mui-table-view-cell" style="position: relative;">
								<p class="cusMsg">未知<span>19999999999</span></p>
								<button class="editBtn"><img src="static/images/editBtn.png"></button> 
							</li>
							<li>
								<button class="secBtn"><img alt="" src="static/images/wuxiaoBtn.png"><span>接访无效</span></button>
								<button class="secBtn"><img alt="" src="static/images/addBtn.png"><span>添加客户</span></button>
								<button class="secBtn"><img alt="" src="static/images/tijiaoBtn.png"><span>提交任务</span></button>
							</li>
						</ul>
					</li>
					
				</ul>
				
				
				<!--任务完成-->
				<div class="nomessage" style="display: none;">
					<img src="static/images/noMessage.png" alt="" />
					<span class="dateTime"></span>
					<p>
						<img src="static/images/icon-yes.png" alt="" />
						恭喜，您已完成任务啦
					</p>
				</div>
			</div>			
		</div>
	</div>
		<nav class="mui-bar mui-bar-tab">
    	<a class="mui-tab-item mui-active" id="select">
        	<span class="mui-icon mui-icon-list"></span>
        	<span class="mui-tab-label">任务</span>
    	</a>
    	<a class="mui-tab-item" id="house">
        	<span class="mui-icon mui-icon-home"></span>
        	<span class="mui-tab-label">房源</span>
    	</a>
   		 <a class="mui-tab-item" id="service">
   	    	<span class="mui-icon mui-icon-chatboxes"></span>
        	<span class="mui-tab-label">业务</span>
    	</a>
    	<a class="mui-tab-item" id="my">
       		<span class="mui-icon mui-icon-person"></span>
        	<span class="mui-tab-label">我的</span>
    	</a>
    	</nav>
    <!-- 输入电话框 -->
    <div class="alertbox" style="display: none;">
    	<form id="myform">
    		<div class="contain">
    			<p>请输入手机号</p>
    		<input class="phoneinp" name="phone" type="text" placeholder="请输入手机号">
    		<p id="errMsg" style="color:red;text-align:right;font-size:.32rem;"></p>
    		<div class="btngroup">
    			<button type="button" class="btn-close">取消</button>
    			<button type="button" class="btn-confirm">确认</button>
    		</div>
    		</div>
    	</form>
    </div>
    <!--遮罩层-->
	<div class="popup-backdrop" style="display: none;"></div>
	<script src="static/js/mui.min.js"></script>
	<script type="text/javascript">
	//初始化
			mui.init({
				swipeBack: true //启用右滑关闭功能
			});
			if (!$("#select").hasClass('mui-active')) {
				document.getElementById('select').addEventListener('tap', function() {
	 			 //打开关于页面
	  			mui.openWindow({
	   				url: 'to_goToAgentIndex', 
	   				show: {
	   					autoShow:false
	   				},
	    			id:'info'
	  				});
	  			});
			}
  			document.getElementById('house').addEventListener('tap', function() {
 			 //打开关于页面
  			mui.openWindow({
   				url: 'to_goToSaleHouseDetail', 
    			id:'info'
  				});
  			});
  			document.getElementById('service').addEventListener('tap', function() {
 			 //打开关于页面
  			mui.openWindow({
   				url: 'to_goToAgentMyService', 
    			id:'info'
  				});
  			});
  			document.getElementById('my').addEventListener('tap', function() {
 			 //打开关于页面
  			mui.openWindow({
   				url: 'to_goToAgentMyPerson', 
    			id:'info'
  				});
			});
	</script>
	
 <section id="rili" style="display: none;z-index: 2000;background: #fff;font-size: .366rem;">
        <header class="mui-bar mui-bar-nav mui-bar-transparent">
        	<a id="suibian" class=" mui-icon mui-icon-left-nav mui-pull-left" onclick="closeThis()"></a>
            <h1 class="mui-title">选择日期</h1>
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

<script src="<%=request.getContextPath()%>/static/js//calendarOne.js"></script>
<script>
(function (){   
	
    /*打开日历*/
    $(".calendar").click(function(){
        $(".mui-content").css("display","none");
        $("#rili").css("display","block");
        $('.mui-bar-tab').css("display","none");
        window.scrollTo(0, document.body.scrollHeight);
    })

})();
/*关闭日期*/
            function closeThis() {
                $("#rili").css("display","none");
                $(".mui-content").css("display","block");
                $('.mui-bar-tab').css("display","block");
            }
    var calendarIns = this.calendarIns

    calendarIns = new calendar.calendar( {
    	count: 12,
        selectDateName: '',
        maxDate: new Date(),
        isShowHoliday: true,
        selectDateName: '选中',
        isShowWeek: false
            // count: 4,
            // selectDate: new Date(),
            // selectDateName: '选中',
            // minDate: new Date(),
            // maxDate: new Date( +new Date() + 100 * 86400000 ),
            // isShowHoliday: true,
            // isShowWeek: false
        } );
    $(calendarIns).on('afterSelectDate', function (event, params) {
        
            var curItem = params.curItem,
                date = params.date,
                dateName = params.dateName;

            calendarIns.setSelectDate( date );

      if (calendarIns.selectDate) {
    	  $("#selectData").val(calendarIns.selectDate);
    	  getAllMytask();
    	   
    	  //console.log('选完了:',calendarIns.selectDate)
        
        closeThis();
        
      }
    })
</script>
</section>	
	
</body>
</html>