//所有订单分页参数
var startAllAppoint = 0;
var limitAllAppoint = 9;
var currentPageAllAppoint = 1;
var totalPageAllAppoint = 0;

//待审核分页参数
var startAllAppointCheck = 0;
var limitAllAppointCheck = 9;
var currentPageAllAppointCheck = 1;
var totalPageAllAppointCheck = 0;

//待付款分页参数
var startAllAppointWaitPay = 0;
var limitAllAppointWaitPay = 9;
var currentPageAllAppointWaitPay = 1;
var totalPageAllAppointWaitPay = 0;

//待签约分页参数
var startAllAppointWaitSign = 0;
var limitAllAppointWaitSign = 9;
var currentPageAllAppointWaitSign = 1;
var totalPageAllAppointWaitSign = 0;

//已签约分页参数
var startAllAppointWaitSigned = 0;
var limitAllAppointWaitSigned = 9;
var currentPageAllAppointWaitSigned = 1;
var totalPageAllAppointWaitSigned = 0;

//已撤销分页参数
var startAllAppointRevoke = 0;
var limitAllAppointRevoke = 9;
var currentPageAllAppointRevoke = 1;
var totalPageAllAppointRevoke = 0;

//已拒绝分页参数
var startAllAppointRefuse = 0;
var limitAllAppointRefuse = 9;
var currentPageAllAppointRefuse = 1;
var totalPageAllAppointRefuse = 0;

//付款待确认
var startAllAppointWaitEnterPay = 0;
var limitAllAppointWaitEnterPay = 9;
var currentPageAllAppointWaitEnterPay = 1;
var totalPageAllAppointWaitEnterPay = 0;

$(document).ready(function(){
	$("#paySureLi").click(function(){
		startAllAppointWaitEnterPay = (currentPageAllAppointWaitEnterPay - 1) * limitAllAppointWaitEnterPay;
		getWaitEnterPayOrderData();
		toPageWaitEnterPay();
	});
	$("#allOrderLi").click(function(){
		startAllAppoint = (currentPageAllAppoint - 1) * limitAllAppoint;
		getAllOrderData();
		toPage();
	});
	$("#checkLi").click(function(){
		startAllAppointCheck = (currentPageAllAppointCheck - 1) * limitAllAppointCheck;
		getCheckOrderData();
		toPageCheck();
	});
	$("#waitPayLi").click(function(){
		startAllAppointWaitPay = (currentPageAllAppointWaitPay - 1) * limitAllAppointWaitPay;
		getWaitPayOrderData();
		toPageWaitPay();   
    });
	$("#signLi").click(function(){
		startAllAppointWaitSign = (currentPageAllAppointWaitSign - 1) * limitAllAppointWaitSign;
		getWaitSignOrderData();
		toPageWaitSign();
    });
	$("#signedLi").click(function(){
		startAllAppointWaitSigned = (currentPageAllAppointWaitSigned - 1) * limitAllAppointWaitSigned;
		getSignedOrderData();
		toPageSigned();
    });
	$("#revokeLi").click(function(){
		startAllAppointRevoke = (currentPageAllAppointRevoke - 1) * limitAllAppointRevoke;
		getRevokeOrderData();
		toPageRevoke();
    });
	$("#refuseLi").click(function(){
		startAllAppointRefuse = (currentPageAllAppointRefuse - 1) * limitAllAppointRefuse;
		getRefuseOrderData();
		toPageRefuse();
    });
	//获取标签标记
	var buttonSign = $("#buttonSign").val();
	if(buttonSign=="" || buttonSign==null || buttonSign == "all"){
		$("#allOrderLi").addClass("active");
		$("#allIndent").addClass("active");
		getAllOrderData();
		toPage();
	}
	if(buttonSign == "willCheck"){
		$("#checkLi").addClass("active");
		$("#checkPend").addClass("active");
		getCheckOrderData();
		toPageCheck();
	}
	if(buttonSign == "waitPayEnter"){
		$("#paySureLi").addClass("active");
		$("#paySure").addClass("active");
		getWaitEnterPayOrderData();
		toPageWaitEnterPay();
	}
	if(buttonSign == "waitSign"){
		$("#signLi").addClass("active");
		$("#signUp").addClass("active");
		getWaitSignOrderData();
		toPageWaitSign();
	}
});

