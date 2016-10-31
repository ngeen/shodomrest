package com.shodom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @ResponseBody
    @RequestMapping(value={"/403"},method=RequestMethod.GET)    
    public String deniedPage() {
		return "ah be paşam";
    }
    
    @RequestMapping(value={"/login"},method=RequestMethod.GET)    
    public String userLogin() {
		return "login";
    }
}
