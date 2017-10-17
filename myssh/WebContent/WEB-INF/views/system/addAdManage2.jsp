<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>新增广告</title>
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
				<legend>新增广告</legend>
			</fieldset>
			<form class="layui-form" action="add_advertisement" method="post" enctype="multipart/form-data">
				<div class="layui-form-item">
					<label class="layui-form-label">显示地区</label>
					<div class="layui-input-inline">
						<select name="province" id="province" lay-filter="province">
						<option value="0">请选择</option> 
                        	<c:forEach var="p" items="${provinces}">
	                            <option value="${p.cityId }">${p.cityName }</option> 
                        	</c:forEach>
						</select>
					</div>
					<div class="layui-input-inline">
						<select name="market" id="market" lay-filter="market">
							<option value="0">请选择</option> 
						</select>
					</div>
					<label class="layui-form-label">广告位</label>
					<div class="layui-input-inline">
						<select name="adPosition" id="adPosition" lay-filter="adPosition">
							<option value="0">请选择</option>                        
                            <option value="foryou">为你推荐</option> 
                            <option value="location">本地精选</option> 
                          	<option value="others">其他推荐</option>
						</select>
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">导读图片</label>
					<div class="layui-input-block">
						<input id="pic" type="file" name="picFile"  onchange="oldFile()" required>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label"></label>
					<img src="" id="pic_img" >
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">广告标题</label>
					<div class="layui-input-block">
						<input type="text" name="adTitle" lay-verify="required" autocomplete="off" placeholder="请输入标题" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">项目地址</label>
					<div class="layui-input-inline">
						<select name="" id="province1" lay-filter="province1">
                            <option value="0">请选择</option> 
                        	<c:forEach var="p" items="${provinces}">
	                            <option value="${p.cityId }">${p.cityName }</option> 
                        	</c:forEach>
						</select> 
					</div>
					<div class="layui-input-inline">
						<select name="market1" id="market1" lay-filter="market1">
							<option value="0">请选择</option> 
						</select>
					</div>
					<div class="layui-input-inline">
						<select name="projectId" id="projectId" lay-filter="project">
							<option value="0">请选择</option> 
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">开始时间</label>
						<div class="layui-input-block">
							<input type="text" name="startTime" id="start"  lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">结束时间</label>
						<div class="layui-input-block">
							<input type="text" name="endTime" id="end"  lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">当前状态最大排序</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" readonly="readonly" id="maxSorting">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">排序</label>
					<div class="layui-input-inline" id="ii">
						<input type="number" name="sorting" id="sorting" lay-verify="number" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">是否启用</label>
					<div class="layui-input-block">
						<input type="checkbox"  name="open" lay-skin="switch" lay-filter="switchTest" title="开关">
					</div>
				</div>
		
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<a href="<%=request.getContextPath()%>/system_to_adList" class="layui-btn layui-btn-primary">返回</a>
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
				
				//下拉选改变事件
				form.on('select(province)', function(data) {
					loadCityForShow(data.value);
	            });
				//广告位下拉触发事件
				form.on('select(adPosition)', function(data) {
					var province = $("#province").val();
					var market = $("#market").val();
					var adPosition = $("#adPosition").val();
					if(province||market||adPosition){
						$.post("query_sorting",
								{
								    "province":province,
								    "market":market,
								    "adPosition":adPosition
								  },
								  function(data){
									  var maxSt = data;
								    $("#maxSorting").val(maxSt);
								  });
					}else{
					}
					
	            });
				
				//项目处的联动
				form.on('select(province1)', function(data) {
					loadCityForProject(data.value);
	            });
				form.on('select(market1)', function(data) {
					loadProject(data.value);
	            });
				//监听提交
				form.on('submit(demo1)', function(data) {
					var projectId = $("#projectId").val();
					var province = $("#province").val();
					var market = $("#market").val();
					var adPosition = $("#adPosition").val();
					var maxSorting = $("#maxSorting").val();
					var sorting = $("#sorting").val();
					if(province==0){
						layer.msg('请选择显示地区', {icon: 5});
						return false;
					}
					if(market==0){
						layer.msg('请选择显示地区', {icon: 5});
						return false;
					}
					if(adPosition==0){
						layer.msg('请选择广告位', {icon: 5});
						return false;
					}
					if(projectId==0){
						layer.msg('请选择项目', {icon: 5});
						return false;
					}
					if(sorting==maxSorting){
						layer.msg('排序号不能与当前最大排序相等', {icon: 5});
						return false;
					}
					return true;
				});
				
			});
			function loadCityForShow(data1){
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
			
			function loadCityForProject(data1){
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
				 $form.find('select[name=market1]').html(option);
				 form.render();
			}
			
			function loadProject(data1){
				var option = '';
				
				$.ajaxSetup({
			        async: false
			 	});
				$.post("findProjectByCity",
						{
						    "cityId":data1
						  },
						  function(data){
							 if(data.length==0){
								 option = "<option value='0'>暂无项目</option> ";
							 }else{
								 for(var i=0;i<data.length;i++){
									 option += "<option value='"+data[i].projectId+"'>"+data[i].projectName+"</option> "
								 }
							 }
							 
						  });
				 $form.find('select[name=projectId]').html(option);
				 form.render();
			}
			function oldFile(){
				var windowURL = window.URL || window.webkitURL;
			    var loadImg = windowURL.createObjectURL(document.getElementById('pic').files[0]);
			    document.getElementById('pic_img').setAttribute('src',loadImg);
			}
		</script>
	</body>

</html>