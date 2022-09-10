package com.example.service;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.example.dto.ChangePasswordDto;
import com.example.dto.ForgotPasswordDto;
import com.example.dto.IPermissionDto;
import com.example.dto.IUserDto;
import com.example.dto.UserDto;
import com.example.entities.UserEntity;
import com.example.exceptionHandling.ResourceNotFoundException;

public interface UserService {

	public void addUsers(UserDto userDto);
	
	Page<IUserDto> getAllUsers(String search, String from, String to);
	
	UserEntity updateUser(UserEntity user, Long id);
	
	void deleteUsers(Long id);

	public UserEntity findByEmail(String email);

	Boolean comparePassword(String password, String hashPassword);
	
	void forgotPasswordConfirm(@NotNull(message = "token is Required*tokenRequired") String token,
			@Valid ForgotPasswordDto userBody, HttpServletRequest request);
	
	List<IPermissionDto> getUserPermission(Long userId) throws IOException;
	
	void changePassword(Long userId, ChangePasswordDto userBody, HttpServletRequest request) throws ResourceNotFoundException;

	public UserEntity getUsers(Long id);
	
	public UserDto fetchUserById(Long id);
	
}
