package com.metrotraining.catalogus.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.metrotraining.catalogus.persistence.UserRepository;
import com.metrotraining.catalogus.pojos.User;
import com.metrotraining.catalogus.pojos.UserRole;
import com.metrotraining.catalogus.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
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
	
	@RequestMapping( value = "/createAdminAccount", method = RequestMethod.GET )
	public String createAdminAccount(Model model) {
		model.addAttribute("user", new User());
		//model.addAttribute("userType", new ArrayList<UserRole>(Arrays.asList(UserRole.values())) );
		return "createAccount";
	}
	
	@RequestMapping( value = "/createAdminAccount/saved", method = RequestMethod.POST )
	public String createAdminAccountSave(User user) {
		User savedUser = userService.saveAdmin(user);
		return "login" + savedUser.getId(); // "mainScreen"
		
	}
	
	

}
