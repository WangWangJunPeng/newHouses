<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta content="telephone=no,email=no" name="format-detection">
    <link href="<%=request.getContextPath()%>/static/css/mui.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/message.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/houseDetail.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/inventoryFile.css">
    
	<link rel="stylesheet" href="static/css/suitable.css" />
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/messageAPP.js" type="text/javascript"></script>
	<title>一房一档</title>
	<script type="text/javascript">
	$.fn.ImgZoomIn = function () {
		 
		bgstr = '<div id="ImgZoomInBG" style=" background:#000000; filter:Alpha(Opacity=70); opacity:1; position:fixed; left:0; top:0; z-index:10000; width:100%; height:100%; display:none;"><iframe src="about:blank" frameborder="0px" scrolling="yes" style="width:100%; height:100%;"></iframe></div>';
		//alert($(this).attr('src'));
		imgstr = '<img id="ImgZoomInImage" src="' + $(this).attr('src')+'" onclick=$(\'#ImgZoomInImage\').hide();$(\'#ImgZoomInBG\').hide(); style="cursor:pointer; display:none; position:fixed; z-index:10001;" />';
		if ($('#ImgZoomInBG').length < 1) {
		$('body').append(bgstr);
		}
		if ($('#ImgZoomInImage').length < 1) {
		$('body').append(imgstr);
		}
		else {
		$('#ImgZoomInImage').attr('src', $(this).attr('src'));
		}
		//alert($(window).scrollLeft());
		//alert( $(window).scrollTop());
		$('#ImgZoomInImage').css('left', $(window).scrollLeft() + ($(window).width() - $('#ImgZoomInImage').width()) / 2);
		$('#ImgZoomInImage').css('top', $(window).scrollTop() + ($(window).height() - $('#ImgZoomInImage').height()) / 2);
		$('#ImgZoomInBG').show();
		$('#ImgZoomInImage').show();
		};
		 
		$(document).ready(function () {
		$("#imgTest").bind("click", function () {
		$(this).ImgZoomIn();
		});
		});
	</script>
	<style type="text/css">
		.mui-bar-nav~.mui-content {
			padding-top:.2rem; 
		}
	
	</style>
</head>
<body class="mui-ios mui-ios-9 mui-ios-9-1">
<header class="mui-bar mui-bar-nav mui-bar-transparent">
        <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
        <h1 class="mui-title">${data.project.projectName }${data.house.unit}${data.house.buildingId }${data.house.houseNo }室</h1>
        <a class="mui-icon mui-icon-right-nav mui-pull-right vs" href="house_toAgentHouseCompetionList"><img class="vs" src="static/images/vs.png"><span class="rightTop">3</span></a>
        <a class="mui-pull-right mui-icon mui-icon-plusempty" href="#fff" id="jiaruDuibi"></a> 
