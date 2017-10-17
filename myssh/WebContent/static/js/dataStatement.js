/* 
* @Author: Marte
* @Date:   2017-06-15 15:57:15
* @Last Modified by:   Marte
* @Last Modified time: 2017-06-20 15:18:37
*/


var dataStateMent = {
		
		URL : {
			visit : function(){
				return "get_visit_data_for_table";
			},
			deal : function(){
				return "get_deal_statement_for_table";;
			},
			out : function(){
				return "get_out_field_statment_for_table";
			}
		},

		getVisitData : function(time, oneDay){
			
			
			
			$.ajax({
				url : dataStateMent.URL.visit(),
				type : "POST",
				async : false,
				data : {time : time, oneDay : oneDay},

			  
			     
				success : function(data){
					//console.log(data);
					
					dataStateMent.fillVisitInfo(data);
					
				}
			});
		},
		
		fillVisitInfo : function(data){
			var s = "<table id='myTable' class='display' cellspacing='0' width='100%'><thead ><tr ><th>日期</th><th>接访总数</th>" +
					"<th>有效接访</th><th>无效接访</th><th>有效接访率</th><th>新客户通道接访</th>" +
					"<th>老客户通道接访</th><th>新客户通道有效接访</th><th>老客户通道有效接访</th><th>新客户通道有效接访率</th>" +
					"<th>老客户通道有效接访率</th><th>确认老客户接访次数</th><th>指定接访</th><th>新客户通道指定接访</th>" +
					"<th>老客户通道指定接访</th><th>指定有效接访</th><th>指定接访率</th><th>新客户通道指定接访率</th><th>老客户通道指定接访率</th>" +
					"<th>指定有效接访率</th><th>总替接数</th><th>总替接率</th><th>总接访时长(/分钟)</th>" +
					"<th>新客户通道接访时长(/分钟)</th><th>老客户通道接访时长(/分钟)</th><th>替接接访时长(/分钟)</th></tr></thead><tbody>";
			$.each(data,function(v,o){
				s += "<tr>";
				s += "<td >" + o.date + "</td>";
				s += "<td>" + o.totalVisitCount + "</td>";
				s += "<td>" + o.effectiveNum + "</td>";
				s += "<td>" + o.invalidNum + "</td>";
				s += "<td>" + o.effectiveRate + " %</td>";
				s += "<td>" + o.newCustomerNum + "</td>";
				s += "<td>" + o.oldCustomerNum + "</td>";
				s += "<td>" + o.newCustomerEffectiveNum + "</td>";
				s += "<td>" + o.oldCustomerEffectiveNum + "</td>";
				s += "<td>" + o.newCustomerEffectiveRate + " %</td>";
				s += "<td>" + o.oldCustomerEffectiveRate + " %</td>";
				s += "<td>" + o.affirmOldCustomerVisitNum + "</td>";
				s += "<td>" + o.appointAgentNum + "</td>";
				s += "<td>" + o.newCustomerAppointAgentNum + "</td>";
				s += "<td>" + o.oldCustomerAppointAgentNum + "</td>";
				s += "<td>" + o.appointAgentEffectiveNum + "</td>";
				s += "<td>" + o.appointAgentRate + " %</td>";
				s += "<td>" + o.newCustomerAppointAgentRate + " %</td>";
				s += "<td>" + o.oldCustomerAppointAgentRate + " %</td>";
				s += "<td>" + o.appointAgentEffectiveRate + " %</td>";
				s += "<td>" + o.allReplaceNum + "</td>";
				//s += "<td>" + o.orderReplaceNum + "</td>";
				s += "<td>" + o.allReplaceRate + " %</td>";
				s += "<td>" + o.allTime + "</td>";
				s += "<td>" + o.newVisitTime + "</td>";
				s += "<td>" + o.oldVisitTime + "</td>";
				s += "<td>" + o.replaceTime + "</td>";
				s += "</tr>";
			});
			s += "</tbody></table>";
			
			
			$('#table-container').children().remove();
				$('#table-container').append(s);
		},
		
		getDealData : function(time, oneDay){
			$.ajax({
				type : "POST",
				url : dataStateMent.URL.deal(),
				async : false,
				data : {time : time, oneDay : oneDay},
				success : function(data){
					//console.log(data);
					dataStateMent.fillDealInfo(data);
				}
			});
		},
		
		fillDealInfo : function(data){
			var s = "<table id='myTable' class='display' cellspacing='0' width='100%'>" +
					"<thead><tr><th>日期</th><th>客户回头率</th><th>储客成交比</th><th>新增二次来访客户数</th>" +
					"<th>新增总储客数</th><th>老客户数</th><th>总认购客户数</th><th>老客户认购数</th>" +
					"<th>老客户认购比</th><th>新客户认购数</th><th>新客户认购比</th><th>总认购套数</th>" +
					"<th>认购金额(/万元)</th><th>认购到款金额(/万元)</th><th>已签约数</th><th>认购锁定房源货值(/万元)</th>" +
					"<th>放弃签约数</th><th>待签约数</th><th>下定数</th><th>已签约房源货值(/万元)</th><th>放弃签约房源货值(/万元)</th>" +
					"<th>待签约房源货值(/万元)</th><th>来访成交比</th><th>认购签约率</th></tr></thead><tbody>";
			$.each(data, function(v,o){
				s += "<tr>";
				s += "<td>" + o.date + "</td>"
				s += "<td>" + o.customerReturnBackVisitRate + " %</td>";//客户回头率
				s += "<td>" + o.reserveCustomerToDealRate + " %</td>";//储客成交比
				s += "<td>" + o.newAddTwiceVisitNum + "</td>";//新增二次来访客户数
				s += "<td>" + o.newAddMyCustomer + "</td>";//新增总储客数
				s += "<td>" + o.oldMyCustomerVisitNum + "</td>";//老客户数
				s += "<td>" + o.allReadyBuyCustomersNum + "</td>";//总认购客户数
				s += "<td>" + o.oldCustomerReadyToBuyNum + "</td>";//老客户认购数
				s += "<td>" + o.oldCustomerReadyToBuyRate + " %</td>";//老客户认购比
				s += "<td>" + o.newCustomerReadyToBuyNum + "</td>";//新客户认购数
				s += "<td>" + o.newCustomerReadyToBuyRate + " %</td>";//新客户认购比
				s += "<td>" + o.allContractNum + "</td>";//总认购套数
				s += "<td>" + o.allContractMoney + "</td>";//认购金额
				s += "<td>" + o.reachContractMoney + "</td>";//认购到款金额
				s += "<td>" + o.haveDealNum + "</td>";//已签约数
				s += "<td>" + o.lockHousePrice + "</td>";//认购锁定房源货值
				s += "<td>" + o.abandonDealNum + "</td>";//放弃签约数
				s += "<td>" + o.readyToDealNum + "</td>";//待签约数
				s += "<td>" + o.readyToAppointNum + "</td>";//下定数
				s += "<td>" + o.haveDealHousePrice + "</td>";//已签约房源货值
				s += "<td>" + o.abandonDealHousePrice + "</td>";//放弃签约房源货值
				s += "<td>" + o.readyToDealHousePrice + "</td>";//待签约房源货值
				s += "<td>" + o.visitToDealRate + " %</td>";//来访成交比
				s += "<td>" + o.readyToDealRate + " %</td>";//认购签约率
				s += "</tr>";
			});
			
			s += "</tbody></table>";
			
			
				$('#table-container').children().remove();
					$('#table-container').append(s);
				
		},
		
		getOutData : function(time, oneDay){
			$.ajax({
				type : "POST",
				url : dataStateMent.URL.out(),
				async : false,
				data : {time : time,oneDay : oneDay},
				success : function(data){
					dataStateMent.fillOutInfo(data);
				}
			});
		},
		
		fillOutInfo : function(data){
			var s = "<table id='myTable' class='display' cellspacing='0' width='100%'><thead><tr>" +
					"<th>日期</th><th>备案客户数</th><th>报备到访数</th><th>备案未到访客户数</th><th>报备到访率</th>" +
					"<th>外场导客占比</th><th>带客成交数</th><th>外场成交</th><th>外场成交占比</th></tr></thead><tbody>";
			$.each(data, function(v, o){
				s += "<tr>";
				s += "<td>" + o.date + "</td>";//日期
				s += "<td>" + o.allGrNum + "</td>";//备案客户数
				s += "<td>" + o.grToGetVisitNum + "</td>";//报备到访数
				s += "<td>" + o.grToNotGetVisitNum + "</td>";//备案未到访客户数
				s += "<td>" + o.grToVisitRate + " %</td>";//报备到访率
				s += "<td>" + o.outFieldToVisitRate + " %</td>";//外场导客占比
				s += "<td>" + o.guideToDealNum + "</td>";//带客成交数
				s += "<td>" + o.outFieldToDealNum + "</td>";//外场成交
				s += "<td>" + o.outFieldToDealRate + " %</td>";//外场成交占比
				s += "</tr>";
			});
			s += "</tbody></table>";
			$('#table-container').children().remove();
			$('#table-container').append(s);
		},
		
		opinionMethod : function(name, time, oneDay){
			
			if(name == "visitMethod"){
				dataStateMent.getVisitData(time,oneDay);
			} else if (name == "dealMethod"){
				dataStateMent.getDealData(time,oneDay);
			} else if (name == "outMethod"){
				dataStateMent.getOutData(time,oneDay);
			}
		},
		
		dataTablesChange : function(time){
			$("#myTable").DataTable({
				
				"scrollY" : function(){
					if(time == "week"){
						return "auto";
					}else{
						return "550";
					}
					
				},
				"scrollX": true,
				"displayLength":"400",
				"paging": false,
				"autoWidth":true,
				"destroy": true,
				//"scrollCollapse": true,
				fixedColumns : {
                    leftColumns : 1
                },
				
			});
		},
		
		dataTablesInit : function(){
			$("#myTable").DataTable({
				"scrollY" : true,
				"scrollX": true,
				"displayLength":"400",
				"paging": false,
				"autoWidth":true,
				"destroy": true,
				//"scrollCollapse": true,
				fixedColumns : {
                    leftColumns : 1
                },
				
				
			});
		}
		
}
  