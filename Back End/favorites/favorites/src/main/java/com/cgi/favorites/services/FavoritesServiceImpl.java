package com.cgi.favorites.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.favorites.controller.FavoritesController;
import com.cgi.favorites.exception.FavoritesAlreadyExsistsException;
import com.cgi.favorites.exception.FavoritesNotFoundException;
import com.cgi.favorites.model.Favorites;
import com.cgi.favorites.repository.FavoritesRepository;
@Service
public class FavoritesServiceImpl implements FavoritesService{
	
	private static final Logger log = LoggerFactory.getLogger(FavoritesServiceImpl.class);

	private FavoritesRepository favoritesRepo;
	@Autowired
	public FavoritesServiceImpl(FavoritesRepository favoritesRepo) {
		super();
		this.favoritesRepo = favoritesRepo;
	}
private Favorites favorites;
	@Override
	public Favorites saveFavorites(Favorites favorites) throws FavoritesAlreadyExsistsException {
//		Favorites addedFavorites=favoritesRepo.findOneByUniqueIdAndUserId(favorites.getUniqueId(), favorites.getUserId());
//		if(addedFavorites!=null){
//			throw new FavoritesAlreadyExsistsException("favorites already exsists");
//		}
//		favoritesRepo.insert(favorites);
//		return true;
		
		
		if (favoritesRepo.findOneByUniqueIdAndUserId(favorites.getUniqueId(), favorites.getUserId()).isPresent()) {
			throw new FavoritesAlreadyExsistsException("favourite already exists");
		}
		return favoritesRepo.insert(favorites);

	}
	
	@Override
	public boolean deleteFavoritesById(String userId, String uniqueId) throws FavoritesNotFoundException {
		try {
			favoritesRepo.deleteByUserIdAndUniqueId(userId,uniqueId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}	

//	@Override
//	public List<Favorites> getAllFavoritesByUserId(String userId) {
//		// TODO Auto-generated method stub
//		return favoritesRepo.findAllByUserId(userId);
//	}	
	
	@Override
	public Favorites[] getAllFavorites(String userId) {
		// TODO Auto-generated method stub
		return favoritesRepo.findAllByUserId(userId);
	}

}
