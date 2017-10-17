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
				<legend>修改子</legend>
				<div class="layui-field-box layui-form">
					<form class="layui-form" action="<%=request.getContextPath()%>/to_updateManaerChartData" method="post" enctype="multipart/form-data">
						<div class="layui-form-item">
							<label class="layui-form-label">图片</label>
							<div class="layui-input-block">
							<input type="hidden" name="chartDataId" id="chartDataId" value="${mc.chartDataId }">
								<input id="pic" type="file" name="file"  onchange="oldFile()" >
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label"></label>
							<img src="${mc.iconUrl }" id="pic_img" >
						</div>
						<div class="layui-form-item">
					    	<label class="layui-form-label">显示文本</label>
						    <div class="layui-input-block" style="max-width:1000px;">
						      <input type="text" name="buttonText" lay-verify="required" autocomplete="off" placeholder="请输入显示文本"  class="layui-input" value="${mc.buttonText }">
						    </div>
					  	</div>
						<div class="layui-form-item">
					    	<label class="layui-form-label">图表标题</label>
						    <div class="layui-input-block" style="max-width:1000px;">
						      <input type="text" name="titleName" lay-verify="required" autocomplete="off" placeholder="请输入图表标题" class="layui-input" value="${mc.titleName }">
						    </div>
					  	</div>
						<div class="layui-form-item">
					    	<label class="layui-form-label">说明文字</label>
						    <div class="layui-input-block" style="max-width:1000px;">
						      <%-- <input type="text" name="descText" lay-verify="required" autocomplete="off" placeholder="请输入说明文字" class="layui-input" value="${mc.descText }"> --%>
						      <textarea class="layui-textarea" id="LAY_demo1" name="descText" style="display: none">  
								 ${mc.descText }
								</textarea>
						    </div>
					  	</div>
						<div class="layui-form-item">
					    	<label class="layui-form-label">前端图名字</label>
						    <div class="layui-input-block" style="max-width:1000px;">
						      <input type="text" name="chartName" lay-verify="required" autocomplete="off" placeholder="请输入前端图名字" class="layui-input" value="${mc.chartName }">
						    </div>
					  	</div>
						<div class="layui-form-item">
					    	<label class="layui-form-label">后台方法</label>
						    <div class="layui-input-block" style="max-width:1000px;">
						      <input type="text" name="backgroundMethod" lay-verify="required" autocomplete="off" placeholder="请输入后台方法" class="layui-input" value="${mc.backgroundMethod }">
						    </div>
					  	</div>
				  	 	<div class="layui-form-item">
					    	<label class="layui-form-label">关联按钮集合</label>
					    	<div class="layui-input-block" id="checkboxDiv" lay-filter="checkboxDiv" name="checkboxDiv">
					    	
			    			</div>
					  	</div>
					<!-- 	<div class="layui-form-item">
					    	<label class="layui-form-label">关联按钮集合</label>
						    <div class="layui-input-block" style="max-width:1000px;">
						      <input type="text" name="relationList" lay-verify="required" autocomplete="off" placeholder="请输入后台方法" class="layui-input">
						    </div>
					  	</div> -->
						<div class="layui-form-item">
					    	<label class="layui-form-label">返回地址</label>
						    <div class="layui-input-block" style="max-width:1000px;">
						      <input type="text" name="returnAddress" lay-verify="required" autocomplete="off" placeholder="请输入返回地址" class="layui-input" value="${mc.returnAddress }" >
						    </div>
					  	</div>
						<div class="layui-form-item">
					    	<label class="layui-form-label">应用场景</label>
						    <div class="layui-input-block" style="max-width:1000px;">
						      <input type="text" name="scenario" lay-verify="required" autocomplete="off" placeholder="请输入应用场景" class="layui-input" value="${mc.scenario }">
						    </div>
					  	</div>

					  	<div class="layui-form-item">
					  		<label class="layui-form-label">是否有趋势图</label>
							<div class="layui-input-inline">
								<select name="trendPhoto" id="trendPhoto" lay-filter="trendPhoto">
								<c:if test="${mc.trendPhoto==0 }">
									<option value="0">没有</option>
									<option value="1">有</option>
								</c:if>
								<c:if test="${mc.trendPhoto==1 }">
									<option value="1">有</option>
									<option value="0">没有</option>
								</c:if>
								</select> 
							</div>
					  	</div>
					  	<input type="hidden" id="owerList" value="${mc.relationList }">
					  	<div class="layui-form-item">
					  		<label class="layui-form-label">是否启用</label>
							<div class="layui-input-inline">
								<select name="isUseful" id="isUseful" lay-filter="isUseful">
								<c:if test="${mc.isUseful==0 }">
									<option value="0">不启用</option>
									<option value="1">启用</option>
								</c:if>
								<c:if test="${mc.isUseful==1 }">
									<option value="1">启用</option>
									<option value="0">不启用</option>
								</c:if>
								</select> 
							</div>
					  	</div>
					  	<div class="layui-form-item">
							<div class="layui-input-block">
								<button class="layui-btn" lay-submit="" lay-filter="demo1">确定修改</button>
								<a href="<%=request.getContextPath()%>/to_goToSonPage" class="layui-btn layui-btn-primary">返回</a>
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
			layui.use(['form','jquery','layedit'], function() {
				 $ = layui.jquery;
				form = layui.form(),
				layer = layui.layer,
				layedit = layui.layedit,
				$form = $('form');
				var index = layedit.build('LAY_demo1');
				//获取关联按钮集合
				$.ajaxSetup({
			        async: false
			 	});
				$.post("to_getManaerChartDataList",
						{},
						  function(data){
							var input = "";
							var owerIds = $("#owerList").val();
							var chartDataId = $("#chartDataId").val();
							if(owerIds){
								var arr = owerIds.split(',');
								console.log(arr);
								for(var i=0;i<data.length;i++){
									if(chartDataId!=data[i].chartDataId){
										console.log(1);
										var flag = false;
										for(var t=0;t<arr.length;t++){
											var owerId = arr[t];
											owerId = $.trim(owerId);
											console.log(2)
											if(data[i].chartDataId==owerId){
												flag = true;
												break;
											}
										
										}
										if(flag){
											input += '<input type="checkbox" name="relationLists" title="'+data[i].buttonText+'" value="'+data[i].chartDataId+'" checked="">';
										}else{
											input += '<input type="checkbox" name="relationLists" title="'+data[i].buttonText+'" value="'+data[i].chartDataId+'">';
										}
									}
								}
							}else{
								 for(var i=0;i<data.length;i++){
									 if(chartDataId!=data[i].chartDataId){
										 input += '<input type="checkbox" name="relationLists" title="'+data[i].buttonText+'" value="'+data[i].chartDataId+'">';
									 }
								 }
								
							}
							
							 $form.find('div[name="checkboxDiv"]').html(input);
							 form.render();
						  });
				form.on('submit(demo1)', function(data) {
					return true;
				});
			});
			function oldFile(){
				var windowURL = window.URL || window.webkitURL;
			    var loadImg = windowURL.createObjectURL(document.getElementById('pic').files[0]);
			    document.getElementById('pic_img').setAttribute('src',loadImg);
			}
		</script>

	</body>

</html>