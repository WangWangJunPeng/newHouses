var canClick = true;
var firstArray = [];
var secondArray = [];
$(document).ready(function() {
	putcooking();
	keepLogin();
	receptionTime();

});

function selectVistShun() {
	$(".selectPho").hide();
	$(".conError").hide();
	$(".vistConfir").show();
	//确认到访不能点击
	$('.conVist').attr('disabled',"true");
	$('.conVist').css({"background":"#ddd"});
	
	$('#knowAgentName').html($('#chaxunagentName').html());
	$('#knowAgentId').val($('#chaxunAgentId').html());
	$('#konwAgentPhoto').attr("src","http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png");
	$(".secBtn").css({"color":"#ffffff","background":"#2db1ef"});
	$('#jieshoujiefangqueren').val("false");
	//getRankingList($('#proId').val());
	getShunxujiefangFirst($('#proId').val());
}

//设置
function showLogin(){
	document.getElementById('set').style.display = "block";
}

function closeLogin(){
	document.getElementById('set').style.display = "none";
}

//考勤
function showKq(){
	$('.kq').show();
	
}

function closeKq(){
	$('.kq').hide();
}

function closeKqContent(){
	$('.kq_content').hide();
}

//到访客户
function showDaoFang() {
	$('.getVist').show();
	$('.getway').show();
}

function showFirst(target) {
	$('.getway').hide();
	$('.vistway').show();
	$('#jieshoujiefangqueren').val($(target).val());
	$('#daofangchaxun').val($(target).val());
	$(".firBtn").css({"color":"#ffffff","background":"#2db1ef"});
	$(".secBtn").css({"color":"#333333","background":"transparent"});
}

function showSecond(target) {
	$('.getway').hide();
	$('.vistway').show();
	$('#jieshoujiefangqueren').val($(target).val());
	$('#daofangchaxun').val($(target).val());
	$(".secBtn").css({"color":"#ffffff","background":"#2db1ef"});
	$(".firBtn").css({"color":"#333333","background":"transparent"});
}

function showSelect() {
	$('.getway').hide()
	$('.selectPho').show()
	$('#secondIn').html("");
	$('#chaxunagentName').html("");
	$('#chaxunjiefangTime').html("");
	$('#chauxncustomerName').html("");
	$('#chauxndaofangNum').html("");
	$('#chaxunAgentId').html("");
	$('#chaxunCustomerPhone').html("");
	$('#chaxunCustomerId').val("");
	$('#chaxunagentPhoto').attr("src","http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png");
	//确认到访不能点击
	$('.select_btn').addClass('hide');
	$('.delete').show();
	$('.ok').show();
	
	firstArray = [];
 	secondArray = [];
}

function closeDaoFang() {
	firstArray = [];
 	secondArray = [];
	getAllClose();
	$('#knowAgentId').html("");
	//贵宾到访确认
	$('#jiefangzhaopian').attr("src","http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png");
	$('#jiefangZYName').html("");
	$('#jiefangNowTime').html("");
	$('#jiefangzhuangtai').html("");
	$('#receptionAgentCommit').html("");
	//查询电话
	$('#chaxunagentPhoto').attr("src","http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png");
	$('#chaxunagentName').html("");
	$('#chaxunjiefangTime').val("");
	$('#chauxncustomerName').html("");
	$('#chauxndaofangNum').html("");
	$('#chaxunAgentId').val("");
	$('#chaxunCustomerPhone').val("");
	$('#chaxunshoucidanfangtime').html("");
	$('#chaxunmocidaofangtime').html("");
	$('#chaxunjiefangStatus').html("");
	$('#chaxunCustomerId').val("");
	
	$('#jieshoujiefangqueren').val("");
	
	
}

function closeLeaveCon() {
	$(".leave_confirm").hide();
}

//顺序接访
function shunXv() {
	canClick = true
	$(".vistway").hide();
	$(".conError").hide();
	$(".vistConfir").show();
	//确认到访不能点击
	$('.conVist').attr('disabled',"true");
	$('.conVist').css({"background":"#ddd"});
	
	$('#knowAgentName').html("");
	$('#knowAgentId').val("");
	$('#konwAgentPhoto').attr("src","http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png");
	
//	getRankingList($('#proId').val());
	
	getShunxujiefangFirst($('#proId').val());
}

function getShunxujiefangFirst(obj){
	$.ajax({
		type:"post",
		async : false,
		url:"all_agent_queueUp",
		data:{projectId:obj
		},
		success:function(data){
			getfirstRangingAgentList(data);
			//getRankingList($('#proId').val());
		},
	});
}
function getfirstRangingAgentList(data){
	
	var date2 = getDay(0);
	$('#jiefangzhaopian').attr("src","http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png");
	$('#jiefangZYName').html("");
	$('#jiefangNowTime').html("");
	$('#jiefangzhuangtai').html("");
	$('#receptionAgentCommit').val("");
	var i = 1;
	if(data.length>0){
		$.each(data,function(v,o){
			if (i == 1){
				if (o.photo != null && o.photo != ""){
					$('#jiefangzhaopian').attr("src",o.photo);
				}else {
					$('#jiefangzhaopian').attr("src","http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png");
				}
				$('#jiefangZYName').html(o.name);
				$('#jiefangNowTime').html(date2);
				$('#jiefangzhuangtai').html("排队中");
				$('#receptionAgentCommit').val(o.id);
			}
			i ++;
		});
	}
}


function againFirstVisit(obj){
	$('#jieshoujiefangqueren').val($(obj).val());
	$(obj).css({"color":"#ffffff","background":"#2db1ef"});
	$(".secBtn").css({"color":"#333333","background":"transparent"});
}
function againSecondVisit(obj){
	$('#jieshoujiefangqueren').val($(obj).val());
	$(obj).css({"color":"#ffffff","background":"#2db1ef"});
	$(".firBtn").css({"color":"#333333","background":"transparent"});
}

//认识的置业顾问
function showKnowCus(){
	$('.seceltCus').show();
	getNewAllAgentListPage();
}

function closeKnowCus(){
	$('.seceltCus').hide();
}

//选择人数
function clickNum(target) {
	if (canClick){
		$(target).css({"color":"#ffffff","background":"#2db1ef"}).parent().siblings().children().css({"color":"#676767","background":"#e5e5e5"});
    	$("#danfangrenshu").val($(target).html());
    	$('.conVist').css({"background":"#2db1ef"});
    	$('.conVist').css({"color":"#fff"});
    	$('.conVist').removeAttr("disabled");
    	$('.ok').css({"background":"#2db1ef"});
    	$('.delete').css({"background":"#2db1ef"});
    	$('.ok').css({"color":"#fff"});
    	$('.delete').css({"color":"#fff"});
	}
}

//电话号码
function clickMM(target){
	 clickNum(target);
	 secondArray.push($(target).html());
	 $("#secondIn").html(secondArray.join(""))
	 
}

function clickNN(target){
	firstArray.push($(target).html());
	$("#firstIn").html(firstArray.join(""))
}

function deleteAll(){
	$("#firstIn").html("");
	$("#secondIn").html("");
	firstArray = [];
	secondArray = [];
}

function searching(){
	if ($('#secondIn').html() != null && $('#secondIn').html() != ""){
		var check = checkMobile($('#secondIn').html());
		
		if (check) {
			toGetOneCusInfo();
		}
	} 
}

