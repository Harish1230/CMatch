package com.cgi.favorites.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.favorites.exception.FavoritesAlreadyExsistsException;
import com.cgi.favorites.model.Favorites;
import com.cgi.favorites.services.FavoritesService;

@RestController
@CrossOrigin("http://localhost:4200")
public class FavoritesController {
	private FavoritesService favoritesService;
	@Autowired
	public FavoritesController(FavoritesService favoritesService) {
		super();
		this.favoritesService = favoritesService;
	}
	private static final Logger log = LoggerFactory.getLogger(FavoritesController.class);

	@PostMapping("/api/v1/favorites")
	public ResponseEntity<?> saveFavorites(@RequestBody Favorites favorites) {
		log.info("Save Favourite Started"+favorites);
		try {
			favoritesService.saveFavorites(favorites);
			return new ResponseEntity<Favorites>(favorites, HttpStatus.CREATED);
		} catch (FavoritesAlreadyExsistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>("Favorites not found Exception", HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/api/v1/favorites/{userId}/{uniqueId}")
	public ResponseEntity<?> deleteFavorites(@PathVariable String userId,@PathVariable String uniqueId) {
		log.info("deleteFavorites Started"+userId);
		try {
			favoritesService.deleteFavoritesById(userId,uniqueId);
			return new ResponseEntity<String>("Favorites deleted", HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>("Not deleted sucessfully", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/api/v1/favorites/{userId}")
	public ResponseEntity<?> getAllFavorites(@PathVariable String userId) {
					
		//	List<Favorites> matches = favoritesService.getAllFavorites(userId);
		
		log.info("getAllFavorites Started"+userId);

	        Favorites[] matches =favoritesService.getAllFavorites(userId);
	        if(matches!=null)
	        {
			return new ResponseEntity<Favorites[]>(matches, HttpStatus.OK);
			} 
			return new ResponseEntity<String>("No favorites found", HttpStatus.NOT_FOUND);
		
	}
}



