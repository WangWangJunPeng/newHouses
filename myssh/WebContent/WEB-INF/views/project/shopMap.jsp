<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="icon" href="static/images/titleLogo.png" />
<title>门店管理后台</title>
<link rel="stylesheet" type="text/css" href="static/css/shopMap.css" />

<style type="text/css">
* {
	padding: 0;
	margin: 0;
}
</style>
</head>
<body>
	<div id="maskBox"></div>
	<div id="allmap" style="height: 850px;"></div>
	 <div class="proviceCity" style="display:none;width:360px;height:200px;background:#ffffff;position:absolute;top:60px;right:20px;;z-index:1100;border:1px solid #999;border-radius:6px;">
        
      </div>
	<div id="shanBox"
		style="width: 410px; height: 560px; background: #ffffff; position: absolute; top: 60px; right: 0; z-index: 1100;display:none;">
		<div class="sureBtn">
			<button id="okBtn">确认</button>
		</div>
		<div class="biaoQianList" style="height: 520px;overflow:auto;">
			<div id="proTags">
				<!-- <p style="font-size: 16px; color: #83889a; margin-left: 14px;">项目</p>
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
				</div> -->
			</div>
		
		<div style="margin-top: 30px;" id="hTags">
			<!-- <p style="font-size: 14px; color: #83889a; margin-left: 14px;">房源</p>
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
			</div> -->
		</div>
		</div>
	</div>


	<div id="mapList"
		style="width: 410px; background: #e4e8ef; position: absolute; top: 0px; right: 0; z-index: 1000;">
		<input type="text" id="cityChoice" placeholder="请选择城市" class="cityChoose"  > 
		<input type="hidden" id="city" value="">
		<button class="changeBtn"  id="changeBtn">筛选</button>

		<div class="shopList"
			style="background: #f0f4fb; height: 800px; overflow: auto; margin-top: 10px;" id="projectLists">
			<!-- <div class="topBack">
				<span class="foundList">为您找到<span id="shop_num"></span><span>个项目</span></span>
			</div>
			<div class="lieshop" id="listShop">
				

			</div>
			<div style="height: 50px;"></div> -->
		</div>

	</div>
	
	<div class="youce"
		style="width: 756px; height: 850px; position: absolute; top: 0; z-index: 900; right: 410px; display: none;">

		<div id="proDetails">
			<div class="top">
				<span
					style="display: inline-block; width: 4px; height: 14px; background: #ff6161;"></span>
				<span style="font-size: 16px; color: #565e78;" id="p_projectName"></span>
			</div>
			<div class="second">
				<span style="font-size: 14px; color: #565e78;" id="p_projectName2"></span>
				<span style="font-size: 14px; color: #565e78; margin-left: 20px;"
					id="p_areaName"></span>
			</div>
			<div class="third">
				<span>可售房源：<span id="p_houseCount"></span></span> <span
					style="margin-left: 30px;">佣金比例: <span id="p_daikanMoney"></span></span>

				<span style="margin-left: 30px;">结佣速度：<span id="p_isFast"></span></span>
			</div>
			<div class="banner">

				<ul class="banner-img" id="p_img">
					
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
							style="margin-left: 7px;">地：</span><span style="color: #ff5c5b;"
							id="p_address">滨江江南之心</span>
					</p>
					<p>
						用地面积：<span style="color: #7a8196;" id="p_landArea"></span>
					</p>
					<p>
						地上面积：<span style="color: #7a8196;" id="p_groundArea"></span>
					</p>
					<p>
						地下面积：<span style="color: #7a8196;" id="p_underGroundArea"></span>
					</p>
				</div>
				<div class="single">
					<p>
						总<span style="margin-left: 7px;">户</span><span
							style="margin-left: 7px;">数：</span><span style="color: #7a8196;"
							id="p_unitCount"></span>
					</p>
					<p>
						密<span style="margin-left: 28px;">度：</span><span
							style="color: #7a8196;" id="p_density"></span>
					</p>
					<p>
						绿<span style="margin-left: 7px;">化</span><span
							style="margin-left: 7px;">率：</span><span style="color: #7a8196;"
							id="p_afforestationRatio"></span>
					</p>
					<p>
						物<span style="margin-left: 7px;">业</span><span
							style="margin-left: 7px;">费：</span><span style="color: #7a8196;"
							id="p_propertyCost"></span>
					</p>
				</div>
				<div class="single">
					<p>
						开工时间：<span style="color: #7a8196;" id="p_startTime"></span>
					</p>
					<p>
						交付时间：<span style="color: #7a8196;" id="p_deliverTime"></span>
					</p>
					<p>
						开<span style="margin-left: 7px;">发</span><span
							style="margin-left: 7px;">商：</span><span style="color: #7a8196;"
							id="p_developer"></span>
					</p>
					<p>
						物业管理：<span style="color: #7a8196;" id="p_manager"></span>
					</p>
				</div>
			</div>
			<div class="sixth">
				<span
					style="display: inline-block; width: 4px; height: 14px; background: #ff6161;"></span>
				<span style="font-size: 16px; color: #565e78;">房屋简介</span>
			</div>
			<div class="seventh">
				<p>暂无</p>
			</div>




		</div>
		<div id="hide">></div>

	</div>
	<div class="youceTwo"
		style="width: 446px; height: 850px; position: absolute; top: 0; z-index: 900; right: 410px; display: none;">
		<div id="fangBox"
			style="width: 410px; background: #e4e8ef; position: absolute; top: 0px; z-index: 1000; right: 0;">
			<!-- <button class="changeBtn" style="margin-left: 20px;">筛选</button> -->
			<div class="fangList"
				style="background: #f0f4fb; height: 850px; overflow: auto; ">

				<!-- <div class="everyHouse cha" >
		                       <div class="listLeft" style="margin-top:30px;">
		                           <img src="images/timg.jpg" alt="" />
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
		                      
		                </div> 
		                <div class="everyHouse" >
		                       <div class="listLeft" style="margin-top:30px;">
		                           <img src="images/timg.jpg" alt="" />
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
		                      
		                </div> 
		                <div class="everyHouse" >
		                       <div class="listLeft" style="margin-top:30px;">
		                           <img src="images/timg.jpg" alt="" />
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
		                      
		                </div> 
		                <div class="everyHouse" >
		                       <div class="listLeft" style="margin-top:30px;">
		                           <img src="images/timg.jpg" alt="" />
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
		                      
		                </div> 
		                <div class="everyHouse" >
		                       <div class="listLeft" style="margin-top:30px;">
		                           <img src="images/timg.jpg" alt="" />
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
		                      
		                </div> 
		                <div class="everyHouse" >
		                       <div class="listLeft" style="margin-top:30px;">
		                           <img src="images/timg.jpg" alt="" />
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
		                      
		                </div> 
		                <div class="everyHouse" >
		                       <div class="listLeft" style="margin-top:30px;">
		                           <img src="images/timg.jpg" alt="" />
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
		                      
		                </div>  -->

			</div>
		</div>
		<div id="hideTwo">></div>
	</div>

	<div class="youceThree"
		style="width: 756px; height: 850px; position: absolute; top: 0; z-index: 900; right: 820px; display: none;">

		<div id="houseDetails">
			<div class="top" style="margin-top: 30px">
				<span
					style="display: inline-block; width: 4px; height: 14px; background: #ff6161;"></span>
				<span style="font-size: 16px; color: #616b88; font-weight: bold;"
					id="h_houseName"></span> <span
					style="font-size: 24px; color: #ff5c5b; margin-left: 20px; font-weight: bold;"
					id="h_listPrice"></span> <span
					style="font-size: 14px; color: #616b88; margin-left: 10px;"
					id="h_benefits">优惠：<span></span></span> <span
					style="font-size: 14px; color: #616b88; margin-left: 10px;">最低成交价</span>
			</div>

			<div class="third">
				<span style="font-weight: bold;">房源类型：<span id="h_houseKind"></span></span>



				<span style="margin-left: 30px;">佣金比例<span id="h_daikanMoney"></span></span>

				<span style="margin-left: 30px;" id="h_buildArea"></span>
				<!-- <span style="margin-left:30px;" id="h_decorationStandard">毛坯</span> -->
			</div>
			<div class="third">
				<span id="h_unitPrice"></span> <span style="margin-left: 30px;">朝向：<span
					id="h_direct"></span></span> <span style="margin-left: 30px;"
					id="h_decorationStandard"></span> <span style="margin-left: 30px;"
					id="h_houseType"></span>
			</div>
			<div class="biaoQian" id="h_houseTags">
				<!--  <span style="background:#c5a9dd;">厨卫不对门</span>
                    <span style="background:#ecc896;">户型方正</span>
                    <span style="background:#e0a0a0;">主卧带卫</span>
                    <span style="background:#96cf95;">餐客分离</span> -->
			</div>
			<div class="banner">

				<ul class="banner-img" id="h_housePic">
					<li><a href="#"><img src="" id="h_img"></a></li>
					<!-- <li><a href="#"><img src="images/timg.jpg"></a></li>
                        <li><a href="#"><img src="images/timg.jpg"></a></li>
                        <li><a href="#"><img src="images/timg.jpg"></a></li>
                        <li><a href="#"><img src="images/timg.jpg"></a></li>
                        <li><a href="#"><img src="images/timg.jpg"></a></li> -->
				</ul>
			</div>
			<div class="banner-btn">
				<a href="javascript:;" class="prevBtn" style="top: 340px;"> < </a> <a
					href="javascript:;" class="nextBtn" style="top: 340px;"> > </a>
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
							style="margin-left: 7px;">地：</span><span style="color: #ff5c5b;"
							id="h_houseAddress"></span>
					</p>
					<p>
						用地面积：<span style="color: #7a8196;" id="h_landArea"></span>
					</p>
					<p>
						地上面积：<span style="color: #7a8196;" id="h_groundArea"></span>
					</p>
					<p>
						地下面积：<span style="color: #7a8196;" id="h_underGroundArea"></span>
					</p>
				</div>
				<div class="single">
					<p>
						总<span style="margin-left: 7px;">户</span><span
							style="margin-left: 7px;">数：</span><span style="color: #7a8196;"
							id="h_unitCount"></span>
					</p>
					<p>
						密<span style="margin-left: 28px;">度：</span><span
							style="color: #7a8196;" id="h_density"></span>
					</p>
					<p>
						绿<span style="margin-left: 7px;">化</span><span
							style="margin-left: 7px;">率：</span><span style="color: #7a8196;"
							id="h_afforestationRatio"></span>
					</p>
					<p>
						物<span style="margin-left: 7px;">业</span><span
							style="margin-left: 7px;">费：</span><span style="color: #7a8196;"
							id="h_propertyCost"></span>
					</p>
				</div>
				<div class="single">
					<p>
						开工时间：<span style="color: #7a8196;" id="h_startTime"></span>
					</p>
					<p>
						交互时间：<span style="color: #7a8196;" id="h_deliverTime"></span>
					</p>
					<p>
						开<span style="margin-left: 7px;">发</span><span
							style="margin-left: 7px;">商：</span><span style="color: #7a8196;"
							id="h_developer"></span>
					</p>
					<p>
						物业管理：<span style="color: #7a8196;" id="h_manager"></span>
					</p>
				</div>
			</div>
			<div class="sixth">
				<span
					style="display: inline-block; width: 4px; height: 14px; background: #ff6161;"></span>
				<span style="font-size: 16px; color: #565e78;">房屋简介</span>
			</div>
			<div class="seventh">
				<p>暂无</p>
			</div>
		</div>
		<div id="hideThree">></div>
	</div>



