package com.cgi.favorites.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cgi.favorites.model.Favorites;

public interface FavoritesRepository extends MongoRepository<Favorites, String> {

	//Favorites findOneByUniqueIdAndUserId(String uniqueId, String userId);

//	List<Favorites> findAllByUserId(String userId);
	
	public Optional<Favorites> findOneByUniqueIdAndUserId(String uniqueId, String userId);

	public Favorites[] findAllByUserId(String userId);

	void deleteByUserIdAndUniqueId(String userId, String uniqueId);

}
