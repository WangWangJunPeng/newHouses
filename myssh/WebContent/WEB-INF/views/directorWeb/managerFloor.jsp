<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" type="text/css" href="static/css/managerFloor.css" />
  <link rel="stylesheet" href="static/css/layui.css"  />
 	 <script src="static/js/jquery-3.1.1.min.js"></script>
   <script src="static/js/layer.js"></script>
   

    <link rel="stylesheet" type="text/css" href="static/css/calendarM.css" />
    <!-- <link  rel="icon" href="http://root-12521005170.cossh.myqcloud.com/static/static/static/images/titleLogo.png"  /> -->
 
   <style type="text/css">
      #maskBox{display:none;background-color:#fafafa;opacity:0.3;position:absolute;right:0px;top:50px;width:1620px;height:1000px;z-index:20;}
      
      p,span,tr,td,a,li,option,select,button{font-family:"微软雅黑" !important;}
    </style>
</head>
<body >
<div id="maskBox"></div>
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
                	<input type="hidden" id="houseNum" />
                    <div class="col-md-2 " style="width:9%;">
                        <div id="edit" style="width:100%;height:34px;line-height:34px;border-radius:4px;border:1px solid #d6dfe6;background:#f6f9fd;position:relative;">
                            <span style="color:#616b88;font-size:14px;display:inline-block;position:absolute;left:12px;">编辑</span>
                            <img src="static/images/bj.png" alt="" style="position:absolute;right:12px;top:6px;"/>
                        </div>

                    </div>
                    <div class="col-md-2 " style="width:9%;">
                        <div id="watch" style="width:100%;height:34px;line-height:34px;border-radius:4px;border:1px solid #d6dfe6;background:#f6f9fd;position:relative;">
                            <span style="color:#616b88;font-size:14px;display:inline-block;position:absolute;left:12px;">查看</span>
                            <img src="static/images/sk.png" alt="" style="position:absolute;right:12px;top:6px;"/>
                        </div>

                    </div>
                    <div class="col-md-2 " style="width:20%;margin-left:62%;text-align:right;">
                        <a href="manage_toListType" style="font-size:14px;color:#616b88;display:inline-block;margin-top:8px;border-bottom:2px solid #ff3b3b;">楼盘表视图</a>
                        <a href="manage_toHousesManage" style="font-size:14px;color:#616b88;display:inline-block;margin-top:8px;margin-left:20px;">列表视图</a>
                    </div>
                </div>
                <div class="row"  style="margin-top:20px;">
                    <div class="listOne col-md-12" style="width:98%;margin-left:1%;padding-bottom:20px;">
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
                        </div>
                        <div class="row" style="margin-top:30px;">
                            <div class="col-md-2 left_down" style="width:14%; height:700px;border:1px solid #d6dfe6;padding:0;">
                                <div style="margin-top:40px;">
                                    <ul class="allArea">
                                        <!-- <li>
                                            <a href="###" style="margin-left:24%;width:50%;display:inline-block;height:40px;line-height: 40px;border-bottom: 1px solid #f2f5f9;position:relative" class="first_floor"><img src="static/images/addhaoTWO.png" alt="" style="position:relative;top:-2px;" class="addjian" /><span  style="font-size:16px;color:#616b88;margin-left:1%;">东区</span></a>                                         
                                            <ul class="second_floor" style="display:none;">
                                                <li>
                                                    <a href="###" style="margin-left:28%;width:60%;display:inline-block;height:40px;line-height: 40px;border-bottom: 1px solid #f2f5f9;" class="first_floor_next"><img src="static/images/loufloor.png" alt="" style="margin-left:1%;"/><span  style="font-size:16px;color:#616b88;margin-left:1%;">楼栋1</span></a>
                                                    
                                                    
                                                </li>
                                                
                                            </ul>
                                        </li> -->
                                                                                    
                                    </ul>
                                    
                                                              
                                  
                                </div>    
                            </div>
                            <div class="col-md-8" style="width:86%;">
                                <div class="row">
                                    <div class="col-md-2" style="width:10%;">
                                        <div id="areaKuai" style="display:none;width:100%;height:34px;line-height:34px;border-radius:4px;background:#ff9666;position:relative;text-align:center;color:#ffffff;font-size:14px;" >
                                            <span id="area"></span>
                                            <span>-</span>
                                            <span id="dong"></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="row danBiao" style="margin-top:10px;display:none;">
                                    <div class="col-md-10" style="width:100%;">
                                        <table class="table table-bordered tTwoTable" style="width:100%;border-radius:4px;">
                                        <thead id="thTable">
                                            <tr id="unitNum">
                                                <!-- <th rowspan="2">楼层数</th>
                                                <th >单元</th> -->
                                                <!-- <th colspan="2" >一单元</th>
                                                <th colspan="2">二单元</th>
                                                <th colspan="2">三单元</th> -->
                                            </tr>
                                          </thead>
                                          <tbody id="trTable">
                                            <!-- <tr>
                                                <th>房号</th>
                                                <th>01</th>
                                                <th>02</th>
                                                <th>01</th>
                                                <th>02</th>
                                                <th>01</th>
                                                <th>02</th>
                                            </tr> -->
                                          <!--  <tr>
                                                <td >10</td>
                                                <td>1001</td>
                                                <td>1002</td>
                                                <td>1001</td>
                                                <td>1002</td>
                                                <td>1001</td>
                                                <td>1002</td>
                                            </tr>  -->
                                        </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                      
             
            </div>
            <div class="addDiv" >            
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
   <script type="text/javascript" src="static/js/managerFloor.js"></script>
   

</body>
</html>
   