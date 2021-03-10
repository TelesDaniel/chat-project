package br.com.danielteles.chatproject.models;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Daniel Teles
 * Classe subtipo de Message para envio e recebimento de mensagens
 * para uma sala de modo que todos os integrates da sala possam visualizar.
 */
@Getter
@Setter
public class MessageRoom extends Message  {
	private String roomId;
	
	public MessageRoom(String roomId, Message message) {
		super(message.getFrom(), message.getText(), message.getDate());
		this.roomId = roomId;
	}
}
