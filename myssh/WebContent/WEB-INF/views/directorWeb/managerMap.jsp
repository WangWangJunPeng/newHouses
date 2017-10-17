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
    <link rel="stylesheet" type="text/css" href="static/css/managerMap.css" />
   
  <!-- <link rel="stylesheet" type="text/css" href="static/css/cityPicker.css"> -->
    <link rel="stylesheet" type="text/css" href="static/css/calendarM.css" />
   
<!--             <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=7d1ed249eb6d3503e90179890468dd74&plugin=AMap.Autocomplete,AMap.PlaceSearch"></script>
 -->     
     <style type="text/css">
        
        p,span,tr,td,a,li,option,select,button{font-family:"微软雅黑" !important;}
        #maskBox{display:none;background-color:#ccc;opacity:0.9;position:absolute;right:0px;top:50px;width:1640px;height:1000px;z-index:20;}
    </style>
        
    
</head>
<body>
    <div class="frame" style="height:950px !important;">
    <div id="maskBox"></div>
        <div class="sidebar" style="background:#616b88 !important;">
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
                        <a href="to_director_page_index" class="noDrop "><span>今日案场</span></a>
                         <!-- <ul style="display:block;border:0;">
                            <li><a href="to_goToManagerMap" style="padding-left:5px;color:#46c1de;font-size: 14px;"><span>地图</span></a></li>
                        </ul> -->
                    </li>
                     <li><a href="to_goToManagerMap" class="noDrop"><span style="color:#46c1de;">门店地图</span></a></li> 
                    <li><a href="to_data_analysis_page" class="noDrop"><span>数据分析</span></a></li>
                    <li><a href="to_data_statement_page" class="noDrop"><span>数据报表</span></a></li>
                    <li><a href="to_order_page" class="noDrop"><span>订单</span></a></li>
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
                        </ul>
                    </li><!-- /dropdown -->
                    <li class="dropdown user-name">
                        <a class="dropdown-toggle" data-toggle="dropdown" style="color:#333333;border:0;"><img src="static/images/sanchong.jpg" class="user-avatar" alt="" /><span style="font-size:16px;color:#616b88;">${sessionScope.userInfo.userCaption}</span></a>
                            <ul class="dropdown-menu right inbox user" style="z-index:2000;">
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
            <div id="main-content" style="padding:0;height:900px;">
               <div id="allmap" style="height:925px;">
               
                </div>
               <!--  <div class="shopDetails">
                    <div class="topShop">
                        <span style="display:inline-block;width:4px;height:14px;background:#ff6161;"></span>
                        <span style="font-size: 16px;color:#565e78;" >链家滨江地产</span>
                    </div>
                    <div class="secondShop">
                        <span style="font-size: 16px;color:#565e78;">链家地产</span>
                    </div>
                    <div class="firstShop">
                        <span>已售出房源：<span>20</span></span>

                        <span style="margin-left:30px;">已备案人数：<span>10</span></span>

                        <span style="margin-left:30px;">门店经纪人数：<span>10</span></span>
                    </div>
                    <div class="fourthImg">
                        <div class="imgBox">
                            <span >></span>
                        </div>
                            
                        <div class="imgRightBox">
                            <div class="everyImg">
                                 <img src="images/timg.jpg" alt="" style="width:320px;height:210px;"/>
                            </div>
                            <div class="everyImg" style="margin-left:10px;">
                                <img src="images/timg.jpg" alt="" style="width:130;height:100px;"/>
                                <img src="images/timg.jpg" alt="" style="width:130px;height:100px;margin-left:8px;"/>
                               
                            </div>
                            <div class="everyImg" style="margin-left:10px;margin-top:10px;">
                                <img src="images/timg.jpg" alt="" style="width:130;height:100px;"/>
                                <img src="images/timg.jpg" alt="" style="width:130px;height:100px;margin-left:8px;"/>
                               
                            </div>

                        </div>
                    </div>
                    
                    <div class="topShop">
                        <span style="display:inline-block;width:4px;height:14px;background:#ff6161;"></span>
                        <span style="font-size: 16px;color:#565e78;">门店信息</span>
                    </div>
                    <div class="information">
                        <span>名称：</span><span>链家地产</span>
                    </div>
                    <div class="information">
                        <span>服务等级：</span><span>1</span>
                    </div>
                    <div class="information">
                        <span>服务年限：</span><span>41</span>
                    </div>
                    <div class="information">
                        <span>地址：</span><span>比较好根据韩国</span>
                    </div>
                </div> -->
                <div id="mapList" style="width:410px;background:#e4e8ef;position:absolute;top:50px;right:0;z-index:100;">
                    <input type="text" id="cityChoice" placeholder="请选择城市" class="cityChoose"  >
                    <input type="hidden" id="province" value="">
                    <input type="hidden" id="city" value="">
                    <!-- <button class="changeBtn">筛选</button> -->
                    <div class="shopList" style="background:#f0f4fb;height:880px;overflow:auto;margin-top:12px;" >
                    	<div class="topBack"><span class="foundList">为您找到<span id="zongNum"></span><span>个门店</span></span></div>
                    	<div class="allList">
                    	
                    	</div>
                    </div>
          		
                     
                   
               </div>
               <div class="proviceCity" style="display:none;width:360px;height:200px;background:#ffffff;position:absolute;top:100px;right:30px;;z-index:1100;border:1px solid #999;border-radius:6px;">