//换人
function changeVist() {
	$('.vistConfir').hide();
	$('.changeCus').show();
	
	toGetRankingChangeAgent($("#proId").val());
	toGetjiefangzhongChangeAgent($("#proId").val());
	
}
function toGetRankingChangeAgent(obj){
	$.ajax({
		type:"post",
		async : false,
		url:"agent_queueUp",
		data:{projectId:obj,
			curentPage : currentPage10,
			limit : 4,
			//checkTeam : 0,
		},
		success:function(data){
			//console.log(data.root);
			getChangeAllRangingAgentList(data.root);
			$('#changerangkingzongyeshu').val(data.totalPage);
			//judgePage(data,$(".pre1"),$('.next1'),currentPage3);
		},
	});
}
function getChangeAllRangingAgentList(data){
	$('#huanrenrangkingTwo').html("");
	var s = '';
	$.each(data,function(v,o){
		s += '<div class="col-md-3"><div class="cus" onclick="changezdCus(this)" data-value="'+o.name+'" data-id="'+o.id+'" data-src="'+o.photo+'" data-status="'+o.stutas+'"><div class="chooseimg pull-left">';
		if (o.photo != null && o.photo != ""){
			s += '<img src="'+o.photo+'" alt="">';
		}else {
			s += '<img src="http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png" alt="">';
		}
		s += '</div><div class="midBox pull-left">';
		if (o.name != null && o.name != ""){
			s += '<p class="name"><img src="static/images/userleft.png" alt="">'+o.name+'</p>';
		}else {
			s += '<p class="name"><img src="static/images/userleft.png" alt="">暂无</p>';
		}
			
		s += '<p class="phone"><img src="static/images/icon_Tel.png" alt="">'+o.phone+'</p>';
		s += '<p class="group"><img src="static/images/icon_edit.png" alt="">';
		if (o.groupName == "1" || o.groupName == null || o.groupName == ""){
			s += '销售一组';
		}else if (o.groupName == "2"){
			s += '销售二组';
		}	
		s += '</p></div><div class="clearfix"></div></div></div>';	
	});
	if(data.length>0){
		$('#huanrenrangkingTwo').html(s);
	}
}
function changezdCus(obj){
	var date2 = getDay(0);
	$(".changeCus ").hide();
	$(".conError").hide();
	$(".vistConfir").show();
	/*if ($(obj).data("src") != null && $(obj).data("src") != ""){
		$('#konwAgentPhoto').attr("src",$(obj).data("src"));
	}else {
		$('#konwAgentPhoto').attr("src","http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png");
	}*/
	
	$('#jiefangZYName').html($(obj).data("value"));
	$('#jiefangNowTime').html(date2);
	$('#jiefangzhuangtai').html("排队中");
	$('#receptionAgentCommit').val($(obj).data("id"));
	if ($(obj).data("src") != null && $(obj).data("src") != ""){
		$('#jiefangzhaopian').attr("src",$(obj).data("src"));
	}else {
		$('#jiefangzhaopian').attr("src","http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png");
	}
	//$('#knowAgentName').html($(obj).data("value"));
	//$('#knowAgentId').val($(obj).data("id"));
}
function toGetjiefangzhongChangeAgent(obj){
	$.ajax({
		type:"post",
		async : false,
		url:"agent_receivIng",
		data:{projectId:obj,
			curentPage : currentPage11,
			limit : 4,
			//checkTeam : 0,
		},
		success:function(data){
			getChangejiefangzhongAgentList(data.root);
			$('#changezuozejiefangzongyeshu').val(data.totalPage);
			//judgePage(data,$(".pre1"),$('.next1'),currentPage3);
		},
	});
}
function getChangejiefangzhongAgentList(data){
	$('#huanrenjiefangTwo').html("");
	var s = '';
	$.each(data,function(v,o){
		s += '<div class="col-md-3"><div class="cus" onclick="changejiefangzhongCus(this)" data-value="'+o.name+'" data-id="'+o.id+'" data-src="'+o.photo+'" data-status="'+o.stutas+'"><div class="chooseimg pull-left">';
		if (o.photo != null && o.photo != ""){
			s += '<img src="'+o.photo+'" alt="">';
		}else {
			s += '<img src="http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png" alt="">';
		}
		s += '</div><div class="midBox pull-left">';
		if (o.name != null && o.name != ""){
			s += '<p class="name"><img src="static/images/userleft.png" alt="">'+o.name+'</p>';
		}else {
			s += '<p class="name"><img src="static/images/userleft.png" alt="">暂无</p>';
		}
		
		s += '<p class="phone"><img src="static/images/icon_Tel.png" alt="">'+o.phone+'</p>';
		s += '<p class="group"><img src="static/images/icon_edit.png" alt="">';
		if (o.groupName == "1" || o.groupName == null || o.groupName == ""){
			s += '销售一组';
		}else if (o.groupName == "2"){
			s += '销售二组';
		}	
		s += '</p></div><div class="clearfix"></div></div></div>';	
	});
	if(data.length>0){
		$('#huanrenjiefangTwo').html(s);
	}
}

function changejiefangzhongCus(obj){
	var date2 = getDay(0);
	$(".changeCus ").hide();
	$(".conError").hide();
	$(".vistConfir").show();
	/*if ($(obj).data("src") != null && $(obj).data("src") != ""){
		$('#konwAgentPhoto').attr("src",$(obj).data("src"));
	}else {
		$('#konwAgentPhoto').attr("src","http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png");
	}*/
	
	$('#jiefangZYName').html($(obj).data("value"));
	$('#jiefangNowTime').html(date2);
	$('#jiefangzhuangtai').html("接访中");
	$('#receptionAgentCommit').val($(obj).data("id"));
	if ($(obj).data("src") != null && $(obj).data("src") != ""){
		$('#jiefangzhaopian').attr("src",$(obj).data("src"));
	}else {
		$('#jiefangzhaopian').attr("src","http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png");
	}
	//$('#knowAgentName').html($(obj).data("value"));
	//$('#knowAgentId').val($(obj).data("id"));
}

function getChangeRangBeforePage(){
	if (currentPage10>1){
		currentPage10 = currentPage10-1;
		toGetRankingChangeAgent($("#proId").val());
	}
}
function getChangeRangNextPage(){
	if (currentPage10 < $('#changerangkingzongyeshu').val()){
		currentPage10 = currentPage10 +1;
		toGetRankingChangeAgent($("#proId").val());
	}
}
function getChangeReceptionBeforePage(){
	if (currentPage11>1){
		currentPage11 = currentPage11-1;
		toGetjiefangzhongChangeAgent($("#proId").val());
	}
}
function getChangeReceptionNextPage(){
	if (currentPage11 < $('#changezuozejiefangzongyeshu').val()){
		currentPage11 = currentPage11 +1;
		toGetjiefangzhongChangeAgent($("#proId").val());
	}
}

function closeChangeCus() {
	$('.vistConfir').show();
	$('.changeCus').hide();
}

//刷新
function refreshLogin() {
	closeKq();
	closeDaoFang();
	closeKqContent();
	
	
	var token = getCookie("userToken");
	$.ajax({
		  type:"post",
		  async : false,
		  url:"base.login_by_token",
		  data:{userToken:token,
		  },
		  beforeSend: function () {
	   			$('.loading').show();
	   	    },
	   	 success:function(data){
	   		$('.loading').hide();
	   		 data = eval("("+data+")");
	   		 if (data.ReturnCode == 200){
	   			 //已登录
//	   			$('.set').hide();
	   			closeLogin();
	   			
	   		 $("#proId").val(data.User.parentId);
	   			 getAllAgentList(data.User.parentId);
	   			 getAgentList();
	   			 //排队
	   			 getRankingList(data.User.parentId);
	   			 //接待
	   			 getAllReceptionAgent(data.User.parentId);
	   			 getShouYeAllReceptionAgent(data.User.parentId);
	   			 getshouyeAllSongBieAgent(data.User.parentId);
	   			 setCookie("userToken",data.token); 
	   		 }else {
	   			 //重新登录
//	   			$('.set').show();
	   			showLogin();
	   		 }
	   	 },
	   	complete: function () {
	   		$('.loading').hide();
	    },
	    error: function (data) {
	    	$('.loading').hide();
	    }
	   });
}
//保持登录
function keepLogin() {
	var token = getCookie("userToken");
	t = setTimeout ('receptionTime()',900000);
	
	if (token != null && token != "") {
		$.ajax({
	 		  type:"post",
	 		  async : false,
	 		  url:"base.login_by_token",
	 		  data:{userToken:token,
	 		  },
	 		 beforeSend: function () {
		   			$('.loading').show();
		   	    },
	 	   	 success:function(data){
	 	   		$('.loading').hide();
	 	   		 data = eval("("+data+")");
	 	   		 if (data.ReturnCode == 200){
	 	   			 //已登录
//	 	   			$('.set').hide()
	 	   			closeLogin();
	 	   		 $("#proId").val(data.User.parentId);
		   			 getAllAgentList(data.User.parentId);
		   			 getAgentList();
		   			 //排队
		   			 getRankingList(data.User.parentId);
		   			 //接待
		   			 getAllReceptionAgent(data.User.parentId);
		   			 getShouYeAllReceptionAgent(data.User.parentId);
		   			 getshouyeAllSongBieAgent(data.User.parentId);
		   			 setCookie("userToken",data.token); 
	 	   		 }else {
	 	   			 //重新登录
//	 	   			$('.set').show();
	 	   		showLogin();
	 	   		 }
	 	   	 },
	 	   	complete: function () {
		   		$('.loading').hide();
		    },
		    error: function (data) {
		    	$('.loading').hide();
		    }
	 	   });
	}else {
//		$('.set').show();
		showLogin();
	}
	
}

var allArriveTime = new Array();
//接待_________________________
function clickJd(obj) {
	$(obj).css("background-color","#2db1ef").parent().siblings().children().css("background-color","#fff");
	$('.delete').show();
	$('.ok').show();
	
	var leftPho = $(obj).children('.top').children('.leftPho');
	console.log(leftPho);
	$('.leftPho').attr("src","static/images/icon_times_disabled.png");
	$('.havePhone').attr("src","static/images/icon_times.png");
	leftPho.attr("src","static/images/Tel_presssed.png");
	
	var rightTop = $(obj).children('.top').children('.rightTop');
	$('.rightTop').css("background-color","#2db1ef");
	$('.rightTop').css("color","#fff");
	$('.rightTop>img').attr("src","static/images/user.png");
	rightTop.css("color","#2db1ef");
	rightTop.css("background-color","#fff");
	rightTop.children('img').attr("src","static/images/user_pressed.png");

	var info = $(obj).children('.info');
	$('.info').css("color","#2e445d");
	$('.reTime').css("color","#2db1ef");
	info.css("color","#fff");
	info.children('.info_content').children('p').children('.reTime').css("color","#fff");
	
	
//	$('.name1').attr("src","static/images/userleft.png");
//	$('.time1').children('img').attr("src","static/images/icon_time.png");
//	info.children('.info_content').children('p').children('name1').attr("src","static/images/userleft_preesed.png");	
//	info.children('.info_content').children('p').children('time1').attr("src","static/images/icon_Tel_presssed.png");


	$('.leaveBtn').show();
	$('.leave').show();
	
	//$('#shouyejiedaicustomerName').html($(target).data("agentName"));
	//顾客电话回显
	$('#firstIn').html($(obj).data("phone"));
	
	getOneAgentVistInfo($(obj).data("visit"));
}
//获取置业顾问信息
function getOneAgentVistInfo(obj){
	$('#visitNo').val("");
	$('#shouyejiedaiagentName').html("");
	$('#jiedaishijian').html("");
	$('#shouyedaofangcishu').html("");
	$('#agentPhoto').attr("src","");
	
	$.ajax({
		type:"post",
		async : false,
		url:"agent_current_recession_record_detail",
		data:{visitNo:obj,
		},
		beforeSend: function () {
   			$('.loading').show();
   	    },
		success:function(data){
			$('.loading').hide();
			//$('#jiedaiagentid').val($(obj).data("id"));
			$('#visitNo').val(obj);
			if (data.agentName != null && data.agentName != "") {
				$('#shouyejiedaiagentName').html(data.agentName);
			}else {
				$('#shouyejiedaiagentName').html("暂无");
			}
			$('#jiedaishijian').html(data.receptTime);
			if (data.agentPhoto!= null && data.agentPhoto != ""){
				$('#agentPhoto').attr("src",data.agentPhoto);
			}else {
				$('#agentPhoto').attr("src","http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png");
			}
			$('#shouyedaofangcishu').html(data.visitCount);
			$('#shouyejiedaicustomerName').html(data.cusName);
//			console.log(data.leaveTime);
//			console.log(data.writeState);
//			console.log(data.checkWay);
			if (data.leaveTime != null || data.writeState == 1 || data.checkWay == 1 || data.phone != null){
				$('.delete').hide();
				$('.ok').hide();
			}
			
		},
		complete: function () {
	   		$('.loading').hide();
	    },
	    error: function (data) {
	    	$('.loading').hide();
	    }
	});
}

