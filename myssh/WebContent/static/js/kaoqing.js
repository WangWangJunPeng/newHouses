$(document).ready(function(){

	// 考勤
	$('.close_all_kq').click(function(event) {
		$(".kq").hide();
	});

	$('.close_all_sign').click(function(event) {
		$('.kq_content').hide();
	});
	
	$('.warn_close').click(function(event) {
		$('#warning-block').hide();
	});
});

