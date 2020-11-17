package com.oauth.jwtoperations.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oauth.jwtoperations.model.ApplicationUsers;
import com.oauth.jwtoperations.repositories.ApplicationUsersRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private ApplicationUsersRepository aup;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUsers users = aup.findByuserName(username);
		return new User(users.getUsername(), users.getPassword(), new ArrayList<GrantedAuthority>());
	}

}
