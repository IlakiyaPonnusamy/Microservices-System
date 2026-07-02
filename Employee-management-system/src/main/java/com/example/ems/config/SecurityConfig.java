package com.example.ems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .csrf(csrf -> csrf.disable())                        //CSRF token 
            .authorizeHttpRequests(auth -> auth                  // who can access which APIs
            		 .requestMatchers(
                             "/swagger-ui/**",                   //where we can access the url
                             "/v3/api-docs/**",
                             "/swagger-ui.html"
                     ).permitAll()  
            		 .requestMatchers("/register").permitAll()   //if match apply rule/Anyone can access/public api
            		 .requestMatchers("/users", "/users/**").authenticated() //Login required/protected API
                    .anyRequest().authenticated()                //All remaining APIs rule applies
            )
            .httpBasic(Customizer.withDefaults());    //Basic Authentication username/password

        return http.build();
    }
}
