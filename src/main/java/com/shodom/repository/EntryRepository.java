package com.shodom.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.shodom.model.Entry;

@Repository
public class EntryRepository {

	Logger logger = LoggerFactory.getLogger(EntryRepository.class);

	@Autowired
	MongoTemplate mongoTemplate;

	public Entry addEntry(Entry e) {
		mongoTemplate.insert(e);
		return e;
	}

	public Entry deleteEntry(String id) {
		Query q = new Query();
		q.addCriteria(Criteria.where("_id").is(id));
		return mongoTemplate.findAndRemove(q, Entry.class);
	}

	public Entry updateEntry(String id, Entry e) {
		Query q = new Query();
		q.addCriteria(Criteria.where("_id").is(id));

		Update update = new Update();
		update.set("title", e.getTitle());
		update.set("content", e.getContent());
		update.set("link", e.getLink());

		return mongoTemplate.findAndModify(q, update, Entry.class);
	}

	public Entry getEntry(String id) {
		Query q = new Query();
		q.addCriteria(Criteria.where("_id").is(id));

		return mongoTemplate.findOne(q, Entry.class);
	}

	public List<Entry> getAll(int page){
	 	Query query = new Query();
	    query.skip(page);
	    query.limit(10);

	    List<Entry> results = mongoTemplate.find(query, Entry.class);
		    
		return results;
	}
}
