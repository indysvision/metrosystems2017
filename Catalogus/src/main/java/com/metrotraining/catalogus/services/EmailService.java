package com.metrotraining.catalogus.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.metrotraining.catalogus.pojos.Email;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;


    public void sendSimpleMessage(Email email, boolean isInvitation) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

      //  helper.addAttachment("logo.png", new ClassPathResource("logo_metro.gif"));

        Context context = new Context();
        context.setVariables(email.getModel());
        
        String html = templateEngine.process(
        		isInvitation ? "email-template" : "email-template-note",
        				context);
        
        helper.setTo(email.getTo());
        helper.setText(html, true);
        helper.setSubject(email.getSubject());
        helper.setFrom(email.getFrom());

        emailSender.send(message);
    }

}