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
                    <li><a href="###" class="noDrop"><span>案场设置</span></a></li>                
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
                    <div class="col-md-12 listOne" style="width:98%;margin-left:1%;margin-top:20px;">
                     
                      <div class="row" >
                        <div class="col-md-12">
                          <p style="margin-left: 1%;margin-bottom: 10px; width: 98%;margin-top:30px;text-align: left;font-size: 14px;color: #616b88;" id="xieyi">
                          </p>
                        </div>
                      </div>
                      <div class="row hideXie" >
                        <div class="col-md-12" style="margin-left: 1%;">
                            <div class="checkbox">
                                <input type="checkbox" value="" id="0" name="agree">
                                <label for="0" style="color:#616b88;font-size:14px;">我已阅读以上条款，同意条款所选全部内容</label>
                            </div>
                      	</div>
	                    <div class="row">
	                        <div class="col-md-12">
	                            <button  style="background: #c4c4c4; width:10%;height: 36px;line-height: 36px;border: 0;border-radius: 5px;color: #fff;margin-left:45%;margin-bottom: 20px;" disabled="disabled" id="nextButton">下一步</button>
	                        </div>
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
	  agreement();
	  
	  $("input[name='agree']").change(function() {
          if ($(this).prop("checked") == true) {
              $("#nextButton").removeAttr('disabled');
              $("#nextButton").css("background","#46c1de");
          }else if($(this).prop("checked") == false){
              $("#nextButton").attr("disabled",true)
              $("#nextButton").css("background","#c4c4c4");
          }

   })
   
   $("#nextButton").click(function(){
	   window.location.href="to_fensales_setting";
   })
  })
   function agreement(){
	  $.ajax({
			type:"post",
			url:"get_fensales_protocol",
			async:true,   			
			success:function(data){
				if(data.code == 200){
					$("#xieyi").html(data.data.content)
				}else if (data.code == 201){
					
					$(".hideXie").hide();
					alert("暂无协议");
					return false;
				}
				
				
			}
		})
  }
  </script>
   

</body>
</html>
    