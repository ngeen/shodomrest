package com.shodom.controller;

import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shodom.model.Entry;
import com.shodom.repository.EntryRepository;


@Controller
//@Secured({"ROLE_USER"})
public class IndexController {
	
	@Autowired
	EntryRepository entryRepository;


    @RequestMapping(value={"/", "/page/{page}"},method=RequestMethod.GET)    
    public String index(@RequestParam(required = false, defaultValue = "0", value="page") Integer page, Model model) {
    	int recordCount = 20;
    	int realPage = page*recordCount;
    	List<List<Entry>> entries = ListUtils.partition(entryRepository.getAll(realPage, recordCount), 4);
    	model.addAttribute("entries", entries);
		return "index";
    }   
    
    @ResponseBody
    @RequestMapping(value={"/listEntry/{page}"},method=RequestMethod.GET)    
    public List<Entry> getEntry(@PathVariable("page") Integer page) {
    	int recordCount = 20;
    	int realPage = page*recordCount;
        return entryRepository.getAll(realPage, recordCount);
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
