package com.example.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import com.example.entities.UserEntity;
import com.example.entities.UserTemp;
import com.example.repository.ExcelRepository;
import com.example.repository.UserRepository;

@Configuration
public class KafakaConsumer {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ExcelRepository excelRepository;

	@KafkaListener(topics = "userUpload", groupId = "bulkUpload")
	public void userBulk(UserTemp userTempObject) {

		try {

			// for avoid duplicate record
			UserEntity dataBaseEmail = userRepository
					.findByEmailIgnoreCase(userTempObject.getEmail());

			if ((null == dataBaseEmail)) {

				UserEntity userEntity = new UserEntity();
				userEntity.setName(userTempObject.getName());
				userEntity.setEmail(userTempObject.getEmail());
				userEntity.setUsername(userTempObject.getUsername());
				userEntity.setAddress(userTempObject.getAddress());
				userEntity.setIsActive(true);
				this.userRepository.save(userEntity);

				userTempObject.setStatus(true);
				excelRepository.save(userTempObject);

			} else {
				userTempObject.setStatus(false);
				excelRepository.save(userTempObject);

			}

		} catch (Exception e) {

		}

	}
}
