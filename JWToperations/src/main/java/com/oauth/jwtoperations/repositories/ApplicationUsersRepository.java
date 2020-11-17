package com.oauth.jwtoperations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oauth.jwtoperations.model.ApplicationUsers;

public interface ApplicationUsersRepository extends JpaRepository<ApplicationUsers, Integer> {

	ApplicationUsers findByuserName(String username);

}
