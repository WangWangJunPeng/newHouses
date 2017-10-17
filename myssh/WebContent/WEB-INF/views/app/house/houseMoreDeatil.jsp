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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/houseMoreDetail.css" />
    
	<link rel="stylesheet" href="static/css/suitable.css" />
    <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	<title>详细信息</title>
</head>
<body class="mui-ios mui-ios-9 mui-ios-9-1">
<header class="mui-bar mui-bar-nav mui-bar-transparent">
        <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
        <h1 class="mui-title">详细信息</h1>
</header>
<div class="mui-content">
			<div class="title">
				房源基本信息
			</div>
			<ul class="mui-table-view">
				 <li class="mui-table-view-cell">物业类型
				 	<span class="mui-pull-right">						
                        <c:if test="${data.sortNum ==1}">
                           别墅
                        </c:if>
                        <c:if test="${data.sortNum ==2}">
                           小高层
                        </c:if>
                        <c:if test="${data.sortNum ==3}">
                           高层
                        </c:if>
                        <c:if test="${data.sortNum ==4}">
                           超高层
                        </c:if>
                        <c:if test="${data.sortNum ==5}">
                           叠排
                        </c:if>
                        <c:if test="${data.sortNum ==6}">
                           多层
                        </c:if>
                        <c:if test="${data.sortNum ==7}">
                           排屋
                        </c:if>
                        <c:if test="${data.sortNum ==8}">
                           商业街
                        </c:if>
                        <c:if test="${data.sortNum ==9}">
                           集中商业
                        </c:if>
                        <c:if test="${data.sortNum ==10}">
                           酒店公寓
                        </c:if>
                        <c:if test="${data.sortNum ==11}">
                           底商
                        </c:if>
                        <c:if test="${data.sortNum ==12}">
                           soho
                        </c:if>
                        <c:if test="${data.sortNum ==13}">
                           写字楼
                        </c:if>
                        <c:if test="${data.sortNum ==null}">
                           未知
                        </c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">房屋用途/年限
				 	<span class="mui-pull-right">
						<c:if test="${data.natureOfProperty !=null}">
                           ${data.natureOfProperty}/
                        </c:if>
                        <c:if test="${data.natureOfProperty ==null}">
                           未知
                        </c:if> 
                        <c:if test="${data.propertyYear !=null}">
                           ${data.propertyYear}年
                        </c:if>
                        <c:if test="${data.propertyYear ==null}">
                           未知
                        </c:if> 
					</span>
				 </li>
				 <li class="mui-table-view-cell">户型
				 	<span class="mui-pull-right">
						<c:if test="${data.houseTypeName !=null}">
                           ${data.houseTypeName}
                        </c:if>
                        <c:if test="${data.houseTypeName ==null}">
                           未知
                        </c:if> 
					</span>
				 </li>
				 <li class="mui-table-view-cell">所在楼层
				 	<span class="mui-pull-right">
						<c:if test="${data.floor !=null}">
                           ${data.floor}楼
                        </c:if>
                        <c:if test="${data.floor ==null}">
                           未知
                        </c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">建筑面积
				 	<span class="mui-pull-right">
						<c:if test="${data.buildArea !=null}">
                            		${data.buildArea }m²
                            	</c:if>                       	
                            	<c:if test="${data.buildArea ==null}">
                            		<c:if test="${data.preBuildArea !=null}">
                            		${data.preBuildArea }m²（预测）
                            		</c:if>
                            		<c:if test="${data.preBuildArea ==null}">
                            		未知
                            		</c:if>
                            	</c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">套内面积
				 	<span class="mui-pull-right">
						<c:if test="${data.insideSpace !=null}">
                           ${data.insideSpace}m²
                        </c:if>
                        <c:if test="${data.insideSpace ==null}">
                           未知
                        </c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">公摊面积
				 	<span class="mui-pull-right">
						<c:if test="${data.sharedArea !=null}">
                           ${data.sharedArea}m²
                        </c:if>
                        <c:if test="${data.sharedArea ==null}">
                           未知
                        </c:if> 
					</span>
				 </li>
				 <li class="mui-table-view-cell">占地面积
				 	<span class="mui-pull-right">
						<c:if test="${data.floorArea !=null}">
                           ${data.floorArea}m²
                        </c:if>
                        <c:if test="${data.floorArea ==null}">
                           未知
                        </c:if> 
					</span>
				 </li>
				 <li class="mui-table-view-cell">非计容面积
				 	<span class="mui-pull-right">
						<c:if test="${data.novolumeArea !=null}">
                           ${data.novolumeArea}m²
                        </c:if>
                        <c:if test="${data.novolumeArea ==null}">
                           未知
                        </c:if> 
					</span>
				 </li>
				 <li class="mui-table-view-cell">实得面积
				 	<span class="mui-pull-right">
						<c:if test="${data.takeArea !=null}">
                           ${data.takeArea}m²
                        </c:if>
                        <c:if test="${data.takeArea ==null}">
                           未知
                        </c:if> 
					</span>
				 </li>
				 <li class="mui-table-view-cell">实得面积比
				 	<span class="mui-pull-right">
						<c:if test="${data.takeAreaProportion !=null}">
                           ${data.takeAreaProportion}%
                        </c:if>
                        <c:if test="${data.takeAreaProportion ==null}">
                           未知
                        </c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">楼栋朝向
				 	<span class="mui-pull-right">
						<c:if test="${data.direct !=null}">
                           ${data.direct}
                        </c:if>
                        <c:if test="${data.direct ==null}">
                           未知
                        </c:if> 
					</span>
				 </li>
				 <li class="mui-table-view-cell">户型位置
				 	<span class="mui-pull-right">
						<c:if test="${data.houseTypePosition ==1}">
                           边套
                        </c:if>
                        <c:if test="${data.houseTypePosition ==2}">
                           中间套
                        </c:if>
                        <c:if test="${data.houseTypePosition ==null}">
                           未知
                        </c:if> 
					</span>
				 </li>
				 <li class="mui-table-view-cell">总面宽
				 	<span class="mui-pull-right">
						<c:if test="${data.areaWide !=null}">
                           ${data.areaWide}米
                        </c:if>
                        <c:if test="${data.areaWide ==null}">
                           未知
                        </c:if> 
					</span>
				 </li>
				 <li class="mui-table-view-cell">采光面
				 	<span class="mui-pull-right">
						<c:if test="${data.lightDirection ==1}">
                           (朝东)
                        </c:if>
                        <c:if test="${data.lightDirection ==2}">
                           (朝南)
                        </c:if>
                        <c:if test="${data.lightDirection ==3}">
                           (朝西)
                        </c:if>
                        <c:if test="${data.lightDirection ==4}">
                           (朝北)
                        </c:if>
                        <c:if test="${data.lightDirection ==5}">
                           (朝东南)
                        </c:if>
                        <c:if test="${data.lightDirection ==6}">
                           (朝东北)
                        </c:if>
                        <c:if test="${data.lightDirection ==7}">
                           (朝西南)
                        </c:if>
                        <c:if test="${data.lightDirection ==8}">
                           (朝西北)
                        </c:if>
                        <c:if test="${data.lightDirection ==null}">
                           未知
                        </c:if> 
					</span>
				 </li>
				 <li class="mui-table-view-cell">最大进深
				 	<span class="mui-pull-right">
						<c:if test="${data.maxDeep !=null}">
                           ${data.maxDeep}米
                        </c:if>
                        <c:if test="${data.maxDeep ==null}">
                           未知
                        </c:if> 
					</span>
				 </li>
				 <li class="mui-table-view-cell">层高
				 	<span class="mui-pull-right">
						<c:if test="${data.floorHeight !=null}">
                           ${data.floorHeight}米
                        </c:if>
                        <c:if test="${data.floorHeight ==null}">
                           未知
                        </c:if> 
					</span>
				 </li>
				 <li class="mui-table-view-cell">日照时间
				 	<span class="mui-pull-right">
						<c:if test="${data.lightTime !=null}">
                           ${data.lightTime}小时
                        </c:if>
                        <c:if test="${data.lightTime ==null}">
                           未知
                        </c:if> 
					</span>
				 </li>
				 <li class="mui-table-view-cell">装修标准
				 	<span class="mui-pull-right">
						<c:if test="${data.renovationStandard ==1}">
                           精装
                        </c:if>
                        <c:if test="${data.renovationStandard ==1}">
                           毛胚
                        </c:if>
                        <c:if test="${data.renovationStandard ==null}">
                           未知
                        </c:if> 
					</span>
				 </li>
			</ul>

			<div class="title">
				房型详细参数
			</div>
			<ul class="mui-table-view">
				 <li class="mui-table-view-cell">主卧1
				 	<span class="mui-pull-right">
						<c:if test="${details.masterBedroomSize !=null}">
                           ${details.masterBedroomSize}
                        </c:if>
                        <c:if test="${details.masterBedroomSize ==null}">
                           未知
                        </c:if>
                        <c:if test="${details.masterBedroomDirection ==1}">
                           (朝东)
                        </c:if>
                        <c:if test="${details.masterBedroomDirection ==2}">
                           (朝南)
                        </c:if>
                        <c:if test="${details.masterBedroomDirection ==3}">
                           (朝西)
                        </c:if>
                        <c:if test="${details.masterBedroomDirection ==4}">
                           (朝北)
                        </c:if>
                        <c:if test="${details.masterBedroomDirection ==5}">
                           (朝东南)
                        </c:if>
                        <c:if test="${details.masterBedroomDirection ==6}">
                           (朝东北)
                        </c:if>
                        <c:if test="${details.masterBedroomDirection ==7}">
                           (朝西南)
                        </c:if>
                        <c:if test="${details.masterBedroomDirection ==8}">
                           (朝西北)
                        </c:if>
                        <c:if test="${details.masterBedroomDirection ==null}">
                           (未知)
                        </c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">主卧2
				 	<span class="mui-pull-right">
						<c:if test="${details.secondMasterBedroomSize !=null}">
                           ${details.secondMasterBedroomSize}
                        </c:if>
                        <c:if test="${details.secondMasterBedroomSize ==null}">
                           未知
                        </c:if>
                        <c:if test="${details.secondMasterBedroomDirection ==1}">
                           (朝东)
                        </c:if>
                        <c:if test="${details.secondMasterBedroomDirection ==2}">
                           (朝南)
                        </c:if>
                        <c:if test="${details.secondMasterBedroomDirection ==3}">
                           (朝西)
                        </c:if>
                        <c:if test="${details.secondMasterBedroomDirection ==4}">
                           (朝北)
                        </c:if>
                        <c:if test="${details.secondMasterBedroomDirection ==5}">
                           (朝东南)
                        </c:if>
                        <c:if test="${details.secondMasterBedroomDirection ==6}">
                           (朝东北)
                        </c:if>
                        <c:if test="${details.secondMasterBedroomDirection ==7}">
                           (朝西南)
                        </c:if>
                        <c:if test="${details.secondMasterBedroomDirection ==8}">
                           (朝西北)
                        </c:if>
                        <c:if test="${details.secondMasterBedroomDirection ==null}">
                           (未知)
                        </c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">次卧1
				 	<span class="mui-pull-right">
						<c:if test="${details.bedroomSize !=null}">
                           ${details.bedroomSize}
                        </c:if>
                        <c:if test="${details.bedroomSize ==null}">
                           未知
                        </c:if>
                        <c:if test="${details.bedroomDirection ==1}">
                           (朝东)
                        </c:if>
                        <c:if test="${details.bedroomDirection ==2}">
                           (朝南)
                        </c:if>
                        <c:if test="${details.bedroomDirection ==3}">
                           (朝西)
                        </c:if>
                        <c:if test="${details.bedroomDirection ==4}">
                           (朝北)
                        </c:if>
                        <c:if test="${details.bedroomDirection ==5}">
                           (朝东南)
                        </c:if>
                        <c:if test="${details.bedroomDirection ==6}">
                           (朝东北)
                        </c:if>
                        <c:if test="${details.bedroomDirection ==7}">
                           (朝西南)
                        </c:if>
                        <c:if test="${details.bedroomDirection ==8}">
                           (朝西北)
                        </c:if>
                        <c:if test="${details.bedroomDirection ==null}">
                           (未知)
                        </c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">次卧2
				 	<span class="mui-pull-right">
						<c:if test="${details.secondBedroomSize !=null}">
                           ${details.secondBedroomSize}
                        </c:if>
                        <c:if test="${details.secondBedroomSize ==null}">
                           未知
                        </c:if>
                        <c:if test="${details.secondBedroomDirection ==1}">
                           (朝东)
                        </c:if>
                        <c:if test="${details.secondBedroomDirection ==2}">
                           (朝南)
                        </c:if>
                        <c:if test="${details.secondBedroomDirection ==3}">
                           (朝西)
                        </c:if>
                        <c:if test="${details.secondBedroomDirection ==4}">
                           (朝北)
                        </c:if>
                        <c:if test="${details.secondBedroomDirection ==5}">
                           (朝东南)
                        </c:if>
                        <c:if test="${details.secondBedroomDirection ==6}">
                           (朝东北)
                        </c:if>
                        <c:if test="${details.secondBedroomDirection ==7}">
                           (朝西南)
                        </c:if>
                        <c:if test="${details.secondBedroomDirection ==8}">
                           (朝西北)
                        </c:if>
                        <c:if test="${details.secondBedroomDirection ==null}">
                           (未知)
                        </c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">书房
				 	<span class="mui-pull-right">
						<c:if test="${details.schoolroomSize !=null}">
                           ${details.schoolroomSize}
                        </c:if>
                        <c:if test="${details.schoolroomSize ==null}">
                           未知
                        </c:if>
                        <c:if test="${details.schoolroomDirection ==1}">
                           (朝东)
                        </c:if>
                        <c:if test="${details.schoolroomDirection ==2}">
                           (朝南)
                        </c:if>
                        <c:if test="${details.schoolroomDirection ==3}">
                           (朝西)
                        </c:if>
                        <c:if test="${details.schoolroomDirection ==4}">
                           (朝北)
                        </c:if>
                        <c:if test="${details.schoolroomDirection ==5}">
                           (朝东南)
                        </c:if>
                        <c:if test="${details.schoolroomDirection ==6}">
                           (朝东北)
                        </c:if>
                        <c:if test="${details.schoolroomDirection ==7}">
                           (朝西南)
                        </c:if>
                        <c:if test="${details.schoolroomDirection ==8}">
                           (朝西北)
                        </c:if>
                        <c:if test="${details.schoolroomDirection ==null}">
                           (未知)
                        </c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">餐厅
				 	<span class="mui-pull-right">
						<c:if test="${details.diningRoomSize !=null}">
                           ${details.diningRoomSize}
                        </c:if>
                        <c:if test="${details.diningRoomSize ==null}">
                           未知
                        </c:if>
                        <c:if test="${details.diningRoomDirection ==1}">
                           (朝东)
                        </c:if>
                        <c:if test="${details.diningRoomDirection ==2}">
                           (朝南)
                        </c:if>
                        <c:if test="${details.diningRoomDirection ==3}">
                           (朝西)
                        </c:if>
                        <c:if test="${details.diningRoomDirection ==4}">
                           (朝北)
                        </c:if>
                        <c:if test="${details.diningRoomDirection ==5}">
                           (朝东南)
                        </c:if>
                        <c:if test="${details.diningRoomDirection ==6}">
                           (朝东北)
                        </c:if>
                        <c:if test="${details.diningRoomDirection ==7}">
                           (朝西南)
                        </c:if>
                        <c:if test="${details.diningRoomDirection ==8}">
                           (朝西北)
                        </c:if>
                        <c:if test="${details.diningRoomDirection ==null}">
                           (未知)
                        </c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">客厅
				 	<span class="mui-pull-right">
						<c:if test="${details.livingRoomSize !=null}">
                           ${details.livingRoomSize}
                        </c:if>
                        <c:if test="${details.livingRoomSize ==null}">
                           未知
                        </c:if>
                        <c:if test="${details.livingRoomDirection ==1}">
                           (朝东)
                        </c:if>
                        <c:if test="${details.livingRoomDirection ==2}">
                           (朝南)
                        </c:if>
                        <c:if test="${details.livingRoomDirection ==3}">
                           (朝西)
                        </c:if>
                        <c:if test="${details.livingRoomDirection ==4}">
                           (朝北)
                        </c:if>
                        <c:if test="${details.livingRoomDirection ==5}">
                           (朝东南)
                        </c:if>
                        <c:if test="${details.livingRoomDirection ==6}">
                           (朝东北)
                        </c:if>
                        <c:if test="${details.livingRoomDirection ==7}">
                           (朝西南)
                        </c:if>
                        <c:if test="${details.livingRoomDirection ==8}">
                           (朝西北)
                        </c:if>
                        <c:if test="${details.livingRoomDirection ==null}">
                           (未知)
                        </c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">卫生间1
				 	<span class="mui-pull-right">
						<c:if test="${details.bathroomSizeOne !=null}">
                           ${details.bathroomSizeOne}
                        </c:if>
                        <c:if test="${details.bathroomSizeOne ==null}">
                           未知
                        </c:if>
                        <c:if test="${details.bathroomLightOne ==0}">
                           (暗)
                        </c:if>
                        <c:if test="${details.bathroomLightOne ==1}">
                           (明)
                        </c:if>  
                        <c:if test="${details.bathroomLightOne ==null}">
                           (未知)
                        </c:if>                   
					</span>
				 </li>
				 <li class="mui-table-view-cell">卫生间2
				 	<span class="mui-pull-right">
						<c:if test="${details.bathroomSizeTwo !=null}">
                           ${details.bathroomSizeTwo}
                        </c:if>
                        <c:if test="${details.bathroomSizeTwo ==null}">
                           未知
                        </c:if>
                        <c:if test="${details.bathroomLightTwo ==0}">
                           (暗)
                        </c:if>
                        <c:if test="${details.bathroomLightTwo ==1}">
                           (明)
                        </c:if>  
                        <c:if test="${details.bathroomLightTwo ==null}">
                           (未知)
                        </c:if>                 
					</span>
				 </li>
				 <li class="mui-table-view-cell">卫生间3
				 	<span class="mui-pull-right">
						<c:if test="${details.bathroomSizeThree !=null}">
                           ${details.bathroomSizeThree}
                        </c:if>
                        <c:if test="${details.bathroomSizeThree ==null}">
                           未知
                        </c:if>
                        <c:if test="${details.bathroomLightThree ==0}">
                           (暗)
                        </c:if>
                        <c:if test="${details.bathroomLightThree ==1}">
                           (明)
                        </c:if>      
                        <c:if test="${details.bathroomLightThree ==null}">
                           (未知)
                        </c:if>                 
					</span>
				 </li>
				 <li class="mui-table-view-cell">卫生间4
				 	<span class="mui-pull-right">
						<c:if test="${details.bathroomSizeFour !=null}">
                           ${details.bathroomSizeFour}
                        </c:if>
                        <c:if test="${details.bathroomSizeFour ==null}">
                           未知
                        </c:if>
                        <c:if test="${details.bathroomLightFour ==0}">
                           (暗)
                        </c:if>
                        <c:if test="${details.bathroomLightFour ==1}">
                           (明)
                        </c:if>            
                        <c:if test="${details.bathroomLightFour ==null}">
                           (未知)
                        </c:if>       
					</span>
				 </li>
				 <li class="mui-table-view-cell">卫生间5
				 	<span class="mui-pull-right">
						<c:if test="${details.bathroomSizeFive !=null}">
                           ${details.bathroomSizeFive}
                        </c:if>
                        <c:if test="${details.bathroomSizeFive ==null}">
                           未知
                        </c:if>
                        <c:if test="${details.bathroomLightFive ==0}">
                           (暗)
                        </c:if>
                        <c:if test="${details.bathroomLightFive ==1}">
                           (明)
                        </c:if>    
                        <c:if test="${details.bathroomLightFive ==null}">
                           (未知)
                        </c:if>                
					</span>
				 </li>
				 <li class="mui-table-view-cell">厨房
				 	<span class="mui-pull-right">
						<c:if test="${details.kitchenSize !=null}">
                           ${details.kitchenSize}
                        </c:if>
                        <c:if test="${details.kitchenSize ==null}">
                           未知
                        </c:if>              
					</span>
				 </li>
				 <li class="mui-table-view-cell">工人房
				 	<span class="mui-pull-right">
						<c:if test="${details.maidBedroomSize !=null}">
                           ${details.maidBedroomSize}
                        </c:if>
                        <c:if test="${details.maidBedroomSize ==null}">
                           未知
                        </c:if>
                        <c:if test="${details.maidBedroomLight ==0}">
                           (暗)
                        </c:if>
                        <c:if test="${details.maidBedroomLight ==1}">
                         	(明)
                        </c:if>     
                        <c:if test="${details.maidBedroomLight ==null}">
                           (未知)
                        </c:if>                 
					</span>
				 </li>
				 <li class="mui-table-view-cell">洗衣房
				 	<span class="mui-pull-right">
						<c:if test="${details.laundryRoomSize !=null}">
                           ${details.laundryRoomSize}
                        </c:if>
                        <c:if test="${details.laundryRoomSize ==null}">
                           未知
                        </c:if>              
					</span>
				 </li>
				 <li class="mui-table-view-cell">阳台1
				 	<span class="mui-pull-right">
						<c:if test="${details.balconySizeOne !=null}">
                           ${details.balconySizeOne}
                        </c:if>
                        <c:if test="${details.balconySizeOne ==null}">
                           未知
                        </c:if>
                        <c:if test="${details.balconyDirectionOne ==1}">
                           (朝东)
                        </c:if>
                        <c:if test="${details.balconyDirectionOne ==2}">
                           (朝南)
                        </c:if>
                        <c:if test="${details.balconyDirectionOne ==3}">
                           (朝西)
                        </c:if>
                        <c:if test="${details.balconyDirectionOne ==4}">
                           (朝北)
                        </c:if>
                        <c:if test="${details.balconyDirectionOne ==5}">
                           (朝东南)
                        </c:if>
                        <c:if test="${details.balconyDirectionOne ==6}">
                           (朝东北)
                        </c:if>
                        <c:if test="${details.balconyDirectionOne ==7}">
                           (朝西南)
                        </c:if>
                        <c:if test="${details.balconyDirectionOne ==8}">
                           (朝西北)
                        </c:if>
                        <c:if test="${details.balconyDirectionOne ==null}">
                           (未知)
                        </c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">阳台2
				 	<span class="mui-pull-right">
						<c:if test="${details.balconySizeTwo !=null}">
                           ${details.balconySizeTwo}
                        </c:if>
                        <c:if test="${details.balconySizeTwo ==null}">
                           未知
                        </c:if>
                        <c:if test="${details.balconyDirectionTwo ==1}">
                           (朝东)
                        </c:if>
                        <c:if test="${details.balconyDirectionTwo ==2}">
                           (朝南)
                        </c:if>
                        <c:if test="${details.balconyDirectionTwo ==3}">
                           (朝西)
                        </c:if>
                        <c:if test="${details.balconyDirectionTwo ==4}">
                           (朝北)
                        </c:if>
                        <c:if test="${details.balconyDirectionTwo ==5}">
                           (朝东南)
                        </c:if>
                        <c:if test="${details.balconyDirectionTwo ==6}">
                           (朝东北)
                        </c:if>
                        <c:if test="${details.balconyDirectionTwo ==7}">
                           (朝西南)
                        </c:if>
                        <c:if test="${details.balconyDirectionTwo ==8}">
                           (朝西北)
                        </c:if>
                        <c:if test="${details.balconyDirectionTwo ==null}">
                           (未知)
                        </c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">阳台3
				 	<span class="mui-pull-right">
						<c:if test="${details.balconySizeThree !=null}">
                           ${details.balconySizeThree}
                        </c:if>
                        <c:if test="${details.balconySizeThree ==null}">
                           未知
                        </c:if>
                        <c:if test="${details.balconyDirectionThree ==1}">
                           (朝东)
                        </c:if>
                        <c:if test="${details.balconyDirectionThree ==2}">
                           (朝南)
                        </c:if>
                        <c:if test="${details.balconyDirectionThree ==3}">
                           (朝西)
                        </c:if>
                        <c:if test="${details.balconyDirectionThree ==4}">
                           (朝北)
                        </c:if>
                        <c:if test="${details.balconyDirectionThree ==5}">
                           (朝东南)
                        </c:if>
                        <c:if test="${details.balconyDirectionThree ==6}">
                           (朝东北)
                        </c:if>
                        <c:if test="${details.balconyDirectionThree ==7}">
                           (朝西南)
                        </c:if>
                        <c:if test="${details.balconyDirectionThree ==8}">
                           (朝西北)
                        </c:if>
                        <c:if test="${details.balconyDirectionThree ==null}">
                           (未知)
                        </c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">设备平台
				 	<span class="mui-pull-right">
						<c:if test="${details.deviceStageSize !=null}">
                           ${details.deviceStageSize}
                        </c:if>
                        <c:if test="${details.deviceStageSize ==null}">
                           未知
                        </c:if>
                        <c:if test="${details.maidBedroomLight ==0}">
                           (暗)
                        </c:if>
                        <c:if test="${details.maidBedroomLight ==1}">
                         	(明)
                        </c:if>          
                        <c:if test="${details.maidBedroomLight ==null}">
                           (未知)
                        </c:if>         
					</span>
				 </li>
				 <li class="mui-table-view-cell">设备平台
				 	<span class="mui-pull-right">
						<c:if test="${details.deviceStageSize !=null}">
                           ${details.deviceStageSize}
                        </c:if>
                        <c:if test="${details.deviceStageSize ==null}">
                           未知
                        </c:if>
                       	<c:if test="${details.deviceStageDirection ==1}">
                           (朝东)
                        </c:if>
                        <c:if test="${details.deviceStageDirection ==2}">
                           (朝南)
                        </c:if>
                        <c:if test="${details.deviceStageDirection ==3}">
                           (朝西)
                        </c:if>
                        <c:if test="${details.deviceStageDirection ==4}">
                           (朝北)
                        </c:if>
                        <c:if test="${details.deviceStageDirection ==5}">
                           (朝东南)
                        </c:if>
                        <c:if test="${details.deviceStageDirection ==6}">
                           (朝东北)
                        </c:if>
                        <c:if test="${details.deviceStageDirection ==7}">
                           (朝西南)
                        </c:if>
                        <c:if test="${details.deviceStageDirection ==8}">
                           (朝西北)
                        </c:if>
                        <c:if test="${details.deviceStageDirection ==null}">
                           (未知)
                        </c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">露台1
				 	<span class="mui-pull-right">
						<c:if test="${details.gazeboSizeOne !=null}">
                           ${details.gazeboSizeOne}
                        </c:if>
                        <c:if test="${details.gazeboSizeOne ==null}">
                           未知
                        </c:if>
                       	<c:if test="${details.gazeboDirectionOne ==1}">
                           (朝东)
                        </c:if>
                        <c:if test="${details.gazeboDirectionOne ==2}">
                           (朝南)
                        </c:if>
                        <c:if test="${details.gazeboDirectionOne ==3}">
                           (朝西)
                        </c:if>
                        <c:if test="${details.gazeboDirectionOne ==4}">
                           (朝北)
                        </c:if>
                        <c:if test="${details.gazeboDirectionOne ==5}">
                           (朝东南)
                        </c:if>
                        <c:if test="${details.gazeboDirectionOne ==6}">
                           (朝东北)
                        </c:if>
                        <c:if test="${details.gazeboDirectionOne ==7}">
                           (朝西南)
                        </c:if>
                        <c:if test="${details.gazeboDirectionOne ==8}">
                           (朝西北)
                        </c:if>
                        <c:if test="${details.gazeboDirectionOne ==null}">
                           (未知)
                        </c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">露台2
				 	<span class="mui-pull-right">
						<c:if test="${details.gazeboSizeTwo !=null}">
                           ${details.gazeboSizeTwo}
                        </c:if>
                        <c:if test="${details.gazeboSizeTwo ==null}">
                           未知
                        </c:if>
                       	<c:if test="${details.gazeboDirectionTwo ==1}">
                           (朝东)
                        </c:if>
                        <c:if test="${details.gazeboDirectionTwo ==2}">
                           (朝南)
                        </c:if>
                        <c:if test="${details.gazeboDirectionTwo ==3}">
                           (朝西)
                        </c:if>
                        <c:if test="${details.gazeboDirectionTwo ==4}">
                           (朝北)
                        </c:if>
                        <c:if test="${details.gazeboDirectionTwo ==5}">
                           (朝东南)
                        </c:if>
                        <c:if test="${details.gazeboDirectionTwo ==6}">
                           (朝东北)
                        </c:if>
                        <c:if test="${details.gazeboDirectionTwo ==7}">
                           (朝西南)
                        </c:if>
                        <c:if test="${details.gazeboDirectionTwo ==8}">
                           (朝西北)
                        </c:if>
                        <c:if test="${details.gazeboDirectionTwo ==null}">
                           (未知)
                        </c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">阁楼
				 	<span class="mui-pull-right">
						<c:if test="${details.penthouseArea !=null}">
                           ${details.penthouseArea}
                        </c:if>
                        <c:if test="${details.penthouseArea ==null}">
                           未知
                        </c:if>              
					</span>
				 </li>
				 <li class="mui-table-view-cell">入户花园
				 	<span class="mui-pull-right">
						<c:if test="${details.indoorGardenArea !=null}">
                           ${details.indoorGardenArea}
                        </c:if>
                        <c:if test="${details.indoorGardenArea ==null}">
                           未知
                        </c:if>              
					</span>
				 </li>
				 <li class="mui-table-view-cell">花园1
				 	<span class="mui-pull-right">
						<c:if test="${details.gardenAreaOne !=null}">
                           ${details.gardenAreaOne}
                        </c:if>
                        <c:if test="${details.gardenAreaOne ==null}">
                           未知
                        </c:if>
                       	<c:if test="${details.gardenAreaOneDirection ==1}">
                           (朝东)
                        </c:if>
                        <c:if test="${details.gardenAreaOneDirection ==2}">
                           (朝南)
                        </c:if>
                        <c:if test="${details.gardenAreaOneDirection ==3}">
                           (朝西)
                        </c:if>
                        <c:if test="${details.gardenAreaOneDirection ==4}">
                           (朝北)
                        </c:if>
                        <c:if test="${details.gardenAreaOneDirection ==5}">
                           (朝东南)
                        </c:if>
                        <c:if test="${details.gardenAreaOneDirection ==6}">
                           (朝东北)
                        </c:if>
                        <c:if test="${details.gardenAreaOneDirection ==7}">
                           (朝西南)
                        </c:if>
                        <c:if test="${details.gardenAreaOneDirection ==8}">
                           (朝西北)
                        </c:if>
                        <c:if test="${details.gardenAreaOneDirection ==null}">
                           (未知)
                        </c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">花园1
				 	<span class="mui-pull-right">
						<c:if test="${details.gardenAreaOne !=null}">
                           ${details.gardenAreaOne}
                        </c:if>
                        <c:if test="${details.gardenAreaOne ==null}">
                           未知
                        </c:if>
                       	<c:if test="${details.gardenAreaOneDirection ==1}">
                           (朝东)
                        </c:if>
                        <c:if test="${details.gardenAreaOneDirection ==2}">
                           (朝南)
                        </c:if>
                        <c:if test="${details.gardenAreaOneDirection ==3}">
                           (朝西)
                        </c:if>
                        <c:if test="${details.gardenAreaOneDirection ==4}">
                           (朝北)
                        </c:if>
                        <c:if test="${details.gardenAreaOneDirection ==5}">
                           (朝东南)
                        </c:if>
                        <c:if test="${details.gardenAreaOneDirection ==6}">
                           (朝东北)
                        </c:if>
                        <c:if test="${details.gardenAreaOneDirection ==7}">
                           (朝西南)
                        </c:if>
                        <c:if test="${details.gardenAreaOneDirection ==8}">
                           (朝西北)
                        </c:if>
                        <c:if test="${details.gardenAreaOneDirection ==null}">
                           (未知)
                        </c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">花园2
				 	<span class="mui-pull-right">
						<c:if test="${details.gardenAreaTwo !=null}">
                           ${details.gardenAreaTwo}
                        </c:if>
                        <c:if test="${details.gardenAreaTwo ==null}">
                           未知
                        </c:if>
                       	<c:if test="${details.gardenAreaTwoDirection ==1}">
                           (朝东)
                        </c:if>
                        <c:if test="${details.gardenAreaTwoDirection ==2}">
                           (朝南)
                        </c:if>
                        <c:if test="${details.gardenAreaTwoDirection ==3}">
                           (朝西)
                        </c:if>
                        <c:if test="${details.gardenAreaTwoDirection ==4}">
                           (朝北)
                        </c:if>
                        <c:if test="${details.gardenAreaTwoDirection ==5}">
                           (朝东南)
                        </c:if>
                        <c:if test="${details.gardenAreaTwoDirection ==6}">
                           (朝东北)
                        </c:if>
                        <c:if test="${details.gardenAreaTwoDirection ==7}">
                           (朝西南)
                        </c:if>
                        <c:if test="${details.gardenAreaTwoDirection ==8}">
                           (朝西北)
                        </c:if>
                        <c:if test="${details.gardenAreaTwoDirection ==null}">
                           (未知)
                        </c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">半地下室
				 	<span class="mui-pull-right">
						<c:if test="${details.harfBasementArea !=null}">
                           ${details.harfBasementArea}
                        </c:if>
                        <c:if test="${details.harfBasementArea ==null}">
                           未知
                        </c:if>
                       	<c:if test="${details.harfBasementDirection ==1}">
                           (朝东)
                        </c:if>
                        <c:if test="${details.harfBasementDirection ==2}">
                           (朝南)
                        </c:if>
                        <c:if test="${details.harfBasementDirection ==3}">
                           (朝西)
                        </c:if>
                        <c:if test="${details.harfBasementDirection ==4}">
                           (朝北)
                        </c:if>
                        <c:if test="${details.harfBasementDirection ==5}">
                           (朝东南)
                        </c:if>
                        <c:if test="${details.harfBasementDirection ==6}">
                           (朝东北)
                        </c:if>
                        <c:if test="${details.harfBasementDirection ==7}">
                           (朝西南)
                        </c:if>
                        <c:if test="${details.harfBasementDirection ==8}">
                           (朝西北)
                        </c:if>
                        <c:if test="${details.harfBasementDirection ==null}">
                           (未知)
                        </c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">地下室1
				 	<span class="mui-pull-right">
						<c:if test="${details.basementAreaOne !=null}">
                           ${details.basementAreaOne}
                        </c:if>
                        <c:if test="${details.basementAreaOne ==null}">
                           未知
                        </c:if>
                       	<c:if test="${details.basementDirectionOne ==1}">
                           (朝东/
                        </c:if>
                        <c:if test="${details.basementDirectionOne ==2}">
                           (朝南/
                        </c:if>
                        <c:if test="${details.basementDirectionOne ==3}">
                           (朝西/
                        </c:if>
                        <c:if test="${details.basementDirectionOne ==4}">
                           (朝北/
                        </c:if>
                        <c:if test="${details.basementDirectionOne ==5}">
                           (朝东南/
                        </c:if>
                        <c:if test="${details.basementDirectionOne ==6}">
                           (朝东北/
                        </c:if>
                        <c:if test="${details.basementDirectionOne ==7}">
                           (朝西南/
                        </c:if>
                        <c:if test="${details.basementDirectionOne ==8}">
                           (朝西北/
                        </c:if>
                        <c:if test="${details.basementDirectionOne ==null}">
                           (未知/
                        </c:if>
                        <c:if test="${details.basementLightOne ==0}">
                           暗)
                        </c:if>
                        <c:if test="${details.basementLightOne ==1}">
                           明)
                        </c:if>
                        <c:if test="${details.basementLightOne ==null}">
                           未知)
                        </c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">地下室2
				 	<span class="mui-pull-right">
						<c:if test="${details.basementAreaTwo !=null}">
                           ${details.basementAreaTwo}
                        </c:if>
                        <c:if test="${details.basementAreaTwo ==null}">
                           未知
                        </c:if>
                       	<c:if test="${details.basementDirectionTwo ==1}">
                           (朝东/
                        </c:if>
                        <c:if test="${details.basementDirectionTwo ==2}">
                           (朝南/
                        </c:if>
                        <c:if test="${details.basementDirectionTwo ==3}">
                           (朝西/
                        </c:if>
                        <c:if test="${details.basementDirectionTwo ==4}">
                           (朝北/
                        </c:if>
                        <c:if test="${details.basementDirectionTwo ==5}">
                           (朝东南/
                        </c:if>
                        <c:if test="${details.basementDirectionTwo ==6}">
                           (朝东北/
                        </c:if>
                        <c:if test="${details.basementDirectionTwo ==7}">
                           (朝西南/
                        </c:if>
                        <c:if test="${details.basementDirectionTwo ==8}">
                           (朝西北/
                        </c:if>
                        <c:if test="${details.basementDirectionTwo ==null}">
                           (未知/
                        </c:if>
                        <c:if test="${details.basementLightTwo ==0}">
                           暗)
                        </c:if>
                        <c:if test="${details.basementLightTwo ==1}">
                           明)
                        </c:if>
                        <c:if test="${details.basementLightTwo ==null}">
                           未知)
                        </c:if>
					</span>
				 </li>
				 <li class="mui-table-view-cell">采光井/通风井
				 	<span class="mui-pull-right">
						<c:if test="${details.lightWellSize !=null}">
                           ${details.lightWellSize}
                        </c:if>
                        <c:if test="${details.lightWellSize ==null}">
                           未知
                        </c:if>              
					</span>
				 </li>
				 <li class="mui-table-view-cell">观景角度
				 	<span class="mui-pull-right">
						<c:if test="${details.viewingAngle !=null}">
                           ${details.viewingAngle}
                        </c:if>
                        <c:if test="${details.viewingAngle ==null}">
                           未知
                        </c:if>              
					</span>
				 </li>
			</ul>
</div>
 <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
</body>
</html>