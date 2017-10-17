<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta content="telephone=no,email=no" name="format-detection">
    <link href="<%=request.getContextPath()%>/static/css/mui.css"	rel="stylesheet" />
	<link rel="stylesheet"	href="<%=request.getContextPath()%>/static/css/agentHouseCompetionList.css" />
	<link rel="stylesheet"	href="<%=request.getContextPath()%>/static/css/suitable.css" />
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>   
     <title>房源对比列表</title>
</head>
<body class="mui-ios mui-ios-9 mui-ios-9-1">
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">房源对比</h1>
		<button class="mui-pull-right mui-icon mui-icon mui-icon-compose" id="bianji"></button>
		<button class="mui-pull-right mui-icon mui-icon" id="cancle">取消</button>
		
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
	    	<div class="mui-input-row mui-checkbox deleteRa">
				<input name="checkbox" type="checkbox">
			</div>
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
	    	<div class="mui-input-row mui-checkbox deleteRa">
				<input name="checkbox" type="checkbox">
			</div>
	    </section>
	    
	    <button class="bottomBtn" id="duibi">开始对比</button>
	    <button class="bottomBtn" style="display: none;" id="shanchu">删除</button>
	</div>
</body>
<script>
$(document).ready(function () {
	$("#bianji").bind("click", function () {
		$("#bianji").hide();
		$("#cancle").show();
		$("#duibi").hide();
		$("#shanchu").show();
	});
	$("#cancle").bind("click", function () {
		$("#cancle").hide();
		$("#bianji").show();
		$("#duibi").show();
		$("#shanchu").hide();
	});
});

</script>
</html>