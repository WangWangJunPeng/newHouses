<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>平台运管管理中心</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">

		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/global.css" media="all">
		<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">

	</head>

	<body>
		<div class="layui-layout layui-layout-admin" style="border-bottom: solid 5px #1aa094;">
			<div class="layui-header header header-demo"  >
				<div class="layui-main">
					<div class="admin-login-box">
						<a class="logo" style="left: 0;" href="<%=request.getContextPath()%>/system_index">
							<span style="font-size: 22px;">平台运管管理中心</span>
						</a>
						<div class="admin-side-toggle">
							<i class="fa fa-bars" aria-hidden="true"></i>
						</div>
						<div class="admin-side-full">
							<i class="fa fa-life-bouy" aria-hidden="true"></i>
						</div>
					</div>
					<ul class="layui-nav admin-header-item">
						<li class="layui-nav-item">
							<a href="javascript:;">清除缓存</a>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;">浏览网站</a>
						</li>
						<li class="layui-nav-item" id="video1">
							<a href="javascript:;">视频</a>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;" class="admin-header-user">
								<img src="images/0.jpg" />
								<span>${data.userCaption}</span>
							</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="javascript:;"><i class="fa fa-user-circle" aria-hidden="true"></i> 个人信息</a>
								</dd>
								<dd>
									<a href="javascript:;"><i class="fa fa-gear" aria-hidden="true"></i> 设置</a>
								</dd>
								<dd id="lock">
									<a href="javascript:;">
										<i class="fa fa-lock" aria-hidden="true" style="padding-right: 3px;padding-left: 1px;"></i> 锁屏 (Alt+L)
									</a>
								</dd>
								<dd>
									<a href="login.html"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
								</dd>
							</dl>
						</li>
					</ul>
					<ul class="layui-nav admin-header-item-mobile">
						<li class="layui-nav-item">
							<a href="login.html"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="layui-side layui-bg-black" id="admin-side">
				<div class="layui-side-scroll" id="admin-navbar-side" lay-filter="side">
				
				</div>
			</div>
			<div class="layui-body"  id="admin-body" >
				<div class="layui-tab admin-nav-card layui-tab-brief" lay-filter="admin-tab" style="overflow:scroll !important;">
					<ul class="layui-tab-title">
						<li class="layui-this">
							<i class="fa fa-dashboard" aria-hidden="true"></i>
							<cite>首页</cite>
						</li>
					</ul>
					<div class="layui-tab-content">
						<div class="layui-tab-item layui-show">
							<iframe src="system_main"></iframe>
						</div>
					</div>
				</div>
				
			</div>
			<div class="layui-footer footer footer-demo" id="admin-footer">
				<div class="layui-main">
					<p>2016 &copy;
						<a href="http://m.zhengjinfan.cn/">m.zhengjinfan.cn/</a> LGPL license
					</p>
				</div>
			</div>
			<div class="site-tree-mobile layui-hide">
				<i class="layui-icon">&#xe602;</i>
			</div>
			<div class="site-mobile-shade"></div>
			
	
			<!--锁屏模板 end -->
			<script type="text/javascript">
			var navs = [ {
				"title": "首页",
				"icon": "fa-stop-circle",
				"href": "system_main",
				"spread": false,
				"children":[{
					"title": "地图",
					"icon": "fa-table",
					"href": "mapInfo"
				}]
			}, {
				"title": "广告管理",
				"icon": "fa-stop-circle",
				"href": "system_to_adList",
				"spread": false
			},  {
				"title": "账号管理",
				"icon": "fa-stop-circle",
				"href": "all_user_info_page",
				"spread": false
			},  {
				"title": "合伙人管理",
				"icon": "fa-stop-circle",
				"href": "toPartnerList",
				"spread": false
			},  {
				"title": "案场管理",
				"icon": "fa-stop-circle",
				"spread": false,
				"children": [{
					"title": "案场列表",
					"icon": "fa-table",
					"href": "system_to_projectList"
				}, {
					"title": "案场审核",
					"icon": "fa-table",
					"href": "system_project_register"
				}]
			},  {
				"title": "图表列表",
				"icon": "fa-stop-circle",
				"href": "to_goToSonPage",
				"spread": false
			},  {
				"title": "数据列表",
				"icon": "fa-stop-circle",
				"href": "to_goToFatherPage",
				"spread": false
			},  {
				"title": "对账单",
				"icon": "fa-stop-circle",
				"href": "to_staement_page",
				"spread": false
			}, {
				"title": "报表",
				"icon": "fa-stop-circle",
				"spread": false,
				"children": [{
					"title": "项目报表",
					"icon": "fa-table",
					"href": "table_project"
				}, {
					"title": "门店报表",
					"icon": "fa-table",
					"href": "system_shop_report"
				}, {
					"title": "经纪人报表",
					"icon": "fa-table",
					"href": "system_medi_report"
				}]
			}, {
				"title": "注册门店审核",
				"icon": "fa-stop-circle",
				"spread": false,
				"children": [{
					"title": "所有申请",
					"icon": "fa-table",
					"href": "all_reviewd_page"
				}, {
					"title": "审核中的申请",
					"icon": "fa-table",
					"href": "apply_reviewd_page"
				}, {
					"title": "审核通过的申请",
					"icon": "fa-table",
					"href": "passed_reviewd_page"
				}, {
					"title": "已拒绝的申请",
					"icon": "fa-table",
					"href": "refuse_reviewd_page"
				}]
			}, {
				"title": "日志下载",
				"icon": "fa-stop-circle",
				"href": "showloadLog",
				"spread": false
			}, {
				"title": "标签管理",
				"icon": "fa-stop-circle",
				"href": "system_tagManage",
				"spread": false
			}, {
				"title": "案场客户导入",
				"icon": "fa-stop-circle",
				"href": "to_system_project_customer_page",
				"spread": false
			}];
			</script>
			<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/plugins/layui/layui.js"></script>
			<script src="<%=request.getContextPath()%>/static/layui/js/index.js"></script>
			<script>
				layui.use('layer', function() {
					var $ = layui.jquery,
						layer = layui.layer;

				});
			</script>
		</div>
	</body>

</html>