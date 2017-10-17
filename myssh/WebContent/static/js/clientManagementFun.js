var guwenid = '';
var tagIds ='';
var coustomerStatus = '';
var visitNum = '';
var oneArray = [];
var twoArray = [];
var threeArray = [];
var fourArray = [];
var duoGu = [];
var duoBiao = [];
function isCheckedThree(obj){
	/*$(".guBox").show();*/
	
   var countThree = 0;
        for(var i=0;i<$("input[name='guwen']").length;i++){
                if($("input[name='guwen']")[i].checked == true){
                	
                    $("#guSure").removeAttr('disabled');
                    $("#guSure").css({"background":"#ff5c5b","color":"#ffffff"});
                        countThree++
                }
        }
     if($(obj).prop("checked") == true){
    	 threeArray.push($(obj).data("id"));
    	 duoGu.push($(obj).siblings().text())
    		$(".guTop").html(duoGu.join(","));
    		$(".guTop").attr("title",duoGu.join(","));
    	 
     }else if($(obj).prop("checked") == false){
    	 threeArray.splice($.inArray($(obj).data("id"),threeArray),1);
    	 duoGu.splice($.inArray($(obj).siblings().text(),duoGu),1);
    	 //console.log(duoGu)
    	 $(".guTop").html(duoGu.join(","));
 		 $(".guTop").attr("title",duoGu.join(","));
     }      
     if (countThree == 0) {
    	 $(".guBox").hide();
    	 	   threeArray = [];
               $("#guSure").attr("disabled",true);
               $("#guSure").css({"background":"#ffffff","color":"#999999"})
     };    
}
function isCheckedFour(obj){
	/*$(".biaoBox").show();*/
	
	var countFour = 0;
    for(var i=0;i<$("input[name='qian']").length;i++){
            if($("input[name='qian']")[i].checked == true){
            	
                $("#xiaSure").removeAttr('disabled');
                $("#xiaSure").css({"background":"#ff5c5b","color":"#ffffff"});
                countFour++
            }
    }
 if($(obj).prop("checked") == true){
	 fourArray.push($(obj).data("id"));
	 duoBiao.push($(obj).siblings().text());
	 var duoBiaoTwo = [];
 	
 	for(var i = 0;i<duoBiao.length;i++){
 	    if(duoBiaoTwo.indexOf(duoBiao[i]) == -1){  //判断在s数组中是否存在，不存在则push到s数组中
 	    	duoBiaoTwo.push(duoBiao[i]);
 	    }
 	}
		$(".biaoTop").html(duoBiaoTwo.join(","));
		$(".biaoTop").attr("title",duoBiaoTwo.join(","))
	 
 }else if($(obj).prop("checked") == false){
		 fourArray.splice($.inArray($(obj).data("id"),fourArray),1);
		 duoBiao.splice($.inArray($(obj).siblings().text(),duoBiao),1);
		 var duoBiaoThree = [];
		 	
		 	for(var i = 0;i<duoBiao.length;i++){
		 	    if(duoBiaoThree.indexOf(duoBiao[i]) == -1){  //判断在s数组中是否存在，不存在则push到s数组中
		 	    	duoBiaoThree.push(duoBiao[i]);
		 	    }
		 	}
		$(".biaoTop").html(duoBiaoThree.join(","));
		$(".biaoTop").attr("title",duoBiaoThree.join(","))
 }      
 if (countFour == 0) {
	 $(".biaoBox").hide();
	 fourArray = [];
           $("#xiaSure").attr("disabled",true);
           $("#xiaSure").css({"background":"#ffffff","color":"#999999"})
 };    
}
//搜索条件
function searchCondition(){
	var page2 = 0;
	var index;
	
	if($("#searchCondition").val() != ''){
		//
		$.ajaxSetup({
		    beforeSend: function() {
		    	index = layer.load();
		    },
		     
		   });
		$.ajax({
			type:"post",
			url:"search_projectCustomerOnLike",
			//async:false,
			data:{num:"10",page:"1",srarchCondition:$("#searchCondition").val()},
							 
			success:function(data){
				//$("#waiting").hide()
				allCientList(data.resultList);
				layer.close(index);
				//console.log(data)
			}
		})		
		layui.laypage({
			cont: $('.beg-table-paged'),
			pages:page2 
				,
			groups: 10 //连续显示分页数
				,
			jump: function(obj, first) {
				//得到了当前页，用于向服务端请求对应数据
				var curr = obj.curr;
				if(!first){
					layer.msg('第 '+ obj.curr +' 页');
					$.ajax({
						type:"post",
						url:"search_projectCustomerOnLike",
						async:false,
						data:{num:"10",page:obj.curr,srarchCondition:$("#searchCondition").val()},
						
						success:function(data){
							page2 = data.count;
							allCientList(data.resultList)
							//console.log(data)
						}
					})
				}				

			}
		});
	}
	
}
//默认归宿顾问个数
function allChoice(){
	$(".guSingle").html('');
	$(".guAllR").html('');
	$.ajax({
		type:"post",
		url:"getProjectAgentByNum",
		async:true,
		data:{num:"8"},
		success:function(data){
			guiShuGuWen(data)
			//console.log(data)
		}
	})
}
//归属顾问更多
function moreGuWen(){
	$(".guSingle").html('');
	$(".guAllR").html('');
	$.ajax({
		type:"post",
		url:"getProjectAgentByNum",
		async:true,
		/*data:{num:"8"},*/
		success:function(data){
			guiShuGuWen(data)
			//console.log(data)
		}
	})
}
//客户标签
function allClientTag(){	
	$.ajax({
		type:"post",
		url:"get_tagSearch",
		async:true,
		//data:{num:"8"},
		success:function(data){
			//console.log(data)
			clientTags(data)
			//console.log(data)
		}
	})
}
var tagTypeAndTag;
function clientTags(data){
	
	tagTypeAndTag = data;
	var singleTag = '';
	var indexnum = 0;
	for(key in data){
		
		singleTag += '<div class="col-md-1 downBox" data-key="'+key+'" onclick="allTags(this)" data-value="'+indexnum+'" style="width:12%;height:30px;line-height:30px;border:1px solid #dde1e8;border-radius:4px;margin-left:1%;margin-top:6px;cursor:pointer;position:relative;">';
		singleTag += '<div class="row" style="padding:0;">';
		singleTag += '<div class="col-md-6" style="padding:0;width:70%;margin-left:10%;">';
		singleTag += '<span class="cuBiao" data-value="1" title="'+key+'">'+key+'</span>';                                                
		singleTag += '</div>';
		singleTag += '<div class="col-md-4" style="padding:0;width:16%;">';
		singleTag += '<img src="static/images/blueDown.png" alt="" />';
		singleTag += '</div>';
		singleTag += '</div>';                                    
		singleTag += '</div>';
		indexnum++;	
	}
	$(".everyTag").html(singleTag)
}
var nb = 0;
var tagAllList="";
function allTags(obj){
	
	if($(obj).data("value") == 0 || $(obj).data("value") == 1 || $(obj).data("value") == 2 || $(obj).data("value") == 3 || $(obj).data("value") == 4 || $(obj).data("value") == 5 || $(obj).data("value") == 6){
		$(".xiaBox").css({"position":"absolute","top":"60px"});
	}else if($(obj).data("value") == 7 || $(obj).data("value") == 8 || $(obj).data("value") == 9 || $(obj).data("value") == 10 || $(obj).data("value") == 11 || $(obj).data("value") == 12 || $(obj).data("value") == 13){
		$(".xiaBox").css({"position":"absolute","top":"96px"});
	}else if($(obj).data("value") == 14 || $(obj).data("value") == 15 || $(obj).data("value") == 16 || $(obj).data("value") == 17 || $(obj).data("value") == 18 || $(obj).data("value") == 19 || $(obj).data("value") == 20){
		$(".xiaBox").css({"position":"absolute","top":"132px"});
	}
	//$(".xiaSingle").html('');
	var keys = $(obj).data("key");
	
	var all_tag = '';
	var allCheck_tag ='';
	$.each(tagTypeAndTag[keys],function(v,o){
		//tagAllList=tagAllList+o.tagTypeId+",";
		all_tag +=  '<div class="col-md-1" style="width:10%;" >'+                               
		//<input type="radio" name="'+o.tagTypeId+'" id="'+o.tagId+'" data-typeid="'+o.tagTypeId+'"  value ="'+o.tagId+'" onchange="getTagInfo(this)"/>'+
		//'<label for="'*/+o.tagId+'" class="mo" style="font-weight: 100;position:relative;top:-2px;left:2px;">'+o.tagName+'</label>'+
	    	
		'<span class="biaoqian"  onclick="getTagInfo(this)" data-typeid="'+o.tagTypeId+'" data-id="'+o.tagId+'">'+o.tagName+'</span>'+
				    
		'</div>';
		//console.log(tagAllList)
	});
	$.each(tagTypeAndTag[keys],function(v,o){
		allCheck_tag += '<div class="col-md-1" style="width:10%;">'+
					'<input type="checkbox" name="qian" id="q'+v+'" data-id="'+o.tagId+'" onchange="isCheckedFour(this)"/>'+
					'<label for="q'+v+'" class="mo" style="font-weight: 100;position:relative;top:-2px;left:2px;">'+o.tagName+'</label>'+
				    '</div>';
	});
	
	$(".allCheckTag").html(allCheck_tag);
	$(".xiaSingle").html(all_tag);
	if(nb == 0){
		$(".xiaBox").show();
	    $(obj).css({"border":"1px solid #46c1de","border-bottom":"2px solid #ffffff"}).siblings().css("border","1px solid #dde1e8");
	    $(obj).children().children().children("img").attr("src","static/images/blueUp.png");
	    $(obj).siblings().children().children().children("img").attr("src","static/images/blueDown.png");
	    $(obj).css("z-index","100").siblings().css("z-index","0");
	    nb = 1;
	}else if(nb == 1){
		$(".xiaBox").hide();
		$(obj).children().children().children("img").attr("src","static/images/blueDown.png");
		$(obj).css("border","1px solid #dde1e8")
		nb = 0;
	}
	
	$(".qianMore").show();
    $(".xiaSingle").show();
    $(".xiaAll").hide();
}
function guiShuGuWen(data){
	var guSingle = '';
	var guAll = '';
	$.each(data,function(v,o){
		//console.log(v)
		guSingle += '<div class="col-md-1" style="width:12%;">';
		if(o.userCaption == null || o.userCaption == ""){
			guSingle += '<span class="guishu" data-id="'+o.userId+'" onclick="getPeopleInfor(this)" style="cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;display:inline-block;width:100%;" title="未知">未知</span>';
		}else{
			guSingle += '<span class="guishu" data-id="'+o.userId+'" onclick="getPeopleInfor(this)" style="cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;display:inline-block;width:100%;" title="'+o.userCaption+'">'+o.userCaption+'</span>';

		}
		guSingle += '</div>';
	});
	$(".guSingle").html(guSingle);
	$.each(data,function(v,o){
		guAll += '<div class="col-md-1" style="width:12%;">';
		guAll += '<input type="checkbox" name="guwen" id="g'+v+'" data-id="'+o.userId+'" style="position:relative;top:-7px;" onchange="isCheckedThree(this)"/>';
		if(o.userCaption == null || o.userCaption == ""){
			guAll += '<label for="g'+v+'" class="mo" style="font-weight: 100;width:80%;position:relative;left:2px;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;display:inline-block;" title="未知">未知</label>'

		}else{
			guAll += '<label for="g'+v+'" class="mo" style="font-weight: 100;width:80%;position:relative;left:2px;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;display:inline-block;" title="'+o.userCaption+'">'+o.userCaption+'</label>'

		}
		guAll += '</div>';
	})
	$(".guAllR").html(guAll);
}

