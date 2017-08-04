$(document).ready(function(){
	$('#match_btn').click(function() {
		alert('매치신청이 완료되었습니다.');
	});
	
	$('#delete_btn').click(function() {
		alert('삭제가 완료되었습니다.');
	});	
	
	if ($("#t_winteam option").index($("#t_winteam option:selected")) == 0) {
		$("#final_rate").val($("#t1_rate").val());
	} else {
		$("#final_rate").val($("#t2_rate").val());
	}
	
	$("#t_winteam").change(function() {
		if ($("#t_winteam option").index($("#t_winteam option:selected")) == 0) {
			$("#final_rate").val($("#t1_rate").val());
		} else {
			$("#final_rate").val($("#t2_rate").val());
		}
	});
	
});