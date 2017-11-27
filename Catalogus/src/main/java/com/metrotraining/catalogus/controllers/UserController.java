package com.metrotraining.catalogus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.metrotraining.catalogus.persistence.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping( value = "/", method =  RequestMethod.GET )
	public String login() {
		return "login";
	}
	
	@RequestMapping( value = "/main", method =  RequestMethod.POST )
	public String main(   @RequestParam(value="email") String email, 
						  @RequestParam(value="password") String password,
						  Model model) {
		
		if( userRepository.existsByEmail(email)) {
			return "main";
		}
		else {
			model.addAttribute("show", 1);
			return "login";
		}
	}
	
	@RequestMapping( value = "/createAdminAccount", method =  RequestMethod.POST )
	public String createAdmin(   @RequestParam(value="name") String name,   
			                     @RequestParam(value="email") String email, 
						         @RequestParam(value="password") String password,
						         Model model) {
		
		if( userRepository.existsByEmail(email)) {
			return "main";
		}
		else {
			model.addAttribute("show", 1);
			return "login";
		}
	}
	

}