//获取付款待确认
function getWaitEnterPayOrderData(){
	$.ajax({
		type : "post",
		url : "wait_pay_money_enter_order_data",
		async:false,
		data : {
			start: startAllAppointWaitEnterPay, limit:limitAllAppointWaitEnterPay
			},
		success : function(data) {
			setWaitEnterPayOrderInfo(data.root);
			startAllAppointWaitEnterPay = data.currentResult;
			totalPageAllAppointWaitEnterPay = data.totalPage;
		}
	});
}

function setWaitEnterPayOrderInfo(data){
	//console.log(data);
	var s = "";
	$.each(data,function(v, o) {
		var time = o.applyTime.substring(0,10); 
		s += '<div class="col-md-3 sameColor" style="margin-left:6%;margin-bottom: 10px;;" onclick=orderOtherDeail('+o.recordNo+',"waitPayEnter","waitPayEnter")>';
		s += '<div class="row"><div class="col-md-10"><p class="pp">'+o.buildingNo+'栋'+o.unit+'单元'+o.houseNo+'室</p></div></div>';
		s += '<div class="row coco"><div class="col-md-3" style="margin-left:4%;margin-top:34px;margin-bottom:40px;width:28%;">';
        s += '<img src="'+o.houseTypePic+'" alt="" class="img-rounded" style="width:100px;height:100px"></div>';
		s += ' <div class="col-md-7" style="margin-left:9%;margin-top:40px;margin-bottom:40px;width:58%;"><div class="row sameTop">';
		s += '<div class="col-md-12"><span>下单时间：</span><span>'+time+'</span></div></div>';
		s += '<div class="row sameTop"><div class="col-md-12"><span>列表价格：</span><span>'+o.listPrice+'</span></div></div>';
		s += '<div class="row sameTop"><div class="col-md-12"><span>购买价格：</span><span>'+o.buyPrice+'</span></div></div></div></div></div> ';
	});
	if (data.length > 0) {
		$("#surePay").html(s);
	} else {
		$("#surePay").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
	}
}

function toPageWaitEnterPay(){
	layui.use([ 'laypage', 'layer' ], function() {
		var layer = layui.layer, laypage = layui.laypage;
		//调用分页
		laypage({
			cont : 'pagedSurePay',
			pages : totalPageAllAppointWaitEnterPay //得到总页数
			,
			curr : currentPageAllAppointWaitEnterPay,
			skip : true,
			jump : function(obj, first) {
				currentPageAllAppointWaitEnterPay = obj.curr;
				console.log(currentPageAllAppointWaitEnterPay);
				startAllAppointWaitEnterPay = (obj.curr - 1) * limitAllAppointWaitEnterPay;
				if (!first) { //一定要加此判断，否则初始时会无限刷新
					getWaitEnterPayOrderData();
				}
			}
		});

	});
}

