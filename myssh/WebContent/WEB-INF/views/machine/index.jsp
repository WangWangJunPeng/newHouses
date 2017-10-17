<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
<head>
	<title>主页</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
	<link href="static/css/bootstrap-3.3.5-dist/css/bootstrap.min.css" title="" rel="stylesheet" />
	<link rel="stylesheet" href="static/css/default.css">
	<link rel="stylesheet" href="static/css/machineIndex.css">
	<link rel="stylesheet" href="static/css/getVist.css">
	<link rel="stylesheet" href="static/css/kaoqing.css">
	<link rel="stylesheet" href="static/css/leave.css">
	<link rel="stylesheet" href="static/css/set.css">
	<link rel="stylesheet" href="static/css/business.css">
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	<!-- <script src="http://root-1252955170.cossh.myqcloud.com/static/fonts/MSYH.TTF"></script>
	<script src="http://root-1252955170.cossh.myqcloud.com/static/fonts/MSYHBD.TTF"></script>
	<script src="http://root-1252955170.cossh.myqcloud.com/static/fonts/MSYHL.TTC"></script> -->
	<script src="static/css/bootstrap-3.3.5-dist/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="static/js/time.js" type="text/javascript"></script>
	<script src="static/js/index.js" type="text/javascript"></script>
	<script src="static/js/getVist.js" type="text/javascript"></script>
	<script src="static/js/kaoqing.js" type="text/javascript"></script>
	<script src="static/js/leave.js" type="text/javascript"></script>
	<script src="static/js/set.js" type="text/javascript"></script>
	
