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
		<blockquote class="layui-elem-quote">
			    	<a href="<%=request.getContextPath()%>/to_goToAddFatherPage" class="layui-btn" id="add">
						<i class="layui-icon">&#xe608;</i>新增数据
					</a>
		</blockquote>
		<fieldset class="layui-elem-field">
				<legend>数据列表</legend>
				<div class="layui-field-box layui-form">
					<table class="layui-table admin-table" style="table-layout:fixed;word-wrap:break-word;max-width:100%;">
						<thead>
							<tr>
								<th width="10%;">类目名称</th>
								<th width="50%;">拥有的标签(数值数组格式)</th>
								<th width="10%;">优先级</th>
								<th width="10%;">是否是定制(0:非定制、1:定制)</th>
								<th width="20%;">操作</th>
							</tr>
						</thead>
						<tbody id="content">
						</tbody>
					</table>
				</div>
			</fieldset>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/plugins/layui/layui.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
		<script>
			var $form;
			var form;
			var $;
			layui.config({
				base: 'static/layui/js/'
			});
			layui.use(['form'], function() {
					var form = layui.form(),
					$ = layui.jquery,
					layer = layui.layer,
					$form = $('form');
				showTable();
				//添加标签按钮
				$(document).on('click','.layui-btn',function(){
					var fId = $(this).attr('id');
					if(fId!='add'){
						//获取标签列表
						$.ajax({
			       			 url: 'to_getManaerChartDataList',
			                    type: 'post',
			                    dataType: 'json',
			                    data:{
			                    },
			                    success: function (data) {
			                    	var tr='';
			                    	if(data.length==0){
			                    		tr = '<td colspan="11">暂无数据</td>';
			                    	}else{
										$.each(data,function(v,o){
											tr += '<tr>'+
												'<th><input type="checkbox" name="haveLabels" lay-skin="primary"  value="'+o.chartDataId+'"></th>'+
												'<th>'+o.buttonText+'</th>'+
												'<th>'+o.titleName+'</th>'+
												'<th>'+o.descText+'</th>'+
												'<th>'+o.chartName+'</th>'+
												'<th>'+o.backgroundMethod+'</th>'+
												'<th>'+o.relationListStr+'</th>'+
												'<th>'+o.returnAddress+'</th>'+
												'<th>'+o.scenario+'</th>'+
												'<th>'+o.trendPhoto+'</th>'+
												'<th>'+o.isUseful+'</th></tr>';
										});
			                    	}
									//弹窗做选择
			                    	  layer.open({
			                    		  	area: '1200px'
			                    	        ,title: '在线调试'
			                    	        ,btn: ['确认', '取消']
			                    	        ,content: '<table class="layui-table admin-table" style="table-layout:fixed;word-wrap:break-word;max-width:100%;">'+
			            						'<thead>'+
			    							'<tr>'+
			    								'<th width="4%;"><input type="checkbox" name="" lay-skin="primary" lay-filter="allselector"></th>'+
			    								'<th width="8%;">显示文本</th>'+
			    								'<th width="8%;">图表标题</th>'+
			    								'<th width="17%;">说明文字</th>'+
			    								'<th width="8%;">前端图名字</th>'+
			    								'<th width="10%;">后台方法</th>'+
			    								'<th width="17%;">关联按钮集合</th>'+
			    								'<th width="8%;">返回地址</th>'+
			    								'<th width="10%;">应用场景</th>'+
			    								'<th width="4%;">趋势图</th>'+
			    								'<th width="4%;">启用</th>'+
			    							'</tr>'+
			    						'</thead>'+
			    						'<tbody>'+tr+
			    						'</tbody>'+
			    					'</table>'
			    							,yes:function(){
			    								//点击确认按钮
			    								var haveLabel = "";
			    								var $checkbox = $("input[name='haveLabels']:checked");
			    								console.log($checkbox)
			    								$checkbox.each(function(index, item){
			    									haveLabel += $(item).val()+",";
			    								    });
			    								//添加关联
			    								$.ajax({
			    					       			 url: 'to_updateAnalysisCategory',
			    					                    type: 'post',
			    					                    async : false,
			    					                    dataType: 'json',
			    					                    data:{
			    					                    	"haveLabel":haveLabel,
			    					                    	"categoryId":fId
			    					                    },
			    					                    success: function () {}
			    					       			});
			    								window.location.href='to_goToFatherPage';
			    							}
			                    	      });
			                    }
			       			});
					}
				})
			});
			function showTable(){
				$.ajax({
	       			 url: 'to_getAnalysisCategoryList',
	                    type: 'post',
	                    dataType: 'json',
	                    data:{
	                    },
	                    success: function (data) {
	                    	var tr='';
	                    	if(data.length==0){
	                    		tr = '<td colspan="5">暂无数据</td>';
	                    	}else{
								$.each(data,function(v,o){
									tr += '<tr>'+
									       	'<td>'+o.categoryName+'</td>'+
										  	'<td>'+o.haveLabels+'</th>'+
											'<td>'+o.priority+'</td>'+
											'<td>'+o.isMade+'</td>'+
											'<td><button type="button" class="layui-btn" id="'+o.categoryId+'">选择标签</button></td>'+
									     '</tr>';
								});
	                    	}
							$(tr).appendTo($("#content"));
	                    },
	                    error:function(){
	                    	alert(":")
	                    }
	       			});
			}
		</script>

	</body>

</html>