/* 
 * @Author: Marte
 * @Date:   2017-06-20 14:57:47
 * @Last Modified by:   Marte
 * @Last Modified time: 2017-06-23 15:20:17
 */

$(document).ready(function() {

	/*$('#start').val("2017-05-03");
	$('#end').val("2017-06-03");*/

	$(".calendar-date   li").click(function(event) {

		var oneDay = $(".date").html();
		var methodName = $('#method').val();
		dataAnalysis.toSearchOne("", "", oneDay);
		$('#start').val("");
		$('#end').val("");
		flags = false;
		$(this).css({
			"background" : "rgba(255, 128, 142,0.5)"
		}).siblings().css({
			"background" : "#47506c"
		});
		;
	})

	//dataAnalysis.getDealDataForLabel();
	//dataAnalysis.getBargainData();

	dataAnalysis.init();

	$('#search').click(function() {
		$("#search").attr("disabled",true);
		var startTime = $('#start').val();
		var endTime = $('#end').val();
		flags = false;
		dataAnalysis.toSearch(startTime, endTime, "");

	});

});

//接访图表
var myChart1 = echarts.init(document.getElementById('main'));
//储客分析图表
var myChart2 = echarts.init(document.getElementById('mainOne'));
//成交分析图表
var myChartTwo = echarts.init(document.getElementById('mainTwo'));

