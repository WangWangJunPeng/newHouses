<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta content="yes" name="apple-mobile-web-app-capable">
        <meta content="yes" name="apple-touch-fullscreen">
        <meta content="telephone=no,email=no" name="format-detection">
        <title>待盘客1</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/frozenui/css/frozen.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/anchangSuitable.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/daiPanke.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/mui.css">
        <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
        <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
        <style type="text/css" >
        .ui-header {
        	height: 1.2rem;
        	line-height: 1.2rem;
        }
        [class^="ui-icon-"] {
        	font-size: .6rem;
        }
        .ui-header .ui-icon-return {
        	left: 0;
        	line-height: 1.2rem;
        }
        .ui-header ~ .ui-container {
        	border-top:1.2rem solid transparent;
        }
        	.mui-switch:before {
        		font-size: .32rem;
        		top: -.2rem;
        		right: .1rem;
        		content:'NO';
        	}
        	.ui-header h1 {
        		font-size: .4rem;
        	}
        	.mui-switch.mui-active:before {
        		content:'YES';
        	}
        	.mui-switch .mui-switch-handle {
	top:0.0666rem;
	width: .3733rem;
	height: .3733rem;
}
textarea {
	border:1px solid #e5e5e5;
	background-color: #efeff4;
	padding: .1rem .1rem;
	font-size: .4rem;
	height: 3rem;
	border-radius: 4px;
}
.mui-segmented-control .mui-control-item {
	line-height: 1rem;
}
        </style>
        <script type="text/javascript">
        	$(function(){
        		if(!'${pc.customerId}'){
        			$('body').empty();
        			$('body').html('<header class="ui-header ui-header-positive ui-boeder-b">'+
        			        '<i class="ui-icon-return" onclick="history.back()"></i>'+
        			        '<h1>该客户置业顾问未作操作</h1>'+
        			    '</header>');
        		}
        		getCustomerAffiliation();//获取归属顾问
        		getTags1();
        		getTags2();
        		status();
        		$("#saveBtn").click(function(){
        			var customerComment = $("#customerComment").val();
        			var commitSpeed = 0;
        			var tijiao1 = $("#tijiao1").prop('checked');
        			var tijiao2 = $("#tijiao2").prop('checked');
        			var tijiao3 = $("#tijiao3").prop('checked');
        			var tijiao4 = $("#tijiao4").prop('checked');
        			var tijiao5 = $("#tijiao5").prop('checked');
        			if(tijiao1){
        				commitSpeed+=1;
        			}
        			if(tijiao2){
        				commitSpeed+=1;
        			}
        			if(tijiao3){
        				commitSpeed+=1;
        			}
        			if(tijiao4){
        				commitSpeed+=1;
        			}
        			if(tijiao5){
        				commitSpeed+=1;
        			}
        			var describe = 0;
        			var miaoshu1 = $("#miaoshu1").prop('checked');
        			var miaoshu2 = $("#miaoshu2").prop('checked');
        			var miaoshu3 = $("#miaoshu3").prop('checked');
        			var miaoshu4 = $("#miaoshu4").prop('checked');
        			var miaoshu5 = $("#miaoshu5").prop('checked');
        			if(miaoshu1){
        				describe+=1;
        			}
        			if(miaoshu2){
        				describe+=1;
        			}
        			if(miaoshu3){
        				describe+=1;
        			}
        			if(miaoshu4){
        				describe+=1;
        			}
        			if(miaoshu5){
        				describe+=1;
        			}
        			var isAgree = -1;
        			
        			var isActive = document.getElementById("mySwitch").classList.contains("mui-active");
        			if(isActive){
        				isAgree = 1;
        			}else{
        				isAgree = 0; 
        			}
					var projectCustomerId = '${pc.customerId}';
					var customerAspiration = '${pc.yixiang}'
					//
					$.ajax({
		       			 url: 'to_updateProjectCustomer',
		                    type: 'post',
		                    async : false,
		                    data: {
		                    	projectCustomerId:projectCustomerId,
		                    	customerComment:customerComment,
		                    	describe:describe,
		                    	commitSpeed:commitSpeed,
		                    	customerAspiration:customerAspiration,
		                    	isAgree:isAgree,
		                    	visitNo:'${visitNo}'
		                    },
		                    success: function (data) {
		                   	 if(data==200){
		                   		 window.location.href='to_goToPanKePage'
		                   	 }
		                    },
		                    /* error:function(){
		                 	   //console.log(2)
		                    } */
		       			});
					
        		});
        	})
       	function status(){
		var status = '${pc.status}';
		var yidaofang = '${pc.comeNum }';
		if(yidaofang>0){
			$(".listone").css("background","#fd6767");
			$(".listes1").css("color","#fd6767");
		}
		//alert(status)
		/* 签约 */
		if(status=="5"){
			$(".listone").css("background","#fd6767");
			$(".listes1").css("color","#fd6767");
			$(".listtwo").css("background","#fd6767");
			$(".listes2").css("color","#fd6767");
			$(".listthree").css("background","#fd6767");
			$(".listes3").css("color","#fd6767");
			$(".listfour").css("background","#fd6767");
			$(".listes4").css("color","#fd6767");
		}
		if(status=="4"){
			$(".listone").css("background","#fd6767");
			$(".listes1").css("color","#fd6767");
			$(".listtwo").css("background","#fd6767");
			$(".listes2").css("color","#fd6767");
			$(".listthree").css("background","#fd6767");
			$(".listes3").css("color","#fd6767");
		}
		if(status=="1"){
			$(".listone").css("background","#fd6767");
			$(".listes1").css("color","#fd6767");
			$(".listtwo").css("background","#fd6767");
			$(".listes2").css("color","#fd6767");
		}
		/* 付款 */
		if(status=="0"){
			$(".listone").css("background","#fd6767");
			$(".listes1").css("color","#fd6767");
			$(".listtwo").css("background","#fd6767");
			$(".listes2").css("color","#fd6767");
		}
		if(status=="3"){
			$(".listone").css("background","#fd6767");
			$(".listes1").css("color","#fd6767");
		}
		if(status=="7"){
			$(".listone").css("background","#fd6767");
			$(".listes1").css("color","#fd6767");
		}
	}
        	
        	function getCustomerAffiliation(){
        		$.ajax({
       			 url: 'to_getCustomerAffiliation',
                    type: 'post',
                    async : false,
                    data: {
                 	   "projectCustomerId":'${pc.customerId}',
                    },
                    success: function (data) {
                   	 data = eval("(" +data+")");
                   	 var name = data.data.agentName;
                   	 var sex = data.data.sex;
                 	// console.log(name)
                   	 $('.rightwidth').html(name);
                 	$('#sex').html(sex)
                   	 
                    },
                    /* error:function(){
                 	   //console.log(2)
                    } */
       			});
        	}
        	
        	function getTags1(){
        		
        		$.ajax({
         			 url: 'getTagUse',
                      type: 'post',
                      dataType: 'json',
                      data: {
                   	   "customerId":'${pc.customerId}',
                   	   "tagTypeId":'${pc.rootTagTypes[0].tagTypeId}'
                      },
                      success: function (data) {
                   	 	showTagType(data,1);
                      },
                      /* error:function(){
                   	   
                      } */
         			});
        	}
        	function getTags2(){
        		
        		$.ajax({
         			 url: 'getTagUse',
                      type: 'post',
                      dataType: 'json',
                      data: {
                   	   "customerId":'${pc.customerId}',
                   	   "tagTypeId":'${pc.rootTagTypes[1].tagTypeId}'
                      },
                      success: function (data) {
                   	 	showTagType(data,0);
                      },
                      /* error:function(){
                   	   
                      } */
         			});
        	}
        	
        	function showTags(data){
        		var text = '';
        		$.each(data,function(v,o){
        			var tag = o.children;
        			if(tag.length>0){
        				//alert("asd");
        				text += showTags(tag);
        			}else{
        				//展示选中的标签
        				text += '<li>'+o.tagName+'</li>';
        				//alert(text);
        			}

        		})
        		return text;
        	}
        	function showTagType(data,num){
        		//var tagTypeDiv = '';
        		$.each(data,function(v, o) {
        			if(o.tagLibs.length>0){
        				showTagType(o.tagLibs);
        			}else{
        				//不存在标签类目
        				var tagTypeName = o.tagTypeName;
        				var sortNum = o.sortNum;
        				var tagTypeId = o.tagTypeId;
        				var tagTypeDiv = '<div class="line">'+
                        '<div class="leftBox">'+tagTypeName+'</div>'+
                        '<div class="right">'+
                            '<ul class="messList">';
                     //           '<li>李果</li>'+
                     //       '</ul>'+
                     //   '</div>'+
                    //'</div>';
        				
        				var tags = o.tags;
        				var lilist = '';
        				if(tags.length>0){
        					lilist = showTags(tags);
        					tagTypeDiv += lilist;
        				}else{
        					//没有选中的标签
        					tagTypeDiv+='<li>暂无</li>';
        				}
        				tagTypeDiv += '</ul>'+
        				'</div>'+
        				'</div>';
        				//alert(tagTypeDiv);
        				if(num==0){//客户信息
        					//$(tagTypeDiv).appendTo($("#customerDiv2"));
        					$("#customerDiv2").prepend(tagTypeDiv);
        				}
        				if(num==1){//身份信息
        					$(tagTypeDiv).appendTo($("#customerDiv"));
        				}
        			}
        			
        		})
        		
        	}
        </script>
