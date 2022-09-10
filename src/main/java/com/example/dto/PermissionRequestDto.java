package com.example.dto;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PermissionRequestDto {

	

	@NotBlank(message = "Action Name is Required*actionNameRequired")
	@NotEmpty(message = "Action Name is Required*actionNameRequired")
	@NotNull(message = "Action Name is Required*actionNameRequired")
	public String actionName;

	@NotBlank(message = "Base URL is Required*baseUrlRequired")
	@NotEmpty(message = "Base URL is Required*baseUrlRequired")
	@NotNull(message = "Base URL is Required*baseUrlRequired")
	public String baseUrl;

	@NotBlank(message = "Description is Required*descriptionRequired")
	@NotEmpty(message = "Description is Required*descriptionRequired")
	@NotNull(message = "Description is Required*descriptionRequired")
	public String description;

	@NotBlank(message = "Method is Required*methodRequired")
	@NotEmpty(message = "Method is Required*methodRequired")
	@NotNull(message = "Method is Required*methodRequired")
	public String method;

	public String path;

	
	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@NotNull(message = "EntityId is Required*entityIdRequired")
	public Long entityId;

	public Long getEntityId() {
		return entityId;
	}
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public PermissionRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PermissionRequestDto(
			@NotBlank(message = "Action Name is Required*actionNameRequired") @NotEmpty(message = "Action Name is Required*actionNameRequired") @NotNull(message = "Action Name is Required*actionNameRequired") String actionName,
			@NotBlank(message = "Base URL is Required*baseUrlRequired") @NotEmpty(message = "Base URL is Required*baseUrlRequired") @NotNull(message = "Base URL is Required*baseUrlRequired") String baseUrl,
			@NotBlank(message = "Description is Required*descriptionRequired") @NotEmpty(message = "Description is Required*descriptionRequired") @NotNull(message = "Description is Required*descriptionRequired") String description,
			@NotBlank(message = "Method is Required*methodRequired") @NotEmpty(message = "Method is Required*methodRequired") @NotNull(message = "Method is Required*methodRequired") String method,
			String path, @NotNull(message = "EntityId is Required*entityIdRequired") Long entityId) {
		super();
		this.actionName = actionName;
		this.baseUrl = baseUrl;
		this.description = description;
		this.method = method;
		this.path = path;
		this.entityId = entityId;
	}

	
	
}
