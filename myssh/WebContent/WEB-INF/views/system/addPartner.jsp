<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>表单</title>
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
		function aa(){
			console.log("sss");
		}
	</script>
	<body>
		<div style="margin: 15px;">
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
				<legend>新增合伙人</legend>
			</fieldset>
			<form class="layui-form" action="<%=request.getContextPath()%>/system_addPartner" method="post">

				<div class="layui-form-item">
					<label class="layui-form-label">合伙人姓名</label>
					<div class="layui-input-block">
						<input type="text" id="userCaption" name="userCaption" class="layui-input"  lay-verify="required">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">手机号码</label>
					<div class="layui-input-block">
						<input type="text"  id="phone" name="phone" class="layui-input"  lay-verify="required|phone	">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">身份证号码</label>
					<div class="layui-input-block">
						<input type="text"  id="idCard" name="idCard" class="layui-input"  lay-verify="required|identity">
					</div>
				</div>
		
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="demo1">提交</button>
						<a href="<%=request.getContextPath()%>/toPartnerList" class="layui-btn layui-btn-primary">返回</a>
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
			layui.use(['form','laydate','jquery'], function() {
				 $ = layui.jquery;
				form = layui.form(),
				layer = layui.layer,
				laydate = layui.laydate;
				$form = $('form');
				
				//监听提交
				form.on('submit(demo1)', function(data) {
				/* 	layer.alert(JSON.stringify(data.field), {
						title: '最终的提交信息'
					}) */
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
		</script>
	</body>

</html>