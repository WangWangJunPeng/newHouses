$(document).ready(function($) {

	// 关闭到访性质
	$('.getBtn').click(function(event) {
		$(".getVist").hide();
	});

	//选择到访性质
	$('.arrive').click(function(event) {
		$(".getway").hide();
		$(".vistway").show();		
	});

	

	

	

	//指定置业顾问
	$(".cus").click(function(event) {
		$(".cus").css("background-color","transparent");
		$(".cus").css("color","#2e445d");
		$(this).css("background-color","#2db1ef");
		$(this).css("color","#fff");
		
		var nameImg = $(this).children('.midBox').children('.name').children('img');
		var phoneImg = $(this).children('.midBox').children('.phone').children('img');
		var groupImg = $(this).children('.midBox').children('.group').children('img');
		$('.name>img').attr("src","static/images/userleft.png");
		$('.phone>img').attr("src","static/images/icon_Tel.png");
		$('.group>img').attr("src","static/images/icon_edit.png");
		nameImg.attr("src","static/images/userleft_preesed.png");
		phoneImg.attr("src","static/images/icon_Tel_presssed.png");
		groupImg.attr("src","static/images/icon_edit_pressed.png");		

	});

	
	/*$(".choose_cus").click(function(event) {
		
	});*/
	$(".select_cus").click(function(event) {
		$('.seceltCus').hide();
	});


	$('.close_single').click(function(event) {
		$('.seceltCus').hide();
	});


});

