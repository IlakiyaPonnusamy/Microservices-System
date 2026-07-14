package com.example.ems.serviceImplementation;

import org.springframework.stereotype.Service;

import com.example.ems.dto.DepartmentResponseDto;
import com.example.ems.exception.DepartmentNotFoundException;
import com.example.ems.service.DepartmentClient;

import feign.FeignException;

@Service
public class DepartmentValidationService {

	private final DepartmentClient departmentClient;

	public DepartmentValidationService(DepartmentClient departmentClient) {
		this.departmentClient = departmentClient;
	}

	public DepartmentResponseDto validateDepartment(Long departmentId) {

		validateDepartmentIdValue(departmentId);

		try {

			DepartmentResponseDto department = departmentClient.getDepartmentById(departmentId);

			if (department == null) {
				throw new DepartmentNotFoundException("Department not found with id: " + departmentId);
			}

			return department;

		} catch (FeignException.NotFound exception) {

			throw new DepartmentNotFoundException("Department not found with id: " + departmentId);

		}
	}

	private void validateDepartmentIdValue(Long departmentId) {

		if (departmentId == null) {
			throw new IllegalArgumentException("Department ID is required");
		}

		if (departmentId <= 0) {
			throw new IllegalArgumentException("Department ID must be greater than zero");
		}
	}
}
