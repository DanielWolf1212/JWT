package com.oauth.jwtoperations;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.oauth.jwtoperations.model.ApplicationUsers;
import com.oauth.jwtoperations.repositories.ApplicationUsersRepository;

@SpringBootApplication
public class JwToperationsApplication {

	@Autowired
	private ApplicationUsersRepository applicationUsersRepository;
	
	@PostConstruct
	public void initUsers() {
		List<ApplicationUsers> l= new ArrayList<ApplicationUsers>();
		ApplicationUsers apl=new ApplicationUsers();
		apl.setUsername("Daniel");
		apl.setPassword("Daniel12@1");
		apl.setEmail("dukedaniel1212@gmail.com");
		l.add(apl);
		applicationUsersRepository.saveAll(l);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(JwToperationsApplication.class, args);
	}

}
