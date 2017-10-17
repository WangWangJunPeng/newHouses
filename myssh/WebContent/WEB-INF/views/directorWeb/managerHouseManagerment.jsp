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
    <link rel="stylesheet" type="text/css" href="static/css/managerHouseManagerment.css" />
  

    <link rel="stylesheet" type="text/css" href="static/css/calendarM.css" />
    <!-- <link  rel="icon" href="http://root-12521005170.cossh.myqcloud.com/static/static/static/images/titleLogo.png"  /> -->
 	<link rel="stylesheet" href="static/css/layui.css"  />
 	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/begtable.css" />
 	 <script src="static/js/jquery-3.1.1.min.js"></script>
 	  <script src="static/js/layer.js"></script>
 	 <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui/plugins/layui/layui.js"></script>
  
   <style type="text/css">
      .layui-laypage .layui-laypage-curr .layui-laypage-em{
      	    background-color: #46c1de !important;
      
      }
      p,span,tr,td,a,li,option,select,button{font-family:"微软雅黑" !important;}
      #maskBox{display:none;background-color:#fafafa;opacity:0.3;position:absolute;right:0px;top:50px;width:1620px;height:1000px;z-index:20;}
      
    </style>
</head>
<body >
<div id="maskBox"></div>
    <div class="frame"  style="min-height:1000px;">
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
            <div id="main-content" >
                <div class="row">
                  <div class="col-md-4">
                      <p><b style="color:#fe1c27;font-size:14px;">*</b><a href="###" style="color:#616b88;font-size:14px;margin-left:4px;">首页</a><a href="###" style="color:#616b88;font-size:14px;">&gt;</a><a href="###" style="color:#a6d675;font-size:14px;">房源管理</a></p>
                  </div>
                    
                </div>
                <div class="row" style="margin-top:16px;">
                    <div class="col-md-2 " style="width:10%;">
                        <select name="" id="houseTypeId" style="height:34px;line-height:34px;width:100%;font-size:14px;color:#616b88;border-radius:4px;">                              
                              
                              
                        </select>

                    </div>
                    <div class="col-md-2 " style="width:10%;padding-left:0;">
                        <select name="" id="houseStatus" style="height:34px;line-height:34px;width:100%;font-size:14px;color:#616b88;border-radius:4px;">                              
                              <option value="">房源状态</option>
                              <option value="0">销控</option>
                              <option value="1">在售</option>
                              <!-- <option value="2">删除</option> -->
                              <option value="3">撤销</option>
                              <option value="4">认购待审核</option>
                              <option value="5">待付款</option>
                              <option value="6">付款待确认</option>
                              <option value="7">待签约</option>
                              <option value="8">已签约</option>
                        </select>

                    </div>
                    <div class="col-md-2 " style="width:10%;padding-left:0;">
                        <select name="" id="houseKind" style="height:34px;line-height:34px;width:100%;font-size:14px;color:#616b88;border-radius:4px;">                              
                              <option value="">房源类型</option>
                              <option value="0">公寓</option>
                              <option value="1">排屋</option>
                              <option value="2">独栋</option>
                              <option value="3">商住两用</option>
                              <option value="4">办公室</option>
                              <option value="5">酒店式公寓</option>
                              <option value="6">商铺</option>
                              <option value="7">车位</option>
                              <option value="8">车库</option>
                              <option value="9">储藏室</option>
                        </select>

                    </div>
                    <div class="col-md-2 " style="width:10%;padding-left:0;">
                        <select name="" id="isSee" style="height:34px;line-height:34px;width:100%;font-size:14px;color:#616b88;border-radius:4px;">                              
                              <option value="">经纪人是否可见</option>                              
                              <option value="0">不可见</option>
                              <option value="1">可见</option>
                              
                        </select>

                    </div>
                    <div class="col-md-2 " style="width:5%;padding-left:0;">
                        <button id="searchHouse" style="height:34px;line-height:34px;width:100%;font-size:14px;color:#ffffff;border-radius:4px;border:0;background:#46c1de;">搜索</button>

                    </div>
                    <div class="col-md-2 " style="width:5%;padding-left:0;">
                        <button id="reset" style="height:34px;line-height:34px;width:100%;font-size:14px;color:#ffffff;border-radius:4px;border:0;background:#46c1de;">重置</button>

                    </div>
                    <div class="col-md-2 " style="width:10%;padding-left:0;">
                        <select name="" id="manyOperate" style="height:34px;line-height:34px;width:100%;font-size:14px;color:#616b88;border-radius:4px;">                              
                              <option value="">批量操作</option>
                              <option value="1">批量上架</option>
                              <option value="2">批量下架</option>
                              <option value="3">批量删除</option>
                              <option value="4">批量对经纪人可见</option>
                              <option value="5">批量对经纪人不可见</option>
                        </select>

                    </div>
                    <div class="col-md-2 " style="width:20%;margin-left:20%;text-align:right;">
                        <a href="manage_toListType" style="font-size:14px;color:#616b88;display:inline-block;margin-top:8px;">楼盘表视图</a>
                        <a href="manage_toHousesManage" style="font-size:14px;color:#616b88;display:inline-block;margin-top:8px;margin-left:20px;border-bottom:2px solid #ff3b3b;">列表视图</a>
                    </div>
                </div>
                <div class="row"  style="margin-top:20px;">
                    <div class="listOne col-md-12" style="width:98%;margin-left:1%;">
                        <div class="row" style="padding-top:30px;">
                            <div class="col-md-2" style="width:6%;">
                                <span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#0ad27a;"></span>
                                <span style="font-size:14px;color:#616b88;display:inline-block;">在售</span>
                            </div>
                            <div class="col-md-2" style="width:6%;">
                                <span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#e47878;"></span>
                                <span style="font-size:14px;color:#616b88;display:inline-block;">销控</span>
                            </div>
                            <div class="col-md-2" style="width:10%;">
                                <span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#cbc341;"></span>
                                <span style="font-size:14px;color:#616b88;display:inline-block;">认购待审核</span>
                            </div>
                            <div class="col-md-2" style="width:8%;">
                                <span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#46c1de;"></span>
                                <span style="font-size:14px;color:#616b88;display:inline-block;">待付款</span>
                            </div>
                           
                            <div class="col-md-2" style="width:10%;">
                                <span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#c5c5c5;"></span>
                                <span style="font-size:14px;color:#616b88;display:inline-block;">付款待确认</span>
                            </div>
                            <div class="col-md-2" style="width:8%;">
                                <span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#8d8d8d;"></span>
                                <span style="font-size:14px;color:#616b88;display:inline-block;">待签约</span>
                            </div>
                            <div class="col-md-2" style="width:8%;">
                                <span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#666666;"></span>
                                <span style="font-size:14px;color:#616b88;display:inline-block;">已签约</span>
                            </div>
                            <div class="col-md-2" style="width:8%;">
                                <span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#ff6161;"></span>
                                <span style="font-size:14px;color:#616b88;display:inline-block;">撤销</span>
                            </div>
                        </div>
                        <div class="row" style="margin-top:30px;">
                            <div class="col-md-12" style="width:100%;">
                                <table class="table table-bordered" style="width:100%;border-radius:4px;">
                                  <thead style="background:#c9d9f8;">
                                    <tr>
                                      <th ></th>
                                      <th>状态</th>
                                      <th>分区</th>
                                      <th>楼栋</th>
                                      <th>单元</th>
                                      <th>房号</th>
                                      <th>户型</th>
                                      <th>建筑面积</th>
                                      <th>套内面积</th>
                                      <th>挂牌价</th>
                                      <th>操作</th>
                                    </tr>
                                  </thead>
                                  <tbody id="housesList">
                                    
                                    <!-- <tr>
                                      <td> 
                                        <input type="checkbox"  />
                                      </td>
                                      <td>
                                        <span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#0ad27a;margin-top:5px;"></span>
                                      </td>
                                      <td>西区</td>
                                      <td>楼栋三</td>
                                      <td>4单元</td>
                                      <td>501</td>
                                      <td>11</td>
                                      <td>0</td>
                                      <td>0</td>
                                      <td>0</td>
                                      <td>
                                        <img src="static/images/bianji.png" alt="" style="cursor:pointer;" id="bianji" />
                                        <img src="static/images/mdelete.png" alt="" style="margin-left:5px;cursor:pointer;"/>
                                        <img src="static/images/kejian.png" alt="" style="margin-left:5px;cursor:pointer;"/>
                                      </td>
                                    </tr> -->
                                    
                                  </tbody>
                                </table>
                            </div>
                            <div  class="col-md-12  " style="width:92%;margin-left:4%;text-align:center;margin-top:40px;margin-bottom:20px;">
									<div class="beg-table-paged"></div>
					
					  		</div>
                        </div>
                        
                    </div>
                </div>
                      
             
            </div>
            <div class="addDiv" >
            <input type="hidden" id="houseNum"/>
                <div class="row">
                    <div class="col-md-6" style="width:60%;margin-left:6%;margin-top:16px;">
                        <p style="color:#616b88;font-size:16px;font-weight:bold;">房源信息</p>
                    </div>
                </div>
                <div class="row" style="margin-top:10px;">
                    <div class="col-md-2" style="width:20%;text-align:right;margin-left:12%;">
                       <span style="font-size:14px;color:#616b88;display:inline-block;margin-top:6px;">房号</span>
                    </div>
                    <div class="col-md-6" style="width:50%;" >
                        <input type="text" disabled id="houseNo" style="width:100%;height:30px;border:1px solid #d6dfe6;background:#f6f9fd;border-radius:4px;padding-left:10px;"/>
                    </div>
                </div>
                <div class="row" style="margin-top:10px;">
                    <div class="col-md-2" style="width:20%;text-align:right;margin-left:12%;">
                       <span style="font-size:14px;color:#616b88;display:inline-block;margin-top:6px;">预售证号</span>
                    </div>
                    <div class="col-md-6" style="width:50%;" >
                        <input id="presalePermissionInfo" disabled type="text" style="width:100%;height:30px;border:1px solid #d6dfe6;background:#f6f9fd;border-radius:4px;padding-left:10px;"/>
                    </div>
                </div>
                <div class="row" style="margin-top:10px;">
                    <div class="col-md-2" style="width:20%;text-align:right;margin-left:12%;">
                       <span style="font-size:14px;color:#616b88;display:inline-block;margin-top:6px;">所属分区</span>
                    </div>
                    <div class="col-md-6" style="width:50%;" >
                        <input type="text" disabled id="district" style="width:100%;height:30px;border:1px solid #d6dfe6;background:#f6f9fd;border-radius:4px;padding-left:10px;"/>
                    </div>
                </div>
                <div class="row" style="margin-top:10px;">
                    <div class="col-md-2" style="width:20%;text-align:right;margin-left:12%;">
                       <span style="font-size:14px;color:#616b88;display:inline-block;margin-top:6px;">楼栋号</span>
                    </div>
                    <div class="col-md-6" style="width:50%;" >
                        <input type="text" disabled id="buildingId" style="width:100%;height:30px;border:1px solid #d6dfe6;background:#f6f9fd;border-radius:4px;padding-left:10px;"/>
                    </div>
                </div>
                <div class="row" style="margin-top:10px;">
                    <div class="col-md-2" style="width:20%;text-align:right;margin-left:12%;">
                       <span style="font-size:14px;color:#616b88;display:inline-block;margin-top:6px;">房源类型</span>
                    </div>
                    <div class="col-md-6" style="width:50%;" >
