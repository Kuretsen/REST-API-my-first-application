package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleEmailService {
    @Value("${spring.mail.username}")
    private String userName;
    @Value("${spring.mail.password}")
    private String password;

    private final JavaMailSender javaMailSender;

    public void send(final Mail mail) {
        log.info("Starting email preparation...");
        log.info("userName = " + userName);
        log.info("password = " + password);
        try {
            SimpleMailMessage mailMessage = createMailMessage(mail);
            javaMailSender.send(mailMessage);
            log.info("Email has been sent.");
        } catch (MailException e) {
            log.error("Failed to process email sending: " + e.getMessage(), e);
        }
    }

    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
//        Optional<String> mailCc = mail.getToCc();
//        if (mailCc.isPresent()) {
//            mailMessage.setCc(mailCc.get());
//        }
        return mailMessage;
    }
}