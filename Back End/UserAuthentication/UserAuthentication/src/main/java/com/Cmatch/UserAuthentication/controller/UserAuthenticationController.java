package com.Cmatch.UserAuthentication.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Cmatch.UserAuthentication.exception.UserAlreadyExistsException;
import com.Cmatch.UserAuthentication.exception.UserNotFoundException;
import com.Cmatch.UserAuthentication.model.User;
import com.Cmatch.UserAuthentication.service.UserAuthenticationService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserAuthenticationController {
	/*
	 * Autowiring should be implemented for the UserAuthenticationService. (Use
	 * Constructor-based autowiring) Please note that we should not create an object
	 * using the new keyword
	 */
	@Autowired
	private UserAuthenticationService userAuthenticationService;

	public UserAuthenticationController(UserAuthenticationService authicationService) {
		this.userAuthenticationService = userAuthenticationService;
	}

	private static final Logger log = LoggerFactory.getLogger(UserAuthenticationController.class);

	/*
	 * Define a handler method which will create a specific user by reading the
	 * Serialized object from request body and save the user details in the
	 * database. This handler method should return any one of the status messages
	 * basis on different situations: 1. 201(CREATED) - If the user created
	 * successfully. 2. 409(CONFLICT) - If the userId conflicts with any existing
	 * user
	 *
	 * This handler method should map to the URL "/api/v1/auth/register" using HTTP
	 * POST method
	 */
	@PostMapping("/api/v1/auth/register")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		log.info("addUser Started" + user);
		try {
			userAuthenticationService.saveUser(user);
			return new ResponseEntity<String>("registered", HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			e.printStackTrace();
			return new ResponseEntity<>("userId conflicts with any existing user", HttpStatus.CONFLICT);
		}
	}

	/*
	 * Define a handler method which will authenticate a user by reading the
	 * Serialized user object from request body containing the username and
	 * password. The username and password should be validated before proceeding
	 * ahead with JWT token generation. The user credentials will be validated
	 * against the database entries. The error should be return if validation is not
	 * successful. If credentials are validated successfully, then JWT token will be
	 * generated. The token should be returned back to the caller along with the API
	 * response. This handler method should return any one of the status messages
	 * basis on different situations: 1. 200(OK) - If login is successful 2.
	 * 401(UNAUTHORIZED) - If login is not successful
	 *
	 * This handler method should map to the URL "/api/v1/auth/login" using HTTP
	 * POST method
	 */
	@PostMapping("/api/v1/auth/login")
	public ResponseEntity<?> login(@RequestBody User user, HttpSession httpSession) {
		log.info("login Started" + user);
		try {
			User user1 = userAuthenticationService.findByUserIdAndPassword(user.getUserId(), user.getUserPassword());
// valid credentials
// get the token
			String token = getToken(user.getUserId(), user.getUserPassword());
			Map<String, Object> map = new HashMap<>();
			map.put("token", token);
			map.put("user", user1);
			map.put("message", "sucesslly loged in");
			httpSession.setAttribute("loggedInUserId", user1);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (UserNotFoundException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpSession httpSession) {

		log.info("logout Started");
//		if( httpSession.getAttribute("loggedInUserId") != null) {
		httpSession.removeAttribute("loggedInUserId");
		return new ResponseEntity<String>("Successfully loggedOut", HttpStatus.OK);
//		}
//		return new ResponseEntity<String>("User Doesn't exists",HttpStatus.NOT_FOUND);

	}

// Generate JWT token
	private String getToken(String userId, String password) {
		log.info("getToken Started" + userId + password);
		long expirationTime = 10_00_000;
		return Jwts.builder().setSubject(userId).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(SignatureAlgorithm.HS256, "secretKey").compact();

	}
}
