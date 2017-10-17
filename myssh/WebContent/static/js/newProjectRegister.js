/* 
* @Author: Marte
* @Date:   2017-07-19 15:45:53
* @Last Modified by:   Marte
* @Last Modified time: 2017-07-20 10:55:57
*/

$(document).ready(function(){
		proMarker();
		allAd();
         getProvinceInfo();
		 bindingChange();
		 
      $("#addUs").click(function(){
    	  $(".topName").show();
    	  $(".welcomeName").hide();
    	  
    	  $(".leftArea").animate({width:"75%"},"slow");
    	  $(".rightArea").animate({width:"25%"},"slow");
    	  //$(".rightArea").animate({background:url(static/images/regBackground.png)},"slow");
    	  $(".dist").show();
    	  $(".twoName").show();
    	  $("select").css("width","22%");
    	  $(".twoName").css("margin-top","20px");
    	  $(".cn").show()
    	  $(".cner").show();
    	  $(".agree").show();
    	  $(".fifth").hide();
    	  $(".agree").show();
    	  $(".fourth").show();  
    	  
      })
      $("#propertyAddress").blur(function(){
    	  var propertyAddress = $("#prov4 option:selected").data("value") + $("#city4 option:selected").data("value") + $("#area4 option:selected").data("value") + $(this).val();
       	
    	  hh(propertyAddress)
      });
      jQuery.validator.addMethod("phone",function(value,element){  
          var tl = /(^1[0-9]{10}$)/;
          return this.optional(element) || (tl.test(value));
      },"(请正确输入手机号)");
     
    	 
    	    $("#myForm").validate({
    	    	
    	    	
    	       rules:{
    	    	   developer:{
    	             required:true,
    	             
    	          },
    	          projectName:{
    	        	  required:true,
    	          },
    	          propertyAddress:{
    	        	  required:true,
    	          },
    	          userCaption:{
    	        	  required:true,
    	          },
    	          phone:{
    	        	  required:true,
    	        	  phone:true
    	          },
    	       },
    	       messages:{
    	    	   developer:{
    	             required:"不能为空",
    	            
    	          },
    	          projectName:{
    	        	  required:"不能为空",
    	          },
    	          propertyAddress:{
    	        	  required:"不能为空",
    	          },
    	          userCaption:{
    	        	  required:"不能为空",
    	          },
    	          phone:{
    	        	  required:"不能为空",
    	          },
    	       },
    	       errorPlacement: function(error, element){ 
    	           if (element.parent().parent().hasClass('cn') ) {
    	               
    	               error.appendTo($(".cn"));

    	           }else if( element.parent().parent().hasClass('cner1')){
    	                   error.appendTo($(".cner1"));
    	           }
    	           else if( element.parent().parent().hasClass('cner2')){
	                   error.appendTo($(".cner2"));
	           }
    	           else if( element.parent().parent().hasClass('cner3')){
	                   error.appendTo($(".cner3"));
	           }
    	           else if( element.parent().parent().hasClass('cner4')){
	                   error.appendTo($(".cner4"));
	           }
    	          
    	    	   //console.log(element)
    	          
    	       },
    	       submitHandler:function(){
    	    	   $.ajax({
    	    	        cache: false,
    	    	        type: "POST",
    	    	        url:"register_project",
    	    	        data:$('#myForm').serialize(),// 你的formid
    	    	        async: true,    
    	    	        success: function(data) {
    	    	        	
    	    	           xin();
    	    	            	
    	    	        }
    	    	    });
    	      },
    	      
    	    });
    	    
             
      
 }); 
    
     
         

var map = new BMap.Map("allmap",{enableMapClick:false});
var point = new BMap.Point(116.331398,39.897445);
map.centerAndZoom(point,15);
 map.enableScrollWheelZoom(true);
// 创建地址解析器实例
 myCity = new BMap.LocalCity();
 myCity.get(myFun);
var myGeo = new BMap.Geocoder();

