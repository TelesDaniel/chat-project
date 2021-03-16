package br.com.danielteles.chatproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import br.com.danielteles.chatproject.repository.RoomRepository;

@SpringBootApplication
@ComponentScan("br.com.danielteles.chatproject")
@EnableMongoRepositories(basePackageClasses = {RoomRepository.class})
public class MainApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
}