<!--                         <input type="text" id="houseStyle" style="width:100%;height:30px;border:1px solid #d6dfe6;background:#f6f9fd;border-radius:4px;padding-left:10px;"/>
 -->                    	<select name="" id="houseStyle" style="height:30px;line-height:30px;width:100%;border:1px solid #d6dfe6;font-size:14px;border-radius:4px;padding-left:10px;">                              
                              
                              <option value="0">公寓</option>
                              <option value="1">排屋</option>
                              <option value="2">独栋</option>
                              <option value="3">商住两用</option>
                              <option value="4">办公室</option>
                              <option value="5">酒店式公寓</option>
                              <option value="6">商铺</option>
                              <option value="7">车位</option>
                              <option value="8">车库</option>
                              <option value="9">储藏室</option>
                        </select>
                    </div>
                </div>
                <div class="row" style="margin-top:10px;">
                    <div class="col-md-2" style="width:20%;text-align:right;margin-left:12%;">
                       <span style="font-size:14px;color:#616b88;display:inline-block;margin-top:6px;">单元</span>
                    </div>
                    <div class="col-md-6" style="width:50%;" >
                        <input type="text" disabled id="danyuan" style="width:100%;height:30px;border:1px solid #d6dfe6;background:#f6f9fd;border-radius:4px;padding-left:10px;"/>
                    </div>
                </div>
                <div class="row" style="margin-top:10px;">
                    <div class="col-md-2" style="width:20%;text-align:right;margin-left:12%;">
                       <span style="font-size:14px;color:#616b88;display:inline-block;margin-top:6px;">楼层</span>
                    </div>
                    <div class="col-md-6" style="width:50%;" >
                        <input type="text" disabled id="floor" style="width:100%;height:30px;border:1px solid #d6dfe6;background:#f6f9fd;border-radius:4px;padding-left:10px;"/>
                    </div>
                </div>
                <div class="row" style="margin-top:10px;">
                    <div class="col-md-2" style="width:20%;text-align:right;margin-left:12%;">
                       <span style="font-size:14px;color:#616b88;display:inline-block;margin-top:6px;">朝向</span>
                    </div>
                    <div class="col-md-6" style="width:50%;" >
                        <input type="text" id="chaoxiang" style="width:100%;height:30px;border:1px solid #d6dfe6;background:#f6f9fd;border-radius:4px;padding-left:10px;"/>
                    </div>
                </div>
                <div class="row" style="margin-top:10px;">
                    <div class="col-md-2" style="width:20%;text-align:right;margin-left:12%;">
                       <span style="font-size:14px;color:#616b88;display:inline-block;margin-top:6px;">建筑面积</span>
                    </div>
                    <div class="col-md-6" style="width:50%;" >
                        <input type="text" id="buildArea" style="width:100%;height:30px;border:1px solid #d6dfe6;background:#f6f9fd;border-radius:4px;padding-left:10px;"/>
                    </div>
                </div>
                <div class="row" style="margin-top:10px;">
                    <div class="col-md-2" style="width:20%;text-align:right;margin-left:12%;">
                       <span style="font-size:14px;color:#616b88;display:inline-block;margin-top:6px;">使用面积</span>
                    </div>
                    <div class="col-md-6" style="width:50%;" >
                        <input type="text" id="usefulArea" style="width:100%;height:30px;border:1px solid #d6dfe6;background:#f6f9fd;border-radius:4px;padding-left:10px;"/>
                    </div>
                </div>
                <div class="row" style="margin-top:10px;">
                    <div class="col-md-2" style="width:20%;text-align:right;margin-left:12%;">
                       <span style="font-size:14px;color:#616b88;display:inline-block;margin-top:6px;">列表价</span>
                    </div>
                    <div class="col-md-6" style="width:50%;" >
                        <input type="text" id="listPrice" style="width:100%;height:30px;border:1px solid #d6dfe6;background:#f6f9fd;border-radius:4px;padding-left:10px;"/>
                    </div>
                </div>
                <div class="row" style="margin-top:10px;">
                    <div class="col-md-2" style="width:20%;text-align:right;margin-left:12%;">
                       <span style="font-size:14px;color:#616b88;display:inline-block;margin-top:6px;">中介供货价</span>
                    </div>
                    <div class="col-md-6" style="width:50%;" >
                        <input type="text" id="shopPrice" style="width:100%;height:30px;border:1px solid #d6dfe6;background:#f6f9fd;border-radius:4px;padding-left:10px;"/>
                    </div>
                </div>
                <div class="row" style="margin-top:10px;">
                    <div class="col-md-2" style="width:20%;text-align:right;margin-left:12%;">
                       <span style="font-size:14px;color:#616b88;display:inline-block;margin-top:6px;">底价</span>
                    </div>
                    <div class="col-md-6" style="width:50%;" >
                        <input type="text" id="minimumPrice" style="width:100%;height:30px;border:1px solid #d6dfe6;background:#f6f9fd;border-radius:4px;padding-left:10px;"/>
                    </div>
                </div>
                <div class="row" style="margin-top:10px;">
                    <div class="col-md-2" style="width:20%;text-align:right;margin-left:12%;">
                       <span style="font-size:14px;color:#616b88;display:inline-block;margin-top:6px;">户型</span>
                    </div>
                    <div class="col-md-6" style="width:50%;" >
                        <input type="text" id="houseType" style="width:100%;height:30px;border:1px solid #d6dfe6;background:#f6f9fd;border-radius:4px;padding-left:10px;"/>
                    </div>
                </div>
                <div class="row" style="margin-top:10px;">
                    <div class="col-md-2" style="width:20%;text-align:right;margin-left:12%;">
                       <span style="font-size:14px;color:#616b88;display:inline-block;margin-top:6px;">装修标准</span>
                      
                    </div>
                    <div class="col-md-6" style="width:50%;" >
                        
                    	 <select name="" id="decorationStandard" style="height:30px;line-height:30px;width:100%;border:1px solid #d6dfe6;font-size:14px;border-radius:4px;padding-left:10px;">                              
                              
                              <option value="0">毛坯</option>
                              <option value="1">普通装修</option>
                              <option value="2">精装修</option>
                              <option value="3">家具全配</option>
                              <option value="4">家电全配</option>
                              
                        </select>
                    </div>
                </div>
                <div class="row" style="margin-top:10px;">
                    <div class="col-md-2" style="width:20%;text-align:right;margin-left:12%;">
                       <span style="font-size:14px;color:#616b88;display:inline-block;margin-top:6px;">描述</span>
                    </div>
                    <div class="col-md-6" style="width:50%;" >
                        <textarea name="" id="description" cols="30" rows="10" style="width:100%;height:60px;border:1px solid #d6dfe6;background:#f6f9fd;border-radius:4px;padding-left:10px;resize:none;"></textarea>
                    </div>
                </div>
                <div class="row" style="margin-top:20px;">
                    <div class="col-md-2" style="width:30%;text-align:right;margin-left:2%;">
                       <span style="font-size:14px;color:#616b88;display:inline-block;margin-top:6px;">是否对经纪人可见</span>
                    </div>
                    <div class="col-md-6" style="width:50%;position:relative" >
                        <input type="radio" id="kejian" name="jian"  style="top:2px;position:relative;margin-left:16px;" value="1"/>
                        <label for="kejian" style="font-size:14px;color:#616b88;margin-top:6px;margin-left:4px;">是</label>
                        <input type="radio" id="bukejian" name="jian" style="top:2px;position:relative;margin-left:70px;" value="0"/>
                        <label for="bukejian" style="font-size:14px;color:#616b88;margin-top:6px;margin-left:4px;">否</label>
                    </div>
                </div>
                <div class="row" style="margin-top:14px;">
                    <div class="col-md-2" style="width:20%;text-align:right;margin-left:12%;">
                       <span style="font-size:14px;color:#616b88;display:inline-block;margin-top:6px;">房源状态</span>
                    </div>
                    <div class="col-md-6" style="width:50%;position:relative" >
                        <input type="radio" id="zaishou" name="status" style="top:2px;position:relative;margin-left:16px;" value="1"/>
                        <label for="zaishou" style="font-size:14px;color:#616b88;margin-top:6px;margin-left:4px;" >在售</label>
                        <input type="radio" id="xiaokong" name="status" style="top:2px;position:relative;margin-left:56px;" value="0"/>
                        <label for="xiaokong" style="font-size:14px;color:#616b88;margin-top:6px;margin-left:4px;">销控</label>
                    </div>
                </div>
                <div class="row" style="margin-top:30px;">
                    <div class="col-md-2" style="width:28%;margin-left:16%;">
                         <button style="height:34px;line-height:34px;width:100%;font-size:14px;color:#616b88;border-radius:4px;border:0;background:#d6dfe6;" id="houseDelete">取消</button>

                    </div>
                    <div class="col-md-2" style="width:28%;margin-left:8%">
                         <button id="finish" style="height:34px;line-height:34px;width:100%;font-size:14px;color:#ffffff;border-radius:4px;border:0;background:#46c1de;">完成</button>

                    </div>
                </div>
            </div>
        
      </div> 
    </div>
    
  
    <script src="static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="static/js/calendarM.js"></script> 
    <script src="static/js/archon.js"></script>
   <script   type="text/javascript" src="static/js/managerHouseManagerment.js"></script>
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
				
			dividePage();
		})
   	function dividePage(){
			
		//console.log(count)
		
		layui.laypage({
			cont: $('.beg-table-paged'),
			pages:count 
				,
			groups: 10 //连续显示分页数
				,
			jump: function(obj, first) {
				//得到了当前页，用于向服务端请求对应数据
				var curr = obj.curr;
				if(!first){
					//layer.msg('第 '+ obj.curr +' 页');
					var index = layer.load(1, {
							  shade: [0.1,'#fff'] //0.1透明度的白色背景
							});
					$.ajax({
				        type:"post",
				        url:"manage_searchHouse",
				        async:false,
				        data:{
				        	  houseTypeId:$("#houseTypeId").val(),
				        	  houseKind:$("#houseKind").val(),
				        	  houseStatus:$("#houseStatus").val(),
				        	  isSee:$("#isSee").val(),
				        	  page:curr,
				        	  num:"10",
				        	},
				        success:function(data){
				        	layer.close(index);
				        	//console.log(data)
				        	housesList(data);
				        }
				    })
				}
				

			}
		});
	}
   </script>

</body>
</html>
    