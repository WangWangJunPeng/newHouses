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
    <link rel="stylesheet" type="text/css" href="static/css/clientManagement.css" />
  

    <link rel="stylesheet" type="text/css" href="static/css/calendarM.css" />
    <!-- <link  rel="icon" href="http://root-12521005170.cossh.myqcloud.com/static/static/static/images/titleLogo.png"  /> -->
 	<link rel="stylesheet" href="static/css/layui.css"  />
 	 <script src="static/js/jquery-3.1.1.min.js"></script>
   <script src="static/js/layer.js"></script>
   <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/plugins/layui/layui.js"></script> 
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/begtable.css" />
   <style type="text/css">
      
      p,span,tr,td,a,li,option,select,button{font-family:"微软雅黑" !important;}
      .layui-laypage .layui-laypage-curr .layui-laypage-em{
      	    background-color: #46c1de !important;
      
      }
      #maskBox{display:none;background-color:#fafafa;opacity:0.3;position:absolute;right:0px;top:50px;width:1620px;height:1000px;z-index:20;}
      #waiting{display:none;background-color:#fafafa;opacity:0.3;position:absolute;top:50px;right:0px;z-index:999;width:1620px;height:1000px;line-height:1000px;text-align:center;}
      
      /* @media screen and (min-width:2000px) and (max-width:1920px){
			 
	  } */
    </style>