//送别_____________
function clickLeave(obj) {
	$(obj).css("background-color","#ffa45a").parent().siblings().children().css("background-color","#fff");
	$('.delete').show();
	$('.ok').show();
	var leftPho = $(obj).children('.top').children('.leftPho2');
	$('.leftPho2').attr("src","static/images/icon_tel_leave.png");
	$('.havePhone').attr("src","static/images/icon_times.png");
	leftPho.attr("src","static/images/Tel_presssed.png");
	leftPho.src = "images/icon_Tel_presssed.png";
	
	var rightTop = $(obj).children('.top').children('.rightTop2');
	$('.rightTop2').css("background-color","#ffa45a");
	$('.rightTop2').css("color","#fff");
	$('.rightTop2>img').attr("src","static/images/user.png");
	rightTop.css("color","#ffa45a");
	rightTop.css("background-color","#fff");
	rightTop.children('img').attr("src","static/images/left_user_pressed.png");
                                                                             
	var info = $(obj).children('.info2');
	$('.info2').css("color","#2e445d");
	$('.info2>.info_content>p>span').css("color","#2e445d");                                                                                                                                           
	$('.getTime2>span').css("color","#ffa45a");
	info.css("color","#fff");
	info.children('.info_content').children('p').children('span').css("color","#fff");

	$('.leaveBtn').hide();
	$('.leave').show();
	
	/*$('#visitNo').val("");
	$('#shouyejiedaiagentName').html("");
	$('#jiedaishijian').html("");
	$('#shouyedaofangcishu').html("");
	$('#agentPhoto').attr("src","");
	
	$('#visitNo').val(obj);
	$('#shouyejiedaiagentName').html(data.agentName);
	$('#jiedaishijian').html(data.receptTime);
	if ($(data.agentPhoto) != null && $(data.agentPhoto) != ""){
		$('#agentPhoto').attr("src",data.agentPhoto);
	}else {
		$('#agentPhoto').attr("src","http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png");
	}
	$('#shouyedaofangcishu').html(data.visitCount);*/
	//顾客电话回显
	$('#firstIn').html($(obj).data("phone"));
	getOneAgentByeInfo($(obj).data("visit"));
}

function getOneAgentByeInfo(obj){
	$('#visitNo').val("");
	$('#shouyejiedaiagentName').html("");
	$('#jiedaishijian').html("");
	$('#shouyedaofangcishu').html("");
	$('#agentPhoto').attr("src","");
	
	$.ajax({
		type:"post",
		async : false,
		url:"agent_current_recession_record_detail",
		data:{visitNo:obj,
		},
		beforeSend: function () {
   			$('.loading').show();
   	    },
		success:function(data){
			$('.loading').hide();
			//$('#jiedaiagentid').val($(obj).data("id"));
			$('#visitNo').val(obj);
			//$('#shouyejiedaiagentName').html(data.agentName);
			if (data.agentName != null && data.agentName != "") {
				$('#shouyejiedaiagentName').html(data.agentName);
			}else {
				$('#shouyejiedaiagentName').html("暂无");
			}
			$('#jiedaishijian').html(data.receptTime);
			if (data.agentPhoto != null && data.agentPhoto != ""){
				$('#agentPhoto').attr("src",data.agentPhoto);
			}else {
				$('#agentPhoto').attr("src","http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png");
			}
			$('#shouyedaofangcishu').html(data.visitCount);
			$('#shouyejiedaicustomerName').html(data.cusName);
			if (data.leaveTime != null || data.writeState == 1 || data.checkWay == 1 || data.phone != null){
				$('.delete').hide();
				$('.ok').hide();
			}
			
		},
		complete: function () {
	   		$('.loading').hide();
	    },
	    error: function (data) {
	    	$('.loading').hide();
	    }
	});
}

function getAllClose(){
	$(".getway").show()
	$(".getVist").hide()
	$(".vistway").hide()
	$(".chooseCus").hide()
	$(".vistConfir").hide()
	$('.selectPho').hide()
	$('.leave').hide()
	$(".seceltCus").hide()
	$(".chooseCus").hide()
	
 	$('#firstIn').html("");
 	$('#secondIn').html("");

 	$('#chaxunCustomerId').val("");
 	
 	
 	
 	//数字清空,号码,人数
 	$(".Num").css({"color":"#676767","background":"#e5e5e5"});
	currentPage1 = 1;
	currentPage2 = 1;
	currentPage3 = 1;
	currentPage4 = 1;
	currentPage5 = 1;
	currentPage6 = 1;
	currentPage7 = 1;
	currentPage8 = 1;
	currentPage9 = 1;
	currentPage10 = 1;
	currentPage11 = 1;
	
	
}
//登录______________________
function getLogin(){
	
	//requestFullScreen();
	$.ajax({
   		  type:"post",
   		  async : false,
   		  url:"new_base.login",
   		  //cache:false,
   		  data:{loginName:$('#exampleInputEmail1').val(),
   			  Password:$('#exampleInputPassword1').val(),
   		  },
   		beforeSend: function () {
   			$('.loading').show();
   	    },
   	   	 success:function(data){
   	   		 data = eval("("+data+")");
   	   		 $(".loading").hide();
   	   		 if (data.ReturnCode == 200){
   	   			 $("#proId").val(data.User.parentId);
   	   			 
 	   			 getAllAgentList(data.User.parentId);
   	   			 getAgentList();
   	   			 getRankingList(data.User.parentId);
   	   			 getAllReceptionAgent(data.User.parentId);
   	   			 getShouYeAllReceptionAgent(data.User.parentId);
   	   			 getshouyeAllSongBieAgent(data.User.parentId);
  	   		
   	   			 intcooking();
   	   			 setCookie("userToken",data.token); 
   	   			 $('#yanzheng').html("");
   	   			 closeLogin();
   	   			
   	   			 
   	   		 }else {
   	   			 //用户不存在
   	   			 $('#yanzheng').html(data.User);
   	   		 }
   	   	 },
//   	  complete: function () {
//	   		$('.loading').hide();
//	    },
//	    error: function (data) {
//	    	$('.loading').hide();
//	    }
   	   });
}
//首页送别置业顾问列表_____________________________
function getshouyeAllSongBieAgent(obj){
	$.ajax({
		type:"post",
		async : false,
		url:"agent_farewelled",
		data:{projectId:obj,
			curentPage : currentPage8,
			limit : 12,
		},
		beforeSend: function () {
   			$('.loading').show();
   	    },
		success:function(data){
			$('.loading').hide();
			$('#shouyesongbiezongpage').val(data.totalPage);
			getShouYeAllSongBieAgentList(data.root);
			judgePage(data,$(".pre4"),$('.next4'),currentPage8);
		},
		complete: function () {
	   		$('.loading').hide();
	    },
	    error: function (data) {
	    	$('.loading').hide();
	    }
	});
}
function getShouYeAllSongBieAgentList(data){
	
	$('#shouyeyisongbie').html("");
	var s = '';
	$.each(data,function(v,o){
		s += '<div class="col-md-2"><div class="content2" onclick="clickLeave(this)" data-agent="'+o.name+'" data-id="'+o.userId+'" data-time="'+o.receptTime+'" data-src="'+o.photo+'" data-visit="'+o.visitNo+'" data-phone="'+o.phone+'" data-checkwey="'+o.checkWay+'" data-writeState="'+o.writeState+'" data-leaveTime="'+o.leaveTime+'"><div class="top">';
		if (o.phone != null && o.phone != ""){
			s += '<img class="pull-left leftPho havePhone" src="static/images/icon_times.png" alt="">';
		}else {
			s += '<img class="pull-left leftPho" src="static/images/icon_times_disabled.png" alt="">';
		}
		s += '<div class="rightTop2 pull-right"><img class="pull-left" src="static/images/user.png" alt="">';
		s += '<span class="pull-right">'+o.visitCount+'</span></div><div class="clearfix"></div></div>';
		s += '<div class="info2"><div class="infoImg">';
		if (o.photo != null && o.photo != ""){
			s += '<img src="'+o.photo+'" alt="">';
		}else {
			s += '<img src="http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png" alt="">';
		}	
		s += '</div><div class="info_content"><p class="conMsg">';
		s += '<img class="mark2" src="static/images/userright.png" alt="">';
		if (o.name != null && o.name != ""){
			s += '置业顾问：<span>'+o.name+'</span>';
		}else {
			s += '置业顾问：<span>暂无</span>';
		}
		s += '</p><p class="getTime2"><img class="mark2" src="static/images/icon_time.png" alt="">';
		var arr = Date.parse(o.arriveTime);
		var lea = Date.parse(o.leaveTime);
		var date3=lea-arr;  //时间差的毫秒数
		var leave1=date3%(24*3600*1000);    //计算天数后剩余的毫秒数
		var hours=Math.floor(leave1/(3600*1000));
		if (hours < 10){
			hours = "0"+hours;
		}
		var leave2=leave1%(3600*1000);        //计算小时数后剩余的毫秒数
		var minutes=Math.floor(leave2/(60*1000));
		if(minutes < 10){
			minutes = "0"+minutes;
		}
		var leave3=leave2%(60*1000);     //计算分钟数后剩余的毫秒数
		var seconds=Math.round(leave3/1000);
		if(seconds < 10){
			seconds = "0"+seconds;
		}
		s += '接待时长：<span>'+hours+":"+minutes+":"+seconds+'</span>';
		s += '</p></div></div></div></div>';			
		

	});
	if(data.length>0){
		$('#shouyeyisongbie').html(s);
	}
}
function shouyesongbieBeforePage(){
	if (currentPage8>1){
		currentPage8 = currentPage8-1;
		getshouyeAllSongBieAgent($("#proId").val());
	}
}
function shouyesongbienextPage(){
	if (currentPage8 < $('#shouyesongbiezongpage').val()){
		currentPage8 = currentPage8 +1;
		getshouyeAllSongBieAgent($("#proId").val());
	}
}

