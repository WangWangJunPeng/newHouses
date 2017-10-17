<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">  
         <meta name="viewport" content="width=device-width, initial-scale=1" />
         <link  rel="icon" href="http://root-1252955170.cossh.myqcloud.com/static/images/titleLogo.png"  />
        <title>九邑-中国房产线上分销平台</title>
        <link rel="stylesheet" href="static/css/jquery.fullPage.css">
        <link rel="stylesheet" href="static/css/swiper.css">
        <link rel="stylesheet" href="static/css/webHome.css">
    </head>
    <body>
        <form action="alipaytest" method="post">
        	商户订单号：<input type="text" readonly="readonly" name="bianhao" id="biaohao">
        	订单名称：<input type="text" name="name">
        	付款金额：<input type="text" name="amount">
        	商品描述：<input type="text" name="body">
        	<input type="submit" value="提交">
        </form>
        <script type="text/javascript">
        
        var vNow = new Date();
		var sNow = "";
		sNow += String(vNow.getFullYear());
		sNow += String(vNow.getMonth() + 1);
		sNow += String(vNow.getDate());
		sNow += String(vNow.getHours());
		sNow += String(vNow.getMinutes());
		sNow += String(vNow.getSeconds());
		sNow += String(vNow.getMilliseconds());
		document.getElementById("biaohao").value =  sNow;
        </script>
</html>