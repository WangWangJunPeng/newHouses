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
      .error{font-size:12px;color:#ff6161;}
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
                    <div class="col-md-12 listOne" style="width:98%;margin-left:1%;margin-top:10px;">
                      <div class="row">
                        <div class="col-md-12">
                          <p style="margin-left:2%;margin-top:20px;font-size:16px;color:#616b88;">填写分销规则后，展开分销业务</p>
                        </div>
                      </div>
                     <form id="myForm">
                     <input id="projectId" name="projectId" type="hidden"/>
                      <div class="row" style="margin-left:5%;margin-top:30px;font-size:15px;color:#616b88;">
                        <div class="col-md-2">
                          <p style="text-align: right; ">是否支持带看</p>
                        </div>
                        <div class="col-md-5">
                          <div class="radio-inline">
                            <label class="radio-inline">
                              <input type="radio" name="checkIsDaiKan" id="checkIsDaiKan" checked  value="1" > 是
                            </label>
                            <label class="radio-inline">
                              <input type="radio" name="checkIsDaiKan" id="checkNoDaiKan" value="0" disabled> 否
                            </label>
                          </div>
                        </div>
                      </div>
                      <div class="row" style="margin-left:5%;margin-top:30px;font-size:15px;color:#616b88;">
                        <div class="col-md-2">
                          <p style="text-align: right; ">带看佣金<span class="redStar">*</span></p>
                        </div>
                        <div class="col-md-5" style="margin-top: -5px;">
                          <input type="text" id="daiKanMoney" name="daiKanMoney" style="background: #f0f4fb; border: 1px solid #e0e7ee;height: 36px;line-height: 36px;padding-left: 5px;border-radius: 5px;"> 
                          <span>%</span>
                      </div>
                    </div>

                      <div class="row" style="margin-left:5%;margin-top:30px;font-size:15px;color:#616b88;">
                        <div class="col-md-2">
                          <p style="text-align: right; ">分销佣金<span class="redStar">*</span></p>
                        </div>
                        <div class="col-md-5" style="margin-top: -5px;">
                          <input type="text" id="fenXiaoMoney" name="fenXiaoMoney" style="background: #f0f4fb; border: 1px solid #e0e7ee;height: 36px;line-height: 36px;padding-left: 5px;border-radius: 5px;"> 
                          <span>%</span>
                      </div>
                     </div>
                  <div class="row" style="margin-left:5%;margin-top:30px;font-size:15px;color:#616b88;">
                        <div class="col-md-2">
                          <p style="text-align: right; ">有效时间<span class="redStar">*</span></p>
                        </div>
                        <div class="col-md-5" style="margin-top: -5px;">
                          <input type="text" id="validDays" name="validDays" style="background: #f0f4fb; border: 1px solid #e0e7ee;height: 36px;line-height: 36px;padding-left: 5px;border-radius: 5px;"> 
                          <span>小时</span>
                      </div>
                  </div>
                  <div class="row" style="margin-left:5%;margin-top:30px;font-size:15px;color:#616b88;">
                        <div class="col-md-2">
                          <p style="text-align: right; ">客户保护期<span class="redStar">*</span></p>
                        </div>
                        <div class="col-md-5" style="margin-top: -5px;">
                          <select name="custormerProtectDays" id="custormerProtectDays" class="form-control" style="width: 175px;background: #f0f4fb;">
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
                  <div class="row" style="margin-left:5%;margin-top:20px;font-size:15px;color:#616b88;">
                        <div class="col-md-2">
                          <p style="text-align: right; ">支持异地销售</p>
                        </div>
                        <div class="col-md-5">
                          <div class="radio-inline">
                            <label class="radio-inline">
                              <input type="radio" name="checkIsYiDi" id="checkIsYiDi"  checked value="1"> 是
                            </label>
                            <label class="radio-inline">
                              <input type="radio" name="checkIsYiDi" id="checkNoYiDi" value="0"> 否
                            </label>
                          </div>
                        </div>
                  </div>
                  <div class="row" style="margin-left:5%;margin-top:30px;font-size:15px;color:#616b88;">
                        <div class="col-md-2">
                          <p style="text-align: right; ">异地销售佣金<span class="redStar">*</span></p>
                        </div>
                        <div class="col-md-5" style="margin-top: -5px;">
                          <input type="text" id="yiDiSalesCommission" name="yiDiSalesCommission" style="background: #f0f4fb; border: 1px solid #e0e7ee;height: 36px;line-height: 36px;padding-left: 5px;border-radius: 5px;"> 
                          <span>%</span>
                      </div>
                  </div>
                  <div class="row" style="margin-left:5%;margin-top:30px;font-size:15px;color:#616b88;">
                        <div class="col-md-2">
                          <p style="text-align: right; ">异地报备有效期<span class="redStar">*</span></p>
                        </div>
                        <div class="col-md-5" style="margin-top: -5px;">
                          <input type="text" id="yiDiValidity" name="yiDiValidity" style="background: #f0f4fb; border: 1px solid #e0e7ee;height: 36px;line-height: 36px;padding-left: 5px;border-radius: 5px;"> 
                          <span>小时</span>
                      </div>
                  </div>
                  <div class="row" style="margin-left:5%;margin-top:20px;font-size:15px;color:#616b88;">
                        <div class="col-md-2">
                          <p style="text-align: right; ">快速结佣</p>
                        </div>
                        <div class="col-md-5">
                          <div class="radio-inline">
                            <label class="radio-inline">
                              <input type="radio" name="checkIsFast" id="checkIsFast" checked value="1"> 是
                            </label>
                            <label class="radio-inline">
                              <input type="radio" name="checkIsFast" id="checkNoFast" value="0"> 否
                            </label>
                          </div>
                        </div>
                  </div>
                  <div class="row" style="margin-left:5%;margin-top:30px;font-size:15px;color:#616b88;">
                        <div class="col-md-2">
                          <p style="text-align: right; ">说明</p>
                        </div>
                        <div class="col-md-5" style="margin-top: -5px;">
                          <textarea name="description" id="description" style="background: #f0f4fb; border: 1px solid #e0e7ee;height: 80px;padding-left: 5px;border-radius: 5px;width: 100%;resize:none;"></textarea> 
                      </div>
                  </div>
                 
                  <div class="row" style="margin-left:15%;margin-top:70px;font-size:15px;color:#616b88;">
                        <div class="col-md-3" style="margin-bottom: 50px;">
                          <span id="cancel" style="display:inline-block;cursor:pointer;text-align:center;background:#f0f4fb; width: 120px;height: 36px;line-height: 36px;border: 0;border-radius: 5px;color: #999;">取消</span>
                        </div>
                        <div class="col-md-3">
                         <input  type="submit" value="确定" style="text-align:center;background: #46c1de; width: 120px;height: 36px;line-height: 36px;border: 0;border-radius: 5px;color: #fff;">
                      </div>
                  </div>
</form>











                  </div>

                  
         
        </div>

      </div>
    </div> 
    </div>
    
   	<script src="static/js/jquery-3.1.1.min.js"></script>  
   	<script type="text/javascript" src="static/js/jquery.validate.min.js"></script>  
    <script src="static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="static/js/calendarM.js"></script> 
    <script src="static/js/archon.js"></script>
    <script type="text/javascript">
	    $(document).ready(function(){
	    	getGudeInfo();
	    	$("#cancel").click(function(){
	    		window.location.href = "to_fensales_setting";
	    	})
	    	jQuery.validator.addMethod("daiKanMoney",function(value,element){  
                var waM =/^(([1-9][0-9])|([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (waM.test(value));
            },"(请输入保留两位小数)");
	    	jQuery.validator.addMethod("fenXiaoMoney",function(value,element){  
                var saleM =/^(([1-9][0-9])|([0-9]*?\.\d{2}))$/;
                return this.optional(element) || (saleM.test(value));
           },"(请输入保留两位小数)");
			jQuery.validator.addMethod("validDays",function(value,element){  
			                var electday = /^[1-9]\d*$/;
			                return this.optional(element) || (electday.test(value));
			            },"(请输入正整数)");
			jQuery.validator.addMethod("yiDiSalesCommission",function(value,element){  
			                var otherS =/^(([1-9][0-9])|([0-9]*?\.\d{2}))$/;
			                return this.optional(element) || (otherS.test(value));
			            },"(请输入保留两位小数)");
			jQuery.validator.addMethod("yiDiValidity",function(value,element){  
			                var dT =/^[1-9]\d*$/;
			                return this.optional(element) || (dT.test(value));
			            },"(请输入正整数)");
	    	$("#myForm").validate({
	            rules:{
	            	daiKanMoney:{
	                     required:true,
	                     daiKanMoney:true
	                 },
	                 fenXiaoMoney:{
	                    required:true,
	                    fenXiaoMoney:true
	                 },
	                 validDays:{
	                     required:true,
	                     validDays:true,

	                 },
	                 yiDiSalesCommission:{
	                     required:true,
	                     yiDiSalesCommission:true
	                 },
	                 yiDiValidity:{
	                     required:true,
	                     yiDiValidity:true,

	                 },
	                 description:{
	                    
	                     maxlength:200,

	                 }
	            },
	            messages:{
	            	daiKanMoney:{
	                     required:"(不能为空)",
	                    
	                 },
	                 fenXiaoMoney:{
	                     required:"(不能为空)",
	                    
	                 },
	                 validDays:{
	                     required:"(不能为空)",
	                    
	                 },
	                 yiDiSalesCommission:{
	                     required:"(不能为空)",
	                    
	                 },
	                 yiDiValidity:{
	                     required:"(不能为空)",
	                    
	                 },
	                 description:{
	                    
	                    maxlength:"(200字以内)"
	                 },
	             },
	            errorPlacement: function(error, element){ 
	            
	                error.appendTo(element.parent()); 
	            },
	            submitHandler:function(){
	            	$.ajax({
		    			type:"post",
		    			url:"to_fensales_add_updata_pro_guide",
		    			async:true,
		    			data:$("#myForm").serialize(),
		    			success:function(data){
		    				window.location.href = data.url;
		    			}
		    		})
	    	      },
	        
	 			});
	    		
	    	})   
	    
	    function getGudeInfo(){
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
				$("#projectId").val(data.projectId);
				console.log(data.hasOwnProperty("projectId"))
				if(data.isDaiKan!=1){
					$("#checkIsDaiKan").prop("checked",false);
					$("#checkNoDaiKan").prop("checked",true);
				}else if(data.isDaiKan == 1){
					$("#checkIsDaiKan").prop("checked",true);
					$("#checkNoDaiKan").prop("checked",false);
				}				
				$("#daiKanMoney").val(data.daiKanMoneyStr);
				$("#fenXiaoMoney").val(data.fenXiaoMoneyStr);
				$("#validDays").val(data.validDays);
				$("#custormerProtectDays").val(data.custormerProtectDays);
				if(data.isYiDi!=1){
					$("#checkIsYiDi").prop("checked",false);
					$("#checkNoYiDi").prop("checked",true);
				}else if(data.isYiDi == 1){
					$("#checkIsYiDi").prop("checked",true);
					$("#checkNoYiDi").prop("checked",false);
				}
				$("#yiDiSalesCommission").val(data.yiDiSalesCommissionStr);
				$("#yiDiValidity").val(data.yiDiValidity);
				if(data.isFast!=1){
					$("#checkIsFast").prop("checked",false);
					$("#checkNoFast").prop("checked",true);
				}else if(data.isFast == 1){
					$("#checkIsFast").prop("checked",true);
					$("#checkNoFast").prop("checked",false);
				}
				$("#description").val(data.description);
			}
		} 
    	 
    
    
    </script>
   

</body>
</html>
 