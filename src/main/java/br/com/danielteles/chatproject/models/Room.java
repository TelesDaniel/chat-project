package br.com.danielteles.chatproject.models;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongodb.lang.NonNull;

import lombok.Builder;
import lombok.Data;

/**
 * Entidade que representa uma sala
 */

@Data
@Builder
@Document(collection = "rooms")
public class Room {
	
	@Id
	private BigInteger id;
	
	@NonNull
	private String name;
	
	@JsonIgnore
	private RoomType type;
	
	@JsonIgnore
	private String owner;
	
	@NonNull
	private RoomCapacity capacity;
	
	public static enum RoomType {
		PRIVATE(1), PUBLIC(0);
		
		int num;
		RoomType(int num) {
			this.num = num;
		}
	} 
	
	public static enum RoomCapacity {
		SMALL(5), MEDIUM(10), BIG(15);
		
		int num;
		RoomCapacity(int num) {
			this.num = num;
		}
	} 
}
