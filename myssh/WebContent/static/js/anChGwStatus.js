var todayDataArr = new Array();
var yestodayData = new Array();
$(document).ready(function(){
	getHeatMap();
	getAgentsData();
});
function getHeatMap(){
	$.ajax({
        type: "POST",
        url:"get_twodays_visit_data",
        //async: false,
        success: function(data) {
        	setHeatMapData(data);
        	heatMap();
        },
        /*error: function(request) {
        	alert("数据获取失败......");
        },*/
    });
}
function setHeatMapData(data){
	//console.log(data);
	var yesTodayData = data.oldChart;
	var todayData = data.chart;
	var yesToday_x = data.yesterdayWorkTime;
	var today_x = data.x_axis;
	$.each(yesToday_x,function(v, o) {
		//console.log(yesTodayData[o]);
		//yestodayData.push(yesTodayData[o]);
	});
	//console.log(yestodayData);
	$.each(today_x,function(v, o) {
		//console.log(todayData[o]);
		todayDataArr.push(todayData[o]);
	});
	//console.log(todayDataArr);
}

function getAgentsData(){
	var sortSign = $("#orderKey").val();
	var sortSignStr = "";
	if(sortSign==2){
		sortSignStr = "receptTime";
	}else if(sortSign==1){
		sortSignStr = "timeLonger";
	}else{
		sortSignStr = "agent";
	}
	$.ajax({
        type: "POST",
        url:"get_today_agent_status_data",
        //async: false,
        data:{sortSign:sortSignStr},
        success: function(data) {
        	setAgentsData(data,sortSign);
        },
        /*error: function(request) {
        	alert("数据获取失败......");
        },*/
    });
}
function setAgentsData(data,sortSign){
	data = data.data;
	console.log(data);
	var s = "";
	$.each(data,function(v, o) {
		//console.log(o);
		s += '<table onclick=getThisAgentInfo("'+o.userId+'")><tbody><tr>';
		if(o.photo!=null && o.photo!=""){
			s += '<td rowspan="2"><img src="'+o.photo+'" alt=""><p class="nameCss">'+o.name+'</p><p class="xinxiCss">'+o.stutas+'</p></td>';
		}else{
			s += '<td rowspan="2"><img src="static/images/touPic.png" alt=""><p class="nameCss">'+o.name+'</p><p class="xinxiCss">'+o.stutas+'</p></td>';
		}
		
		s += '<td><p class="line1">来访时间</p><p class="line2">'+o.comeVisitTime+'</p></td>';
		s += '<td><p class="line1">接访时长</p><p class="line2">'+o.receptTimeLonger+'</p></td>';
		s += '<td><p class="line1">来访人数</p><p class="line2">'+o.comeCusCount+'</p></td>';
		s += '</tr><tr>';
		s += '<td><p class="line1">客户通道</p><p class="line2">'+o.cusPassageWay+'</p></td>';
		s += '<td><p class="line1">是否指定接访</p><p class="line2">'+o.isAppoint+'</p></td>';
		s += '<td><p class="line1">是否登记</p><p class="line2">'+o.isWrite+'</p></td>';
		s += '</tr></tbody></table>';
	});
	if (data.length > 0) {
		if(sortSign==2){
			$("#receptTime").html("");
			$("#receptTime").html(s);
		}else if(sortSign==1){
			$("#timeLonger").html("");
			$("#timeLonger").html(s);
		}else{
			$("#agent").html("");
			$("#agent").html(s);
		}
	} else {
		if(sortSign==2){
			$("#receptTime").html('<img style="width:2rem;height:2rem;" src="static/images/appnoMessage.png">');
		}else if(sortSign==1){
			$("#timeLonger").html('<img style="width:2rem;height:2rem;" src="static/images/appnoMessage.png">');
		}else{
			$("#agent").html('<img style="width:2rem;height:2rem;" src="static/images/appnoMessage.png">');
		}
	}
}
function getThisAgentInfo(userId){
	window.location.href = "to_agent_info_page?userId="+userId;
}