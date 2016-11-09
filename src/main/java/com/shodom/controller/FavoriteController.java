package com.shodom.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shodom.model.Entry;
import com.shodom.model.Favorite;
import com.shodom.model.WebUser;
import com.shodom.repository.EntryRepository;
import com.shodom.repository.FavoriteRepository;
import com.shodom.repository.WebUserRepository;

@Controller
@RequestMapping(value={"/favorite"},method=RequestMethod.POST)
public class FavoriteController {
	
	@Autowired
	FavoriteRepository favoriteRepository;
	
	@Autowired
	WebUserRepository webUserRepository;
	
	@Autowired
	EntryRepository entryRepository;
	
	
	public Boolean getFavorite(String userId, String entryId){
		Favorite fav = favoriteRepository.getFavorite(userId, entryId);
		return (fav == null) ? false : true;
	}
	
	@ResponseBody
	@PostMapping("/add")
	public String addFavorite(@ModelAttribute Favorite fav){
		favoriteRepository.addFavorite(fav);
		return "Kayit Eklendi";
	}
	
	@ResponseBody
	@PostMapping("/delete")
	public String deleteFavorite(@ModelAttribute Favorite fav){
		favoriteRepository.deleteFavorite(fav.getUserId(), fav.getEntryId());
		return "Kayit Silindi";
	}
	
	@GetMapping("/list")
	public String listFavorites(Principal principal,Model model){
		WebUser webUser = webUserRepository.getUser(principal.getName());
		List<Favorite> favorites = favoriteRepository.getFavoriteList(webUser.getId());
		List<Entry> entryList = new ArrayList<Entry>();
		for (Favorite favorite : favorites) {
			entryList.add(entryRepository.getEntry(favorite.getEntryId()));
		}
		List<List<Entry>> entries = ListUtils.partition(entryList, 4);
		model.addAttribute("entries",entries);
		return "favoriteList";
	}
}