var dataAnalysis = {

	URL : {

		visit : function() {
			return "get_visit_list_for_chart";
		},
		momeryCuEcharts : function() {
			return "get_memory_cus_data";
		},
		dealCustomer : function() {
			return "get_house_buy_data_for_table";
		},
		visitLabel : function() {
			return "get_visit_data_for_label";
		},
		momeryCustomerLabel : function() {
			return "get_momery_customer_data_for_label";
		},
		totalVisit : function() {
			return "get_deal_data_for_label";
		}
	},
	//获取接访数据
	getVisitDataForChart : function(startTime, endTime, oneDay) {
		myChart1.showLoading({
			text : 'loading',
			effect : 'whirling' 
			});
		$.ajax({
			type : "POST",
			url : dataAnalysis.URL.visit(),
			async : true,
			data : {
				startTime : startTime,
				endTime : endTime,
				oneDay : oneDay
				
			},
			success : function(data) {
				//console.log(data);
				dataAnalysis.jieChart(data);
			}

		})
	},

	//接访数据分析
	/*jieChart : function(data) {

		var loseCustomer = [];
		var loseCustomerAppoint = [];
		var newCustomer = [];
		var newCustomerAppoint = [];
		var visitAgain = [];
		var date = [];
		for (var i = 0; i < data.length; i++) {
			loseCustomer.push(-data[i].loseCustomer);
			loseCustomerAppoint.push(-data[i].loseCustomerAppoint);
			newCustomer.push(data[i].newCustomer);
			newCustomerAppoint.push(data[i].newCustomerAppoint);
			visitAgain.push(data[i].visitAgain);
			//var da = data.date[i].substring(5,10);
			date.push(data[i].dataDate);
			myChart1.hideLoading();
		}

		
		
		myChart1.setOption({
			//backgroundColor: '#eee',
			legend : {
				data : [ '新登(指)', '新登', '再到访', '流失(指)', '流失' ],
				align : 'left',
				left : 10
			},
			brush : {
				removeOnClick : true,
				toolbox : [ 'lineX', 'clear' ],
				xAxisIndex : 0
			},
			toolbox : {
				right : 30,
				feature : {
					magicType : {

					},
				//dataView: {}
				}
			},
			tooltip : {
				show : true,
				formatter : function(params) {
					var toolt = 0;
					if (params.data < 0) {
						toolt = -params.data;
					} else {
						toolt = params.data;
					}
					return params.seriesName + ' <br />' + params.name + ' : '
							+ toolt + '';
				}

			},
			xAxis : {
				type : "category",
				min : 'dataMin',
				max : "dataMax",
				interval : 60,
				axisLabel : {
					interval : 'auto',
					formatter : function(value, index) {
						// 格式化成月/日，只在第一个刻度显示年份
						var date = new Date(value);
						var texts = [ (date.getMonth() + 1), date.getDate() ];
						if (index === 0) {
						    texts.unshift(date.getYear());
						}
						return texts.join('.');
					}
				},
				data : date,
				silent : false,
				axisLine : {
					onZero : true
				},
				splitLine : {
					show : false
				},
				splitArea : {
					show : false
				}
			},
			yAxis : {
				minInterval : 1,
				axisLabel : {
					formatter : function(value, index) {
						if (value < 0) {
							return -value;
						} else {
							return value;
						}
					}
				},
				inverse : false,
				splitArea : {
					show : false
				}
			},
			grid : {
				left : 100
			},
			color : [ '#6CBDD8', '#A4DED3', '#FFE28B', '#5A8BA7', '#BCC1C0' ],
			series : [ {
				name : '新登(指)',
				type : 'bar',
				stack : 'one',
				//itemStyle: itemStyle,
				data : newCustomerAppoint
			}, {
				name : '新登',
				type : 'bar',
				stack : 'one',
				// itemStyle: itemStyle,
				data : newCustomer
			}, {
				name : '再到访',
				type : 'bar',
				stack : 'one',
				//itemStyle: itemStyle,
				data : visitAgain
			}, {
				name : '流失(指)',
				type : 'bar',
				stack : 'one',
				//itemStyle: itemStyle,
				data : loseCustomerAppoint
			}, {
				name : '流失',
				type : 'bar',
				stack : 'one',
				//itemStyle: itemStyle,
				data : loseCustomer
			} ]

		});
		myChart1.on('brushSelected', renderBrushed);

		function renderBrushed(params) {
			var arr3 = []
			var brushed = [];
			var brushComponent = params.batch[0];

			var rawIndices = brushComponent.selected[0].dataIndex;
			brushed.push(rawIndices);

			//console.log(brushed);

			//进行数据的比较
			var arr1 = brushed[0];
			for (var x = 0; x < arr1.length; x++) {
				var arr2 = arr1[x];

				if (arr3.indexOf(arr2) == -1) {
					arr3.push(arr2);
				}
			}
			//console.log(arr3);
			dataAnalysis.visitDataComper(loseCustomer, loseCustomerAppoint,
					newCustomer, newCustomerAppoint, visitAgain, date, arr3);

		}
	},*/
	
	jieChart : function(data) {

		var loseCustomer = [];
		var loseCustomerAppoint = [];
		var newCustomer = [];
		var newCustomerAppoint = [];
		var visitAgain = [];
		var date = [];
		for (var i = 0; i < data.length; i++) {
			loseCustomer.push(-(data[i].loseCustomer+data[i].loseCustomerAppoint));
			newCustomer.push(data[i].newCustomer+data[i].newCustomerAppoint);
			visitAgain.push(data[i].visitAgain);
			//var da = data.date[i].substring(5,10);
			date.push(data[i].dataDate);
		}
		myChart1.hideLoading();

		
		
		myChart1.setOption({
			//backgroundColor: '#eee',
			legend : {
				data : [ '新登', '再到访',  '流失' ],
				align : 'left',
				left : 10
			},
			brush : {
				removeOnClick : true,
				toolbox : [ 'lineX', 'clear' ],
				xAxisIndex : 0
			},
			toolbox : {
				right : 30,
				feature : {
					magicType : {

					},
				//dataView: {}
				}
			},
			tooltip : {
				show : true,
				formatter : function(params) {
					var toolt = 0;
					if (params.data < 0) {
						toolt = -params.data;
					} else {
						toolt = params.data;
					}
					return params.seriesName + ' <br />' + params.name + ' : '
							+ toolt + '';
				}

			},
			xAxis : {
				type : "category",
				min : 'dataMin',
				max : "dataMax",
				interval : 60,
				axisLabel : {
					interval : 'auto',
					formatter : function(value, index) {
						// 格式化成月/日，只在第一个刻度显示年份
						var date = new Date(value);
						var texts = [ (date.getMonth() + 1), date.getDate() ];
						/*if (index === 0) {
						    texts.unshift(date.getYear());
						}*/
						return texts.join('.');
					}
				},
				data : date,
				silent : false,
				axisLine : {
					onZero : true
				},
				splitLine : {
					show : false
				},
				splitArea : {
					show : false
				}
			},
			yAxis : {
				minInterval : 1,
				axisLabel : {
					formatter : function(value, index) {
						if (value < 0) {
							return -value;
						} else {
							return value;
						}
					}
				},
				inverse : false,
				splitArea : {
					show : false
				}
			},
			grid : {
				left : 100
			},
			color : [ '#A4DED3', '#FFE28B', '#BCC1C0' ],
			series : [ {
				name : '新登',
				type : 'bar',
				stack : 'one',
				// itemStyle: itemStyle,
				data : newCustomer
			}, {
				name : '再到访',
				type : 'bar',
				stack : 'one',
				//itemStyle: itemStyle,
				data : visitAgain
			}, {
				name : '流失',
				type : 'bar',
				stack : 'one',
				//itemStyle: itemStyle,
				data : loseCustomer
			} ]

		});
		myChart1.on('brushSelected', renderBrushed);

		function renderBrushed(params) {
			var arr3 = []
			var brushed = [];
			var brushComponent = params.batch[0];

			var rawIndices = brushComponent.selected[0].dataIndex;
			brushed.push(rawIndices);

			//console.log(brushed);

			//进行数据的比较
			var arr1 = brushed[0];
			for (var x = 0; x < arr1.length; x++) {
				var arr2 = arr1[x];

				if (arr3.indexOf(arr2) == -1) {
					arr3.push(arr2);
				}
			}
			//console.log(arr3);
			dataAnalysis.visitDataComper(loseCustomer, newCustomer, visitAgain, date, arr3);

		}
	},
	//进行数据的对比
	visitDataComper : function(loseCustomer, newCustomer, visitAgain, date, arr) {
		if (arr.length < 1) {
			$('#visitNone1').show();
			$('#visitNone2').hide();
			return;
		}

		var a = 0;
		var b = 0;
		var c = 0;
		var d = 0;
		var e = 0;

		var f = 0;
		var g = 0;
		var h = 0;
		var t = 0;
		var j = 0;

		//当前鼠标所在位置
		var len1 = arr[arr.length - 1];
		//当前鼠标往后两倍
		var len2 = len1 + arr.length;
		//当前鼠标往后一格
		var len3 = len1 + 1;
		//对比日期开始时间1
		var startDate = date[arr[0]];
		//对比日期结束时间1
		var centerDate = date[len1];
		//对比日期开始时间2
		var centerDate2 = "";
		if (len3 <= date.length - 1) {
			centerDate2 = date[len3];
		} else {
			centerDate2 = date[date.length - 1];
		}
		//对比日期结束时间2
		var endDate = "";
		if (len2 <= date.length - 1) {

			endDate = date[len2];
		} else {
			endDate = date[date.length - 1];

		}

		//console.log("开始时间:"+startDate+"  中间时间1:"+centerDate+"中间时间2"+centerDate2+" 结束时间 :"+endDate);

		for (var i = 0; i < arr.length; i++) {
			var inde = arr[i];
			a += -loseCustomer[inde];
			//b += -loseCustomerAppoint[inde];
			c += newCustomer[inde];
			//d += newCustomerAppoint[inde];
			e += visitAgain[inde];
		}

		var le1 = len3;
		var le2 = len2;
		/*			console.log("------------le1-----------"+le1);
		 console.log("-------------le2----------------"+le2);
		 console.log("dateLength-------"+date.length);
		 */if (le2 < date.length) {
			for (var x = le2; x >= le1; x--) {
				//console.log("x====="+x);
				f += -loseCustomer[x];
				//g += -loseCustomerAppoint[x];
				h += newCustomer[x];
				//t += newCustomerAppoint[x];
				j += visitAgain[x];

			}
		} else if (len1 == date.length - 1) {
			f = -(loseCustomer[date.length - 1]);
			//g = -(loseCustomerAppoint[date.length - 1]);
			h = newCustomer[date.length - 1];
			//t = newCustomerAppoint[date.length - 1];
			j = visitAgain[date.length - 1];
		} else {
			for (var y = date.length - 1; y >= le1; y--) {
				//console.log("y====="+y);
				f += -loseCustomer[y];
				//g += -loseCustomerAppoint[y];
				h += newCustomer[y];
				//t += newCustomerAppoint[y];
				j += visitAgain[y];
			}
		}

		//开始时间1
		$('#startD1').html(startDate);
		//结束时间1
		$('#endD1').html(centerDate);
		//开始时间2
		$('#startD2').html(centerDate2);
		//结束时间2
		$('#endD2').html(endDate);
		//总接访量
		var zongAll = a+c+e+f+h+j;
		if (zongAll == 0){
			$(".zongRate").css({"width":"50%","background":"#fbe2e3"});
			$(".zongColor").css("background","#d5f2f9");
		}else{
			var zongRate = (a+c+e)*100/zongAll;
			$(".zongRate").css("width",zongRate.toFixed(1)+'%');
			if(a+c+e > f+h+j){
				$(".zongRate").css("background","#eea7a7");
				$(".zongColor").css("background","#d5f2f9");
			}else if(a+c+e < f+h+j) {
				$(".zongRate").css("background","#fbe2e3");
				$(".zongColor").css("background","#46c1de");
			}else if(a+c+e == f+h+j) {
				$(".zongRate").css("background","#eea7a7");
				$(".zongColor").css("background","#46c1de");
			}
		}
		$("#allOne").html(a+c+e);
		$("#allTwo").html(f+h+j);
		//========流失======="
		var loseAll = a + f;
		if(loseAll == 0){
			$(".liushi").css({"width":"50%","background":"#fbe2e3"});
			$(".zongColor").css("background","#d5f2f9")
		}else{
			var loseAllRate = a*100/loseAll;
			$(".liushi").css("width",loseAllRate.toFixed(1)+'%');
			if(a > f){
				$(".liushi").css("background","#eea7a7");
				$(".jieColor").css("background","#d5f2f9");
			}else if(a < f) {
				$(".liushi").css("background","#fbe2e3");
				$(".jieColor").css("background","#46c1de");
			}else if(a == f) {
				$(".liushi").css("background","#eea7a7");
				$(".jieColor").css("background","#46c1de");
			}
		}
		
	
		
		$('#losedD1').html(a);
		//========流失（指）=======
		//$('#losedZ1').html(b);
		//=========新登======"
		var newRateAll = h + c;
		if(newRateAll == 0){
			$(".newRate").css({"width":"50%","background":"#fbe2e3"});
			$(".newColor").css("background","#d5f2f9");
		}else{
			var newRate = c*100/newRateAll;
			$(".newRate").css("width",newRate.toFixed(1)+'%');
			if(c > h){
				$(".newRate").css("background","#eea7a7");
				$(".newColor").css("background","#d5f2f9");
			}else if(c < h) {
				$(".newRate").css("background","#fbe2e3");
				$(".newColor").css("background","#46c1de");
			}else if(c == h) {
				$(".newRate").css("background","#eea7a7");
				$(".newColor").css("background","#46c1de");
			}
		}
		$('#nc1').html(c);
		
		
		
		//======新登（指）=========
		//$('#ncz1').html(d);
		//========再到访=======
		
		var againAll = e + j;
		if(againAll == 0){
			$(".againRate").css({"width":"50%","background":"#fbe2e3"});
			$(".oldColor").css("background","#d5f2f9");
		}else{
			var againAllRate = e*100/againAll;
			$(".againRate").css("width",againAllRate.toFixed(1)+'%');
			if(e > j){
				$(".againRate").css("background","#eea7a7");
				$(".oldColor").css("background","#d5f2f9");
			}else if(e < j) {
				$(".againRate").css("background","#fbe2e3");
				$(".oldColor").css("background","#46c1de");
			}else if(e == j) {
				$(".againRate").css("background","#eea7a7");
				$(".oldColor").css("background","#46c1de");
			}
		}
		//老客户来访占比
		var oneAll = a+c+e;
		var twoAll = f+h+j;
		var againVisitRateOne = 0;
		var againVisitRateTwo = 0;
		if(oneAll == 0){
			$("#againVisitRateOne").html(0+'%')
		}else{
			againVisitRateOne = e*100/oneAll;
			$("#againVisitRateOne").html(againVisitRateOne.toFixed(1)+'%')
			
		}			
		if(twoAll == 0){
			$("#againVisitRateTwo").html(0+'%')
		}else{
			 againVisitRateTwo = j*100/twoAll;
			$("#againVisitRateTwo").html(againVisitRateTwo.toFixed(1)+'%')
			
		}
		
		var againVisitRateAll = againVisitRateOne + againVisitRateTwo;
		
		if (againVisitRateAll == 0 ){
			$(".againVisitRate").css({"width":"50%","background":"#fbe2e3"});
			$(".oldLaiColor").css("background","#d5f2f9");
			
		}else{
			var againSingleRate = againVisitRateOne*100/againVisitRateAll;
			$(".againVisitRate").css("width",againSingleRate.toFixed(1)+'%');
			if(againVisitRateOne > againVisitRateTwo){
				$(".againVisitRate").css("background","#eea7a7");
				$(".oldLaiColor").css("background","#d5f2f9");
			}else if(againVisitRateOne < againVisitRateTwo) {
				$(".againVisitRate").css("background","#fbe2e3");
				$(".oldLaiColor").css("background","#46c1de");
			}else if(againVisitRateOne == againVisitRateTwo) {
				$(".againVisitRate").css("background","#eea7a7");
				$(".oldLaiColor").css("background","#46c1de");
			}
		}
		
		
		//接访流失占比
		
		var loseVisitRateOne = 0;
		var loseVisitRateTwo = 0;
		if(oneAll == 0){
			$("#loseVisitRateOne").html(0+'%')
		}else{
			loseVisitRateOne = a*100/oneAll;
			$("#loseVisitRateOne").html(loseVisitRateOne.toFixed(1)+'%')
			
		}	
		if(twoAll == 0){
			$("#loseVisitRateTwo").html(0+'%')
		}else{
			loseVisitRateTwo = f*100/twoAll;
			$("#loseVisitRateTwo").html(loseVisitRateTwo.toFixed(1)+'%')
			
		}
		var loseVisitRateAll = loseVisitRateOne + loseVisitRateTwo;
		
		if (loseVisitRateAll == 0 ){
			$(".loseVisteRate").css({"width":"50%","background":"#fbe2e3"});
			$(".jieliuColor").css("background","#d5f2f9");
		}else{
			var loseSingleRate = loseVisitRateOne*100/loseVisitRateAll;
			$(".loseVisteRate").css("width",loseSingleRate.toFixed(1)+'%');
			if(loseVisitRateOne > loseVisitRateTwo){
				$(".loseVisteRate").css("background","#eea7a7");
				$(".jieliuColor").css("background","#d5f2f9");
			}else if(loseVisitRateOne < loseVisitRateTwo) {
				$(".loseVisteRate").css("background","#fbe2e3");
				$(".jieliuColor").css("background","#46c1de");
			}else if(loseVisitRateOne == loseVisitRateTwo) {
				$(".loseVisteRate").css("background","#eea7a7");
				$(".jieliuColor").css("background","#46c1de");
			}
		}
		
		
		
		
		
		
		
		$('#va1').html(e);
		//========对比流失=======
		$('#losedD2').html(f);
		//========对比流失（指）=======
		//$('#losedZ2').html(g);
		//=========对比新登======
		$('#nc2').html(h);
		//======对比新登（指）=========
		//$('#ncz2').html(t);
		//========对比再到访=======
		$('#va2').html(j);
		$('#visitNone1').hide();
		$('#visitNone2').show();
	},

	getMomeryCuEchartsData : function(startTime, endTime, oneDay) {
		myChart2.showLoading({
            text : 'loading',
            effect : 'whirling' 
            });
		$.ajax({
			type : "POST",
			url : dataAnalysis.URL.momeryCuEcharts(),
			async : true,
			data : {
				startTime : startTime,
				endTime : endTime,
				oneDay : oneDay
			},
			success : function(data) {
				//console.log(data);
				dataAnalysis.momeryCustomerEcharts(data);
				$("#search").attr("disabled",false);
			}
		});
	},

	//储客分析图表
	momeryCustomerEcharts : function(data) {

		//总储客
		var newCustomer = [];
		//老客户
		var oldCustomer = [];
		var date = [];
		for (var i = 0; i < data.data.length; i++) {
			newCustomer.push(data.data[i].newCustomer);
			oldCustomer.push(data.data[i].oldCustomer);
			var da = data.date[i].substring(5, 10);
			date.push(data.date[i]);
			//console.log(data.data[i].loseCustomer);
		}
		myChart2.hideLoading();
		//console.log(newCustomer);
		//console.log(oldCustomer);
		//console.log(date);
		

		myChart2.setOption({
			//backgroundColor: '#eee',
			legend : {
				data : [ '总储客', '老客户' ],
				align : 'left',
				left : 10
			},
			brush : {
				toolbox : [''],
				xAxisIndex : 0
			},
			color : [ '#A5DCD2', '#FFE58B' ],
			toolbox : {
				right : 30,
				feature : {
					magicType : {

					},
				//dataView: {}
				}
			},
			tooltip : {},
			xAxis : {
				type : "category",
				min : 'dataMin',
				max : "dataMax",
				interval : 60,
				data : date,
				axisLabel : {
					interval : 'auto',
					formatter : function(value, index) {
						// 格式化成月/日，只在第一个刻度显示年份
						var date = new Date(value);
						var texts = [ (date.getMonth() + 1), date.getDate() ];
						/*if (index === 0) {
						    texts.unshift(date.getYear());
						}*/
						return texts.join('.');
					}
				},
				silent : false,
				axisLine : {
					onZero : true
				},
				splitLine : {
					show : false
				},
				splitArea : {
					show : false
				}
			},
			yAxis : {
				min : 0,
				/*minInterval: 1,
				interval : 1,*/
				inverse : false,
				splitArea : {
					show : false
				},

			},
			grid : {
				left : 100
			},

			series : [ {
				name : '总储客',
				type : 'line',
				stack : '1',
				areaStyle : {
					normal : {}
				},
				data : newCustomer
			}, {
				name : '老客户',
				type : 'line',
				stack : '2',
				areaStyle : {
					normal : {}
				},
				data : oldCustomer
			} ]

		});
		myChart2.on('brushSelected', renderBrushed);

		function renderBrushed(params) {
			var arr3 = []
			var brushed = [];
			var brushComponent = params.batch[0];

			var rawIndices = brushComponent.selected[0].dataIndex;
			brushed.push(rawIndices);

			//console.log(brushed);

			//进行数据的比较
			var arr1 = brushed[0];
			for (var x = 0; x < arr1.length; x++) {
				var arr2 = arr1[x];

				if (arr3.indexOf(arr2) == -1) {
					arr3.push(arr2);
				}
			}
			//console.log(arr3);
			dataAnalysis.memeryCuDataComper(newCustomer, oldCustomer, date,
					arr3);

		}

	},

	//进行数据的对比
	memeryCuDataComper : function(newCustomer, oldCustomer, date, arr) {
		if (arr.length < 1) {
			$('#memeryView1').show();
			$('#memeryView2').hide();
			return;
		}

		var a = 0;
		var b = 0;

		var f = 0;
		var g = 0;

		//当前鼠标所在位置
		var len1 = arr[arr.length - 1];
		//当前鼠标往后两倍
		var len2 = len1 + arr.length;
		//当前鼠标往后一格
		var len3 = len1 + 1;
		/*console.log("len1===="+len1);
		console.log("len2===="+len2);*/
		//对比日期开始时间1
		var startDate = date[arr[0]];
		//对比日期结束时间1
		var centerDate = date[len1];
		//对比日期开始时间2
		var centerDate2 = "";
		if (len3 < date.length - 1) {
			centerDate2 = date[len3];
		} else {
			centerDate2 = date[date.length - 1];
		}
		//对比日期结束时间2
		var endDate = "";
		if (len2 <= date.length - 1) {

			endDate = date[len2];
		} else {
			endDate = date[date.length - 1];

		}

		//console.log("开始时间:"+startDate+"  中间时间1:"+centerDate+"中间时间2"+centerDate2+" 结束时间 :"+endDate);

		for (var i = 0; i < arr.length; i++) {
			var inde = arr[i];
			a += newCustomer[inde];
			b += oldCustomer[inde];

		}

		var le1 = len3;
		var le2 = len2;
		/*console.log("------------le1-----------"+le1);
		console.log("-------------le2----------------"+le2);
		console.log("dateLength-------"+date.length);*/
		if (le2 < date.length) {
			for (var x = le2; x >= le1; x--) {
				//console.log("x====="+x);
				f += newCustomer[x];
				g += oldCustomer[x];
			}
		} else if (len1 == date.length - 1) {
			f = newCustomer[date.length - 1];
			g = oldCustomer[date.length - 1];
		} else {
			for (var y = date.length - 1; y >= le1; y--) {
				//console.log("y====="+y);
				f += newCustomer[y];
				g += oldCustomer[y];
			}
		}

		//开始时间1
		$('#startD3').html(startDate);
		//结束时间1
		$('#endD3').html(centerDate);
		//开始时间2
		$('#startD4').html(centerDate2);
		//结束时间2
		$('#endD4').html(endDate);

		//========新客户======="
		$('#nct1').html(a);
		//========老客户=======
		$('#oct1').html(b);
		//========对比新客户=======
		$('#nct2').html(f);
		//========对比老客户=======
		$('#oct2').html(g);
		$('#memeryView1').hide();
		$('#memeryView2').show();
	},

	//成交客户分析数据获取（不根据时间交互，累计）
	getBargainData : function() {
		 myChartTwo.showLoading({
	            text : 'loading',
	            effect : 'whirling' 
	            });
		$.ajax({
			type : "POST",
			url : dataAnalysis.URL.dealCustomer(),
			async : true,
			success : function(data) {
				//console.log(data);
				dataAnalysis.bargainChart(data);
			}
		});
	},

	//成交客户分析
	bargainChart : function(data) {

		var houseTypeName = [];
		var inventoryHouseNum = [];
		var soldHouseNum = [];
		for (var i = 0; i < data.houseTypeName.length; i++) {
			houseTypeName.push(data.houseTypeName[i]);
			inventoryHouseNum.push(data.inventoryHouseNum[i]);
			soldHouseNum.push(data.soldHouseNum[i]);
			
		}
		
		//进行百分比的计算
		//可售房源的百分比
		var idachild = [];
		//已售房源的百分比
		var sdachild = [];
		for(var y = 0; y < inventoryHouseNum.length; y++){
			var ab1 = inventoryHouseNum[y];
			var ab2 = soldHouseNum[y];
			//可售房源的百分比(Number(Price*Num)).toFixed(2)
			if(ab1+ab2 != 0){
				idachild.push((Number((ab1/(ab1+ab2)) * 100)).toFixed(2));
				//已售房源的百分比
				sdachild.push((Number((ab2/(ab1+ab2)) * 100)).toFixed(2));
			}else{
				idachild.push(0);
				sdachild.push(0);
			}
		}
		if(data.houseTypeName.length==0){
			idachild.push(0);
			sdachild.push(0);
		}
		myChartTwo.hideLoading();
		


		
		
		
		var optionTwo = {

				 tooltip : {
				        trigger: 'axis',
				        formatter: function(p) {
		                 // return optionTwo.series[p.seriesIndex].trueData[p.dataIndex] / 10 * 10;
		                	/*return "库存房源&nbsp;:&nbsp;" + inventoryHouseNum[p[0].dataIndex] / 10 * 10  + "套</br>占比&nbsp;:&nbsp; " + p[1].data + "%</br>" +
		                			"已售房源&nbsp;:&nbsp;"+soldHouseNum[p[0].dataIndex] / 10 * 10  + "套</br>占比&nbsp;:&nbsp;" + p[0].data + "%";*/
				        	return "库存房源占比&nbsp;:&nbsp; " + p[1].data + "%</br>" +
                			"已售房源占比&nbsp;:&nbsp;" + p[0].data + "%";
		                }
				    },

			    grid: {
			        left: '0',
			        right: '2%',
			        bottom: '10%',
			        containLabel: true
			    },
			    legend: {
			        itemHeight: 14,
			        itemWidth: 14,
			        selectedMode: false,
			        right : '10%',
			        data: ['库存房源', '已售房源']
			    },
			    color : ['#eea7a7','#9fd269'],
			    yAxis: [{
			        show: false,
			        type: 'value',
			        position : 'right'
			        
			    }, {
			        type: 'value',
			        axisLabel: {
			            formatter: '{value} %'
			        },
			        min: 0,
			        interval: 10,
			        max: 100,
			    }],
			    xAxis: {
			        type: 'category',
			        axisTick: {
			            show: false //隐藏Y轴刻度
			        },
			        axisLabel: {  
			        	   interval:0,  
			        	   rotate:20  
			        	} ,
			        data: houseTypeName
			    },
			    series: [{
			        name: '已售房源',
			        type: 'bar',
			        barWidth: 45,
			        stack: '1',
			        /*label: {
			            normal: {
			                show: true,
			                position: 'inside',
			                formatter: function(p) {
			                   return optionTwo.series[p.seriesIndex].trueData[p.dataIndex] / 10 * 10 + "套";
			                }
			            }
			        },*/
			        //trueData: soldHouseNum,
			        data: sdachild
			    }, {
			        name: '库存房源',
			        type: 'bar',
			        barWidth: 45,
			        stack: '1',
			        /*label: {
			            normal: {
			                show: true,
			                position: 'inside',
			                formatter: function(p) {
			                    return optionTwo.series[p.seriesIndex].trueData[p.dataIndex] / 10 * 10 + "套";
			                }
			            }
			        },*/
			        //trueData: inventoryHouseNum,
			        data: idachild
			    }]
				
			
		}
		
		
		

		myChartTwo.setOption(optionTwo);

	},

	//接访数据（侧边）
	getVisitLabel : function(startTime, endTime, oneDay) {
		//首先清空
		$('#appointVisitCount').html("");
		$('#appointVisitLosed').html("");
		$('#averageVisitTime').html("");
		$('#inValidVisitCount').html("");
		$('#newCustomerAccess').html("");
		$('#newCustomerAccessLosed').html("");
		$('#oldCustomerAccess').html("");
		$('#oldCustomerAccessLosed').html("");
		$('#replaceVisitCount').html("");
		$('#totalVisitCount').html("");
		$('#validVisitCount').html("");
		$('#allAppointLosed').html("");
		$('#allAppointVisitCount').html("");
		$('#allAverageVisitTime').html("");
		$('#allNewCustomerAccess').html("");
		$('#allNewStomerAccessLosed').html("");
		$('#allOldCustomerAccess').html("");
		$('#allOldCustomerAccessLosed').html("");
		$('#allVisitCount').html("");
		$('#allReplaceVisitCount').html("");
		
		$('#visitLabelshow').show();
		
		
		$.ajax({
			type : "POST",
			url : dataAnalysis.URL.visitLabel(),
			async : true,
			data : {
				startTime : startTime,
				endTime : endTime,
				oneDay : oneDay
			},
			success : function(data) {
				//console.log(data);
				dataAnalysis.fillVisitLabel(data);
				$('#visitLabelshow').hide();
			}
		});
	},

	fillVisitLabel : function(data) {
		$('#appointVisitCount').html(data.appointVisitCount);
		$('#appointVisitLosed').html(data.appointVisitLosed + "%");
		$('#averageVisitTime').html(data.averageVisitTime);
		$('#inValidVisitCount').html(data.inValidVisitCount);
		$('#newCustomerAccess').html(data.newCustomerAccess);
		$('#newCustomerAccessLosed').html(data.newCustomerAccessLosed + "%");
		$('#oldCustomerAccess').html(data.oldCustomerAccess);
		$('#oldCustomerAccessLosed').html(data.oldCustomerAccessLosed + "%");
		$('#replaceVisitCount').html(data.replaceVisitCount);
		$('#totalVisitCount').html(data.totalVisitCount);
		$('#validVisitCount').html(data.validVisitCount);
		$('#allAppointLosed').html("/平均:" + data.allAppointLosed + "%");
		$('#allAppointVisitCount').html("/累计:" + data.allAppointVisitCount);
		$('#allAverageVisitTime').html("/平均:" + data.allAverageVisitTime);
		$('#allNewCustomerAccess').html("/累计:" + data.allNewCustomerAccess);
		$('#allNewStomerAccessLosed').html(
				"/平均:" + data.allNewStomerAccessLosed + "%");
		$('#allOldCustomerAccess').html("/累计:" + data.allOldCustomerAccess);
		$('#allOldCustomerAccessLosed').html(
				"/平均:" + data.allOldCustomerAccessLosed + "%");
		$('#allVisitCount').html("/累计:" + data.allVisitCount);
		$('#allReplaceVisitCount').html("/累计:" + data.allReplaceVisitCount);
		toChangeCircleOne();
	},

	//获取储客数据（侧边）
	getMomeryCustomerLabel : function(startTime, endTime, oneDay) {
		//首先清空
		$('#customerReturnBackVisitNum').html("");
		$('#newCustomerCount').html("");
		$('#platformCustomerCount').html("");
		$('#platformCustomerRate').html("");
		$('#totalCustomerReturnBackVisitNum').html("");
		$('#totalNewCustomerCount').html("");
		$('#totalPlatformCustomerCount').html("");
		$('#totalPlatformCustomerRate').html("");
		
		
		$('#memoryLoading').show();
		$.ajax({
			type : "POST",
			url : dataAnalysis.URL.momeryCustomerLabel(),
			async : true,
			data : {
				startTime : startTime,
				endTime : endTime,
				oneDay : oneDay
			},
			success : function(data) {
				//console.log(data);
				$('#memoryLoading').hide();
				dataAnalysis.fillMomeryCuDataForLabel(data);
				/*if(flags){
					$('#start').val(data.thisDate);
					$('#end').val(data.thisSevenDate);
				}*/
			}
		});
	},

	fillMomeryCuDataForLabel : function(data) {
		$('#customerReturnBackVisitNum').html(data.customerReturnBackVisitNum);
		$('#newCustomerCount').html(data.newCustomerCount);
		$('#platformCustomerCount').html(data.platformCustomerCount);
		$('#platformCustomerRate').html(data.platformCustomerRate + "%");
		$('#totalCustomerReturnBackVisitNum').html(
				"/累计 " + data.totalCustomerReturnBackVisitNum);
		$('#totalNewCustomerCount').html("/累计 " + data.totalNewCustomerCount);
		$('#totalPlatformCustomerCount').html(
				"/累计 " + data.totalPlatformCustomerCount);
		$('#totalPlatformCustomerRate').html(
				"/平均  " + data.totalPlatformCustomerRate + "%");
	},

	//获取成交数据（侧边）累计数据，不与时间互动
	getDealDataForLabel : function() {
		$('#dealLoading').show();
		$.ajax({
			type : "POST",
			url : dataAnalysis.URL.totalVisit(),
			async : true,
			success : function(data) {
				//console.log(data);
				$('#dealLoading').hide();
				dataAnalysis.fillDealDataForLabel(data);
			}
		});
	},

	fillDealDataForLabel : function(data) {
		$('#enterBuyCount').html(data.enterBuyCount);
		$('#appointCount').html(data.appointCount + "万元");
		$('#enterBuyRate').html(data.enterBuyRate + "%");
		$('#signedCount').html(data.signedCount);
		$('#visitAndBuyRate').html(data.visitAndBuyRate + "%");
		$('#recordsAndSignedRate').html(data.recordsAndSignedRate + "%");
		$('#notSignedCount').html(data.enterBuyCount - data.signedCount);
		toChangeCircleSeven();
	},

	init : function() {

		dataAnalysis.getDealDataForLabel();
		dataAnalysis.getBargainData();
		dataAnalysis.getVisitDataForChart("", "", "");
		dataAnalysis.getMomeryCuEchartsData("", "", "");
		dataAnalysis.getVisitLabel("", "", "");
		dataAnalysis.getMomeryCustomerLabel("", "", "");
		
	},

	toSearch : function(startTime, endTime, oneDay) {

		if (startTime == "") {
			alert("开始日期不能为空!");
			$("#search").attr("disabled",false);
			return;
		}
		if (endTime == "") {
			alert("结束日期不能为空!");
			$("#search").attr("disabled",false);
			return;
		}
		$("#canvas").remove();
		dataAnalysis.getVisitDataForChart(startTime, endTime, "");
		dataAnalysis.getMomeryCuEchartsData(startTime, endTime, "");
		dataAnalysis.getVisitLabel(startTime, endTime, "");
		dataAnalysis.getMomeryCustomerLabel(startTime, endTime, "");
		$(".can").html(
				'<canvas id="canvas"  height="100"  width="100" ></canvas>');

	},

	toSearchOne : function(startTime, endTime, oneDay) {
		$("#canvas").remove();
		dataAnalysis.getVisitDataForChart("", "", oneDay);
		dataAnalysis.getMomeryCuEchartsData("", "", oneDay);
		dataAnalysis.getVisitLabel("", "", oneDay);
		dataAnalysis.getMomeryCustomerLabel("", "", oneDay);
		$(".can").html(
				'<canvas id="canvas"  height="100"  width="100" ></canvas>');
		//toChangeCircleOne();
	}

}