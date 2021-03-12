package br.com.danielteles.chatproject.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.danielteles.chatproject.models.Room;

public interface RoomRepository extends MongoRepository<Room, BigInteger> {

}
