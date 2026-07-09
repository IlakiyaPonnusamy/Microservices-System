package com.example.ems.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ems.auth.dto.UserDto;
import com.example.ems.auth.service.UserService;

@RestController
@RequestMapping("/users")
public class AuthController {

	// field injection
	@Autowired
	UserService userService;

	// user access with valid token
	@GetMapping
	public String getEmployees() {
		return "Employee data accessed successfully";
	}

	@PatchMapping("/updateRole/{usrnm}")
	public UserDto updateRole(@PathVariable String usrnm, @RequestBody String role) {

		UserDto usrDto = userService.updateRole(usrnm, role);
		return usrDto;

	}
	
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		userService.deleteById(id);
		System.out.println("User deleted succesfully by ADMIN, Userid" + ":" + id);
	}
}
