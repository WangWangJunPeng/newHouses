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
        <title>设置</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/frozenui/css/frozen.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/anchangSuitable.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/setting.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
        <script src="<%=request.getContextPath()%>/static/frozenui/lib/zepto.min.js"></script>
        <script src="<%=request.getContextPath()%>/static/frozenui/js/frozen.js"></script>
		<script type="text/javascript">
		function logout(){
			$.ajax({
				type:"post",
				url:"director_logout",
				success :function(data){
					getOut(data);
				}
			});
		}
		function getOut(data){
			var isIos = /iPad|iPhone|iPod/.test(navigator.userAgent) && !window.MSStream;
			if (isIos){
				 WebViewJavascriptBridge.callHandler('quitAccount',null,function(response) {
		                 alert(response);
		      	});
			}else{
				window.location.href=data.url;
			}
		}
		</script>
</head>
<body>
	<header class="ui-header ui-header-positive ui-boeder-b">
        <i class="ui-icon-return" onclick="history.back()"></i>
        <h1>设置</h1>
    </header>
    <section class="ui-container">
    	<ul class="ui-list-text ui-list-active ui-list-cover ui-border-tb ui-list-link">
			<!-- <li class="ui-border-t task" data-href="task.html">
                <div class="ui-list-info">
                    <h4>意见反馈</h4>
                </div>
            </li>
            <li class="ui-border-t aboutUs">
                <div class="ui-list-info">
                    <h4>关于我们</h4>
                </div>
            </li> -->
			<li class="ui-border-t changePwd" data-href="to_goToChangePasswordPage">
                <div class="ui-list-info">
                    <h4>修改密码</h4>
                </div>
            </li>
        </ul>
        <h4 class="quit" onclick="logout()">退出</h4>

    </section>
    <script>
        (function (){           
            /*页面跳转*/
            $('.changePwd').click(function(){
                if($(this).data('href')){
                location.href= $(this).data('href');
            }
            });

        })();
        </script>
</body>
</html>