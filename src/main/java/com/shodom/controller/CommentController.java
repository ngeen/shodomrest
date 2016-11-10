package com.shodom.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shodom.model.Comment;
import com.shodom.repository.CommentRepository;

@Controller
public class CommentController {

	@Autowired
	CommentRepository commentRepository;
	
	public List<Comment> comments(String entryId){ 
		return commentRepository.getAllConfirmedComments(0, entryId);
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
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/confirmComments")
	public String confirmComments(Model model){
		List<Comment> comments = commentRepository.getAllComments();
		model.addAttribute("comments", comments);
		return "confirmComments";
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/confirmComment/{commentId}")
	public String confirmComment(@PathVariable("commentId") String commentId){
		commentRepository.confirmComment(commentId);
		return "redirect:/confirmComments";
	}
	
}
