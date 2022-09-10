package com.example.dto;

import java.util.List;



public class EntityDto {

	private String entityName;
	
	private String description;
	
	private List<EntityPermissionDto> permissions;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public List<EntityPermissionDto> getPermissions() {

		return permissions;

	}

	public void setPermissions(List<EntityPermissionDto> permissions) {

		this.permissions = permissions;

	}
	
	public EntityDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EntityDto(String entityName, String description, List<com.example.dto.EntityPermissionDto> permissions) {
		super();
		this.entityName = entityName;
		this.description = description;
		this.permissions = permissions;
	}

	
	
	
}
