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
    <title>拒绝订单</title>
    <link rel="stylesheet" href="static/frozenui/css/frozen.css">
    <link rel="stylesheet" type="text/css" href="static/css/cancleOrderApp.css">
   	<script src="static/js/flexible.js" type="text/javascript"></script>
</head>
<body>
	<header class="ui-header ui-header-positive ui-boeder-b">
        <i class="ui-icon-return" onclick="history.back()"></i>
        <h1>拒绝原因</h1>
        <input type="hidden" value="${orderId}" id="orderId">
    </header>
    <section class="cancleReason">
		<p>是否取消订单？请告诉我们原因</p>
		<h5>我们会努力做的更好</h5>
	</section>
	<section id="firstOption" class="optionSection">
		<label class="ui-radio" for="radio">
			订单右边，暂时不下订单
            <input type="radio" name="radio" value="订单右边，暂时不下订单">
        </label>
	</section>
	<section class="optionSection">
		<label class="ui-radio" for="radio">
			填错订单信息，需要重新发布订单
            <input type="radio" name="radio" value="填错订单信息，需要重新发布订单">
        </label>
	</section>
	<section class="optionSection">
		<label class="ui-radio" for="radio">
			我要修改下订房源
            <input type="radio" name="radio" value="我要修改下订房源">
        </label>
	</section>
	<section class="optionSection">
		<label class="ui-radio" for="radio">
			没有及时审批，不想等了
            <input type="radio" name="radio" value="没有及时审批，不想等了">
        </label>
	</section>
	<section class="optionSection">
		<label class="ui-radio" for="radio">
			等时间太长，我要重新考虑
            <input type="radio" name="radio" value="等时间太长，我要重新考虑">
        </label>
    </section>
	<section class="optionSection">
		<label class="ui-radio" for="radio">
			其他
            <input type="radio" name="radio" value="其他">
        </label>
	</section>
	<section class="inputSection">
		<textarea class="reasonInput" placeholder="如果你因其他原因取消，也请告诉我们原因" id="moreReason"></textarea>
	</section>			
	<section class="btnSection">
		<button class="confirmBtn" onclick="getRealRevoke()"> 确认提交</button>
	</section>
	<script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
	function getRealRevoke(){
		var orderId = $('#orderId').val();
		var reason = $(".optionSection input[name='radio']:checked").val();
		if(reason == '其他'){
			reason = $("#moreReason").val();
		}
		 $.post("to_not_agree_order",{orderId : orderId, reason : reason},function(data){
			if(data.result == "success"){
				window.location.href = "to_getManagerAllOrderPage";
			}
		});
	}
	</script>
</body>
</html>