package com.example.notificationservice.sender;

import com.example.notificationservice.model.NotificationEvent;

public interface NotificationSender {
    boolean supports(String channel);
    void send(NotificationEvent event) throws Exception;
}
