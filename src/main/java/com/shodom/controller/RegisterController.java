package com.shodom.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.mkopylec.recaptcha.validation.RecaptchaValidator;
import com.shodom.model.WebUser;
import com.shodom.repository.WebUserRepository;

@Controller
public class RegisterController {

	@Autowired
	WebUserRepository webUserRepository;
	
	@Autowired
	private RecaptchaValidator recaptchaValidator;
	
	@GetMapping("/register") 
    public String registerUser(Model model) {
		WebUser webUser = new WebUser();
		model.addAttribute("user", webUser);
		return "register";
    }
	
	@PostMapping("/addUser")
    public String addUserAction(@ModelAttribute WebUser webUser) {
		if (!recaptchaValidator.validate(webUser.getResponse()).isSuccess()) {
			return "redirect:/register?token";
		}
		
		if(webUser.getPassword().length() <= 0 || webUser.getUserMail().length() <= 0 || webUser.getUserName().length() <= 0){
			return "redirect:/register?null";
		}

		GrantedAuthority roleUser=new SimpleGrantedAuthority("ROLE_USER");
		List<GrantedAuthority> ga = new ArrayList<GrantedAuthority>();
		ga.add(roleUser);
		webUser.setUserRole(ga);
		webUserRepository.addUser(webUser);
		return "redirect:/login";
		
        
    }
	
	@GetMapping("/getUser/{userName}")
	@ResponseBody
	public String getUser(@PathVariable("userName") String userName){
		WebUser wu = webUserRepository.getUser(userName);
		if(wu == null){
			return "0";
		}
		return wu.getId();
	}
}
