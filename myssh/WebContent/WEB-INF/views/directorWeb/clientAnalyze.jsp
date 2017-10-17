<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <link rel="stylesheet" type="text/css" href="static/css/clientAnalyze.css" />
  

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
                     <li>
                    	<a href="to_pCustomerManager" class="noDrop"><span>客户管理</span></a>
                    	<ul style="display:block;border:0;">
                            <li><a href="customerAnalyze" style="padding-left:5px;color:#ffffff;font-size: 14px;"><span style="color:#46c1de;">客户分析</span></a></li>
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
                    <div class="col-md-11 listOne" style="width:98%;margin-left:1%;padding-bottom:200px;">
                        <div class="row">
                            <div class="col-md-12" style="width:100%;">
                                <div id="analyzePic" style="height:500px;width:100%;"></div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8" style="width:50%;">
                                <div id="shanPic" style="height:360px;width:100%;"></div>
                            </div>
                            <div class="col-md-4" style="width:35%;margin-left:6%;background:#f8f9fc;border:1px solid #f0f4fb;border-radius:4px;margin-top:60px;">
                                <div class="row" >
                                    <div class="col-md-6" style="width:50%;height:50px;line-height:50px;">
                                        <span style="font-size:16px;color:#56678a;font-weight:bold;">客户二次回头分析：</span>
                                    </div>
                                </div>
                                <div class="row" style="background:#f0f4fb;">
                                    <div class="col-md-12">
                                        <div class="row" >
                                            <div class="col-md-2" style="width:8%;padding:0;margin-left:2%;text-align:center;">
                                                <span style="color:#ffffff;font-size: 14px;width:18px;height:18px;line-height:18px;border-radius:2px;background:#847ac3;display:inline-block;text-align:center;margin-top:16px;">1</span>
                                            </div>
                                            <div class="col-md-8" style="width:84%;padding:0;">
                                                <p style="color:#56678a;font-size: 14px;margin-top:16px;border-bottom:1px dashed #e2edf5;padding-bottom:8px;">您案场的新客户最快回访时间为<span>${analyze.data["fastest"] }</span>天，最晚回访时间<span>${analyze.data["latest"] }</span>天;</p>
                                                <!-- <p style="color:#56678a;font-size: 14px;margin-top:8px;border-bottom:1px dashed #e2edf5;padding-bottom:8px;"><span>90%</span>的新客户会在首访后<span>7天-29天</span>间产生第一次回访；</p>
                                                <p style="color:#56678a;font-size: 14px;margin-top:8px;border-bottom:1px dashed #e2edf5;padding-bottom:8px;"><span>75%</span>的新客户会在<span>7天-23天</span>间产生第一次回访；</p>
                                                 --><p style="color:#56678a;font-size: 14px;margin-top:8px;border-bottom:1px dashed #e2edf5;padding-bottom:8px;"><span>${analyze.data["mostP"] }</span>的回头客会发生在<c:forEach items="${analyze.data['mostD']}" var="d">第${d }天</c:forEach>;</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-2" style="width:8%;padding:0;margin-left:2%;text-align:center;">
                                                <span style="color:#ffffff;font-size: 14px;width:18px;height:18px;line-height:18px;border-radius:2px;background:#41bfe7;display:inline-block;text-align:center;">2</span>
                                            </div>
                                            <div class="col-md-8" style="width:84%;padding:0;">
                                                <p style="color:#56678a;font-size: 14px;border-bottom:1px dashed #e2edf5;padding-bottom:8px;">在29天之后您案场的客户回头率为<span>${analyze.data["turnBackByDate"] }</span>;</p>
                                                
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-2" style="width:8%;padding:0;margin-left:2%;text-align:center;">
                                                <span style="color:#ffffff;font-size: 14px;width:18px;height:18px;line-height:18px;border-radius:2px;background:#f8d73a;display:inline-block;text-align:center;">3</span>
                                            </div>
                                            <div class="col-md-8" style="width:84%;padding:0;">
                                                <p style="color:#56678a;font-size: 14px;border-bottom:1px dashed #e2edf5;padding-bottom:8px;">您案场的客户回访率为<span>${analyze.data["turnBack"] }</span>;</p>
                                                
                                            </div>
                                        </div>
                                        <!-- <div class="row">
                                            <div class="col-md-2" style="width:8%;padding:0;margin-left:2%;text-align:center;">
                                                <span style="color:#ffffff;font-size: 14px;width:18px;height:18px;line-height:18px;border-radius:2px;background:#aad77e;display:inline-block;text-align:center;">4</span>
                                            </div>
                                            <div class="col-md-8" style="width:84%;padding:0;">
                                                <p style="color:#56678a;font-size: 14px;border-bottom:1px dashed #e2edf5;padding-bottom:8px;">您案场的客户回访率为<span>39</span>;</p>
                                                
                                            </div>
                                        </div> -->
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
    <script src="static/js/clientAnalyze.js"></script>
   <script type="text/javascript">
       var myAnalyze = echarts.init(document.getElementById('analyzePic'));
        var option = {           
               
                color: ['#b0e2ef'],
                    tooltip : {
                        trigger: 'axis',
                       
                    },
                   grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis : [
                        {
                            name:"天",
                            type : 'category',
                            data : ['1天以内','1天', '2天', '3天', '4天', '5天', '6天', '7天','8天', '9天', '10天', '11天', '12天', '13天', '14天','15天', '16天', '17天', '18天', '19天', '20天', '21天','22天', '23天', '24天', '25天', '26天', '27天', '28天','29天','30天及以上'],
                            axisTick: {
                            	alignWithLabel: true
                            },

                        }
                    ],
                    yAxis : [
                        {
                            type : 'value',
                            name:"人数/人"
                        }
                    ],
                    series : [
                        {
                            name:'人数',
                            type:'bar',
                            barWidth: '60%',
                            data:${analyze.histogram}
                        }
                    ]
               };
     myAnalyze.setOption(option);

   </script>
    <script type="text/javascript">
    	
       var myShan = echarts.init(document.getElementById('shanPic'));
        var optionTwo = {           
               color: ['#24b6e4','#f9d21a','#7266ba','#9fd269'],
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
    
    series : [
        {
            //name: '访问来源',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                {value:${analyze.pie["1"]}, name:'第一周'},
                {value:${analyze.pie["2"]}, name:'第二周'},
                {value:${analyze.pie["3"]}, name:'第三周'},
                {value:${analyze.pie["4"]}, name:'其他'},
                
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
     myShan.setOption(optionTwo);

   </script>
</body>
</html>
    