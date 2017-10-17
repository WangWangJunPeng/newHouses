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
    <link rel="stylesheet" type="text/css" href="static/css/orderForm.css" />
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <link rel="stylesheet" type="text/css" href="static/css/calendarM.css" />
    <link  rel="icon" href="http://root-12521005170.cossh.myqcloud.com/static/images/titleLogo.png" />
    <!-- 翻页插件 -->
	<link rel="stylesheet" href="static/css/layui.css" media="all" />
	 <style type="text/css">
    	
    	p,span,tr,td,a,li,option,select,button{font-family:"微软雅黑" !important;}
    </style>
</head>
<body>
    <div class="frame">
    	<input id="buttonSign" type="hidden" value="${buttonSign}">
        <div class="sidebar" style="background:#616b88;">
            <div class="wrapper">           
                <div style="width:280px;;background:#565e78;height:80px;margin-left:-20px;">
                    <img src="static/images/logoer.png" alt=""  class="img-responsive"  style="padding-top:14px;margin-left:81px;"/>
                </div>
               <div style="height:40px;position:relative;margin-top:10px;margin-left:10px;">
                    <img src="static/images/peopleR.png"  style="position:relative;"/>
                    <span style="color:#eaeaea;font-size:18px;font-weight:bold;position:relative;top:3px;left:4px;">案场经理个人中心</span>
                </div>
                <ul class="nav  nav-list">
                    
                    <li>
                    
                    	<a href="to_director_page_index" class="noDrop"><span>今日案场</span></a>
                    	<!--  <ul style="display:block;border:0;">
                            <li><a href="to_goToManagerMap" style="padding-left:5px;color:#ffffff;font-size: 14px;"><span>地图</span></a></li>
                        </ul>  -->
                        
                        
                        	
                    </li> 
                    <li><a href="to_goToManagerMap" class="noDrop"><span>门店地图</span></a></li>
                    <li><a href="to_data_analysis_page" class="noDrop"><span>数据分析</span></a></li>
                    <li><a href="to_data_statement_page" class="noDrop"><span>数据报表</span></a></li>
                    <li><a href="to_order_page" class="noDrop"><span style="color:#46c1de;">订单</span></a></li>
                    <li><a href="to_manager_team_page" class="noDrop"><span>团队</span></a></li> 
                    <li><a href="to_pCustomerManager" class="noDrop"><span>客户管理</span></a>
                    	<ul style="display:block;border:0;">
                            <li><a href="customerAnalyze" style="padding-left:5px;color:#ffffff;font-size: 14px;"><span>客户分析</span></a></li>
                        </ul>
                    
                    
                    </li>                         
                </ul>
               <div id="calendar" class="calendar" style="display:none;"></div>
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
                <div class="listOne" style="width:96%;margin-left:30px;margin-top:30px;">
                    <ul class="nav nav-tabs" role="tablist" id="tab-list" style="width:100%;border:0;padding:0;">

                        <li id="allOrderLi" style="width:12.5%;text-align:center;font-size:14px;background:#c9d9f8;border:0;"><a href="#allIndent" role="tab" data-toggle="tab" style="color:#616b88;border:0;margin-right:0;border-radius:0;padding-top:20px;padding-bottom:20px;">全部订单</a></li>
                        <li id="checkLi" class="" style="width:12.5%;text-align:center;font-size:14px;background:#c9d9f8;border:0;"><a href="#checkPend" role="tab" data-toggle="tab" style="color:#616b88;border:0;margin-right:0;border-radius:0;padding-top:20px;padding-bottom:20px;">待审核</a></li>
                        <li id="waitPayLi" class="" style="width:12.5%;text-align:center;font-size:14px;background:#c9d9f8;border:0;"><a href="#obligation" role="tab" data-toggle="tab" style="color:#616b88;border:0;margin-right:0;border-radius:0;padding-top:20px;padding-bottom:20px;">待付款</a></li>
                        <li id="paySureLi" class="" style="width:12.5%;text-align:center;font-size:14px;background:#c9d9f8;border:0;"><a href="#paySure" role="tab" data-toggle="tab" style="color:#616b88;border:0;margin-right:0;border-radius:0;padding-top:20px;padding-bottom:20px;">付款确认</a></li>
                        <li id="signLi" class="" style="width:12.5%;text-align:center;font-size:14px;background:#c9d9f8;border:0;"><a href="#signUp" role="tab" data-toggle="tab" style="color:#616b88;border:0;margin-right:0;border-radius:0;padding-top:20px;padding-bottom:20px;">待签约</a></li>
                        <li id="signedLi" class="" style="width:12.5%;text-align:center;font-size:14px;background:#c9d9f8;border:0;"><a href="#signed" role="tab" data-toggle="tab" style="color:#616b88;border:0;margin-right:0;border-radius:0;padding-top:20px;padding-bottom:20px;">已签约</a></li>
                        <li id="revokeLi" class="" style="width:12.5%;text-align:center;font-size:14px;background:#c9d9f8;border:0;"><a href="#repeal" role="tab" data-toggle="tab" style="color:#616b88;border:0;margin-right:0;border-radius:0;padding-top:20px;padding-bottom:20px;">已撤销</a></li>
                        <li id="refuseLi" class="" style="width:12.5%;text-align:center;font-size:14px;background:#c9d9f8;border:0;"><a href="#refuse" role="tab" data-toggle="tab" style="color:#616b88;border:0;margin-right:0;border-radius:0;padding-top:20px;padding-bottom:20px;">已拒绝</a></li>
                        
                    </ul>
                    <div class="tab-content allSpan" style="margin-top:50px;margin-left:20px;">
                            <div class="tab-pane" id="allIndent">
                                <div class="row " id="allOrder">
                                    <!-- <div class="col-md-3 sameColor" style="margin-left:90px;margin-bottom: 10px;;">
                                        <div class="row">
                                           <div class="col-md-10">
                                               <p class="pp">滨江海创9栋1单元103室</p>
                                           </div>
                                        </div>
                                        <div class="row coco">
                                            <div class="col-md-3" style="margin-left:20px;margin-top:34px;margin-bottom:40px;">
                                                <img src="images/timg.jpg" alt="" class="img-rounded">
                                            </div>
                                            <div class="col-md-7" style="margin-left:30px;margin-top:30px;margin-bottom:40px;">
                                                <div class="row sameTop">
                                                    <div class="col-md-12">
                                                        <span>下单时间：</span>
                                                        <span>2017.1.2</span>
                                                    </div>
                                                  

                                                </div>
                                                <div class="row sameTop">
                                                     <div class="col-md-12">
                                                        <span>价格：</span>
                                                        <span>1111万</span>
                                                    </div>
                                                </div>
                                                <div class="row sameTop">
                                                    <span class="sameA">待审核</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div> -->
                                </div>
                                <!-- 分页 -->
								<div class="admin-main">
									<div class="admin-table-page">
										<div id="pagedAll" class="page"></div>
									</div>
								</div>
							</div>
							
							
                            <div class="tab-pane" id="checkPend">
                                <div class="row " id="checkOrder">
                                     <!-- <div class="col-md-3 sameColor" style="margin-left:90px;margin-bottom: 10px;;">
                                        <div class="row">
                                           <div class="col-md-10">
                                               <p class="pp">滨江海创9栋1单元103室</p>
                                           </div>
                                        </div>
                                        <div class="row coco">
                                            <div class="col-md-3" style="margin-left:20px;margin-top:34px;margin-bottom:40px;">
                                                <img src="images/timg.jpg" alt="" class="img-rounded">
                                            </div>
                                            <div class="col-md-7" style="margin-left:30px;margin-top:30px;margin-bottom:40px;">
                                                <div class="row sameTop">
                                                    <div class="col-md-12">
                                                        <span>下单时间：</span>
                                                        <span>2017.1.2</span>
                                                    </div>
                                                  

                                                </div>
                                                <div class="row sameTop">
                                                     <div class="col-md-12">
                                                        <span>价格：</span>
                                                        <span>1111万</span>
                                                    </div>
                                                </div>
                                                <div class="row sameTop">
                                                    <span class="sameA">待审核</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div> -->
                                </div>
                                 <!-- 分页 -->
								<div class="admin-main">
									<div class="admin-table-page">
										<div id="pagedCheck" class="page"></div>
									</div>
								</div>
                            </div>

                           <div class="tab-pane " id="obligation">
                                <div class="row " id="waitPay">
                                    
                                </div>
                                 <!-- 分页 -->
								<div class="admin-main">
									<div class="admin-table-page">
										<div id="pagedWaitPay" class="page"></div>
									</div>
								</div>
                            </div>
                            <div class="tab-pane " id="paySure">
                                <div class="row " id="surePay">
                                   <!--   -->
                                </div>
                                 <!-- 分页 -->
								<div class="admin-main">
									<div class="admin-table-page">
										<div id="pagedSurePay" class="page"></div>
									</div>
								</div>
                            </div>
                            
                            <div class="tab-pane" id="signUp">
                                <div class="row " id="waitSign">
                                     <!-- <div class="col-md-3 sameColor" style="margin-left:90px;margin-bottom: 10px;;">
                                        <div class="row">
                                           <div class="col-md-10">
                                               <p class="pp">滨江海创9栋1单元103室</p>
                                           </div>
                                        </div>
                                        <div class="row coco">
                                            <div class="col-md-3" style="margin-left:20px;margin-top:34px;margin-bottom:40px;">
                                                <img src="images/timg.jpg" alt="" class="img-rounded">
                                            </div>
                                            <div class="col-md-7" style="margin-left:30px;margin-top:30px;margin-bottom:40px;">
                                                <div class="row sameTop">
                                                    <div class="col-md-12">
                                                        <span>下单时间：</span>
                                                        <span>2017.1.2</span>
                                                    </div>
                                                  

                                                </div>
                                                <div class="row sameTop">
                                                     <div class="col-md-12">
                                                        <span>价格：</span>
                                                        <span>1111万</span>
                                                    </div>
                                                </div>
                                                <div class="row sameTop">
                                                    <span class="sameA">待签约</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div> -->
                                </div>
                                 <!-- 分页 -->
								<div class="admin-main">
									<div class="admin-table-page">
										<div id="pagedSign" class="page"></div>
									</div>
								</div>
                            </div>
                            <div class="tab-pane" id="signed">
                                <div class="row " id="waitSigned">
                                     <!-- <div class="col-md-3 sameColor" style="margin-left:90px;margin-bottom: 10px;;">
                                        <div class="row">
                                           <div class="col-md-10">
                                               <p class="pp">滨江海创9栋1单元103室</p>
                                           </div>
                                        </div>
                                        <div class="row coco">
                                            <div class="col-md-3" style="margin-left:20px;margin-top:34px;margin-bottom:40px;">
                                                <img src="images/timg.jpg" alt="" class="img-rounded">
                                            </div>
                                            <div class="col-md-7" style="margin-left:30px;margin-top:30px;margin-bottom:40px;">
                                                <div class="row sameTop">
                                                    <div class="col-md-12">
                                                        <span>下单时间：</span>
                                                        <span>2017.1.2</span>
                                                    </div>
                                                  

                                                </div>
                                                <div class="row sameTop">
                                                     <div class="col-md-12">
                                                        <span>价格：</span>
                                                        <span>1111万</span>
                                                    </div>
                                                </div>
                                                <div class="row sameTop">
                                                    <span class="sameA">已签约</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div> -->
                                </div>
                                 <!-- 分页 -->
								<div class="admin-main">
									<div class="admin-table-page">
										<div id="pagedSigned" class="page"></div>
									</div>
								</div>
                            </div>
                            <div class="tab-pane" id="repeal">
                                <div class="row " id="revoke">
                                     <!-- <div class="col-md-3 sameColor" style="margin-left:90px;margin-bottom: 10px;;">
                                        <div class="row">
                                           <div class="col-md-10">
                                               <p class="pp">滨江海创9栋1单元103室</p>
                                           </div>
                                        </div>
                                        <div class="row coco">
                                            <div class="col-md-3" style="margin-left:20px;margin-top:34px;margin-bottom:40px;">
                                                <img src="images/timg.jpg" alt="" class="img-rounded">
                                            </div>
                                            <div class="col-md-7" style="margin-left:30px;margin-top:30px;margin-bottom:40px;">
                                                <div class="row sameTop">
                                                    <div class="col-md-12">
                                                        <span>下单时间：</span>
                                                        <span>2017.1.2</span>
                                                    </div>
                                                  

                                                </div>
                                                <div class="row sameTop">
                                                     <div class="col-md-12">
                                                        <span>价格：</span>
                                                        <span>1111万</span>
                                                    </div>
                                                </div>
                                                <div class="row sameTop">
                                                    <span class="sameA">已撤销</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div> -->
                                </div>
                                 <!-- 分页 -->
								<div class="admin-main">
									<div class="admin-table-page">
										<div id="pagedRevoke" class="page"></div>
									</div>
								</div>
                            </div>
                            <div class="tab-pane" id="refuse">
                                <div class="row " id="myResuse">
                                    <!--  <div class="col-md-3 sameColor" style="margin-left:90px;margin-bottom: 10px;;">
                                        <div class="row">
                                           <div class="col-md-10">
                                               <p class="pp">滨江海创9栋1单元103室</p>
                                           </div>
                                        </div>
                                        <div class="row coco">
                                            <div class="col-md-3" style="margin-left:20px;margin-top:34px;margin-bottom:40px;">
                                                <img src="images/timg.jpg" alt="" class="img-rounded">
                                            </div>
                                            <div class="col-md-7" style="margin-left:30px;margin-top:30px;margin-bottom:40px;">
                                                <div class="row sameTop">
                                                    <div class="col-md-12">
                                                        <span>下单时间：</span>
                                                        <span>2017.1.2</span>
                                                    </div>
                                                  

                                                </div>
                                                <div class="row sameTop">
                                                     <div class="col-md-12">
                                                        <span>价格：</span>
                                                        <span>1111万</span>
                                                    </div>
                                                </div>
                                                <div class="row sameTop">
                                                    <span class="sameA">已拒绝</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div> -->
                                </div>
                                 <!-- 分页 -->
								<div class="admin-main">
									<div class="admin-table-page">
										<div id="pagedRefuse" class="page"></div>
									</div>
								</div>
                            </div>
                    </div>
                </div>
            </div>
          
        </div>

       
    </div> 
    
    
    <script src="static/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="static/js/echarts.js"></script>
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
    <!-- <script src="js/application.js"></script> -->
    <!-- <script src="js/moment.min.js"></script> -->
    <!-- <script src="js/jquery.dataTables.min.js"></script> -->
    <!-- <script src="js/jquery.sortable.js"></script> -->
    <!-- <script type="text/javascript" src="js/jquery.gritter.js"></script> -->
    <script src="static/js/archon.js"></script>
    <script src="static/js/orderForm.js"></script>
    <script type="text/javascript" src="static/js/layer.js"></script>
	<script type="text/javascript" src="static/layui/plugins/layui/layui.js"></script>
    <script>
            $(document).ready(function(){
                $("#demo-navbar .dropdown-menu a").click(function(){
                        var href = $(this).attr("href");
                        $("#tab-list a[href='"+ href +"']").tab("show");

                });
            });
   </script>
  
</body>
</html>
