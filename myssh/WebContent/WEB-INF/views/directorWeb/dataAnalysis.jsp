<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
    <link rel="stylesheet" type="text/css" href="static/css/dataAnalysis.css" />
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
   <link rel="stylesheet" type="text/css" href="static/lib/laydate/need/laydate.css" />
    <link rel="stylesheet" type="text/css" href="static/css/calendarM.css" />
    <link  rel="icon" href="http://root-12521005170.cossh.myqcloud.com/static/images/titleLogo.png" />
    
     <style type="text/css">
    	
    	p,span,tr,td,a,li,option,select,button{font-family:"微软雅黑" !important;}
    </style>
        
    
</head>
<body>
    <div class="frame">
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
                   
                    <li>
                    	<a href="to_director_page_index" class="noDrop"><span>今日案场</span></a>
                    	<!--  <ul style="display:block;border:0;">
                            <li><a href="to_goToManagerMap" style="padding-left:5px;color:#ffffff;font-size: 14px;"><span>地图</span></a></li>
                        </ul> -->	
                    </li> 
                     <li><a href="to_goToManagerMap" class="noDrop"><span>门店地图</span></a></li>
                    <li><a href="to_data_analysis_page" class="noDrop"><span style="color:#46c1de;">数据分析</span></a></li>
                    <li><a href="to_data_statement_page" class="noDrop"><span>数据报表</span></a></li>
                    <li><a href="to_order_page" class="noDrop"><span>订单</span></a></li>
                    <li><a href="to_manager_team_page" class="noDrop"><span>团队</span></a></li>
                     <li><a href="to_pCustomerManager" class="noDrop"><span>客户管理</span></a>
                    	<ul style="display:block;border:0;">
                            <li><a href="customerAnalyze" style="padding-left:5px;color:#ffffff;font-size: 14px;"><span>客户分析</span></a></li>
                        </ul>
                    </li>                      
                </ul>
               <div id="calendar" class="calendar" ></div>
              <!--  <div class="row jindu" style="width:240px;margin-left:2px;border-radius:4px;background:#47506c;margin-top:40px;">
                   <div class="col-md-12 "  >
                        <div class="row">
                           <div class="col-md-12" style="margin-top:20px;">
                               <p style="color:#d5d9e0;font-size:14px;">项目任务完成进度</p>
                           </div>
                        </div>
                        <div class="row">
                           <div class="col-md-12">
                               <p style="font-size:12px;color:#d5d9e0;">接访量完成<span>70%</span></p>
                           </div>
                         </div>
                        <div class="progress" style="height:10px;" >
                            <div class="progress-bar progress-bar-warning"  role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:70%;">
                            </div>
                        </div>
                        <div class="row">
                          <div class="col-md-12">
                               <p style="font-size:12px;color:#d5d9e0;">认购量完成<span>70%</span></p>
                          </div>
                          </div>
                        <div class="progress" style="height:10px;">
                            <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:48%;">
   
                            </div>
                        </div>
                        <div class="row">
                           <div class="col-md-12">
                               <p style="font-size:12px;color:#d5d9e0;">签约量完成<span>70%</span></p>
                           </div>
                        </div>
                        <div class="progress" style="height:10px;">
                            <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 1.50%;">
   
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
                                <img src="images/theme/avatarTwo.png" alt="" class="avatar">
                                <div class="message">
                                    <span class="username">汪俊鹏</span> 
                                    <span class="mini-details">(6) <i class="icon-paper-clip"></i></span>
                                    <span class="time pull-right"> 06:58 PM</span>
                                    <p>汪汪汪</p>
                                </div>
                            </li>
                            <li>
                                <img src="images/theme/avatarFive.png" alt="" class="avatar">
                                <div class="message">
                                    <span class="username">陈诗雨</span> 
                                    <span class="mini-details">(2) <i class="icon-paper-clip"></i></span>
                                    <span class="time pull-right"> 09:58 AM</span>
                                    <p>鱼鱼鱼</p>
                                </div>
                            </li>
                            <li>
                                <img src="images/theme/avatarSix.png" alt="" class="avatar">
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
                        </ul> -->
                    </li><!-- /dropdown -->
                    <li class="dropdown user-name">
                        <a class="dropdown-toggle" data-toggle="dropdown" style="color:#333333;border:0;"><img src="static/images/sanchong.jpg" class="user-avatar" alt="" /><span style="font-size:16px;color:#616b88;">${sessionScope.userInfo.userCaption}</span></a>
                            <ul class="dropdown-menu right inbox user">
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
            	
                <div class="row" style="margin-top:34px;margin-left:20px;">
                    <div class="col-md-6">
                        <input class="laydate-icon  start-time" name="startTime" id="start" placeholder="起始日期"/>
                        <input class="laydate-icon  end-time" name="deliverTime" id="end" placeholder="截止日期"/>
                        <button type="button" class="btn btn-success " style="height:36px;vertical-align:top;" id="search">查询</button>
                    </div>
                   
                </div>

                <div class="row" style="margin-top:24px;margin-left:36px;">
                    <div class="col-md-8" >
                        <div class="row listOne">
                            <div class="col-md-12">
                                <div class="row">
                                    <div class="col-md-3">
                                        <p class="jie">接访分析</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                         <div id="main" style="height:480px;max-width:100%;"></div>
                                    </div>
                                </div>

                            </div>                                
                        </div>
                        <div class="row listOne" style="margin-top:24px;">
                            <div class="col-md-12">
                                <div class="row">
                                    <div class="col-md-3">
                                        <p class="jie">储客分析</p>
                                    </div>
                                </div>
                                 <div class="row">
                                    <div class="col-md-12">
                                         <div id="mainOne" style="height:260px;max-width:100%;"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row listOne" style="margin-top:24px;">
                            <div class="col-md-12">
                                <div class="row">
                                    <div class="col-md-3">
                                        <p class="jie">成交分析</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12" >
                                        <div id="mainTwo" style="height:378px;max-width:100%;"></div>
                                    </div>
                                </div>
                            </div>
                        </div>      
                    </div>
                    <div class="col-md-3 " style="margin-left:54px;">
                        <div class="row listOne" style="height:528px;">
                            <div class="col-md-12" id="visitNone1">
                                <div class="row">
                                    <div class="col-md-3" style="width:50%;">
                                        <p class="jie">接访</p>
                                    </div>
                                    <div class="col-md-5 " style="text-align:right;width:50%;">
                                        <p class="dan">单位:次</p>
                                    </div>
                                </div>
                                <div class="row">
                                <div class="col-md-12" id="visitLabelshow" style="text-align:center">
                                	<img alt="" src="static/images/5-1503130Q911.gif"><span style="display:inline-block;vertical-align:bottom;">正在加载...</span>
                                </div>
                                    <div class="col-md-12" id="visitLabel">
                                        <div class="row" style="margin-top:20px;">
                                            <div class="col-md-12">
                                                <div class="row" >
                                                    <div class="col-md-6 can" style="text-align:right;padding-right:0;">
                                                        <canvas id="canvas"  height="100"  width="100" ></canvas>
                                                    </div>
                                                    <div class="col-md-6" style="padding-left:0;">
                                                        <div style="margin-top:20px;">
                                                            <span  style="font-size:14px;color:#46c1de;">无效接访</span>
                                                            <span style="font-size: 14px;color:#46c1de;margin-left: 20px;" id="inValidVisitCount"></span>
                                                        </div>
                                                        <div style="margin-top:15px;">
                                                            <span style="font-size:14px;color:#ffab11;">有效接访</span>
                                                            <span style="font-size:14px;color:#ffab11;margin-left: 20px;" id="validVisitCount"></span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row fontSame">
                                           <div class="col-md-12" >
                                               <div class="row "  style="border-bottom: 1px solid #f1f3f9;padding-bottom:15px;padding-top:15px;">
                                                   <div class="col-md-5" style="text-align:left;width:50%;">
                                                       <span>接访总量:</span>
                                                       <span id="totalVisitCount"></span>
                                                   </div>
                                                   <div class="col-md-5 " style="text-align:right;width:50%;">
                                                       <span id="allVisitCount"></span>
                                                   </div>
                                               </div>
                                               <div class="row" style="border-bottom: 1px solid #f1f3f9;padding-bottom:15px;padding-top:15px;">
                                                   <div class="col-md-7" style="width:60%;">
                                                       <div>
                                                           <span>新客户通道:</span>
                                                           <span id="newCustomerAccess"></span>
                                                       </div>
                                                       <div style="margin-top:5px;">
                                                           <span>新客户通道流失率:</span>
                                                           <span id="newCustomerAccessLosed"></span>
                                                       </div>
                                                   </div>
                                                   <div class="col-md-4" style="text-align:right;width:40%;">
                                                       <div>
                                                          
                                                           <span id="allNewCustomerAccess"></span>
                                                       </div>
                                                       <div style="margin-top:5px;">
                                                                                                       
                                                            <span id="allNewStomerAccessLosed"></span>
                                                       </div>
                                                   </div>

                                               </div>
                                               <div class="row" style="border-bottom: 1px solid #f1f3f9;padding-bottom:15px;padding-top:15px;">
                                                   <div class="col-md-7" style="width:60%;">
                                                       <div>
                                                           <span>老客户通道:</span>
                                                           <span id="oldCustomerAccess"></span>
                                                       </div>
                                                       <div style="margin-top:5px;">
                                                           <span>老客户通道流失率:</span>
                                                           <span id="oldCustomerAccessLosed"></span>
                                                       </div>
                                                   </div>
                                                   <div class="col-md-4 " style="text-align:right;width:40%;">
                                                       <div>
                                                          
                                                           <span id="allOldCustomerAccess"></span>
                                                       </div>
                                                       <div style="margin-top:5px;">
                                                                                                             
                                                            <span id="allOldCustomerAccessLosed"></span>
                                                       </div>
                                                   </div>
                                                   
                                               </div>
                                              
                                                <div class="row" style="border-bottom: 1px solid #f1f3f9;padding-bottom:15px;padding-top:15px;">
                                                   <div class="col-md-7" style="width:60%;">
                                                       <div>
                                                           <span>指定接访:</span>
                                                           <span id="appointVisitCount"></span>
                                                       </div>
                                                       <div style="margin-top:5px;">
                                                           <span>指定接访流失率:</span>
                                                           <span id="appointVisitLosed"></span>
                                                       </div>
                                                       <div style="margin-top:5px;">
                                                           <span>替接:</span>
                                                           <span id="replaceVisitCount"></span>
                                                       </div>
                                                   </div>
                                                   <div class="col-md-4 " style="text-align:right;width:40%;">
                                                       <div>
                                                          
                                                           <span id="allAppointVisitCount"></span>
                                                       </div>
                                                       <div style="margin-top:5px;">
                                                                                                           
                                                            <span id="allAppointLosed"></span>
                                                       </div>
                                                        <div style="margin-top:5px;">
                                                                                                          
                                                            <span id="allReplaceVisitCount"></span>
                                                       </div>
                                                   </div>
                                                   
                                               </div>
                                                <div class="row "  style="border-bottom: 1px solid #f1f3f9;padding-bottom:15px;padding-top:15px;">
                                                   <div class="col-md-6" style="text-align:left;width:60%;">
                                                       <span>平均接待时长:</span>
                                                       <span id="averageVisitTime"></span><span></span>
                                                   </div>
                                                   <div class="col-md-5 " style="text-align:right;width:40%;">
                                                       <span id="allAverageVisitTime"></span><span></span>
                                                   </div>
                                               </div>
                                           </div>
                                        </div>
                                    </div>                                  
                                </div>
                            </div>
                            <div class="col-md-12" id="visitNone2" style="margin-bottom:20px;display:none;">
                                <div class="row">
                                    <div class="col-md-12">
                                        <p class="jie">接访数据</p>
                                    </div>                                   
                                </div>
                                <div class="row" style="margin-top:20px;">
                                    <div class="col-md-4" style="text-align:center;width:40%;">
                                        <div>
                                            <span style="font-size:12px;color:#ff9666;" id="startD1"></span>                                                    
                                        </div>
                                        <div>
                                            <span style="font-size:12px;color:#ff9666;" id="endD1"></span>                                                   
                                        </div>
                                    </div>
                                    <div class="col-md-3 " style="text-align:center;width:20%;">
                                        <div style="margin-top:5px;">
                                            <span style="font-size:24px;color:#ff9666;">VS</span>
                                        </div>
                                    </div>
                                    <div class="col-md-4 " style="text-align:center;width:40%;">
                                        <div>
                                            <span style="font-size:12px;color:#ff9666;" id="startD2"></span>   
                                        </div>
                                        <div >
                                            <span style="font-size:12px;color:#ff9666;" id="endD2"></span>                              
                                        </div>
                                    </div>
                                </div>
                                <div class="row fontSame" >
	                                
                                    <div class="col-md-12" >
                                        <div class="row kk">
                                            <div class="col-md-12" style="text-align:center;">
                                                <span >总接访量</span>
                                            </div>
                                                
                                        </div>
                                        <div class="row">
                                            <div class="col-md-5" style="text-align:left;width:50%;" >
                                                <span  id="allOne"></span>
                                            </div>
                                            <div class="col-md-6" style="text-align:right;width:50%;">
                                                <span  id="allTwo"></span>
                                            </div>
                                        </div>
                                        <div class="row duiBi">
                                            <div class="col-md-12" style="">
                                                <div class="progress zongColor">
													<div class="progress-bar zongRate" style="">
														
													</div>
												</div>
                                            </div>
                                        </div>
                                        <div class="row ">
                                            <div class="col-md-12" style="text-align:center;">
                                                <span >老客户来访</span>
                                            </div>
                                                
                                        </div>
                                        <div class="row">
                                            <div class="col-md-5" style="text-align:left;width:50%;" >
                                                <span  id="va1"></span>
                                            </div>
                                            <div class="col-md-6" style="text-align:right;width:50%;">
                                                <span  id="va2"></span>
                                            </div>
                                        </div>
                                        <div class="row duiBi">
                                            <div class="col-md-12" style="">
                                                <div class="progress oldColor">
													<div class="progress-bar againRate" >
														
													</div>
												</div>
                                            </div>
                                        </div>
                                        <div class="row ">
                                            <div class="col-md-12" style="text-align:center;">
                                                <span >新客户来访</span>
                                            </div>
                                                
                                        </div>
                                        <div class="row">
                                            <div class="col-md-5" style="text-align:left;width:50%;" >
                                                <span  id="nc1"></span>
                                            </div>
                                            <div class="col-md-6" style="text-align:right;width:50%;">
                                                <span  id="nc2"></span>
                                            </div>
                                        </div>
                                         <div class="row duiBi">
                                            <div class="col-md-12" style="">
                                                <div class="progress newColor">
													<div class="progress-bar newRate" ">
														
													</div>
												</div>
                                            </div>
                                        </div>
                                       
                                        <div class="row ">
                                            <div class="col-md-12" style="text-align:center;">
                                                <span >接访流失数</span>
                                            </div>
                                                
                                        </div>
                                        <div class="row">
                                            <div class="col-md-5" style="text-align:left;width:50%;" >
                                                <span  id="losedD1"></span>
                                            </div>
                                            <div class="col-md-6" style="text-align:right;width:50%;">
                                                <span  id="losedD2"></span>
                                            </div>
                                        </div>
                                         <div class="row duiBi">
                                            <div class="col-md-12" >
                                                <div class="progress jieColor" >
													<div class="progress-bar liushi" >
														
													</div>
												</div>
                                            </div>
                                        </div>
                                        
                                        
                                        
                                        <div class="row ">
                                            <div class="col-md-12" style="text-align:center;">
                                                <span>老客户来访占比</span>
                                            </div>
                                                
                                        </div>
                                        <div class="row">
                                            <div class="col-md-5" style="text-align:left;width:50%;" >
                                                <span  id="againVisitRateOne"></span>
                                            </div>
                                            <div class="col-md-6" style="text-align:right;width:50%;">
                                                <span  id="againVisitRateTwo"></span>
                                            </div>
                                        </div>
                                         <div class="row duiBi">
                                            <div class="col-md-12" >
                                                <div class="progress oldLaiColor" >
													<div class="progress-bar againVisitRate" >
														
													</div>
												</div>
                                            </div>
                                        </div>
                                        
                                        
                                        <div class="row ">
                                            <div class="col-md-12" style="text-align:center;">
                                                <span>接访流失占比</span>
                                            </div>
                                                
                                        </div>
                                        <div class="row">
                                            <div class="col-md-5" style="text-align:left;width:50%;" >
                                                <span  id="loseVisitRateOne"></span>
                                            </div>
                                            <div class="col-md-6" style="text-align:right;width:50%;">
                                                <span  id="loseVisitRateTwo"></span>
                                            </div>
                                        </div>
                                         <div class="row duiBi">
                                            <div class="col-md-12" style="">
                                                <div class="progress jieliuColor" >
													<div class="progress-bar loseVisteRate" >
														
													</div>
												</div>
                                            </div>
                                        </div>
                                        
                                    </div>
                                </div>
                            </div>                                
                        </div>
                        <div class="row listOne" style="margin-top:24px;height:308px;">
                            <div class="col-md-12" id="memeryView1">
                                <div class="row">                                    
                                    <div class="col-md-3" style="width:50%;">
                                        <p class="jie">储客</p>
                                    </div>
                                    <div class="col-md-5 " style="text-align:right;width:50%;">
                                        <p class="dan">单位:组</p>
                                    </div>
                                </div>
                                <div class="row fontSame">
	                                <div class="col-md-12" id="memoryLoading" style="text-align:center">
	                                	<img alt="" src="static/images/5-1503130Q911.gif"><span style="display:inline-block;vertical-align:bottom;">正在加载...</span>
	                                </div>
                                    <div class="col-md-12">
                                        <div class="row "  style="border-bottom: 1px solid #f1f3f9;padding-bottom:16px;padding-top:35px;">
                                           <div class="col-md-5" style="text-align:left;width:50%;">
                                               <span>新增储客:</span>
                                               <span id="newCustomerCount"></span>
                                           </div>
                                           <div class="col-md-5 " style="text-align:right;width:50%;">
                                               <span id="totalNewCustomerCount"></span>
                                           </div>
                                        </div>
                                        <div class="row "  style="border-bottom: 1px solid #f1f3f9;padding-bottom:16px;padding-top:16px;">
                                           <div class="col-md-5" style="text-align:left;width:50%;">
                                               <span>新增二次回头客:</span>
                                               <span id="customerReturnBackVisitNum"></span>
                                           </div>
                                           <div class="col-md-5 " style="text-align:right;width:50%;">
                                               <span id="totalCustomerReturnBackVisitNum"></span>
                                           </div>
                                        </div>
                                        <div class="row "  style="border-bottom: 1px solid #f1f3f9;padding-bottom:16px;padding-top:16px;">
                                           <div class="col-md-5" style="text-align:left;width:50%;">
                                               <span>平台导客:</span>
                                               <span id="platformCustomerCount"></span>
                                           </div>
                                           <div class="col-md-5 " style="text-align:right;width:50%;">
                                               <span id="totalPlatformCustomerCount"></span>
                                           </div>
                                        </div>
                                        <div class="row "  style="border-bottom: 1px solid #f1f3f9;padding-bottom:40px;padding-top:16px;">
                                           <div class="col-md-5" style="text-align:left;width:50%;">
                                               <span>平台导客率:</span>
                                               <span id="platformCustomerRate"></span>
                                           </div>
                                           <div class="col-md-5 " style="text-align:right;width:50%;">
                                               <span id="totalPlatformCustomerRate"></span>
                                           </div>
                                        </div>
                                    </div>
                                </div>                               
                            </div>
                       <!--      <div class="col-md-12" style="display:none;" id="memeryView2">
                                <div class="row">                                    
                                    <div class="col-md-4">
                                        <p class="jie">储客数据</p>
                                    </div>
                                </div>
                                <div class="row" style="margin-left:30px;margin-top:20px;">
                                    <div class="col-md-4" style="text-align:center;">
                                        <div>
                                            <span style="font-size:12px;color:#ff9666;" id="startD3"></span>                                                    
                                        </div>
                                        <div>
                                            <span style="font-size:12px;color:#ff9666;" id="endD3"></span>                                                   
                                        </div>
                                    </div>
                                    <div class="col-md-3 " style="text-align:center;">
                                        <div style="margin-top:5px;">
                                            <span style="font-size:24px;color:#ff9666;">VS</span>
                                        </div>
                                    </div>
                                    <div class="col-md-4 " style="text-align:center;">
                                        <div>
                                            <span style="font-size:12px;color:#ff9666;" id="startD4"></span>   
                                        </div>
                                        <div >
                                            <span style="font-size:12px;color:#ff9666;" id="endD4"></span>                              
                                        </div>
                                    </div>
                                </div>
                                <div class="row fontSame" style="margin-bottom:40px;">
                                    <div class="col-md-12">
                                        <div class="row kk">
                                            <div class="col-md-12" style="text-align:center;">
                                                <span >新增客户</span>
                                            </div>
                                                
                                        </div>
                                        <div class="row">
                                            <div class="col-md-5" style="text-align:left;margin-left:20px;" >
                                                <span  id="nct1"></span>
                                            </div>
                                            <div class="col-md-6" style="text-align:right;">
                                                <span  id="nct2"></span>
                                            </div>
                                        </div>
                                        <div class="row kk">
                                            <div class="col-md-12" style="text-align:center;">
                                                <span >老客户</span>
                                            </div>
                                                
                                        </div>
                                        <div class="row">
                                            <div class="col-md-5" style="text-align:left;margin-left:20px;" >
                                                <span  id="oct1"></span>
                                            </div>
                                            <div class="col-md-6" style="text-align:right;">
                                                <span  id="oct2"></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div> -->
                        </div>
                        <div class="row listOne" style="margin-top:24px;height:426px;">
                            <div class="col-md-12">
                                <div class="row">                                    
                                    <div class="col-md-3" style="width:50%;">
                                        <p class="jie">累计成交</p>
                                    </div>
                                    <div class="col-md-5 " style="text-align:center;width:50%;">
                                        <p class="dan">单位:套\万</p>
                                    </div>
                                </div>
                                <div class="row" style="">
                                	<div class="col-md-12" id="dealLoading" style="text-align:center">
	                                	<img alt="" src="static/images/5-1503130Q911.gif"><span style="display:inline-block;vertical-align:bottom;">正在加载...</span>
	                                </div>
                                    <div class="col-md-12">
                                        <div class="row" style="margin-top:20px;">
                                            <div class="col-md-3" style="text-align:center;padding-right:0;width:40%;">
                                                <canvas id="canvasTwo"  height="100"  width="100" ></canvas>
                                            </div>
                                            <div class="col-md-4" style="text-align:left;width:60%;">
                                                
                                                <div style="margin-top:38px;">
                                                    <span style="font-size:12px;color:#ffab11;">尚未完成签约</span>
                                                    <span style="font-size:12px;color:#ffab11;margin-left: 20px;" id="notSignedCount"></span>
                                                </div>
                                            </div>
                                        </div>
                                         <div class="row fontSame">
                                   <div class="col-md-12" >
                                       <div class="row "  style="border-bottom: 1px solid #f1f3f9;padding-bottom:15px;padding-top:15px;">
                                           <div class="col-md-12" style="text-align:left;">
                                               <span>认购总量:</span>
                                               <span id="enterBuyCount"></span>
                                           </div>
                                          
                                       </div>
                                       <div class="row" style="border-bottom: 1px solid #f1f3f9;padding-bottom:15px;padding-top:15px;">
                                           <div class="col-md-12" style="">
                                               <div>
                                                   <span>下定锁定房源货值:</span>
                                                   <span id="appointCount"></span>
                                               </div>
                                               <div style="margin-top:5px;">
                                                   <span>客户认购率:</span>
                                                   <span id="enterBuyRate"></span>
                                               </div>
                                           </div>
                                           

                                       </div>
                                       <div class="row" style="border-bottom: 1px solid #f1f3f9;padding-bottom:15px;padding-top:15px;">
                                           <div class="col-md-12" style="">
                                               <div>
                                                   <span>签约数量:</span>
                                                   <span id="signedCount"></span>
                                               </div>
                                               <div style="margin-top:5px;">
                                                   <span>来访成交率:</span>
                                                   <span id="visitAndBuyRate"></span>
                                               </div>
                                           </div>
                                           
                                           
                                       </div>
                                      
                                       
                                        <div class="row "  style="border-bottom: 1px solid #f1f3f9;padding-bottom:15px;padding-top:15px;">
                                           <div class="col-md-12" style="text-align:left;">
                                               <span>认购签约率:</span>
                                               <span id="recordsAndSignedRate"></span>
                                           </div>
                                           
                                       </div>
                                   </div>
                               </div>
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                    </div>
                </div>  
            </div>
          
        </div>

       
    </div> 
    
    
    <script src="static/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="static/js/echarts.min.js"></script>
   <!--  <script src="js/jquery-ui-1.10.3.custom.min.js"></script>
    <script src="js/jquery.ui.touch-punch.min.js"></script> -->
    <script src="static/js/bootstrap.min.js"></script>
   <!--  <script src="js/bootstrap-select.js"></script>
    <script src="js/bootstrap-switch.js"></script>
    <script src="js/flatui-checkbox.js"></script>
    <script src="js/flatui-radio.js"></script>
    <script src="js/jquery.tagsinput.js"></script>
    <script src="js/jquery.placeholder.js"></script>
    <script src="js/bootstrap-typeahead.js"></script> -->
    <script type="text/javascript" src="static/js/calendarM.js"></script>  
   
    <script src="static/js/archon.js"></script>
    <script src="static/js/dataAnalysis.js"></script>
    <script type="text/javascript" src="static/lib/laydate/laydate.js"></script> 
   <script type="text/javascript">
   var start = {
          elem: '#start',
          format: 'YYYY-MM-DD',
          //min: laydate.now(), //设定最小日期为当前日期
          max: laydate.now(-1), //最大日期
          istime: true,
          istoday: false,
          choose: function(datas){
             end.min = datas; //开始日选好后，重置结束日的最小日期
             end.start = datas  //将结束日的初始值设定为开始日
          }
        };
    var end = {
      elem: '#end',
      format: 'YYYY-MM-DD',
      //min: ,
      
      max:laydate.now(-1),
      istime: true,
      istoday: false,
      choose: function(datas){
        start.max = datas; //结束日选好后，重置开始日的最大日期
      }
    };
    laydate(start);
    laydate(end); 

   </script>
   
   <script type="text/javascript">
   $(document).ready(function(){
	   
	  
       
		})

