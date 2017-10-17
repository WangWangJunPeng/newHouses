/* 
* @Author: Marte
* @Date:   2017-06-12 10:15:10
* @Last Modified by:   Marte
* @Last Modified time: 2017-06-16 13:49:58
*/

$(document).ready(function(){
	
	 var num = 0;
	 var nn = 0;
	
	  //chuanDate();
		 guTotal(num);
		 shopTotal(nn)
		 getGuideRecordsByToday();
		 thingValue();
		 chart();
	  
	  shareMap();
      $(".calendar-date   li").click(function(event) {
    	  $("#timeMoment").val("");
    	  //console.log($("#timeMoment").val())
    	  startTime = $(".date").html() +" "+"00:00:00";
    	  endTime = $(".date").html() +" "+"23:59:59";
    	  //console.log(startTime)
    	  //chuanDate();
    	  chart();
    	  getGuideRecordsByToday();
    	  guTotal(num);
    	  shopTotal(nn);
	     
    	  thingValue();
    	 
    	  shareMap();
         $(this).css({
            
            "background": "rgba(255, 128, 142,0.5)"
         }).siblings().css({
            
             "background": "#47506c"
         });
         
       });
      /*$(".guPan").click(function(event) {
    	  num = $(this).index();
          $(".guPan").eq(num).children().css("background","#23b7e5").parent().siblings().children().css("background","#d2d2d2")
          
          if(num == 0){
        	  $(".everyCon").eq(num).show().siblings('.everyCon').hide()
        	  $(".jieFang").remove();
        	  guTotal(num);
        	  
        	  
          }else if(num == 1){
        	  $(".everyCon").eq(num).show().siblings('.everyCon').hide()
        	  
        	   
        	  guTotal(num);
          }else if(num == 2){
        	  
        	  $(".everyCon").eq(num).show().siblings('.everyCon').hide()
        	  guTotal(num);
        	  
          }
          
      });*/    
      //顾问排行
      $("#rightHead").click(function(){
    	  num = num + 1;
    	  if(num == 3){
    		  num = 0;
    	  }
    	  guTotal(num);
      })
      $("#leftHead").click(function(){
    	  num = num - 1;
    	  if(num == -1){
    		  num = 2;
    	  }
    	  //console.log(num)
    	  guTotal(num);
      })
       $("#otherRightHead").click(function(){
    	   nn = nn + 1;
    	  if(nn == 2){
    		  nn = 0;
    	  }
    	  shopTotal(num);
      })
      $("#otherLeftHead").click(function(){
    	   nn = nn - 1;
    	  if(nn == -1){
    		  nn = 1;
    	  }
    	  shopTotal(num);
      })
      /*$(".shopPan").click(function(){
    	 
           nn =  $(this).index();
           $(".shopPan").eq(nn).children().css("background","#23b7e5").parent().siblings().children().css("background","#d2d2d2")
          if(nn == 0){
        	  
        	  shopTotal(nn);
        	  
          }else if(nn == 1){
        	 
        	  shopTotal(nn); 
          }
          
                   
           
      });*/
      
      /*$(".cardPicture").click(function(){
    	 
    	   var carNum = $(this).index();
    	  
    	   $("#cardNumber").val(carNum);
    	  chart();
      })*/
      
      
      
      
      
      
     
   /* $("#timeMoment").change(function(){
    	//alert($("#cardNumber").val())
    	$(".date").html("");
    	//console.log($(".date").html())
    	 //visitor();
    	 chart();
    	 guTotal(num); 	
    	 shopTotal(nn);
    	 thingValue();
    	
    	 
    	 shareMap();
    })*/
  
      
});

//今日备案数
var startTime = '';
var endTime ='';
function getGuideRecordsByToday(){
	 //console.log($(".date").html() +" "+"00:00:00");
	 //console.log($(".date").html() +" "+"23:59:59")
	 $.ajax({
			type:"post",
			url:"getGuideRecordsByToday",
			//async: false,
			data:{startTime:startTime,endTime:endTime},
			success:function(data){
				var data = eval("("+data+")");
				$("#totalBaoBei").html(data.data.haveRecords);
			}
		})
 }
