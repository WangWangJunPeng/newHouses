$(function(){
	var orderId = $('#orderId').val();
	
	orderStatus.init(orderId);
	//拒绝审核通过
	$('.notOk').click(function(){
		orderStatus.refuseTheOrder(orderId);
	});
	//同意审核通过
	$('.okok').click(function(){
		orderStatus.agreeTheOrder(orderId);
	});
});


var orderStatus = {
		
		URL : {
			
			orderDeail : function(){
				return "to_get_order_deail_data_app";
			},
			refuseOrder : function(){//进入拒绝页面
				return "to_refuse_page";
			},
			agreeOrder : function(){
				return "to_agree_order";
			}
		},
		
		init : function(orderId){
			orderStatus.getOrderDeailData(orderId);
		},
		
		getOrderDeailData : function(orderId){
			$.ajax({
				type : "POST",
				url : orderStatus.URL.orderDeail(),
				async : false,
				data : {orderId : orderId},
				success : function(data){
					console.log(data);
					orderStatus.fillOrderDeailInfo(data);
				}
			});
			
		},
		
		fillOrderDeailInfo : function(data){
			//订单详情
			$('#orderSubmitMan').text(data.orderSubmitMan);
			if(data.orderNature == 0){
				$('#dingdanX').text("自购");
			}else if (data.orderNature == 1){
				$('#dingdanX').text("代购");
			};
			let price1 = data.price/data.houseArea;
			let price = price1.toFixed(2);
			$('#houseId').text(data.houseName);
			$('#enterBuyName').text(data.enterBuyName);
			$('#idCard').text(data.idCard);
			$('#houseId1').text(data.houseId);
			$('#houseName').text(data.houseName);
			$('#willSellNo').text(data.willSellNo);
			$('#houseTypeName').text(data.houseTypeName);
			$('#price').text(data.price);
			$('#discount').text(data.discount);
			$('#enterBuyPrice').text(data.enterBuyPrice);
			$('#accountStyle').text(data.accountStyle);
			$('#earnest').text(data.earnest);
			$('#houseArea').text(data.houseArea+"㎡");
			$('#house1TypeName').text(data.houseType);
			$('#danPrice').text(price);
			if(data.checkSign!=null){
				$('#xuzhi').text(data.checkSign + '条款');
			}else{
				$('#xuzhi').text('未同意条款');
			}
			if(data.payStyle == 0){
				$('#payStyle').text("线上");
			}else if(data.payStyle == 1){
				$('#payStyle').text("线下");
			}
			
			//订单状态
			if(data.submitOrder!=null){
				$("#orderStutas").text(data.submitOrder);
				$("#submitOrderTime").text(data.submitTime);
			}else{
				$(".orderStutas").css("display","none");
			}
			if(data.waitCheck!=null){
				$("#waitChect").text(data.waitCheck);
				$("#waitChectTime").text(data.applyTime);
			}else{
				$(".waitChect").css("display","none");
			}
			if(data.checkSign!=null){
				$("#checked").text(data.checkSign);
				$("#checkedTime").text(data.checkTime);
				//$(".directorCheck").css("display","none");
			}else{
				$(".checked").css("display","none");
			}
			if(data.orderOutTime!=null){
				$("#outTime").text(data.orderOutTime);
				$("#outTimeTime").text(data.outTime);
				//$(".directorCheck").css("display","none");
			}else{
				$(".outTime").css("display","none");
			}
			if(data.payMoney!=null){
				$("#payMoney").text(data.payMoney);
				$("#payMoneyTime").text(data.payTime);
				//$(".directorCheck").css("display","none");
			}else{
				$(".payMoney").css("display","none");
			}
			if(data.enterPay){
				$("#enterPay").text(data.enterPay);
				$("#enterPayTime").text(data.enterPayTime);
				//$(".directorCheck").css("display","none");
			}else{
				$(".enterPay").css("display","none");
			}
			if(data.sign){
				$("#sign").text(data.sign);
				$("#signTime").text(data.signTime);
				//$(".directorCheck").css("display","none");
			}else{
				$(".sign").css("display","none");
			}
			if(data.orderNeedCheck!=0){
				$(".directorCheck").css("display","none");
			}
			
		},
		
		refuseTheOrder : function(orderId){
			window.location.href = "to_refuse_page?orderId="+orderId;
		},
		
		agreeTheOrder : function(orderId){
			$.post(orderStatus.URL.agreeOrder(), {orderId : orderId}, function(data){
				if(data.result == "success"){
					window.location.href = "to_getManagerAllOrderPage";
				}
			});
		}
}