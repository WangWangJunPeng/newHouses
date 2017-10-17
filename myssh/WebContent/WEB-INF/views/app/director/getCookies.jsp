<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta content="yes" name="apple-mobile-web-app-capable">
        <meta content="yes" name="apple-touch-fullscreen">
        <meta content="telephone=no,email=no" name="format-detection">
        <title>待盘客</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/frozenui/css/frozen.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/anchangSuitable.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/daiPanke.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/mui.css">
        <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
        <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
        <script type="text/javascript">
        	$(function(){
				//var cookies = $("#cookie").val();
				//console.log(cookies);
        		autoLogin();
        	})
        	function autoLogin(){
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
        	        		window.location.href = "director_login";
        	        	}
        	        	
        	        },
        	        error: function(request) {
        	        	window.location.href = "director_login";
        	        },
        	    });
        	}
        </script>
</head>
<body>
	<% Cookie[] cookies = request.getCookies();
	   Cookie sCookie=null;
		for(int i=0;i<cookies.length;i++){
			sCookie=cookies[i];
			if("token".equals(sCookie.getName())){ %>
			<input type="hidden" id="cookie" value="<%=sCookie.getValue()%>">
			<% }
		}
	%>
</body>
</html>