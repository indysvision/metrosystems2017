package com.metrotraining.catalogus.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.metrotraining.catalogus.pojos.User;
import com.metrotraining.catalogus.pojos.UserRole;
import com.metrotraining.catalogus.pojos.UserStatus;
import com.metrotraining.catalogus.services.UserService;

@Controller
public class MainController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/return", method = RequestMethod.GET)
	public String back(@RequestParam (value="id") long id, @RequestParam (value="mode") String mode,
			Model model,
			RedirectAttributes redirectAttributes) {
		if (mode.equals("exit")) {
			redirectAttributes.addAttribute("id", id);
			redirectAttributes.addAttribute("mode", "view");
		}
		return "redirect:prof";
	}
	@RequestMapping(value = "/prof", method = RequestMethod.GET)
	public String mainPage(@RequestParam (value="id") long id, @RequestParam (value="mode") String mode,
			Model model) {
		model.addAttribute("id", id);
		model.addAttribute("mode", mode);
		
		if (mode.equals("add")) {
			String defaultSource = ".\\images\\cat.jpg";
		       File file = new File(defaultSource);
		        byte[] bFile = new byte[(int) file.length()];
		 
		        try {
		            FileInputStream fileInputStream = new FileInputStream(file);
		            fileInputStream.read(bFile);
		            fileInputStream.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		 
		        User user = new User("name","cat1", "desc", "a@b.c",bFile, "", UserRole.ADMIN, 19860515,20170505,"parola", UserStatus.ACTIVE);
		 
		        userService.save(user);

		}
		else if (mode.equals("view")) {
	        User user = userService.getUser(id);
	        
	        try{      
	            String encodedFile = Base64.getEncoder().encodeToString(user.getPhoto());
	            model.addAttribute("users", user);
	            model.addAttribute("imagine", encodedFile);
	        }catch(Exception e){
	            e.printStackTrace();
	        }
		}
		
	return "prof";
	}
	
	@RequestMapping(value = "/editare", method = RequestMethod.GET)
	public String editUser(@RequestParam (value="id") long id, 
			 @RequestParam (value="mode") String mode,
			 @RequestParam (value="originalMode") String originalMode,
			 @RequestParam (value="newName", required=false) String newName,
			 @RequestParam (value="newCat", required=false) String newCat,
			 @RequestParam (value="newDesc", required=false) String newDesc,
			 @RequestParam (value="newEmail", required=false) String newEmail,
			 @RequestParam (value="newPhotoUrl", required=false) String newPhotoUrl,
			 Model model) {
		
		    model.addAttribute("id", id);
		    model.addAttribute("mode", mode);
		    model.addAttribute("originalMode", originalMode);
		
			if (mode.equals("edit")) {
		        User user = userService.getUser(id);
		        model.addAttribute("editUser", user);

		        if (newName != null) {
			        user.setName(newName);	
		        }
		        if (newCat != null) {
		        	user.setCategory(newCat);
		        }
		        if (newDesc != null) {
		        	user.setDescription(newDesc);
		        }
		        if (newEmail != null) {
		        	user.setEmail(newEmail);
		        }
		        if (newPhotoUrl!= null) {
				    File file = new File(newPhotoUrl);
			        byte[] bFile = new byte[(int) file.length()];
			 
			        try {
			            FileInputStream fileInputStream = new FileInputStream(file);
			            fileInputStream.read(bFile);
			            fileInputStream.close();
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			        user.setPhoto(bFile);
			        user.setPhotoUrl(newPhotoUrl);
		        }
		        userService.save(user);

			}
			return "editUser";
	}
	
}
