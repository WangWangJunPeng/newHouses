<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
    <link  rel="icon" href="static/images/titleLogo.png"  />
    <title>门店管理后台</title>
   
    <link rel="stylesheet" type="text/css" href="static/css/otherMap.css" />
  
    
   	  <style type="text/css">
        *{padding:0;margin:0;}  
        .IIInsomnia-city-picker{top:40px;}      
       </style>
</head>
    <body>
    <div id="maskBox">
    
    </div>
    <div id="waiting"> 
    	<p>加载中，请等待......<img alt="" src="static/images/5-1503130Q911.gif"></p>
    </div>
		<div id="allmap" style="height: 850px;"></div>
		<div class="youceFourth" style="display:none;width:756px;height:850px;position:absolute;top:0;z-index:900;right:410px;display:none;">
		</div>
	<div id="mapList"
		style="width: 410px; background: #e4e8ef; position: absolute; top: 0px; right: 0; z-index: 1000;">
		<input type="text" id="cityChoice" placeholder="请选择城市" class="cityChoose" >
		 <input type="hidden" id="province" value=""> 
		 <input type="hidden" id="city" value="">
			<input id="signValue" type="hidden" value="1">
			<button class="changeBtn"  id="changeBtn" onclick="getAllTags()">筛选</button>
		<div class="total">
			<div class="qiehuan">
				<input id="clickValue" type="hidden" value=0>
				<button style="width: 202px; height: 36px; font-size: 14px; line-height: 36px; border: 0; background: #e4e8ef; margin-top: 10px;" id="pro" onclick="getProOrShopData(0)">项目</button>
				<button style="width: 202px; height: 36px; font-size: 14px; line-height: 36px; border: 0; background: #46c1de; margin-top: 10px;" id="sho" onclick="getProOrShopData(1)">门店</button>
			</div>
			<div class="listZong" >
				<div class="topBack">
					<span class="foundList">为您找到<span id="proCount">0</span><span>个项目</span></span>
				</div>
				<div id="proData">
				</div>
			<!--<div class="everyOne">
					<div class="listLeft">
						<img src="images/noPic.png" alt="" />
					</div>
					<div class="listRight">
						<div class="proName">
							<span style="color: #47506c; font-size: 16px; cursor: pointer;"
								class="xiangmu">滨江花园一号</span>
						</div>
						<div class="zige">
							<span
								style="width: 56px; border: 1px solid #ff6161; color: #ff6161;">平台认证</span>
							<span
								style="width: 96px; border: 1px solid #ab90e5; color: #ab90e5;">均价20000元/平</span>
						</div>
						<div class="yongjin">
							<div class="xiao">
								<p style="color: #fd6c38; font-size: 12px;">3%</p>
								<p style="color: #616b88; font-size: 12px;">平均佣金</p>
							</div>

							<div class="xiao" style="margin-left: 30px;">
								<p style="color: #fd6c38; font-size: 12px;">签约后三天内</p>
								<p style="color: #616b88; font-size: 12px;">结佣时间</p>
							</div>
						</div>
						<div class="houseSale">
							<div class="xiao shu clis">
								<p style="color: #46c1de; font-size: 12px;">50套</p>
								<p style="color: #46c1de; font-size: 12px;">可售房源</p>
							</div>

							<div class="xiao" style="margin-left: 40px;">
								<p style="color: #46c1de; font-size: 12px;">30套</p>
								<p style="color: #46c1de; font-size: 12px;">已售房源</p>
							</div>
						</div>
					</div>
				</div> -->
			</div>
			<div class="listZongTwo" style="display:none;">
				<div class="topBack">
					<span class="foundList">为您找到<span id="shopCounn">120</span><span>个门店</span></span>
				</div>
				<div id="shopsData"></div>
				<!-- <div class="everyList mendian" >
					<div class="leftImg">
						<img src="static/images/morentouxiang.png" alt="" />
					</div>
					<div class="rightWord">
						<p style="font-size: 16px;color:#616b88;margin-top:10px;">链家滨江地产</p>
						<p style="font-size: 12px;color:#616b88;margin-top:10px;">已备案：<span>3</span></p>
						<p style="font-size: 12px;color:#616b88;margin-top:10px;">已成交：<span>5</span></p>
						<p style="font-size: 12px;color:#616b88;margin-top:10px;">经济人数：<span>6</span></p>
						<div class="surePLat">平台认证</div>
					</div>	
				</div> -->
			</div>
		</div>
	</div>

	<div class="youce" style="width: 756px; height: 850px; position: absolute; top: 0; z-index: 900; right: 410px; display: none;">
		<div id="proDetails">
			<div class="top">
				<span style="display: inline-block; width: 4px; height: 14px; background: #ff6161;"></span>
				<span style="font-size: 16px; color: #565e78;" id="grlProName1">钱江地产</span>
			</div>
			<div class="second">
				<span style="font-size: 14px; color: #565e78;" id="grlProName2">钱江花园</span> <span style="font-size: 14px; color: #565e78; margin-left: 20px;" id="grlPreaName">滨江区</span>
			</div>
			<div class="third">
				<span>房源：<span id="grlHouseCount">共20套/剩余13套</span></span> <span style="margin-left: 30px;">佣金比例<span id="grlDaikanMoney">3%</span></span> <span style="margin-left: 30px;">结佣速度：<span id="grlIsFast">快速结佣</span></span>
			</div>
			<div class="banner">
				<ul class="banner-img" id="grlPicImg">
					<!-- <li><a href="#"><img src="static/images/noPic.png"></a></li>
					<li><a href="#"><img src="static/images/noPic.png"></a></li>
					<li><a href="#"><img src="static/images/noPic.png"></a></li>
					<li><a href="#"><img src="static/images/noPic.png"></a></li>
					<li><a href="#"><img src="static/images/noPic.png"></a></li>
					<li><a href="#"><img src="static/images/noPic.png"></a></li> -->
				</ul>
			</div>
			<div class="banner-btn">
				<a href="javascript:;" class="prevBtn"> < </a> <a
					href="javascript:;" class="nextBtn"> > </a>
			</div>
			<div class="fourth">

				<span
					style="display: inline-block; width: 4px; height: 14px; background: #ff6161;"></span>
				<span style="font-size: 16px; color: #565e78;">项目信息</span>

			</div>
			<div class="fifth">
				<div class="single">
					<p>
						所<span style="margin-left: 7px;">在</span><span
							style="margin-left: 7px;">地：</span><span style="color: #ff5c5b;" id="grlAddress">滨江江南之心</span>
					</p>
					<p>
						用地面积：<span style="color: #7a8196;" id="grlLandArea">340000.00m2</span>
					</p>
					<p>
						地上面积：<span style="color: #7a8196;" id="grlGroudArea">340000.00m2</span>
					</p>
					<p>
						地下面积：<span style="color: #7a8196;" id="grlUnderGroudArea">340000.00m2</span>
					</p>
				</div>
				<div class="single">
					<p>
						总<span style="margin-left: 7px;">户</span><span style="margin-left: 7px;">数：</span><span style="color: #7a8196;" id="grlTotalHuShu">2552</span>
					</p>
					<p>
						密<span style="margin-left: 28px;">度：</span><span style="color: #7a8196;" id="grlMiDu">37.00%</span>
					</p>
					<p>
						绿<span style="margin-left: 7px;">化</span><span style="margin-left: 7px;">率：</span><span style="color: #7a8196;" id="grlLVHuRate">30.00%</span>
					</p>
					<p>
						物<span style="margin-left: 7px;">业</span><span style="margin-left: 7px;">费：</span><span style="color: #7a8196;" id="grlWuYeFei">3.8元/m2</span>
					</p>
				</div>
				<div class="single">
					<p>
						开工时间：<span style="color: #7a8196;" id="grlStartTime">2017-01-02</span>
					</p>
					<p>
						交互时间：<span style="color: #7a8196;" id="grlJiaoHuTime">2017-06-04</span>
					</p>
					<p>
						开<span style="margin-left: 7px;">发</span><span style="margin-left: 7px;">商：</span><span style="color: #7a8196;" id="grlKaifashang">杭州津滨好好久好久</span>
					</p>
					<p>
						物业管理：<span style="color: #7a8196;" id="grlWuyeguanli">很骄傲就看见看见就看好</span>
					</p>
				</div>
			</div>
			<div class="sixth">
				<span style="display: inline-block; width: 4px; height: 14px; background: #ff6161;"></span>
				<span style="font-size: 16px; color: #565e78;">房屋简介</span>
			</div>
			<div class="seventh">
				<p id="grlFangwuJianJie">暂无</p>
			</div>
		</div>
		<div id="hide" onclick="hidePro()">></div>
	</div>
	
	<div class="youceTwo" style="width:446px;height:850px;position:absolute;top:0;z-index:900;right:410px;display:none;">
        		<div id="fangBox" style="width:410px;background:#e4e8ef;position:absolute;top:0px;z-index:1000;right:0;">
			            <button class="changeBtn" style="margin-left:20px;margin-top:12px; background: #46c1de;" onclick="getSignHouse(1)">可售</button>
			            <button class="changeBtn" onclick="getSignHouse(0)">全部</button>
			            <input id="selectProjectId" type="hidden">
			            <div class="fangList" style="background:#f0f4fb;height:800px;overflow:auto;margin-top:12px;">
			                <!-- <div class="everyHouse cha" >
		                       <div class="listLeft" style="margin-top:30px;">
		                           <img src="static/images/noPic.png" alt="" />
		                       </div>
		                       <div class="listLeft" style="margin-top:30px;">
		                           <p style="font-size:16px;color:#47506c;">滨江区海床基地北楼B座</p>
		                           <span style="font-size:16px;color:#47506c;">2059室</span><span style="font-size:18px;color:#ff6c39;margin-left:65px;">365万</span>
		                           <div style="margin-top:6px;">
		                               <span style="font-size:12px;color:#616b88;">89m2</span>
		                               <span style="font-size:12px;color:#616b88;margin-left:8px;">3室两厅一卫</span>
		                               <span style="font-size:12px;color:#616b88;margin-left:8px;">23000/平</span>
		                           </div>
		                           <div class="zj">
		                               <span style="width:56px;border:1px solid #ff6161;color:#ff6161;">平台认证</span>
		                               <span style="width:56px;border:1px solid #ab90e5;color:#ab90e5;">精装修</span>
		                           </div>
		                       </div>
			                </div> --> 
		            	</div>
		        	</div>     
				<div id="hideTwo" onclick="hideHouse()">
	                >
	            </div>
			</div>
	
	<div class="youceThree" style="width:756px;height:850px;position:absolute;top:0;z-index:900;right:820px;display:none;">

	            <div id="houseDetails">
	                <div class="top" style="margin-top:30px">
	                    <span style="display:inline-block;width:4px;height:14px;background:#ff6161;"></span>
	                    <span style="font-size: 16px;color:#616b88;font-weight:bold;" id="h_houseName">滨江区海床基地北楼B座20859</span>
	                    <span style="font-size: 24px;color:#ff5c5b;margin-left:20px;font-weight:bold;" id="h_listPrice">356万</span>
	                    <span style="font-size: 14px;color:#616b88;margin-left:10px;" id="h_benefits">优惠：<span>0.01%</span></span>
	                    <span style="font-size: 14px;color:#616b88;margin-left:10px;">最低成交价</span>
	                </div>
	               
	                <div class="third">
	                    <span style="font-weight:bold;">房源类型：<span id="h_houseKind">排污</span></span>
	
	                   
	
	                    <span style="margin-left:30px;">佣金比例<span id="h_daikanMoney">3%</span></span>
	
	                    <span style="margin-left:30px;" id="h_buildArea">89m2</span>
	                    <!-- <span style="margin-left:30px;">毛坯</span> -->
	                </div>
	                <div class="third">
	                    <span id="h_unitPrice">2300/平</span>
	                    <span style="margin-left:30px;">朝向：<span id="h_direct">南</span></span>
	                    <span style="margin-left:30px;" id="h_decorationStandard">精装修</span>
	                    <span style="margin-left:30px;" id="h_houseType">3室两厅一卫</span>
	                </div>
	                <div class="biaoQian" id="h_houseTags">
	                    <!-- <span style="background:#c5a9dd;">厨卫不对门</span>
	                    <span style="background:#ecc896;">户型方正</span>
	                    <span style="background:#e0a0a0;">主卧带卫</span>
	                    <span style="background:#96cf95;">餐客分离</span> -->
	                </div>
	                <div class="banner">
	                    
	                    <ul class="banner-img" id="h_housePic">
	                        <li><a href="#"><img src="" id="h_img"></a></li>
	                    </ul>
	                </div>
	                <div class="banner-btn">
	                    <a href="javascript:;" class="prevBtn" style="top:340px;"> < </a>
	                    <a href="javascript:;" class="nextBtn" style="top:340px;"> > </a>
	                </div>
	                <div class="fourth">
	           
	                    <span style="display:inline-block;width:4px;height:14px;background:#ff6161;"></span>
	                    <span style="font-size: 16px;color:#565e78;">房源信息</span>
	                    
	                </div>
	                <div class="fifth">
	                    <div class="single">
	                        <p>所<span style="margin-left:7px;">在</span><span style="margin-left:7px;">地：</span><span style="color:#ff5c5b;" id="h_houseAddress">滨江江南之心</span></p>
	                        <p>用地面积：<span style="color:#7a8196;" id="h_landArea">340000.00m2</span></p>
	                        <p>地上面积：<span style="color:#7a8196;" id="h_groundArea">340000.00m2</span></p>
	                        <p>地下面积：<span style="color:#7a8196;" id="h_underGroundArea">340000.00m2</span></p>
	                    </div>
	                    <div class="single">
	                         <p>总<span style="margin-left:7px;">户</span><span style="margin-left:7px;">数：</span><span style="color:#7a8196;" id="h_unitCount">2552</span></p>
	                        <p>密<span style="margin-left:28px;">度：</span><span style="color:#7a8196;" id="h_density">37.00%</span></p>
	                        <p>绿<span style="margin-left:7px;">化</span><span style="margin-left:7px;">率：</span><span style="color:#7a8196;" id="h_afforestationRatio">30.00%</span></p>
	                        <p>物<span style="margin-left:7px;">业</span><span style="margin-left:7px;">费：</span><span style="color:#7a8196;" id="h_propertyCost">3.8元/m2</span></p>
	                    </div>
	                    <div class="single">
	                         <p>开工时间：<span style="color:#7a8196;" id="h_startTime">2017-01-02</span></p>
	                        <p>交互时间：<span style="color:#7a8196;" id="h_deliverTime">2017-06-04</span></p>
	                        <p>开<span style="margin-left:7px;">发</span><span style="margin-left:7px;">商：</span><span style="color:#7a8196;" id="h_developer">杭州津滨好好久好久</span></p>
	                        <p>物业管理：<span style="color:#7a8196;" id="h_manager">很骄傲就看见看见就看好</span></p>
	                    </div>
	                </div>
	                <div class="sixth">
	                     <span style="display:inline-block;width:4px;height:14px;background:#ff6161;"></span>
	                    <span style="font-size: 16px;color:#565e78;">房屋简介</span>
	                </div>
	                <div class="seventh">
	                    <p>暂无</p>
	                </div>
	            </div>
	            <div id="hideThree" onclick="hideHouseInfo()">
	                >
	            </div>
        	</div>
	 <div class="proviceCity" style="display:none;width:360px;height:200px;background:#ffffff;position:absolute;top:60px;right:30px;;z-index:1100;border:1px solid #999;border-radius:6px;">