//首页接待置业顾问列表____________________________
function getShouYeAllReceptionAgent(obj){
	$.ajax({
		type:"post",
		async : false,
		url:"agent_recessionInVisitRecords",
		data:{projectId:obj,
			curentPage : currentPage7,
			limit : 12,
		},
		beforeSend: function () {
   			$('.loading').show();
   	    },
		success:function(data){
			$('.loading').hide();
			getShouYeAllReceptionAgentList(data.root);
			$('#shouyejiefangzongpage').val(data.totalPage);
			judgePage(data,$(".pre3"),$('.next3'),currentPage7);
		},
		complete: function () {
	   		$('.loading').hide();
	    },
	    error: function (data) {
	    	$('.loading').hide();
	    }
	});
}
function getShouYeAllReceptionAgentList(data){
	var nowTime = Date.parse(getDay(2));
	
	$('#shouyejiefangzhong').html("");
	var s = '';
	$.each(data,function(v,o){
		allArriveTime[v] = o.arriveTime;
		s += '<div class="col-md-2" ><div class="content" onclick="clickJd(this)" data-agent="'+o.name+'" data-id="'+o.userId+'" data-time="'+o.receptTime+'" data-src="'+o.photo+'" data-visit="'+o.visitNo+'" data-phone="'+o.phone+'" data-checkwey="'+o.checkWay+'" data-writeState="'+o.writeState+'" data-leaveTime="'+o.leaveTime+'"><div class="top">';
		if (o.phone != null && o.phone != ""){
			s += '<img class="pull-left leftPho havePhone" src="static/images/icon_times.png" alt=""><div class="rightTop pull-right">';
		}else {
			s += '<img class="pull-left leftPho" src="static/images/icon_times_disabled.png" alt=""><div class="rightTop pull-right">';
		}
		s += '<img class="pull-left" src="static/images/user.png" alt="">';
		s += '<span class="pull-right">'+o.visitCount+'</span></div><div class="clearfix"></div></div>';	
		s += '<div class="info"><div class="infoImg">';		
		if (o.photo != null && o.photo != ""){
			s += '<img src="'+o.photo+'" alt="">';
		}else {
			s += '<img src="http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png" alt="">';
		}				
		s += '</div><div class="info_content"><p>';
		s += '<img class="mark2 name1" src="static/images/userright.png" alt="">';
		if (o.name != null && o.name != ""){
			s += '置业顾问：<span>'+o.name+'</span>';
		}else {
			s += '置业顾问：<span>暂无</span>';
		}	
		s += '</p><p><img class="mark2 time1" src="static/images/icon_time.png" alt="">';	
		s += '接待时长：<span class="reTime"></span>';
		s += '</p></div></div></div></div>';
		
	});
	
	if(data.length>0){
		$('#shouyejiefangzhong').html(s);
	}
}
function shouyejiefangBeforePage(){
	if (currentPage7>1){
		currentPage7 = currentPage7-1;
		getShouYeAllReceptionAgent($("#proId").val());
	}
}
function shouyejiefangnextPage(){
	if (currentPage7 < $('#shouyejiefangzongpage').val()){
		currentPage7 = currentPage7 +1;
		getShouYeAllReceptionAgent($("#proId").val());
	}
}
function getNewAllAgentListPageTwo(){
	$.ajax({
		  type:"post",
		  async : false,
		  url:"project.getAgentPageList",
		  //dataType : "json",
		  data:{projectId:$("#proId").val(),
			 curentPage : currentPage5,
			limit : 6,
		  },
		  beforeSend: function () {
	   			$('.loading').show();
	   	    },
	   	 success:function(data){
	   		$('.loading').hide();
	   		 getNewAllAgentListShowTwo(data.root);
	   		 judgePage(data,$(".pre7"),$('.next7'),currentPage5);
	   		$('#knowzhidingzhiyeguwen').val(data.totalPage);
	   	 },
	   	complete: function () {
	   		$('.loading').hide();
	    },
	    error: function (data) {
	    	$('.loading').hide();
	    }
	   });
}
function getNewAllAgentListShowTwo(data){
	$('#knowzhidingjiefang').html("");
	var s = '';
	$.each(data,function(v,o){
		s += '<div class="col-md-4" onclick="getOneKnowAgentInfo(this)" data-id="'+o.userId+'" data-value="'+o.userCaption+'" data-photo="'+o.photo+'"><div class="cus select_cus"><div class="chooseimg pull-left">';
		if (o.photo != null && o.photo != ""){
			s += '<img src="'+o.photo+'" alt="">';
		}else {
			s += '<img src="http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png" alt="">';
		}	
		s += '</div><div class="midBox pull-left">';
		if (o.userCaption != null && o.userCaption != ""){
			s += '<p class="name"><img src="static/images/userleft.png" alt="">'+o.userCaption+'</p>';
		}else {
			s += '<p class="name"><img src="static/images/userleft.png" alt="">暂无</p>';
		}
		//s += '<p class="name"><img src="static/images/userleft.png" alt="">'+o.userCaption+'</p>';
		s += '<p class="phone"><img src="static/images/icon_Tel.png" alt="">'+o.phone+'</p>';
		s += '<p class="group"><img src="static/images/icon_edit.png" alt="">';
		if (o.groupName == "1" || o.groupName == null || o.groupName == ""){
			s += '销售一组';
		}else if (o.groupName == "2"){
			s += '销售二组';
		}	
		s += '</p></div><div class="clearfix"></div></div></div>';
	});
	if(data.length>0){
		$('#knowzhidingjiefang').html(s);
	}
}

function getNewAllAgentListPage(){
	$.ajax({
		  type:"post",
		  async : false,
		  url:"project.getAgentPageList",
		  //dataType : "json",
		  data:{projectId:$("#proId").val(),
			 curentPage : currentPage5,
			limit : 6,
		  },
		  beforeSend: function () {
	   			$('.loading').show();
	   	    },
	   	 success:function(data){
	   		$('.loading').hide()
	   		 $('#knowzhidingzhiyeguwen').val(data.totalPage);
	   		 getNewAllAgentListShow(data.root);
	   		judgePage(data,$(".pre7"),$('.next7'),currentPage5);
	   	 },
	   	complete: function () {
	   		$('.loading').hide();
	    },
	    error: function (data) {
	    	$('.loading').hide();
	    }
	   });
}
function getNewAllAgentListShow(data){
	$('#knowzhidingjiefang').html("");
	var s = '';
	$.each(data,function(v,o){
		s += '<div class="col-md-4" onclick="getOneKnowAgentInfo(this)" data-id="'+o.userId+'" data-value="'+o.userCaption+'" data-photo="'+o.photo+'"><div class="cus select_cus"><div class="chooseimg pull-left">';
		if (o.photo != null && o.photo != ""){
			s += '<img src="'+o.photo+'" alt="">';
		}else {
			s += '<img src="http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png" alt="">';
		}	
		s += '</div><div class="midBox pull-left">';
		if (o.userCaption != null && o.userCaption != ""){
			s += '<p class="name"><img src="static/images/userleft.png" alt="">'+o.userCaption+'</p>';
		}else {
			s += '<p class="name"><img src="static/images/userleft.png" alt="">暂无</p>';
		}
		//s += '<p class="name"><img src="static/images/userleft.png" alt="">'+o.userCaption+'</p>';
		s += '<p class="phone"><img src="static/images/icon_Tel.png" alt="">'+o.phone+'</p>';
		s += '<p class="group"><img src="static/images/icon_edit.png" alt="">';
		if (o.groupName == "1" || o.groupName == null || o.groupName == ""){
			s += '销售一组';
		}else if (o.groupName == "2"){
			s += '销售二组';
		}	
		s += '</p></div><div class="clearfix"></div></div></div>';
	});
	if(data.length>0){
		$('#knowzhidingjiefang').html(s);
	}
}
function getOneKnowAgentInfo(obj){
	var dateChaxun = getDay(0);
	if ($(obj).data("photo") != null && $(obj).data("photo") != ""){
		$('#konwAgentPhoto').attr("src",$(obj).data("photo"));
		$('#chaxunagentPhoto').attr("src",$(obj).data("photo"));
	}else {
		$('#konwAgentPhoto').attr("src","http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png");
		$('#chaxunagentPhoto').attr("src","http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png");
	}
	$('#knowAgentName').html($(obj).data("value"));
	$('#chaxunagentName').html($(obj).data("value"));
	$('#knowAgentId').val($(obj).data("id"));
	$('#chaxunAgentId').val($(obj).data("id"));
	//$('#receptionAgentCommit').val($(obj).data("id"));
	$('#chaxunjiefangTime').html(dateChaxun);
	
	
	$(".seceltCus").hide();
}
function knowAgentListBeforePage(){
	if (currentPage5>1){
		currentPage5 = currentPage5-1;
		getNewAllAgentListPage();
	}
}
function knowAgentListNextPage(){
	if (currentPage5 < $('#knowzhidingzhiyeguwen').val()){
		currentPage5 = currentPage5 +1;
		getNewAllAgentListPage();
	}
}



