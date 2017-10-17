<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

	<head>
		<meta charset="UTF-8">
		<title>账号管理</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/begtable.css" />
	</head>

	<body>
		<div style="margin: 15px;">
		<input type="hidden" id="total" value="${count}">
		<fieldset class="layui-elem-field">
		  <legend>账号管理</legend>
		  <blockquote class="layui-elem-quote">
		  <form class="layui-form layui-form-pane" action="">
		  	  <div class="layui-form-item">
				    <div class="layui-inline">
				    	<a href="javascript:;" class="layui-btn" id="add">
							<i class="layui-icon">&#xe608;</i> 新增
						</a>
				    </div>
				    <div class="layui-inline">
				      <div class="layui-input-inline">
				        <select name="status" lay-filter="status11">
				          <option value="0">所有状态</option>
	                      <option value="1">启用</option>
	                      <option value="2">禁用</option>
				        </select>
				      </div>
				    </div>
  				</div>
	  		</form>
			</blockquote>
			<table class="layui-table" lay-even="" lay-skin="row">
			  <colgroup>
			    <col width="150">
			    <col width="150">
			    <col width="200">
			    <col>
			  </colgroup>
			  <thead>
			    <tr>
			      	<td>账号</td>
					<td>名称</td>
					<td>角色</td>
					<td>创建时间</td>
					<td>状态</td>
					<td>所属上级</td>
					<td>操作</td>
			    </tr> 
			  </thead>
			  <tbody id="layuitbody">
			  	<tr>
			  		<td colspan="7">暂无数据</td>
			  	</tr>
			  </tbody>
			</table> 
				<div class="beg-table-paged"></div> 
		</fieldset>
		</div>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/plugins/layui/layui.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
		<script>
			var layer;
			var $;
			layui.config({
				base: 'static/layui/js/'
			});
			layui.use(['begtable','form','layer'], function() {
				var begtable = layui.begtable(),
					form = layui.form(),
					layer = layui.layer,
					$ = layui.jquery,
					laypage = layui.laypage;
				form.on('select(status11)', function(data) {
					var status;
					if(data.value==0){
						status = "";
					}else{
						status = data.value;
					}
					initPage(status);
					//alert(status);
				/* 	$.ajax({
						type : "post", 
						async : false, 
						url : "system_all_user", 
						data : {"status" : status,"page":,"count":},
						success : function(data) { 
							$("#layuitbody").empty();
							fillUserInfo(data); 
						},
					}); */
	            });
				initPage("");
			function initPage(status){
				laypage({
					cont: $('.beg-table-paged'),
					pages: getCount(status) //总页数
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
								type : "post", /*为post方式*/
								async : false, /*开启同步请求，true为异步请求*/
								url : "system_all_user", /*url为发请求的url，利用Controller@RequestMapping进行拦截*/
								data : {"status" : status,"page":curr,"count":10},
								success : function(data) { /*当请求成功之后回调*/
									fillUserInfo(data); /*获取json串,并传给这个方法*/
								},
							});
	
					}
				});
			}
				function getCount(status){
					var pages;
					$.ajax({
						type : "post",
						async : false, 
						url : "system_all_user_num", 
						data : {"status" : status},
						success : function(data) { 
							pages = data.num; 
						},
					});
					return pages;
				}
			//重置密码
			$(document).on("click",'.reset-pop',function(){
				var datavalue = this.getAttribute("data-value");
				layer.open({
					btn: ['确定', '取消'],
					  title: '密码重置'
					  ,content: '您将重置该账号的密码'
					  ,btn1: function(index, layero){
						  	//按钮【按钮一】的回调
						   	$.ajax({
								type : "post",
								url : "account_manager_to_update_userInfo",
								data : {userId : datavalue,doSign : "reset"},
								success : function(data) {
								 	layer.msg("密码重置成功");
									//window.location.href = "all_user_info_page";
								}
							});
						   
						    layer.close(index);
						  }
						  ,btn2: function(index, layero){
						    //console.log(2);
						  }
					});    
			})
			//禁用账号
			$(document).on("click",'.stop-pop',function(){
				var datavalue = this.getAttribute("data-value");
				layer.open({
					btn: ['确定', '取消'],
					  title: '账号禁用'
					  ,content: '您将禁用该账号'
					  ,btn1: function(index, layero){
						  	//按钮【按钮一】的回调
						   	$.ajax({
								type : "post",
								url : "account_manager_to_update_userInfo",
								data : {userId : datavalue,userStatus: 3,doSign : "enableOrdisable"},
								success : function(data) {
									layer.msg("已成功禁用该用户");
									window.location.href = "all_user_info_page";
								}
							});
						   
						    layer.close(index);
						  }
						  ,btn2: function(index, layero){
						    //console.log(2);
						  }
					});    
			})
			//启用账号
			$(document).on("click",'.open-pop',function(){
				var datavalue = this.getAttribute("data-value");
				layer.open({
					btn: ['确定', '取消'],
					  title: '账号启用'
					  ,content: '您将启用该账号'
					  ,btn1: function(index, layero){
						  	//按钮【按钮一】的回调
						   	$.ajax({
								type : "post",
								url : "account_manager_to_update_userInfo",
								data : {userId : datavalue,userStatus: 1,doSign : "enableOrdisable"},
								success : function(data) {
									layer.msg("启用成功");
									window.location.href = "all_user_info_page";
								}
							});
						   
						    layer.close(index);
						  }
						  ,btn2: function(index, layero){
						    //console.log(2);
						  }
					});    
			})
			//删除账号
			$(document).on("click",'.cancel-pop',function(){
				var datavalue = this.getAttribute("data-value");
				layer.open({
					btn: ['确定', '取消'],
					  title: '账号删除'
					  ,content: '您将删除该账号'
					  ,btn1: function(index, layero){
						  	//按钮【按钮一】的回调
						   	$.ajax({
								type : "post",
								url : "account_manager_to_update_userInfo",
								data : {userId : datavalue,userStatus: 2,doSign : "delete"},
								success : function(data) {
									window.location.href = "all_user_info_page";
								}
							});
						   
						    layer.close(index);
						  }
						  ,btn2: function(index, layero){
						    //console.log(2);
						  }
					});    
			})
			
			});
			
			
			
			//第一次加载用户
			function fillUserInfo(data) {
				var s = "";
				$.each(data, function(v, o) { /*o为json的数据（后台传过来的）*/
					s += '<tr><td>' + o.phone + '</td>';
					s += '<td>' + o.userCaption + '</td>';
					if(o.roleId == 1){
						s += '<td>中介经纪人</td>';
					}
					if(o.roleId == 2){
						s += '<td>店长</td>';
					}
					if(o.roleId == 3){
						s += '<td>置业顾问</td>';
					}
					if(o.roleId == 4){
						s += '<td>案场助理</td>';
					}
					if(o.roleId == 5){
						s += '<td>超级管理员</td>';
					}
					if(o.roleId == 6){
						s += '<td>合伙人</td>';
					}
					if(o.roleId == 7){
						s += '<td>案场经理</td>';
					}
					s += '<td>' + o.createTime + '</td>';
					if(o.userStatus == 1){
					s += '<td>启用</td>';
					}
					if(o.userStatus == 3){
					s += '<td>禁用</td>';
					}
					s += '<td>' + o.contactPerson + '</td>';
					if(o.roleId != 5){
						s += '<td><a href="to_edit_user_page?userId='+o.userId+'&roleId='+o.roleId+'" class="edit-pen"><img src="static/images/edit.png" alt="" title="编辑" /></a>';
					}else{
						s +='<td>';
					}
					s += '<span class="reset-pop" data-value="'+o.userId+'"><img src="static/images/rest.png" alt="" title="密码重置"/></span>';
					if(o.roleId != 5){
						if(o.userStatus == 1){
							s += '<span class="stop-pop" data-value="'+o.userId+'"><img src="static/images/disable.png" alt="" title="禁用" /></span>';
						}
						if(o.userStatus == 3){
							s += '<span class="open-pop" data-value="'+o.userId+'"><img src="static/images/eable.png" alt="" title="启用" /></span>';
						}
						s += '<span class="cancel-pop" data-value="'+o.userId+'"><img src="static/images/delete.png" alt="" title="删除" /></span></td></tr>';
					}else{
						s += '</td></tr>';
					}
				});
					$(s).appendTo($("#layuitbody"));
				 /*当服务器有数据传送过来,将所有的元素都添加到id为systemCountInfo中*/
					
			}
		</script>

	</body>

</html>