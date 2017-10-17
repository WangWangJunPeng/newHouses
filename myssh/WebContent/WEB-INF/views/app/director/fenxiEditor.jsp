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
        <title>应用编辑</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/frozenui/css/frozen.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/anchangSuitable.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/fenxi.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/fenxiEditor.css">
        <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/iconfont.js"></script>
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
                    s += '<a href="###" title="">';
                    if (o.iconUrl != null && o.iconUrl != ""){
                    	s += '<img src="'+o.iconUrl+'" alt="">';
                    }else {
	                    s += '<img src="<%=request.getContextPath()%>/static/images/yeji.png" alt="">';
                    }
                    s += '<p>'+o.buttonText+'</p>';
                    
                    s += '<div class="group closed">';
                    s += '<input type="radio" id="$'+o.chartDataId+'" value="$'+o.chartDataId+'" checked></input>';
                    s += '<label for="'+o.chartDataId+'"><img src="<%=request.getContextPath()%>/static/images/shanchu.png" alt=""></label>';
                    s += '</div></a></li>';
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
                		s += '<li><a href="###" title="">';
                		 if (o1.iconUrl != null && o1.iconUrl != ""){
     		                s += '<img src="'+o1.iconUrl+'" alt="">';
     	                }else {
     	                	s += '<img src="<%=request.getContextPath()%>/static/images/yeji.png" alt="">';
     	                }
                		 s += '<p>'+o1.buttonText+'</p>';
                		 s += '<div class="group cheesed">';
                		 s += '<input type="radio" id="'+o1.chartDataId+'" value="'+o1.chartDataId+'" ></input>';
                		 s += '<label for="'+o1.chartDataId+'"><img src="<%=request.getContextPath()%>/static/images/tianjiaPicc.png" alt=""></label>';
                		 s += '</div></a></li>';
                	});
            		s += '</ul></div></div>';
        		});
        		if(data.length>0){
          			$('#getManagerOwnFenxi').html(s);
      			 }else {
      				 $("#getManagerOwnFenxi").html("<tr><td style='width:10%;height:30px;margin:0 auto;'>暂无数据</td></tr>");
         		}
      		}

      		var getDelNum = "";
      		function getUpdateShouCang() {
      			var aa="";
      			$(".top input[type='radio']:checked").each(function(){
      				aa += "$"+$(this).val()+ ",";
      			})
      			
      			$.ajax({
			       	   type: "POST",
			       	   url: "to_addManagerAnalyse",
			       		//async: false,
			       	   data:{chartDataIds:aa,
			       		chartDataIdStr:getDelNum},
			       	   success: function(){
			       		window.location.href="to_getManagerAnalysePage";
			       	   },
			       	   error:function(){
			       		 //alert(11111)
			       	   }
			       });
      		}
      	</script>
    </head>
    <body ontouchstart="">
        <header class="ui-header ui-header-positive ui-border-b">
            <i class="ui-icon-return" onclick="history.back()"></i><h1>应用编辑</h1>
        </header>
        <section class="ui-container">
        <form>
            <div class="tip top">
                <div class="tips">
                    <h2>我的收藏</h2>
                </div>                
                <div class="list">
                    <ul id="managerShouCangInfo">
                    </ul>
                </div>
            </div>
            
            <div id="getManagerOwnFenxi">
            </div>
                        
            
            <button class="wancheng" type="button" onclick="getUpdateShouCang()">完成</button>
        </form>
        </section>
        <script src="<%=request.getContextPath()%>/static/frozenui/lib/zepto.min.js"></script>
        <script src="<%=request.getContextPath()%>/static/frozenui/js/frozen.js"></script>
        <script>
        $(document).ready(function(){ 
            $(".top input").each(function (i) {
                var pid = $(this).attr("id").substring(1);
                $("#"+pid).prop('checked', 'checked');
            });     
            /*页面跳转*/
            $('.ui-list li,.ui-tiled li').click(function(){
                if($(this).data('href')){
                location.href= $(this).data('href');
            }
            });
            
            $(".cheesed label").click(function(){
                var id1 = 's'+$(this).siblings('input').attr("id");
                var phtml = $(this).parent(".cheesed").siblings('p').html();
                var val = $(this).siblings('input').val();
                var src = $(this).parent(".cheesed").siblings("img").attr("src");
                if ($(this).siblings('input').is(':checked')==false&&$(".top ul li").length<5) {
                    
                    $(".top ul").append('<li><a><img src="'+src+'" alt=""><p>'+phtml+'</p><div class="group closed"><input value="'+val+'" type="radio" id="'+id1+'" checked></input><label for=""><img src="<%=request.getContextPath()%>/static/images/shanchu.png" alt=""></label></div></a></li>')

                }else{

                }
            });
            
            $(document).on("click",".closed",function(){
            	
            		getDelNum += $(this).children("input").val()+",";
                    var id2 = $(this).children('input').attr('id').substring(1);                    
                    $(this).parents('li').remove();
                    $("#"+id2).prop('checked', '');
            });
            
        });
        </script>
    </body>
</html>