//分页参数设置
var limit = 5;
var limit2 = 3;
var limit3 = 4;
var type = 0;
var currentPage1 = 1;
var currentPage2 = 1;
var currentPage3 = 1;
var currentPage4 = 1;
var currentPage5 = 1;
var currentPage6 = 1;
var currentPage7 = 1;
var currentPage8 = 1;
var currentPage9 = 1;
var currentPage10 = 1;
var currentPage11 = 1;

function getAgentList(){
	 type = 0;
	$('#signAgentList').html("");
	$.ajax({
   		  type:"post",
   		  async : false,
   		  url:"new_project.getAgentList",
   		  data:{projectId:$("#proId").val(),
   			curentPage : currentPage1,
   			limit : 5
   		  },
   		beforeSend: function () {
   			$('.loading').show();
   	    },
   	   	 success:function(data){
   	   	$('.loading').hide();
   	   		 $('#qiantuizongyeshu').val(data.totalPage);
   	   		 getAgentListShow(data.root);
   	   		 judgePage(data,$(".pre5"),$('.next5'),currentPage1);
   	   	 },
   	  complete: function () {
	   		$('.loading').hide();
	    },
	    error: function (data) {
	    	$('.loading').hide();
	    }
   	   });
	
}
function getAgentListShow(data){
	$('#signAgentList').html("");
	var s = '';
	$.each(data,function(v,o){
		s += '<div class="kqMsg col-xs-12" onclick="clickKq(this)" data-value="'+o.proId+'"><div data-value="'+o.id+'" class="kq_imgBox pull-left">';
		if (o.photo != null && o.photo != ""){
			s += '<img src="'+o.photo+'" alt="">';
		}else {
			s += '<img src="http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png" alt="">';
		}
		s += '</div><div class="midBox pull-left"><p class="name">';
		if (o.name != null && o.name != ""){
			s += '<img src="static/images/userleft.png" alt=""><span class="zhiName">'+o.name+'</span>';
		}else {
			s += '<img src="static/images/userleft.png" alt=""><span class="zhiName">暂无</span>';
		}
		s += '</p><p class="phone"><img src="static/images/icon_Tel.png" alt="">'+o.phone;
		s += '</p><p class="group"><img src="static/images/icon_edit.png" alt="">';	
		if (o.groupName == "1" || o.groupName == null || o.groupName == ""){
			s += '销售一组';
		}else if (o.groupName == "2"){
			s += '销售二组';
		}
		s += '</p></div><div class="clearfix"></div>';
		s += '</div>';
	});
	if(data.length>0){
		$('#signAgentList').html(s);
	}
}
//考情________________________
function clickKq(obj){
	$('.kq_content').show();
	$('.kqMsg').css("background-color","#fff");
	$(obj).css("background-color","#2db1ef");
	$('#warning-block').hide();

	var name = $(obj).children('.midBox').children('.name');
	var phone = $(obj).children('.midBox').children('.phone');
	var group = $(obj).children('.midBox').children('.group');

	$('.name').children('img').attr("src","static/images/userleft.png");
	$('.phone').children('img').attr("src","static/images/icon_Tel.png");
	$('.group').children('img').attr("src","static/images/icon_edit.png");
	name.children('img').attr("src","static/images/userleft_preesed.png");
	phone.children('img').attr("src","static/images/icon_Tel_presssed.png");
	group.children('img').attr("src","static/images/icon_edit_pressed.png");
	
	var date = getDay(0);
	$('#agentSignTime').text(date);	
	var srcPath = $(obj).children(".kq_imgBox").children().attr("src");
	var zhiYe = $(obj).children(".midBox").children(".name").children(".zhiName").html();
	
	$('#signPic').attr("src",srcPath);
	$('#agentName').html(zhiYe);
	
	$("#agentProjectId").val($(obj).data("value"));
	$("#agentUserId").val($(obj).children(".kq_imgBox").data("value"));
}
//签到________________________________
function getSignConfirmation(){
	var date = getDay(2);
	$.ajax({
 		  type:"post",
 		  async : false,
 		  url:"new_project.sign",
 		  data:{type:type,
 			 projectId : $('#agentProjectId').val(),
 			userId : $('#agentUserId').val(),
 			time : date,
 			//checkTeam : 0,
 		  },
 		 beforeSend: function () {
    			$('.loading').show();
    	    },
 	   	 success:function(data){
 	   		$('.loading').hide();
 	   		 //data = eval("("+data+")");
 	   		 if (data.returncode == 200){
 	   			 currentPage1 = 1;
 	   			 if (type == 0){
 	   				 getAgentList();
 	   				 getRankingList($("#proId").val());
 	   				getAllReceptionAgent($("#proId").val());
 	   			 }else {
 	   				 getQianTuiList();
 	   				 getRankingList($("#proId").val());
 	   				getAllReceptionAgent($("#proId").val());
 	   			 }
 	   			 $('.kq_content').hide();
 	   			 $('#warning-block').hide();
 	   		 }else if (data.returncode == 501) {
 	   			 $('.goodbye').html("操作异常,请稍后重试");
 	   			 $('.alertDiv').show();
 	   		 }else {
 	   			 //签到失败
 	   			 $('#warning-block').show();
 	   			 $('.alert p:first').html('签退失败,该置业顾问正在接访中!');
 	   		 }
 	   	 },
 	   	complete: function () {
	   		$('.loading').hide();
	    },
	    error: function (data) {
	    	$('.loading').hide();
	    }
 	   });
}

