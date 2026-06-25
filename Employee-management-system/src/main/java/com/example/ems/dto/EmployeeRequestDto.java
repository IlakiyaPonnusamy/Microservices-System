package com.example.ems.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EmployeeRequestDto {
	
	private Long id;
	private String employeeId;
	private String firstName;
	private String lastName;
	private String designation;
	private Double salary;
	private String email;
	private String mobileNumber;
	private String department;
	private LocalDate dateOfJoining;
	private String status;

}
