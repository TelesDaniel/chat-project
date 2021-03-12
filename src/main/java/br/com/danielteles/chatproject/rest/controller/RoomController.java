package br.com.danielteles.chatproject.rest.controller;

import java.util.List;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.danielteles.chatproject.models.Room;

@RestController
@RequestMapping("/api/room")
public class RoomController {
	
	//@Autowired
	//private RoomService service;

	@PutMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createRoom(@RequestBody Room room) {
		return null;
	}
	
	@GetMapping(path = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Room>> findRooms(
			@RequestParam @DefaultValue("0") int page, 
			@RequestParam @DefaultValue("5") int count) {
		return null;
	}
	
	@DeleteMapping(path = "/{id}")
	public void deleteRoom() {
	}
}
