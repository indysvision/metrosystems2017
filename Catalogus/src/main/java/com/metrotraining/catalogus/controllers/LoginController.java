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
import com.metrotraining.catalogus.pojos.Mail;

@Controller
public class LoginController {
	
	
	@Autowired
    private EmailService emailService;

	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("mode", "main");
		return "addTeacher";
	}*/
	
	@RequestMapping(value = "/editTeacher", method = RequestMethod.GET)
	public String editTeacherPart(Model model) {
		model.addAttribute("mode", "main");
		return "addTeacher";
	}
	
	@RequestMapping(value = "/listTeacher", method = RequestMethod.GET)
	public String listTeacher(Model model) {
		model.addAttribute("mode", "listTeacher");
		return "listEditTeacher";
	}
	
	@RequestMapping(value = "/addTeacher", method = RequestMethod.GET)
	public String saveUser(@RequestParam(value = "name") String name,
			@RequestParam(value = "emailCreate") String emailCreate,
			@RequestParam(value = "category") String category,
			 Model model) {

		

	        Mail mail = new Mail();
	        mail.setFrom("no-reply@memorynotfound.com");
	        mail.setTo(emailCreate);
	        mail.setSubject("You are invited to join class");

	        Map emailModel = new HashMap();
	        emailModel.put("connect_string", "http://localhost:8090/activateTeacher");
	        emailModel.put("name", name);
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
		
		
		
			model.addAttribute("mode", "mailSent");
		return "addTeacher";
	}

	@RequestMapping(value = "/activateTeacher", method = RequestMethod.GET)
	public String activateTeacher(Model model) {
		
		return "activateTeacher";
	}
	
	@RequestMapping(value = "/return", method = RequestMethod.GET)
	public String returnLoginPage(Model model) {
		model.addAttribute("mode", "main");
		return "addTeacher";
	}
}
