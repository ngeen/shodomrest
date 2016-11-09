package com.shodom.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shodom.model.Comment;
import com.shodom.repository.CommentRepository;

@Controller
public class CommentController {

	@Autowired
	CommentRepository commentRepository;
	
	public List<Comment> comments(String entryId){ 
		return commentRepository.getAllShowComments(0, entryId);
	}
	
	@ResponseBody
	@PostMapping("/postComment")
	public void saveComment(@ModelAttribute Comment comment,Principal principal){
		comment.setShowFlg("0");
		if(principal == null){
			comment.setUserName("Ziyaretçi");
			
		}else {
			comment.setUserName(principal.getName());
		}
		commentRepository.addComment(comment);

	}
	
}
