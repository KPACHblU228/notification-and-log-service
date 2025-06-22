package com.example.notificationservice.controller;

import com.example.notificationservice.model.NotificationEvent;
import com.example.notificationservice.sender.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestEmailController {

    @Autowired
    private EmailSender emailSender;

    @GetMapping("/test-email")
    public String testEmail(@RequestParam String to, @RequestParam String message) {
        NotificationEvent event = new NotificationEvent();
        event.setAddress(to);
        event.setMessage(message);
        try {
            emailSender.send(event);
            return "Письмо отправлено на " + to;
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка при отправке: " + e.getMessage();
        }
    }

}
