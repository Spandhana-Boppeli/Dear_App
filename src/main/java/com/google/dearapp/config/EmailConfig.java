package com.google.dearapp.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ComponentScan("com.google.dearapp")
public class EmailConfig {
	@Bean
   JavaMailSender  javaMailSender() {
		
       JavaMailSenderImpl jmsi = new JavaMailSenderImpl();
		
		jmsi.setHost("smtp.gmail.com");
		jmsi.setPort(587);
		jmsi.setUsername("spandhana102@gmail.com");
		jmsi.setPassword("ulhf djlr baks fojy"); 
				
		Properties props = jmsi.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		
		return jmsi;
	
	}

}
