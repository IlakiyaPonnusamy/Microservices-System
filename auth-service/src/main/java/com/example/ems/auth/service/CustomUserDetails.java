package com.example.ems.auth.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.ems.auth.entity.Users;

/*UserDetails Spring Interface
helps to convert Users → CustomUserDetails → Spring Security
*/public class CustomUserDetails implements UserDetails{
 
	private Users user;

    public CustomUserDetails(Users user) {
        this.user = user;
    }

    //Returns encrypted password from database
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

}
