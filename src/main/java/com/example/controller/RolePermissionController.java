package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.AssignPermission;
import com.example.dto.ErrorResponseDto;
import com.example.dto.ListResponseDto;
import com.example.dto.RolePermissionDto;
import com.example.dto.SuccessResponseDto;
import com.example.entities.RolePermissionEntity;
import com.example.entities.UserRoleEntity;
import com.example.exceptionHandling.ResourceNotFoundException;
import com.example.service.RolePermissionService;
import com.example.service.RoleService;

@RestController
@RequestMapping("/rolePermission")
public class RolePermissionController {

	@Autowired
	private RolePermissionService rolePermissionService;
	
	@Autowired
	private RoleService roleService;
	
	@PreAuthorize("hasRole('assignPermissionToRole')")
	@PostMapping()
	public ResponseEntity<?> assignPermissionToRole(@RequestBody AssignPermission assignPermission,HttpServletRequest request){
		
		try {
			rolePermissionService.addPermissionToRole(assignPermission);
			return new ResponseEntity<>(new SuccessResponseDto("Permission Assign To Role","PermissionAssignToRole",null),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(new ErrorResponseDto("Permission not assign to Role","PermissionNotAssignToRole"),HttpStatus.BAD_REQUEST);
		}
		
	}
	

	
	@PreAuthorize("hasRole('getRoleAndPermissionById')")
	@GetMapping("/{id}")
	public ResponseEntity<?> getRoleAndPermissionById(@PathVariable(value = "id") Long roleId) {

		try {
			System.out.println("Rolepermission>>  ");
			RolePermissionDto rolePermissionData = roleService.getRoleAndPermissionById(roleId);
			System.out.println("rolePermissionData>>  "+rolePermissionData);
			
			return new ResponseEntity<>(new SuccessResponseDto("Success", "success", rolePermissionData), HttpStatus.OK);

		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "roleNotFound"), HttpStatus.NOT_FOUND);

		}

	}
}
























//@PreAuthorize("hasRole('getAllRolesPermissions')")
//@GetMapping()
//public ResponseEntity<?> getAllRolesPermissions(@RequestParam(defaultValue = "") String search,
//		@RequestParam(defaultValue = "1") String pageNo, @RequestParam(defaultValue = "25") String size){
//	
//	Page<RolePermissionEntity> rolePermissionEntity=rolePermissionService.getAllRolesPermissions(search, pageNo, size);
//	System.out.println("role>>"+rolePermissionEntity);
//	if (rolePermissionEntity.getTotalElements() != 0) {
//		return new ResponseEntity<>(new SuccessResponseDto("Success", "success",
//				new ListResponseDto(rolePermissionEntity.getContent(), rolePermissionEntity.getTotalElements())), HttpStatus.OK);
//	}
//	return new ResponseEntity<>(new ErrorResponseDto("Data Not Found", "dataNotFound"), HttpStatus.NOT_FOUND);
//}
