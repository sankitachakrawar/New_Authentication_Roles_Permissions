//package com.example.controller;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.dto.ErrorResponseDto;
//import com.example.dto.RoleUserDto;
//import com.example.dto.SuccessResponseDto;
//import com.example.exceptionHandling.ResourceNotFoundException;
//import com.example.service.RoleService;
//@RestController
//@RequestMapping("/roleUser")
//public class RoleUserController {
//
//	@Autowired
//	private RoleService roleService;
	
//	@PreAuthorize("hasRole('getRoleAndUserById')")
//	@GetMapping("/{id}")
//	public ResponseEntity<?> getRoleAndUserById(@PathVariable(value = "id") Long id) {
//
//		try {
//
//			RoleUserDto roleUserData = roleService.getRoleAndUserById(id);
//			return new ResponseEntity<>(new SuccessResponseDto("Success", "success", roleUserData), HttpStatus.OK);
//
//		} catch (ResourceNotFoundException e) {
//
//			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "roleNotFound"), HttpStatus.NOT_FOUND);
//
//		}
//
//	}
//}
