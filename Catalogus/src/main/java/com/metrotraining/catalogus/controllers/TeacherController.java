package com.metrotraining.catalogus.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.metrotraining.catalogus.services.EmailService;
import com.metrotraining.catalogus.persistence.UserRepository;
import com.metrotraining.catalogus.pojos.Email;
import com.metrotraining.catalogus.pojos.User;
import com.metrotraining.catalogus.pojos.UserRole;
import com.metrotraining.catalogus.pojos.UserStatus;

@Controller
public class TeacherController {

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserRepository userList;

	@RequestMapping(value = "/editTeacher", method = RequestMethod.GET)
	public String editTeacherPart(@RequestParam(value = "teacherId", required = false) Long id, Model model) {
		
		if (id != null) {
			model.addAttribute("teacher", userList.findById(id));
			model.addAttribute("mode", "edit");
		}
		else {
			model.addAttribute("mode", "invite");
		}
		return "listEditTeacher";
	}

	@RequestMapping(value = "/listTeacher", method = RequestMethod.GET)
	public String listTeacher(@RequestParam(value = "teacherId", required = false) Long id, Model model) {
		if (id != null) {
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
			@RequestParam(value = "teacherId", required = false) Long id, Model model) {
		
		System.out.println("id: " + id);
		if (id == null) {

			userList.save(new User(name, category, description, emailCreate, null, null, UserRole.TEACHER, 0, 0,
					"dummy_pasword", UserStatus.PENDING));

			Email email = new Email();
			email.setFrom("no-reply@memorynotfound.com");
			email.setTo(emailCreate);
			email.setSubject("You are invited to join class");

			Map emailModel = new HashMap();
			emailModel.put("connect_string", "http://localhost:8090/activateTeacher/"+userList.findByEmail(emailCreate).getId());
			emailModel.put("name", name);
			email.setModel(emailModel);

			try {
				emailService.sendSimpleMessage(email, true);
			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			model.addAttribute("mode", "mailSent");
		} else {
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

	@RequestMapping(value = "/activateTeacher/{id}", method = RequestMethod.GET)
	public String activateTeacher(Model model, @PathVariable Long id ,RedirectAttributes redirectAttributes ) {
		User usr_activ = userList.findById(id);
		usr_activ.setStatus(UserStatus.ACTIVE);
		userList.save(usr_activ);
		
		redirectAttributes.addAttribute("id", id);
        redirectAttributes.addAttribute("mode", "view");
        
		return "redirect:/prof";
	}
	
	@RequestMapping(value = "/sendMail", method = RequestMethod.GET)
	public String sendMail(Model model) {

		return "sendMail";
	}
	
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public String emailSent(Model model,
			@RequestParam(value="email_list") String email_list,   
			@RequestParam(value="notes") String notes ) {
		
		System.out.println(email_list + "      "+notes);

		String[] result = email_list.split(";");

        for(String s : result){
            System.out.println(s);
            Email email = new Email();
			email.setFrom("no-reply@memorynotfound.com");
			email.setTo(s);
			email.setSubject("new message");

			Map emailModel = new HashMap();
			emailModel.put("mesaj", notes);
			email.setModel(emailModel);
			try {
				emailService.sendSimpleMessage(email, false);
			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}  
        }	
		return "sendMail";
	}
	

}
