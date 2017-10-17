<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta content="yes" name="apple-mobile-web-app-capable">
        <meta content="yes" name="apple-touch-fullscreen">
        <meta content="telephone=no,email=no" name="format-detection">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
        <title>登陆页</title>
        <link rel="stylesheet" href="static/frozenui/css/frozen.css">
        <link rel="stylesheet" type="text/css" href="static/css/loginDirector.css">
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript">
        	$(document).ready(function(){
        		$("#appLogin").click(function(){
        			login();
        		});
        		var logFlag = $("#noSession").val();
        		console.log(logFlag);
        		if(logFlag!=null && logFlag!=""){
        			autoLogin();        			
        		}
        	});
        	function autoLogin(){
        		$("#noSession").val("");
       			$.ajax({
           	        type: "post",
           	       // dataType:"json",
           	        url:"form_userToken_to_my_task_page",
           	        async: false,
           	        data:{userToken:$("#cookie").val(),roleId:"7"},
           	        success: function(data) {
           	        	if(data.returenCode==200){
           	        		window.location.href = data.skipURL;
           	        	}else{
           	        		//window.location.href = "director_login";
           	        	}
           	        }
           	    });
        	}
        	function login(){
        		$.ajax({
                    type: "POST",
                    url:"applogin",
                    data:$("#uesrMsg").serialize(),// 你的formid
                    async: false,
                    error: function(request) {
                    	
                    },
                    success: function(data) {
                    	//成功
                    	if(data.returenCode==200){
                    		//setTookenInCookie();
                    		window.location.href = data.skipURL;
                    	}
                    	//没有权限
                    	if(data.returenCode==401){
                    		$("#msgInfo").text(data.msg);
                    	}
                    	//用户密码错误
                    	if(data.returenCode==402){
                    		//alert(data.msg);
                    		$("#msgInfo").text(data.msg);
                    	}
                    }
                });
        	}
        </script>
    </head>
    <body ontouchstart>
    	<input id="token" type="hidden">
    	<input id="noSession" type="hidden" value="${noSession}">
        <section class="ui-container">
           <div class="logo">
               <img src="static/images/logo@2x.png" alt="">
           </div>
           <div class="passForm">
               <form id="uesrMsg">
                    <div class="pssname">
                        <input class="userCaption" name="phone" type="text" placeholder="用户名"></input>
                        <img src="static/images/zhanghaoPic.png" alt="">
                    </div>
                    <div class="pssword">
                        <input class="password" name="password" type="password" placeholder="密码"></input>
                        <img src="static/images/suoPic.png" alt="">
                    </div>
                    <p id="msgInfo"></p>
                    <input name="sign" value="director" type="hidden">
                    <button id="appLogin" type="button">登录</button>                    
               </form>
           </div>
           <div class="forget">
                <p>
                    <span class="left"></span>忘记密码<span class="right"></span>
                </p>
           </div>
        </section>
        <script type="text/javascript" src="static/js/jquery.cookie.js"></script>
        <script type="text/javascript">
        $(document).ready(function() {
            if($.cookie("userName")!=""&&$.cookie("password")!=""){
        
        var user =$.cookie("userName")
        var pass =$.cookie("password")
        console.log($.cookie("userName"))
        $(".userCaption").val(user);
        $(".password").val(pass);
    }
    $('body').height($('body')[0].clientHeight+50);
    $("#appLogin").click(function(){
        $.ajax({
            //cache: true,
            type: "POST",
            url:"../applogin",
            data:$('#uesrMsg').serialize(),// 你的formid
            async: false,
            error: function(request) {
                
            },
            success: function(data) {
                //成功
                if(data.returenCode==200){
                	$("#token").val(data.userToken);
                    setpas();
                    window.location.href = "../"+data.skipURL;
                }
                //没有权限
                if(data.returenCode==401){
                    $("#msgInfo").text(data.msg);
                }
                //用户密码错误
                if(data.returenCode==402){
                    //alert(data.msg);
                    $("#msgInfo").text(data.msg);
                }
            }
        });

    });
    //判断浏览是否支持H5缓存
    function setpas(){
        if(window.localStorage){
            // 获取缓存里面的数据
            var userCaptionVal = JSON.stringify($(".userCaption").val())
            var passwordVal = JSON.stringify($(".password").val())
            localStorage.setItem("userName", userCaptionVal);
            localStorage.setItem("password", passwordVal);
            var user = JSON.parse(localStorage.getItem("userName"));
            var pwd = JSON.parse(localStorage.getItem("password"));
            $(".userCaption").val(user);
            $(".password").val(pwd);
        }else{
            $.cookie("userName",$(".userCaption").val(),{
                expires:7,
                path: "/"
            })
            $.cookie("password",$(".password").val(),{
                expires:7,
                path: "/"
            })
            $.cookie("token",$("#token").val(),{
    			expires:7,
    			path: "/"
    		})
        };
    };
    
    
});
        </script>
        <% Cookie[] cookies = request.getCookies();
	   Cookie sCookie=null;
	   if(cookies!=null){
		   for(int i=0;i<cookies.length;i++){
				sCookie=cookies[i];
				if("token".equals(sCookie.getName())){ %>
				<input type="hidden" id="cookie" value="<%=sCookie.getValue()%>">
				<% }
			}
	   }
	%>
    </body>
</html>