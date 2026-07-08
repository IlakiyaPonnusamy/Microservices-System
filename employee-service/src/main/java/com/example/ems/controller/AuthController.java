package com.example.ems.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ems.dto.AuthRequestDto;
import com.example.ems.dto.AuthResponseDto;
import com.example.ems.security.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;

	private final JwtService jwtService;

	public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
		this.authenticationManager = authenticationManager;
		this.jwtService = new JwtService();
	}
	
	@PostMapping("/login")
	public AuthResponseDto login(@RequestBody AuthRequestDto request) {

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassWord()));
// internally authenticating using userdetails service, Spring automatically calls it. validating user present in db
		
		String token = jwtService.generateToken(request.getUserName());

		return new AuthResponseDto(token);
	}

}
