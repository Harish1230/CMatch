package com.Cmatch.UserAuthentication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.Cmatch.UserAuthentication.model.User;
import com.Cmatch.UserAuthentication.service.UserAuthenticationService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserAuthenticationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserAuthenticationService authenticationService;

	private User user;

	@InjectMocks
	private UserAuthenticationController authenticationController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		user = new User();
		user.setUserId("Jhon123");
		user.setUserName("Jhon");
		user.setUserPassword("Smith");
		user.setMobilenumber("123456");
	}

	@Test
	public void testRegisterUser() throws Exception {

		Mockito.when(authenticationService.saveUser(user)).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user))).andExpect(MockMvcResultMatchers.status().isCreated())
				.andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void testLoginUser() throws Exception {

		String userId = "Jhon123";
		String password = "123456";

		Mockito.when(authenticationService.saveUser(user)).thenReturn(true);
		Mockito.when(authenticationService.findByUserIdAndPassword(userId, password)).thenReturn(user);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user))).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	// Parsing String format data into JSON format
	private static String jsonToString(final Object obj) throws JsonProcessingException {
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			result = jsonContent;
		} catch (JsonProcessingException e) {
			result = "Json processing error";
		}
		return result;
	}

}
