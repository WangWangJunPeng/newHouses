<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <title>登录</title>
      <meta charset="utf-8">
   </head>
<script type="text/javascript" src="../static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../static/js/jquery.cookie.js"></script>
<script  type="text/javascript">
$(document).ready(function() {
	$.ajax({
        type: "POST",
        url:"../applogin",
        data:{
        	phone:'${phone}',
        	password:'${password}',
        	sign:'${sign}'
        },// 你的formid
        async: false,
        error: function() {
        	
        },
        success: function(data) {
        	//成功
        	if(data.returenCode==200){
        		$("#token").val(data.userToken);
        		window.location.href = "../"+data.skipURL;
        	}
        	//没有权限
        	if(data.returenCode==401){
        		$("#msgInfo").text(data.msg);
        	}
        	//用户密码错误
        	if(data.returenCode==402){
        		//alert(data.msg);
        		$("#msgInfo").text(data.msg);
        	}
        }
    });
});
</script>
</body>
	<h1 id="msgInfo"></h1>
</html>