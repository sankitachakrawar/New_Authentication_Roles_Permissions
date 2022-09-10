package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.AssignRole;
import com.example.dto.ErrorResponseDto;
import com.example.dto.ListResponseDto;
import com.example.dto.SuccessResponseDto;
import com.example.entities.UserRoleEntity;
import com.example.service.UserRoleService;

@RestController
@RequestMapping("/userRole")
public class UserRoleController {

	@Autowired
	private UserRoleService userRoleService;
	
	//@PreAuthorize("hasRole('assignRoleToUser')")
	@PostMapping()
	public ResponseEntity<?> assignRoleToUser(@RequestBody AssignRole assignRole,HttpServletRequest request){
		
		try {
			userRoleService.addRoleToUser(assignRole);
			return new ResponseEntity<>(new SuccessResponseDto("Role assign to user","Roleassigntouser",null),HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<>(new ErrorResponseDto("Role not assign to user","Rolenotassigntouser"),HttpStatus.BAD_REQUEST);
		}	
	}
	
	
//	@PreAuthorize("hasRole('getAllUSerRoles')")
//	@GetMapping()
//	public ResponseEntity<?> getAllUserRoles(@RequestParam(defaultValue = "") String search,
//			@RequestParam(defaultValue = "1") String pageNo, @RequestParam(defaultValue = "25") String size){
//		
//		Page<UserRoleEntity> userRoleDto=userRoleService.getAllUserRoles(search, pageNo, size);
//		System.out.println("userRole>>"+userRoleDto);
//		if (userRoleDto.getTotalElements() != 0) {
//			return new ResponseEntity<>(new SuccessResponseDto("Success", "success",
//					new ListResponseDto(userRoleDto.getContent(), userRoleDto.getTotalElements())), HttpStatus.OK);
//		}
//		return new ResponseEntity<>(new ErrorResponseDto("Data Not Found", "dataNotFound"), HttpStatus.NOT_FOUND);
//	}
}