//卡片,折线图,仪表图
function chart(){
	
	$.ajax({
		type:"post",
		url:"get_three_types_data",
		
		data:{anyDate:$(".date").html(),anyTimes:$("#timeMoment").val()},
		success:function(data){

			topCard(data);
			lineCard(data);
			//meterCard(data)
			threePic(data)
		}
	})
}
//顶部卡片数据
function topCard(t){
	//console.log(t)
	$("#totalVisitCount").html(t.totalVisitCount);
	$("#totalSaveCustomerCount").html(t.totalSaveCustomerCount);
	$("#totalEnterCount").html(t.totalEnterCount);
	$("#totalSignCount").html(t.totalSignCount);
}
function threePic(data){
	customerChannel(data);
	jieFang(data);
	chang(data);
}
function customerChannel(data){
	
	
	 var myChartOne = echarts.init(document.getElementById('mainOne'));
     var optionOne = {   		 
    		    tooltip : {
    		    	confine:true,
    		        trigger: 'item',
    		        formatter: "{a} <br/>{b} : {c} ({d}%)"
    		    },
    		    legend: {
    		       bottom:'5',
    		        left: 'center',
    		        data: ['新客户通道','老客户通道']
    		    },
    		    color:["#8a98e7","#f3857a"],
    		    series : [
    		        {    		            
    		            type: 'pie',
    		            radius : '50%',
    		            center: ['50%', '50%'],
    		            label: {
    		                normal: {
    		                    show: false,
    		                    position: 'center'
    		                },
    		               
    		            },
    		            data:[
    		                {value:data.newCusVisitCount, name:'新客户通道'},
    		                {value:data.oldCusVistCount, name:'老客户通道'}    		               
    		            ],
    		           /* itemStyle: {
    		                emphasis: {
    		                    shadowBlur: 10,
    		                    shadowOffsetX: 0,
    		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
    		                }
    		            }*/
    		        }
    		    ]
   };
     myChartOne.setOption(optionOne);
}
function jieFang(data){
	
	 var myChartTwo = echarts.init(document.getElementById('mainTwo'));
     var optionTwo = {
    		 tooltip: {
    			 	confine:true,
    		        trigger: 'item',
    		        formatter: "{a} <br/>{b}: {c} ({d}%)"
    		    },
    		    legend: {
     		       bottom:'5',
     		        left: 'center',
     		        data: ['有效接访','无效接访']
     		    },
    		    color:["#99d97c","#49aaef"],
    		    series: [
    		        {
    		           
    		            type:'pie',
    		            radius : '50%',
    		            radius: ['40%', '50%'],
    		            label: {
    		                normal: {
    		                    show: false,
    		                    position: 'center'
    		                },
    		               
    		            },
    		            //avoidLabelOverlap: false,
    		            /*
    		            label: {
    		                
    		                emphasis: {
    		                    show: true,
    		                    textStyle: {
    		                        fontSize: '20',
    		                        
    		                    }
    		                }
    		            },*/
    		            
    		            data:[
    		                {value:data.valideVisitCount, name:'有效接访'},
    		                
    		                {value:data.unValideVisitCount, name:'无效接访'}
    		            ]
    		        }
    		    ]
   };
     myChartTwo.setOption(optionTwo);
}
function chang(data){
	
	
	 var myChartThree = echarts.init(document.getElementById('mainThree'));
    var optionThree = {
   		 
   		    tooltip : {
   		    	confine:true,
   		        trigger: 'item',
   		        formatter: "{a} <br/>{b} : {c} ({d}%)"
   		    },
   		    legend: {
		       bottom:'5',
		        left: 'center',
		        data: ['外场','内场']
		    },
   		    color:["#9675ce","#01c0c8"],
   		    series : [
   		        {
   		            
   		            type: 'pie',
   		            radius : '50%',
   		            center: ['50%', '50%'],
   		         label: {
   	                normal: {
   	                    show: false,
   	                    position: 'center'
   	                },
   	               
   	            },
   		            data:[
   		                {value:data.newCusAppointVisitRate, name:'外场'},
   		                {value:data.inSideVisitCount, name:'内场'}
   		               
   		            ],
   		           /* itemStyle: {
   		                emphasis: {
   		                    shadowBlur: 10,
   		                    shadowOffsetX: 0,
   		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
   		                }
   		            }*/
   		        }
   		    ]
  };
    myChartThree.setOption(optionThree);
}

