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
        <title>分析</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/frozenui/css/frozen.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/anchangSuitable.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/fenxi.css">
        <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/iconfont.js"></script>
    	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    	<script type="text/javascript">
    	$(document).ready(function(){
    		getShouCang();
    		getFenxi();
    	});
    	function getShouCang(){
    		$.ajax({
     			 type : "post",
     			 async : false,
     			 url:"to_getManagerAnalyseList",
     			 success : function (data) {
     				 data = eval("(" +data+")");
     				 getManagerShouCang(data.root);
     			 }
     		 });
    	}
    	function getManagerShouCang(data){
    		var s = '';
    		$.each(data,function(v,o){
    			s += '<li>';
                s += '<a href="to_goToSeeCustomerReceptionPage?chartDataId='+o.chartDataId+'" title="">';
                if (o.iconUrl != null && o.iconUrl != ""){
                	s += '<img src="'+o.iconUrl+'" alt="">';
                }else {
                    s += '<img src="<%=request.getContextPath()%>/static/images/yeji.png" alt="">';
                }
                s += '<p>'+o.buttonText+'</p></a></li>';
    		});
    		 if(data.length>0){
      			$('#managerShouCangInfo').prepend(s);
  			 }/* else {
  				 $("#managerShouCangInfo").prepend("<tr><td style='width:10%;height:30px;margin:0 auto;'>暂无数据</td></tr>");
     		} */
    	}
    	function getFenxi(){
    		$.ajax({
    			 type : "post",
    			 async : false,
    			 url:"to_getAppSeeAnalyzeTagsInfo",
    			 success : function (data) {
    				 data = eval("(" +data+")");
    				 getManagerFenxi(data.root);
    			 }
    		 });
    	}
    	function getManagerFenxi(data){
    		var s = '';
    		$.each(data,function(v,o){
    			s += '<div class="tip"><div class="tips">';
                s += '<h2>'+o.categoryName+'</h2></div>';               
            	s += '<div class="list"><ul>';
            	
            	$.each(o.mcdList,function(v1,o1){
	                s += '<li><a href="to_goToSeeCustomerReceptionPage?chartDataId='+o1.chartDataId+'" title="">';
	                if (o1.iconUrl != null && o1.iconUrl != ""){
		                s += '<img src="'+o1.iconUrl+'" alt="">';
	                }else {
	                	s += '<img src="<%=request.getContextPath()%>/static/images/yeji.png" alt="">';
	                }
	                s += '<p>'+o1.buttonText+'</p></a></li>';
            	});
                                        
                s += '</ul></div></div>';
    		});
    		if(data.length>0){
      			$('#getManagerOwnFenxi').html(s);
  			 }else {
  				 $("#getManagerOwnFenxi").html("<tr><td style='width:10%;height:30px;margin:0 auto;'>暂无数据</td></tr>");
     		}
    	}
    	</script>
    
    </head>
    <body ontouchstart="">
        <header class="ui-header ui-header-positive ui-border-b">
            <h1>分析</h1>
        </header>
        <footer class="ui-footer ui-footer-btn">
           <ul class="ui-tiled ui-border-t">
                <li data-href="to_director_index" class="ui-border-r"><div><svg class="icon" aria-hidden="true">
                     <use xlink:href="#icon-xieziloubiaozhunhetong"></use>
                    </svg><p>案场</p></div></li>
                <li data-href="###" class="ui-border-r"><div><svg class="icon" aria-hidden="true">
                     <use xlink:href="#icon-fenxi"></use>
                    </svg><p class="colorB">分析</p></div></li>
                <li data-href="to_getManagerAllOrderPage" class="ui-border-r"><div><svg class="icon" aria-hidden="true">
                     <use xlink:href="#icon-dingdan"></use>
                    </svg><p>订单</p></div></li>
                <li data-href="to_getManagerMyInfoPage"><div><svg class="icon" aria-hidden="true">
                     <use xlink:href="#icon-jingjiren"></use>
                    </svg><p>我</p></div></li>
            </ul>
        </footer>
        <section class="ui-container">
            <div class="tip">
                <div class="tips">
                    <h2>我的收藏</h2>
                </div>                
                <div class="list" >
                   <ul id="managerShouCangInfo">
                        <li>
                            <a href="to_goToEditShouCangPage" title="">
                                <img src="<%=request.getContextPath()%>/static/images/tianjiaPic.png" alt="">
                                <p>编辑</p>
                            </a>                           
                        </li>
                    </ul>
                </div>
            </div>
            <div id="getManagerOwnFenxi">
            	
            </div>
        </section>
        <script src="<%=request.getContextPath()%>/static/frozenui/lib/zepto.min.js"></script>
        <script src="<%=request.getContextPath()%>/static/frozenui/js/frozen.js"></script>
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