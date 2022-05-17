package com.cbamz.tvshowrecommender.service.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {
    // Implementation of EmailSender so we can send mails via Java SMTP.
    private final JavaMailSender javaMailSender;
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Override
    @Async // Ensures that this will always be called in separate threads.
    public void send(String to, String email) {
        try {
            // Using MimeMessage to construct email object.
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Account Confirmation");
            helper.setFrom("support@cbamz.com");
            javaMailSender.send(mimeMessage);
        } catch(MessagingException e) {
            LOGGER.error("Error encountered while sending email.", e);
            throw new IllegalStateException("Error encountered while sending email.");
        }
    }
}