//仪表图
/*function meterCard(data){
	if($("#cardNumber").val() == "" || $("#cardNumber").val() == 0){
		$(".mainTwo").show();
		$(".mainThree").show();
		$(".mainFourth").show();
		$(".mainFive").show();
		$(".mainOne").css("margin-left","15px")
		oneRate(data);
		twoRate(data);
		threeRate(data);
		fourRate(data);
		fiveRate(data);
	}else if($("#cardNumber").val() == 1){
		$(".mainOne").show();
		$(".mainTwo").hide();
		$(".mainThree").hide();
		$(".mainFourth").hide();
		$(".mainFive").hide();
		$(".mainOne").css("margin-left","320px");
		oneRate(data);
	}else if($("#cardNumber").val() == 2){
		$(".mainOne").show();
		$(".mainTwo").show();
		$(".mainThree").show();
		$(".mainFourth").hide();
		$(".mainFive").hide();
		$(".mainOne").css("margin-left","160px");
		oneRate(data);
		twoRate(data);
		threeRate(data);
	}else if($("#cardNumber").val() == 3){
		$(".mainOne").show();
		$(".mainTwo").show();
		$(".mainThree").show();
		$(".mainFourth").hide();
		$(".mainFive").hide();
		$(".mainOne").css("margin-left","160px");
		oneRate(data);
		twoRate(data);
		threeRate(data);
	}
}*/
//老客户通道有效接访率
/*function oneRate(data){
	var valueRate; 
	var nameRate;
	if($("#cardNumber").val() == "" || $("#cardNumber").val() == 0){
		valueRate = data.oldCusVlidVistRate;
		nameRate = data.oldCusVlidVistRateName
	}else if($("#cardNumber").val() == 1){
		valueRate = data.comeBackRate;
		nameRate = data.comeBackRateName;
	}else if($("#cardNumber").val() == 2){
		valueRate = data.totalEnterBuyRate;
		nameRate = data.totalEnterBuyRateName;
	}else if($("#cardNumber").val() == 3){
		valueRate = data.comeDeailRate;
		nameRate = data.comeDeailRateName;
	}
	   var myChartOne = echarts.init(document.getElementById('mainOne'));
       var optionOne = {
           tooltip : {
               bottom:0,
               formatter: "{a} <br/>{b} : {c}%"
           },  
           series: [
               {
                   name: '业务指标',
                   type: 'gauge',
                   radius: '95%',
                  
                   axisLine: { // 坐标轴线
                       lineStyle: { // 属性lineStyle控制线条样式
                           color: [
                               
                               [1, '#46c1de'],
                               [1, '#46c1de']
                           ],
                           width: 1,                          
                       }
                   },
                     splitLine: { // 分隔线
                       length: 8, // 属性length控制线长
                       lineStyle: { // 属性lineStyle（详见lineStyle）控制线条样式
                           width: 1,
                           color: '#46c1de',      
                       },
                       
                   },
                  title: {
                       show: true,
                       offsetCenter:[0,"120%"],
                        textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                            fontSize: 12,
                           color: '#616b88'
                       }
                   },
                   startAngle: 314.99,
                   splitNumber: 10,
                   detail: {
                       formatter:'{value}%',
                        textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                            fontSize: 12,
                           color: '#46c1de'
                       }
                   },
                   data: [{value:valueRate, name:nameRate}]
               }
           ]
     };
       myChartOne.setOption(optionOne);
}
//外场导客比
function twoRate(data){
	var valueRate; 
	var nameRate;
	if($("#cardNumber").val() == "" || $("#cardNumber").val() == 0){
		valueRate = data.newCusAppointVisitRate;
		nameRate = data.newCusAppointVisitRateName;
	}else if($("#cardNumber").val() == 2){
		valueRate = data.newCusEnterBuyRate;
		nameRate = data.newCusEnterBuyRateName;
	}else if($("#cardNumber").val() == 3){
		valueRate = data.deailSaveCusRate;
		nameRate = data.deailSaveCusRateName;
	}
	 var myChartFive = echarts.init(document.getElementById('mainFive'));
     var optionFive = {

         tooltip : {
             bottom:0,
             formatter: "{a} <br/>{b} : {c}%"
         },
  
         series: [
             {
                 name: '业务指标',
                 type: 'gauge',
                 radius: '95%',

                 axisLine: { // 坐标轴线
                     lineStyle: { // 属性lineStyle控制线条样式
                         color: [
                             
                             [1, '#f9d21a'],
                             [1, '#f9d21a']
                         ],
                         width: 1,                         
                     }
                 },
                   splitLine: { // 分隔线
                     length: 8, // 属性length控制线长
                     lineStyle: { // 属性lineStyle（详见lineStyle）控制线条样式
                         width: 1,
                         color: '#f9d21a', 

                     }
                 },
                title: {
                     show: true,
                     offsetCenter:[0,"120%"],
                      textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                          fontSize: 12,
                         color: '#616b88'
                     }
                 },
                 startAngle: 314.99,
                 splitNumber: 10,
                 detail: {
                     formatter:'{value}%',
                      textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                          fontSize: 12,
                         color: '#f9d21a'
                     }
                 },
                 data: [{value:valueRate, name:nameRate}]
             }
         ]
   };
     myChartFive.setOption(optionFive);
}
//老客接访占比
function threeRate(data){
	var valueRate; 
	var nameRate;
	if($("#cardNumber").val() == "" || $("#cardNumber").val() == 0){
		valueRate = data.oldCusRate;
		nameRate = data.oldCusRateName;
	}else if($("#cardNumber").val() == 2){
		valueRate = data.oldCusEnterBuyRate;
		nameRate = data.oldCusEnterBuyRateName;
	}else if($("#cardNumber").val() == 3){
		valueRate = data.enterSignRate;
		nameRate = data.enterSignRateName;
	}
    var myChartTwo = echarts.init(document.getElementById('mainTwo'));
    var optionTwo = {

        tooltip : {
            bottom:0,
            formatter: "{a} <br/>{b} : {c}%"
        },
 
        series: [
            {
                name: '业务指标',
                type: 'gauge',
                radius: '95%',

                axisLine: { // 坐标轴线
                    lineStyle: { // 属性lineStyle控制线条样式
                        color: [
                            
                            [1, '#fea176'],
                            [1, '#fea176']
                        ],
                        width: 1,                         
                    }
                },
                  splitLine: { // 分隔线
                    length: 8, // 属性length控制线长
                    lineStyle: { // 属性lineStyle（详见lineStyle）控制线条样式
                        width: 1,
                        color: '#fea176',      
                    }
                },
               title: {
                    show: true,
                    offsetCenter:[0,"120%"],
                     textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                         fontSize: 12,
                        color: '#616b88'
                    }
                },
                startAngle: 314.99,
                splitNumber: 10,
                detail: {
                    formatter:'{value}%',
                     textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                         fontSize: 12,
                        color: '#fea176'
                    }
                },
                data: [{value:valueRate, name:nameRate}]
            }
        ]
  };
    myChartTwo.setOption(optionTwo);
}
//指定接访率
function fourRate(data){

    var myChartThree = echarts.init(document.getElementById('mainThree'));
    var optionThree = {

        tooltip : {
            bottom:0,
            formatter: "{a} <br/>{b} : {c}%"
        },
 
        series: [
            {
                name: '业务指标',
                type: 'gauge',
                radius: '95%',

                axisLine: { // 坐标轴线
                    lineStyle: { // 属性lineStyle控制线条样式
                        color: [
                            
                            [1, '#9fd269'],
                            [1, '#9fd269']
                        ],
                        width: 1,                         
                    }
                },
                  splitLine: { // 分隔线
                    length: 8, // 属性length控制线长
                    lineStyle: { // 属性lineStyle（详见lineStyle）控制线条样式
                        width: 1,
                        color: '#9fd269',      
                    }
                },
               title: {
                    show: true,
                    offsetCenter:[0,"120%"],
                     textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                         fontSize: 12,
                        color: '#616b88'
                    }
                },
                startAngle: 314.99,
                splitNumber: 10,
                detail: {
                    formatter:'{value}%',
                     textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                         fontSize: 12,
                        color: '#9fd269'
                    }
                },
                data: [{value:data.apointAgentRate, name:data.apointAgentRateName}]
            }
        ]
  };
    myChartThree.setOption(optionThree);
   
}
//老客户通道指定接访率
function fiveRate(data){
	var myChartFourth = echarts.init(document.getElementById('mainFourth'));
    var optionFourth = {

        tooltip : {
            bottom:0,
            formatter: "{a} <br/>{b} : {c}%"
        },
 
        series: [
            {
                name: '业务指标',
                type: 'gauge',
                radius: '95%',

                axisLine: { // 坐标轴线
                    lineStyle: { // 属性lineStyle控制线条样式
                        color: [
                            
                            [1, '#e57c7c'],
                            [1, '#e57c7c']
                        ],
                        width: 1,                         
                    }
                },
                  splitLine: { // 分隔线
                    length: 8, // 属性length控制线长
                    lineStyle: { // 属性lineStyle（详见lineStyle）控制线条样式
                        width: 1,
                        color: '#e57c7c',      
                    }
                },
               title: {
                    show: true,
                    offsetCenter:[0,"120%"],
                     textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                         fontSize: 12,
                        color: '#616b88',

                    }
                },
                startAngle: 314.99,
                splitNumber: 10,
                detail: {
                    formatter:'{value}%',
                     textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                         fontSize: 12,
                        color: '#e57c7c'
                    }
                },
                data: [{value:data.oldCusAppointVisitRate, name:data.oldCusAppointVisitRateName}]
            }
        ]
  };
    myChartFourth.setOption(optionFourth);
   

}*/
//折线图
function lineCard(data){
//console.log(data);
	var array = [];
	var newArray= [];
	/*if($("#cardNumber").val() == "" ||  $("#cardNumber").val() == 0 ){
		$("#topLine").html("接访")*/
		for(var i=0;i<data.x_axis.length;i++){
			//console.log(data.visitLine)
			newArray.push(data.visitLine[data.x_axis[i]].count)
			array.push(data.x_axis[i])
			
		}
		/*console.log(newArray)
		console.log(Math.max.apply(null, newArray))*/
	/*}else if($("#cardNumber").val() == 1){
		$("#topLine").html("储客")
		for(var i=0;i<data.x_axis.length;i++){
			//console.log(data.visitLine[data.x_axis[i]].count)
			newArray.push(data.saveCustomerLine[data.x_axis[i]].count)
			array.push(data.x_axis[i])
			
		}
		
	}else if($("#cardNumber").val() == 2){
		$("#topLine").html("下订")
		for(var i=0;i<data.x_axis.length;i++){
			//console.log(data.visitLine[data.x_axis[i]].count)
			newArray.push(data.enterBuyLine[data.x_axis[i]].enterBuyCount)
			array.push(data.x_axis[i])
			
		}
	}else if($("#cardNumber").val() == 3){
		$("#topLine").html("签约")
		for(var i=0;i<data.x_axis.length;i++){
			//console.log(data.visitLine[data.x_axis[i]].count)
			newArray.push(data.signLine[data.x_axis[i]].signCount)
			array.push(data.x_axis[i])
			
		}
	}*/
	//console.log(data)
	//console.log(newArray)
	
    var myChart = echarts.init(document.getElementById('main'));
	myChart.setOption({
		   tooltip: { 
			   show:true,
		        trigger : 'item',  
		          
		        showDelay: 0,  
		        hideDelay: 0,  
		        transitionDuration:0,   
		        padding: 10,   
		             formatter: function (params,ticket,callback) {
		            	 var time = data.x_axis[params.dataIndex]
		             
		            	 /*if($("#cardNumber").val() == "" || $("#cardNumber").val() == 0){*/
		            		/* console.log(params.dataIndex)*/
		            			 var count =  data.visitLine[time].count;
		            			 var vaild =  data.visitLine[time].vaild;
		            			 var vaildRate =  data.visitLine[time].vaildRate;
		            			 var oldCusWayCount =  data.visitLine[time].oldCusWayCount;
		            			 var averageSeconds =  data.visitLine[time].averageSeconds;
/*		            			 var res ="接访总数"+':'+count+'<br/>'+"有效接访"+':'+vaild+'<br/>'+"有效接访率"+':'+vaildRate+'<br/>'+"老客户通道接访"+':'+oldCusWayCount+'<br/>'+"接访平均时长"+':'+averageSeconds;  
*/		            			 var res ="接访次数"+':'+count;  

		            			 return res; 
		            	 /*}else if($("#cardNumber").val() == 1){
			            	 var count = data.saveCustomerLine[time].count;
			            	 var res = "新增总储客数"+':'+count;
			            	 return res; 
			             }else if($("#cardNumber").val() == 2){
			            	 var enterBuyCount =  data.enterBuyLine[time].enterBuyCount;
	            			 var havePayCount =  data.enterBuyLine[time].havePayCount;
	            			 var totalBuyPrice =  data.enterBuyLine[time].totalBuyPrice;
	            			 var res = "总认购客户数"+':'+enterBuyCount+'<br/>'+"已下定"+':'+havePayCount+'<br/>'+"锁定房源货值"+':'+totalBuyPrice;
	            			 return res; 
			             }else if($("#cardNumber").val() == 3){
			            	 var signCount =  data.signLine[time].signCount;
	            			 var signPrice =  data.signLine[time].signPrice;
	            			 var giveUpSignCount =  data.signLine[time].giveUpSignCount;
	            			 var waitSignCount =  data.signLine[time].waitSignCount;
	            			 var res = "已签约数"+':'+signCount+'<br/>'+"已签约房源货值"+':'+signPrice+'<br/>'+"放弃签约数"+':'+giveUpSignCount+'<br/>'+"待签约数"+':'+waitSignCount;
	            			 return res;
			             }*/
		                  
		             }
		    },	   
		    xAxis: [
		        {
		            type: 'category',
		            
                	min : 'dataMin',
                	max : "dataMax",
                	interval : 60,
                	axisLabel : {interval : 'auto',formatter: function (value, index) {
                	    // 格式化成月/日，只在第一个刻度显示年份
                		
                	    var date = new Date(value);
                	     
                	    
                	    
                	  /* if($("#timeMoment").val() == ""){*/
                		   var texts = [date.getHours()+':00'];
                		   return texts;
                	  /* }else{
                		   var texts = [(date.getMonth() + 1), date.getDate()];
                		   return texts.join('.');
                	   }*/
                	    
                	   
                	    
                	}},
                    
                    silent: false,
                    axisLine: {onZero: true},
                    splitLine: {show: false},
                    
		            boundaryGap: false,
		            data: array,
		           
		        }
		    ],
		    yAxis:{
		    	
		    	 
		    },
		    series: [		       	       
		        {
		            //name:'接访人数',
		            type:'line',
		            
		           itemStyle:{
		                normal:{
		                    lineStyle:{
		                        color:"#fe8a52"
		                    }
		                }
		           },
				    areaStyle: {normal: {color:"#fe8a52"}},
		            data:newArray
		        }
		    ]
		});
	
		
}

