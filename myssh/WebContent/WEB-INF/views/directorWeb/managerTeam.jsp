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
    <link rel="stylesheet" type="text/css" href="static/css/managerTeam.css" />
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <!-- <link rel="stylesheet" type="text/css" href="laydate/need/laydate.css" /> -->
    <link rel="stylesheet" type="text/css" href="static/css/calendarM.css" />
    <link  rel="icon" href="http://root-12521005170.cossh.myqcloud.com/static/images/titleLogo.png" />
     <style type="text/css">
    	
    	p,span,tr,td,a,li,option,select,button{font-family:"微软雅黑" !important;}
    </style>
    
        
    
</head>
<body >
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
                    	 <!-- <ul style="display:block;border:0;">
                            <li><a href="to_goToManagerMap" style="padding-left:5px;color:#ffffff;font-size: 14px;"><span>地图</span></a></li>
                        </ul> -->
                    </li> 
                     <li><a href="to_goToManagerMap" class="noDrop"><span>门店地图</span></a></li>
                    <li><a href="to_data_analysis_page" class="noDrop"><span>数据分析</span></a></li>
                    <li><a href="to_data_statement_page" class="noDrop"><span>数据报表</span></a></li>
                    <li><a href="to_order_page" class="noDrop"><span>订单</span></a></li>
                    <li><a href="to_manager_team_page" class="noDrop"><span style="color:#46c1de;">团队</span></a></li>
                      <li><a href="to_pCustomerManager" class="noDrop"><span>客户管理</span></a>
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
                    </li><!-- / dropdown -->                
                </ul><!-- / Top right user menu -->
            </div>
            <div id="main-content" >
                <div class="row"  style="margin-top:20px;margin-left:2%;">
                    <div class="col-md-2" style="width:10%;">
                       <select class="form-control" style="padding-left:4px;padding-right:0;width:100%;" id="select_time">
                            <option value="week">最近一周</option>
                            <option value="half_month">最近半月</option>
                            <option value="one_month">最近一月</option>
                            <option value="half_year">最近半年</option>
                            <option value="one_year">最近一年</option>
                        </select>
                    </div> 
                </div>
                <div class="row" style="margin-left:3%;margin-top:24px;">
                    <div class="col-md-9" style="width:76%;">
                        <div class="row listOne ">
                            <div class="col-md-12">
                                <div class="row" style="margin-top:18px;margin-bottom:20px;">
                                    <div class="col-md-3 headTh" >
                                        <div class="row">
                                            <div class="col-md-3" style="text-align:center;line-height:60px;height:60px;width:75px;padding:0;margin:-20px 0 0 0;background:#edf2f9;">
                                                <img src="static/images/jiepai.png" alt="" />
                                            </div>
                                            <div class="col-md-6
                                            " style="text-align:center;">
                                                <span>接访排行</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-2 headTh" style="text-align:center;">
                                        <span>姓名</span>
                                    </div>
                                     <div class="col-md-2 headTh" style="text-align:center;">
                                       <span>接访</span>
                                    </div>
                                     <div class="col-md-2 headTh" style="text-align:center;">
                                       <span>有效接访</span>
                                    </div>
                                     <div class="col-md-3 headTh" style="text-align:center;">
                                       <span>有效接访率</span>
                                    </div>
                                </div>
                                <div class="sameHeight" id="visit_top">
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-top:24px;">
                           <div class="col-md-7 listOne" style="width:64%;">
                               <div class="row" style="margin-top:18px;margin-bottom:20px;">
                                    <div class="col-md-4 headTh" >
                                        <div class="row">
                                            <div class="col-md-2" style="text-align:center;line-height:60px;height:60px;width:30%;padding:0;margin:-20px 0 0 0;background:#edf2f9;">
                                                <img src="static/images/qianTi.png" alt="" />
                                            </div>
                                            <div class="col-md-7" style="text-align:center;width:60%;">
                                                <span>成交排行</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-2 headTh" style="text-align:center;">
                                        <span>姓名</span>
                                    </div>
                                     <div class="col-md-2 headTh" style="text-align:center;">
                                       <span>认购</span>
                                    </div>
                                     <div class="col-md-2 headTh" style="text-align:center;">
                                       <span>签约</span>
                                    </div>
                                     <div class="col-md-2 headTh" style="text-align:center;">
                                       <span>货值</span>
                                    </div>
                                   
                                </div>
                                <div class="sameHeight" id="deal_top">
                                        
                                   </div>
                           </div>
                           <div class="col-md-4 listOne" style="margin-left:3%;width:33%;">
                               <div class="row" style="margin-top:18px;margin-bottom:20px;">
                                   <div class="col-md-2">
                                       <img src="static/images/weiding.png" alt="" />
                                   </div>
                                   <div class="col-md-10">
                                       <span style="font-size:16px;color:#616b88;font-weight:bold;">储客排行</span>
                                   </div>
                               </div>
                               <div class="sameHeightTwo" id="not_reg">
                                  
                               </div>
                           </div>
                        </div>
                    </div>
                    <div class="col-md-2 listOne" style="margin-left:2%;width:21%;">
                       <div class="row" style="margin-top:18px;margin-bottom:10px;">
                            <div class="col-md-2">
                                 <img src="static/images/weiding.png" alt="" />
                            </div>
                            <div class="col-md-10">
                                 <span style="font-size:16px;color:#616b88;font-weight:bold;">团队管理</span>
                            </div>
                        </div>
                        <div class="sameHeightThree" id="agent_info">
                           
                        </div>
                    </div>
                </div>
            </div>
          
        </div>
      
       
    </div> 
    
    
    <script src="static/js/jquery-3.1.1.min.js"></script>
    
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
    <script src="static/js/managerTeam.js"></script>
    
  
</body>
</html>
    