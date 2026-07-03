package com.example.ems.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.ems.security.JwtAuthFilter;


@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
			throws Exception {

		http.csrf(csrf -> csrf.disable()) // CSRF token
				.authorizeHttpRequests(auth -> auth // who can access which APIs
						.requestMatchers("/swagger-ui/**", // where we can access the url
								"/v3/api-docs/**", "/swagger-ui.html")
						.permitAll().requestMatchers("/register", "/auth/login").permitAll()//Anyone can access/public api
																								
						.requestMatchers("/users", "/users/**").authenticated() // Login/authentication  required/protected API
						.anyRequest().authenticated() // All remaining APIs JWT authentication required
				)
				// JWT is stateless, so no session should be created
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				// Register JWT filter before Spring's username/password filter
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
