package com.kh.mixmatch.chat;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class chatHandler extends TextWebSocketHandler{
	private Map<String, WebSocketSession> users = new ConcurrentHashMap<String, WebSocketSession>();
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println(session.getId() + " 연결 됨");
		users.put(session.getId(), session);
	}
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println(session.getId() + " 연결종료");
		users.remove(session.getId());
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println(session.getId() + "로부터 메세지 수신 : " + message.getPayload());
		
		// 연결되어 있는 모든 클라이언트에 메세지 전송
		for(WebSocketSession s : users.values()){
			s.sendMessage(message);
			System.out.println(s.getId() + "에 메세지 발송 : " + message.getPayload());
		}
	}
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.out.println(session.getId() + " 예외 발생 : " + exception.getMessage());
	}
}
