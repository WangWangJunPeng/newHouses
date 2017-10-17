<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <!DOCTYPE html>
  <html lang="en">
<head>
    <meta charset="utf-8">
    <title>案场助理个人中心</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Loading Bootstrap -->
    <link href="static/css/bootstrap/css/bootstrap.css" rel="stylesheet">
   
    <link href="static/css/archon.css" rel="stylesheet">
    <link href="static/css/responsive.css" rel="stylesheet">
    <link href="static/css/timeline.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="static/css/singleHouseMessage.css" />
  <link rel="stylesheet" href="static/css/zoomify.min.css"/>

    <link rel="stylesheet" type="text/css" href="static/css/calendarM.css" />
    <!-- <link  rel="icon" href="http://root-12521005170.cossh.myqcloud.com/static/static/static/images/titleLogo.png"  /> -->
 
   <style type="text/css">
      
      p,span,tr,td,a,li,option,select,button{font-family:"微软雅黑" !important;}
    </style>
</head>
<body >
    <div class="frame" style="min-height:1000px;">
        <div class="sidebar" style="background:#616b88;">
            <div class="wrapper">           
               <div style="width:280px;;background:#565e78;height:80px;margin-left:-20px;">
                    <img src="static/images/logoer.png" alt=""  class="img-responsive"  style="padding-top:14px;margin-left:81px;"/>
                </div>
                <div style="height:40px;position:relative;margin-top:10px;margin-left:10px;">
                    <img src="static/images/peopleR.png" alt=""  style="position:relative;"/>
                    <span style="color:#eaeaea;font-size:18px;font-weight:bold;position:relative;top:3px;left:4px;">案场经理个人中心</span>
                </div>
                <ul class="nav  nav-list">
                   
  
                    <li><a href="to_director_page_index" class="noDrop active" ><span style="color:#46c1de;">今日案场</span></a>
                        <!-- <ul style="display:block;border:0;"> -->
                        <!-- </ul> -->
                    </li> 
                    <li><a href="to_goToManagerMap" class="noDrop"><span>门店地图</span></a></li>                    
                    <li><a href="to_data_analysis_page" class="noDrop"><span>数据分析</span></a></li>
                    <li><a href="to_data_statement_page" class="noDrop"><span>数据报表</span></a></li>
                    <li><a href="to_order_page" class="noDrop"><span>订单</span></a></li>
                    <li><a href="to_manager_team_page" class="noDrop"><span>团队</span></a></li> 
                    <li><a href="to_manager_team_page" class="noDrop"><span>客户管理</span></a></li> 
                    <li><a href="###" class="noDrop"><span>房源管理</span></a></li>        
                </ul>
               <div id="calendar" class="calendar" style="display:none;"></div>
               <!-- <div class="row jindu" style="width:240px;margin-left:2px;border-radius:4px;background:#47506c;margin-top:40px;">
                   <div class="col-md-12 "  >
                       <div class="row">
                           <div class="col-md-12" style="margin-top:20px;">
                               <p style="color:#d5d9e0;font-size:14px;">项目任务完成进度</p>
                           </div>
                       </div>
                        <div class="row">
                           <div class="col-md-12">
                               <p style="font-size:12px;color:#d5d9e0;">接访量完成<span id="visitRate">70%</span></p>
                           </div>
                       </div>
                         <div class="progress" style="height:10px;" >
                            <div class="progress-bar progress-bar-warning" id="jinOne" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:70%;">
                            </div>
                        </div>
                         <div class="row">
                           <div class="col-md-12">
                               <p style="font-size:12px;color:#d5d9e0;">认购量完成<span id="enterBuyRate">70%</span></p>
                           </div>
                       </div>
                         <div class="progress" style="height:10px;">
                            <div class="progress-bar progress-bar-info" id="jinTwo" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:48%;">
   
                            </div>
                        </div>
                         <div class="row">
                           <div class="col-md-12">
                               <p style="font-size:12px;color:#d5d9e0;">签约量完成<span id="signRate">70%</span></p>
                           </div>
                       </div>
                         <div class="progress" style="height:10px;">
                            <div class="progress-bar progress-bar-warning" id="jinThree" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 1.50%;">
   
                            </div>
                        </div>
                         <div class="row">
                           <div class="col-md-12">
                               <p style="font-size:12px;color:#d5d9e0;">售出房源货值完成<span>70%</span></p>
                           </div>
                       </div>
                         <div class="progress" style="height:10px;">
                            <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 1.50%;">
   
                            </div>
                        </div>
                         <div class="row">
                           <div class="col-md-12">
                               <p style="font-size:12px;color:#d5d9e0;">新增储蓄完成<span>70%</span></p>
                           </div>
                       </div>
                         <div class="progress" style="height:10px;">
                            <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 1.50%;">
   
                            </div>
                        </div> 

                   </div>
               </div> -->
            </div>
        </div>

        <div class="content" >
            <div class="navbar" style="background:#ffffff;border:0">
                <a href="#" onclick="return false;" class="btn pull-left toggle-sidebar "><i class="icon-list" ></i></a>
               
                <ul class="nav navbar-nav user-menu pull-right">
                    <!-- First nav user item -->
                   

                    <!-- Second nav user item -->
                    <li class="dropdown hidden-xs">
                        <a class="dropdown-toggle" data-toggle="dropdown"><i class="icon-bell" style="color:#333;"></i></a>
                       
                    </li>
           <li class="dropdown hidden-xs">
                        <a class="dropdown-toggle" data-toggle="dropdown"><i class="icon-envelope-alt" style="color:#333;"></i></a>
                       
                    <li class="dropdown user-name">
                        <a class="dropdown-toggle" data-toggle="dropdown" style="color:#333333;border:0;"><img src="static/images/sanchong.jpg" class="user-avatar" alt="" /><span style="font-size:16px;color:#616b88;">${sessionScope.userInfo.userCaption}</span></a>
                            <ul class="dropdown-menu right inbox user" >
                                <li class="user-avatar" >
                                    <img src="static/images/sanchong.jpg" class="user-avatar" alt="" />
                                    <span >${sessionScope.userInfo.userCaption}</span>
                                </li>
                            <li>

                                <i class="icon-user avatar"></i>
                                <div class="message">
                                   <a href="to_edit_passworld">修改密码</a>
                                </div>
                            </li>                          
                            <li><a href="web_logout">退出</a></li>
                        </ul>
                    </li>              
                </ul>
            </div>
            <div id="main-content" >
               <div class="row">
                  <div class="col-md-4">
                      <p><b style="color:#fe1c27;font-size:14px;">*</b><a href="###" style="color:#616b88;font-size:14px;margin-left:4px;">首页</a><a href="###" style="color:#616b88;font-size:14px;">&gt;</a><a href="###" style="color:#a6d675;font-size:14px;">房源管理</a></p>
                  </div>
                    
                </div>
                <div class="row">
                    <div class="col-md-12 listOne" style="width:98%;margin-left:1%;">
                        <div class="row">
                            <div class="col-md-6" style="margin-top:36px;width:60%;margin-left:2%;">
                                <span style="color:#616b88;font-size:22px;font-weight:bold;">${houseInfo.project.projectName  }${houseInfo.house.buildingId  }${houseInfo.house.unit  }${houseInfo.house.houseNo  }</span>
                            </div>
                            
                        </div>
                        <div class="row">
                            <div class="col-md-4 bigger" style="text-align:center;width:30%;">
                                <img src="${houseInfo.houseType.photoURL  }" alt="" style="width:260px;height:240px;margin-top:50px;"/>
                            </div>
                            <div class="col-md-8" style="width:70%;">
                                <table class="table table-bordered" style="width:100%;margin-top:50px;">
                                    
                                   
                                    <tr>
                                        <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;"><span style="display:inline-block;">房号：</span></td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.house.houseNo  }</td>
                                        <td style="background:#f6f9fd;">户型：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">
                                        	
                                        ${houseInfo.house.houseTypeName==null?"未知": houseInfo.house.houseTypeName }</td>
                                       
                                    </tr>
                                    <tr>
                                        <td style="background:#f6f9fd;">单价：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.priceMin==null?"未知":  houseInfo.priceMin }</td>
                                        <td style="background:#f6f9fd;">预售证号：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.house.presalePermissionInfo==null?"未知":  houseInfo.house.presalePermissionInfo}</td>
                                       
                                    </tr>
                                    <tr>
                                        <td style="background:#f6f9fd;">朝向：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;"><span style="display:inline-block;width:60px;height:30px;line-height:30px;border:1px solid #9ec77d;text-align:center;">${houseInfo.house.direct==null?"未知": houseInfo.house.direct}</span></td>
                                        <td style="background:#f6f9fd;">总价：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.house.listPrice==null?"未知":  houseInfo.house.listPrice  }</td>
                                       
                                    </tr>
                                    <tr>
                                        <td style="padding-top:30px;padding-bottom:30px;background:#f6f9fd;" >户型特点：</td>
                                        <td colspan="3" style="padding-top:30px;padding-bottom:30px;font-weight:600;">${houseInfo.house.description==null?"未知":  houseInfo.house.description  }</td>
                                       
                                       
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div class="row" style="margin-top:60px;">
                           
                            <div class="col-md-4" style="width:40%;position:relative;margin-left:1%;">
                                <img src="static/images/housemessage.png" alt="" />
                                <span style="color:#616b88;font-size:18px;font-weight:bold;display:inline-block;top:3px;position:relative;margin-left:3%;">户型信息</span>
                                <span style="display:inline-block;width:1px;height:14px;background:#dfe1e7;margin-left:2%;top:3px;position:relative;"></span>
                                <span style="color:#616b88;font-size:18px;font-weight:bold;display:inline-block;top:3px;position:relative;margin-left:2%;">${houseInfo.house.houseTypeName==null?"未知": houseInfo.house.houseTypeName }</span>
                            </div>
                           
                        </div>
                        <div class="row" style="margin-top:20px;">
                            <div class="col-md-12" style="width:100%;">
                                <table class="table table-bordered" style="width:100%;">
                                    
                                   
                                    <tr>
                                        <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">建筑面积：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">
                                        	<c:if test="${houseInfo.house.buildArea==null && houseInfo.house.preBuildArea==null}">
			                            	未知
			                            	</c:if>
                                        	<c:if test="${houseInfo.house.buildArea == null && houseInfo.house.preBuildArea != null} ">
			                            	${houseInfo.house.preBuildArea}(预测)
			                            	</c:if>
                                        	<c:if test="${houseInfo.house.buildArea != null && houseInfo.house.preBuildArea == null} ">
			                            	${houseInfo.house.buildArea}
			                            	</c:if>
			                            	<c:if test="${houseInfo.house.buildArea != null && houseInfo.house.preBuildArea != null} ">
			                            	${houseInfo.house.buildArea}
			                            	</c:if>
                                        </td>
                                        <td style="background:#f6f9fd;">楼栋朝向：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">
                                        <span style="display:inline-block;width:60px;height:30px;line-height:30px;border:1px solid #9ec77d;text-align:center;">
                                        	<c:if test="${houseInfo.projectBuilding.buildingDirection==1 }">
			                            	东
			                            	</c:if>
			                            	<c:if test="${houseInfo.projectBuilding.buildingDirection==2}">
			                            	南
			                            	</c:if>
			                           	    <c:if test="${houseInfo.projectBuilding.buildingDirection==3 }">
			                            	西
			                            	</c:if>
                                        	<c:if test="${houseInfo.projectBuilding.buildingDirection==4 }">
			                            	北
			                            	</c:if>
			                            	<c:if test="${houseInfo.projectBuilding.buildingDirection==5 }">
			                            	东南
			                            	</c:if>
			                            	<c:if test="${houseInfo.projectBuilding.buildingDirection==6 }">
			                            	东北
			                            	</c:if>
			                            	<c:if test="${houseInfo.projectBuilding.buildingDirection==7 }">
			                            	西南
			                            	</c:if>
			                            	<c:if test="${houseInfo.projectBuilding.buildingDirection==8 }">
			                            	西北
			                            	</c:if>
			                            	<c:if test="${houseInfo.projectBuilding.buildingDirection==null }">
			                            	未知
			                            	</c:if>
                                        </span></td>
                                        <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">套内面积：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.house.insideSpace==null?"未知": houseInfo.house.insideSpace }</td>
                                    </tr>
                                    <tr>
                                        <td style="background:#f6f9fd;" >户型位置：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;" colspan="5">${houseInfo.house.houseTypePosition==null?"未知": houseInfo.house.houseTypePosition }</td>
                                       
                                       
                                    </tr>
                                    
                                </table>
                            </div>
                        </div>
                        <div class="row" >
                           
                            <div class="col-md-4" style="width:40%;position:relative;margin-left:1%;">
                                <img src="static/images/pricemessage.png" alt="" />
                                <span style="color:#616b88;font-size:18px;font-weight:bold;display:inline-block;top:3px;position:relative;margin-left:3%;">价格信息</span>                               
                            </div>
                           
                        </div>
                        <div class="row" style="margin-top:20px;">
                            <div class="col-md-12" style="width:100%;">
                                <table class="table table-bordered" style="width:100%;">
                                    
                                   
                                    <tr>
                                        <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">单价：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">
                                           ${houseInfo.priceMin==null?"未知":  houseInfo.priceMin }
                                        </td>
                                        <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">物业费：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">
                                           ${houseInfo.project.propertyCost==null?"未知":  houseInfo.project.propertyCost}
                                        </td>
                                        <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">总价：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">
                                           ${houseInfo.house.listPrice==null?"未知":  houseInfo.house.listPrice  }
                                        </td>
                                    </tr>
                                    
                                    
                                </table>
                            </div>
                        </div>
                        <div class="row" >
                           
                            <div class="col-md-4" style="width:40%;position:relative;margin-left:1%;">
                                <img src="static/images/discountmessage.png" alt="" />
                                <span style="color:#616b88;font-size:18px;font-weight:bold;display:inline-block;top:3px;position:relative;margin-left:3%;">优惠信息</span>                               
                            </div>
                           
                        </div>
                        <div class="row" style="margin-top:20px;">
                            <div class="col-md-12" style="width:100%;">
                                <table class="table table-bordered" style="width:100%;">
                                    
                                   
                                    <tr>
                                        <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">优惠信息：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">
                                        <c:forEach items="${houseInfo.projectBenefits }" var="benefits" varStatus="status">
                                        	<c:if test="${status.index==0}">
			                            	    <span title="${benefits.caption }"  style="cursor:pointer;display:inline-block;height:28px;line-height:28px;text-align:center;background:#ffece8;color:#ff8062;padding-left:4px;padding-right:4px;">${benefits.benefitsName }</span>

			                            	</c:if>
                                            <c:if test="${status.index==1}">
			                            	    <span title="${benefits.caption }"  style="cursor:pointer;display:inline-block;height:28px;line-height:28px;text-align:center;background:#f4f7f9;color:#849aae;padding-left:4px;padding-right:4px;">${benefits.benefitsName }</span>

			                            	</c:if>
			                            	<c:if test="${status.index==2}">
			                            	    <span title="${benefits.caption }"  style="cursor:pointer;display:inline-block;height:28px;line-height:28px;text-align:center;background:#ffe8f8;color:#e765a9;padding-left:4px;padding-right:4px;">${benefits.benefitsName }</span>

			                            	</c:if>
			                            	<c:if test="${status.index==3}">
			                            	    <span title="${benefits.caption }"  style="cursor:pointer;display:inline-block;height:28px;line-height:28px;text-align:center;background:#fffbe8;color:#e7c112;padding-left:4px;padding-right:4px;">${benefits.benefitsName }</span>

			                            	</c:if>
			                            	<c:if test="${status.index==4}">
			                            	    <span title="${benefits.caption }"  style="cursor:pointer;display:inline-block;height:28px;line-height:28px;text-align:center;background:#e8f8ff;color:#66c4f7;padding-left:4px;padding-right:4px;">${benefits.benefitsName }</span>

			                            	</c:if>
			                            	<c:if test="${status.index==5}">
			                            	    <span title="${benefits.caption }"  style="cursor:pointer;display:inline-block;height:28px;line-height:28px;text-align:center;background:#f1ffe8;color:#94c69d;padding-left:4px;padding-right:4px;">${benefits.benefitsName }</span>

			                            	</c:if>
			                            	<c:if test="${status.index==6}">
			                            	    <span title="${benefits.caption }"  style="cursor:pointer;display:inline-block;height:28px;line-height:28px;text-align:center;background:#efe8ff;color:#af99d2;padding-left:4px;padding-right:4px;">${benefits.benefitsName }</span>

			                            	</c:if>
                                        </c:forEach>
                                        </td>
                                       
                                    </tr>
                                    
                                    
                                </table>
                            </div>
                        </div>
                        <div class="row" >
                           
                            <div class="col-md-4" style="width:40%;position:relative;margin-left:1%;">
                                <img src="static/images/importantmessage.png" alt="" />
                                <span style="color:#616b88;font-size:18px;font-weight:bold;display:inline-block;top:3px;position:relative;margin-left:3%;">重要信息</span>                               
                            </div>
                           
                        </div>
                        <div class="row" style="margin-top:20px;">
                            <div class="col-md-12" style="width:100%;">
                                <table class="table table-bordered" style="width:100%;">
                                    
                                   
                                    <tr>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">重要提示：</td>
                                       
                                       <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.project.importanceMsg==null?"未知":  houseInfo.project.importanceMsg}</td>
                                    </tr>
                                    
                                    
                                </table>
                            </div>
                        </div>
                        <div class="row" >
                           
                            <div class="col-md-4" style="width:40%;position:relative;margin-left:1%;">
                                <img src="static/images/unitmessage.png" alt="" />
                                <span style="color:#616b88;font-size:18px;font-weight:bold;display:inline-block;top:3px;position:relative;margin-left:3%;">单元信息</span>                               
                            </div>
                           
                        </div>
                        <div class="row" style="margin-top:20px;">
                            <div class="col-md-12" style="width:100%;">
                                <table class="table table-bordered" style="width:100%;">
                                    
                                   
                                    <tr>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">单元总公摊：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">暂无</td>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">得房率：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.projectBuildingUnit.unitEfficiencyRate==null?"未知":  houseInfo.projectBuildingUnit.unitEfficiencyRate}</td>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">单层总户数：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.unitTotal==null?"未知":  houseInfo.unitTotal}</td>
                                    </tr>
                                    <tr>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">电梯数：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.projectBuildingUnit.liftNum==null?"未知":  houseInfo.projectBuildingUnit.liftNum}</td>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">单元号：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.projectBuildingUnit.unitName==null?"未知":  houseInfo.projectBuildingUnit.unitName}</td>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">单元层数：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.projectBuildingUnit.floorNum==null?"未知":  houseInfo.projectBuildingUnit.floorNum}</td>
                                    </tr>
                                    
                                </table>
                            </div>
                        </div>
                        <div class="row" >
                           
                            <div class="col-md-4" style="width:40%;position:relative;margin-left:1%;">
                                <img src="static/images/floormessage.png" alt="" />
                                <span style="color:#616b88;font-size:18px;font-weight:bold;display:inline-block;top:3px;position:relative;margin-left:3%;">楼栋信息</span>                               
                            </div>
                           
                        </div>
                        <div class="row" style="margin-top:20px;">
                            <div class="col-md-12" style="width:100%;">
                                <table class="table table-bordered" style="width:100%;">
                                    
                                   
                                    <tr>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">楼栋位置：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;color:#ff9666;">${houseInfo.projectBuilding.buildingPosition==null?"未知":  houseInfo.projectBuilding.buildingPosition}</td>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">最大楼层：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.projectBuildingUnit.floorNum==null?"未知":  houseInfo.projectBuildingUnit.floorNum}</td>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">架空层：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">
                                      		<c:if test="${houseInfo.projectBuilding.emptySpace==0 || houseInfo.projectBuilding.emptySpace==null}">
			                            	无
			                            	</c:if>
			                            	<c:if test="${houseInfo.projectBuilding.emptySpace != 0 && houseInfo.projectBuilding.emptySpace != null}">
			                            	${houseInfo.projectBuilding.emptySpace }
			                            	</c:if>
                                     
                                      
                                      </td>
                                    </tr>
                                    <tr>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">单元数：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.unitNum==null?"未知":  houseInfo.unitNum}</td>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">直连地库：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;color:#ff9666;">
                                      	<c:if test="${houseInfo.projectBuilding.isToBasement==0}">
			                            	暂无
			                            	</c:if>
			                            	<c:if test="${houseInfo.projectBuilding.isToBasement==1}">
			                            	是
			                            	</c:if>
			                            	<c:if test="${houseInfo.projectBuilding.isToBasement==null}">
			                            	暂无
			                            </c:if>
                                      </td>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">非机动车库：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;color:#ff9666;">
                                      	<c:if test="${houseInfo.projectBuilding.havaNonMotorGarage==0}">
			                            	暂无
			                            	</c:if>
			                            	<c:if test="${houseInfo.projectBuilding.havaNonMotorGarage==1}">
			                            	有
			                            	</c:if>
			                            	<c:if test="${houseInfo.projectBuilding.havaNonMotorGarage==null}">
			                            	暂无
			                            </c:if>
                                      
                                      
                                      </td>
                                    </tr>
                                    <tr>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">楼梯号：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.house.buildingNo==null?"未知":  houseInfo.house.buildingNo}</td>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">结构形式：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;color:#ff9666;">${houseInfo.projectBuilding.constitute==null?"未知":  houseInfo.projectBuilding.constitute}</td>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">裙楼：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;color:#ff9666;">
                                      	<c:if test="${houseInfo.projectBuilding.havaAnnex==0}">
			                            	暂无
			                            	</c:if>
			                            	<c:if test="${houseInfo.projectBuilding.havaAnnex==1}">
			                            	有
			                            	</c:if>
			                            	<c:if test="${houseInfo.projectBuilding.havaAnnex==null}">
			                            	暂无
			                            </c:if>
                                      
                                      </td>
                                    </tr>
                                    <tr>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">楼栋总户数：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;" colspan="5">${houseInfo.buildingTotal==null?"未知":  houseInfo.buildingTotal}</td>
                                     </tr>
                                </table>
                            </div>
                        </div>
                        <div class="row" >
                           
                            <div class="col-md-4" style="width:40%;position:relative;margin-left:1%;">
                                <img src="static/images/paymessage.png" alt="" />
                                <span style="color:#616b88;font-size:18px;font-weight:bold;display:inline-block;top:3px;position:relative;margin-left:3%;">交付信息</span>                               
                            </div>
                           
                        </div>
                        <div class="row" style="margin-top:20px;">
                            <div class="col-md-12" style="width:100%;">
                                <table class="table table-bordered" style="width:100%;">
                                    
                                   
                                    <tr>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">交付时间：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;color:#ff9666;">
                                            <span style="color:#616b88;font-size:16px;font-weight:bold;display:inline-block;height:28px;line-height:28px;border:1px solid #2973b3">${houseInfo.project.deliverTime==null?"未知":  houseInfo.project.deliverTime}</span> 
                                      </td>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">精装交付：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">暂无</td>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">毛坯交付：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">暂无</td>
                                    </tr>
                                    
                                </table>
                            </div>
                        </div>
                        <div class="row" >
                           
                            <div class="col-md-4" style="width:40%;position:relative;margin-left:1%;">
                                <img src="static/images/projectmessage.png" alt="" />
                                <span style="color:#616b88;font-size:16px;font-weight:bold;display:inline-block;top:3px;position:relative;margin-left:3%;">项目信息</span>                               
                            </div>
                           
                        </div>
                        <div class="row" style="margin-top:20px;">
                            <div class="col-md-12" style="width:100%;">
                                <table class="table table-bordered" style="width:100%;">
                                    
                                   
                                    <tr>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">开盘时间：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">
                                            <span style="color:#616b88;font-size:16px;font-weight:bold;display:inline-block;height:28px;line-height:28px;border:1px solid #2973b3">${houseInfo.project.openingTime==null?"未知":  houseInfo.project.openingTime}</span> 
                                      </td>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">容积率：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.project.volumeRatio==null?"未知":  houseInfo.project.volumeRatio}</td>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">用地面积：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.project.landArea==null?"未知":  houseInfo.project.landArea}</td>
                                    </tr>
                                    <tr>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">总户数：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">
                                           ${houseInfo.project.unitCount==null?"未知":  houseInfo.project.unitCount}
                                      </td>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">车位比：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">1：${houseInfo.parkingRate==null?"未知":  houseInfo.parkingRate}</td>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">总建筑面积：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.project.buildArea==null?"未知":  houseInfo.project.buildArea}</td>
                                    </tr>
                                    <tr>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">车位数：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">
                                           ${houseInfo.project.parking==null?"未知":  houseInfo.project.parking}
                                      </td>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">所属区位：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.house.district==null?"未知":  houseInfo.house.district }</td>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">地下建筑面积：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.project.underGroundArea==null?"未知":  houseInfo.project.underGroundArea }</td>
                                    </tr>
                                    <tr>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">建筑密度：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">
                                           ${houseInfo.project.density==null?"未知":  houseInfo.project.density }
                                      </td>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">物业类型：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;color:#ff9666;" colspan="3">${houseInfo.project.propertyType==null?"未知":  houseInfo.project.propertyType }</td>
                                     
                                    </tr>
                                    <tr>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">项目地址：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;" colspan="5">
                                           ${houseInfo.project.propertyAddress==null?"未知":  houseInfo.project.propertyAddress }
                                      </td>
                                     
                                     
                                    </tr>
                                    <tr>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">售楼部位置：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;" colspan="5">
                                           ${houseInfo.project.saleAddress==null?"未知":  houseInfo.project.saleAddress }
                                      </td>
                                     
                                     
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div class="row" >
                           
                            <div class="col-md-4" style="width:100%;position:relative;margin-left:1%;">
                                <img src="static/images/peitaomessage.png" alt="" />
                                <span style="color:#616b88;font-size:18px;font-weight:bold;display:inline-block;top:3px;position:relative;margin-left:1%;">配套信息</span>
                                <span style="display:inline-block;width:1px;height:14px;background:#dfe1e7;margin-left:1%;top:3px;position:relative;"></span>
                                <span style="color:#616b88;font-size:18px;font-weight:bold;display:inline-block;top:3px;position:relative;margin-left:1%;">自建配套</span>
                                <span style="color:#616b88;font-size:18px;font-weight:bold;display:inline-block;top:3px;position:relative;margin-left:50%;">周边配套</span>
                            </div>
                           
                        </div>
                        <div class="row" style="margin-top:20px;">
                            <div class="col-md-12" style="width:100%;">
                                <table class="table table-bordered" style="width:100%;">                                   
                                    <tr>
                                        <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">小区会所：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.project.ownerClubArea==null?"未知":  houseInfo.project.ownerClubArea }</td>
                                        <td style="background:#f6f9fd;" rowspan="2">商业：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;" colspan="2" rowspan="2">${houseInfo.project.business==null?"未知":  houseInfo.project.business }</td>
                                        
                                    </tr>
                                    <tr>
                                        <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">健身房：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.project.ownerGym==null?"未知":  houseInfo.project.ownerGym }</td>
                                      
                                        
                                    </tr>
                                    <tr>
                                        <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">社区中心：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.project.ownerCommunityCenter==null?"未知":  houseInfo.project.ownerCommunityCenter }</td>
                                        <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">教育：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.project.education==null?"未知":  houseInfo.project.education }</td>
                                        
                                    </tr>
                                    <tr>
                                        <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">游泳池：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.project.ownerSwimmingPool==null?"未知":  houseInfo.project.ownerSwimmingPool }</td>
                                        <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">金融：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.project.financial==null?"未知":  houseInfo.project.financial }</td>
                                        
                                    </tr>
                                    <tr>
                                        <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">学校：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.project.ownerSchool==null?"未知":  houseInfo.project.ownerSchool }</td>
                                       <td style="background:#f6f9fd;" rowspan="2">交通：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;" colspan="2" rowspan="2">${houseInfo.project.transportation==null?"未知":  houseInfo.project.transportation }</td>
                                        
                                    </tr>
                                     <tr>
                                        <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">幼儿园：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.project.ownerKindergarten==null?"未知":  houseInfo.project.ownerKindergarten }</td>
                                      
                                        
                                    </tr>
                                     <tr>
                                        <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">商业：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.project.ownerBusiness==null?"未知":  houseInfo.project.ownerBusiness }</td>
                                        <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">医疗：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">${houseInfo.project.medical==null?"未知":  houseInfo.project.medical }</td>
                                        
                                    </tr>
                                    <tr>
                                        <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;"></td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;"></td>
                                       <td style="background:#f6f9fd;" rowspan="2">其他：</td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;" colspan="2" rowspan="2">${houseInfo.project.other==null?"未知":  houseInfo.project.other }</td>
                                        
                                    </tr>
                                     <tr>
                                        <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;"></td>
                                        <td style="padding-top:16px;padding-bottom:16px;font-weight:600;"></td>
                                      
                                        
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div class="row" >
                           
                            <div class="col-md-4" style="width:40%;position:relative;margin-left:1%;">
                                <img src="static/images/buildmessage.png" alt="" />
                                <span style="color:#616b88;font-size:18px;font-weight:bold;display:inline-block;top:3px;position:relative;margin-left:3%;">开发商信息</span>                               
                            </div>
                           
                        </div>
                        <div class="row" style="margin-top:20px;">
                            <div class="col-md-12" style="width:100%;">
                                <table class="table table-bordered" style="width:100%;">
                                    
                                   
                                    <tr>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">设计单位：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">
                                           	${houseInfo.project.designCompany==null?"未知":  houseInfo.project.designCompany }	
                                      </td>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">开发商：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;color:#ff9666;">${houseInfo.project.developer==null?"未知":  houseInfo.project.developer }	</td>
                                      
                                    </tr>
                                    <tr>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">开工时间：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">
                                            <span style="color:#616b88;font-size:16px;font-weight:bold;display:inline-block;height:28px;line-height:28px;border:1px solid #2973b3">${houseInfo.project.startTime==null?"未知":  houseInfo.project.startTime }</span>
                                      </td>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">投资商：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;color:#ff9666;">${houseInfo.project.investor==null?"未知":  houseInfo.project.investor }	</td>
                                      
                                    </tr>
                                    <tr>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">企业性质：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">
                                          ${houseInfo.project.typeid==null?"未知":  houseInfo.project.typeid }
                                      </td>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">施工单位：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;color:#ff9666;">${houseInfo.project.constructionCompany==null?"未知":  houseInfo.project.constructionCompany }</td>
                                      
                                    </tr>
                                    <tr>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">开发资质：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;">
                                           ${houseInfo.project.developmenStatus==null?"未知":  houseInfo.project.developmenStatus }
                                      </td>
                                      <td style="padding-top:16px;padding-bottom:16px;background:#f6f9fd;">物业管理公司：</td>
                                       
                                      <td style="padding-top:16px;padding-bottom:16px;font-weight:600;color:#ff9666;">
                                      ${houseInfo.project.manager==null?"未知":  houseInfo.project.manager }
                                      </td>
                                      
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>      
             
            </div>
          
        
      </div> 
    </div>
    
   <script src="static/js/jquery-3.1.1.min.js"></script>
    
    <script src="static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="static/js/calendarM.js"></script> 
    <script src="static/js/archon.js"></script>
   <script src="static/js/zoomify.min.js"></script>
    <script type="text/javascript">
			$(".bigger img").zoomify();
	</script>
   

</body>
</html>
    