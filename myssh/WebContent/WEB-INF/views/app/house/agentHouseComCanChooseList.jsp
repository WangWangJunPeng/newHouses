<%@page import="java.net.URLEncoder"%>
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
    <link href="<%=request.getContextPath()%>/static/css/mui.css"	rel="stylesheet" />
	<link rel="stylesheet"	href="<%=request.getContextPath()%>/static/css/agentHouseCompetionList.css" />
	<link rel="stylesheet"	href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>   
    <title>房源对比可选列表</title>
</head>
<body class="mui-ios mui-ios-9 mui-ios-9-1">
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">房源</h1>

	</header> 
	<div class="mui-content">
	    <section class="messageList">
	    	<h3 class="title">12栋1单元3321号</h3>
	    	<p>
	    		<img alt="" src="static/images/icon1APP.png">
	    		<span> 三房两厅一卫</span>
	    		<span>  |  面积120平方米</span>	
	    	</p>
	    	<p>
	    		<img alt="" src="static/images/icon2APP.png">
	    		<span> 三房两厅一卫</span>
	    		<span>  |  面积120平方米</span>
	    	</p>
	    	<button class="duibiBtn" onclick="plusClick(this)"><span>+</span>对比</button>
	    </section>
	    <section class="messageList">
	    	<h3 class="title">12栋1单元3321号</h3>
	    	<p>
	    		<img alt="" src="static/images/icon1APP.png">
	    		<span> 三房两厅一卫</span>
	    		<span>  |  面积120平方米</span>
	    	</p>
	    	<p>
	    		<img alt="" src="static/images/icon2APP.png">
	    		<span> 三房两厅一卫</span>
	    		<span>  |  面积120平方米</span>
	    	</p>
	    	<button class="duibiBtn" onclick="plusClick(this)"><span>+</span>对比</button>
	    </section>
	</div>
</body>

<script>
	function plusClick(target){
		$(target).css("background-color","#ccc")
	}
</script>
</html>