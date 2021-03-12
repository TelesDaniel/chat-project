package br.com.danielteles.chatproject.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClientSettings.Builder;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import br.com.danielteles.chatproject.repository.RoomRepository;

@Configuration
@EnableMongoRepositories(basePackageClasses = {RoomRepository.class})
public class MongoDBConnectionConfig extends AbstractMongoClientConfiguration {
	
	@Autowired
	private Environment env;

	@Override
	protected String getDatabaseName() {
		return env.getProperty("MONGO_DATABASE", "admin");
	}
	
	@Override
	protected void configureClientSettings(Builder builder) {
		
		builder.applyToClusterSettings(settings -> {
			settings.hosts(List.of(
					new ServerAddress(env.getProperty("MONGO_HOST", "localhost"), 
							env.getProperty("MONGO_PORT", Integer.class, 27017))));
		});
		
		boolean auth = env.getProperty("MONGO_AUTH", "NO").toLowerCase().startsWith("y");
		if(auth) {
			builder.credential(
					MongoCredential.createCredential(
						env.getProperty("MONGO_USER", "admin"), 
						env.getProperty("MONGO_DATABASE", "admin"),
						env.getProperty("MONGO_PASSWORD", "Password123").toCharArray()));			
		}
	}
}
