package com.cgi.UserRegistration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.UserRegistration.model.User;

@Repository
public interface UserRegistrationRepository extends JpaRepository<User, String>{

	
	//User findByUsernameAndPassword(String username, String password);

}
