$(document).ready(function(){
	var currentPage; //현재페이지
	var count; //댓글 총갯수
	var rowCount; //한 페이지에 보여질 행(레코드) 수
	
	//댓글 목록
	function selectData(pageNum,gt_seq){
		currentPage = pageNum;
		
		if(pageNum == 1){
			//처음 호출시는 해당 ID의 div의 내부 내용물 제거
			$('#output').empty();
		}
		
		//로딩 이미지 호출
		$('#loading').show();
		
		$.ajax({
			type:'post',
			data:{pageNum:pageNum,gt_seq:gt_seq},
			url:'teamBoardListReply.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				//로딩 이미지 감추기
				$('#loading').hide();
				count = data.count;
				rowCount = data.rowCount;
				var list = data.list;
				
				if(count < 0 || list == null){
					alert('목록 호출시 오류 발생!');
				}else{
					$(list).each(function(index,item){
						var output = '';
						output += '<div class="item" id="'+item.gtre_no+'">';
						output += '  <h4>' + item.id + '</h4>';
						output += '  <div class="sub-item">';
						output += '    <p>' + item.gtre_content + '</p>';
						output += '    <div>' + item.gtre_date;
						
						if($('#user_id').val() && $('#user_id').val() == item.id){
							//로그인한 id가 댓글 작성자 id와 같은 경우
							output += ' <input type="button" value="수정" data-num="'+item.gtre_no+'" data-id="'+item.id+'" class="modify-button">';
							output += ' <input type="button" value="삭제" data-num="'+item.gtre_no+'" data-id="'+item.id+'" class="delete-button">';
						}else{
							//로그인하지 않았거나 작성자 id와 다른 경우
							output += ' <input type="button" value="수정" disabled="disabled">';
							output += ' <input type="button" value="삭제" disabled="disabled">';
						}
						
						output += '      <hr size="1" noshade>';
						output += '    </div>';
						
						//문서 객체에 추가
						$('#output').append(output);
						
						//paging button 처리
						if(currentPage>=Math.ceil(count/rowCount)){
							//다음 페이지가 없음
							$('.paging-button').hide();
						}else{
							//다음 페이지가 있음
							$('.paging-button').show();
						}
					});
				}
			},
			error:function(){
				//로딩 이미지 감추기
				$('#loading').hide();
				alert('목록 호출시 네트워크 오류!');
			}
		});
	}
	
	//다음 댓글 보기 버튼 클릭시 데이터 추가
	$('.paging-button input').click(function(){
		var pageNum = currentPage + 1;
		selectData(pageNum,$('#gt_seq').val());
	});
	
	//댓글 등록
	$('#re_form').submit(function(event){
		
		if($('#gtre_content').val()==''){
			alert('내용을 입력하세요!');
			$('#gtre_content').focus();
			return false;
		}
		
		//전송할 데이터
		var data = $(this).serialize();
		
		//댓글 전송 및 등록
		$.ajax({
			type:'post',
			data:data,
			url:'teamBoardWriteReply.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == 'logout'){
					alert('로그인해야 작성할 수 있습니다.');
				}else if(data.result == 'success'){
					//폼초기화
					initForm();
					/*
					 *댓글 작성이 성공하면 새로 삽입한 글을 포함해서
					 *첫번째 페이지의 게시글들을 다시 호출함
					 * */
					selectData(1,$('#gt_seq').val());
				}else{
					alert('등록시 오류 발생!');
				}
			},
			error:function(){
				alert('등록시 네트워크 오류!');
			}
		});
		//기본 이벤트 제거
		event.preventDefault();
	});
	
	//댓글 작성 폼 초기화
	function initForm(){
		$('textarea').val('');
		$('#re_first .letter-count').text('300/300');
	}
	
	//textarea에 내용 입력시 글자수 체크
	$(document).on('keyup','textarea',function(){
		//남은 글자수를 구함
		var inputLength = $(this).val().length;
		
		if(inputLength>300){//300자를 넘어선 경우
			$(this).val($(this).val().substring(0,300));
		}else{//300자 이하인 경우
			var remain = 300 - inputLength;
			remain += '/300';
			if($(this).attr('id')=='gtre_content'){
				//등록폼 글자수
				$('#re_first .letter-count').text(remain);
			}else{
				//수정폼 글자수
				$('#mre_first .letter-count').text(remain);
			}
		}
	});
	
	//댓글 수정 버튼 클릭시 수정폼 노출
	$(document).on('click','.modify-button',function(){
		//댓글 번호
		var num = $(this).attr('data-num');
		//작성자 아이디
		var id = $(this).attr('data-id');
		//댓글 내용
		var content = $('#'+num+' p').text();
		
		//댓글 수정폼 UI
		var modifyUI =  '<form id="mre_form">';
		    modifyUI += ' <input type="hidden" name="gtre_no" id="mgtre_no" value="'+num+'">';
		    modifyUI += ' <input type="hidden" name="id" id="muser_id" value="'+id+'">';
		    modifyUI += ' <textarea rows="3" cols="50" name="gtre_content" id="mgtre_content" class="rep-content">'+content+'</textarea>';
		    modifyUI += ' <div id="mre_first"><span class="letter-count">300/300</span></div>';
		    modifyUI += ' <div id="mre_second" class="align-right">';
		    modifyUI += '  <input type="submit" value="수정">';
		    modifyUI += '  <input type="button" value="취소" class="re-reset">';
		    modifyUI += ' </div>';
		    modifyUI += ' <hr size="1" noshade width="96%">';
		    modifyUI += '</form>';
		    
		//이전에 이미 수정하는 댓글이 있을 경우 수정버튼을 클릭하면
		//숨김 sub-item를 환원시키고 수정폼을 초기화함    
		initModifyForm();
		
		//지금 클릭해서 수정하고자 하는 데이터는 감추기
		$('#'+num+' .sub-item').hide();
		//수정폼을 수정하고자하는 데이터가 있는 div에 노출
		$('#'+num).append(modifyUI);
		
		//입력한 글자수 셋팅
		var inputLength = $('#mgtre_content').val().length;
		var remain = 300 - inputLength;
		remain += '/300';
		
		//문서 객체에 반영
		$('#mre_first .letter-count').text(remain);
		
	});
	
	//수정폼에서 취소 버튼 클릭시 수정폼 초기화
	$(document).on('click','.re-reset',function(){
		initModifyForm();
	});
	
	//댓글 수정 폼 초기화
	function initModifyForm(){
		$('.sub-item').show();
		$('#mre_form').remove();
	}
	
	//댓글 수정
	$(document).on('submit','#mre_form',function(event){
		if($('#mgtre_content').val()==''){
			alert('내용을 입력하세요!');
			$('#mgtre_content').focus();
			return false;
		}
		
		//폼에 입력한 데이터 반환
		var data = $(this).serialize();
		
		//수정
		$.ajax({
			type:'post',
			data:data,
			url:'teamBoardUpdateReply.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == 'logout'){
					alert('로그인해야 수정할 수 있습니다.');
				}else if(data.result == 'success'){
					//변경한 데이터로 UI 갱신
					$('#'+$('#mgtre_no').val()+' p').text($('#mgtre_content').val());
					//수정폼 초기화
					initModifyForm();
				}else if(data.result == 'wrongAccess'){
					alert('타인의 글은 수정할 수 없습니다.');
				}else{
					alert('수정시 오류 발생!');
				}
			},
			error:function(){
				alert('수정시 네트워크 오류!');
			}
		});
		
		//기본 이벤트 제거
		event.preventDefault();
	});
	
	//댓글 삭제
	$(document).on('click','.delete-button',function(){
		//댓글번호
		var data_num = $(this).attr('data-num');
		//작성자 아이디
		var id = $(this).attr('data-id');
		
		$.ajax({
			type:'post',
			data:{gtre_no:data_num,id:id},
			url:'teamBoardDeleteReply.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == 'logout'){
					alert('로그인해야 삭제할 수 있습니다.');
				}else if(data.result == 'success'){
					alert('삭제 완료!');
					selectData(1,$('#gt_seq').val());
				}else if(data.result == 'wrongAccess'){
					alert('타인의 글은 삭제할 수 없습니다.');
				}else{
					alert('삭제시 오류 발생!');
				}
			},
			error:function(){
				alert('삭제시 네트워크 오류!');
			}
		});
		
	});
	
	//초기 데이터(목록) 호출
	selectData(1,$('#gt_seq').val());
});