package com.shodom.controller;

import java.io.File;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;
import com.shodom.model.Entry;
import com.shodom.repository.EntryRepository;

@Controller
public class WebController {
	
	@Autowired
	EntryRepository entryRepository;

    @ResponseBody
    @RequestMapping(value={"/403"},method=RequestMethod.GET)    
    public String deniedPage() {
		return "ah be paşam";
    }
    
    @RequestMapping(value={"/404"},method=RequestMethod.GET)    
    public String notFoundPage() {
		return "notFound";
    }
    
    @ResponseBody
    @RequestMapping(value={"/robots.txt"},method=RequestMethod.GET)    
    public String robots() {
    	String robots = "User-agent: * \r\nAllow: / \r\nSitemap: http://sitemap.shodom.com/index.xml";
		return robots;
    }
    
    @RequestMapping(value={"/login"},method=RequestMethod.GET)    
    public String userLogin() {
		return "login";
    }
    
    @ResponseBody
    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/siteHaritasi")
	public ResponseEntity<String> create() {
    	try {
    		WebSitemapGenerator wsg = WebSitemapGenerator.builder("http://shodom.com", new File("/var/www/html/sitemap"))
					.fileNamePrefix("index") // name of the generated sitemap
					.build();
    		for (Entry entry : entryRepository.getAllPublished()) {
    			WebSitemapUrl url = new WebSitemapUrl.Options("http://shodom.com/detail/"+entry.getUrlRoute()).lastMod(entry.getPublishDate().toDate()).priority(1.0).changeFreq(ChangeFreq.HOURLY).build();
    			wsg.addUrl(url);
			}
			wsg.write();
		} catch (MalformedURLException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
    	
    	return new ResponseEntity<String>("Harita Oluşturuldu",HttpStatus.OK);
	}
}
