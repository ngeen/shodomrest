package com.shodom.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.shodom.model.Favorite;

@Repository
public class FavoriteRepository {

	@Autowired
	MongoTemplate mongoTemplate;
	
	public Favorite getFavorite(String userId, String entryId) {
		Query q = new Query();
		q.addCriteria(Criteria.where("userId").is(userId));
		q.addCriteria(Criteria.where("entryId").is(entryId));
		
		return mongoTemplate.findOne(q, Favorite.class);
	}
	
	public List<Favorite> getFavoriteList(String userId){
		Query q = new Query();
		q.addCriteria(Criteria.where("userId").is(userId));
		
		return mongoTemplate.find(q, Favorite.class);
	}
	
	public Favorite addFavorite(Favorite fav){
		mongoTemplate.insert(fav);
		return fav;
	}
	
	public void deleteFavorite(String userId, String entryId){
		Query q = new Query();
		q.addCriteria(Criteria.where("userId").is(userId));
		q.addCriteria(Criteria.where("entryId").is(entryId));
		
		mongoTemplate.findAndRemove(q, Favorite.class);
	}
}
