package com.mim.planetas.service;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mim.planetas.rest.ClimaResource;

@Service
public class MailService {
	
	final static Logger logger = LoggerFactory.getLogger(ClimaResource.class);

    public void send() {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		try {

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("anything@clima-1303.appspotmail.com","clima-1303"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("miguelmino@gmail.com", "Mr. User"));
			msg.setSubject("Consulta del Clima");

			msg.setSentDate(new Date());
//			Multipart mPart = new MimeMultipart();
//			BodyPart messageBodyPart = new MimeBodyPart();
//			String cont ="hello";
//			messageBodyPart.setContent(cont,"text/plain");
//			mPart.addBodyPart(messageBodyPart);
//			
			
			msg.setText("Se ha realizado una consulta del clima.");
			
			msg.saveChanges();
				
			Transport.send(msg);

		} catch (AddressException e) {
			logger.error("test cron AddressException");
		} catch (MessagingException e) {
			logger.error("test cron MessagingException" + e.getMessage());
			e.printStackTrace();
			
			
		} catch (UnsupportedEncodingException e) {
			logger.error("test cron UnsupportedEncodingException");
		}

    }
}