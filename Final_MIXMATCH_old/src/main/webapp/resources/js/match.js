$(document).ready(function(){
	$('#match_btn').click(function() {
		if (confirm('매치신청 하시겠습니까?')) {
			alert('매치신청 완료');
		} else {
			return false;
		}
	});
	
	$('#delete_btn').click(function() {
		if (confirm('삭제 하시겠습니까?')) {
			alert('매치삭제 완료');
		} else {
			return false;
		}
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
	
	$("#insert_form").submit(function(){
		if($("#m_date").val()==""){
			alert("날짜를 입력해주세요.");
			$("#m_date").focus();
			return false;
		}
		if($("#m_time").val()==""){
			alert("시간을 입력해주세요.");
			$("#m_time").focus();
			return false;
		}
		if($("#m_place").val()==""){
			alert("경기장을 입력해주세요.");
			$("#m_place").focus();
			return false;
		}
		
		if (confirm('매치등록 하시겠습니까?')) {
			alert('매치등록 완료');
		} else {
			return false;
		}
	});
	
	$("#result_form").submit(function(){
		if($("#m_home").val()==""){
			alert("점수를 입력해주세요.");
			$("#m_home").focus();
			return false;
		}
		if($("#m_away").val()==""){
			alert("점수를 입력해주세요.");
			$("#m_away").focus();
			return false;
		}
		
		if (confirm('점수등록 하시겠습니까?')) {
			alert('점수등록 완료');
		} else {
			return false;
		}
	});
	
	$("#toto_form").submit(function(){
		if($("#t_score").val()==""){
			alert("예상점수를 입력해주세요.");
			$("#t_score").focus();
			return false;
		}
		if($("#t_point").val()==""){
			alert("베팅포인트를 입력해주세요.");
			$("#t_point").focus();
			return false;
		}
	});
	
});