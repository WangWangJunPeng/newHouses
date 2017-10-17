/* 
* @Author: Marte
* @Date:   2017-09-05 09:36:18
* @Last Modified by:   Marte
* @Last Modified time: 2017-09-15 17:06:30
*/

$(document).ready(function(){
    manage_findHouseType();
    manage_searchHouse();
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
    /*$("#bianji").click(function(event) {
         $(".addDiv").animate({right:"0px"}, 500);
    });*/
    $("#houseDelete").click(function(event) {
        $(".addDiv").animate({right:"-480px"}, 500);
        $("#maskBox").hide()

    });
    $("#finish").click(function(){
    	finishEdit();
    })
//批量操作
    $("#manyOperate").change(function(){
    	var flag = $(this).val();
    	batchOperate(flag);
    	
    })
    $("#searchHouse").click(function(){
    	
    	var countTwo = 0;
    	$.ajax({
            type:"post",
            url:"manage_searchHouse",
            async:false,
            data:{
            	  houseTypeId:$("#houseTypeId").val(),
            	  houseKind:$("#houseKind").val(),
            	  houseStatus:$("#houseStatus").val(),
            	  isSee:$("#isSee").val(),
            	  page:"1",
            	  num:"10",
            	},
            success:function(data){
            	countTwo =  Math.ceil(data.total/10);
            	//console.log(countTwo)
            	housesList(data);
            	//return countTwo
            }
            	
        })
        
        //console.log(countTwo)
    	layui.laypage({
			cont: $('.beg-table-paged'),
			pages:countTwo 
				,
			groups: 10 //连续显示分页数
				,
			jump: function(obj, first) {
				//得到了当前页，用于向服务端请求对应数据
				var curr = obj.curr;
				if(!first){
					//layer.msg('第 '+ obj.curr +' 页');
					var index = layer.load(1, {
							  shade: [0.1,'#fff'] //0.1透明度的白色背景
							});
					$.ajax({
				        type:"post",
				        url:"manage_searchHouse",
				        async:false,
				        data:{
				        	  houseTypeId:$("#houseTypeId").val(),
				        	  houseKind:$("#houseKind").val(),
				        	  houseStatus:$("#houseStatus").val(),
				        	  isSee:$("#isSee").val(),
				        	  page:curr,
				        	  num:"10",
				        	},
				        success:function(data){
				        	
				        	//console.log(data)
				        	countTwo =  Math.ceil(data.total/10);
				        	housesList(data);
				        	layer.close(index);
				        }
				    })
				}
				

			}
		});
    })
    //重置
    $("#reset").click(function(){
    	window.location.href ="manage_toHousesManage";
    })
    
});
function manage_findHouseType(){
     $.ajax({
            type:"post",
            url:"manage_findHouseType",         
            success:function(data){
            //console.log(data)
            var housTypeName = '<option value="">选择房型</option>';
            $.each(data,function(o,v){
                housTypeName += '<option value="'+v.houseTypeId+'">'+v.housType+'</option>'
            })
            $("#houseTypeId").html(housTypeName);
            }
        })
}
var count;
function manage_searchHouse(){
	
	$.ajax({
        type:"post",
        url:"manage_searchHouse",
        async:false,
        data:{
        	  houseTypeId:$("#houseTypeId").val(),
        	  houseKind:$("#houseKind").val(),
        	  houseStatus:$("#houseStatus").val(),
        	  isSee:$("#isSee").val(),
        	  page:"1",
        	  num:"10",
        	},
        success:function(data){
        	count =  Math.ceil(data.total/10);
        	//console.log(count);
        	
        	housesList(data);
        }
    })
    return count;
}
//房源列表
function housesList(data){
	var everyHouse = '';
	
	$.each(data.houses,function(o,v){
		
			everyHouse += '<tr>';
			everyHouse += '<td><input type="checkbox" data-housenum="'+v.houseNum+'"  name="checkone"/></td>';
			if(v.houseStatus == 0){
				everyHouse += '<td><span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#e47878;margin-top:5px;"></span></td>';
			}else if(v.houseStatus == 1){
				everyHouse += '<td><span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#0ad27a;margin-top:5px;"></span></td>';
			}else if(v.houseStatus == 3){
				everyHouse += '<td><span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#ff6161;margin-top:5px;"></span></td>';
			}else if(v.houseStatus == 4){
				everyHouse += '<td><span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#cbc341;margin-top:5px;"></span></td>';
			}else if(v.houseStatus == 5){
				everyHouse += '<td><span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#46c1de;margin-top:5px;"></span></td>';
			}else if(v.houseStatus == 6){
				everyHouse += '<td><span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#c5c5c5;margin-top:5px;"></span></td>';
			}else if(v.houseStatus == 7){
				everyHouse += '<td><span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#8d8d8d;margin-top:5px;"></span></td>';
			}else if(v.houseStatus == 8){
				everyHouse += '<td><span style="display:inline-block;width:10px;height:10px;border-radius:5px;background:#666666;margin-top:5px;"></span></td>';
			}
			everyHouse += '<td>'+v.district+'</td>';
			everyHouse += '<td>'+v.buildingNo+'</td>';
			everyHouse += '<td>'+v.unit+'</td>';
			everyHouse += '<td>'+v.houseNo+'</td>';
			everyHouse += '<td>'+v.houseTypeName+'</td>';
			if(v.buildArea == null || v.buildArea == ""){
				everyHouse += '<td>未知</td>';
				
			}else{
				everyHouse += '<td>'+v.buildArea+'</td>';
			}	
			if(v.insideSpace == null || v.insideSpace == ""){
				everyHouse += '<td>未知</td>';
				
			}else{
				everyHouse += '<td>'+v.insideSpace+'</td>';
			}	
			if(v.listPrice == null || v.listPrice == ""){
				everyHouse += '<td>未知</td>';
				
			}else{
				everyHouse += '<td>'+v.listPrice+'</td>';
			}	
			
			everyHouse += '<td>';
			if(v.houseStatus == 0 || v.houseStatus == 1){
				everyHouse += '<img src="static/images/bianji.png" alt="" onclick="bianji(this)" data-status="'+v.houseStatus+'" data-housenum="'+v.houseNum+'" style="cursor:pointer;" title="编辑" />';

			}else{
				everyHouse += '<img src="static/images/bianji.png" alt="" onclick="noChange()"  style="cursor:pointer;" title="编辑" />';

			}
			if(v.houseStatus == 0){
				everyHouse += '<img src="static/images/mdelete.png" alt="" onclick="deleteHouse(this)" data-housenum="'+v.houseNum+'" data-status="'+v.houseStatus+'" title="删除" style="margin-left:5px;cursor:pointer;"/>';

			}else{
				everyHouse += '<img src="static/images/mdelete.png" alt="" onclick="noChange()"  title="删除" style="margin-left:5px;cursor:pointer;"/>';

			}
			if(v.houseStatus == 0 || v.houseStatus == 1){
				if(v.isOpen == 1){
					everyHouse += '<img src="static/images/kejian.png" alt="" title="可见" onclick="isWatch(this)" data-housenum="'+v.houseNum+'" data-isopen="'+v.isOpen+'" style="margin-left:5px;cursor:pointer;"/>';
	
				}else if(v.isOpen == 0){
					everyHouse += '<img src="static/images/bukejian.png" title="不可见" onclick="isWatch(this)" data-housenum="'+v.houseNum+'" data-isopen="'+v.isOpen+'" alt="" style="margin-left:5px;cursor:pointer;"/>';
	
				}
			}else{
				if(v.isOpen == 1){
					everyHouse += '<img src="static/images/kejian.png" alt="" title="可见" onclick="noChange()"  style="margin-left:5px;cursor:pointer;"/>';
	
				}else if(v.isOpen == 0){
					everyHouse += '<img src="static/images/bukejian.png" title="不可见" onclick="noChange()" alt="" style="margin-left:5px;cursor:pointer;"/>';
	
				}
			}
				
			
			everyHouse += '</td>'
			everyHouse += '</tr>';
		
	})
	if(everyHouse == ""){
		layer.msg("暂无数据")
	}
	$("#housesList").html(everyHouse);	
}
//非在售 销控 不能操作
function noChange(){
	layer.msg('该状态下不能操作!',{icon:1,time:1000});
}
//删除
function deleteHouse(obj){
	layer.confirm('确认要删除吗？', {
        title:['操作确认'],
        btn: ['确定','取消'],
        btnAlign:'c'
    }, function(){
         
    	$.ajax({
            type:"post",
            url:"manage_editHouseInfo",
            data:{houseNum:$(obj).data("housenum"),houseStatus:2},
            success:function(data){
            	if(data.status == 200){
            		layer.msg('删除成功!',{icon:1,time:1000},function(){
    					//alert("您确定要进行调整");
    					window.location.href ="manage_toHousesManage";
    					
    					});
            	}else{
            		layer.msg('删除失败!',{icon:1,time:1000},function(){
    					//alert("您确定要进行调整");
    					window.location.href ="manage_toHousesManage";
    					
    					});
            	}
            }
        })
       	 
          
        
    });
	
}
//是否可见
function isWatch(obj){
	var numBer;
	if($(obj).data("isopen") == 0){
		numBer = 1;
	}else if($(obj).data("isopen") == 1){
		numBer = 0;
	}
	
	
	layer.confirm('确认要修改吗？', {
        title:['操作确认'],
        btn: ['确定','取消'],
        btnAlign:'c'
    }, function(){
         
    	$.ajax({
            type:"post",
            url:"manage_editHouseInfo",
            data:{houseNum:$(obj).data("housenum"),isOpen:numBer},
            success:function(data){
            	if(data.status == 200){
            		layer.msg('修改成功!',{icon:1,time:1000},function(){
    					//alert("您确定要进行调整");
            			if(numBer == 1){
            				$(obj).attr("src","static/images/kejian.png")
            			}else if(numBer == 0){
            				$(obj).attr("src","static/images/bukejian.png")
            			}
    					window.location.href ="manage_toHousesManage";
    					
    					});
            	}else{
            		layer.msg('修改失败!',{icon:1,time:1000},function(){
    					//alert("您确定要进行调整");
    					window.location.href ="manage_toHousesManage";
    					
    					});
            	}
            }
        })
          
        
    });	
}
function bianji(obj){
	 $(".addDiv").animate({right:"0px"}, 500);
	 $("#maskBox").show();
	 $.ajax({
	        type:"post",
	        url:"manage_findHouseById",  
	        data:{houseNum:$(obj).data("housenum")},
	        success:function(data){
	        	console.log(data)
	        	$("#houseNum").val(data.houseNum);
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
            			window.location.href ="manage_toHousesManage";
            		})
            	}else{
            		layer.msg(data.message)
            	}
            }
        })
        	
         
        
    });
	
}