//ajax获取数据
function getAllOrderData(){
	$.ajax({
		type : "post",
		url : "get_all_order_data",
		async:false,
		data : {
			start: startAllAppoint, limit:limitAllAppoint
			},
		success : function(data) {
			setAllOrderInfo(data.root);
			startAllAppoint = data.currentResult;
			totalPageAllAppoint = data.totalPage;
		}
	});
}
//添加数据
function setAllOrderInfo(data){
	//console.log(data);
	var s = "";
	$.each(data,function(v, o) {
		var time = o.applyTime.substring(0,10); 
		if(o.orderStatus == "付款待确认"){
			s += '<div class="col-md-3 sameColor" style="margin-left:6%;margin-bottom: 10px;;" onclick=orderOtherDeail('+o.recordNo+',"all","waitPayEnter")>';
		}else if(o.orderStatus == "待签约"){
			s += '<div class="col-md-3 sameColor" style="margin-left:6%;margin-bottom: 10px;" onclick=orderOtherDeail('+o.recordNo+',"all","waitSign")>';
		}else{
			s += '<div class="col-md-3 sameColor" style="margin-left:6%;margin-bottom: 10px;" onclick=orderDeail('+o.recordNo+',"all")>';
		}
		s += '<div class="row">';
		s += '<div class="col-md-10">';
		s += '<p class="pp">'+o.buildingNo+'栋'+o.unit+'单元'+o.houseNo+'室</p></div></div>';
		s += '<div class="row coco">';
		s += '<div class="col-md-5" style="margin-top:38px;margin-bottom:40px;width:28%;margin-left:4%;">';
		s += '<img src="'+o.houseTypePic+'" alt="" class="img-rounded formPic"></div>';
		s += '<div class="col-md-6" style="width:58%;margin-top:34px;margin-bottom:40px;margin-left:9%;">';
		s += '<div class="row sameTop"><div class="col-md-12"><span>下单时间：</span><span>'+time+'</span></div></div>';
		s += '<div class="row sameTop"><div class="col-md-12"><span>列表价格：</span><span>'+o.listPrice+'</span></div></div>';
		s += '<div class="row sameTop"><div class="col-md-12"><span>购买价格：</span><span>'+o.buyPrice+'</span></div></div>';
		if(o.orderStatus == "待签约"){
			s += '<div class="row sameTop"><span class="sameA" style="background-color:#f4cb0b;">'+o.orderStatus+'</span></div></div></div></div>';
		}else if(o.orderStatus == "待审核"){
			s += '<div class="row sameTop"><span class="sameA" style="background-color:#f43a3a;">'+o.orderStatus+'</span></div></div></div></div>';
		}else if(o.orderStatus == "待付款"){
			s += '<div class="row sameTop"><span class="sameA" style="background-color:#ff9666;">'+o.orderStatus+'</span></div></div></div></div>';
		}else if(o.orderStatus == "已撤销"){
			s += '<div class="row sameTop"><span class="sameA" style="background-color:#a6a6a6;">'+o.orderStatus+'</span></div></div></div></div>';
		}else if(o.orderStatus == "已拒绝"){
			s += '<div class="row sameTop"><span class="sameA" style="background-color:#cccccc;">'+o.orderStatus+'</span></div></div></div></div>';
		}else if(o.orderStatus == "已签约"){
			s += '<div class="row sameTop"><span class="sameA" style="background-color:#9fd269;">'+o.orderStatus+'</span></div></div></div></div>';
		}else if(o.orderStatus == "待签约"){
			s += '<div class="row sameTop"><span class="sameA" style="background-color:#9fd269;">'+o.orderStatus+'</span></div></div></div></div>';
		}else if(o.orderStatus == "付款待确认"){
			s += '<div class="row sameTop"><span class="sameA" style="background-color:#5fd260;">'+o.orderStatus+'</span></div></div></div></div>';
		}else{
			s += '<div class="row sameTop"><span class="sameA" style="background-color:#bbc1cb;">'+o.orderStatus+'</span></div></div></div></div>';
		}
		
	});
	if (data.length > 0) {
		$("#allOrder").html(s);
	} else {
		$("#allOrder").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
	}
}

