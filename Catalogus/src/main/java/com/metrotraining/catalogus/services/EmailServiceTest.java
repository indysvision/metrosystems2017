package com.metrotraining.catalogus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.metrotraining.catalogus.pojos.User;

@Service
public class EmailServiceTest {
	
	private JavaMailSender javaMailSender;

	@Autowired
	public EmailServiceTest(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendNotification(User user) throws MailException {
		// send email
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom("realmetrosystems@gmail.com");
		mail.setSubject("Your account has been created!");
		mail.setText("This is the link to login yo your new account: " + "link var");
		javaMailSender.send(mail);
	}
	

	

}
