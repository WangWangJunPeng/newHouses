<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta content="yes" name="apple-mobile-web-app-capable">
        <meta content="yes" name="apple-touch-fullscreen">
        <meta content="telephone=no,email=no" name="format-detection">
        <title>待点评客户</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/frozenui/css/frozen.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/anchangSuitable.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/reviewedCus.css">
        <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
</head>
<body>
	<header class="ui-header ui-header-positive ui-boeder-b">
        <i class="ui-icon-return" onclick="history.back()"></i>
        <h1></h1>
    </header>
    <section class="ui-container">
    	<table border="1">
  			<tr>
   				<th>客户姓名</th>
    			<th>到访时间</th>
  			</tr>
    	<c:forEach items="${customerList }" var="cl">
    		
  			<tr class="first" data-href="to_pankefinish?projectCustomerId=${cl.customerId }&visitNo=${cl.visitNo}">
    			<td>${cl.customerName }</td>
    			<td>${cl.arriveTime }</td>
  			</tr>
    	</c:forEach>
		</table>
    </section>
    <script src="<%=request.getContextPath()%>/static/frozenui/lib/zepto.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/frozenui/js/frozen.js"></script>
    <script>
        (function (){           
            /*页面跳转*/
            $('tr').click(function(){
                if($(this).data('href')){
                location.href= $(this).data('href'); 
            } 
            });
        })();
    </script>
</body>
</html>