function toChangeCircleOne(){
	   
	   canvas = document.getElementById('canvas'),  //获取canvas元素
       context = canvas.getContext('2d'),  //获取画图环境，指明为2d
       centerX = canvas.width/2,   //Canvas中心点x轴坐标
       centerY = canvas.height/2,  //Canvas中心点y轴坐标
       rad = Math.PI*2/100; //将360度分成100份，那么每一份就是rad度
	   
	   var validData = $('#validVisitCount').html();
	   var inValidData = $('#inValidVisitCount').html();
	   var totalVisit = $('#totalVisitCount').html();
	   if(totalVisit == 0 || totalVisit == "0"){
          yellowCircle(0);
          text(0);
          blueCircle(0);
	   }else{
          yellowCircle(Math.round((validData / totalVisit)*100));
          text(Math.round((validData / totalVisit)*100));
          blueCircle(Math.round((inValidData / totalVisit)*100));
		   
	   }
          
   }
        var canvas = document.getElementById('canvas'),  //获取canvas元素
            context = canvas.getContext('2d'),  //获取画图环境，指明为2d
            centerX = canvas.width/2,   //Canvas中心点x轴坐标
            centerY = canvas.height/2,  //Canvas中心点y轴坐标
            rad = Math.PI*2/100; //将360度分成100份，那么每一份就是rad度

        //绘制蓝色外圈
        function blueCircle(n){
            
           
            context.save();
            context.strokeStyle = "#46c1de";
            //设置描边样式
            context.lineWidth = 3; //设置线宽
            context.beginPath(); //路径开始
            context.arc(centerX, centerY, 30 , -Math.PI/2, -Math.PI/2 +n*rad, false); //用于绘制圆弧context.arc(x坐标，y坐标，半径，起始角度，终止角度，顺时针/逆时针)
            context.stroke(); //绘制
            context.closePath(); //路径结束
            context.restore();
        }
        //绘制白色外圈
        function yellowCircle(){
        	
        	
            context.save();
            context.beginPath();
            context.lineWidth = 3;
            context.strokeStyle = "#ffab11";
            context.arc(centerX, centerY, 30 , 0, Math.PI*2, false);
            context.stroke();
            context.closePath();
            context.restore();
        }
        //百分比文字绘制
        function text(n){
        	
            context.save(); //save和restore可以保证样式属性只运用于该段canvas元素
            context.strokeStyle = "#ffab11"; //设置描边样式
            context.font = "14px Arial"; //设置字体大小和字体
            //绘制字体，并且指定位置
            context.strokeText(n.toFixed(0)+"%", centerX-12, centerY+6);
            context.stroke(); //执行绘制
            context.restore();
        }
        //动画循环
        // (function drawFrame(){

        //     window.requestAnimationFrame(drawFrame, canvas);
        //     context.clearRect(0, 0, canvas.width, canvas.height);

            


        // }());
    

   </script>
   <script type="text/javascript">
         $(document).ready(function(){
        	
})

