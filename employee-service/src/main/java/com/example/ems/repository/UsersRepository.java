package com.example.ems.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ems.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<Users, Integer>{

	//Jpql query
	@Query("SELECT u FROM Users u WHERE u.username = :username")
	Optional<Users> findByUsername(@Param("username") String username);
}
