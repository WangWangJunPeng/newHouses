<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
    <title>订单状态</title>
    <link rel="stylesheet" href="static/frozenui/css/frozen.css">
    <link rel="stylesheet" type="text/css" href="static/css/anchangSuitable.css">
    <link rel="stylesheet" href="static/css/orderStatusApp.css">
   	<script src="static/js/flexible.js" type="text/javascript"></script>

</head>
<body>
	<header class="ui-header ui-header-positive ui-boeder-b">
        <i class="ui-icon-return" onclick="history.back()"></i>
        <h1 id="houseId"></h1>
        <input type="hidden" value="${orderId}" id="orderId">
    </header>
    <div class="ui-container">
        <div class="ui-tab" id="tab1">
            <ul class="ui-tab-nav ui-border-b">
                <li class="current">订单详情</li>
                <li>订单状态</li>
            </ul>
            <ul class="ui-tab-content" style="width:100%">
                <li class="mes">
                    <p class="adviser">订单发起顾问：<span id="orderSubmitMan"></span><img id="biezhen" src="static/images/biezhenApp.png">
                    </p>
                    <div id="touying">
                    	<div class="cusMess">
                        <h2 class="headTop">
                            <span class="shuxian"></span>
                            客户信息
                        </h2>
                        <div class="mainMes">
                            <div class="line">
                                <div class="leftbox">订单性质</div>
                                <div class="right" id="dingdanX">                                    
                                </div>
                            </div>
                            <div class="line">
                                <div class="leftbox">认购客户</div>
                                <div class="right" id="enterBuyName">                                    
                                </div>
                            </div>
                            <div class="line">
                                <div class="leftbox">身份证号码</div>
                                <div class="right" id="idCard">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="cusMess">
                        <h2 class="headTop">
                            <span class="shuxian"></span>
                            房源信息
                        </h2>
                        <div class="mainMes">
                            <div class="line">
                                <div class="leftbox">房源ID</div>
                                <div class="right" id="houseId1">
                                </div>
                            </div>
                            <div class="line">
                                <div class="leftbox">房号</div>
                                <div class="right" id="houseName">
                                </div>
                            </div>
                            <div class="line">
                                <div class="leftbox">预售证号</div>
                                <div class="right" id="willSellNo">
                                </div>
                            </div>
                            <div class="line">
                                <div class="leftbox">房型</div>
                                <div class="right" id="houseTypeName">
                                </div>
                            </div>
                            <div class="line">
                                <div class="leftbox">户型</div>
                                <div class="right" id="house1TypeName">
                                </div>
                            </div>
                            <div class="line">
                                <div class="leftbox">面积</div>
                                <div class="right" id="houseArea">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="cusMess">
                        <h2 class="headTop">
                            <span class="shuxian"></span>
                            价格优惠与结算方式
                        </h2>
                        <div class="mainMes">
                        	<div class="line">
                                <div class="leftbox">认购单价</div>
                                <div class="right" id="danPrice">
                                </div>
                            </div>
                            <div class="line">
                                <div class="leftbox">认购总价</div>
                                <div class="right" id="price">
                                </div>
                            </div>
                            <div class="line">
                                <div class="leftbox">优惠条款</div>
                                <div class="right" id="discount">
                                </div>
                            </div>
                            <div class="line">
                                <div class="leftbox">结算方式</div>
                                <div class="right" id="accountStyle">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="cusMess">
                        <h2 class="headTop">
                            <span class="shuxian"></span>
                            定金与条款
                        </h2>
                        <div class="mainMes">
                            <div class="line">
                                <div class="leftbox">定金数额</div>
                                <div class="right" id="earnest">
                                </div>
                            </div>
                            <div class="line">
                                <div class="leftbox">支付方式</div>
                                <div class="right" id="payStyle">
                                </div>
                            </div>
                            <div class="line">
                                <div class="leftbox">条款需知</div>
                                <div class="right" id="xuzhi">
                                </div>
                            </div>
                        </div>
                    </div>
                    </div>
                   <div class="bootomDiv directorCheck">
                        <div class="middle">
                            <button class="successed btn-1 cancle  notOk">拒绝</button>
                            <button class="successed  okok">同意</button>
                        </div>
                    </div>
                </li>
                <li>
                	 <div class="message orderStutas">
                        <div class="leftBox">
                        <span id="submitOrderTime"></span>
                        </div>
                        <div class="midBox"><img src="static/images/empyt_circl.png" alt=""></div>
                        <div class="rightBox">
                            <p id="orderStutas"></p>
                        </div>
                    </div>
                    <div class="message waitChect">
                        <div class="leftBox">
                        <span id="waitChectTime"></span>
                        </div>
                        <div class="midBox"><img src="static/images/empyt_circl.png" alt=""></div>
                        <div class="rightBox">
                            <p id="waitChect"></p>
                        </div>
                    </div>
                    <div class="message checked">
                        <div class="leftBox">
                        <span id="checkedTime"></span>
                        </div>
                        <div class="midBox"><img src="static/images/empyt_circl.png" alt=""></div>
                        <div class="rightBox">
                            <p id="checked"></p>
                        </div>
                    </div>
                    <div class="message outTime">
                        <div class="leftBox">
                        <span id="outTimeTime"></span>
                        </div>
                        <div class="midBox"><img src="static/images/empyt_circl.png" alt=""></div>
                        <div class="rightBox">
                            <p id="outTime"></p>
                        </div>
                    </div>
                    <div class="message payMoney">
                        <div class="leftBox">
                        <span id="payMoneyTime"></span>
                        </div>
                        <div class="midBox"><img src="static/images/empyt_circl.png" alt=""></div>
                        <div class="rightBox">
                            <p id="payMoney"></p>
                        </div>
                    </div>
                    <div class="message enterPay">
                        <div class="leftBox">
                        <span id="enterPayTime"></span>
                        </div>
                        <div class="midBox"><img src="static/images/empyt_circl.png" alt=""></div>
                        <div class="rightBox">
                            <p id="enterPay"></p>
                        </div>
                    </div>
                    <div class="message sign">
                        <div class="leftBox">
                        <span id="signTime"></span>
                        </div>
                        <div class="midBox"><img src="static/images/empyt_circl.png" alt=""></div>
                        <div class="rightBox">
                            <p id="sign"></p>
                        </div>
                    </div>
                    <div class="empty"></div>
                    <div class="bootomDiv directorCheck">
                        <div class="middle">
                            <button class="successed btn-1 cancle notOk">拒绝</button>
                            <button class="successed  okok">同意</button>
                        </div>
                    </div> 
                </li>
            </ul>
        </div>
    </div>

    

    <script src="static/frozenui/lib/zepto.min.js"></script>
    <script src="static/frozenui/js/frozen.js"></script>
    <script src="static/js/orderStatus.js"></script>
    <script >
        (function (){
            var tab = new fz.Scroll('.ui-tab', {
                role: 'tab',
                autoplay: false,
            });
            /* 滑动开始前 */
            tab.on('beforeScrollStart', function(fromIndex, toIndex) {
                // from 为当前页，to 为下一页
            })
            /* 滑动结束 */
             tab.on('scrollEnd', function(curPage) {
                 // curPage 当前页
             });
        })();
        </script>
</body>
</html>