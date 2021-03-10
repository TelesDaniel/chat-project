package br.com.danielteles.chatproject.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.danielteles.chatproject.models.Room;
import br.com.danielteles.chatproject.repository.RoomRepository;

/**
 * Camada de servico para salas
 * @see br.com.danielteles.chatproject.models.Room
 */

@Service
public class RoomService {

	private final Logger log = LoggerFactory.getLogger(RoomService.class);
	
	private RoomRepository repository;
	
	public RoomService(RoomRepository repository) {
		this.repository = repository;
	}
	
	/**
	 * Metodo para criacao e persistencia de uma novas Sala 
	 * @return {@link String} room id
	 */
	public ResponseEntity<String> create(Room room) {
		try {
			room = repository.save(room);		
			return ResponseEntity.ok(room.getId());
		}catch (Exception e) {
			var msg = "Erro ao criar sala";
			log.error(msg, e);
			return new ResponseEntity<String>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Metodo para busca paginada de salas
	 */
	public ResponseEntity<List<Room>> find(int page, int count) {
		
		try {
			Page<Room> rooms = repository.findAll(PageRequest.of(page, count, Sort.by("id")));	
			return ResponseEntity.ok(rooms.toList());
		}catch (Exception e) {
			var msg = "Erro ao buscar salas";
			log.error(msg, e);
			return new ResponseEntity<List<Room>>(List.of(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Metodo para remocaode salas
	 */
	public void delete(Room room) {
		repository.delete(room);
	}
}
