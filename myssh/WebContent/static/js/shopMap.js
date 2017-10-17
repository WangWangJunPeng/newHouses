$(document).ready(function(){
	getjingweidu();
	shopMap.getProjectListForMap();
	
	//项目筛选点击事件
	$('#changeBtn').click(function(){
		
		var display =$('#shanBox').css('display');
		if(display == "none"){
			$('#shanBox').show();
		}else{
			$('#shanBox').hide();
		}
		$('#okBtn').click(function(){
			
			$('#shanBox').hide();
			shopMap.getClickTags();
			//console.log(pro);
			//console.log(ppTags);
			shopMap.getProjectInfoLatitudes(ppTags, hhTags, "", projectStart, projectLimit);
		});
	});
	
	$("#cityChoice").click(function(e){
		e.stopPropagation();
		getProvS();
		
		
	})
		$("#mapList").click(function(e){
			//e.stopPropagation()
			$(".proviceCity").hide();
		});
		$("#allmap").click(function(e){
			//e.stopPropagation()
			$(".proviceCity").hide();
		});
	
	
	


	$("#projectLists").scroll(function(){
		var hh = $(this).height();
		var tt = $(this).scrollTop();
		var sh = $(this)[0].scrollHeight
		//console.log(sh)
		//console.log(tt)
		//console.log(hh)
		
		var totalCounts = $('#shop_num').html();
		var thisCount = $('.lieshop').children();
		
		if(totalCounts > 10 && thisCount.length >= 10 && thisCount.length < totalCounts){
			if(hh + tt >= sh){
				//projectStart = projectLimit;
				projectLimit+=10;
				scroll =false;
				shopMap.getProjectInfoLatitudes(ppTags, hhTags, "", projectStart, projectLimit);
			}
		}
	})
	
	
});
//获取动态经纬度
function getjingweidu(){
	$.ajax({
		type : "post",
		async:false,
		url : "to_getJingweiduByIp",
		dataType:"json",
		
		success:function(data){
			//console.log(data)
			var jingdu = data[0];
			var weidu = data[1];
			//console.log(jingdu)
			//console.log(weidu)
			all(jingdu,weidu);
		}
	})
}
var map;
var point;
var limit = 10;
var bs;
var bssw ;
var bsne;
var maxLongitudes; 
var maxLatitudes;
var minLongitudes;
var minLatitudes;
var myCity;
function all(jingdu,weidu){
	createMap(jingdu,weidu);
	shopMap.init();
	setMapEvent();
}
function createMap(jingdu,weidu){
	map = new BMap.Map("allmap",{enableMapClick:false});
	 point = new BMap.Point(jingdu,weidu);
	 myCity = new BMap.LocalCity();
	 myCity.get(myFun);
	 map.centerAndZoom(point,12);// 建树点坐标,初始化地图,设置中心点坐标和地图级别。	
	 map.enableScrollWheelZoom(true);
	 bs = map.getBounds();
	 bssw = bs.getSouthWest();
	 bsne = bs.getNorthEast();
	  maxLongitudes = bsne.lng; 
	  maxLatitudes = bsne.lat;
	  minLongitudes = bssw.lng;
	  minLatitudes = bssw.lat;
}

function setMapEvent(){
	/*map.addEventListener("dragend", function(){ 
		console.log("拖拽")
		projectStart = 0;
		projectLimit = 10;
		bs = map.getBounds();
		bssw = bs.getSouthWest();
		bsne = bs.getNorthEast();
		maxLongitudes = bsne.lng; 
		maxLatitudes = bsne.lat;
		minLongitudes = bssw.lng;
		minLatitudes = bssw.lat;
		
		shopMap.getProjectInfoLatitudes(ppTags, hhTags, "", projectStart, projectLimit);
	});*/
	//图块加载完成事件
	map.addEventListener("tilesloaded", function(){ 
		//console.log("图块加载")
		projectStart = 0;
		projectLimit = 10;
		bs = map.getBounds();
		bssw = bs.getSouthWest();
		bsne = bs.getNorthEast();
		maxLongitudes = bsne.lng; 
		maxLatitudes = bsne.lat;
		minLongitudes = bssw.lng;
		minLatitudes = bssw.lat;
		//alert("页面加载完成")
		shopMap.getProjectInfoLatitudes(ppTags, hhTags, "", projectStart, projectLimit);
	});
	
	/*map.addEventListener("zoomend",function(){
		console.log("中心点变更")
		projectStart = 0;
		projectLimit = 10;
		 bs = map.getBounds();
		 bssw = bs.getSouthWest();
		 bsne = bs.getNorthEast();
		 maxLongitudes = bsne.lng; 
		 maxLatitudes = bsne.lat;
		 minLongitudes = bssw.lng;
		 minLatitudes = bssw.lat;
		 shopMap.getProjectInfoLatitudes(ppTags, hhTags, "", projectStart, projectLimit);
	});*/

}
function myFun(result){
	var cityName = result.name;
	map.setCenter(cityName);	
}



