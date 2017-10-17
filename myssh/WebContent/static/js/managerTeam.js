 $(document).ready(function(){
	 
	 
      $(".calendar-date   li").click(function(event) {
    	  
    	 var oneDay = $(".date").html();
    	  managerTeam.selectOneDayData(oneDay);
    	  $('#de').remove();
    	  $('#select_time').prepend("<option id='de' value=''>请选择</option>")
    	  $('#select_time').val("");
    	  $(this).css({ "background": "rgba(255, 128, 122,0.5)" }).siblings().css({ "background": "#47506c"});;
      })
      
      var time = $('#select_time').val();
      //页面初始化
      managerTeam.init(time);
      
      $('#select_time').change(function(){
    	  time = $('#select_time').val();
    	  if(time != ''){
    		  $('#de').remove();
    		  managerTeam.init(time);
    	  }
      });
      
  })
  
  
  var managerTeam = {
	 
	 URL : {
		 visitTop : function(){
			 return "get_visit_top_and_data";
		 },
		 dealTop : function(){
			 return "get_deal_top_and_data";
		 },
		 memoryCuTop : function(){
			 return "get_memory_cu_top";
		 },
		 userInfo : function(){
			 return "get_agent_img";
		 }
	 },
	 
	 init : function(time){
		 managerTeam.getVisitTopData(time, "");
		 managerTeam.getDealTopData(time, "");
		 managerTeam.getVisitNotRegisterData(time, "");
		 managerTeam.getUserInfo();
	 },
	 
	 selectOneDayData : function(oneDay){
		 managerTeam.getVisitTopData("", oneDay);
		 managerTeam.getDealTopData("", oneDay);
		 managerTeam.getVisitNotRegisterData("", oneDay);
		 managerTeam.getUserInfo();
	 },
	 
	 getVisitTopData : function(time, oneDay){
		$.ajax({
			type : "POST",
			url : managerTeam.URL.visitTop(),
			async : true,
			data : {time : time, oneDay : oneDay},
			success : function(data){
				//console.log(data);
				managerTeam.fillVisitTopInfo(data);
			}
		}); 
	 },
	 
	 fillVisitTopInfo : function(data){
		 var s = "";
		 var index = 0;
		 $.each(data, function(v,o){
			 s += '<div class="row" style="margin-top:15px;margin-bottom:40px;"><div class="col-md-3 "  style="text-align:center">';
			 if(index == 0){
				 s += '<img src="static/images/gold.png" alt=""   /></div>';
			 } else if(index == 1){
				 s += ' <img src="static/images/silver.png" alt="" /></div>';
			 } else if(index == 2){
				 s += '<img src="static/images/copper.png" alt="" /></div>';
			 } else if(index == 3){
				 s += '<span class="paiK" style="color:#ffffff;background:#f9d21a;">'+4+'</span></div>';
			 }else if(index == 4){
				 s += '<span class="paiK" style="color:#ffffff;background:#23ad44;">'+5+'</span></div>';
			 }else{
				 s += '<span class="paiK" style="color:#ffffff;background:#b2b2b2;">'+(index+1)+'</span></div>';
			 }
			 s += '<div class="col-md-2 " style="text-align:center;">';
			 s += '<span style="font-size:12px;color:#616b88;">' + o.userName + '</span></div>';
			 s += '<div class="col-md-2 " style="text-align:center;">';
			 s += '<span style="font-size:12px;color:#616b88;">' + o.visitCount + '次</span></div>';
			 s += '<div class="col-md-2 " style="text-align:center;">';
			 s += '<span style="font-size:12px;color:#616b88;">' + o.validVisitCount + '次</span></div>';
			 s += '<div class="col-md-3 " style="text-align:center;">';
			 s += '<span style="font-size:12px;color:#616b88;">' + o.validVisitRate + '%</span></div></div>';
		/*	 s += '<div class="col-md-2 " style="text-align:center;margin-top:5px;">';
			 s += '<span style="font-size:16px;color:#616b88;">' + o.momeryCuCount + '</span></div>';*/
			 
			 
			 index++;
		 });
		 $('#visit_top').children().remove();
		 $('#visit_top').append(s);
	 },
	 
	 getDealTopData : function(time, oneDay){
		 $.ajax({
			type : "POST",
			url : managerTeam.URL.dealTop(),
			async : true,
			data : {time : time, oneDay : oneDay},
			success : function(data){
				//console.log(data);
				managerTeam.fillDealTopData(data);
			}
		 });
	 },
	 
	 fillDealTopData : function(data){
		 var s = '';
		 var index = 0;
		 $.each(data, function(v, o){
			 s += '<div class="row" style="margin-top:15px;margin-bottom:40px;"><div class="col-md-4 "  style="text-align:center">';
			 if(index == 0){
				 s += '<img src="static/images/gold.png" alt=""   /></div>';
			 }else if(index == 1){
				 s += '<img src="static/images/silver.png" alt=""  /></div>';
			 }else if(index == 2){
				 s += '<img src="static/images/copper.png" alt=""   /></div>';
			 }else if(index == 3){
				 s += '<span class="paiK" style="color:#ffffff;background:#f9d21a;">'+4+'</span></div>';
			 }else if(index == 4){
				 s += '<span class="paiK" style="color:#ffffff;background:#23ad44;">'+5+'</span></div>';
			 }else{
				 s += '<span class="paiK" style="color:#ffffff;background:#b2b2b2;">'+(index+1)+'</span></div>';
			 }
			 s += '<div class="col-md-2 " style="text-align:center;">';
			 s += '<span style="font-size:12px;color:#616b88;">' + o.userName + '</span></div>';
			 s += '<div class="col-md-2 " style="text-align:center;">';
			 s += '<span style="font-size:12px;color:#616b88;">' + o.recordCount + '次</span></div>';
			 s += '<div class="col-md-2 " style="text-align:center;">';
			 s += '<span style="font-size:12px;color:#616b88;">' + o.signCount + '次</span></div>';
			 s += '<div class="col-md-2 " style="text-align:center;">';
			 s += '<span style="font-size:12px;color:#616b88;">' + o.totalMoney + '</span></div></div>';
			 
			 index++;
		 });
		 $('#deal_top').children().remove();
		 $('#deal_top').append(s);
	 },
	 
	 //储客排行
	 getVisitNotRegisterData : function(time, oneDay){
		 $.ajax({
			type : "POST",
			url : managerTeam.URL.memoryCuTop(),
			async : true,
			data : {time : time, oneDay : oneDay},
			success : function(data){
				//console.log(data);
				managerTeam.fillVisitNotRegInfo(data);
			}
		 
		 });
	 },
	 
	 
	 fillVisitNotRegInfo : function(data){
		 var s = '';
		 var nn = 0;
		 $.each(data, function(v, o){
			s += '<div class="row"  style="margin-top:20px;margin-bottom:20px;" ><div class="col-md-2" style="width:18%;">'; 
			if(o.userImg == '' || o.userImg == null){
				s += '<img src="static/images/timg.png" alt="" class="img-rounded" style="width:50px;height:50px;"/></div>';
			}else{
				s += '<img src="' + o.userImg + '" alt="" class="img-rounded" style="width:50px;height:50px;"/></div>';
			}
			s += '<div class="col-md-6" style="width:58%;margin-left:2%;">';
			s += '<p style="font-size:12px;color:#616b88;">' + o.userName + '</p><p><span style="font-size:12px;color:#616b88;">新增储客：</span><span style="font-size:12px;color:#616b88;">' + o.newCustomerCount + '组</span></p></div>';
			s += '<div class="col-md-3 " style="width:20%;margin-top:12px;">';
			if(nn == 0){
				s += '<span class="cupai" style="color:#ffffff;background:#fb7d6f;">'+1+'</span>';
			}else if(nn == 1){
				s += '<span class="cupai" style="color:#ffffff;background:#23b7e5;">'+2+'</span>';
			}else if(nn == 2){
				s += '<span class="cupai" style="color:#ffffff;background:#7266ba;">'+3+'</span>';
			}else if(nn == 3){
				s += '<span class="cupai" style="color:#ffffff;background:#f9d21a;">'+4+'</span>';
			}else if(nn == 4){
				s += '<span class="cupai" style="color:#ffffff;background:#23ad44;">'+5+'</span>';
			}else{
				s += '<span class="cupai" style="color:#ffffff;background:#b2b2b2;">'+(nn + 1)+'</span>';
			}
			
			s += '</div></div>';
			nn++;
		 });
		 $('#not_reg').children().remove();
		 $('#not_reg').append(s);
	 },
	 
	 getUserInfo : function(){
		 $.ajax({
			type : "POST",
			url : managerTeam.URL.userInfo(),
			async : true,
			success : function(data){
				//console.log(data);
				managerTeam.fillUserInfo(data);
			}
		 });
	 },
	 
	 fillUserInfo : function(data){
		 var s = '';
		 $.each(data, function(v, o){
			s += '<div class="row"  style="margin-top:20px;margin-bottom:20px;" ><div class="col-md-2 ">';
			if(o.userImg == '' || o.userImg == null){
				s += '<img src="static/images/timg.png" alt="" class="img-rounded" style="width:50px;height:50px;"/></div>';
			}else {
				s += '<img src="' + o.userImg + '" alt="" class="img-rounded" style="width:50px;height:50px;"/></div>';
			}
			s += '<div class="col-md-8" style="text-align:center;">';
			s += '<div><span style="font-size:12px;color:#616b88;">' + o.userName + '</span></div>';
			s += '<div style="margin-top:10px;"><span style="font-size:12px;color:#616b88;">' + o.phone + '</span></div>';
			s += '</div></div>'
		 });
		 $('#agent_info').children().remove();
		 $('#agent_info').append(s);
	 }
 }