</head>
<body>
	<header class="ui-header ui-header-positive ui-boeder-b">
        <i class="ui-icon-return" onclick="history.back()"></i>
        <h1 id="nnn">客户详情</h1>
    </header>
    <div class="ui-container">
    <div class="titlebar">
			<div class="container">
				<div class="lefttitle">
					<p>到访时间:<span class="leftwidth">${pc.firstTime}</span></p>
				</div>
				<div class="righttltle">
					<p>归属顾问:<span class="rightwidth"></span></p>
				</div>
			</div>
		</div>
		<!-- 中 -->
		<div class="people">
			<div class="container">
				<div class="left">
					<p class="pro" id="pro"></p>
				</div>
				<div class="right1">
					<p><img src="<%=request.getContextPath()%>/static/images/name.png"/><span>${pc.customerName }</span></p>
					<p><img src="<%=request.getContextPath()%>/static/images/phone.png"/><span>${pc.customerPhone }</span></p>
					<p class="top1">
						<span id="yidao" class="yuanyuan" data-value="${pc.comeNum}">已到访:${pc.comeNum }次</span>
						<span class="yuanyuan">${pc.yixiang }意向</span>
					</p>
				</div>
			</div>
		</div>
		<!-- 状态指示 -->
		<div class="state">
			<ul class="statelist">
				<li class="list1"><p class="pt"><span class="dian listone"></span></p><p class="listes1">已到访</p></li>
				<li class="list1"><p class="pt"><span class="dian listtwo"></span></p><p class="listes2">已认购</p></li>
				<li class="list1"><p class="pt"><span class="dian listthree"></span></p><p class="listes3">已付款</p></li>
				<li class="list1"><p class="pt"><span class="dian  listfour"></span></p><p class="listes4">已签约</p></li>
			</ul>
		</div>
    </div>
    <!--信息展示-->
		<div id="slider" class="mui-slider">
			<div id="sliderSegmentedControl" class="mui-slider-indicator mui-segmented-control mui-segmented-control-inverted">
				<a id="oneDiv" class="mui-control-item mui-active" href="#item1mobile" value="${pc.rootTagTypes[0].tagTypeId}">${pc.rootTagTypes[0].tagTypeName}</a>
				<a id="twoDiv" class="mui-control-item" href="#item2mobile" value="${pc.rootTagTypes[1].tagTypeId}" >${pc.rootTagTypes[1].tagTypeName}</a>
			</div>
			<div id="sliderProgressBar" class="mui-slider-progress-bar mui-col-xs-6"></div>
			<div class="mui-slider-group">
				<div id="item1mobile" class="mui-slider-item mui-control-content mui-active">
					<div id="scroll1" class="mui-scroll-wrapper">
						<div class="mui-scroll">
							<div class="">
								<div class="cusMess jiben">
                        <h2 class="headTop">
                            <span class="shuxian"></span>
                            客户基本信息
                        </h2>
                        <div class="mainMes">
                            <div class="line">
                                <div class="leftBox">姓名</div>
                                <div class="right">${pc.customerName }</div>
                            </div>
                            <div class="line">
                                <div class="leftBox">性别</div>
                                <div class="right">
                                     <ul class="messList">
                                        <li id="sex"></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="line">
                                <div class="leftBox">联系方式</div>
                                <div class="right">${pc.customerPhone }</div>
                            </div>
                            <div class="line">
                                <div class="leftBox">客户意向</div>
                                <div class="right">
                                    <ul class="messList">
                                        <li>${pc.yixiang }</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="cusMess">
                        <h2 class="headTop">
                            <span class="shuxian"></span>
                            客户信息
                        </h2>
                        <div class="mainMes" id="customerDiv">
                        </div>
                    </div>
                    
                    <div class="cusMess">
                        <h2 class="headTop">
                            <span class="shuxian"></span>
                            经理评分
                        </h2>
                        <div class="mainMes">
                           <div class="list">
                           		<div class="leftbox">
                           			客户描述
                           		</div>
                           		<div class="rightbox">
                           			<div class="imglist">
                           				<ul id="miaoshu">
                           					<li>
                           						<label for="miaoshu1"><img src="<%=request.getContextPath()%>/static/images/kongStar.png" disabled=""></label>
                           						<input id="miaoshu1" type="checkbox">
                           					</li>
                           					<li>
                           						<label for="miaoshu2"><img src="<%=request.getContextPath()%>/static/images/kongStar.png" alt=""></label>
                           						<input id="miaoshu2" type="checkbox">
                           					</li>
                           					<li>
                           						<label for="miaoshu3"><img src="<%=request.getContextPath()%>/static/images/kongStar.png" alt=""></label>
                           						<input id="miaoshu3" type="checkbox">
                           					</li>
                           					<li>
                           						<label for="miaoshu4"><img src="<%=request.getContextPath()%>/static/images/kongStar.png" alt=""></label>
                           						<input id="miaoshu4" type="checkbox">
                           					</li>
                           					<li>
                           						<label for="miaoshu5"><img src="<%=request.getContextPath()%>/static/images/kongStar.png" alt=""></label>
                           						<input id="miaoshu5" type="checkbox">
                           					</li>
                           				</ul>
                           			</div>
                           		</div>
                           </div>
                           <div class="list">
                           		<div class="leftbox">
                           			提交速度
                           		</div>
                           		<div class="rightbox">
                           			<div class="imglist">
                           				<ul id="tijiao">
                           					<li>
                           						<label for="tijiao1"><img src="<%=request.getContextPath()%>/static/images/kongStar.png" disabled=""></label>
                           						<input id="tijiao1" type="checkbox">
                           					</li>
                           					<li>
                           						<label for="tijiao2"><img src="<%=request.getContextPath()%>/static/images/kongStar.png" alt=""></label>
                           						<input id="tijiao2" type="checkbox">
                           					</li>
                           					<li>
                           						<label for="tijiao3"><img src="<%=request.getContextPath()%>/static/images/kongStar.png" alt=""></label>
                           						<input id="tijiao3" type="checkbox">
                           					</li>
                           					<li>
                           						<label for="tijiao4"><img src="<%=request.getContextPath()%>/static/images/kongStar.png" alt=""></label>
                           						<input id="tijiao4" type="checkbox">
                           					</li>
                           					<li>
                           						<label for="tijiao5"><img src="<%=request.getContextPath()%>/static/images/kongStar.png" alt=""></label>
                           						<input id="tijiao5" type="checkbox">
                           					</li>
                           				</ul>
                           			</div>
                           		</div>
                           </div>
                           <div class="list">
                           		<div class="leftbox">
                           			经理判客
                           		</div>
                           		<div class="rightbox">
                           			<ul class="messList width1">
                           				<li>${pc.yixiang }</li>                           				
                           			</ul>
									<!-- 蓝色开关打开状态 -->
									<div id="mySwitch" class="mui-switch mui-switch-blue ">
  										<div class="mui-switch-handle" style="top:.066rem;"></div>
									</div>
                           		</div>
                           </div>
                           <div class="list">
                           		<div class="leftbox">
                           			经理点评
                           		</div>                           		
                           </div>
                           	<textarea id="customerComment" placeholder="不超过50个字"></textarea>
                        </div>
                    </div>
							</div>
						</div>
					</div>
				</div>
				<div id="item2mobile" class="mui-slider-item mui-control-content">
					<div id="scroll2" class="mui-scroll-wrapper">
						<div class="mui-scroll">
							<div class="contain" id="customerDiv2">
								 <div class="line">
									<div class="leftBox">备注</div>
									<div class="rightbox">
										<p id="desc">${pc.desc }</p>
									</div>
								</div>
								<!--<div class="list">
									<div class="leftbox"><div class="title">性格</div></div>
									<div class="rightbox">
										<ul class="messList">
										<li>路过</li>
										<li>朋友圈分享的</li>
										<li>朋友圈分享的</li>
										</ul>
									</div>
								</div> -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--底部按钮-->
	<div class="btn-success mui-bar mui-bar-tab">
			<div class="midde">
				<button type="button"  id="saveBtn">保存</button>
				
			</div>
	</div>
	
	<!--遮罩层-->
	<div class="popup-backdrop" style="display: none;"></div>
	<script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			var name = '${pc.customerName}';
			var name1 = name.substring(0,1);
			$("#pro").html(name1);
			
			mui.init({
				swipeBack:true //启用右滑关闭功能
			});
			
			var miaoshu = document.getElementById("miaoshu");
			var aLi = miaoshu.getElementsByTagName("input");
			var img = miaoshu.getElementsByTagName("img");
			var i = 0;
			for (i=0;i<aLi.length;i++){
				aLi[i].index = i;				
				aLi[i].onclick = function() {
					for(i=0; i<5; i++){    
		                aLi[i].className="";
		                img[i].src="<%=request.getContextPath()%>/static/images/kongStar.png";
		            }
					if($(this).hasClass("active")==false){
						for(i=0; i<=this.index;i++){ 
							aLi[i].className="active"
							img[i].src="<%=request.getContextPath()%>/static/images/shiStar.png";
			            }
					}
				};

			}
			
			var tijiao = document.getElementById("tijiao");
			var aLi1 = tijiao.getElementsByTagName("input");
			var img1 = tijiao.getElementsByTagName("img");
			var i = 0;
			for (i=0;i<aLi1.length;i++){
				aLi1[i].index = i;				
				aLi1[i].onclick = function() {
					for(i=0; i<5; i++){    
		                aLi1[i].className="";
		                img1[i].src="<%=request.getContextPath()%>/static/images/kongStar.png";
		            }
					if($(this).hasClass("active")==false){
						for(i=0; i<=this.index;i++){ 
							aLi1[i].className="active"
							img1[i].src="<%=request.getContextPath()%>/static/images/shiStar.png";
			            }
					}
				};

			}
			
			var isActive = document.getElementById("mySwitch").classList.contains("mui-active");
			if(isActive){
  			console.log("打开状态");
			}else{
  			console.log("关闭状态");  
			}
			var item1 = document.getElementById('item1mobile');
			var item2 = document.getElementById('item2mobile');
			document.getElementById('slider').addEventListener('slide',function(e){
				if (e.detail.slideNumber === 0){
					
				}
				if(e.detail.slideNumber === 1){
					
				}
			},1000)
		})
	</script>
</body>
</html>