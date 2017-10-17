<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>

	<head>
		<meta charset="UTF-8">
		<title>项目报表</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/begtable.css" />
	</head>

	<body>
		<div style="margin: 15px;">
			<blockquote class="layui-elem-quote">
			<form class="layui-form">
			  	  <div class="layui-form-item">
			  	  		<div class="layui-inline">
							<select name="province" id="province" lay-filter="province">
								<option value="">请选择</option> 
	                        	<c:forEach var="p" items="${provinces}">
									<option value="${p.cityId }">${p.cityName }</option>
								</c:forEach>
							</select>
						</div>
			  	  		<div class="layui-inline">
							<select name="market" id="market" lay-filter="market">
								<option value="">请选择</option> 
							</select>
						</div>
					    <div class="layui-inline">
							<input type="text"  id="start" lay-verify="date" placeholder="起始时间" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">
						</div>——
						<div class="layui-inline">
							<input type="text"  id="end" lay-verify="date" placeholder="结束时间" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">
						</div>
						<div class="layui-inline">
							<a href="javascript:;" class="layui-btn" id="search">
								<i class="layui-icon"></i> 搜索
							</a>
						</div>
	  				</div>
	  				</form>
				<fieldset class="layui-elem-field">
		 		<legend>项目报表</legend>
				<table class="layui-table">
				  <colgroup>
				    <col width="150">
				    <col width="150">
				    <col width="200">
				    <col>
				  </colgroup>
				  <thead>
			      	<tr>
			      		<td>项目</td>
			      		<td>房源数</td>
			      		<td>外场成交数</td>
			      		<td>内场成交数含带客成交数</td>
			      		<td>带看佣金比例</td>
			      		<td>分销佣金比例</td>
			      	</tr>
				  </thead>
				  <tbody id="layuitbody">
				  	<tr>
				  		<td colspan="6">暂无数据</td>
				  	</tr>
				  </tbody>
				</table> 
				<div class="beg-table-paged"></div>
				</fieldset>
			</blockquote>
		</div>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/plugins/layui/layui.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
		<script>
			var $form;
			var form;
			var $;
			layui.config({
				base: 'static/layui/js/'
			});
			layui.use(['form','laydate','begtable'], function() {
				var begtable = layui.begtable(),
					form = layui.form(),
					laydate = layui.laydate,
					$ = layui.jquery,
					$form = $('form'),
					laypage = layui.laypage;
					
				initLayPage();
				//省市联动
				form.on('select(province)', function(data) {
					loadCityForShow(data.value);
	            });
				//加载数据
				//getProjectReportForms();
				$(document).on('click','#search',function(){
					initLayPage();
				})
				function initLayPage(){
					laypage({
						cont: $('.beg-table-paged'),
						pages: initPage() //总页数
							,
						groups: 5 //连续显示分页数
							,
						jump: function(obj, first) {
							//得到了当前页，用于向服务端请求对应数据
							var curr = obj.curr;
							if(!first){
								layer.msg('第 '+ obj.curr +' 页');
							}
							//TODO  分页数据
							$("#layuitbody").empty();
							getProjectReportForms(curr,10);
		
						}
					});
				}
				//初始化页数
				function initPage(){
					var city = "";
					if($('#province').val() != ""){
						city = $('#province').val() + "-" + $('#market').val();
					}
					var pages;
					$.ajax({
						type : "post", 
						async : false, 
						url : "table_project_list_num", //得到总页数
						data : {
							startTime : $("#start").val(),
							endTime : $("#end").val(),
							city : city
						},
						success : function(data) { 
							pages = data;
						},
					});
					return pages;
				}
				
			//联动
			function loadCityForShow(data1){
				$form.find('select[name=area]').html("<option value='0'>请选择</option>");
				 form.render();
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
			});
			
			//获取签到认购等数据
			function getProjectReportForms(page,limit){
				var city = "";
				if($('#province').val() != ""){
					city = $('#province').val() + "-" + $('#market').val();
				}
				$.ajax({
					type : "post",
					async : false,
					url : "system_project_report_forms",
					data : {
						startTime : $("#start").val(),
						endTime : $("#end").val(),
						city : city,
						page:page,
						count:limit
					},
					success : function(data){
						fillPorjectInfo(data);
					},
				error : function(){
					alert("请求失败")
				}
				});
			}
			function fillPorjectInfo(data){
				var s = "";
				var index = 1;
				$.each(data, function(v, o){
					s += '<tr><td>' + o.project+ '</td>';
					s += '<td>' + o.houseCount + '</td>';
					s += '<td>' + o.outsideCount + '</td>';
					s += '<td>' + o.insideCount + '</td>';
					s += '<td>' + o.daikanMoney +' %' +  '</td>';
					s += '<td>' + o.fenxiaoMoney +' %'+'</td></tr>';
					index += 1;
				});
				if (data.length > 0) {
					$("#layuitbody").empty();
					$(s).appendTo($("#layuitbody"));
				} else {
					$("#layuitbody").empty();
					$('<tr><td colspan="6">暂无数据</td></tr>').appendTo($("#layuitbody"));
				}
				
			}			
		</script>

	</body>

</html>