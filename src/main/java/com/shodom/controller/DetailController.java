package com.shodom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shodom.model.Entry;
import com.shodom.repository.EntryRepository;

@Controller
public class DetailController {

	@Autowired
	EntryRepository entryRepository;
	
	@GetMapping("/detail/{urlRoute}") 
    public String editEntry(@PathVariable("urlRoute") String urlRoute,Model model) {
		Entry e = entryRepository.getEntryByUrlRoute(urlRoute);
		model.addAttribute("entry", e);
		return "detail";
    }
}
