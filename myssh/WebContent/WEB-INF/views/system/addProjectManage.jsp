<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>添加案场</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	</head>
	<script type="text/javascript">
	</script>
	<body>
		<div style="margin: 15px;">
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
				<legend>添加案场</legend>
			</fieldset>
			<form class="layui-form" action="addProject" method="post">
				
				<div class="layui-form-item">
					<label class="layui-form-label">案场名称</label>
					<div class="layui-input-block">
						<input type="text" name="projectName" lay-verify="required" autocomplete="off" placeholder="请输入案场名称" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">所在地</label>
					<div class="layui-input-inline">
						<select name="province" id="province" lay-filter="province">
						<option value="">请选择</option> 
                        	<c:forEach var="p" items="${provinces}">
	                            <option value="${p.cityId }">${p.cityName }</option> 
                        	</c:forEach>
						</select>
					</div>
					<div class="layui-input-inline">
						<select name="market" id="market" lay-filter="market">
							<option value="">请选择</option> 
						</select>
					</div>
					<div class="layui-input-inline">
						<select name="area" id="area" lay-filter="area">
							<option value="">请选择</option> 
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">案场助理姓名</label>
					<div class="layui-input-block">
						<input type="text" name="userCaption" lay-verify="required" autocomplete="off" placeholder="请输入案场助理姓名" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">案场助理电话</label>
					<div class="layui-input-block">
						<input type="text" name="phone" id="phone" lay-verify="phone" autocomplete="off" placeholder="请输入助理电话" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<a href="<%=request.getContextPath()%>/system_to_projectList" class="layui-btn layui-btn-primary">返回</a>
					</div>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/plugins/layui/layui.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
		<script>
			var $form;
			var form;
			var $;
			layui.use(['form','jquery'], function() {
				 $ = layui.jquery;
				form = layui.form(),
				layer = layui.layer,
				$form = $('form');
				
				//省市联动
				form.on('select(province)', function(data) {
					loadCityForShow(data.value);
	            });
				form.on('select(market)', function(data) {
					loadAreaForShow(data.value);
	            });
				
				//监听提交
				form.on('submit(demo1)', function(data) {
					return true;
				});
				//检测电话号码是否重复
				$(document).on('change','#phone',function(){
					var phoneNum = $("#phone").val();
        			if(phoneNum){
	        			$.post("find_only_phone",
	    						{
	    						    "phoneNum":phoneNum
	    						  },
	    						  function(data){  
	    							if(data.status==200){//表示号码没有重复
	    								layer.alert('该号码可以使用');
	    							}
	    							if(data.status==202){
	    								layer.alert(data.message);
	    								 $("#phone").val('');
	    							}
	    						  });
        			}else{
        				
        			}
        			
        		
				})
				
			});
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