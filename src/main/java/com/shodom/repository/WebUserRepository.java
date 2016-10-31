package com.shodom.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.shodom.model.WebUser;

@Repository
public class WebUserRepository {

	@Autowired
	MongoTemplate mongoTemplate;
	
	public WebUser getUser(String userName) {
		Query q = new Query();
		q.addCriteria(Criteria.where("userName").is(userName));

		return mongoTemplate.findOne(q, WebUser.class);
	}
	
	public WebUser addUser(WebUser webUser){
		mongoTemplate.insert(webUser);
		return webUser;
	}
}
