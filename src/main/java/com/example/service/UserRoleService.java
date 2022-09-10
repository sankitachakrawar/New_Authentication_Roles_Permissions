package com.example.service;

import com.example.dto.AssignRole;


public interface UserRoleService {

	void addRoleToUser(AssignRole assignRole);
	
	//Page<UserRoleEntity> getAllUserRoles(String search, String from, String to);
}
