package com.cgi.UserRegistration.service;

import com.cgi.UserRegistration.exception.UserAlreadyExistsException;
import com.cgi.UserRegistration.exception.UserNotFoundException;
import com.cgi.UserRegistration.model.User;

public interface UserRegistrationService {
	User registerUser(User user) throws UserAlreadyExistsException;

    User updateUser(String id,User user) throws UserNotFoundException;

    boolean deleteUser(String id) throws UserNotFoundException;


    User getUserById(String id) throws UserNotFoundException;
    
    boolean saveUser(User user)  throws UserAlreadyExistsException;


}
