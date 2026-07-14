package com.example.ems.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ems.dto.DepartmentResponseDto;

@FeignClient(name = "department-service")
public interface DepartmentClient {

	@GetMapping("/api/department/{departmentId}")
	DepartmentResponseDto getDepartmentById(@PathVariable("departmentId") Long departmentId);
}