</body>
<script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>


<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=dxCgW5uXbilxuIKHY7eYpujkgdsqSj3I"></script>
 <script type="text/javascript" src="http://api.map.baidu.com/library/RichMarker/1.2/src/RichMarker_min.js"></script>
	
<script type="text/javascript" src="static/js/cityData.js"></script>
<script type="text/javascript" src="static/js/cityPicker.js"></script>
<script type="text/javascript" src="static/js/shopMap.js"></script>


<script>
function getProvS(){
	$(".proviceCity").show();
	getAllProv();
}
function getAllProv(){
	$.ajax({
		type : "post",
		url : "get_all_prov",
		async:false,
		//data : {selectStatus : $("#selectStyleId").val()},
		success : function(data) {
			data = eval("(" + data + ")");
			//console.log(data);
			fileProviceIntoDiv(data.root);
			//formWin();
		}
	});
};
function fileProviceIntoDiv(data){
	//console.log(data);
	var p = '';
	$.each(data,function(v,o){
		//console.log(o);
		/* s += '<a href="javascript:;" onclick="getCurrentProvOfShi('+o.cityId+')">'+o.cityName+'</a>&nbsp;'; */
		p += '<span style="display:inline-block;font-size:10px;width:48px;height:20px;line-height:20px;background:#f1f1f1;color:#83889a;text-align:center; border-radius:4px;margin-left:8px;margin-top:8px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;" onclick="getCurrentProvOfShi('+o.cityId+')">'+o.cityName+'</span>'
	});
	if(data.length>0){
		$(".proviceCity").html(p);
	}
}

