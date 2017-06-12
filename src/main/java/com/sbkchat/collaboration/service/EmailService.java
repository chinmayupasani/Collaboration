package com.sbkchat.collaboration.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.sbkchat.collaboration.dto.User;

@Service
public class EmailService {

	// Autowired the mail sender bean here
	
	@Autowired
	private JavaMailSender mailSender;
	
	// email name which is not similar to the user name
	private static String from = "sbkChat";
	
	/**
	 *  approvedUserMessage method will be called using emailService that can be Autowired
	 *  in the AdminController once the user is approved by admin with the given role
	 *  args-User
	 *  requires the user object to fetch the email and other content of the user to send the email
	 *  Similarly we can create other methods for different purposes
	 */
	
	public void approvedUserMessage(User user)
	{
	
		// mime message is used to send email like an HTML page
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		MimeMessageHelper helper = null;
		
		try {
			
			// instantiating the helper and attaching it with mimeMessage
			
			helper = new MimeMessageHelper(mimeMessage,false, "utf-8");
			
			// set up Your HTML message here
			StringBuilder htmlMeg = new StringBuilder();
			
			htmlMeg.append("<h1> Welcome "+ user.getFirstName() + " "+ user.getLastName() + "!</h1>");
			htmlMeg.append("<p> Your account has been activated!</p><br/>");
			htmlMeg.append("<p> Thanks for joining with us!</p><br/>");
			
			// add the HTML content to the mimeMessage
			mimeMessage.setContent(htmlMeg.toString(),"text/html");
			
			// set the subject and recipient of mail
			helper.setTo(user.getEmailId());
			helper.setSubject("WELCOME TO SBKChat");
			helper.setFrom(from);
			
			// send the message
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
	}
}






















