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
    <link rel="stylesheet" type="text/css" href="static/css/distributionDone.css" />
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

                <div class="row">
                    <div class="col-md-2 " style="width:20%;margin-left:80%;margin-top:20px;">
                                
                                <span style="color:#616b88;font-size:14px;display:inline-block;margin-left:56%;">分销业务</span>
                                <span style="width:60px;height:20px;display:inline-block;border:1px solid #c4c4c4;border-radius:12px;position:absolute;margin-left:2%;">
                                    <button style="width:29px;height:20px;background:#46c1de;border:0;border-top-left-radius:12px;border-bottom-left-radius:12px;color:#ffffff;font-size:12px;position:absolute;" id="openSwitch">展开</button>
                                    <button style="width:29px;height:20px;background:#46c1de;border:0;border-top-right-radius:12px;border-bottom-right-radius:12px;color:#ffffff;font-size:12px;position:absolute;right:0;" id="closeSwitch">关闭</button>
                                    <span style="width:26px;height:18px;display:inline-block;border-radius:16px;background:#ffffff;position:absolute;top:1px;left:30px;" id="ball"></span>
                                </span>
                            
                    </div>
                </div>
               <div class="row ">
                    <div class="col-md-12 listOne" style="width:98%;margin-left:1%;margin-top:20px;">
                        <div class="row">
                            <div class="col-md-4" style="padding-left: 30px;padding-top: 20px;position: relative;">
                                <p style="color: #7266ba;font-size: 26px;width: 100%;text-align: right;"><img src="static/images/daikanyongjin.png" alt="" style="position: absolute;left: 32px;top: 25px;"><span id="daiKanMoney"></span></p>
                                <p style="color: #7266ba;font-size: 14px;">带看佣金</p>
                                <div class="progress">
                                    <div class="progress-bar progress-bar-pur daikanRate" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 30%;">
                                    </div>
                                 </div>
                            </div>
                            <div class="col-md-4" style="padding-left: 30px;padding-top: 20px;position: relative;">
                                <p style="color: #ff9666;font-size: 26px;width: 100%;text-align: right;"><img src="static/images/fenxiaoyongjin.png" alt="" style="position: absolute;left: 32px;top: 25px;"><span id="fenXiaoMoney"></span></p>
                                <p style="color: #ff9666;font-size: 14px;">分销佣金</p>
                                <div class="progress">
                                    <div class="progress-bar progress-bar-org fenxiaoRate" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 30%;">
                                    </div>
                                 </div>
                            </div>
                            <div class="col-md-4" style="padding-left: 30px;padding-top: 20px;position: relative;">
                                <p style="color: #9fd269;font-size: 26px;width: 100%;text-align: right;"><img src="static/images/yidixiaoshouyongjin.png" alt="" style="position: absolute;left: 32px;top: 25px;"><span id="yiDiSalesCommission"></p>
                                <p style="color: #9fd269;font-size: 14px;">异地销售佣金</p>
                                <div class="progress">
                                    <div class="progress-bar progress-bar-gre yidiRate" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 30%;">
                                    </div>
                                 </div>
                            </div>
                        </div>
                      
                    </div> 
                    
                </div>

                <div class="row" style="margin-top: 20px;">
                    <div class="col-md-2" style="width:20%;margin-left:1%;">
                        <div class="row">
                            <div class="youxiao">
                                <p style="width: 100%;text-align: center;font-size: 36px;color: #fff;padding-top: 20px;" id="validDays"></p>
                                <p style="width: 100%;text-align: center;font-size: 12px;color: #fff;">报备有效期（小时）</p>
                            </div>
                        </div>
                        <div class="row" style="margin-top: 20px;">
                            <div class="yidi">
                                <p style="width: 100%;text-align: center;font-size: 36px;color: #fff;padding-top: 20px;" id="yiDiValidity"></p>
                                <p style="width: 100%;text-align: center;font-size: 12px;color: #fff;">异地报备有效期（小时）</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-5 listOne" style="width:38%;margin-left:1%;">
                        <div class="col-md-12">
                            <div class="row">
                                <p style="background: #f6f9fd;font-size: 12px;text-align: left;width: 100%;height: 46px;line-height: 46px;"><img src="static/images/shifoudaik.png" alt="" style="margin-right: 9px;width: 46px;height: 46px;">是否支持带看</p>
                            </div>
                            <div class="row" style="background: #fff;font-size: 12px;margin-top: -10px;">
                                <div class="col-md-4" style="height: 46px;line-height: 46px;">
                                    <p style="text-align: left;margin-left: 40px; ">支持带看</p>
                                </div>
                                <div class="col-md-5 col-md-offset-3" style="margin-top: 12px;line-height: 20px;text-align:right">
                                    <div class="radio-inline">
                                        <label class="radio-inline">
                                            <input type="radio" name="checkIsDaiKan" id="checkIsDaiKan" disabled> 是
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="checkIsDaiKan" id="checkNoDaiKan" disabled> 否
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
        
                        <div class="col-md-12">
                            <div class="row">
                                <p style="background: #f6f9fd;font-size: 12px;text-align: left;width: 100%;height: 46px;line-height: 46px;"><img src="static/images/shifoujieyong.png" alt="" style="margin-right: 9px;width: 46px;height: 46px;">是否快速结佣</p>
                            </div>
                            <div class="row" style="background: #fff;font-size: 12px;margin-top: -10px;">
                                <div class="col-md-4" style="height: 46px;line-height: 46px;">
                                    <p style="text-align: left;margin-left: 40px; ">快速结佣</p>
                                </div>
                                <div class="col-md-5 col-md-offset-3" style="margin-top: 12px;line-height: 20px;text-align:right">
                                    <div class="radio-inline">
                                        <label class="radio-inline">
                                            <input type="radio" name="checkIsFast" id="checkIsFast" disabled> 是
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="checkIsFast" id="checkNoFast" disabled> 否
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-12">
                            <div class="row">
                                <p style="background: #f6f9fd;font-size: 12px;text-align: left;width: 100%;height: 46px;line-height: 46px;"><img src="static/images/shifouyisdi.png" alt="" style="margin-right: 9px;width: 46px;height: 46px;">是否支持异地销售</p>
                            </div>
                            <div class="row" style="background: #fff;font-size: 12px;margin-top: -10px;">
                                <div class="col-md-4" style="height: 46px;line-height: 46px;">
                                    <p style="text-align: left;margin-left: 40px; ">支持异地销售</p>
                                </div>
                                <div class="col-md-5 col-md-offset-3" style="margin-top: 12px;line-height: 20px;text-align:right;">
                                    <div class="radio-inline">
                                        <label class="radio-inline">
                                            <input type="radio" name="checkIsYiDi" id="checkIsYiDi" disabled> 是
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="checkIsYiDi" id="checkNoYiDi" disabled> 否
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>

                    <div class="col-md-4 listOne" style="width:38%;margin-left:1%;">
                        <div class="col-md-12">
                            <div class="row">
                                <p style="background: #f6f9fd;font-size: 12px;text-align: left;width: 100%;height: 46px;line-height: 46px;"><img src="static/images/kehubaohu.png" alt="" style="margin-right: 9px;width: 46px;height: 46px;">客户保护期</p>
                            </div>
                            <div class="row" style="background: #fff;font-size: 12px;margin-top: -10px;height: 46px;">
                                <select class="form-control" disabled name="custormerProtectDays" id="custormerProtectDays" style="width: 80%;background: #f6f9fd;margin-top: 10px;font-size: 12px;margin-top: 8px;margin-left: 10%;">
                                    <option value="1">1个月</option>
		                            <option value="2">2个月</option>
		                            <option value="3">3个月</option>
		                            <option value="4">4个月</option>
		                            <option value="5">5个月</option>
		                            <option value="6">6个月</option>
		                            <option value="7">7个月</option>
		                            <option value="8">8个月</option>
		                            <option value="9">9个月</option>
		                            <option value="10">10个月</option>
		                            <option value="11">11个月</option>
		                            <option value="12">12个月</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="row">
                                <p style="background: #f6f9fd;font-size: 12px;text-align: left;width: 100%;height: 46px;line-height: 46px;"><img src="static/images/shuoming.png" alt="" style="margin-right: 9px;width: 46px;height: 46px;">说明</p>
                            </div>
                            <div class="row" style="background: #fff;font-size: 12px;margin-top: -10px;">
                                <p style="width: 90%;margin-left: 5%;margin-top: 10px;" id="description"></p>
                            </div>
                        </div>
                    </div>


                </div>

                <div class="row">
                    <div class="col-md-12">
                        <button id="alterButton" style="width: 10%;margin-left: 45%;font-size: 12px;margin-top: 50px;height: 36px;line-height: 36px;border-radius: 5px;border: 0;background: #46c1de;color: #fff;">修改</button>
                    </div>
                    
                </div>


                 <div class="tishi" style="display:none;">
                    <div class="row">
                        <div class="col-md-12">
                            <p style="background:#fff;color: #616b88;height: 36px;line-height: 36px;padding-left: 10px;">提示</p>
                        </div>
                        
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <p style="width: 90%;margin-left: 5%;color: #fe1e1e;margin-top: 20px;">是否继续“关闭”分销业务操作，关闭后分销业务规则不可恢复!</p>
                        </div>
                        <div class="col-md-12">
                            <div class="col-md-6">
                                <button style="color: #a2abba;background: #d6dfe6;" id="cancelBtn">取消</button>
                            </div>
                            <div class="col-md-6">
                                <button style="color: #fff;background: #46c1de;" id="sureBtn">确定</button>
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
    		getGudeInfo();
    		$("#closeSwitch").click(function(event) {
                $("#ball").css("left","30px");
                $("#openSwitch").css("background","#46c1de");
                $("#closeSwitch").css("background","#46c1de");
                $(".tishi").hide()
            });
            $("#openSwitch").click(function(event) {
                $("#ball").css("left","2px");
                $("#openSwitch").css("background","#c4c4c4");
                $("#closeSwitch").css("background","#c4c4c4");
                $(".tishi").show()
            });
    		$("#alterButton").click(function(){
    			window.location.href ="to_fensales_setting";
    		});
    		$("#sureBtn").click(function(){
    			
    			$.ajax({
        			type:"post",
        			url:"fensales_close",
        			async:true,   			
        			success:function(data){
        				console.log(data)
        				if(data.code == 200){
        					window.location.href = "to_fensales_introduce"
        				}else if(data.code == 201){
        					alert("关闭失败")
        				}
        			}
        		})
    		})
    		
    	})
    function getGudeInfo(){
	    	$.ajax({
    			type:"post",
    			url:"get_current_pro_guide_info",
    			async:true,   			
    			success:function(data){
    				data = eval("("+data+")");
    				console.log(data.data)
					fillInGuide(data.data);
    			}
    		})
	    }
	    function fillInGuide(data){
			if(data.hasOwnProperty("projectId")){
				
				if(data.isDaiKan!=1){
					$("#checkIsDaiKan").prop("checked",false);
					$("#checkNoDaiKan").prop("checked",true);
				}else if(data.isDaiKan == 1){
					$("#checkIsDaiKan").prop("checked",true);
					$("#checkNoDaiKan").prop("checked",false);
				}				
				$("#daiKanMoney").html(data.daiKanMoneyStr+'%');
				$(".daikanRate").css("width",data.daiKanMoneyStr+'%')
				$("#fenXiaoMoney").html(data.fenXiaoMoneyStr+'%');
				$(".fenxiaoRate").css("width",data.fenXiaoMoneyStr+'%')
				
				$("#validDays").html(data.validDays);
				$("#custormerProtectDays").val(data.custormerProtectDays);
				if(data.isYiDi!=1){
					$("#checkIsYiDi").prop("checked",false);
					$("#checkNoYiDi").prop("checked",true);
				}else if(data.isYiDi == 1){
					$("#checkIsYiDi").prop("checked",true);
					$("#checkNoYiDi").prop("checked",false);
				}
				$("#yiDiSalesCommission").html(data.yiDiSalesCommissionStr+'%');
				$(".yidiRate").css("width",data.yiDiSalesCommissionStr+'%')
				$("#yiDiValidity").html(data.yiDiValidity);
				if(data.isFast!=1){
					$("#checkIsFast").prop("checked",false);
					$("#checkNoFast").prop("checked",true);
				}else if(data.isFast == 1){
					$("#checkIsFast").prop("checked",true);
					$("#checkNoFast").prop("checked",false);
				}
				$("#description").html(data.description);
			}
		}
    
    
    </script>
   

</body>
</html>
    