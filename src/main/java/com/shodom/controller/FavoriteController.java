package com.shodom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shodom.model.Favorite;
import com.shodom.repository.FavoriteRepository;

@Controller
public class FavoriteController {
	
	@Autowired
	FavoriteRepository favoriteRepository;
	
	public Boolean getFavorite(String userId, String entryId){
		Favorite fav = favoriteRepository.getFavorite(userId, entryId);
		return (fav == null) ? false : true;
	}
	
	@ResponseBody
	@PostMapping("/addFavorite")
	public String addFavorite(String userId, String entryId){
		Favorite fav = new Favorite();
		fav.setEntryId(entryId);
		fav.setUserId(userId);
		favoriteRepository.addFavorite(fav);
		return "Kayit Eklendi";
	}
	
	@ResponseBody
	@PostMapping("/deleteFavorite")
	public String deleteFavorite(String userId, String entryId){
		favoriteRepository.deleteFavorite(userId, entryId);
		return "Kayit Silindi";
	}
}
