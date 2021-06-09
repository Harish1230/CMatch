package com.Cmatch.UserAuthentication.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.Cmatch.UserAuthentication.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserAuthenticationRepositoryTest {

	@Autowired
	private UserAuthenticationRepository autheticationRepository;

	private User user;

	@Before
	public void setUp() throws Exception {
		user = new User();
		user.setUserId("Harish@gmail.com");
		user.setUserPassword("Harish@123");
	}

	@After
	public void tearDown() throws Exception {
		autheticationRepository.deleteAll();
	}

	@Test
	public void testRegisterUserSuccess() {
		autheticationRepository.save(user);
		User object = autheticationRepository.findById(user.getUserId()).get();
		Assert.assertEquals(user.getUserId(), object.getUserId());
	}

	@Test
	public void testLoginUserSuccess() {
		autheticationRepository.save(user);
		User object = autheticationRepository.findById(user.getUserId()).get();
		Assert.assertEquals(user.getUserId(), object.getUserId());
	}

}