function toChangeCircleSeven(){
        	 var notSign  = $('#enterBuyCount').html() - $('#signedCount').html();
        	 //console.log(notSign);
        	 var total = $('#enterBuyCount').html();
        	 var sign = total - notSign;
        	 //console.log(total);
        	 //console.log(sign);
        	 if(total == 0 || total == "0"){
	            yellowCircleTwo(0);
	            textTwo(0);
	            blueCircleTwo(0);
        	 }else{
	            yellowCircleTwo(Math.round((notSign/total) * 100));
	            textTwo(Math.round((notSign/total) * 100));
            blueCircleTwo(Math.round((sign/total) * 100));
        	 }
         }


        var canvasTwo = document.getElementById('canvasTwo'),  //获取canvas元素
            contextTwo = canvasTwo.getContext('2d'),  //获取画图环境，指明为2d
            centerTwoX = canvasTwo.width/2,   //Canvas中心点x轴坐标
            centerTwoY = canvasTwo.height/2,  //Canvas中心点y轴坐标
            radTwo = Math.PI*2/100; //将360度分成100份，那么每一份就是rad度

        //绘制蓝色外圈
        function blueCircleTwo(n){
            contextTwo.save();
            contextTwo.strokeStyle = "#46c1de";
            //设置描边样式
            contextTwo.lineWidth = 3; //设置线宽
            contextTwo.beginPath(); //路径开始
            contextTwo.arc(centerTwoX, centerTwoY, 30 , -Math.PI/2, -Math.PI/2 +n*radTwo, false); //用于绘制圆弧context.arc(x坐标，y坐标，半径，起始角度，终止角度，顺时针/逆时针)
            contextTwo.stroke(); //绘制
            contextTwo.closePath(); //路径结束
            contextTwo.restore();
        }
        //绘制白色外圈
        function yellowCircleTwo(){
            contextTwo.save();
            contextTwo.beginPath();
            contextTwo.lineWidth = 3;
            contextTwo.strokeStyle = "#ffab11";
            contextTwo.arc(centerTwoX, centerTwoY, 30 , 0, Math.PI*2, false);
            contextTwo.stroke();
            contextTwo.closePath();
            contextTwo.restore();
        }
        //百分比文字绘制
        function textTwo(n){
            contextTwo.save(); //save和restore可以保证样式属性只运用于该段canvas元素
            contextTwo.strokeStyle = "#ffab11"; //设置描边样式
            contextTwo.font = "14px Arial"; //设置字体大小和字体
            //绘制字体，并且指定位置
            contextTwo.strokeText(n.toFixed(0)+"%", centerTwoX-12, centerTwoY+6);
            contextTwo.stroke(); //执行绘制
            contextTwo.restore();
        }

   </script>
</body>
</html>
    