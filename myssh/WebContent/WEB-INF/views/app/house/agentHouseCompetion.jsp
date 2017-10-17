<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta content="telephone=no,email=no" name="format-detection">
    <link href="<%=request.getContextPath()%>/static/css/mui.css" rel="stylesheet"/>    
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/houseDetail.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/inventoryFile.css">
	<link rel="stylesheet" href="static/css/suitable.css" />
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
     <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
	<title>房源对比</title>
	<style type="text/css">
		.mui-bar-nav~.mui-content {
			padding-top:.2rem; 
		}
		.mainTile {
			background: #f3f3f3;
			color:#666;
			margin-left: .6rem;
			height: .6rem;
    		line-height: .6rem;
		}
	</style>
</head>
<body class="mui-ios mui-ios-9 mui-ios-9-1">
	<header class="mui-bar mui-bar-nav mui-bar-transparent">
        <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
        <h1 class="mui-title">房源对比</h1>
    </header>
    <div class="mui-content">
    	<p class="mainTile">项目名称<span style="margin-left: .6rem;">金地格林（一期）</span ><span style="margin-left: .6rem;">万科润园（二期）</span></p>
    	<div class="introduce">
               <h2>房源信息</h2>
                <table class="cssTable">
                    <tbody>
                        <tr>
                            <td class="w25" colspan="2">房号</td>
                            <td class="w40">4-1-202</td>
                            <td class="w40">4-1-202</td>
                        </tr>
                         <tr>
                            <td rowspan="2">价格信息</td>
                            <td class="w125" colspan="0.5">总价</td>
                            <td >131万（最低）</td>
                            <td >131万（最低）</td>
                        </tr>
                        <tr>
                            <td class="w125" colspan="0.5">单价</td>
                            <td >131万（最低）</td>
                            <td >131万（最低）</td>
                        </tr>
                        <tr>
                            <td class="w25" colspan="2">户型图</td>
                            <td class="w40">图片</td>
                            <td class="w40">图片</td>
                        </tr>
                        <tr>
                            <td class="w25" colspan="2">户型特质</td>
                            <td class="w40">4-1-202</td>
                            <td class="w40">4-1-202</td>
                        </tr>
                    </tbody>
                </table>
         </div>
         <div class="introduce">
               <h2>户型信息</h2>
                <table class="cssTable">
                    <tbody>
                        <tr>
                            <td class="w25">户型</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">建筑面积</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">套内面积</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">朝向</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">户型位置</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                    </tbody>
                </table>
         </div>
         <div class="introduce">
               <h2>重要指标</h2>
                <table class="cssTable">
                    <tbody>
                        <tr>
                            <td class="w25">非计容面积</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">实得面积</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">实得面积比</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">总面宽</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">最大进深</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">所在楼层</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">日照时间</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">采光面</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">装修标准</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">重要提示<span style="colour:red;">*</span></td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                    </tbody>
                </table>
         </div>
         <div class="detailmsg" style="padding-left: 40%;"><a href="house_detail_comparison">详细对比 ></a></div>
         <div class="introduce">
               <h2>单元信息</h2>
                <table class="cssTable">
                    <tbody>
                        <tr>
                            <td class="w25">得房率</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">单层总户数</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">电梯数</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">单元楼层</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                    </tbody>
                </table>
         </div>
         <div class="introduce">
               <h2>楼栋信息</h2>
                <table class="cssTable">
                    <tbody>
                        <tr>
                            <td class="w25">楼栋位置</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">单元数</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">最大楼层</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">楼顶户数</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">直连地库</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">架空层</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">裙房</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">结构形式</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">非机动车库</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                    </tbody>
                </table>
         </div>
         <div class="introduce">
               <h2>交付信息</h2>
                <table class="cssTable">
                    <tbody>
                        <tr>
                            <td class="w25">交房时间</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">交付标准</td>
                            <td class="w40">交付标准交付标准交付标准交付标准交付标准交付标准交付标准交付标准交付标准交付标准交付标准</td>
                            <td class="w40">交付标准交付标准交付标准交付标准交付标准交付标准交付标准交付标准交付标准</td>
                        </tr>
                    </tbody>
                </table>
         </div>
         <div class="introduce">
               <h2>项目信息</h2>
                <table class="cssTable">
                    <tbody>
                        <tr>
                            <td class="w25">项目标签</td>
                            <td class="w40 ">
								<ul class="tablist">
        							<li>路过</li>
        							<li>老带新</li>
        							<li>朋友介绍</li>
        							<li>只是随便了解</li>
        							<li>网络分享的消息</li>
        							<li>只是随便来了解的</li>
        						</ul>
							</td>
                            <td class="w40">
                            	<ul class="tablist">
        							<li>路过</li>
        							<li>老带新</li>
        							<li>朋友介绍</li>
        							<li>只是随便了解</li>
        							<li>网络分享的消息</li>
        							<li>只是随便来了解的</li>
        						</ul>
                            </td>
                        </tr>
                        <tr>
                            <td class="w25">开盘时间</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">项目地址</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">所属区位</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">用地面积</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">总建筑面积</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">计容面积</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">地下建筑面积</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">总户数</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">容积率</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">车位配比</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">车位数</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">建筑密度</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">物业类型</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">售楼部位置</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                    </tbody>
                </table>
         </div>
         <div class="introduce">
               <h2>配套信息</h2>
                <table class="cssTable">
                    <tbody>
                        <tr>
                            <td class="w5" rowspan="8">自建配套</td>
                            <td class="cos1">小区会所</td>
                            <td class="cos2">15层</td>
                            <td class="cos2">15层</td>
                        </tr>
                        <tr>
                            <td class="cos1">健身房</td>
                            <td class="cos2">30层</td>
                            <td class="cos2">30层</td>
                        </tr>
                        <tr>
                            <td class="cos1">社区中心</td>
                            <td class="cos2">30层</td>
                            <td class="cos2">30层</td>
                        </tr>
                        <tr>
                            <td class="cos1">游泳池</td>
                            <td class="cos2">30层</td>
                            <td class="cos2">30层</td>
                        </tr>
                        <tr>
                            <td class="cos1">学校</td>
                            <td class="cos2">30层</td>
                            <td class="cos2">30层</td>
                        </tr>
                        <tr>
                            <td class="cos1">幼儿园</td>
                            <td class="cos2">30层</td>
                            <td class="cos2">30层</td>
                        </tr>
                        <tr>
                            <td class="cos1">商业</td>
                            <td class="cos2">30层</td>
                            <td class="cos2">30层</td>
                        </tr>
                        <tr>
                            <td class="cos1">其他</td>
                            <td class="cos2">30层</td>
                            <td class="cos2">30层</td>
                        </tr>
                        <tr>
                            <td class="w5" rowspan="6">周边配套</td>
                            <td class="cos1">商业</td>
                            <td class="cos2">15层</td>
                            <td class="cos2">15层</td>
                        </tr>
                        <tr>
                            <td class="cos1">教育</td>
                            <td class="cos2">30层</td>
                            <td class="cos2">30层</td>
                        </tr>
                        <tr>
                            <td class="cos1">金融</td>
                            <td class="cos2">30层</td>
                            <td class="cos2">30层</td>
                        </tr>
                        <tr>
                            <td class="cos1">交通</td>
                            <td class="cos2">30层</td>
                            <td class="cos2">30层</td>
                        </tr>
                        <tr>
                            <td class="cos1">医疗</td>
                            <td class="cos2">30层</td>
                            <td class="cos2">30层</td>
                        </tr>
                        <tr>
                            <td class="cos1">其他</td>
                            <td class="cos2">30层</td>
                            <td class="cos2">30层</td>
                        </tr>
                    </tbody>
                </table>
         </div>
         <div class="introduce">
               <h2>开发商信息</h2>
                <table class="cssTable">
                    <tbody>
                        <tr>
                            <td class="w25">开发商</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">投资商</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">企业性质</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">开发性质</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">设计单位</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>                      
                        <tr>
                            <td class="w25">施工单位</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">物业管理公司</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                        <tr>
                            <td class="w25">开工时间</td>
                            <td class="w40">2房2厅一卫一厨</td>
                            <td class="w40">2房2厅一卫一厨</td>
                        </tr>
                    </tbody>
                </table>
         </div>
    </div>
</body>
</html>