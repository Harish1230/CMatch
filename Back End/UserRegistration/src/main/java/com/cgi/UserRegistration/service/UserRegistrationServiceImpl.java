package com.cgi.UserRegistration.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cgi.UserRegistration.exception.UserAlreadyExistsException;
import com.cgi.UserRegistration.exception.UserNotFoundException;
import com.cgi.UserRegistration.model.User;
import com.cgi.UserRegistration.repository.UserRegistrationRepository;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
	
	@Autowired
	private UserRegistrationRepository repository;
	
	private static Logger log=LoggerFactory.getLogger(UserRegistrationServiceImpl.class);
	
	public UserRegistrationServiceImpl(UserRegistrationRepository repository) {
	       this.repository = repository;
	    }
	
	
	public User registerUser(User user) throws UserAlreadyExistsException {
		log.debug("Starting of the method registerUser");
        if(repository.existsById(user.getUserId())) {
        	log.debug("User Already Exists");
            throw new UserAlreadyExistsException("User Already Exists");
        }
        else
        {
            User newUser=repository.save(user);
            if(newUser!=null) {
            log.debug("Ending of the method");
            return newUser;
            }
            else
            throw new UserAlreadyExistsException("User Already Exists");
        }
    }



	public User updateUser(String userId, User user) throws UserNotFoundException {
		log.debug("Starting of the method updateUser");
		User updUser;
	       try {
	           updUser = repository.findById(userId).get();
	           updUser.setUserName(user.getUserName());
	           updUser.setUserPassword(user.getUserPassword());
	           updUser.setMobilenumber(user.getMobilenumber());
	           
	           updUser.setUserId(user.getUserId());
	           repository.save(updUser);
	       } catch (Exception e) {
	           // TODO Auto-generated catch block
	           throw new UserNotFoundException("User not Found");
	       }
	       return updUser;
    }


	public boolean deleteUser(String userId) throws UserNotFoundException {
		log.debug("Starting of the method deleteUser");
        try {
        	repository.deleteById(userId);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new UserNotFoundException("User not Found");
        }
        return true;
    }
	
//	public String getuserNameById(String userId) throws UserNotFoundException {
//    	User user=repository.findById(userId).get();
//		if(user == null) {
//			throw new UserNotFoundException("user not found");
//		}
//
//		return user.getUserName();
//	}
	
	public User getUserById(String userId) throws UserNotFoundException {
		log.debug("Starting of the method getUserById");
    	User user=repository.findById(userId).get();
		if(user == null) {
			throw new UserNotFoundException("user not found");
		}

		return user;
	}
	
	@Override
	   public boolean saveUser(User user) throws UserAlreadyExistsException {
		log.debug("Starting of the method saveUser");
	       Optional<User> userr = repository.findById(user.getUserId());
	       if (userr.isPresent())
	           throw new UserAlreadyExistsException("User with UserId already exists");
	       repository.save(user);
	       return true;
	   }


	
	

}
