<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>

	<head>
		<meta charset="UTF-8">
		<title>分析图</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/begtable.css" />
	</head>

	<body>
		<fieldset class="layui-elem-field">
				<legend>新增父类目</legend>
				<div class="layui-field-box layui-form">
					<form class="layui-form" action="<%=request.getContextPath()%>/to_addAnalysisCategory1" method="post">
						<div class="layui-form-item">
					    	<label class="layui-form-label">类目名称</label>
						    <div class="layui-input-block" style="max-width:1000px;">
						      <input type="text" name="categoryName" lay-verify="title" autocomplete="off" placeholder="请输入类目名称" class="layui-input">
						    </div>
					  	</div>
						<div class="layui-form-item">
					    	<label class="layui-form-label">优先级</label>
						    <div class="layui-input-block" style="max-width:1000px;">
						      <input type="text" name="priority" lay-verify="title" autocomplete="off" placeholder="请输入优先级" class="layui-input">
						    </div>
					  	</div>
						<div class="layui-form-item">
					    	<label class="layui-form-label">是否定制</label>
						    <div class="layui-input-block" style="max-width:1000px;">
						      <input type="checkbox"  name="isMade" lay-skin="switch" lay-filter="switchTest" title="开关">
						    </div>
					  	</div>
					  	<div class="layui-form-item" id="projectDiv">
					  		<label class="layui-form-label">项目</label>
							<div class="layui-input-inline">
								<select name="projectId" id="projectId" lay-filter="projectId">
								<option value="">请选择项目</option>
								</select> 
							</div>
					  	</div>
					  	<div class="layui-form-item">
							<div class="layui-input-block">
								<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
								<a href="<%=request.getContextPath()%>/to_goToFatherPage" class="layui-btn layui-btn-primary">返回</a>
							</div>
						</div>
					</form>
				</div>
			</fieldset>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/plugins/layui/layui.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
		<script>
			layui.config({
				base: 'static/layui/js/'
			});
			var $form;
			var form;
			var $;
			layui.use(['form','jquery'], function() {
				 $ = layui.jquery;
				form = layui.form(),
				layer = layui.layer,
				$form = $('form');
					
				form.on('submit(demo1)', function(data) {
					return true;
				});
				showProject();
				var target=document.getElementById("projectDiv");
				target.style.display="none";
				form.on('switch(switchTest)', function(data){
					  var flag = data.elem.checked; //开关是否开启，true或者false
					  if(flag){
						  target.style.display="block";
					  }else{
						  target.style.display="none";
					  }
					}); 
				
				
			});
					function showProject(){
						var option = "<option value=''>请选择项目</option>";
						
						$.ajaxSetup({
					        async: false
					 	});
						$.post("to_getProjectIdList",
								{
								  },
								  function(data){  
									// var data = eval('('+ data +')');
									 for(var i=0;i<data.length;i++){
										 option += "<option value='"+data[i].projectId+"'>"+data[i].projectName+"</option> "
									 }
									 
								  });
						 $form.find('select[name="projectId"]').html(option);
						 form.render();
					}
		</script>

	</body>

</html>