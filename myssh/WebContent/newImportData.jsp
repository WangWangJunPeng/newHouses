<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	
	div.mainDiv{
		position:absolute;
		margin:auto;
		top:0;
		bottom:0;
		left:0;
		right:0;
		/* border-right:4px solid;
		border-bottom-style:double; */
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
		padding-left:3%
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
		<div class="mainDiv">
			<h1 style="margin-left:-15px">三重奏房产交易平台数据管理工具</h1>
			<hr style="height:3px;width:66%;border:none;border-top:3px double red;">
			<!--案场下拉框 -->
			<div class="headDiv">
				<select id="project" style="width: 62%;height:45px;vertical-align:middle;font-size:25px">
					<option value="">--请选择案场--</option>
				</select>
			</div>
			<!-- 导入 -->
			<div class="importDiv">
				<div class="oneDiv" style="font-weight:bold;">
					数据导入工具
				</div>
				<div class="discribe">置业顾问文件:</div>
				<div class="fileInput">
					<input type="file">
				</div>
				
				<div class="discribe">门店中介文件:</div>
				<div class="fileInput">
					<input type="file">
				</div>
				
				<div class="discribe">带看记录文件:</div>
				<div class="fileInput">
					<input type="file">
				</div>
				
				<div class="discribe">到访记录文件:</div>
				<div class="fileInput">
					<input type="file">
				</div>
				
				<div class="discribe">案场助理名称:</div>
				<div class="fileInput">
					<input type="text">
				</div>
				
				<div class="discribe">登录手机号码:</div>
				<div class="fileInput">
					<input type="text">
				</div>
				<div class="oneDiv" style="text-align: center;">
					<input type="button" value="添 加" style="width:100px;height:32px; border: 0px;background-color:;">
				</div>
			</div>
			<!--删除  -->
			<div class="deleteDiv">
				<div class="oneDiv" style="font-weight:bold;margin-left:6px">数据删除工具</div>
				<div class="startTime">
					<input type="text" style="width:98%">
				</div>
				<dir class="collection">——</dir>
				<div class="entTime">
					<input type="text" style="width:98%">
				</div>
				<div class="twoDiv">
					<a href="javascript:;" style="margin-top:3px;margin-left: 140px">删除导入数据</a>
				</div>
			</div>
		</div>
</body>
</html>