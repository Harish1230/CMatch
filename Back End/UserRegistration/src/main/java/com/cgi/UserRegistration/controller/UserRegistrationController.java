
package com.cgi.UserRegistration.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cgi.UserRegistration.exception.UserAlreadyExistsException;
import com.cgi.UserRegistration.exception.UserNotFoundException;
import com.cgi.UserRegistration.model.User;
import com.cgi.UserRegistration.service.UserRegistrationService;

@RestController
@CrossOrigin("*")
public class UserRegistrationController {

	@Autowired
	private UserRegistrationService userRegistrationService;

	public UserRegistrationController(UserRegistrationService registrationService) {
		this.userRegistrationService = userRegistrationService;
	}

	private static final Logger log = LoggerFactory.getLogger(UserRegistrationController.class);

	@PostMapping("/user")

	public ResponseEntity<?> createUser(@RequestBody User user) {

		log.info("createUser Started" + user);
		try {
			userRegistrationService.registerUser(user);
			return new ResponseEntity<String>("Successfully registered", HttpStatus.CREATED);

		} catch (UserAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@PutMapping("/user/secure/{id}")
	public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User user, HttpSession httpSession) {
		log.info("updateUser Started" + id);
		try {
//			if(httpSession.getAttribute("loggedInUserId")==null || !id.equals(httpSession.getAttribute("loggedInUserId"))) {
//				return new ResponseEntity<String>("UnAuthorized User", HttpStatus.UNAUTHORIZED);
//			}
			User userUpdated = userRegistrationService.updateUser(user.getUserId(), user);
			if (userUpdated != null) {
				return new ResponseEntity<User>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
			}
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/secure/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable String id, HttpSession httpSession) {
		log.info("deleteUser Started" + id);
		try {

			if (!userRegistrationService.deleteUser(id))
				throw new Exception("not found");
			else
				return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/api/v1/user/secure/{id}")
	public ResponseEntity<?> getUserId(@PathVariable String id) {
		log.info("getUserId Started" + id);
		try {
			User user = userRegistrationService.getUserById(id);
			if (user == null) {
				return new ResponseEntity<String>("not found", HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<String>("successfull", HttpStatus.OK);
			}
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>("not found", HttpStatus.NOT_FOUND);
		}

	}

}