//分页
function toPage() {
	layui.use([ 'laypage', 'layer' ], function() {
		var layer = layui.layer, laypage = layui.laypage;
		//调用分页
		laypage({
			cont : 'pagedAll',
			pages : totalPageAllAppoint //得到总页数
			,
			curr : currentPageAllAppoint,
			skip : true,
			jump : function(obj, first) {
				currentPageAllAppoint = obj.curr;
				startAllAppoint = (obj.curr - 1) * limitAllAppoint;
				if (!first) { //一定要加此判断，否则初始时会无限刷新
					getAllOrderData();
				}
			}
		});

	});
};
//待审核
function getCheckOrderData(){
	$.ajax({
		type : "post",
		url : "wait_check_order_data",
		async:false,
		data : {
			start: startAllAppointCheck , limit:limitAllAppointCheck
			},
		success : function(data) {
			setCheckOrderInfo(data.root);
			startAllAppointCheck = data.currentResult;
			totalPageAllAppointCheck = data.totalPage;
		}
	});
}
function setCheckOrderInfo(data){
	//console.log(data);
	var s = "";
	$.each(data,function(v, o) {
		var time = o.applyTime.substring(0,10); 
		s += '<div class="col-md-3 sameColor" style="margin-left:6%;margin-bottom: 10px;" onclick=orderDeail('+o.recordNo+',"willCheck")>';
		s += '<div class="row">';
		s += '<div class="col-md-10">';
		s += '<p class="pp">'+o.buildingNo+'栋'+o.unit+'单元'+o.houseNo+'室</p></div></div>';
		s += '<div class="row coco">';
		s += '<div class="col-md-3" style="width:28%;margin-top:34px;margin-bottom:40px;margin-left:4%;">';
		s += '<img src="'+o.houseTypePic+'" alt="" class="img-rounded formPic"></div>';
		s += '<div class="col-md-7" style="width:58%;margin-left:9%;margin-top:40px;margin-bottom:40px;">';
		s += '<div class="row sameTop"><div class="col-md-12"><span>下单时间：</span><span>'+time+'</span></div></div>';
		s += '<div class="row sameTop"><div class="col-md-12"><span>列表价格：</span><span>'+o.listPrice+'</span></div></div>';
		s += '<div class="row sameTop"><div class="col-md-12"><span>购买价格：</span><span>'+o.buyPrice+'</span></div></div>';
		s += '</div></div></div>';
	});
	if (data.length > 0) {
		$("#checkOrder").html(s);
	} else {
		$("#checkOrder").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
	}
}
function toPageCheck(){
	layui.use([ 'laypage', 'layer' ], function() {
		var layer = layui.layer, laypage = layui.laypage;
		//调用分页
		laypage({
			cont : 'pagedCheck',
			pages : totalPageAllAppointCheck //得到总页数
			,
			curr : currentPageAllAppointCheck,
			skip : true,
			jump : function(obj, first) {
				currentPageAllAppointCheck = obj.curr;
				startAllAppointCheck = (obj.curr - 1) * limitAllAppointCheck;
				if (!first) { //一定要加此判断，否则初始时会无限刷新
					getCheckOrderData();
				}
			}
		});

	});
}

//待付款
function getWaitPayOrderData(){
	$.ajax({
		type : "post",
		url : "wait_pay_money_order_data",
		async:false,
		data : {
			start: startAllAppointWaitPay , limit:limitAllAppointWaitPay
			},
		success : function(data) {
			setWaitPayOrderInfo(data.root);
			startAllAppointWaitPay = data.currentResult;
			totalPageAllAppointWaitPay = data.totalPage;
		}
	});
}
function setWaitPayOrderInfo(data){
	console.log(data);
	var s = "";
	$.each(data,function(v, o) {
		var time = o.applyTime.substring(0,10); 
		s += '<div class="col-md-3 sameColor" style="margin-left:6%;margin-bottom: 10px;" onclick=orderDeail('+o.recordNo+','+null+')>';
		s += '<div class="row">';
		s += '<div class="col-md-10">';
		s += '<p class="pp">'+o.buildingNo+'栋'+o.unit+'单元'+o.houseNo+'室</p></div></div>';
		s += '<div class="row coco">';
		s += '<div class="col-md-3" style="width:28%;margin-top:34px;margin-bottom:40px;margin-left:4%;">';
		s += '<img src="'+o.houseTypePic+'" alt="" class="img-rounded formPic"></div>';
		s += '<div class="col-md-7" style="margin-left:9%;margin-top:40px;margin-bottom:40px;width:58%;">';
		s += '<div class="row sameTop"><div class="col-md-12"><span>下单时间：</span><span>'+time+'</span></div></div>';
		s += '<div class="row sameTop"><div class="col-md-12"><span>列表价格：</span><span>'+o.listPrice+'</span></div></div>';
		s += '<div class="row sameTop"><div class="col-md-12"><span>购买价格：</span><span>'+o.buyPrice+'</span></div></div>';
		s += '</div></div></div>';
	});
	if (data.length > 0) {
		$("#waitPay").html(s);
	} else {
		$("#waitPay").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
	}
}
function toPageWaitPay(){
	layui.use([ 'laypage', 'layer' ], function() {
		var layer = layui.layer, laypage = layui.laypage;
		//调用分页
		laypage({
			cont : 'pagedWaitPay',
			pages : totalPageAllAppointWaitPay ,//得到总页数
			curr : currentPageAllAppointWaitPay,
			skip : true,
			jump : function(obj, first) {
				currentPageAllAppointWaitPay = obj.curr;
				startAllAppointWaitPay = (obj.curr - 1) * limitAllAppointWaitPay;
				if (!first) { //一定要加此判断，否则初始时会无限刷新
					getWaitPayOrderData();
				}
			}
		});

	});
}

