package br.com.danielteles.chatproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.arangodb.model.CollectionCreateOptions;
import com.arangodb.springframework.core.ArangoOperations;

@Configuration
@Order(value = Ordered.LOWEST_PRECEDENCE)
public class ArangoDBCollectionsConfig {
	
	@Autowired
	private ArangoOperations template;
	
	@Bean
	public void configureDatabase() {
		template.collection("rooms", new CollectionCreateOptions().waitForSync(false));
	}
}
