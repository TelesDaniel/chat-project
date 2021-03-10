package br.com.danielteles.chatproject.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
	
	private long id;
	private String name;
}
