<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Layui</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/begtable.css" />
	</head>

	<body>
		<div style="margin: 15px;">
		<input type="hidden" id="total" value="${count}">
		  <blockquote class="layui-elem-quote">
			    	<a href="<%=request.getContextPath()%>/toAddAd" class="layui-btn" id="add">
						<i class="layui-icon">&#xe608;</i> 新增广告
					</a>
			</blockquote>
		<fieldset class="layui-elem-field">
		  <legend>广告管理</legend>
		  
		  
			<table class="layui-table" lay-even="" lay-skin="row">
			  <colgroup>
			    <col width="150">
			    <col width="150">
			    <col width="200">
			    <col>
			  </colgroup>
			  <thead>
			    <tr>
			      	<td>导图</td>
					<td>广告标题</td>
					<td>所属项目</td>
					<td>广告位</td>
					<td>开始时间</td>
					<td>结束时间</td>
					<td>排序</td>
					<td>城市</td>
					<td>操作</td>
			    </tr> 
			  </thead>
			  <tbody id="layuitbody">
			  	<tr>
			  		<td colspan="9">暂无数据</td>
			  	</tr>
			  </tbody>
			</table> 
				<div class="beg-table-paged"></div> 
		</fieldset>
		</div>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/plugins/layui/layui.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
		<script>
			layui.config({
				base: 'static/layui/js/'
			});
			var pages = $('#total').val();
			layui.use('begtable', function() {
				var begtable = layui.begtable(),
					layer = layui.layer,
					$ = layui.jquery,
					laypage = layui.laypage;

				laypage({
					cont: $('.beg-table-paged'),
					pages: pages //总页数
						,
					groups: 5 //连续显示分页数
						,
					jump: function(obj, first) {
						//得到了当前页，用于向服务端请求对应数据
						var curr = obj.curr;
						if(!first){
							layer.msg('第 '+ obj.curr +' 页');
						}
						
							$("#layuitbody").empty();
							$.ajax({
				       			 url: 'findAdByPage',
				                    type: 'post',
				                    dataType: 'json',
				                    data:{
				                    	'page':curr,
				                    	'count':5 //一页显示条数
				                    },
				                    success: function (data) {
				                    	var tr='';
										$.each(data,function(v,o){
											tr += '<tr><td><img alt="导图" height="106px" width="185px" src="'+o.adUrl+'"></td><td>'+o.adTitle+'</td><td>'+o.projectName+'</td><td>'+o.adPosition+'</td><td>'+o.startTime+'</td><td>'+o.endTime+'</td><td>'+o.sorting+'</td><td>'+o.province+'-'+o.city+'</td><td><a href="system_to_updataAd?adId='+o.adId+'">修改</a></td></tr>';
										});
										$(tr).appendTo($("#layuitbody"));
				                    }
				       			});
	
					}
				});
			//	console.log(begtable.getSelectedRows());
			//	console.log(location);
			});
		</script>

	</body>

</html>