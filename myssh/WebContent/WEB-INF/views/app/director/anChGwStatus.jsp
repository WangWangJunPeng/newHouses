<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta content="yes" name="apple-mobile-web-app-capable">
        <meta content="yes" name="apple-touch-fullscreen">
        <meta content="telephone=no,email=no" name="format-detection">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
        <title>案场顾问状态</title>
        <link rel="stylesheet" href="static/frozenui/css/frozen.css">
        <link rel="stylesheet" type="text/css" href="static/css/anchangSuitable.css">
        <link rel="stylesheet" type="text/css" href="static/css/anChGwStatus.css">
        <script type="text/javascript" src="static/js/iconfont.js"></script>
        <script type="text/javascript" src="static/js/echarts.min.js"></script>
        <script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="static/js/anChGwStatus.js"></script>
    </head>
    <body ontouchstart>
         <header class="ui-header ui-header-positive ui-border-b">
            <i class="ui-icon-return" onclick="history.back()"></i><h1>案场顾问状态</h1>
        </header>
        <section class="ui-container">
            <div id="main"></div>
            
<div class="ui-tab" id="tab1">
    <ul class="ui-tab-nav ui-border-b">
        <li class="current">按顾问</li>
        <li>按接访时长</li>
        <li>按到访时间</li>
    </ul>
    <ul class="ui-tab-content" style="width:300%">
        <li>
            <div class="list" id="agent">
                <!-- <table>
                    <tbody>
                        <tr>
                            <td rowspan="2">
                                <img src="static/images/touPic.png" alt="">
                                <p class="nameCss">张三</p>
                                <p class="xinxiCss">接访中</p>
                            </td>
                            <td>
                                <p class="line1">来访时间</p>
                                <p class="line2">9:00</p>
                            </td>
                            <td>
                                <p class="line1">接访时长</p>
                                <p class="line2">暂无</p>
                            </td>
                            <td>
                                <p class="line1">来访人数</p>
                                <p class="line2">2人</p>
                            </td>
                        </tr>
                        <tr>
                            
                            <td>
                                <p class="line1">客户通道</p>
                                <p class="line2">新客户</p>
                            </td>
                            <td>
                                <p class="line1">是否指定接访</p>
                                <p class="line2">否</p>
                            </td>
                            <td>
                                <p class="line1">是否登记</p>
                                <p class="line2">未登记</p>
                            </td>
                        </tr>
                    </tbody>
                </table> -->
            </div>
        </li>
        <li>
            <div class="list" id="timeLonger">
                <!-- <table>
                    <tbody>
                        <tr>
                            <td rowspan="2">
                                <img src="static/images/touPic.png" alt="">
                                <p class="nameCss">张三</p>
                                <p class="xinxiCss">接访中</p>
                            </td>
                            <td>
                                <p class="line1">来访时间</p>
                                <p class="line2">9:00</p>
                            </td>
                            <td>
                                <p class="line1">接访时长</p>
                                <p class="line2">暂无</p>
                            </td>
                            <td>
                                <p class="line1">来访人数</p>
                                <p class="line2">2人</p>
                            </td>
                        </tr>
                        <tr>
                            
                            <td>
                                <p class="line1">客户通道</p>
                                <p class="line2">新客户</p>
                            </td>
                            <td>
                                <p class="line1">是否指定接访</p>
                                <p class="line2">否</p>
                            </td>
                            <td>
                                <p class="line1">是否登记</p>
                                <p class="line2">未登记</p>
                            </td>
                        </tr>
                    </tbody>
                </table> -->
            </div>
        </li>
     	<li>
            <div class="list" id="receptTime">
             <!--    <table>
                    <tbody>
                        <tr>
                            <td rowspan="2">
                                <img src="static/images/touPic.png" alt="">
                                <p class="nameCss">张三</p>
                                <p class="xinxiCss">接访中</p>
                            </td>
                            <td>
                                <p class="line1">来访时间</p>
                                <p class="line2">9:00</p>
                            </td>
                            <td>
                                <p class="line1">接访时长</p>
                                <p class="line2">暂无</p>
                            </td>
                            <td>
                                <p class="line1">来访人数</p>
                                <p class="line2">2人</p>
                            </td>
                        </tr>
                        <tr>
                            
                            <td>
                                <p class="line1">客户通道</p>
                                <p class="line2">新客户</p>
                            </td>
                            <td>
                                <p class="line1">是否指定接访</p>
                                <p class="line2">否</p>
                            </td>
                            <td>
                                <p class="line1">是否登记</p>
                                <p class="line2">未登记</p>
                            </td>
                        </tr>
                    </tbody>
                </table> -->
            </div>
        </li>
    </ul>
</div>        
        </section>
        <input id="orderKey" type="hidden">
        <script src="static/frozenui/lib/zepto.min.js"></script>
        <script src="static/frozenui/js/frozen.js"></script>
        <script>
        function heatMap(){  
            /*选项卡*/ 
          
			var tab = new fz.Scroll('.ui-tab', {
                role: 'tab',
                autoplay: false
            });
            /* 滑动结束 */
             tab.on('scrollEnd', function(curPage) {
                // curPage 当前页
            	$("#orderKey").val(curPage);
            	getAgentsData();
             });
            /*echarts图表*/
            var myChart = echarts.init(document.getElementById('main'));
            option = {
    title: {
        text: '今日案场热力图',
        textStyle:{
            fontSize:'12',
            fontWeight:'200',
            color:'#fff'
        }
    },
    tooltip: {
        trigger: 'axis'
    },
    backgroundColor:'#508bef',    
    grid: {
        left: '0',
        right: '8%',
        bottom: '8%',
        containLabel: true
    },
    color:['#6ffdda'],
    xAxis: {
        type: 'category',
        splitNumber:0,
        boundaryGap: false,
        splitLine:{
            lineStyle:{
                color:'#6a9bef'
            },
            show:true
        },
        axisLine:{
            lineStyle:{
                color:'#6a9bef',
                opacity:'1'
            }
        },
        axisLabel:{
            textStyle:{
                color:"#b2c8f7"
            }
        },
        axisTick:{
            show:false
        },
        data: ['8:00','','10:00','','12:00','','14:00','','16:00','','18:00','','20:00']
    },
    yAxis: {
        axisLine:{
            lineStyle:{
                color:'#6a9bef'
            }
        },
        axisTick:{
            show:false
        },
        axisLabel:{
            show:false
        },
        splitLine:{
            show:false
        },
        splitNumber:0,
        type: 'value'
    },
    series: [
        {
            name:'今日到访数',
            type:'line',
            stack: '总量',
            data:todayDataArr
        },
        {
            name:'昨日到访数',
            type:'line',
            stack: '总量',
            data:yestodayData
        }
    ]
};

            myChart.setOption(option);

/*获得数据形成图表*/
/*$.get('data.json').done(function (data) {
    // 填入数据
    myChart.setOption({

        series: [{
            // 根据名字对应到相应的系列
            name:'访问来源',
            data: data.data
        }]
    });
});*/

        }
        </script>
    </body>
</html>