</head>
<body>
	<div id="maskBox"></div>
	<div id="waiting"> 
    	<p style="z-index:1000;"><img alt="" src="static/images/5-1503130Q911.gif"></p>
    </div>
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
                <div class="row ">
                    <div class="col-md-4 topSearch">
                        <div class="row ">
                          <div class="col-md-2" style="margin:0;padding:0;width:10%;height:34px;line-height: 34px;text-align:center;">
                             <img src="static/images/mSearch.png" > 
                          </div>
                          <div class="col-md-8" style="margin:0;padding:0;width:68%;height:32px;line-height: 34px;">
                              <input type="text" id="searchCondition" style="width:100%;height:100%;line-height: 32px;border:0;outline:0;padding-left:10px;font-size:12px;color:#616b88;" placeholder="请输入客户姓名或手机号"/>
                          </div>
                          <div class="col-md-2" style="margin:0;padding:0;width:22%;">
                              <button style="border:0;width:100%;height:36px;background:#46c1de;color:#ffffff;font-size:14px;" id="searchBtn">搜索</button>
                          </div>
                        </div>
                          
                    </div>
                    <div class="col-md-2 topSelect">
                      <select name="" id="guiShuChange" style="height:34px;line-height:34px;width:100%;font-size:14px;color:#616b88;">
                        <option value="0">请选择归属</option>
                        <option value="1">调整客户归属</option>
                        <option value="2">调整顾问归属</option>
                      </select>

                    </div>
                </div>
                <div class="row ">
                    <div class="col-md-12 listOne" style="width:92%;margin-left:4%;margin-top:20px;">
                        <div class="row allBox" style="padding-top:20px;padding-bottom:20px;border-bottom:1px solid #dde1e8;">
                            <div class="col-md-2" style="text-align:center;position:relative;width:9%;padding:0;margin-top:5px;">
                                <span class="shaixuan">条件筛选</span>
                                <b style="font-size: 14px;color:#616b88;position:relative;top:-2px;">&gt;</b>
                            </div>
                            <div class="col-md-2 cuBox" style="position:relative;width:16%;height:30px;line-height:30px;border:1px solid #dde1e8;border-radius:4px;padding:0;display:none;">
                                <span class="same " style="width:40%;position:relative;top:-8px;left:2%;">客户管理：</span>
                                <span class="same customsTop" style="width:45%;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;"></span>
                                  <span  style="display: inline-block;position:relative;top:-9px;width:5%;cursor: pointer;"><img src="static/images/newCha.png" alt="" class="deleteBox"/></span>
                            </div>
                            <div class="col-md-2 visitBox" style="position:relative;width:16%;height:30px;line-height:30px;border:1px solid #dde1e8;border-radius:4px;margin-left:1%;padding:0;display:none;">
                                <span class="same" style="width:40%;position:relative;top:-8px;margin-left:2%;">来访次数：</span>
                                <span class="same visitTop" style="width:45%;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;"></span>
                                 <span style="display: inline-block;position:relative;top:-9px;width:5%;cursor: pointer;"><img src="static/images/newCha.png" alt="" class="deleteBoxTwo"/></span>
                            </div>
                            <div class="col-md-2 guBox" style="position:relative;width:16%;height:30px;line-height:30px;border:1px solid #dde1e8;border-radius:4px;margin-left:1%;padding:0;display:none;">
                                <span class="same" style="width:40%;position:relative;top:-8px;margin-left:2%;">归属顾问：</span>
                                <span class="same guTop" style="width:45%;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;">毛逍遥、李强强</span>
                                  <span style="display: inline-block;position:relative;top:-9px;width:5%;cursor: pointer;"><img src="static/images/newCha.png" alt="" class="deleteBoxThree"/></span>
                            </div>
                            <div class="col-md-2 biaoBox" style="position:relative;width:16%;height:30px;line-height:30px;border:1px solid #dde1e8;border-radius:4px;margin-left:1%;padding:0;display:none;">
                                <span class="same " style="width:40%;position:relative;top:-8px;margin-left:2%;">客户标签：</span>
                                <span class="same biaoTop" style="width:45%;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;">老师、公务员</span>
                                <span style="display: inline-block;position:relative;top:-9px;width:5%;cursor: pointer;"><img src="static/images/newCha.png" alt="" class="deleteBoxFour"/></span>
                            </div>
                        </div>
                        <div class="row borderKung" style="padding-top:10px;padding-bottom:10px;border-bottom:1px dashed #dde1e8;">
                          <div class="col-md-1" style="width:10%;padding:0;text-align:center;margin-top:6px;">
                            <span style="font-size: 14px ;color:#616b88;">客户状态：</span>
                          </div>
                          <div class="col-md-9 defaultStatus" style="width:76%;padding:0;margin-top:6px;">
                                <div class="col-md-1" style="width:10%;">
                                    <span class="customStatus" data-value="1">到访</span>
                                </div>
                                 <div class="col-md-1" style="width:10%;">
                                   <!-- <input type="radio" name="custatus" id="c1" style="display:none;"/>
                                    <label for="c1" class="mo">认购</label> -->
                                    <span class="customStatus" data-value="2">认购</span>
                                </div>
                                <div class="col-md-1" style="width:10%;">
                                 <span class="customStatus" data-value="3">付款</span>
                                </div>
                                <div class="col-md-1" style="width:10%;">
                                   <span class="customStatus" data-value="4">签约</span>
                                </div>
                                <div class="col-md-1" style="width:12%;">
                                  <span class="customStatus" data-value="5">认购否决</span>
                                </div>
                                <div class="col-md-1" style="width:10%;">
                                  <span class="customStatus" data-value="6">退款</span>
                                </div>
                                <div class="col-md-1" style="width:10%;">
                                  <span class="customStatus" data-value="7">撤单</span>
                                </div>
                                

                          </div>
                          <div class="col-md-9 allCheck" style="width:76%;padding:0;margin-top:6px;display:none;">
                            <div class="row">
                                <div class="col-md-1" style="width:10%;position:relative">
                                    <input type="checkbox" name="custatus" id="c0"  value="1" />
                                    <label for="c0" class="mo" style="font-weight: 100;position:relative;top:-2px;;">到访</label>                                   
                                </div>
                                 <div class="col-md-1" style="width:10%;position:relative">
                                   <input type="checkbox" name="custatus" id="c1"  value="2"/>
                                    <label for="c1" class="mo" style="font-weight: 100 ;position:relative;top:-2px;">认购</label>
                                    
                                </div>
                                <div class="col-md-1" style="width:10%;position:relative">
                                  <input type="checkbox" name="custatus" id="c2" value="3"/>
                                    <label for="c2" class="mo" style="font-weight: 100;position:relative;top:-2px;">付款</label>
                                </div>
                                <div class="col-md-1" style="width:10%;position:relative">
                                    <input type="checkbox" name="custatus" id="c3" value="4"/>
                                    <label for="c3" class="mo" style="font-weight: 100;position:relative;top:-2px;">签约</label>
                                </div>
                                <div class="col-md-1" style="width:12%;position:relative">
                                   <input type="checkbox" name="custatus" id="c4" value="5"/>
                                    <label for="c4" class="mo" style="font-weight: 100 ;position:relative;top:-2px;">认购否决</label>
                                </div>
                                <div class="col-md-1" style="width:10%;position:relative">
                                   <input type="checkbox" name="custatus" id="c5" value="6"/>
                                    <label for="c5" class="mo" style="font-weight: 100;position:relative;top:-2px;">退款</label>
                                </div>
                                <div class="col-md-1" style="width:10%;position:relative">
                                   <input type="checkbox" name="custatus" id="c6" value="7"/>
                                    <label for="c6" class="mo" style="font-weight: 100;position:relative;top:-2px;">撤单</label>
                                </div>
                            </div>
                            <div class="row" style="margin-top:10px;">
                                <div class="col-md-2" style="width:40%;margin-left:30%;">
                                  <button class="btnAll btnOne" disabled="disabled" id="cuSure">确认</button>
                                  <button class="btnAll btnTwo deleteCheck">取消</button>
                                </div>
                               
                            </div> 
                                

                          </div>
                          <div class="col-md-2 cuAllcheck" style="width:6%;border:1px solid #d6dfe6;padding:0;height:32px;line-height:32px;border-radius:4px;margin-left:6%;cursor: pointer;">
                              <img src="static/images/addHao.png" alt="" style="margin-left:20%;"/>    
                              <span style="font-size: 14px ;color:#616b88;margin-left:8%;">多选</span>
                          </div>    
                         
                        </div>
                        <div class="row visitKuang" style="padding-top:10px;padding-bottom:10px;border-bottom:1px dashed #dde1e8;">
                          <div class="col-md-1" style="width:10%;padding:0;text-align:center;margin-top:6px;">
                            <span style="font-size: 14px ;color:#616b88;">来访次数：</span>
                          </div>
                          <div class="col-md-9 visitAll" style="width:76%;padding:0;margin-top:6px;display:none;">
                             <div class="row">
                            
                          
                               <div class="col-md-1" style="width:12%;position:relative">
                                   <input type="checkbox" name="visitNum" id="v0"  value="1"/>
                                    <label for="v0" class="mo" style="font-weight: 100 ;position:relative;top:-2px;">1次</label>
                                </div>
                                <div class="col-md-1" style="width:10%;position:relative">
                                   <input type="checkbox" name="visitNum" id="v1" value="2"/>
                                    <label for="v1" class="mo" style="font-weight: 100;position:relative;top:-2px;">2次</label>
                                </div>
                                <div class="col-md-1" style="width:14%;position:relative">
                                   <input type="checkbox" name="visitNum" id="v2" value="3"/>
                                    <label for="v2" class="mo" style="font-weight: 100;position:relative;top:-2px;">3次及以上</label>
                                </div>
                              </div>
                              <div class="row" style="margin-top:10px;">
                                <div class="col-md-2" style="width:40%;margin-left:30%;">
                                  <button class="btnAll btnOne" disabled="disabled" id="visitSure">确认</button>
                                  <button class="btnAll btnTwo visitDelete">取消</button>
                                </div>
                               
                            </div> 
                          </div>
                          <div class="col-md-9 visitSingle" style="width:76%;padding:0;margin-top:6px;">
                           
                                <div class="col-md-1" style="width:10%;">
                                     <span class="visitNum" data-value="1">1次</span>

                                </div>
                                 <div class="col-md-1" style="width:10%;">
                                   <span class="visitNum" data-value="2">2次</span>
                                </div>
                                <div class="col-md-1" style="width:12%;">
                                   <span class="visitNum" data-value="3">3次及以上</span>
                                </div>
                          </div>
                              
                          
                          <div class="col-md-2 visitCheck" style="width:6%;border:1px solid #d6dfe6;padding:0;height:32px;line-height:32px;border-radius:4px;margin-left:6%;cursor: pointer;">
                              <img src="static/images/addHao.png" alt="" style="margin-left:20%;"/>    
                              <span style="font-size: 14px ;color:#616b88;margin-left:8%;">多选</span>
                          </div>    
                         
                        </div>
                        <div class="row guKuamg" style="padding-top:10px;padding-bottom:10px;border-bottom:1px dashed #dde1e8;">
                            <div class="col-md-1" style="width:10%;padding:0;text-align:center;margin-top:6px;">
                              <span style="font-size: 14px ;color:#616b88;">归属顾问：</span>
                            </div>
                            <div class="col-md-9 guSingle" style="width:76%;padding:0;margin-top:6px;">
                                <!-- <div class="col-md-1" style="width:12%;">
                                    <span class="guishu" data-value="1">张三</span>
                                </div> -->
                                <!-- <div class="col-md-1" style="width:12%;">                               
                                    <span class="guishu" data-value="2">李四</span>
                                </div>
                                <div class="col-md-1" style="width:12%;">
                                 <span class="guishu" data-value="3">毛晓</span>
                                </div>
                                <div class="col-md-1" style="width:12%;">
                                   <span class="guishu" data-value="4">汪汪</span>
                                </div>
                                <div class="col-md-1" style="width:12%;">
                                  <span class="guishu" data-value="5">地理热吧</span>
                                </div>
                                <div class="col-md-1" style="width:12%;">
                                  <span class="guishu" data-value="6">好人</span>
                                </div>
                                <div class="col-md-1" style="width:12%;">
                                  <span class="guishu" data-value="7">黄黄</span>
                                </div>
                                 <div class="col-md-1" style="width:12%;">
                                    <span class="guishu" data-value="8">张三</span>
                                </div> -->
                               
                                
                                 
                            </div> 
                            <div class="col-md-9 guAll" style="width:76%;padding:0;margin-top:6px;display:none;">
                                <div class="row guAllR">
                                    <!-- <div class="col-md-1" style="width:12%;">
                                        <input type="checkbox" name="guwen" id="g0" />
                                        <label for="g0" class="mo" style="font-weight: 100;position:relative;top:-2px;">张三</label>
                                    </div>
                                    <div class="col-md-1" style="width:12%;">                               
                                        <input type="checkbox" name="guwen" id="g1" />
                                        <label for="g1" class="mo" style="font-weight: 100;position:relative;top:-2px;">张三</label>
                                    </div>
                                    <div class="col-md-1" style="width:12%;">
                                        <input type="checkbox" name="guwen" id="g2" />
                                        <label for="g2" class="mo" style="font-weight: 100;position:relative;top:-2px;">张三</label>
                                    </div>
                                    <div class="col-md-1" style="width:12%;">
                                       <input type="checkbox" name="guwen" id="g3" />
                                        <label for="g3" class="mo" style="font-weight: 100;position:relative;top:-2px;">张三</label>
                                    </div>
                                    <div class="col-md-1" style="width:12%;">
                                      <input type="checkbox" name="guwen" id="g4" />
                                        <label for="g4" class="mo" style="font-weight: 100;position:relative;top:-2px;">张三</label>
                                    </div>
                                    <div class="col-md-1" style="width:12%;">
                                      <input type="checkbox" name="guwen" id="g5" />
                                        <label for="g5" class="mo" style="font-weight: 100;position:relative;top:-2px;">张三</label>
                                    </div>
                                    <div class="col-md-1" style="width:12%;">
                                        <input type="checkbox" name="guwen" id="g6" />
                                        <label for="g6" class="mo" style="font-weight: 100;position:relative;top:-2px;">张三</label>
                                    </div>
                                     <div class="col-md-1" style="width:12%;">
                                        <input type="checkbox" name="guwen" id="g7" />
                                        <label for="g7" class="mo" style="font-weight: 100;position:relative;top:-2px;">张三</label>
                                    </div> -->
                                   
                                    
                                </div>
                                <div class="row">
                                    <div class="col-md-2" style="width:40%;margin-left:30%;">
                                        <button class="btnAll btnOne" disabled="disabled" id="guSure">确认</button>
                                        <button class="btnAll btnTwo guDelete">取消</button>
                                    </div>
                                </div>
                                 
                            </div>
                            <div class="col-md-1 moreDuo" style="width:4%;padding:0;cursor: pointer;height:32px;line-height:32px;margin-left:1%;">
                                <div class="row" style="padding:0;" id="moreDuo">
                                    <div class="col-md-6" style="width:44%;padding:0;" >
                                         <span style="font-size: 14px ;color:#616b88;" class="many">更多</span>
                                    </div>
                                                                    
                                    <div class="col-md-4" style="width:33%;padding:0;height:32px;text-align:center;border:1px solid #d6dfe6;border-radius:4px;">
                                        <img src="static/images/moreDown.png" alt="" style=""/>
                                    </div>                                 
                                </div>
                                <div class="row" style="padding:0;display:none;" id="litle">
                                    <div class="col-md-6" style="width:44%;padding:0;" >
                                         <span style="font-size: 14px ;color:#616b88;" class="many">收起</span>
                                    </div>
                                                                    
                                    <div class="col-md-4" style="width:33%;padding:0;height:32px;text-align:center;border:1px solid #d6dfe6;border-radius:4px;">
                                        <img src="static/images/shouUp.png" alt="" style=""/>
                                    </div>                                 
                                </div>
                            </div>
                            <div class="col-md-1 guCheck" style="width:6%;border:1px solid #d6dfe6;padding:0;height:32px;line-height:32px;border-radius:4px;cursor: pointer;margin-left:1%;">
                                <img src="static/images/addHao.png" alt="" style="margin-left:20%;"/>    
                                <span style="font-size: 14px ;color:#616b88;margin-left:8%;">多选</span>                     
                            </div>
                        </div>
                        <div style="position: relative;">
                        <div class="row" style="padding-top:10px;padding-bottom:20px;">
                            <div class="col-md-1" style="width:10%;padding:0;text-align:center;margin-top:6px;">
                              <span style="font-size: 14px ;color:#616b88;">客户标签：</span>
                            </div>
                            <div class="col-md-9 everyTag" style="width:76%;padding:0;margin-top:-6px;">
                                

                            </div>
                        </div> 
                         <div class="row xiaBox" style="z-index:10;border:1px solid #46c1de;margin-top:-22px;display:none;width:100%;background:#ffffff;">
                            <div class="col-md-9 xiaSingle" style="width:76%;padding:0;margin-top:10px;margin-left:4%;">
                               
                                   
                                
                            </div>
                            <div class="col-md-9 xiaAll" style="width:76%;padding:0;margin-top:14px;margin-left:4%;display:none;">
                                <div class="row allCheckTag">
                                      

                                </div>
                                <div class="row" style="padding-bottom:10px;">
                                    <div class="col-md-2" style="width:40%;margin-left:40%;">
                                        <button class="btnAll btnOne" disabled="disabled" id="xiaSure">确认</button>
                                        <button class="btnAll btnTwo  xiaDelete">取消</button>
                                    </div>
                                </div>    
                                
                            </div>
                            <div class="col-md-1  qianMore " style="width:6%;border:1px solid #d6dfe6;padding:0;height:32px;line-height:32px;border-radius:4px;cursor: pointer;margin-left:12%;margin-top:8px;margin-bottom:10px;">
                                <img src="static/images/addHao.png" alt="" style="margin-left:20%;"/>    
                                <span style="font-size: 14px ;color:#616b88;margin-left:8%;">多选</span>                     
                            </div> 
                        </div> 
                        </div>
                    </div>
                </div>
                 <div class="row" style="margin-top:20px;">
                    <div class="col-md-2 " style="width:10%;margin-left:87%;">
                            <select name="" id="changeRole" style="height:34px;line-height:34px;width:100%;font-size:14px;color:#616b88;">
                              
                              <option value="0">卡片式视图</option>
                              <option value="1">列表式视图</option>
                            </select>

                    </div>
                </div>
                <div class="row listOne" style="width:94%;margin-left:3%;margin-top:20px;">
                      <div class="col-md-12  kaPian" >
                          <div class="row kaList" >
                               
                      		</div>
                      </div>
                      <div class="col-md-12  lieBiao" style="padding:0;display:none;">
                            <table class="table table-striped table-hover" style="width:100%;">
                                  <thead>
                                    <tr>
                                      <th >姓名</th>
                                      <th>来访次数</th>
                                      <th>客户状态</th>
                                      <th>归属顾问</th>
                                      <th>手机号码</th>
                                      <th>客户标签</th>
                                    </tr>
                                  </thead>
                                  <tbody id="liebiao">
                                    <!-- <tr>
                                      <td>Tanmay</td>
                                      <td>Bangalore</td>
                                      <td>560001</td>
                                       <td>Tanmay</td>
                                      <td>Bangalore</td>
                                      <td>560001</td>
                                    </tr>
                                    <tr>
                                      <td>Sachin</td>
                                      <td>Mumbai</td>
                                      <td>400003</td>
                                      <td>Tanmay</td>
                                      <td>Bangalore</td>
                                      <td>560001</td>
                                    </tr>
                                    <tr>
                                      <td>Uma</td>
                                      <td>Pune</td>
                                      <td>411027</td>
                                       <td>Tanmay</td>
                                      <td>Bangalore</td>
                                      <td>560001</td>
                                    </tr>
                                    <tr>
                                      <td>Tanmay</td>
                                      <td>Bangalore</td>
                                      <td>560001</td>
                                       <td>Tanmay</td>
                                      <td>Bangalore</td>
                                      <td>560001</td>
                                    </tr>
                                    <tr>
                                      <td>Tanmay</td>
                                      <td>Bangalore</td>
                                      <td>560001</td>
                                       <td>Tanmay</td>
                                      <td>Bangalore</td>
                                      <td>560001</td>
                                    </tr> -->
                                  </tbody>
                                </table>
                      	</div>
                      <div  class="col-md-12  " style="width:92%;margin-left:4%;text-align:center;margin-top:40px;margin-bottom:20px;">
							<div class="beg-table-paged"></div>
					
					  </div>           
        		</div>
				
			<div class="addDiv" >
               <div class="row" style="margin-top:20px;">
                	<div class="col-md-6 " style="width:60%;margin-left:6%;">
                		<span style="font-size:16px;color:#616b88">按顾问调整归属</span>
                	</div>
                    <div class="col-md-2 colmd " style="width:10%;margin-left:20%;">
                        <img src="static/images/newDe.png" class="img-responsive  rightDelete" alt="Responsive image">
                    </div>
                </div>
                <div class="row" style="margin-top:60px;">
                	<div class="col-md-2 " style="width:20%;margin-left:10%;margin-top:10px;">
                		<span style="font-size:14px;color:#616b88">被调整顾问</span>
                	</div>
                   <div class="col-md-8 " style="width:55%;margin-left:2%;">
	                   	<div class="row" style="border:1px solid #d6dfe6;background:#f0f4fb;border-top-left-radius:4px;border-top-right-radius:4px;">
	                   		 <div class="col-md-12" style="padding:0;width:68%;height:34px;line-height: 32px;width:100%;">
	                              <input type="text" id="adjustName" style="width:100%;height:100%;background:#f0f4fb;line-height: 32px;border:0;outline:0;padding-left:10px;font-size:12px;color:#616b88;" />
	                          </div>
	                          
	                     </div>
	                     <div class="row adjustGuwen" style="border:1px solid #d6dfe6;background:#f0f4fb;height:180px;overflow:auto;">
	                     		
	                     </div>
                   </div>
                </div>
                <div class="row" style="margin-top:60px;">
                	<div class="col-md-2 " style="width:20%;margin-left:10%;margin-top:10px;">
                		<span style="font-size:14px;color:#616b88">归属顾问</span>
                	</div>
                   <div class="col-md-8 " style="width:55%;margin-left:2%;">
	                   	<div class="row" style="border:1px solid #d6dfe6;background:#f0f4fb;border-top-left-radius:4px;border-top-right-radius:4px;">
	                   		   
	                          <div class="col-md-12" style="padding:0;width:68%;height:34px;line-height: 32px;width:100%;">
	                              <input type="text" id="guiShuName" style="width:100%;height:100%;background:#f0f4fb;line-height: 32px;border:0;outline:0;padding-left:10px;font-size:12px;color:#616b88;" />
	                          </div>
	                          
	                     </div>
	                     <div class="row adjustGuiShu" style="border:1px solid #d6dfe6;background:#f0f4fb;height:180px;overflow:auto;">
	                     		
	                     </div>
                   </div>
                </div>
                
                <div class="row" style="margin-top:50px;">
                		<div class="col-md-6 col-md-offset-3" style="text-align:center;">
                			<button id="guwenSure"  style="background:#f0f4fb;border-radius:4px;font-size:14px;color:#616b88;border:0;border:1px solid #d6dfe6;width:120px;height:40px;line-height:40px;color:">确认</button>
                		</div>
                
                </div>
        	</div>	
			<div class="addDivTwo" >
               <div class="row" style="margin-top:20px;">
                	<div class="col-md-6 " style="width:60%;margin-left:6%;">
                		<span style="font-size:16px;color:#616b88">按客户调整归属</span>
                	</div>
                    <div class="col-md-2 colmd " style="width:10%;margin-left:20%;">
                        <img src="static/images/newDe.png" class="img-responsive  rightDelete" alt="Responsive image">
                    </div>
                </div>
                <div class="row" style="margin-top:60px;">
                	<div class="col-md-2 " style="width:20%;margin-left:10%;margin-top:10px;">
                		<span style="font-size:14px;color:#616b88">被调整客户</span>
                	</div>
                   <div class="col-md-8 fgfg" style="width:55%;margin-left:2%;">
	                   	<div class="row" style="border:1px solid #d6dfe6;background:#f0f4fb;border-top-left-radius:4px;border-top-right-radius:4px;">
	                   		<div class="col-md-2"  style="padding:0;width:10%;height:34px;line-height: 34px;text-align:center;">
	                             <img src="static/images/mSearch.png" style="cursor:pointer;" id="searchClient" > 
	                          </div> 
	                          <div class="col-md-8" style="padding:0;width:68%;height:32px;line-height: 32px;">
	                              <input type="text"  id="clientNamePhone"  style="width:100%;height:100%;background:#f0f4fb;line-height: 32px;border:0;outline:0;padding-left:10px;font-size:12px;color:#616b88;" placeholder="请输入客户姓名或手机号" />
	                          </div>
	                          
	                     </div>
	                    <!--  <div class="row allClientG" style="border:1px solid #d6dfe6;background:#f0f4fb;height:180px;overflow:auto;">
	                     		<div class="col-md-12" style="position:relative;margin-top:10px">
	                     			<input type="checkbox" name="cl" id="l0" style="position:relative;top:2px;"/>
	                     			<label for="l0" class="mo" style="font-weight: 100;position:relative">张三</label>	
	                     		</div>
	                     		<div class="col-md-12" style="position:relative;margin-top:10px">
	                     			<input type="checkbox" name="cl" id="l0" style="position:relative;top:2px;"/>
	                     			<label for="l0" class="mo" style="font-weight: 100;position:relative">张三</label>	
	                     		</div>
	                     		<div class="col-md-12" style="position:relative;margin-top:10px">
	                     			<input type="checkbox" name="cl" id="l0" style="position:relative;top:2px;"/>
	                     			<label for="l0" class="mo" style="font-weight: 100;position:relative">张三</label>	
	                     		</div>
	                     		<div class="col-md-12" style="position:relative;margin-top:10px">
	                     			<input type="checkbox" name="cl" id="l0" style="position:relative;top:2px;"/>
	                     			<label for="l0" class="mo" style="font-weight: 100;position:relative">张三</label>	
	                     		</div><div class="col-md-12" style="position:relative;margin-top:10px">
	                     			<input type="checkbox" name="cl" id="l0" style="position:relative;top:2px;"/>
	                     			<label for="l0" class="mo" style="font-weight: 100;position:relative">张三</label>	
	                     		</div>
	                     		<div class="col-md-12" style="position:relative;margin-top:10px">
	                     			<input type="checkbox" name="cl" id="l0" style="position:relative;top:2px;"/>
	                     			<label for="l0" class="mo" style="font-weight: 100;position:relative">张三</label>	
	                     		</div>
	                     </div> -->
                   </div>
                </div>
                <div class="row" style="margin-top:60px;">
                	<div class="col-md-2 " style="width:20%;margin-left:10%;margin-top:10px;">
                		<span style="font-size:14px;color:#616b88">归属顾问</span>
                	</div>
                   <div class="col-md-8 " style="width:55%;margin-left:2%;">
	                   	<div class="row" style="border:1px solid #d6dfe6;background:#f0f4fb;border-top-left-radius:4px;border-top-right-radius:4px;">
	                   		   
	                          <div class="col-md-12" style="padding:0;width:68%;height:34px;line-height: 32px;width:100%;">
	                              <input type="text"  style="width:100%;height:100%;background:#f0f4fb;line-height: 32px;border:0;outline:0;padding-left:10px;font-size:12px;color:#616b88;" />
	                          </div>
	                          
	                     </div>
	                     <div class="row clientGui" style="border:1px solid #d6dfe6;background:#f0f4fb;height:180px;overflow:auto;">
	                     		
	                     </div>
                   </div>
                </div>
                
                <div class="row" style="margin-top:50px;">
                		<div class="col-md-6 col-md-offset-3" style="text-align:center;">
                			<button   id="clientSure" style="background:#f0f4fb;border-radius:4px;font-size:14px;color:#616b88;border:0;border:1px solid #d6dfe6;width:120px;height:40px;line-height:40px;color:">确认</button>
                		</div>
                
                </div>
        	</div>		
			
       
    </div> 
    </div>
    </div>
      
  
 
   
    <script src="static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="static/js/calendarM.js"></script> 
    <script src="static/js/archon.js"></script>
    <script src="static/js/clientManagement.js"></script>
   <script type="text/javascript" src="static/js/clientManagementFun.js"></script>
	<script type="text/javascript">
	var layer;
	var $;
	var laypage;
	layui.config({
		base: 'static/layui/js/'
	});
	layui.use(['begtable','form','layer'], function() {
		var begtable = layui.begtable(),
			form = layui.form(),
			layer = layui.layer,
			$ = layui.jquery,
			laypage = layui.laypage;
			
		 	initPage();
	})
	
	function initPage(){
		
		var pages = visitInforList();
		
		
		//console.log(pages)
		layui.laypage({
			cont: $('.beg-table-paged'),
			pages:pages 
				,
			groups: 10 //连续显示分页数
				,
			jump: function(obj, first) {
				//console.log(tagIds)
				//得到了当前页，用于向服务端请求对应数据
				var curr = obj.curr;
				
				if(!first){
					layer.msg('第 '+ obj.curr +' 页');
					
					$.ajax({
						type:"post",
						url:"search_projectCustomer",
						data:{num:"10",page:obj.curr,projectAgentId:guwenid,coustomerStatus:coustomerStatus,visitNum:visitNum,tagIds:tagIds},
						async:false,
						
						success:function(data){
							
							//alert(data.count);
							allCientList(data.resultList);
							
							//console.log(data.resultList)
						}
						
					})
				}
				

			}
		});
	}
	</script>
</body>
</html>
    