package com.Cmatch.UserAuthentication.service;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Cmatch.UserAuthentication.controller.UserAuthenticationController;
import com.Cmatch.UserAuthentication.exception.UserAlreadyExistsException;
import com.Cmatch.UserAuthentication.exception.UserNotFoundException;
import com.Cmatch.UserAuthentication.model.User;
import com.Cmatch.UserAuthentication.repository.UserAuthenticationRepository;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    /*
	 * Autowiring should be implemented for the UserAuthenticationRepository. (Use
	 * Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	
	private static final Logger log = LoggerFactory.getLogger(UserAuthenticationController.class);

	
	@Autowired
    private UserAuthenticationRepository userAutheticationRepository;	

     /*
	 * This method should be used to validate a user using userId and password.
	 *  Call the corresponding method of Respository interface.
	 * 
	 */
    @Override
    public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {

      
    	User user = userAutheticationRepository.findByUserIdAndUserPassword(userId, password);
        if (user == null)
            throw new UserNotFoundException("UserId and Password mismatch");
        
        return user;

    }

	/*
	 * This method should be used to save a new user.Call the corresponding method
	 * of Respository interface.
	 */

    @Override
    public boolean saveUser(User user) throws UserAlreadyExistsException {
       
       try {
		userAutheticationRepository.findById(user.getUserId()).get();
		throw new UserAlreadyExistsException("User with ID Already exists");
	} catch (NoSuchElementException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		userAutheticationRepository.save(user);
    	return true;
	} 
    }
}

