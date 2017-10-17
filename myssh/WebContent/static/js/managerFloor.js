/* 
* @Author: Marte
* @Date:   2017-09-06 09:49:49
* @Last Modified by:   Marte
* @Last Modified time: 2017-09-06 17:46:49
*/
$(document).ready(function(){
	manage_findAllProjectZone();
	
    $(".calendar-date  li").click(function(event) {    
            $(this).css({"background": "rgba(255, 128, 142,0.5)"}).siblings().css({"background": "#47506c"});              
       });
    $(window).scroll(function(event) {
        if($(window).scrollTop() > 0){
            $(".addDiv").css("top","0");
            
        }
        if($(window).scrollTop() == 0){
            $(".addDiv").css("top","50px");
            
        }
    });
    
    $("#edit").click(function(){
    	
    	var houseNum = $("#houseNum").val();
    	if(houseNum != ""){
    		$("#maskBox").show();
    		$(".addDiv").animate({right:"0"}, 500);
    		
        	editHouseNum(houseNum);
    	}else{
    		layer.alert("请选择房号")
    	}
    	
    })
    $("#finish").click(function(){
    	finishEdit();
    })
    $("#watch").click(function(){
    	var houseNum = $("#houseNum").val();
    	console.log(houseNum)
    	if(houseNum != ""){
    		window.location.href = "manage_toHouseHouseDetails?houseNum="+houseNum; 
    	}else{
    		layer.alert("请选择房号")
    	}
    	
    	
    })
   $("#houseDelete").click(function(){
	   $("#maskBox").hide();
	   $(".addDiv").animate({right:"-480px"}, 500);
	  
   })
   
});
//所有区
function manage_findAllProjectZone(){
	$.ajax({
        type:"post",
        url:"manage_findAllProjectZone",         
        success:function(data){
        	allArea(data)
        }
    })
}
function allArea(data){
	var everyArea = '';
	$.each(data,function(o,v){
		everyArea += '<li>';
		everyArea += '<a href="###" onclick="searchDong(this)" data-id="'+v.zoneId+'" style="margin-left:24%;width:50%;display:inline-block;height:40px;line-height: 40px;border-bottom: 1px solid #f2f5f9;position:relative" class="first_floor"><img src="static/images/addhaoTWO.png" alt="" style="position:relative;top:-2px;" class="addjian" /><span  style="font-size:16px;color:#616b88;margin-left:1%;">'+v.zoneName+'</span></a>';                                        
		everyArea += '<ul class="second_floor" ></ul>';
        everyArea += '</li>';
	})
	$(".allArea").html(everyArea);
}
var num = 0;
function searchDong(obj){
	if (num == 0) {
        $(obj).siblings(".second_floor").slideDown("fast");
        $(obj).children(".addjian").attr("src","static/images/jianhao.png");
      num = 1;
   }else if(num == 1){
	   $(obj).siblings(".second_floor").slideUp("fast");
	   $(obj).children(".addjian").attr("src","static/images/addhaoTWO.png");
      num = 0;

   }
	var zoneId = $(obj).data("id");
	$.ajax({
        type:"post",
        url:"manage_findAllBuildingByZoneId", 
        data:{zoneId:zoneId},
        success:function(data){
        	var singleDong = '';
        	$.each(data,function(o,v){
        		singleDong += '<li>';
                singleDong += '<a href="###" onclick="allHouse(this)" data-buildingid="'+v.buildingId+'" style="margin-left:28%;width:60%;display:inline-block;height:40px;line-height: 40px;border-bottom: 1px solid #f2f5f9;" class="first_floor_next"><img src="static/images/loufloor.png" alt="" style="margin-left:1%;"/><span  style="font-size:16px;color:#616b88;margin-left:1%;">'+v.buildingName+'</span></a>';
                singleDong += '</li>';
        	})
        	$(obj).siblings().html(singleDong);
        }
    })
}
//查询楼栋
function allHouse(obj){
	$(".danBiao").show();
	$("#areaKuai").show();
	$("#area").html($(obj).parent().parent().siblings().children("span").html());
	$("#dong").html($(obj).children("span").html());
	var buildingId = $(obj).data("buildingid");
	manage_findBuildingHouses(buildingId);
}
function manage_findBuildingHouses(buildingId){
	
	$.ajax({
        type:"post",
        url:"manage_findBuildingHouses", 
        data:{buildingId:buildingId},
        success:function(data){
        	console.log(data)
        	
        	allDan(data);
        }
    })
}
function allDan(data){
	
	$("#trTable").html("");
	$("#unitNum").html("");
	$("#unitNum").next().remove();
	var tOne = '<th rowspan="2">楼层数</th><th>单元</th>';
	$.each(data.unitInfos,function(o,v){
		tOne += '<th colspan="'+v.unitUser+'" >'+v.unitName+'</th>'
	});
	$("#unitNum").html(tOne);	
	var thOne ='<tr ><th>房号</th>';
	var index;
	for (var k = 0; k<data.unitInfos.length;k++){
		index = data.unitInfos[k].unitUser;
		for(var i = 1;i<=index;i++){
			if(i<10){
				thOne += '<th>0'+i+'</th>';
			}else{
				thOne += '<th>'+i+'</th>';
			}
		}
	}	
		thOne += '</tr>';
		$("#thTable").append(thOne);	
		var tTwo = '';	
		var v = data.allHouse;
		var num = 0;
		for(key in v){
			num++;
		}
		for(var x = num;x>0;x--){

			tTwo += '<tr>';
			tTwo += '<td colspan="2">'+x+'</td>';
			
			//console.log(v[nn])
			$.each(v[x],function(o,v1){
				if(v1.colNum !=0 && v1.rowNum !=0){
					
					if(v1.houseStatus == 0){
						tTwo += '<td class="colorTd" onclick=houseId(this) data-houseid="'+v1.houseNum+'"  colspan="'+v1.colNum+'" rowspan="'+v1.rowNum+'"><span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#e47878;margin-top:5px;margin-right:10px;"></span><span>'+v1.houseNo+'</span></td>';

					}
					if(v1.houseStatus == 1){
						tTwo += '<td class="colorTd" onclick=houseId(this) data-houseid="'+v1.houseNum+'" colspan="'+v1.colNum+'" rowspan="'+v1.rowNum+'"><span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#0ad27a;margin-top:5px;margin-right:10px;"></span><span>'+v1.houseNo+'</span></td>';

					}
					if(v1.houseStatus == 2){
						tTwo += '<td class="colorTd" colspan="'+v1.colNum+'" rowspan="'+v1.rowNum+'" style="background:#c5c5c5;"><span>空</span></td>';

					}
					if(v1.houseStatus == 3){
						tTwo += '<td class="colorTd" onclick=houseId(this) data-houseid="'+v1.houseNum+'" colspan="'+v1.colNum+'" rowspan="'+v1.rowNum+'"><span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#ff6161;margin-top:5px;margin-right:10px;"></span><span>'+v1.houseNo+'</span></td>';

					}
					if(v1.houseStatus == 4){
						tTwo += '<td class="colorTd" onclick=houseId(this) data-houseid="'+v1.houseNum+'" colspan="'+v1.colNum+'" rowspan="'+v1.rowNum+'"><span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#cbc341;margin-top:5px;margin-right:10px;"></span><span>'+v1.houseNo+'</span></td>';

					}
					if(v1.houseStatus == 5){
						tTwo += '<td class="colorTd" onclick=houseId(this) data-houseid="'+v1.houseNum+'" colspan="'+v1.colNum+'" rowspan="'+v1.rowNum+'"><span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#46c1de;margin-top:5px;margin-right:10px;"></span><span>'+v1.houseNo+'</span></td>';

					}
					if(v1.houseStatus == 6){
						tTwo += '<td class="colorTd" onclick=houseId(this) data-houseid="'+v1.houseNum+'" colspan="'+v1.colNum+'" rowspan="'+v1.rowNum+'"><span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#c5c5c5;margin-top:5px;margin-right:10px;"></span><span>'+v1.houseNo+'</span></td>';

					}
					if(v1.houseStatus == 7){
						tTwo += '<td class="colorTd" onclick=houseId(this) data-houseid="'+v1.houseNum+'" colspan="'+v1.colNum+'" rowspan="'+v1.rowNum+'"><span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#8d8d8d;margin-top:5px;margin-right:10px;"></span><span>'+v1.houseNo+'</span></td>';

					}
					if(v1.houseStatus == 8){
						tTwo += '<td class="colorTd" onclick=houseId(this) data-houseid="'+v1.houseNum+'" colspan="'+v1.colNum+'" rowspan="'+v1.rowNum+'"><span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#666666;margin-top:5px;margin-right:10px;"></span><span>'+v1.houseNo+'</span></td>';

					}					
				}			
			})					
		}
		tTwo += '</tr>';
	$("#trTable").html(tTwo);
}
function houseId(obj){
	$("#houseNum").val($(obj).data("houseid"));
	$(obj).css({"background":"#46c1de","color":"#ffffff"}).siblings('.colorTd').css({"background":"#ffffff","color":"#616b88"});
    $(obj).css({"background":"#46c1de","color":"#ffffff"}).parent().siblings().children('.colorTd').css({"background":"#ffffff","color":"#616b88"})

}
//编辑
function editHouseNum(houseNum){
	$.ajax({
        type:"post",
        url:"manage_findHouseById",  
        data:{houseNum:houseNum},
        success:function(data){
        	console.log(data)
        	//$("#houseNum").val(data.houseNum);
        	if(data.houseNo == null || data.houseNo == ""){
        		
        		$("#houseNo").val("未知");
        	}else{
        		$("#houseNo").val(data.houseNo);
        	}
        	
        	if(data.presalePermissionInfo == null || data.presalePermissionInfo == ""){
        		
        		$("#presalePermissionInfo").val("未知");
        	}else{
        		$("#presalePermissionInfo").val(data.presalePermissionInfo);
        	}
        	
        	if(data.district == null || data.district == ""){
        		
        		$("#district").val("未知");
        	}else{
        		$("#district").val(data.district);
        	}
        	
        	
        	if(data.buildingId == null || data.buildingId == ""){
        		$("#buildingId").val("未知");
        	}else{
        		
        		$("#buildingId").val(data.buildingId);
        	}
        	
        	
        		
        		$("#houseStyle").val(data.houseKind)
        	
        	
        	if(data.unit == null || data.unit == ""){
        		$("#danyuan").val("未知");
        	}else{
        		
        		$("#danyuan").val(data.unit)
        	}
        	if(data.floor == null || data.floor == ""){
        		$("#floor").val("未知");
        	}else{
        		
        		$("#floor").val(data.floor)
        	}
        	
        	if(data.direct == null || data.direct == ""){
        		$("#chaoxiang").val("未知");
        	}else{
        		
        		$("#chaoxiang").val(data.direct)
        	}
        	
        	if(data.buildArea == null || data.buildArea == ""){
        		$("#buildArea").val("未知");
        	}else{
        		
        		$("#buildArea").val(data.buildArea)
        	}
        	
        	if(data.usefulArea == null || data.usefulArea == ""){
        		$("#usefulArea").val("未知");
        	}else{
        		
        		$("#usefulArea").val(data.usefulArea)
        	}
        	
        	if(data.listPrice == null || data.listPrice == ""){
        		$("#listPrice").val("未知");
        	}else{
        		
        		$("#listPrice").val(data.listPrice)
        	}
        	if(data.shopPrice == null || data.shopPrice == ""){
        		$("#shopPrice").val("未知");
        	}else{
        		
        		$("#shopPrice").val(data.shopPrice)
        	}
        	
        	if(data.minimumPrice == null || data.minimumPrice == ""){
        		$("#minimumPrice").val("未知");
        	}else{
        		
        		$("#minimumPrice").val(data.minimumPrice)
        	}
        	
        	if(data.houseTypeName == null || data.houseTypeName == ""){
        		$("#houseType").val("未知");
        	}else{
        		
        		$("#houseType").val(data.houseTypeName)
        	}
        		        		        	
        	$("#decorationStandard").val(data.decorationStandard)
        	        	
        	if(data.description == null || data.description == ""){
        		$("#description").val("暂无");
        	}else{
        		
        		$("#description").val(data.description)
        	}
        	if(data.isOpen == 0){
        		$("#bukejian").attr("checked",true)
        	}else if(data.isOpen == 1){
        		$("#kejian").attr("checked",true)
        	}
        	
        	
        		if(data.houseStatus == 0){
	        		$("#xiaokong").attr("checked",true);
	        		
	        	}else if(data.houseStatus == 1){
	        		$("#zaishou").attr("checked",true);
	        		
	        	}      	
        }
    })
}

	function finishEdit(){
		console.log($("#houseNum").val())
		layer.confirm('确认要进行编辑？', {
	        title:['操作确认'],
	        btn: ['确定','取消'],
	        btnAlign:'c'
	    }, function(){
	         
	    	$.ajax({
	            type:"post",
	            url:"manage_editHouseInfo",
	            data:{
	            	houseNum:$("#houseNum").val(),
	            	houseStyle:$("#houseStyle").val(),
	            	chaoxiang:$("#chaoxiang").val(),
	            	buildArea:$("#buildArea").val(),
	            	usefulArea:$("#usefulArea").val(),
	            	listPrice:$("#listPrice").val(),
	            	shopPrice:$("#shopPrice").val(),
	            	minimumPrice:$("#minimumPrice").val(),
	            	houseTypeName:$("#houseType").val(),
	            	decorationStandard:$("#decorationStandard").val(),
	            	description:$("#description").val(),
	            	isOpen:$("input[name='jian']:checked").val(),
	            	houseStatus:$("input[name='status']:checked").val(),
	            	},
	            success:function(data){
	            	if(data.status == 200){
	            		layer.msg(data.message,{icon:1,time:1000},function(){
	            			window.location.href ="manage_toListType";
	            		})
	            	}else{
	            		layer.msg(data.message)
	            	}
	            }
	        })
	        	
	         
	        
	    });
		
}