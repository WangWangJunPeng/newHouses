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
    <link rel="stylesheet" type="text/css" href="static/css/orderDetails.css" />
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <!-- <link rel="stylesheet" type="text/css" href="laydate/need/laydate.css" /> -->
    <link rel="stylesheet" type="text/css" href="static/css/calendarM.css" />
    <link  rel="icon" href="http://root-12521005170.cossh.myqcloud.com/static/images/titleLogo.png" />
     <style type="text/css">
    	
    	p,span,tr,td,a,li,option,select,button{font-family:"微软雅黑" !important;}
    	.dingDan a:hover{color:#ff6161 !important;}
    	.dingDan span:hover{color:#ff6161 !important;}
    </style>
    
        
    
</head>
<body >
	<input id="orderNo" type="hidden" value="${orderNo}">
	<input id="buttonSign" type="hidden" value="${buttonSign}">
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
                    
                    <li >
                    	<a href="to_director_page_index" class="noDrop" ><span>今日案场</span></a>
                    	 <!-- <ul style="display:block;border:0;">
                            <li><a href="to_goToManagerMap" style="padding-left:5px;color:#ffffff;font-size: 14px;"><span>地图</span></a></li>
                        </ul> -->
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
            	<div class="row" style="margin-top:14px;">
            		<div class="col-md-4 dingDan">
            			<a href="to_order_page" style="font-size:14px;color:#616b88;" >订单</a><span style="font-size:14px;color:#616b88;">--</span><span style="font-size:14px;color:#616b88;cursor:pointer;">订单详情</span>
            		</div>
            	</div>
              <div class="row" style="margin-left:4%;margin-top:34px;">
              		<div class="col-md-4" style="width:180px;height:36px;background:#ff9666;border-radius:4px;line-height:36px;">
              			<span style="font-size:14px;color:#ffffff;">订单提交人：</span>
              			<span id="orderSubmitMan" style="font-size:14px;color:#ffffff;"></span>
              		</div>
              </div>
              <div class="row" style="margin-left:4%;margin-top:24px;">
                  <div class="col-md-4 listOne oppo" style="width:38%;">
                      <div class="row" style="margin-top:10px;">
                          <div class="col-md-12" style="text-align:center;">
                              <span style="font-size:16px;color:#616b88;">订单状态</span>
                          </div>
                      </div>
                      <section id="cd-timeline" class="cd-container">
                            <div class="cd-timeline-block orderStutas">
                                <div class="cd-timeline-img cd-picture">
                                </div>
                                <div class="cd-timeline-content" style="margin:0;padding:0;text-align:right;background:#f0f4fb;">
                                    <p style="margin:0;padding:6px 6px 0 0;" id="orderStutas"></p>
                                   <p style="margin:0;color:#616b88;padding:0px 6px 0 0;" id="submitOrderTime"></p>
                                    <span class="cd-date" id="submitOrderYear"></span>
                                </div>
                            </div>
                            <div class="cd-timeline-block waitChect">
                                <div class="cd-timeline-img cd-movie">
                                </div>
                                <div class="cd-timeline-content" style="padding:0;text-align:left;background:#f0f4fb;">
                                   <p style="margin:0;padding:6px 6px 0 0;" id="waitChect"></p>
                                   <p style="margin:0;color:#616b88;padding:0px 6px 0 0;" id="waitChectTime"></p>
                                    <span class="cd-date" id="waitChectYear"></span>
                                </div>
                            </div>
                            <div class="cd-timeline-block checked">
                                <div class="cd-timeline-img cd-picture">
                                </div>
                                <div class="cd-timeline-content" style="margin:0;padding:0;text-align:right;background:#f0f4fb;">
                                    <p style="margin:0;padding:6px 6px 0 0;" id="checked"></p>
                                   <p style="margin:0;color:#616b88;padding:0px 6px 0 0;" id="checkedTime"></p>
                                    <span class="cd-date" id="checkedYear"></span>
                                </div>
                            </div>
                            <div class="cd-timeline-block outTime">
                                <div class="cd-timeline-img cd-movie">
                                </div>
                                <div class="cd-timeline-content" style="margin:0;padding:0;text-align:left;background:#f0f4fb;">
                                   <p style="margin:0;padding:6px 6px 0 0;" id="outTime"></p>
                                   <p style="margin:0;color:#616b88;padding:0px 6px 0 0;" id="outTimeTime"></p>
                                    <span class="cd-date" id="outTimeYear"></span>
                                </div>
                            </div>
                            <div class="cd-timeline-block payMoney">
                                <div class="cd-timeline-img cd-picture">
                                </div>
                                <div class="cd-timeline-content" style="margin:0;padding:0;text-align:right;background:#f0f4fb;">
                                   <p style="margin:0;padding:6px 6px 0 0;" id="payMoney"></p>
                                   <p style="margin:0;color:#616b88;padding:0px 6px 0 0;" id="payMoneyTime"></p>
                                    <span class="cd-date" id="payMoneyYear"></span>
                                </div>
                            </div>
                            <div class="cd-timeline-block enterPay">
                                <div class="cd-timeline-img cd-movie">
                                </div>
                                <div class="cd-timeline-content" style="margin:0;padding:0;text-align:left;background:#f0f4fb;">
                                   <p style="margin:0;padding:6px 6px 0 0;" id="enterPay"></p>
                                   <p style="margin:0;color:#616b88;padding:0px 6px 0 0;" id="enterPayTime"></p>
                                    <span class="cd-date" id="enterPayYear"></span>
                                </div>
                            </div>
                            <div class="cd-timeline-block sign">
                                <div class="cd-timeline-img cd-movie">
                                </div>
                                <div class="cd-timeline-content" style="margin:0;padding:0;text-align:right;background:#f0f4fb;">
                                   <p style="margin:0;padding:6px 6px 0 0;" id="sign"></p>
                                   <p style="margin:0;color:#616b88;padding:0px 6px 0 0;" id="signTime"></p>
                                    <span class="cd-date" id="signYear"></span>
                                </div>
                            </div>
                             <div class="cd-timeline-block revoke">
                                <div class="cd-timeline-img cd-movie">
                                </div>
                                <div class="cd-timeline-content" style="margin:0;padding:0;text-align:left;background:#f0f4fb;">
                                   <p style="margin:0;padding:6px 6px 0 0;" id="revoke"></p>
                                   <p style="margin:0;color:#616b88;padding:0px 6px 0 0;" id="revokeTime"></p>
                                    <span class="cd-date" id="revokeYear"></span>
                                </div>
                            </div>
                        </section>
                  </div>
                  <div class="col-md-7 " style="width:58%;margin-left:2%;">
                      <div class="row listOne">
                          <div class="col-md-12" style="margin-bottom:20px;">
                              <div class="row" style="margin-top:16px;">
                                  <div class="col-md-1"  >
                                      <img src="static/images/cin.png" class="img-responsive" alt="Responsive image" style="margin-left:1.5%;">                                    
                                  </div>
                                  <div class="col-md-3">
                                      <span style="font-size:16px;color:#616b88;">客户信息</span>
                                  </div>
                                  <div class="col-md-6 col-md-offset-2" style="text-align:right;">
                                    <div class="row">
                                        <div class="col-md-3 col-md-offset-3" style="text-align:right;">
                                             <span style="font-size:12px;color:#616b88;">订单性质</span>
                                        </div>
                                        <div class="col-md-3" style="text-align:right;">
                                             <input type="radio"  name="buy" id="vOne" style="padding-top:10px;" disabled/>
                                            <label for="vOne" style="margin-left:10px;vertical-align:top ;font-size:12px;color:#616b88;">自购</label>
                                        </div>
                                        <div class="col-md-3" style="text-align:left;">
                                             <input type="radio"  name="buy" id="vTwo" disabled/>
                                            <label  for="vTwo" style="margin-left:10px;vertical-align:top ;font-size:12px;color:#616b88;">代购</label>
                                        </div>
                                    </div>
                      
                                  </div>
                              </div>
                            <div class="row" style="margin-top:20px;border-bottom:1px solid #f1f5f7;padding-bottom:14px;">
                                <div class="col-md-1" style="margin-left:1.5%">
                                    <img src="static/images/buyName.png" class="img-responsive" alt="Responsive image" style="margin-left:1.5%;margin-top:4px;">
                                </div>
                                <div class="col-md-3">
                                    <span style="font-size:14px;color:#616b88;">认购姓名</span>
                                </div>
                                <div class="col-md-3 col-md-offset-4" style="text-align:right;padding-right:0px;">
                                    <span style="font-size:12px;color:#616b88;" id="enterBuyName"></span>
                                </div>
                            </div>
                              <div class="row" style="margin-top:14px;">
                                <div class="col-md-1" style="margin-left:1.5%">
                                     <img src="static/images/idShen.png" class="img-responsive" alt="Responsive image" style="margin-left:1.5%;margin-top:4px;">
                                </div>
                                <div class="col-md-3">
                                    <span style="font-size:14px;color:#616b88;">身份证号码</span>
                                </div>
                                <div class="col-md-3 col-md-offset-4" style="text-align:right;padding-right:0px;">
                                    <span style="font-size:12px;color:#616b88;" id="idCarkNum"></span>
                                </div>
                            </div>
                             <div class="row" style="margin-top:28px;">
                                  <div class="col-md-1"  >
                                      <img src="static/images/dingjin.png" class="img-responsive" alt="Responsive image" style="margin-left:1.5%;">
                                      
                                  </div>
                                  <div class="col-md-3">
                                      <span style="font-size:16px;color:#616b88;">订金与条款</span>
                                  </div>
                                  <div class="col-md-6 col-md-offset-2" style="text-align:right;">
                                    <div class="row">
                                        <div class="col-md-3 col-md-offset-3" style="text-align:right;">
                                             <span style="font-size:12px;color:#616b88;">支付方式</span>

                                        </div>
                                        <div class="col-md-3" style="text-align:right;">
                                             <input type="radio"  name="fu" id="up" style="padding-top:10px;" disabled/>
                                            <label for="up" style="margin-left:10px;vertical-align:top ;font-size:12px;color:#616b88; ">线上</label>
                                        </div>
                                        <div class="col-md-3" style="text-align:left;">
                                             <input type="radio"  name="fu" id="down" disabled/>
                                            <label  for="down" style="margin-left:10px;vertical-align:top ;font-size:12px;color:#616b88;">线下</label>
                                        </div>
                                    </div>
                      
                                  </div>
                              </div>
                            <div class="row" style="margin-top:20px;border-bottom:1px solid #f1f5f7;padding-bottom:14px;">
                                <div class="col-md-1" style="margin-left:1.5%;">
                                      <img src="static/images/dingShu.png" class="img-responsive" alt="Responsive image" style="margin-left:1.5%;margin-top:4px;">
                                </div>
                                <div class="col-md-3">
                                    <span style="font-size:14px;color:#616b88;">订金数额</span>
                                </div>
                                <div class="col-md-3 col-md-offset-4" style="text-align:right;padding-right:0px;">
                                    <span style="font-size:12px;color:#616b88;" id="haveToPay"></span>
                                </div>
                            </div>
                              <div class="row" style="margin-top:14px;">
                                <div class="col-md-1" style="margin-left:1.5%;">
                                     <img src="static/images/yiqian.png" class="img-responsive" alt="Responsive image" style="margin-left:1.5%;margin-top:4px;">
                                </div>
                                <div class="col-md-3">
                                    <span style="font-size:14px;color:#616b88;">已签约条款</span>
                                </div>
                                <div class="col-md-3 col-md-offset-4" style="text-align:right;padding-right:0px;">
                                    <input type="radio"  name="tiao" id="tiao" checked/>
                                    <label  for="tiao" style="margin-left:10px;vertical-align:top ;font-size:12px;color:#616b88;">条款</label>
                                </div>
                            </div>
                          </div>
                      </div>
                      <div class="row " style="margin-top:20px;">
                        <div class="col-md-6  listOne" style="width:56%">
                            <div class="row" style="background:#ff6c60;border-radius:6px;line-height:50px;">
                                <div class="col-md-2" style="height:50px;background:#ec6053;border-radius:6px;">
                                     <img src="static/images/housesXin.png" class="img-responsive" alt="Responsive image" style="margin-left:12px;margin-top:14px;">
                                </div>
                                <div class="col-md-4">
                                    <span style="font-size:16px;color:#ffffff;">房源信息</span>
                                </div>
                            </div>
                            <div class="row" style="margin-top:36px;margin-bottom:35px;">
                                <div class="col-md-2 " style="width:20%;margin-left:30%;">
                                    <img id="housePic" src="static/images/cin.png" alt="" style="width:60px;height:60px;"/>
                                </div>
                                <div class="col-md-4" style="width:40%;">
                                    <div style="margin-top:8px;">
                                        <span style="font-size:12px;color:#616b88;">户型</span>
                                    </div>
                                    <div style="margin-top:8px;">
                                        <span style="font-size:12px;color:#616b88;" id="houseTyple">三室两厅</span>
                                    </div>
                                </div>
                            </div>
                            <div class="row" style="background:#f0f4fb;">
                                <div class="col-md-4" style="text-align:center;border-right:1px solid #e5edf9;height:90px">
                                    <div style="margin-top:20px;">
                                        <span style="font-size:12px;color:#616b88;" id="houseId"></span>
                                    </div>
                                    <div style="margin-top:10px;">
                                        <span style="font-size:12px;color:#616b88;">房源ID</span>
                                    </div>
                                </div>
                                <div class="col-md-4" style="text-align:center;border-right:1px solid #e5edf9;height:90px">
                                    <div style="margin-top:20px;">
                                        <span  style="font-size:12px;color:#616b88;display:inline-block;width:80px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;
                                        " id="houseCome"></span>
                                    </div>
                                    <div style="margin-top:10px;">
                                        <span style="font-size:12px;color:#616b88;">房源</span>
                                    </div>
                                </div>
                                <div class="col-md-4" style="text-align:center;border-right:1px solid #e5edf9;height:90px">
                                    <div style="margin-top:20px;">
                                        <span style="font-size:12px;color:#616b88;" id="willSellNo"></span>
                                    </div>
                                    <div style="margin-top:10px;">
                                        <span style="font-size:12px;color:#616b88;">预售证号</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-5 listOne " style="width:42%;margin-left:2%">
                             <div class="row" style=";border-radius:6px;line-height:50px;">
                                <div class="col-md-2" style="height:50px;background:#f3f5fb;border-radius:6px;">
                                     <img src="static/images/wayer.png" class="img-responsive" alt="Responsive image" style="margin-left:6px;margin-top:14px;">
                                </div>
                                <div class="col-md-6">
                                    <span style="font-size:16px;color:#616b88;">优惠与结算方式</span>
                                </div>
                            </div>
                            <div class="row" style="margin-top:18px;border-bottom:1px dashed  #f1f5f7;padding-bottom:16px;">
                                 <div class="col-md-2" style="width:20%;">
                                     <div style="width:20px;height:20px;border-radius:6px;background:#24c5a4;">
                                         
                                     </div>
                                </div>
                                <div class="col-md-3" style="width:30%;">
                                    <span style="font-size:12px;color:#616b88;">价格</span>
                                </div>
                                <div class="col-md-3 " style="text-align:right;padding-right:0px;width:30%;margin-left:10%;">
                                    <span style="font-size:12px;color:#616b88;" id="listPrice"></span>
                                </div>
                            </div>
                            <div class="row" style="margin-top:18px;border-bottom:1px dashed  #f1f5f7;padding-bottom:16px;">
                                 <div class="col-md-2" style="width:20%;">
                                     <div style="width:20px;height:20px;border-radius:6px;background:#ffc941;">
                                         
                                     </div>
                                </div>
                                <div class="col-md-3" style="width:30%;">
                                    <span style="font-size:12px;color:#616b88;">优惠条款</span>
                                </div>
                                <div class="col-md-3 " style="text-align:right;padding-right:0px;width:30%;margin-left:10%;">
                                    <span style="font-size:12px;color:#616b88;display:inline-block;width:80px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;" class="hideWord" id="youhui"></span>
                                </div>
                            </div>
                             <div class="row" style="margin-top:15px;border-bottom:1px dashed  #f1f5f7;padding-bottom:16px;">
                                 <div class="col-md-2" style="width:20%;">
                                     <div style="width:20px;height:20px;border-radius:6px;background:#f05050;">
                                         
                                     </div>
                                </div>
                                <div class="col-md-3" style="width:30%;">
                                    <span style="font-size:12px;color:#616b88;">认购价格</span>
                                </div>
                                <div class="col-md-3 " style="text-align:right;padding-right:0px;width:30%;margin-left:10%;">
                                    <span style="font-size:12px;color:#616b88;" id="enterBuyPrice"></span>
                                </div>
                            </div>
                            <div class="row" style="margin-top:18px;border-bottom:1px dashed  #f1f5f7;padding-bottom:19px;">
                                 <div class="col-md-2" style="width:20%;">
                                     <div style="width:20px;height:20px;border-radius:6px;background:#988fd8;">
                                         
                                     </div>
                                </div>
                                <div class="col-md-3" style="width:30%;">
                                    <span style="font-size:12px;color:#616b88;">结算方式</span>
                                </div>
                                <div class="col-md-3 col-md-offset-3" style="text-align:right;padding-right:0px;width:30%;margin-left:10%;">
                                    <span style="font-size:12px;color:#616b88;" id="jieSuanStyle"></span>
                                </div>
                            </div>
                        </div>
                      </div>
                  </div>
              </div>
              <div class="row directorCheck" style="margin-top:60px;">
                  <div class="col-md-4 col-md-offset-2" style="text-align:right;">
                      <button type="button" style="width:130px;height:36px;line-height:34px;font-size:14px;color:#ffffff;border:0;border-radius:4px;background:#46c1de;" onclick="checkOrder()">同意</button>
                  </div>
                  <div class="col-md-4">
                      <button type="button" class="refuse" style="width:130px;height:36px;line-height:34px;font-size:14px;color:#999999;border:0;border-radius:4px;background:#d6dfe6;">拒绝</button>
                  </div>
              </div>
            </div>
          
        </div>
        <div class="addDiv" >
                <div class="row">
                    <div class="col-md-12 colmd">
                        <img src="static/images/newDe.png" class="img-responsive  slideRight" alt="Responsive image">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <p style="font-size:14px;color:#616b88;margin-left:40px;">请选择拒绝原因</p>
                    </div>          
                </div>
                <div class="row" style="margin-top:50px;">
                    <div class="col-md-3 col-md-offset-3" style="">
                        <input type="radio"  name="reson" id="fir" style="padding-top:10px;" value="价格低" checked="checked"/>
                         <label for="fir" style="margin-left:10px;vertical-align:top ;font-size:14px;color:#616b88; ">价格低</label>
                    </div>
                </div>
                 <div class="row" style="margin-top:24px;">
                    <div class="col-md-3 col-md-offset-3" style="">
                        <input type="radio"  name="reson" id="sec" style="padding-top:10px;" value="不想卖"/>
                         <label for="sec" style="margin-left:10px;vertical-align:top ;font-size:14px;color:#616b88; ">不想卖</label>
                    </div>
                </div>
                 <div class="row" style="margin-top:24px;">
                    <div class="col-md-3 col-md-offset-3" style="">
                        <input type="radio"  name="reson" id="thi" style="padding-top:10px;" value="不知道"/>
                         <label for="thi" style="margin-left:10px;vertical-align:top ;font-size:14px;color:#616b88; ">不知道</label>
                    </div>
                </div>
                <div class="row" style="margin-top:36px;">
                     <div class="col-md-10 col-md-offset-2" style="">
                        <textarea id="fullR" class="form-control" rows="3" style="resize:none;width:300px;height:140px;border:1px solid #d6dfe6;background:#f0f4fb;"></textarea>
                    </div>
                </div>
                 <div class="row" style="margin-top:50px;">
                     <div class="col-md-6 col-md-offset-4" style="">
                        <button type="button"  style="width:180px;height:36px;line-height:34px;font-size:14px;color:#616b88;border:0;border:1px solid #d6dfe6;border-radius:4px;background:#f0f4fb;" onclick="refuseOrder()">确定</button>
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
    <script src="static/js/orderDetails.js"></script>
    
  
</body>
</html>
    