<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
  <title>当日业务一览</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
  <link href="static/css/bootstrap-3.3.5-dist/css/bootstrap.min.css" title="" rel="stylesheet" />
  <link rel="stylesheet" href="static/layuiTwo/layui/css/layui.css">
  <link rel="stylesheet" href="static/css/default.css">
  <link rel="stylesheet" href="static/css/business.css">
  <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
  <script src="http://root-1252955170.cossh.myqcloud.com/static/fonts/MSYH.TTF"></script>
  <script src="http://root-1252955170.cossh.myqcloud.com/static/fonts/MSYHBD.TTF"></script>
  <script src="http://root-1252955170.cossh.myqcloud.com/static/fonts/MSYHL.TTC"></script>
  <script src="static/css/bootstrap-3.3.5-dist/js/bootstrap.min.js" type="text/javascript"></script>
  <script src="static/layuiTwo/layui/layui.js" type="text/javascript"></script>
  <script src="static/js/time.js" type="text/javascript"></script>
  <script type="text/javascript">
  
	  $(document).ready(function(){
		  var date = getDay(1);
		  $('.time').attr('placeholder',date);
			getAgentWorkInfo(date);
			getVisitList(date);
		});
  function getAgentWorkInfo(date){
	  $.ajax({
   		  type:"post",
   		  async : false,
   		  url:"today_work_agent",
   		  data:{proId:$("#projectId").val(),
   			dataStr : date,
   		  },
   	   	 success:function(data){
   	   	 	getAgentDayWorkShow(data);
   	   	 },
   	   });
  }
  function getAgentDayWorkShow(data){
	  $('#workAgentNum').html("");
	  $('#agentWorkShow').html("");
	  var num = 0;
	  var s = '<tbody><tr><th>置业顾问</th><th>签到时间</th><th>签退时间</th></tr>';
	  $.each(data,function(v,o){
		  if (o.arriveTime != null && o.arriveTime != ""){
			  num ++;
			  var time = '';
			  time = o.arriveTime.substring(11);
		  }
		  if (o.name != null && o.name != "") {
			 s += '<tr><td>'+o.name+'</td>';
		  }else {
			 s += '<tr><td>暂无</td>';
		  }
         s += '<td>'+time+'</td>';
         if (o.leaveTime != null && o.leaveTime != ""){
	         s += '<td>'+o.leaveTime+'</td></tr>'; 
         }else {
	         s += '<td>暂无</td></tr></tbody>'; 
         }	
	  });
	  
	  if(data.length>0){
		  $('#workAgentNum').html(data.length);
		  $('#agentWorkShow').html(s);
	  }else {
		  $('#workAgentNum').html(0);
	  }
  }
  function getVisitList(date){
	  $.ajax({
   		  type:"post",
   		  async : false,
   		  url:"today_visit_info",
   		  data:{proId:$("#projectId").val(),
   			dataStr : date,
   		  },
   	   	 success:function(data){
   	   	 	getAgentDayVisitShow(data);
   	   	 },
   	   });
  }
  function getAgentDayVisitShow(data){
	  $('#agentVisitShow').html("");
	  $('#visitNum').html("");
	  var s = '<tbody><tr><th>到访时间</th><th>人数</th><th>客户电话</th><th>实际接访</th><th>送别时间</th></tr>';
	  $.each(data,function(v,o){
		  if (o.arriveTime != null && o.arriveTime != ""){
			  var time1 = '';
			  time1 = o.arriveTime.substring(11);
		  }
		 s += '<tr><td>'+time1+'</td>';
         s += '<td>'+o.visitCount+'</td>';
         if (o.phone != null && o.phone != ""){
	         s += '<td>'+o.phone+'</td>';
         }else {
	         s += '<td>暂无</td>';
         }
         if (o.name != null && o.name != "") {
	         s += '<td>'+o.name+'</td>';
         }else {
	         s += '<td>暂无</td>';
         }
         if (o.leaveTime != null && o.leaveTime != ""){
			var time2 = '';
			time2 = o.leaveTime.substring(11);
	         s += '<td>'+time2+'</td>';
         }else {
	         s += '<td>暂无</td>';
         }
         s += '</tr></tbody>';
	  });
	  if(data.length>0){
		  $('#agentVisitShow').html(s);
		  $('#visitNum').html(data.length);
	  }else {
		  $('#visitNum').html(0);
	  }
  }
  </script>
