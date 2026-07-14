package com.example.ems.department.dto;

import jakarta.validation.constraints.Pattern;

public class DepartmentRequestDto {

	private String name;
	@Pattern(regexp="^[0-9]{3}$", message="code should contain exactly 3 digits")
	private String code;
	private String location;
	private Boolean active;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
