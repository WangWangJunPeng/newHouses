<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>修改合伙人关联关系</title>
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
				<legend>修改合伙人关联</legend>
			</fieldset>
			<form id="form11" class="layui-form" action="<%=request.getContextPath()%>/changePartner_relation" method="post">

				<div class="layui-form-item">
					<label class="layui-form-label">合伙人姓名</label>
					<div class="layui-input-block">
						<input type="text" name="userCaption" id="userCaption" value="${partner.partnerName}" class="layui-input" readonly="readonly">
                        <input type="hidden" name="userId" value="${partner.partnerId}">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">手机号码</label>
					<div class="layui-input-block">
						<input type="text" id="phone" name="phone" value="${partner.phone}" class="layui-input" lay-verify="phone">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">已关联案场</label>
					<div class="layui-input-block">
						<c:forEach var="p" items="${partner.projects}">
                        	<input type="checkbox" name="projectIds" title="${p.projectName}" checked="" value="${p.projectId }">
                        </c:forEach>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">已关联门店</label>
					<div class="layui-input-block">
						<c:forEach var="s" items="${partner.shops}">
                        	<input type="checkbox" name="shopIds" title="${s.shopName }" checked="" value="${s.shopId }">
                        </c:forEach>
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
				var phone1 = $("#phone").val();
				//监听提交
				form.on('submit(demo1)', function(data) {
					
					var partnerName = $("#userCaption").val();
    				var projects = "";
    				var shops = "";
    				$("input:checkbox[name='projectIds']").not("input:checked").each(function() {
    					projects += $(this).attr('title') + "    ";
    				});
    				$("input:checkbox[name='shopIds']").not("input:checked").each(function() {
    					shops += $(this).attr('title') + "    ";
    				});
    				var phoneNum = $("#phone").val();
    				var	context = "";
    				if(phoneNum != phone1){
    					context = "修改手机号码为:"+phoneNum+"<br/>";
    				}
    				if(projects!=""){
    					context += '取消案场关联:'+projects+'<br/>';
    				}
    				if(shops!=""){
    					context+='取消门店关联:'+shops;
    				}
					var cc = false;
					layer.open({
						  title: '您对 <u>'+partnerName+'</u> 做了以下修改:'
						  ,content: context
						  ,btn: ['确认', '取消']
						  ,btn1: function(index, layero){
						  document.getElementById("form11").submit();
						    return true;
						  }
						  ,btn2: function(index, layero){
						    return true;
						  }
						}); 
					return false;
				});
				
				
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
	    								$("#phone").val(phone1);
	    							}
	    						  });
        			}else{
        				
        			}
        			
        		
				})
				
			});
		</script>
	</body>

</html>