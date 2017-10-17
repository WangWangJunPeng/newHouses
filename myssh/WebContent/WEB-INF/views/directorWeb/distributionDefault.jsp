<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
    <link rel="stylesheet" type="text/css" href="static/css/distributionDefault.css" />
  

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
                   
  
                    <li><a href="to_director_page_index" class="noDrop active" ><span style="color:#46c1de;">今日案场</span></a>
                        <!-- <ul style="display:block;border:0;"> -->
                        <!-- </ul> -->
                    </li> 
                    <li><a href="to_goToManagerMap" class="noDrop"><span>门店地图</span></a></li>                    
                    <li><a href="to_data_analysis_page" class="noDrop"><span>数据分析</span></a></li>
                    <li><a href="to_data_statement_page" class="noDrop"><span>数据报表</span></a></li>
                    <li><a href="to_order_page" class="noDrop"><span>订单</span></a></li>
                    <li><a href="to_manager_team_page" class="noDrop"><span>团队</span></a></li> 
                    <li><a href="to_pCustomerManager" class="noDrop"><span>客户管理</span></a></li>
                    <li><a href="to_fensales_introduce" class="noDrop"><span>案场设置</span></a></li>                           
                </ul>
               <div id="calendar" class="calendar" style="display:none;"></div>
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
                     
                    </li>
           <li class="dropdown hidden-xs">
                        <a class="dropdown-toggle" data-toggle="dropdown"><i class="icon-envelope-alt" style="color:#333;"></i></a>
                   
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

            <div id="main-content">
                <div class="row ">
                    <div class="col-md-4 topRule">
                        <p>
                          <span class="redStar">*</span>
                          <span style="color:#616b88;font-size:14px;">案场设置></span>
                          <span class="disClass">分销业务</span>
                        </p> 
                    </div>
                          
                </div>

               <div class="row ">
                    <div class="col-md-12 listOne" style="width:98%;margin-left:1%;margin-top:10px;">
                      <div class="row">
                            <div class="col-md-2 " style="width:20%;margin-left:80%;margin-top:20px;">
                                <span style="color:#616b88;font-size:14px;display:inline-block;margin-left:56%;">分销业务</span>
                                <span style="width:60px;height:20px;display:inline-block;border:1px solid #c4c4c4;border-radius:12px;position:absolute;margin-left:2%;">
                                    <button style="width:29px;height:20px;background:#c4c4c4;border:0;border-top-left-radius:12px;border-bottom-left-radius:12px;color:#ffffff;font-size:12px;position:absolute;" id="openSwitch">展开</button>
                                    <button style="width:29px;height:20px;background:#c4c4c4;border:0;border-top-right-radius:12px;border-bottom-right-radius:12px;color:#ffffff;font-size:12px;position:absolute;right:0;" id="closeSwitch">关闭</button>
                                    <span style="width:26px;height:18px;display:inline-block;border-radius:16px;background:#ffffff;position:absolute;top:1px;left:2px;" id="ball"></span>
                                </span>
                            </div>
                      </div>
                      <div class="row">
                        <div class="col-md-12">
                           <p style="margin-top:10px;width: 100%;text-align: center;font-size: 18px;height: 36px;line-height: 36px;color: #616b88;">九邑分销业务介绍</p>  
                        </div>
                      </div>
                      <div class="row">
                        <div class="col-md-12">
                          <p style="margin-left: 1%;margin-bottom: 30px; width: 98%;text-align: left;font-size: 14px;color: #616b88;">
                          九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍九邑分销业务介绍
                          </p>
                        </div>
                      </div>
                    
                </div>


            </div>

                  
         
        </div>

       
    </div> 
    </div>
    
   <script src="static/js/jquery-3.1.1.min.js"></script>
    
    <script src="static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="static/js/calendarM.js"></script> 
    <script src="static/js/archon.js"></script>
   	<script type="text/javascript">
        $(document).ready(function(){
        		getGudeInfoCheck();
                $("#closeSwitch").click(function(event) {
                    $("#ball").css("left","30px");
                    $("#openSwitch").css("background","#46c1de");
                    $("#closeSwitch").css("background","#46c1de");
                    window.location.href="to_fensales_deal";
                });
                $("#openSwitch").click(function(event) {
                    $("#ball").css("left","2px");
                    $("#openSwitch").css("background","#c4c4c4");
                    $("#closeSwitch").css("background","#c4c4c4");
                });
        })
		function getGudeInfoCheck(){
			    	$.ajax({
		    			type:"post",
		    			url:"get_current_pro_guide_info",
		    			async:true,   			
		    			success:function(data){
		    				data = eval("("+data+")");
		    				//console.log(data.data)
							fillInGuide(data.data);
		    			}
		    		})
			    }
        function fillInGuide(data){
			if(data.hasOwnProperty("projectId")){
				window.location.href="to_fensales_turn_details";
			}
		} 
   </script>
   

</body>
</html>
    