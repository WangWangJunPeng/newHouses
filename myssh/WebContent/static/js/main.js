$(function(){
	
});


var systemMain = {
		
	URL : {
		projectInfo : function(){
			return "all_system_count";
		},
		projectVisit : function(){
			return "all_visit_person_count";
		},
		projectMedi : function(){
			return "all_meid_data_count";
		}
	},
	
	getSystemCount : function(){
		$.ajax({
			type : "POST",
			url : systemMain.URL.projectInfo(),
			async : true,
			success : function(data){
				systemMain.fillSystemInfo(data);
			}
		});
	},
	
	fillSystemInfo : function(data){
		var s = "";
		$.each(data, function(v, o) { /*o为json的数据（后台传过来的）*/
			s += '<tr><td>' + o.projectCount + '</td>';
			s += '<td>' + o.adviserCount + '</td>';
			s += '<td>' + o.houseCount + '</td>';
			s += '<td>' + o.shopCount + '</td>';
			s += '<td>' + o.agentCount + '</td>';
			s += '<td>' + o.partnerCount + '</td></tr>';
		});

		
		if (data.length > 0) {
			$("#layuitbody1").empty();
			$(s).appendTo($("#layuitbody1"));
		} else {
			$("#layuitbody1").empty();
			$('<td colspan="5">暂无数据</td>').appendTo($("#layuitbody1"));
		}
	},
	
	getVisitInfo : function(startTime, endTime){
		$.ajax({
			type : "POST",
			url : systemMain.URL.projectVisit(),
			async : true,
			data : {
				startTime : startTime,
				endTime : endTime
			},
			success : function(data){
				systemMain.fillVisitInfo(data);
			}
		});
	},
	
	fillVisitInfo : function(data){
		var s = "";
		$.each(data, function(v, o){
			s += '<tr><td>' + o.visiterNum+ '/' +o.visitCount+ '组' + '</td>';
			s += '<td>' + o.writeCount + '</td>';
			s += '<td>' + '申请：'+ o.applyCount +'<br/>'+ '同意：'+ o.agreeCount +'<br/>'+ '否决：'+ o.votedCount + '</td>';
			s += '<td>' + o.writedCount + '</td>';
			s += '<td>' + o.getMoneyCount + '</td>';
			s += '<td>' + o.mediumPerc +' %'+'</td>';
			s += '<td>' + o.mediumTurnove +' %' +  '</td></tr>';
			
		});
		
		$("#search").html("搜索");
		if (data.length > 0) {
			$("#layuitbody2").empty();
			$(s).appendTo($("#layuitbody2"));
		} else {
			$("#layuitbody2").empty();
			$('<td colspan="5">暂无数据</td>').appendTo($("#layuitbody2"));
		}
	},
	
	getMediInfo : function(startTime, endTime){
		$.ajax({
			type : "POST",
			url : systemMain.URL.projectMedi(),
			async : true,
			data : {
				startTime : startTime,
				endTime : endTime
			},
			success : function(data){
				systemMain.fillMediInfo(data);
			}
		});
	},
	
	fillMediInfo : function(data){
		var s = "";
		$.each(data, function(v, o){
			s += '<tr><td>' + o.applyCount + '</td>';
			s += '<td>' + o.confirmCount + '</td>';
			s += '<td>' + o.vetoCount + '</td>';
			s += '<td>' + o.loanCount + '</td>';
			s += '<td>' + o.writeSuccessCount +' %'+'</td>';
			
		});
		if (data.length > 0) {
			$("#layuitbody3").empty();
			$(s).appendTo($("#layuitbody3"));
		} else {
			$("#layuitbody3").empty();
			$('<td colspan="5">暂无数据</td>').appendTo($("#layuitbody3"));
		}
		
	}
		
}