var everyTageId =[];
var allTypeId = [];
function getTagInfo(obj){
	
	for(var i=0;i<allTypeId.length;i++){
		if(allTypeId[i] == $(obj).data("typeid")){
			allTypeId.splice(i,1);
			everyTageId.splice(i,1);
			//return false;
		}
		
	}
	allTypeId.push($(obj).data("typeid"));
	everyTageId.push($(obj).data("id"))
	//console.log(allTypeId)
     console.log(everyTageId.join(","))
	 //console.log(tagTypeId);
	 $(".biaoBox").show();
	 duoBiao = [];
	 $(".biaoTop").html("");
	 $(".biaoTop").html($(obj).text());
	 $(".biaoTop").attr("title",$(obj).text())
	tagIds = everyTageId.join(",");
	 //console.log(tagIds)
	 $(obj).css("color","#46c1de").parent().siblings().children().css("color","#616b88");
	initPage();
	
}
function getPeopleInfor(obj){
	//console.log($(obj).data("id"))
	
	$(".guBox").show();
	duoGu = [];
	 $(".guTop").html("");
	 $(".guTop").html($(obj).text());
	 $(".guTop").attr("title",$(obj).text())
	guwenid = $(obj).data("id");
	initPage();
	//initPage();
	$(obj).css("color","#46c1de").parent().siblings().children().css("color","#616b88");
}
function visitInforList(){
	//console.log(1245654545465465454)
	var count;
	$.ajax({
		type:"post",
		url:"search_projectCustomer",
		data:{num:"10",page:"1",projectAgentId:guwenid,coustomerStatus:coustomerStatus,visitNum:visitNum,tagIds:tagIds},
		async:false,		
		success:function(data){
			//alert(data.count);
			allCientList(data.resultList)
			
			count =  data.count;
		}
	})
	return count;
}
function allCientList(data){
	$(".kaList").html('');
	$("#liebiao").html('');
	kapianList(data)
	liebiaoList(data);
}
//卡片
function kapianList(data){
	var kaList = '';
	$.each(data,function(v,o){
		//console.log(o.tagsList)
		kaList += '<div class="col-md-2"  onclick="everyClientInfo(\''+o.projectCustomerId+'\')" style="width:19%;background:#f6f9fd;margin-left:0.8%;margin-top:40px;border:1px solid #dde1e8;border-radius:4px;margin-bottom:10px">';
		kaList += '<div class="row" style="height:46px;line-height:46px;">';
		kaList += '<div class="col-md-2" style="width:18%;text-align:center;padding:0;height:46px;background:#f1f3fa;">';
		kaList += '<img src="static/images/nameManage.png" alt="" style=""/>';
		kaList += '</div>';    
		kaList += '<div class="col-md-6" style="width:70%;">';
		kaList += '<span style="font-size: 14px;color:#616b88;font-weight:bold;">姓名：</span>';
		kaList += '<span style="font-size: 14px;color:#616b88;">'+o.projectCustomerName+'</span>';    
		kaList += '</div>';    
		kaList += '</div>';
		kaList += '<div class="row" style="padding-top:20px;background:#e1e8f4;">';
		kaList += '<div class="col-md-12" >';
		kaList += '<div class="row">';
		kaList += '<div class="col-md-2" style="width:10%;margin-left:2%;margin-top:2px;">';
		kaList += '<img src="static/images/visitNum.png" alt="" />';
		kaList += '</div>';
		kaList += '<div class="col-md-2" style="width:82%;padding:0;padding-left:12px">';
		kaList += '<span style="font-size: 14px;color:#616b88;font-weight:bold;">来访次数：</span>';
		kaList += '<span style="font-size: 14px;color:#616b88;">'+o.visitTimes+'</span>';
		kaList += '</div>';
		kaList += '</div>';
		kaList += '<div class="row" style="margin-top:10px;">';
		kaList += '<div class="col-md-2" style="width:10%;margin-left:2%;margin-top:2px;">';
		kaList += '<img src="static/images/cuZhuang.png" alt="" />';
		kaList += '</div>';
		kaList += '<div class="col-md-2" style="width:82%;padding:0;padding-left:12px">';
		kaList += '<span style="font-size: 14px;color:#616b88;font-weight:bold;">客户状态：</span>';
		kaList += '<span style="font-size: 14px;color:#616b88;">'+o.status+'</span>';
		kaList += '</div>';
		kaList += '</div>';
		kaList += '<div class="row" style="margin-top:10px;">';
		kaList += '<div class="col-md-2" style="width:10%;margin-left:2%;margin-top:2px;">';
		kaList += '<img src="static/images/guiShu.png" alt="" />';
		kaList += '</div>';
		kaList += '<div class="col-md-2" style="width:82%;padding:0;padding-left:12px">';
		kaList += '<span style="font-size: 14px;color:#616b88;font-weight:bold;">归属顾问：</span>';
		kaList += '<span style="font-size: 14px;color:#616b88;">'+o.ownerUserName+'</span>';
		kaList += '</div>';
		kaList += '</div>';
		kaList += '<div class="row" style="margin-top:10px;">';
		kaList += '<div class="col-md-2" style="width:10%;margin-left:2%;margin-top:2px;">';
		kaList += '<img src="static/images/phoneNum.png" alt="" />';
		kaList += '</div>';
		kaList += '<div class="col-md-2" style="width:82%;padding:0;padding-left:12px">';
		kaList += '<span style="font-size: 14px;color:#616b88;font-weight:bold;">手机号码：</span>';    
		kaList += '<span style="font-size: 14px;color:#616b88;">'+o.projectCustomerPhone+'</span>';
		kaList += '</div>';
		kaList += '</div>';
		kaList += '<div class="row" style="margin-top:10px;padding-bottom:20px;">';
		kaList += '<div class="col-md-2" style="width:5%;margin-left:2%;margin-top:2px;">';
		kaList += '<img src="static/images/cuBiao.png" alt="" />';
		kaList += '</div>';
		kaList += '<div class="col-md-8" style="width:82%;">';
		kaList += '<div class="row">';
		kaList += '<div class="col-md-4" style="width:36%;padding:0;padding-left:12px">';
		kaList += '<span style="font-size: 14px;color:#616b88;font-weight:bold;display:inline-block;width:100%;">客户标签：</span>';
		kaList += '</div>';
		kaList += '<div class="col-md-6" style="width:50%;padding:0;">';
		if(o.tagsList != "" && o.tagsList.length == 4 ){
			kaList +='<span style="font-size: 14px;color:#616b88;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;display:inline-block;width:36px; border-right:1px solid #cad4e5;text-align:left;" title="'+o.tagsList[0]+'">'+o.tagsList[0]+'</span>';
		}else{
			kaList +='<span style="font-size: 14px;color:#616b88;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;display:inline-block;width:36px; border-right:1px solid #cad4e5;text-align:left;" title="未知">未知</span>';

		}
		if(o.tagsList != "" && o.tagsList.length == 4){
			kaList +='<span style="font-size: 14px;color:#616b88;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;display:inline-block;width:36px; border-right:1px solid #cad4e5;text-align:center;" title="'+o.tagsList[1]+'">'+o.tagsList[1]+'</span>';
		}else{
			kaList +='<span style="font-size: 14px;color:#616b88;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;display:inline-block;width:36px; border-right:1px solid #cad4e5;text-align:center;" title="未知">未知</span>';

		}
		if(o.tagsList != "" && o.tagsList.length == 4){
			kaList +='<span style="font-size: 14px;color:#616b88;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;display:inline-block;width:36px; border-right:1px solid #cad4e5;text-align:center;" title="'+o.tagsList[2]+'">'+o.tagsList[2]+'</span>';
		}else{
			kaList +='<span style="font-size: 14px;color:#616b88;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;display:inline-block;width:36px; border-right:1px solid #cad4e5;text-align:center;" title="未知">未知</span>';

		}
		if(o.tagsList != "" && o.tagsList.length == 4){
			kaList +='<span style="font-size: 14px;color:#616b88;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;display:inline-block;width:36px; border-right:1px solid #cad4e5;text-align:left;" title="'+o.tagsList[3]+'">'+o.tagsList[3]+'</span>';
		}else{
			kaList +='<span style="font-size: 14px;color:#616b88;cursor: pointer;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;display:inline-block;width:36px; border-right:1px solid #cad4e5;text-align:left;" title="未知">未知</span>';

		}
		/*$.each(o.tagsList,function(ve,oe){
				console.log(oe)
		})*/
		kaList += '</div>';
		kaList += '</div>';
		kaList += '</div>';
		kaList += '</div>';
		kaList += '</div>';           
        kaList += '</div>';
		kaList += '</div>';
	});
	
	$(".kaList").html(kaList)
}
//列表
function liebiaoList(data){
	
	var liebiao = '';
	$.each(data,function(v,o){
		//console.log(2323)
		liebiao += '<tr onclick="everyClientInfo(\''+o.projectCustomerId+'\')">';
		liebiao += '<td>'+o.projectCustomerName+'</td>';
		liebiao += '<td>'+o.visitTimes+'</td>';
		liebiao += '<td>'+o.status+'</td>';
		liebiao += '<td>'+o.ownerUserName+'</td>';
		liebiao += '<td>'+o.projectCustomerPhone+'</td>';
		liebiao += '<td>';
		$.each(o.tagsList,function(ve,oe){
		liebiao +='<span style="font-size: 14px;color:#616b88;margin-right:6px;">'+oe+'</span>';
		})
		liebiao += '</td>';
		liebiao += '</tr>';
	})
	$("#liebiao").html(liebiao);
}
function everyClientInfo(id){
	//console.log(id)
	window.location.href = "projectCustomerInfoManager?projectCustomerId="+id;
	
}
//按顾问归属被调整客户
function adjustGuClient(){
	$.ajax({
		type:"post",
		url:"getProjectAgentByNum",
		async:true,		
		success:function(data){
			adjustGuwen(data)
			//console.log(data)
			adjustGuiShuF(data)
			
		}
	})
}
function adjustGuwen(data){
	var sdGu = '';
	$.each(data,function(v,o){
		sdGu += '<div class="col-md-12" style="position:relative;margin-top:10px">';
		sdGu += '<input type="radio" name="cl" id="l'+v+'"  onclick="everyAdjustGuwen(this)"  style="position:relative;top:2px;" data-id="'+o.userId+'" />';
		sdGu += '<label for="l'+v+'" class="mo" style="font-weight: 100;position:relative;left:2px;">'+o.userCaption+'</label>';	
		sdGu += '</div>';
	})
	
	$(".adjustGuwen").html(sdGu);
}
var adjustGu = "";
var adjustGuiShu = "";
function everyAdjustGuwen(obj){
	//console.log($(obj).data("id"));
	adjustGu = $(obj).data("id");
	$("#adjustName").val($(obj).siblings().html())
	$("#adjustGu").val($(obj).data("id"));
	//console.log(adjustGu)
	//console.log($("#adjustGu").val())
}

