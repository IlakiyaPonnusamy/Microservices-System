package com.example.ems.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class EmployeUserController {
	
	@GetMapping
    public String getEmployees() {
        return "Employee data accessed successfully";
    }

}
