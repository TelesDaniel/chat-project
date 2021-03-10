package br.com.danielteles.chatproject.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class MessageDeserializerTest {

	@Autowired
	private ObjectMapper mapper;
	
	/** 
	 * Teste para criação de objetos do tipo MessageRoom apartir de um JSON utilizando um deserializer, o MessageDeserializer
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 * 
	 * @see br.com.danielteles.chatproject.models.Message
	 * @see br.com.danielteles.chatproject.models.MessageRoom
	 * @see br.com.danielteles.chatproject.models.MessageDeserializer
	 */
	@Test
	public void convertJsonToObject () throws JsonMappingException, JsonProcessingException{
		MessageRoom mock = new MessageRoom("sala1", Message.builder().from("daniel").text("text message").date(ZonedDateTime.now()).build());
		
		String json = mapper.writeValueAsString(mock);
		Message message = mapper.readValue(json , Message.class);
		
		assertTrue(message instanceof MessageRoom);
		assertEquals(mock.getRoomId(), ((MessageRoom) message).getRoomId());
		assertEquals(mock.getFrom(), message.getFrom());
		assertEquals(mock.getText(), message.getText());
		assertEquals(mock.getDate().toEpochSecond(), message.getDate().toEpochSecond());
	}
	
	/** 
	 * Teste para criação de objetos do tipo MessageRoom apartir de um JSON utilizando um deserializer, o MessageDeserializer, 
	 * com atributos Nulos
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 * 
	 * @see br.com.danielteles.chatproject.models.Message
	 * @see br.com.danielteles.chatproject.models.MessageRoom
	 * @see br.com.danielteles.chatproject.models.MessageDeserializer
	 */
	@Test
	public void convertJsonToObjectValuesNull () throws JsonMappingException, JsonProcessingException{
		MessageRoom mock = new MessageRoom(null , Message.builder().from(null).text(null).date(null).build());
		
		String json = mapper.writeValueAsString(mock);
		Message message = mapper.readValue(json , Message.class);
		
		assertTrue(message instanceof MessageRoom);
		assertNull(((MessageRoom) message).getRoomId());
		assertNull(message.getFrom());
		assertNull(message.getText());
		assertEquals(ZonedDateTime.parse("1969-01-01T00:00:00+00:00").toEpochSecond(), message.getDate().toEpochSecond());
	}
}
