<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	function getProvS(){
		getAllProv();
	}
	function getAllProv(){
		$.ajax({
			type : "post",
			url : "get_all_prov",
			async:false,
			//data : {selectStatus : $("#selectStyleId").val()},
			success : function(data) {
				data = eval("(" + data + ")");
				//console.log(data);
				fileProviceIntoDiv(data.root);
				//formWin();
			}
		});
	};
	function fileProviceIntoDiv(data){
		//console.log(data);
		var s = "";
		$.each(data,function(v,o){
			//console.log(o);
			s += '<a href="javascript:;" onclick="getCurrentProvOfShi('+o.cityId+')">'+o.cityName+'</a>&nbsp;';
		});
		if(data.length>0){
			$("#provice").html(s);
		}
	}
	
	function getCurrentProvOfShi(id){
		//alert(id);
		$.ajax({
			type : "post",
			url : "menu_list_city_area",
			async:false,
			data : {shengOrShi : id},
			success : function(data) {
				data = eval("(" + data + ")");
				//console.log(data);
				fileShiIntoDiv(data.root);
				//formWin();
			}
		});
	}
	function fileShiIntoDiv(data){
		var s = "";
		$.each(data,function(v,o){
			console.log(o);
			s += '<a href="javascript:;" data-value='+o.cityId+' onclick=fileNameIntoInput(this)>'+o.cityName+'</a>&nbsp;';
		});
		if(data.length>0){
			$("#provice").html(s);
		}
	}
	function fileNameIntoInput(obj){
		var shiId = $(obj).data("value");
		$("#address").val($(obj).html());
		$("#provice").html("");
	}
</script>
</head>
<body>
	<input id="address" onclick="getProvS()">:选择位置
	<input id="shiId" type="hidden">
	<div id="provice">
	</div>
	<div id="shiData">
	</div>
</body>
</html>