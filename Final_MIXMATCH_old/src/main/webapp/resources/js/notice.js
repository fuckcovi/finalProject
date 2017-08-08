$(document).ready(function(){
	
	var currentPage;	// 현재페이지
	var count;	// 댓글 갯수
	var rowCount; // 한페이지에 보여질 행
	
	// 댓글 목록
	function selectData(pageNum,gnre_seq){
		currentPage = pageNum;
		if(pageNum==1){
			// 처음 호출 시 해당id의 div내부 내용물 제거
			$("#output").empty();	// 목록 초기화
		}
		// 로딩 이미지 호출
		$("#loading").show();
		
		$.ajax({
			type:"post",
			data:{pageNum:pageNum, gnre_seq:gnre_seq},// key:value
			url:"noticelistReply.do",	// 댓글의 목록 url. 페이지 자체는 그대론데 밑에서 호출되는 화면
			dataType:"json",
			cache:false,
			timeout:30000,
			success:function(data){
				$("#loading").hide();
				count = data.count;
				rowCount = data.rowCount;
				var list = data.list;
				
				if(count <0 || list == null){	// 데이터 넘어오다 오류난 경우
					alert("목록 호출 시 오류 발생!");
				}else{
					$(list).each(function(index,item){
						var output="";
						output += "<div class='item' id='"+item.gnre_no+"'>";
						output += "	<h4>"+item.id +"</h4>";
						output += "	<div class='sub-item'>";
						output += "		<p>"+item.gnre_content+"</p>";
						output += "		<div>"+item.gnre_date;
						
						if($("#user_id").val() && $("#user_id").val()== item.id){
							// 로그인한 id가 댓글작성자id와 같은경우
							output += " <input type='button' value='수정' data-num='"+item.gnre_no+"' data-id='"+item.id+"' class='modify-button'>";
							output += " <input type='button' value='삭제' data-num='"+item.gnre_no+"' data-id='"+item.id+"' class='delete-button'>";
						}else{	// 로그인하지 않았거나 작성자 id와 다른경우
							output += " <input type='button' value='수정' disabled='disabled'>";
							output += " <input type='button' value='삭제' disabled='disabled'>";
						}
						
						output += "			<hr size='1' noshade>";
						output += "		</div>";
						output += "	</div>";
						output += "</div>";
						
						// 문서 객체에 추가
						$("#output").append(output);
						// paging버튼 처리
						if(currentPage >= Math.ceil(count/rowCount)){
							// 다음페이지 버튼 없음
							$(".paging-button").hide();
						}else{
							$(".paging-button").show();
						}
					});
				}
			},
			error:function(){
				// 로딩 이미지 감추기
				$("#loading").hide();
				alert("목록 호출 시 네트워크 오류sdfasdfsdf!");
			}
		});
	}
	//  다음 댓글 보기 버튼클릭 시 데이터 추가
	$(".paging-button input").click(function(){
		var pageNum = currentPage +1;
		selectData(pageNum, $("#gn_seq").val());
	});
	
	
	
	// 댓글 등록
	$("#re_form").submit(function(){
		if($("#gnre_content").val()==""){
			alert("내용을 입력하세요");
			$("#gnre_content").focus();
			return false;
		}
		
		// 전송할 데이터
		var data = $(this).serialize();
		
		// 댓글 전송 및 등록
		$.ajax({
			type:"post",
			data:data,	// 위에 전송할 데이터를 데이터로 쓰겠다.
			url:"noticewriteReply.do",
			dataType:"json",
			cache:false,
			timeout:30000,
			success:function(data){	// 위의 데이터와는 다른 별개의 데이터가 입력되었을때 함수
				if(data.result == "logout"){
					alert("로그인해야 작성할 수 있습니다.");
				}else if(data.result == "success"){
					// 폼초기화
					initForm();
					// 댓글작성이 성공하면 새로 삽입한 글을 포함하여 첫번쨰페이지의 게시글들을 호출
					selectData(1,$("#gn_seq").val());
				}else{
					alert("등록 시 오류 발생");
				}
			},
			error:function(){
				alert("등록시 네트워크 오류");
			}			
		});
		
		// 기본 이벤트 제거
		event.preventDefault();
	});
	
	// 댓글 작성 폼 초기화
	function initForm(){
		$("#gnre_content").val("");
		$("#re_first .letter-count").text("300/300");
	}
	
	// 댓글 작성 시 글자 수 세기 : textarea에 내용 입력시 
	$(document).on("keyup","textarea",function(){
		var inputLength = $(this).val().length;
		if(inputLength > 300){	// 300자 초과
			$(this).val($(this).val().substring(0,300));	// 0~300번찌까지 글자 자르기
		}else{	// 300자 이하
			var remain = 300 - inputLength;
			remain += "/300";
			if($(this).attr("id")=="gnre_content"){
				// 등록 폼 글자수
				$("#re_first .letter-count").text(remain);
			}else{
				// 수정 폼 글자 수
				$("#mre_first .letter-count").text(remain);
			}
		}
	});
	
	/*
	
	// 댓글 삭제	- 데이터 연결할때는 on메소드 사용
	$(document).on("click",".delete-button",function(){
		// 댓글 번호			// 방금 누른 버튼의 속성값
		var data_num = $(this).attr("data-num");
		// 작성자 아이디
		var id = $(this).attr("data-id");
		
		$.ajax({
			type:"post",
			data:{re_num:data_num,id:id},
			url:"deleteReply.do",
			dataType:"json",
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result=="logout"){
					alert("로그인 해야 삭제 할 수 있습니다.");
				}else if(data.result=="success"){
					alert("삭제 완료");
					selectData(1,$("#seq").val());
				}else if(data.result=="wrongAccess"){
					alert("타인의 글은 삭제불가");
				}else{
					alert("삭제 시 오류 발생");
				}
			},
			error:function(){
				alert("네트워크 오류 발생으로 삭제 실패");
			}
		});
	});
	
	// 댓글수정 버튼 클릭시 수정폼 노출
	$(document).on("click",".modify-button",function(){
		// 댓글 번호
		var num = $(this).attr("data-num");
		// 작성자 아이디
		var id = $(this).attr("data-id");
		// 댓글 내용
		var content = $("#"+num+" p").text();
		
		// 댓글 수정폼 UI
		var modifyUI = "<form id='mre_form'>";
			modifyUI +="	<input type='hidden' name='re_num' id='mre_num' value='"+num+"'>";
			modifyUI +="	<input type='hidden' name='id' id='muser_id' value='"+id+"'>";
			modifyUI +="	<textarea rows='3' cols='50' name='re_content' id='mre_content' class='rep-content'>"+content+"</textarea>";
			modifyUI +="	<div id='mre_first'><span class='letter-count'>300/300</span></div>";
			modifyUI +="	<div id='mre_second' class='align-right'>";
			modifyUI +="		<input type='submit' value='수정'>";
			modifyUI +="		<input type='button' value='취소' class='re-reset'>";
			modifyUI +="	</div>";
			modifyUI +="	<hr size='1' noshade width='96%'>";
			modifyUI +="</form>";
			
		// 이전에 이미 수정하는 댓글이 있을 경우 수정 버튼을 클릭하면 숨김 sub-item을 환원시키고 수정폼을 초기화
		initModifyForm();
		//  지금 클릭해서 수정하고자 하는 데이터는 감추기
		$("#"+num+" .sub-item").hide();
		// 수정폼을 수정하고자 하는 댓글에 노출시키기
		$("#"+num).append(modifyUI);
		//  수정폼에 띄워진 입력한 글자수 세팅
		var inputLength = $("#mre_content").val().length;
		var remain = 300 - inputLength;
		remain += "/300";
		
		// 문서 객체에 반영
		$("#mre_first .letter-count").text(remain);
	});
	
	// 수정폼에서 취소버튼 클릭시 수정폼 초기화
	$(document).on("click",".re-reset",function(){
		initModifyForm();
	});
	
	// 댓글 수정 폼 초기화
	function initModifyForm(){
		$(".sub-item").show();
		$("#mre_form").remove();
	}
	
	// 댓글 수정
	$(document).on("submit","#mre_form",function(event){
		if($("#mre_content").val()==""){
			alert("내용을 입력하세요");
			$("#mre_content").focus();
			return false;
		}
		// 폼에입력한 데이터 반환
		var data = $(this).serialize();

		// 수정
		$.ajax({
			type:"post",
			data:data,
			url:"updateReply.do",
			dataType:"json",
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result=="logout"){
					alert("로그인 해야 수정 할 수 있습니다.");
				}else if(data.result =="success"){
					// 변경한 데이터로 ui갱신
					$("#"+$("#mre_num").val()+" p").text($("#mre_content").val());
					// 수정폼 초기화
					initModifyForm();
				}else if(data.result=="wrongAccess"){
					alert("타인의 글은 수정할 수 없습니다.");
				}else{
					alert("수정 데이터 업로드 시 에러 발생");
				}
			},
			error:function(){
				alert("수정 시 네트워트 오류 발생");
			}
		});
		
		// 기본 이벤트 제거
		event.preventDefault();
	});
	*/
	
	// 초기 데이터(목록) 호출
	selectData(1, $("#gnre_seq").val());
	
});