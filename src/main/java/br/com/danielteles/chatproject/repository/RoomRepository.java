package br.com.danielteles.chatproject.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.danielteles.chatproject.models.Room;

public interface RoomRepository extends MongoRepository<Room, String> {

}
