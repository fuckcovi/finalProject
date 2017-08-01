$(document).ready(function(){
	var checkTnameDuplicated = 0;
	
	$("#confirmTname").click(function(){
		if($("#t_name").val()==""){
			alert("팀명을 입력해주세요.");
			$("#t_name").focus();
			return;
		}
		$("#message_tname").text("");
		$("#loading").show();
		
		$.ajax({
			url:"confirmTname.do",
			type:"post",
			data:{tname:$("#t_name").val()},
			dataType:"json",
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result=="tnameNotFound"){
					$("#message_tname").css("color","black").text("사용가능한 팀명입니다.");
					checkTnameDuplicated=1;
				}else if(data.result=="tnamdDuplicated"){
					$("#message_tname").css("color","red").text("이미 등록된 팀명입니다.");
					$("#t_name").focus();
					checkTnameDuplicated=0;
				}else{
					alert("팀명 중복체크 오류 발생");
					checkTnameDuplicated=0;
				}
			},
			error:function(){
				$("#loading").hide();
				alert("팀 생성 실패 - 네트워크 오류 발생");
			}
		});
	});
	
	$("#teamRegister #t_name").keyup(function(){
		checkTnameDuplicated=0;
		$("#message_tname").text("");
	});	
	
	$("#teamRegister").submit(function(){
		if(checkTnameDuplicated==0){
			alert("팀명 중복확인을 해주세요.");
			if($("#t_name").val("")){
				$("#t_name").focus();
			}else{
				$("#confirmTname").focus();
			}
			return;
		}
	});
});