<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
    <head>
        <meta charset="UTF-8">
    	<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta content="yes" name="apple-mobile-web-app-capable">
    	<meta content="yes" name="apple-touch-fullscreen">
    	<meta content="telephone=no,email=no" name="format-detection">
        <title>我的</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/frozenui/css/frozen.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/anchangSuitable.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/dmy.css">
        <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/iconfont.js"></script>
    </head>
    <body>
        <footer class="ui-footer ui-footer-btn">
           <ul class="ui-tiled ui-border-t">
                <li data-href="to_director_index" class="ui-border-r"><div><svg class="icon" aria-hidden="true">
                     <use xlink:href="#icon-xieziloubiaozhunhetong"></use>
                    </svg><p>案场</p></div></li>
                <li data-href="to_getManagerAnalysePage" class="ui-border-r"><div><svg class="icon" aria-hidden="true">
                     <use xlink:href="#icon-zonghefenxi"></use>
                    </svg><p>分析</p></div></li>
                <li data-href="to_getManagerAllOrderPage" class="ui-border-r"><div><svg class="icon" aria-hidden="true">
                     <use xlink:href="#icon-dingdan"></use>
                    </svg><p>订单</p></div></li>
                <li data-href="###"><div><svg class="icon" aria-hidden="true">
                     <use xlink:href="#icon-wo"></use>
                    </svg><p class="colorB">我</p></div></li>
            </ul>
        </footer>
        <div class="ui-container">
            <div class="top">
                <a class="setting" href="to_goToSettingPage">
                    <img src="<%=request.getContextPath()%>/static/images/setting.png" alt="">
                </a>
                <div class="head">
                    <img src="<%=request.getContextPath()%>/static/images/head_bg.png" alt="">
                    <c:if test="${sessionScope.userInfo.photo != null && sessionScope.userInfo.photo != ''}">
	                    <img class="headpro" src="${sessionScope.userInfo.photo}" alt="">
                    </c:if>
                    <c:if test="${sessionScope.userInfo.photo == null || sessionScope.userInfo.photo == ''}">
	                    <img class="headpro" src="<%=request.getContextPath()%>/static/images/morentouxiang.png" alt="">
                    </c:if>
                    <p id="name">${sessionScope.userInfo.userCaption}</p>
                    <p id="job"></p>
                </div>
            </div>
            
            <ul class="ui-list ui-list-text ui-list-active ui-list-cover ui-border-tb ui-list-link">

                <li class="ui-border-t task" data-href="to_goToManagerTask">
                    <div class="ui-list-thumb">
                        <span style="background-image:url(<%=request.getContextPath()%>/static/images/task.png)"></span>
                    </div>
                    <div class="ui-list-info">
                        <h4>任务</h4>
                    </div>
                </li>
                
                <li class="ui-border-t team" data-href="to_goToVisitRankingPage">
                    <div class="ui-list-thumb"> 
                        <span style="background-image:url(<%=request.getContextPath()%>/static/images/team.png)"></span>
                    </div>
                    <div class="ui-list-info">
                        <h4>我的团队</h4>
                    </div>
                </li>
                <li class="ui-border-t forms" data-href="to_goToMyServiceStatement">
                    <div class="ui-list-thumb">
                        <span style="background-image:url(<%=request.getContextPath()%>/static/images/form.png)"></span>
                    </div>
                    <div class="ui-list-info">
                        <h4>业绩报表</h4>
                    </div>
                </li>
            </ul>


        </div>


        <script src="<%=request.getContextPath()%>/static/frozenui/lib/zepto.min.js"></script>
        <script src="<%=request.getContextPath()%>/static/frozenui/js/frozen.js"></script>
        <script>
        (function (){           
            /*页面跳转*/
            $('.ui-list li,.ui-tiled li,.ui-nowrap,.ui-border-t').click(function(){
                if($(this).data('href')){
                location.href= $(this).data('href');
            }
            });
        })();
        </script>
    </body>
</html>