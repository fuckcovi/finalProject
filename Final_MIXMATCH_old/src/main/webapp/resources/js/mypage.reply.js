$(document).ready(function(){
	var currentPage;		//현재페이지
	var count;				//게시글 총갯수
	var rowCount;			//한 페이지에 보여질 행(레코드) 수

	//글 등록 유효성체크
	$(document).on('submit','#mypageCommand',function(event){
		if ($('#h_content').val() == '') {
			alert('내용을 입력하세요');
			$('#h_content').focus();
			return false;
		}
	});
	
	
	/*mypageList.jsp에서의 부모글번호(h_seq)는 반복문으로 처리되기때문에 자바스크립에서도 반복문으로 받아야해.
	반복문으로 받지않으면 제일 마지막 부모글번호(h_seq)만 넘어와.*/
	
	$("input[name=h_seq]").each(function(idx){  
		var h_seq = $(this).val();
		
		//미니홈피 글수정
		$(document).on('click','#post-modify'+h_seq,function(){	//수정 버튼을 클릭했을때
			
			//글 번호
			var post_num = $(this).attr('data-postNum');
			//작성자 아이디
			var post_id = $(this).attr('data-postId');
			//글 내용 공개
			var post_show = $(this).attr('data-postShow')
			//글 내용
			var post_content = $('#'+post_num+' .post-content');
			
			//글 수정폼 UI
			var	postModifyUI =' <form id="post_re_form">';
				postModifyUI +=' <input type="hidden" name="h_seq" id="h_seq" value="'+post_num+'">';
				postModifyUI +=' <input type="hidden" name="id" id="id" value="'+post_id+'">';
				postModifyUI +=' <input type="hidden" name="h_show" id="h_show" value="'+post_show+'">';
				postModifyUI +=' 	<textarea name="h_content" id="h_content'+post_num+'" />'; 
				postModifyUI +='	  <div class="modify_delete_button_re">';
				postModifyUI +='  		<input type="submit" value="수정">';
				postModifyUI +='  		<input type="button" value="취소" class="post-reset">';
				postModifyUI +='      </div>';
				postModifyUI +=' </form>'
				
			//이전에 이미 수정하는 댓글이 있을 경우 수정버튼을 클릭하면 숨김 .mregister_form을 환원시키고 수정폼을 초기화함	
			initModifyForm();
			$('#'+post_num+' .modify_delete_button').hide();	
			
			//지금 클릭해서 수정하고자 하는 데이터는 감추기
			$('#'+post_num+' .post-content2').hide();
			
			//수정폼을 수정하고자하는 데이터가 있는 div에 노출
			$('.post-content1'+post_num).append(postModifyUI);
			
			//수정폼에서 취소 버튼 클릭시 수정폼 초기화
			$(document).on('click','.post-reset',function(){
				initModifyForm();
			});
			
			//글 수정 폼 초기화
			function initModifyForm(){
				$('.post-content2').show();
				$('#post_re_form').remove();
				$('.modify_delete_button').show();
			}
			
			//글 수정
			$(document).on('submit','#post_re_form',function(event){
				if ($('#h_content'+post_num).val() == '') {
					alert('수정내용을 입력하세요');
					$('#h_content'+post_num).focus();
					return false;
				}
				
				//폼에 입력한 데이터 반환
				var data = $(this).serialize();
				
				//수정
				$.ajax({
					type:'post',
					data:data,
					url:'update.do',
					dataType:'json',
					cache:false,
					async: false,
					timeout:30000,
					success:function(data){
						if (data.result == 'wrongAccess') {
							alert('타인의 글은 수정할 수 없습니다.');
						}else if(data.result == 'success'){
							//변경한 데이터로 UI 갱신
							$('#'+post_num+' .post-content2').text($('#h_content'+post_num).val());
							initModifyForm();
							location.reload();
						}else{
							alert('수정시 오류 발생');
						}
					},
					error:function(){
						alert('수정시 네트워크 오류');
					}
				});
				
				//기본 이벤트 제거
				event.preventDefault();
				
			});
		});
		
		//미니홈피 글삭제
		$(document).on('click','#post-delete'+h_seq,function(){
			//글번호
			var post_num = $(this).attr('data-postNum');
			//작성자 아이디
			var id = $(this).attr('data-postId');
			
			$.ajax({
				type:'post',
				data:{h_seq:post_num,id:id},
				url:'delete.do',
				dataType:'json',
				cache:false,
				timeoup:30000,
				success:function(data){
					if(data.resul == 'logout'){
						alert('로그인해야 삭제할 수 있습니다.');
					}else if(data.result == 'success'){
						alert('삭제완료');
						location.reload();
					}else if (data.result == 'wrongAccess') {
						alert('타인의 글은 삭제할 수 없습니다.')
					}else{
						alert('삭제시 오류 발생');
					}
				},
				error:function(){
					alert('삭제시 네트워크 오류');
				}
			});	
		});
	
		
		//초기 데이터(목록)호출
		selectData(1,h_seq);		//1페이지, 부모글번호
		
		 //댓글 목록
		function selectData(pageNum,h_seq){
			currentPage = pageNum;
			
			if (pageNum == 1) {
				//처음 호출시는 해당 ID의 div의 내부 내용물 제거
				$('#output'+h_seq).empty();		//h_seq를 붙이지않으면 댓글영역이 어느 글의 댓글영역인지 구별이 안되기때문에 제일 마지막 output영역에 모든댓글이 들어가.그래서 꼭 h_seq를 붙여줘야해.
			}
			
			$.ajax({
				type:'post',
				data:{pageNum:pageNum,h_seq:h_seq},
				url:'postReplyList.do',
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					count = data.count;
					rowCount = data.rowCount;
					var list = data.list;
					
					if (count < 0 || list == null) {
						alert('목록 호출시 오류 발생');
					}else{
						$(list).each(function(index,item){
							
							var output = '';
							output += '<div id="reply'+item.h_re_seq+'">';
							output += '<div class="reply-list" id="'+item.h_re_seq+'">';
							output += ' <table id="tbl">';
							output += '   <tr>';
							output += ' 	<td>'+item.id+'</td>';
							output += '     <td id="re_content">'+item.h_re_content+'</td>'; 	  		
							output += '   </tr>'; 	
							output += '   <tr>'; 
							output += '   	<td>'+item.h_re_regdate+'</td>'; 
							output += '  	<td>'; 
								
							
							if($('#user_id').val() && $('#user_id').val() == item.id){
								//로그인한 id가 글 작성자 id와 같은 경우
								output += '	<input type="button" value="수정" data-replyNum="'+item.h_re_seq+'" data-replyId="'+item.id+'" class="reply-modify">';
								output += '	<input type="button" value="삭제" data-replyNum="'+item.h_re_seq+'" data-replyId="'+item.id+'" class="reply-delete">';
							}
							
							output += '   	</td>';
							output += '   </tr>'; 
							output += '   </div>'; 
							output += '			</div>';
							
							
							//문서객체에 추가
							$('#output'+h_seq).append(output);
						
							//paging button 처리
							if (currentPage >= Math.ceil(count/rowCount)) {
								//다음 페이지가 없음
								$('.paging-button'+h_seq).hide();
							}else{
								//다음 페이지가 있음
								$('.paging-button'+h_seq).show();
							}
						});
					}
				},
				error:function(){
					alert('목록 호출시 네트워크 오류');
				}
			});	

		}
		
		//다음 댓글 보기 버튼 클릭시 데이터 추가
		$('.paging-button'+h_seq+' input').click(function(){
			var pageNum = currentPage + 1;
			selectData(pageNum,h_seq);
		});

		
		//댓글 등록
		$('#re_form'+h_seq).submit(function(event){
			
			if ($('#h_re_content'+h_seq).val()=='') {
				alert('내용을 입력하세요');
				$('#h_re_content'+h_seq).focus();
				return false;
			}
		
			//전송할 데이터
			var data = $(this).serialize();		//this는 폼
		
			//댓글 전송 및 등록
			$.ajax({
				type:'post',
				data:data,		//data:data -> 왼쪽은 key값. 오른쪽은 위에 data를 불러온거
				url:'postWriteReply.do',	//받은 데이터를 writeReply.do에게 보내주면서 동작해. 
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){				//여기의 data는 변수고 ajax가 데이터를 넣어준거야. 위의 data:data와는 다른거야.
					if (data.result == 'logout') {
						alert('로그인해야 작성할 수 있습니다.');
					}else if (data.result == 'success') {
						//폼 초기화
						initForm();
						//댓글 작성이 성공하면  새로 삽입한 글을 포함해서 첫번째 페이지의 게시글들을 다시 호출
						selectData(1,h_seq);
					}else{
						alert('등록시 오류 발생!');
					}
				},			
				error:function(){
					alert('등록시 네트워크 오류');
				}
			});
			//기본 이벤트 제거
			event.preventDefault();
		});
		
		//댓글 작성 폼 초기화
		function initForm(){
			$('#h_re_content'+h_seq).val('');
			$('#re_first .letter-count').text('300/300');
		}
		
		//textarea에 내용 입력시 글자수 체크
		$(document).on('keyup','#h_re_content'+h_seq,function(){
			//남은 글자수를 구함
			var inputLength = $(this).val().length;		//this는 textarea
			
			if (inputLength > 300) {		//300자를 넘어선 경우
				$(this).val($(this).val().sutbstrin(0,300));
			}else{		//300자 이하인 경우
				var remain = 300 - inputLength;
				remain += '/300';
				if ($(this).attr('id') == 'h_re_content'+h_seq) {
					//등록폼 글자수
					$('#re_first .letter-count'+h_seq).text(remain);
				}else{
					//수정폼 글자수
					$('#mre_first .letter-count'+h_seq).text(remain);
				}
			}
		});
		
		
		
		
	});
	
	//댓글 수정 버튼 클릭시 수정폼 노출
	$(document).on('click','.reply-modify',function(){
		//댓글 번호
		var reply_num = $(this).attr('data-replyNum')
		//작성자 아이디
		var id = $(this).attr('data-replyId');
		//댓글 내용
		var reply_content = $('#'+reply_num+' #re_content').text();
		
		
		//댓글 수정폼 UI
		var modifyUI = 	'<form id="mre_form">';
			modifyUI +=	' <input type="hidden" name="h_re_seq" id="reply_num" value="'+reply_num+'">';
			modifyUI +=	' <input type="hidden" name="id" id="muser_id" value="'+id+'">';
			modifyUI += ' <input type="text" name="h_re_content" id="mh_re_content" value="'+reply_content+'">';
			modifyUI += ' <div id="mre_second" class="align-right">';
			modifyUI += ' 	<input type="submit" value="수정">';
			modifyUI += ' 	<input type="button" value="취소" class="re-reset">';
			modifyUI += ' </div>';
			modifyUI += '</form>';
	
		//이전에 이미 수정하는 댓글이 있을 경우 수정버튼을 클릭하면 숨김 sub-item을 환원시키고 수정폼을 초기화함
		initModifyForm();
		
		//지금 클릭해서 수정하고자 하는 데이터는 감추기
		$('#'+reply_num).hide();
		
		//수정폼을 수정하고자하는 데이터가 있는 div에 노출
		$('#reply'+reply_num).append(modifyUI);	
		
			
	});
	
	//수정폼에서 취소 버튼 클릭시 수정폼 초기화
	$(document).on('click','.re-reset',function(){
		initModifyForm();
	});
	
	//댓글 수정 폼 초기화
	function initModifyForm(){
		$('.reply-list').show();
		$('#mre_form').remove();
	}
	
	//댓글 수정
	$(document).on('submit','#mre_form',function(event){
		if ($('#mh_re_content').val() == '') {
			alert('내용을 입력하세요');
			$('#mh_re_content').focus();
			return false;
		}
		
		var replyNum = $("#reply_num").val();
		
		
		//폼에 입력한 데이터 반환
		var data = $(this).serialize();
		
		//수정
		$.ajax({
			type:'post',
			data:data,
			url:'updateReply.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if (data.result == 'logout') {
					alert('로그인해야 수정할 수 있습니다.');
				}else if (data.result == 'success') {
					//변경한 데이터로 UI 갱신
					$('#'+replyNum+' #tbl tr td:eq(1)').text($('#mh_re_content').val());
					
					//수정폼 초기화
					initModifyForm();
				}else if(data.result == 'wrongAccess'){
					alert('타인의 글은 수정할 수 없습니다.');
				}else{
					alert('수정시 오류 발생');
				}
			},
			error:function(){q
				alert('수정시 네트워크 오류');
			}
		});
		
		//기본 이벤트 제거
		event.preventDefault();
		
	});
	
	
	
	
	
	
	//댓글삭제
	$(document).on('click','.reply-delete',function(){
		//댓글번호
		var re_delete_num = $(this).attr('data-replyNum');
		//작성자  아이디
		var id = $(this).attr('data-replyId');
		
		
		$.ajax({
			type:'post',
			data:{h_re_seq:re_delete_num,id:id},
			url:'deleteReply.do',
			dataType:'json',
			cache:false,
			timeoup:30000,
			success:function(data){
				if(data.resul == 'logout'){
					alert('로그인해야 삭제할 수 있습니다.');
				}else if(data.result == 'success'){
					alert('삭제완료');
					location.reload();
				}else if (data.result == 'wrongAccess') {
					alert('타인의 글은 삭제할 수 없습니다.')
				}else{
					alert('삭제시 오류 발생');
				}
			},
			error:function(){
				alert('삭제시 네트워크 오류');
			}
		});
	});
});