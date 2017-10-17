<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	function uploadExc(){
		var form = new FormData();
		form.append("file",$("#file").get(0).files[0]);
		$.ajax({
			url:"import_new",
			dataType:"json",
			type : "post",
			data:form,
			contentType: false,
			processData: false,
			/* beforeSend:function(){
				$("#subMsg").css("display","block");
			},  */
			success:function(data){
				if(data.code==200){
					//$("#subMsg").css("display","none");
					alert("上传完成");
				}
			},
			error:function(){
				alert("上传失败...");
			}
		});
	}
</script>
</head>
<body>
	<form>
		<input id="file" type="file" name="file">
		<input type="button" value="提交" onclick="uploadExc()">
	</form>
	
</body>
</html>