package br.com.danielteles.chatproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.ResponseEntity;

import br.com.danielteles.chatproject.exceptions.NotFoundException;
import br.com.danielteles.chatproject.models.Room;
import br.com.danielteles.chatproject.models.Room.RoomCapacity;
import br.com.danielteles.chatproject.repository.RoomRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
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
	
	@Order(1)
	@RepeatedTest(4)
	public void shouldCreateNewRoom() {
		roomPersisted.setId(null);
		ResponseEntity<Room> response = service.save(roomPersisted);
		assertNotNull(response, "response cannot be null");
		var room = response.getBody();
		assertNotNull(room, "response.body cannot be null");
		
		assertEquals(roomPersisted.getName(), room.getName());
		assertEquals(roomPersisted.getCapacity(), room.getCapacity());
		roomPersisted = room;
	} 
	
	@Test
	@Order(2)
	public void shouldExistRoom() throws NotFoundException {
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
	public void shouldUpdateRoom() throws NotFoundException {
		roomPersisted.setName("sala02");
		roomPersisted.setCapacity(RoomCapacity.MEDIUM);
		
		ResponseEntity<Room> response = service.save(roomPersisted);
		assertNotNull(response, "response cannot be null");
		
		var room = response.getBody();
		assertNotNull(room, "response.body cannot be null");
		
		assertEquals(roomPersisted.getId(), room.getId());
		assertEquals(roomPersisted.getName(), room.getName());
		assertEquals(roomPersisted.getCapacity(), room.getCapacity());
	}
	
	@Test
	@Order(4)
	public void shouldThrowsExceptionWhenNotFindingValue() {
		assertThrows(NotFoundException.class, () -> {
			service.findById(BigInteger.valueOf(Long.MAX_VALUE));
		});
	}
	
	@Order(5)
	@ParameterizedTest
	@ValueSource(ints = {0, 1})
	public void shouldReturnRoomsPagined(int page) {
		var count = 2;
		ResponseEntity<List<Room>> response = service.find(page, count);
		assertNotNull(response, "response cannot be null");
		var rooms = response.getBody();
		assertNotNull(rooms, "response.body cannot be null");
		assertEquals(count, rooms.size());
		assertNotNull(rooms.get(0).getName(), "room.name cannot be null");
		assertNotNull(rooms.get(0).getCapacity(), "room.capacity cannot be null");
	}
	

	@Test
	@Order(6)
	public void shouldRemoveRoom() {
		ResponseEntity<String> response = service.delete(roomPersisted.getId());
		assertNotNull(response, "response cannot be null");
		assertThrows(NotFoundException.class, () -> {
			service.findById(roomPersisted.getId());
		});
	}
}