function chenshi(city){
	if(city != ""){
		//console.log($("#cityChoice").val())
		//alert(11);
		//console.log(city);
		map.centerAndZoom(city,12);      // 用城市名设置地图中心点
	}
}

function addMarker(point,shid,projectName,proInSystemStutas){
	if(proInSystemStutas == 1){
		var htm = "<div style='background:#00b39c;color:#ffffff;width:64px;height:64px;border-radius:32px;line-height:64px;text-align:center;font-size:12px;' class='dd'>"
	        +projectName+ "</div>";
	}else{
		var htm = "<div style='background:#e94736;color:#ffffff;width:64px;height:64px;border-radius:32px;line-height:64px;text-align:center;font-size:12px;' class='dd'>"
	        +projectName+ "</div>";
	}
	/*var htm = "<div style='background:#00b39c;color:#ffffff;width:64px;height:64px;border-radius:32px;line-height:64px;text-align:center;font-size:12px;' class='dd'>"
        +projectName+ "</div>";*/
  //var marker = new BMap.Marker(point);
	  var myRichMarkerObject = new BMapLib.RichMarker(htm, point, {"enableDragging": false});

  map.addOverlay(myRichMarkerObject);
  //console.log(shid);
  $(".dd").parent().css("border-radius","32px");
  addClickHandler(myRichMarkerObject,shid);
}
function addClickHandler(myRichMarkerObject,shid){
	myRichMarkerObject.addEventListener("click",function(e){
		$(".proviceCity").hide();
		 shopMap.getProjectInfoLatitudes("", "", shid, projectStart, projectLimit);
	});
}



//房源分页全局变量
var houseStart = 0;
var houseLimit = 100;
var houseCurrent = 0;
var houseTotal = 0;

//项目分页全局变量
var projectStart = 0;
var projectLimit = 10;
var projectCurrent = 0;
var projectTotal = 0;

//选中的标签全局变量
var ppTags = '';
var hhTags = '';


