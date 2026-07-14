package com.example.ems.department.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ems.department.dto.DepartmentRequestDto;
import com.example.ems.department.dto.DepartmentResponseDto;
import com.example.ems.department.entity.Department;
import com.example.ems.department.service.DepartmentService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/department")
public class DepartmentController {

	private final DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@GetMapping("/getAll")
	public List<Department> getAllDepartment() {
		return departmentService.getAllDepartments();

	}

	@GetMapping("/{id}")
	public Department getDepartmentById(@PathVariable Long id) {
		return departmentService.getDepartmentById(id);
	}

	@PostMapping("/create")
	public ResponseEntity<Department> createDepartment(@Valid @RequestBody DepartmentRequestDto request) {

		Department dept = departmentService.createDepartment(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(dept);

	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		departmentService.deleteById(id);
	}

	@GetMapping("/getActive")
	public List<DepartmentResponseDto> getActiveDepartments() {
		
		return departmentService.getActiveDepartments();
	}
	
	@GetMapping("/searchBy")
	public List<DepartmentResponseDto> searchByLocation(@RequestParam String param) {

		return departmentService.searchByLocation(param);
	}
	
	@GetMapping("/fieldSorting")
	public List<DepartmentResponseDto> sortingByFields() {

		return departmentService.sortingByFields();

	}
	
	@GetMapping("/jpql")
	public ResponseEntity<List<DepartmentResponseDto>> fetchUsingJPQl(@RequestParam String name, @RequestParam Boolean active) {

		List<DepartmentResponseDto> response = departmentService.fetchUsingJPQl(name, active);
		return ResponseEntity.ok(response);

	}
	
	@GetMapping("/group-by")
	public ResponseEntity<Map<String, List<DepartmentResponseDto>>> groupDepartments() {

		Map<String, List<DepartmentResponseDto>> mapList = departmentService.groupDepartments();
		return ResponseEntity.ok(departmentService.groupDepartments());
	}
}
