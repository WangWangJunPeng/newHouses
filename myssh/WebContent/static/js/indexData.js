$(document).ready(function(){
	getIndexData();
	getIndexGueidData();
});
function getIndexData(){
	$.ajax({
        type: "POST",
        url:"get_today_receiver_data",
        //async: false,
        success: function(data) {
        	setIndexData(data);
        },
        /*error: function(request) {
        	alert("数据获取失败......");
        },*/
    });
}
function getIndexGueidData(){
	$.ajax({
        type: "POST",
        url:"getGuideRecordsByToday",
        //async: false,
        success: function(data) {
        	data = eval("("+data+")");
        	setGueidIndexData(data.data);
        },
        /*error: function(request) {
        	alert("数据获取失败......");
        },*/
    });
}
function setIndexData(data){
	var recingNum = data.recingCount
	var waitRecNum = data.wait;
	var rest = data.rest;
	var recingManNum = "";
	var waitRecManNum = "";
	var restManNum = "";
	if(recingNum>10){
		for(var i=0;i<10;i++) {
			recingManNum += '<img src="static/images/xiaoren.png" alt="">';
		}
	}else{
		for(var i=0;i<recingNum;i++) {
			recingManNum += '<img src="static/images/xiaoren.png" alt="">';
		}
	}
	if(waitRecNum>10){
		for(var i=0;i<10;i++) {
			waitRecManNum += '<img src="static/images/xiaoren.png" alt="">';
		}
	}else{
		for(var i=0;i<waitRecNum;i++) {
			waitRecManNum += '<img src="static/images/xiaoren.png" alt="">';
		}
	}
	if(rest>10){
		for(var i=0;i<10;i++) {
			restManNum += '<img src="static/images/xiaoren.png" alt="">';
		}
	}else{
		for(var i=0;i<rest;i++) {
			restManNum += '<img src="static/images/xiaoren.png" alt="">';
		}
	}
	
	$("#recing").html(recingManNum);
	$("#recingNum").text(recingNum);
	
	$("#waitRec").html(waitRecManNum);
	$("#waitRecNum").text(waitRecNum);
	
	$("#rest").html(restManNum);
	$("#restNum").text(rest);
	
	$("#todayVisitCount").text(data.todayRecCount);
	$("#todayNewCus").text(data.newCusVisitNum);
	$("#todayOldCus").text(data.oldCusVisitNum);
	$("#vaildRate").text(data.validVisitRate);
	
	$("#todayPayCount").text(data.enterBuy);
	$("#todayPay").text(data.orderTotalCount);
	$("#checked").text(data.alreadyCheckOrderCount);
	
	$("#todaySignCount").text(data.signCount);
	$("#todayWaitSign").text(data.waitSignCount);
	$("#finishSign").text(data.finishSignCount);
}
function setGueidIndexData(data){
	$("#todayGueidCount").text(data.haveRecords);
	$("#gueidVisited").text(data.haveVisit);
	$("#gueidNoVisit").text(data.notVisit);
}