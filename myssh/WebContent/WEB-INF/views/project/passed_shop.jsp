<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>审核通过的申请</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/begtable.css" />
	</head>

	<body>
		<div style="margin: 15px;">
		<fieldset class="layui-elem-field">
		  <legend>审核通过的申请</legend>
			<table class="layui-table" lay-even="" lay-skin="row">
			  <colgroup>
			    <col width="150">
			    <col width="150">
			    <col width="200">
			    <col>
			  </colgroup>
			  <thead>
			    <tr>
			    	<th>公司名称</th>
			    	<th>门店名称</th>
			    	<th>所在地</th>
			    	<th>负责人</th>
			    	<th>合伙人</th>
			    	<th>电话</th>
			    	<th>申请时间</th>
			    	<th>当前状态</th>
			    	<th>审核人</th>
			    	<th>审核时间</th>
		    	</tr> 
			  </thead>
			  <tbody id="layuitbody">
			  	<tr>
			  		<td colspan="10">暂无数据</td>
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
			//分页参数
			layui.use('begtable', function() {
				var begtable = layui.begtable(),
					layer = layui.layer,
					$ = layui.jquery,
					laypage = layui.laypage;
				var pages = getShopAuditInfo();
				console.log(pages);
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
							$("#layuitbody").empty();
							$.ajax({
				        		type : "post",
				        		async : false,
				        		url : "<%=request.getContextPath()%>/all_shops_reviewd",
				        		data : {start:curr, limit:10, applyId:1},
				        		success : function(data,status){
				        			data = eval("(" + data + ")");
				        			fillShopAuditInfo(data.root);
				        		}
				        		
				        	});
						}
						
	
					}
				});
			//	console.log(begtable.getSelectedRows());
			//	console.log(location);
			});
			 function getShopAuditInfo(){
				 var pages;
		        	$.ajax({
		        		type : "post",
		        		async : false,
		        		url : "<%=request.getContextPath()%>/all_shops_reviewd",
		        		data : {start:1, limit:10, applyId:1},
		        		success : function(data,status){
		        			data = eval("(" + data + ")");
		        			fillShopAuditInfo(data.root);
		        			pages = data.totalPage;
		        		}
		        		
		        	});
		        	return pages;
		        }
			 
			function fillShopAuditInfo(data){
	        	var s = "";
	        	$.each(data, function(v, o){
	        		s += '<tr><td><a href="select_shop_reviewd?shopId='+ o.shopId +'">'+ o.companyName +'</a></td>';	
	        		s += '<td>' + o.shopName + '</td>';	
	        		s += '<td>' + o.address + '</td>';	
	        		s += '<td>' + o.contactPerson + '</td>';
	        		s += '<td>暂无</td>'
	        		s += '<td>' + o.phone + '</td>';	
	        		s += '<td>' + o.applyTime + '</td>';
	        		if(o.shopStatus == '0'){
	        		s += '<td>审核中</td>';	
	        		}else if(o.shopStatus == '1'){
	        		s += '<td>已通过</td>';	
	        		}else if(o.shopStatus == '3'){
	        		s += '<td>已拒绝</td>';	
	        		}else{
	        		s += '<td></td>';	
	        		}
	        		s += '<td>' + o.userCaption + '</td>';	
	        		s += '<td>' + o.approveTime + '</td></tr>';	
	        	});
	        	if(data.length > 0){
	        		$("#layuitbody").empty();
					$(s).appendTo($("#layuitbody"));
	        	}else{
	        		$("#layuitbody").empty();
					$('<tr><td colspan="6">暂无数据</td></tr>').appendTo($("#layuitbody"));
	        	}
	        }
			
			
		</script>

	</body>

</html>