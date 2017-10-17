<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

	<head>
		<meta charset="UTF-8">
		<title>合伙人管理</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/begtable.css" />
	</head>

	<body>
		<div style="margin: 15px;">
		<fieldset class="layui-elem-field">
		  <legend>合伙人管理</legend>
		  <blockquote class="layui-elem-quote">
		  <form class="layui-form layui-form-pane" action="">
		  	  <div class="layui-form-item">
				    <div class="layui-inline">
				    	<a href="<%=request.getContextPath()%>/system_toAddPartner" class="layui-btn" id="add">
							<i class="layui-icon">&#xe608;</i> 新增合伙人
						</a>
				    </div>
				    <div class="layui-inline">
				    	<a href="<%=request.getContextPath()%>/toAdPartner_Relation" class="layui-btn" id="add">
							<i class="layui-icon">&#xe608;</i> 新建合伙人关联
						</a>
				    </div>
				    <div class="layui-inline">
				      <div class="layui-input-inline">
				        <select name="status" lay-filter="status">
				          <option value="0">所有状态</option>
	                      <option value="1">启用</option>
	                      <option value="2">禁用</option>
				        </select>
				      </div>
				    </div>
  				</div>
	  		</form>
			</blockquote>
			<table class="layui-table" >
			  <colgroup>
			    <col width="150">
			    <col width="150">
			    <col>
			    <col>
			  </colgroup>
			  <thead>
			    <tr>
			    	<th rowspan='2'>账户名称</th>
			    	<th rowspan='2'>合伙人姓名</th>
			    	<th colspan='4'>关联案场</th>
			    	<th colspan='4'>关联门店</th>
			    	<th rowspan='2'>操作</th>
			    </tr>
			    <tr>
			    	<th>案场名称</th>
            		<th>关系建立时间</th>
            		<th>关系结束时间</th>
            		<th>有效年限</th>
            		<th>门店名称</th>
            		<th>关系建立时间</th>
            		<th>关系结束时间</th>
            		<th>有效年限</th>
            	</tr> 
			  </thead>
			  <tbody id="layuitbody">
			  	<tr>
			  		<td colspan="11">暂无数据</td>
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
				/* form.on('select(status)', function(data) {
					var status;
					if(data.value==0){
						status = "";
					}else{
						status = data.value;
					}
					alert(status);
					$.ajax({
						type : "post", 
						async : false,
						url : "system_all_user", 
						data : {"status" : status},
						success : function(data) { 
							$("#layuitbody").empty();
							fillUserInfo(data); 
						},
					});
	            }); */
				
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
						$("#layuitbody").empty();
						$.ajax({
							type : "post", /*为post方式*/
							async : false, /*开启同步请求，true为异步请求*/
							url : "partner_relation_list", /*url为发请求的url，利用Controller@RequestMapping进行拦截*/
							data : {"page":curr,"count":5},
							success : function(data) { /*当请求成功之后回调*/
								data = eval("(" + data + ")");
								setPartnerInfo(data.root);
							},
						});
	
					}
				});
				
			});
			//初始化页数
			function initPage(){
				var pages;
				$.ajax({
					type : "post", /*为post方式*/
					async : false, /*开启同步请求，true为异步请求*/
					url : "partner_list_num", /*url为发请求的url，利用Controller@RequestMapping进行拦截*/
					data : {},
					success : function(data) { /*当请求成功之后回调*/
						pages = data;
					},
				});
				return pages;
			}
			//加载合伙人列表
			function setPartnerInfo(data) {
				var s = "";
				$.each(data,function(v, o) {
					var max=0;
					var projects = o.projects;
					var shops = o.shops;
					max = projects.length;
					if(max<shops.length){
						max = shops.length;
					}
					
					s += '<tr><td rowspan="'+max+'">' + o.phone+ '</td>';
					s += '<td rowspan="'+max+'">' + o.partnerName + '</td>';
					if(projects.length>0){
						s += '<td>' + projects[0].projectName + '</td>';
						s += '<td>' + projects[0].createTime + '</td>';
						s += '<td>' + projects[0].removeTime + '</td>';
						s += '<td>' + projects[0].validity + '</td>';
					}else{
						s += '<td></td>';
						s += '<td></td>';
						s += '<td></td>';
						s += '<td></td>';
					}
					if(shops.length>0){
						s += '<td>' + shops[0].shopName + '</td>';
						s += '<td>' + shops[0].createTime + '</td>';
						s += '<td>' + shops[0].removeTime + '</td>';
						s += '<td>' + shops[0].validity + '</td>';
					}else{
						s += '<td></td>';
						s += '<td></td>';
						s += '<td></td>';
						s += '<td></td>';
					}
					s += '<td rowspan="'+max+'"><span class="open-pop" data-value=""><a href="query_partner?userId='+o.partnerId+'">修改</a></span></td>';
					for(var i=1;i<max;i++){
						if(projects.length>i){
							s += '<tr><td>' + projects[i].projectName + '</td>';
							s += '<td>' + projects[i].createTime + '</td>';
							s += '<td>' + projects[i].removeTime + '</td>';
							s += '<td>' + projects[i].validity + '</td>';
						}else{
							s += '<tr><td></td>';
							s += '<td></td>';
							s += '<td></td>';
							s += '<td></td>';
						}
						if(shops.length>i){
							s += '<td>' + shops[i].shopName + '</td>';
							s += '<td>' + shops[i].createTime + '</td>';
							s += '<td>' + shops[i].removeTime + '</td>';
							s += '<td>' + shops[i].validity + '</td></tr>';
						}else{
							s += '<td></td>';
							s += '<td></td>';
							s += '<td></td>';
							s += '<td></td></tr>';
						}
					}
				});

				if (data.length > 0) {
					$(s).appendTo($("#layuitbody"));
				} else {
					
				}
			}
		</script>

	</body>

</html>