//顾问总数据
function guTotal(num){
	 var agentOrderSign;
	
		    if(num == 0){
		    	agentOrderSign = "visit";  
		    	
		    }else if(num == 1){
		    	agentOrderSign = "enterBuy";
		    	//console.log(agentOrderSign)
		    }else if(num == 2){
		  	 
		    	agentOrderSign = "sign"
		    }
	 //alert(agentOrderSign)
	$.ajax({
		
		type:"post",
		url:"get_three_order_data",
		
		data:{anyDate:$(".date").html(),agentOrderSign:agentOrderSign,anyTimes:$("#timeMoment").val(),shopOrderSign:""},
		success:function(data){
			//console.log(agentOrderSign)
			//console.log(data)
			if(agentOrderSign == "visit"){
				visitor(data);
			}else if(agentOrderSign == "enterBuy"){
				enterBuy(data);
			}else if(agentOrderSign == "sign"){
				sign(data);
			}
			
			
			
			}
		})
}
//接访
function visitor(data){
	//console.log(data.angentList)
	$(".jieFang").remove();
	$(".renGou").remove();
	$(".qianyue").remove();    
	var s ="";
	  s="<div class='jieFang everyCon sameHeight' >";
			$.each(data.angentList,function(o,v){
				o++;
				s+="<div class='row peopleNum'>";
				s+="<div class='col-md-5' style='width:70%;'><div class='row'>";
				s+= "<div class='col-md-1' style='margin-top:8px'><span >"+o+"</span></div>"
				if(v.photo == '' || v.photo == null){
					s+="<div class='col-md-4'><img src='static/images/timg.png' class='img-circle  cycle'></div>";
				}else{
					s+="<div class='col-md-4'><img src='"+v.photo+"' class='img-circle  cycle'></div>";
				}				
				s+="<div class='col-md-6' style='margin-top:8px;'><span>"+v.userCaption+"</span></div>"
				s+="</div></div>";
				s+="<div class='col-md-4 ' style='margin-top:8px;text-align:right;width:30%;'><span >接访：</span><span>"+v.num+"</span><span>次</span></div>"
				s+="</div>";
			})
			s+="</div>"
			$(".renGou").remove();
			$(".qianyue").remove();
			$("#gu").append(s);
	
	
}
//认购
function enterBuy(data){
	$(".jieFang").remove();
	$(".renGou").remove();
	$(".qianyue").remove(); 
	var e ="";
	
			e="<div class='renGou everyCon sameHeight'>";
			$.each(data.angentList,function(o,v){
				o++;
				e+="<div class='row peopleNum'>";
				e+="<div class='col-md-5' style='width:70%;'><div class='row'>";
				e+= "<div class='col-md-1' style='margin-top:8px'><span >"+o+"</span></div>"
				if(v.photo == '' || v.photo == null){
					e+="<div class='col-md-4'><img src='static/images/timg.png' class='img-circle  cycle'></div>";
				}else{
					e+="<div class='col-md-4'><img src='"+v.photo+"' class='img-circle  cycle'></div>";
				}	
				e+="<div class='col-md-5' style='margin-top:8px;'><span>"+v.userCaption+"</span></div>";
				e+="</div></div>";
				e+="<div class='col-md-4 ' style='margin-top:8px;text-align:right;width:29.5%;'><span >下定：</span><span>"+v.num+"</span><span>套</span></div>";
				e+="</div>";
			})
			e+="</div>";
			$(".qianyue").remove();
			$(".jieFang").remove();
			$("#gu").append(e);
	
	
}
//签约
function sign(data){
	$(".jieFang").remove();
	$(".renGou").remove();
	$(".qianyue").remove(); 
	var g ="";
	
			g="<div class='qianyue everyCon sameHeight' >";
			$.each(data.angentList,function(o,v){
				o++;
				g+="<div class='row peopleNum'>";
				g+="<div class='col-md-5' style='width:70%;'><div class='row'>";
				g+= "<div class='col-md-1' style='margin-top:8px'><span >"+o+"</span></div>"
				if(v.photo == '' || v.photo == null){
					g+="<div class='col-md-4'><img src='static/images/timg.png' class='img-circle  cycle'></div>";
				}else{
					g+="<div class='col-md-4'><img src='"+v.photo+"' class='img-circle  cycle'></div>";
				}	
				g+="<div class='col-md-6' style='margin-top:8px;'><span>"+v.userCaption+"</span></div>";
				g+="</div></div>";
				g+="<div class='col-md-4 ' style='margin-top:8px;text-align:right;width:29.5%'><span >签约：</span><span>"+v.num+"</span><span>套</span></div>";
				g+="</div>";
			})
			g+="</div>";
			$(".jieFang").remove();
			$(".renGou").remove();
			$("#gu").append(g);
		
}
//门店总数据
function shopTotal(nn){
	var shopOrderSign;
	
    if(nn == 0){
    	shopOrderSign = "guide";  
    	
    }else if(nn == 1){
    	shopOrderSign = "guideVisit";
    	//console.log(agentOrderSign)
    }
	$.ajax({
		type:"post",
		url:"get_three_order_data",
		ansy: false,
		data:{anyDate:$(".date").html(),agentOrderSign:"",anyTimes:$("#timeMoment").val(),shopOrderSign:shopOrderSign},
		success:function(data){
			//console.log(data)
			//reported(data);
			//daoFang(data);
			if(shopOrderSign == "guide"){
				reported(data)
			}else if(shopOrderSign == "guideVisit"){
				daoFang(data);
			}
			
			}
		})
}
//已报备

