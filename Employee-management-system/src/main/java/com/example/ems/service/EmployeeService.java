package com.example.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ems.dto.EmployeeRequestDto;
import com.example.ems.entity.Employee;

@Service
public interface EmployeeService {

	Employee createEmployee(EmployeeRequestDto request);

	Optional<Employee> getEmployebyId(Long id);

	List<Employee> getEmpList();

	Employee updateEmployee(Long id, EmployeeRequestDto emp);

	Employee updateEmployeeLastname(Long id, String ln);

	void deleteById(Long id);


}
