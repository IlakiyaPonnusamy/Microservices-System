package com.example.ems.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ems.auth.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{

	//Jpql query
	@Query("SELECT u FROM Users u WHERE u.username = :username")
	Optional<Users> findByUsername(@Param("username") String username);
}