//待签约
function getWaitSignOrderData(){
	$.ajax({
		type : "post",
		url : "wait_sign_order_data",
		async:false,
		data : {
			start: startAllAppointWaitSign , limit:limitAllAppointWaitSign
			},
		success : function(data) {
			setWaitSignOrderInfo(data.root);
			startAllAppointWaitSign = data.currentResult;
			totalPageAllAppointWaitSign = data.totalPage;
		}
	});
}
function setWaitSignOrderInfo(data){
	console.log(data);
	var s = "";
	$.each(data,function(v, o) {
		var time = o.applyTime.substring(0,10); 
		s += '<div class="col-md-3 sameColor" style="margin-left:6%;margin-bottom: 10px;" onclick=orderOtherDeail('+o.recordNo+',"waitSign","waitSign")>';
		s += '<div class="row">';
		s += '<div class="col-md-10">';
		s += '<p class="pp">'+o.buildingNo+'栋'+o.unit+'单元'+o.houseNo+'室</p></div></div>';
		s += '<div class="row coco">';
		s += '<div class="col-md-3" style="margin-left:4%;margin-top:34px;margin-bottom:40px;width:28%;">';
		s += '<img src="'+o.houseTypePic+'" alt="" class="img-rounded formPic"></div>';
		s += '<div class="col-md-7" style="margin-left:9%;margin-top:40px;margin-bottom:40px;width:58%;">';
		s += '<div class="row sameTop"><div class="col-md-12"><span>下单时间：</span><span>'+time+'</span></div></div>';
		s += '<div class="row sameTop"><div class="col-md-12"><span>列表价格：</span><span>'+o.listPrice+'</span></div></div>';
		s += '<div class="row sameTop"><div class="col-md-12"><span>购买价格：</span><span>'+o.buyPrice+'</span></div></div>';
		s += '</div></div></div>';
	});
	if (data.length > 0) {
		$("#waitSign").html(s);
	} else {
		$("#waitSign").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
	}
}
function toPageWaitSign(){
	layui.use([ 'laypage', 'layer' ], function() {
		var layer = layui.layer, laypage = layui.laypage;
		//调用分页
		laypage({
			cont : 'pagedSign',
			pages : totalPageAllAppointWaitSign ,//得到总页数
			curr : currentPageAllAppointWaitSign,
			skip : true,
			jump : function(obj, first) {
				currentPageAllAppointWaitSign = obj.curr;
				startAllAppointWaitSign = (obj.curr - 1) * limitAllAppointWaitSign;
				if (!first) { //一定要加此判断，否则初始时会无限刷新
					getWaitSignOrderData();
				}
			}
		});

	});
}

