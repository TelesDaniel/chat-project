package br.com.danielteles.chatproject.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import br.com.danielteles.chatproject.models.Message;
import br.com.danielteles.chatproject.models.MessageRoom;
import br.com.danielteles.chatproject.models.MessageUser;
import br.com.danielteles.chatproject.service.MessagingService;

@Controller
public class MessagingController {

	@Autowired
	private MessagingService service;

	@MessageMapping("/send")
	public void sendMessage(@Payload Message message, @Header("simpSessionId") String sessionId){
		if(message instanceof MessageRoom) {
			service.sendMessage((MessageRoom) message, sessionId);			
		}else if (message instanceof MessageUser) {
			service.sendMessage((MessageUser) message, sessionId);
		}
	}
}