<!--            			<span style="display:inline-block;font-size:10px;width:48px;height:20px;line-height:20px;background:#f1f1f1;color:#83889a;text-align:center; border-radius:4px;margin-left:8px;margin-top:8px;">jkjkjkj</span>
 -->         
       </div>
       <div id="shanBox"
		style="width: 410px; height: 560px; background: #ffffff; position: absolute; top: 60px; right: 0; z-index: 1100;display:none;">
		<div class="sureBtn">
			<button id="okBtn" onclick="getProByTags()">确认</button>
		</div>
		<div class="biaoQianList" style="height: 520px; overflow: auto;">
			<div id="proTags">
				<!--  <p style="font-size: 16px; color: #83889a; margin-left: 14px;">项目</p>
				<div class="proLableList">
					<div style="margin-left: 20px; margin-top: 20px;">
						<p style="font-size: 14px; color: #83889a; margin-bottom: 20px;">规模特征</p>

						<input type="checkbox" name="gui" id="0" style="display: none;" />
						<label for="0" class="mo">超大盘</label> <input type="checkbox"
							name="gui" id="1" style="display: none;" /> <label for="1"
							class="mo">大盘</label> <input type="checkbox" name="gui" id="2"
							style="display: none;" /> <label for="2" class="mo">中盘</label> <input
							type="checkbox" name="gui" id="3" style="display: none;" /> <label
							for="3" class="mo">小盘</label> <input type="checkbox" name="gui"
							id="4" style="display: none;" /> <label for="4" class="mo">小盘</label>
						<input type="checkbox" name="gui" id="5" style="display: none;" />
						<label for="5" class="mo">小盘</label>
					</div>
				</div>  -->
			</div>
			<div style="margin-top: 30px;" id="hTags">
				<!--  <p style="font-size: 14px; color: #83889a; margin-left: 14px;">房源</p>
			<div class="proLableList">
				<div style="margin-left: 20px; margin-top: 20px;">
					<p style="font-size: 14px; color: #83889a; margin-bottom: 20px;">规模特征</p>

					<input type="checkbox" name="gui" id="0" style="display: none;" />
					<label for="0" class="mo">超大盘</label> <input type="checkbox"
						name="gui" id="1" style="display: none;" /> <label for="1"
						class="mo">大盘</label> <input type="checkbox" name="gui" id="2"
						style="display: none;" /> <label for="2" class="mo">中盘</label> <input
						type="checkbox" name="gui" id="3" style="display: none;" /> <label
						for="3" class="mo">小盘</label> <input type="checkbox" name="gui"
						id="4" style="display: none;" /> <label for="4" class="mo">小盘</label>
					<input type="checkbox" name="gui" id="5" style="display: none;" />
					<label for="5" class="mo">小盘</label>
				</div>
			  
			  
			  </div>  -->
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
 <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=dxCgW5uXbilxuIKHY7eYpujkgdsqSj3I"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/library/RichMarker/1.2/src/RichMarker_min.js"></script>
    
