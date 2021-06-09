package com.cgi.favourite.service;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.cgi.favorites.controller.FavoritesController;
import com.cgi.favorites.exception.FavoritesAlreadyExsistsException;
import com.cgi.favorites.exception.FavoritesNotFoundException;
import com.cgi.favorites.model.Favorites;
import com.cgi.favorites.services.FavoritesService;

public class FavouriteServiceTest {

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
	public void addFavouriteSuccess() throws FavoritesAlreadyExsistsException {
	}

	@Test(expected = FavoritesAlreadyExsistsException.class)
	public void addFavouriteFailure() throws FavoritesAlreadyExsistsException {
	}

	@Test(expected = FavoritesNotFoundException.class)
	public void deleteFavourite() throws FavoritesNotFoundException {
	}

	@Test
	public void getAllFavourites() {
	}

}
