<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

	<head>
		<meta charset="UTF-8">
		<title>首页</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/begtable.css" />
	</head>

	<body>
		<div style="margin: 15px;">
			<blockquote class="layui-elem-quote">
			<table class="layui-table">
			  <colgroup>
			    <col width="150">
			    <col width="150">
			    <col width="200">
			    <col>
			  </colgroup>
			  <thead>
			    <tr>
			    	<td>项目总数</td>
			    	<td>置业顾问总数</td>
			    	<td>房源总数</td>
			    	<td>门店总数</td>
			    	<td>经纪人总数</td>
			    	<td>合伙人总数</td>
			    </tr> 
			  </thead>
			  <tbody id="layuitbody1">
			  		<tr>
				  		<td colspan="6" style="text-align:center;"><img alt="" src="static/images/5-1503130Q911.gif"></td>
				  	</tr>
			  </tbody>
			</table> 
			</blockquote>
			<blockquote class="layui-elem-quote">
			  	  <div class="layui-form-item">
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
				<fieldset class="layui-elem-field">
		 		<legend>项目</legend>
				<table class="layui-table">
				  <colgroup>
				    <col width="150">
				    <col width="150">
				    <col width="200">
				    <col>
				  </colgroup>
				  <thead>
				    <tr>
				      	<td>到访</td>
						<td>登记</td>
						<td>认购</td>
						<td>签约</td>
						<td>到款</td>
						<td>中介客户贡献度</td>
						<td>中介客户成交贡献度</td>
				    </tr> 
				  </thead>
				  <tbody id="layuitbody2">
				  	<tr >
				  		<td colspan="7" style="text-align:center;"><img alt="" src="static/images/5-1503130Q911.gif"></td>
				  	</tr>
				  </tbody>
				</table> 
				</fieldset>
				
				<fieldset class="layui-elem-field">
		 		<legend>中介</legend>
				<table class="layui-table">
				  <colgroup>
				    <col width="150">
				    <col width="150">
				    <col width="200">
				    <col>
				  </colgroup>
				  <thead>
				    <tr>
				      	<td>备案</td>
						<td>确认</td>
						<td>否决</td>
						<td>认购</td>
						<td>签约成功率</td>
				    </tr> 
				  </thead>
				  <tbody id="layuitbody3">
				  	<tr>
				  		<td colspan="5" style="text-align:center;"><img alt="" src="static/images/5-1503130Q911.gif"></td>
				  	</tr>
				  </tbody>
				</table> 
				</fieldset>
			</blockquote>
		</div>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/main.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/plugins/layui/layui.js"></script>
		<script>
			var layer;
			var $;
			layui.config({
				base: 'static/layui/js/'
			});
			layui.use(['begtable','form','layer','laydate'], function() {
				var begtable = layui.begtable(),
					form = layui.form(),
					layer = layui.layer,
					laydate = layui.laydate,
					$ = layui.jquery;
				
				$(document).on('click','#search',function(){
					
					var startTime = $('#start').val();
					var endTime = $('#end').val();
					
					
					$("#search").html('<img style="margin-top:10px;" alt="" src="static/images/5-1503130Q911.gif">')
					$('#layuitbody2').html('<tr ><td colspan="7" style="text-align:center;"><img alt="" src="static/images/5-1503130Q911.gif"></td></tr>');
					$('#layuitbody3').html('<tr ><td colspan="5" style="text-align:center;"><img alt="" src="static/images/5-1503130Q911.gif"></td></tr>');
					systemMain.getVisitInfo(startTime, endTime);
					systemMain.getMediInfo(startTime, endTime);
					
					//第一张表的数据
					/* $.ajax({
						type : "post",
						async : true,
						url : "all_visit_person_count",
						data : {
							startTime : $("#start").val(),
							endTime : $("#end").val(),
						},
						success : function(data){
							data = eval("(" + data + ")");
							fillVisitInfo(data.root);
						},
					error : function(){
					}
					});
					//第三张表的数据
					$.ajax({
						type : "post",
						async : true,
						url : "all_meid_data_count",
						data : {
							startTime : $("#start").val(),
							endTime : $("#end").val(),
						},
						success : function(data){
							data = eval("(" + data + ")");
							fillMeidInfo(data.root);
						},
					error : function(){
					}
					}); */
				})
				
				
				systemMain.getVisitInfo("", "");
					systemMain.getMediInfo("", "");
					systemMain.getSystemCount();
				
				
				
				//加载数据
				/* $.ajax({
					type : "post", 
					async : true,
					url : "all_system_count", 
					success : function(data) { 
						data = eval("(" + data + ")");
						fillSystemInfo(data.root); 
					}
				}); */
				//第二张表的数据
				/* $.ajax({
					type : "post",
					async : true,
					url : "all_visit_person_count",
					data : {
						startTime : $("#start").val(),
						endTime : $("#end").val(),
					},
					success : function(data){
						data = eval("(" + data + ")");
						fillVisitInfo(data.root);
					},
				error : function(){
				}
				});
				//第三张表的数据
				$.ajax({
					type : "post",
					async : true,
					url : "all_meid_data_count",
					data : {
						startTime : $("#start").val(),
						endTime : $("#end").val(),
					},
					success : function(data){
						data = eval("(" + data + ")");
						fillMeidInfo(data.root);
					},
				error : function(){
				}
				}); */
				
			});
			
			function fillMeidInfo(data){
				var s = "";
				$.each(data, function(v, o){
					s += '<tr><td>' + o.applyCount + '</td>';
					s += '<td>' + o.confirmCount + '</td>';
					s += '<td>' + o.vetoCount + '</td>';
					s += '<td>' + o.loanCount + '</td>';
					s += '<td>' + o.writeSuccessCount +' %'+'</td>';
					
				});
				if (data.length > 0) {
					$("#layuitbody3").empty();
					$(s).appendTo($("#layuitbody3"));
				} else {

				}
				
			}
			
			
			function fillVisitInfo(data){
				var s = "";
				$.each(data, function(v, o){
					s += '<tr><td>' + o.visiterNum+ '/' +o.visitCount+ '组' + '</td>';
					s += '<td>' + o.writeCount + '</td>';
					s += '<td>' + '申请：'+ o.applyCount +'<br/>'+ '同意：'+ o.agreeCount +'<br/>'+ '否决：'+ o.votedCount + '</td>';
					s += '<td>' + o.writedCount + '</td>';
					s += '<td>' + o.getMoneyCount + '</td>';
					s += '<td>' + o.mediumPerc +' %'+'</td>';
					s += '<td>' + o.mediumTurnove +' %' +  '</td></tr>';
					
				});
				if (data.length > 0) {
					$("#layuitbody2").empty();
					$(s).appendTo($("#layuitbody2"));
				} else {

				}
				
			}
			
			function fillSystemInfo(data) {
				var s = "";
				$.each(data, function(v, o) { /*o为json的数据（后台传过来的）*/
					s += '<tr><td>' + o.projectCount + '</td>';
					s += '<td>' + o.adviserCount + '</td>';
					s += '<td>' + o.houseCount + '</td>';
					s += '<td>' + o.shopCount + '</td>';
					s += '<td>' + o.agentCount + '</td>';
					s += '<td>' + o.partnerCount + '</td></tr>';
				});

				if (data.length > 0) {
					$("#layuitbody1").empty();
					$(s).appendTo($("#layuitbody1"));
				} else {
					
				}
			}
		</script>

	</body>

</html>