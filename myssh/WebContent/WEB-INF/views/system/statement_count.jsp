<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>

	<head>
		<meta charset="UTF-8">
		<title>首页</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/begtable.css" />
	</head>

	<body>
		<div style="margin: 15px;">
			<blockquote class="layui-elem-quote">
			<form class="layui-form">
			  	  <div class="layui-form-item">
			  	  		<div class="layui-inline">
							<select name="province" id="province" lay-filter="province">
								<option value="">请选择</option> 
	                        	<c:forEach var="p" items="${provinces}">
									<option value="${p.cityId }">${p.cityName }</option>
								</c:forEach>
							</select>
						</div>
			  	  		<div class="layui-inline">
							<select name="market" id="market" lay-filter="market">
								<option value="">请选择</option> 
							</select>
						</div>
		  	  			<div class="layui-inline">
							<select name="area" id="area" lay-filter="area">
								<option value="">请选择</option> 
							</select>
						</div>
					    <div class="layui-inline">
							<input type="text"  id="start" lay-verify="date" placeholder="起始时间" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">
						</div>——
						<div class="layui-inline">
							<input type="text"  id="end" lay-verify="date" placeholder="结束时间" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">
						</div>
						<div class="layui-inline">
							<a href="javascript:;" class="layui-btn" id="search">
								<i class="layui-icon"></i> 搜索
							</a>
						</div>
	  				</div>
	  				</form>
				<fieldset class="layui-elem-field">
		 		<legend>对账单</legend>
				<table class="layui-table">
				  <colgroup>
				    <col width="150">
				    <col width="150">
				    <col width="200">
				    <col>
				  </colgroup>
				  <thead>
			      	<tr>
			      		<td>项目</td>
			      		<td>佣金类型</td>
			      		<td>佣金金额</td>
			      		<td>门店</td>
			      		<td>经纪人</td>
			      		<td>佣金来源</td>
			      		<td>带看时间</td>
			      		<td>签约时间</td>
			      		<td>结款时间</td>
			      		<td>确认到款<br>时间</td>
			      		<td>结款<br>状态</td>
			      		<td>操作</td>
			      	</tr>
				  </thead>
				  <tbody id="layuitbody">
				  	<tr>
				  		<td colspan="12">暂无数据</td>
				  	</tr>
				  </tbody>
				</table> 
				</fieldset>
			</blockquote>
		</div>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/plugins/layui/layui.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
		<script>
			var $form;
			var form;
			var $;
			layui.config({
				base: 'static/layui/js/'
			});
			layui.use(['form','layer','laydate'], function() {
					form = layui.form(),
					layer = layui.layer,
					laydate = layui.laydate,
					$ = layui.jquery,
					$form = $('form');
				//省市联动
				form.on('select(province)', function(data) {
					loadCityForShow(data.value);
	            });
				form.on('select(market)', function(data) {
					loadAreaForShow(data.value);
	            });
				//加载数据
				getProjectStatementForms();
				$(document).on('click','#search',function(){
					getProjectStatementForms();
				})
				
				
			});
			function getProjectStatementForms(){
				var city = "";
				if($('#province').val() != ""){
					city = $('#province').val() + "-" + $('#market').val() + "-" +$('#area').val();
				}
				$.ajax({
					type : "post",
					async : false,
					url : "system_statement_count",
					data : {
						startTime : $("#start").val(),
						endTime : $("#end").val(),
						city : city,
					},
					success : function(data){
						fillProjectStatementInfo(data);
					},
				error : function(){
					alert("请求失败")
				}
				});
			}
			
			function fillProjectStatementInfo(data){
				var s = "";
				$.each(data, function(v, o){
					console.log(o);
					s += '<tr><td>' + o.projectName+ '</td>';
					
					if(o.moneyType == "0")
						s += '<td>分销佣金</td>';
						else if(o.moneyType == 1)
						s += '<td>带看佣金</td>';
						else
						s += '<td>-无-</td>';
						
					if(o.moneyType == 0)
						s += '<td>' + o.fenXiaoMoney + '</td>';
						else if(o.moneyType == 1)
						s += '<td>' + o.daiKanMoney + '</td>';
						else
						s += '<td>-无-</td>';	
						
					if(o.shopName != null && o.shopName != "")	
						s += '<td>' + o.shopName + '</td>';
						else
							s += '<td>-无-</td>';
					if(o.agentUserId != null && o.agentUserId != "")		
						s += '<td>' + o.agentUserId + '</td>';
						else
							s += '<td> -无- </td>';	
					s += '<td>' + o.houseInfo + '</td>';
					if(o.applyTime != null)
						s += '<td>' + o.applyTime.substring(0, 10) + '</td>';
						else
							s += '<td>***</td>';
					if(o.contractConfirmTime != null)
						s += '<td>' + o.contractConfirmTime.substring(0, 10) + '</td>';
						else
							s += '<td>***</td>';
					if(o.systemPayConfirmTime != null)
						s += '<td>' + o.systemPayConfirmTime.substring(0, 10) + '</td>';
						else
							s += '<td>***</td>';
					if(o.systemReceiveConfirmTime != null)
						s += '<td>' + o.systemReceiveConfirmTime.substring(0, 10) + '</td>';
						else
							s += '<td>***</td>';
					
					if(o.isSystemPayConfirm == null || o.isSystemPayConfirm == 0)
						s += '<td>未结款</td>';
					if(o.isSystemPayConfirm == 1)
						s += '<td>已结款</td>';
					if(o.isSystemPayConfirm == 2)
						s += '<td>已完成</td>';
					if(o.isSystemPayConfirm == 2)
						s += '<td><a href="edit_commission_statement?houseNum='+o.houseNum+'&isConfirm=0" style="background:#FF4545; width:70px; height:25px; line-height:25px; color:#ffffff; border:0; display: inline-block; border-radius:4px" id="callOff">取消到款</a></td></tr>';
						else
							s += '<td><a href="edit_commission_statement?houseNum='+o.houseNum+'&isConfirm=1" style="background:#0c95db;  width:70px; height:25px; line-height:25px; color:#ffffff; border:0; display: inline-block; border-radius:4px" id="callOn">确认到款</a></td></tr>';
				});
				if (data.length > 0) {
					$("#layuitbody").empty();
					$(s).appendTo($("#layuitbody")); 
				} else {
					
				}
				
			}
			
			
			function loadCityForShow(data1){
				$form.find('select[name=area]').html("<option value='0'>请选择</option>");
				 form.render();
				var option = "<option value='0'>请选择</option>";
				
				$.ajaxSetup({
			        async: false
			 	});
				$.post("menu_list_city_area",
						{
						    "shengOrShi":data1
						  },
						  function(data){  
							 var data = eval('('+ data +')');
							 for(var i=0;i<data.root.length;i++){
								 option += "<option value='"+data.root[i].cityId+"'>"+data.root[i].cityName+"</option> "
							 }
							 
						  });
				 $form.find('select[name=market]').html(option);
				 form.render();
			}
			
			function loadAreaForShow(data1){
				var option = "<option value='0'>请选择</option>";
				
				$.ajaxSetup({
			        async: false
			 	});
				$.post("menu_list_city_area",
						{
						    "shengOrShi":data1
						  },
						  function(data){  
							 var data = eval('('+ data +')');
							 for(var i=0;i<data.root.length;i++){
								 option += "<option value='"+data.root[i].cityId+"'>"+data.root[i].cityName+"</option> "
							 }
							 
						  });
				 $form.find('select[name=area]').html(option);
				 form.render();
			}
		</script>

	</body>

</html>