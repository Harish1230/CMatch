package com.cgi.UserRegistration.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cgi.UserRegistration.exception.UserAlreadyExistsException;
import com.cgi.UserRegistration.exception.UserNotFoundException;
import com.cgi.UserRegistration.model.User;
import com.cgi.UserRegistration.repository.UserRegistrationRepository;

public class UserRegistrationServiceTest {

	@Mock
	UserRegistrationRepository userRepository;

	User user;

	@InjectMocks
	UserRegistrationServiceImpl userService;

	List<User> userList = null;
	Optional<User> options;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		user = new User();
		user.setUserId("John123");
		user.setUserName("john");
		user.setUserPassword("johnpass");
		userList = new ArrayList<>();
		userList.add(user);

		options = Optional.of(user);

	}

	@Test
	public void registerUserSuccess() throws UserAlreadyExistsException {
		when(userRepository.save((User) any())).thenReturn(user);
		User userSaved = userService.registerUser(user);
		assertEquals(user, userSaved);

	}

	@Test(expected = UserAlreadyExistsException.class)
	public void registerUserFailure() throws UserAlreadyExistsException {
		when(userRepository.save((User) any())).thenReturn(null);
		User userSaved = userService.registerUser(user);
		assertEquals(user, userSaved);

	}

	@Test
	public void updateUser() throws UserNotFoundException {
		when(userRepository.findById(user.getUserId())).thenReturn(options);
		user.setUserName("jeeshu");
		User fetchuser = userService.updateUser(user.getUserId(), user);
		assertEquals(user, fetchuser);

	}

	@Test
	public void deleteUserSuccess() throws UserNotFoundException {
		when(userRepository.findById(user.getUserId())).thenReturn(options);
		boolean flag = userService.deleteUser(user.getUserId());
		assertEquals(true, flag);

	}

	@Test
	public void getUserById() throws UserNotFoundException {

		when(userRepository.findById(user.getUserId())).thenReturn(options);

		User fetchedUser = userService.getUserById(user.getUserId());

		assertEquals(user, fetchedUser);

	}

}
