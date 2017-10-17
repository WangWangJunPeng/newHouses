/* 
* @Author: Marte
* @Date:   2017-07-06 16:38:48
* @Last Modified by:   Marte
* @Last Modified time: 2017-07-06 16:44:50
*/

$(document).ready(function(){
    /*getAreaShops();*/
	getjingweidu();
	//setMapEvent();
//	getAllShopsListForMap();
	/*getOneShopList();
	getShopsListNum();
	getAllShopsListForMap();*/
	$(".shopList").scroll(function(){	
		var hh = $(this).height();
		var tt = $(this).scrollTop();
		var sh = $(this)[0].scrollHeight;
		//console.log(hh);
		//console.log(tt)
		//console.log(sh)
		if(hh + tt >= sh){
			limit+=10;
			getOneShopList();
			console.log(121232)
		}
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
		
});


function getjingweidu(){
	$.ajax({
		type : "post",
		async:true,
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

//地图
var map;
var point;
var limit = 10;
var bs;
var bssw;
var bsne;
var addressOne;
var addressTwo;
var myCity;
function all(jingdu,weidu){
	createMap(jingdu,weidu);
//	getShopsListNum();
//	getOneShopList();
	getAllShopsListForMap();
	
	
	
	
}
function createMap(jingdu,weidu){
	map = new BMap.Map("allmap",{enableMapClick:false});
	 point = new BMap.Point(jingdu,weidu);
	 myCity = new BMap.LocalCity();
	 myCity.get(myFun);
	 map.centerAndZoom(point,15);// 建树点坐标,初始化地图,设置中心点坐标和地图级别。	
	 map.enableScrollWheelZoom(true);
	 bs = map.getBounds();
	 bssw = bs.getSouthWest();
	 bsne = bs.getNorthEast();
	 addressOne = bssw.lng +','+bssw.lat;
	 addressTwo = bsne.lng +','+bsne.lat;
	 //setMapEvent();
//	 return map;
//	 map.addEventListener("zoomend",function(){
//			bs = map.getBounds();
//			bssw = bs.getSouthWest();
//			bsne = bs.getNorthEast();
//			addressOne = bssw.lng +','+bssw.lat;
//			addressTwo = bsne.lng +','+bsne.lat;
//			getShopsListNum();
//			getOneShopList();
//		
//			//getAllShopsListForMap();
//			//$("#cityChoice").val("");
//			$(".shopDetails").remove();
//		});
	 
	 map.addEventListener("tilesloaded", function(){    
			projectStart = 0;
			projectLimit = 10;
			bs = map.getBounds();
			bssw = bs.getSouthWest();
			bsne = bs.getNorthEast();
			addressOne = bssw.lng +','+bssw.lat;
			addressTwo = bsne.lng +','+bsne.lat;
			getShopsListNum();
			//alert("页面加载完成")
			getOneShopList();
			//getAllShopsListForMap();
			//getAllShopsListForMap();
			//$("#cityChoice").val("");
			$(".shopDetails").remove();
		});
//	 map.addEventListener("dragend", function(){    	  
//			bs = map.getBounds();
//			bssw = bs.getSouthWest();
//			bsne = bs.getNorthEast();
//			addressOne = bssw.lng +','+bssw.lat;
//			addressTwo = bsne.lng +','+bsne.lat;	
//			getOneShopList();
//			getShopsListNum();  
//			//getAllShopsListForMap();
//			//$("#cityChoice").val("");
//			$(".shopDetails").remove();
//		});
	 
}

//function setMapEvent(){
////	map.addEventListener("zoomend",function(){
////		bs = map.getBounds();
////		bssw = bs.getSouthWest();
////		bsne = bs.getNorthEast();
////		addressOne = bssw.lng +','+bssw.lat;
////		addressTwo = bsne.lng +','+bsne.lat;
////		getShopsListNum();
////		getOneShopList();
////	
////		//getAllShopsListForMap();
////		//$("#cityChoice").val("");
////		$(".shopDetails").remove();
////	});
//	map.addEventListener("tilesloaded", function(){    
//		projectStart = 0;
//		projectLimit = 10;
//		bs = map.getBounds();
//		bssw = bs.getSouthWest();
//		bsne = bs.getNorthEast();
//		addressOne = bssw.lng +','+bssw.lat;
//		addressTwo = bsne.lng +','+bsne.lat;
//		getShopsListNum();
//		//alert("页面加载完成")
//		getOneShopList();
//		
//		//getAllShopsListForMap();
//		//$("#cityChoice").val("");
//		$(".shopDetails").remove();
//	});
//	map.addEventListener("dragend", function(){    	  
//		bs = map.getBounds();
//		bssw = bs.getSouthWest();
//		bsne = bs.getNorthEast();
//		addressOne = bssw.lng +','+bssw.lat;
//		addressTwo = bsne.lng +','+bsne.lat;	
//		getOneShopList();
//		getShopsListNum();  
//		//getAllShopsListForMap();
//		//$("#cityChoice").val("");
//		$(".shopDetails").remove();
//	});
//}







/*var map = new BMap.Map("allmap",{enableMapClick:false});
var point = new BMap.Point(120.21937542,30.25924446);
map.centerAndZoom(point,15);*/
function myFun(result){
	var cityName = result.name;
	map.setCenter(cityName);	
}


function chenshi(city){
	if(city != ""){
		//console.log($("#cityChoice").val())
		map.centerAndZoom(city,11); 
		/*addressOne = "";
		addressTwo = "";
		bs = map.getBounds();
		bssw = bs.getSouthWest();
		bsne = bs.getNorthEast();
		addressOne = bssw.lng +','+bssw.lat;
		addressTwo = bsne.lng +','+bsne.lat;	
		console.log(addressOne)
		getOneShopList();
		getShopsListNum();*/
//		getShopsListNum();
//		getOneShopList();
		// 用城市名设置地图中心点
	}
}
/*if($("#cityChoice").val() != ""){
	console.log($("#cityChoice").val())
	map.centerAndZoom($("#cityChoice").val(),11);      // 用城市名设置地图中心点
}*/
/*var limit = 10;
var bs = map.getBounds();
var bssw = bs.getSouthWest();
var bsne = bs.getNorthEast();
var addressOne = bssw.lng +','+bssw.lat;
var addressTwo = bsne.lng +','+bsne.lat;
//console.log(addressOne)
map.enableScrollWheelZoom(true);*/
function addMarker(point,shid,inSystemStutas,shopName){
	if(inSystemStutas == 1){
		var htm = "<div style='background:#00b39c;color:#ffffff;width:80px;height:22px;line-height:22px;border-radius:4px;text-align:center;font-size:10px;' class='dd'>"
	        +   shopName
	       
	        + "</div>";
	}else if(inSystemStutas == 0){
		var htm = "<div style='background:#e94736;color:#ffffff;width:80px;height:22px;line-height:22px;border-radius:4px;text-align:center;font-size:10px;' class='dd'>"
	        +   shopName
	       
	        + "</div>";
	}
	
  var myRichMarkerObject = new BMapLib.RichMarker(htm, point, {"enableDragging": false});
  map.addOverlay(myRichMarkerObject);
  $(".dd").parent().css("border-radius","4px");
  addClickHandler(myRichMarkerObject,shid,point);
}
function addClickHandler(myRichMarkerObject,shid,point){
	myRichMarkerObject.addEventListener("click",function(e){
		$(".shopList").off();
		$(".shopDetails").remove();
		$(".proviceCity").hide();
		
		getOneShopByMap(shid);
	});
}
function getOneShopByMap(obj){
	$.ajax({
		type : "post",
		url : "to_getOneShops",
		dataType:"json",
		data:{shopId:obj},
		success:function(data){
			everyShop(data);
		}
	})
}
/*map.addEventListener("zoomend",function(){
	bs = map.getBounds();
	bssw = bs.getSouthWest();
	bsne = bs.getNorthEast();
	addressOne = bssw.lng +','+bssw.lat;
	addressTwo = bsne.lng +','+bsne.lat;	
	getOneShopList();
	getShopsListNum();
	//getAllShopsListForMap();
	$("#cityChoice").val("")
});
map.addEventListener("dragend", function(){    	  
	bs = map.getBounds();
	bssw = bs.getSouthWest();
	bsne = bs.getNorthEast();
	addressOne = bssw.lng +','+bssw.lat;
	addressTwo = bsne.lng +','+bsne.lat;	
	getOneShopList();
	getShopsListNum();  
	//getAllShopsListForMap();
	$("#cityChoice").val("")
});*/
function getAllShopsListForMap(){
	$.ajax({			
			type : "post",
			url : "to_getOneShopsListInMap",
			dataType:"json",
			async:true,
			data:{
				addressOne:"",
				addressTwo:"",
				start:'',
				limit:'',
				sign:'',
				quId:'',			
				},
			success:function(data){
				//console.log(data)
				for (var i = 0; i < data.length; i++) {
					var shid = data[i].shopId;
					var shopName = data[i].shopName; 
					var inSystemStutas = data[i].inSystemStutas;
				    var point = new BMap.Point(data[i].longitude,data[i].latitude);
				    //console.log(data)
				    addMarker(point,shid,inSystemStutas,shopName);
				}
			}
		})
	}
/*function getAllShopsList(){
	 
$.ajax({
		
		type : "post",
		url : "to_getOneShopsList",
		dataType:"json",
		data:{
			addressOne:'',
			addressTwo:'',
			start:0,
			limit:50,
			sign:1,
			quId:'',			
			},
		success:function(data){
			getEveryShopInfo(data.root);
			console.log(data.root)
			
		}
	})
}*/


/*function getAreaShops(){
$.ajax({
		
		type : "post",
		url : "to_getWantToKonwShops",
		dataType:"json",
		data:{cityId:$("#city").val()},
		success:function(data){
			everyArea(data);
			
		}
	})
}
function everyArea(data){
	var a = '';
	$.each(data,function(v,o){
		a += '<div class="houseList" onclick="getOneShopList('+o.quyuId+')">';
		 if (o.photo != null && o.photo != ""){
        	a += '<div class="leftImg"><img src="'+o.photo+'" alt="" /></div>';   
        }else {
        	a += '<div class="listLeft"><img src="static/images/noPic.png" alt="" /></div>';
        }
        a += '<div class="listRight">';
        a += '<div style="position:relative;height:30px;"><span style="color:#47506c;font-size: 16px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;width:50px;display:inline-block;position:absolute;top:10px;left:10px;" title="'+o.quyu+'">'+o.quyu+'</span><span style="display:inline-block;width:70px;height:22px;line-height:22px;background:#616b88;border-radius:4px;text-align:center;margin-left:20px;color:#ffffff;font-size: 12px;position:absolute;top:10px;left:60px;">'+o.shopsNum+'个门店</span></div>';
        
        a += '<div class="cghg">';
        a += '<div class="xiao bei"><p style="color:#ffa040;font-size: 12px;">'+o.guideRecordsNum+'</p> <p style="color:#47506c;font-size: 12px;">已备案</p></div>';
        a += '<div class="xiao jiao"><p style="color:#ff5c5b;font-size: 12px;">'+o.haveDealNum+'</p><p style="color:#47506c;font-size: 12px;">已成交</p></div>';          
        a += '</div>';
        a += '<div class="df"><span style="color:#47506c;font-size: 14px;">经纪人数：</span><span style="color:#47506c;font-size: 14px;">'+o.agentNum+'</span></div>';
       
        a += '</div>';
        a += '</div>';         
	})
	$(".shopList").append(a);
}*/
function getShopsListNum(){
	//console.log(addressOne)
$.ajax({
		
		type : "post",
		url : "to_goToGetAllShopsList",
		dataType:"json",
		async:true,
		data:{
			addressOne:addressOne,
			addressTwo:addressTwo,
			sign:'',
			quId:'',			
			},
		success:function(data){
			//allShopList(data);
			$("#zongNum").text(data.shopsList);
		}
	})
}

function getOneShopList(){
	//console.log(addressOne)
	//console.log(addressOne);
$.ajax({
		
		type : "post",
		url : "to_getOneShopsList",
		dataType:"json",
		async:true,
		data:{
			addressOne:addressOne,
			addressTwo:addressTwo,
			start:0,
			limit:limit,
			sign:'',
			quId:'',			
			},
		success:function(data){
			everyShop(data.root);
			//console.log(data.root)
			//mapList(data.root)
			
			
		}
	})
}
function everyShop(data){
	$(".allList").html("");
	//console.log(data);
	var s = '';
	$.each(data,function(v,o){
		
        s += '<div class="everyList" onclick="getShopInfo('+o.shopId+')">';
        if (o.photo != null && o.photo != ""){
        	s += '<div class="leftImg"><img src="'+o.photo+'" alt="" /></div>';   
        }else {
        	s += '<div class="leftImg"><img src="static/images/noPic.png" alt="" /></div>';   
        }
        s += '<div class="rightWord">';
        s += '<p style="font-size: 16px;color:#616b88;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;width:160px;" title="'+o.shopName+'">'+o.shopName+'</p>';
        s += '<p style="font-size: 12px;color:#616b88;">已备案：<span>'+o.guideRecordsNum+'</span></p>';
        s += '<p style="font-size: 12px;color:#616b88;">已成交：<span>'+o.haveDealNum+'</span></p>';
        s += '<p style="font-size: 12px;color:#616b88;">经济人数：<span>'+o.agentNum+'</span></p>';
        if(o.inSystemStutas == 1){
        	s += '<div class="surePLat">平台认证</div>'
        }else{
        	s += '<div class="surePLat">暂未认证</div>'
        }
        
        s += '</div>';
        s += '</div>';
 
            
	})
	$(".allList").append(s);
	 
}

function getShopInfo(info){
	$.ajax({
		type : "post",
		url : "to_getOneShops",
		dataType:"json",
		async:true,
		data:{shopId:info},
		success:function(data){
			everyInfo(data)
		}
	})
}
function everyInfo(data){
	$(".shopDetails").remove();
	var i = '';
	$.each(data,function(v,o){
		i +=  '<div class="shopDetails">';
		i +=  '<div class="topShop"><span style="display:inline-block;width:4px;height:14px;background:#ff6161;"></span><span style="font-size: 16px;color:#565e78;margin-left:5px;" >'+o.companyName+'</span></div>';
        i +=  '<div class="secondShop"><span style="font-size: 16px;color:#565e78;">'+o.companyName+'</span></div>';
		i +=  '<div class="firstShop"><span>已售出房源：<span>'+o.haveDealNum+'</span></span><span style="margin-left:30px;">已备案人数：<span>'+o.guideRecordsNum+'</span></span><span style="margin-left:30px;">门店经纪人数：<span>'+o.agentNum+'</span></span></div>';
		i +=  '<div class="fourthImg">';
		i +=  '<div class="imgBox"><span style="cursor:pointer;" onclick="hideShop()">></span></div>'
		i +=  '<div class="imgRightBox">';
        i +=  '<div class="everyImg">'; 
        if (o.photo != null && o.photo != ""){
        	i +=  '<img src="'+o.photo+'" alt="" style="width:320px;height:210px;"/>';                       
        }else {
        	i +=  '<img src="static/images/noPic.png" alt="" style="width:320px;height:210px;"/>';                       
        }
        i +=  '</div>'
        i +=  '<div class="everyImg" style="margin-left:10px;">';
        i +=  '<img src="static/images/noPic.png" alt="" style="width:130px;height:100px;"/>';
        i +=  '<img src="static/images/noPic.png" alt="" style="width:130px;height:100px;margin-left:8px;"/>';
        i +=  '</div>';                    
        i +=  '<div class="everyImg" style="margin-left:10px;margin-top:10px;">'               
        i +=  '<img src="static/images/noPic.png" alt="" style="width:130px;height:100px;"/>'                  
        i +=  '<img src="static/images/noPic.png" alt="" style="width:130px;height:100px;margin-left:8px;"/>'                      
        i +=  '</div>';                      
  
        i += '</div>';
		i += '</div>';
        i += '<div class="topShop"><span style="display:inline-block;width:4px;height:14px;background:#ff6161;"></span><span style="font-size: 16px;color:#565e78;margin-left:5px;">门店信息</span></div>';
        i += '<div class="information"><span>名称：</span><span>'+o.shopName+'</span></div>';
        i += '<div class="information"><span>服务等级：</span><span>1</span></div>';
        i += '<div class="information"><span>服务年限：</span><span>1</span></div>';
        i += '<div class="information"><span>地址：</span><span>'+o.address+'</span></div>';
		i += '</div>';
	});
	$("#maskBox").show();
	$("#main-content").append(i)
}
function hideShop(){
	$("#maskBox").hide();
	$(".shopDetails").hide();
}

//地图

	

	
	


	
