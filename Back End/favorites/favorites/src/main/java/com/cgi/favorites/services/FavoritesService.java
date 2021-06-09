package com.cgi.favorites.services;
import java.util.List;

import com.cgi.favorites.exception.FavoritesAlreadyExsistsException;
import com.cgi.favorites.exception.FavoritesNotFoundException;
import com.cgi.favorites.model.Favorites;

public interface FavoritesService {
	public Favorites saveFavorites(Favorites favorites) throws FavoritesAlreadyExsistsException;
	public boolean deleteFavoritesById(String userId,String uniqueId) throws FavoritesNotFoundException;
	
	//List<Favorites> getAllFavoritesByUserId(String userId);

	public Favorites[] getAllFavorites(String userId);

}