function getCurrentProvOfShi(id){
	//alert(id);
	$.ajax({
		type : "post",
		url : "menu_list_city_area",
		async:false,
		data : {shengOrShi : id},
		success : function(data) {
			data = eval("(" + data + ")");
			//console.log(data);
			fileShiIntoDiv(data.root);
			//formWin();
		}
	});
}
function fileShiIntoDiv(data){
	var s = '';
	$.each(data,function(v,o){
		//console.log(o);
		
		
		s += '<span data-value='+o.cityId+' onclick=fileNameIntoInput(this) style="text-overflow:ellipsis;white-space:nowrap;overflow:hidden;display:inline-block;font-size:10px;width:48px;height:20px;line-height:20px;background:#f1f1f1;color:#83889a;text-align:center; border-radius:4px;margin-left:8px;margin-top:8px;">'+o.cityName+'</span>'
	});
	if(data.length>0){
		$(".proviceCity").html(s);
	}
}
//省市区
function fileNameIntoInput(obj){
	var shiId = $(obj).data("value");
	$("#cityChoice").val($(obj).html());
	$(".proviceCity").hide();
	//shopMap.getProjectInArea(shiId);
	//alert(111);
	chenshi($(obj).html());
}
/* function hideCity(){
	$(".proviceCity").hide();
} */
</script>
<script type="text/javascript">
var $banner=$('.banner');
var $banner_ul=$('.banner-img');
var $btn=$('.banner-btn');
var $btn_a=$btn.find('a')
var v_width=$banner.width();   
var page=1; 
var timer=null;
var btnClass=null;
var page_count=6;
$(document).ready(function(){
        //把这个值赋给小圆点的个数
        
     
        $banner_ul.width(page_count*v_width);

       
       /*  $banner.mouseover(function(){
                clearInterval(timer);
        }).mouseout(function(){               
                clearInterval(timer);
                timer=setInterval(move,3000);

            }).trigger("mouseout"); */
        $btn_a.click(function(){

                        btnClass=this.className;

                        //clearInterval(timer);

                        //timer=setInterval(move,3000);

                        move($(this),this.className);

                });

});
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
</script>
<script type="text/javascript">
    $(".xiangmu").click(function(event) {
        // $(this).animate({ 
        //    right:"-1130px"
        //   },1000);
        // $(".youce").animate({ 
        //    right:"410px"
        //   },1000);
        $(".youce").show()
    });
    $("#hide").click(function(event) {
        // $(this).animate({ 
        //    right:"-1130px"
        //   },1000);
        // $(".youce").animate({ 
        //    right:"-410px"
        //   },1000);
           $(".youce").hide();
           $("#maskBox").hide();
    });
    $(".clis").click(function(event) {
    	$(".youceTwo").show()
    })
    
    
    
     $("#hideTwo").click(function(event) {
        
           $(".youceTwo").hide();
           $("#maskBox").hide();
    });
    $(".cha").click(function(event) {
    	$(".youceThree").show()
    })
	$("#hideThree").click(function(event) {
    	$(".youceThree").hide()
    })
</script>
</html>