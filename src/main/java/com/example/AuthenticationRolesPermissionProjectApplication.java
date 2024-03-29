package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.properties.FileStorageProperties;

@SpringBootApplication
@EnableCaching
@EnableJms
@EnableConfigurationProperties({
    FileStorageProperties.class
})
//@EnableWebMvc

public class AuthenticationRolesPermissionProjectApplication {

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(AuthenticationRolesPermissionProjectApplication.class, args);
	}


	
}
