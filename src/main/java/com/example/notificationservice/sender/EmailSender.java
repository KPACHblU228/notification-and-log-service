package com.example.notificationservice.sender;

import com.example.notificationservice.model.NotificationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EmailSender implements NotificationSender {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public boolean supports(String channel) {
        return "email".equalsIgnoreCase(channel);
    }

    @Override
    public void send(NotificationEvent event) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("banwordbot@yandex.com");
        message.setTo(event.getAddress());
        message.setSubject("Notification");
        message.setText(event.getMessage());
        mailSender.send(message);
    }


}
