package br.com.danielteles.chatproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import br.com.danielteles.chatproject.repository.RoomRepository;

@Configuration
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@EnableMongoRepositories(basePackageClasses = {RoomRepository.class})
public class MongoDBConnectionConfig {//implements MongoDatabaseFactory {
	
	@Autowired
	private Environment env;
//
//	@Override
//	protected String getDatabaseName() {
//		return env.getProperty("MONGO_DATABASE", "test");
//	}
	
//	@Bean
//	public MongoClientFactoryBean mongo() {
////		var factory = new MongoClientFactoryBean();
////		factory.setHost(env.getProperty("MONGO_HOST", "localhost"));
////		factory.setPort(env.getProperty("MONGO_PORT", Integer.class, 27017));
////		
////		bool auth = env.getProperty("MONGO_AUTH", "NO").toLowerCase().startsWith("y");
////		if(auth) {
////			factory.setCredential(new MongoCredential().
////					createCredential(env.getProperty("MONGO_USER", "admin"), 
////							env.getProperty("MONGO_DATABASE", "test"),
////							env.getProperty("MONGO_PASSWORD", "Password123")));			
////		}
//		
//		return null;
//	}
}
