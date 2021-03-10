package br.com.danielteles.chatproject.models;

import java.io.IOException;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * @author Daniel Teles
 * Classe para deserializacao de um JSON ao objeto Message e seus subtipos.
 * @see br.com.danielteles.chatproject.models.Message
 * @see br.com.danielteles.chatproject.models.MessageRoom
 * @see br.com.danielteles.chatproject.models.MessageUser
 */

public class MessageDeserializer extends StdDeserializer<Message> {
	
	private static final long serialVersionUID = -128661559230407321L;

	public MessageDeserializer() {
		this(null);
	}
	
	protected MessageDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Message deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode node = p.getCodec().readTree(p);
		
		Message message = null;
		try {
			message = Message.builder()
					.text(node.get("text").asText(null))
					.from(node.get("from").asText(null))
					.date(ZonedDateTime.parse(node.get("date").asText("1969-01-01T00:00:00+00:00")))
					.build();
			
			if(node.has("roomId")) 
				return new MessageRoom(
						node.get("roomId").asText(null),
						message);
			else if(node.has("userId")) 
				return new MessageUser(
						node.get("userId").asText(null),
						message);			
		}catch (Exception e) {
			throw new IOException("Invalid Json format", e);
		}
		
		return message;
	}

}
