<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" type="text/css" href="static/lib/laydate/need/laydate.css" />
<script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" >
	$(document).ready(function (){
		$.ajax({
			type : "post",
			dateType:"json",
			async : false,
			url : "import_data_pro_menu",
			/* data : {
				phoneNum : $("#phone").val()
			}, */
			success : function(data) {
				fileProMenu(data);
			}
		});
		checkHaveEngineer();
	});
	
	function fileProMenu(data){
		var s = '<option value="">请选择案场</option>';
		$.each(data,function(v,o){
			s += '<option value="'+o.proId+'">'+o.proName+'</option>';
		});
		if(data.length>0){
			$("#proMenu").html(s);
		}else{
			$("#proMenu").html('<option value="0">暂无案场</option>');
		}
	}
	function checkHaveEngineer(){
		$.ajax({
			type : "post",
			dateType:"json",
			async : false,
			url : "checkProHaveEngineer",
			data : {
				proId : $("#proMenu").val()
			},
			success : function(data) {
				fileProEngineer(data);
			}
		});
	}
	function fileProEngineer(data){
		console.log(data);
		if(data.isHave!=0){
			$("#isHave").val(data.isHave);
			//$("#agentName").hide();
			//$("#engineerName").hide();
			$("#engineerName").attr("disabled","disabled");
			$("#engineerName").val(data.name);
			$("#engineerPhone").val(data.phone);
			$("#engineerPhone").attr("disabled","disabled");
		}else{
			$("#engineerName").val("");
			//$("#agentName").show();
			//$("#engineerName").show();
			$("#engineerName").attr("disabled",false);
			$("#engineerPhone").val("");
			$("#engineerPhone").attr("disabled",false);
		}
	}
	
	function uploadForm(){
		$("#msgId").html("导入中，请勿关闭页面...");
		var form = new FormData();
		var proId = $("#proMenu").val();
		form.append("proId", proId);
		var domList = $("input[name='txtFile']");
		domList.each(function(){
			var domFileList = this.files;
			if(domFileList.length>0){
				for(var n=0;n<domFileList.length;n++){
					form.append("txtFile", domFileList[n]);
				}
			}
		});
		$.ajax({
			url:"import_txtData_to_table",
			dataType:"json",
			type : "post",
			data:form,
			contentType: false,
			processData: false,
			beforeSend:function(){
				show();
			},
			success:function(data){
				alert(data.msg);
			},
			complete:function(){
				hide();
			}
		
		});
	}
	
	function addEngineer(){
		var name = $("#engineerName").val();
		var phone = $("#engineerPhone").val();
		var proId = $("#proMenu").val();
		$.ajax({
			url:"add_engineer",
			dataType:"json",
			type : "post",
			data:{userCaption:name,phone:phone,parentId:proId},
			success:function(data){
				if(data.code==200){
					uploadForm();
				}
			}
		});
	}
	
	function submitForm(){
		if($("#proMenu").val()==null || $("#proMenu").val()==""){
			alert("请选先选择案场");
		}else if($("#engineerName").val()==null || $("#engineerName").val()==""){
			alert("请选填写助理名称");
		}else if($("#engineerPhone").val()==null || $("#engineerPhone").val()==""){
			alert("请选填写登录手机号");
		}else{
			if($("#isHave").val()!=1){
				addEngineer();
			}else{
				uploadForm();
			}
		}
	}
	//导入数据删除
	function deleteImportData(){
		$("#msgId").html("正在删除...");
		var start = $("#start").val();
		var end = $("#end").val();
		var proId = $("#proMenu").val();
		if(start==null || start==""){
			alert("请先选择起始时间");
		}else if(end==null || end==""){
			alert("请先选择结束时间");
		}else if(proId==null || proId==""){
			alert("请先选择删除的项目");
		}else{
			$.ajax({
				url:"delete_import_data",
				dataType:"json",
				type : "post",
				data:{start:start,end:end,proId:proId},
				beforeSend:function(){
					show();
				},
				success:function(data){
					alert(data.msg);
				},
				complete:function(){
					hide();
				}
			});
		}
	}
</script>
<style type="text/css">
	div.mainDiv{
		position:absolute;
		margin:auto;
		top:0;
		bottom:0;
		left:0;
		right:0;
		border-right:4px solid;
		border-bottom-style:double;
		border-color:red;
		width: 800px;
		height: 480px;
		margin-top:5%
		
	}
	div.headDiv{
		border: 0px solid ;
		border-color:red;
		width: 93.5%;
		height: 10%;
		margin-top:2%;
		margin-left:3%;
	}
	div.importDiv{
		border-right:4px solid ;
		border-color:#80FFFF;
		width: 40.5%;
		height: 62%;
		margin-top :10px;
		float: left;
		margin-left: 3%;
	}
	.oneDiv{
		float:left;
		border: 0px solid ;
		border-color:red;
		width: 100%;
		height: 10%;
		/* margin-top :5px;
		margin-bottom:5px; */
	}
	.discribe{
		border: 0px solid ;
		border-color:red;
		width: 33%;
		height: 10%;
		margin-top :1px;
		float: left;
		margin-left: 4%;
	}
	.fileInput{
		border: 0px solid ;
		border-color:red;
		width: 60%;
		height: 10%;
		margin-top :1px;
		float: right;
		margin-right: 3%;
	}
	div.deleteDiv{
		border-left-style:double;
		border-color:red;
		width: 52.5%;
		height: 62%;
		margin-top :10px;
		float:right;
		margin-right: 3%;
	}
	.startTime{
		margin-top:15%;
		margin-left:6%;
		border: 0px solid ;
		border-color:red;
		width: 40%;
		height: 10%;
		float: left;
		
	}
	.entTime{
		margin-top:15%;
		border: 0px solid ;
		border-color:red;
		width: 40%;
		height: 10%;
		float: right;
	}
	.collection{
		margin-top:15%;
		border: 0px solid ;
		border-color:red;
		width: 3%;
		height: 10%;
		float:left;
		padding-left:7%
	}
	.twoDiv{
		margin-top:20%;
		margin-left:6%;
		float:left;
		border: 0px solid ;
		border-color:red;
		width: 100%;
		height: 10%;
	}
