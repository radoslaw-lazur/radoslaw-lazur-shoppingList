package com.shoppinglist.service;

import com.shoppinglist.domain.Mail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class SimpleEmailService {
    private JavaMailSender javaMailSender;

    public void send(final Mail mail) {
        log.info("Starting e-mail preparation...");
        try {
            SimpleMailMessage mailMessage = createMailMessege(mail);
            javaMailSender.send(mailMessage);
            log.info("E-mail has been sent");
        } catch (MailException e) {
            log.info("Failed to process e-mail sending: {}", e.getMessage(), e);
        }
    }

    public SimpleMailMessage createMailMessege(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        if (mail.getToCc() != null && !mail.getToCc().equals("")){
            mailMessage.setCc(mail.getToCc());
        }
        return mailMessage;
    }
}
