package com.cgi.favourite.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.cgi.favorites.controller.FavoritesController;
import com.cgi.favorites.model.Favorites;
import com.cgi.favorites.repository.FavoritesRepository;
import com.cgi.favorites.services.FavoritesService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FavouriteControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private FavoritesService favouriteService;
	private FavoritesController favouriteController;

	private Favorites[] allfavourites = null;

	public Favorites favourite;

	@Before
	public void setUp() throws Exception {

		favourite = new Favorites();
		favourite.setFavId("800787609");
		favourite.setUniqueId("9711");
		favourite.setTeam1("CSK");
		favourite.setTeam2("RCB");
		favourite.setUserId("226");
		allfavourites = new Favorites[5];

	}

	@Test
	public void addFavouriteSuccess() throws Exception {
	}

	@Test
	public void addFavouriteFailure() throws Exception {
	}

	@Test
	public void getAllFavouritsByIdSuccess() throws Exception {
	}

	@Test
	public void getAllFavouritsByIdFailure() throws Exception {
	}

	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