//批量操作
function batchOperate(flag){
	var flagMessage;
	if(flag == 1){
		flagMessage = "批量上架"
	}
	if(flag == 2){
		flagMessage = "批量下架"
	}
	if(flag == 3){
		flagMessage = "批量删除"
	}
	if(flag == 4){
		flagMessage = "批量对经纪人可见"
	}
	if(flag == 5){
		flagMessage = "批量对经纪人不可见"
	}
	var manyDeleteHouse = [];
	$("input[name='checkone']:checked").each(function(){ 
		var tv =$(this).data("housenum"); 
		manyDeleteHouse.push(tv);
	})
	
	//console.log(manyDeleteHouse)
	if(manyDeleteHouse != ""){
		layer.confirm('确认要'+flagMessage+'吗？', {
            title:['操作确认'],
            btn: ['确定','取消'],
            btnAlign:'c'
        },function(){
             
            	
            	 $.ajax({
            	        type:"post",
            	        url:"manage_batchEditHouseInfo",  
            	        data:{flag:flag,houseNums:manyDeleteHouse.join(",")},
            	        success:function(data){
            	        	if(data.status == 200){
            	        		layer.msg(data.message,{icon:1,time:1000},function(){
                        			window.location.href ="manage_toHousesManage";
                        		})
            	        	}else{
            	        		layer.msg(data.message,{icon:1,time:1000})
            	        	}
            	        	
            	        }
            	    })
            
            
        });
	}else{
		layer.confirm('请选择要操作的房源', {
            title:['操作确认'],
            btn: ['确定','取消'],
            btnAlign:'c'
        })
	}
	
}