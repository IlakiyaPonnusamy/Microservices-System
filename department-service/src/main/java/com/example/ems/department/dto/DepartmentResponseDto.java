package com.example.ems.department.dto;

public class DepartmentResponseDto {
	
	private String name;
	private String code;
	private String location;
	private Boolean active;
	
	
	public DepartmentResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DepartmentResponseDto(String name, String code, String location, Boolean active) {
		super();
		this.name = name;
		this.code = code;
		this.location = location;
		this.active = active;
	}
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
