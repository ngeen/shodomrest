package com.shodom.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shodom.model.Entry;
import com.shodom.model.WebUser;
import com.shodom.repository.EntryRepository;
import com.shodom.repository.WebUserRepository;

@Controller
public class DetailController {

	@Autowired
	EntryRepository entryRepository;

	@Autowired
	WebUserRepository webUserRepository;

	@Autowired
	CommentController commentController;

	@Autowired
	FavoriteController favoriteController;

	@GetMapping("/detail/{urlRoute}")
	public String detailEntry(@PathVariable("urlRoute") String urlRoute, Model model, Principal principal) {
		Entry e = entryRepository.getEntryByUrlRoute(urlRoute);
		Boolean favori = false;
		if (principal != null) {
			WebUser webUser = webUserRepository.getUser(principal.getName());
			favori = favoriteController.getFavorite(webUser.getId(), e.getId());
			model.addAttribute("user",webUser);
		}
		model.addAttribute("entry", e);
		model.addAttribute("comments", commentController.comments(e.getId()));
		model.addAttribute("favori", favori);
		return "detail";
	}
}
