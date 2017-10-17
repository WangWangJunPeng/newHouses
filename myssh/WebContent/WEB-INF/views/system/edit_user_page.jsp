<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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

	<body>
		<div style="margin: 15px;">
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
				<legend>账号编辑</legend>
			</fieldset>

			<form class="layui-form" action="update_user_info" method="post">
				<div class="layui-form-item">
					<label class="layui-form-label">经济人类型</label>
					<div class="layui-input-block">
						<c:if test="${roleId == 1 || roleId == 2 }">
							<c:choose>
								<c:when test="${roleId==1}"><input type="radio" value="1" id="agent" name="role" checked="checked" title="中介经纪人"></c:when>
								<c:otherwise><input type="radio" value="1" id="agent" name="role" title="中介经纪人"></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${roleId==2}"><input type="radio" value="2" id="shopManager" name="role" checked="checked" title="店长"></c:when>
								<c:otherwise><input type="radio" value="2" id="shopManager" name="role" title="店长"></c:otherwise>
							</c:choose>
						</c:if>
						<c:if test="${roleId == 3 || roleId == 4 }">
							<c:choose>
								<c:when test="${roleId==3}"><input type="radio" value="3" id="counselor" name="role" checked="checked" title="置业顾问"></c:when>
								<c:otherwise><input type="radio" value="3" id="counselor" name="role" title="置业顾问"></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${roleId==4}"><input type="radio" value="4" id="assistant" name="role" checked="checked" title="案场助理"></c:when>
								<c:otherwise><input type="radio" value="4" id="assistant" name="role" title="案场助理"></c:otherwise>
							</c:choose>
						</c:if>
						<c:if test="${roleId==6}">
							<input type="radio" value="6" id="assistant" name="role" checked="checked" title="合伙人">
						</c:if>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">姓名</label>
					<div class="layui-input-block">
						<input type="text" name="userCaption" id="name" value="${userCaption}" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">电话</label>
					<div class="layui-input-block">
						<input type="text" name="phone" id="phone" value="${phone}" class="layui-input" lay-verify="required|phone">
						<input type="text" name="userId" id="name" value="${userId}" style="display:none;">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">身份证号码</label>
					<div class="layui-input-block">
						<input type="text" name="idCard" id="id_card" value ="${idCard}" class="layui-input" lay-verify="required|identity">
						<input type="text" name="doSign" value="reset" style="display:none;">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<a href="<%=request.getContextPath()%>/all_user_info_page" class="layui-btn layui-btn-primary">返回</a>
					</div>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/plugins/layui/layui.js"></script>
		<script>
			layui.use(['form'], function() {
				var form = layui.form(),
				layer = layui.layer,
				$ = layui.jquery;

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

				//监听提交
				form.on('submit(demo1)', function(data) {
					return true;
				});
			});
		</script>
	</body>

</html>