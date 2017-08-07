<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
	#chatArea{
	width:300px; height:100px; overflow-y:auto;border:1px solid black; margin:0 auto;}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	var wsocket;
	function connect(){
		wsocket = new WebSocket("ws://localhost:8080/mixmatch/chat-ws.do");

		wsocket.onopen = onOpen;
		wsocket.close = onClose;
		// 서버로부터 메세지를 받으면 호출되는 함수 지정
		wsocket.onmessage = onMessage;
		
		// WebSocket 객체가 생성되어 서버와 연결되면 데이터를 입력할 수 있게 입력 필드를 활성화
		$("#enterBtn").attr("disabled",true);
		$("#message").attr("disabled",false);
		$("#sendBtn").attr("disabled",false);
	}
	
	function disconnect(){
		var msg = "msg:["+ $("#nickname").val() +"퇴장!]";
		wsocket.send(msg);
		
	};
	function onOpen(evt){
		var msg = "msg:["+ $("#nickname").val() +"입장!]";
		wsocket.send(msg);
	}
	function onMessage(evt){
		var data = evt.data;
		if(data.substring(0,4)=="msg:"){
			appendMessage(data.substring(4));
			
			// 메세지에 '퇴장'이 포함되있으며 '퇴장'의 아이디가 본인인지 확인하여  웹소켓 종료
			if(data.substring(5,data.lastIndexOf("퇴장!")) == $("#nickname").val() 
								&& data.lastIndexOf("퇴장!") != -1){
				wsocket.close();
		
			}
		}
	}
	function onClose(evt){
		$("#nickname").val("");
		$("#message").attr("disabled",true);
		$("#sendBtn").attr("disabled",true);
	}
	function send(){
		var nickname = $("#nickname").val();
		var msg = $("#message").val();
		wsocket.send("msg:"+nickname+":" +msg);
		// 보내고 나서 메세지 초기화
		$("#message").val("");
	}
	
	// 전달 받은 메세지를 화면에 표시 : 스크롤 지정
	function appendMessage(msg){
		$("#chatMessageArea").append(msg + "<br>");
		var chatAreaHeight = $("#chatArea").height();
		var maxScroll = $("#chatMessageArea").height - chatAreaHeight;
		$("#chatArea").scrollTop(maxScroll);
	}
	
	$(document).ready(function(){
		$("#message").keypress(function(event){
			var keycode = (event.keyCode ? event.keyCode:event.which);
			if(keycode == "13"){	// 엔터를 누른 경우 보내기
				send();
			}
			// 이벤트 전파 막기
			event.stopPropagation();
		});
		$("#sendBtn").click(function(){
			send();
		});
		$("#enterBtn").click(function(){
			if($("#nickname").val()==""){
				alert("이름을 입력하세요");
				$("#nickname").focus();
				return;
			}
			connect();
		});
		$("#exitBtn").click(function(){
			if($("#nickname").val()==""){
				alert("이름을 입력하세요");
				$("#nickname").focus();
				return;
			}
			disconnect();
		});
	});
	
</script>
<body>
	이름 : <input type="text" id="nickname">
	<input type="button" id="enterBtn" value="입장">
	<input type="button" id="exitBtn" value="나가기">
	<h1>대화 영역</h1>
	<div id="chatArea">
		<div id="chatMessageArea"></div>
	</div>
	<br>
	<input type="text" id="message" disabled>
	<input type="button" id="sendBtn" value="전송" disabled>
</body>