//已签约
function getSignedOrderData(){
	$.ajax({
		type : "post",
		url : "already_sign_order_data",
		async:false,
		data : {
			start: startAllAppointWaitSigned , limit:limitAllAppointWaitSigned
			},
		success : function(data) {
			seSignedOrderInfo(data.root);
			startAllAppointWaitSigned = data.currentResult;
			totalPageAllAppointWaitSigned = data.totalPage;
		}
	});
}
function seSignedOrderInfo(data){
	console.log(data);
	var s = "";
	$.each(data,function(v, o) {
		var time = o.applyTime.substring(0,10); 
		s += '<div class="col-md-3 sameColor" style="margin-left:6%;margin-bottom: 10px;" onclick=orderDeail('+o.recordNo+','+null+')>';
		s += '<div class="row">';
		s += '<div class="col-md-10">';
		s += '<p class="pp">'+o.buildingNo+'栋'+o.unit+'单元'+o.houseNo+'室</p></div></div>';
		s += '<div class="row coco">';
		s += '<div class="col-md-3" style="margin-left:4%;margin-top:34px;margin-bottom:40px;width:28%">';
		s += '<img src="'+o.houseTypePic+'" alt="" class="img-rounded formPic"></div>';
		s += '<div class="col-md-7" style="margin-left:9%;margin-top:40px;margin-bottom:40px;width:58%;">';
		s += '<div class="row sameTop"><div class="col-md-12"><span>下单时间：</span><span>'+time+'</span></div></div>';
		s += '<div class="row sameTop"><div class="col-md-12"><span>列表价格：</span><span>'+o.listPrice+'</span></div></div>';
		s += '<div class="row sameTop"><div class="col-md-12"><span>购买价格：</span><span>'+o.buyPrice+'</span></div></div>';
		s += '</div></div></div>';
	});
	if (data.length > 0) {
		$("#waitSigned").html(s);
	} else {
		$("#waitSigned").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
	}
}
function toPageSigned(){
	layui.use([ 'laypage', 'layer' ], function() {
		var layer = layui.layer, laypage = layui.laypage;
		//调用分页
		laypage({
			cont : 'pagedSigned',
			pages : totalPageAllAppointWaitSigned ,//得到总页数
			curr : currentPageAllAppointWaitSigned,
			skip : true,
			jump : function(obj, first) {
				currentPageAllAppointWaitSigned = obj.curr;
				startAllAppointWaitSigned = (obj.curr - 1) * limitAllAppointWaitSigned;
				if (!first) { //一定要加此判断，否则初始时会无限刷新
					getSignedOrderData();
				}
			}
		});

	});
}

//已撤销
function getRevokeOrderData(){
	$.ajax({
		type : "post",
		url : "already_revoke_order_data",
		async:false,
		data : {
			start: startAllAppointRevoke , limit:limitAllAppointRevoke
			},
		success : function(data) {
			setRevokeOrderInfo(data.root);
			startAllAppointRevoke = data.currentResult;
			totalPageAllAppointRevoke = data.totalPage;
		}
	});
}
function setRevokeOrderInfo(data){
	console.log(data);
	var s = "";
	$.each(data,function(v, o) {
		var time = o.applyTime.substring(0,10); 
		s += '<div class="col-md-3 sameColor" style="margin-left:6%;margin-bottom: 10px;" onclick=orderDeail('+o.recordNo+','+null+')>';
		s += '<div class="row">';
		s += '<div class="col-md-10">';
		s += '<p class="pp">'+o.buildingNo+'栋'+o.unit+'单元'+o.houseNo+'室</p></div></div>';
		s += '<div class="row coco">';
		s += '<div class="col-md-3" style="margin-left:4%;margin-top:34px;margin-bottom:40px;width:28%;">';
		s += '<img src="'+o.houseTypePic+'" alt="" class="img-rounded formPic"></div>';
		s += '<div class="col-md-7" style="margin-left:9%;margin-top:40px;margin-bottom:40px;width:58%;">';
		s += '<div class="row sameTop"><div class="col-md-12"><span>下单时间：</span><span>'+time+'</span></div></div>';
		s += '<div class="row sameTop"><div class="col-md-12"><span>列表价格：</span><span>'+o.listPrice+'</span></div></div>';
		s += '<div class="row sameTop"><div class="col-md-12"><span>购买价格：</span><span>'+o.buyPrice+'</span></div></div>';
		s += '</div></div></div>';
	});
	if (data.length > 0) {
		$("#revoke").html(s);
	} else {
		$("#revoke").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
	}
}
function toPageRevoke(){
	layui.use([ 'laypage', 'layer' ], function() {
		var layer = layui.layer, laypage = layui.laypage;
		//调用分页
		laypage({
			cont : 'pagedRevoke',
			pages : totalPageAllAppointRevoke ,//得到总页数
			curr : currentPageAllAppointRevoke,
			skip : true,
			jump : function(obj, first) {
				currentPageAllAppointRevoke = obj.curr;
				startAllAppointRevoke = (obj.curr - 1) * limitAllAppointRevoke;
				if (!first) { //一定要加此判断，否则初始时会无限刷新
					getRevokeOrderData();
				}
			}
		});

	});
}

