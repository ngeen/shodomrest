package com.shodom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shodom.model.Entry;
import com.shodom.repository.EntryRepository;


@RestController
public class BlogController {
	
	@Autowired
	EntryRepository entryRepository;

    @ResponseBody
    @RequestMapping(value={"/","/index"},method=RequestMethod.GET)    
    public String index() {
        return "İndex Sayfası Burası";
    }
    
    @ResponseBody
    @RequestMapping(value={"/addEntry"},method=RequestMethod.POST)    
    public Entry saveEntry(@RequestBody Entry entry) {
        entryRepository.addBlog(entry);
        return entry;
    }
    
    @ResponseBody
    @RequestMapping(value={"/listEntry"},method=RequestMethod.GET)    
    public Entry getEntry() {
        return new Entry();
    }
    
    @ResponseBody
    @RequestMapping(value = "/getEntry", method = RequestMethod.POST)
    public ResponseEntity<Entry> update(@RequestBody Entry entry) {

        // TODO: call persistence layer to update
        return new ResponseEntity<Entry>(entry, HttpStatus.OK);
    }
    
}