function getNextPage1(){
	if (currentPage1 < $('#qiantuizongyeshu').val()){
		currentPage1 = currentPage1 +1;
		//getShouYeAllReceptionAgent($("#proId").val());
		getAgentList();
	}
}
function getBeforePage1(){
	/*currentPage1 = currentPage1-1;
	if (currentPage1<1){
		currentPage1 = 1;
	}*/
	//getAgentList();
	if (currentPage1>1){
		currentPage1 = currentPage1-1;
		getAgentList();
		//getshouyeAllSongBieAgent($("#proId").val());
	}
}
//签退list_______________________________
function getQianTuiList(){
	type = 1;
	$('#signAgentList2').html("");
	$.ajax({
 		  type:"post",
 		  async : false,
 		  url:"project.getAgentSignOutList",
 		  data:{projectId:$("#proId").val(),
 			curentPage : currentPage2,
 			limit : 5,
 		  },
 		 beforeSend: function () {
 			$('.loading').show();
 	    },
 	   	 success:function(data){
 	   		$('.loading').hide();
 	   		 $('#qiantuizongyeshu').val(data.totalPage);
 	   		 getQianTuiListShow(data.root);
 	   		 //getRankingList($("#proId").val());
 	   		 //getAgentList();
 	   		 judgePage(data,$(".pre6"),$('.next6'),currentPage2);
 	   	 },
 	   	complete: function () {
	   		$('.loading').hide();
	    },
	    error: function (data) {
	    	$('.loading').hide();
	    }
 	   });
}
function getQianTuiListShow(data){
	$('#signAgentList2').html("");
	var s = '';
	$.each(data,function(v,o){
		s += '<div class="kqMsg col-xs-12" onclick="clickKq(this)" data-value="'+o.proId+'"><div data-value="'+o.id+'" class="kq_imgBox pull-left">';
		if (o.photo != null && o.photo != ""){
			s += '<img src="'+o.photo+'" alt="">';
		}else {
			s += '<img src="http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png" alt="">';
		}
		s += '</div><div class="midBox pull-left"><p class="name">';
		if (o.name != null && o.name != ""){
			s += '<img src="static/images/userleft.png" alt=""><span class="zhiName">'+o.name+'</span>';
		}else {
			s += '<img src="static/images/userleft.png" alt=""><span class="zhiName">暂无</span>';
		}
		//s += '<img src="static/images/userleft.png" alt=""><span class="zhiName">'+o.name+'</span>';
		s += '</p><p class="phone"><img src="static/images/icon_Tel.png" alt="">'+o.phone;
		s += '</p><p class="group"><img src="static/images/icon_edit.png" alt="">';	
		if (o.groupName == "1" || o.groupName == null || o.groupName == ""){
			s += '销售一组';
		}else if (o.groupName == "2"){
			s += '销售二组';
		}
		s += '</p></div><div class="clearfix"></div>';
		s += '</div>';
	});
	if(data.length>0){
		$('#signAgentList2').html(s);
	}
}
function getDaiQianYueList(){
	type = 0;
	currentPage1 = 1;
	getAgentList();
}
function getNextPage2(){
	if (currentPage2 < $('#qiantuizongyeshu').val()){
		currentPage2 = currentPage2 +1;
		getQianTuiList();
	}
}
function getBeforePage2(){
	if (currentPage2>1){
		currentPage2 = currentPage2-1;
		getQianTuiList();
	}
}
//所有置业顾问列表____________分组______________
function getAllAgentList(data){
	$.ajax({
 		  type:"post",
 		  async : false,
 		  url:"project.getAgentList",
 		  //dataType : "json",
 		  data:{projectId:data,
 		  },
 		 beforeSend: function () {
  			$('.loading').show();
  	    },
 	   	 success:function(data){
 	   		$('.loading').hide();
 	   		 data = eval("("+data+")");
 	   		 data = eval("("+data.users+")");
 	   		 getAllAgentListShow(data);
 	   	 },
 	   	complete: function () {
	   		$('.loading').hide();
	    },
	    error: function (data) {
	    	$('.loading').hide();
	    }
 	   });
}
function getAllAgentListShow(data){
	$('.group1').html("");
	$('.group2').html("");
	var s = '';
	var t = '';
	var i = 1;
	var j = 1;
	$.each(data,function(v,o){
		if (o.group == null || o.group == "" || o.group == "1" ){
			s += '<div class="msg group_msg col-xs-12" onclick="getToTwoGroup(this)" data-value="'+o.userId+'"><div class="imgBox pull-left">';
			if (o.photo != null && o.photo != ""){
				s += '<img src="'+o.photo+'" alt="">';
			}else {
				s += '<img src="http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png" alt="">';
			}
			s += '</div><div class="midBox pull-left">';
			if (o.userCaption != null && o.userCaption != ""){
				s += '<p class="name"><img src="static/images/userleft.png" alt="">'+o.userCaption;
			}else {
				s += '<p class="name"><img src="static/images/userleft.png" alt="">暂无';
			}
			//s += '<p class="name"><img src="static/images/userleft.png" alt="">'+o.userCaption;
			s += '</p>';	
			s += '<p class="phone"><img src="static/images/icon_Tel.png" alt="">'+o.phone;		
			s += '</p>';		
			s += '<p class="group"><img src="static/images/icon_edit.png" alt="">销售一组';		
			s += '</p></div>';		
			s += '<div class="sign"><img src="static/images/sanjiaoxing.png" alt="">';		
			s += '<span id="num">'+i+'</span></div><div class="clearfix"></div></div>';	
			
			i ++;
		}else if(o.group == "2"){
			t += '<div class="msg group_msg col-xs-12" onclick="getToOneGroup(this)" data-value="'+o.userId+'"><div class="imgBox pull-left">';
			if (o.photo != null && o.photo != ""){
				t += '<img src="'+o.photo+'" alt="">';
			}else {
				t += '<img src="http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png" alt="">';
			}
			t += '</div><div class="midBox pull-left">';
			if (o.userCaption != null && o.userCaption != ""){
				t += '<p class="name"><img src="static/images/userleft.png" alt="">'+o.userCaption;
			}else {
				t += '<p class="name"><img src="static/images/userleft.png" alt="">暂无';
			}
			//t += '<p class="name"><img src="static/images/userleft.png" alt="">'+o.userCaption;
			t += '</p>';	
			t += '<p class="phone"><img src="static/images/icon_Tel.png" alt="">'+o.phone;		
			t += '</p>';		
			t += '<p class="group"><img src="static/images/icon_edit.png" alt="">销售二组';		
			t += '</p></div>';		
			t += '<div class="sign"><img src="static/images/sanjiaoxing.png" alt="">';		
			t += '<span id="num">'+j+'</span></div><div class="clearfix"></div></div>';	
			
			j ++;
		}
	});
	if(data.length>0){
		$('.group1').html(s);
	}
	if(data.length>0){
		$('.group2').html(t);
	}
}
//分组_____________to___二组___________
function getToTwoGroup(obj){
	/*$.ajax({
		  type:"post",
		  async : false,
		  url:"agent_group",
		  data:{userId:$(obj).data("value"),F
			  group : "2",
		  },
		  beforeSend: function () {
	  			$('.loading').show();
	  	    },
	   	 success:function(){
	   		$('.loading').hide();
	   		getAllAgentList($("#proId").val());
  			getAgentList();
  			getRankingList($("#proId").val());
  			getAllReceptionAgent($("#proId").val());
  			getShouYeAllReceptionAgent($("#proId").val());
	   	 },
	   	complete: function () {
	   		$('.loading').hide();
	    },
	    error: function (data) {
	    	$('.loading').hide();
	    }
	   });*/
}
//分组_____________to___一组___________
function getToOneGroup(obj){
	/*$.ajax({
		type:"post",
		async : false,
		url:"agent_group",
		data:{userId:$(obj).data("value"),
			group : "1",
		},
		beforeSend: function () {
  			$('.loading').show();
  	    },
		success:function(){
			$('.loading').hide();
			getAllAgentList($("#proId").val());
  			getAgentList();
  			getRankingList($("#proId").val());
  			getAllReceptionAgent($("#proId").val());
  			getShouYeAllReceptionAgent($("#proId").val());
		},
		complete: function () {
	   		$('.loading').hide();
	    },
	    error: function (data) {
	    	$('.loading').hide();
	    }
	});*/
}
//置业顾问排队列表_______________________
function getRankingList(obj){
	$.ajax({
		type:"post",
		async : true,
		url:"agent_queueUp",
		data:{projectId:obj,
			curentPage : currentPage3,
			limit : limit,
			//checkTeam : 0,
		},
		beforeSend: function () {
  			$('.loading').show();
  	    },
		success:function(data){
			$('.loading').hide();
			getAllRangingAgentList(data.root);
			judgePage(data,$(".pre1"),$('.next1'),currentPage3);
			$('#rangkingzongyeshu').val(data.totalPage);
			if (data.root.length <1){
				$('.conError').show();
			}
		},
		complete: function () {
	   		$('.loading').hide();
	    },
	    error: function (data) {
	    	$('.loading').hide();
	    }
	});
}
function getAllRangingAgentList(data){
	var date2 = getDay(0);
	$('#jiefangzhaopian').attr("src","http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png");
	$('#jiefangZYName').html("");
	$('#jiefangNowTime').html("");
	$('#jiefangzhuangtai').html("");
	$('#receptionAgentCommit').val("");
	$('#ranking').html("");
	var s = '';
	var i = 1;
	if(data.length>0){
		canClick = true;
		
		$.each(data,function(v,o){
			
			s += '<div class="msg col-xs-12"><div class="imgBox pull-left">';
			if (o.photo != null && o.photo != ""){
				s += '<img src="'+o.photo+'" alt="">';
			}else {
				s += '<img src="http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png" alt="">';
			}
			s += '</div><div class="midBox pull-left"><p class="name">';
			if (o.name != null && o.name != ""){
				s += '<img src="static/images/userleft.png" alt="">'+o.name;
			}else {
				s += '<img src="static/images/userleft.png" alt="">暂无';
			}
			s += '</p>';	
			s += '<p class="phone"><img src="static/images/icon_Tel.png" alt="">'+o.phone;
			s += '</p>';
			s += '<p class="group"><img src="static/images/icon_edit.png" alt="">';
			if (o.groupName == "1" || o.groupName == null || o.groupName == ""){
				s += '销售一组';
			}else if (o.groupName == "2"){
				s += '销售二组';
			}
			s += '</p></div>';
			s += '<div class="sign"><img src="static/images/sanjiaoxing.png" alt="">';
			s += '<span id="num">'+i+'</span>';
			s += '</div><div class="clearfix"></div></div>';
			i++;
		});
	}else {
		//不能选人数
		canClick = false;
	}
	if(data.length>0){
		$('#ranking').html(s);
	}
}
function getRangBeforePage(){
	if (currentPage3>1){
		currentPage3 = currentPage3-1;
		getRankingList($("#proId").val());
	}
}
function getRangNextPage(){
	if (currentPage3 < $('#rangkingzongyeshu').val()){
		currentPage3 = currentPage3 +1;
		getRankingList($("#proId").val());
	}
}
//置业顾问接访列表_______________________
function getAllReceptionAgent(obj){
	$.ajax({
		type:"post",
		async : false,
		url:"agent_receivIng",
		data:{projectId:obj,
			curentPage : currentPage4,
			limit : limit,
			//checkTeam : 0,
		},
		beforeSend: function () {
  			$('.loading').show();
  	    },
		success:function(data){
			$('.loading').hide();
			$('#zuozejiefangzongyeshu').val(data.totalPage);
			getAllReceptionAgentList(data.root);
			judgePage(data,$(".pre2"),$('.next2'),currentPage4);
		},
		complete: function () {
	   		$('.loading').hide();
	    },
	    error: function (data) {
	    	$('.loading').hide();
	    }
	});
}
function getAllReceptionAgentList(data){
	
	$('#receptionAgent').html("");
	var s = '';
	var i = 1;
	$.each(data,function(v,o){
		s += '<div class="msg col-xs-12"><div class="imgBox pull-left">';
		if (o.photo != null && o.photo != ""){
			s += '<img src="'+o.photo+'" alt="">';
		}else {
			s += '<img src="http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png" alt="">';
		}
		s += '</div><div class="midBox pull-left"><p class="name">';
		if (o.name != null && o.name != ""){
			s += '<img src="static/images/userleft.png" alt="">'+o.name;
		}else {
			s += '<img src="static/images/userleft.png" alt="">暂无';
		}
		s += '</p>';	
		s += '<p class="phone"><img src="static/images/icon_Tel.png" alt="">'+o.phone;
		s += '</p>';
		s += '<p class="group"><img src="static/images/icon_edit.png" alt="">';
		if (o.groupName == "1" || o.groupName == null || o.groupName == ""){
			s += '销售一组';
		}else if (o.groupName == "2"){
			s += '销售二组';
		}
		s += '</p></div>';
		s += '<div class="sign"><img src="static/images/sanjiaoxing.png" alt="">';
		s += '<span id="num">'+i+'</span>';
		s += '</div><div class="clearfix"></div></div>';
		
		i++;
	});
	if(data.length>0){
		$('#receptionAgent').html(s);
	}
}
function getReceptionBeforePage(){
	/*currentPage4 = currentPage4 - 1;
	if (currentPage4 < 1){
		currentPage4 = 1;
	}*/
	if (currentPage4>1){
		currentPage4 = currentPage4-1;
		getAllReceptionAgent($("#proId").val());
		//getshouyeAllSongBieAgent($("#proId").val());
	}
}
function getReceptionNextPage(){
	if (currentPage4 < $('#zuozejiefangzongyeshu').val()){
		currentPage4 = currentPage4 +1;
		getAllReceptionAgent($("#proId").val());
		//getShouYeAllReceptionAgent($("#proId").val());
	}
}
//接访客户___________________
function getRealVisitCommitOne(){
	firstArray = [];
	//secondArray = [];
	$("#firstIn").html("");
	//$("#secondIn").html("");
	var checkWay = 0;
	var customerName = "";
	var phone = "";
	var chaxunCustomerId = "";
	
	if ($("#secondIn").html() != null && $("#secondIn").html() != ""){
		checkWay = 1;
		customerName = $('#chauxncustomerName').html();
		phone = $("#secondIn").html();
		chaxunCustomerId = $("#chaxunCustomerId").val();
	}
	
	var date5 = getDay(2);
	$.ajax({
		type:"post",
		async : false,
		url:"project.uploadVisitReocrd",
		data:{userId: $('#receptionAgentCommit').val(),
			projectId : $("#proId").val(),
			receptTime : date5,
			arriveTime : date5,
			visitStatus : 1,
			appointUserId : $('#knowAgentId').val(),
			customerCount : $("#danfangrenshu").val(),
			isNew : $("#jieshoujiefangqueren").val(),
			customerName : customerName,
			phone : phone,
			checkWay : checkWay,
			customerId : chaxunCustomerId,
		},
		beforeSend: function () {
  			$('.loading').show();
  	    },
		success:function(data){
			$('.loading').hide();
			//getAllReceptionAgentList(data.root);
			//getLogin();
			//$('.vistConfir').hide();
			 getAllAgentList($("#proId").val());
			 getAgentList();
			 getRankingList($("#proId").val());
			 getAllReceptionAgent($("#proId").val());
			 getShouYeAllReceptionAgent($("#proId").val());
			 getAllClose();
			 //到访人数清空___________________________
			 $(".Num").css({"color":"#676767","background":"#e5e5e5"});
		     $("#danfangrenshu").val("");
		     
		     $('#firstIn').html("");
		  	 $('#secondIn').html("");
		},
		complete: function () {
	   		$('.loading').hide();
	    },
	    error: function (data) {
	    	$('.loading').hide();
	    }
	});
}
function getRealVisitCommitTwo(){
	firstArray = [];
	secondArray = [];
	$("#firstIn").html("");
	$("#secondIn").html("");
		
	var date5 = getDay(2);
	$.ajax({
		type:"post",
		async : false,
		url:"project.uploadVisitReocrd",
		data:{userId: $('#chaxunAgentId').val(),
			projectId : $("#proId").val(),
			receptTime : date5,
			arriveTime : date5,
			visitStatus : 1,
			//appointUserId : $('#knowAgentId').val(),
			appointUserId : $('#chaxunAgentId').val(),
			customerCount : 1,
			//customerCount : $("#danfangrenshu").val(),
			isNew : 0,
			customerName : $('#chauxncustomerName').html(),
			phone : $("#chaxunCustomerPhone").val(),
			checkWay : 1,
			customerId : $("#chaxunCustomerId").val()
		},
		beforeSend: function () {
  			$('.loading').show();
  	    },
		success:function(data){
			//getAllReceptionAgentList(data.root);
			//getLogin();
			//$('.vistConfir').hide();
			$('.loading').hide();
			getAllAgentList($("#proId").val());
			getAgentList();
			getRankingList($("#proId").val());
			getAllReceptionAgent($("#proId").val());
			getShouYeAllReceptionAgent($("#proId").val());
			getAllClose();
			closeDaoFang();
			
			$('#firstIn').html("");
		  	$('#secondIn').html("");
		},
		complete: function () {
	   		$('.loading').hide();
	    },
	    error: function (data) {
	    	$('.loading').hide();
	    }
	});
}
//获取认识的置业顾问列表____________________
function getnewAllZhiDingAgentList(){
	$(".vistway").hide();
	$(".chooseCus").show();
	//确认到访不能点击
	$('.conVist').attr('disabled',"true");
	$('.conVist').css({"background":"#ddd"});
	
	$.ajax({
		  type:"post",
		  async : false,
		  url:"project.getAgentPageList",
		  //dataType : "json",
		  data:{projectId:$("#proId").val(),
			 curentPage : currentPage6,
			limit : 8,
		  },
		  beforeSend: function () {
	  			$('.loading').show();
	  	    },
	   	 success:function(data){
	   		$('.loading').hide();
	   		 $('#zhidingzhiyeguwenzongpage').val(data.totalPage);
	   		 getNewAllZhiDingAgentListShow(data.root);
	   		judgePage(data,$(".pre8"),$('.next8'),currentPage6);
	   	 },
	   	complete: function () {
	   		$('.loading').hide();
	    },
	    error: function (data) {
	    	$('.loading').hide();
	    }
	   });
}
function getNewAllZhiDingAgentListShow(data){
	$('#zhidingjiefangagentlist').html("");
	var s = '';
	$.each(data,function(v,o){
		s += '<div class="col-md-3"><div class="cus" onclick="clcikzdCus(this)" data-value="'+o.userCaption+'" data-id="'+o.userId+'" data-src="'+o.photo+'" data-status="'+o.stutas+'"><div class="chooseimg pull-left">';
		if (o.photo != null && o.photo != ""){
			s += '<img src="'+o.photo+'" alt="">';
		}else {
			s += '<img src="http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png" alt="">';
		}
		s += '</div><div class="midBox pull-left">';
		if (o.userCaption != null && o.userCaption != ""){
			s += '<p class="name"><img src="static/images/userleft.png" alt="">'+o.userCaption+'</p>';
		}else {
			s += '<p class="name"><img src="static/images/userleft.png" alt="">暂无</p>';
		}
			
		s += '<p class="phone"><img src="static/images/icon_Tel.png" alt="">'+o.phone+'</p>';
		s += '<p class="group"><img src="static/images/icon_edit.png" alt="">';
		if (o.groupName == "1" || o.groupName == null || o.groupName == ""){
			s += '销售一组';
		}else if (o.groupName == "2"){
			s += '销售二组';
		}	
		s += '</p></div><div class="clearfix"></div></div></div>';	
	});
	if(data.length>0){
		$('#zhidingjiefangagentlist').html(s);
	}
}
function zhidingAgentListBeforePage(){
	/*currentPage6 = currentPage6-1;
	if (currentPage6<1){
		currentPage6 = 1;
	}*/
	if (currentPage6>1){
		currentPage6 = currentPage6-1;
		getnewAllZhiDingAgentList();
		//getshouyeAllSongBieAgent($("#proId").val());
	}
}
function zhidingAgentListNextPage(){
	if (currentPage6 < $('#zhidingzhiyeguwenzongpage').val()){
		currentPage6 = currentPage6 +1;
		//getShouYeAllReceptionAgent($("#proId").val());
		getnewAllZhiDingAgentList();
	}
}