</head>
<body>
  <div class="container-fluid">
    <div class="topT">
      <p class="centerP">
        当日业务一览
      </p>
      <input class="time layui-input" onclick="layui.laydate({elem: this, istime: true, choose: function(dates){ getAgentWorkInfo(dates);getVisitList(dates);}, format: 'YYYY年MM月DD日'})">
     
    </div>
    <div class="content_buss">
      <div class="leftbox pull-left">
      <input type="hidden" value="${proId }" id="projectId">
        <p class="centerP arrive">到岗人数  <span id="workAgentNum"></span>
        <img class="print" src="static/images/print.png" onclick="method1('agentWorkShow')" alt="">
        </p>
        <table class="tablelist" id="agentWorkShow">

        </table>
      </div>
      <div class="rightbox pull-left">
        <p class="centerP">接访批次  <span id="visitNum"></span>
        <img class="print" src="static/images/print.png" onclick="method1('agentVisitShow')" alt="">
        </p>
        <table class="tablelist" id="agentVisitShow">
         
        </table>
      </div>
      <div class="clearfix"></div>
    </div>
    <button class="close_buss" onclick="closeBack()">关闭</button>
  </div>

<script src="static/layuiTwo/layui/layui.js" type="text/javascript"></script>
<script>
	layui.use('laydate', function(){
  		var laydate = layui.laydate;
	});

	function closeBack() {
		window.history.go(-1);
	}
	
	
</script>
<script>
  
        var idTmr;
        function  getExplorer() {
            var explorer = window.navigator.userAgent ;
            //ie 
            if (explorer.indexOf("MSIE") >= 0) {
                return 'ie';
            }
            //firefox 
            else if (explorer.indexOf("Firefox") >= 0) {
                return 'Firefox';
            }
            //Chrome
            else if(explorer.indexOf("Chrome") >= 0){
                return 'Chrome';
            }
            //Opera
            else if(explorer.indexOf("Opera") >= 0){
                return 'Opera';
            }
            //Safari
            else if(explorer.indexOf("Safari") >= 0){
                return 'Safari';
            }
        }
        function method1(tableid) {//整个表格拷贝到EXCEL中
            if(getExplorer()=='ie')
            {
                var curTbl = document.getElementById(tableid);
                var oXL = new ActiveXObject("Excel.Application");

                //创建AX对象excel 
                var oWB = oXL.Workbooks.Add();
                //获取workbook对象 
                var xlsheet = oWB.Worksheets(1);
                //激活当前sheet 
                var sel = document.body.createTextRange();
                sel.moveToElementText(curTbl);
                //把表格中的内容移到TextRange中 
                sel.select;
                //全选TextRange中内容 
                sel.execCommand("Copy");
                //复制TextRange中内容  
                xlsheet.Paste();
                //粘贴到活动的EXCEL中       
                oXL.Visible = true;
                //设置excel可见属性

                try {
                    var fname = oXL.Application.GetSaveAsFilename("Excel.xls", "Excel Spreadsheets (*.xls), *.xls");
                } catch (e) {
                    print("Nested catch caught " + e);
                } finally {
                    oWB.SaveAs(fname);

                    oWB.Close(savechanges = false);
                    //xls.visible = false;
                    oXL.Quit();
                    oXL = null;
                    //结束excel进程，退出完成
                    //window.setInterval("Cleanup();",1);
                    idTmr = window.setInterval("Cleanup();", 1);

                }

            }
            else
            {
                tableToExcel(tableid)
            }
        }
        function Cleanup() {
            window.clearInterval(idTmr);
            CollectGarbage();
        }
        var tableToExcel = (function() {
              var uri = 'data:application/vnd.ms-excel;base64,',
              template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>',
                base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) },
                format = function(s, c) {
                    return s.replace(/{(\w+)}/g,
                    function(m, p) { return c[p]; }) }
                return function(table, name) {
                if (!table.nodeType) table = document.getElementById(table)
                var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
                window.location.href = uri + base64(format(template, ctx))
              }
            })()
    
</script>
</body>
</html>