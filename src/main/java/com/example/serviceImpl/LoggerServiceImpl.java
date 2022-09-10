package com.example.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.dto.LoggerDto;
import com.example.entities.LoggerEntity;
import com.example.entities.UserEntity;
import com.example.repository.LoggerRepository;
import com.example.service.LoggerServiceInterface;


//@Component
@Service("LoggerServiceImpl")
public class LoggerServiceImpl implements LoggerServiceInterface {

	
	
	public LoggerServiceImpl() {


	}
	
	@Autowired
	private LoggerRepository loggerRepository;

	@Override
	public void createLogger(LoggerDto loggerDto, UserEntity user) {

		LoggerEntity logger = new LoggerEntity();
		logger.setId(user);
		logger.setToken(loggerDto.getToken());
		logger.setCreatedAt(loggerDto.getCreatedAt());
		logger.setExpireAt(loggerDto.getExpireAt());
		loggerRepository.save(logger);

	}

	@Transactional
	@Override
	public void logoutUser(String token) {
		
		final String token1 = token.substring(7);
			loggerRepository.removeByToken(token1);
			//this.loggerRepository.delete(entity);
			
	}

	
	@Override
	public List<LoggerEntity> getAllDetails() {
		
		return this.loggerRepository.findAll();
	}
	
	
	
	
	
	
	
	
	
	

}













