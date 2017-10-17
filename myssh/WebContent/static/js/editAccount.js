$(document).ready(function(){
	$("#userCaption").blur(function(){
		var na = /^[\u4E00-\u9FA5]{2,10}$/;
		if(na.test($("#userCaption").val())){
			
			$("#forReturn").removeAttr("disabled");
			$("#forReturn").css("background-color","#0c95db");
		}else{
			
			$("#forReturn").attr("disabled", true);
			$("#forReturn").css("background-color","#999999");
		}
	})
	
	$("#phone").blur(function(){
		var tl = /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^1[34578][0-9]{9}$)/;
	
		if(tl.test($("#phone").val())){
			
			$("#forReturn").removeAttr("disabled");
			$("#forReturn").css("background-color","#0c95db");
		}else{
			
			$("#forReturn").attr("disabled", true);
			$("#forReturn").css("background-color","#999999");
		}
	})
	
	$("#idCard").blur(function(){
		var ida = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
		if(ida.test($("#idCard").val())){
			
			$("#forReturn").removeAttr("disabled");
			$("#forReturn").css("background-color","#0c95db");
		}else{
			
			$("#forReturn").attr("disabled", true);
			$("#forReturn").css("background-color","#999999");
		}
	})
	
	
});

function addAccountForm(obj){
	if($("input[name='userCaption']").val() != '' && $("input[name='phone']").val() != '' && $("input[name='idCard']").val() != ''){
	var toUrl = $(obj).data("value");
	$.ajax({
		type : "post",
		url : "account_manager_to_add_or_update",
		dataType:"json",
		data : $("#alertAccountVerify").serialize(),
		success : function(data) {
			if(data.code==200){
				//alert(data.msg);
				if(toUrl==1){
					window.location.href = "goto_accountsnum_manage_page";
				}else if(toUrl==0){
					window.location.href = "to_account_manager_page";
				}
			}else if(data.code==201){
				alert(data.msg);
			}
		}
	});
	}else{
		layer.alert("信息不能为空",{icon: 5});
	}
}