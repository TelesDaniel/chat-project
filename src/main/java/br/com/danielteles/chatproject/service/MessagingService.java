package br.com.danielteles.chatproject.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

import br.com.danielteles.chatproject.models.MessageRoom;
import br.com.danielteles.chatproject.models.MessageUser;

@Service
public class MessagingService {

	SimpMessagingTemplate messagingtemplate;
	SimpUserRegistry userRegistry;
	 
	public MessagingService(SimpMessagingTemplate messagingtemplate) {
		this.messagingtemplate = messagingtemplate;
	}
	
	public void sendMessage(MessageRoom message, String sessionId) {
		/*
		 * Verificar se sessionId esta conectado
		 * Verificar se sessionId tem permissao/esta conectado a esse grupo
		 * */
		messagingtemplate.convertAndSend("/topic/"+ message.getRoomId(), message);
	}
	
	public void sendMessage(MessageUser message, String sessionId) {
		
		
	}
}
