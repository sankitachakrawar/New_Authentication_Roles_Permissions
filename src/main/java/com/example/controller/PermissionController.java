package com.example.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.ErrorResponseDto;
import com.example.dto.PermissionRequestDto;
import com.example.dto.SuccessResponseDto;
import com.example.entities.PermissionEntity;
import com.example.exceptionHandling.ResourceNotFoundException;
import com.example.service.PermissionService;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@PreAuthorize("hasRole('addPermissions')")
	@PostMapping()
	public ResponseEntity<?> addPermissions(@RequestBody PermissionRequestDto permissionRequestDto){
		permissionService.addPermissions(permissionRequestDto);
		return new ResponseEntity<>(new SuccessResponseDto("Success", "success", null),HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('updatePermissions')")
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePermissions(@RequestBody PermissionRequestDto permissionRequestDto,@PathVariable Long id){
		try {

			permissionService.updatePermission(permissionRequestDto, id);
			return new ResponseEntity<>(new SuccessResponseDto("Success", "success", null), HttpStatus.OK);

		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "permissionNotFound"), HttpStatus.NOT_FOUND);

		}
	}
	
	@PreAuthorize("hasRole('deletePermissions')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePermission(@PathVariable Long id){
		this.permissionService.deletePermission(id);
		return new ResponseEntity<>("Permission Deleted",HttpStatus.OK);
	}
	
	
	
}
