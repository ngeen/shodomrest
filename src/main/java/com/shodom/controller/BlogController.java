package com.shodom.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shodom.model.Entry;
import com.shodom.repository.EntryRepository;


@Controller
public class BlogController {
	
	@Autowired
	EntryRepository entryRepository;


    @RequestMapping(value={"/","/index"},method=RequestMethod.GET)    
    public String index(Model model) {
    	String tarih = new Date().toString();
		model.addAttribute("ipGirisleri", "");
		model.addAttribute("ipTarih", tarih);
		return "index";
    }
    
    @ResponseBody
    @RequestMapping(value={"/listEntry/{page}"},method=RequestMethod.POST)    
    public List<Entry> getEntry(@PathVariable("page") Integer page) {
        return entryRepository.getAll(page);
    }
    
    @ResponseBody
    @RequestMapping(value = "/getEntry/{id}", method = RequestMethod.POST)
    public Entry getEntry(@PathVariable("id") String id) {
    	return	entryRepository.getEntry(id);       
    }
    
    @ResponseBody
    @RequestMapping(value={"/addEntry"},method=RequestMethod.POST)    
    public Entry addEntry(@RequestBody Entry entry) {
        return entryRepository.addEntry(entry);
    }
    
    @ResponseBody
    @RequestMapping(value = "/deleteEntry/{id}", method = RequestMethod.POST)
    public Entry deleteEntry(@PathVariable("id") String id){
    	return entryRepository.deleteEntry(id);
    }
    
    @ResponseBody
    @RequestMapping(value = "/updateEntry/{id}", method = RequestMethod.POST)
    public Entry updateEntry(@PathVariable("id") String id, @RequestBody Entry entry){
    	return entryRepository.updateEntry(id, entry);
    }
    
}
