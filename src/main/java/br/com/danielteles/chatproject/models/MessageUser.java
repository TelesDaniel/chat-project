package br.com.danielteles.chatproject.models;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Daniel Teles
 * Classe subtipo de Message para envio e recebimento de mensagens 
 * para usuários especificos e de modo privado.
 */
@Getter
@Setter
public class MessageUser extends Message {

	private String toUser;
	
	public MessageUser(String toUser, Message message) {
		super(message.getFrom(), message.getText(), message.getDate());
		this.toUser = toUser;
	}

}
