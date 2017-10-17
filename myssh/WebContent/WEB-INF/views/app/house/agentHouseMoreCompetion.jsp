<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta content="telephone=no,email=no" name="format-detection">
    <link href="<%=request.getContextPath()%>/static/css/mui.css" rel="stylesheet"/>    
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/houseDetail.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/inventoryFile.css">
	<link rel="stylesheet" href="static/css/suitable.css" />
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
     <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
<title>户型详细参数</title>
<style type="text/css">
		.mui-bar-nav~.mui-content {
			padding-top:.2rem; 
		}
		.mainTile {
			background: #f3f3f3;
			color:#666;
			margin-left: .6rem;
			height: .6rem;
    		line-height: .6rem;
		}
	</style>
</head>
<body class="mui-ios mui-ios-9 mui-ios-9-1">
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
        <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
        <h1 class="mui-title">户型详细参数</h1>
    </header>
    <div class="mui-content">
    	<p class="mainTile">项目名称<span style="margin-left: .6rem;">金地格林（一期）</span ><span style="margin-left: .6rem;">万科润园（二期）</span></p>
    	<div class="introduce">
                <table class="cssTable">
                    <tbody>
                        <tr>
                            <td class="w25">主卧1</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">主卧2</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">次卧1</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>

                    </tbody>
                </table>
         </div>
    </div>
    
</body>
</html>