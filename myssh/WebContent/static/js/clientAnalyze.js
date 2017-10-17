/*$(document).ready(function(){
	var a = '${analyze.histogram}';
	console.log(a);
	
})*/
/*function customerAnalyze(){
	$.ajax({
		type:"post",
		url:"customerAnalyze",
		async:true,
		//data:{num:"8"},
		success:function(data){
			
			//clientTags(data)
			console.log(data)
		}
	})
}*/
       /*var myAnalyze = echarts.init(document.getElementById('analyzePic'));
        var option = {           
               
                color: ['#b0e2ef'],
                    tooltip : {
                        trigger: 'axis',
                       
                    },
                   grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis : [
                        {
                            name:"天",
                            type : 'category',
                            data : ['1天以内','1天', '2天', '3天', '4天', '5天', '6天', '7天','8天', '9天', '10天', '11天', '12天', '13天', '14天','15天', '16天', '17天', '18天', '19天', '20天', '21天','22天', '23天', '24天', '25天', '26天', '27天', '28天','29天','30天以上'],
                            axisTick: {
                            alignWithLabel: false
                            },

                        }
                    ],
                    yAxis : [
                        {
                            type : 'value',
                            name:"人数/人"
                        }
                    ],
                    series : [
                        {
                            name:'直接访问',
                            type:'bar',
                            barWidth: '60%',
                            data:[2,10, 52, 200, 334, 390, 330, 220,10, 52, 200, 334, 390, 330, 220, 220,10, 52, 200, 334, 390, 330, 220, 220,10, 52, 200, 334, 390, 330, 220]
                        }
                    ]
               };
     myAnalyze.setOption(option);

   </script>
    <script type="text/javascript">
       var myShan = echarts.init(document.getElementById('shanPic'));
        var optionTwo = {           
               color: ['#24b6e4','#f9d21a','#7266ba','#9fd269'],
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
    
    series : [
        {
            name: '访问来源',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                {value:335, name:'第一周'},
                {value:310, name:'第二周'},
                {value:234, name:'第三周'},
                {value:135, name:'其他'},
                
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
     myShan.setOption(optionTwo);
*/