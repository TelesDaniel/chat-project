package br.com.danielteles.chatproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDB.Builder;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.ArangoConfiguration;

import br.com.danielteles.chatproject.repository.RoomRepository;

@Configuration
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@EnableArangoRepositories(basePackageClasses = {RoomRepository.class})
public class ArangoDBConnectionConfig implements ArangoConfiguration {
	
	@Autowired
	private Environment env;
	
	@Override
	public Builder arango() {
		return new ArangoDB.Builder()
				.host(env.getProperty("ARANGO_HOST", "localhost"),
				env.getProperty("ARANGO_PORT", Integer.class, 8529))
				.user(env.getProperty("ARANGO_USER", "root"))
				.password(env.getProperty("ARANGO_PASSWORD", ""));
	}

	@Override
	public String database() {
		return env.getProperty("ARANGO_DATABASE", "_system");
	}

}