function  reported(data){
	$(".yiBao").remove();
	$(".baoFang").remove();
	
	var colorArray = ["#f05050","#988fd8","#ffc941","#24c5a4","#1eb9cd","#e9878a","#47506c","#fb9767","#a7c0e0","#c0607b","#d2d2d2"];

	var r = "";
	
			r="<div class='yiBao twoCon sameHeight' >";
			$.each(data.shopList,function(o,v){
			
				r+="<div class='row peopleNum'>";
				r+="<div class='col-md-5' style='width:65%;'><div class='row'>";
				if(o == 0){
					r+="<div class='col-md-2' ><div style='width:18px;height:18px;border-radius:4px;margin-top:10px;background:"+colorArray[0]+"' ></div></div>"
				}else if(o == 1){
					r+="<div class='col-md-2' ><div style='width:18px;height:18px;border-radius:4px;margin-top:10px;background:"+colorArray[1]+"' ></div></div>"
				}else if(o == 2){
					r+="<div class='col-md-2' ><div style='width:18px;height:18px;border-radius:4px;margin-top:10px;background:"+colorArray[2]+"' ></div></div>"
				}else if(o == 3){
					r+="<div class='col-md-2' ><div style='width:18px;height:18px;border-radius:4px;margin-top:10px;background:"+colorArray[3]+"' ></div></div>"
				}else if(o == 4){
					r+="<div class='col-md-2' ><div style='width:18px;height:18px;border-radius:4px;margin-top:10px;background:"+colorArray[4]+"' ></div></div>"
				}else if(o == 5){
					r+="<div class='col-md-2' ><div style='width:18px;height:18px;border-radius:4px;margin-top:10px;background:"+colorArray[5]+"' ></div></div>"
				}else if(o == 6){
					r+="<div class='col-md-2' ><div style='width:18px;height:18px;border-radius:4px;margin-top:10px;background:"+colorArray[6]+"' ></div></div>"
				}else if(o == 7){
					r+="<div class='col-md-2' ><div style='width:18px;height:18px;border-radius:4px;margin-top:10px;background:"+colorArray[7]+"' ></div></div>"
				}else if(o == 8){
					r+="<div class='col-md-2' ><div style='width:18px;height:18px;border-radius:4px;margin-top:10px;background:"+colorArray[8]+"' ></div></div>"
				}else if(o == 9){
					r+="<div class='col-md-2' ><div style='width:18px;height:18px;border-radius:4px;margin-top:10px;background:"+colorArray[9]+"' ></div></div>"
				}else{
					r+="<div class='col-md-2' ><div style='width:18px;height:18px;border-radius:4px;margin-top:10px;background:"+colorArray[10]+"' ></div></div>"

				}
				
				r+="<div class='col-md-10' style='margin-top:8px;'><span>"+v.shopName+"</span></div>";
				r+="</div></div>";
				r+="<div class='col-md-4 ' style='margin-top:8px;width:35%;text-align:right;'><span >已报备：</span><span>"+v.num+"</span><span>组</span></div>";
				r+="</div>";
				
			})
			
			r+="</div>";
			$(".baoFang").remove();
			if(data.shopList.length>0){
				$("#shop").append(r);
			}else{
				$("#shop").append("<div class='yiBao twoCon sameHeight' style='text-align: center;' >暂无数据</div>");
			}
}

