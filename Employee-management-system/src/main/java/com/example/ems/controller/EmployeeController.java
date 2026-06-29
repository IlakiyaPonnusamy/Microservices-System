package com.example.ems.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ems.dto.EmployeeRequestDto;
import com.example.ems.entity.Employee;
import com.example.ems.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	// field injection
	@Autowired
	EmployeeService empService;

	@PostMapping("/createEmployee")
	public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeRequestDto request) {
		Employee employee =empService.createEmployee(request);
		
		return ResponseEntity.status(HttpStatus.CREATED)
                .body(employee);


	}

	@GetMapping("/{id}")
	public Optional<Employee> getEmployebyId(@PathVariable Long id) {

		return empService.getEmployebyId(id);

	}

	@GetMapping("/idList")
	public List<Employee> getEmpList() {

		return empService.getEmpList();

	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequestDto emp) {
		Employee updatedEmp = empService.updateEmployee(id, emp);
		return ResponseEntity.ok(updatedEmp);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Employee> updateEmployeeln(@PathVariable Long id, @RequestBody String ln) {
		Employee updatedEmp = empService.updateEmployeeLastname(id, ln);
		return ResponseEntity.ok(updatedEmp);
	}

	@DeleteMapping("/id")
	public void deleteById(@PathVariable Long id) {
		empService.deleteById(id);
	}
}
