package com.google.dearapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.google.dearapp.entity.User;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
public class EmailService {
	@Autowired
	private JavaMailSender jms;
	
	public void sendFirstEmail(User u) {
		MimeMessage mimeMessage= jms.createMimeMessage();
		
		try {
			MimeMessageHelper helper=new MimeMessageHelper(mimeMessage, true);
			helper.setTo(u.getEmail());
			helper.setSubject("Welcome Dear! Account Created Successfully");
			helper.setText("Dear "+u.getName()+", Your Account has been Created Successfully.Find The Best Matches");
			helper.setText("Dear "+u.getName()+", Your Account has been Created Successfully,and here is your otp  :"+u.getOtp()+" start your Journey and find the best Matches from DEARAPP.Thank You for Registering");
			
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		jms.send(mimeMessage);;
	}

	public int getOTP() {
		// TODO Auto-generated method stub
		int otp=0;
		while(otp<=999)
			otp=(int)(Math.random()*100000);
		return otp;
	}

}
