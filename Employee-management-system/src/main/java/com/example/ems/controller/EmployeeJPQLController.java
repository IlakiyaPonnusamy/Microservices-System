package com.example.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ems.entity.Employee;
import com.example.ems.service.EmployeeJPQLService;

@RestController
@RequestMapping("/employy")
public class EmployeeJPQLController {

	@Autowired
	EmployeeJPQLService employeeService;

	@GetMapping("/department")
	public List<Employee> getEmployeByDept(@RequestParam String department) {
		return employeeService.getEmployeByDept(department);

	}
}
