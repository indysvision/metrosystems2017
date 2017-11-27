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

import com.metrotraining.catalogus.services.EmailService;
import com.metrotraining.catalogus.persistence.UserRepository;
import com.metrotraining.catalogus.pojos.Mail;
import com.metrotraining.catalogus.pojos.User;
import com.metrotraining.catalogus.pojos.UserRole;
import com.metrotraining.catalogus.pojos.UserStatus;

@Controller
public class LoginController {
	
	
	@Autowired
    private EmailService emailService;
	
	@Autowired
	private UserRepository userList;

	@RequestMapping(value = "/editTeacher", method = RequestMethod.GET)
    public String editTeacherPart(@RequestParam(value = "teacherId", required = false) Long id,
    		                      Model model) {
		if (id == null) {
        model.addAttribute("mode", "invite");
        
		}
		else {
			model.addAttribute("mode", "invite");
			model.addAttribute("teacher", userList.findById(id));
		}
		return "listEditTeacher";
    }	
	
	@RequestMapping(value = "/listTeacher", method = RequestMethod.GET)
	public String listTeacher(@RequestParam(value = "teacherId", required = false) Long id,
			                  Model model) {
		if (id != null) 
		{
			
			userList.delete(id);
		}
		
		model.addAttribute("mode", "listTeacher");
		model.addAttribute("teacherId", null);
		model.addAttribute("teacherList", userList.findByType(UserRole.TEACHER));
		
		return "listEditTeacher";
	}
	
		
	@RequestMapping(value = "/addTeacher", method = RequestMethod.GET)
	public String saveUser(@RequestParam(value = "name") String name,
			@RequestParam(value = "emailCreate") String emailCreate,
			@RequestParam(value = "category") String category,
			@RequestParam(value = "description") String description,
			@RequestParam(value = "teacherId", required = false ) Long id,
			 Model model) {
		 
		    if (id == null ) {

		    userList.save(new User(name, category, description, emailCreate, null, UserRole.TEACHER,
					2017, 2017, "dummy_pasword", UserStatus.PENDING));

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
		    }
		    else
		    {
		    	User teacher = userList.findById(id);
		    	teacher.setCategory(category);
		    	teacher.setDescription(description);
		    	teacher.setEmail(emailCreate);
		    	teacher.setName(name);
		    	userList.save(teacher);
		    	
		    	model.addAttribute("mode", "listTeacher");
				model.addAttribute("teacherId", null);
				model.addAttribute("teacherList", userList.findByType(UserRole.TEACHER));
		    }
		    
		return "listEditTeacher";
	}

	@RequestMapping(value = "/activateTeacher", method = RequestMethod.GET)
	public String activateTeacher(Model model) {
		
		return "activateTeacher";
	}
	
	
}
