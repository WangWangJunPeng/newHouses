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
				<form class="layui-form">
					<div class="layui-form-item">
					<div class="layui-input-inline">
						<a href="<%=request.getContextPath()%>/to_goToAddSonPage" class="layui-btn" id="add">
						<i class="layui-icon">&#xe608;</i>新增数据
					</a>
					</div>
					<div class="layui-input-inline">
						<select name="isUseful" id="isUseful" lay-filter="isUseful">
							<option value="3">所有状态</option> 
							<option value="1">启用</option> 
							<option value="0">禁用</option> 
						</select>
					</div>
				</div>
				</form>
		</blockquote>
		<fieldset class="layui-elem-field">
				<legend>数据列表</legend>
				<div class="layui-field-box layui-form">
					<table class="layui-table admin-table" style="table-layout:fixed;word-wrap:break-word;max-width:100%;text-align:center">
						<thead>
							<tr>
								<th width="10%;">显示文本</th>
								<th width="6%;">图表标题</th>
								<th width="14%;">说明文字</th>
								<th width="10%;">前端图名字</th>
								<th width="10%;">后台方法</th>
								<th width="14%;">关联按钮集合</th>
								<th width="10%;">返回地址</th>
								<th width="10%;">应用场景</th>
								<th width="4%;">是否有趋势图</th>
								<th width="4%;">是否启用</th>
								<th width="8%;">操作</th>
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
					layer = layui.layer,
					$ = layui.jquery,
					$form = $('form');
				//全选
				/*   form.on('checkbox(allselector)', function(data){
				    var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
				    child.each(function(index, item){
				      item.checked = data.elem.checked;
				    });
				    form.render('checkbox');
				  }); */					
				  showTable("");
				//下拉选改变事件
				form.on('select(isUseful)', function(data) {
					var isUseful  = data.value;
					if(isUseful!=3){
						showTable(isUseful);
					}else{
						showTable("");
					}
	            });
				  $(document).on('click','.layui-btn',function(){
					  var name = $(this).attr("name");
					  var cId = $(this).parent().attr('id');
					  if(name){
					  	if(name=='alertBtn'){//删除按钮 aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
					  		window.location.href='to_goAlertSonPage?chartDataId='+cId;
					  	}else{
							layer.open({
								  title: '提示'
								  ,content: '你确定要更改该条记录状态吗？'
								  ,btn: ['确认', '取消']
								  ,yes: function(index, layero){
									  $.ajax({
							       			 url: 'to_deleteManaerChartData',
							                    type: 'post',
							                    dataType: 'json',
							                    data:{
							                    	'chartDataId':cId
							                    },
							                    success: function (data) {},
							       			});
									  location.reload();
								  }
								  ,btn2: function(index, layero){
								    
								  }
								}); 						
					  	}
						  
					  }
				  });
			});
			function showTable(isUseful){
				$("#content").empty();
				$.ajax({
	       			 url: 'to_getManaerChartDataList',
	                    type: 'post',
	                    dataType: 'json',
	                    data:{
	                    	'isUseful':isUseful
	                    },
	                    success: function (data) {
	                    	var tr='';
	                    	if(data.length==0){
	                    		tr = '<td colspan="11">暂无数据</td>';
	                    	}else{
								$.each(data,function(v,o){
									tr += '<tr>'+
										'<th>'+o.buttonText+'</th>'+
										'<th>'+o.titleName+'</th>'+
										'<th>'+o.descText+'</th>'+
										'<th>'+o.chartName+'</th>'+
										'<th>'+o.backgroundMethod+'</th>'+
										'<th>'+o.relationListStr+'</th>'+
										'<th>'+o.returnAddress+'</th>'+
										'<th>'+o.scenario+'</th>'+
										'<th>'+o.trendPhoto+'</th>'+
										'<th>'+o.isUseful+'</th>'+
										'<th id="'+o.chartDataId+'"><button class="layui-btn layui-btn-small" name="alertBtn"><i class="layui-icon">&#xe642;</i></button>';
										if(o.isUseful==1){
											tr += '<button class="layui-btn layui-btn-small" name="xxBtn"><i class="layui-icon">&#x1007;</i></button></th>';
										}
										if(o.isUseful==0){
											tr += '<button class="layui-btn layui-btn-small" name="xxBtn"><i class="layui-icon">&#xe616;</i></button></th>';
										}
										tr += '</tr>';
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