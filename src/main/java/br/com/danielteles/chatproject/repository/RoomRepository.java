package br.com.danielteles.chatproject.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import br.com.danielteles.chatproject.models.Room;

public interface RoomRepository extends ArangoRepository<Room, String> {

}
