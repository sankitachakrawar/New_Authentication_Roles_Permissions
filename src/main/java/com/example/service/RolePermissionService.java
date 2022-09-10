package com.example.service;

import com.example.dto.AssignPermission;

public interface RolePermissionService {

	void addPermissionToRole(AssignPermission assignPermission);
	
	//Page<RolePermissionEntity> getAllRolesPermissions(String search, String from, String to);
}
