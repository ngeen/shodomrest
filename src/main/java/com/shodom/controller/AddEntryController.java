package com.shodom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shodom.model.Entry;
import com.shodom.repository.EntryRepository;

@Controller
public class AddEntryController {

	@Autowired
	EntryRepository entryRepository;
	
	@RequestMapping(value={"/addEntry"},method=RequestMethod.GET)  
    public String addEntry(Model model) {
		model.addAttribute("entry", new Entry());
		return "addEntry";
    }
    
	@RequestMapping(value={"/addEntry"},method=RequestMethod.POST)  
    public String addEntryAction(@ModelAttribute Entry entry) {
        return "index";
    }
}
