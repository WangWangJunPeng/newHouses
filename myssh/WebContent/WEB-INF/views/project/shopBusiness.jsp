<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="icon" href="static/images/titleLogo.png" />
<title>门店管理后台</title>
<!-- <link rel="stylesheet" type="text/css" href="static/css/reset.css">
    <link rel="stylesheet" type="text/css" href="static/css/commend.css">
    <link rel="stylesheet" type="text/css" href="static/css/shopBusiness.css"> -->
<!-- <link rel="stylesheet" type="text/css" href="static/lib/laydate/need/laydate.css" />  -->
<link rel="stylesheet" href="static/layui/plugins/layui/css/layui.css"
	media="all" />
	<link rel="stylesheet" type="text/css" href="static/css/anim.css">
<script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>

<!-- <script type="text/javascript" src="static/lib/laydate/laydate.js"></script> -->
<script type="text/javascript" src="static/layui/plugins/layui/layui.js"></script>

</head>
<body>
	<div class="admin-main">
		<div class="layui-form-pane"
			style="margin-top: 20px; margin-left: 10px;text-align: left;">
			<form>
				<div class="layui-input-inline">
					<input type="text" id="cusName" placeholder="客户姓名"
						autocomplete="off" class="layui-input">
				</div>
				<div class="layui-inline">
					<input class="layui-input" placeholder="开始时间" id="start"
						onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
				</div>
				<div class="layui-inline">
					<input class="layui-input" placeholder="结束时间" id="end"
						onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
				</div>
				<button class="layui-btn" type="button" id="searchData"
					onclick="selectData()">搜索</button>
			</form>
			<fieldset class="layui-elem-field" style="margin-top: 10px;">
				<div class="layui-field-box layui-form">
					<table class="layui-table admin-table" id="shoperBusiness">

					</table>
					<div id="myAnimation">
						<div class="wrapper">
							<div class="load-bar">
								<div class="load-bar-inner" data-loading="0">
									<!-- <span id="counter"></span> -->
								</div>
							</div>
							<p>玩命加载中...</p>
						</div>
					</div>
				</div>
			</fieldset>
			<div class="admin-table-page" style="text-align: left;">
				<div id="paged" class="page"></div>
			</div>
		</div>

	</div>
	<%-- <%@include file="../publicpage/shoppublicpage.jsp" %> --%>
	<!-- <div class="container">
        
            <p style="padding-top:10px;margin-left:10px;font-size:12px;color:#0c95db;"><a href="to_store_index" style="font-size:12px;color:#0c95db;">首页</a>&nbsp;-&nbsp;业务</p>
      
        <div class="box">
            <form>
                <input id="cusName" type="text" placeholder="客户姓名" class="name-in" />
                <input type="text" class="laydate-icon time-in" placeholder="起始时间" style="width:180px;" id="start" />
                <b style="margin-left:10px;">--</b>
                <input type="text" class="laydate-icon time-in" placeholder="结束时间" style="width:180px;" id="end"/>
                <input type="button" value="搜索" onclick="selectData()" />
            </form>
           
            <div class="table-list">
                <table cellpadding="8" id="shoperBusiness">
            	</table>
            </div>           
        </div>
        
        <div style="height:10px;"></div>
    </div> -->
</body>
<script type="text/javascript">
	$(document).ready(function(){
		/* $('#shoperBusiness').load("upLoadDemo.jsp"); */
		
		getShoperBusiness();
		
		
	});
	
	//分页参数设置
	var startAllAppoint = 0;
	var limitAllAppoint = 10;
	var currentPageAllAppoint = 1;
	var totalPageAllAppoint = 0;
function getShoperBusiness(){
	toAnimationStart(0);
	$.ajax({
		type:"post",
		url:"list_shoper_business_info",
		async : true,
		/* beforeSend: function(){
			$('#shoperBusiness').load("upLoadDemo.jsp");
		}, */
		data:{
			cusOrProName:$("#cusName").val(), 
			startTime:$("#start").val(),
			endTime:$("#end").val(),
			start : startAllAppoint,
			limit: limitAllAppoint
			},
		/* beforeSend: function(){
				$('#shoperBusiness').load("upLoadDemo.jsp");
			}, */
		success:function(data){
			data = eval("("+data+")");
			setShoperBusiness(data.root);
			startAllAppoint = data.currentResult;
			totalPageAllAppoint = data.totalPage;
			//console.log("hahah:"+totalPageAllAppoint);
			toAnimationStart(99);
			toPage();
		}
	});
}

function toAnimationStart(current){
	var interval = setInterval(increment,100);
	  

	  function increment(){
	    current++;
	    //$('#counter').html(current+'%'); 
	    if(current == 100) { current = 0; }
	  }

	  $('.load-bar').mouseover(function(){
	            clearInterval(interval);
	  }).mouseout(function(){
	      interval = setInterval(increment,100);
	         });
}
	

function setShoperBusiness(data){
	var s = "<tr><th>姓名</th><th>项目名称</th><th>当前状态</th><th>备案时间</th></tr>";
	$.each(data,function(v,o){
			s+='<tr><td>'+o.shopCustomerName+'</td>';
			s+='<td>'+o.projectName+'<input type="hidden" value="' + o.projectId + '" name="projectId"></td>';
			if(o.applyStatus == 0){//已备案
			s+='<td>已备案</td>';
			}else if(o.applyStatus == 1){//已到访
			s+='<td>已到访</td>';
			}else if(o.applyStatus == 2){//已成交
			s+='<td>已成交</td>';
			}
			s+='<td>'+o.applyTime+'</td></tr>';
	});

	if(data.length>0){
		/* $('#shoperBusiness').children().remove(); */
		$("#shoperBusiness").empty();
		$("#shoperBusiness").html(s);
	}else{
		$("#shoperBusiness").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
	}
	$("#myAnimation").empty();
	$('#searchData').html('搜索');
}

function selectData(){
	$('#searchData').html('<i class="layui-icon">&#xe63d;</i>');
	getShoperBusiness();
	currentPageAllAppoint = 1;
	
}

        	
 layui.use(['form', 'layedit', 'laydate','layer', 'laypage', 'element'], function(){
  var layer = layui.layer
  ,laypage = layui.laypage
  ,element = layui.element()
  ,form = layui.form()
  ,layedit = layui.layedit
   ,laydate = layui.laydate;
  
 
  
  var start = {
		    min: laydate.now()
		    ,max: '2099-06-16 23:59:59'
		    ,istoday: false
		    ,choose: function(datas){
		      end.min = datas; //开始日选好后，重置结束日的最小日期
		      end.start = datas //将结束日的初始值设定为开始日
		    }
		  };
		  
		  var end = {
		    min: laydate.now()
		    ,max: '2099-06-16 23:59:59'
		    ,istoday: false
		    ,choose: function(datas){
		      start.max = datas; //结束日选好后，重置开始日的最大日期
		    }
		  };
}); 
        
function toPage() {

	layui.use(['form', 'laypage', 'layedit','layer', 'laydate'], function() {
		var form = layui.form(),
			layer = layui.layer,
			layedit = layui.layedit,
			laydate = layui.laydate,
			laypage = layui.laypage;

						// 调用分页
						laypage({
							cont : 'paged',
							pages : totalPageAllAppoint, // 得到总页数
							curr : currentPageAllAppoint,
							skip : true,
							jump : function(obj, first) {
								currentPageAllAppoint = obj.curr;
								startAllAppoint = (obj.curr - 1)* limitAllAppoint;
								if (!first) { // 一定要加此判断，否则初始时会无限刷新
									getShoperBusiness();
								}
							}
						});

					});
};
    </script>
</html>