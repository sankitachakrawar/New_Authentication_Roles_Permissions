package com.example.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="userTemp")
public class UserTemp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="username")
	private String username;
	
	@Column(name="address")
	private String address;
	
	@Column(name="status")
	private boolean status=true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public UserTemp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserTemp(Long id, String name, String email, String username, String address, boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.address = address;
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserTemp [id=" + id + ", name=" + name + ", email=" + email + ", username=" + username + ", address="
				+ address + ", status=" + status + "]";
	}

	
	
	
	
}
