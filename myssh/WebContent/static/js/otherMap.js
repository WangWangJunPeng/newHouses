$(document).ready(function(){
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
        
//        $banner.mouseover(function(){
//                clearInterval(timer);
//        }).mouseout(function(){               
//                clearInterval(timer);
//                timer=setInterval(move,3000);
//
//            }).trigger("mouseout");
        $btn_a.click(function(){
                        btnClass=this.className;
//                        clearInterval(timer);
//                        timer=setInterval(move,3000);
                        move($(this),this.className);

                });
        $(".changeBtn").click(function(){
        	$(this).css({"background":"#46c1de","color":"#ffffff"}).siblings(".changeBtn").css({"background":"#ffffff","color":"#565e78"})
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
        	$("#hide").click(function(){
        		$("#maskBox").hide();
        	})
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
//分页参数设置
var startAllAppoint = 0;
var limitAllAppoint = 15;
var currentPage = 1;
var totalPage = 1;
//房源分页全局变量
var houseStart = 0;
var houseLimit = 10;
var houseCurrent = 0;
var houseTotal = 0;
$(document).ready(function(){
	getjingweidu();
	ajaxGetTags();
	turnPage();
});
//翻页
function turnPage(){
	$(".listZong").scroll(function(){
		var hh = $(this).height();		//可见高度
		var tt = $(this).scrollTop();	//滚动高度
		var sh = $(this)[0].scrollHeight//内容高度
		if(hh + tt >= sh){
			if(currentPage<totalPage){
				limitAllAppoint += 10;
				var clickValue = $("#clickValue").val();
				if(clickValue==0){
					getProjectOrShopsList(0);
				}
			}
		}
	})
	//门店
	$(".listZongTwo").scroll(function(){
		var hh = $(this).height();		//可见高度
		var tt = $(this).scrollTop();	//滚动高度
		var sh = $(this)[0].scrollHeight//内容高度
		if(hh + tt >= sh){
			if(currentPage<totalPage){
				limitAllAppoint += 10;
				var clickValue = $("#clickValue").val();
				if(clickValue==1){
					getProjectOrShopsList(1);
				}
			}
			limitAllAppoint += 10;
			var clickValue = $("#clickValue").val();
			if(clickValue==0){
				getProjectOrShopsList(0);
			}
		}
	})
}
//门店
function turnShopPage(){
	$(".listZongTwo").scroll(function(){
		var hh = $(this).height();		//可见高度
		var tt = $(this).scrollTop();	//滚动高度
		var sh = $(this)[0].scrollHeight//内容高度
		if(hh + tt >= sh){
			limitAllAppoint += 10;
			var clickValue = $("#clickValue").val();
			console.log(clickValue);
			if(clickValue==1){
				getProjectOrShopsList(1);
			}
		}
	})
}
//动态获取经纬度
function getjingweidu(){
	$.ajax({
		type : "post",
		async:false,
		url : "to_getJingweiduByIp",
		dataType:"json",
		success:function(data){
			var jingdu = data[0];
			var weidu = data[1];
			all(jingdu,weidu);
		}
	})
}
//地图
var map;
var point;
var bs;
var bssw ;
var bsne;
var addressOne;
var addressTwo;
var maxLongitudes; 
var maxLatitudes;
var minLongitudes;
var minLatitudes;
var myCity;
function all(jingdu,weidu){
	createMap(jingdu,weidu);
	getProjectOrShopsList(0);
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
	 addressOne = bssw.lng +','+bssw.lat;
	 addressTwo = bsne.lng +','+bsne.lat;
}
function setMapEvent(){
	/*map.addEventListener("zoomend",function(){//放大缩小
		bs = map.getBounds();
		bssw = bs.getSouthWest();
		bsne = bs.getNorthEast();
		maxLongitudes = bsne.lng; 
		maxLatitudes = bsne.lat;
		minLongitudes = bssw.lng;
		minLatitudes = bssw.lat;
		addressOne = bssw.lng +','+bssw.lat;
		addressTwo = bsne.lng +','+bsne.lat;	
		//判断加载案场还是加载门店 0：案场；1门店
		var clickValue = $("#clickValue").val();
		if(clickValue==0){
			getProjectOrShopsList(0);
		}else{
			getProjectOrShopsList(1);
		}
	});*/
	map.addEventListener("tilesloaded", function(){    
		bs = map.getBounds();
		bssw = bs.getSouthWest();
		bsne = bs.getNorthEast();
		maxLongitudes = bsne.lng; 
		maxLatitudes = bsne.lat;
		minLongitudes = bssw.lng;
		minLatitudes = bssw.lat;
		addressOne = bssw.lng +','+bssw.lat;
		addressTwo = bsne.lng +','+bsne.lat;	
		//alert("页面加载完成")
		var clickValue = $("#clickValue").val();
		if(clickValue==0){
			getProjectOrShopsList(0);
		}else{
			getProjectOrShopsList(1);
		}
	});
	/*map.addEventListener("dragend", function(){//拖动   	  
		bs = map.getBounds();
		bssw = bs.getSouthWest();
		bsne = bs.getNorthEast();
		addressOne = bssw.lng +','+bssw.lat;
		addressTwo = bsne.lng +','+bsne.lat;
		maxLongitudes = bsne.lng; 
		maxLatitudes = bsne.lat;
		minLongitudes = bssw.lng;
		minLatitudes = bssw.lat;
		//判断加载案场还是加载门店 0：案场；1门店
		var clickValue = $("#clickValue").val();
		if(clickValue==0){
			getProjectOrShopsList(0);
		}else{
			getProjectOrShopsList(1);
		}
	});*/
}
function myFun(result){
	var cityName = result.name;
	map.setCenter(cityName);	
}
function chenshi(city){
	if(city != ""){
		//console.log($("#cityChoice").val())
		map.centerAndZoom(city,12);// 用城市名设置地图中心点
	}
}
function addMarker(point,Id,name,stutas,hh){
	if(stutas == 1){
		if(hh == 0){
			var htm = "<div style='background:#00b39c;color:#ffffff;width:64px;height:64px;border-radius:32px;line-height:64px;text-align:center;font-size:12px;' class='dd'>"
		        +name+ "</div>";
		}else if(hh == 1){
			var htm = "<div style='background:#00b39c;color:#ffffff;width:80px;height:22px;line-height:22px;border-radius:4px;text-align:center;font-size:10px;' class='dd'>"
		        +name + "</div>";
		}
		
	}else{
		if(hh == 0){
			var htm = "<div style='background:#e94736;color:#ffffff;width:64px;height:64px;border-radius:32px;line-height:64px;text-align:center;font-size:12px;' class='dd'>"
		        +name+ "</div>";
		}else if(hh == 1){
			var htm = "<div style='background:#e94736;color:#ffffff;width:80px;height:22px;line-height:22px;border-radius:4px;text-align:center;font-size:10px;' class='dd'>"
		        +   name
		       
		        + "</div>";
		}
		
	}
	
  var myRichMarkerObject = new BMapLib.RichMarker(htm, point, {"enableDragging": false});
  map.addOverlay(myRichMarkerObject);
  $(".dd").parent().css("border-radius","32px");
 addClickHandler(myRichMarkerObject,Id,point);
}
function addClickHandler(myRichMarkerObject,projectId,point){
	myRichMarkerObject.addEventListener("click",function(e){
		$(".proviceCity").hide();
		var clickValue = $("#clickValue").val();
		if(clickValue==0){
			$(".listZong").off();
			getOneProjectByMap(projectId);
		}else{
			$(".listZongTwo").off();
			getOneShopByMap(projectId);
		}
	});
}

//获取指定案场
function getOneProjectByMap(projectId){
	$.ajax({
		type : "post",
		async : false,
		url : "get_project_by_property",
		data : {
			projectId:projectId ,start:startAllAppoint,limit:limitAllAppoint
		},
		success : function(data) {
			setProjectOrShopsData(data);
			setProMap(data);
		}
	});
}

//获取指定门店
function getOneShopByMap(projectId){
	$.ajax({
		type : "post",
		//async : false,
		url : "get_shop_data",
		data : {
			shopId:projectId
		},
		success : function(data) {
			setShopsData(data);
			setShopMap(data);
		}
	});
}

//门店1;	案场0
function getProOrShopData(data){
	map.clearOverlays();
	limitAllAppoint = 15;
	if(data!=1){
		$(".listZongTwo").css("display","none");
		$(".listZong").css("display","block");
	}else{
		$(".listZong").css("display","none");
		$(".listZongTwo").css("display","block");
	}
	$("#clickValue").val(data);//标记点击时间
	getProjectOrShopsList(data);
}
function getProjectOrShopsList(data){
	hidePro();
	hideHouse();
	hideHouseInfo();
	hidShopDetails();
	var url = "get_project_by_property";
	if(data!=1){
		$("#pro").css({"background":"#46c1de","color":"#ffffff"});
		$("#sho").css({"background":"#ffffff","color":"#565e78"});
		url = "get_project_by_property";
	}else{
		$("#sho").css({"background":"#46c1de","color":"#ffffff"});
		$("#pro").css({"background":"#ffffff","color":"#565e78"});
		url = "get_shops_list_data";
	}
	ajaxGetData(url,data);
}

function ajaxGetData(url,d){
	var tags = getProTags();
	var hTags = getHouTags();
	var proId = null;
	$.ajax({
		type : "post",
		//async : false,
		url : url,
		data : {
			// 门店参数,sign移动地图时值为2，否则值为1
			addressOne:addressOne,addressTwo:addressTwo,quId:$("#city").val(),sign:$("#signValue").val(),
			// 案场参数
			maxLongitudes:maxLongitudes,minLongitudes: minLongitudes,maxLatitudes:maxLatitudes ,
			minLatitudes:minLatitudes,tags:tags,houseTags:hTags ,projectId:proId ,
			//翻页通用参数
			start:startAllAppoint,limit:limitAllAppoint
		},
		//traditional: true,
		beforeSend: function(){  
			//$("body").append('<div id="waiting"></div>');
			$("#waiting").show();
			
		},  
		success : function(data) {
			if(d!=1){
				setProjectOrShopsData(data);
				setProMap(data);
			}else{
				setShopsData(data.root);
				setShopMap(data.root);
				turnShopPage();
			}
			
		},
		 complete: function () {  
		        $("#waiting").hide();  
		    },
	});
}

//案场 地图
var hh = 0;
function setProMap(data){
	//console.log(data)
	hh = 0;
	for (var i = 0; i < data.root.length; i++) {
		var projectId = data.root[i].project.projectId;
		var projectName = data.root[i].project.projectName; 
		var proInSystemStutas = data.root[i].project.proInSystemStutas;
		//console.log(data.root[i].project.propertyLongitude)
	    var point = new BMap.Point(data.root[i].project.saleLongitude,data.root[i].project.saleLatitude);
	    addMarker(point,projectId,projectName,proInSystemStutas,hh);
	}
}

//门店 地图
function setShopMap(data){
	hh = 1;
	for (var i = 0; i < data.length; i++) {
		var shopId = data[i].shopId;
		var shopName = data[i].shopName; 
		var inSystemStutas = data[i].inSystemStutas;
		//console.log(data.root[i].project.propertyLongitude)
	    var point = new BMap.Point(data[i].longitude,data[i].latitude);
	    addMarker(point,shopId,shopName,inSystemStutas,hh);
	}
}

function setProjectOrShopsData(data){
	totalPage = data.totalPage;//获取总页数
	$("#proCount").text(data.total);
	var s = "";
	$.each(data.root,function(v,o){
		var jpro = JSON.stringify(o).replace(/"/g, '&quot;');
		s += '<div class="everyOne">';
		if(o.proInfo.picList.length>0){
			var picUrl = o.proInfo.picList[0].substring(o.proInfo.picList[0].indexOf("[")+1,o.proInfo.picList[0].indexOf("]"));
			s += '<div class="listLeft"><img src="'+picUrl+'" alt="" /></div>';
		}else{
			s += '<div class="listLeft"><img src="static/images/noPic.png"/></div>';
		}
		s += '<div class="listRight">';
		s += '<div class="proName"><span style="color: #47506c; font-size: 16px; cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;width:160px;display:inline-block;"class="xiangmu" onclick="getProData('+jpro+')" >'+o.project.projectName+'</span></div>';
		if(o.project.proInSystemStutas==1){
			s += '<div class="zige"><span style="width: 56px; border: 1px solid #ff6161; color: #ff6161;">平台认证</span>';
		}else{
			s += '<div class="zige"><span style="width: 56px; border: 1px solid #ff6161; color: #ff6161;">未认证</span>';
		}
		
		if(o.project.averagePrice!=null){
			s+= '<span style="width: 96px; border: 1px solid #ab90e5; color: #ab90e5;margin-left:6px;">均价'+o.project.averagePrice+'元/平</span></div>';
		}else{
			s+='<span style="width: 96px; border: 1px solid #ab90e5; color: #ab90e5;margin-left:6px;">均价:未填写</span></div>';
		}
		if(o.guide!=null && o.guide!=""){
			s += '<div class="yongjin"><div class="xiao"><p style="color: #fd6c38; font-size: 12px;">'+o.guide.daiKanMoney+'%</p><p style="color: #616b88; font-size: 12px;">平均佣金</p></div>';
		}else{
			s += '<div class="yongjin"><div class="xiao"><p style="color: #fd6c38; font-size: 12px;">暂无</p><p style="color: #616b88; font-size: 12px;">平均佣金</p></div>';
		}
		if(o.guide != null && o.guide != "" && o.guide.isFast == 1){
			s += '<div class="xiao" style="margin-left: 30px;"><p style="color: #fd6c38; font-size: 12px;">支持快速</p><p style="color: #616b88; font-size: 12px;">结佣速度</p></div>';
		}else{
			s += '<div class="xiao" style="margin-left: 30px;"><p style="color: #fd6c38; font-size: 12px;">不支持快速</p><p style="color: #616b88; font-size: 12px;">结佣速度</p></div>';
		}
		s += '<div class="houseSale"><div class="xiao shu clis" onclick=houseList("'+o.projectId+'")><p style="color: #46c1de; font-size: 12px;">'+o.houseCount+'套</p><p style="color: #46c1de; font-size: 12px;">可售房源</p></div>';
		s += '<div class="xiao" style="margin-left: 40px;"><p style="color: #46c1de; font-size: 12px;">'+o.buyHouseCount+'套</p><p style="color: #46c1de; font-size: 12px;">已售房源</p></div></div></div></div></div>';
	});
	$("#proData").html("");
	if(data.root.length>0){
		//console.log(limitAllAppoint);
		$("#proData").html(s);
	}else{
		$("#proData").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
	}
}

function getProData(pro){
	
	hideHouse();
	hideHouseInfo();
	$(".youce").show();
	$("#maskBox").show();
	$("#grlProName1").text(pro.project.projectName);
	$("#grlProName2").text(pro.project.projectName);
	$("#grlPreaName").text(pro.areaName);
	$("#grlHouseCount").text("共"+pro.houseCount+"套/剩余"+pro.buyHouseCount+"套");
	if(pro.guide == null || pro.guide == ""){
		$('#grlIsFast').text("不支持快速结佣");
		$('#grlDaikanMoney').text(":暂无");
	}else{
		$('#grlDaikanMoney').text(pro.guide.daiKanMoney+"%");
		if(pro.guide.isFast == 1){
			$('#grlIsFast').text("快速结佣");
		}else{
			$('#grlIsFast').text("不支持快速结佣");
		}
	}
	var pic = '';
	//console.log(pro.proInfo.picList);
	if(pro.proInfo.picList.length > 0){
		for(var i=0; i<pro.proInfo.picList.length; i++){
			var p = pro.proInfo.picList[i].substring(pro.proInfo.picList[i].indexOf("[")+1,pro.proInfo.picList[i].indexOf("]"));
			pic += '<li><a href="#"><img src="'+p+'"></a></li>';
		}
		if(pro.proInfo.picList.length<6){
			for(var i=pro.proInfo.picList.length;i<6;i++){
				pic += '<li><a href="#"><img src="static/images/noPic.png"></a></li>';
			}
		}
	}else{//添加暂无图片的默认图片
		for(var i=0;i<6;i++){
			pic = '<li><a href="#"><img src="static/images/noPic.png"></a></li>';
		}
	}
	$('#grlPicImg').find("li").remove();
	$('#grlPicImg').append(pic);
	if(pro.project.saleAddress!=null && pro.project.saleAddress!=""){
		$('#grlAddress').text(pro.project.saleAddress);
	}else{
		$('#grlAddress').text("未填写");
	}
	
	if(pro.project.landArea == null || pro.project.landArea == ""){
		$('#grlLandArea').text('未填写');
	}else{
		$('#grlLandArea').text(pro.project.landArea+'㎡');
	}
	if(pro.project.groundArea == null || pro.project.groundArea == ""){
		$('#grlGroudArea').text('未填写');
	}else{
		$('#grlGroudArea').text(pro.project.groundArea+'㎡');
	}
	if(pro.project.underGroundArea == null || pro.project.underGroundArea == ""){
		$('#grlUnderGroudArea').text('未填写');
	}else{
		$('#grlUnderGroudArea').text(pro.project.underGroundArea+'㎡');
	}
	if(pro.project.unitCount == null || pro.project.unitCount == ""){
		$('#grlTotalHuShu').text("未填写");
	}else{
		$('#grlTotalHuShu').text(pro.project.unitCount);
	}
	if(pro.project.density == null || pro.project.density == ""){
		$('#grlMiDu').text('未填写');
	}else{
		$('#grlMiDu').text((pro.project.density)+'%');
	}
	if(pro.project.afforestationRatio == null || pro.project.afforestationRatio == null){
		$('#grlLVHuRate').text("未填写");
	}else{
		$('#grlLVHuRate').text((pro.project.afforestationRatio)+'%');
	}
	if(pro.project.propertyCost == null || pro.project.propertyCost == ""){
		$('#grlWuYeFei').text('未填写');
	}else{
		$('#grlWuYeFei').text(pro.project.propertyCost+'元/㎡');
	}
		 
	if(pro.project.startTime == null || pro.project.startTime == ""){
		$('#grlStartTime').text("未填写");
	}else{
		$('#grlStartTime').text(pro.project.startTime.substring(0,10));
	}
	if(pro.project.deliverTime == null || pro.project.deliverTime == ""){
		$('#grlJiaoHuTime').text("未填写");
	}else{
		$('#grlJiaoHuTime').text(pro.project.deliverTime.substring(0,10));
	}
	if(pro.project.developer == null || pro.project.developer == ""){
		$('#grlKaifashang').text("未填写");
	}else{
		$('#grlKaifashang').text(pro.project.developer);
	}
	if(pro.project.manager == null || pro.project.manager == ""){
		$('#grlWuyeguanli').text("未填写");
	}else{
		$('#grlWuyeguanli').text(pro.project.manager);
	}
}

function houseList(proId){
	
	$("#selectProjectId").val(proId);
	$.ajax({
		type : "post",
		//async : false,
		url : "get_current_project_of_house_data",
		data : {
			projectId:proId,houseTags:null,status:0,start:houseStart,limit:houseLimit
		},
		success : function(data) {
			fileHouseList(data);
			hidePro();
		}
	});
}
function fileHouseList(data){
	//console.log(data);
	var s = "";
	$("#maskBox").show();
	$(".youceTwo").show();
	$.each(data.root,function(v,o){
		var dd = JSON.stringify(o).replace(/"/g, '&quot;');
		if(o.houseType!=null && o.houseType.photoURL!=null && o.houseType.photoURL!=""){
			s += '<div class="everyHouse" onclick="getHouseData('+dd+')"><div class="listLeft" style="margin-top:30px;"><img src="'+o.houseType.photoURL+'" alt="" /></div>';
		}else{
			s += '<div class="everyHouse" onclick="getHouseData('+dd+')"><div class="listLeft" style="margin-top:30px;"><img src="static/images/noPic.png" alt="" /></div>';
		}
		s += '<div class="listLeft" style="margin-top:30px;"><p style="font-size:16px;color:#47506c;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;width:160px;">'+o.house.district+'区'+o.house.buildingNo+'栋'+o.house.unit+'单元</p>';
		s += '<span style="font-size:16px;color:#47506c;">'+o.house.houseNo+'室</span><span style="font-size:18px;color:#ff6c39;margin-left:65px;">'+(o.house.listPrice*0.0001).toFixed(2)+'万</span>';
	    s += '<div style="margin-top:6px;">';
	    s += '<span style="font-size:12px;color:#616b88;">'+o.house.buildArea+'㎡</span>';
	    if(o.houseType!=null){
	    	 s += '<span style="font-size:12px;color:#616b88;margin-left:8px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;width:60px;display:inline-block;">'+o.houseType.housType+'</span>';
	    }else{
	    	 s += '<span style="font-size:12px;color:#616b88;margin-left:8px;">暂无</span>';
	    }
	    s += '<span style="font-size:12px;color:#616b88;margin-left:8px;">'+(o.house.listPrice/o.house.buildArea).toFixed(2)+'/平</span></div>';
	    if(o.proInSystemStutas == 1){
	    	s += '<div class="zj"><span style="width:56px;border:1px solid #ff6161;color:#ff6161;">平台认证</span>';
		}else{
			s += '<div class="zj"><span style="width:56px;border:1px solid #ff6161;color:#ff6161;">平台认证</span>';
		}
		if(o.house.decorationStandard == 0){
			//s += '<span style="width:56px;border:1px solid #ab90e5;color:#ab90e5;">精装修</span></div></div></div>';
			s += '<span style="width:56px;border:1px solid #ab90e5;color:#ab90e5;margin-left:6px;">毛坯</span></div></div></div>';
		}else if(o.house.decorationStandard == 1){
			s += '<span style="width:56px;border:1px solid #ab90e5;color:#ab90e5;margin-left:6px;">普通装修</span></div></div></div>';
		}else if(o.house.decorationStandard == 2){
			s += '<span style="width:56px;border:1px solid #ab90e5;color:#ab90e5;margin-left:6px;">精装修</span></div></div></div>';
		}else if(o.house.decorationStandard == 3){
			s += '<span style="width:56px;border:1px solid #ab90e5;color:#ab90e5;margin-left:6px;">家具全配</span></div></div></div>';
		}else if(o.house.decorationStandard == 4){
			s += '<span style="width:56px;border:1px solid #ab90e5;color:#ab90e5;margin-left:6px;">家电全配</span></div></div></div>';
		}else{
			s += '<span style="width:56px;border:1px solid #ab90e5;color:#ab90e5;margin-left:6px;">暂无</span></div></div></div>';
		}
	});
	if(data.root.length>0){
		$(".fangList").html(s);
	}else{
		$(".fangList").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
	}
}
function getHouseData(obj){
	//console.log(obj);
	$('#h_houseTags').children().remove();
	var houseTags = '';
	var houseNum = obj.house.houseNum;
	fillHouseDataInfo(obj);
	$.ajax({
		type : "POST",
		url : 'get_house_tags',
		//async : false,
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
}
function fillHouseDataInfo(obj){
	//console.log(obj);
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
	if(obj.houseType!=null){
		$('#h_houseType').html(obj.houseType.houseType);
		$('#h_img').attr("src",obj.houseType.photoURL);
	}else{
		$('#h_houseType').html("暂无");
		$('#h_img').attr("src","static/images/noPic.png");
	}
	
	$('#h_houseAddress').html(obj.projectInfo.projectAddress);
	$('#h_landArea').html(obj.projectInfo.landArea+"m²");
	$('#h_groundArea').html(obj.projectInfo.groundArea+"m²");
	$('#h_underGroundArea').html(obj.projectInfo.underGroundArea+"m²");
	$('#h_unitCount').html(obj.projectInfo.unitCount);
	$('#h_density').html((obj.projectInfo.density)+"%");
	$('#h_afforestationRatio').html((obj.projectInfo.afforestationRatio)+"%");
	$('#h_propertyCost').html(obj.projectInfo.propertyCost+"元/m²");
	if(obj.projectInfo.startTime!=null && obj.projectInfo.startTime!=""){
		$('#h_startTime').html(obj.projectInfo.startTime.substring(0,10));
	}else{
		$('#h_startTime').html("未填写");
	}
	if(obj.projectInfo.deliverTime!=null && obj.projectInfo.deliverTime!=""){
		$('#h_deliverTime').html(obj.projectInfo.deliverTime.substring(0,10));
	}else{
		$('#h_deliverTime').html("未填写");
	}
	if(obj.projectInfo.developer!=null && obj.projectInfo.developer!=""){
		$('#h_developer').html(obj.projectInfo.developer);
	}else{
		$('#h_developer').html("未填写");
	}
	if(obj.projectInfo.manager!=null && obj.projectInfo.manager!="" ){
		$('#h_manager').html(obj.projectInfo.manager);
	}else{
		$('#h_manager').html("未填写");
	}
}
function getSignHouse(sign){
	var num = $(".changeBtn").index();
	$(".changeBtn").eq(num).css({"background":"#46c1de","color":"#ffffff"}).siblings(".changeBtn").css({"background":"#c7cbd0","color":"#565e78"})
	$.ajax({
		type : "post",
		//async : false,
		url : "get_current_project_of_house_data",
		data : {
			projectId:$("#selectProjectId").val(),houseTags:null,status:sign,start:houseStart,limit:houseLimit
		},
		success : function(data) {
			fileHouseList(data);
		}
	});
}

function setShopsData(data){
	//console.log(data);
	getShopsListNum();
	var s = '';
	$.each(data,function(v,o){
		s += '<div class="everyList mendian" onclick=getShopById('+o.shopId+')>';
		if(o.photo!=null && o.photo!=""){
			s += '<div class="leftImg"><img src="'+o.photo+'" alt="" /></div>';
		}else{
			s += '<div class="leftImg"><img src="static/images/noPic.png" alt="" /></div>';
		}
		s += '<div class="rightWord">';
		s += '<p style="font-size: 16px;color:#616b88;margin-top:10px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;width:160px;display:inline-block;">'+o.shopName+'</p>';
		s += '<p style="font-size: 12px;color:#616b88;margin-top:10px;">已备案：<span>'+o.guideRecordsNum+'</span></p>';
		s += '<p style="font-size: 12px;color:#616b88;margin-top:10px;">已成交：<span>'+o.haveDealNum+'</span></p>';
		s += '<p style="font-size: 12px;color:#616b88;margin-top:10px;">经济人数：<span>6'+o.agentNum+'</span></p>';
		if(o.inSystemStutas!=1){
			s += '<div class="surePLat">未认证</div>';
		}else{
			s += '<div class="surePLat">平台认证</div>';
		}
		s += '</div></div>';
	});
	if(data.length>0){
		$("#shopsData").html(s);
	}else{
		$("#shopsData").html("<br/><span style='width:10%;height:30px;display:block;margin:0 auto;'>暂无数据</span>");
	}
}
//门店总数
function getShopsListNum() {
	$.ajax({
		type : "post",
		url : "to_goToGetAllShopsList",
		dataType : "json",
		//async : false,
		data : {
			addressOne : addressOne,
			addressTwo : addressTwo,
			sign : '',
			quId : '',
		},
		success : function(data) {
			// allShopList(data);
			$("#shopCounn").text(data.shopsList);
		}
	})
}

//获取单个门店的详情
function getShopById(id){
	$.ajax({
		type : "post",
		url : "get_shop_data",
		dataType : "json",
		//async : false,
		data : {
			shopId:id
		},
		success : function(data) {
			// allShopList(data);
			setCurrentShopInfo(data);
		}
	})
}

function setCurrentShopInfo(data){
	//console.log(data);
	$("#maskBox").show();
	$(".youceFourth").show();
	var s = '<div class="shopDetails"><div class="topShop"><span style="display:inline-block;width:4px;height:14px;background:#ff6161;"></span>';
	$.each(data,function(v,o){
		s += '<span style="font-size: 16px;color:#565e78;margin-left:5px;" >'+o.companyName+'</span></div>';
		s += '<div class="secondShop"><span style="font-size: 16px;color:#565e78;">'+o.shopName+'</span></div>';
		s += '<div class="firstShop"><span>已售出房源：<span>'+o.haveDealNum+'</span></span>';
		s += '<span style="margin-left:30px;">已备案人数：<span>'+o.guideRecordsNum+'</span></span>';
		s += '<span style="margin-left:30px;">门店经纪人数：<span>'+o.agentNum+'</span></span></div>';
		if(o.photo!=null && o.photo!=""){
			s += '<div class="fourthImg"><img src="'+o.photo+'"></div>';
		}else{
			s += '<div class="fourthImg"><img src="static/images/noPic.png"></div>';
		}
		s += '<div class="fourth"><span style="display:inline-block;width:4px;height:14px;background:#ff6161;"></span><span style="font-size: 16px;color:#565e78;margin-left:5px;">门店信息</span></div>';
		s += '<div class="fifth"><div class="single">';
		s += '<p>名<span style="margin-left:28px;">称：</span><span style="color:#ff5c5b;">'+o.shopName+'</span></p>';
		s += '<p>服务等级：<span style="color:#7a8196;">1</span></p>';
		s += '<p>服务年限：<span style="color:#7a8196;">340000.00m2</span></p>';
		s += '<p>地<span style="margin-left:28px;">址 ：</span><span style="color:#7a8196;">'+o.address+'</span></p></div></div></div>';
		//s += '<div class="sixth"><span style="display:inline-block;width:4px;height:14px;background:#ff6161;"></span></div>';
		//s += '<span style="font-size: 16px;color:#565e78;">房屋简介</span></div>';
		//s += '<div class="seventh"><p>林居房产金选房源 林居房产您身边的房产专家，“以诚待人，用心*事”！临安房产中介行业的佼佼者！ 我们始终以</p></div>';
		s += '<div id="hideFourth" onclick="hidShopDetails()">></div>';
		
	});
	if(data.length>0){
		$(".youceFourth").html(s);
	}
}

//隐藏门店详情
function hidShopDetails(){
	$("#maskBox").hide();
	$(".youceFourth").hide();
}

//城市
function getProvS(){
	var dis = $(".proviceCity").css("display");
	if (dis == "none") {
		$('#shanBox').hide();
		$(".proviceCity").show();
		getAllProv();
	}else{
		$('.proviceCity').hide();
	}
}

	function getAllProv(){
		$.ajax({
			type : "post",
			url : "get_all_prov",
			//async:false,
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
		var p = '';
		$.each(data,function(v,o){
			p += '<span style="cursor:pointer !important;display:inline-block;font-size:10px;width:48px;height:20px;line-height:20px;background:#f1f1f1;color:#83889a;text-align:center; border-radius:4px;margin-left:8px;margin-top:8px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;" onclick="getCurrentProvOfShi('+o.cityId+')">'+o.cityName+'</span>'
		});
		if(data.length>0){
			$(".proviceCity").html(p);
		}
	}
//	function hideCity(){
//		$(".proviceCity").hide();
//	}
	function getCurrentProvOfShi(id){
		//alert(id);
		$.ajax({
			type : "post",
			url : "menu_list_city_area",
			//async:false,
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
			s += '<span data-value='+o.cityId+' onclick=fileNameIntoInput(this) style="cursor:pointer !important;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;display:inline-block;font-size:10px;width:48px;height:20px;line-height:20px;background:#f1f1f1;color:#83889a;text-align:center; border-radius:4px;margin-left:8px;margin-top:8px;">'+o.cityName+'</span>'
		});
		if(data.length>0){
			$(".proviceCity").html(s);
		}
	}
	function fileNameIntoInput(obj){
		$("#city").val($(obj).html());
		chenshi($(obj).html())
		var shiId = $(obj).data("value");
		$(".allList").html("");
		$(".shopDetails").remove();
		$("#cityChoice").val($(obj).html());
		$(".proviceCity").hide();
	}
 
 //标签筛选
 function getAllTags(){
	var display = $('#shanBox').css('display');
	if (display == "none") {
		$('.proviceCity').hide();
		$('#shanBox').show();
	} else {
		//$('#proTags').html("");
		//$('#hTags').html("");
		$('#shanBox').hide();
	}
 }

 function ajaxGetTags() {
	$.ajax({
		type : "post",
		url : "get_project_all_standard_tag_info",
		//async : false,
		success : function(data) {
			$.each(data, function(v, o) {
				if (o.tagTypeName == "项目标签") {
					// 遍历项目标签
					fillProjectTags(o);
				} else if (o.tagTypeName == "房源标签") {
					fillHouseTags(o);
				}
			});
		}
	});
}
 
 function fillProjectTags(data){
	 //console.log(data.tagLibs);
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
 }
 function fillHouseTags(data){
	 //console.log(data.tagLibs);
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
 }

function getProTags(){
	var tags = new Array();
	$("#proTags input[name='prot']:checked").each(function(){ //遍历table里的全部checkbox
		tags.push($(this).val());
    });
	return tags;
}

function getHouTags(){
	var hTags = new Array();
	$("#hTags input[name='hout']:checked").each(function(){
		hTags.push($(this).val());
	});
	return hTags;
}

function getProByTags(){
	$('#shanBox').hide();
	var clickValue = $("#clickValue").val();
	if(clickValue==0){
		getProjectOrShopsList(0);
	}else{
		getProjectOrShopsList(1);
	}
}

function hidePro(){
	
	$(".youce").hide();
}

function hideHouse(){
	$("#maskBox").hide();
	$(".youceTwo").hide();
}

function hideHouseInfo(){
	$(".youceThree").hide();
}