function adjustGuiShuF(data){
	var guiShu = '';
	$.each(data,function(v,o){
		guiShu += '<div class="col-md-12" style="position:relative;margin-top:10px">';
		guiShu += '<input type="radio" name="gu" id="s'+v+'" style="position:relative;top:2px;" data-id="'+o.userId+'" onclick="everyAdjustGuiShu(this)"/>';
		guiShu += '<label for="s'+v+'" class="mo" style="font-weight: 100;position:relative;left:2px;">'+o.userCaption+'</label>';	
		guiShu += '</div>';
	})
	
	$(".adjustGuiShu").html(guiShu);
}
function everyAdjustGuiShu(obj){
	//console.log($(obj).data("id"));
	adjustGuiShu = $(obj).data("id");
	//console.log(adjustGuiShu)
	$("#guiShuName").val($(obj).siblings().html())
	$("#adjustGuiShu").val($(obj).data("id"));
	
}


function changeOwnerByAgent(){
	if(adjustGu != "" && adjustGuiShu !=""){
  		 layer.confirm('确认要进行调整吗？', {
            title:['操作确认'],
            btn: ['确定','取消'],
            btnAlign:'c'
        }, function(){
             
            	 $.ajax({
            			type:"post",
            			url:"changeOwnerByAgent",
            			async:true,
            			data:{originalId:adjustGu,neoId:adjustGuiShu},
            			success:function(data){
            				if(data.status == 200){
            					layer.msg('调整成功!',{icon:1,time:1000},function(){
            					//alert("您确定要进行调整");
            					window.location.href ="to_pCustomerManager";
            					adjustGu = "";
            					adjustGuiShu = "";
            					});
            					
            				}
            				else{
            					layer.alert("调整失败")
            			          
            					
            				}
            				
            			}
            		})
           	 
              
            
        }); 	
	}else if(adjustGu == "" || adjustGuiShu ==""){
		layer.alert("请选择被调整顾问和归属")
	}
	
}
//客户调整归属
function adjustClient(){
	$.ajax({
		type:"post",
		url:"getProjectAgentByNum",
		async:true,		
		success:function(data){
			//console.log(data)
			clientGuiShu(data)
			
		}
	})
}
var guiShuGuWenNum = "";
var clientArray = [];
function clientGuiShu(data){
	var anc = '';
	$.each(data,function(v,o){
		anc += '<div class="col-md-12" style="position:relative;margin-top:10px">';
		anc += '<input type="radio" name="uu" id="u'+v+'" style="position:relative;top:2px;" data-id="'+o.userId+'"  onclick="clientAdjustGuiShu(this)"/>';
		anc += '<label for="u'+v+'" class="mo" style="font-weight: 100;position:relative;left:2px;">'+o.userCaption+'</label>';	
		anc += '</div>';
	})
	$(".clientGui").html(anc)
}
function clientAdjustGuiShu(obj){
	guiShuGuWenNum = $(obj).data("id");
}
function searchCli(){
	$.ajax({
		type:"post",
		url:"search_projectCustomerOnChangeOwner",
		async:true,
		data:{srarchCondition:$("#clientNamePhone").val()},
		success:function(data){
			//console.log(data)
			allClient(data);
			
		}
	})
}
function allClient(data){
	//console.log(data)
	$(".allClientG").remove();
	var allC = '<div class="row allClientG" style="border:1px solid #d6dfe6;background:#f0f4fb;height:180px;overflow:auto;">';
	$.each(data,function(v,o){
		allC += '<div class="col-md-12" style="position:relative;margin-top:10px">';
		
		allC += '<input type="checkbox" name="mm" id="m'+v+'" style="position:relative;top:2px;" data-id="'+o.projectCustomerId+'"  onclick="clientAdjustNum(this)"/>';
		if(o.projectCustomerName == "" || o.projectCustomerName == null){
			allC += '<label for="m'+v+'" class="mo" style="font-weight: 100;position:relative;left:2px;">未知</label>';

		}else{
			allC += '<label for="m'+v+'" class="mo" style="font-weight: 100;position:relative;left:2px;">'+o.projectCustomerName+'</label>';

		}
		allC += '<span style="font-size:12px;color:#616b88;position:absolute;left:100px;top:4px;">当前归属顾问'+o.description+'</span>'
		allC += '</div>';
	})
	allC += '<div>';
	$(".fgfg").append(allC);
	
}
function clientAdjustNum(obj){
	 if($(obj).prop("checked") == true){
		 clientArray.push($(obj).data("id"))
    	    	   	 
     }else if($(obj).prop("checked") == false){
    	 clientArray.splice($.inArray($(obj).data("id"),clientArray),1);
    		
     }      
}
function changeOwnerByCustomerId(){
	//console.log(clientArray)
	if(guiShuGuWenNum != "" && clientArray.join(",") !=""){
 		 layer.confirm('确认要进行调整吗？', {
           title:['操作确认'],
           btn: ['确定','取消'],
           btnAlign:'c'
       }, function(){
            
    	   $.ajax({
    			type:"post",
    			url:"changeOwnerByCustomerId",
    			async:true,
    			data:{projectCustomerId:clientArray.join(","),agentId:guiShuGuWenNum},
    			success:function(data){
    				if(data.status == 200){
    					layer.msg('调整成功!',{icon:1,time:1000},function(){
    					//alert("您确定要进行调整");
    					window.location.href ="to_pCustomerManager";
    					guiShuGuWenNum = "";
    					clientArray = [];
    					});
    					
    				}else{
    					layer.alert("调整失败")
  			          
    					
    				}
    				
    			}
    		})
          	 
             
           
       }); 	
	}else if(guiShuGuWenNum == "" || clientArray.join(",") ==""){
		layer.alert("请选择被调整客户和归属")
	}
	
	
}

