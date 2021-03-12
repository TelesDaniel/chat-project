package br.com.danielteles.chatproject.models;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Builder;
import lombok.Data;

/**
 * @author Daniel Teles
 * Classe principal para envio e recebimento de mensagens durante o chat.
 */
@Data
@Builder
@JsonDeserialize(using = MessageDeserializer.class)
public class Message {
	
	private String from, text;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private ZonedDateTime date;
}
