<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>

	<head>
		<meta charset="UTF-8">
		<title>案场审核</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/begtable.css" />
	</head>

	<body>
		<div style="margin: 15px;">
		<fieldset class="layui-elem-field">
		  <legend>案场审核</legend>
			<table class="layui-table" id="proTable">
			  
			</table> 
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
	   	var startAllAppoint = 1; //当前页
	   	var limitAllAppoint = 10; //一页显示条数
	   	var totalPageAllAppoint = 0;//总页数
		
		function getProjectInfo(){
			$.ajax({
				type : "POST",
				url : "get_registerProject",
				async : false,
				data : {page:startAllAppoint, count:limitAllAppoint},
				success : function(data){
					fillProjectInfo(data.dataList);
					//startAllAppoint = data.currentResult;
	   				totalPageAllAppoint = data.pageTotal;
				}
				
			})
		}
		
		function fillProjectInfo(data){
			var s ="<colgroup><col width='150'><col width='150'><col><col></colgroup><thead><tr><th width='15%;'>案场名称</th><th width='15%;'>案场所在地</th><th width='15%;'>物业地址</th><th width='10%;'>联系人</th><th width='10%;'>联系电话</th><th width='10%;'>申请时间</th><th width='10%;'>操作</th></tr></thead><tbody id='layuitbody'>";
			$.each(data,function(v,o){
				s += "<tr>";
				s += " <td>"+o.projectName+"</td>";
				s += " <td>"+o.city+"</td>";
				s += " <td>"+o.propertyAddress+"</td>";
				s += " <td>"+o.userCaption+"</td>";
				s += " <td>"+o.phone+"</td>";
				s += " <td>"+o.createTime+"</td>";
				s += "<td><div class='layui-btn-group'>"+
				  "<button class='layui-btn' onclick='agreeRegister(this)' name='"+o.projectId+"'>同意</button>"+
				  "<button class='layui-btn' onclick='refuseRegister(this)'>否决</button>"+
				"</div></td>";
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
					/* $(document).on('click','#asd',function(){
						alert(1)
					}) */
					
					
			});
			function agreeRegister(data){//同意申请
				//alert('同意')
				var projectId = $(data).attr('name');
				$.ajax({
					type : "POST",
					url : "check_ProjectRegister",
					async : false,
					data : {projectId:projectId, agree:1},
					success : function(data){
						layer.msg(data.msg);
						setTimeout("fff()",1000);
						
					}
					
				})
				
			}
			function fff(){
				window.location.href='system_project_register';
			}
			function refuseRegister(data){//拒绝申请
				//alert('拒绝')
				var projectId = $(data).attr('name');
				$.ajax({
					type : "POST",
					url : "check_ProjectRegister",
					async : false,
					data : {projectId:projectId, agree:0},
					success : function(data){
						layer.msg(data.msg);
						setTimeout("fff()",1000);
					}
					
				})
			}
			function toPage(){
	            
	            layui.use(['form', 'laypage','layer'], function() {
	                var form = layui.form(),
	                    layer = layui.layer,
	                    laypage = layui.laypage;
	                
	                //调用分页
	                  laypage({
					cont: $('.beg-table-paged'),
					pages: totalPageAllAppoint //总页数
						,
					groups: 5 //连续显示分页数
						,
					jump: function(obj, first) {
						//得到了当前页，用于向服务端请求对应数据
						var curr = obj.curr;
						if(!first){
							layer.msg('第 '+ obj.curr +' 页');
						}
						
						$.ajax({
							type : "POST",
							url : "get_registerProject",
							async : false,
							data : {page:obj.curr, count:limitAllAppoint},
							success : function(data){
								fillProjectInfo(data.dataList);
								//startAllAppoint = data.currentResult;
				   				//totalPageAllAppoint = data.pageTotal;
							}
							
						})
	
					}
	                
	                
	            });
	        });
			};
				
		</script>

	</body>

</html>