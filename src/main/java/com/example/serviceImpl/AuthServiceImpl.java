package com.example.serviceImpl;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.SPRequestEntity;
import com.example.entities.UserEntity;
import com.example.repository.AuthRepository;
import com.example.repository.SPRequestRepository;
import com.example.repository.UserRepository;
import com.example.service.AuthService;


@Service
public class AuthServiceImpl implements AuthService{

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthRepository authRepository;
	
	@Override
	public UserEntity loginUser(String email, String password) throws Exception {
		UserEntity userEntity=userRepository.getUserByEmail(email);
		if(userEntity == null) {
			throw new Exception("You entered incorrect Email.");
		}else {
			if (userEntity.getEmail().equals(email) && userEntity.getPassword().equals(password)) {
				return userEntity;
			}
			throw new Exception("You entered incorrect password.");
		}
	}

	@Autowired
	private SPRequestRepository repository;
	
	@Override
	public Optional<SPRequestEntity> getUserById(Long id) {
		return repository.getuser(id);
		 
	}

	

}
