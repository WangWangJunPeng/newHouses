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
    <link rel="stylesheet" type="text/css" href="static/css/managerHome.css" />
     <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>

    <link rel="stylesheet" type="text/css" href="static/css/calendarM.css" />
    <!-- <link  rel="icon" href="http://root-12521005170.cossh.myqcloud.com/static/static/images/titleLogo.png"  /> -->
  <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=7d1ed249eb6d3503e90179890468dd74&plugin=AMap.Autocomplete,AMap.PlaceSearch"></script>
   <style type="text/css">
    	
    	p,span,tr,td,a,li,option,select,button{font-family:"微软雅黑" !important;}
    </style>
</head>
<body>
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
                   
  
                    <li><a href="to_director_page_index" class="noDrop active" ><span style="color:#46c1de;">今日案场</span></a>
                    	  <!-- <ul style="display:block;border:0;"> -->
                        <!-- </ul> -->
                    </li> 
                    <li><a href="to_goToManagerMap" class="noDrop"><span>门店地图</span></a></li>                    
                    <li><a href="to_data_analysis_page" class="noDrop"><span>数据分析</span></a></li>
                    <li><a href="to_data_statement_page" class="noDrop"><span>数据报表</span></a></li>
                    <li><a href="to_order_page" class="noDrop"><span>订单</span></a></li>
                    <li><a href="to_manager_team_page" class="noDrop"><span>团队</span></a></li>
                    <li>
                    	<a href="to_pCustomerManager" class="noDrop"><span>客户管理</span></a>
                        <ul style="display:block;border:0;">
                            <li><a href="customerAnalyze" style="padding-left:5px;color:#ffffff;font-size: 14px;"><span>客户分析</span></a></li>
                        </ul>	
                    </li>
                   <li><a href="to_fensales_introduce" class="noDrop"><span>案场设置</span></a></li>
                   <li><a href="manage_toListType" class="noDrop"><span>房源管理</span></a></li>                
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
                        </ul>
                    </li><!-- /dropdown -->
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
            <div id="main-content"  >
                <div class="row" style="display:none;" >
                    <div class="col-md-1 colmd">
                       <select class="form-control" id="timeMoment">
                       		  <option value="">请选择时间</option>
                              <option value="oneWeek">最近一周</option>
                              <option value="halfMonth">最近半月</option>
                              <option value="oneMonth">最近一月</option>
                              <option value="halfYear">最近半年</option>
                              <option value="oneYear">最近一年</option>
                        </select>
                    </div> 
                </div> 
                <div class="row kPic" >
                    <div class="col-md-3 cardPicture" style="width:18%;background:url(static/images/top1.png);background-size:100% 100%;height:112px;line-height:30px;margin-left:0.8%">

                       <!-- <img src="static/images/top1.png" class="img-responsive active" alt="Responsive image"> -->
                       <div class="row everyPic" style="">
                       	 <div class="col-md-12">
                       	 	<p  class="num" ><span id="totalVisitCount"></span><span style="font-size:14px;margin-left:6px;">组</span></p>
                            <p  class="namePic">接访</p>
                       	 </div>
                           
                       </div>

                    </div>
                    <div class="col-md-3 cardPicture" style="width:18%;margin-left:1.8%;background:url(static/images/top2.png);background-size:100% 100%;height:112px;line-height:30px;">
                       <!-- <img src="static/images/top2.png" class="img-responsive" alt="Responsive image"> -->
                        <div class="row everyPic" style="">
                        	<div class="col-md-12">
                        		<p class="num" ><span id="totalSaveCustomerCount"></span><span style="font-size:14px;margin-left:6px;">组</span></p>
                           		<p class="namePic">储客</p>
                        	</div>
                           
                       </div>
                    </div>
                    <div class=" col-md-3 cardPicture" style="width:18%;margin-left:1.8%;background:url(static/images/top3.png);background-size:100% 100%;height:112px;line-height:30px;">
                       <!--  <img src="static/images/top3.png" class="img-responsive" alt="Responsive image"> -->
                         <div class="row everyPic" style="">
                         	<div class="col-md-12">
                         		 <p  class="num" ><span id="totalEnterCount"></span><span style="font-size:14px;margin-left:6px;">套</span></p>
	                          		<p class="namePic">下定</p>
                         	
                         	</div>
	                           
                       </div>
                    </div>
                     <div class="col-md-3 cardPicture" style="width:18%;margin-left:1.8%;background:url(static/images/top4.png);background-size:100% 100%;height:112px;line-height:30px;"> 
                         <!-- <img src="static/images/top4.png" class="img-responsive" alt="Responsive image"> -->
                          <div class="row everyPic" style="">
                          	<div class="col-md-12">
                          		<p  class="num" ><span id="totalSignCount"></span><span style="font-size:14px;margin-left:6px;">套</span></p>
		                         <p class="namePic">签约</p>
                          	</div>
		                           
                       </div>
                    </div>
                    <div class=" col-md-3 cardPicture" style="width:18%;margin-left:1.8%;background:url(static/images/baobei.png);background-size:100% 100%;height:112px;line-height:30px;">
                       <!--  <img src="static/images/top3.png" class="img-responsive" alt="Responsive image"> -->
                         <div class="row everyPic" style="">
                         	<div class="col-md-12">
                         		 <p  class="num" ><span id="totalBaoBei"></span><span style="font-size:14px;margin-left:6px;">组</span></p>
	                          		<p class="namePic">报备</p>
                         	
                         	</div>
	                           
                       </div>
                    </div>
                    <input type="hidden" id="cardNumber"/>
                </div>


                <div class="row list">
                    <div class="col-md-6 ">
                        <div class="row listOne">
                            <div class="col-md-12">
                                <div class="row">
                                    <div class="col-md-3">
                                        <p class="jie" id="topLine">内场热力图</p>
                                    </div>
                                     
                                 </div>   
                                <div class="row">
                                    <div class="col-md-12">
                                         <div id="main" style="height:400px;max-width:100%;"></div>
                                    </div>
                                   
                                </div>                          
                                    
                            </div>
                        </div>
                       
                            <div class="row listOne bin">
                                
                                    <div class="col-md-4 mainOne" style="text-align:center;width:33%;;">
                                        <div id="mainOne" style="height:210px;"></div>
                                    </div>
                                    <div class="col-md-4 mainTwo " style="text-align:center;width:33%;;">
                                      <div id="mainTwo" style="height:210px;" ></div>
                                    </div>
                                    <div class="col-md-4 mainThree" style="text-align:center;width:33%;;">
                                       <div id="mainThree" style="height:210px;"></div>
                                    </div>
                                  
                            
                        </div>
                    </div>
                    <div class="col-md-5 " style="margin-left:1.5%;width:48.5%;">
                        <div class="row listOne" style="width:100%;">
                             <div class="col-md-12">
                                <div class="row">
                                    <div class="col-md-4">
                                        <p class="jie">外场热力图</p>
                                    </div>
                                     
                                 </div>  
                                 <div class="row">
                                    <div class="col-md-12 shareMaper" style="height:630px;">
                                        <div id="container"   style="height:580px;width:90%;margin-left:5%;margin-top:20px;"></div>
                                     </div>
                                 </div>
                                 </div>
                          
                        </div>
                    </div>
                </div>
                
                <div class="row  pan">
                    <div class="col-md-3 listOne " style="width:33%;">
                        <div class="row">
                            <div class="col-md-12" id="gu">
                                <div class="row">
                                    <div class="col-md-6">
                                        <p class="jie">顾问排行</p>
                                    </div>
                                    <div class="col-md-3 col-md-offset-3" style="text-align:right;margin-top:20px;">
                                        <!-- <ul class="myUl">
                                            <li class="guPan" ><a href="###" title="接访" style="background:#23b7e5;"></a></li>
                                            <li  class="guPan" ><a href="###" title="下定" style="background:#d2d2d2;"></a></li>
                                            <li class="guPan" ><a href="###" title="签约" style="background:#d2d2d2;"></a></li>
                                        </ul> --> 
                                        <img alt="" src="static/images/blueLeft.png" style="margin-right:10px;cursor:pointer;" id="leftHead">
                                        <img alt="" src="static/images/blueRight.png "  style="cursor:pointer;" id="rightHead">
                                    </div>
                                </div>
                                
                                   
                               <!--  <div class="jieFang everyCon sameHeight" >
                                   
                                </div>
                                <div class="renGou everyCon sameHeight" style="display:none;">
                                    
                                </div>
                                
                                <div class="qianyue everyCon sameHeight" style="display:none;">
                                   
                                </div> -->

                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 listOne " style="margin-left:1%;width:32%;">
                        <div class="row">
                             <div class="col-md-12" id="shop">
                                <div class="row">
                                    <div class="col-md-6">
                                        <p class="jie">门店排行</p>
                                    </div>
                                    <div class="col-md-3 col-md-offset-3" style="text-align:right;margin-top:20px;">
                                         <img alt="" src="static/images/blueLeft.png" style="margin-right:10px;cursor:pointer;" id="otherLeftHead">
                                        <img alt="" src="static/images/blueRight.png "  style="cursor:pointer;" id="otherRightHead">
                                    </div>
                                </div>
                               <!--  <div class="yiBao twoCon sameHeight" >
                                    
                                </div> -->
                                <!-- <div class="baoFang twoCon sameHeight"  style="display:none;">
                                    
                                </div> -->
                            </div>
                            
                        </div>
                    </div>
                    <div class="col-md-3 listOne " style="margin-left:1%;width:32%;">
                        <div class="row">
                             <div class="col-md-12">
                                <div class="row">
                                    <div class="col-md-6">
                                        <p class="jie">货值金额</p>
                                    </div>
                                   <!--  <div class="col-md-3 col-md-offset-6">
                                        <ul class="myUl">
                                            <li class="shopPan"><a href="###" title="已报备" ></a></li>
                                            <li  class="shopPan"><a href="###" title="报备已到访"></a></li>
                                            <li ><a href="###"  ></a></li>
                                        </ul>
                                    </div> -->
                                </div>
                                    <div class="sameHeight" >
                                        <div class="row peopleNum ">
	                                        <div class="col-md-4" style="width:70%;">
	                                            <div class="row">
	                                             
	                                                <div class="col-md-12" style="margin-top:8px;">
	                                                    <span>认购锁定房源货值</span>
	                                                </div>
	                                            </div>
	                                           
	                                           
	                                        </div>
	                                        <div class="col-md-5 " style="margin-top:8px;text-align:right;width:30%;">
	                                           
	                                            <span id="enterBuyClockHouse"></span>
	                                            
	                                        </div>
                                    	</div>
                                    	<div class="row peopleNum ">
	                                        <div class="col-md-4" style="width:70%;">
	                                            <div class="row">
	                                             
	                                                <div class="col-md-12" style="margin-top:8px;">
	                                                    <span>认购到款金额</span>
	                                                </div>
	                                            </div>
	                                           
	                                           
	                                        </div>
	                                        <div class="col-md-5  " style="margin-top:8px;text-align:right;width:30%;">
	                                           
	                                            <span id="enterBuyGetMoney"></span>
	                                            
	                                        </div>
                                    	</div> 
                                    	<div class="row peopleNum ">
	                                        <div class="col-md-4"  style="width:70%;">
	                                            <div class="row">
	                                             
	                                                <div class="col-md-12" style="margin-top:8px;">
	                                                    <span>已签约房源货值</span>
	                                                </div>
	                                            </div>
	                                           
	                                           
	                                        </div>
	                                        <div class="col-md-5 " style="margin-top:8px;width:30%;text-align:right;">
	                                           
	                                            <span id="sign"></span>
	                                            
	                                        </div>
                                    	</div> 
                                    	<div class="row peopleNum ">
	                                        <div class="col-md-4" style="width:70%;">
	                                            <div class="row">
	                                             
	                                                <div class="col-md-12" style="margin-top:8px;">
	                                                    <span>待签约房源货值</span>
	                                                </div>
	                                            </div>
	                                           
	                                           
	                                        </div>
	                                        <div class="col-md-5 " style="margin-top:8px;width:30%;text-align:right;">
	                                           
	                                            <span id="waitSign"></span>
	                                            
	                                        </div>
                                    	</div> 
                                    	<div class="row peopleNum ">
	                                        <div class="col-md-4" style="width:70%;">
	                                            <div class="row">
	                                             
	                                                <div class="col-md-12" style="margin-top:8px;">
	                                                    <span>门店佣金</span>
	                                                </div>
	                                            </div>
	                                           
	                                           
	                                        </div>
	                                        <div class="col-md-5" style="margin-top:8px;width:30%;text-align:right;">
	                                           
	                                            <span id="shopsMoney"></span>
	                                            
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
    <script src="static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="static/js/calendarM.js"></script> 
    <script src="static/js/archon.js"></script>
    <script src="static/js/managerHome.js"></script>
  
 


<!--      <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=7d1ed249eb6d3503e90179890468dd74&plugin=AMap.Autocomplete,AMap.PlaceSearch"></script>
 -->
  
</body>
</html>
