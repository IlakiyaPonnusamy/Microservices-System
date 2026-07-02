package com.example.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ems.entity.Users;
import com.example.ems.repository.UsersRepository;

@RestController
public class UserController {

	private final UsersRepository usersRepo;

	public UserController(UsersRepository usersRepository) {
		this.usersRepo = usersRepository;
	}

	@Autowired
	PasswordEncoder passwordEncoder;

	

	@PostMapping("/register")
	public String registerUser(@RequestBody Users user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		usersRepo.save(user);

		return "User registered successfully";
	}

}
