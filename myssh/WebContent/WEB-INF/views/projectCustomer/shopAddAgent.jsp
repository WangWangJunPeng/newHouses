<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="icon" href="static/images/titleLogo.png" />
<title>经纪人管理</title>
<link rel="stylesheet" href="static/layui/plugins/layui/css/layui.css"
	media="all" />
<!-- <link rel="stylesheet" type="text/css" href="static/css/reset.css">
	<link rel="stylesheet" type="text/css" href="static/css/commend.css">
	<link rel="stylesheet" type="text/css" href="static/css/shopAddAgent.css"> -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css/validation.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="static/layui/plugins/layui/layui.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/jquery.validate.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/shopAddAgent.js"></script>
<style type="text/css">
.error {
	color: #ff0000;
	font-size: 10px;
}
</style>
</head>
<body>

	<span class="layui-breadcrumb">
	  <a href="to_medi_manager_page">经纪人管理</a>
	  <a><cite>新增经纪人</cite></a>
	</span>

	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>新增经纪人</legend>
	</fieldset>

	<form class="layui-form" action="medi_manager_to_add_or_update"
		method="post" id="createForm">

		<div class="layui-form-item">
			<label class="layui-form-label">经纪人类型</label>
			<div class="layui-input-block">
				<input type="radio" name="rightSign" value="medi" title="经纪人"
					checked="checked"> <input type="radio" name="rightSign"
					value="shopowner" title="店长">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">姓名<span>*</span></label>
			<div class="layui-input-inline">
				<input type="text" name="userCaption" id="name" lay-verify="title"
					autocomplete="off" placeholder="请输出姓名" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">电话<span>*</span></label>
			<div class="layui-input-inline">
				<input type="text" name="phone" id="phone" lay-verify="phone"
					autocomplete="off" placeholder="请输入手机号" class="layui-input">
				<a href="#" style="color: #2772DB; font-size: 10px;" id="phone_ok">
					<a href="#" style="color: #ff0000; font-size: 10px;"
					id="phone_exist"></a>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">身份证号码<span>*</span></label>
			<div class="layui-input-inline">
				<input type="text" name="idCard" id="id_card" lay-verify="identity"
					autocomplete="off" placeholder="请输入身份证号码" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="submit" class="layui-btn" id="forNext"
					name="returnSign" value="提交后继续新增下一个"> <input type="submit"
					class="layui-btn" id="forReturn" name="returnSign" value="提交后返回列表">
				<!-- <button class="layui-btn" name="returnSign" type="submit"
					id="forNext">提交后继续新增下一个经纪人</button>
				<button class="layui-btn" name="returnSign" type="submit"
					id="forReturn">提交后返回经纪人列表</button> -->
				<!--  <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button> -->
			</div>
		</div>
	</form>

	<%-- <%@include file="../publicpage/shoppublicpage.jsp" %> --%>

	<!-- <div class="contain">
		<div class="formMiddle">
				<form action="medi_manager_to_add_or_update" method="post" id="createForm">
			<section>
				<p>经纪人类型</p>
				<input type="radio" value="medi" id="kind1" name="rightSign" checked="checked">	
				<label for="kind1">经纪人</label>								
				<input type="radio" value="shopowner" id="kind2" name="rightSign">
				<label for="kind2">店长</label>
			</section>
			<section>
				<label for="name" style="margin-left: 45px;">姓名<span>*</span></label>
				<input type="text" name="userCaption" id="name">
			</section>
			<section>
				<label for="phone" style="margin-left: 45px;">电话<span>*</span></label>
				<input type="text" name="phone" id="phone"><a href="#" style="color:#2772DB;font-size: 10px;" id="phone_ok"></a><a href="#" style="color:#ff0000;font-size: 10px;" id="phone_exist"></a>
			</section>
			<section>
				<label for="id_card" style="margin-left: -10px;">身份证号码<span>*</span></label>
				<input type="text" name="idCard" id="id_card">
			</section>
			<input type="submit" id="forNext" name="returnSign" value="提交后继续新增下一个">
			<input type="submit" id="forReturn" name="returnSign" value="提交后返回列表">
			<button name="returnSign" type="submit" id="forNext">提交后继续新增下一个经纪人</button>
			<button name="returnSign" type="submit" id="forReturn">提交后返回经纪人列表</button>
		</form>
		</div>
	</div> -->
	<script type="text/javascript">
		layui.use([ 'form', 'layedit', 'laydate', 'element' ],function() {
							var form = layui.form(),
							layer = layui.layer,
							layedit = layui.layedit,
							laydate = layui.laydate,
							element = layui.element();

							//创建一个编辑器
							var editIndex = layedit.build('LAY_demo_editor');

							//自定义验证规则
							form.verify({
								title : function(value) {
									if (value.length < 5) {
										return '标题至少得5个字符啊';
									}
								},
								pass : [ /(.+){6,12}$/, '密码必须6到12位' ],
								content : function(value) {
									layedit.sync(editIndex);
								}
							});

							//监听指定开关
							form.on('switch(switchTest)', function(data) {
								layer.msg('开关checked：'
										+ (this.checked ? 'true' : 'false'), {
									offset : '6px'
								});
								layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF',
										data.othis)
							});

							//监听提交
							form.on('submit(demo1)', function(data) {
								layer.alert(JSON.stringify(data.field), {
									title : '最终的提交信息'
								})
								return false;
							});

						});
	</script>
</body>
</html>