package com.oauth.jwtoperations.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.jwtoperations.jwtutility.JwtUtility;
import com.oauth.jwtoperations.model.ApplicationUsers;
import com.oauth.jwtoperations.model.AuthModel;
import com.oauth.jwtoperations.repositories.ApplicationUsersRepository;

@RestController
@CrossOrigin
public class Controllers {

	@Autowired
	private ApplicationUsersRepository apur;
	@Autowired
	private JwtUtility jwtUtility;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/")
	public String Home() {
		return "application is running";
	}
	
	@GetMapping("/getUser")
	public List<ApplicationUsers> getUser(){
		return apur.findAll();
	}

	@PostMapping("/userRegister")
	public String userRegister(@RequestBody ApplicationUsers apu) {
		if(apur.save(apu)!=null) {
			return "Registered success";
		}
		else {
			return "Failed";
		}
	}
	
	@PostMapping("/getToken")
	public String generateToken(@RequestBody AuthModel authModel) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authModel.getUsername(), authModel.getPassword()));
		} catch (Exception e) {
			throw new Exception("invalid username and password");
		}
		return jwtUtility.generateToken(authModel.getUsername());
	}
}
