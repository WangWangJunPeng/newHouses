/* 
* @Author: Marte
* @Date:   2017-08-14 15:13:07
* @Last Modified by:   Marte
* @Last Modified time: 2017-08-16 20:57:53
*/

$(document).ready(function(){
	
	//visitInforList()
	allChoice();
	allClientTag();
	var duoCus = [];
	var duovisit = [];
     $(".calendar-date  li").click(function(event) {    
            $(this).css({"background": "rgba(255, 128, 142,0.5)"}).siblings().css({"background": "#47506c"});              
       });
     $(".customStatus").click(function(event) {
    	 $(".cuBox").show();
    	 duoCus = [];
    	 $(".customsTop").html("");
    	 $(".customsTop").html($(this).html());
    	 $(".customsTop").attr("title",$(this).html())
    	 $(this).css("color","#46c1de").parent().siblings().children().css("color","#616b88");
    	 coustomerStatus = $(this).data('value');
         //visitInforList();
    	 initPage()
     });
     $(".deleteBox").click(function(){
    	 $(".cuBox").hide();
    	 coustomerStatus = '';
    	 oneArray = [];
    	 initPage();
    	 $(".customStatus").css("color","#616b88");
     });
     $(".deleteBoxTwo").click(function(){
    	 $(".visitBox").hide();
    	 visitNum = '';
    	 twoArray = [];
    	 initPage();
    	 $(".visitNum").css("color","#616b88");
     });
     $(".deleteBoxThree").click(function(){
    	 $(".guBox").hide();
    	 guwenid = '';
    	 threeArray = [];
    	 initPage();
    	 $(".guishu").css("color","#616b88");
    	 
     });
     $(".deleteBoxFour").click(function(){
    	 $(".biaoBox").hide();
    	 tagIds = '';
    	 fourArray  = [];
    	 initPage();
    	 $(".biaoqian").css("color","#616b88");
     });
     $(".visitNum").click(function(event) {
    	 $(".visitBox").show();
    	 duovisit = [];
    	 $(".visitTop").html("");
    	 $(".visitTop").html($(this).html());
    	 $(".visitTop").attr("title",$(this).html())
         $(this).css("color","#46c1de").parent().siblings().children().css("color","#616b88");
         visitNum = $(this).data('value');
         initPage();
     });

     $(".cuAllcheck").click(function(event) {
        $(this).hide();
        $(".defaultStatus").hide();
        $(".allCheck").show();
        $(".borderKung").css("border","1px solid #46c1de");

     });
     $(".deleteCheck").click(function(event) {
    	 for(var i=0;i<$("input[name='custatus']").length;i++){
    		 $("input[name='custatus']")[i].checked = false;
    	 }
    	 //$(".cuBox").hide();
    	 coustomerStatus = '';
    	 oneArray = [];
    	 duoCus = [];
        $(".cuAllcheck").show()
        $(".defaultStatus").show();
        $(".allCheck").hide();
        $(".borderKung").css("border","0")
        $(".borderKung").css("border-bottom","1px dashed #dde1e8")
        $("#cuSure").attr("disabled",true);
        $("#cuSure").css({"background":"#ffffff","color":"#999999"})
     });
     $(".visitCheck").click(function(event) {
    	 
    	 
        $(this).hide();
        $(".visitSingle").hide();
        $(".visitAll").show();
        $(".visitKuang").css("border","1px solid #46c1de");

     });
     $(".visitDelete").click(function(event) {
    	 for(var i=0;i<$("input[name='visitNum']").length;i++){
    		 $("input[name='visitNum']")[i].checked = false;
    	 }
    	 //$(".visitBox").hide();
    	 visitNum = '';
    	 twoArray = [];
    	 duovisit = [];
         $(".visitCheck").show();
         $(".visitSingle").show();
         $(".visitAll").hide();
         $(".visitKuang").css("border","0");
         $(".visitKuang").css("border","1px dashed #dde1e8");
         $("#visitSure").attr("disabled",true);
         $("#visitSure").css({"background":"#ffffff","color":"#999999"})
     });
     $(".guCheck").click(function(event) {
         $(this).hide();
         $(".guSingle").hide();
         $(".moreDuo").hide();
        $(".guAll").show();
        $(".guKuamg").css("border","1px solid #46c1de");
     });
     $(".guDelete").click(function(event) {
    	 for(var i=0;i<$("input[name='guwen']").length;i++){
    		 $("input[name='guwen']")[i].checked = false;
    	 }
    	 //$(".guBox").hide();
    	 guwenid = '';
    	 threeArray = [];
    	 duoGu = [];
         $(".guCheck").show();
         $(".guSingle").show();
         $(".guAll").hide();
         $(".moreDuo").show();
         $(".guKuamg").css("border","0");
         $(".guKuamg").css("border","1px dashed #dde1e8");
         $("#guSure").attr("disabled",true);
         $("#guSure").css({"background":"#ffffff","color":"#999999"})
     });
     //var num = 0;
     /*$(".downBox").click(function(){
        
       
        $(".xiaBox").show();
        $(this).css({"border":"1px solid #46c1de","border-bottom":"2px solid #ffffff"}).siblings().css("border","1px solid #dde1e8")   
        $(".qianMore").show();
        $(".xiaSingle").show();
        $(".xiaAll").hide();
        
          
     })*/
     $(".qianMore").click(function(event) {
         $(this).hide();
         $(".xiaSingle").hide();
         $(".xiaAll").show()
     });
    $(".xiaDelete").click(function(event) {
    	for(var i=0;i<$("input[name='qian']").length;i++){
    		 $("input[name='qian']")[i].checked = false;
    	}
    	 //$(".biaoBox").hide();
    	//tagIds = '';
    	
    	//fourArray  = [];
    	//duoBiao = [];
        $(".qianMore").show();
        $(".xiaSingle").show();
        $(".xiaAll").hide();
        $("#xiaSure").attr("disabled",true);
        $("#xiaSure").css({"background":"#ffffff","color":"#999999"});
    });
    $("input[name='custatus']").change(function(event) {
    	//$(".cuBox").show();
    	
                //isChecked($(this).val());

                var count = 0;
                for(var i=0;i<$("input[name='custatus']").length;i++){
                        if($("input[name='custatus']")[i].checked == true){
                            $("#cuSure").removeAttr('disabled');
                            $("#cuSure").css({"background":"#ff5c5b","color":"#ffffff"})
                                count++
                        }
                }
                if($(this).prop("checked") == true){
                	oneArray.push($(this).val());
                	duoCus.push($(this).siblings().html())
                	$(".customsTop").html(duoCus.join(","));
                	$(".customsTop").attr("title",duoCus.join(","))
               	 
                }else if($(this).prop("checked") == false){
                	oneArray.splice($.inArray($(this).val(),oneArray),1);
                	duoCus.splice($.inArray($(this).val(),duoCus),1);
                	$(".customsTop").html(duoCus.join(","));
                	$(".customsTop").attr("title",duoCus.join(","))
                }
             if (count == 0) {
            	 $(".cuBox").hide();
            	 oneArray = [];
                       $("#cuSure").attr("disabled",true);
                       $("#cuSure").css({"background":"#ffffff","color":"#999999"})
             };
     });
    
    $("input[name='visitNum']").change(function(event) {
    	/*$(".visitBox").show();*/
    	
                /*isCheckedTwo($(this).val()); */
                var countTwo = 0;
                for(var i=0;i<$("input[name='visitNum']").length;i++){
                        if($("input[name='visitNum']")[i].checked == true){
                            $("#visitSure").removeAttr('disabled');
                            $("#visitSure").css({"background":"#ff5c5b","color":"#ffffff"})
                                countTwo++
                        }
                }
                
                if($(this).prop("checked") == true){
                	twoArray.push($(this).val());
                	duovisit.push($(this).siblings().html())
                	$(".visitTop").html(duovisit.join(","));
                	$(".visitTop").attr("title",duovisit.join(","))
               	 
                }else if($(this).prop("checked") == false){
                	twoArray.splice($.inArray($(this).val(),twoArray),1);
                	duovisit.splice($.inArray($(this).val(),duovisit),1);
                	$(".visitTop").html(duovisit.join(","));
                	$(".visitTop").attr("title",duovisit.join(","))
                }
             if (countTwo == 0) {
            	 $(".visitBox").hide();
            	 twoArray = [];
                       $("#visitSure").attr("disabled",true);
                         $("#visitSure").css({"background":"#ffffff","color":"#999999"})
             };
     });
   
    $("#changeRole").change(function(){
        if ($(this).val() == "0") {
            $(".kaPian").show();
            $(".lieBiao").hide();
        };
         if ($(this).val() == "1") {
            $(".kaPian").hide();
            $(".lieBiao").show();
        };
    }) 
    $("#guSure").click(function(){
    	for(var i=0;i<$("input[name='guwen']").length;i++){
   		 $("input[name='guwen']")[i].checked = false;
   	 }
   	 //$(".guBox").hide();
   	 
        $(".guCheck").show();
        $(".guSingle").show();
        $(".guAll").hide();
        $(".moreDuo").show();
        $(".guKuamg").css("border","0");
        $(".guKuamg").css("border","1px dashed #dde1e8");
        $("#guSure").attr("disabled",true);
        $("#guSure").css({"background":"#ffffff","color":"#999999"})
    	$(".guBox").show();
    	//console.log(threeArray)
    	 guwenid = threeArray.join(",");
    	//console.log(gushuSting)
    	 initPage();
    	 //guwenid = '';
    	 threeArray = [];
    	 duoGu = [];
    });
    $("#xiaSure").click(function(){
    	//console.log(fourArray)
    	//去重
    	var s = [];
    	
    	for(var i = 0;i<fourArray.length;i++){
    	    if(s.indexOf(fourArray[i]) == -1){  //判断在s数组中是否存在，不存在则push到s数组中
    	        s.push(fourArray[i]);
    	    }
    	}
    	//console.log(s)
    	/*for(var i=0;i<$("input[name='qian']").length;i++){
   		 $("input[name='qian']")[i].checked = false;
   	 }*/
   	 //$(".biaoBox").hide();
   	 var hh= s.concat(everyTageId);
       $(".qianMore").show();
       $(".xiaSingle").show();
       $(".xiaAll").hide();
       //$("#xiaSure").attr("disabled",true);
       $("#xiaSure").css({"background":"#ff5c5b","color":"#ffffff" +
       		""})
    	//console.log(fourArray)
    	$(".biaoBox").show();
   	 		tagIds = hh.join(",");
   	//console.log(gushuSting)
    	initPage();
    	//tagIds = '';
   	 //fourArray  = [];
   	 //duoBiao = [];
   });
    $("#cuSure").click(function(){
    	for(var i=0;i<$("input[name='custatus']").length;i++){
   		 $("input[name='custatus']")[i].checked = false;
   	 }
   	 //$(".cuBox").hide();
   
       $(".cuAllcheck").show()
       $(".defaultStatus").show();
       $(".allCheck").hide();
       $(".borderKung").css("border","0")
       $(".borderKung").css("border-bottom","1px dashed #dde1e8")
       $("#cuSure").attr("disabled",true);
       $("#cuSure").css({"background":"#ffffff","color":"#999999"})
    	$(".cuBox").show();
    	coustomerStatus = oneArray.join(",");
    	initPage();
    	//coustomerStatus = '';
	   	 oneArray = [];
	   	 duoCus = [];
    });
    $("#visitSure").click(function(){
    	for(var i=0;i<$("input[name='visitNum']").length;i++){
   		 $("input[name='visitNum']")[i].checked = false;
   	 }
   	 //$(".visitBox").hide();
   
        $(".visitCheck").show();
        $(".visitSingle").show();
        $(".visitAll").hide();
        $(".visitKuang").css("border","0");
        $(".visitKuang").css("border","1px dashed #dde1e8");
        $("#visitSure").attr("disabled",true);
        $("#visitSure").css({"background":"#ffffff","color":"#999999"})
    	$(".visitBox").show();
    	visitNum = twoArray.join(",");
    	initPage();
    	 //visitNum = '';
    	 twoArray = [];
    	 duovisit = [];
    });
    $("#searchBtn").click(function(){
    	$(".customStatus").css("color","#616b88");
    	 $(".visitNum").css("color","#616b88");
    	 $(".guishu").css("color","#616b88");
    	 $(".biaoqian").css("color","#616b88");
    	 
    	 for(var i=0;i<$("input[name='qian']").length;i++){
    		 $("input[name='qian']")[i].checked = false;
    	 }
    	 $(".biaoBox").hide();
    	 tagIds = '';
    	 fourArray  = [];
    	 duoBiao = [];
    	 
    	 for(var i=0;i<$("input[name='guwen']").length;i++){
    		 $("input[name='guwen']")[i].checked = false;
    	 }
    	 $(".guBox").hide();
    	 guwenid = '';
    	 threeArray = [];
    	 duoGu = [];
    	 
    	 for(var i=0;i<$("input[name='visitNum']").length;i++){
    		 $("input[name='visitNum']")[i].checked = false;
    	 }
    	 $(".visitBox").hide();
    	 visitNum = '';
    	 twoArray = [];
    	 duovisit = [];
    	 
    	 for(var i=0;i<$("input[name='custatus']").length;i++){
    		 $("input[name='custatus']")[i].checked = false;
    	 }
    	 $(".cuBox").hide();
    	 coustomerStatus = '';
    	 oneArray = [];
    	 duoCus = [];
    	 
    	searchCondition();
    });
    
    $("#moreDuo").click(function(){
    	$("#moreDuo").hide()
    	 $("#litle").show();
    		moreGuWen();   	
    	
    })
    $("#litle").click(function(){
    	$("#litle").hide();
    	 $("#moreDuo").show();
    		allChoice();   	
    	
    });
    $(window).scroll(function(event) {
        if($(window).scrollTop() > 0){
            $(".addDiv").css("top","0");
            $(".addDivTwo").css("top","0");
        }
        if($(window).scrollTop() == 0){
            $(".addDiv").css("top","50px");
            $(".addDivTwo").css("top","50px");
        }
    });
    /*$(".refuse").click(function(event) {
        $(".addDiv").animate({right:"0"}, 500);
        //popBox();
    });*/
    $(".rightDelete").click(function(){
    	$(".addDiv").animate({right:"-470px"}, 500);
    	$(".addDivTwo").animate({right:"-470px"}, 500);
    	$("#maskBox").hide();
    	$("#guiShuChange").val("0");
    });
    $("#guiShuChange").change(function(){
    	if ($(this).val() == "0") {
    		$(".addDiv").animate({right:"-470px"}, 500);
        	$(".addDivTwo").animate({right:"-470px"}, 500);
        	
        };
         if ($(this).val() == "1") {
        	 $("#maskBox").show()
        	 adjustClient();
        	 $(".addDiv").animate({right:"-470px"}, 500);
            $(".addDivTwo").animate({right:"0"}, 500);
        };
        if ($(this).val() == "2") {
        	adjustGuClient();
        	$("#maskBox").show();
        	$(".addDivTwo").animate({right:"-470px"}, 500);
            $(".addDiv").animate({right:"0"}, 500);
        };
    });
    $("#guwenSure").click(function(){
    	
    	changeOwnerByAgent();
    	
    });
    $("#searchClient").click(function(){
    	if($("#clientNamePhone").val() != ""){
    		searchCli();
    	}
    	
    		
    });
    $("#clientSure").click(function(){
    	
    	changeOwnerByCustomerId()
    	
    });

});