function clcikzdCus(obj){
	var date2 = getDay(0);
	$(".chooseCus").hide();
	$(".conError").hide();
	$(".vistConfir").show();
	if ($(obj).data("src") != null && $(obj).data("src") != ""){
		$('#konwAgentPhoto').attr("src",$(obj).data("src"));
	}else {
		$('#konwAgentPhoto').attr("src","http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png");
	}
	
	if ($(obj).data("status") == "排队中" || $(obj).data("status") == "接访中"){
		canClick = true;
		$('#jiefangZYName').html($(obj).data("value"));
		$('#jiefangNowTime').html(date2);
		$('#jiefangzhuangtai').html($(obj).data("status"));
		$('#receptionAgentCommit').val($(obj).data("id"));
		if ($(obj).data("src") != null && $(obj).data("src") != ""){
			$('#jiefangzhaopian').attr("src",$(obj).data("src"));
		}else {
			$('#jiefangzhaopian').attr("src","http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png");
		}
	}else {
		getRankingList($("#proId").val());
		canClick = false;
		$(".conError").show();
	}
	
	$('#knowAgentName').html($(obj).data("value"));
	$('#knowAgentId').val($(obj).data("id"));
}

//送别客户__________________________
function leaveCus() {
	$('.leave_confirm').show();
	$('.goodbye').html("送别该批贵宾吗?");
}

