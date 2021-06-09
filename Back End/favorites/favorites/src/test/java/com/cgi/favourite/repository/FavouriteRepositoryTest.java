package com.cgi.favourite.repository;

import java.util.NoSuchElementException;
import org.springframework.test.context.junit4.SpringRunner;

import com.cgi.favorites.model.Favorites;
import com.cgi.favorites.repository.FavoritesRepository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

public class FavouriteRepositoryTest {

	@Autowired
	private FavoritesRepository favouriteRepository;

	public Favorites favourite;

	@Before
	public void setUp() throws Exception {

		favourite = new Favorites();
		favourite.setFavId("800787609");
		favourite.setUniqueId("9711");
		favourite.setTeam1("CSK");
		favourite.setTeam2("RCB");
		favourite.setUserId("226");

	}

	@After
	public void tearDown() throws Exception {

		favouriteRepository.deleteAll();
	}

	@Test
	public void addFavouriteTest() {
	}

	@Test(expected = NoSuchElementException.class)
	public void deleteFavouriteTest() {
	}

	@Test
	public void getAllFavourites() {
	}

}
