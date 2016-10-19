package com.shodom.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.shodom.model.Entry;

@Repository
public class EntryRepository {

	Logger logger = LoggerFactory.getLogger(EntryRepository.class);
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public void addBlog(Entry e){
		mongoTemplate.insert(e);
	}
	
	public void deleteBlog(Entry e){
		mongoTemplate.remove(e);		
	}
	
	public void editBlog(Entry e){
		
	}
}