//已拒绝
function getRefuseOrderData(){
	$.ajax({
		type : "post",
		url : "already_refuse_order_data",
		async:false,
		data : {
			start: startAllAppointRefuse , limit:limitAllAppointRefuse
			},
		success : function(data) {
			setRefuseOrderInfo(data.root);
			startAllAppointRefuse = data.currentResult;
			totalPageAllAppointRefuse = data.totalPage;
		}
	});
}
function setRefuseOrderInfo(data){
	console.log(data);
	var s = "";
	$.each(data,function(v, o) {
		var time = o.applyTime.substring(0,10); 
		s += '<div class="col-md-3 sameColor" style="margin-left:6%;margin-bottom: 10px;" onclick=orderDeail('+o.recordNo+','+null+')>';
		s += '<div class="row">';
		s += '<div class="col-md-10">';
		s += '<p class="pp">'+o.buildingNo+'栋'+o.unit+'单元'+o.houseNo+'室</p></div></div>';
		s += '<div class="row coco">';
		s += '<div class="col-md-3" style="margin-left:4%;margin-top:34px;margin-bottom:40px;width:28%;">';
		s += '<img src="'+o.houseTypePic+'" alt="" class="img-rounded formPic"></div>';
		s += '<div class="col-md-7" style="margin-left:9%;margin-top:40px;margin-bottom:40px;width:58%;">';
		s += '<div class="row sameTop"><div class="col-md-12"><span>下单时间：</span><span>'+time+'</span></div></div>';
		s += '<div class="row sameTop"><div class="col-md-12"><span>列表价格：</span><span>'+o.listPrice+'</span></div></div>';
		s += '<div class="row sameTop"><div class="col-md-12"><span>购买价格：</span><span>'+o.buyPrice+'</span></div></div>';
		s += '</div></div></div>';
	});
	if (data.length > 0) {
		$("#myResuse").html(s);
	} else {
		$("#myResuse").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
	}
}
function toPageRefuse(){
	layui.use([ 'laypage', 'layer' ], function() {
		var layer = layui.layer, laypage = layui.laypage;
		//调用分页
		laypage({
			cont : 'pagedRefuse',
			pages : totalPageAllAppointRefuse ,//得到总页数
			curr : currentPageAllAppointRefuse,
			skip : true,
			jump : function(obj, first) {
				currentPageAllAppointRefuse = obj.curr;
				startAllAppointRefuse = (obj.curr - 1) * limitAllAppointRefuse;
				if (!first) { //一定要加此判断，否则初始时会无限刷新
					getRefuseOrderData();
				}
			}
		});

	});
}

//订单详情
function orderDeail(orderNo,buttonSign){
	//console.log(buttonSign);
	window.location = "for_order_deail_page_set_orderId?orderId="+orderNo+"&buttonSign="+buttonSign;
		
	//console.log(orderNo);
	//console.log(buttonSign);
	/*$.ajax({
		type : "post",
		url : "for_order_deail_page_set_orderId",
		async:false,
		data : {
			orderId: orderNo,buttonSign:buttonSign
			},
		success : function(data) {
			//console.log(data.url);
			//window.location = data.url;
		}
	});*/
}

//付款待确认，签约待确认
function orderOtherDeail(orderNo,buttonSign,whichButtonHidSign){
	window.location = "for_order_other_deail_page_set_orderId?orderId="+orderNo+"&buttonSign="+buttonSign+"&whichButtonHidSign="+whichButtonHidSign;
}