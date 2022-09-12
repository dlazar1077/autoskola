package hr.autoskola.utilities.email.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail,
                                String subject,
                                String body
    ) {
        try {
        	 MimeMessage mimeMessage = mailSender.createMimeMessage();
             MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
             helper.setFrom("autoskola.project@gmail.com");
             helper.setTo(toEmail);
             helper.setText(body,true);
             helper.setSubject(subject);
             mailSender.send(mimeMessage);
        } catch(Exception e) {
        	
        }
    }

    
}
