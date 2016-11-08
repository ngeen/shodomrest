package com.shodom.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.shodom.model.Comment;

@Repository
public class CommentRepository {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public Comment addComment(Comment e) {
		mongoTemplate.insert(e);
		return e;
	}

	public Comment deleteComment(String id) {
		Query q = new Query();
		q.addCriteria(Criteria.where("_id").is(id));
		return mongoTemplate.findAndRemove(q, Comment.class);
	}

	public Comment showComment(String id) {
		Query q = new Query();
		q.addCriteria(Criteria.where("_id").is(id));

		Update update = new Update();
		update.set("showFlg", "1");

		return mongoTemplate.findAndModify(q, update, Comment.class);
	}

	public Comment getComment(String id) {
		Query q = new Query();
		q.addCriteria(Criteria.where("_id").is(id));

		return mongoTemplate.findOne(q, Comment.class);
	}
	
	public Comment getCommentByUrlRoute(String urlRoute){
		Query q = new Query();
		q.addCriteria(Criteria.where("urlRoute").is(urlRoute));

		return mongoTemplate.findOne(q, Comment.class);
	}

	public List<Comment> getAllComments(int page){
	 	Query query = new Query();
	    query.skip(page);
	    query.limit(20);
	    query.with(new Sort(new Order(Direction.DESC, "publishDate")));

	    List<Comment> results = mongoTemplate.find(query, Comment.class);
		    
		return results;
	}
	
	public List<Comment> getAllShowComments(int page){
	 	Query query = new Query();
	    query.skip(page);
	    query.limit(20);
	    query.with(new Sort(new Order(Direction.DESC, "publishDate")));

	    List<Comment> results = mongoTemplate.find(query, Comment.class);
		    
		return results;
	}

}
