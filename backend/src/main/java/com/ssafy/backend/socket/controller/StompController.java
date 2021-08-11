package com.ssafy.backend.socket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.ssafy.backend.socket.model.SocketModel;

@Controller
public class StompController {
	
	@MessageMapping("/TTT")
	@SendTo("/topic/message")
	public SocketModel ttt(SocketModel model) throws Exception{
		System.out.println(model.getUserName());
		String userName=model.getUserName();
		String content=model.getContent();
		SocketModel result=new SocketModel(userName,content);
		return result;
	}
}
