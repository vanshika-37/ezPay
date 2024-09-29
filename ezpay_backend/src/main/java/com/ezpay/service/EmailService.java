package com.ezpay.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/*
* @author Subhashree M
* @since 4th September, 2024
*/

@Service
public class EmailService {
	// The mailSender object is injected automatically by Spring, and it's used to send emails.
    @Autowired
    private JavaMailSenderImpl mailSender;
    
    
 // Method to send an email with a given recipient, subject, and body content.
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();// Create a simple email message
        message.setTo(to);             // Set recipient's email address
        message.setSubject(subject);   // Set the subject of the email
        message.setText(body);         // Set the body content of the email
        message.setFrom("chatbot.ezpay@gmail.com");  // Set the sender's email address

        mailSender.send(message);  // Use the mailSender to send the email
    }
}
