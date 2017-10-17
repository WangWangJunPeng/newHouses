$(document).ready(function(){
	if($("#whichButtonHidSign").val()=="waitPayEnter"){
		$("#waitPayMoneyEnter").css("display","block");
	}
	if($("#whichButtonHidSign").val()=="waitSign"){
		$("#signEnter").css("display","block");
	}
    var orderNo = $("#orderNo").val();
    getDetailsInfo(orderNo);
});
//获取订单数据
function getDetailsInfo(orderNo){
	$.ajax({
		type : "post",
		url : "get_order_deail_data",
		async:false,
		data : {
			orderId: orderNo
			},
		success : function(data) {
			setDetailsInfo(data);
		}
	});
}
//页面赋值
function setDetailsInfo(data){
	//console.log(data);
	setOrderStatusInfo(data);//填充订单状态数据
	setOrderCustomerInfo(data);//填充订单客户信息
	setOrderOrderHouseInfo(data);//填充订单房源信息
	setPayMoneyPic(data);//打款凭证图片
}

function setPayMoneyPic(data){
	if(data.payMoneyPic!=null && data.payMoneyPic!=""){
		$("#payMoneyPic").attr("src",data.payMoneyPic);
		
	}else{
		$("#payMoneyPic").attr("src","static/images/noPic.png");
	}
}

function setOrderStatusInfo(data){
	if(data.submitOrder!=null){
		$("#orderStutas").text(data.submitOrder);
		$("#submitOrderTime").text(data.submitTime);
	}else{
		$(".orderStutas").css("display","none");
		//$(".orderStutas").remove();
	}
	if(data.waitCheck!=null){
		$("#waitChect").text(data.waitCheck);
		$("#waitChectTime").text(data.applyTime);
	}else{
		$(".waitChect").css("display","none");
		//$(".waitChect").remove();
	}
	if(data.checkSign!=null){
		$("#checked").text(data.checkSign);
		$("#checkedTime").text(data.checkTime);
		//$(".directorCheck").css("display","none");
	}else{
		$(".checked").css("display","none");
		//$(".checked").remove();
	}
	if(data.orderOutTime!=null){
		$("#outTime").text(data.orderOutTime);
		$("#outTimeTime").text(data.outTime);
		//$(".directorCheck").css("display","none");
	}else{
		$(".outTime").css("display","none");
		//$(".outTime").remove();
	}
	if(data.payMoney!=null){
		$("#payMoney").text(data.payMoney);
		$("#payMoneyTime").text(data.payTime);
		//$(".directorCheck").css("display","none");
	}else{
		$(".payMoney").css("display","none");
		//$(".payMoney").remove();
	}
	if(data.enterPay){
		$("#enterPay").text(data.enterPay);
		$("#enterPayTime").text(data.enterPayTime);
		//$(".directorCheck").css("display","none");
	}else{
		$(".enterPay").css("display","none");
		//$(".enterPay").remove();
	}
	if(data.sign){
		$("#sign").text(data.sign);
		$("#signTime").text(data.signTime);
		//$(".directorCheck").css("display","none");
	}else{
		$(".sign").css("display","none");
		//$(".sign").remove();
	}
	if(data.revoke!=null){
		$("#revoke").text(data.revoke);
		$("#revokeTime").text(data.revokeTime);
	}else{
		$(".revoke").css("display","none");
		//$(".revoke").remove();
	}
	
}
function setOrderCustomerInfo(data){
	if(data.orderNature==0){
		$("#vOne").attr("checked","checked");
	}else if(data.orderNature==1){
		$("#vTwo").attr("checked","checked");
	}
	if(data.enterBuyName!=null && data.enterBuyName!=""){
		$("#enterBuyName").text(data.enterBuyName);
	}else{
		$("#enterBuyName").text("未填写");
	}
	if(data.orderSubmitMan!=null && data.orderSubmitMan!=""){
		$("#orderSubmitMan").text(data.orderSubmitMan);
	}else{
		$("#orderSubmitMan").text("未填写");
	}
	if(data.idCard!=null && data.idCard!=""){
		$("#idCarkNum").text(data.idCard);
	}else{
		$("#idCarkNum").text("未填写");
	}
	if(data.payMode==0){
		$("#down").attr("checked","checked");
	}else if(data.payMode==1){
		$("#up").attr("checked","checked");
	}
	if(data.earnest!=null && data.earnest!=""){
		$("#haveToPay").text(data.earnest);
	}else{
		$("#haveToPay").text("未支付");
	}
}
function setOrderOrderHouseInfo(data){
	//console.log(data);
	if(data.housePic!=null && data.housePic!=""){
		$("#housePic").attr("src",data.housePic);
	}
	if(data.houseTypeName!=null && data.houseTypeName!=""){
		$("#houseTyple").text(data.houseTypeName);
	}else{
		$("#houseTyple").text("暂无");
	}
	if(data.houseId!=null && data.houseId!=""){
		$("#houseId").text(data.houseId);
	}
	$("#houseCome").text(data.houseName);
	$("#houseCome").attr("title",data.houseName)
	if(data.willSellNo!=null && data.willSellNo!=""){
		$("#willSellNo").text(data.willSellNo);
	}else{
		$("#willSellNo").text("暂无");
	}
	if(data.discount!=null && data.discount!=""){
		$("#youhui").text(data.discount);
		$("#youhui").attr("title",data.discount)
	}else{
		$("#youhui").text("暂无");
	}
	$("#listPrice").text(data.price);
	$("#enterBuyPrice").text(data.enterBuyPrice);
	$("#jieSuanStyle").text(data.accountStyle);
}
//确认到款
function payMoneyEnter(){
	var orderNo = $("#orderNo").val();
	var buttonSign = $("#buttonSign").val();
	$.ajax({
		type : "post",
		url : "trade_business_for_enter_bargain",
		ansy: false,
		data : {
			recordNo : orderNo
		},
		success : function(data) {
			window.location.href="to_order_page?buttonSign="+buttonSign;
		},
	});
}

//签约确认
function signEnter(){
	var orderNo = $("#orderNo").val();
	var buttonSign = $("#buttonSign").val();
	$.ajax({
		type : "post",
		url : "trade_business_for_enter_sign",
		ansy: false,
		data : {
			recordNo : orderNo
		},
		success : function(data) {
			window.location.href="to_order_page?buttonSign="+buttonSign;
		},
	});
}
