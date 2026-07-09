package com.example.ems.auth.service;

import com.example.ems.auth.dto.UserDto;

public interface UserService {

	void deleteById(Long id);

	UserDto updateRole(String usrnm, String role);

}
