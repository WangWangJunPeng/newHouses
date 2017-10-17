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
        <title>修改密码</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/frozenui/css/frozen.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/anchangSuitable.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/changePwdTwo.css">
       	<script src="<%=request.getContextPath()%>/static/frozenui/lib/zepto.min.js"></script>
        <script src="<%=request.getContextPath()%>/static/frozenui/js/frozen.js"></script>
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript">
    	function getToSentMessage(){
    		timedCount();
    		$.ajax({
     			 type : "post",
     			 async : false,
     			 url:"to_SentMessage",
     			success : function () {
     				
     			}
     		 });
    	}
    	 var c=60;
         var t
         function timedCount(){
             document.getElementById('sentMsg').innerHTML="重新发送(" + c + "s)";
             document.getElementById('sentMsg').setAttribute("disabled", true);
             c--
             t=setTimeout("timedCount()",1000)
             if (c ==0) {
                 c=60
                 document.getElementById('sentMsg').innerHTML="免费获取验证码";
                 document.getElementById('sentMsg').removeAttribute("disabled"); 
                 stopCount();
             }
         }

         function stopCount(){
             clearTimeout(t)
          }
    	function getUpdatePsd(){   		
    		$.ajax({
    			 type : "post",
    			 async : false,
    			 dataType : "json",
    			 url:"to_changePasswordNew",
    			 data :{newVerification:$('#newVerification').val(),
    				 password : $('#password').val(),
    				 truepassword : $('#truepassword').val()
    			 },
    			 success : function (data) {
    				 getBack(data);
    			 }
    		 });
    	}
    	function getBack(data){	
    		if (data.returnCode == 200 ){
    			window.location.href="director_login";
    		}else {
    			showAlert(data.msg);
    		}
    	}
    	
    	function showAlert(msg){	
    		var dia=$.dialog({      	        
                // title:'提示',
                content:msg,
                button:["确认"]
            });
    	}
    	
        </script>
        
</head>
<body>
	<header class="ui-header ui-header-positive ui-boeder-b">
        <i class="ui-icon-return" onclick="history.back()"></i>
        <h1>修改密码</h1>
    </header>
    <section class="ui-container">
    	<div class="ui-form ui-border-t">
            <form action="#">
                <div class="ui-form-item ui-form-item-r ui-border-b">
                    <input class="phone" type="text" placeholder="请输入验证码" id="newVerification">
                    <button id="sentMsg" class="getCode" type="button" class="ui-border-l" onclick="getToSentMessage()">获取验证码</button>
                </div>
                 <div class="ui-form-item ui-form-item-pure ui-border-b">
                    <label for="#">新密码:</label>
                    <input class="newPwd" type="password" placeholder="请输入新密码" id="password">
                </div>
                <div class="ui-form-item ui-form-item-pure ui-border-b">
                    <label for="#">确认密码:</label>
                    <input class="conPwd" type="password" placeholder="请再次确认密码" id="truepassword">
                </div> 
            </form>
        </div>
        
        <button class="confirmBtn" type="button" onclick="getUpdatePsd()"> 确认</button>
    </section>
   	<section id="dialog"></section>
   	
</body>
</html>