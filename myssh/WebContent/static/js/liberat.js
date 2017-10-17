$(document).ready(function(){
	getVisitData();
});
function getVisitData(){
	$.ajax({
        type: "POST",
        url:"get_toDay_detail_Receive_data",
        async: false,
        data:{startDate:starTime , endDate:endTime},
        success: function(data) {
        	setVisitData(data);
        	canlenderAndMyChart();
        },
        error: function(request) {
        	$.ajax({
    	        type: "post",
    	       // dataType:"json",
    	        url:"form_userToken_to_my_task_page",
    	        async: false,
    	        data:{userToken:$("#cookie").val(),roleId:"7"},
    	        success: function(data) {
    	        	if(data.returenCode==200){
    	        		window.location.href = data.skipURL;
    	        	}else{
    	        		window.location.href = "director_login";
    	        	}
    	        }
    	    });
        },
    });
}
function setVisitData(data){
	$("#timeLongs").text(data.averrageTime+"分钟");
	$("#visitTotalCount").text(data.recCount);
	$("#vaildRate").text(data.validRate);
	if(data.hasOwnProperty("grVisitedRate")){
		$("#gueidVisitRate").text(data.grVisitedRate);
	}else{
		$("#gueidVisitRate").text("");
	}
	$("#oldCusRate").text(data.oldRate);
	$("#replaceRate").text(data.replaceRate);
	$("#systemRate").text(data.systemRate);
	$("#valid").val(data.valid);
	$("#unValid").val(data.unValid);
}