<!--            			<span style="display:inline-block;font-size:10px;width:48px;height:20px;line-height:20px;background:#f1f1f1;color:#83889a;text-align:center; border-radius:4px;margin-left:8px;margin-top:8px;">jkjkjkj</span>
 -->         
        		</div>
            </div>
          
        </div>

       
    </div> 
    
    
    <script src="static/js/jquery-3.1.1.min.js"></script>
    <script src="static/js/managerMap.js"></script>
    
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=dxCgW5uXbilxuIKHY7eYpujkgdsqSj3I"></script>
     
     <script type="text/javascript" src="http://api.map.baidu.com/library/RichMarker/1.2/src/RichMarker_min.js "></script>
	  
    <script src="static/js/bootstrap.min.js"></script>
   <!--  <script type="text/javascript" src="static/js/cityData.js"></script>
<script type="text/javascript" src="static/js/cityPicker.js"></script> -->
    <script type="text/javascript" src="static/js/calendarM.js"></script>  
   
    <script src="static/js/archon.js"></script>
    <script>
    function getProvS(){
    	$(".proviceCity").show();
		getAllProv();
	}
   
	function getAllProv(){
		$.ajax({
			type : "post",
			url : "get_all_prov",
			async:false,
			//data : {selectStatus : $("#selectStyleId").val()},
			success : function(data) {
				data = eval("(" + data + ")");
				//console.log(data);
				fileProviceIntoDiv(data.root);
				//formWin();
			}
		});
	};
	function fileProviceIntoDiv(data){
		//console.log(data);
		var p = '';
		$.each(data,function(v,o){
			//console.log(o);
			/* s += '<a href="javascript:;" onclick="getCurrentProvOfShi('+o.cityId+')">'+o.cityName+'</a>&nbsp;'; */
			p += '<span style="cursor:pointer;display:inline-block;font-size:10px;width:48px;height:20px;line-height:20px;background:#f1f1f1;color:#83889a;text-align:center; border-radius:4px;margin-left:8px;margin-top:8px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;" onclick="getCurrentProvOfShi('+o.cityId+')">'+o.cityName+'</span>'
		});
		if(data.length>0){
			$(".proviceCity").html(p);
		}
	}
	
	function getCurrentProvOfShi(id){
		//alert(id);
		
		$.ajax({
			type : "post",
			url : "menu_list_city_area",
			async:false,
			data : {shengOrShi : id},
			success : function(data) {
				data = eval("(" + data + ")");
				//console.log(data);
				fileShiIntoDiv(data.root);
				//formWin();
			}
		});
	}
	function fileShiIntoDiv(data){
		var s = '';
		$.each(data,function(v,o){
			
			
			s += '<span data-value='+o.cityId+' onclick=fileNameIntoInput(this) style="cursor:pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;display:inline-block;font-size:10px;width:48px;height:20px;line-height:20px;background:#f1f1f1;color:#83889a;text-align:center; border-radius:4px;margin-left:8px;margin-top:8px;">'+o.cityName+'</span>'
		});
		if(data.length>0){
			$(".proviceCity").html(s);
		}
	}
	function fileNameIntoInput(obj){
		$("#city").val($(obj).html());
		
		chenshi($(obj).html())
		var shiId = $(obj).data("value");
		$(".allList").html("");
		$(".shopDetails").remove();
		//getOneShopList();
		//getShopsListNum();
		$("#cityChoice").val($(obj).html());
		$(".proviceCity").hide();
	}
	
	</script>
	
	
	
	
	
</body>
</html>
    