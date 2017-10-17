$(document).ready(function(){
	getSuccessData();
});
function getSuccessData(){
	$.ajax({
        type: "POST",
        url:"get_deal_data",
        async: false,
        data:{startDate:starTime , endDate:endTime},
        success: function(data) {
        	setSuccessData(data);
        	canlenderAndMyChart();
        },
        /*error: function(request) {
        	alert("数据获取失败......");
        },*/
    });
}
function setSuccessData(data){
	$("#todaySign").val(data.signCount);
	$("#todayPay").val(data.payMoney);
	$("#dealCount").text(data.dealCount);
	$("#lockedMoney").text(data.lockedValue);
	$("#signedMoney").text(data.signedHouseValue);
	$("#systemCusEnterBuyRate").text(data.systemCusEnterBuyRate);
	$("#systemSignRate").text(data.systemCusSignRate);
}