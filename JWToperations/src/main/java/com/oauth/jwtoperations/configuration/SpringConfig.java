package com.oauth.jwtoperations.configuration;

import org.aspectj.weaver.patterns.AnyTypePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import com.oauth.jwtoperations.jwtutility.Filter;
import com.oauth.jwtoperations.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
@Service
public class SpringConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomUserDetailService userDetailService;
	
	@Autowired
	private Filter filter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService); 
	}
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}   
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http	
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/getToken")
			.permitAll()
			.antMatchers(HttpMethod.OPTIONS,"/**")
			.permitAll()
			.antMatchers("/userRegister")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.exceptionHandling()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http
			.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		http
			.cors().disable();
	}
}