</header>
<div class="mui-content">
	<div class="mid">
    <div class="banner">
        <div id="slider" class="mui-slider">
                <div class="mui-slider-group mui-slider-loop" style="transform: translate3d(-10rem, 0rem, 0rem) translateZ(0rem);transition-duration: 0.2s">
                    <!-- 额外增加的一个节点(循环轮播：第一个节点是最后一张轮播) -->
                    <div class="mui-slider-item mui-slider-item-duplicate">
						<img src="${data.houseType.photoURL}" >
					</div>
                    <c:if test="${fn:length(data.projectEffectPic)>0}">
									<div class="mui-slider-item mui-slider-item-duplicate">
										<img src="${data.projectEffectPic[fn:length(data.projectEffectPic)-1]}" >
									</div>
	                    <c:forEach begin="0" end="${fn:length(data.projectEffectPic)-1}" var="p">
									<div class="mui-slider-item ">
										<img class="exampleImg" onClick="$(this).ImgZoomIn();" src="${data.projectEffectPic[p]}" >
									</div>
						</c:forEach>
									
                    </c:if>
                </div>
            </div>
        <%-- <p class="houseName">${data.projectName }${data.buildingNo }${data.unit }${data.floor }${data.houseNo }</p> --%>
       	<ul class="tablist">
       		<c:forEach items="${data.houseTags }" var="list">	
       			<li>${list }</li>						
			</c:forEach> 
        </ul>
    </div>
    <div class="houseIntroduce introduce">
               <h2>户型信息</h2>
                <table class="cssTable">
                    <tbody>
                        <tr>
                            <td class="cos1">户型</td>
                            <td class="cos2">
                            	<c:if test="${data.house.houseTypeName !=null}">
                            		${data.house.houseTypeName }
                            	</c:if>
                            	<c:if test="${data.house.houseTypeName ==null}">
                            		未知
                            	</c:if>
                            </td>
                            <td class="cos1">预售证号</td>
                            <td class="cos2">
                            	<c:if test="${data.house.buildArea==null}">
                            		未知
                            	</c:if>
                            	<c:if test="${data.house.buildArea!=null }">
                            		${data.house.buildArea }
                            	</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="cos1">建筑面积</td>
                            <td class="cos2">
                            	<c:if test="${data.house.buildArea !=null}">
                            		${data.house.buildArea }m²
                            	</c:if>                       	
                            	<c:if test="${data.house.buildArea ==null}">
                            		<c:if test="${data.house.preBuildArea !=null}">
                            		${data.house.preBuildArea }m²（预测）
                            		</c:if>
                            		<c:if test="${data.house.preBuildArea ==null}">
                            		未知
                            		</c:if>
                            	</c:if>
                            </td>
                            <td class="cos1">套内面积</td>
                            <td class="cos2">
                            	<c:if test="${data.house.insideSpace !=null}">
                            		${data.house.insideSpace }m²
                            	</c:if>
                            	<c:if test="${data.house.insideSpace ==null}">
                            		未知
                            	</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="cos1">楼栋朝向</td>
                            <td class="cos2">
                            	<c:if test="${data.projectBuilding.buildingDirection !=null}">
                            		${data.projectBuilding.buildingDirection}
                            	</c:if>
                            	<c:if test="${data.projectBuilding.buildingDirection ==null}">
                            		未知
                            	</c:if>
                            </td>
                            <td class="cos1">户型位置</td>
                            <td class="cos2">
                            	<c:if test="${data.house.houseTypePosition ==1}">
                            	边套
                            	</c:if>
                            	<c:if test="${data.house.houseTypePosition ==2}">
                            	中间套
                            	</c:if>
                            	<c:if test="${data.house.houseTypePosition ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="detailmsg"><a href="house_detail_info?houseNum=${data.house.houseNum}">详细信息 ></a></div>
            <div class="priceMessage introduce">
                <h2>价格信息</h2>
                <table>
                    <tbody>
                        <tr>
                        	<td rowspan="2">价格</td>
                            <td class="single">单价</td>
                            <td class="pri" colspan="1.5">
                            	<c:if test="${data.priceMin !=null}">
                            		${data.priceMin}元/㎡
                            	</c:if>
                            	<c:if test="${data.priceMin ==null}">
                            	</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="allprice single">总价</td>
                            <td class="pri " colspan="1.5">
                            	<c:if test="${data.house.listPrice !=null}">
                            		${data.house.listPrice}元
                            	</c:if>
                            	<c:if test="${data.house.listPrice ==null}">
                            		未知
                            	</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="cos1">物业费</td>
                            <td colspan="3">
                            	<c:if test="${data.project.propertyCost !=null}">
                            		${data.project.propertyCost}元/平方米
                            	</c:if>
                            	<c:if test="${data.project.propertyCost ==null}">
                            		未知
                            	</c:if>
                            </td>
                        </tr>
                       <%-- <%int i=0; %>
						<c:forEach items="${data.youhuiList }" var="list">							
							<tr>
								<td colspan=1>优惠 <%i=i+1; out.print(i);%></td>
								<td colspan=5>${list.caption }</td>
							</tr>
						</c:forEach>  --%>
                       <%--  <tr>
                            <td colspan="6">最低成交价：${data.zuidiPrice }元，优惠力度：${data.lidu}% 最低成交优惠组合：${data.youhui }</td>
                        </tr> --%>
                    </tbody>
                </table>

            </div>
            <div class="mainMsg introduce">
                <h2>重要提示</h2>
                <table>
                	<tbody>
                	<tr>
                		<td colspan="6">
                			<c:if test="${data.project.importanceMsg !=null}">
                            	${data.project.importanceMsg}
                            </c:if>
                            <c:if test="${data.project.importanceMsg ==null}">
                            	未知
                            </c:if>
                        </td>
                	</tr>
                	</tbody>
                </table>
            </div>
            <div class="untiMsg introduce">
               <h2>单元信息</h2>
                <table class="cssTable">
                    <tbody>
                        <tr>
                            <td class="cos1">单元总公摊</td>
                            <td class="cos2">
                            	<c:if test="${data.house.sharedArea !=null}">
                            	${data.house.sharedArea }%
                            	</c:if>
                            	<c:if test="${data.house.sharedArea ==null}">
                            	未知
                            	</c:if>
                            </td>
                            <td class="cos1">得房率</td>
                            <td class="cos2">
                            	<c:if test="${data.projectBuildingUnit.unitEfficiencyRate !=null}">
                            	${data.projectBuildingUnit.unitEfficiencyRate }%
                            	</c:if>
                            	<c:if test="${data.projectBuildingUnit.unitEfficiencyRate ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="cos1">单元总户数</td>
                            <td class="cos2">
                            	<c:if test="${data.unitTotal !=null}">
                            	${data.unitTotal }
                            	</c:if>
                            	<c:if test="${data.unitTotal ==null}">
                            	未知
                            	</c:if>
                            </td>
                            <td class="cos1">电梯数</td>
                            <td class="cos2">
                            	<c:if test="${data.projectBuildingUnit.liftNum !=null}">
                            	${data.projectBuildingUnit.liftNum }
                            	</c:if>
                            	<c:if test="${data.projectBuildingUnit.liftNum ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr> 
                        <tr>
                            <td class="cos1">单元号</td>
                            <td class="cos2">
                            	<c:if test="${data.projectBuildingUnit.unitName !=null}">
                            	${data.projectBuildingUnit.unitName }
                            	</c:if>
                            	<c:if test="${data.projectBuildingUnit.unitName ==null}">
                            	未知
                            	</c:if>
                            </td>
                            <td class="cos1">单元层数</td>
                            <td class="cos2">
                            	<c:if test="${data.projectBuildingUnit.floorNum !=null}">
                            	${data.projectBuildingUnit.floorNum }
                            	</c:if>
                            	<c:if test="${data.projectBuildingUnit.floorNum ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr>
                        <!-- <tr>
                            <td class="cos1">单元特性</td>
                            <td colspan="3">
                            	<ul class="tablist">
        							<li>没有</li>
       			 				</ul>
        					</td>
                        </tr> -->
                    </tbody>
                </table>
            </div>
            <div class="untiMsg introduce">
               <h2>楼栋信息</h2>
                <table class="cssTable">
                    <tbody>
                    	<tr>
                            <td class="cos1">楼栋位置</td>
                            <td colspan="3">
                            	呵呵呵的
        					</td>
                        </tr>
                        <tr>
                            <td class="cos1">楼栋号</td>
                            <td class="cos2">
                            	<c:if test="${data.projectBuildingUnit.buildingId !=null}">
                            	${data.projectBuildingUnit.buildingId }
                            	</c:if>
                            	<c:if test="${data.projectBuildingUnit.buildingId ==null}">
                            	未知
                            	</c:if>
                            </td>
                            <td class="cos1">单元数</td>
                            <td class="cos2">
                            	<c:if test="${data.unitNum !=null}">
                            	${data.unitNum }
                            	</c:if>
                            	<c:if test="${data.unitNum ==null}">
                            	未知
                            	</c:if>
                           	</td>
                        </tr>
                        <tr>
                            <td class="cos1">最大楼层</td>
                            <td class="cos2">没有</td>
                            <td class="cos1">楼栋总户数</td>
                            <td class="cos2">没有</td>
                        </tr> 
                        <tr>
                            <td class="cos1">直连地库</td>
                            <td class="cos2">
                            	<c:if test="${data.projectBuilding.isToBasement ==1}">
                            	否
                            	</c:if>
                            	<c:if test="${data.projectBuilding.isToBasement ==2}">
                            	是
                            	</c:if>
                            	<c:if test="${data.projectBuilding.isToBasement ==null}">
                            	未知
                            	</c:if>
							</td>
                            <td class="cos1">架空层</td>
                            <td class="cos2">
								<c:if test="${data.projectBuilding.emptySpace ==0}">
                            	没有
                            	</c:if>
                            	<c:if test="${data.projectBuilding.emptySpace !=0}">
                            	${data.projectBuilding.emptySpace }
                            	</c:if>
                            	<c:if test="${data.projectBuilding.emptySpace ==null}">
                            	未知
                            	</c:if>
							</td>
                        </tr>
                        <tr>
                            <td class="cos1">非机动车库</td>
                            <td class="cos2">
								<c:if test="${data.projectBuilding.emptySpace ==0}">
                            	无
                            	</c:if>
                            	<c:if test="${data.projectBuilding.emptySpace ==1}">
                            	有
                            	</c:if>
                            	<c:if test="${data.projectBuilding.emptySpace ==null}">
                            	未知
                            	</c:if>
							</td>
                            <td class="cos1">裙房</td>
                            <td class="cos2">
								<c:if test="${data.projectBuilding.emptySpace ==0}">
                            	无
                            	</c:if>
                            	<c:if test="${data.projectBuilding.emptySpace ==1}">
                            	有
                            	</c:if>
                            	<c:if test="${data.projectBuilding.emptySpace ==null}">
                            	未知
                            	</c:if>
							</td>
                        </tr>
                        <tr>
                            <td class="cos1">结构形式</td>
                            <td colspan="3">123
                            	<%-- <c:if test="${data.projectBuilding.constitut }==null">
                            	${data.projectBuilding.constitut }
                            	</c:if>
                            	<c:if test="${data.projectBuilding.constitut }!=null">
                            	未知
                            	</c:if> --%>
                            </td>                                                                                                                                                                                    
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="payCriterion introduce">
                <h2>交付信息</h2>
                <table class="cssTable">
                    <tbody>
                    	<tr>
                            <td class="cos1">交付时间</td>
                            <td colspan="3">
                            	<c:if test="${data.project.deliverTime !=null}">
                            	${data.project.deliverTime }
                            	</c:if>
                            	<c:if test="${data.project.deliverTime ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr>
                        <!-- <tr>
                            <td class="cos1">毛胚交付</td>
                            <td colspan="3">
                            	<p class="room-cell">
                            		没有
                            		<a class="chakanxq" href="house_coarse_delivery_detail">查看详情</a>
                            	</p>
                            	
        					</td>                                                                                                                                                                                    
                        </tr>
                        <tr>       
                            <td class="cos1">精装交付</td>
                            <td colspan="3">
                            	<p class="room-cell">
                            		没有
                            		<a class="chakanxq" href="house_exquisite_delivery_detail">查看详情</a>
                            	</p>
                            	
        					</td>                                                                                                                                                                                    
                        </tr> -->
                    </tbody>
                </table>
            </div>
            <div class="introduce">
                <h2>项目信息</h2>
                <ul class="tablist">
        			<c:forEach items="${data.projectTags }" var="list">	
       					<li>${list }</li>						
					</c:forEach> 
       			</ul>
                <table class="cssTable">
                    <tbody>
                    	<tr>
                            <td class="cos1">开盘时间</td>
                            <td class="cos2">
                            	<c:if test="${data.project.openingTime !=null}">
                            	${data.project.openingTime }
                            	</c:if>
                            	<c:if test="${data.project.openingTime ==null}">
                            	未知
                            	</c:if>
                           </td>
                            <td class="cos1">所属区位</td>
                            <td class="cos2">
                            	<c:if test="${data.house.district !=null}">
                            	${data.house.district }
                            	</c:if>
                            	<c:if test="${data.house.district ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="cos1">项目地址</td>
                            <td colspan="3">
                            	<c:if test="${data.project.propertyAddress !=null}">
                            	${data.project.propertyAddress }
                            	</c:if>
                            	<c:if test="${data.project.propertyAddress ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="cos1">用地面积</td>
                            <td class="cos2">
                            	<c:if test="${data.project.landArea !=null}">
                            	${data.project.landArea }
                            	</c:if>
                            	<c:if test="${data.project.landArea ==null}">
                            	未知
                            	</c:if>
                            </td>
                            <td class="cos1">总建面积</td>
                            <td class="cos2">
                            	<c:if test="${data.project.buildArea !=null}">
                            	${data.project.buildArea }
                            	</c:if>
                            	<c:if test="${data.project.buildArea ==null}">
                            	未知
                            	</c:if>
                           </td>
                        </tr>
                        <tr>
                            <td class="cos1">计容面积</td>
                            <td class="cos2">
								<c:if test="${data.project.capacityArea !=null}">
                            	${data.project.capacityArea }
                            	</c:if>
                            	<c:if test="${data.project.capacityArea ==null}">
                            	未知
                            	</c:if>
							</td>
                            <td class="cos1">地上面积</td>
                            <td class="cos2">
                            	<c:if test="${data.project.groundArea !=null}">
                            	${data.project.groundArea }
                            	</c:if>
                            	<c:if test="${data.project.groundArea ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="cos1">总户数</td>
                            <td class="cos2">
                            	<c:if test="${data.project.unitCount ==null}">
                            	${data.project.unitCount }
                            	</c:if>
                            	<c:if test="${data.project.unitCount !=null}">
                            	未知
                            	</c:if>
                           	</td>
                            <td class="cos1">容积率</td>
                            <td class="cos2">
                            	<c:if test="${data.project.volumeRatio !=null}">
                            	${data.project.volumeRatio }
                            	</c:if>
                            	<c:if test="${data.project.volumeRatio ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="cos1">车位比</td>
                            <td class="cos2">
                            	<c:if test="${data.parkingRate !=null}">
                            	${data.parkingRate }
                            	</c:if>
                            	<c:if test="${data.parkingRate ==null}">
                            	未知
                            	</c:if>
                            </td>
                            <td class="cos1">车位数</td>
                            <td class="cos2">
                            	<c:if test="${data.project.parking !=null}">
                            	${data.project.parking }
                            	</c:if>
                            	<c:if test="${data.project.parking ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="cos1">建筑密度</td>
                            <td class="cos2">
                            	<c:if test="${data.project.density !=null}">
                            	${data.project.density }
                            	</c:if>
                            	<c:if test="${data.project.density ==null}">
                            	未知
                            	</c:if>
                            </td>
                            <td class="cos1">绿化率</td>
                            <td class="cos2">
                            	<c:if test="${data.project.afforestationRatio !=null}">
                            	${data.project.afforestationRatio }
                            	</c:if>
                            	<c:if test="${data.project.afforestationRatio ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr>                     
                        <tr>
                            <td class="cos1">物业类型</td>
                            <td colspan="3">
                            	<c:if test="${data.project.propertyType !=null}">
                            	${data.project.propertyType }
                            	</c:if>
                            	<c:if test="${data.project.propertyType ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="cos1">售楼部位置</td>
                            <td colspan="3">	
                            	<c:if test="${data.project.saleAddress !=null}">
                            	${data.project.saleAddress }
                            	</c:if>
                            	<c:if test="${data.project.saleAddress ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="introduce">
                <h2>配套信息</h2>
                <table class="cssTable">
                    <tbody>
                    	<tr>
                            <td colspan="2" style="background: #f3f3f3;">自建配套</td>
                            <td colspan="2" style="background: #f3f3f3;">周边配套</td>
                        </tr>
                        <tr>
                            <td class="cos1">小区会所</td>
                            <td class="cos2">
                            	<c:if test="${data.project.ownerClubArea !=null}">
                            	${data.project.ownerClubArea }
                            	</c:if>
                            	<c:if test="${data.project.ownerClubArea ==null}">
                            	未知
                            	</c:if>
                            </td>
                            <td class="cos1">商业</td>
                            <td class="cos2">
                            	<c:if test="${data.project.business !=null}">
                            	${data.project.business }
                            	</c:if>
                            	<c:if test="${data.project.business ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="cos1">健身房</td>
                            <td class="cos2">
                            	<c:if test="${data.project.ownerGym !=null}">
                            	${data.project.ownerGym }
                            	</c:if>
                            	<c:if test="${data.project.ownerGym ==null}">
                            	未知
                            	</c:if>
                            </td>
                            <td class="cos1">教育</td>
                            <td class="cos2">
                            	<c:if test="${data.project.education !=null}">
                            	${data.project.education }
                            	</c:if>
                            	<c:if test="${data.project.education ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="cos1">社区中心</td>
                            <td class="cos2">
                            	<c:if test="${data.project.ownerCommunityCenter !=null}">
                            	${data.project.ownerCommunityCenter }
                            	</c:if>
                            	<c:if test="${data.project.ownerCommunityCenter ==null}">
                            	未知
                            	</c:if>
                            </td>
                            <td class="cos1">金融</td>
                            <td class="cos2">
                            	<c:if test="${data.project.financial !=null}">
                            	${data.project.financial }
                            	</c:if>
                            	<c:if test="${data.project.financial ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="cos1">游泳池</td>
                            <td class="cos2">
                            	<c:if test="${data.project.ownerSwimmingPool !=null}">
                            	${data.project.ownerSwimmingPool }
                            	</c:if>
                            	<c:if test="${data.project.ownerSwimmingPool ==null}">
                            	未知
                            	</c:if>
                            </td>
                            <td class="cos1">交通</td>
                            <td class="cos2">
                            	<c:if test="${data.project.transportation !=null}">
                            	${data.project.transportation }
                            	</c:if>
                            	<c:if test="${data.project.transportation ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="cos1">学校</td>
                            <td class="cos2">
                            	<c:if test="${data.project.ownerSchool !=null}">
                            	${data.project.ownerSchool }
                            	</c:if>
                            	<c:if test="${data.project.ownerSchool ==null}">
                            	未知
                            	</c:if>
                            </td>
                            <td class="cos1">医疗</td>
                            <td class="cos2">
                            	<c:if test="${data.project.medical !=null}">
                            	${data.project.medical }
                            	</c:if>
                            	<c:if test="${data.project.medical ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr>                     
                        <tr>
                            <td class="cos1">幼儿园</td>
                            <td class="cos2">
                            	<c:if test="${data.project.ownerKindergarten !=null}">
                            	${data.project.ownerKindergarten }
                            	</c:if>
                            	<c:if test="${data.project.ownerKindergarten ==null}">
                            	未知
                            	</c:if>
                            </td>
                            <td class="cos1">其他</td>
                            <td class="cos2">
                            	<c:if test="${data.project.other !=null}">
                            	${data.project.other }
                            	</c:if>
                            	<c:if test="${data.project.other ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr> 
                        <tr>
                            <td class="cos1">商业</td>
                            <td class="cos2">
                            	<c:if test="${data.project.ownerBusiness !=null}">
                            	${data.project.ownerBusiness }
                            	</c:if>
                            	<c:if test="${data.project.ownerBusiness ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr> 
                        <tr>
                            <td class="cos1">其他</td>
                            <td class="cos2">
                            	<c:if test="${data.project.ownerOther !=null}">
                            	${data.project.ownerOther }
                            	</c:if>
                            	<c:if test="${data.project.ownerOther ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr> 
                    </tbody>
                </table>
            </div>
            <div class="introduce">
                <h2>开发商信息</h2>
                <table class="cssTable">
                    <tbody>
                        <tr>
                            <td class="cos1">开发商</td>
                            <td colspan="3">
                            	<c:if test="${data.project.developer !=null}">
                            	${data.project.developer }
                            	</c:if>
                            	<c:if test="${data.project.developer ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr>
                         <tr>
                            <td class="cos1">投资商</td>
                            <td colspan="3">
                            	<c:if test="${data.project.investor !=null}">
                            	${data.project.investor }
                            	</c:if>
                            	<c:if test="${data.project.investor ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr>                 
                        <tr>
                            <td class="cos1">企业性质</td>
                            <td colspan="3">
								<c:if test="${data.project.typeid ==1}">
                            	民企
                            	</c:if>
                            	<c:if test="${data.project.typeid ==2}">
                            	国企
                            	</c:if>
                            	<c:if test="${data.project.typeid ==3}">
                            	上市公司
                            	</c:if>
                            	<c:if test="${data.project.typeid ==null}">
                            	未知
                            	</c:if>
							</td>
                        </tr>
                        <tr>
                            <td class="cos1">开发资质</td>
                            <td colspan="3">
								<c:if test="${data.project.developmenStatus ==1}">
                            	1级
                            	</c:if>
                            	<c:if test="${data.project.developmenStatus ==2}">
                            	1级暂定
                            	</c:if>
                            	<c:if test="${data.project.developmenStatus ==3}">
                            	2级暂定
                            	</c:if>
                            	<c:if test="${data.project.developmenStatus ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr>
                         <tr>
                            <td class="cos1">设计单位</td>
                            <td colspan="3">
                            	<c:if test="${data.project.designCompany !=null}">
                            	${data.project.designCompany }
                            	</c:if>
                            	<c:if test="${data.project.designCompany ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr>
                         <tr>
                            <td class="cos1">施工单位</td>
                            <td colspan="3">
                            	<c:if test="${data.project.constructionCompany !=null}">
                            	${data.project.constructionCompany }
                            	</c:if>
                            	<c:if test="${data.project.constructionCompany ==null}">
                            	未知
                            	</c:if>
                           </td>
                        </tr>
                         <tr>
                            <td class="cos1">物业管理公司</td>
                            <td colspan="3">
                            	<c:if test="${data.project.manager !=null}">
                            	${data.project.manager }
                            	</c:if>
                            	<c:if test="${data.project.manager ==null}">
                            	未知
                            	</c:if>
                            </td>
                        </tr>
                         <tr>
                            <td class="cos1">开工时间</td>
                            <td colspan="3">
                            	<c:if test="${data.project.startTime !=null}">
                            	${data.project.startTime }
                            	</c:if>
                            	<c:if test="${data.project.startTime ==null}">
                            	未知
                            	</c:if>
                           	</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="maskStu" id="tishi">
		<div class="tishi_content">
			<h1>提示</h1>
			<p>该客户不属于您</p>
			<button id="tishi_cancle">确定</button>
		</div>
	</div>
</div>
 <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
    <script type="text/javascript"> 
        mui.init({
            swipeBack:true //启用右滑关闭功能
        });
        var slider = mui("#slider");
        slider.slider({
            interval: 5000
        });
        
        $(document).ready(function () {
        	var houseNum= ${data.house.houseNum};
        	$("#jiaruDuibi").bind("click", function () {
        		$.ajax({
    				url : 'updateCollectHouse',
    				type : 'post',
    				dataType : 'json',
    				data : {
    					houseNum:houseNum,
    					flag:1
    				},
    				success : function(data) {
    					console.log(data);
    					if(data.status==200){
    						console.log(data.message);
    						$('#tishi').show();						
    					}
    				}
    			});
        	});
        });
    </script>
</body>
</html>