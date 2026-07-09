package com.example.ems.auth.dto;

public class UserDto {

	private String userName;

	private String role;

	public UserDto() {
	}

	public UserDto(String userName, String role) {
		super();
		this.userName = userName;
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
