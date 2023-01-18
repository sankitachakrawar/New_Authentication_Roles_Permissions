package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;

@EnableKafka
@Configuration
public class KafkaProducer {

	public static final String USER = "userUpload";
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	public void addUsersToUsersMainTable(Object obj) {

		kafkaTemplate.send(USER, obj);

	}
}
