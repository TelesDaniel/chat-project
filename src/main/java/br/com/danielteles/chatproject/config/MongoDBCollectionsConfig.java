package br.com.danielteles.chatproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import br.com.danielteles.chatproject.models.Room;
import com.mongodb.client.*;

@Configuration
@Order(value = Ordered.LOWEST_PRECEDENCE)
public class MongoDBCollectionsConfig {
	
	@Autowired
	private MongoTemplate operations;
	
	@Bean
	public void configureDatabase() {
		operations.createCollection("rooms");
	}
}