</style>
</head>
<body>
	<input id="isHave" type="hidden">
	<div class="mainDiv">
		<h1 style="margin-left:-15px">三重奏房产交易平台数据管理工具</h1>
		<hr style="height:3px;width:66%;border:none;border-top:3px double red;">
		<!--案场下拉框 -->
		<div class="headDiv">
			<select id="proMenu" name="proId" style="width: 62%;height:45px;vertical-align:middle;font-size:25px" onchange="checkHaveEngineer()"></select>
		</div>
		<!-- 导入 -->
		<div class="importDiv">
			<div class="oneDiv" style="font-weight:bold;">
				数据导入工具
			</div>
			<div class="discribe">导入文件:</div>
			<div class="fileInput">
				<input id="txtFile0" type="file" name="txtFile">
			</div>
			<!-- 
			<div class="discribe">门店中介文件:</div>
			<div class="fileInput">
				<input id="txtFile1" type="file" name="txtFile">
			</div>
			
			<div class="discribe">带看记录文件:</div>
			<div class="fileInput">
				<input id="txtFile2" type="file" name="txtFile">
			</div>
			
			<div class="discribe">到访记录文件:</div>
			<div class="fileInput">
				<input id="txtFile3" type="file" name="txtFile">
			</div>
			-->
			<div class="discribe">案场助理名称:</div>
			<div class="fileInput">
				<input id="engineerName" name="userCaption">
			</div> 
			
			<div class="discribe">登录手机号码:</div>
			<div class="fileInput">
				<input id="engineerPhone" name="phone">
			</div>
			<div class="oneDiv" style="text-align: center;">
				<input type="button" value="添加" style="width:100px; height:32px; background-color:#80FFFF;border: 0" onclick="submitForm()">
			</div>
		</div>
		<!--删除  -->
		<!-- <div class="deleteDiv">
			<div class="oneDiv" style="font-weight:bold;margin-left:6px">数据删除工具</div>
			<div class="startTime">
				<input class="laydate-icon  start-time" name="startTime" id="start" placeholder="起始日期" style="width:98%"/>
			</div>
			<dir class="collection">—</dir>
			<div class="entTime">
				<input class="laydate-icon  end-time" name="deliverTime" id="end" placeholder="截止日期" style="width:98%"/>
			</div>
			<div class="twoDiv">
				<a href="javascript:;" onclick="deleteImportData()" style="margin-top: 3px;margin-left: 140px">删除导入数据</a>
			</div>
		</div> -->
	</div>
<!-- 弹框提示 -->
	<div class="transbox" style="display:none">
		<div style="height: 200px;width:220px;z-index:1000;" class="hgh">
			<img src="static/images/loading.jpg">
			<p id="msgId" style="color:#fff;"></p>
		</div>
	</div>
</body>
<script type="text/javascript" src="static/lib/laydate/laydate.js"></script>
<script type="text/javascript">
	var start = {
		elem : '#start',
		format : 'YYYY-MM-DD hh:mm:ss',
		//min: laydate.now(), //设定最小日期为当前日期
		max : '2099-06-16', //最大日期
		istime : true,
		istoday : false,
		choose : function(datas) {
			end.min = datas; //开始日选好后，重置结束日的最小日期
			end.start = datas //将结束日的初始值设定为开始日
		}
	};
	var end = {
		elem : '#end',
		format : 'YYYY-MM-DD hh:mm:ss',
		//min: laydate.now(),
		max : '2099-06-16',
		istime : true,
		istoday : false,
		choose : function(datas) {
			start.max = datas; //结束日选好后，重置开始日的最大日期
		}
	};
	laydate(start);
	laydate(end);
</script>
<script>
	function show(){
		document.getElementsByClassName("transbox")[0].style.display="block";
	} 
	function hide(){
		document.getElementsByClassName("transbox")[0].style.display="none";
	}
</script>
<style>
talbe.background
{
  width: 600px;
  height: 600px;
  border: 1px solid black;
  position:absolute;
  text-align:center;
  line-height:600px;
  top:0;
  left:0;
}

div.transbox
{
  width: 100%;
  height: 100%;
  position:absolute;
  top:0;
  left:0;
  z-index:10;
  background-color: #333333;
  border: 1px solid black;
  /* for IE */
  filter:alpha(opacity=60);
  /* CSS3 standard */
  opacity:0.3;

  
}
.transbox .hgh{
	position:absolute;
	margin:auto;
	top:0;
	bottom:0;
	left:0;
	right:0;
}
</style>
</html>