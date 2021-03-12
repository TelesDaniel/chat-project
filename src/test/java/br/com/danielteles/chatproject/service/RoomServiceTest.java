package br.com.danielteles.chatproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;

import br.com.danielteles.chatproject.exceptions.NotFoundException;
import br.com.danielteles.chatproject.models.Room;
import br.com.danielteles.chatproject.models.Room.RoomCapacity;
import br.com.danielteles.chatproject.repository.RoomRepository;

@SpringBootTest
public class RoomServiceTest {

	private  RoomService service;
	private static Room roomPersisted;
	static {
		roomPersisted = Room.builder().name("sala01").capacity(RoomCapacity.SMALL).build();
	}
	
	@Autowired
	public RoomServiceTest(RoomRepository repository) {
		this.service = new RoomService(repository);
	}
	
	@Test
	@Order(1)
	public void shouldCreateNewRoom() {
		ResponseEntity<Room> response = service.create(roomPersisted);
		assertNotNull(response, "response cannot be null");
		var room = response.getBody();
		assertNotNull(room, "response.body cannot be null");
		
		assertEquals(roomPersisted.getName(), room.getName());
		assertEquals(roomPersisted.getCapacity(), room.getCapacity());
		roomPersisted = room;
	} 
	
	@Test
	@Order(2)
	public void shouldExistRoomOnDb() throws NotFoundException {
		ResponseEntity<Room> response = service.findById(roomPersisted.getId());
		
		assertNotNull(response, "response cannot be null");
		var room = response.getBody();
		assertNotNull(room, "response.body cannot be null");
		
		assertEquals(roomPersisted.getId(), room.getId());
		assertEquals(roomPersisted.getName(), room.getName());
		assertEquals(roomPersisted.getCapacity(), room.getCapacity());
	}
	
	@Test
	@Order(3)
	public void shouldThrowsExceptionWhenNotFindingValue() {
		assertThrows(NotFoundException.class, () -> {
			service.findById(BigInteger.valueOf(Long.MAX_VALUE));
		});
	}
}
