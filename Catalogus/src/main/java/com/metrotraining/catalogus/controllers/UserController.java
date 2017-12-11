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
		System.out.println("suntem in main " + userRepository.existsByEmailAndPassword(email,password));
		if( userRepository.existsByEmailAndPassword(email,password)) {
			User userLogin = userRepository.findByEmailAndPassword(email,password);
			model.addAttribute("editUser",userLogin);
			return "editUser";
		}
		else {
			model.addAttribute("show", 1);
			return "login";
		}
	}
	
	@RequestMapping( value = "/createAdminAccount", method = RequestMethod.GET )
	public String createAdminAccount() {
		return "createAccount";
	}
	
	@RequestMapping( value = "/createAdminAccount", method = RequestMethod.POST )
	public String createAdminAccountSave(Model model,
			@RequestParam(value="name") String name,   
			@RequestParam(value="email") String email, 
			@RequestParam(value="password") String password) {
//		User savedUser = userService.saveAdmin(user);
		System.out.println(name +" " + email + " " + password);
		User userToSave = new User(name,email,password,UserRole.ADMIN);
		model.addAttribute("user", userToSave);

		
		userService.save(userToSave);
		//model.addAttribute("userType", new ArrayList<UserRole>(Arrays.asList(UserRole.values())) );

	
		
		return "redirect:listTeacher"; // "mainScreen"
		
	}
}
	