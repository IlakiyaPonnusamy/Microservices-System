package com.example.ems.department.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jspecify.annotations.Nullable;

import com.example.ems.department.dto.DepartmentRequestDto;
import com.example.ems.department.dto.DepartmentResponseDto;
import com.example.ems.department.entity.Department;

public interface DepartmentService {

	List<Department> getAllDepartments();

	Department createDepartment(DepartmentRequestDto request);

	Department getDepartmentById(Long id);

	void deleteById(Long id);

	List<DepartmentResponseDto> getActiveDepartments();

	List<DepartmentResponseDto> searchByLocation(String param);

	List<DepartmentResponseDto> sortingByFields();

	List<DepartmentResponseDto> fetchUsingJPQl(String name, Boolean active);

	Map<String, List<DepartmentResponseDto>> groupDepartments();


}
