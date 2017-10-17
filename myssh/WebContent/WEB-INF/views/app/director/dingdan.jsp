<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
        <title>我的订单</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/frozenui/css/frozen.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/mui.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/anchangSuitable.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/dingdan.css">
        <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>        
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script src="<%=request.getContextPath()%>/static/js/flexible.js" type="text/javascript"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/iconfont.js"></script>
</head>
<body ontouchstart="">
	<header class="ui-header ui-header-positive ui-boeder-b">
        <h1>我的订单</h1>
        <%-- <input type="hidden" value=""> --%>
    </header>
    <footer class="ui-footer ui-footer-btn">
           <ul class="ui-tiled ui-border-t">
                <li data-href="to_director_index" class="ui-border-r" id="li1"><div><svg class="icon" aria-hidden="true" >
                     <use xlink:href="#icon-xieziloubiaozhunhetong"></use>
                    </svg><p>案场</p></div></li>
                <li data-href="to_getManagerAnalysePage" class="ui-border-r" id="li2"><div><svg class="icon" aria-hidden="true" >
                     <use xlink:href="#icon-zonghefenxi"></use>
                    </svg><p>分析</p></div></li>
                <li data-href="###" class="ui-border-r" id="li3"><div><svg class="icon" aria-hidden="true" >
                     <use xlink:href="#icon-dingdan1"></use>
                    </svg><p class="colorB">订单</p></div></li>
                <li data-href="to_getManagerMyInfoPage" id="li4"><div><svg class="icon" aria-hidden="true" >
                     <use xlink:href="#icon-jingjiren"></use>
                    </svg><p>我</p></div></li>
            </ul>
        </footer>
    <div class="ui-container">
    	<div id="slider" class="mui-slider mui-fullscreen">
            <div id="sliderSegmentedControl" class="mui-scroll-wrapper mui-slider-indicator mui-segmented-control mui-segmented-control-inverted">
            <div class="mui-scroll">
                <a class="mui-control-item mui-active" data-value="all" href="#item1mobile">
                    <img src="<%=request.getContextPath()%>/static/images/allOrder.png" alt="" />
                    全部订单
                </a>
                <a class="mui-control-item" href="#item2mobile">
                    <img src="<%=request.getContextPath()%>/static/images/examine.png" alt="" />
                    待审核
                </a>
                <a class="mui-control-item" href="#item3mobile">
                    <img src="<%=request.getContextPath()%>/static/images/payment.png" alt="" />
                    待付款
                </a>
                <a class="mui-control-item" href="#item4mobile">
                    <img src="<%=request.getContextPath()%>/static/images/noSign.png" alt="" />
                    待签约
                </a>
                <a class="mui-control-item" href="#item5mobile">
                    <img src="<%=request.getContextPath()%>/static/images/alreadySign.png" alt="" />
                    已签约
                </a>
                <a class="mui-control-item" href="#item6mobile">
                    <img src="<%=request.getContextPath()%>/static/images/reject.png" alt="" />
                    已拒绝
                </a>
                <a class="mui-control-item" href="#item7mobile">
                    <img src="<%=request.getContextPath()%>/static/images/cancle.png" alt="" />
                    已撤销
                </a>
            </div>
        </div>
        <div class="mui-slider-group">
            <div id="item1mobile" class="mui-slider-item mui-control-content mui-active">
                <div id="scroll1" class="mui-scroll-wrapper">
                    <div class="warpp"><h2 class="titleh2">最近订单</h2></div>
                    <div class="mui-scroll">
                    <ul class="mui-table-view" id="allOrder">
                    </ul>
                    </div>
                 </div>
            </div>
            <div id="item2mobile" class="mui-slider-item mui-control-content">
                <div id="scroll1" class="mui-scroll-wrapper">
                    <div class="warpp"><h2 class="titleh2">最近订单</h2></div>
                    <div class="mui-scroll">
                        <ul class="mui-table-view" id="waitCheck">

                        </ul>
                    </div>
                </div>
            </div>
            <div id="item3mobile" class="mui-slider-item mui-control-content">
                <div id="scroll1" class="mui-scroll-wrapper">
                    <div class="warpp"><h2 class="titleh2">最近订单</h2></div>
                    <div class="mui-scroll">
                        <ul class="mui-table-view" id="waitPay">
                        </ul>
                        </div>
                     </div>
                </div>
            <div id="item4mobile" class="mui-slider-item mui-control-content">
                <div id="scroll1" class="mui-scroll-wrapper">
                    <div class="warpp"><h2 class="titleh2">最近订单</h2></div>
                    <div class="mui-scroll">
                        <ul class="mui-table-view" id="waitSign">
                        </ul>
                    </div>
                </div>
            </div>
            <div id="item5mobile" class="mui-slider-item mui-control-content">
                <div id="scroll1" class="mui-scroll-wrapper">
                    <div class="warpp"><h2 class="titleh2">最近订单</h2></div>
                    <div class="mui-scroll">
                        <ul class="mui-table-view" id="hadSign">
                        </ul>
                    </div>
                </div>
            </div>
            <div id="item6mobile" class="mui-slider-item mui-control-content">
                <div id="scroll1" class="mui-scroll-wrapper">
                    <div class="warpp"><h2 class="titleh2">最近订单</h2></div>
                    <div class="mui-scroll">
                        <ul class="mui-table-view" id="hadRefuse">
                        </ul>
                    </div>
                </div>
            </div>
            <div id="item7mobile" class="mui-slider-item mui-control-content">
                <div id="scroll1" class="mui-scroll-wrapper">
                    <div class="warpp"><h2 class="titleh2">最近订单</h2></div>
                    <div class="mui-scroll">
                        <ul class="mui-table-view" id="hadRevoke">
                        </ul>
                    </div>
                </div>
            </div>
            <div id="item8mobile" class="mui-slider-item mui-control-content">
                <div id="scroll1" class="mui-scroll-wrapper">
                    <div class="warpp"><h2 class="titleh2">最近订单</h2></div>
                    <div class="mui-scroll">
                        <ul class="mui-table-view" id="waitPayEnter">
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="<%=request.getContextPath()%>/static/frozenui/lib/zepto.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/frozenui/js/frozen.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/mui.min.js"></script>
	<script src="<%=request.getContextPath()%>/static/js/mui.pullToRefresh.js"></script>
	<script src="<%=request.getContextPath()%>/static/js/mui.pullToRefresh.material.js"></script>
    <script type="text/javascript">
    var start = 0;
	var limit = 5;
	getCurrentMenuData(0);
			mui.init({
			});
			(function($){
				//阻尼系数
				var deceleration = mui.os.ios?0.003:0.0009;
				$('.mui-scroll-wrapper').scroll({
					bounce: false,
					indicators: true, //是否显示滚动条
					deceleration:deceleration
				});
				
				$.ready(function() {
					
					var item1 = document.getElementById('item1mobile');
					var item2 = document.getElementById('item2mobile');
					var item3 = document.getElementById('item3mobile');
					var item5 = document.getElementById('item5mobile');
					var item6 = document.getElementById('item6mobile');
					var item7 = document.getElementById('item7mobile');
					var item8 = document.getElementById('item8mobile');
					document.getElementById('slider').addEventListener('slide',function(e){
						if (e.detail.slideNumber === 0){
							//alert(1);
							getCurrentMenuData(0);
						}
						if(e.detail.slideNumber === 1){
							//alert(2);
							getCurrentMenuData(1);
						}
						if(e.detail.slideNumber === 2){
							getCurrentMenuData(2);
						}
						if(e.detail.slideNumber === 3){
							getCurrentMenuData(3);
						}
						if(e.detail.slideNumber === 4){
							getCurrentMenuData(4);
						}
						if(e.detail.slideNumber === 5){
							getCurrentMenuData(5);
						}
						if(e.detail.slideNumber === 6){
							getCurrentMenuData(6);
						}
						if(e.detail.slideNumber === 7){
							getCurrentMenuData(7);
						}
					},1000)
					//循环初始化所有下拉刷新，上拉加载。
					$.ready(function() {
						//循环初始化所有下拉刷新，上拉加载。
						 $.each(document.querySelectorAll('.mui-slider-group .mui-scroll'), function(index, pullRefreshEl) {
							$(pullRefreshEl).pullToRefresh({
								down: {
									callback: function() {
										var self = this;
										setTimeout(function() {
											limit = limit+5;
											var ul = self.element.querySelector('.mui-table-view');
											ul.insertBefore(createFragment(ul, index, 5,true), ul.firstChild);
											self.endPullDownToRefresh();
										}, 1000);
									}
								},
								up: {
									callback: function() {
										var self = this;
										setTimeout(function() {
											limit = limit+5;
											var ul = self.element.querySelector('.mui-table-view');
												ul.appendChild(createFragment(ul, index, 2));
												self.endPullUpToRefresh();
										}, 1000);
									}
								}
							});
							getId();
							getLocation();
						});
						var createFragment = function(ul, index, count, reverse) {
							var id = null;
							if(index==0){
								//全部
								id = "allOrder";
							}else if(index==1){
								//待审核
								id = "waitCheck";
							}else if(index==2){
								//待付款
								id = "waitPay";
							}else if(index==3){
								//待签约
								id = "waitSign";
							}else if(index==4){
								//已签约
								id = "hadSign";
							}else if(index==5){
								//已决绝
								id = "hadRefuse";
							}else if(index==6){
								//已撤销
								id = "hadRevoke";
							}else if(index==7){
								//付款待确认
								id = "waitPayEnter";
							}
							var fragment = document.createDocumentFragment();
							section = document.createElement('section');
							/* for (var i = 0; i < count; i++) {
								li = document.createElement('li');
								li.className = 'mui-table-view-cell';
								li.innerHTML = '第' + (index + 1) + '个选项卡子项-' + (length + (reverse ? (count - i) : (i + 1)));
								fragment.appendChild(li);
							} */
							var div = getCurrentMenuData(index);
							if(div!=undefined){
								dropHtml(id);
								section.innerHTML=div;
								fragment.appendChild(section);
								return fragment;
							}else{
								return fragment;
							}
						};
					});
					
				});
			})(mui);
	
		function dropHtml(id){
			$("#"+id).children().remove();
		}
		
		//获取当前菜单的数据
		function getCurrentMenuData(menu){
			var url = null;
			//var str = null;
			if(menu==0){
				//全部
				url = "to_get_all_order_data_app";
			}else if(menu==1){
				//待审核
				url = "to_wait_check_order_data_app";
			}else if(menu==2){
				//待付款
				url = "to_wait_pay_money_order_data_app";
			}else if(menu==3){
				//待签约
				url = "to_wait_sign_order_data_app";
			}else if(menu==4){
				//已签约
				url = "to_already_sign_order_data_app";
			}else if(menu==5){
				//已决绝
				url = "to_already_refuse_order_data_app";
			}else if(menu==6){
				//已撤销
				url = "to_already_sign_order_data_app";
			}
			//判断url,执行后台方法，获取数据
			
			if(url!=null){
				
				$.ajax({
	    		  type:"post",
	    		  async : false,
	    		  dataType:"json",
	    		  url:url,
	    		  data:{start:start,
	    			  limit:limit},
	    	   	  success:function(data){
	    	   		getToAllContractRecords(data,menu);
	    	   		getId();
	    	   		
	    	   	  }
		    	});
				
			}else{
				
			}
		
		}
		//添加数据
		function getToAllContractRecords(data,menu){
			 var div='';
			$.each(data.root,function(v,o){
				 div +='<a><div class="message" data-value='+o.recordNo+'><div class="container"><div class="leftbox">';
				 div +='<img src="'+o.houseTypePic+'" alt="" /></div><div class="rightbox">';
				 //div +='<h2><span>'+o.objectName+'</span></h2>';
				 div +='<h2><span>'+o.buildingNo+'</span><span>栋</span><span>'+o.unit+'</span><span>单元</span>';
				 //div += '<span>'+o.floor+'</span><span>楼</span>';
				 div += '<span>'+o.houseNo+'</span><span>号</span></h2><p>下单时间：<span>'+o.applyTime+'</span></p>';
				 //div +='<p>认购客户：<span>'+o.customerName+'</span><span class="redcolor floatRight">';
				 div += '<span>总价:</span>'+o.listPrice+'元</span>';
				 //div += '</p>';
				 if (o.recordStatus == 0 ){
					 div +='<p><span class="redcolor">待审核</span><span class="floatRight"><span>单价:</span>'+o.unitPrice+'元/㎡</span></p>';
				 }
				 if (o.recordStatus == 1 && o.voucherUploadTime!=null && o.voucherUploadTime!=''){
					 div +='<p><span class="redcolor">付款待确认</span><span class="floatRight"><span>单价:</span>'+o.unitPrice+'元/㎡</span></p>';
				 }
				 if (o.recordStatus == 1 && (o.voucherUploadTime==null || o.voucherUploadTime=='')){
					 div +='<p><span class="redcolor">待付款</span><span class="floatRight"><span>单价:</span>'+o.unitPrice+'元/㎡</span></p>';
				 }
				 if (o.recordStatus == 3 ){
					 div +='<p><span class="redcolor">已拒绝</span><span class="floatRight"><span>单价:</span>'+o.unitPrice+'元/㎡</span></p>';
				 }
				 if (o.recordStatus == 4 ){
					 div +='<p><span class="redcolor">待签约</span><span class="floatRight"><span>单价:</span>'+o.unitPrice+'元/㎡</span></p>';
				 }
				 if (o.recordStatus == 5 ){
					 div +='<p><span class="redcolor">已签约</span><span class="floatRight"><span>单价:</span>'+o.unitPrice+'元/㎡</span></p>';
				 }
				 if (o.recordStatus == 7 ){
					 div +='<p><span class="redcolor">已撤销</span><span class="floatRight"><span>单价:</span>'+o.unitPrice+'元/㎡</span></p>';
				 }
				 div +='</div></div></div></a>';
				 
			 })
			 //全部
			 if(data.total>0 && menu==0){
				 $("#allOrder").html(div);
				 return div;
			 }else{
				 $("#picInfos").html("<br/><span style='width:100%;height:30px;display:block;margin:0 auto;text-align:center;font-size:.3rem;'>暂无数据</span>");
				 
			 }
			//待审核
			if(data.total>0 && menu==1){
				 $("#waitCheck").html(div);
				 return div;
			 }else{
				 $("#waitCheck").html("<br/><span style='width:100%;height:30px;display:block;margin:0 auto;text-align:center;font-size:.3rem;'>暂无数据</span>");
			 }
			//待付款
			if(data.total>0 && menu==2){
				 $("#waitPay").html(div);
				 return div;
			 }else{
				 $("#waitPay").html("<br/><span style='width:100%;height:30px;display:block;margin:0 auto;text-align:center;font-size:.3rem;'>暂无数据</span>");
			 }
			//待签约
			if(data.total>0 && menu==3){
				 $("#waitSign").html(div);
				 return div;
			 }else{
				 $("#waitSign").html("<br/><span style='width:100%;height:30px;display:block;margin:0 auto;text-align:center;font-size:.3rem;'>暂无数据</span>");
			 }
			//已签约
			if(data.total>0 && menu==4){
				 $("#hadSign").html(div);
				 return div;
			 }else{
				 $("#hadSign").html("<br/><span style='width:100%;height:30px;display:block;margin:0 auto;text-align:center;font-size:.3rem;'>暂无数据</span>");
			 }
			//已决绝
			if(data.total>0 && menu==5){
				 $("#hadRefuse").html(div);
				 return div;
			 }else{
				 $("#hadRefuse").html("<br/><span style='width:100%;height:30px;display:block;margin:0 auto;text-align:center;font-size:.3rem;'>暂无数据</span>");
			 }
			//已撤销
			if(data.total>0 && menu==6){
				 $("#hadRevoke").html(div);
				 return div;
			 }else{
				 $("#hadRevoke").html("<br/><span style='width:100%;height:30px;display:block;margin:0 auto;text-align:center;font-size:.3rem;'>暂无数据</span>");
			 }
			//付款待确认
			if(data.total>0 && menu==7){
				 $("#waitPayEnter").html(div);
				 return div;
			 }else{
				 $("#waitPayEnter").html("<br/><span style='width:100%;height:30px;display:block;margin:0 auto;text-align:center;font-size:.3rem;'>暂无数据</span>");
			 }
		}
		//订单列表点击跳转
		/* function setHiddenValue(d){
	   		window.location.href = "to_goToReadOneContractRecord?recordNo="+d;
		} */
		function getId(){
			$(".message").on('tap', function() { 
				window.location.href = "to_go_get_order_deail_data_app?orderId="+$(this).data("value");
			})
		}
		function getLocation(){
			$("#li1").on('tap', function() { 
				console.log(1)
				window.location.href = "to_director_index" ;
			})
			$("#li2").on('tap', function() { 
				window.location.href = "to_getManagerAnalysePage" ;
			})
			$("#li4").on('tap', function() { 
				window.location.href = "to_getManagerMyInfoPage" ;
			})
		}
	</script>
</body>
</html>