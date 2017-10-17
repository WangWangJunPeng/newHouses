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
    <link rel="stylesheet" type="text/css" href="static/css/dataStatement.css" />
           <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>

    <link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/fixedcolumns/3.0.1/css/dataTables.fixedColumns.css" />
    <link rel="stylesheet" type="text/css" href="static/css/calendarM.css" />
    <link  rel="icon" href="http://root-12521005170.cossh.myqcloud.com/static/images/titleLogo.png"  />
    <link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
    <link rel="stylesheet" href="static/css/layui.css"  />
 	 <script src="static/js/jquery-3.1.1.min.js"></script>
   <script src="static/js/layer.js"></script>
    <style type="text/css">
    	th,td { white-space:nowrap; } 
    	p,span,tr,td,a,li,option,select,button{font-family:"微软雅黑" !important;}
    </style>
        
    
</head>
<body>
    <div class="frame">
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

                    <li>
                    	<a href="to_director_page_index" class="noDrop" ><span >今日案场</span></a>
                    	<!--  <ul style="display:block;border:0;">
                            <li><a href="to_goToManagerMap" style="padding-left:5px;color:#ffffff;font-size: 14px;"><span>地图</span></a></li>
                        </ul> -->
                    </li> 
                     <li><a href="to_goToManagerMap" class="noDrop"><span>门店地图</span></a></li>
                    <li><a href="to_data_analysis_page" class="noDrop"><span>数据分析</span></a></li>
                    <li><a href="to_data_statement_page" class="noDrop" ><span style="color:#46c1de;">数据报表</span></a></li>
                    <li><a href="to_order_page" class="noDrop"><span>订单</span></a></li>
                    <li><a href="to_manager_team_page" class="noDrop"><span>团队</span></a></li>
                    <li><a href="to_pCustomerManager" class="noDrop"><span>客户管理</span></a>
                    
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
                        </ul> -->
                    </li><!-- /dropdown -->
                    <li class="dropdown user-name">
                        <a class="dropdown-toggle" data-toggle="dropdown" style="color:#333333;border:0;"><img src="static/images/sanchong.jpg" class="user-avatar" alt="" /><span style="font-size:16px;color:#616b88;">${sessionScope.userInfo.userCaption}</span></a>
                            <ul class="dropdown-menu right inbox user">
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
               <div class="row"  style="margin-top:40px;">
               		<div class="col-md-4 col-md-offset-8" >
               			<div class="row" style="text-align:right;">
		                    <div class="col-md-4 "  style="" >
		                       <select class="form-control" id="method">
		                              <option value="visitMethod">接访</option>
		                              <option value="dealMethod">成交与储客</option>
		                              <option value="outMethod">外场</option>
		                              
		                        </select>
		                    </div> 
		                    <div class="col-md-4 " >
		                       <select class="form-control" id="select_time">
		                              <option value="week">最近一周</option>
		                              <option value="half_month">最近半月</option>
		                              <option value="one_month">最近一月</option>
		                              <option value="half_year">最近半年</option>
		                              <option value="one_year">最近一年</option>
		                        </select>
		                    </div> 
		                    <div class="col-md-4 " >
		
		                        <button type="button" style="width:100%;border:0;background:#ffffff;height: 34px;line-height:34px;color:#555555;border: 1px solid #BDC3C7;border-radius: 4px;" onclick="javascript:method1('dataTables_scroll')">下载<img src="static/images/downLoad.png" alt="" style="margin-left:5%;"/></button>
		
		                    </div> 
		                  </div>  
                   </div>
                </div>
                <div id="table-container" style="margin-top:24px;">
                </div>
  
            </div>
          
        </div>

       
    </div> 
    
    
    <!-- <script src="static/js/jquery-3.1.1.min.js"></script> -->
     <script type="text/javascript" src="static/dataTables/js/jquery.js"></script>
    <script type="text/javascript" src="static/js/echarts.js"></script>
   <!--  <script src="js/jquery-ui-1.10.3.custom.min.js"></script>
    <script src="js/jquery.ui.touch-punch.min.js"></script> -->
    <script src="static/js/bootstrap.min.js"></script>
   <!--  <script src="js/bootstrap-select.js"></script>
    <script src="js/bootstrap-switch.js"></script>
    <script src="js/flatui-checkbox.js"></script>
    <script src="js/flatui-radio.js"></script>
    <script src="js/jquery.tagsinput.js"></script>
    <script src="js/jquery.placeholder.js"></script>
    <script src="js/bootstrap-typeahead.js"></script> -->
    <script type="text/javascript" src="static/js/calendarM.js"></script> 
   
    
  
    
    <!-- <script src="js/application.js"></script> -->
    <!-- <script src="js/moment.min.js"></script> -->
    <!-- <script src="js/jquery.dataTables.min.js"></script> -->
    <!-- <script src="js/jquery.sortable.js"></script> -->
    <!-- <script type="text/javascript" src="js/jquery.gritter.js"></script> -->
    <script src="static/js/archon.js"></script>
    <script src="static/js/dataStatement.js"></script>
    <script type="text/javascript" src="static/dataTables/js/jquery.dataTables.js"></script>
    <script type="text/javascript" src="static/dataTables/js/dataTables.fixedColumns.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$.extend( $.fn.dataTable.defaults, {
                searching: false,
                //ordering:  false
            } );
			
			
			
			var time = $('#select_time').val();
			$(".calendar-date   li").click(function(event) {
				var oneDay = $(".date").html();
				var methodName = $('#method').val();
				dataStateMent.opinionMethod(methodName,"",oneDay);
				
				$('#de').remove();
				$('#select_time').prepend("<option id='de' value=''>请选择</option>")
				$('#select_time').val("");
				dataStateMent.dataTablesChange(time);
				 $(this).css({ "background": "rgba(255, 128, 142,0.5)"}).siblings().css({ "background": "#47506c"});
			});
			 $("#select_time").change(function(){
				 time = $('#select_time').val();
				 var methodName = $('#method').val();
				 if(time != ""){
					$('#de').remove();
					dataStateMent.opinionMethod(methodName, time, "");
					
					dataStateMent.dataTablesChange(time);
				 }
			 });
			 
			dataStateMent.opinionMethod("visitMethod" ,time,"");
			
			dataStateMent.dataTablesInit();
			
			$('#method').change(function(){
				var methodName = $('#method').val();
				time = $('#select_time').val();
				 if(time == ""){
					 time = "week";
					 $('#select_time').val("week");
				 }
				$('#de').remove();
				dataStateMent.opinionMethod(methodName ,time,"");
				dataStateMent.dataTablesChange(time);
				
			});

		});
		
		
	</script>
	<script type="text/javascript" >
        var idTmr;
        
        function  getExplorer() {
            var explorer = window.navigator.userAgent ;
            //ie 
            if (explorer.indexOf("MSIE") >= 0) {
                return 'ie';
            }
            //firefox 
            else if (explorer.indexOf("Firefox") >= 0) {
                return 'Firefox';
            }
            //Chrome
            else if(explorer.indexOf("Chrome") >= 0){
                return 'Chrome';
            }
            //Opera
            else if(explorer.indexOf("Opera") >= 0){
                return 'Opera';
            }
            //Safari
            else if(explorer.indexOf("Safari") >= 0){
                return 'Safari';
            }
        }
        function method1(tableid) {//整个表格拷贝到EXCEL中
            if(getExplorer()=='ie')
            {
                var curTbl = document.getElementsByClassName(tableid)[0];
                var oXL = new ActiveXObject("Excel.Application");

                //创建AX对象excel 
                var oWB = oXL.Workbooks.Add();
                //获取workbook对象 
                var xlsheet = oWB.Worksheets(1);
                //激活当前sheet 
                var sel = document.body.createTextRange();
                sel.moveToElementText(curTbl);
                //把表格中的内容移到TextRange中 
                sel.select;
                //全选TextRange中内容 
                sel.execCommand("Copy");
                //复制TextRange中内容  
                xlsheet.Paste();
                //粘贴到活动的EXCEL中       
                oXL.Visible = true;
                //设置excel可见属性

                try {
                    var fname = oXL.Application.GetSaveAsFilename("Excel.xls", "Excel Spreadsheets (*.xls), *.xls");
                } catch (e) {
                    print("Nested catch caught " + e);
                } finally {
                    oWB.SaveAs(fname);

                    oWB.Close(savechanges = false);
                    //xls.visible = false;
                    oXL.Quit();
                    oXL = null;
                    //结束excel进程，退出完成
                    //window.setInterval("Cleanup();",1);
                    idTmr = window.setInterval("Cleanup();", 1);

                }

            }
            else
            {
                tableToExcel('dataTables_scroll')
            }
        }
        function Cleanup() {
            window.clearInterval(idTmr);
            CollectGarbage();
        }
        var tableToExcel = (function() {
              var uri = 'data:application/vnd.ms-excel;base64,',
              template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>',
                base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) },
                format = function(s, c) {
                    return s.replace(/{(\w+)}/g,
                    function(m, p) { return c[p]; }) }
                return function(table, name) {
                if (!table.nodeType) table = document.getElementsByClassName(table)[0]
                var ctx = {worksheet: 'Worksheet', table: table.innerHTML}
                window.location.href = uri + base64(format(template, ctx))
              }
            })()
    </script>
</body>
</html>
    