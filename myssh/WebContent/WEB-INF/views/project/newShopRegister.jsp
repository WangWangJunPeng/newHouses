<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>门店注册</title>
        <link rel="stylesheet" type="text/css" href="static/css/newShopRegister.css" />
        
 <!--  <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
        <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=7d1ed249eb6d3503e90179890468dd74&plugin=AMap.Autocomplete,AMap.PlaceSearch"></script>
    -->
 
    </head>
    <body  >
        <div class="container">
            <div class="leftArea">
                <div class="topNotice">
                    <img src="static/images/laba.png" alt="" />
                    <span class="gong" >公告：</span>
                    <ul id="allAd">
                       
                    </ul>
                </div>
                <div class="mapBox">
                    <div id="allmap"></div>
                  
                </div>
                
            </div>
            <div class="rightArea">
                <div class="topName" style="display:none;">
                    <p style="font-size:30px;font-weight:bold;">注册九邑SAAS账号</p>
                   <!--  <p style="font-size:18px;letter-spacing: 5px;">输入您的地址看看您周边的项目</p> -->
                </div>
                <div class="welcomeName">
                    <p style="font-size:36px;font-weight:bold;">欢迎来到九邑SAAS</p>
                   <!--  <p style="font-size:18px;letter-spacing: 5px;">输入您的地址看看您周边的项目</p> -->
                </div>
                <form id="myForm">
                <div class="cn" style="display:none;">
                        <div class="every">
                            <img src="static/images/conpanyName.png" alt="" />
                            <input type="text" placeholder="请输入公司名称" name="companyName" id="companyName"/>
                        </div>
                    </div>
                     <div class="cner cner1" style="display:none;">
                        <div class="every">
                            <img src="static/images/housesShop.png" alt="" />
                            <input type="text" placeholder="请输入门店名称" name="shopName" id="shopName"/>
                        </div>
                    </div>
                <div class="twoName" style="display:none;">
                    <select name="province" class="prov" id="prov4" >
                    	<!-- <option value="" id="firstPro">请选择</option> -->
                    </select>
                    <select name="market" class="city" id="city4" >
                    	<!-- <option value="" id="firstCity">请选择</option> -->
                    </select>
                    <select name="area" class="dist" id="area4" >
                    	<!-- <option value="" id="firstArea">请选择</option> -->
                    </select>
                    <input id="hidprov4" type="hidden" name="city">
                    <!-- <input id="hidcity4" type="hidden" name="market">
                    <input id="hidarea4" type="hidden" name="area"> -->
                </div>
                <!-- 
                    <div class="threeName">
                        <p>为您找到<span>100</span>个可分销的项目</p>
                        
                    </div> -->
                 <div class="cner cner2" style="display:none;">
                        <div class="every">
                            <img src="static/images/mapWei.png" alt="" />
                            <input type="text" placeholder="请输入门店地址" name="address" id="address"/>
                            <input id="longitude" type="hidden" name="longitude">
                            <input id="latitude" type="hidden" name="latitude">
                        </div>
                    </div>
                     <div class="cner cner3" style="display:none;">
                        <div class="every">
                            <img src="static/images/peopleJJ.png" alt="" />
                            <input type="text" placeholder="联系人" name="contactPerson" id="contactPerson"/>
                        </div>
                    </div>

                    <div class="cner cner4" style="display:none;">
                        <div class="every">
                            <img src="static/images/telphone.png" alt="" />
                            <input type="text" placeholder="联系方式" name="phone" id="phone"/>
                        </div>
                    </div>
                      <div class="agree" style="display:none;">
                        <input type="checkbox" name="dd" id="dd" checked/>
                        <label for="dd">已阅读分销协议</label>
                       <span style="display:none;" class="error">输入框不能为空</span>
                    </div>
                    <div class="fourth" style="display:none;">
                       <input id="finish" type="submit" value="完成">
                    </div>
                    </form>
                    <div class="fifth" >
                        <button id="addUs">加入SAAS</button>
                    </div>
           			
           				
           			
                   
                    
                </div>  
                <div class="message">
                <p>我们将于12小时之内审核联系您</p>
            </div>
        </div>
    </body>
    <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="static/js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=dxCgW5uXbilxuIKHY7eYpujkgdsqSj3I"></script>
		    <script type="text/javascript" src="http://api.map.baidu.com/library/RichMarker/1.2/src/RichMarker_min.js"></script>
		
    <script type="text/javascript" src="static/js/newShopRegister.js"></script>
    <!--  <script type="text/javascript">
    $(document).on('blur','#phone',function(){
		var phoneNum = $("#phone").val();
		if(phoneNum){
			$.post("find_only_phone",
					{
					    "phoneNum":phoneNum
					  },
					  function(data){  
						if(data.status==200){//表示号码没有重复
							
							$("#finish").removeAttr("disabled");
						}
						if(data.status==202){
							alert(data.message);
							 $("#phone").val('');
							 $("#finish").attr("disabled","disabled");
						}
					  });
		}else{
			
		}
		
	
	})
    </script> -->
</html>