package com.example.ems.auth.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ems.auth.dto.AuthRequestDto;
import com.example.ems.auth.dto.AuthResponseDto;
import com.example.ems.auth.entity.Users;
import com.example.ems.auth.repository.UsersRepository;
import com.example.ems.auth.security.JwtService;

@RestController
public class UserController {

	// constructor injection
	private final AuthenticationManager authenticationManager;

	private final JwtService jwtService;

	private final PasswordEncoder passwordEncoder;

	private final UsersRepository usersRepo;

	public UserController(AuthenticationManager authenticationManager, JwtService jwtService,
			PasswordEncoder passwordEncoder, UsersRepository usersRepo) {
		this.authenticationManager = authenticationManager;
		this.jwtService = new JwtService();
		this.passwordEncoder = passwordEncoder;
		this.usersRepo = usersRepo;
	}

	// user register for 1st time
	@PostMapping("/register")
	public String registerUser(@RequestBody Users user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		usersRepo.save(user);

		return "User registered successfully";
	}

	// to generate token
	@PostMapping("/login")
	public AuthResponseDto login(@RequestBody AuthRequestDto request) {

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassWord()));
		// internally authenticating using userdetails service, Spring automatically
		// calls it. validating user present in db

		String token = jwtService.generateToken(request.getUserName());

		return new AuthResponseDto(token);
	}

}