function hh(propertyAddress){
	//console.log(propertyAddress)
	map.clearOverlays();
	myGeo.getPoint(propertyAddress, function(point){
		$("#propertyLongitude").val(point.lng);
		$("#propertyLatitude").val(point.lat);
		
		if (point) {
			
			map.centerAndZoom(point, 15);
			map.addOverlay(new BMap.Marker(point));
			
			}else{
				alert("您选择地址没有解析到结果!");
			}
		});
	//submit();
}
function myFun(result){
	var cityName = result.name;
	map.setCenter(cityName);	
}
 function proMarker(){
	 $.ajax({			
			type : "post",
			url : "to_getOneShopsListInMapByOnLogin",
			//dataType:"json",
			async: true,
			success:function(data){
				for (var i = 0; i < data.length; i++) {
					//var shid = data[i].shopId;
					var shopName = data[i].shopName; 
					//var inSystemStutas = data[i].inSystemStutas;
				    var point = new BMap.Point(data[i].longitude,data[i].latitude);
				    //console.log(data)
				    addMarker(point,shopName);
				}
			},
			error : function(data){
				console.log(12321312)
			}
		})
} 
function addMarker(point,shopName){
	var htm = "<div style='background:#00b39c;color:#ffffff;width:80px;height:22px;line-height:22px;border-radius:4px;text-align:center;font-size:10px;' class='dd'>"
        +   shopName
       
        + "</div>";  // 创建标注
	/*map.addOverlay(marker); */
	 var myRichMarkerObject = new BMapLib.RichMarker(htm, point, {"enableDragging": false});
	  map.addOverlay(myRichMarkerObject);
	  $(".dd").parent().css("border-radius","4px");
}
function ghg(city){
 if(city != ""){
		//console.log($("#cityChoice").val())
		//alert(11);
		//console.log(city);
		map.centerAndZoom(city,15);      // 用城市名设置地图中心点
	}
}


 
/*function submit(){
	//console.log($("#longitude").val())
	//console.log($("#hidprov4").val());
		//console.log($("#hidcity4").val())
	//console.log($("#hidarea4").val())
	$.ajax({
        cache: false,
        type: "POST",
        url:"register_project",
        data:$('#myForm').serialize(),// 你的formid
        async: false,    
        success: function(data) {
        	
           xin();
            	
        }
    });
}*/

function xin(){
	$(".message").fadeIn("slow");
	setTimeout("shua()",2000);
	
}
function shua(){
	window.location.href = "shop_reg_page"
}
//公告滚动
var scrollIndex=0;
var Timer=null;
function allAd(){
	var s = '';
	$.ajax({
		type : "POST",
		url : "get_announcement",
		async : true,
		success : function(data){
			$.each(data,function(v,o){
				s += '<li>'+o+'</li>';
			})
			$("#allAd").html(s);
			scroll_f();
		}
	});
}
function scroll_f(){
      clearInterval(Timer);
      var ul=$(".topNotice ul");
      var li=ul.children("li");
      var h=li.height();
      var index=0;
      ul.css("height",h*li.length*2);
      ul.html(ul.html()+ul.html());    
      function run(){
          if(scrollIndex>=li.length){
              ul.css("top","0px");
              scrollIndex=1;
              ul.animate({top:-scrollIndex*h},400);
            }else{
              scrollIndex++;
              ul.animate({top:-scrollIndex*h},400);
             }
           }
         Timer=setInterval(run,3000); 
}
function getProvinceInfo(){
	$.ajax({
		type : "post",
		async : false,
		url : "menu_list_province_first",
		success : function(data) {
			data = eval("(" + data + ")");
			setProvinceInfo(data.root);
		}
	});
}

function setProvinceInfo(data){
	for(var i=0;i<data.length;i++){
		$("<option data-value='"+data[i].cityName+"' value='"+data[i].cityId+"'>"+data[i].cityName+"</option> ").appendTo($("#prov4"));
		
	}
	getShiMenue();
}

function bindingChange(){
	$("#prov4").change(function(){
		getShiMenue();
	})
	$("#city4").change(function(){
		changeMarket();
		//ghg($("#city4 option:selected").data("value"))
	})
}

function getShiMenue(){
		var pId = $("#prov4").val();
		//$("#firstCity").siblings().remove();
		$("#city4").html("")
		$.ajax({
			type : "post",
			async : false,
			url : "menu_list_city_area",
			data:{"shengOrShi":pId},
			success : function(data) {
				data = eval("(" + data + ")");
				for(var i=0;i<data.root.length;i++){
					$("<option data-value='"+data.root[i].cityName+"' value='"+data.root[i].cityId+"'>"+data.root[i].cityName+"</option> ").appendTo($("#city4"))
				}
				changeMarket();
			}
		});
}

	function changeMarket(){
		var pId = $("#city4").val();
		//$("#firstArea").siblings().remove();
		$("#area4").html("");
		$.ajax({
		type : "post",
		async : false,
		url : "menu_list_city_area",
		data:{"shengOrShi":pId},
		success : function(data) {
			data = eval("(" + data + ")");
			for(var i=0;i<data.root.length;i++){
				 $("<option data-value='"+data.root[i].cityName+"' value='"+data.root[i].cityId+"'>"+data.root[i].cityName+"</option> ").appendTo($("#area4"));
			 }
		}
	});
		//var bianma = $("#prov4").val() + '-' + $("#city4").val() + '-' + $("#area4").val();
		/*$("#hidprov4").val($("#prov4").val());
		$("#hidcity4").val($("#city4").val());
		$("#hidarea4").val($("#area4").val());*/
		
	}