<script type="text/javascript" src="static/js/otherMap.js"></script>
<script type="text/javascript">
/* $(function(){
        var $banner=$('.banner');
        var $banner_ul=$('.banner-img');
        var $btn=$('.banner-btn');
        var $btn_a=$btn.find('a')
        var v_width=$banner.width();   
        var page=1; 
        var timer=null;
        var btnClass=null;
        var page_count=6;//把这个值赋给小圆点的个数
        
     
        $banner_ul.width(page_count*v_width);

        function move(obj,classname){

        if(!$banner_ul.is(':animated')){
                if(classname=='prevBtn'){
                        if(page==1){
                            $banner_ul.animate({left:-v_width*(page_count-1)});
                            page=page_count; 
                         
            }else{
                $banner_ul.animate({left:'+='+v_width},"slow");
                         page--;
                        }        

                }else{
                    if(page==page_count){
                            $banner_ul.animate({left:0});
                            page=1;
                }else{
                     $banner_ul.animate({left:'-='+v_width},"slow");
                      page++;
                    }
                        }
                }
        }
        $banner.mouseover(function(){
                clearInterval(timer);
        }).mouseout(function(){               
                clearInterval(timer);
                timer=setInterval(move,3000);

            }).trigger("mouseout");
        $btn_a.click(function(){

                        btnClass=this.className;

                        clearInterval(timer);

                        timer=setInterval(move,3000);

                        move($(this),this.className);

                });

}); */
</script>
</html>