//报备已到访
function  daoFang(data){
	$(".yiBao").remove();
	$(".baoFang").remove();
	var colorArray = ["#f05050","#988fd8","#ffc941","#24c5a4","#1eb9cd","#e9878a","#47506c","#fb9767","#a7c0e0","#c0607b","#d2d2d2"];

	var f = "";
	
			f="<div class='baoFang twoCon sameHeight'>";
			$.each(data.shopList,function(o,v){
				f+="<div class='row peopleNum'>";
				f+="<div class='col-md-5' style='width:55%;'><div class='row'>";
				if(o == 0){
					f+="<div class='col-md-2' ><div style='width:18px;height:18px;border-radius:4px;margin-top:10px;background:"+colorArray[0]+"' ></div></div>"
				}else if(o == 1){
					f+="<div class='col-md-2' ><div style='width:18px;height:18px;border-radius:4px;margin-top:10px;background:"+colorArray[1]+"' ></div></div>"
				}else if(o == 2){
					f+="<div class='col-md-2' ><div style='width:18px;height:18px;border-radius:4px;margin-top:10px;background:"+colorArray[2]+"' ></div></div>"
				}else if(o == 3){
					f+="<div class='col-md-2' ><div style='width:18px;height:18px;border-radius:4px;margin-top:10px;background:"+colorArray[3]+"' ></div></div>"
				}else if(o == 4){
					f+="<div class='col-md-2' ><div style='width:18px;height:18px;border-radius:4px;margin-top:10px;background:"+colorArray[4]+"' ></div></div>"
				}else if(o == 5){
					f+="<div class='col-md-2' ><div style='width:18px;height:18px;border-radius:4px;margin-top:10px;background:"+colorArray[5]+"' ></div></div>"
				}else if(o == 6){
					f+="<div class='col-md-2' ><div style='width:18px;height:18px;border-radius:4px;margin-top:10px;background:"+colorArray[6]+"' ></div></div>"
				}else if(o == 7){
					f+="<div class='col-md-2' ><div style='width:18px;height:18px;border-radius:4px;margin-top:10px;background:"+colorArray[7]+"' ></div></div>"
				}else if(o == 8){
					f+="<div class='col-md-2' ><div style='width:18px;height:18px;border-radius:4px;margin-top:10px;background:"+colorArray[8]+"' ></div></div>"
				}else if(o == 9){
					f+="<div class='col-md-2' ><div style='width:18px;height:18px;border-radius:4px;margin-top:10px;background:"+colorArray[9]+"' ></div></div>"
				}else{
					f+="<div class='col-md-2' ><div style='width:18px;height:18px;border-radius:4px;margin-top:10px;background:"+colorArray[10]+"' ></div></div>"

				}
				
				f+="<div class='col-md-10' style='margin-top:8px;'><span>"+v.shopName+"</span></div>";
				f+="</div></div>";
				f+="<div class='col-md-4' style='margin-top:8px;width:45%;text-align:right;'><span >报备已到访：</span><span>"+v.num+"</span><span>组</span></div>";
				f+="</div>";
			})
			f+="</div>";
			$(".yiBao").remove();
			if(data.shopList.length>0){
				$("#shop").append(f);
			}else{
				$("#shop").append("<div class='baoFang twoCon sameHeight' style='text-align: center;' >暂无数据</div>");
			} 
	
}
//货值金额
function thingValue(){
	var t = "";
	$.ajax({
		type:"post",
		url:"get_three_order_data",
		
		data:{anyDate:$(".date").html(),agentOrderSign:"",anyTimes:$("#timeMoment").val(),shopOrderSign:""},
		success:function(data){
			$("#enterBuyClockHouse").html(data.enterBuyClockHouse);
			$("#enterBuyGetMoney").html(data.enterBuyGetMoney);
			$("#sign").html(data.sign);
			$("#waitSign").html(data.waitSign);
			$("#shopsMoney").html(data.shopsMoney);
		}
		
	})
	
}
//分享圈
function shareMap(){
	$.ajax({
		type:"post",
		url:"get_pro_shops_data",
		
		data:{anyDate:$(".date").html(),anyTimes:$("#timeMoment").val()},
		success:function(data){
			//console.log(data)
			mapShop(data);
			
		}
	})
	
}
var map = new AMap.Map('container', {
	resizeEnable: true,
    zoom:12,
    });   
   map.plugin(["AMap.ToolBar"], function() {
        map.addControl(new AMap.ToolBar());
    }); 
    var infoWindow = new AMap.InfoWindow({

    }); 
    var marker;