</head>
<body onload="startTime()">
	<nav class="nav navbar-default navbar-mystyle navbar-fixed-top">
  		<div class="navbar-header">
    		<!-- <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> 
     			<span class="icon-bar"></span> 
     			<span class="icon-bar"></span> 
     			<span class="icon-bar"></span> 
    		</button> -->
    		<!-- <a class="navbar-brand mystyle-brand"><span class="glyphicon glyphicon-home"></span></a> -->
    		<img class="navbar-brand mystyle-brand" alt="" src="static/images/mlogo.png">
    	</div>

  		<div class="collapse navbar-collapse">
    		<ul class="nav navbar-nav">
      			<li class="li-border "><p class="mystyle-color" id="toadyTime"></p></li>
   			 </ul>
    		<ul class="nav navbar-nav center-nav">
       			<li class="li-border"><p class="mystyle-color">接访管控机</p></li>
      		</ul>
    		<ul class="nav navbar-nav pull-right">
    			<!-- <li class="li-border"><a onclick="requestFullScreen()" href="#"><img alt="" src="static/images/fullScr.png"></a></li> -->
    			<li class="li-border"><a class="refresh" href="#" onclick="refreshLogin()"><img alt="" src="static/images/mRefresh.png"></a></li>
    			<li class="li-border"><a class="setting" href="#" onclick="showLogin()"><img alt="" src="static/images/mSet.png"></a></li>
      		</ul>
  		</div>
	</nav>

	<div class="down-main">
		<div class="left-main">
			<p class="type">
				<img src="static/images/zhiyeguwen.png" alt="">
				置业顾问
			</p>
			<!-- 选项卡 -->
			<ul class="nav nav-tabs left-tab">
				<li class="active"><a href="#tab-item1" data-toggle="tab">排队中</a></li>
				<li><a href="#tab-item2" data-toggle="tab">接待中</a></li>
			</ul>
			<div class="tab-content left-content">
				<div class="tab-pane fade active in" id="tab-item1">
					<div class="msglist row" id="ranking">

					</div>
					<input type="hidden" id="rangkingzongyeshu">
					<div class="row pagelist1">
						<div class="page-item1">
							<ul class="pager">
    							<li onclick="getRangBeforePage()"><a class="pre1" href="###">上一页</a></li>
    							<li onclick="getRangNextPage()"><a class="next1" href="###">下一页</a></li>
  							</ul>
  						</div>
					</div>
					

				</div>
				<div class="tab-pane fade" id="tab-item2">
					<div class="msglist row" id="receptionAgent"></div>

					<input type="hidden" id="zuozejiefangzongyeshu">
					
					<div class="row pagelist2">
						<div class="page-item2">
							<ul class="pager">
    							<li onclick="getReceptionBeforePage()"><a class="pre2" href="###">上一页</a></li>
    							<li onclick="getReceptionNextPage()"><a class="next2" href="###">下一页</a></li>
  							</ul>
  						</div>
					</div>
	<!-- 				<div class="page-item2 row">
						<ul class="pager">
    						<li onclick="getReceptionBeforePage()"><a class="pre2" href="###">上一页</a></li>
    						<li onclick="getReceptionNextPage()"><a class="next2" href="###">下一页</a></li>
  						</ul>
					</div> -->
				</div>
			</div>
			<button class="kaoqing" onclick="showKq()">考勤</button>
		</div>
		
		<div class="right-main">
			<div class="container-fluid">
				<!-- tittle -->
				<p class="type">
					<img src="static/images/jiefangjilv.png" alt="">
					接访记录
				</p>
				<div class="content-list">
				<!-- 选项卡 -->
				<ul class="nav nav-tabs right-tab">
					<li class="active"><a href="#tab-item3" data-toggle="tab">接待中</a></li>
					<li><a href="#tab-item4" data-toggle="tab">已送别</a></li>
				</ul>
				<div class="tab-content right-content">
					<div class="tab-pane fade active in" id="tab-item3">
						<div class="row cardlist" id="shouyejiefangzhong">

						</div>
						
						<!--  首页接访隐藏总页数 -->
						<input type="hidden" id="shouyejiefangzongpage">
						<!-- 分页 -->
						<div class="row pagelist3">
							<div class="page-item3">
								<ul class="pager">
    								<li onclick="shouyejiefangBeforePage()"><a class="pre3" href="###">上一页</a></li>
    								<li onclick="shouyejiefangnextPage()"><a class="next3" href="###">下一页</a></li>
  								</ul>
							</div>
						</div>



					</div>
					
					<!--  首页接访隐藏总页数 -->
						<input type="hidden" id="shouyesongbiezongpage">
					<!-- 已送别 -->
					<div class="tab-pane fade" id="tab-item4">
						<div class="row cardlist" id="shouyeyisongbie">
						
						</div>
						<div class="row pagelist4">
							<div class="page-item4">
								<ul class="pager">
    								<li onclick="shouyesongbieBeforePage()"><a class="pre4" href="###">上一页</a></li>
    								<li onclick="shouyesongbienextPage()"><a class="next4" href="###">下一页</a></li>
  								</ul>
							</div>
						</div>
						<!-- <div class="page-item4">
							<ul class="pager">
    							<li onclick="shouyesongbieBeforePage()"><a class="pre4" href="###">上一页</a></li>
    							<li onclick="shouyesongbienextPage()"><a class="next4" href="###">下一页</a></li>
  							</ul>
						</div> -->
					</div>
				</div>
				</div>
			

				<div class="footer-btn col-xs-12">
					<button class="visitBtn" onclick="showDaoFang()">到访客户</button>
					<a href="#" class="editBtn" onclick="toGoToAgentDayWorkPage()"></a>
					<div class="clearfix"></div>
				</div>
				
			</div>
			
		</div>
		
	</div>

	<!-- 设置 -->
	<div class="mask " id="set" style="display:none;">
		<div class="right-box">
			<div class="set_main">
				<p class="topTit">运行参数<img class="closeBtn" src="static/images/btn_close.png" alt="" onclick="closeLogin()"></p>
				<div class="set_content">
					<div class="set_left pull-left">
						<p class="set_title w80">服务器参数</p>
						<form class="login_form">
  							<div class="form-group">
    							<label for="exampleInputEmail1">登录账号</label>
    							<input type="text" class="form-control" id="exampleInputEmail1" placeholder="请输入登录账号">
 							</div>
  							<div class="form-group">
    							<label for="exampleInputPassword1">登录密码</label>
    							<input type="password" class="form-control" id="exampleInputPassword1" placeholder="请输入登录密码">
 							</div>
  							<div class="form-group">
    							<div class="radioCh col-sm-10">
     								<!-- <div>
        								<label><input type="radio" name="lungang" value="1" checked> 小组轮岗</label>
        								<label><input type="radio" name="lungang" value="0"> 顺序轮岗</label>
      								</div> -->
    							</div>
  							</div>
	  						<button type="button" class="loginBtn blueBtn" onclick="getLogin()">登录</button>
							<p id="yanzheng"></p>
						</form>
						
					</div>
					<div class="set_right pull-left">
						<p class="set_title w90">销售小组</p>
						<div class="w90">
							<div class="w50 pull-left" >
								<p class="groupName">销售一组</p>
								<div class="group1">

								</div>
							</div>
							<div class="w50 pull-left">
								<p class="groupName">销售二组</p>
								<div class="group2">
									
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>

					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>

	<!-- 签到 签退 -->
	<div class="kq_content mask">
		<div class="right-box">
			<div class="container-fluid">
				<div class="sign_main shadow">
					<p class="topTit">考勤<img class="closeBtn" src="static/images/btn_close.png" alt="" onclick="closeKqContent()"></p>
					<div class="info">
                		<div class="infoImg">
                			<img src="static/images/image_user_10.png" alt="" id="signPic">
                		</div>
                		<div class="info_content">
                    	<p class="getTime">
                    		<img class="mark2" src="static/images/userright.png" alt="">
                      		置业顾问：
                      		<span id="agentName"></span>
                    	</p>
                    	<p class="getTime">
                    		<img class="mark2" src="static/images/icon_time.png" alt="">
                      		打卡时间：
                      		<span id="agentSignTime"></span>
                    	</p>
                    	</div>
                    	<input type="hidden" id="agentProjectId">
                    	<input type="hidden" id="agentUserId">
                	</div>
                	<button class="sign_confirm" type="button" onclick="getSignConfirmation()">确认</button>
				</div>
				<!-- 警告框 -->
				<div class="alert alert-warning" id="warning-block">
  					<button type="button" class="warn_close close">&times;</button>
  					<p></p>
				</div>
			</div>
		</div>
	</div>
	

	<!-- 考勤 -->
	<div class="mask kq">
		<div class="right-box">
			<div class="container-fluid">
				<div class="kq_main shadow">
					<p class="topTit">考勤管理<img class="closeBtn" src="static/images/btn_close.png" alt="" onclick="closeKq()"></p>
					<!-- 选项卡 -->
					<ul class="nav nav-tabs kq-tab">
						<li class="active" onclick="getDaiQianYueList()" ><a href="#tab-item_sign" data-toggle="tab">签到</a></li>
						<li onclick="getQianTuiList()"><a href="#tab-item_quit" data-toggle="tab">签退</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane fade active in" id="tab-item_sign">
							<div class="kqlist row" id="signAgentList">
								<input type="hidden" value="0">
							</div>
							<!-- 签到list总页数 -->
							<input type="hidden" id="qiantuizongyeshu">
						<input type="hidden" id="proId">
						<!-- 分页 -->
						<div class="page-item1 row">
							<ul class="pager">
    							<li onclick="getBeforePage1()"><a class="pre5" href="###">上一页</a></li>
    							<li onclick="getNextPage1()"><a class="next5" href="###">下一页</a></li>
  							</ul>
						</div>
					</div>
				
					<div class="tab-pane fade" id="tab-item_quit">
						<div class="kqlist row" id="signAgentList2">
							<input type="hidden" value="1">
						</div>
							<!-- 签退list总页数 -->
							<input type="hidden" id="qiantuizongyeshu">
						<div class="page-item2 row">
							<ul class="pager">
    							<li onclick="getBeforePage2()"><a class="pre6" href="###">上一页</a></li>
    							<li onclick="getNextPage2()"><a class="next6" href="###">下一页</a></li>
  							</ul>
						</div>
					</div>
				</div>
				
			</div>
			</div>
		</div>
	</div>

	<!-- 到访客户 -->
	<div class="mask getVist">
		<div class="right-box">
			<div class="container-fluid">
				
				<div class="getway shadow">
					<p>选择到访性质<img class="closeBtn" src="static/images/btn_close.png" alt="" onclick="closeDaoFang()"></p>
					<button class="arrive way" value="true" id="firstVisit" onclick="showFirst(this)">首次到访</button>
					<button class="arrive way" value="false" id="secondVisit"onclick="showSecond(this)" >再次到访</button>
					<button class="way" onclick="showSelect()">查询电话</button>	
					
					<input type="hidden" id="daofangchaxun">
				</div>
				<div class="vistway shadow">
					<p>选择接访方式<img class="closeBtn" src="static/images/btn_close.png" alt="" onclick="closeDaoFang()"></p>
					<button class="way" onclick="shunXv()">顺序接访</button>
					<button class="way" onclick="getnewAllZhiDingAgentList()">指定接访</button>
				</div>
				<!-- 指定置业顾问 -->
				<div class="chooseCus shadow">
					<p class="topTit">指定置业顾问<img class="closeBtn" src="static/images/btn_close.png" alt="" onclick="closeDaoFang()"></p>
					<div class="chooseList row" id="zhidingjiefangagentlist">

					</div>
					<input type="hidden" id="zhidingzhiyeguwenzongpage">
					
					<!-- 分页 -->
						<div class="row pagelist5">
							<div class="page-item3">
								<ul class="pager">
    								<li onclick="zhidingAgentListBeforePage()"><a class="pre8" href="###">上一页</a></li>
    								<li onclick="zhidingAgentListNextPage()"><a class="next8" href="###">下一页</a></li>
  								</ul>
							</div>
						</div>
				</div>

				<!-- 认识的置业顾问 -->
				<div class="seceltCus shadow">
					<p class="topTit">认识的置业顾问<img class="closeBtn" src="static/images/btn_close.png" alt="" onclick="closeKnowCus()"></p>
					<div class="chooseList row" id="knowzhidingjiefang">

					</div>
					<!-- 指定置业顾问总页数 -->
					<input type="hidden" id="knowzhidingzhiyeguwen">
					<!-- 分页 -->
						<div class="row pagelist6">
							<div class="page-item3">
								<ul class="pager">
    								<li onclick="knowAgentListBeforePage()"><a class="pre7" href="###">上一页</a></li>
    								<li onclick="knowAgentListNextPage()"><a class="next7" href="###">下一页</a></li>
  								</ul>
							</div>
						</div>
				</div>
				<!-- 贵宾到访 -->
				<div class="vistConfir shadow">
					<p class="topTit">贵宾到访确认<img class="closeBtn" src="static/images/btn_close.png" alt=""  onclick="closeDaoFang()"></p>
					<div class="con">
						<div class="people col-md-6">
							<div class="halfBox">
								<p class="topTit">到访宾客人数</p>
								<div class="phoneBor row">
									<div class="col-md-4"><p class="Num" onclick="clickNum(this)">1</p></div>
									<div class="col-md-4"><p class="Num" onclick="clickNum(this)">2</p></div>
									<div class="col-md-4"><p class="Num" onclick="clickNum(this)">3</p></div>
									<div class="col-md-4"><p class="Num" onclick="clickNum(this)">4</p></div>
									<div class="col-md-4"><p class="Num" onclick="clickNum(this)">5</p></div>
									<div class="col-md-4"><p class="Num" onclick="clickNum(this)">6</p></div>
									<div class="col-md-4"><p class="Num" onclick="clickNum(this)">7</p></div>
									<div class="col-md-4"><p class="Num" onclick="clickNum(this)">8</p></div>
									<div class="col-md-4"><p class="Num" onclick="clickNum(this)">9</p></div>
								</div>
								<div class="vistType">
									<button class="firBtn pull-left" value="true" onclick="againFirstVisit(this)">首次到访</button>
									<button class="secBtn pull-left" value="false" onclick="againSecondVisit(this)">再次到访</button>
									<div class="clearfix"></div>
									
									<input type="hidden" id="danfangrenshu">
									<input type="hidden" id="jieshoujiefangqueren">
								</div>
								<div class="knowCus" onclick="showKnowCus()">
									<p class="centerP">认识的置业顾问</p>
									<p class="know">
										<img class="pull-left" src="static/images/image_user_10.png" alt="" id="konwAgentPhoto">
										姓名:<span id="knowAgentName"></span>
										<input type="hidden" id="knowAgentId">
									</p>
								</div>
							</div>
							
						</div>
						<div class="cusMsg col-md-6">
							<div class="halfBox">
								<p class="topTit">接访置业顾问</p>
								<div class="info top10">
                					<div class="infoImg">
               						<img src="static/images/image_user_10.png" alt="" id="jiefangzhaopian">
               						</div>
                   					<p class="conMsg">
                   						<img class="mark2" src="static/images/userright.png" alt="">
                     						置业顾问：
                     						<span id="jiefangZYName"></span>
                   					</p>
                   					<p class="conMsg">
                   						<img class="mark2" src="static/images/icon_time.png" alt="">
                     						接访时间：
                     						<span id="jiefangNowTime"></span>
                   					</p>
                   					<p class="conMsg">
                   						<img class="mark2" src="static/images/nowStatus.png" alt="">
                     						当前状态：
                     						<span id="jiefangzhuangtai"></span>
                   					</p>
                   					<p class="conError errorCol">暂无置业顾问</p>
                   					<input type="hidden" id="receptionAgentCommit">
               					</div>
							</div>	
							<button class="chanVist" onclick="changeVist()">换人</button>
							<button class="conVist" onclick="getRealVisitCommitOne()">确认到访</button>
						</div>							
						
						<div class="clearfix"></div>
					</div>
				</div>
				
				<!-- 选择置业顾问 -->
				<div class="changeCus shadow">
					<p class="topTit">选择置业顾问<img class="closeBtn" src="static/images/btn_close.png" alt="" onclick="closeChangeCus()"></p>
					<div class="chooseList row" id="huanrenzhiyeguwen">

					</div>
					<!-- 选项卡 -->
					<ul class="nav nav-tabs right-tab">
						<li class="active"><a href="#tab-item5" data-toggle="tab">排队中</a></li>
						<li><a href="#tab-item6" data-toggle="tab">接待中</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane fade active in" id="tab-item5">
							<div class="row" id="huanrenrangkingTwo">
								排队中
							</div>
							
							
					<input type="hidden" id="changerangkingzongyeshu">
					<div class="row pagelist8">
						<div class="page-item5">
							<ul class="pager">
    							<li onclick="getChangeRangBeforePage()"><a class="pre1" href="###">上一页</a></li>
    							<li onclick="getChangeRangNextPage()"><a class="next1" href="###">下一页</a></li>
  							</ul>
  						</div>
					</div>
					

				</div>
				<div class="tab-pane fade" id="tab-item6">
					<div class="row" id="huanrenjiefangTwo"></div>
					
					<input type="hidden" id="changezuozejiefangzongyeshu">
					
					<div class="row pagelist9">
						<div class="page-item6">
							<ul class="pager">
    							<li onclick="getChangeReceptionBeforePage()"><a class="pre2" href="###">上一页</a></li>
    							<li onclick="getChangeReceptionNextPage()"><a class="next2" href="###">下一页</a></li>
  							</ul>
  						</div>
					</div>
				</div>
			</div>
				</div>			
				
				<!-- 查询电话 -->
				<div class="selectPho shadow">
					<p class="topTit">查询电话号码<img class="closeBtn" src="static/images/btn_close.png" alt="" onclick="closeDaoFang()"></p>
					<div class="con">
						<div class="people col-md-6">
							<div class="halfBox">
								<p class="topTit">贵宾电话</p>
								<p class="phoShow" style="line-height:40px;color:#333;font-size:16px;" id="secondIn"></p>
								<div class="phoneBor row">
									<div class="col-md-4"><p class="Num mm" onclick="clickMM(this)">1</p></div>
									<div class="col-md-4"><p class="Num mm" onclick="clickMM(this)">2</p></div>
									<div class="col-md-4"><p class="Num mm" onclick="clickMM(this)">3</p></div>
									<div class="col-md-4"><p class="Num mm" onclick="clickMM(this)">4</p></div>
									<div class="col-md-4"><p class="Num mm" onclick="clickMM(this)">5</p></div>
									<div class="col-md-4"><p class="Num mm" onclick="clickMM(this)">6</p></div>
									<div class="col-md-4"><p class="Num mm" onclick="clickMM(this)">7</p></div>
									<div class="col-md-4"><p class="Num mm" onclick="clickMM(this)">8</p></div>
									<div class="col-md-4"><p class="Num mm" onclick="clickMM(this)">9</p></div>
									<div class="col-md-4"><p class="ok" id="searching" onclick="searching()">ok</p></div>
									<div class="col-md-4"><p class="Num mm" onclick="clickMM(this)">0</p></div>
									<div class="col-md-4"><p class="delete" onclick="deleteAll()">清除</p></div>
								</div>
							</div>
							<button class="conVist select_btn" onclick="selectVistShun()">顺序接访</button>
							
						</div>
						<div class="cusMsg col-md-6">
							<div class="halfBox">
								<p class="topTit">接访置业顾问</p>
								<div class="info">
                					<div class="infoImg smImg">
                						<img src="static/images/image_user_10.png" alt="" id="chaxunagentPhoto">
                						</div>
                    					<p class="conMsg">
                    						<img class="mark2" src="static/images/userright.png" alt="">
                      						置业顾问：
                      						<span id="chaxunagentName"></span>
                    					</p>
                    					<p class="conMsg">
                    						<img class="mark2" src="static/images/icon_time.png" alt="">
                      						接访状态：
                      						<span id="chaxunjiefangStatus"></span>
                    					</p>
                    					<p class="conMsg">
                    						<img class="mark2" src="static/images/guibin.png" alt="">
                      						贵宾姓名：
                      						<span id="chauxncustomerName"></span>
                    					</p>
                    					<p class="conMsg">
                    						<img class="mark2" src="static/images/arrivetime.png" alt="">
                      						到访次数：
                      						<span id="chauxndaofangNum"></span>
                    					</p>
                    					<p class="conMsg">
                    						<img class="mark2" src="static/images/firArrive.png" alt="">
                      						首次到访：
                      						<span id="chaxunshoucidanfangtime"></span>
                    					</p>
                    					<p class="conMsg">
                    						<img class="mark2" src="static/images/lastArrive.png" alt="">
                      						末次到访：
                      						<span id="chaxunmocidaofangtime"></span>
                    					</p>
                    					
                					<input type="hidden" id="chaxunCustomerId">
                					<input type="hidden" id="chaxunAgentId">
                					<input type="hidden" id="chaxunjiefangTime">
                					</div>
                					<input type="hidden" id="chaxunCustomerPhone">
								</div>	
								<button class="conVist select_btn" onclick="getRealVisitCommitTwo()" id="getRealVisitCommitTwo">指定接访</button>
						</div>							
						
						<div class="clearfix"></div>
					</div>
				</div>
			
			</div>
		</div>
		
	</div>
	
	<!-- 加载页面 -->
	<div class="mask loading">
		<img class="loadImg" alt="" src="static/images/mloading.gif">
	</div>

	<!-- 登记 送别 -->
	<div class="leave mask">
		<div class="right-box">
			<div class="changePho shadow">
				<p class="topTit">贵宾电话登记<img class="closeBtn" src="static/images/btn_close.png" alt="" onclick="getAllClose()"></p>
				<div class="con">
					<div class="people col-md-6">
						<div class="halfBox">
							<p class="topTit">贵宾电话</p>
							<p class="phoShow" style="line-height:40px;color:#333;font-size:16px;" id="firstIn"></p>
							<div class="phoneBor row">
								<div class="col-md-4"><p class="Num nn" onclick="clickNN(this)">1</p></div>
								<div class="col-md-4"><p class="Num nn" onclick="clickNN(this)">2</p></div>
								<div class="col-md-4"><p class="Num nn" onclick="clickNN(this)">3</p></div>
								<div class="col-md-4"><p class="Num nn" onclick="clickNN(this)">4</p></div>
								<div class="col-md-4"><p class="Num nn" onclick="clickNN(this)">5</p></div>
								<div class="col-md-4"><p class="Num nn" onclick="clickNN(this)">6</p></div>
								<div class="col-md-4"><p class="Num nn" onclick="clickNN(this)">7</p></div>
								<div class="col-md-4"><p class="Num nn" onclick="clickNN(this)">8</p></div>
								<div class="col-md-4"><p class="Num nn" onclick="clickNN(this)">9</p></div>
								<div class="col-md-4"><p class="ok" onclick="toCommitByePhone()">ok</p></div>
								<div class="col-md-4"><p class="Num nn" onclick="clickNN(this)">0</p></div>
								<div class="col-md-4"><p class="delete" onclick="deleteAll()">清空</p></div>
							</div>
						</div>		
					</div>
					<div class="cusMsg col-md-6">
						<div class="halfBox">
							<p class="topTit">接访置业顾问</p>
							<div class="info">
                				<div class="infoImg smImg">
                					<img src="static/images/image_user_10.png" alt="" id="agentPhoto">
                				</div>
                    			<p class="conMsg">
                    				<img class="mark2" src="static/images/userright.png" alt="">
                      				置业顾问：
                      				<span id="shouyejiedaiagentName"></span>
                    			</p>
                    			<p class="getTime">
                    				<img class="mark2" src="static/images/icon_time.png" alt="">
                      				接访时间：
                      				<span id="jiedaishijian"></span>
                    			</p>
                    			<p class="getTime">
                    				<img class="mark2" src="static/images/guibin.png" alt="">
                      				贵宾姓名：
                      				<span id="shouyejiedaicustomerName"></span>
                    			</p>
                    			<p class="getTime">
                    				<img class="mark2" src="static/images/arrivetime.png" alt="">
                      				到访次数：
                      				<span id="shouyedaofangcishu"></span>
                    			</p>
                    			<input type="hidden" id="jiedaiagentid">
                    			<input type="hidden" id="visitNo">
                			</div>

                			<button class="leaveBtn" onclick="leaveCus()">送别贵宾</button>
						</div>	
					</div>							
						
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
		
	<!-- 送别确认 -->
	<div class="leave_confirm mask">
		<div class="right-box">
			<div class="container-fluid">
				<div class="leave_main shadow">
					<p class="topTit">确认送别贵宾<img class="closeBtn" src="static/images/btn_close.png" alt="" onclick="closeLeaveCon()"></p>
					<p class="goodbye">送别该批贵宾吗？</p>
                	<button class="sign_confirm" type="button" onclick="toGoodByeCustomer()">确认</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 警告框 -->
	<div class="alertDiv mask">
		<div class="right-box">
			<div class="container-fluid">
				<div class="alert_main shadow">
				<p class="topTit">提示</p>
					<p class="goodbye">操作异常,请稍后重试</p>
                	<button class="sign_confirm" type="button" onclick="closeAlert()">确认</button>
				</div>
			</div>
		</div>
	</div>
</body>

</html>