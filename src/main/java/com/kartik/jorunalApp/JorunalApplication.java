package com.kartik.jorunalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
public class JorunalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JorunalApplication.class, args);
	}
	@Bean
	public PlatformTransactionManager lala(MongoDatabaseFactory db){
		return new MongoTransactionManager(db);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
