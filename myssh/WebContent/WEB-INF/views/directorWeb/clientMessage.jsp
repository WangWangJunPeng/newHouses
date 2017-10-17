<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>案场经理个人中心</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Loading Bootstrap -->
    <link href="static/css/bootstrap/css/bootstrap.css" rel="stylesheet">
   
    <link href="static/css/archon.css" rel="stylesheet">
    <link href="static/css/responsive.css" rel="stylesheet">
    <link href="static/css/timeline.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="static/css/clientMessage.css" />
  

    <link rel="stylesheet" type="text/css" href="static/css/calendarM.css" />
    <!-- <link  rel="icon" href="http://root-12521005170.cossh.myqcloud.com/static/static/static/images/titleLogo.png"  /> -->
 
   <style type="text/css">
      
      p,span,tr,td,a,li,option,select,button{font-family:"微软雅黑" !important;}
    </style>
</head>
<body >
    <div class="frame" >
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
                   
  
                    <li><a href="to_director_page_index" class="noDrop active" ><span >今日案场</span></a>
                        <!-- <ul style="display:block;border:0;"> -->
                        <!-- </ul> -->
                    </li> 
                    <li><a href="to_goToManagerMap" class="noDrop"><span>门店地图</span></a></li>                    
                    <li><a href="to_data_analysis_page" class="noDrop"><span>数据分析</span></a></li>
                    <li><a href="to_data_statement_page" class="noDrop"><span>数据报表</span></a></li>
                    <li><a href="to_order_page" class="noDrop"><span>订单</span></a></li>
                    <li><a href="to_manager_team_page" class="noDrop"><span>团队</span></a></li> 
                   <li><a href="to_pCustomerManager" class="noDrop"><span style="color:#46c1de;">客户管理</span></a>
                    	<ul style="display:block;border:0;">
                            <li><a href="customerAnalyze" style="padding-left:5px;color:#ffffff;font-size: 14px;"><span>客户分析</span></a></li>
                        </ul>
                    </li>                         
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
                        <!-- <ul class="dropdown-menu right notifications">
                            <li class="dropdown-menu-title">
                               提醒
                            </li>
                            <li>
                                <i class="icon-cog avatar text-success"></i>
                                <div class="message">
                                    <span class="username text-success">New settings activated</span> 
                                    <span class="time pull-right"> 06:58 PM</span>
                                </div>
                            </li>
                            <li>
                                <i class="icon-shopping-cart avatar text-danger"></i>
                                <div class="message">
                                    <span class="username text-danger">You have 2 returns</span> 
                                    <span class="time pull-right"> 04:29 PM</span>
                                </div>
                            </li>
                            <li>
                                <i class="icon-user avatar text-success"></i>
                                <div class="message">
                                    <span class="username text-success">New User registered</span> 
                                    <span class="time pull-right"> Yesterday</span>
                                </div>
                            </li>
                            <li>
                                <i class="icon-comment avatar text-info"></i>
                                <div class="message">
                                    <span class="username text-info">New Comment received</span> 
                                    <span class="time pull-right"> Yesterday</span>
                                </div>
                            </li>
                            <li>
                                <i class="icon-cog avatar text-warning"></i>
                                <div class="message">
                                    <span class="username text-warning">User deleted</span> 
                                    <span class="time pull-right"> 2 days ago</span>
                                </div>
                            </li>
                            <li>
                                <i class="icon-dollar avatar"></i>
                                <div class="message">
                                    <span class="username">Earned 200 points</span> 
                                    <span class="time pull-right">3 days ago</span>
                                </div>
                            </li>
                            <li>
                                <i class="icon-hdd avatar text-danger"></i>
                                <div class="message">
                                    <span class="username text-danger">Memory size exceeded </span> 
                                    <span class="time pull-right"> 1 week ago</span>
                                </div>
                            </li>

                            <li class="dropdown-menu-footer">
                                <a href="#">View All Notifications</a>
                            </li>
                        </ul> -->
                    </li>
           <li class="dropdown hidden-xs">
                        <a class="dropdown-toggle" data-toggle="dropdown"><i class="icon-envelope-alt" style="color:#333;"></i></a>
                        <!-- <ul class="dropdown-menu right inbox">
                            <li class="dropdown-menu-title">
                                信息 <span>25</span>
                            </li>
                            <li>
                                <img src="static/images/theme/avatarTwo.png" alt="" class="avatar">
                                <div class="message">
                                    <span class="username">汪俊鹏</span> 
                                    <span class="mini-details">(6) <i class="icon-paper-clip"></i></span>
                                    <span class="time pull-right"> 06:58 PM</span>
                                    <p>汪汪汪</p>
                                </div>
                            </li>
                            <li>
                                <img src="static/images/theme/avatarFive.png" alt="" class="avatar">
                                <div class="message">
                                    <span class="username">陈诗雨</span> 
                                    <span class="mini-details">(2) <i class="icon-paper-clip"></i></span>
                                    <span class="time pull-right"> 09:58 AM</span>
                                    <p>鱼鱼鱼</p>
                                </div>
                            </li>
                            <li>
                                <img src="static/images/theme/avatarSix.png" alt="" class="avatar">
                                <div class="message">
                                    <span class="username">郭志成</span> 
                                    <span class="mini-details">(6) <i class="icon-paper-clip"></i></span>
                                    <span class="time pull-right">Yesterday</span>
                                    <p>贱贱贱</p>
                                </div>
                            </li>
                           
                            <li class="dropdown-menu-footer">
                                <a href="#">更多消息</a>
                            </li>
                        </ul>
                    </li><!/dropdown -->
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
                    <div class="col-md-8" style="width:68%;">
                        <div class="row">
                            <div class="col-md-3 listOne" style="width:30%;margin-left:4%;padding-bottom:20px;">
                                <div class="row">
                                    <div class="col-md-6" style="width:60%;margin-left:20%;text-align:center;margin-top:20px;">
                                        <img src="static/images/client.png" alt="" class="img-circle" style="width:80px;height:80px;">
                                        <p style="font-size: 14px;color:#616b88;padding:0;margin:0;">${customerInfo.projectCustomerName }</p>
                                    </div>
                                </div>
                                <div class="row" style="margin-top:40px;">
                                    <div class="col-md-4" style="width:82%;margin-left:15%;"> 
                                        <span style="font-size: 14px;color:#616b88;">来访次数：</span>
                                        <span style="font-size: 14px;color:#616b88;">${customerInfo.arriveTimes }次</span>
                                    </div>
                                </div>
                                <div class="row" style="margin-top:15px;">
                                    <div class="col-md-4" style="width:82%;margin-left:15%;"> 
                                        <span style="font-size: 14px;color:#616b88;">联系方式：</span>
                                        <span style="font-size: 14px;color:#616b88;">${customerInfo.projectCustomerPhone }</span>
                                    </div>
                                </div>
                                <div class="row" style="margin-top:15px;">
                                    <div class="col-md-4" style="width:82%;margin-left:15%;"> 
                                        <span style="font-size: 14px;color:#616b88;">归属顾问：</span>
                                        <span style="font-size: 14px;color:#616b88;">${customerInfo.ownerUserName }</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 listOne" style="width:30%;margin-left:2%;padding-bottom:48px;">
                                <div class="row" style="height:42px;line-height:42px;">
                                    <div class="col-md-2" style="width:18%;text-align:center;padding:0;height:42px;background:#f1f3fa;">
                                        <img src="static/images/shengfen.png" alt="" style="margin-top:10px;"/>
                                    </div>
                                    <div class="col-md-6" style="width:60%;">
                                        <span style="font-size: 16px;color:#616b88;font-weight:bold;">身份信息</span>
                                            
                                    </div>
                                </div>
                                <div class="row" style="margin-top:40px;">
                                    <div class="col-md-4" style="width:50%;margin-left:1%;"> 
                                        <span style="font-size: 14px;color:#616b88;">性别：</span>
                                        <c:if test="${customerInfo.sex==0 }">
	                                        <span style="font-size: 14px;color:#616b88;">未知</span>
                                        </c:if>
                                        <c:if test="${customerInfo.sex==1 }">
	                                        <span style="font-size: 14px;color:#616b88;">男</span>
                                        </c:if>
                                        <c:if test="${customerInfo.sex==2 }">
	                                        <span style="font-size: 14px;color:#616b88;">女</span>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="row" style="margin-top:20px;">
                                    <div class="col-md-4" style="width:50%;margin-left:1%;"> 
                                        <span style="font-size: 14px;color:#616b88;">年龄：</span>
                                        <span style="font-size: 14px;color:#616b88;">${customerInfo.age }</span>
                                    </div>
                                </div>
                                <div class="row" style="margin-top:20px;">
                                    <div class="col-md-4" style="width:100%;margin-left:1%;"> 
                                        <span style="font-size: 14px;color:#616b88;">身份证号：</span>
                                        <span style="font-size: 14px;color:#616b88;">${customerInfo.idCard }</span>
                                    </div>
                                </div>
                                <div class="row" style="margin-top:20px;">
                                    <div class="col-md-4" style="width:50%;margin-left:1%;"> 
                                        <span style="font-size: 14px;color:#616b88;">职业：</span>
                                        <span style="font-size: 14px;color:#616b88;">${customerInfo.job }</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 listOne" style="width:30%;margin-left:2%;">
                                <div class="row" style="height:42px;line-height:42px;">
                                    <div class="col-md-2" style="width:18%;text-align:center;padding:0;height:42px;background:#f1f3fa;">
                                        <img src="static/images/kehuxiang.png" alt="" style="margin-top:10px;"/>
                                    </div>
                                    <div class="col-md-6" style="width:60%;">
                                        <span style="font-size: 16px;color:#616b88;font-weight:bold;">客户意向</span>
                                            
                                    </div>
                                </div>
                                <div class="row" style="margin-top:30px;">
                                    <div class="col-md-4" style="width:50%;margin-left:1%;"> 
                                        <span style="font-size: 14px;color:#ffffff;display:inline-block;width:80px;height:26px;line-height:26px;background:#ff5c5b;border-radius:4px;text-align:center;">${customerInfo.intention }</span>
                                       
                                    </div>
                                </div>
                                <div class="row" style="margin-top:30px;">
                                    <div class="col-md-4" style="width:50%;margin-left:1%;"> 
                                        <span style="font-size: 14px;color:#56678a;">经理点评</span>
                                    </div>
                                </div>
                                <div class="row" style="margin-top:20px;">
                                    <div class="col-md-4" style="width:98%;margin-left:1%;height:102px;"> 
                                        <span style="font-size: 14px;color:#616b88;">${customerInfo.managerEvaluate }</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-top:20px;">
                            <div class="col-md-5 listOne" style="width:46%;margin-left:4%;">
                                <div class="row" style="height:42px;line-height:42px;">
                                    <div class="col-md-2" style="width:18%;text-align:center;padding:0;height:42px;background:#f1f3fa;">
                                        <img src="static/images/kehugui.png" alt="" style="margin-top:10px;"/>
                                    </div>
                                    <div class="col-md-6" style="width:60%;">
                                        <span style="font-size: 16px;color:#616b88;font-weight:bold;">客户轨迹</span>
                                            
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12 oppo" style="width:100%;">
                                        <section id="cd-timeline" class="cd-container">
                                        <c:forEach items="${customerInfo.tracks }" var="tr">
                                            <div class="cd-timeline-block">
                                                <div class="cd-timeline-img cd-picture">
                                                   
                                                </div>

                                                <div class="cd-timeline-content" style="margin:0;padding:0;text-align:center;background:#f0f4fb;">
                                                    <p style="margin:0;padding:6px 6px 0 0;">${tr.time },${tr.status }</p>
                                                </div>
                                            </div>
                                        </c:forEach>
                                            <!-- <div class="cd-timeline-block">
                                                <div class="cd-timeline-img cd-picture">
                                                   
                                                </div>

                                                <div class="cd-timeline-content" style="padding:0;text-align:center;background:#f0f4fb;">
                                                   <p style="margin:0;padding:6px 6px 0 0;">待审核</p>
                                                   
                                                </div>
                                            </div> -->
                                            <!-- <div class="cd-timeline-block">
                                                <div class="cd-timeline-img cd-picture">
                                                    
                                                </div>

                                                <div class="cd-timeline-content" style="margin:0;padding:0;text-align:center;background:#f0f4fb;">
                                                    <p style="margin:0;padding:6px 6px 0 0;">审核通过</p>
                                                  
                                                </div>
                                            </div> -->
                                           <!--  <div class="cd-timeline-block">
                                                <div class="cd-timeline-img cd-picture">
                                                    
                                                </div>

                                                <div class="cd-timeline-content" style="margin:0;padding:0;text-align:center;background:#f0f4fb;">
                                                    <p style="margin:0;padding:6px 6px 0 0;">审核通过</p>
                                                  
                                                </div>
                                            </div> -->
                                           <!--  <div class="cd-timeline-block">
                                                <div class="cd-timeline-img cd-picture">
                                                   
                                                </div>

                                                <div class="cd-timeline-content" style="margin:0;padding:0;text-align:center;background:#f0f4fb;">
                                                   
                                                   <p style="margin:0;padding:6px 6px 0 0;">超时提醒</p>
                                                  
                                                </div>
                                            </div> -->
                                            <!-- <div class="cd-timeline-block">
                                                <div class="cd-timeline-img cd-movie">
                                                   
                                                </div>

                                                <div class="cd-timeline-content" style="margin:0;padding:0;text-align:center;background:#f0f4fb;">
                                                   
                                                   <p style="margin:0;padding:6px 6px 0 0;">审核拒绝</p>
                                                  
                                                </div>
                                            </div> -->

                                        </section>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 listOne" style="width:46%;margin-left:2%;">
                                <div class="row" style="height:42px;line-height:42px;">
                                    <div class="col-md-2" style="width:18%;text-align:center;padding:0;height:42px;background:#f1f3fa;">
                                        <img src="static/images/chengjiaoyu.png" alt="" style="margin-top:10px;"/>
                                    </div>
                                    <div class="col-md-6" style="width:60%;">
                                        <span style="font-size: 16px;color:#616b88;font-weight:bold;">成交预测</span>
                                            
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12" style="width:100%;padding:0;height:450px;text-align:center;">
                                        <!-- <div id="pathPic" style="height:450px;width:100%;"></div> -->
                                       	<p style="padding-top:180px;font-size:36px;color:#616b88;">暂未开放此功能</p>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 listOne" style="width:28%;height:783px;">
                        <div class="row" style="height:42px;line-height:42px;margin-bottom:30px;">
                            <div class="col-md-2" style="width:18%;text-align:center;padding:0;height:42px;background:#f1f3fa;">
                                <img src="static/images/kehubiao.png" alt="" style="margin-top:10px;"/>
                            </div>
                            <div class="col-md-6" style="width:60%;">
                                <span style="font-size: 16px;color:#616b88;font-weight:bold;">客户标签</span>
                                    
                            </div>
                        </div>
                        <div style="height:712px;overflow-y:auto;overflow-x:hidden">
                        <c:forEach items="${customerInfo.ownerTags }" var="entry">
	                        <div class="row" style="margin-top:10px;">
	                            <div class="col-md-4" style="width:90%;margin-left:2%;"> 
	                                <div class="row">
	                                    <div class="col-md-2" style="width:32%;margin-top:15px;text-align:right;padding:0;">
	                                        <span style="font-size: 14px;color:#56678a;font-weight:bold;">${entry.key }<b>：</b></span>
	                                    </div>
	                                    <div class="col-md-8" style="width:66%;padding:0;margin-top:10px;">
	                                    <c:if test="${entry.value.size()>0}">
		                                    <c:forEach items="${entry.value}" var="tag">
		                                        <span style="font-size: 14px;color:#616b88;display:inline-block;width:80px;height:30px;line-height:30px;border:1px solid #d6dfe6;border-radius:4px;text-align:center;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;">${tag.tagName }</span>

		                                    </c:forEach>
	                                    </c:if>
	                                    <c:if test="${entry.value.size()==0}">
               			                        <span style="font-size: 14px;color:#616b88;display:inline-block;width:80px;height:30px;line-height:30px;border:1px solid #d6dfe6;border-radius:4px;text-align:center;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;">暂无</span>
	                                    </c:if>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
                        
                        </c:forEach>
                        </div>
                        <!-- <div class="row" style="margin-top:10px;">
                            <div class="col-md-4" style="width:90%;margin-left:2%;"> 
                                <div class="row">
                                    <div class="col-md-2" style="width:32%;margin-top:15px;text-align:right;padding:0;">
                                        <span style="font-size: 14px;color:#56678a;font-weight:bold;">户型<b>：</b></span>
                                    </div>
                                    <div class="col-md-8" style="width:66%;padding:0;margin-top:10px;">
                                        <span style="font-size: 14px;color:#616b88;display:inline-block;width:80px;height:30px;line-height:30px;border:1px solid #d6dfe6;border-radius:4px;text-align:center;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;">两室一厅一卫</span>
                                        
                                    </div>
                                </div>
                            </div>
                        </div> -->
                        <!-- <div class="row" style="margin-top:10px;">
                            <div class="col-md-4" style="width:90%;margin-left:2%;"> 
                                <div class="row">
                                    <div class="col-md-2" style="width:32%;margin-top:15px;text-align:right;padding:0;">
                                        <span style="font-size: 14px;color:#56678a;font-weight:bold;">面积<b>：</b></span>
                                    </div>
                                    <div class="col-md-8" style="width:66%;padding:0;margin-top:10px;">
                                        <span style="font-size: 14px;color:#616b88;display:inline-block;width:80px;height:30px;line-height:30px;border:1px solid #d6dfe6;border-radius:4px;text-align:center;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;">90至100平方米</span>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-top:10px;">
                            <div class="col-md-4" style="width:90%;margin-left:2%;"> 
                                <div class="row">
                                    <div class="col-md-2" style="width:32%;text-align:right;padding:0;margin-top:15px;">
                                        <span style="font-size: 14px;color:#56678a;font-weight:bold;">搜索到多的<b>：</b></span>
                                    </div>
                                    <div class="col-md-8" style="width:66%;padding:0;margin-top:10px;">
                                        <span style="font-size: 14px;color:#616b88;display:inline-block;width:80px;height:30px;line-height:30px;border:1px solid #d6dfe6;border-radius:4px;text-align:center;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;">90至100平方米</span>
                                        <span style="font-size: 14px;color:#616b88;display:inline-block;width:80px;height:30px;line-height:30px;border:1px solid #d6dfe6;border-radius:4px;text-align:center;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;">90至100平方米</span>
                                        <span style="font-size: 14px;color:#616b88;display:inline-block;width:80px;height:30px;line-height:30px;border:1px solid #d6dfe6;border-radius:4px;text-align:center;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;">90至100平方米</span>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-top:10px;">
                            <div class="col-md-4" style="width:90%;margin-left:2%;"> 
                                <div class="row">
                                    <div class="col-md-2" style="width:32%;margin-top:15px;text-align:right;padding:0;">
                                        <span style="font-size: 14px;color:#56678a;font-weight:bold;">性别<b>：</b></span>
                                    </div>
                                    <div class="col-md-8" style="width:66%;padding:0;margin-top:10px;">
                                        <span style="font-size: 14px;color:#616b88;display:inline-block;width:80px;height:30px;line-height:30px;border:1px solid #d6dfe6;border-radius:4px;text-align:center;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;">男</span>
                                        
                                    </div>
                                </div>
                            </div>
                        </div> -->
                        <!-- <div class="row" style="margin-top:10px;">
                            <div class="col-md-4" style="width:90%;margin-left:2%;"> 
                                <div class="row">
                                    <div class="col-md-2" style="width:32%;margin-top:15px;text-align:right;padding:0;">
                                        <span style="font-size: 14px;color:#56678a;font-weight:bold;">形象<b>：</b></span>
                                    </div>
                                    <div class="col-md-8" style="width:66%;padding:0;margin-top:10px;">
                                        <span style="font-size: 14px;color:#616b88;display:inline-block;width:80px;height:30px;line-height:30px;border:1px solid #d6dfe6;border-radius:4px;text-align:center;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;">稳重</span>
                                        <span style="font-size: 14px;color:#616b88;display:inline-block;width:80px;height:30px;line-height:30px;border:1px solid #d6dfe6;border-radius:4px;text-align:center;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;">和气</span>
                                    </div>
                                </div>
                            </div>
                        </div> -->
                    </div>
                </div>
            </div>

       
    </div> 
    </div>
    
   <script src="static/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="static/js/echarts.js"></script>
    <script src="static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="static/js/calendarM.js"></script> 
    <script src="static/js/archon.js"></script>
    <script src="static/js/clientMessage.js"></script>
   <!-- <script type="text/javascript">
  
        var myPath = echarts.init(document.getElementById('pathPic'));
        var option = {           
               
                tooltip: {
                    confine:true,
                },
                
                radar: {
                    // shape: 'circle',
                    indicator: [
                       { name: '销售（sales）', max: 6500},
                       { name: '管理（Administration）', max: 16000},
                       { name: '信息技术（Information Techology）', max: 30000},
                       { name: '客服（Customer Support）', max: 38000},
                       { name: '研发（Development）', max: 52000},
                       { name: '市场（Marketing）', max: 25000}
                    ]
                },
                series: [{
                    name: '预算 vs 开销（Budget vs spending）',
                    type: 'radar',
                    // areaStyle: {normal: {}},
                    data : [
                        {
                            value : [4300, 10000, 28000, 35000, 50000, 19000],
                            name : '预算分配（Allocated Budget）'
                        },
                         {
                            value : [5000, 14000, 28000, 31000, 42000, 21000],
                            name : '实际开销（Actual Spending）'
                        }
                    ]
                }]
               };
        myPath.setOption(option);

   </script> -->

</body>
</html>
  