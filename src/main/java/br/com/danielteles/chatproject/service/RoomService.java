package br.com.danielteles.chatproject.service;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.danielteles.chatproject.exceptions.NotFoundException;
import br.com.danielteles.chatproject.models.Room;
import br.com.danielteles.chatproject.repository.RoomRepository;

/**
 * Camada de servico para salas
 * @see br.com.danielteles.chatproject.models.Room
 */

@Service
public class RoomService {

	private RoomRepository repository;
	
	public RoomService(RoomRepository repository) {
		this.repository = repository;
	}

	public ResponseEntity<Room> create(Room room) {
		Room persisted = repository.save(room);
		return new ResponseEntity<Room>(persisted, HttpStatus.CREATED);
	}

	public ResponseEntity<Room> findById(BigInteger roomId) throws NotFoundException {
		Optional<Room> optional = repository.findById(roomId);
		if(!optional.isPresent())
			throw new NotFoundException();
		return new ResponseEntity<Room>(optional.get(), HttpStatus.CREATED);
	}
}
