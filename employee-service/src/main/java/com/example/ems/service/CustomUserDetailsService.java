package com.example.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ems.entity.Users;
import com.example.ems.repository.UsersRepository;

/*UserDetailsService spring interface
database authentication main class*/
@Service
public class CustomUserDetailsService implements UserDetailsService{

	private final UsersRepository userRepository;

    CustomUserDetailsService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Spring Security always calls this
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Users user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + username));

        return new CustomUserDetails(user);
    }

}
