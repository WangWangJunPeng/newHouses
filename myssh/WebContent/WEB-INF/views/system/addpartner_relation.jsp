<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>添加合伙人关联</title>
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
				<legend>新建合伙人关联</legend>
			</fieldset>

			<form class="layui-form" action="<%=request.getContextPath()%>/addPartner_relation" method="post">
			
				<div class="layui-form-item">
					<label class="layui-form-label">合伙人</label>
					<div class="layui-input-inline">
						<select name="userId" id="userId">
							<option value="">请选择</option>
                            <c:forEach items="${users}" var="u">
                            	<option value="${u.userId}">${u.userCaption}</option>
                            </c:forEach> 
						</select>
					</div>
				</div>
			
				<div class="layui-form-item" pane="">
				    <label class="layui-form-label">项目</label>
				    <div class="layui-input-inline">
				      	<input type="checkbox"  lay-skin="primary" lay-filter="projectBox">
					</div>
					<label class="layui-form-label">有效期限</label>
			 		<div class="layui-input-inline">
						<select name="Pvalidity">
	                        <c:forEach begin="0" end="99" var="num">
		                        <c:choose>
		                        	<c:when test="${num==99}">
		                        		<option value=${num} selected = "selected">${num}年</option>
		                        	</c:when>
		                        	<c:otherwise>
			                        	<option value=${num}>${num}年</option>
		                        	</c:otherwise>
		                        </c:choose>
	                        </c:forEach>
                        </select>
				    </div>
			  	</div>
				<div class="layui-form-item">
				 	<label class="layui-form-label"></label>
				    <div class="layui-input-block"  name="projectDiv">
				    </div>
				</div>
				<div class="layui-form-item" pane="">
				    <label class="layui-form-label">门店</label>
				    <div class="layui-input-inline">
				      <input type="checkbox"  lay-skin="primary" lay-filter="shopsBox">
				    </div>
				    <label class="layui-form-label">有效期限</label>
				    <div class="layui-input-inline">
				    	<select name="Svalidity">
	                        <c:forEach begin="0" end="99" var="num">
		                        <c:choose>
		                        	<c:when test="${num==99}">
		                        		<option value=${num} selected = "selected">${num}年</option>
		                        	</c:when>
		                        	<c:otherwise>
			                        	<option value=${num}>${num}年</option>
		                        	</c:otherwise>
		                        </c:choose>
	                        </c:forEach>
                        </select>
				    </div>
			  	</div>
				<div class="layui-form-item">
				 	<label class="layui-form-label"></label>
				    <div class="layui-input-block" name="shopDiv">
				    </div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<a href="<%=request.getContextPath()%>/toPartnerList" class="layui-btn layui-btn-primary">返回</a>
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
					$form = $('form');
				//监听提交
				form.on('submit(demo1)', function(data) {
					/* layer.alert(JSON.stringify(data.field), {
						title: '最终的提交信息'
					}) */
					return true;
				});
				
				//项目复选框选
				form.on('checkbox(projectBox)',function(data){
					$form.find('div[name=projectDiv]').html('');
					 form.render();
					var userId = $("#userId").val();
					if(userId){
						if(data.elem.checked){
							$.post("query_all",
	        						{
	        						    "status":1,
	        						  },
	        						  function(data){
	        							 var s =''; 
	        							 var data = eval('('+ data +')');
	        							 for(var i=0;i<data.total;i++){
	        								 s+='<input type="checkbox" name="projectIds"  title="'+data.root[i].projectName+'"  value="'+data.root[i].projectId+'">';
	        							 }
									 $form.find('div[name=projectDiv]').html(s);
									 form.render();
	        							 
	        						  });
						}
					}else{
						layer.msg('请选择合伙人');
					}
				});
				//门店复选框选
				form.on('checkbox(shopsBox)',function(data){
					$form.find('div[name=shopDiv]').html('');
					 form.render();
					var userId = $("#userId").val();
					if(userId){
						if(data.elem.checked){
							$.post("query_all",
	        						{
	        						    "status":2,
	        						  },
	        						  function(data){
	        							 var s =''; 
	        							 var data = eval('('+ data +')');
	        							 for(var i=0;i<data.total;i++){
	        								 s+='<input type="checkbox" name="shopIds"  title="'+data.root[i].shopName+'"  value="'+data.root[i].shopId+'">';
	        							 }
									 $form.find('div[name=shopDiv]').html(s);
									 form.render();
	        							 
	        						  });
						}
					}else{
						layer.msg('请选择合伙人');
					}
				});
				
				
			});
		</script>
	</body>

</html>