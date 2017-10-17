<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta content="yes" name="apple-mobile-web-app-capable">
        <meta content="yes" name="apple-touch-fullscreen">
        <meta content="telephone=no,email=no" name="format-detection">
        <title>案场</title>
        <link rel="stylesheet" href="static/frozenui/css/frozen.css">
        <link rel="stylesheet" type="text/css" href="static/css/anchangSuitable.css">
        <link rel="stylesheet" type="text/css" href="static/css/anchangIndex.css">
        <script src="static/js/flexible.js" type="text/javascript"></script>
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="static/js/iconfont.js"></script>
        <script src="static/frozenui/lib/zepto.min.js"></script>
        <script src="static/frozenui/js/frozen.js"></script>
        <script type="text/javascript" src="static/js/indexData.js"></script>
        <script type="text/javascript" src="static/js/jquery.event.drag-1.5.min.js"></script>
		<script type="text/javascript" src="static/js/jquery.touchSlider.js"></script>
        <script type="text/javascript">
            $(function(){
            	$(".main_visual").hover(function(){
            		$("#btn_prev,#btn_next").fadeIn()
            	},function(){
            		$("#btn_prev,#btn_next").fadeOut()
            	});
            	
            	$dragBln = false;
            	
            	$(".main_image").touchSlider({
            		flexible : true,
            		speed : 200,
            		btn_prev : $("#btn_prev"),
            		btn_next : $("#btn_next"),
            		paging : $(".flicking_con a"),
            		counter : function (e){
            			$(".flicking_con a").removeClass("on").eq(e.current-1).addClass("on");
            		}
            	});
            	
            	$(".main_image").bind("mousedown", function() {
            		$dragBln = false;
            	});
            	
            	$(".main_image").bind("dragstart", function() {
            		$dragBln = true;
            	});
            	
            	$(".main_image a").click(function(){
            		if($dragBln) {
            			return false;
            		}
            	});
            	
            	timer = setInterval(function(){
            		$("#btn_next").click();
            	}, 5000);
            	
            	$(".main_visual").hover(function(){
            		clearInterval(timer);
            	},function(){
            		timer = setInterval(function(){
            			$("#btn_next").click();
            		},5000);
            	});
            	
            	$(".main_image").bind("touchstart",function(){
            		clearInterval(timer);
            	}).bind("touchend", function(){
            		timer = setInterval(function(){
            			$("#btn_next").click();
            		}, 5000);
            	});
                function showTime(){ 
                    var show_day=new Array('星期一','星期二','星期三','星期四','星期五','星期六','星期日'); 
                    var time=new Date(); 
                    var year=time.getFullYear(); 
                    var month=time.getMonth()+1; 
                    var date=time.getDate(); 
                    var day=time.getDay();  
                    var now_time=year+'年'+month+'月'+date+'日'; 
                    $(".xingqi").html(show_day[day-1]);
                    $(".riqi").html(now_time);
}
            showTime();
            }); 
        </script>
    </head>
    <body ontouchstart>
        <footer class="ui-footer ui-footer-btn">
            <ul class="ui-tiled ui-border-t">
                <li data-href="to_director_index" class="ui-border-r"><div><svg class="icon" aria-hidden="true">
                     <use xlink:href="#icon-anchang"></use>
                    </svg><p class="colorB">案场</p></div></li>
                <li data-href="to_getManagerAnalysePage" class="ui-border-r"><div><svg class="icon" aria-hidden="true">
                     <use xlink:href="#icon-zonghefenxi"></use>
                    </svg><p>分析</p></<div></div></li>
                <li data-href="to_getManagerAllOrderPage" class="ui-border-r"><div><svg class="icon" aria-hidden="true">
                     <use xlink:href="#icon-dingdan"></use>
                    </svg><p>订单</p></div></li>
                <li data-href="to_getManagerMyInfoPage"><div><svg class="icon" aria-hidden="true">
                     <use xlink:href="#icon-jingjiren"></use>
                    </svg><p>我</p></div></li>
            </ul>
        </footer>
        <div class="ui-container">
        <div class="container">
        <section id="slider">
        <div class="ui-slider" >
        <div class="main_visual">
			<div class="main_image">
				<ul>
					<li><a href="to_director_liberatingPage">
                        <span>
                            <div class="middle">
                    <div class="databar">
                    <p class="xingqi"></p>
                    <p class="riqi"></p>
                    </div>
                <div class="jiefang">
                    <p class="jiefangshu" id="todayVisitCount">0</p>
                    <p class="jiefangtext">今日接访</p>
                </div>
                <div class="jiefanglist">
                    <ul>
                        <li class="line1">
                            <p id="todayNewCus">0</p>
                            <p>新客户通道接访</p>
                        </li>
                        <li class="line1">
                            <p id="todayOldCus">0</p>
                            <p>老客户通道接访</p>
                        </li>
                        <li>
                            <p id="vaildRate">0</p>
                            <p>有效接访率</p>
                        </li>
                    </ul>
                </div>
                </div>
                </span></a></li>
                
					<li><a href="to_director_successDataPage"><span><div class="middle">
                <div class="databar">
                <p class="xingqi"></p>
                <p class="riqi"></p>
                </div>
                <div class="jiefang">
                    <p class="jiefangshu" id="todayPayCount">0</p>
                    <p class="jiefangtext">今日下定</p>
                </div>
                <div class="jiefanglist jiefanglist2">
                    <ul class="listcss">
                        <li class="line1">
                            <p id="todayPay">0</p>
                            <p>订单总数</p>
                        </li>
                        <li>
                            <p id="checked">0</p>
                            <p>已审核订单</p>
                        </li>
                    </ul>
                </div>
                </div></span></a></li>
                
					<li><a href="to_director_successDataPage"><span><div class="middle">
                <div class="databar">
                <p class="xingqi"></p>
                <p class="riqi"></p>
                </div>
                <div class="jiefang">
                    <p class="jiefangshu" id="todaySignCount">0</p>
                    <p class="jiefangtext">今日签约</p>
                </div>
                <div class="jiefanglist jiefanglist2">
                    <ul class="listcss">
                        <li class="line1">
                            <p id="todayWaitSign">0</p>
                            <p>付款待签约</p>
                        </li>
                        <li>
                            <p id="finishSign">0</p>
                            <p>放弃签约</p>
                        </li>
                    </ul>
                </div>
                </div></span></a></li>
                
					<li><a href="to_goToGuideRecordsHaveAndNotPage"><span><div class="middle">
                <div class="databar">
                <p class="xingqi"></p>
                <p class="riqi"></p>
                </div>
                <div class="jiefang">
                    <p class="jiefangshu" id="todayGueidCount">0</p>
                    <p class="jiefangtext">今日报备</p>
                </div>
                <div class="jiefanglist jiefanglist2">
                    <ul class="listcss">
                        <li class="line1">
                            <p id="gueidVisited">0</p>
                            <p>备案已到访</p>
                        </li>
                        <li>
                            <p id="gueidNoVisit">11</p>
                            <p>备案未到访</p>
                        </li>
                    </ul>
                </div>
                </div>
                    </span></a></li>
				</ul>

			</div>
		</div>
		</div>
		</section>
		</div>
				
            <div class="bottomDiv"><a href="to_today_agent_status_page">
                <div class="line">
                    <div class="leftline" id="recing">
                        
                    </div>
                    <div class="rightline">
                        <p>接访中: <span id="recingNum"></span></p>
                    </div>
                </div>
                <div class="line lineop1">
                    <div class="leftline" id="waitRec">
                        <img src="static/images/xiaoren.png" alt="">
                    </div>
                    <div class="rightline">
                        <p>空闲中: <span id="waitRecNum"></span></p>
                    </div>
                </div>
                <div class="line lineop2">
                    <div class="leftline" id="rest">
                        <img src="static/images/xiaoren.png" alt="">
                    </div>
                    <div class="rightline">
                        <p>休息中: <span id="restNum"></span></p>
                    </div>
                </div>
            </a></div>
		</div>
        
            
      

        <script>
        (function (){           
            /*页面跳转*/
            $('.ui-list li,.ui-tiled li').click(function(){
                if($(this).data('href')){
                location.href= $(this).data('href');
            }
            });
        })();
        </script>
    </body>
</html>