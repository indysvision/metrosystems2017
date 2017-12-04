package com.metrotraining.catalogus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrotraining.catalogus.pojos.User;
import com.metrotraining.catalogus.services.EmailServiceTest;

@RestController
public class EmailController {
	
	@Autowired
	private EmailServiceTest emailServiceTest;
	
	@RequestMapping("/emailSignUp")
	public String signUp() {
		return "Please sign up!";
	}

	@RequestMapping("/emailSignUpSuccess")
	public String signUpSuccess() {
		// create user
		User user = new User();
		user.setName("marc");
		user.setEmail("alexandru.marcu01@metrosystems.net");
		
		// send a notification
		try {
			emailServiceTest.sendNotification(user);
		} catch(MailException e) {
			// catch error
			System.out.println(e);
		}
		
		return "Thank you for registering.";
	}
}
