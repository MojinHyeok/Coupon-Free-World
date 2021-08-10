package com.ssafy.backend.socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.ssafy.backend.user.model.UserModel;

@Component
public class SocketHandler extends TextWebSocketHandler {
	List<WebSocketSession> sessions=new ArrayList<WebSocketSession>();
	HashMap<String, WebSocketSession> userSessions = new HashMap<>(); //웹소켓 세션을 담아둘 맵
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
		System.out.println("handleTextMessage: "+session+" :"+message);
		String senderId=getId(session);
		//특정 유저에게 보내기
		String msg=message.getPayload();
		if(msg!=null) {
			String[] str=msg.split(",");
			String caller=str[0];
			String target=str[1];
			WebSocketSession targetSession=userSessions.get(target);
			if(targetSession!=null) {
				TextMessage tmpMsg=new TextMessage(caller+"님이 "+ target+"님을 팔로우요청을 하였습니다.!");
				targetSession.sendMessage(tmpMsg);
			}
		}
		
//		for( WebSocketSession sess: sessions) {
//			sess.sendMessage(new TextMessage(senderId+" : "+message.getPayload()));
//		}
		//메시지 발송
//		String msg = message.getPayload();
//		for(String key : sessionMap.keySet()) {
//			WebSocketSession wss = sessionMap.get(key);
//			try {
//				wss.sendMessage(new TextMessage(msg));
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//소켓 연결
//		super.afterConnectionEstablished(session);
//		sessionMap.put(session.getId(), session);
		System.out.println("AfterConnectionEstablished: "+ session);
		String senderId = getId(session);
		if(senderId!=null) {	// 로그인 값이 있는 경우만
			System.out.println(senderId + " 연결 됨");
			userSessions.put(senderId, session);
			sessions.add(session);
		}
//		String senderId = getId(session);
//		userSessions.put(senderId, session);
	}
	
	private String getId(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		String loginuser= (String)httpSession.get("one_user");
		System.out.println(loginuser);
		return loginuser==null? null: loginuser;
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//소켓 종료
		String senderID=getId(session);
		if(senderID!=null) {	// 로그인 값이 있는 경우만
			System.out.println(senderID + " 연결 종료됨");
			userSessions.remove(senderID);
			sessions.remove(session);
		}
	}
}