function mapShop(data){
	
	map.clearMap();
	
	
	//alert(11)
	//var positioner;
	for(var i=0;i<data.length;i++){
		
		/*if(data[i].llNum != null && data[i].llNum != ""){
			positioner = data[i].llNum.split(",")
		}*/
			   marker=new AMap.Marker({
	             position: data[i].llNum.split(","),
	             map:map,
	            
	             icon: new AMap.Icon({
	                 //size: new AMap.Size(20, 30),
	                 
	                 image: "static/images/shopG.png"     //自定义的标记图片样式(图片需自己准备)
	             })
	        })
		
		
		 	content = [];
			content.push("门店名称："+data[i].name);
		  		
		  	
		  	content.push("带看数："+data[i].guideCount);
	  		
		  	content.push("带看到访数："+data[i].guideVisitCount);
			content.push("带看到访率："+data[i].guideVisitRate+"%");
			content.push("地址："+data[i].address);
			content.push("电话："+data[i].phone);
		  	marker.content = content;
		    

		  	//marker.on('dblclick',openAmap);     //这里采用调到新页面方式导航，也可直接定义带导航功能的信息窗体，请参考http://lbs.amap.com/api/javascript-api/example/infowindow/infowindow-has-search-function/
		  //给Marker绑定单击事件
		  	marker.on('mouseover', markerOver);
	}
	map.setFitView();
	
}

//跳至地图当中导航
/*function openAmap(e){
    e.target.markOnAMAP({
        name:e.target.title,
        position:e.target.getPosition()
    })
}*/
//信息窗口
function markerOver(e){
    infoWindow.setContent(e.target.content.join('<br/>'));
    infoWindow.open(map, e.target.getPosition());
}