<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>

	<head>
		<meta charset="UTF-8">
		<title>案场管理</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/begtable.css" />
	</head>

	<body>
		<div style="margin: 15px;">
		<fieldset class="layui-elem-field">
		  <legend>案场管理</legend>
		  <blockquote class="layui-elem-quote">
		  <form class="layui-form layui-form-pane" action="">
		  	  <div class="layui-form-item">
				    <div class="layui-inline">
				    	<a href="<%=request.getContextPath()%>/toAddProject" class="layui-btn" id="add">
							<i class="layui-icon">&#xe608;</i> 新增案场
						</a>
				    </div>
				    <div class="layui-input-inline">
						<select name="province" id="province" lay-filter="province">
						<option value="">请选择省</option> 
                        	<c:forEach var="p" items="${provinces}">
	                            <option value="${p.cityId }">${p.cityName }</option> 
                        	</c:forEach>
						</select>
					</div>
					<div class="layui-input-inline">
						<select name="market" id="market" lay-filter="market">
							<option value="">请选择市</option> 
						</select>
					</div>
					<div class="layui-input-inline">
						<select name="area" id="area" lay-filter="area">
							<option value="">请选择区/县</option> 
						</select>
					</div>
					<div class="layui-input-inline">
						<button class="layui-btn" id="search" type="button">搜索</button>
					</div>

  				</div>
	  		</form>
			</blockquote>
			<table class="layui-table" id="proTable">
			  
			</table> 
			<div class="admin-table-page">
                    <div id="paged" class="page">
                    </div>
                </div>
				<div class="beg-table-paged"></div> 
		</fieldset>
		</div>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/plugins/layui/layui.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
		<script type="text/javascript">
		
		$(function(){
			
			getProjectInfo();
			toPage();
		});
		
		//分页参数设置
	   	var startAllAppoint = 0;
	   	var limitAllAppoint = 10;
	   	var currentPageAllAppoint = 1;
	   	var totalPageAllAppoint = 0;
		
		function getProjectInfo(){
			var province = $("#province").val();
			var market = $("#market").val();
			var area = $("#area").val();
			$.ajax({
				type : "POST",
				url : "get_project_list",
				async : false,
				data : {start:startAllAppoint, limit:limitAllAppoint,province:province,market:market,area:area},
				success : function(data){
					data = eval("("+data+")");
					fillProjectInfo(data.root);
					startAllAppoint = data.currentResult;
	   				totalPageAllAppoint = data.totalPage;
				}
				
			})
		}
		
		function fillProjectInfo(data){
			var s ="<colgroup><col width='150'><col width='150'><col><col></colgroup><thead><tr><th>案场名称</th><th>案场所在地</th><th>案场售楼地址</th><th>案场助理姓名</th><th>案场助理电话</th><th>所属合伙人</th></tr></thead><tbody id='layuitbody'>";
			$.each(data,function(v,o){
				s += "<tr>";
				s += " <td>"+o.projectName+"</td>";
				s += " <td>"+o.city+"</td>";
				s += " <td>"+o.saleAddress+"</td>";
				if(o.assistantProject == null){
					s += "<td></td>";
					s += "<td></td>";
				}else{
					
				s += " <td>"+o.assistantProject.userCaption+"</td>";
				s += " <td>"+o.assistantProject.phone+"</td>";
				}
				if(o.partners == null){
					s += "<td></td>";
				}else{
					s += " <td>"+o.partners.userCaption+"</td>";
				}
				s += "</tr>"
			});
			s += "</tbody>";
			
			
			if(data.length > 0){
				$('#proTable').children().remove();
				$('#proTable').append(s);
			}
		}
		
			var layer;
			var $;
			var $form;
			layui.config({
				base: 'static/layui/js/'
			});
			layui.use(['begtable','form','layer'], function() {
				var begtable = layui.begtable(),
					form = layui.form(),
					layer = layui.layer,
					$ = layui.jquery,
					laypage = layui.laypage;
				$form = $('form');
				
				//省市联动
				form.on('select(province)', function(data) {
					loadCityForShow(data.value);
	            });
				form.on('select(market)', function(data) {
					loadAreaForShow(data.value);
	            });
				//点击搜索按钮
				$(document).on('click','#search',function(){
					getProjectInfo();
					currentPageAllAppoint = 1;
					toPage();
				})
			function loadAreaForShow(data1){
				var option = "<option value=''>请选择区/县</option>";
				
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
				 $form.find('select[name=area]').html(option);
				 form.render();
			}
			function loadCityForShow(data1){
				$form.find('select[name=area]').html("<option value=''>请选择区/县</option>");
				 form.render();
				var option = "<option value=''>请选择市</option>";
				
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
			function toPage(){
	            
	            layui.use(['form', 'laypage', 'layedit','layer', 'laydate'], function() {
	                var form = layui.form(),
	                    layer = layui.layer,
	                    layedit = layui.layedit,
	                    laydate = layui.laydate,
	                    laypage = layui.laypage;
	                
	                //调用分页
	                  laypage({
	                    cont: 'paged'
	                    ,pages: totalPageAllAppoint //得到总页数
	                    ,curr: currentPageAllAppoint
	                    ,skip: true
	                    ,jump: function(obj, first){
	                        
	                        currentPageAllAppoint = obj.curr;
	                        startAllAppoint = (obj.curr-1)*limitAllAppoint;
	                        	
	                       	 getProjectInfo();
	                      //document.getElementById('biuuu_city_list').innerHTML = render(obj, obj.curr);
	                      if(!first){ //一定要加此判断，否则初始时会无限刷新
	                          //location.href = '?page='+obj.curr;
	                        }
	                    }
	                  });
	                
	                
	            });
	        };
			
				
		</script>

	</body>

</html>