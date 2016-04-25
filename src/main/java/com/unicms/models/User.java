package com.unicms.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


public class User {
	
	private int id;

	@Size(min=6, max=50, message = "Please use between 6 and 50 characters.")
    private String username;
	
	@Size(min=6, max=30, message = "Please use password between 6 and 30 characters.")
	private String password;
	
	@ValidEmail(message = "Please use a valid email address.")
	private String email;
	
	@Size(min=3, max=50,  message = "Please use between 3 and 50 characters.")
	private String firstName;
	
	@Size(min=3, max=50, message = "Please use between 3 and 50 characters.")
	private String lastName;
	
	private String fullName;
	private boolean status;
	private String created_at;
	
	//role
	private int roleId;
	private String roleName;
	
	
	//getters and setters
	
	public String getFullName() {
		return this.getFirstName() + " " + this.getLastName();
	}

	public void setFullName(String firstName, String lastName) {
		this.fullName = firstName + ' ' +lastName;
	}
	
	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public boolean getStatus() {
		return status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

   
}