package com.example.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.ApiLogger;
import com.example.repository.ApiLoggerRepository;
import com.example.service.ApiLoggerService;
@Service("apiLoggerServiceImpl")
public class ApiLoggerServiceImpl implements ApiLoggerService{

	@Autowired
	private ApiLoggerRepository apiLoggerRepository;
	
	
	
	public ApiLoggerServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public void createApiLogger(ApiLogger apiLogger) {
		
		apiLoggerRepository.save(apiLogger);
	}

}
