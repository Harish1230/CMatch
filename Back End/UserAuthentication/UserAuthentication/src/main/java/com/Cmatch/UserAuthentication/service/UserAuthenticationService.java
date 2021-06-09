package com.Cmatch.UserAuthentication.service;

import com.Cmatch.UserAuthentication.exception.UserAlreadyExistsException;
import com.Cmatch.UserAuthentication.exception.UserNotFoundException;
import com.Cmatch.UserAuthentication.model.User;

public interface UserAuthenticationService {

    	/*
	 * Should not modify this interface. You have to implement these methods in
	 * corresponding Impl classes
	 */

    public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;

    boolean saveUser(User user) throws UserAlreadyExistsException;
}