function toGoodByeCustomer(){
	firstArray = [];
	secondArray = [];
	$("#firstIn").html("");
	$("#secondIn").html("");
		
	var dateBye = getDay(2);
	$.ajax({
		  type:"post",
		  async : false,
		  url:"project.uploadVisitReocrd",
		  //dataType : "json",
		  data:{visitNo: $('#visitNo').val(),
			  visitStatus : 3,
			  leaveTime : dateBye,
			  projectId : $("#proId").val(),
			  //checkTeam : 0,
			},
			beforeSend: function () {
	  			$('.loading').show();
	  	    },
	   	 success:function(data){
	   		$('.loading').hide();
	   		$('.leave_confirm').hide();
	   		 getAllAgentList($("#proId").val());
			 getAgentList();
			 getRankingList($("#proId").val());
			 getAllReceptionAgent($("#proId").val());
			 getShouYeAllReceptionAgent($("#proId").val());
			 getshouyeAllSongBieAgent($("#proId").val());
			 getAllClose();
	   	 },
	   	complete: function () {
	   		$('.loading').hide();
	    },
	    error: function (data) {
	    	$('.loading').hide();
	    }
	   });
}
//获取客户到访记录____________________
function toGetOneCusInfo(){
	var chaxunjiefangDate = getDay(0);
	$.ajax({
		  type:"post",
		  async : false,
		  url:"project.getCustomer",
		  //dataType : "json",
		  data:{projectId:$("#proId").val(),
			  phone : $("#secondIn").html(),
			},
			beforeSend: function () {
	  			$('.loading').show();
	  	    },
	   	 success:function(data){
	   		$('.loading').hide();
	   		 data = eval("("+data+")");
	   		 /*getAllAgentList($("#proId").val());
			 getAgentList();
			 getRankingList($("#proId").val());
			 getAllReceptionAgent($("#proId").val());
			 getShouYeAllReceptionAgent($("#proId").val());
			 getAllClose();*/
	   		 //console.log(data);
	   		 if (data.returnCode == 200){
	   			 //$('#chaxunAgentphoneTwo').val($("#secondIn").html());
	   			 
	   			 $('#chaxunCustomerId').val(data.customer.customerId);
	   			 $('#chauxndaofangNum').html(data.customer.visitCount);
	   			 $('#chauxncustomerName').html(data.customer.customerName);
	   			 $('#chaxunCustomerPhone').val($("#secondIn").html());
	   			 $('#chaxunAgentId').val(data.customer.userId);
	   			 $('#chaxunagentName').html(data.customer.agentName);
	   			 $('#chaxunjiefangStatus').html(data.customer.status);
	   			 $('#chaxunshoucidanfangtime').html(data.customer.firstVisitTime);
	   			 $('#chaxunmocidaofangtime').html(data.customer.lastVisitTime);
	   			 $('#chaxunjiefangTime').val(chaxunjiefangDate);
	   			 if (data.customer.agentPhoto != null && data.customer.agentPhoto != ""){
	   				 $('#chaxunagentPhoto').attr("src",data.customer.agentPhoto);
	   			 }else {
	   				 $('#chaxunagentPhoto').attr("src","http://root-1252955170.cossh.myqcloud.com/pictures/morentouxiang.png");
	   			 }
	   			 
	   			 
	   			$('.select_btn').removeClass('hide');
	   			 if (data.customer.status == "休息") {
	   				 $('#getRealVisitCommitTwo').hide();	   				 
	   			 }else {
	   				 $('#getRealVisitCommitTwo').show();	
	   			 }
	   		 }else if (data.returnCode == 400) {
	   			$('.goodbye').html(data.customer);
				$('.alertDiv').show();
	   		 }
	   	 },
	   	complete: function () {
	   		$('.loading').hide();
	    },
	    error: function (data) {
	    	$('.loading').hide();
	    }
	   });
}
function toGoToAgentDayWorkPage(){
	window.location.href="to_machineBusiness?projectId="+$("#proId").val();
}
//提交电话号码______________________
function toCommitByePhone(){
	var check = checkMobile($('#firstIn').html());
	
	if (check) {
		$.ajax({
			  type:"post",
			  async : false,
			  url:"project.uploadVisitReocrd",
			  //dataType : "json",
			  data:{visitNo: $('#visitNo').val(),
				  phone : $('#firstIn').html(),
				  projectId : $("#proId").val(),
				  //customerName : $('#shouyejiedaicustomerName').html(),
				  checkWay : 0,
				},
				beforeSend: function () {
		  			$('.loading').show();
		  	    },
		   	 success:function(data){
		   		$('.loading').hide();
		   		 getAllAgentList($("#proId").val());
				 getAgentList();
				 getRankingList($("#proId").val());
				 getAllReceptionAgent($("#proId").val());
				 getShouYeAllReceptionAgent($("#proId").val());
				 //getAllClose();
				 
		   	 },
		   	complete: function () {
		   		$('.loading').hide();
		    },
		    error: function (data) {
		    	$('.loading').hide();
		    }
		   });
	}
	
	
}



function receptionTime() {
	t = setTimeout ('receptionTime()',1000)
	
	var times = document.getElementsByClassName('reTime');

	for (var i = 0; i < times.length; i++) {
		var time = times[i]
		var arraveDate = allArriveTime[i]
		arraveDate = arraveDate.replace(/-/g,"/");
		var startTime = new Date(arraveDate); //到访时间
		var nowTime = new Date();  //现在时间

		var date3 = nowTime.getTime()-startTime.getTime()  //时间差的毫秒数

		//计算出小时数
		var leave1=date3%(24*3600*1000)    //计算天数后剩余的毫秒数
		var hours=Math.floor(leave1/(3600*1000))
		if (hours < 10){
			hours = "0"+hours;
		}
		//计算相差分钟数
		var leave2=leave1%(3600*1000)        //计算小时数后剩余的毫秒数
		var minutes=Math.floor(leave2/(60*1000))
		if(minutes < 10){
			minutes = "0"+minutes;
		}
		//计算相差秒数
		var leave3=leave2%(60*1000)      //计算分钟数后剩余的毫秒数
		var seconds=Math.round(leave3/1000)
		if(seconds < 10){
			seconds = "0"+seconds;
		}
		$(time).text(hours + ":" + minutes + ":" + seconds) 
			
	}
}

//写cookies 
function setCookie(name,value) 
{ 
    var Days = 30; 
    var exp = new Date(); 
    exp.setTime(exp.getTime() + Days*24*60*60*1000); 
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString(); 
} 

//读取cookies 
function getCookie(name) 
{ 
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
 
    if(arr=document.cookie.match(reg))
 
        return unescape(arr[2]); 
    else 
        return null; 
} 

//删除cookies 
function delCookie(name) 
{ 
    var exp = new Date(); 
    exp.setTime(exp.getTime() - 1); 
    var cval=getCookie(name); 
    if(cval!=null) 
        document.cookie= name + "="+cval+";expires="+exp.toGMTString(); 
} 

//判断分页
function judgePage(data,pre,next,currentPage) {
	if(data.totalPage == 1 || data.totalPage == 0) {
		setDis(pre);
		setDis(next);
	}else if(currentPage == 1){
		setDis(pre);
		setEnable(next);
	}else{
		if(currentPage == data.totalPage){
			setEnable(pre);
			setDis(next);
		}else {
			setEnable(next);			
			setEnable(pre);			
		}
	}
}

//设置分页不可点击
function setDis(target) {
	$(target).css({"color":"#ffffff","background-color":"#ddd"});
	$(target).attr("disabled",true);
}
//设置分页可点击
function setEnable(target) {
	$(target).css({"color":"#337ab7","background-color":"#fff"});
	$(target).removeAttr("disabled");
}

//全屏
function requestFullScreen() {
    var de = document.documentElement;

    if (de.requestFullscreen) {
        de.requestFullscreen();

    } else if (de.mozRequestFullScreen) {
        de.mozRequestFullScreen();
    } else if (de.webkitRequestFullScreen) {
        de.webkitRequestFullScreen();
    }
}

//账号密码回显
function pwdny(){
	  if(aa ==true){
	      intcooking()
	    }else{
	      $.cookie("netPhone","",{
	        expires:7,
	        path: "/"
	      })
	      $.cookie("netPassword","",{
	        expires:7,
	        path: "/"
	      })
	      
	      localStorage.setItem("netPhone","");
	      localStorage.setItem("netPassword","");
	      
	      
	    
	     }
	  };

	  
	  function intcooking(){
	    /*  存密码*/
	    if(window.localStorage){
	      var netPhone = JSON.stringify($("#exampleInputEmail1").val())
	      var netPassword = JSON.stringify($("#exampleInputPassword1").val())
	      localStorage.setItem("netPhone",netPhone);
	      localStorage.setItem("netPassword",netPassword);
	    }else{
	      $.cookie("netPhone",$("#phone").val(),{
	      expires:7,
	      path: "/"
	    })
	    $.cookie("netPassword",$("#password").val(),{
	      expires:7,
	      path: "/"
	    })}
	  }
	  
	  
	  function putcooking(){
	    /*取密码  */
	    if(window.localStorage){
	      
	      var netPhone = JSON.parse(localStorage.getItem("netPhone"));
	      var netPassword = JSON.parse(localStorage.getItem("netPassword"));
	      $("#exampleInputEmail1").val(netPhone);
	      $("#exampleInputPassword1").val(netPassword);
	      
	    }
	    else{
	      var netPhone=$.cookie("netPhone");
	      var netPassword=$.cookie("netPassword");
	      $("#exampleInputEmail1").val(netPhone);
	      $("#exampleInputPassword1").val(netPassword);
	    }
	      
	    
	    
	  }

	  function closeAlert(){
		  $('.alertDiv').hide();
//		  refreshLogin();
	  }
	  
	  function checkMobile(phone){
		    if((/^1[0-9]{10}/.test(phone))){ 
		    	return true; 
		    }else {
		    	$('.goodbye').html("电话号码输入有误");
				$('.alertDiv').show();
				deleteAll();
		        return false; 
		    	
		    } 
		} 