package com.example.ems.auth.service.impl;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.ems.auth.dto.UserDto;
import com.example.ems.auth.entity.Users;
import com.example.ems.auth.repository.UsersRepository;
import com.example.ems.auth.service.UserService;



@Service
public class UserServiceImpl implements UserService {

	private final UsersRepository userRepo;

	public UserServiceImpl(UsersRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')") // only admin role user can delete
	public void deleteById(Long id) {
		if (!userRepo.existsById(id)) {
			return;
		}

		userRepo.deleteById(id);
	}

	@Override
	@PreAuthorize("hasRole('USERS')")
	public UserDto updateRole(String usrnm, String role) {
		return userRepo.findByUsername(usrnm).map(user -> {
			user.setRole(role);
			Users updateduser = userRepo.save(user);
			UserDto userDto = new UserDto();
			userDto.setUserName(updateduser.getUsername());
			userDto.setRole(updateduser.getRole());
			return userDto;
		}).orElseThrow(() -> new RuntimeException("User not found"));

	}

}