var shopMap = {
	URL : {
		projectInArea : function() {
			return "get_project_in_area";
		},
		projectInfoInLatitudes : function() {
			return "get_project_info";
		},
		projectListInMap : function() {
			return "get_project_list_for_map";
		},
		projectHouseList : function(){
			return "get_house_by_pro_tags";
		},
		houseTags : function(){
			return "get_house_tags";
		},
		allTagList : function(){
			return "get_project_all_standard_tag_info";
		},
		
	},
	
	

	init : function() {
		shopMap.getProjectInfoLatitudes("", "", "", projectStart, projectLimit);
		shopMap.getAllTags();
	},
	
	projectClik : function(obj){
		
		$('.youceThree').hide();
		//console.log(obj);
		$('#p_projectName').html(obj.project.projectName);
		$('#p_projectName2').html(obj.project.projectName);
		$('#p_areaName').html(obj.areaName);
		$('#p_houseCount').html(obj.houseCount+"套");
		if(obj.guide == null || obj.guide == ""){
			$('#p_isFast').html("不支持快速结佣");
			$('#p_daikanMoney').html("暂无");
		}else{
			$('#p_daikanMoney').html(obj.guide.daiKanMoney+"%");
			if(obj.guide.isFast == 1){
				$('#p_isFast').html("快速结佣");
			}else{
				$('#p_isFast').html("不支持快速结佣");
			}
		}
		var pic = '';
		if(obj.proInfo.picList.length > 0){
			for(var i=0; i<obj.proInfo.picList.length; i++){
				
				var pic1 = obj.proInfo.picList[i];
				var pic2 = pic1.split(",");
				for(var x = 0; x < pic2.length; x++){
					//console.log(pic2[x]);
					var pic3 = pic2[x].replace("[","");
					var pic4 = pic3.replace("]","");
					pic += '<li><a href="#"><img src="'+pic4+'"></a></li>';
				}
				//console.log(pic2)
				/*var pArr = obj.proInfo.picList[i].split(",").replace("[","").repalce("]","");
				console.log(pArr)*/
				/*for(var x = 0; x<pArr.length; x++){
					
					var ppp = pArr[x];
					pic = '<li><a href="#"><img src="'+ppp+'"></a></li>';
				}*/
			}
		}else{//添加暂无图片的默认图片
			pic = '<li><a href="#"><img src="images/timg.jpg"></a></li>';
		}
		$('#p_img').html("");
		$('#p_img').append(pic);
		$('#p_address').html(obj.project.saleAddress);
		if(obj.project.landArea == null || obj.project.landArea == ""){
			$('#p_landArea').html('未填写');
		}else{
			$('#p_landArea').html(obj.project.landArea+'㎡');
		}
		if(obj.project.groundArea == null || obj.project.groundArea == ""){
			$('#p_groundArea').html('未填写');
		}else{
			$('#p_groundArea').html(obj.project.groundArea+'㎡');
		}
		if(obj.project.underGroundArea == null || obj.project.underGroundArea == ""){
			$('#p_underGroundArea').html('未填写');
		}else{
			$('#p_underGroundArea').html(obj.project.underGroundArea+'㎡');
		}
		if(obj.project.unitCount == null || obj.project.unitCount == ""){
			$('#p_unitCount').html("未填写");
		}else{
			$('#p_unitCount').html(obj.project.unitCount);
		}
		if(obj.project.density == null || obj.project.density == ""){
			$('#p_density').html('未填写');
		}else{
			$('#p_density').html((obj.project.density)+'%');
		}
		if(obj.project.afforestationRatio == null || obj.project.afforestationRatio == null){
			$('#p_afforestationRatio').html("未填写");
		}else{
			$('#p_afforestationRatio').html((obj.project.afforestationRatio)+'%');
		}
		if(obj.project.propertyCost == null || obj.project.propertyCost == ""){
			$('#p_propertyCost').html('未填写');
		}else{
			$('#p_propertyCost').html(obj.project.propertyCost+'元/㎡');
		}
			 
		if(obj.project.startTime == null || obj.project.startTime == ""){
			$('#p_startTime').html("未填写");
		}else{
			$('#p_startTime').html(obj.project.startTime.substring(0,10));
		}
		if(obj.project.deliverTime == null || obj.project.deliverTime == ""){
			$('#p_deliverTime').html("未填写");
		}else{
			$('#p_deliverTime').html(obj.project.deliverTime.substring(0,10));
		}
		if(obj.project.developer == null || obj.project.developer == ""){
			$('#p_developer').html("未填写");
		}else{
			$('#p_developer').html(obj.project.developer);
		}
		if(obj.project.manager == null || obj.project.manager == ""){
			$('#p_manager').html("未填写");
		}else{
			$('#p_manager').html(obj.project.manager);
		}
		$("#maskBox").show();
		$('.youce').show();
		$('.youceTwo').hide();
	},
	
	houseClick : function(projectId){
		//当点击可售房源的时候是没有进行房源标签的筛选的
		shopMap.getProjectHouseList(projectId, "", houseStart, houseLimit);
	},
	
	houseInfoClick : function(obj){
		//console.log(obj);
		$('#h_houseTags').children().remove();
		var houseTags = '';
		var houseNum = obj.house.houseNum;
		shopMap.fillHouseDataInfo(obj);
		$.ajax({
			type : "POST",
			url : shopMap.URL.houseTags(),
			async : false,
			data : {
				houseNum : houseNum
			},
			success : function(data){
				$.each(data, function(v, o){
					houseTags += '<span style="background:#c5a9dd;">'+o.tagName+'</span>';
				});
			}
		});
		$('#h_houseTags').append(houseTags);
		$('.youceThree').show();
	},
	
	fillHouseDataInfo : function(obj){
		$('#h_houseName').html(obj.house.district+"区"+obj.house.buildingNo+"栋"+obj.house.unit+"单元"+obj.house.houseNo+"室");
		$('#h_listPrice').html((obj.house.listPrice*0.0001).toFixed(2) + "万");
		$('#h_benefits').html(obj.benefits+"%");
		if(obj.house.houseKind == 0){
			$('#h_houseKind').html("公寓");
		}else if(obj.house.houseKind == 1){
			$('#h_houseKind').html("排屋");
		}else if(obj.house.houseKind == 2){
			$('#h_houseKind').html("独栋");
		}else if(obj.house.houseKind == 3){
			$('#h_houseKind').html("商住两用");
		}else if(obj.house.houseKind == 4){
			$('#h_houseKind').html("办公室");
		}else if(obj.house.houseKind == 5){
			$('#h_houseKind').html("酒店式公寓");
		}else if(obj.house.houseKind == 6){
			$('#h_houseKind').html("商铺");
		}else if(obj.house.houseKind == 7){
			$('#h_houseKind').html("车位");
		}else if(obj.house.houseKind == 8){
			$('#h_houseKind').html("车库");
		}else if(obj.house.houseKind == 9){
			$('#h_houseKind').html("储藏室");
		}
		$('#h_daikanMoney').html(obj.daiKanMoney+"%");
		$('#h_buildArea').html(obj.house.buildArea+"m²");
		$('#h_unitPrice').html((obj.house.listPrice/obj.house.buildArea).toFixed(2)+"/平");
		$('#h_direct').html(obj.house.direct);
		if(obj.house.decorationStandard == 0){
			$('#h_decorationStandard').html("毛坯");
		}else if(obj.house.decorationStandard == 1){
			$('#h_decorationStandard').html("普通装修");
		}else if(obj.house.decorationStandard == 2){
			$('#h_decorationStandard').html("精装修");
		}else if(obj.house.decorationStandard == 3){
			$('#h_decorationStandard').html("家具全配");
		}else if(obj.house.decorationStandard == 4){
			$('#h_decorationStandard').html("家电全配");
		}
		$('#h_houseType').html(obj.houseType.houseType);
		$('#h_img').attr("src",obj.houseType.photoURL);
		$('#h_houseAddress').html(obj.projectInfo.projectAddress);
		$('#h_landArea').html(obj.projectInfo.landArea+"m²");
		$('#h_groundArea').html(obj.projectInfo.groundArea+"m²");
		$('#h_underGroundArea').html(obj.projectInfo.underGroundArea+"m²");
		$('#h_unitCount').html(obj.projectInfo.unitCount);
		$('#h_density').html((obj.projectInfo.density)+"%");
		$('#h_afforestationRatio').html((obj.projectInfo.afforestationRatio)+"%");
		$('#h_propertyCost').html(obj.projectInfo.propertyCost+"元/m²");
		$('#h_startTime').html(obj.projectInfo.startTime.substring(0,10));
		$('#h_deliverTime').html(obj.projectInfo.deliverTime.substring(0,10));
		$('#h_developer').html(obj.projectInfo.developer);
		$('#h_manager').html(obj.projectInfo.manager);
		
	},
	//通过房源id查找房源的tags
	getHouseTagByHouseNum : function(houseNum){
		$.ajax({
			type : "POST",
			url : shopMap.URL.houseTags(),
			async : true,
			data : {
				houseNum : houseNum
			},
			success : function(data){
				var tags = [];
				$.each(data, function(v, o){
					tags.push(o.tagName);
					
				});
				//console.log(tags)
				houseTags = tags;
			}
		});
	},
	
	getProjectHouseList : function(projectId, houseTags, start, limit){
		$.ajax({
			type : "POST",
			url : shopMap.URL.projectHouseList(),
			async : false,
			data : {
				projectId : projectId,
				houseTags : houseTags,
				start : start,
				limit : limit
			},
			success : function(data){
				console.log(data);
				shopMap.fillProjectHouseInfo(data.root);
			}
		});
	},
	
	fillProjectHouseInfo : function(data){
		var s = '';
		$.each(data, function(v,o){
			
			var dd = JSON.stringify(o).replace(/"/g, '&quot;');
			s += '<div class="everyHouse" onClick="shopMap.houseInfoClick('+dd+')" >';
			s += '<div class="listLeft" style="margin-top:30px;">';
			s += '<img src="'+o.houseType.photoURL+'" alt="" />';
			s += '</div>';
			s += '<div class="listLeft" style="margin-top:30px;">';
			s += '<p style="font-size:16px;color:#47506c;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;width:100px;" title="'+o.house.district+'区'+o.house.buildingNo+'栋'+o.house.unit+'单元">'+o.house.district+'区'+o.house.buildingNo+'栋'+o.house.unit+'单元</p>';
			s += '<span style="font-size:16px;color:#47506c;">'+o.house.houseNo+'室</span><span style="font-size:18px;color:#ff6c39;margin-left:65px;">'+(o.house.listPrice*0.0001).toFixed(2)+'万</span>';
			s += '<div style="margin-top:6px;">';
			s += '<span style="font-size:12px;color:#616b88;">'+o.house.buildArea+'㎡</span>';
			s += '<span style="font-size:12px;color:#616b88;margin-left:6px;">'+o.houseType.housType+'</span>';
			s += '<span style="font-size:12px;color:#616b88;margin-left:6px;">'+(o.house.listPrice/o.house.buildArea).toFixed(2)+'/平</span>';
			s += '</div>';
			s += '<div class="zj">';
			if(o.proInSystemStutas == 1){
				s += '<span style="width:56px;border:1px solid #ff6161;color:#ff6161;">平台认证</span>';
			}else{
				s += '<span style="width:56px;border:1px solid #ff6161;color:#ff6161;">暂未认证</span>';
			}
			if(o.house.decorationStandard == 0){
				s += '<span style="width:56px;border:1px solid #ab90e5;color:#ab90e5;margin-left:6px;">毛坯</span>';
			}else if(o.house.decorationStandard == 1){
				s += '<span style="width:56px;border:1px solid #ab90e5;color:#ab90e5;margin-left:6px;">普通装修</span>';
			}else if(o.house.decorationStandard == 2){
				s += '<span style="width:56px;border:1px solid #ab90e5;color:#ab90e5;margin-left:6px;">精装修</span>';
			}else if(o.house.decorationStandard == 3){
				s += '<span style="width:56px;border:1px solid #ab90e5;color:#ab90e5;margin-left:6px;">家具全配</span>';
			}else if(o.house.decorationStandard == 4){
				s += '<span style="width:56px;border:1px solid #ab90e5;color:#ab90e5;margin-left:6px;">家电全配</span>';
			}
			s += '</div>';
			s += '</div>';
			s += '</div>';
			
		});
		
		if(data.length > 0){
			$('.youceThree').hide();
			$('.fangList').children().remove();
			$('.fangList').append(s);
		}else{
			$('.youceThree').hide();
			$('.fangList').children().remove();
			$('.fangList').append('<span>暂无数据<span>');
		}
		
		$('.youceTwo').show();
		$("#maskBox").show();
		$('.youce').hide();
	},
	
	 getProjectInArea : function(city) {
		$.ajax({
			type : "POST",
			url : shopMap.URL.projectInArea(),
			async : true,
			data : {
				city : city
			},
			success : function(data) {
				//console.log(data);
			}
		});
	},

	getProjectInfoLatitudes : function(tags, houseTags, projectId, start,
			limit) {
		//console.log(maxLongitudes)
		$.ajax({
			type : "POST",
			url : shopMap.URL.projectInfoInLatitudes(),
			async : true,
			data : {
				maxLongitudes : maxLongitudes,
				minLongitudes : minLongitudes,
				maxLatitudes : maxLatitudes,
				minLatitudes : minLatitudes,
				pTags : tags,
				hTags : houseTags,
				projectId : projectId,
				start : start,
				limit : limit,
			},
			success : function(data){
				//console.log(data);
				shopMap.fillProjectInfoLatitudes(data.root, data.wheres);
			}
		});
	},
	
	fillProjectInfoLatitudes : function(data, obj){
		//$('#shop_num').html(obj);
		var s = "";
		s += '<div class="topBack">';
		s += '<span class="foundList">为您找到<span id="shop_num">'+obj+'</span><span>个项目</span></span>';
		s += '</div>';
		s += '<div class="lieshop">';
		$.each(data, function(v,o){
			var pp = "";
			if(o.proInfo.picList.length>0){
				var pic = o.proInfo.picList[0].split(",");
				pp = pic[0].replace("[","").replace("]","");
			}
			var dat = JSON.stringify(o).replace(/"/g, '&quot;');
			
			s += '<div class="houseList">';
			s += '<input type="hidden" value="' + o.projectId + '"/>';
			s += '<div class="listLeft">';
			s += ' <img src="' + pp + '" alt="" />';
			s += '</div>';
			s += '<div class="listRight">';
			s += '<div  class="proName">';
			s += '<span style="color:#47506c;font-size: 16px;cursor:pointer;display:inline-block;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;width:160px;height:20px;" onClick="shopMap.projectClik('+dat+')" class="xiangmu" title="'+o.project.projectName+'">' + o.project.projectName + '</span>';
			s += '</div>';
			s += ' <div class="zige">';
			if(o.project.proInSystemStutas == 1){
				s += '<span style="width:56px;border:1px solid #ff6161;color:#ff6161;">平台认证</span>';
			}
			s += '<span style="width:96px;border:1px solid #ab90e5;color:#ab90e5;">均价' + (o.project.averagePrice*1) + '元/平</span>';
			s += '</div>';
			s += '<div class="yongjin">';
			s += '<div class="xiao">';
			if(o.guide == null || o.guide == ""){
				s += '<p style="color:#fd6c38;font-size: 12px;">暂无</p>';
			}else{
				s += '<p style="color:#fd6c38;font-size: 12px;">' + o.guide.daiKanMoney + '%</p>';
			}
			s += '<p style="color:#616b88;font-size: 12px;">平均佣金</p>';
			s += '</div>';
			s += '<div class="xiao" style="margin-left:30px;">';
			if(o.guide == null || o.guide == ""){
				s += '<p style="color:#fd6c38;font-size: 12px;">不支持快速</p>';
			}else{
				if(o.guide.isFast == 1){
					s += '<p style="color:#fd6c38;font-size: 12px;">支持快速</p>';
				}else{
					s += '<p style="color:#fd6c38;font-size: 12px;">不支持快速</p>';
				}
			}
			s += '<p style="color:#616b88;font-size: 12px;">结佣速度</p>';
			s += '</div>';
			s += '</div>';
			s += '<div class="houseSale">';
			var proId = "'"+o.projectId+"'";//不转成字符串就会报错
			s += '<div class="xiao shu" onclick="shopMap.houseClick('+proId+')">';
			s += '<p style="color:#46c1de;font-size: 12px;" >' + o.houseCount + '套</p>';
			s += '<p style="color:#46c1de;font-size: 12px;" >可售房源</p>';
			s += '</div>';
			s += '<div class="xiao" style="margin-left:40px;">';
			s += '<p style="color:#46c1de;font-size: 12px;" >' + o.buyHouseCount + '套</p>';
			s += '<p style="color:#46c1de;font-size: 12px;">已售房源</p>';
			s += '</div>'; 
			s += '</div>';
			s += '</div>';
			s += '</div>';
		});
		s += '</div>';
	
		if(data.length > 0){
			$('#projectLists').html("");
			$('#projectLists').append(s);
		}else{
			$('#projectLists').children().remove();
		}
	},
	
	//筛选条件 - 项目标签&房源标签
	getAllTags : function(){
		$.ajax({
			type : "POST",
			url : shopMap.URL.allTagList(),
			async : true,
			success : function(data){
				//console.log(data);
				for(var i = 0; i < data.length; i++){
					if(data[i].tagTypeName == "项目标签"){
						//遍历项目标签
						shopMap.fillProjectTags(data[i]);
					}else if(data[i].tagTypeName == "房源标签"){
						shopMap.fillHouseTags(data[i]);
					}
				}
			}
		});
	},
	
	fillProjectTags : function(data){
		var s = '<p style="font-size: 16px; color: #83889a; margin-left: 14px;">'+data.tagTypeName+'</p>';
		s += '<div class="proLableList">';
		s += '<div style="margin-left: 20px; margin-top: 20px;">';
		var i = 0;
		$.each(data.tagLibs, function(v, o){
			s += '<p style="font-size: 14px; color: #83889a; margin-bottom: 20px;">'+o.tagTypeName+'</p>';
			$.each(o.tags, function(c,t){
				if(o.isMultiple == 0){//单选
						s += '<input type="checkbox" id="p'+i+'" name="prot" value="'+t.originalTagId+'" style="display: none;" />';
						s += '<label for="p'+i+'" class="mo" title="'+t.tagName+'">'+t.tagName+'</label>';
				}else if(o.isMultiple == 1){//多选
					//alert(t.children.length);
						s += '<input type="checkbox" id="p'+i+'" name="prot" value="'+t.originalTagId+'" style="display: none;" />';
						s += '<label for="p'+i+'" class="mo" title="'+t.tagName+'">'+t.tagName+'</label>';
				}
				i++;
			});
		});
		
		s += '</div>';
		s += '</div>';
		$('#proTags').append(s);
	},
	
	fillHouseTags : function(data){
		var s = '<p style="font-size: 16px; color: #83889a; margin-left: 14px;">'+data.tagTypeName+'</p>';
		s += '<div class="proLableList">';
		s += '<div style="margin-left: 20px; margin-top: 20px;">';
		var i = 0;
		$.each(data.tagLibs, function(v, o){
			s += '<p style="font-size: 14px; color: #83889a; margin-bottom: 20px;">'+o.tagTypeName+'</p>';
			$.each(o.tags, function(c,t){
				if(o.isMultiple == 0){//单选
					if( t.children.length > 0){
						$.each(t.children, function(x,y){
							s += '<input type="checkbox" id="h'+i+'" name="hout" value="'+y.originalTagId+'" style="display: none;" />';
							s += '<label for="h'+i+'" class="mo" title="'+y.tagName+'">'+y.tagName+'</label>';
							i++;
						});
					}else{
						s += '<input type="checkbox" id="h'+i+'" name="hout" value="'+t.originalTagId+'" style="display: none;" />';
						s += '<label for="h'+i+'" class="mo" title="'+t.tagName+'">'+t.tagName+'</label>';
					}
				}else if(o.isMultiple == 1){//多选
					if( t.children.length > 0){
						$.each(t.children, function(x, y){
							s += '<input type="checkbox" id="h'+i+'" name="hout" value="'+y.originalTagId+'" style="display: none;" />';
							s += '<label for="h'+i+'" class="mo" title="'+y.tagName+'">'+y.tagName+'</label>';
							i++;
						});
					}else{
						s += '<input type="checkbox" id="h'+i+'" name="hout" value="'+t.originalTagId+'" style="display: none;" />';
						s += '<label for="h'+i+'" class="mo" title="'+t.tagName+'">'+t.tagName+'</label>';
					}
				}
				i++;
			});
		});
		
		s += '</div>';
		s += '</div>';
		
		$('#hTags').append(s);
	},
	
	getClickTags : function(){
		var pro;
		var house;
		var idsstr = '';
		var hIdsstr = '';
		$("#proTags input[name='prot']:checked").each(function(){ //遍历table里的全部checkbox
	        idsstr += $(this).val() + ","; //获取所有checkbox的值
	    });
		if(idsstr.length > 0){
			idsstr = idsstr.substring(0, idsstr.length - 1); //把最后一个逗号去掉
		}
		ppTags = idsstr;
		$("#hTags input[name='hout']:checked").each(function(){
			hIdsstr += $(this).val() + ",";
		});
		if(hIdsstr.length > 0){
			hIdsstr = hIdsstr.substring(0, hIdsstr.length - 1); //把最后一个逗号去掉
		}
		hhTags = hIdsstr;
	},
	
	getProjectListForMap : function(){
		$.ajax({
			type : "POST",
			url : shopMap.URL.projectListInMap(),
			async : true,
			success : function(data){
				//console.log(data)
				for (var i = 0; i < data.length; i++) {
					var shid = data[i].projectId;
					var proInSystemStutas = data[i].proInSystemStutas;
					var projectName = data[i].projectName;
				    var point = new BMap.Point(data[i].saleLongitude,data[i].saleLatitude);
				    addMarker(point,shid,projectName,proInSystemStutas);
				}
			}
		});
	}
	

}