$(document).ready(function(){
	getGwStstusDeatilsData();
});
function getGwStstusDeatilsData(){
	$.ajax({
        type: "post",
       // dataType:"json",
        url:"get_agent_status_data",
        async: false,
        data:{startDate:starTime , endDate:endTime,userId:$("#userId").val()},
        success: function(data) {
        	setGwStstusDeatilsData(data);
        	canlenderAndMyChart();
        },
        /*error: function(request) {
        	alert("数据获取失败......");
        },*/
    });
}
function setGwStstusDeatilsData(data){
	if(data.agentPhoto!=null && data.agentPhoto!=""){
		$("#agentPhoto").attr("src",data.agentPhoto);
	}else{
		$("#agentPhoto").attr("src","static/images/touPic.png");
	}
	$("#agentName").text("");
	$("#agentName").text(data.agentName);
	$("#agentStutas").text(data.status);
	$("#totalVisit").text(data.totalVisit);
	$("#vaildVisitRate").text(data.validRate);
	if(data.validRateCompare>0){
		$("#validVisitImg").attr("src","static/images/upPic.png");
	}else if(data.validRateCompare<0){
		$("#validVisitImg").attr("src","static/images/downPic.png");
	}else{
		$("#validVisitImg").remove();
	}
	$("#totalSave").text(data.totalSaveCusCount);
	$("#cusComeBack").text(data.secondRate);
	if(data.secondRateCompare>0){
		$("#comeBackImg").attr("src=","static/images/upPic.png");
	}else if(data.secondRateCompare<0){
		$("#comeBackImg").attr("src","static/images/downPic.png");
	}else{
		$("#comeBackImg").remove();
	}
	$("#oldCusCount").text(data.newAddCusCount);
	$("#newAdd").text(data.secondCount);
	$("#enterBuyGetMoney").text(data.getMoney);
	$("#paySignRate").text(data.paySignRate);
	if(data.paySignRateCompare>0){
		$("#cPaySignRate").attr("src=","static/images/upPic.png");
	}else if(data.paySignRateCompare<0){
		$("#cPaySignRate").attr("src","static/images/downPic.png");
	}else{
		$("#cPaySignRate").remove();
	}
	$("#unValidVisit").val(data.unvalid);
	$("#oldCusVisit").val(data.oleCus);
	$("#newCusVisit").val(data.newCus);
	$("#signed").val(data.sign);
	$("#payMoney").val(data.areadlyPayCount);
	$("#waitSign").val(data.waitSign);
	
}