package com.metrotraining.catalogus.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.metrotraining.catalogus.mail.EmailService;
import com.metrotraining.catalogus.mail.Mail;

@Controller
public class LoginController {
	@Autowired
    private EmailService emailService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Model model) {
		
		return "mainTeacher";
	}
	
	
	@RequestMapping(value = "/addTeacher", method = RequestMethod.GET)
	public String saveUser(@RequestParam(value = "name",required = false) String name,
			@RequestParam(value = "emailCreate",required = false) String emailCreate,
			@RequestParam(value = "category",required = false) String category,
			 Model model) {
		System.out.println("0");

		

	        Mail mail = new Mail();
	        mail.setFrom("no-reply@memorynotfound.com");
	        mail.setTo("moraru.diana90@yahoo.com");
	        mail.setSubject("Sending Email with Thymeleaf HTML Template Example");

	        Map emailModel = new HashMap();
	        emailModel.put("name", "Memorynotfound.com");
	        emailModel.put("location", "Belgium");
	        emailModel.put("signature", "https://memorynotfound.com");
	        mail.setModel(emailModel);

	        try {
				emailService.sendSimpleMessage(mail);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
		model.addAttribute("mode", "createUser");
		return "mainTeacher";
	}

	
}
